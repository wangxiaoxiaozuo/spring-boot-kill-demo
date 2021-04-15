package com.kill.core.entity.common;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <pre>
 * +--------+---------+-----------+---------+
 * |                                        |
 * +--------+---------+-----------+---------+
 * </pre>
 *
 * @author wangjian
 * @since 2019/12/25 18:01:20
 */
@Data
public class PageParams implements Serializable {

    @NotNull(message = "页数不能为空")
    private Integer page;

    @NotNull(message = "条数不能为空")
    private Integer size;
}
