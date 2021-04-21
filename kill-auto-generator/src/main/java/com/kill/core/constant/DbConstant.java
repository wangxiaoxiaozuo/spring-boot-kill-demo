package com.kill.core.constant;

/**
 * <pre>
 * +--------+---------+-----------+---------+
 * |                                        |
 * +--------+---------+-----------+---------+
 * </pre>
 *
 * @author wangjian
 * @since 2021/04/19 18:51:08
 */
public class DbConstant {


    public static final String GET_TABLE_NAME_SQL = "SELECT table_name,engine,table_rows " +
        "FROM information_schema.TABLES WHERE table_schema = 'kill-demo'";

    public static final String MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver";

}
