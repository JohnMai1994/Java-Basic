package com.example.mypackage.Lambda0;


import org.junit.Test;

import java.util.*;

public class MainTest {

    // 原来的匿名内部类
    @Test
    public void test1() {
        Comparator<Integer> com = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);  // 核心有用的代码
            }
        };
        TreeSet<Integer> ts = new TreeSet<>(com);
    }

    // Lambda 表达式
    @Test
    public void test2() {
        Comparator<Integer> com = (x, y) -> Integer.compare(x,y);
        TreeSet<Integer> ts = new TreeSet<>(com);

    }



//    !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

    List<Employee> employees = Arrays.asList(
            new Employee("John", 18, 999.4),
            new Employee("SB Tom", 1, -1),
            new Employee("Qif", 45, 933.55),
            new Employee("Jessica", 43, 99.4),
            new Employee("Mai", 28, 9349)
    );



    // 添加需求：获取当前公司中员工年龄大于35的员工信息
    public List<Employee> fillterEmployees (List<Employee> list) {
        List<Employee> emps = new ArrayList<>();

        for (Employee emp: list) {
            if (emp.getAge() >= 35){
                emps.add(emp);
            }
        }
        return emps;
    }

//    添加需求：获取当前公司员工工资大于5000的员工工资
public List<Employee> fillterEmployees2 (List<Employee> list) {
    List<Employee> emps = new ArrayList<>();

    for (Employee emp: list) {
        if (emp.getSalary() >= 5000){
            emps.add(emp);
        }
    }
    return emps;
}

    @Test
    public void test3() {
        List<Employee> lsit = fillterEmployees(employees);

        for (Employee employee: lsit) {
            System.out.println(employee);
        }
    }

// 如果不断的给相识的需求，那么就会写很多冗余代码！！
//   优化方法1：通过建造一个新的interface来优化
    // 这种策略模式叫做：策略设计模式
    // 缺点就是，每次新要求都需要新建一个类


    public List<Employee> filterEmplyee(List<Employee> list, MyPredicate<Employee> myPredicate){
        List<Employee> emps = new ArrayList<>();
        for (Employee employee: list) {
            if (myPredicate.test(employee)) {
                emps.add(employee);
            }
        }
        return emps;
    }

    @Test
    public void test4() {
        List<Employee> lsit = filterEmplyee(employees, new FilterEmplyeeByAge());
        for (Employee employee: lsit) {
            System.out.println(employee);
        }
        System.out.println("-----------------------------------------------------");
        List<Employee> list2 = filterEmplyee(employees, new FilterEmplyeeBySalary());

        for (Employee employee: list2) {
            System.out.println(employee);
        }
    }

    // 优化放法2：匿名内部类
    @Test
    public void test5() {
        List<Employee> list = filterEmplyee(employees, new MyPredicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getSalary() >=200;
            }
        });

        for (Employee employee: list) {
            System.out.println(employee);
        }
    }

    // 优化方法3：Lambda 表达式
    @Test
    public void test6() {

        List<Employee> list = filterEmplyee(employees, (employee) -> employee.getSalary() <=5000 );
        list.forEach(System.out::println);

//        for (Employee employee: list) {
//            System.out.println(employee);
//        }

    }

    // 优化方法4: Stream API
    @Test
    public void test7(){
        employees.stream()
                .filter((employee -> employee.getSalary() <= 500))
                .limit(2)
                .forEach(System.out::println);

        System.out.println("--------------------------------------");
        employees.stream()
                .map(Employee::getName)
                .forEach(System.out::println);
    }


}