package com.example.mypackage;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Scanner;

public class TestNonBlockingClient {

    public static void main(String[] args) throws IOException {
        // 1. 获取通道
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));

        // 2. 切换成非阻塞模式
        socketChannel.configureBlocking(false);  // !!!很重要

        // 3. 分配指定大小的缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);

        // 4. 发送数据给服务端
//        buf.put(new Date().toString().getBytes());
//        buf.flip();
//        socketChannel.write(buf);
//        buf.clear();
//        socketChannel.close();

        // 4.1 用scanner发送数据到服务端
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入内容：");

        while (scanner.hasNext()) {
            String str = scanner.next();
            buf.put((new Date().toString() + "\n" + str).getBytes());
            buf.flip();
            socketChannel.write(buf);
            buf.clear();
        }

        // 5. 关闭通道
        socketChannel.close();
    }
}
