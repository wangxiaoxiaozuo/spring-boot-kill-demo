package com.web.config.swagger;

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
 * @since 2020/10/21 11:10:01
 */
@Configuration
@ConfigurationProperties(prefix = "lr.swagger")
@Data
public class SwaggerProperties {


    /**
     * 扫描包路径
     */
    private String basePackagePath;

    /**
     * 服务名称
     */
    private String tittle;

    /**
     * 服务版本号
     */
    private String version = "0.0.1";

    /**
     * 是否启用Swagger
     */
    private Boolean enable = true;

    /**
     * 项目负责人
     */
    private String projectManager = "王建";


}
