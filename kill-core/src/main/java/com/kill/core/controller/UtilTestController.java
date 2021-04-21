package com.kill.core.controller;


import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kill.core.annotation.IpLimit;
import com.kill.core.entity.KillUser;
import com.kill.core.entity.ProjectPath;
import com.kill.core.entity.SysResource;
import com.kill.core.entity.params.BasicSchoolQueryDTO;
import com.kill.core.entity.params.BasicSchoolQueryParams;
import com.kill.core.entity.params.ProjectPathParams;
import com.kill.core.entity.params.WordSimilarParams;
import com.kill.core.entity.vo.BasicTrainSchoolVO;
import com.kill.core.properties.DrugProperties;
import com.kill.core.service.*;
import com.kill.core.service.impl.process.entity.SchoolDataCheckResult;
import com.kill.core.utils.IpUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apdplat.word.WordSegmenter;
import org.apdplat.word.analysis.SimpleTextSimilarity;
import org.apdplat.word.segmentation.Word;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wangjian
 * @since 2021-01-07
 */
@RestController
@RequestMapping("/core/project")
@Api(tags = "好用工具测试")
@AllArgsConstructor
@Slf4j
public class UtilTestController {

    private IProjectPathService projectPathService;

    private DrugProperties drugProperties;

    private PubMessageService pubMessageService;

    private UtilTestService utilTestService;

    private IKillUserService killUserService;

    private ISysResourceService sysResourceService;

    private static final String PATH = "/delete";

    @GetMapping
    @ApiOperation("MybatisPlus分页查询测试")
    @IpLimit
    public List<SysResource> getDataByPage() {
        return sysResourceService.list();
    }


    @GetMapping("/test")
    @ApiOperation("Dubbo接口测试")
    public String testDubbo() {
        Integer delayTime = drugProperties.getDelayTime();
        log.info("测试配置时间：{}", delayTime);
        return projectPathService.testDubbo();
    }

    @GetMapping("/school")
    @ApiOperation("学校是否包含测试")
    public void isContainSchool(){
        pubMessageService.isContainSchool();
    }

    @GetMapping("/sub/{openId}")
    @ApiOperation("SpringBoot消息订阅测试")
    public String sendSubMessage(@PathVariable String openId) {
        pubMessageService.sendSubMessage(openId);
        return "发送成功！！！！！";
    }

    @PostMapping("/bloom/filter/test")
    @ApiOperation("布隆过滤器测试")
    public String importHospitalExcel(@RequestParam("file") MultipartFile file,
                                      @RequestParam String schoolName) {
        return pubMessageService.importHospitalExcelFile(file, schoolName);
    }

    @GetMapping("/order/number")
    @ApiOperation("Redis生成唯一订单号测试")
    public String createOrderNumber() {
        return pubMessageService.createOrderNumber();
    }


    @GetMapping("/line/code")
    @ApiOperation("Hutool图形验证码测试")
    public void getCreateLineCaptcha(HttpServletResponse response) throws IOException {
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100);
        lineCaptcha.write(response.getOutputStream());
    }


    @PostMapping("/export/{batchNum}")
    @ApiOperation("EasyPoi导出测试")
    public void exportBatchErrorExcel(HttpServletResponse response, @PathVariable String batchNum) {
        pubMessageService.exportBatchErrorExcel(response, batchNum);
    }


    @DeleteMapping(PATH)
    @ApiOperation("Word分词测试")
    public void deleteByIds(@RequestBody List<Integer> schoolIds, HttpServletRequest request) {
        System.out.println(schoolIds.toString());
        System.out.println(request.getHeader("User-Agent"));
        System.out.println(IpUtils.getIpAddr(request));
        List<String> collect = WordSegmenter.seg("我叫李太白，我是一个诗人，我生活在唐朝").stream().map(Word::getText).collect(Collectors.toList());
        System.out.println(collect.toString());
    }

    @PostMapping("/same")
    @ApiOperation("Word词语相似度测试")
    public Double getWordSimilar(@RequestBody WordSimilarParams params) {
        SimpleTextSimilarity simpleTextSimilarity = new SimpleTextSimilarity();
        return simpleTextSimilarity.similarScore(params.getWord1(), params.getWord2());
    }


    @GetMapping("/file")
    @ApiOperation("FileUtils工具测试")
    public void testFileUtils(HttpServletResponse response) throws IOException {
//        File[] fileList = FileUtil.ls("/Users/wangjian/Desktop/");
//        for (File file : fileList) {
//
//            System.out.println(FileUtil.extName(file));
////            log.info("fileName:\n{}", file.getName());
//        }

        String readUtf8String = FileUtil.readUtf8String(new File("/Users/wangjian/Desktop/古诗词.text"));
        System.out.println(readUtf8String);

        ServletOutputStream outputStream = response.getOutputStream();
        FileUtil.writeToStream(new File("/Users/wangjian/Desktop/古诗词.text"), outputStream);
    }


    @PostMapping("/analysis")
    @ApiOperation("数据比对分解")
    public SchoolDataCheckResult analysisDataList(MultipartFile file) {
        return utilTestService.analysisDataList(file);
    }

    public static void main(String[] args) {

        List<BasicTrainSchoolVO> data = new ArrayList<>();
        BasicTrainSchoolVO basicTrainSchoolVO = new BasicTrainSchoolVO()
            .setSchoolId(369945597);
        BasicTrainSchoolVO basicTrainSchoolVO1 = new BasicTrainSchoolVO()
            .setSchoolId(13669069);
        data.add(basicTrainSchoolVO1);
        data.add(basicTrainSchoolVO);


        BasicSchoolQueryDTO params = new BasicSchoolQueryDTO();
        params.setSchoolNameOrIdList("369945597,13669069,3,王建");
        List<String> matchList = new ArrayList<>();
        if (StrUtil.isNotEmpty(params.getSchoolNameOrIdList())) {
            matchList.addAll(Arrays.asList(params.getSchoolNameOrIdList().split(",")));
        }
        if (StrUtil.isNotEmpty(params.getBatchNumList())) {
            matchList.addAll(Arrays.asList(params.getBatchNumList().split(",")));
        }
        List<String> unMatchList = matchList.stream()
            .filter(match -> !data
                .stream()
                .map(ss -> ss.getSchoolId().toString())
                .collect(Collectors.toList())
                .contains(match))
            .filter(match -> !data
                .stream()
                .map(BasicTrainSchoolVO::getSchoolName)
                .collect(Collectors.toList())
                .contains(match))
            .collect(Collectors.toList());
        System.out.println(unMatchList.toString());
//        long count = 10;
//        BasicSchoolQueryParams params = new BasicSchoolQueryParams()
//            .setSchoolNameOrIdList("12,1212,121212");
//        // 拆分字段计算拆分后的数据条数
//        int needMatchCount = 0;
//        if (StrUtil.isNotEmpty(params.getSchoolNameOrIdList())) {
//            needMatchCount += params.getSchoolNameOrIdList().split(",").length;
//        }
//        if (StrUtil.isNotEmpty(params.getBatchNumList())) {
//            needMatchCount += params.getBatchNumList().split(",").length;
//        }
//        log.info("查询条件：NameOrIdList:{},BatchNumList:{},应匹配数量：{},匹配数量：{}",
//            params.getSchoolNameOrIdList(), params.getBatchNumList(), needMatchCount, count);


//        List<String> list = new ArrayList<>();
//        list.add("aaaa");
//        list.add("bbbb");
//        list.add("bbvvv");
//        list.add("ddsdsd");
//        String collect = String.join(",", list);
//        System.out.println(collect);


    }

}
