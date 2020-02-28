package test.com.example.mypackage;

import org.junit.Test;

import java.util.LinkedHashSet;

public class linkedHashSet {
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



    @Test
    public void test1() {
        // 利用LinkedHashSet来存储自定义对象 Person
        LinkedHashSet<Person> set = new LinkedHashSet<Person>();

        set.add(new Person("张三",123));
        set.add(new Person("李四",12));
        set.add(new Person("王五",22));
        set.add(new Person("张三",123));

        for (Person p : set) {
            System.out.println(p);

        }
        // 结果:向集合中存储两个张三对象，但是集合中就成功存储了一个
        //    并且！！！
        //  存进的顺序，和取出的顺序是一致的，FIFO
        // Output:
//        Person [name: 张三 , age: 123]
//        Person [name: 李四 , age: 12]
//        Person [name: 王五 , age: 22]
    }

}
