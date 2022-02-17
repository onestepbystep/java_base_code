package io;

import java.io.*;

/**
 * @Author:hmh
 * @Date: 2022/2/14 10:46
 */
public class FileReaderWriterExam {

    public static void main(String[] args) {
        try {
            FileReader fileReader = new FileReader("G:\\tmp\\topn.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
//            bufferedReader.lines().forEach(System.out::println);

            //  true:追加到文件末尾，不会全部覆盖
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("G:\\tmp\\newTop.txt",true));
            bufferedReader.lines().forEach(line->{
                try {
                    bufferedWriter.write(line+"\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            bufferedWriter.close();
            bufferedReader.close();




        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
