package client;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import entity.GeneratorConfigData;
import service.CodeGeneratorService;
import service.impl.CodeGeneratorServiceImpl;

/**
 * <pre>
 * +--------+---------+-----------+---------+
 * |                                        |
 * +--------+---------+-----------+---------+
 * </pre>
 *
 * @author wangjian
 * @since 2020/12/31 11:32:32
 */
public class CodeGeneratorClient {


    public static void main(String[] args) {
        GeneratorConfigData generatorConfigData = new GeneratorConfigData();
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
