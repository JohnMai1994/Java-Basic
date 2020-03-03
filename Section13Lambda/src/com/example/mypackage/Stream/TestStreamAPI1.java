package com.example.mypackage.Stream;

import com.example.mypackage.Lambda0.Employee;
import org.junit.Test;
import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class TestStreamAPI1 {

    // Stream的三个操作步骤







//    3. 终止操作

    @Test
    public void test1() {
        //    1: 可以通过Collection 系列集合提供的Stream()(串行流) or ParallelStream()(并行流)
        List<String> list = new ArrayList<>();
        Stream<String> stream1 =  list.stream();

        //    2: 通过Arrays 中的静态方法stream() 获取数组流
        Employee[] emps = new Employee[10];
        Stream<Employee> stream2 =  Arrays.stream(emps);

        //    3: 通过Stream 类中的静态方法of()
        Stream<String> stream3 = Stream.of("aa", "bb", "cc");

        //    4: 创建无限流
        // 迭代
        Stream<Integer> stream4 = Stream.iterate(0, (x) -> x+2);  // 从种子开始，不断的x+2 产生结果
        stream4.limit(19).forEach(System.out::println);

        // 生成
        Stream.generate(() -> (Double)Math.random())
                .limit(5)
                .forEach(System.out::println);




    }

}
