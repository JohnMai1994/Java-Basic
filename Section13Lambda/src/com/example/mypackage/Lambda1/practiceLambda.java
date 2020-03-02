package com.example.mypackage.Lambda1;

import com.example.mypackage.Lambda0.Employee;
import org.junit.Test;

import java.security.PublicKey;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class practiceLambda {

    List<Employee> employees = Arrays.asList(
            new Employee("John", 18, 999.4),
            new Employee("SB Tom", 1, -1),
            new Employee("Qif", 45, 933.55),
            new Employee("Jessica", 45, 99.4),
            new Employee("Mai", 28, 9349)
    );

    @Test
    public void test1() {
        Collections.sort(employees, (e1, e2) -> {
            if (e1.getAge() == e2.getAge()) {
                return e1.getName().compareTo(e2.getName());
            } else {
                return Integer.compare(e1.getAge(), e2.getAge());
            }
        });

        for (Employee emp: employees) {
            System.out.println(emp);
        }
    }

    // 需求2：用于处理字符串
    public String strHandler (String str, Myfun2 myfun2) {
        return myfun2.getValue(str);
    }

    @Test
    public void test2() {
        String myStr = "Hello World";
        System.out.println(strHandler(myStr, (x) -> {
            return x.trim().toUpperCase();
        }));
    }

    // 需求3：
    public String intToStr (int num, Myfun3<Integer, String> myfun3) {
        return myfun3.getValue(num);
    }

    @Test
    public void test3() {
        System.out.println(intToStr(55, (x) -> {
            return "我是你妈逼Tom的老父亲" + 55;
        }));
    }

}
