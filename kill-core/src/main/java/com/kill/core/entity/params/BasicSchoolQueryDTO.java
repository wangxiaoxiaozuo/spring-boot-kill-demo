package com.kill.core.entity.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel(value = "BasicSchoolQueryDTO", description = "学校标准库查询参数")
public class BasicSchoolQueryDTO {

    @ApiModelProperty(value = "页数，从1开始 每页10条", required = true)
    private Integer pageIndex = 1;

    @ApiModelProperty(value = "条数，每页显示条数，默认10条", required = true)
    private Integer pageSize = 10;

    @ApiModelProperty(value = "学校名称（模糊查询）/学校编号、多个请用逗号分开")
    private String schoolNameOrIdList;

    @ApiModelProperty(value = "批次号、多个请用逗号分开")
    private String batchNumList;

    @ApiModelProperty(value = "学校来源：{ 1. 学校信息库 2. 中台用户创建 3. C端用户创建 }")
    private Integer recordSource;

    @ApiModelProperty(value = "创建方式：{1:导入/添加 2：申请}")
    private Integer applyMethod;

    @ApiModelProperty(value = " 类型(1: 学校 2: 单位)")
    private Integer schoolType;

    @ApiModelProperty(value = "省份 code")
    private String provinceCode;

    @ApiModelProperty(value = "市 code")
    private String cityCode;

    @ApiModelProperty(value = "区 code")
    private String countryCode;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "修改开始时间")
    private String updateStartTime;

    @ApiModelProperty(value = "修改结束时间")
    private String updateEndTime;


}
