package com.kill.core.service.impl.process.utils;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.hutool.core.util.StrUtil;
import com.kill.core.entity.BatchSchoolExcel;
import lombok.experimental.UtilityClass;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <pre>
 * +--------+---------+-----------+---------+
 * |                                        |
 * +--------+---------+-----------+---------+
 * </pre>
 *
 * @author wangjian
 * @since 2021/04/08 09:59:58
 */
@UtilityClass
public class ReadSchoolDataExcelUtils {


    /**
     * easyPoi 读取Excel中学校映射数据
     *
     * @param file excel 流文件
     * @return 学校数据
     */
    public List<BatchSchoolExcel> analysisExcelSchoolData(MultipartFile file) {
        ImportParams params = new ImportParams();
        params.setHeadRows(1);
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            List<BatchSchoolExcel> batchSchoolExcels = ExcelImportUtil.importExcel(inputStream, BatchSchoolExcel.class, params);
            // 无效数据行过滤
            batchSchoolExcels = batchSchoolExcels.stream()
                    .filter(schoolExcel ->
                            StrUtil.isNotEmpty(schoolExcel.getCityName()) ||
                                    StrUtil.isNotEmpty(schoolExcel.getCountryName()) ||
                                    StrUtil.isNotEmpty(schoolExcel.getProvinceName()) ||
                                    StrUtil.isNotEmpty(schoolExcel.getSchoolName()) ||
                                    StrUtil.isNotEmpty(schoolExcel.getSchoolAddress()) ||
                                    StrUtil.isNotEmpty(schoolExcel.getSchoolRank())
                    ).collect(Collectors.toList());

            return batchSchoolExcels;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("解析学校Excel数据异常");
        }

    }
}
