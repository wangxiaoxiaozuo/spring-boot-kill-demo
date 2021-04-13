package com.kill.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SysUserStateEnum {

    NORMAL(0, "正常"),
    LOCKED(1, "锁定");

    private Integer userState;
    private String desc;
}
