package test.com.example.mypackage;

import org.junit.Test;

import java.util.Comparator;
import java.util.TreeSet;

public class treeSet {


    @Test
    public void test3() {
        class Person {
            private String name;
            private int age;

            public Person(String name, int age) {
                this.name = name;
                this.age = age;
            }

            public String getName() {
                return name;
            }

            public int getAge() {
                return age;
            }

            @Override
            public int hashCode() {
                final int prime = 31;
                int result = 1;
                result = prime*result + age;
                result = prime*result + name.hashCode();

                return result;
            }

            @Override
            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null) {
                    return false;
                }
                if (getClass() != obj.getClass()) {
                    return false;
                }
                Person other = (Person) obj;
                if (age != other.age) {
                    return false;
                }
                if (!name.equals(other.name)) {
                    return false;
                }
                return true;
            }

            @Override
            public String toString() {
                return "Person [name: " + name + " , age: " + age + "]";
            }

        }
        // 利用TreeSet来存储自定义类Person对象
        // 创建TreeSet对象的时候传入Comparator比较器，使用匿名内部类的方式
        // 比较规则是先按照 年龄排序，年龄相等的情况按照年龄排序
        TreeSet<Person> treeSet = new TreeSet<Person>(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                if (o1 == o2) {
                    return 0;
                }
                int result = o1.getAge() - o2.getAge();
                if (result == 0) {
                    return o1.getName().compareTo(o2.getName());
                }
                return result;
            }
        });

        treeSet.add(new Person("张三1", 20));
        treeSet.add(new Person("张三1", 19));
        treeSet.add(new Person("张三1", 18));
        treeSet.add(new Person("张三1", 17));
        treeSet.add(new Person("张三1", 21));
        treeSet.add(new Person("张三2", 17));
        treeSet.add(new Person("张三2", 18));

        for (Person p : treeSet){
            System.out.println(p);
        }
        // output：
//        Person [name: 张三1 , age: 17]
//        Person [name: 张三2 , age: 17]
//        Person [name: 张三1 , age: 18]
//        Person [name: 张三2 , age: 18]
//        Person [name: 张三1 , age: 19]
//        Person [name: 张三1 , age: 20]
//        Person [name: 张三1 , age: 21]

    }

    @Test
    public void test2() {
        class Person implements Comparable<Person> {
            private String name;
            private int age;

            public Person(String name, int age) {
                this.name = name;
                this.age = age;
            }

            @Override
            public int hashCode() {
                final int prime = 31;
                int result = 1;
                result = prime*result + age;
                result = prime*result + name.hashCode();

                return result;
            }

            @Override
            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null) {
                    return false;
                }
                if (getClass() != obj.getClass()) {
                    return false;
                }
                Person other = (Person) obj;
                if (age != other.age) {
                    return false;
                }
                if (!name.equals(other.name)) {
                    return false;
                }
                return true;
            }

            @Override
            public String toString() {
                return "Person [name: " + name + " , age: " + age + "]";
            }

            @Override
            public int compareTo(Person o) {
                int result = this.age - o.age;
                if (result == 0){
                    return  this.name.compareTo(o.name);
                }
                return result;
            }
        }
        // 利用TreeSet 来存储自定义类Person对象
        TreeSet<Person> treeSet = new TreeSet<Person>();
        treeSet.add(new Person("张三1", 20));
        treeSet.add(new Person("张三1", 19));
        treeSet.add(new Person("张三1", 18));
        treeSet.add(new Person("张三1", 17));
        treeSet.add(new Person("张三1", 21));
        treeSet.add(new Person("张三2", 17));
        treeSet.add(new Person("张三2", 18));

        for (Person p : treeSet){
            System.out.println(p);
        }
        // output：
//        Person [name: 张三1 , age: 17]
//        Person [name: 张三2 , age: 17]
//        Person [name: 张三1 , age: 18]
//        Person [name: 张三2 , age: 18]
//        Person [name: 张三1 , age: 19]
//        Person [name: 张三1 , age: 20]
//        Person [name: 张三1 , age: 21]

    }




    @Test
    public void test1() {
        TreeSet<String> treeSet = new TreeSet<String >();

        treeSet.add("abc");
        treeSet.add("efg");
        treeSet.add("hij");
        treeSet.add("lmn");
        treeSet.add("atr");
        treeSet.add("egf");

        for (String str: treeSet) {
            System.out.println(str);
        }
        // 结果
        /*
        abc
        atr
        efg
        egf
        hij
        lmn
         */


    }
}
