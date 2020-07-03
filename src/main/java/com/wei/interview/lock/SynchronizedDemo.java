package com.wei.interview.lock;

/**
 * @author weizhenchao
 * @create 2020-04-02-上午 10:00
 */
public class SynchronizedDemo {
    public void method01() {
        synchronized (this) {
            System.out.println("synchronized 代码块");
        }
    }

    /*public synchronized void method02() {
        System.out.println("synchronized ⽅法");
    }*/
}
