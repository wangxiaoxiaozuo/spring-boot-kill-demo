package com.kill.core.controller;


import com.kill.core.service.IShoppingCartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wangjian
 * @since 2021-01-07
 */
@RestController
@RequestMapping("/cart")
@Api(tags = "购物车部分逻辑")
@AllArgsConstructor
@Slf4j
public class ShoppingCartController {

    private IShoppingCartService shoppingCartService;

    @GetMapping
    @ApiOperation("获取购物车商品列表")
    public String getShopList() {
        return shoppingCartService.getShopList();
    }

}
