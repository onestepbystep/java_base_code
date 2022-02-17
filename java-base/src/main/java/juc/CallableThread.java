package juc;

import java.util.concurrent.Callable;

/**
 * @Author:hmh
 * @Date: 2022/1/26 9:55
 */
public class CallableThread implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println("ThreadName:"+Thread.currentThread().getName()+" implement callable running");
        return "success";
    }
}
