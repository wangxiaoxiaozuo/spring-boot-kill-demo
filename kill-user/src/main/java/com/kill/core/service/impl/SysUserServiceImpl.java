package com.kill.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kill.core.entity.SysUser;
import com.kill.core.entity.SysUserRole;
import com.kill.core.entity.param.SysUserDTO;
import com.kill.core.entity.vo.SysUserVo;
import com.kill.core.mapper.SysUserMapper;
import com.kill.core.service.ISysUserRoleService;
import com.kill.core.service.ISysUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wangjian
 * @since 2019-12-25
 */
@Service
@AllArgsConstructor
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {


    private ISysUserRoleService sysUserRoleService;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void grantUserRole(Integer userId, List<Integer> roleIds) {
        // 1.清空原本的权限
        LambdaQueryWrapper<SysUserRole> wrapper =
            Wrappers.<SysUserRole>lambdaQuery()
                .eq(SysUserRole::getUserId, userId);
        sysUserRoleService.remove(wrapper);
        // 2.授权
        List<SysUserRole> list = new ArrayList<>();
        roleIds.forEach(s ->
            list.add(new SysUserRole().setUserId(userId).setRoleId(s))
        );
        sysUserRoleService.saveBatch(list);
    }


    @Override
    public IPage<SysUserVo> getSySUserVoByPage(SysUserDTO sysUserDTO) {
        Page<SysUserVo> sysUserPage = new Page<>(sysUserDTO.getPageNum(), sysUserDTO.getPageSize());
        return this.baseMapper.getUserAndRoleList(sysUserPage, sysUserDTO.getCondition());
    }
}
