package com.kill.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <pre>
 * +--------+---------+-----------+---------+
 * |                                        |
 * +--------+---------+-----------+---------+
 * </pre>
 *
 * @author wangjian
 * @since 2019/12/27 15:39:15
 */
@Getter
@AllArgsConstructor
public enum MenuTypeEnum {

    CATALOG(0, "目录"),
    MENU(1, "菜单"),
    BUTTON(2, "按钮");

    private Integer typeCode;
    private String codeDesc;

}
