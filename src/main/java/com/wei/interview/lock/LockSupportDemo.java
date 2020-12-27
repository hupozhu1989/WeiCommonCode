package com.wei.interview.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author weizhenchao
 * @version 1.0
 * @date：2020/12/27
 */
public class LockSupportDemo {
    static Object objectLock = new Object();
    static Lock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();

    public static void main(String[] args) {
        //synchronizedWaitNotify();

        //lockAwaitSignal();

        lockSupportParkUnpark();
    }

    /**
     * LockSupport===park、unpark
     */
    public static void lockSupportParkUnpark() {
        Thread aa = new Thread(() -> {
            /*try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            System.out.println(Thread.currentThread().getName() +"\t"+ "---come in" +"\t"+ System.currentTimeMillis());
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() +"\t"+ "---come out" +"\t"+ System.currentTimeMillis());
        }, "AA");
        aa.start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread bb = new Thread(() -> {
            LockSupport.unpark(aa);
            System.out.println(Thread.currentThread().getName() + "\t" + "---通知");
        }, "BB");
        bb.start();
    }

    /**
     * lock===await、signal局限性
     * 线程先要获得并持有锁,必须在锁块(synchronized或lock)中
     * 必须要先等待后唤醒,线程才能够被唤醒
     */
    public static void lockAwaitSignal() {
        new Thread(()->{
            /*try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName()+"\t"+"---come in"+"\t"+ System.currentTimeMillis());
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"\t"+"---come out"+"\t"+ System.currentTimeMillis());
            } finally {
                lock.unlock();
            }
        },"AA").start();

        new Thread(()->{
            lock.lock();
            try {
                condition.signal();
                System.out.println(Thread.currentThread().getName()+"\t"+"---通知");
            } finally {
                lock.unlock();
            }
        },"BB").start();
    }

    /**
     * synchronized===wait、notify局限性
     * ①wait和notify方法必须要在同步块或者方法里面且成对出现使用
     * ②先wait后notify才OK
     */
    public static void synchronizedWaitNotify() {
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (objectLock){
                System.out.println(Thread.currentThread().getName()+"\t"+"---come in"+"\t"+ System.currentTimeMillis());
                try {
                    objectLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"\t"+"---come out"+"\t"+ System.currentTimeMillis());
            }
        },"AA").start();

        new Thread(()->{
            synchronized (objectLock){
                objectLock.notify();
                System.out.println(Thread.currentThread().getName()+"\t"+"---通知");
            }
        },"BB").start();
    }
}
