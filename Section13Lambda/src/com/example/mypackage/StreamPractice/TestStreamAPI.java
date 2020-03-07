package com.example.mypackage.StreamPractice;

import com.example.mypackage.Lambda0.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;

public class TestStreamAPI {



    // 给定一个数字列表，如何返回一个由每个数字的平方构成的列表呢？
        // [1,2,3,4,5] -> [1,4,9,16,25]

    @Test
    public void test1() {
        Integer[] nums = new Integer[]{1,2,3,4,5};

        Arrays.stream(nums)
                .map((x)-> x*x)
                .forEach(System.out::println);
    }

    // 2. 怎么用map和reduce方法数一数，stream中由多少employee呢？
    List<Employee> employees = Arrays.asList(
            new Employee("John", 18, 999.4, Employee.Status.BUSY),
            new Employee("SB Tom", 1, -1, Employee.Status.FREE),
            new Employee("Qif", 45, 933.55, Employee.Status.FREE),
            new Employee("Qif", 45, 933.55, Employee.Status.FREE),
            new Employee("Qif", 45, 933.55, Employee.Status.FREE),
            new Employee("Jessica", 43, 99.4, Employee.Status.VOCATION),
            new Employee("Mai", 28, 9349, Employee.Status.BUSY),
            new Employee("Mai", 28, 9349, Employee.Status.BUSY)
    );

    @Test
    public void test2() {
        Optional<Integer> count =  employees.stream()
                .map((e) -> 1)
                .reduce(Integer::sum);

        System.out.println(count.get());

    }



}
