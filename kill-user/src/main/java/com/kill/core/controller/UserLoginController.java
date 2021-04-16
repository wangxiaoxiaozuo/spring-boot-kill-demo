package com.kill.core.controller;

import com.kill.core.entity.SysResource;
import com.kill.core.entity.UserLoginParams;
import com.kill.core.service.ISysResourceService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
@Api(tags = "用户管理相关接口")
@AllArgsConstructor
public class UserLoginController {


    private ISysResourceService sysResourceService;


    @PostMapping("/login")
    public Integer userLogin(UserLoginParams params) {
        System.out.println(params.toString());
        return 1;
    }

    @GetMapping
    public List<SysResource> getSysResourceList() {
        return sysResourceService.list();
    }

}
