package com.kill.core.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

/**
 * 表名: train_school
 */
@Data
@Accessors(chain = true)
public class BasicTrainSchoolVO {

    @ApiModelProperty(value = " 类型(1: 学校 2: 单位)")
    private Integer schoolType;

    @ApiModelProperty(value = "编号")
    private Integer schoolId;

    @ApiModelProperty(value = "学校来源：{ 1. 学校信息库 2. 中台用户创建 3. C端用户创建 }")
    private Integer recordSource;

    @ApiModelProperty(value = "学校名称")
    private String schoolName;

    @ApiModelProperty(value = "省份名称")
    private String provinceName;

    @ApiModelProperty(value = "市名称")
    private String cityName;

    @ApiModelProperty(value = "区名称")
    private String countryName;

    @ApiModelProperty(value = "省份 code")
    private Long provinceCode;

    @ApiModelProperty(value = "市 code")
    private Long cityCode;

    @ApiModelProperty(value = "区 code")
    private Long countryCode;

    @ApiModelProperty(value = "学校详细地址")
    private String schoolAddress;

    @ApiModelProperty(value = "创建方式：{1:导入/添加 2：申请}")
    private Integer applyMethod;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "创建时间")
    private Date gmtCreate;

    @ApiModelProperty(value = "修改时间")
    private Date gmtModified;
}
