package com.example.mypackage.Stream;

import com.example.mypackage.Lambda0.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class TestStreamAPI2 {
    /*
	* .filter --- 接收 Lambda, 从流中排除某些元素
	* .limit --- 截断流，使其元素不超过给定数量
	* .skip(n) --- 跳过元素，返回一个扔掉了前n个元素的流，若流中元素不足n个，则返回一个空流，与limit(n) 互补
	* .distinct() --- 筛选，通过stream所生成element的hashCode() 和 equals() 去除重复element
     */

    List<Employee> employees = Arrays.asList(
            new Employee("John", 18, 999.4),
            new Employee("John", 18, 999.4),
            new Employee("SB Tom", 1, -1),
            new Employee("Qif", 45, 933.55),
            new Employee("Jessica", 43, 99.4),
            new Employee("Mai", 28, 9349)
    );

    // 内部迭代
    @Test
    public void test1() {
        // 中间操作: 不会执行任何操作
        // filter
        Stream<Employee> stream =  employees.stream()
                                        .filter((employee) -> {
                                            System.out.println("Stream API 的中间操作");
                                            return employee.getAge()>35;
                                        });
        //终止操作： 一次性执行全部内容（惰性求值） (注意，如果没有终止操作的话，中间操作是不会执行)
        stream.forEach(System.out::println);
    }


    // 外部迭代
    @Test
    public void test2() {
        Iterator<Employee> it = employees.iterator();

        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    @Test
    public void test3() {
        employees.stream()
                .filter((e)-> {
                    System.out.println("hi");
                    return e.getSalary() > 10;
                })
                .limit(2) // 如果找到了2个合乎条件的，就停止
                .forEach(System.out::println);
    }

    @Test
    public void test4() {
        employees.stream()
                .filter((e)-> {
                    System.out.println("hi");
                    return e.getSalary() > 10;
                })
                .skip(1)  // 跳过第n个合乎条件的，显示剩下的
                .forEach(System.out::println);
    }

    @Test
    public void test5() {
        employees.stream()
                .distinct()   // 如果Employee这个类中没有hashCode和equals的话，没办法去重复
                .forEach(System.out::println);
    }

    @Test
    public void test6() {
        List<String> list = Arrays.asList("bbb", "aaa", "cc");

        list.stream()
                .map((str) -> str.length())   // 这里用的是Function
                .forEach(System.out::println);
        System.out.println("-------------------------------");
        employees.stream()
                .map(Employee::getName)
                .forEach(System.out::println);

        System.out.println("-------------------------------");

        Stream<Stream<Character>> streamStream = list.stream()
                .map(TestStreamAPI2::filterCharacter);  // {{b,b,b}, {a,a,a}, {c,c,c}}

        streamStream.forEach((sm) -> {
            sm.forEach(System.out::println);
        });

        System.out.println("-------------------------------");

        Stream<Character> characterStream = list.stream()
                .flatMap(TestStreamAPI2::filterCharacter); // {b,b,b,a,a,a,c,c,c}

        characterStream.forEach(System.out::println);


    }

    @Test
    public void test7() {
        List<String> list = Arrays.asList("bbb", "aaa", "cc");

        list.stream()
                .sorted()
                .forEach(System.out::println);

        System.out.println("---------------------------------");
        list.stream()
                .sorted((e1, e2) -> {
                    if (e1.length() > e2.length()){
                        return e2.compareTo(e1);
                    } else {
                        return e1.length() - e2.length();
                    }

                })
                .forEach(System.out::println);
    }





    public static Stream<Character> filterCharacter(String string) {
        List<Character> list = new ArrayList<>();

        for (Character ch: string.toCharArray()) {
            list.add(ch);
        }
        return list.stream();
    }



}
