package com.kill.core.service.impl;

import com.kill.core.entity.TrainSchool;
import com.kill.core.mapper.TrainSchoolMapper;
import com.kill.core.service.UtilTestService;
import com.kill.core.service.impl.process.SchoolDataCheckHandler;
import com.kill.core.service.impl.process.entity.SchoolDataCheckResult;
import com.kill.core.service.impl.process.strategy.impl.StandardSchoolDataCheckStrategy;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <pre>
 * +--------+---------+-----------+---------+
 * |                                        |
 * +--------+---------+-----------+---------+
 * </pre>
 *
 * @author wangjian
 * @since 2021/04/09 09:17:29
 */
@Service
@AllArgsConstructor
public class UtilTestServiceImpl implements UtilTestService {

    private TrainSchoolMapper trainSchoolMapper;

    @Override
    public SchoolDataCheckResult analysisDataList(MultipartFile file) {

        // 获取数据库中的学校数据
        List<TrainSchool> trainSchools = trainSchoolMapper.selectList(null);
        // 执行策略获取结果
        return new SchoolDataCheckHandler(new StandardSchoolDataCheckStrategy(trainSchools)).process(file);
    }
}
