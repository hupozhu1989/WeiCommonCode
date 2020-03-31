package com.wei.interview;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 集合类线程不安全
 * @author weizhenchao
 * @version 1.0
 * @date：2020/3/27
 */
public class ContainerNotSafeDemo {
    public static void main(String[] args) {
        //method01ArrayList();
        //method02HashSet();
        method03HashMap();

    }

    public static void method03HashMap() {
        //Map<String,String> map = new HashMap<>();
        //Map<String,String> map = Collections.synchronizedMap(new HashMap<>());
        Map<String,String> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0,8));
                System.out.println(map);
            },String.valueOf(i)).start();
        }
    }

    public static void method02HashSet() {
        //Set<String> set = new HashSet<>();
        //Set<String> set = Collections.synchronizedSet(new HashSet<>());
        Set<String> set = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                set.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(Thread.currentThread().getName()+"\t"+set);
            },String.valueOf(i)).start();
        }
    }

    public static void method01ArrayList() {
        List<String> list = new ArrayList<>();//不安全
        //List<Object> list = Collections.synchronizedList(new ArrayList<>());
        //List<String> list = new CopyOnWriteArrayList();
        for (int i = 1; i <= 30; i++) {//i->30
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(Thread.currentThread().getName()+"\t"+list);
            },String.valueOf(i)).start();
        }
        /**
         * 1.故障现象
         *      java.util.ConcurrentModificationException
         * 2.导致原因
         *  并发争抢修改导致
         *
         * 3.解决方案
         *  3.1 new Vector()
         *  3.2 Collections.synchronizedList(new ArrayList<>());
         *  3.3 new CopyOnWriteArrayList()  写时复制
         *      写时复制 copyOnWrite 容器即写时复制的容器,往容器添加元素的时候,不直接往当前容器object[]添加,而是先将当前容器object[]进行
         *      copy,复制出一个新的object[] newElements,然后向新容器object[] newElements 里面添加元素,
         *      添加元素后,再将原容器的引用指向新的容器 setArray(newElements);
         *      这样的好处是可以对copyOnWrite容器进行并发的读,而不需要加锁 因为当前容器不会添加任何容器.所以copyOnwrite容器也是一种
         *      读写分离的思想,读和写不同的容器.
         * 4.优化建议
         */}
}
