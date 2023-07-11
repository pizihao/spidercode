package com.example.server;

import com.google.common.collect.Maps;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.*;
import lombok.experimental.Accessors;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.SQLExec;
import org.apache.tools.ant.types.EnumeratedAttribute;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
//import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 在集成测试环境启动，同步生产环境的模板库到集成测试环境
 */
//@Component
@RequiredArgsConstructor
public class SyncTemplate implements ApplicationRunner {


    private static final String PROD_HOST = "localhost";
    private static final int PROD_PORT = 3306;
    private static final String PROD_USERNAME = "root";
    private static final String PROD_PASSWORD = "root";


    public static final String PROP_DEFAULT_READONLY = "defaultReadOnly";
    public static final String PROP_DRIVER_CLASSNAME = "driverClassName";
    public static final String PROP_DEFAULT_AUTO_COMMIT = "defaultAutoCommit";
    public static final String PROP_PASSWORD = "password";
    public static final String PROP_URL = "url";
    public static final String PROP_USERNAME = "username";
    public static final String MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver";

    public static final String URL_STR = "jdbc:mysql://%s:%s/%s?characterEncoding=UTF-8&useSSL=false&allowMultiQueries=true";

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //  生产环境的数据源
        String databaseName = "framesample_database_templates";
        String url = String.format(URL_STR, PROD_HOST, PROD_PORT, databaseName);
        Properties map = new Properties();
        map.put(PROP_DEFAULT_READONLY, "false");
        map.put(PROP_DEFAULT_AUTO_COMMIT, "false");
        map.put(PROP_DRIVER_CLASSNAME, MYSQL_DRIVER);
        map.put(PROP_URL, url);
        map.put(PROP_PASSWORD, PROD_PASSWORD);
        map.put(PROP_USERNAME, PROD_USERNAME);
        DataSource dataSource = new HikariDataSource(new HikariConfig(map));
//        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        // 删除库 DDL
        String dropDatabase = String.format(InformationSql.DROP_DATABASES, databaseName);
        // 创建库 DDL
        String createDatabase = String.format(InformationSql.CREATE_DATABASES, databaseName);

//        List<Structure> structures = Arrays.stream(StructureType.values())
//                .map(s -> s.getStructure(jdbcTemplate, databaseName))
//                .map(s -> s.collect(Collectors.toList()))
//                .flatMap(Collection::stream)
//                .collect(Collectors.toList());

        // 组合所有的SQl
        StringBuilder sql = new StringBuilder()
                .append(dropDatabase).append("\n")
                .append(createDatabase).append("\n")
                .append("USE ").append(databaseName).append(";\n");
//        structures.forEach(
//                s -> sql.append(s.getSql()).append("\n")
//        );

        System.out.println(sql);

        SQLExec sqlExec = new SQLExec();
        // 设置数据库参数
        sqlExec.setDriver(MYSQL_DRIVER);
        sqlExec.setUrl("jdbc:mysql://" + PROD_HOST + ":" + PROD_PORT + "/" + databaseName
                + "?characterEncoding=UTF-8&useSSL=false&autoReconnect=true&failOverReadOnly=false&allowMultiQueries=true");
        sqlExec.setUserid(PROD_USERNAME);
        sqlExec.setPassword(PROD_PASSWORD);

        // 要执行的脚本
        sqlExec.addText(sql.toString());
        sqlExec.setEncoding("UTF-8");

        SQLExec.DelimiterType dt = new SQLExec.DelimiterType();
        dt.setValue("row");
        sqlExec.setDelimiterType(dt);
        sqlExec.setDelimiter("//");
        sqlExec.setKeepformat(true);

        sqlExec.setOnerror((SQLExec.OnError) (EnumeratedAttribute.getInstance(SQLExec.OnError.class, "abort")));

        sqlExec.setPrint(true);

        sqlExec.setProject(new Project());
        sqlExec.execute();

        System.out.println("执行完成");
    }

    /**
     * 数据库表
     */
    @Setter
    @Getter
    @Accessors(chain = true)
    @ToString
    static class Structure {
        String name;
        String sql;
        String sqlFragment;

        public Structure(String name) {
            this.name = name;
        }

        public String getName() {
            return "`" + name + "`";
        }

        public String getSimpleName() {
            return name;
        }

    }
}
