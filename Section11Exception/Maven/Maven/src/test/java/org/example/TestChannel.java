package org.example;

import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.Set;

public class TestChannel {

    @Test
    public void test6() throws CharacterCodingException {
            Charset cs1 = Charset.forName("GBK");

            // 获取编码器
            CharsetEncoder charsetEncoder= cs1.newEncoder();

            // 获取解码器
            CharsetDecoder charsetDecoder= cs1.newDecoder();

        CharBuffer charBuffer = CharBuffer.allocate(1024);
        charBuffer.put("Hello 麦加东");
        charBuffer.flip();

        // 编码
        ByteBuffer byteBuffer = charsetEncoder.encode(charBuffer);


        for (int i = 0; i < 12;i++) {
            System.out.println(byteBuffer.get());
        }

        // 解码
        byteBuffer.flip();
        CharBuffer charBuffer2 = charsetDecoder.decode(byteBuffer);
        System.out.println(charBuffer2.toString());

        System.out.println("--------------------------------------------------");
        byteBuffer.flip();
         Charset cs2 = Charset.forName("UTF-8"); // 很明显会乱码，因为用GDK编码，但是用UTF-8解码
         CharBuffer charBuffer3 = cs2.decode(byteBuffer);
        System.out.println(charBuffer3.toString());


    }


    @Test
    public void test5() {
        Map<String , Charset> map =  Charset.availableCharsets();

        Set<Map.Entry<String, Charset>> set = map.entrySet();

        for (Map.Entry<String, Charset> entry : set) {
            System.out.println(entry.getKey() + " = " + entry.getValue() );
        }



    }


    @Test
    public void test4() {
        // 建立读取/写入文件方法 && Channel
        RandomAccessFile raf1 = null;
        RandomAccessFile raf2 = null;
        FileChannel channel1 = null;
        FileChannel channel2 = null;
        try {
            raf1 = new RandomAccessFile("1.txt","rw");
            // 1. 获取通道
             channel1 = raf1.getChannel();
            // 2. 分配知道大小的缓冲区
            ByteBuffer buf1 = ByteBuffer.allocate(100);
            ByteBuffer buf2 = ByteBuffer.allocate(1024);

            // 3. 分散读取
            ByteBuffer[] bufs = {buf1, buf2};
            channel1.read(bufs);

            for (ByteBuffer byteBuffer : bufs) {
                byteBuffer.flip();
            }

            System.out.println(new String(bufs[0].array(), 0, bufs[0].limit()));
            System.out.println("--------------------");
            System.out.println(new String(bufs[1].array(), 0, bufs[1].limit()));

            // 4. 具体写入
            raf2 = new RandomAccessFile("2.txt", "rw");
            channel2 = raf2.getChannel();
            // 聚合写入
            channel2.write(bufs);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {

            try {
                if (raf1!= null) {
                    raf1.close();
                }
                if (raf2!= null) {
                    raf2.close();
                }
                if (channel1!= null) {
                    channel1.close();
                }
                if (channel2!= null) {
                    channel1.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }


    @Test
    public void test3() {
        try {
            FileChannel inChannel = FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);
            FileChannel outChannel = FileChannel.open(Paths.get("4.jpg"), StandardOpenOption.WRITE, StandardOpenOption.CREATE, StandardOpenOption.READ);

            inChannel.transferTo(0, inChannel.size(), outChannel);
            outChannel.transferFrom(inChannel, 0, inChannel.size());

            inChannel.close();
            outChannel.close();


        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    @Test
    public void test2() {
        long star = System.currentTimeMillis();

        // 2. 使用直接缓冲区完成文件的复制（内存映射）
        try {
            FileChannel inChannel = FileChannel.open(Paths.get("F:/照片/02.mpeg"), StandardOpenOption.READ);
            FileChannel outChannel = FileChannel.open(Paths.get("F:/照片/test2.mpeg"), StandardOpenOption.WRITE, StandardOpenOption.CREATE, StandardOpenOption.READ);

            // 内存映射文件 （这个和allocate一样）
            MappedByteBuffer inMappedBuf =  inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
            MappedByteBuffer outMappedBuf =  outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());

            // 直接对缓冲区进行数据的读写操作
            byte[] dst = new byte[inMappedBuf.limit()];
            inMappedBuf.get(dst);
            outMappedBuf.put(dst);

            inChannel.close();
            outChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println("Test2耗费时间： " + (end - star));


    }


    @Test

    public void test1() {
        long star = System.currentTimeMillis();
        // 1. 利用通道完成文件的复制（非直接缓冲区）
        // 建立4个空的Stream Channel
        FileInputStream fis = null;
        FileOutputStream fos = null;
        FileChannel inChannel = null;
        FileChannel outChannel = null;

        try {
            fis = new FileInputStream("F:/照片/02.mpeg");
            fos = new FileOutputStream("F:/照片/test1.mpeg");

            // 1. 获取通道
             inChannel = fis.getChannel();
             outChannel = fos.getChannel();

            // 2. 分配指定大小的缓冲区
            ByteBuffer buf = ByteBuffer.allocate(1024);

            // 3. 将通道中的数据存到缓冲区中
            while (inChannel.read(buf)!= -1) {
                buf.flip();  // 切换成读取数据的模式，把position，limit状态改变
                // 4. 将缓冲区中的数据写入通道
                outChannel.write(buf);
                buf.clear(); // 清空缓冲区
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outChannel != null) {
                try {
                    outChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (inChannel != null) {
                try {
                    inChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        long end = System.currentTimeMillis();
        System.out.println("Test1耗费时间： " + (end - star));
    }

}
