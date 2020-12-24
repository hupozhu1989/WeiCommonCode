package com.wei.interview.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author weizhenchao
 * @version 1.0
 * @date：2020/3/26
 */
public class CASDemo {
    /*
        CAS(CompareAndSwap)
        从某一内存上取值V,旧的预期值A,要修改的更新值B,当且仅当预期值A和内存值V相同时,将内存值V修改为B,否则什么都不做

        AtomicInteger为什么可以保证原子性?
        1.UnSafe类在于sun.misc包中,其内部方法操作可以向C的指针一样直接操作内存,UnSafe类中所有的方法都是native修饰的,
            也就是说UnSafe类中的方法都是直接调用操作底层资源执行响应的任务
        2.变量ValueOffset,是该变量在内存中的偏移地址,因为UnSafe就是根据内存偏移地址获取数据的
        3.变量value用volatile修饰,保证了多线程之间的可见性

        public final int getAndAddInt(Object var1, long var2, int var4) {
            int var5;
            do {
                var5 = this.getIntVolatile(var1, var2);
            } while(!this.compareAndSwapInt(var1, var2, var5, var5 + var4));
            return var5;
        }
        var1 AtomicInteger对象本身
        var2 该对象值的引用地址
        var4 需要变动的数值
        var5 是用过var1 var2找出内存中绅士的值
        用该对象当前的值与var5比较
        如果相同,更新var5的值并且返回true
        如果不同,继续取值然后比较,直到更新完成
     */
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);
        System.out.println(atomicInteger.compareAndSet(5,2019) + "\t current data = "+atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5,1024) + "\t current data = "+atomicInteger.get());
    }
}
