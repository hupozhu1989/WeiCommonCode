package com.wei.interview.thread.pool.b;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import static com.wei.interview.thread.pool.b.ThreadPoolConstants.*;

public class ThreadPoolExecutorDemo {

    public static void main(String[] args) {
        //使用阿里巴巴推荐的创建线程池的方式
        //通过ThreadPoolExecutor构造函数自定义参数创建
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
            CORE_POOL_SIZE,//5
            MAX_POOL_SIZE,//10
            KEEP_ALIVE_TIME,//1L
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(QUEUE_CAPACITY),//100
            new ThreadPoolExecutor.CallerRunsPolicy()
        );

        for (int i = 0; i < 10; i++) {
            //创建WorkerThread对象（WorkerThread类实现了Runnable 接口）
            Runnable worker = new MyRunnable("" + i);
            //执行Runnable
            executor.execute(worker);
        }
        //终止线程池
        /*
            当线程池调用该方法时,线程池的状态则立刻变成SHUTDOWN状态。此时，则不能再往线程池中添加任何任务，否则将会抛出
            RejectedExecutionException异常。但是，此时线程池不会立刻退出，直到添加到线程池中的任务都已经处理完成，才会退出。
         */
        executor.shutdown();
        /*
            执行该方法，线程池的状态立刻变成STOP状态，并试图停止所有正在执行的线程，不再处理还在池队列中等待的任务，当然，它会返回那些未执行的任务。
            它试图终止线程的方法是通过调用Thread.interrupt()方法来实现的，这种方法的作用有限，如果线程中没有sleep 、wait、Condition、定时锁等应用,
            interrupt()方法是无法中断当前的线程的。所以，ShutdownNow()并不代表线程池就一定立即就能退出，它可能必须要等待所有正在执行的任务都执行完成了才能退出。
         */
        //executor.shutdownNow();
        while (!executor.isTerminated()) {
            try {
                TimeUnit.SECONDS.sleep(1L);
                System.err.println("~~~我还活着~~~");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Finished all threads");
    }
}
