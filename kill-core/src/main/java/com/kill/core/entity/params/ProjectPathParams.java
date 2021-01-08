package com.kill.core.entity.params;


import com.common.data.entity.PageParams;
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
@ApiModel(value="ProjectPathVO对象", description="")
public class ProjectPathParams extends PageParams implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "所属环境")
    private String pathEnv;

    @ApiModelProperty(value = "路径名称")
    private String pathName;




}
