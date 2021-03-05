package com.kill.core.controller;


import com.kill.core.service.ITestDubboService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

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
@Slf4j
public class TestDubboController {

    private ITestDubboService testDubboService;

    @GetMapping
    @ApiOperation("测试Dubbo服务重新")
    public String testDubbo() {
        return testDubboService.testDubbo();
    }


    public static void main(String[] args) {
//        Map map = new HashMap<>();
//        map.put("abc", "123");
//        map.put("qwe", "226");
//        HashMap hashMap = new HashMap<String, String>();
//
//        hashMap.put("qqq", 123);

        Map<String, String> map = new HashMap<>();
        map.put("wwee", "wewew");

        log.info("map大小：{}" ,map.size());


    }


}
