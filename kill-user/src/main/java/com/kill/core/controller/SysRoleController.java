package com.kill.core.controller;


import com.kill.core.entity.SysRole;
import com.kill.core.service.ISysRoleService;
import io.swagger.annotations.Api;
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
@Api(tags = "角色管理")
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
