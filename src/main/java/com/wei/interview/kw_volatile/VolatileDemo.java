package com.wei.interview.kw_volatile;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * volatile保证可见性和不保证原子性
 * @author weizhenchao
 * @version 1.0
 * @date：2020/3/25
 */
public class VolatileDemo {
    /*
        JMM: 可见性 原子性 有序性;
        volatile的作用:①保证可见性,②不保证原子性,③禁止指令重排
        各个线程对主内存中共享变量的操作都是各个线程各自拷贝到自己的工作内存操作后再写回主内存中的
     */
    public static void main(String[] args) {//main是一切运行方法的入口
        //volatile的可见性: 每个线程的工作内存  主物理内存
        //seeOkByVolatile();

        //volatile不保证原子性
        automaticMethod();
    }

    //打开或关闭number的volatile注释，证明volatile不保证原子性
    //打开或关闭synchronized注释，证明synchronized的原子性
    public static void automaticMethod() {
        MyData myData = new MyData();
        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                for (int j = 1; j <= 1000; j++) {
                    myData.addPlusPlus();
                    myData.addPlusPlusAtomic();
                }
            },String.valueOf(i)).start();
        }

        while (Thread.activeCount() > 2){
            //让步,线程让出当前时间片给其他线程执行
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName()+"\t finally number value: " + myData.number);
        System.out.println(Thread.currentThread().getName()+"\t finally atomicInteger value: " + myData.atomicInteger);
    }

    //打开或关闭number的volatile注释，证明其可见性
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
    int number = 0;
    //volatile int number = 0;
    AtomicInteger atomicInteger = new AtomicInteger();

    public void addTo60(){
        this.number = 60;
    }

    public synchronized void addPlusPlus(){
        this.number++;
    }

    public void addPlusPlusAtomic(){
        atomicInteger.incrementAndGet();
    }

}