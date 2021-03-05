package com.kill.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * <pre>
 * +--------+---------+-----------+---------+
 * |                                        |
 * +--------+---------+-----------+---------+
 * </pre>
 *
 * @author wangjian
 * @since 2021/03/02 11:38:42
 */
@EnableDiscoveryClient
@SpringBootApplication
public class KillGateWayApplication {

    public static void main(String[] args) {
        SpringApplication.run(KillGateWayApplication.class, args);
    }
}
