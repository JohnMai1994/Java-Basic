package com.example.mypackage;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

public class TestNonBlockingServer {



    // 客户端
    @Test
    public void client() throws IOException {
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

    // 服务端
    @Test
    public void server() throws IOException {

        // 1. 获取通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        // 2. 切换非阻塞模式
        serverSocketChannel.configureBlocking(false);

        // 3. 绑定连接
        serverSocketChannel.bind(new InetSocketAddress(9898));

        // 4. 获取选择器
        Selector selector = Selector.open();

        // 5. 将通道注册到选择器上面, 并且指定”监听事件“
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        // 6. 轮询式的获取选择器上已经“准备就绪”的事件
        while (selector.select() > 0) {
            // 7. 获取当前选择器中所有注册的“选择键（已就绪的监听状态）”
            Iterator<SelectionKey> it =  selector.selectedKeys().iterator();

            while (it.hasNext()) {
                // 8. 获取准备“就绪”的事件
                SelectionKey sk = it.next();   // ！！！！用完之后需要取消掉

                // 9. 判断具体是什么事件准备就绪
                // 如果是 接收 .isAcceptable
                //        写入 .isWritable()
                //        读入 .isReadable()
                //        连接 .isConnectable()
                if (sk.isAcceptable()) {
                    // 10. !!!“接收就绪”!!!
                    //     获取客户端连接
                    SocketChannel socketChannel = serverSocketChannel.accept();

                    // 11. 切换非阻塞模式
                    socketChannel.configureBlocking(false);  // !!! 很重要

                    // 12. 将该通道注册到选择器上面
                    socketChannel.register(selector, SelectionKey.OP_READ);
                } else if (sk.isReadable()) {
                    // 13 !!!"读就绪状态"!!!
                    //     获取选择器状态的通道
                     SocketChannel socketChannel = (SocketChannel) sk.channel();

                     // 14. 读取数据
                    ByteBuffer buf = ByteBuffer.allocate(1024);

                    int len = 0;
                    while ((len = socketChannel.read(buf)) >0) {  // 大于0说明有数据
                        buf.flip();
                        System.out.println(new String(buf.array(), 0, len));
                        buf.clear();
                    }
                }
                // 15. 取消选择健
                it.remove(); // !!! 很重要 !!!


            }
        }


    }



}
