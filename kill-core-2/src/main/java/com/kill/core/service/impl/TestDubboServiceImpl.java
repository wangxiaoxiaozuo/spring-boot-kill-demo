package com.kill.core.service.impl;

import com.kill.core.service.ITestDubboService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wangjian
 * @since 2021-01-07
 */
@Service
public class TestDubboServiceImpl implements ITestDubboService {

    @Override
    public String testDubbo() {
        return "这是一个测试";
    }
}
