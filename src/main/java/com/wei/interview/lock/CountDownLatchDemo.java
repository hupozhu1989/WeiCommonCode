package com.wei.interview.lock;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch-火箭发射
 * @author weizhenchao
 * @version 1.0
 * @date：2020/3/29
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws Exception{
        //closeDoor();
        mieLiuGuo();
        /*
            让一些线程阻塞直到另外一些完成后才被唤醒
            CountDownLatch主要有两个方法,当一个或多个线程调用await方法时,调用线程会被阻塞
            其他线程调用countDown方法计数器减1(调用countDown方法时线程不会阻塞),当计数器的值变为0,因调用await方法被阻塞的线程会被唤醒,继续执行
         */
    }

    public static void mieLiuGuo() throws InterruptedException {
        String[] sixCountries = {"韩","赵","魏","楚","燕","齐"};
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t国 被灭");
                countDownLatch.countDown();
            },sixCountries[i-1]).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"\t**********秦 一统天下");
    }

    public static void closeDoor() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t上完自习,离开教室");
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"\t**********班长最后关门走人");
    }

}
