package test.com.example.mypackage;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class arrayList {


    @Test
    public void test2() {
        class stack {
            private LinkedList<String > linkedList = new LinkedList<String>();

            // Push
            public void push(String str) {
                linkedList.addFirst(str);
            }

            // pop
            public String pop() {
                return linkedList.removeFirst();
            }


            // peek
            public String peek() {
                return linkedList.peek();
            }

            // isEmpty
            public Boolean isEmpty() {
                return linkedList.isEmpty();
            }


        }
    }



    @Test
    public void test1() {
        // 使用ArrayList进行添加和遍历
        List<String > list = new ArrayList<>();

        list.add("interface 1");
        list.add("接口 2");
        list.add("我是大帅哥");

        // 第一种遍历，用迭代器
        Iterator<String > it = list.iterator();
        while (it.hasNext()) {
            String next = it.next();
            System.out.println(next);
        }
        System.out.println("------------------------------------");
        // 第二种遍历, 使用for loop
        for (String next: list) {
            System.out.println(next);
        }

        // 第三种遍历，使用foreach
        System.out.println("------------------------------------");
        list.forEach((String next) -> System.out.println(next));



    }
}
