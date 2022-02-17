package juc;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * 递增直接利用AtomicLong 中的getAndIncrement 实际利用CAS compare and set
 * 线程1 2 3 当线程A执行递增事，数据变成1，线程二此时拿到还是之前的0，cas判断工作内容的值，不等于主内容值，
 * 则重新获取，再次执行CAS操作
 *
 * @Author:hmh
 * @Date: 2022/2/10 12:14
 */
public class AtomicIntegerTest {

    private static int i =0;
    private static ExecutorService pool = Executors.newCachedThreadPool();

    private static Object[] objects = Arrays.asList(1, 2, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2).toArray();
    private static CountDownLatch downLatch = new CountDownLatch(objects.length);
    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        //  增加synchronized 方法，串行执行增加变量
        for(Object o:objects) {
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    addInt();
                }
            });
        }
        pool.shutdown();
        System.out.println("===========等待累加完成================");
        try {
            downLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();

        System.out.println("synchronized 累加结果为："+ i+" 花费时间："+(end - start) );

        AtomicInteger atomicInteger = new AtomicInteger(0);

        Arrays.stream(objects)
                .parallel()
                .forEach( x -> atomicInteger.getAndIncrement());

        System.out.println("atomicInteger 累加结果为："+atomicInteger.get()+" 花费时间："+(end - System.currentTimeMillis()) );


    }

    public  synchronized static void  addInt(){
        i++;
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        downLatch.countDown();
    }
}
