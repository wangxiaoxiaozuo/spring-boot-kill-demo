package com.kill.core.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.kill.core.entity.GeneratorConfigData;
import lombok.AllArgsConstructor;
import com.kill.core.service.CodeGeneratorService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * +--------+---------+-----------+---------+
 * |                                        |
 * +--------+---------+-----------+---------+
 * </pre>
 *
 * @author wangjian
 * @since 2020/12/31 11:31:25
 */
@AllArgsConstructor
public class CodeGeneratorServiceImpl implements CodeGeneratorService {

    private GeneratorConfigData configData;

    @Override
    public GlobalConfig createGeneratorConfig() {
        return new GlobalConfig()
                .setOutputDir(System.getProperty("user.dir") + configData.getProjectName() + "/src/main/java")
                .setAuthor(configData.getAuthorName())
                .setOpen(false)
                .setSwagger2(true);
    }

    @Override
    public DataSourceConfig createDatasourceConfig() {
        return new DataSourceConfig()
                .setUrl(configData.getDataSourceUrl())
                .setDriverName(configData.getDataSourceDriverName())
                .setUsername(configData.getDataSourceUserName())
                .setPassword(configData.getDataSourcePassword());
    }

    @Override
    public PackageConfig createPackageConfig() {
        return new PackageConfig()
                .setModuleName(configData.getModuleName())
                .setParent(configData.getPatentPath());
    }


    @Override
    public InjectionConfig createInjectionConfig() {
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>();
                map.put("vo", configData.getPatentPath() + "." + configData.getModuleName() + ".entity.vo");
                this.setMap(map);
            }
        };

        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(configData.getTemplatePath()) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return System.getProperty("user.dir") + configData.getProjectName()+ "/src/main/resources/mapper/"
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });

        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(configData.getEntityTemplatePath()) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                String temp = (configData.getPatentPath() + "." + configData.getModuleName()).replace('.', '/') + "/entity";
                return System.getProperty("user.dir") + configData.getProjectName() + "/src/main/java/" + temp
                        + "/" + tableInfo.getEntityName() + StringPool.DOT_JAVA;
            }
        });
        //添加VO自动生成模板
        focList.add(new FileOutConfig(configData.getEntityVOTemplatePath()) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                String temp = (configData.getPatentPath() + "." + configData.getModuleName()).replace('.', '/') + "/entity/vo";
                return System.getProperty("user.dir") + configData.getProjectName() + "/src/main/java/" + temp
                        + "/" + tableInfo.getEntityName() + "VO" + StringPool.DOT_JAVA;
            }
        });
        cfg.setFileOutConfigList(focList);
        return cfg;
    }


    @Override
    public TemplateConfig createTemplateConfig() {
        return new TemplateConfig()
                .setXml(null);
    }

    @Override
    public StrategyConfig createStrategyConfig() {
        return new StrategyConfig()
                .setNaming(NamingStrategy.underline_to_camel)
                .setColumnNaming(NamingStrategy.underline_to_camel)
                .setEntityLombokModel(true)
                .setRestControllerStyle(true)
                .setSuperEntityColumns("id")
                .setInclude(configData.getTableNames())
                .setControllerMappingHyphenStyle(true)
                .setTablePrefix(configData.getModuleName() + "_");

    }
}
