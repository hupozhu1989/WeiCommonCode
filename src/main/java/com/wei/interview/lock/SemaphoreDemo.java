package com.wei.interview.lock;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 信号量
 * @author weizhenchao
 * @version 1.0
 * @date：2020/3/29
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        /*
            信号量的主要用户两个目的,一个是用于多个共享资源的相互排斥使用,另一个用于并发资源数的控制,限制线程并发数量,如果不限制线程并发的数量,
            则CPU资源很快被耗尽
         */
        Semaphore semaphore = new Semaphore(3);//模拟3个停车位
        for (int i = 1; i <= 6; i++) {//模拟6部汽车
            new Thread(()->{
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"\t抢到停车位");
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName()+"\t停车3秒后离开车位");
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }

    }

}
