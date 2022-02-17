package io;

import java.io.*;

/**
 * @Author:hmh
 * @Date: 2022/2/14 14:47
 */
public class FileRWStreamExam {

    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("G:\\tmp\\word.txt");
//        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);

        int read = bufferedInputStream.read();



        FileOutputStream fileOutputStream = new FileOutputStream("G:\\tmp\\newword.txt");
//        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        while( read!=-1 ){
            char ch = (char)read;
            System.out.println(ch);
            bufferedOutputStream.write(read);
            read = bufferedInputStream.read();
        }

        bufferedOutputStream.close();
        bufferedInputStream.close();
    }
}
