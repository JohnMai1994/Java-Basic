package com.example.mypackage;

import java.sql.*;

public class Myconnection {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println("测试一下输出");
        new Myconnection().getCon();
    }
    public Connection getCon() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); //加载数据库驱动 这个路径下的静态代码会被执行

            String url = "jdbc:mysql://localhost/myemployees?useUnicode=true&characterEncoding=utf-8";//设置数据库的地址 设置编码  支持汉字
            String user="root";//用户名
            String password = "password";//用户密码
            Connection conn = DriverManager.getConnection(url, user, password);//使用mysql驱动当中的连接数据库的API

            //执行SQL语句的步骤
            //第一步
            Statement stat = conn.createStatement();
            //第二步
            String command = "select * from employees";
            //第三步
            ResultSet result =  stat.executeQuery(command);

            //按行读取查询结果当中的数据
            while(result.next()) {
                //使用访问器方法获取信息
                System.out.println(result.getString(1) + " " +  result.getString(2) + " " + result.getString(3) );
            }
            result.close();
            return conn;
        }
        catch(Exception e) {
            e.printStackTrace();//异常处理
            return null;
        }
    }


}
