package com.kill.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * <pre>
 * +--------+---------+-----------+---------+
 * |                                        |
 * +--------+---------+-----------+---------+
 * </pre>
 *
 * @author wangjian
 * @since 2021/01/26 14:17:31
 */
@Configuration
@ConfigurationProperties(prefix = "his.order")
@Data
public class DrugProperties {
    /**
     * 订单延迟时间
     */
    private Integer delayTime;
}
