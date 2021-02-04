package com.kill.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.common.api.RPCTestDubboService;
import com.kill.core.entity.ProjectPath;
import com.kill.core.mapper.ProjectPathMapper;
import com.kill.core.service.IProjectPathService;
import org.apache.dubbo.config.annotation.Reference;
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
public class ProjectPathServiceImpl extends ServiceImpl<ProjectPathMapper, ProjectPath> implements IProjectPathService {


    @Reference
    private RPCTestDubboService rpcTestDubboService;

    @Override
    public String testDubbo() {
        return rpcTestDubboService.helloService("王建");
    }

}
