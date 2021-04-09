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
 * 审核关联学校信息表
 * </p>
 *
 * @author wangjian
 * @since 2021-04-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TrainSchool implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 学校主键ID
     */
    @TableId(value = "school_id", type = IdType.AUTO)
    private Integer schoolId;

    /**
     * 关联批次号ID
     */
    private String batchNum;

    /**
     * 学校名称
     */
    private String schoolName;

    /**
     * 学校详细地址
     */
    private String schoolAddress;

    /**
     * 省份码值
     */
    private String provinceCode;

    /**
     * 省份名称
     */
    private String provinceName;

    /**
     * 市码值
     */
    private String cityCode;

    /**
     * 市名称
     */
    private String cityName;

    /**
     * 区码值
     */
    private String countryCode;

    /**
     * 区名称
     */
    private String countryName;

    /**
     * 审核人名称
     */
    private String auditUserName;

    /**
     * 审核人ID
     */
    private Long auditUserId;

    /**
     * 审核状态{0:待审核，1：审核通过，2：审核驳回}
     */
    private Integer auditStatus;

    /**
     * 审核失败原因
     */
    private String auditFailReason;

    /**
     * 申请人ID
     */
    private Long applyUserId;

    /**
     * 申请人名称
     */
    private String applyUserName;

    /**
     * 申请时间
     */
    private LocalDateTime applyTime;

    /**
     * 导入来源（1 标准库，2：研修宝）
     */
    private Integer importSource;

    /**
     * 学段
     */
    private String schoolRank;

    /**
     * 状态 0-删除 1-正常
     */
    private Integer state;

    /**
     * 空间
     */
    private String dtTenant;

    /**
     * 品牌
     */
    private String dtBrand;

    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 更新时间
     */
    private LocalDateTime gmtUpdate;

    private LocalDateTime ts;


}
