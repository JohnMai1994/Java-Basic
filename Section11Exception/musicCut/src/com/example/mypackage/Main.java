package com.example.mypackage;

import java.io.*;

public class Main {

    public static void main(String[] args) {

        // Create Buffered Input/Output Stream
        BufferedOutputStream bufferedOutputStream = null;
        BufferedInputStream music1InputStream = null;
        BufferedInputStream music2InputStream = null;
        // Create Music File
        File music1 = new File("music1.mp3");
        File music2 = new File("music2.mp3");
        File[] musics = {music1, music2};

        byte[]  by1= new byte[1024 * 300];
        byte[]  by2 = new byte[1024 * 300];

        try{
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("twoMusic.mp3"));
            // Store Music 1 2 into bufferStream
            music1InputStream = new BufferedInputStream(new FileInputStream(musics[0]));
            music2InputStream = new BufferedInputStream(new FileInputStream(musics[1]));
            // Skip Part of the Music
            music1InputStream.skip(1024 *1024*3);

            int count = 0;
            while ((music1InputStream.read(by1) != -1)&&(music2InputStream.read(by2) != -1)) {
                bufferedOutputStream.write(by1);
                bufferedOutputStream.write(by2);
                count++;
                if (count >= (20)) {
                    break;
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (music1InputStream != null) {
                    music1InputStream.close();
                }
                if (music2InputStream != null) {
                    music2InputStream.close();
                }
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


        }


    }
}
