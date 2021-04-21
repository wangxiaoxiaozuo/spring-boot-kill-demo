package com.kill.core.service.impl;

import com.common.data.utils.BizVerify;
import com.kill.core.constant.DbConstant;
import com.kill.core.entity.DataSourceManager;
import com.kill.core.entity.TableInfo;
import com.kill.core.entity.params.DataSourceManagerParams;
import com.kill.core.mapper.DataSourceManagerMapper;
import com.kill.core.service.IDataSourceManagerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
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
        BizVerify.verify(dataSourceManager != null, "数据源为空");

        HikariConfig configuration = new HikariConfig();
        configuration.setJdbcUrl(dataSourceManager.getDataSourceUrl());
        configuration.setPassword(dataSourceManager.getDataUserName());
        configuration.setUsername(dataSourceManager.getDataUserPassword());
        configuration.setDriverClassName(DbConstant.MYSQL_DRIVER);
        List<TableInfo> tableInfoList = new ArrayList<>();

        try (HikariDataSource dataSource = new HikariDataSource(configuration);
             Connection conn = dataSource.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(DbConstant.GET_TABLE_NAME_SQL)) {

            while (rs.next()) {
                TableInfo tableInfo = new TableInfo()
                    .setTableName(rs.getString("table_name"))
                    .setEngine(rs.getString("engine"))
                    .setTableRows(rs.getLong("table_rows"));
                tableInfoList.add(tableInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tableInfoList;
    }
}
