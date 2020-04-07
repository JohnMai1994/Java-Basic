package com.example.mypackage.atguigu2.preparedstatement.crud;
import com.example.mypackage.atguigu2.bean.Customer;
import com.example.mypackage.atguigu3.util.JDBCUtils;
import org.junit.Test;
import java.lang.reflect.Field;
import java.sql.*;

// 针对于Customers表的查询操作
public class CustomerForQuery {
    @Test
    public void testQueryForCustomers() {
        String sql = "select *  from customers where id = ?";
        Customer customer = queryForCustomer(sql, 10);
        System.out.println(customer);

    }


    /*
    * 针对customers表的通用查询操作
    * */

    public Customer queryForCustomer(String sql , Object... args)  {
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
                Customer customer = new Customer();
                // 处理结果集一行数据中的每一列
                for(int i = 0; i < columnCount; i++) {
                    // 获取列值
                   Object columnValue = rs.getObject(i+1);
                   // 获取每个列的列名
                    String columnName = rsmd.getColumnName(i+1);
                   // 给cust对象指定的columnName属性，赋值为columnValue, 通过反射
                    Field field = Customer.class.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(customer, columnValue);
                }
                return customer;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, ps, rs);
        }


        return  null;
    }



    @Test
    public void testQuery1()  {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "select id, name, email, birth from customers where id = ? ";
            ps = conn.prepareStatement(sql);
            ps.setObject(1, 2);
            // 执行并返回结果集
            resultSet = ps.executeQuery();
            // 处理结果集
            if(resultSet.next()) { // next(): 判断结果集的下一条是否有数据，如果有数据返回true，并指针下移，如果返回false，指针不下移
                // 获取当前这条数据的各个字段值
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String email = resultSet.getString(3);
                Date birth = resultSet.getDate(4);
                // 方式一
    //            System.out.println("id = " + id + ", name = " + name + ", email = " + email + ", birth = " + birth);
                // 方式二:封装到数组中
    //            Object[] data = new Object[] {id, name, email, birth};
                // 方式三:将数据封装为一个对象
                Customer customer = new Customer(id, name, email, birth);
                System.out.println(customer);
            } else {
                System.out.println("No data in this Selection ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        JDBCUtils.closeResource(conn, ps, resultSet);


    }

}
