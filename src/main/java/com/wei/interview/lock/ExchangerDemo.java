package com.wei.interview.lock;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

public class ExchangerDemo {

    public static void main(String[] args) {
        /*
            两个线程到达同步点后,相互交换数据
         */
        Exchanger<Object> exchanger = new Exchanger<>();
        new Thread(() -> {
            Object object = new Object();
            System.out.println(Thread.currentThread().getName() + "创建的对象是" + object);
            try {
                object = exchanger.exchange(object);
                System.out.println(Thread.currentThread().getName() + "交换后得到的对象是" + object);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "线程1").start();

        new Thread(() -> {
            Object object = new Object();
            System.out.println(Thread.currentThread().getName() + "创建的对象是" + object);
            try {
                TimeUnit.SECONDS.sleep(2);
                object = exchanger.exchange(object);
                System.out.println(Thread.currentThread().getName() + "交换后得到的对象是" + object);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "线程2").start();
    }

}