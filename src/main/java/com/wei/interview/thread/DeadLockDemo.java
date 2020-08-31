package com.wei.interview.thread;

import java.util.concurrent.TimeUnit;

/**
 * 死锁
 * @author weizhenchao
 * @create 2020-04-01-下午 2:39
 */
public class DeadLockDemo {
    public static void main(String[] args) {
        //死锁是指两个或者以上的线程在执行过程中,因争夺资源而造成的一种相互等待的现象,若无外力干涉那他们都将无法推进下去
        String lockA = "lockA";
        String lockB = "lockB";
        new Thread(new HoldThread(lockA, lockB), "threadAAA").start();
        new Thread(new HoldThread(lockB, lockA), "threadBBB").start();

        /*
            Linux       ps -ef|grep xxxx        ls -l
            windows下的java运行程序,也有类似ps的查看进程的命令,jps = java ps
                jps -l
                jstack 6164

         */
    }
}

class HoldThread implements Runnable{
    private String lockA;
    private String lockB;

    public HoldThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA) {
            System.out.println(Thread.currentThread().getName() + "\t 自己持有锁" + lockA + "尝试获得" + lockB);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName() + "\t 自己持有锁" + lockB + "尝试获得" + lockA);
            }
        }
    }
}
