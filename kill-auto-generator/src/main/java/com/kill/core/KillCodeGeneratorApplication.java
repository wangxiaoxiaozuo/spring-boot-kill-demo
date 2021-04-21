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
 * @since 2021/04/15 17:34:32
 */
@SpringBootApplication
@MapperScan("com.kill.core.mapper")
public class KillCodeGeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(KillCodeGeneratorApplication.class, args);
    }
}
