package com.kill.core.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.common.data.entity.PageParams;
import com.kill.core.entity.DataSourceManager;
import com.kill.core.entity.TableInfo;
import com.kill.core.entity.params.DataSourceManagerParams;
import com.kill.core.service.IDataSourceManagerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wangjian
 * @since 2021-04-20
 */
@RestController
@RequestMapping("/source")
@Api(tags = "数据源管理")
@AllArgsConstructor
public class DataSourceManagerController {

    private IDataSourceManagerService dataSourceManagerService;

    @PostMapping
    @ApiOperation("保存数据源")
    public void saveDataSource(DataSourceManagerParams dataSourceManagerParams) {
        dataSourceManagerService.saveDataSource(dataSourceManagerParams);
    }

    @GetMapping
    @ApiOperation("分页查询数据源列表")
    public IPage<DataSourceManager> getDataSourceByPage(PageParams pageParams) {
        return dataSourceManagerService.page(new Page<>(pageParams.getPageNum(), pageParams.getPageSize()));
    }

    @GetMapping("/table/{dataSourceId}")
    @ApiOperation("查询数据源下的所有表")
    public List<TableInfo> getTableListByDataSourceId(@PathVariable Integer dataSourceId){
        return dataSourceManagerService.getTableListByDataSourceId(dataSourceId);
    }


}
