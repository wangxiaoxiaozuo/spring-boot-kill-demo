package com.kill.core.service.impl.process.strategy.impl;

import com.kill.core.constant.BatchOrderConstant;
import com.kill.core.entity.BatchSchoolExcel;
import com.kill.core.entity.TrainSchool;
import com.kill.core.service.impl.process.entity.SchoolDataCheckResult;
import com.kill.core.service.impl.process.entity.SimilarResult;
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
    public SchoolDataCheckResult check(List<BatchSchoolExcel> batchSchoolExcels) {
        SimpleTextSimilarity simpleTextSimilarity = new SimpleTextSimilarity();
        List<SimilarResult> mayHasErrorList = new ArrayList<>();

        for (BatchSchoolExcel schoolExcelData : batchSchoolExcels) {
            double maxScore = 0;
            String maxSimilarWord = "";
            for (TrainSchool trainSchool : trainSchools) {
                double similarScore = simpleTextSimilarity.similarScore(
                    schoolExcelData.getSchoolName(),
                    trainSchool.getSchoolName());
                if (similarScore > maxScore) {
                    maxScore = similarScore;
                    maxSimilarWord = trainSchool.getSchoolName();
                }
            }
            if (maxScore >= BatchOrderConstant.DEFAULT_LOAD_FACTOR && maxScore < 1) {
                SimilarResult similarResult = new SimilarResult()
                    .setWord(schoolExcelData.getSchoolName())
                    .setSimilarScore(maxScore)
                    .setSimilarWord(maxSimilarWord);
                mayHasErrorList.add(similarResult);
                log.info("超过阈值：{},比对词语：{},相似词语为：{},相似分值：{}",
                    BatchOrderConstant.DEFAULT_LOAD_FACTOR, schoolExcelData.getSchoolName(), maxSimilarWord, maxScore);
            }
        }
        return new SchoolDataCheckResult().setMayHaveErrorSchoolList(mayHasErrorList);
    }


}
