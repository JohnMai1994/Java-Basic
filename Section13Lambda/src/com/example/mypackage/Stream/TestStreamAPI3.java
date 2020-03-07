package com.example.mypackage.Stream;

import com.example.mypackage.Lambda0.Employee;
import org.junit.Test;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestStreamAPI3 {

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
    public void test() {
        // 是否全部匹配 Status状态是BUSY
        boolean b1 = employees.stream()
                .allMatch((employee -> employee.getStatus().equals(Employee.Status.BUSY)));
        System.out.println(b1);

        // 是否有其中一个匹配 Status状态是BUSY
        boolean b2= employees.stream()
                .anyMatch((employee -> employee.getStatus().equals(Employee.Status.BUSY)));
        System.out.println(b2);

        // 是否没有匹配 Status所有元素
        boolean b3= employees.stream()
                .noneMatch((employee -> employee.getStatus().equals(Employee.Status.BUSY)));
        System.out.println(b3);

    }

    @Test
    public void test2() {

        // Optional<Employee> 避免返回是空null
        Optional<Employee> employee = employees.stream()
                .sorted((e1,e2) -> Double.compare(e1.getSalary(), e2.getSalary()))
                .findFirst();

        System.out.println(employee.get());

        //
        Optional<Employee> employee1 =employees.stream()
                .filter((e -> e.getStatus().equals(Employee.Status.FREE)))
                .findAny();
        System.out.println(employee1.get());

    }

    @Test
    public void test3() {
        Long count = employees.stream()
                .count();
        System.out.println(count);

        Optional<Double> op2 =  employees.stream()
                .map(Employee::getSalary)
                .min(Double::compareTo);
        System.out.println(op2.get());

    }

    @Test
    public void test4() {
        List<Integer> list = Arrays.asList(1,2,3,4,5,66,7,8,11);
        Integer sum = list.stream()
                .reduce(1, (x, y) -> x*y);   // identity 起始值作为x 反复结合，得到新值
        System.out.println(sum);

        Optional<Double> employSum = employees.stream()
                .map(Employee::getSalary)
                .reduce(Double::sum);
        System.out.println(employSum.get());

    }


    @Test
    public void test5() {
       Set<String> set =  employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toSet());

        set.forEach(System.out::println);

        System.out.println("-------------------");

        List<String> list =  employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toList());

        list.forEach(System.out::println);

        HashSet<String> hs = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toCollection(HashSet::new));

        hs.forEach(System.out::println);

        
    }

}
