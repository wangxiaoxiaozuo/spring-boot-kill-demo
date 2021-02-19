package com.kill.core.entity;

import lombok.Data;

/**
 * <pre>
 * +--------+---------+-----------+---------+
 * |                                        |
 * +--------+---------+-----------+---------+
 * </pre>
 *
 * @author wangjian
 * @since 2021/02/19 16:02:04
 */
@Data
public class WeChatToken {

    private String access_token;

    private String expires_in;

    private String errcode;

    private String errmsg;


}
