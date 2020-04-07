package com.atguigu1.util;


import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/*
* @Description: 操作数据库的工具类
* */
public class JDBCUtils {


    // 获取数据库的连接
    public static Connection getConnection() throws Exception {
        Connection conn = null;
        //1. 读取jdbc.properties配置文件的4个基本信息
        //    user, password, url, DriverClass
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");
        Properties properties = new Properties();
        properties.load(is);
        //
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String classDriver = properties.getProperty("driverClass");

        //2. 加载驱动
        Class.forName(classDriver);

        //3. 获取连接
        conn = DriverManager.getConnection(url,user, password);
        return conn;
    }

    // 关闭连接和Statement
    public static void closeResource(Connection conn, Statement ps) {
        // 关闭ps
        try {
            if (ps != null) ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 关闭conn
        try {
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // 关闭资源的操作
    public static void closeResource(Connection conn, Statement ps, ResultSet resultSet) {
        // 关闭ps
        try {
            if (ps != null) ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 关闭conn
        try {
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 关闭resultSet
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
