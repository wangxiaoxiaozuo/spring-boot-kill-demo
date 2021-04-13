package com.kill.core.entity.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * <pre>
 * +--------+---------+-----------+---------+
 * |  菜单树                                 |
 * +--------+---------+-----------+---------+
 * </pre>
 *
 * @author wangjian
 * @since 2019/12/27 16:13:15
 */
@Data
@Accessors(chain = true)
public class MenuTreeVo {
    /**
     * 菜单ID
     */
    private Integer value;
    /**
     * 菜单名称
     */
    private String label;

    /**
     * 子集菜单
     */
    private List<MenuTreeVo> children;
}
