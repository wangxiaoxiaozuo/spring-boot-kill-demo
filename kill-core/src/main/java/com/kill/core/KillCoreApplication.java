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
 * @since 2021/01/07 15:03:37
 */
@SpringBootApplication
@MapperScan("com.kill.core.mapper")
public class KillCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(KillCoreApplication.class, args);
    }


}
