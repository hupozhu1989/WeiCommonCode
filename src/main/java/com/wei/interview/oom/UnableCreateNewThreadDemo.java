package com.wei.interview.oom;

import java.util.concurrent.TimeUnit;

/**
 * @author weizhenchao
 * @create 2020-04-07-下午 3:25
 */
public class UnableCreateNewThreadDemo {
    public static void main(String[] args) {

        for (int i = 1;  ;i++){
            System.out.println("************ i="+i);
            new Thread(()->{
                try {
                    TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();

            /*
                高并发请求服务器时,出现以下异常:
                    java.lang.OutOfMemoryError: unable to create new native thread
                native thread异常与对应的平台有关

                导致原因:
                    1.应用创建太多线程,一个应用进程创建多个线程,超过系统承载极限
                    2.你的服务器不允许你的应用程序创建这么多线程,Linux系统默认允许单个进程可以创建的线程数是1024个

                解决办法:
                    1.想办法降低你的应用程序创建线程的数量,分析应用是否需要这么多线程数,如不需要,将线程数降低
                    2.修改Linux服务器配置,扩大Linux默认限制

             */
        }



    }
}
