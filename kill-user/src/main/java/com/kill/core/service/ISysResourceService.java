package com.kill.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kill.core.entity.SysResource;
import com.kill.core.entity.vo.MenuTreeVo;
import com.kill.core.entity.vo.MenuVo;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author wangjian
 * @since 2019-12-27
 */
public interface ISysResourceService extends IService<SysResource> {

    List<MenuTreeVo> getMenuTree();

    List<MenuVo> getMenuList();

    List<MenuVo> getOwnMenuByUserId(Integer userId);
}
