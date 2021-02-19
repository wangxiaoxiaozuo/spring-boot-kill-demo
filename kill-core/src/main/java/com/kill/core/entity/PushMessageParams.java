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
 * @since 2021/02/19 16:06:42
 */
@Data
public class PushMessageParams {

    /**
     * 发送用户OpenId
     */
    private String toUserOpenId;

    /**
     * 小程序提供的模板ID
     */
    private String formId;
}
