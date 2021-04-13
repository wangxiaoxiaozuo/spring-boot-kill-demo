package com.kill.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kill.core.entity.MallCategory;

/**
 * <p>
 * 商品类目表，类目和商品(spu)是一对多关系，类目与品牌是多对多关系 服务类
 * </p>
 *
 * @author wangjian
 * @since 2020-01-02
 */
public interface IMallCategoryService extends IService<MallCategory> {

}
