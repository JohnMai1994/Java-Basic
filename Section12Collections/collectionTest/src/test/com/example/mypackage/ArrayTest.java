package test.com.example.mypackage; 

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class genericity {


    @Test
    public void test3() {
        class Animal {
            private String name;

            public Animal(String name) {
                this.name = name;
            }

            public void setName(String name) {
                this.name = name;
            }

            @Override
            public String toString() {
                return "Animal{" +
                        "name='" + name + '\'' +
                        '}';
            }
        }

        class Person<T> {
            private T t;

            public Person(T t) {
                this.t = t;
            }

            public void setT(T t) {
                this.t = t;
            }

            public T getT() {
                return t;
            }
        }
        // 如果<T>的类型是 String的话
        Person<String> personString = new Person<>("John Mai");
        personString.setT("abc");
        System.out.println(personString.getT());
        // 如果<T>的类型是 Int的话
        Person<Boolean> personBoolean = new Person<>(true);
        System.out.println(personBoolean.getT());
        personBoolean.setT(false);
        // 如果<T>的类型是 Animal的话
        Animal cat = new Animal("大肥猫");
        Person<Animal> personAnimal = new Person<>(cat);
        System.out.println(personAnimal.getT());
    }


    @Test
    public void Test2() {
        // 添加泛型
        List<String> myNewList = new ArrayList<String >();
        myNewList.add("Hello");
        myNewList.add("1234");
        myNewList.add("你好吗");
        // 转Iterator 的时候，需要改变他的String
        Iterator<String> it = myNewList.iterator();
        while (it.hasNext()) {
            String string = it.next();
            System.out.println(string);

        }
    }


    @Test
    public void Test1() throws Exception {
        // 不加泛型，就进行添加和遍历
        List myNewList = new ArrayList<>();
        myNewList.add(1);
        myNewList.add("123");
        myNewList.add(3.4f);

        Iterator it = myNewList.iterator();
        while (it.hasNext()) {
            // 没有泛型，所以这里只能使用Object接收
            Object obj = it.next();
            System.out.println(obj);
        }
    }


}
