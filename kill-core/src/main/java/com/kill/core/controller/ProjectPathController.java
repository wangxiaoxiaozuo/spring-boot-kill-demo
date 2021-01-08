package com.kill.core.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kill.core.entity.ProjectPath;
import com.kill.core.entity.params.ProjectPathParams;
import com.kill.core.service.IProjectPathService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
public class ProjectPathController {

    private IProjectPathService projectPathService;

    @GetMapping
    @ApiOperation("查询")
    public IPage<ProjectPath> getDataByPage(@Validated ProjectPathParams pathParams) {
        return projectPathService.page(new Page<>(pathParams.getPageNum(), pathParams.getPageSize()));
    }


}
