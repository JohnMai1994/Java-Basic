package com.timbuchalka;

import java.io.*;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws IOException {
//        File xanadu = new File("xanadu.txt");
//        FileInputStream in = null;
//        FileOutputStream out = null;
//
//        try {
//            in = new FileInputStream("xanadu.txt");
//            out = new FileOutputStream("outagain.txt");
//            int c;
//
//            while ((c = in.read()) != -1) {
//                out.write(c);
//            }
//        } finally {
//            if (in != null) {
//                in.close();
//            }
//            if (out != null) {
//                out.close();
//            }
//        }

//        File file = new File("abc.txt");
//        FileWriter fileWriter = null;
////        BufferedWriter bufferedWriter = null;
//
//        try{
//            fileWriter = new FileWriter(file);
////            bufferedWriter = new BufferedWriter(fileWriter);
//            String string = "你好世界，abcd";
//            fileWriter.write(string);
//
//
//        } catch (IOException e) {
//            throw e;
//        } finally {
////            if (bufferedWriter != null ){
////                bufferedWriter.close();
////            }
//            if (fileWriter != null) {
//                fileWriter.close();
//            }
//        }

//        DataOutputStream dos = null;
//        File f = new File("abc.txt");
//
//
//        dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(f)));
//        String[] names = {"衬衣", "手套", "围巾"};
//        int[] numbers = {3,4,2};
//        float[] prices = {3.42f,4.33f,32.2f};
//
//        for (int i = 0; i < names.length; i++) {
//            dos.writeChars(names[i]);
//            dos.writeChar('\t');
//            dos.writeFloat(prices[i]);
//            dos.writeChar('\t');
//            dos.writeInt(numbers[i]);
//
//        }
//        dos.close();
//
//        DataInputStream dis = null;
//        dis = new DataInputStream(new BufferedInputStream(new FileInputStream(f)));
//
//        FileInputStream a = new FileInputStream(f);
//
//        String name = null;
//        float price = 0.0f;
//        int number = 0;
//        char tem[] = null;
//        int len = 0;
//        char c = 0;
//        try {
//            while (true) {
//                tem = new char[200];
//                len=0;
//                while ((c= dis.readChar())!= '\t'){
//                    tem[len] = c;
//                    len++;
//                }
//                name = new String(tem,0,len);
//                price = dis.readFloat();
//                dis.readChar();
//                number = dis.readInt();
//
//                System.out.println("名称：" + name + " 价格：" + price + " 数量：" + number);
//
//            }
//        }catch (Exception e) {
//            dis.close();
//        }


//        String s = "0,YOU, ARE SITTING IN FRONT OF A COMPUTER LEARNING JAVA.";
//        Scanner scanner = new Scanner(s);
//        scanner.useDelimiter(",");
//        int loc = scanner.nextInt();
////        scanner.skip(scanner.delimiter());
//        String abc = scanner.next();
////        scanner.skip(scanner.delimiter());
//        String description = scanner.nextLine();
//        System.out.println(loc +   description);
//        System.out.println(abc);





    }
}
