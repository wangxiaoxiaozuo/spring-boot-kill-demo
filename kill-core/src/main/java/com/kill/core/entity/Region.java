package com.kill.core.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 行政区
 * </p>
 *
 * @author wangjian
 * @since 2021-04-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Region implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 名称
     */
    private String name;

    /**
     * 父节点编码
     */
    private Long pid;

    /**
     * 层级
     */
    private Integer level;

    /**
     * 拼音首字母
     */
    private String letter;

    /**
     * 类型:(1:行政,2:培训)
     */
    private Integer type;

    /**
     * 状态:(1:正常,0:删除)
     */
    private Boolean state;

    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 逻辑更新时间
     */
    private LocalDateTime gmtModified;

    /**
     * 物理更新时间
     */
    private LocalDateTime ts;


}
