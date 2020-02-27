package test.com.example.mypackage;

import org.junit.Test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class hashSet {

    @Test
    public void test2() {
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

        Set<Person> set = new HashSet<Person>();

        set.add(new Person("John", 12));
        set.add(new Person("Mike", 12));
        set.add(new Person("SB Tom", 12));
        set.add(new Person("John", 12));

        for (Person p : set) {
            System.out.println(p);
        }

        //所以在向HashSet集合中存储自定义对象时，为了保证set集合的唯一性，那么必须重写hashCode和equals方法。


    }


    @Test
    public void test1(){
        Set<String>  set = new HashSet<>();
        set.hashCode();
        set.add("111");
        set.add("11");
        set.add("11");
        set.add("abc");

        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            String str = it.next();
            System.out.println(str);
            // 这里的打印序列，是根据str.hashCode()的序列排序的
            //   str.hashCode 越小，则排越前
            System.out.println(str + " hashCode is: "+ str.hashCode());
        }


    }

}
