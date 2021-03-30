package com.kill.core.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@NoArgsConstructor
@AllArgsConstructor
@ExcelTarget("BatchSchoolExcelEntity")
public class BatchSchoolExcel implements Serializable {

    @Excel(name = "学校名称", width = 20)
    private String schoolName;

    @Excel(name = "学校所在省份", width = 20)
    private String provinceName;

    @Excel(name = "学校所在市", width = 20)
    private String countryName;

    @Excel(name = "学校所在区", width = 20)
    private String cityName;

    @Excel(name = "学校详细地址", width = 20)
    private String schoolAddress;

}
