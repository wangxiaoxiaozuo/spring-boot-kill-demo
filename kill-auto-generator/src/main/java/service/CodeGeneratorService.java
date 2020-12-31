package service;

import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;

public interface CodeGeneratorService {

    /**
     * 生成配置文件
     */
    GlobalConfig createGeneratorConfig();


    /**
     * 数据库连接配置
     */
    DataSourceConfig createDatasourceConfig();


    /**
     * 包配置
     */
    PackageConfig createPackageConfig();

    /**
     *
     */
    InjectionConfig createInjectionConfig();


    /**
     * 模板配置
     */
    TemplateConfig createTemplateConfig();

    /**
     * 策略配置
     */
    StrategyConfig createStrategyConfig();

}
