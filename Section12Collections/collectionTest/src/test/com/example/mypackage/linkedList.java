package test.com.example.mypackage;

import org.junit.Test;

import java.util.LinkedList;

public class linkedList {
    @Test
    public void Queue() {
        class QueueTest {
            private LinkedList<String > linkedList = new LinkedList<String>();
            // Push
            public void push(String str) {
                linkedList.addFirst(str);
            }

            // remove
            public String pop() {
                return linkedList.removeLast();
            }

            // isEmpty
            public Boolean isEmpty() {
                return linkedList.isEmpty();
            }
        }

        QueueTest queue = new QueueTest();
        queue.push("我是第一个进的");
        queue.push("我是第二个进的");
        queue.push("我是第三个进的");
        queue.push("我是第四个进的");
        queue.push("我是第五个进的");

        while (!queue.isEmpty()){
            System.out.println(queue.pop());
        }

    }


    @Test
    public void Stack() {
        class StackTest {
            private LinkedList<String > linkedList = new LinkedList<String>();

            // Push
            public void push(String str) {
                linkedList.addFirst(str);
            }

            // pop
            public String pop() {
                return linkedList.removeFirst();
            }


            // peek 查看栈顶对象而不移除它
            public String peek() {
                return linkedList.peek();
            }

            // isEmpty
            public Boolean isEmpty() {
                return linkedList.isEmpty();
            }
        }

        // 存入
        StackTest stack = new StackTest();
        stack.push("第一个进入的");
        stack.push("第二个进入的");
        stack.push("第三个进入的");
        stack.push("第四个进入的");
        stack.push("第五个进入的");
        // 取出
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
        // 查看 查看最顶的对象
        stack.push("Hello");
        System.out.println(stack.peek());
        stack.push("你好吗");
        System.out.println(stack.peek());

    }


}
