package com.kill.core.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kill.core.entity.SysResource;
import com.kill.core.entity.SysRole;
import com.kill.core.entity.vo.MenuTreeVo;
import com.kill.core.entity.vo.MenuVo;
import com.kill.core.enums.MenuTypeEnum;
import com.kill.core.mapper.SysResourceMapper;
import com.kill.core.mapper.SysUserMapper;
import com.kill.core.service.ISysResourceService;
import com.kill.core.utils.Constants;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wangjian
 * @since 2019-12-27
 */
@Service
@AllArgsConstructor
public class SysResourceServiceImpl extends ServiceImpl<SysResourceMapper, SysResource> implements ISysResourceService {

    private SysUserMapper sysUserMapper;

    @Override
    public List<MenuTreeVo> getMenuTree() {
        List<SysResource> sysResources = baseMapper.selectList(null);
        //加载根节点
        List<SysResource> collect = sysResources
            .stream()
            .filter(s -> Integer.parseInt(s.getType()) == MenuTypeEnum.CATALOG.getTypeCode())
            .collect(Collectors.toList());
        List<MenuTreeVo> list = new ArrayList<>();
        list.add(new MenuTreeVo()
            .setLabel("根节点")
            .setValue(0)
            .setChildren(createNode(collect, sysResources)));
        return list;
    }

    /**
     * @param sysResources 所有菜单节点
     * @param parentId     父节点ID
     *                     获取该节点的所有子节点
     */
    private List<MenuTreeVo> getChildren(List<SysResource> sysResources, Integer parentId) {
        // 获取该父节点的所有子节点
        List<SysResource> collect = sysResources.stream()
            .filter(menu -> menu.getParentId().equals(parentId))
            .collect(Collectors.toList());
        List<MenuTreeVo> list = new ArrayList<>();
        //转换为指定类型
        if (CollUtil.isNotEmpty(collect) && collect.size() > 0) {
            list = createNode(collect, sysResources);
        }
        return list;
    }

    private List<MenuTreeVo> createNode(List<SysResource> collect, List<SysResource> sysResources) {
        List<MenuTreeVo> list = new ArrayList<>();
        collect.forEach(menu -> {
            MenuTreeVo menuTreeVo = new MenuTreeVo()
                .setValue(menu.getMenuId())
                .setLabel(menu.getLabel());
            List<MenuTreeVo> children = getChildren(sysResources, menu.getMenuId());
            if (CollUtil.isNotEmpty(children)) {
                menuTreeVo.setChildren(children);
            }
            list.add(menuTreeVo);
        });
        return list;
    }

    @Override
    public List<MenuVo> getMenuList() {
        // 获取所有菜单
        List<SysResource> sysResources = baseMapper.selectList(null);
        // 获取所有根节点  SysResource --> MenuVo
        return createMevoList(sysResources);
    }


    private List<MenuVo> getMenuVoChildren(List<SysResource> sysResources, Integer parenId) {
        return sysResources.stream()
            .filter(menu -> menu.getParentId().equals(parenId))
            .map(s -> BeanUtil.toBean(s, MenuVo.class))
            .collect(Collectors.toList());
    }

    @Override
    public List<MenuVo> getOwnMenuByUserId(Integer userId) {
        // 获取用户的权限 如果包含ROLE_ADMIN
        List<SysResource> ownMenuList;
        List<String> roleAdmin =
            sysUserMapper.getRoleList(userId)
                .stream()
                .map(SysRole::getRoleCode)
                .filter(Constants.ROLE_ADMIN::equals)
                .collect(Collectors.toList());
        if (CollUtil.isNotEmpty(roleAdmin)) {
            ownMenuList = baseMapper.selectList(null);
        } else {
            ownMenuList = sysUserMapper.getOwnMenuByUserId(userId);
        }
        return createMevoList(ownMenuList);
    }

    public List<MenuVo> createMevoList(List<SysResource> sysResources) {
        return sysResources
            .stream()
            .filter(s -> Integer.parseInt(s.getType()) == MenuTypeEnum.CATALOG.getTypeCode())
            .map(s -> BeanUtil.toBean(s, MenuVo.class))
            .peek(s -> s.setChildren(getMenuVoChildren(sysResources, s.getMenuId())))
            .collect(Collectors.toList());
    }
}
