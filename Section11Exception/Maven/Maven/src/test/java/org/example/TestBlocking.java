package org.example;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class TestBlocking {

    @Test
    // 客户端
    public void client() throws IOException {
        // 1. 获取通道
        SocketChannel socketChannel =  SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));

        FileChannel inChannel = FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);

        // 2. 分配指定大小的缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);

        // 3. 读取本地文件，并发送到服务端去
        while (inChannel.read(buf) != -1) {
            buf.flip();
            socketChannel.write(buf);
            buf.clear();
        }
        socketChannel.shutdownOutput();

        // !!!!!!!接收服务器端的反馈!!!!!!!
        int len = 0;
        while ((len = socketChannel.read(buf)) != -1) {
            buf.flip();
            System.out.println(new String(buf.array(), 0, len ));
            buf.clear();
        }

        // 4. 关闭通道
        inChannel.close();
        socketChannel.close();

    }


    @Test
    // 服务端
    public void server() throws IOException {
//        1. 获取通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        FileChannel outChannel = FileChannel.open(Paths.get("4.jpg"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);

        // 2. 绑定连接端口号
        serverSocketChannel.bind(new InetSocketAddress(9898));


        // 3. 获取客户端连接的通道
        SocketChannel socketChannel =  serverSocketChannel.accept();

        // 4. 分配一共指定大小的缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);

        // 5. 接收客户端的数据，并保存到本地
        while (socketChannel.read(buf) != -1) {
            buf.flip();
            outChannel.write(buf);
            buf.clear();
        }

        // 发送反馈到客户端
        buf.put("服务端接收数据成功".getBytes());
        buf.flip();
        socketChannel.write(buf);




        // 6. 关闭通道
        serverSocketChannel.close();
        outChannel.close();
        socketChannel.close();




    }

}
