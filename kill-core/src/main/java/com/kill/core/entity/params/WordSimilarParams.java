package com.kill.core.entity.params;

import lombok.Data;

/**
 * <pre>
 * +--------+---------+-----------+---------+
 * |                                        |
 * +--------+---------+-----------+---------+
 * </pre>
 *
 * @author wangjian
 * @since 2021/04/08 16:38:30
 */
@Data
public class WordSimilarParams {

    private String word1;

    private String word2;
}
