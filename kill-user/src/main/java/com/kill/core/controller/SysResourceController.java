package com.kill.core.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.kill.core.entity.SysResource;
import com.kill.core.entity.vo.MenuVo;
import com.kill.core.enums.MenuTypeEnum;
import com.kill.core.service.ISysResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 菜单管理
 *
 * @author wangjian
 * @since 2019-12-27
 */
@RestController
@RequestMapping("/sysResource")
@AllArgsConstructor
@Api(tags = "菜单管理")
public class SysResourceController {

    private ISysResourceService sysResourceService;

    @GetMapping("/root")
    public List<SysResource> getRootMenu() {
        LambdaQueryWrapper<SysResource> wrapper =
            Wrappers.<SysResource>lambdaQuery()
                .eq(SysResource::getType, MenuTypeEnum.CATALOG.getTypeCode())
                .orderByAsc(SysResource::getSort);
        return sysResourceService.list(wrapper);
    }

    @PostMapping
    @ApiOperation("添加菜单")
    public void addSysResource(@Validated @RequestBody SysResource sysResource) {
        sysResourceService.save(sysResource);
    }

    @DeleteMapping("/{menuId}")
    @ApiOperation("删除菜单")
    public void deleteSysResource(@PathVariable Integer menuId) {
        sysResourceService.removeById(menuId);
    }

    @GetMapping("/menuList")
    @ApiOperation("获取菜单列表")
    public List<MenuVo> getMenuList() {
        return sysResourceService.getMenuList();
    }

    @GetMapping("/{userId}/menuList")
    @ApiOperation("获取用户自己的菜单列表")
    public List<MenuVo> getOwnMenuByUserId(@PathVariable Integer userId) {
        return sysResourceService.getOwnMenuByUserId(userId);
    }

    @GetMapping("/routerList")
    @ApiOperation("获取动态路由")
    public List<SysResource> getRouterList(){
        LambdaQueryWrapper<SysResource> wrapper =
            Wrappers.<SysResource>lambdaQuery()
                .eq(SysResource::getType, MenuTypeEnum.MENU.getTypeCode());
        return sysResourceService.list(wrapper);
    }


}
