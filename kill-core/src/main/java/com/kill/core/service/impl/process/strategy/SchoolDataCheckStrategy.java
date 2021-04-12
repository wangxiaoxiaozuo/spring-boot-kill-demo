package com.kill.core.service.impl.process.strategy;

import com.kill.core.entity.BatchSchoolExcel;
import com.kill.core.service.impl.process.entity.SchoolDataCheckResult;

import java.util.List;

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


    SchoolDataCheckResult check(List<BatchSchoolExcel> batchSchoolExcels);

}
