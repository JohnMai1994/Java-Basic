package com.example.mypackage.atguigu2.preparedstatement.crud;


import com.example.mypackage.atguigu1.statement.crud.User;
import com.example.mypackage.atguigu2.bean.Order;
import com.example.mypackage.atguigu3.util.JDBCUtils;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.*;

/*
针对于Order表的通用查询操作
* */
public class OrderForQuery {
    /*
    * 针对于表的字段名和类的属性名不相同的情况，
    * 1. 必须神马sql时，使用类的属性名来命名字段的别名
    * 2. 使用ResultSetMetaData时，需要使用getColumnLabel()来替换getColumnName(),获取列的别名
    * 补充说明：
    *   如果sql中没有给字段其别名，getColumnLabel() 获取的就是列名
    * */
    @Test
    public void testOrderForQuery() {
        String sql = "select order_id orderId, order_name orderName, order_date orderDate from `order` where order_id = ?";
        Order order = orderForQuery(sql, 1);
        System.out.println(order);
    }


    // 通用的针对于order表的查询操作
    public Order orderForQuery(String sql, Object... args)  {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1, args[i]);
            }
            // 执行，获取结果集
            rs = ps.executeQuery();
            // 获取结果集的元数据
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();

            if(rs.next()) {
                Order order = new Order();
                for(int i =0; i < columnCount; i++) {
                    // 获取列值
                    Object columnValue = rs.getObject(i+1);
                    // 获取列名
                    // String columnName = rsmd.getColumnName(i+1); 通过getColumnName获取的是列名，而不是别名
                    String columnLabel = rsmd.getColumnLabel(i+1);
                    // 通过反射，将对象指定名的属性赋值为指定的值columnValue
                    Field field = Order.class.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(order, columnValue);
                }
                return order;
            }



        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, ps, rs);
        }
        return null;

    }


    @Test
    public void testQuery1()  {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "SELECT order_id, order_name, order_date FROM `order` WHERE order_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setObject(1, 2);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int orderID = (int) rs.getObject(1);
                String orderName = (String) rs.getObject(2);
                Date orderDate = (Date) rs.getObject(3);
                Order order = new Order(orderID, orderName, orderDate);
                System.out.println(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, ps);
        }



    }


}
