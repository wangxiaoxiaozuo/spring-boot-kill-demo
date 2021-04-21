package com.kill.core.service;

import com.kill.core.entity.DataSourceManager;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kill.core.entity.TableInfo;
import com.kill.core.entity.params.DataSourceManagerParams;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wangjian
 * @since 2021-04-20
 */
public interface IDataSourceManagerService extends IService<DataSourceManager> {

    void saveDataSource(DataSourceManagerParams dataSourceManagerParams);

    List<TableInfo> getTableListByDataSourceId(Integer dataSourceId);
}
