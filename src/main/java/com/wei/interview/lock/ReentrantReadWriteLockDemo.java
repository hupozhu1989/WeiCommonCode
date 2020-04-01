package com.wei.interview.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 * @author weizhenchao
 * @version 1.0
 * @date：2020/3/29
 */
public class ReentrantReadWriteLockDemo {
    /*
     * 多个线程同时操作 一个资源类没有任何问题 所以为了满足并发量
     * 读取共享资源应该可以同时进行
     * 但是 如果有一个线程想去写共享资源  就不应该有其他线程可以对资源进行读或写
     * <p>
     * 小总结:
     * 读 读能共存
     * 读 写不能共存
     * 写 写不能共存
     * 写操作 原子+独占 整个过程必须是一个完成的统一整体 中间不允许被分割 被打断
     */
    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        for (int i = 1; i <= 5; i++) {
            final int temp = i;
            new Thread(()->{
                myCache.put(temp+"",temp);
            },String.valueOf(i)).start();
        }

        for (int i = 1; i <= 5; i++) {
            final int temp = i;
            new Thread(()->{
                myCache.get(temp+"");
            },String.valueOf(i)).start();
        }
    }
}

class MyCache{
    //volatile修饰保证可见性
    private volatile Map<String,Object> map = new HashMap<>();
    private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    /**
     * 写
     */
    public void put(String key,Object value){
        rwLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t正在写入:"+key);
            try {
                //暂停一会儿线程
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"\t写入完成:");
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    /**
     * 读
     */
    public void get(String key){
        rwLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t正在读取:");
            //暂停一会儿线程
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Object value = map.get(key);
            System.out.println(Thread.currentThread().getName()+"\t读取完成:"+value);
        } finally {
            rwLock.readLock().unlock();
        }
    }
}
