package com.kill.core.service.impl.rpc;

import cn.hutool.core.util.StrUtil;
import com.common.api.RPCTestDubboService;
import org.apache.dubbo.config.annotation.Service;

/**
 * <pre>
 * +--------+---------+-----------+---------+
 * |                                        |
 * +--------+---------+-----------+---------+
 * </pre>
 *
 * @author wangjian
 * @since 2021/02/03 09:31:56
 */
@Service
public class RPCTestDubboServiceImpl implements RPCTestDubboService {

    @Override
    public String helloService(String name) {
        return StrUtil.concat(true, "我是：", name);
    }


}
