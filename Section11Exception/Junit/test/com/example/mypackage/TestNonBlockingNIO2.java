package com.example.mypackage;

import org.junit.Test;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Date;
import java.util.Iterator;

public class TestNonBlockingNIO2 {
    @Test
    public void send() throws IOException {
        // 1. 创建datagramChannel
        DatagramChannel datagramChannel = DatagramChannel.open();


        // 2. 切换成非阻塞模式
        datagramChannel.configureBlocking(false);

        // 3. 创建缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        // 4. 传输数据
        String data = "Hello World";

        buffer.put((new Date().toString() + "\n" + data).getBytes());
        buffer.flip();

        // 5. datagramChannel 发送
        datagramChannel.send(buffer, new InetSocketAddress("127.0.0.1", 9898));
        buffer.clear();

        datagramChannel.close();


    }

    @Test
    public void  receive() throws IOException {
        DatagramChannel datagramChannel = DatagramChannel.open();

        datagramChannel.configureBlocking(false);
        // ！！！ 用于监听 9898 端口 bind
        datagramChannel.bind(new InetSocketAddress(9898));

        Selector selector = Selector.open();

        datagramChannel.register(selector, SelectionKey.OP_READ);

        while (selector.select(100000) > 0) {
            Iterator<SelectionKey> it =  selector.selectedKeys().iterator();

            while (it.hasNext()) {
                SelectionKey sk = it.next();

                if (sk.isReadable()) {
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    datagramChannel.receive(buffer);
                    buffer.flip();
                    System.out.println(new String(buffer.array(), 0, buffer.limit()));
                    buffer.clear();

                }
            }
        }
        selector.close();



    }

}
