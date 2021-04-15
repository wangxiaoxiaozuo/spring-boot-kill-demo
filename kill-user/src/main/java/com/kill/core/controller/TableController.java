package com.kill.core.controller;

import com.kill.core.service.ITableService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <pre>
 * +--------+---------+-----------+---------+
 * |                                        |
 * +--------+---------+-----------+---------+
 * </pre>
 *
 * @author wangjian
 * @since 2021/04/15 11:58:47
 */
@RestController
@Api(tags = "表结构接口管理")
@AllArgsConstructor
@RequestMapping("/table")
public class TableController {

    private ITableService tableService;


    @GetMapping
    @ApiOperation("获取所有表名称列表")
    public List<String> getTableNameList() {
        return tableService.getTableNameList();
    }

}
