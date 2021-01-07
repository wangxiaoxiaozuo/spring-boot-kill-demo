package com.kill.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2021-01-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ProjectPath implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "path_id", type = IdType.AUTO)
    private Integer pathId;

    /**
     * 所属环境
     */
    private String pathEnv;

    /**
     * 路径名称
     */
    private String pathName;

    /**
     * 路径
     */
    private String path;

    /**
     * 图片路径
     */
    private String imagePath;

    /**
     * 路径描述
     */
    private String pathDesc;

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


}
