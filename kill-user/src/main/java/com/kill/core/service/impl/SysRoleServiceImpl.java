package com.kill.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kill.core.entity.SysRole;
import com.kill.core.entity.SysRoleResource;
import com.kill.core.mapper.SysRoleMapper;
import com.kill.core.service.ISysRoleResourceService;
import com.kill.core.service.ISysRoleService;
import com.kill.core.utils.Constants;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wangjian
 * @since 2019-12-26
 */
@Service
@AllArgsConstructor
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    private ISysRoleResourceService sysRoleResourceService;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteRoleById(Integer roleId) {
        this.baseMapper.deleteById(roleId);
        //删除权限下授权的菜单
        LambdaQueryWrapper<SysRoleResource> eq =
            Wrappers.<SysRoleResource>lambdaQuery()
                .eq(SysRoleResource::getRoleId, roleId);
        sysRoleResourceService.remove(eq);
    }

    @Override
    public void addRole(SysRole sysRole) {
        //码值转换为大写
        sysRole.setRoleCode(Constants.ROLE_PREFIX + sysRole.getRoleCode().toUpperCase());
        //判定权限名称以及码值是否重复
        LambdaQueryWrapper<SysRole> wrapper =
            Wrappers.<SysRole>lambdaQuery()
                .eq(SysRole::getRoleName, sysRole.getRoleName())
                .eq(SysRole::getRoleCode, sysRole.getRoleCode());
        List<SysRole> roleList = this.baseMapper.selectList(wrapper);
//        VerifyException.CollectIsNotNull(roleList, "该权限已经存在");
        this.baseMapper.insert(sysRole);
    }
}
