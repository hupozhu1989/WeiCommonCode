package com.wei.interview.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程通信之生产者消费者阻塞队列版
 * volatile/CAS/atomicInteger/BlockQueue/线程交互/原子引用
 * @author weizhenchao
 * @create 2020-03-30-上午 10:23
 */
public class ProdConsumerBlockQueueDemo {
    //生产者生产一个,消费者消费一个
    public static void main(String[] args) {
        MyResource myResource = new MyResource(new ArrayBlockingQueue<>(10));
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t生产线程启动");
            try {
                myResource.myProd();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"Producer").start();
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t消费线程启动");
            try {
                myResource.myConsumer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"Consumer").start();
        try { TimeUnit.SECONDS.sleep(10); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println("============");
        System.out.println("时间到,停止活动");
        myResource.stop();

    }
}
class MyResource{
    //默认开启 进行生产消费的交互
    private volatile boolean flag = true;
    //默认值是0
    AtomicInteger atomicInteger = new AtomicInteger();

    private BlockingQueue<String> blockingQueue = null;

    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    /**
     * 生产者生产
     */
    public void myProd() throws Exception {
        String data = null;
        boolean returnValue;
        while (flag){
            data = atomicInteger.incrementAndGet()+"";//++i
            returnValue = blockingQueue.offer(data,1L, TimeUnit.SECONDS);
            if (returnValue){
                System.out.println(Thread.currentThread().getName() + "\t生产队列数据【" + data + "】成功");
            }else {
                System.out.println(Thread.currentThread().getName() + "\t生产队列数据【" + data + "】失败");
            }
            TimeUnit.SECONDS.sleep(1);//每一秒往队列放一个值进去
            /*if ("3".equals(data)){
                //模拟消费者超时
                TimeUnit.SECONDS.sleep(3);
            }*/
        }
        System.out.println(Thread.currentThread().getName() + "\t停止，表示flag=" + flag);
    }

    /**
     * 消费者消费
     */
    public void myConsumer() throws Exception {
        String result = null;
        while (flag){
            result = blockingQueue.poll(2L,TimeUnit.SECONDS);
            if (null == result || "".equals(result)){
                flag=false;
                System.out.println(Thread.currentThread().getName()+"\t超过2s没有取到，消费退出");
                break;
            }
            System.out.println(Thread.currentThread().getName() + "\t消费队列数据【" + result + "】成功");
        }
    }

    public void stop(){
        flag = false;
    }
}

