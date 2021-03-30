package com.kill.core.service.impl;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import com.kill.core.constant.BatchOrderConstant;
import com.kill.core.constant.PushMessageConstant;
import com.kill.core.entity.BatchSchoolExcel;
import com.kill.core.entity.WeChatToken;
import com.kill.core.service.PubMessageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;


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
    public String getAuthToken() throws Exception {
        String url = StrUtil.format(PushMessageConstant.GET_TOKEN_URL, PushMessageConstant.APPID, PushMessageConstant.APP_SECRET);
        WeChatToken weChatToken = restTemplate.getForObject(url, WeChatToken.class);
        return weChatToken.getAccess_token();
    }


    @Override
    public String importHospitalExcelFile(MultipartFile file, String schoolName) {
        List<BatchSchoolExcel> batchSchoolExcels = analysisExcelSchoolData(file);
        log.info("读取Excel的大小为：{}，文件名称:{}", batchSchoolExcels.size(), file.getOriginalFilename());
        // 用布隆过滤器耗时
        long startTime = System.currentTimeMillis();
        BloomFilter<CharSequence> bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), 200000, 1E-7);
        batchSchoolExcels.forEach(readSchoolName -> bloomFilter.put(readSchoolName.getSchoolName()));

        return StrUtil.format("是否存在：{},检测共耗时：{}", bloomFilter.mightContain(schoolName), System.currentTimeMillis() - startTime);
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
}
