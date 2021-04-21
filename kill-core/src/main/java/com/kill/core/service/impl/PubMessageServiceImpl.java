package com.kill.core.service.impl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import com.kill.core.constant.BatchOrderConstant;
import com.kill.core.constant.PushMessageConstant;
import com.kill.core.entity.*;
import com.kill.core.mapper.RegionMapper;
import com.kill.core.service.PubMessageService;
import com.kill.core.service.impl.process.utils.ReadSchoolDataExcelUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


/**
 * <pre>
 * +--------+---------+-----------+---------+
 * |                                        |
 * +--------+---------+-----------+---------+
 * </pre>
 *
 * @author wangjian
 * @since 2021/02/19 15:49:23
 */
@Service
@Slf4j
@AllArgsConstructor
public class PubMessageServiceImpl implements PubMessageService {

    private RestTemplate restTemplate;

    private RedisTemplate redisTemplate;

    private RegionMapper regionMapper;

    /**
     * Excel中名称数据长度限制
     */
    public static final Integer SCHOOL_NAME_LENGTH = 50;

    public static final Integer SCHOOL_ADRESS_NAME_LENGTH = 200;

    @Override
    public void sendSubMessage(String openId) {
        String authToken = null;
        try {
            authToken = getAuthToken();
        } catch (Exception e) {
            log.error("获取微信token失败：{}", e.getMessage());
        }
        log.info("获取微信token:{}", authToken);

        String json = "{\"touser\":\"" + openId + "\",\"template_id\":\"MWKPsaBbUFN8WJ1bIjfbVT_qy9fh8sARUP0LM5zoCBg\",\"page\":\"index\",\"data\":{\"name3\":{\"value\":\"王小左\"},\"thing4\":{\"value\":\"这就是一个提示信息\"},\"thing2\":{\"value\":\"多喝热水\"}}}";

        String url = StrUtil.format(PushMessageConstant.SEND_MESSAGE_URL, authToken);
        sendMessage(json, url);

    }

    private void sendMessage(String params, String url) {
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        HttpEntity<String> formEntity = new HttpEntity<>(params, headers);
        ResponseEntity<String> resultResponseEntity = restTemplate.postForEntity(url, formEntity, String.class);
        log.info("发送订阅消息返回：{}", resultResponseEntity.getBody());
    }

    @Override
    public String getAuthToken() {
        String url = StrUtil.format(PushMessageConstant.GET_TOKEN_URL, PushMessageConstant.APPID, PushMessageConstant.APP_SECRET);
        WeChatToken weChatToken = restTemplate.getForObject(url, WeChatToken.class);
        return weChatToken.getAccess_token();
    }


