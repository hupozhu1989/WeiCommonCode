package com.wei.interview.kw_volatile;

import java.util.concurrent.CountDownLatch;

/**
 * @author weizhenchao
 * @version 1.0
 * @date：2020/7/3
 */
public class T03_CacheLinePadding {
    /*
      按块读取，程序局部性原理，可以提高效率，充分发挥总线CPU针脚等一次性读取更多数据的能力
      cache line，缓存行对齐，64个字节
      一个long类型8个字节
     */

    public static final long COUNT = 1_0000_0000L;

    private static class T {
        public volatile long x = 0L;
    }

    public static T[] arr = new T[2];

    static {
        arr[0] = new T();
        arr[1] = new T();
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(2);

        Thread t1 = new Thread(()->{
            for (long i = 0; i < COUNT; i++){
                arr[0].x = i;
            }
            latch.countDown();
        });

        Thread t2 = new Thread(()->{
            for (long i = 0; i < COUNT; i++){
                arr[1].x = i;
            }
            latch.countDown();
        });

        final long start = System.nanoTime();
        t1.start();
        t2.start();
        latch.await();
        //2814
        System.out.println((System.nanoTime()-start)/100_0000);
    }

}
