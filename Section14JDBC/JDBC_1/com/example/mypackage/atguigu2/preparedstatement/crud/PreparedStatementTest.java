package com.example.mypackage.atguigu2.preparedstatement.crud;


import com.example.mypackage.atguigu1.statement.crud.User;
import com.example.mypackage.atguigu3.util.JDBCUtils;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Scanner;

// 演示使用PreparedStatement替换Statement，解决SQL注入问题

/* 除了结局Statement的拼串问题，PreparedStatement还有哪些好处？
    1. PreparedStatement操作Blob的数据，而Statement做不到
    2. PreparedStatement可以实现高效的批量操作
*
* */
public class PreparedStatementTest {
    @Test
    public void testLogin() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please, your user name:");
        String user = scanner.nextLine();
        System.out.println("Your password");
        String password = scanner.nextLine();
        String sql = "SELECT user, password From user_table WHERE user = ? And password = ?;";
        User returnUser = getInstance(User.class, sql,user, password);
        if (returnUser != null) {
            System.out.println("Login Successfully");
        } else {
            System.out.println("User name not exist or password error");
        }
    }





    // 目的：针对于不同的表的通用的查询操作，返回表中的一条记录
    public <T> T getInstance(Class<T> clazz, String sql, Object... args){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1, args[i]);
            }

            rs = ps.executeQuery();
            // 获取结果集resultSet的元数据，即列名等
            // 元数据： String name = "Tome" : 数据是"Tom", 元数据就是String和name
            ResultSetMetaData rsmd = rs.getMetaData();
            // 通过ResultSetMetaData获取结果集中的列数
            int columnCount = rsmd.getColumnCount();

            if (rs.next()) {
                T t = clazz.newInstance();

                // 处理结果集一行数据中的每一列
                for(int i = 0; i < columnCount; i++) {
                    // 获取列值
                    Object columnValue = rs.getObject(i+1);
                    // 获取每个列的列名
                    String columnLabel = rsmd.getColumnLabel(i+1);
                    // 给cust对象指定的columnName属性，赋值为columnValue, 通过反射
                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t, columnValue);
                }
                return t;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, ps, rs);
        }
        return  null;
    }

}
