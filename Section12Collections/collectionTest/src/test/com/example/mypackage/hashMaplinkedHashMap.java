package test.com.example.mypackage;

import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class hashMaplinkedHashMap {
    @Test
    public void test1() {


        // 利用hashMap存储，自定义对象Person作为key
        //  为了保证key的唯一性，必须重写hashCode和equals方法
        class Person{
            private String name;
            private int age;

            public Person(String name, int age) {
                this.name = name;
                this.age = age;
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

        // LinkedHashMap 和 HashMap用法区别不大

//        HashMap<Person, String > map = new HashMap<Person, String >();
        LinkedHashMap<Person, String > map = new LinkedHashMap<Person, String >();

        map.put(new Person("张三", 12), "Java");
        map.put(new Person("李四", 12), "Java");
        map.put(new Person("张三", 14), "IOS");
        map.put(new Person("王五", 12), "PHP");
        map.put(new Person("张三", 12), "C++");

        // loop 有4种方法
        // 方法一：普遍使用，二次取值
        System.out.println("通过Map.keySet遍历key和Value");
        for (Person p : map.keySet()) {
            System.out.println(p + " 它的值是" + map.get(p) );
        }

        // 方法二：通过Map.entrySet使用iterator遍历key和balue
        System.out.println();
        System.out.println("通过Map.entrySet使用iterator遍历key和balue");
        Iterator<Map.Entry<Person, String >> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Person, String> entry = it.next();
            System.out.println(entry.getKey() + " 它的值是" + entry.getValue());
        }

        // 方法三：通过Map.entrySet遍历key和value
        System.out.println();
        System.out.println("通过Map.entrySet遍历key和value");
        for (Map.Entry<Person,String > entry : map.entrySet()) {
            System.out.println(entry.getKey() + " 它的值是" + entry.getValue());
        }

        // 方法四：
        System.out.println();
        System.out.println("通过Map.values()遍历所有的value，但不能遍历key");
        for (String value : map.values()){
            System.out.println("values: " + value);
        }
    }



}
