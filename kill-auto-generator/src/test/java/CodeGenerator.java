import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.*;

/**
 * <pre>
 * +--------+---------+-----------+---------+
 * |       mybatis-plus 代码自动生成器        |
 * +--------+---------+-----------+---------+
 * </pre>
 *
 * @author wangjian
 * @since 2020/12/31 09:58:57
 */
public class CodeGenerator {
    private static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(("请输入" + tip + "："));
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {


        String patentPath = "com.health";
        String moduleName = "hospital";

        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        final String projectPath = System.getProperty("user.dir") + "/health-hospital-service";
        gc.setOutputDir(projectPath + "/src/main/java");
        String author = scanner("author");
        gc.setAuthor(author);
        gc.setOpen(false);
        gc.setSwagger2(true);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://39.106.206.137:33061/new_ihp_hospital?useSSL=false&serverTimezone=GMT%2B8&allowPublicKeyRetrieval=true");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("SalesInteli2019");

        mpg.setDataSource(dsc);


        PackageConfig pc = new PackageConfig();
        pc.setModuleName(moduleName);
        pc.setParent(patentPath);
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>();
                map.put("vo", patentPath + "." + moduleName + ".entity.vo");
                this.setMap(map);
            }
        };
        String templatePath = "/templates/mapper.xml.ftl";


        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mapper/"
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });

        //重写entity配置，去掉默认swagger注释
        String templatePath1 = "/templates/entity.java.ftl";
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath1) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                String temp = (patentPath + "." + moduleName).replace('.', '/') + "/entity";
                System.out.println(temp);
                return projectPath + "/src/main/java/" + temp
                        + "/" + tableInfo.getEntityName() + StringPool.DOT_JAVA;
            }
        });
        //添加VO自动生成模板
        String templatePath2 = "/templates/vo.java.ftl";
        focList.add(new FileOutConfig(templatePath2) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                String temp = (patentPath + "." + moduleName).replace('.', '/') + "/entity/vo";
                System.out.println(temp);
                return projectPath + "/src/main/java/" + temp
                        + "/" + tableInfo.getEntityName() + "VO" + StringPool.DOT_JAVA;
            }
        });

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        cfg.setFileOutConfigList(focList);


        mpg.setCfg(cfg);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setSuperEntityColumns("id");
        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

}
