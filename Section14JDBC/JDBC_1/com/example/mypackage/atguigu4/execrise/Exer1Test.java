package com.example.mypackage.atguigu4.execrise;

import com.example.mypackage.atguigu3.util.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Exer1Test {






    // 课后练习1：插入多个数据
    @Test
    public void Exec1() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入用户名：");
        String name = scanner.nextLine();
        System.out.println("请输入邮箱：");
        String email = scanner.nextLine();
        System.out.println("请输入生日(1992-09-08)：");
        String birthday = scanner.nextLine();
        String sql = "Insert into customers(name, email, birth) values(? , ? , ? )";
        int InsertCount = insert(sql, name, email, birthday );
        if (InsertCount > 0) {
            System.out.println("成功添加了" + InsertCount + "条数据");
        } else {
            System.out.println("添加失败！");
        }
    }



    /********************************************************************/
    /*插入新数据*/
    public int insert(String sql, Object... args)  {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1, args[i]);
            }
            /*
            * 如果是查询操作：ps.execute会返回true
            * 如果是增删改：  ps.execute会返回false
            * // 方式一
            * ps.execute();
            * // 方式二
            * */
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, ps);
        }
        return 0;
    }


}
