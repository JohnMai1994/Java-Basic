package com.example.mypackage;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class TestMyconnection {

    // 方式一
    @Test
    public void test1() {
        try {
            //1. 获取MySQL-Java的Driver对象
            Driver driver = new com.mysql.cj.jdbc.Driver();
            //2.
            // jdbc:mysql: 协议
            // localhost: ip地址
            // 3306: 默认的mysql的端口号
            // test: test这个数据库
            String url = "jdbc:mysql://localhost/myemployees";
            //3. 将用户名和密码封装在Properties里面
            Properties info = new Properties();
            info.setProperty("user", "root");
            info.setProperty("password", "password");
            //4. 连接数据库
            Connection conn = driver.connect(url, info);
            System.out.println("连接成功！");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 方法二: 对方式一的迭代:在如下的程序中不出现第三方的api
    @Test
    public void test2() {
        try {
            //1. 获取Driver实现类对象：使用反射
            Class.forName("com.mysql.cj.jdbc.Driver"); // Driver的静态方法帮我们注册了。所以省略注册步骤

            //2. 提供要连接的数据库
            String url = "jdbc:mysql://localhost/myemployees";

            //3. 将用户名和密码封装在Properties里面
            Properties info = new Properties();
            info.setProperty("user", "root");
            info.setProperty("password", "password");
            //4. 获取连接
            Connection conn = DriverManager.getConnection(url, info);
            /*
            可替换 3-4步骤：
            String url = "jdbc:mysql://localhost/myemployees";
            String user = "root";
            String password = "password";
            Connection conn = DriverManager.getConnection(url, user, password);
             */
            System.out.println(conn);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    // 方式三：（最终版）将数据库连接需要的4个基本信息声明在配置文件中，通过读取配置文件的方式，获取连接
    // 好处：
    // 1. 实现数据与代码的分离，实现了解耦
    // 2. 如果需要修改配置文件信息，可以避免程序重新打包


    @Test
    public void test3() {

        try {
            //1. 读取jdbc.properties配置文件的4个基本信息
            //    user, password, url, DriverClass
            InputStream is = TestMyconnection.class.getClassLoader().getResourceAsStream("jdbc.properties");
            Properties properties =  new Properties();
            properties.load(is);
            //
            String user = properties.getProperty("user");
            String password = properties.getProperty("password");
            String url = properties.getProperty("url");
            String classDriver = properties.getProperty("driverClass");

            //2. 加载驱动
            Class.forName(classDriver);

            //3. 获取连接
            Connection conn = DriverManager.getConnection(url,user, password);
            System.out.println(conn);


        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }


    }






}
