package com.wei.common.keyword.kw_volatile;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author weizhenchao
 * @version 1.0
 * @date：2020/3/25
 */
public class VolatileDemo {
    /*
        JMM: 可见性 原子性 有序性
     */
    public static void main(String[] args) {//main是一切运行方法的入口
        //volatile的可见性
        //seeOkByVolatile();

        //volatile不保证原子性
        MyData myData = new MyData();
        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                for (int j = 1; j <= 1000; j++) {
                    myData.addPlusPlus();
                    myData.addAtomic();
                }
            },String.valueOf(i)).start();
        }

        while (Thread.activeCount() > 2){
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName()+"\t finally number value: " + myData.number);
        System.out.println(Thread.currentThread().getName()+"\t finally atomicInteger value: " + myData.atomicInteger);
    }

    public static void seeOkByVolatile() {
        MyData myData = new MyData();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"\t come in");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
             myData.addTo60();
            System.out.println(Thread.currentThread().getName()+"\t update number value:"+myData.number);
        },"AAA").start();

        while (myData.number == 0){
            //main线程如未感知到number改变,一直在此空转
        }
        System.out.println(Thread.currentThread().getName()+"\t mission is over");
    }
}

class MyData {
    //int number = 0;
    volatile int number = 0;

    public void addTo60(){
        this.number = 60;
    }
    
    public /*synchronized*/ void addPlusPlus(){
        this.number++;
    }

    AtomicInteger atomicInteger = new AtomicInteger();
    public void addAtomic(){
        atomicInteger.incrementAndGet();
    }

}