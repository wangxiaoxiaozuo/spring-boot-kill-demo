package com.kill.core.service.impl.process.strategy;

import com.kill.core.entity.BatchSchoolExcel;
import com.kill.core.service.impl.process.entity.AnalysisResult;

import java.util.List;
import java.util.Map;

/**
 * <pre>
 * +--------+---------+-----------+---------+
 * |                                        |
 * +--------+---------+-----------+---------+
 * </pre>
 *
 * @author wangjian
 * @since 2021/04/08 10:04:02
 */
public interface SchoolDataCheckStrategy {


    AnalysisResult check(List<BatchSchoolExcel> batchSchoolExcels);

}
