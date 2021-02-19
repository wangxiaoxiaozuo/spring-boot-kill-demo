package com.kill.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * <pre>
 * +--------+---------+-----------+---------+
 * |                                        |
 * +--------+---------+-----------+---------+
 * </pre>
 *
 * @author wangjian
 * @since 2021/02/19 15:58:07
 */
@Configuration
public class RestTemplateConfig {


    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
