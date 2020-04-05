package com.wei.interview.thread;

import java.util.concurrent.*;

/**
 * @author weizhenchao
 * @create 2020-04-01-下午 1:36
 */
public class MyThreadPoolDemo2 {
    public static void main(String[] args) {
        //自定义线程池
        method01();

        //Executors生成线程池
        //method02();
    }

    private static void method02() {
        //一池5个处理线程
        //ExecutorService threadPool= Executors.newFixedThreadPool(5);
        //一池一线程
        //ExecutorService threadPool= Executors.newSingleThreadExecutor();
        //一池N线程
        ExecutorService threadPool = Executors.newCachedThreadPool();

        //模拟10个用户来办理业务 没有用户就是来自外部的请求线程
        try {
            for (int i = 1; i <= 20; i++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName() + "\t办理业务");
                });
                try {
                    TimeUnit.MICROSECONDS.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            threadPool.shutdown();
        }
    }

    private static void method01() {
        ExecutorService threadPool = new ThreadPoolExecutor(
            2,
            5,
            1L,
            TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(3),
            Executors.defaultThreadFactory(),
            //直接抛出RejectedException异常阻止系统正常运行
            //new ThreadPoolExecutor.AbortPolicy()//默认
            //"调用者运行"一种调节机制,该策略既不会抛弃任务,也不会抛出异常,而是将某些任务回退到调用者,从而降低新任务的流量
            //new ThreadPoolExecutor.CallerRunsPolicy()
            //抛弃队列中等待最久的任务,然后把当前任务加入队列中尝试再次提交
            //new ThreadPoolExecutor.DiscardOldestPolicy()
            //直接丢弃任务,不予任何处理也不抛出异常.如果允许任务丢失,这是最好的拒绝策略
            new ThreadPoolExecutor.DiscardPolicy()
        );
        //模拟10个用户来办理业务 没有用户就是来自外部的请求线程
        try {
            for (int i = 1; i <= 10; i++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName() + "\t办理业务");
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            threadPool.shutdown();
        }
    }
}
