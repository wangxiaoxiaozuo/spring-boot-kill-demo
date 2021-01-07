package com.web.config.validator;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;

/**
 * @author : wangjian
 * @desc 参数校验 遇到错误不继续验证余下参数项
 * @date 2019/7/5 13:59
 **/
@Configuration
public class ValidatorAutoConfiguration {

    @Bean
    public Validator validator() {
        return Validation
            .byProvider(HibernateValidator.class)
            .configure()
            .failFast(true)
            .buildValidatorFactory().getValidator();
    }
}
