package com.kill.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author wangjian
 * @since 2021-04-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DataSourceManager implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "data_source_id", type = IdType.AUTO)
    private Integer dataSourceId;

    /**
     * 数据源名称
     */
    private String dataSourceName;

    /**
     * 数据源路径
     */
    private String dataSourceUrl;

    /**
     * 用户名
     */
    private String dataUserName;

    /**
     * 用户密码
     */
    private String dataUserPassword;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 删除
     */
    private Integer deleted;


}
