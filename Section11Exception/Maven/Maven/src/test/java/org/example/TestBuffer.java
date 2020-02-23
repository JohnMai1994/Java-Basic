package org.example;

import static org.junit.Assert.assertTrue;


import org.junit.Test;

import java.nio.ByteBuffer;

//




public class TestBuffer
{
    @Test
    public void Test3() {
        // 分配直接缓冲区
        ByteBuffer buf = ByteBuffer.allocateDirect(1024);
//        ByteBuffer buf = ByteBuffer.allocate(1024);

        System.out.println(buf.isDirect());

    }


    @Test
    public void  test2() {
        String str = "Hello World";
        ByteBuffer buf = ByteBuffer.allocate(1024);
        buf.put(str.getBytes());
        buf.flip();
        byte[] dst = new byte[buf.limit()];
        buf.get(dst,0,2);
        System.out.println(new String(dst, 0, 2));

        System.out.println(buf.position());

        // mark(): 标记一下
        buf.mark();

        buf.get(dst, 2, 2);
        System.out.println(new String(dst,2,2));
        System.out.println(buf.position());

        // reset(): 重置
        buf.reset();
        System.out.println(buf.position());

        // 判断缓冲区中是否还有剩余数据
        if (buf.hasRemaining()) {
            // 获取缓冲区可以操作的数量
            System.out.println(buf.remaining());
        }
    }


    @Test
    public void test1()
    {
        String str = "Hello World";

       // 1. 分配一个指定大小的缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);
        System.out.println("---------------------allocate()--------------------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        // 2. 利用put() 存入数据
        buf.put(str.getBytes());
        System.out.println("---------------------put()--------------------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        //3. 切换读取数据的模式
        buf.flip();
        System.out.println("---------------------flip()--------------------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        //4. 利用get() 读取缓冲区的数据
        byte[] dst = new byte[buf.limit()];
        buf.get(dst);
        System.out.println("----------------------------------------------");
        System.out.println(new String(dst, 0, dst.length));
        System.out.println("---------------------get()--------------------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        //5. rewind：可重复读数据
        buf.rewind();
        System.out.println("---------------------rewind()--------------------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        // 6. clear：清空缓冲区，但是缓冲区中的数据依然存在，但是处于“被遗忘的状态”
        buf.clear();
        System.out.println("---------------------clear()--------------------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        // 7. 虽然buffer clear了，但是缓冲区中的数据依然存在，但是处于“被遗忘的状态”
        System.out.println("此处会显示 H ：" + (char) buf.get());  // 此处会显示H

        // 8. rewind vs flip 的区别
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put("ABCDEFG".getBytes());
        buffer.position(0);
        buffer.put("H".getBytes());
        // buffer.flip();
        buffer.rewind();
        System.out.println("----------------------rewind---------------------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());
//        执行flip()后，limit的值为1，position的值为0，所以只会输出“H”。
//        而执行rewind()后，limit的值为10，position的值为0，所以不仅会输出“HBCDEFG”7个字符，还会额外多输出3个空白字符，共计10个字节。

        // 所有，一定要保证limit值是对的前提下，才能使用rewind
//        1. flip会改变limit的值，一般会设置为当前的读写位置；
//        2. rewind不会改变limit的值，一般会设置为capacity的值；



    }

    @Test
    public void HelloWorld() {
        System.out.println("Fuck Tom Hall");

    }
}
