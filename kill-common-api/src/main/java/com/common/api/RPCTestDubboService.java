package com.common.api;

public interface RPCTestDubboService {


    /**
     * 测试dubbo服务注册和启动的问题
     *
     * @param name 参数名
     * @return 返回参数名称
     */
    String helloService(String name);

}
