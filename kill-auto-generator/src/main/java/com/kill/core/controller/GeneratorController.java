package com.kill.core.controller;

import com.kill.core.service.IGeneratorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <pre>
 * +--------+---------+-----------+---------+
 * |                                        |
 * +--------+---------+-----------+---------+
 * </pre>
 *
 * @author wangjian
 * @since 2021/04/15 17:33:49
 */
@RestController
@RequestMapping("/code")
@AllArgsConstructor
@Api(tags = "代码生成相关接口管理")
public class GeneratorController {

    private IGeneratorService generatorService;

    @GetMapping("/{tableName}")
    @ApiOperation("代码生成")
    public void codeGenerator(@PathVariable String tableName) {
        generatorService.codeGenerator(tableName);
    }


}
