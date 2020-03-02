package com.example.mypackage.Lambda2;

import org.junit.Test;

import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.*;

public class JavaCoreInterface {

    // Consumer<T> 消费型接口
    class Person{
        String name;
        int money;

        public Person(String name, int money) {
            this.name = name;
            this.money = money;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
        }
    }
    public void happy(Person person, Consumer<Person> consumer) {
        consumer.accept(person);
    }

    @Test
    public void test1() {
        Person John = new Person("John", 10000);
        happy(John, (x) -> {
            System.out.println("John 花了100元");
            x.setMoney(x.getMoney() - 100);
        });
        System.out.println(John.getMoney());

    }

    // 需求：产生指定个数的证书，并放入集合中
    public List<Integer> getNumList(int num, Supplier<Integer> supplier) {
        List<Integer> list = new ArrayList<>();

        for (int i =0; i < num; i++) {
            Integer n = supplier.get();
            list.add(n);
        }
        return list;
    }

    // Supplier<T> 供给型接口:
    @Test
    public void test2() {
        List<Integer> numList = getNumList(99, () -> (int) (Math.random() * 100));
        for (Integer num: numList){
            System.out.println(num);
        }
    }




    // 需求： 用于处理字符串
    public String strHandler(String string, Function<String, String> function) {
        return function.apply(string);
    }

    // Function<T, R> 函数型接口
    @Test
    public void test3() {
       String newStr = strHandler("    abcjJIEjak", (str)-> str.toUpperCase().trim());
        System.out.println(newStr);

    }

    // 需求：判断字符串长度是否大于5
    public List<String> filterStr(List<String > list , Predicate<String > predicate) {
        List<String > stringList = new ArrayList<>();
        for (String str: list){
            if (predicate.test(str)) {
                stringList.add(str);
            }
        }
        return stringList;
    }

    // Predicate<T> 断言型接口

    @Test
    public void test4() {
        List<String > list = Arrays.asList("He", "asd", "adsf", "你好");
        List<String > strList = filterStr(list, (str) -> str.length() > 3);

        System.out.println(strList);
    }



}
