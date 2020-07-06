package com.wei.interview.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * 可重入锁(也叫做递归锁)
 * 在同一线程外层方法获取锁的时候,在进入内层方法会自动获取锁
 * 线程可以进入任何一个它已经标记的锁所同步的代码块
 *
 * @author veliger@163.com
 * @date 2019-04-12 23:36
 **/
public class ReenterLockDemo2 {
    public static void main(String[] args) {
        Phone2 phone2 = new Phone2();
        Thread t3 = new Thread(phone2,"t3");
        Thread t4 = new Thread(phone2,"t4");
        t3.start();
        t4.start();
    }
}

class Phone2 implements Runnable {
    private Lock lock = new ReentrantLock();

    @Override
    public void run() {
        get();
    }

    private void get() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\tget");
            set();
        } finally {
            lock.unlock();
        }
    }

    private void set() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\tset");
        } finally {
            lock.unlock();
        }
    }
}
 
 