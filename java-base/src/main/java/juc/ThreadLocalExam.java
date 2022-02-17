package juc;

import java.util.function.Supplier;

/**
 * @Author:hmh
 * @Date: 2022/2/11 15:19
 */
public class ThreadLocalExam {

    public static void main(String[] args) {

        ThreadLocal<Integer> integerThreadLocal = new ThreadLocal<Integer>(){
            @Override
            protected Integer initialValue() {
                System.out.println("初始化的线程ID："+Thread.currentThread().getId());
                return 10;
            }
        };
        System.out.println("当前线程id："+Thread.currentThread().getId()+" threadlocal初始值:"+integerThreadLocal.get());
        integerThreadLocal.set(100);
        System.out.println("当前线程id："+Thread.currentThread().getId()+" threadlocal更新为:"+integerThreadLocal.get());
        //  新的线程，利用integerThreadLocal.get() ，ThreadLocal会新建一个新的副本，不会用公共的变量
        new Thread(()->{
            System.out.println("新的线程："+Thread.currentThread().getId()+" threadlocal初始值："+integerThreadLocal.get());
        }).start();

        

    }
}
