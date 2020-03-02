package com.example.mypackage.Lambda2;

import com.example.mypackage.Lambda0.Employee;

import org.junit.Test;

import java.io.PrintStream;
import java.security.PublicKey;
import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class TestMethodRef {

    // 对象::实例方法名
    @Test
    public void test1() {
        Consumer<String> con = (x) -> System.out.println(x);

        // 方法引用的方式
        PrintStream ps = System.out;
        Consumer<String > con1 = ps::println;
        con1.accept("Hello 世界");
    }

    @Test
    public void test2() {
        Employee emp = new Employee("John", 32, 333);
        Supplier<String > sup = ()->emp.getName();
        String str = sup.get();
        System.out.println(str);

        Supplier<Integer> sup1 = emp::getAge;
        int age = sup1.get();
        System.out.println(age);
    }

    // 类::静态方法名
    @Test
    public void test3() {
        Comparator<Integer> com = (x, y) -> Integer.compare(x,y);

        Comparator<Integer> com1 = Integer::compare;

        System.out.println(com1.compare(1, 2));
    }

    // 类:: 实例方法名
    @Test
    public void test4() {
        BiPredicate<String, String > bp = (x, y) -> x.equals(y);

        BiPredicate<String, String > bp2 = String::equals;
    }

    @Test
    public void test5(){
        // 根据接口的 参数不一样，Employee::new调用的构造函数也不一样
        // Supplier 参数为0，所以调用 Employee();
        Supplier<Employee> sup = () -> new Employee();
        Supplier<Employee> sup1 = Employee::new;
        // Function 参数为1，所以调用 Employee(int Age); 当然也是要看你的接口的
        Function<Integer, Employee> fun = (x) -> new Employee(x);
        Function<Integer, Employee> fun2 = Employee::new;
        System.out.println(fun.apply(33));
        System.out.println(fun2.apply(22));
    }

    // 数组引用
    @Test
    public void test6() {
        Function<Integer, String []> function = (x) -> new String[x];
        String[] strs = function.apply(10);
        System.out.println(strs.length);

        Function<Integer, String[]> function1 = String[]::new;
        String[] strs1 = function1.apply(20);
        System.out.println(strs1.length);

    }


}
