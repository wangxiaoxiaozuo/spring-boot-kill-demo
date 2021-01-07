package com.web.config.swagger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * <pre>
 * +--------+---------+-----------+---------+
 * |                                        |
 * +--------+---------+-----------+---------+
 * </pre>
 *
 * @author wangjian
 * @since 2020/10/21 11:14:35
 */
@Configuration
@ConditionalOnProperty(prefix = "lr.swagger", value = "enabled", matchIfMissing = true)
@EnableSwagger2
@EnableConfigurationProperties(SwaggerProperties.class)
public class SwaggerAutoConfiguration {

    @Autowired
    private SwaggerProperties swaggerProperties;

    @Value("${server.servlet.context-path}")
    private String visitPath;

    @Bean
    public Docket createRestApi() {
//        List<Parameter> parameters = new ArrayList<>();
//        parameters.add(new ParameterBuilder()
//            .name("Authorization")
//            .description("bearer + value")
//            .modelRef(new ModelRef("string"))
//            .parameterType("header")
//            .required(false)
//            .build());
//        parameters.add(new ParameterBuilder()
//            .name("TenantKey")
//            .description("多租户")
//            .modelRef(new ModelRef("string"))
//            .parameterType("header")
//            .required(false)
//            .build());

        return new Docket(DocumentationType.SWAGGER_2)
            .enable(swaggerProperties.getEnable())
            .pathMapping(visitPath)
//            .globalOperationParameters(parameters)
            .select()
            .apis(RequestHandlerSelectors.basePackage(swaggerProperties.getBasePackagePath()))
            .paths(PathSelectors.any())
            .build().apiInfo(new ApiInfoBuilder()
                .title(swaggerProperties.getTittle())
                .description("0.0.1版本接口文档")
                .version(swaggerProperties.getVersion())
                .contact(new Contact(swaggerProperties.getProjectManager(), "", "mailwangj@163.com"))
                .license("The Apache License")
                .build());
    }


}
