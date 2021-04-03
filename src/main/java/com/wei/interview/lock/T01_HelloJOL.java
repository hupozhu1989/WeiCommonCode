package com.wei.interview.lock;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

/**
 * @author weizhenchao
 * @version 1.0
 * @date：2020/7/2
 */
public class T01_HelloJOL {
    public static void main(String[] args) throws Exception{
        //默认情况 偏向锁有个时延，默认是4秒--101
        //TimeUnit.SECONDS.sleep(5);

        //锁 = 001 无锁态
        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        //001 + hashcode
        o.hashCode();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        //轻量级自旋锁--00
        synchronized (o){
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
            TimeUnit.SECONDS.sleep(5);
        }

    }
}
