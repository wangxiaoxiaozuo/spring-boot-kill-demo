package com.kill.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author wangjian
 * @since 2019-12-25
 */
@Data
@Accessors(chain = true)
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer userId;

    /**
     * 登录用户名
     */
    @NotNull(message = "用户名不能为空")
    @Length(min = 3, max = 20, message = "用户名在3~10个字符之间")
    private String userName;

    /**
     * 登录用户密码
     */
    @NotNull(message = "用户密码不能为空")
    @Length(min = 6, max = 15, message = "用户密码在6~15个字符之间")
    private String password;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 性别 0-男 1-女
     */
    private String sex;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 邮箱地址
     */
    private String mail;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 0-正常，1-锁定
     */
    private Integer locked;

    /**
     * 0-正常，1-删除
     */
    @TableLogic
    private String deleted;

}
