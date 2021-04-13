package com.kill.core.controller;

import com.kill.core.entity.UserLoginParams;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <pre>
 * +--------+---------+-----------+---------+
 * |                                        |
 * +--------+---------+-----------+---------+
 * </pre>
 *
 * @author wangjian
 * @since 2021/04/12 19:35:31
 */
@RestController
@Api
public class UserLoginController {


    @PostMapping("/login")
    public Integer userLogin(UserLoginParams params){
        System.out.println(params.toString());
        return 1;
    }
}
