package com.kill.core.service.impl;

import com.kill.core.entity.TableInfo;
import com.kill.core.mapper.TableMapper;
import com.kill.core.service.ITableService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <pre>
 * +--------+---------+-----------+---------+
 * |                                        |
 * +--------+---------+-----------+---------+
 * </pre>
 *
 * @author wangjian
 * @since 2021/04/15 12:00:00
 */
@Service
@AllArgsConstructor
public class ITableServiceImpl implements ITableService {

    private TableMapper tableMapper;


    @Override
    public List<TableInfo> getTableNameList() {
        return tableMapper.getTableNameList();
    }
}
