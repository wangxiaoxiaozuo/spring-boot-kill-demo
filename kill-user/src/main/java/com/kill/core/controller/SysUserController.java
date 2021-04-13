package com.kill.core.controller;


import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.kill.core.entity.SysUser;
import com.kill.core.entity.param.SysUserDTO;
import com.kill.core.entity.vo.SysUserVo;
import com.kill.core.service.ISysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 查询商铺后台管理用户
 */
@RestController
@RequestMapping("/sysUser")
@AllArgsConstructor
@Api(tags = "后台用户管理")
public class SysUserController {

    private ISysUserService sysUserService;

    @GetMapping("/page")
    public IPage<SysUserVo> getSySUserVoByPage(@Validated SysUserDTO sysUserDTO) {
        return sysUserService.getSySUserVoByPage(sysUserDTO);
    }

    @PostMapping
    public void addUser(@Validated @RequestBody SysUser sysUser) {
        sysUserService.save(sysUser);
    }

    @PutMapping("/{userId}/{userState}")
    public void updateUserState(@PathVariable Integer userId, @PathVariable String userState) {
        LambdaUpdateWrapper<SysUser> eq =
            Wrappers.<SysUser>lambdaUpdate()
                .set(SysUser::getLocked, userState)
                .eq(SysUser::getUserId, userId);
        sysUserService.update(eq);
    }

    @DeleteMapping("/{userId}")
    public void deleteUserById(@PathVariable Integer userId) {
        sysUserService.removeById(userId);
    }

    @PutMapping("/{userId}")
    @ApiOperation("修改用户信息")
    public void updateUserById(@PathVariable Integer userId, @Validated @RequestBody SysUser sysUser) {
        sysUser.setUserId(userId);
        sysUserService.updateById(sysUser);
    }

    @PutMapping("/{userId}/role")
    @ApiOperation("用户授权")
    public void grantUserRole(@PathVariable Integer userId, @RequestBody List<Integer> roleIds) {
        sysUserService.grantUserRole(userId, roleIds);
    }


}
