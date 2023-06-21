package com.example.server;

/**
 * 查询mysql表结构，存储过程，索引DDL的sql
 */
public interface InformationSql {

    /**
     * 删除数据库
     */
    String DROP_DATABASES = "DROP DATABASE %s;";

    /**
     * 创建数据库
     */
    String CREATE_DATABASES = "CREATE DATABASE %s;";

    /**
     * 获取所有的表，当前数据库表
     */
    String TABLES = "SHOW TABLES;";

    /**
     * 查询建表语句
     */
    String SELECT_CREATE_TABLE = "SHOW CREATE TABLE %s;";

    /**
     * 表的列名
     */
    String TABLE_COLUMN = "SELECT CONCAT(GROUP_CONCAT(COLUMN_NAME)) AS insert_statement\n" +
            "FROM (\n" +
            "  SELECT COLUMN_NAME\n" +
            "  FROM INFORMATION_SCHEMA.COLUMNS\n" +
            "  WHERE TABLE_SCHEMA = '%s' AND TABLE_NAME = '%s'\n" +
            ") AS t;";

    /**
     * 转化为insert语句
     */
    String TABLE_INSERT = "INSERT INTO %s (%s) VALUES %s;";

    /**
     * 转化为select语句
     */
    String TABLE_SELECT = "SELECT %s from %s;";

    /**
     * 查询所有的存储过程
     */
    String PROCEDURE = "show procedure status where Db = '%s';";

    /**
     * 查询建表语句
     */
    String SELECT_CREATE_PROCEDURE = "SHOW CREATE PROCEDURE %s;";

    /**
     * 查询所有的触发器 1
     */
    String TRIGGERS = "SHOW TRIGGERS;";

    /**
     * 查询创建触发器语句   3
     */
    String SELECT_CREATE_TRIGGERS = "SHOW CREATE TRIGGER %s;";

    /**
     * 查询所有的视图 1
     */
    String VIEWS = "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.VIEWS WHERE TABLE_SCHEMA = '%s';";

    /**
     * 查询创建视图语句   2
     */
    String SELECT_CREATE_VIEW = "SHOW CREATE VIEW %s;";

    /**
     * 查询所有的函数 1
     */
    String FUNCTIONS = "SHOW FUNCTION STATUS WHERE Db = '%s';";

    /**
     * 查询创建函数语句   3
     */
    String SELECT_CREATE_FUNCTION = "SHOW CREATE FUNCTION %s;";

    /**
     * 查询所有的事件 2
     */
    String EVENTS = "SHOW EVENTS;";

    /**
     * 查询创建事件语句   4
     */
    String SELECT_CREATE_EVENT = "SHOW CREATE EVENT %s;";

}
