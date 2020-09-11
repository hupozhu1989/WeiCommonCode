package com.wei.interview.gc;

import java.util.Random;

/**
 * @author weizhenchao
 * @create 2020-04-08-下午 3:05
 */
public class GCDemo {
    public static void main(String[] args) {
        String str = "strong";
        while (true){
            str += str + new Random().nextInt(11111111)+new Random().nextInt(222222222);
            str.intern();
        }
    }
    /*
        GC算法（引用计数/复制/标清/标整）是内存回收的方法论，垃圾收集器就是算法落地实现
        因为目前为止还没有完美的收集器出现，更加没有万能的收集器，只是针对具体应用最合适的收集器，进行分代收集

        串行垃圾回收器（Serial）
            它为单线程环境设计并且只使用一个线程进行垃圾回收，会暂停所有的用户线程。所以不适合服务器环境
        并行垃圾回收器（Parallel）
            多个垃圾回收线程并行工作，此时用户线程是暂停的，适用于科学计算/大数据处理等弱交互场景
        并发垃圾回收器（CMS）
            用户线程和垃圾收集线程同时执行（不一定是并行，可能交替执行），不需要停顿用户线程，互联网公司多用它，适用于对响应时间有要求的场景
        G1垃圾回收器
            G1垃圾回收器将堆内存分割成不同的区域然后并发的对其进行垃圾回收

        怎么查看默认的垃圾收集器是哪个？
            java -XX:+PrintCommandLineFlags -version

        默认的垃圾收集器有哪些
            UseSerialGC,UseParallelGC,UseConcMarkSweepGC,UseParNewGC,UseParallelOldGC,UseG1GC
        参数说明:
            DefNew      Default New Generation
            Tenured     Old
            ParNew      Parallel New Generation
            PSYoungGen  Parallel Scavenge
            ParOldGen   Parallel Old Generation
        Server/Client模式分别是什么意思: 64位只有Server模式

        新生代:
            串行GC（Serial）/（Serial Coping）
                一个单线程的收集器,在进行垃圾收集的时候,必须暂停其他所有工作线程直到它收集结束
                对应JVM参数: -XX:+UseSerialGC
                开启后会使用:Serial(Young区用)+Serial Old(Old区用)的收集器组合
                表示:新生代、老年代都会使用串行回收收集器，新生代使用复制算法，老年代使用标记-整理算法

            并行GC(ParNew)
                使用多线程进行垃圾回收，在垃圾收集时，会Stop-The-World暂停其他所有工作线程直到它收集结束
                常见应用场景是搭配老年代的CMS GC工作
                对应JVM参数: -XX:+UseParNewGC 启用ParNew收集器,只影响新生代的收集,不影响老年代
                开启后使用:ParNew(Young区用)+Serial Old(Old区用)的收集器组合,新生代使用复制算法，老年代使用标记-整理算法

            并行回收GC(Parallel)/(Parallel Scavenge)
                Parallel Scavenge收集器类似ParNew,也是一个新生代垃圾收集器,使用复制算法,并行的多线程垃圾回收期,俗称吞吐量优先收集器,
                一句话:串行收集器在新生代和老年代的并行化
                对应JVM参数: -XX:UseParallelGC或-XX:UseParallelOldGC(可互相激活),新生代使用复制算法，老年代使用标记-整理算法

        老年代:
            串行回收GC(Serial Old)/(Serial MSC)
                Serial Old收集器是Serial垃圾收集器的老年版本,单线程的收集器,使用标记-整理算法
            并行GC(Parallel Old)/(Parallel MSC)
                Parallel Old收集器是Parallel Scavenge的老年代版本,使用多线程的标记-整理算法
                对应JVM参数: -XX:+UseParallelOldGC,新生代Parallel+老年代Parallel Old

            并发标记清除GC(CMS)
                ConcurrentMarkSweep:并发标记清除,是一种以最短回收停顿时间为目标的收集器
                对应JVM参数: -XX:+UseConcMarkSweepGC 开启该参数后会自动将-XX:+UseParNewGC打开,开启该参数后,使用ParNew(Young区用)+
                CMS(Old区用)+Serial Old的收集器组合,Serial Old将作为CMS出错的后备收集器
                4步过程:
                    初始标记(CMS initial mark)
                    并发标记(CMS concurrent mark)和用户线程一起:主要标记过程,标记全部对象
                    重新标记(CMS remark):为了修正在并发标记期间,因用户程序继续运行而导致标记产生变动的那一部分对象的标记记录,仍然需要暂停所有的工作线程
                    并发清除(CMS concurrent sweep)和用户线程一起:清除GC Root不可达对象,不需要暂停工作线程,由于耗时最长的并发标记和并发清除过程中,
                        垃圾收集线程和用户线程一起并发工作,所以总体上来看CMS收集器的内存回收和用户线程是一起并发执行
                优缺点:
                    优点:并发收集低停顿
                    缺点:①并发执行，对CPU资源压力大;②采用的标记清除算法会导致大量碎片

            G1垃圾收集器
                整体采用标记-整理算法,局部通过复制算法,不会产生内存碎片


               -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+UseG1GC

               -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+UseConcMarkSweepGC
     */
}
