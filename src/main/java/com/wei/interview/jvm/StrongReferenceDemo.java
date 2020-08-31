package com.wei.interview.jvm;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author weizhenchao
 * @version 1.0
 * @date：2020/4/6
 */
public class StrongReferenceDemo {
    public static void main(String[] args) throws Exception{
        //强引用
        //strong();

        //软引用
        //soft_enough_memory();
        //soft_not_enough_memory();

        //弱引用
        //weak();

        //myWeakHashMap();

        //method_reference_queue();

        method02();
    }

    public static void method02() throws Exception{
        Object object = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        WeakReference<Object> weakReference = new WeakReference<>(object,referenceQueue);

        System.out.println(object);
        System.out.println(referenceQueue.poll());
        System.out.println(weakReference.get());

        System.out.println("======================");
        object = null;
        System.gc();
        TimeUnit.MILLISECONDS.sleep(500L);
        System.out.println(object);
        System.out.println(referenceQueue.poll());
        System.out.println(weakReference.get());
    }


    public static void method_reference_queue(){
        Object object = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        PhantomReference<Object> phantomReference = new PhantomReference<>(object,referenceQueue);

        System.out.println(object);
        System.out.println(referenceQueue.poll());
        System.out.println(phantomReference.get());
    }

    public static void myWeakHashMap(){
        WeakHashMap<Integer,String> map = new WeakHashMap<>();
        Integer key = new Integer(2);

        map.put(key,"weakHashMap");
        System.out.println(map);

        key = null;
        System.out.println(map);

        System.gc();
        System.out.println(map+"\t"+map.size());
    }


    public static void weak() {
        Object o1 = new Object();
        WeakReference<Object> weakReference = new WeakReference<>(o1);
        System.out.println(o1);
        System.out.println(weakReference.get());

        o1=null;
        System.gc();
        System.out.println("================");
        System.out.println(o1);
        System.out.println(weakReference.get());
    }

    public static void soft_enough_memory() {
        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o1);
        System.out.println(o1);
        System.out.println(softReference.get());

        o1 = null;
        System.gc();
        System.out.println("================");
        System.out.println(o1);
        System.out.println(softReference.get());//内存够用不会被回收
    }

    /**
     * JVM配置,故意产生大对象并配置小内存,使其内存不够用导致oom,查看软引用的回收情况
     * -Xms5m -Xmx5m -XX:+PrintGCDetails
     */
    public static void soft_not_enough_memory() {
        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o1);
        System.out.println(o1);
        System.out.println(softReference.get());
        System.out.println("========================");

        o1 = null;
        try {
            byte[] bytes = new byte[30*1024*1024];//30m
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println(o1);
            System.out.println(softReference.get());//内存够用不会被回收
        }
    }


    public static void strong() {
        Object obj1 = new Object();//默认强引用
        Object obj2 = obj1;//obj2引用赋值
        obj1 = null;//置空
        System.gc();
        System.out.println(obj2);
    }

    /*
        强引用(默认):当内存不足时,JVM开始垃圾回收,对于强引用的对象,就算是出现了OOM也不会对该对象进行回收,死都不收.
        只要还有强引用指向一个对象,就表明对象还"活着",垃圾收集器不会碰这种对象.当一个对象被强引用变量引用时,它处于
        可达状态,它是不能被垃圾回收机制回收的,即使该对象以后永远都不会被用到,JVM也不会回收,因此强引用是造成Java内存泄露的主要原因之一.
        对于一个普通对象,如果没有其他的引用关系,只要超过了引用的作用域或者显式的将(强)引用赋值为null,一般认为就是可以被垃圾收集的了(具体回收时机还要看垃圾收集策略)
        java总最常见的强引用:把一个对象赋给一个引用变量,这个引用变量就是一个强引用

        软引用是一种强引用弱化了一些的引用,需要用java.lang.ref.SoftReference类来实现,对于只有软引用的对象来说,
        当系统内存充足时,它不会被回收,当系统内存不足时,会被回收
        软引用通常用在对内存敏感的程序中,比如告诉缓存就有用到软引用,内存够用时就保留,不够就回收

        弱引用需要用java.lang.ref.WeakReference类来实现,它比软引用的生存期更短,对于只有弱引用的对象来说,只要垃圾回收机制一运行,不管JVM的内存空间是否足够,都会回收该对象占用的内存
        软/弱引用场景:假如有一个应用需要读取大量的本地图片,如果每次读取图片都从硬盘读取则会严重影响性能,如果一次性全部加载到内存中又可能造成内存溢出.此时使用软引用可以解决这个问题:
        设计思路:用一个hashmap来保存图片的路径和相应图片对象关联的软引用之间的映射关系,在内存不足时,JVM会自动回收这些缓存图片对象所占用的空间,从而有效避免了OOM的问题
        Map<String,SoftReference<Bitmap>> imageCache = new HashMap<String,SoftReference<Bitmap>>();

        虚引用需要用java.lang.ref.PhantomReference类来实现,虚引用不会决定对象的生命周期,如果一个对象仅持有虚引用,name它就和没有任何引用一样,在任何时候都可能被垃圾回收期回收,
        它不能单独使用也不能通过它访问对象,虚引用必须和引用队列(ReferenceQueue)联合使用.虚引用主要作用是跟踪对象被垃圾回收的状态,提供了一种确保对象被finalize以后做某些事情的机制.
        设置虚引用关联的唯一目的,就是在这个对象被收集器回收的时候收到一个系统通知或者后续添加进一步的处理

     */
}
