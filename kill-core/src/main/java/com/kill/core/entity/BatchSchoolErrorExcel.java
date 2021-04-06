package com.kill.core.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <pre>
 * +--------+---------+-----------+---------+
 * |      实体映射Excel字段                   |
 * +--------+---------+-----------+---------+
 * </pre>
 *
 * @author wangjian
 * @since 2021/03/23 16:38:11
 */
@Data
@ToString
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ExcelTarget("BatchSchoolErrorExcel")
public class BatchSchoolErrorExcel implements Serializable {

    @Excel(name = "异常提示", width = 50)
    private String auditFailReason;

    @Excel(name = "学校（单位）名称", width = 20)
    private String schoolName = "";

    @Excel(name = "省（含自治区/直辖市）", width = 20)
    private String provinceName = "";

    @Excel(name = "市", width = 20)
    private String countryName = "";

    @Excel(name = "区县", width = 20)
    private String cityName = "";

    @Excel(name = "地址", width = 20)
    private String schoolAddress = "";

    @Excel(name = "学段", width = 20)
    private String schoolRank = "";




}
