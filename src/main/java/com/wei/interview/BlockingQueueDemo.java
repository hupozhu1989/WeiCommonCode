package com.wei.interview;

/**
 * @author weizhenchao
 * @version 1.0
 * @date：2020/3/29
 */
public class BlockingQueueDemo {
    public static void main(String[] args) {



    }
    /*
        当阻塞队列是空时,从队列中获取元素的操作将会被阻塞.
        当阻塞队列是满时,往队列中添加元素的操作将会被阻塞.
        BlockingQueue的核心方法
        方法类型    抛出异常     特殊值     阻塞      超时
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

        ArrayBlockingQueue: 由数组结构组成的有界阻塞队列.
        LinkedBlockingDeque: 由链表结构组成的有界(但大小默认值Integer>MAX_VALUE)阻塞队列.
        SynchronousQueue:不存储元素的阻塞队列,也即是单个元素的队列.
     */

}
