package com.kill.core.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.kill.core.entity.SysRole;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <pre>
 * +--------+---------+-----------+---------+
 * |                                        |
 * +--------+---------+-----------+---------+
 * </pre>
 *
 * @author wangjian
 * @since 2020/01/11 15:20:02
 */
@Data
public class SysUserVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer userId;

    private String userName;

    private String realName;

    private String sex;

    private String phone;

    private String mail;

    /**
     * 0-正常，1-锁定
     */
    private Integer locked;

    private List<SysRole> roleList;

}
