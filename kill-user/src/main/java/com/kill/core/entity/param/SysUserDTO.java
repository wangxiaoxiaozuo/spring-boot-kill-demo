package com.kill.core.entity.param;

import com.common.data.entity.PageParams;
import lombok.Data;

/**
 * <pre>
 * +--------+---------+-----------+---------+
 * |                                        |
 * +--------+---------+-----------+---------+
 * </pre>
 *
 * @author wangjian
 * @since 2019/12/25 18:00:40
 */
@Data
public class SysUserDTO extends PageParams {

    private String condition;


}
