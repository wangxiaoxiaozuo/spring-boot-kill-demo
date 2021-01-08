package com.common.data.entity;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <pre>
 * +--------+---------+-----------+---------+
 * |     分页参数                            |
 * +--------+---------+-----------+---------+
 * </pre>
 *
 * @author wangjian
 * @since 2020/02/17 13:17:19
 */
@Data
public class PageParams implements Serializable {

    @NotNull(message = "分页参数不能为空")
    private Integer pageNum = 1;

    @NotNull(message = "分页参数不能为空")
    private Integer pageSize = 10;
}
