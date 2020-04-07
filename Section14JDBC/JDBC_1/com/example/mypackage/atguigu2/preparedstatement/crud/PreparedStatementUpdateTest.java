package com.example.mypackage.atguigu2.preparedstatement.crud;


import com.example.mypackage.TestMyconnection;
import com.example.mypackage.atguigu3.util.JDBCUtils;
import org.junit.Test;

import java.io.InputStream;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Properties;

/*
* 使用PrpareStatement来替换Statement，实现对数据库的增删改操作
* 增删改
*
*
* */
public class PreparedStatementUpdateTest {

    // 增删改
    @Test
    public void testcommonUpdate() {
        // 删
//        String sql = "delete from customers where id = ?";
//        commonUpdate(sql, 3);
        // 改
//        String sql2 = "update customers set email = ? where name = ?";
//        commonUpdate(sql2, "abc@1234.com", "周杰伦");
        // 增
        String sql3 = "INSERT INTO customers (name, email, birth) values(?,?,?)";
        commonUpdate(sql3, "帅哥", "abc@iqwoeur.com", "2000-01-02");
    }


    // 通用的增删改操作
    public  void commonUpdate(String sql, Object ...args) {  // sql中占位符的个数与args的长度相同
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            // 1. 获取数据库的连接
            conn = JDBCUtils.getConnection();
            // 2. 预编译sql语句，返回preparedStatement的实例
            ps = conn.prepareStatement(sql);
            // 3. 填充占位符
            for(int i = 0; i < args.length; i++) {
                ps.setObject(i+1, args[i]);  // 小心参数声明错误
            }
            // 4. 执行
            ps.execute();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 5. 关闭支援
            JDBCUtils.closeResource(conn, ps);
        }
    }



    // 修改customers表的一条记录
    @Test
    public void testEdit() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            // 1. 获取数据库的连接
            conn = JDBCUtils.getConnection();
            // 2. 预编译sql语句，返回preparedStatement的实例
            String sql = "update customers set name = ? where id = ?";
             ps = conn.prepareStatement(sql);
            // 3. 填充占位符
            ps.setObject(1, "麦加东");
            ps.setObject(2, 6);

            // 4. 执行
            ps.execute();

            // 5. 资源管理
            JDBCUtils.closeResource(conn, ps);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }






    // 向customers表中添加一条记录： 增
    @Test
    public void testInsert() {
        Connection conn = null;
        PreparedStatement ps = null;
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
             conn = DriverManager.getConnection(url,user, password);
            System.out.println(conn);

            //4. 预编译SQL语句，返回PreparedStatement的实例
            String sql = "INSERT INTO customers (name, email, birth) values(?,?,?)"; // 占位符
             ps = conn.prepareStatement(sql);
            //5. 填充占位符
            ps.setString(1, "哪吒");
            ps.setString(2, "nezha@gmail.com");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date = sdf.parse("1500-01-01");
            ps.setDate(3, new Date(date.getTime()));

            //6. 执行操作sql
            ps.execute();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                //7. 关闭资源
                ps.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

}
