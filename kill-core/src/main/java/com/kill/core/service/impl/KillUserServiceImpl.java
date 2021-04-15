package com.kill.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kill.core.entity.KillUser;
import com.kill.core.mapper.KillUserMapper;
import com.kill.core.service.IKillUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangjian
 * @since 2021-04-15
 */
@Service
public class KillUserServiceImpl extends ServiceImpl<KillUserMapper, KillUser> implements IKillUserService {

}
