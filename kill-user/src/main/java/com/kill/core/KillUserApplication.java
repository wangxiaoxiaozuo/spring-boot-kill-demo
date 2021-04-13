package com.kill.core;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <pre>
 * +--------+---------+-----------+---------+
 * |                                        |
 * +--------+---------+-----------+---------+
 * </pre>
 *
 * @author wangjian
 * @since 2021/04/12 14:12:06
 */
@SpringBootApplication
@MapperScan("com.kill.core.mapper")
public class KillUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(KillUserApplication.class, args);
    }
}
