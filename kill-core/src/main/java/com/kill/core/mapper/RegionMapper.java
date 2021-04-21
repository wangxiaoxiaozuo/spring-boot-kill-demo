package com.kill.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kill.core.entity.Region;
import com.kill.core.entity.TrainSchoolDO;

import java.util.List;

/**
 * <p>
 * 行政区 Mapper 接口
 * </p>
 *
 * @author wangjian
 * @since 2021-04-21
 */
public interface RegionMapper extends BaseMapper<Region> {

    List<TrainSchoolDO> getAllSchool();

}
