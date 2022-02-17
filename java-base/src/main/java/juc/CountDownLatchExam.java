package juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author:hmh
 * @Date: 2022/2/9 14:42
 */
public class CountDownLatchExam {

    public static void main(String[] args) {

        /**
         * 最后执行打印程序，不会等待线程执行玩，执行无序
         */
      /*  ExecutorService es1 = Executors.newSingleThreadExecutor();
        System.out.println("===执行主线程====");
        es1.execute(new RunableThread());
        es1.shutdown();
        ExecutorService es2 = Executors.newSingleThreadExecutor();
        es2.execute(new RunableThread());
        es2.shutdown();

        System.out.println("====等待其他线程执行完成，继续执行主线程=====");*/

        /**
         * 利用CountDownLatch等待其他线程执行完后，
         * 再继续执行主线程
         */

/*
        CountDownLatch countDownLatch = new CountDownLatch(2);
        ExecutorService es3 = Executors.newSingleThreadExecutor();
        es3.execute(new Runnable() {
            @Override
            public void run() {
                try{
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println("执行子线程2");
                }catch(InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    //  自减1
                    countDownLatch.countDown();
                }
            }
        });
        es3.shutdown();

        ExecutorService es4 = Executors.newSingleThreadExecutor();
        es4.execute(new Runnable() {
            @Override
            public void run() {
                try{
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println("执行子线程1");
                }catch(InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    //  自减1
                    countDownLatch.countDown();
                }
            }
        });
        es4.shutdown();

        try {
            System.out.println("=====等待两个线程执行======");
            //  这代代码，只要countDownLatch不等于0 就会一直等待，不会执行后面程序
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("=====子线程执行完，继续执行主线程======");*/


        //  第三种方式 自定义类
        ExecutorService executorService = Executors.newCachedThreadPool();
        int n =10;
        CountDownLatch downLatch = new CountDownLatch(n);
        for (int i =0;i< n; i++ ) {
            executorService.execute(new CountDownRunable(downLatch));
        }

        executorService.shutdown();
        System.out.println("======等待自定义子线程执行完====");
        try {
            // 等待所有线程执行完，在执行后续代码
            downLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("======自定义子线程执行完,继续执行主线程打印任务====");
    }
}
