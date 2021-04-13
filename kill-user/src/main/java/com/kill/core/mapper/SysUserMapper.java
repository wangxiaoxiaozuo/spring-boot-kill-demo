package com.kill.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kill.core.entity.SysResource;
import com.kill.core.entity.SysRole;
import com.kill.core.entity.SysUser;
import com.kill.core.entity.vo.SysUserVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author wangjian
 * @since 2019-12-25
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    List<SysRole> getRoleList(@Param("userId") Integer userId);

    List<SysResource> getPathByRoleName(@Param("roleName") String roleName);

    IPage<SysUserVo> getUserAndRoleList(@Param("page") IPage<SysUserVo> page, @Param("condition") String condition);

    List<SysResource> getOwnMenuByUserId(@Param("userId") Integer userId);
}
