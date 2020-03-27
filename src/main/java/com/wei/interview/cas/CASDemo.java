package com.wei.interview.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author weizhenchao
 * @version 1.0
 * @date：2020/3/26
 */
public class CASDemo {
    /*
        CAS的核心类:Unsafe

     */
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);

        System.out.println(atomicInteger.compareAndSet(5,2019) + "\t current data = "+atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5,1024) + "\t current data = "+atomicInteger.get());

    }
}
