package com.kill.core.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author wangjian
 * @since 2021-04-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class KillUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userName;

    private Integer age;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 删除标识0未删除1已删除
     */
    private Boolean delFlag;

    /**
     * 版本号
     */
    private Integer version;


}
