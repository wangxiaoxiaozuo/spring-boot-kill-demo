package com.kill.core.controller;


import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kill.core.annotation.IpLimit;
import com.kill.core.entity.ProjectPath;
import com.kill.core.entity.params.ProjectPathParams;
import com.kill.core.entity.params.WordSimilarParams;
import com.kill.core.properties.DrugProperties;
import com.kill.core.service.IProjectPathService;
import com.kill.core.service.PubMessageService;
import com.kill.core.service.UtilTestService;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

    private static final String PATH = "/delete";

    @GetMapping
    @ApiOperation("MybatisPlus分页查询测试")
    @IpLimit
    public IPage<ProjectPath> getDataByPage(@Validated ProjectPathParams pathParams) {
        return projectPathService.page(new Page<>(pathParams.getPageNum(), pathParams.getPageSize()));
    }


    @GetMapping("/test")
    @ApiOperation("Dubbo接口测试")
    public String testDubbo() {
        Integer delayTime = drugProperties.getDelayTime();
        log.info("测试配置时间：{}", delayTime);
        return projectPathService.testDubbo();
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


    @PostMapping("/analysis")
    @ApiOperation("数据比对分解")
    public SchoolDataCheckResult analysisDataList(MultipartFile file){
        return utilTestService.analysisDataList(file);
    }


}
