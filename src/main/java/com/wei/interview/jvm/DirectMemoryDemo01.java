package com.wei.interview.jvm;

import sun.nio.ch.DirectBuffer;

import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;

/**
 * @author weizhenchao
 * @version 1.0
 * @date：2020/12/24
 */
public class DirectMemoryDemo01 {
    public static void main(String[] args) throws InterruptedException {
        method01();
    }

    public static void method01() throws InterruptedException {
        System.out.println("开始加");
        //256M
        ByteBuffer bb = ByteBuffer.allocateDirect(1024 * 1024 * 256);
        TimeUnit.SECONDS.sleep(10);
        System.out.println("开始减");
        ((DirectBuffer)bb).cleaner().clean();
        TimeUnit.SECONDS.sleep(10);
    }
}
