package juc;

import java.util.logging.Logger;

/**
 * @Author:hmh
 * @Date: 2022/1/26 9:43
 */
public class ThreadExdThread extends Thread{


    @Override
    public void run() {
        System.out.println("ThreadName:"+currentThread().getName()+" is extend thread runing ");
    }
}
