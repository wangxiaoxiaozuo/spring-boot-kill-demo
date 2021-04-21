package com.kill.core.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value="ProjectPathVO对象")
public class ProjectPathVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "path_id", type = IdType.AUTO)
    private Integer pathId;

    @ApiModelProperty(value = "所属环境")
    private String pathEnv;

    @ApiModelProperty(value = "路径名称")
    private String pathName;

    @ApiModelProperty(value = "路径")
    private String path;

    @ApiModelProperty(value = "图片路径")
    private String imagePath;

    @ApiModelProperty(value = "路径描述")
    private String pathDesc;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "删除标识0未删除1已删除")
    private Boolean delFlag;


}
