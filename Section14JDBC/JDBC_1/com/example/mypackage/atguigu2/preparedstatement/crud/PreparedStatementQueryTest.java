package com.example.mypackage.atguigu2.preparedstatement.crud;


import com.example.mypackage.atguigu2.bean.Customer;
import com.example.mypackage.atguigu2.bean.Order;
import com.example.mypackage.atguigu3.util.JDBCUtils;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/*
* 使用PreparedStatement实现针对不同表的通用查询操作
* */
public class PreparedStatementQueryTest {

    @Test
    public void testGetForList() {
        String sql = "select id, name, email from customers where id < ?" ;
        List<Customer> customersList = getForList(Customer.class, sql, 5);
        customersList.forEach(System.out::println);
    }


    public <T> List<T> getForList(Class<T> clazz, String sql, Object... args) {
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
            // 创建集合对象
            List<T> list = new ArrayList<T>();

            while (rs.next()) {
                T t = clazz.newInstance();
                // 处理结果集一行数据中的每一列: 给t对象指定属性赋值
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
                list.add(t);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, ps, rs);
        }
        return null;

    }



    // 获取一个对象的方法
    @Test
    public void testGetInstance() {
        String sql = "select * from customers where id = ?";
        Customer customer = getInstance(Customer.class, sql, 4);
        System.out.println(customer);

        String sql1 = "select order_id orderId, order_name orderName, order_date orderDate from `order` where order_id = ?";
        Order order = getInstance(Order.class, sql1, 2);
        System.out.println(order);
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

