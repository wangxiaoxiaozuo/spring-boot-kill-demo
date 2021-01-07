package com.web.config.common;

import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.PackageVersion;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.TimeZone;

/**
 * <pre>
 * +--------+---------+-----------+---------+
 * |                                        |
 * +--------+---------+-----------+---------+
 * </pre>
 *
 * @author wangjian
 * @since 2020/04/25 13:15:41
 */
@Configuration
@ConditionalOnClass(ObjectMapper.class)
@AutoConfigureBefore(JacksonAutoConfiguration.class)
@Slf4j
public class HealthWebAutoConfiguration {


    @Bean("jackson2ObjectMapperBuilderCustomizer")
    public Jackson2ObjectMapperBuilderCustomizer customizer() {
        return builder -> {
            builder.locale(Locale.CHINA);
            builder.timeZone(TimeZone.getTimeZone(ZoneId.systemDefault()));
            builder.simpleDateFormat(DatePattern.NORM_DATETIME_PATTERN);
            builder.modules(new JacksonModule());
        };
    }

    static class JacksonModule extends SimpleModule {
        JacksonModule() {
            super(PackageVersion.VERSION);
            this.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(
                DateTimeFormatter.ofPattern(DatePattern.NORM_DATETIME_PATTERN)));
            this.addSerializer(LocalDate.class, new LocalDateSerializer(
                DateTimeFormatter.ofPattern(DatePattern.NORM_DATE_PATTERN)));
            this.addSerializer(LocalTime.class, new LocalTimeSerializer(
                DateTimeFormatter.ofPattern(DatePattern.NORM_TIME_PATTERN)));
            this.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(
                DateTimeFormatter.ofPattern(DatePattern.NORM_DATETIME_PATTERN)));
            this.addDeserializer(LocalDate.class, new LocalDateDeserializer(
                DateTimeFormatter.ofPattern(DatePattern.NORM_DATE_PATTERN)));
            this.addDeserializer(LocalTime.class, new LocalTimeDeserializer(
                DateTimeFormatter.ofPattern(DatePattern.NORM_TIME_PATTERN)));
        }
    }




}
