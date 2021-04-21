package com.kill.core.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <pre>
 * +--------+---------+-----------+---------+
 * |                                        |
 * +--------+---------+-----------+---------+
 * </pre>
 *
 * @author wangjian
 * @since 2021/04/15 15:31:24
 */
@Data
@Accessors(chain = true)
public class TableInfo implements Serializable {

    private String tableName;

    private String engine;

    private Long tableRows;

}
