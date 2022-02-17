package juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Author:hmh
 * @Date: 2022/2/9 15:44
 */
public class CountDownRunable implements Runnable{
    private final CountDownLatch countDownLatch;

    public CountDownRunable(CountDownLatch countDownLatch){
        this.countDownLatch = countDownLatch;
    }


    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("子线程打印信息 "+System.currentTimeMillis());
        System.out.println("当前count值："+countDownLatch.getCount());
        countDownLatch.countDown();

    }
}
