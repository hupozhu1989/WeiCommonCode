package com.wei.interview.lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier-人到齐了再开会
 * @author weizhenchao
 * @version 1.0
 * @date：2020/3/29
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,()->{System.out.println("********召唤神龙");});
        for (int i = 1; i <= 7; i++) {
            final int temp = i;
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t收集到第【"+temp+"】颗龙珠");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
        /*
            CyclicBarrier(循环栅栏)：CyclicBarrier 和 CountDownLatch⾮常类似，它也可以实现线程间的技术等待，但是它的功能⽐CountDownLatch更加复杂和强⼤。
            主要应⽤场景和CountDownLatch类似。CyclicBarrier 的字⾯意思是可循环使⽤（Cyclic）的屏障（Barrier）。它要做的事情是，让⼀组线程到达⼀个屏障
            （也可以叫同步点）时被阻塞，直到最后⼀个线程到达屏障时，屏障才会开门，所有被屏障拦截的线程才会继续⼲活。

            CyclicBarrier默认的构造⽅法是 CyclicBarrier(int parties)，其参数表示屏障拦截的线程数量，每个线程调⽤await()⽅法告诉 CyclicBarrier 我已经到达了屏障，然后当前线程被阻塞。
            CyclicBarrier的字面意思是可循环(Cyclic)使用的屏障(barrier).它要做的事情是,让一组线程到达一个屏障(也可以叫做同步点)时被阻塞,
         */
    }
}
