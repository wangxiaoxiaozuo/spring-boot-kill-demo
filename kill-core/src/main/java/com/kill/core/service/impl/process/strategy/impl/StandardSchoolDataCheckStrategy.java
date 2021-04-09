package com.kill.core.service.impl.process.strategy.impl;

import com.kill.core.constant.BatchOrderConstant;
import com.kill.core.entity.BatchSchoolExcel;
import com.kill.core.entity.TrainSchool;
import com.kill.core.service.impl.process.entity.AnalysisResult;
import com.kill.core.service.impl.process.strategy.SchoolDataCheckStrategy;
import lombok.extern.slf4j.Slf4j;
import org.apdplat.word.analysis.SimpleTextSimilarity;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * +--------+---------+-----------+---------+
 * |        标准版本数据校验策略                  |
 * +--------+---------+-----------+---------+
 * </pre>
 *
 * @author wangjian
 * @since 2021/04/08 10:05:41
 */
@Slf4j
public class StandardSchoolDataCheckStrategy implements SchoolDataCheckStrategy {

    private List<TrainSchool> trainSchools;

    public StandardSchoolDataCheckStrategy(List<TrainSchool> trainSchools) {
        this.trainSchools = trainSchools;
    }

    @Override
    public AnalysisResult check(List<BatchSchoolExcel> batchSchoolExcels) {
        SimpleTextSimilarity simpleTextSimilarity = new SimpleTextSimilarity();
        List<String> mayHasErrorList = new ArrayList<>();
        batchSchoolExcels.forEach(schoolExcelData -> trainSchools.forEach(trainSchool -> {
            double similarScore = simpleTextSimilarity.similarScore(schoolExcelData.getSchoolName(), trainSchool.getSchoolName());
            if (similarScore > BatchOrderConstant.DEFAULT_LOAD_FACTOR) {
                mayHasErrorList.add(schoolExcelData.getSchoolName());
            }
        }));
        return new AnalysisResult().setMayHaveErrorSchoolList(mayHasErrorList);
    }


}
