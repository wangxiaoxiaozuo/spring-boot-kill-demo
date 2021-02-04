package com.kill.core.controller;


import com.kill.core.service.ITestDubboService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wangjian
 * @since 2021-01-07
 */
@RestController
@RequestMapping("/core/test")
@Api(tags = "测试Dubbo")
@AllArgsConstructor
public class TestDubboController {

    private ITestDubboService testDubboService;

    @GetMapping
    @ApiOperation("测试Dubbo服务重新")
    public String testDubbo() {
        return testDubboService.testDubbo();
    }


}
