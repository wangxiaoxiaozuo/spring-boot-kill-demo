package com.kill.core.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <pre>
 * +--------+---------+-----------+---------+
 * |    代码生成器配置文件                     |
 * +--------+---------+-----------+---------+
 * </pre>
 *
 * @author wangjian
 * @since 2020/12/31 11:08:17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeneratorConfigData {


    /**
     * 表名称
     */
    private String[] tableNames = new String[]{"region"};
    /**
     * 项目前缀
     */
    private String patentPath = "com.kill";

    /**
     * 模块名称
     */
    private String moduleName = "core";

    /**
     * 生成的包存储位置项目名称
     */
    private String projectName = "/kill-auto-generator";

    /**
     * 署名
     */
    private String authorName = "wangjian";

    /**
     * 数据库连接
     */
//    private String dataSourceUrl = "jdbc:mysql://127.0.0.1:3306/kill-demo?useSSL=false&serverTimezone=GMT%2B8&allowPublicKeyRetrieval=true";
//    private String dataSourceUrl = "jdbc:mysql://127.0.0.1:3306/ocr_data?useSSL=false&serverTimezone=GMT%2B8&allowPublicKeyRetrieval=true";
//    private String dataSourceUrl = "jdbc:p6spy:mysql://120.221.160.3:33061/new_ihp_further?useSSL=false&serverTimezone=GMT%2B8&allowPublicKeyRetrieval=true";
    private String dataSourceUrl = "jdbc:mysql://10.10.50.72:3306/train_project_center?generateSimpleParameterMetadata=true&amp&useSSL=false";

    /**
     * 数据库驱动
     */
    private String dataSourceDriverName = "com.mysql.cj.jdbc.Driver";

    /**
     * 数据库用户名
     */
//    private String dataSourceUserName = "root";
    private String dataSourceUserName = "srt_dt";

    /**
     * 数据库密码
     */
    private String dataSourcePassword = "t81Qxr2EZ8ws";
//    private String dataSourcePassword = "123456";

    /**
     * 模板文件存储位置
     */
    private String templatePath = "/templates/mapper.xml.ftl";

    /**
     * 实体类模板文件位置
     */
    private String entityTemplatePath = "/templates/entity.java.ftl";

    /**
     * vo 模板文件位置
     */
    private String entityVOTemplatePath = "/templates/vo.java.ftl";

}
