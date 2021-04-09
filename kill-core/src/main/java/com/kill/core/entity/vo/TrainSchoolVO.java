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
 * 审核关联学校信息表
 * </p>
 *
 * @author wangjian
 * @since 2021-04-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TrainSchoolVO对象", description="审核关联学校信息表")
public class TrainSchoolVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "学校主键ID")
    @TableId(value = "school_id", type = IdType.AUTO)
    private Integer schoolId;

    @ApiModelProperty(value = "关联批次号ID")
    private String batchNum;

    @ApiModelProperty(value = "学校名称")
    private String schoolName;

    @ApiModelProperty(value = "学校详细地址")
    private String schoolAddress;

    @ApiModelProperty(value = "省份码值")
    private String provinceCode;

    @ApiModelProperty(value = "省份名称")
    private String provinceName;

    @ApiModelProperty(value = "市码值")
    private String cityCode;

    @ApiModelProperty(value = "市名称")
    private String cityName;

    @ApiModelProperty(value = "区码值")
    private String countryCode;

    @ApiModelProperty(value = "区名称")
    private String countryName;

    @ApiModelProperty(value = "审核人名称")
    private String auditUserName;

    @ApiModelProperty(value = "审核人ID")
    private Long auditUserId;

    @ApiModelProperty(value = "审核状态{0:待审核，1：审核通过，2：审核驳回}")
    private Integer auditStatus;

    @ApiModelProperty(value = "审核失败原因")
    private String auditFailReason;

    @ApiModelProperty(value = "申请人ID")
    private Long applyUserId;

    @ApiModelProperty(value = "申请人名称")
    private String applyUserName;

    @ApiModelProperty(value = "申请时间")
    private LocalDateTime applyTime;

    @ApiModelProperty(value = "导入来源（1 标准库，2：研修宝）")
    private Integer importSource;

    @ApiModelProperty(value = "学段")
    private String schoolRank;

    @ApiModelProperty(value = "状态 0-删除 1-正常")
    private Integer state;

    @ApiModelProperty(value = "空间")
    private String dtTenant;

    @ApiModelProperty(value = "品牌")
    private String dtBrand;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime gmtCreate;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime gmtUpdate;

    private LocalDateTime ts;


}
