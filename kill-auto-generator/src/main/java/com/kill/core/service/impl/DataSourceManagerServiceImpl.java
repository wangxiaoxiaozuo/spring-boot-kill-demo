package com.kill.core.service.impl;

import com.common.data.utils.BizVerify;
import com.kill.core.entity.DataSourceManager;
import com.kill.core.entity.TableInfo;
import com.kill.core.entity.params.DataSourceManagerParams;
import com.kill.core.mapper.DataSourceManagerMapper;
import com.kill.core.service.IDataSourceManagerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wangjian
 * @since 2021-04-20
 */
@Service
public class DataSourceManagerServiceImpl extends ServiceImpl<DataSourceManagerMapper, DataSourceManager> implements IDataSourceManagerService {


    @Override
    public void saveDataSource(DataSourceManagerParams dataSourceManagerParams) {
        DataSourceManager dataSourceManager = new DataSourceManager();
        BeanUtils.copyProperties(dataSourceManagerParams, dataSourceManager);
        baseMapper.insert(dataSourceManager);
    }

    @Override
    public List<TableInfo> getTableListByDataSourceId(Integer dataSourceId) {
        DataSourceManager dataSourceManager = baseMapper.selectById(dataSourceId);
        BizVerify.verify(dataSourceManager != null, "未查询到指定的数据源");

        return null;
    }
}
