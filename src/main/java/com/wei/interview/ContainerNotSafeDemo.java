package com.wei.interview;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
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
                System.out.println(set);
            },String.valueOf(i)).start();
        }
    }

    public static void method01ArrayList() {
        //List<String> list = new ArrayList<>();//不安全
        //List<Object> list = Collections.synchronizedList(new ArrayList<>());
        List<String> list = new CopyOnWriteArrayList();
        for (int i = 0; i < 30; i++) {//i->30
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
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
         *
         * 4.优化建议
         */}
}
