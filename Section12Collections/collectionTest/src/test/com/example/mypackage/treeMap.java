package test.com.example.mypackage;

import org.junit.Test;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;


public class treeMap {
    @Test
    public void test1() {
        class Person{
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
            // 要让哈希表存储不重复的元素，就必须重新写hashCode和equals方法

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

        // 利用TreeMap存储，自定义对象person作为键
        // 自定义object实现comparable接口或者传入comparator比较器
        TreeMap<Person, String> map = new TreeMap<Person, String >(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                if (o1 == o2){
                    return 0;
                }
                int result = o1.getAge() - o2.getAge();
                if (result == 0){
                    return o1.getName().compareTo(o2.getName());
                }
                return result;
            }
        });

        map.put(new Person("张三", 12), "JAVA");
        map.put(new Person("李四", 50), "IOS");
        map.put(new Person("小花", 32), "JS");
        map.put(new Person("小黑", 32), "PHP");
        map.put(new Person("张三", 12), "C++");

        // foreach遍历
        for (Map.Entry<Person,String> entry : map.entrySet()){
            System.out.println(entry.getKey()+"==="+entry.getValue());
        }
    }

}
