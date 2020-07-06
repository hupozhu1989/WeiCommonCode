package com.wei.interview.kw_volatile;

import java.util.concurrent.TimeUnit;

/**
 * 线程的可见性
 * @author weizhenchao
 * @version 1.0
 * @date：2020/7/3
 */
public class T01_ThreadVisibility {
    //打开或关闭volatile
    private static /*volatile*/ boolean running = true;

    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            while (running){
                //do sth
            }
            System.out.println("end");
        },"server").start();

        TimeUnit.SECONDS.sleep(1L);
        running = false;
    }
}
