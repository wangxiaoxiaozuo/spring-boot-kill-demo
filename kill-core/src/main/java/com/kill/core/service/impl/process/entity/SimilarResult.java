package com.kill.core.service.impl.process.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <pre>
 * +--------+---------+-----------+---------+
 * |                                        |
 * +--------+---------+-----------+---------+
 * </pre>
 *
 * @author wangjian
 * @since 2021/04/09 10:08:29
 */
@Accessors(chain = true)
@Data
public class SimilarResult {

    /**
     * 相似词语
     */
    private String word;

    /**
     * 相似分数值
     */
    private double similarScore;
}
