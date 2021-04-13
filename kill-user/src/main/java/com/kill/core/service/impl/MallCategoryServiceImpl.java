package com.kill.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kill.core.entity.MallCategory;
import com.kill.core.mapper.MallCategoryMapper;
import com.kill.core.service.IMallCategoryService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品类目表，类目和商品(spu)是一对多关系，类目与品牌是多对多关系 服务实现类
 * </p>
 *
 * @author wangjian
 * @since 2020-01-02
 */
@Service
public class MallCategoryServiceImpl extends ServiceImpl<MallCategoryMapper, MallCategory> implements IMallCategoryService {

}
