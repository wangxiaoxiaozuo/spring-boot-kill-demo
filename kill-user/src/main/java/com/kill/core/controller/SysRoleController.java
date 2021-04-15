package com.kill.core.controller;


import com.mall.admin.entity.SysRole;
import com.mall.admin.service.ISysRoleService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色管理接口
 */
@RestController
@RequestMapping("/sysRole")
@AllArgsConstructor
public class SysRoleController {

    private ISysRoleService sysRoleService;


    @GetMapping("/list")
    public List<SysRole> getRoleList() {
        return sysRoleService.list();
    }

    @PostMapping
    public void addRole(@Validated @RequestBody SysRole sysRole) {
        sysRoleService.addRole(sysRole);
    }

    @PutMapping("/{roleId}")
    public void updateRoleById(@Validated @RequestBody SysRole sysRole, @PathVariable Integer roleId) {
        sysRole.setRoleId(roleId);
        sysRoleService.updateById(sysRole);
    }

    @DeleteMapping("/{roleId}")
    public void deleteRoleById(@PathVariable Integer roleId) {
        sysRoleService.deleteRoleById(roleId);
    }


}
