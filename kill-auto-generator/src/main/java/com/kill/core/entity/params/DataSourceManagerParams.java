package com.kill.core.entity.params;

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
 * @since 2021-04-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="DataSourceManagerParams", description="")
public class DataSourceManagerParams implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "data_source_id", type = IdType.AUTO)
    private Integer dataSourceId;

    @ApiModelProperty(value = "数据源名称")
    private String dataSourceName;

    @ApiModelProperty(value = "数据源路径")
    private String dataSourceUrl;

    @ApiModelProperty(value = "用户名")
    private String dataUserName;

    @ApiModelProperty(value = "用户密码")
    private String dataUserPassword;

}
