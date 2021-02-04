package com.kill.core.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.stereotype.Component;

/**
 * <pre>
 * +--------+---------+-----------+---------+
 * |                                        |
 * +--------+---------+-----------+---------+
 * </pre>
 *
 * @author wangjian
 * @since 2020/07/02 19:58:14
 */
@Aspect
@Component
@Slf4j
public class ExportDubboServiceSupport {



    @Before("execution(* org.springframework.cloud.client.serviceregistry.ServiceRegistry.register(*)) && args(registration)")
    public void before(Registration registration) {
        DubboBootstrap dubboBootstrap = DubboBootstrap.getInstance();
        dubboBootstrap.start();
        log.info("export ------ success ");
    }

}
