package com.wei.interview.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author weizhenchao
 * @version 1.0
 * @date：2020/3/31
 */
public class MyThreadPoolDemo {
    public static void main(String[] args) {
        //ExecutorService threadPool = Executors.newFixedThreadPool(5);//一池5个处理线程
        //ExecutorService threadPool = Executors.newSingleThreadExecutor();//一池1个处理线程
        ExecutorService threadPool = Executors.newCachedThreadPool();//一池N个处理线程
        try {
            //模拟10个用户来办理业务,每个用户就是一个来自外部的请求线程
            for (int i = 1; i <= 20; i++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t办理业务");
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
    }
    /*
        线程池做的工作主要是控制运行的线程的数量,处理过程中将任务加入队列,然后在线程创建后启动这些任务,如果线程超过了最大数量,超出的数量的线程排队等候,等其他线程执行完毕,
        再从队列中取出任务来执行.

        主要特点为:线程复用,控制最大并发数,管理线程.
        第一、降低资源消耗.通过重复利用自己创建的线程降低线程创建和销毁造成的消耗.
        第二、提高响应速度.当任务到达时,任务可以不需要等到线程和粗昂就爱你就能立即执行.
        第三、提高线程的可管理性.线程是稀缺资源,如果无限的创阿金,不仅会消耗资源,还会较低系统的稳定性,使用线程池可以进行统一分配,调优和监控.

        Executors.newFixedThreadPool(int)
            主要特点如下:
            1.创建一个定长线程池,可控制线程的最大并发数,超出的线程会在队列中等待.
            2.newFixedThreadPool创建的线程池corePoolSize和MaxmumPoolSize是 相等的,它使用的的LinkedBlockingQueue
            适用:执行一个长期的任务,性能好很多

        Executors.newSingleThreadExecutor()
            主要特点如下:
            1.创建一个单线程化的线程池,它只会用唯一的工作线程来执行任务,保证所有任务都按照指定顺序执行.
            2.newSingleThreadExecutor将corePoolSize和MaxmumPoolSize都设置为1,它使用的的LinkedBlockingQueue
            适用:一个任务一个线程执行的任务场景

        Executors.newCachedThreadPool()
            主要特点如下:
            1.创建一个可缓存线程池,如果线程池长度超过处理需要,可灵活回收空闲线程,若无可回收,则创建新线程.
            2.newCachedThreadPool将corePoolSize设置为0MaxmumPoolSize设置为Integer.MAX_VALUE,它使用的是SynchronousQUeue,
            也就是说来了任务就创建线程运行,如果线程空闲超过60秒,就销毁线程
            适用:执行很多短期异步的小程序或者负载较轻的服务器

        线程池7大参数：
            1.corePoolSize:线程池中的常驻核心线程数
            2.maximumPoolSize:线程池能够容纳同时执行的最大线程数,此值大于等于1
            3.keepAliveTime:多余的空闲线程存活时间,当空间时间达到keepAliveTime值时,多余的线程会被销毁直到只剩下corePoolSize个线程为止
            4.unit:keepAliveTime的单位
            5.workQueue:任务队列,被提交但尚未被执行的任务.
            6.threadFactory:表示生成线程池中工作线程的线程工厂,用户创建新线程,一般用默认即可
            7.handler:拒绝策略,表示当线程队列满了并且工作线程大于等于线程池的最大显示 数(maxnumPoolSize)时如何来拒绝.
     */
}
