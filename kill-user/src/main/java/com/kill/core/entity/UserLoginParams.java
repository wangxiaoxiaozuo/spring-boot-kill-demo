package com.kill.core.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * <pre>
 * +--------+---------+-----------+---------+
 * |                                        |
 * +--------+---------+-----------+---------+
 * </pre>
 *
 * @author wangjian
 * @since 2021/04/12 19:37:18
 */
@Data
@ToString
public class UserLoginParams implements Serializable {

    private String username;

    private String password;

}
