package com.example.mypackage.atguigu4.execrise;

import com.example.mypackage.atguigu2.bean.ExamStudent;
import com.example.mypackage.atguigu3.util.JDBCUtils;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Scanner;

public class Exer2Test {

    /*********************/
    // 删除数据：方法一
    @Test
    public void Exec2Delete() {
        System.out.println("请输入学生的考号：");
        Scanner scanner = new Scanner(System.in);
        String examCard = scanner.nextLine();
        // 查询指定准考号的学生
        String sql = "select FlowID flowID, Type type, IDCard iDCard, ExamCard examCard, StudentName studentName, Location location, Grade grade from examstudent where ExamCard = ?";
        ExamStudent examStudent = getInstance(ExamStudent.class, sql, examCard);
        if (examStudent == null) {
            System.out.println("查无此人，请重新输入");
        } else {
            String sql1 = "delete from examstudent where examCard = ?";
            int deleteCount = update(sql1, examCard);
            if (deleteCount > 0) {
                System.out.println("删除成功了"+ deleteCount +"条信息！");
            } else {
                System.out.println("删除失败");
            }
        }
    }
    // 删除数据：优化方法二
    @Test
    public void Exec2Delete2() {
        System.out.println("请输入学生的考号：");
        Scanner scanner = new Scanner(System.in);
        String examCard = scanner.nextLine();
        String  sql = "delete from examstudent where examCard = ?";
        int deleteCount = update(sql, examCard);
        if (deleteCount > 0) {
            System.out.println("删除成功了"+ deleteCount +"条信息！");
        } else {
            System.out.println("删除失败");
        }
    }


    /*********/
    // 获取数据
    @Test
    public void Exec2Query() {
        System.out.println("请输入您要输入的类型：");
        System.out.println("a: 准考证号");
        System.out.println("b: 身份证号");
        Scanner scanner = new Scanner(System.in);
        String selection = scanner.nextLine();
        String number = "";
        String sql = "";
        ExamStudent examStudent;

        switch (selection) {
            case ("a") :
                System.out.println("请输入你的准考证号码：");
                number = scanner.nextLine();
                sql = "select FlowID flowID, Type type, IDCard iDCard, ExamCard examCard, StudentName studentName, Location location, Grade grade from examstudent where ExamCard = ?";
                examStudent = getInstance(ExamStudent.class, sql, number);
                if (examStudent != null) {
                    System.out.println(examStudent);
                } else {
                    System.out.println("没有这个准考证号的考生");
                }
                break;
            case ("b"):
                System.out.println("请输入你的身份证号码：");
                number = scanner.nextLine();
                sql = "select FlowID flowID, Type type, IDCard iDCard, ExamCard examCard, StudentName studentName, Location location, Grade grade from examstudent where IDCard = ?";
                examStudent = getInstance(ExamStudent.class, sql, number);
                if (examStudent != null) {
                    System.out.println(examStudent);
                } else {
                    System.out.println("没有这个身份证号的考生");
                }
                break;
            default:
                System.out.println("你的输入有误，请重新进入程序！！");
                break;
        }
    }

    @Test
    public void Exec2Insert() {
        boolean keepGoing = true;
        while (keepGoing) {
            System.out.println("插入新的考生信息：");
            Scanner scanner = new Scanner(System.in);
            System.out.println("四or六级考试（4, 6）:");
            int type = scanner.nextInt();
            System.out.println("请输入身份证号：");
            String IDCard = scanner.nextLine();
            System.out.println("请输入准考证号码：");
            String ExamCard = scanner.nextLine();
            System.out.println("请输入学生姓名：");
            String StudentName = scanner.nextLine();
            System.out.println("请输入区域：");
            String Location = scanner.nextLine();
            System.out.println("请输入成绩：");
            String Grade = scanner.nextLine();
            String sql = "Insert into examstudent(`Type`, IDCard, ExamCard, StudentName, Location, Grade) values (?,?,?,?,?,?)";
            int InsertCount = update(sql, type, IDCard, ExamCard, StudentName, Location, Grade);
            if (InsertCount > 0) {
                System.out.println("成功添加了" + InsertCount + "条数据");
            } else {
                System.out.println("添加失败！");
            }

            System.out.println("是否继续录入？");
            keepGoing = scanner.nextBoolean();
        }
    }


    /**************************************************************/
    /*删除数据*/



    /**************************************************************/
    /* 查询数据 ！！！ 记得创建新的类，来对应查询的*/
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


    /********************************************************************/
    /*插入新数据*/
    public int update(String sql, Object... args)  {
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
