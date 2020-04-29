package com.wei.interview.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @author weizhenchao
 * @version 1.0
 * @date：2020/3/30
 */
public class CallableDemo {
    public static void main(String[] args) throws Exception{
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread());
        //FutureTask<Integer> futureTask2 = new FutureTask<>(new MyThread());
        new Thread(futureTask,"AA").start();
        //new Thread(futureTask2,"BB").start();
        int result01 = 100;
        System.out.println(Thread.currentThread().getName()+"\t");

        while (!futureTask.isDone()){
            TimeUnit.SECONDS.sleep(1L);
            System.out.println("~~~等待~~~");
        }
        int result02 = futureTask.get();//一般get()方法放在最后面
        System.out.println("******result:"+(result01+result02));
    }


}

class MyThread implements Callable<Integer>{
    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName()+"\tcome in");
        TimeUnit.SECONDS.sleep(3L);
        return 1024;
    }
}
