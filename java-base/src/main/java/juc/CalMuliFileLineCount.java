package juc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 *
 *  统计多个文件的总长度
 * @Author:hmh
 * @Date: 2022/2/8 18:50
 */
public class CalMuliFileLineCount {

    public static void main(String[] args) {


        //  frist code method
        FutureTask<Long> longFutureTask1 = new FutureTask<>(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                FileReader fileReader = new FileReader("G:\\tmp\\topn.txt");
                Thread.sleep(10000);
                return new BufferedReader(fileReader).lines().count();
            }
        });

        FutureTask<Long> longFutureTask2 = new FutureTask<>(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                FileReader fileReader = new FileReader("G:\\tmp\\word.txt");
                return new BufferedReader(fileReader).lines().count();
            }
        });


        FutureTask<Long> longFutureTask3 = new FutureTask<>(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                FileReader fileReader = new FileReader("G:\\tmp\\vistor.txt");
                return new BufferedReader(fileReader).lines().count();
            }
        });

        longFutureTask1.run();
        longFutureTask2.run();
        longFutureTask3.run();


        Long total = 0L;
        try {
           total = longFutureTask1.get()+longFutureTask2.get()+longFutureTask3.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }finally {
            System.out.println("total size:"+total);
        }


    }
}
