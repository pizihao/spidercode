package com.example.server;

/**
 * 查询mysql表结构，存储过程，索引DDL的sql
 */
public interface InformationSql {

    /**
     * 获取所有的表，当前数据库表
     */
    String TABLES = "SHOW TABLES;";

    /**
     * 删除数据库
     */
    String DROP_DATABASES = "DROP DATABASE %s;";

    /**
     * 创建数据库
     */
    String CREATE_DATABASES = "CREATE DATABASE %s;";

    /**
     * 查询建表语句
     */
    String SELECT_CREATE_TABLE = "SHOW CREATE TABLE %s;";

    /**
     * 删除数据库
     */
    String DROP_TABLE = "DROP TABLE %s;";

    /**
     * 查询所有的存储过程
     */
    String PROCEDURE = "show procedure status where Db = '%s';";

    /**
     * 查询建表语句
     */
    String SELECT_CREATE_PROCEDURE = "SHOW CREATE PROCEDURE %s;";

    /**
     * 删除存储过程
     */
    String DELETE_PROCEDURE = "DROP PROCEDURE IF EXISTS %s;";

    /**
     * 存储过程开始的标记
     */
    String DELIMITER_START = "DELIMITER //\n";
    /**
     * 存储过程结束的标记
     */
    String DELIMITER_STOP = "// \nDELIMITER ;";

}
