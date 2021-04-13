package com.kill.core.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kill.core.entity.SysUser;
import com.kill.core.entity.param.SysUserDTO;
import com.kill.core.entity.vo.SysUserVo;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author wangjian
 * @since 2019-12-25
 */
public interface ISysUserService extends IService<SysUser> {

    void grantUserRole(Integer userId, List<Integer> roleIds);

    IPage<SysUserVo> getSySUserVoByPage(SysUserDTO sysUserDTO);
}
