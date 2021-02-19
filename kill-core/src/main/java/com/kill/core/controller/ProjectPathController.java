package com.kill.core.controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.common.data.entity.BizException;
import com.kill.core.annotation.IpLimit;
import com.kill.core.entity.ProjectPath;
import com.kill.core.entity.PushMessageParams;
import com.kill.core.entity.params.ProjectPathParams;
import com.kill.core.properties.DrugProperties;
import com.kill.core.service.IProjectPathService;
import com.kill.core.service.PubMessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wangjian
 * @since 2021-01-07
 */
@RestController
@RequestMapping("/core/project")
@Api(tags = "项目管理")
@AllArgsConstructor
public class ProjectPathController {

    private IProjectPathService projectPathService;

    private DrugProperties drugProperties;

    private PubMessageService pubMessageService;

    @GetMapping
    @ApiOperation("查询")
    @IpLimit
    public IPage<ProjectPath> getDataByPage(@Validated ProjectPathParams pathParams) {
//        throw new BizException("这是测试异常");
//        BeanUtil.toBean()
        return projectPathService.page(new Page<>(pathParams.getPageNum(), pathParams.getPageSize()));
    }


    @GetMapping("/test")
    @ApiOperation("dubbo接口测试")
    public String testDubbo() {
        Integer delayTime = drugProperties.getDelayTime();
        System.out.println(delayTime);
        return projectPathService.testDubbo();
    }

    @GetMapping("/sub/{openId}")
    public String sendSubMessage(@PathVariable String openId) {
        pubMessageService.sendSubMessage(openId);
        return "发送成功！！！！！";
    }

}
