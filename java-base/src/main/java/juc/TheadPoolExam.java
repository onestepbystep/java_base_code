package juc;

import java.util.concurrent.*;

/**
 * @Author:hmh
 * @Date: 2022/1/26 9:29
 */
public class TheadPoolExam {


    /**
     * 线程池
     */
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(10);



        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10,
                20,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque(10)
                );


        threadPoolExecutor.execute(new ThreadExdThread());
        for (int i =0;i<20;i++) {
            executorService.execute(new RunableThread());

        }


        FutureTask<String> stringFutureTask = new FutureTask<>(new CallableThread());
        stringFutureTask.run();
        try {
            System.out.println("task return:"+stringFutureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        executorService.shutdown();
        threadPoolExecutor.shutdown();





    }
}
