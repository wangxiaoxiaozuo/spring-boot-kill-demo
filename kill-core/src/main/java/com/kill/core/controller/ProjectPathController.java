package com.kill.core.controller;


import com.kill.core.entity.params.ProjectPathParams;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wangjian
 * @since 2021-01-07
 */
@RestController
@RequestMapping("/core/project")
@Api(tags = "项目管理")
public class ProjectPathController {


    @PostMapping
    @ApiOperation("新增项目")
    public void testSwagger(@Validated @RequestBody ProjectPathParams pathParams){

    }



}
