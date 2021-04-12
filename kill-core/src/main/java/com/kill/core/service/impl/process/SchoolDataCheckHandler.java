package com.kill.core.service.impl.process;

import com.kill.core.entity.BatchSchoolExcel;
import com.kill.core.service.impl.process.entity.SchoolDataCheckResult;
import com.kill.core.service.impl.process.strategy.SchoolDataCheckStrategy;
import com.kill.core.service.impl.process.utils.ReadSchoolDataExcelUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <pre>
 * +--------+---------+-----------+---------+
 * |      Excel学校数据校验处理器              |
 * +--------+---------+-----------+---------+
 * </pre>
 *
 * @author wangjian
 * @since 2021/04/08 09:50:14
 */
public class SchoolDataCheckHandler {


    public SchoolDataCheckHandler(SchoolDataCheckStrategy schoolDataCheckStrategy) {
        this.schoolDataCheckStrategy = schoolDataCheckStrategy;
    }

    private SchoolDataCheckStrategy schoolDataCheckStrategy;

    /**
     * @param file 数据文件流
     * @return error: List<TrainSchoolDO> 校验包含错误数据
     * mayHaveError : List<TrainSchoolDO>  校验中可能包含错误的数据
     * noError: List<TrainSchoolDO> 校验中不包含错误的数据
     */
    public SchoolDataCheckResult process(MultipartFile file) {
        // 步骤一：文件上传至阿里云返回地址
        // 步骤二：Excel文件流解析
        List<BatchSchoolExcel> batchSchoolExcels = ReadSchoolDataExcelUtils.analysisExcelSchoolData(file);
        // 步骤三：数据校验
        return schoolDataCheckStrategy.check(batchSchoolExcels);
    }


}
