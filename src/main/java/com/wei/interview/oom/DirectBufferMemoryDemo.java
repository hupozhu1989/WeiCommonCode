package com.wei.interview.oom;

import java.nio.ByteBuffer;

/**
 * @author weizhenchao
 * @create 2020-04-07-上午 11:30
 */
public class DirectBufferMemoryDemo {
    public static void main(String[] args) {
        //System.out.println("配置的maxDirectMemory:"+ VM.maxDirectMemory()/(double)1024/1024);

        /*
            参数配置: -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
            故障现象: Exception in thread "main" java.lang.OutOfMemoryError: Direct buffer memory
            导致原因: 写NIO程序经常使用ByteBuffer来读取或写入数据,这是一种基于通道(channel)与缓冲区(buffer)的IO方式,
                它可以使用native函数库直接分配堆外内存,然后通过一个存储在Java堆里面的DirectByteBuffer对象作为这块内存的引用进行操作.
                这样能在一些场景中显著提高性能,因为避免了在java堆和native堆中来回复制数据

                ByteBuffer.allocate(int capacity)   分配JVM堆内存,属于GC管辖范围,由于需要拷贝所以速度相对较慢
                ByteBuffer.allocateDirect(int capacity) 分配OS本地内存,不属于GC管辖范围,由于不需要拷贝所以速度相对较快

                如果不断分配本地内存,堆内存很少使用,那么JVM就不需要执行GC,DirectByteBuffer对象们就不会被回收,这时候堆内存充足,但本地内存
                可能已经用光了,再次尝试分配本地内存就会出现OutOfMemoryError
         */

        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(6*1024*1024);

    }
}
