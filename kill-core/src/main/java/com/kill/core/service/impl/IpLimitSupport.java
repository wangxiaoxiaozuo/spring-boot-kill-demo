package com.kill.core.service.impl;

import cn.hutool.core.date.DateUtil;
import com.common.data.entity.BizException;
import com.common.data.utils.IpUtils;
import com.kill.core.constant.CoreIPLimitConstant;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

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
public class IpLimitSupport {

    @Autowired
    private RedisTemplate redisTemplate;


    @Pointcut("@annotation(com.kill.core.annotation.IpLimit)")
    private void pointcut() {
    }

    @Before("pointcut()")
    public void before() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String key = CoreIPLimitConstant.IP_LIMIT_KEY + IpUtils.getIPAddress(attributes.getRequest()) + "_" + DateUtil.today();
        int times;
        Object timesForRedis = redisTemplate.opsForValue().get(key);
        if (Objects.isNull(timesForRedis)) {
            times = 0;
        } else {
            times = Integer.parseInt(timesForRedis.toString());
        }
        log.info("当前访问请求key：{},访问次数：{}", key, times);
        if (times >= 10) {
            throw new BizException("当前IP限制次数超限");
        } else {
//            redisTemplate.opsForValue().increment(key);
            redisTemplate.opsForValue().set(key, (times + 1) + "", 24, TimeUnit.HOURS);
        }
    }

}
