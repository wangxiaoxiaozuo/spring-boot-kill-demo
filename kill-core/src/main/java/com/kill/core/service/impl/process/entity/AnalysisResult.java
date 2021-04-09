package com.kill.core.service.impl.process.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * <pre>
 * +--------+---------+-----------+---------+
 * |                                        |
 * +--------+---------+-----------+---------+
 * </pre>
 *
 * @author wangjian
 * @since 2021/04/09 09:37:57
 */
@Data
@Accessors(chain = true)
public class AnalysisResult {

    /**
     * 包含错误的数据
     */
    private List<String> hasErrorSchoolList;

    /**
     * 可能包含错误的数据
     */
    private List<String> mayHaveErrorSchoolList;

    /**
     * 没有错误的数据
     */
    private List<String> noErrorSchoolList;

}
