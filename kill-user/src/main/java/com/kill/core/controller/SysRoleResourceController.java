package com.kill.core.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.mall.admin.entity.SysRoleResource;
import com.mall.admin.service.ISysRoleResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wangjian
 * @since 2020-01-02
 */
@RestController
@RequestMapping("/roleResource")
@AllArgsConstructor
@Api(tags = "权限对应菜单管理")
public class SysRoleResourceController {

    private ISysRoleResourceService sysRoleResourceService;

    @GetMapping("/{roleId}/resources")
    @ApiOperation("获取该权限对应的菜单ID")
    public List<Integer> getResourceIdsByRoleId(@PathVariable Integer roleId) {
        LambdaQueryWrapper<SysRoleResource> eq =
            Wrappers.<SysRoleResource>lambdaQuery()
                .eq(SysRoleResource::getRoleId, roleId);
        return sysRoleResourceService.list(eq)
            .stream()
            .map(SysRoleResource::getResourceId)
            .collect(Collectors.toList());
    }


    @PostMapping("/{roleId}")
    @ApiOperation("授权菜单")
    public void grantMenuToRoleId(@PathVariable Integer roleId, @RequestBody List<Integer> menuIds) {
        sysRoleResourceService.grantMenuToRoleId(roleId, menuIds);
    }

}
