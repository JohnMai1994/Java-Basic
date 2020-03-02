package com.example.mypackage.Lambda1;


import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;

public class TestLambda {


    @Test
    public void test1() {
        int num =0; // jdk 1.7 前，int必须是final


        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World" + num);
            }
        };

        System.out.println("-----------------------------");

        Runnable r1 = () -> System.out.println("Hello World");
        r.run();
        r1.run();

    }

    @Test
    public void test2() {
        Consumer<String> consumer = x-> System.out.println(x);
        consumer.accept("我是大帅哥");

    }
    @Test
    public void test3() {
        Comparator<Integer> comparator = (x1, x2) -> {
            System.out.println("函数式接口：");
            return Integer.compare(x1,x2);
        };
    }

    @Test
    public void test4() {
        Comparator<Integer> comparator = (Integer x1, Integer x2) -> Integer.compare(x1,x2);

    }


    // 需求：对一个数进行运算
    @Test
    public void test5() {
        int num = operation(100, (x) -> x * x);
        System.out.println(num);
        System.out.println(operation(200, (x) -> x + 200));

    }

    public int operation(int num, MyFun myFun){
        return myFun.getValue(num);
    }


}
