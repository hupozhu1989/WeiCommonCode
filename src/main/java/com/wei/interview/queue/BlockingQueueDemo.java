package com.wei.interview.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * BlockingQueue常用方法练习
 * @author weizhenchao
 * @version 1.0
 * @date：2020/3/29
 */
public class BlockingQueueDemo {
    public static void main(String[] args) throws Exception{
//        test_add();
//        test_remove();
//        test_offer();
//        test_poll();
//        test_put();
//        test_take();
//        test_offer2();
        test_poll2();

        /*
            off(e,time,unit)
            poll(time,unit)
         */
    }

    private static void test_poll2() throws InterruptedException {
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue(1);
        System.out.println(blockingQueue.offer(1, 2, TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(2, TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(2, TimeUnit.SECONDS));
    }

    private static void test_offer2() throws InterruptedException {
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue(3);
        System.out.println(blockingQueue.offer(1, 2, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer(2, 2, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer(3, 2, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer(4, 2, TimeUnit.SECONDS));
    }

    private static void test_take() throws InterruptedException {
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue(1);
        blockingQueue.put(1);
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
    }

    private static void test_put() throws InterruptedException {
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue(3);
        blockingQueue.put(1);
        blockingQueue.put(2);
        blockingQueue.put(3);
        blockingQueue.put(4);
    }

    private static void test_poll() {
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue(1);
        System.out.println(blockingQueue.offer(1));
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
    }

    private static void test_offer() {
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue(3);
        System.out.println(blockingQueue.offer(1));
        System.out.println(blockingQueue.offer(2));
        System.out.println(blockingQueue.offer(3));
        System.out.println(blockingQueue.offer(4));
    }

    private static void test_remove() {
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue(1);
        System.out.println(blockingQueue.add(1));
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
    }

    private static void test_add() {
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue(3);
        System.out.println(blockingQueue.add(1));
        System.out.println(blockingQueue.add(2));
        System.out.println(blockingQueue.add(3));
        System.out.println(blockingQueue.add(4));
    }

    /*
        BlockingQueue的核心方法
        方法类型    抛出异常      特殊值      阻塞      超时
          插入      add(e)      off(e)    put(e)   off(e,time,unit)
          移除      remove()    poll()    take()   poll(time,unit)
          检查      element()   peek()    不可用    不可用

        抛出异常    当阻塞队列满时,再往队列里面add插入元素会抛IllegalStateException: Queue full
                   当阻塞队列空时,再往队列Remove元素时候回抛出NoSuchElementException
        特殊值      插入方法,成功返回true 失败返回false
                   移除方法,成功返回元素,队列里面没有就返回null
        一直阻塞    当阻塞队列满时,生产者继续往队列里面put元素,队列会一直阻塞直到put数据or响应中断退出
                   当阻塞队列空时,消费者试图从队列take元素,队列会一直阻塞消费者线程直到队列可用.
        超时退出    当阻塞队列满时,队列会阻塞生产者线程一定时间,超过后限时后生产者线程就会退出

        常用:
            ArrayBlockingQueue: 由数组结构组成的有界阻塞队列.
            LinkedBlockingDeque: 由链表结构组成的有界(但大小默认值Integer>MAX_VALUE)阻塞队列.
            SynchronousQueue:不存储元素的阻塞队列,也即是单个元素的队列.
     */

}
