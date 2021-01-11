package com.kill.core.annotation;

import java.lang.annotation.*;


/**
 * <pre>
 * +--------+---------+-----------+---------+
 * |        基于注解限制单个IP下单次数         |
 * +--------+---------+-----------+---------+
 * </pre>
 *
 * @author wangjian
 * @since 2020/07/02 19:56:09
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IpLimit {


}
