package com.wei.interview.blockingqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author weizhenchao
 * @version 1.0
 * @date：2020/3/29
 */
public class SynchronousQueueDemo {
    public static void main(String[] args) {
        /*
            SynchronousQueue没有容量
            与其他BlockingQueue不同,SynchronousQueue是一个不存储元素的BlockingQueue
            每个put操作必须要等待一个take操作,否则不能继续添加元素,反之亦然.
         */
        BlockingQueue<String> blockingQueue = new SynchronousQueue<>();
        new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName()+"\t put 1");
                blockingQueue.put("1");
                System.out.println(Thread.currentThread().getName()+"\t put 2");
                blockingQueue.put("2");
                System.out.println(Thread.currentThread().getName()+"\t put 3");
                blockingQueue.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t1").start();

        new Thread(()->{
            try {
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"\t take "+blockingQueue.take());
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"\t take "+blockingQueue.take());
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"\t take "+blockingQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t2").start();

    }
}
