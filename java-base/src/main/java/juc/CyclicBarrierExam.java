package juc;

import java.security.SecureRandom;
import java.util.concurrent.*;

/**
 *
 *
 * 等待所有线程
 * @Author:hmh
 * @Date: 2022/2/11 9:53
 */
public class CyclicBarrierExam {

    private ExecutorService executorService;
    private int parties;
    private CyclicBarrier cyclicBarrier;


    public CyclicBarrierExam() {
        parties = 10;
        cyclicBarrier = new CyclicBarrier(parties,()-> System.out.println(Thread.currentThread().getName()+" getCyclicBarrierCommand done"));
        executorService =  Executors.newFixedThreadPool(parties);
    }


    public static void main(String[] args) {
        CyclicBarrierExam cyclicBarrierExam = new CyclicBarrierExam();
        cyclicBarrierExam.doSomething();
        System.out.println("主线程打印程序");

    }


    public  void doSomething(){

        for(int i =0;i<parties;i++){
            executorService.submit(
                    ()->{
                        try {
                            TimeUnit.SECONDS.sleep(new SecureRandom().nextInt(3));
                            // 设置屏障处，必须等待所有线程到达这个地方，才会执行下一步打印
                            cyclicBarrier.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (BrokenBarrierException e) {
                            e.printStackTrace();
                        }
                        //  所有线程到达这里，才会一起打印
                        //  最开始打印初始化的CyclicBarrier中的runable方法
                        System.out.println(Thread.currentThread().getName()+" 等待所有线程一起执行完");
                    }

            );
        }
        executorService.shutdown();
    }

}
