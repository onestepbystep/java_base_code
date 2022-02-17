package juc;

import java.util.concurrent.TimeUnit;

/**
 * @Author:hmh
 * @Date: 2022/1/26 9:46
 */
public class RunableThread implements Runnable{
    @Override
    public void run() {

        System.out.println("ThreadName:"+Thread.currentThread().getName()+" implement runnable runing");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
