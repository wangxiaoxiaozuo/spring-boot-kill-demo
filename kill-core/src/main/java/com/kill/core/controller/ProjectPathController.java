package com.kill.core.controller;


import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.stylefeng.roses.kernel.pinyin.PinyinServiceImpl;
import cn.stylefeng.roses.kernel.pinyin.api.PinYinApi;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import com.kill.core.annotation.IpLimit;
import com.kill.core.entity.ProjectPath;
import com.kill.core.entity.params.ProjectPathParams;
import com.kill.core.properties.DrugProperties;
import com.kill.core.service.IProjectPathService;
import com.kill.core.service.PubMessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
@Api(tags = "项目管理")
@AllArgsConstructor
@Slf4j
public class ProjectPathController {

    private IProjectPathService projectPathService;

    private DrugProperties drugProperties;

    private PubMessageService pubMessageService;

    @GetMapping
    @ApiOperation("查询")
    @IpLimit
    public IPage<ProjectPath> getDataByPage(@Validated ProjectPathParams pathParams) {
//        throw new BizException("这是测试异常");
//        BeanUtil.toBean()
        return projectPathService.page(new Page<>(pathParams.getPageNum(), pathParams.getPageSize()));
    }


    @GetMapping("/test")
    @ApiOperation("dubbo接口测试")
    public String testDubbo() {
        Integer delayTime = drugProperties.getDelayTime();
        System.out.println(delayTime);
        return projectPathService.testDubbo();
    }

    @GetMapping("/sub/{openId}")
    public String sendSubMessage(@PathVariable String openId) {
        pubMessageService.sendSubMessage(openId);
        return "发送成功！！！！！";
    }

    @PostMapping("/bloom/filter/test")
    public String importHospitalExcel(@RequestParam("file") MultipartFile file,
                                      @RequestParam String schoolName) {
        return pubMessageService.importHospitalExcelFile(file, schoolName);
    }

    @GetMapping("/order/number")
    public String createOrderNumber(){
        return pubMessageService.createOrderNumber();
    }


    public static void main(String[] args) {
//        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100);
//        lineCaptcha.write("/Users/wangjian/Desktop/line.png");
//        System.out.println(lineCaptcha.getCode());
//
//        System.out.println(Math.round(-1.5));
//
//        PinYinApi pinYinApi = new PinyinServiceImpl();
//        String s = pinYinApi.getChineseStringFirstLetterUpper("北京市");
//        System.out.println(s);

        BloomFilter<CharSequence> bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), 200000, 1E-7);
        System.out.println(bloomFilter.mightContain("wangjian"));
    }




    @GetMapping("/line/code")
    public void getCreateLineCaptcha(HttpServletResponse response) throws IOException {
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100);
        lineCaptcha.write(response.getOutputStream());
    }


    @PostMapping("/export/{batchNum}")
    @ApiOperation(value = "导出错误Excel")
    public void exportBatchErrorExcel(HttpServletResponse response, @PathVariable String batchNum) {
        pubMessageService.exportBatchErrorExcel(response, batchNum);
    }







}
