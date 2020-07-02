package com.wei.interview.lock;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author weizhenchao
 * @version 1.0
 * @date：2020/7/2
 */
public class HelloJOL {
    public static void main(String[] args) throws Exception{
        //延时4s偏向锁--101
        //TimeUnit.SECONDS.sleep(5);

        //无锁--001
        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        //
        o.hashCode();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        //轻量级自旋锁--00
        synchronized (o){
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }

    }
}
