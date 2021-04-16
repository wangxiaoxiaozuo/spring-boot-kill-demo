package com.kill.core.service.impl;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.kill.core.entity.GeneratorConfigData;
import com.kill.core.service.CodeGeneratorService;
import com.kill.core.service.IGeneratorService;
import org.springframework.stereotype.Service;

/**
 * <pre>
 * +--------+---------+-----------+---------+
 * |                                        |
 * +--------+---------+-----------+---------+
 * </pre>
 *
 * @author wangjian
 * @since 2021/04/15 18:11:08
 */
@Service
public class IGeneratorServiceImpl implements IGeneratorService {


    @Override
    public void codeGenerator(String tableName) {
        GeneratorConfigData generatorConfigData = new GeneratorConfigData();
        generatorConfigData.setTableNames(new String[]{tableName});
        CodeGeneratorService codeGeneratorService = new CodeGeneratorServiceImpl(generatorConfigData);
        // 全局配置
        new AutoGenerator().setGlobalConfig(codeGeneratorService.createGeneratorConfig())
            // 数据源配置
            .setDataSource(codeGeneratorService.createDatasourceConfig())
            // 文件配置
            .setPackageInfo(codeGeneratorService.createPackageConfig())
            // 使用模板配置
            .setTemplate(codeGeneratorService.createTemplateConfig())
            .setCfg(codeGeneratorService.createInjectionConfig())
            // 策略配置
            .setStrategy(codeGeneratorService.createStrategyConfig())
            // 模板引擎配置
            .setTemplateEngine(new FreemarkerTemplateEngine())
            // 执行
            .execute();
    }
}