    @Override
    public String importHospitalExcelFile(MultipartFile file, String schoolName) {
        List<BatchSchoolExcel> batchSchoolExcels = analysisExcelSchoolData(file);

        List<Region> regionDOS = regionMapper.selectList(null);


        log.info("读取Excel的大小为：{}，文件名称:{}", batchSchoolExcels.size(), file.getOriginalFilename());
        // 用布隆过滤器耗时
        long startTime = System.currentTimeMillis();
        List<String> schoolNameList = new ArrayList<>();
        BloomFilter<CharSequence> bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), 200000, 1E-7);
        batchSchoolExcels.forEach(schoolExcelData -> {
            // 获取省对应的code
            Long provinceCode = null, cityCode = null, countryCode = null;

            for (Region region : regionDOS) {
                if (region.getName().equals(schoolExcelData.getProvinceName())) {
                    provinceCode = region.getId();
                }
            }
            if (provinceCode != null) {
                for (Region region : regionDOS) {
                    if (region.getPid().equals(provinceCode) && region.getName().equals(schoolExcelData.getCityName())) {
                        cityCode = region.getId();
                    }
                }
            }
            if (provinceCode != null && cityCode != null) {
                for (Region region : regionDOS) {
                    if (region.getPid().equals(cityCode) && region.getName().equals(schoolExcelData.getCountryName())) {
                        countryCode = region.getId();
                    }
                }
            }
            String nameKey = StrUtil.format("{}-{}-{}-{}",
                schoolExcelData.getSchoolName(), provinceCode,
                countryCode, cityCode);
            bloomFilter.put(nameKey);
            schoolNameList.add(nameKey);
        });


        return StrUtil.format("bloomFilter是否存在：{},List是否存在{},检测共耗时：{}",
            bloomFilter.mightContain(schoolName),
            schoolNameList.contains(schoolName),
            System.currentTimeMillis() - startTime);
    }


    @Override
    public String createOrderNumber() {
        // key的前缀
        String prefix = "B" + DateUtil.format(new Date(), "yyyyMMdd");
        // redis自增ID
        Long num = redisTemplate.opsForValue().increment(BatchOrderConstant.ORDER_NUMBER_REDIS_KEY, 1L);
        // 数据补全
        return prefix + String.format("%06d", num);
    }

    private List<BatchSchoolExcel> analysisExcelSchoolData(MultipartFile file) {
        ImportParams params = new ImportParams();
        params.setHeadRows(1);
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return ExcelImportUtil.importExcel(inputStream, BatchSchoolExcel.class, params);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("解析数据异常");
        }
    }

    @Override
    public void exportBatchErrorExcel(HttpServletResponse response, String batchNum) {
        List<BatchSchoolErrorExcel> list = new ArrayList<>();
        BatchSchoolErrorExcel batchSchoolErrorExcel = new BatchSchoolErrorExcel()
            .setAuditFailReason("---")
            .setCityName("--")
            .setCountryName("--")
            .setProvinceName("--")
            .setSchoolAddress("---");
        list.add(batchSchoolErrorExcel);
        // 设置Excel名称
        String excelName = batchNum + "错误数据";
        ExportParams exportParams = new ExportParams();
        exportParams.setTitle(excelName);
        exportParams.setSheetName(excelName);
        exportParams.setType(ExcelType.XSSF);
        Workbook sheets = ExcelExportUtil
            .exportExcel(exportParams, BatchSchoolErrorExcel.class, list);
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/octet-stream");
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Content-disposition",
                "attachment;filename=" + URLEncoder.encode("excelName.xlsx", "UTF-8"));
            sheets.write(response.getOutputStream());
        } catch (IOException e) {
            log.info("导出错误数据异常：{}", e.getMessage());
            throw new RuntimeException("导出错误数据异常");
        }
    }


    @Override
    public void isContainSchool() {
        // 查询所有学校
        List<TrainSchoolDO> allSchool = regionMapper.getAllSchool();
        // 构建布隆过滤器
        BloomFilter<String> bloomFilter = buildSchoolBloomFilterConvert(allSchool);


        TrainSchoolDO trainSchoolDO = new TrainSchoolDO()
            .setProvinceCode("140000")
            .setCountryCode("141030")
            .setCityCode("141000")
            .setSchoolName("大宁县三多乡连村小学")
            .setSchoolAddress("大宁县三多乡连村小学大宁县三多乡连村小学大宁县三多乡连村小学大宁县三多乡连村小学");

        for (TrainSchoolDO trainSchool : allSchool) {
            if (getConcatSchoolName(trainSchool).equals(getConcatSchoolName(trainSchoolDO))) {
                System.out.println("--------" + trainSchool.toString());
            }
        }

        List<String> concatNameList = new ArrayList<>();
        allSchool.forEach(basicSchool -> concatNameList.add(getConcatSchoolName(basicSchool)));
//        System.out.println("");

        System.out.println("----------------->>>>>" + trainSchoolDataCheck(trainSchoolDO, bloomFilter));
        System.out.println("----------------->>>>>222" + trainSchoolDataCheck2(trainSchoolDO, concatNameList));
    }

    private String getConcatSchoolName(TrainSchoolDO trainSchoolDO) {
        return StrUtil.format("{}-{}-{}-{}",
            trainSchoolDO.getSchoolName(), trainSchoolDO.getProvinceCode(),
            trainSchoolDO.getCountryCode(), trainSchoolDO.getCityCode());
    }

    private Boolean trainSchoolDataCheck2(TrainSchoolDO trainSchoolDO, List<String> concatNameList) {
        String concatSchoolName = getConcatSchoolName(trainSchoolDO);
        return trainSchoolDO.getProvinceCode() != null &&
            trainSchoolDO.getCountryCode() != null &&
            trainSchoolDO.getCityCode() != null &&
            !concatNameList.contains(concatSchoolName) &&
            trainSchoolDO.getSchoolAddress() != null &&
            trainSchoolDO.getSchoolName().length() <= SCHOOL_NAME_LENGTH &&
            trainSchoolDO.getSchoolAddress().length() <= SCHOOL_ADRESS_NAME_LENGTH;
    }

    private Boolean trainSchoolDataCheck(TrainSchoolDO trainSchoolDO, BloomFilter<String> schoolBloomFilter) {
        String concatSchoolName = getConcatSchoolName(trainSchoolDO);
        return trainSchoolDO.getProvinceCode() != null &&
            trainSchoolDO.getCountryCode() != null &&
            trainSchoolDO.getCityCode() != null &&
            !schoolBloomFilter.mightContain(concatSchoolName) &&
            trainSchoolDO.getSchoolAddress() != null &&
            trainSchoolDO.getSchoolName().length() <= SCHOOL_NAME_LENGTH &&
            trainSchoolDO.getSchoolAddress().length() <= SCHOOL_ADRESS_NAME_LENGTH;
    }

    /**
     * 学校名称布隆过滤器
     */
    private BloomFilter<String> buildSchoolBloomFilterConvert(List<TrainSchoolDO> trainSchoolDOList) {
        List<String> schoolNameConcatList = trainSchoolDOList.stream()
            .map(this::getConcatSchoolName)
            .collect(Collectors.toList());
        BloomFilter<String> bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), 200000, 1E-7);
        schoolNameConcatList.forEach(school -> bloomFilter.put(school));
        return bloomFilter;
    }
}
