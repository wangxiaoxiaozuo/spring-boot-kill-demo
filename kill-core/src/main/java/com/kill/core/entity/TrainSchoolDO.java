package com.kill.core.entity;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 表名: train_school
 *
 * @mbg.generated
 */
@Data
@Accessors(chain = true)
public class TrainSchoolDO {
    /**
     * 字段描述: 学校主键ID
     * <p>
     * 字段名: train_school.school_id
     *
     * @mbg.generated
     */
    private Integer schoolId;

    /**
     * 字段描述: 关联批次号ID
     * <p>
     * 字段名: train_school.batch_num
     *
     * @mbg.generated
     */
    private String batchNum;

    /**
     * 字段描述: 学校名称
     * <p>
     * 字段名: train_school.school_name
     *
     * @mbg.generated
     */
    private String schoolName;

    /**
     * 字段描述: 学校详细地址
     * <p>
     * 字段名: train_school.school_address
     *
     * @mbg.generated
     */
    private String schoolAddress;

    /**
     * 字段描述: 省份码值
     * <p>
     * 字段名: train_school.province_code
     *
     * @mbg.generated
     */
    private String provinceCode;

    /**
     * 字段描述: 省份名称
     * <p>
     * 字段名: train_school.province_name
     *
     * @mbg.generated
     */
    private String provinceName;

    /**
     * 字段描述: 市码值
     * <p>
     * 字段名: train_school.city_code
     *
     * @mbg.generated
     */
    private String cityCode;

    /**
     * 字段描述: 市名称
     * <p>
     * 字段名: train_school.city_name
     *
     * @mbg.generated
     */
    private String cityName;

    /**
     * 字段描述: 区码值
     * <p>
     * 字段名: train_school.country_code
     *
     * @mbg.generated
     */
    private String countryCode;

    /**
     * 字段描述: 区名称
     * <p>
     * 字段名: train_school.country_name
     *
     * @mbg.generated
     */
    private String countryName;

    /**
     * 字段描述: 审核人ID
     * <p>
     * 字段名: train_school.audit_user_id
     *
     * @mbg.generated
     */
    private Long auditUserId;

    /**
     * 字段描述: 审核人姓名
     * <p>
     * 字段名: train_school.audit_user_name
     *
     * @mbg.generated
     */
    private String auditUserName;

    /**
     * 字段描述: 审核状态{0:待审核，1：审核通过，2：审核失败}
     * <p>
     * 字段名: train_school.audit_status
     *
     * @mbg.generated
     */
    private Integer auditStatus;

    /**
     * 字段描述: 审核失败原因
     * <p>
     * 字段名: train_school.audit_fail_reason
     *
     * @mbg.generated
     */
    private String auditFailReason;

    /**
     * 字段描述: 状态 0-删除 1-正常
     * <p>
     * 字段名: train_school.state
     *
     * @mbg.generated
     */
    private Integer state;

    /**
     * 字段描述: 空间
     * <p>
     * 字段名: train_school.dt_tenant
     *
     * @mbg.generated
     */
    private String dtTenant;

    /**
     * 字段描述: 品牌
     * <p>
     * 字段名: train_school.dt_brand
     *
     * @mbg.generated
     */
    private String dtBrand;

    /**
     * 字段描述: 创建时间
     * <p>
     * 字段名: train_school.gmt_create
     *
     * @mbg.generated
     */
    private Date gmtCreate;

    /**
     * 字段描述: 更新时间
     * <p>
     * 字段名: train_school.gmt_update
     *
     * @mbg.generated
     */
    private Date gmtUpdate;


    /**
     * 字段描述: 申请时间
     * <p>
     * 字段名: train_school.apply_time
     *
     * @mbg.generated
     */
    private Date applyTime;

    /**
     * 字段描述: 申请ID
     * <p>
     * 字段名: train_school.apply_user_id
     *
     * @mbg.generated
     */
    private Long applyUserId;

    /**
     * 字段描述: 申请人名称
     * <p>
     * 字段名: train_school.apply_user_name
     *
     * @mbg.generated
     */
    private String applyUserName;

    /**
     * 字段描述: 学段
     * <p>
     * 字段名: train_school.school_rank
     *
     * @mbg.generated
     */
    private String schoolRank;

    /**
     * 字段描述: 申请渠道
     * <p>
     * 字段名: train_school.import_source
     *
     * @mbg.generated
     */
    private Integer importSource;

    @Override
    public String toString() {
        return "TrainSchoolDO{" +
            "schoolName='" + schoolName + '\'' +
            ", provinceCode='" + provinceCode + '\'' +
            ", cityCode='" + cityCode + '\'' +
            ", countryCode='" + countryCode + '\'' +
            '}';
    }
}
