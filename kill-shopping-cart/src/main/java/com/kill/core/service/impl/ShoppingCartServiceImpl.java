package com.kill.core.service.impl;

import com.kill.core.service.IShoppingCartService;
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
public class ShoppingCartServiceImpl implements IShoppingCartService {

    @Override
    public String getShopList() {
        return "这是一个测试购物服务启动逻辑";
    }
}
