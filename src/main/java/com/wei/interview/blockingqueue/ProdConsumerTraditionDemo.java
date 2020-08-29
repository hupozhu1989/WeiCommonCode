package com.wei.interview.blockingqueue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程通信之condition.await()、condition.signalAll()
 * @author weizhenchao
 * @version 1.0
 * @date：2020/3/29
 */
public class ProdConsumerTraditionDemo {
    /*
        多线程模板口诀:
        1.  线程  操作(方法)  资源类
        2.  判断  干活        通知
        3.  防止虚假唤醒机制
     */
    public static void main(String[] args) {
        //一个初始值为0的变量 两个线程交替操作 一个加1 一个减1来5轮
        ShareData shareData = new ShareData();
        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                try {
                    shareData.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "AA").start();
        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                try {
                    shareData.deIncrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "BB").start();
    }
}

/**
 * 共享资源类
 */
class ShareData{
    private int num = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() throws Exception{
        lock.lock();
        try {
            while (num != 0){//多线程判断用【while】
                //等待不生产
                condition.await();
            }
            //干活
            num++;
            System.out.println(Thread.currentThread().getName() + "\t加一" + "\t" + num);
            //通知唤醒
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void deIncrement() throws Exception {
        lock.lock();
        try {
            while (num == 0){//多线程判断用【while】
                //等待不生产
                condition.await();
            }
            //干活
            num--;
            System.out.println(Thread.currentThread().getName() + "\t减一" + "\t" + num);
            //通知唤醒
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

}
