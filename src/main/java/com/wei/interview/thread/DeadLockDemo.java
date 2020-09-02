package com.wei.interview.thread;

/**
 * 死锁
 * @author weizhenchao
 * @create 2020-04-01-下午 2:39
 */
public class DeadLockDemo {
    /*
        死锁是指两个或者以上的线程在执行过程中,因争夺资源而造成的一种相互等待的现象,若无外力干涉那他们都将无法推进下去
        如何避免线程死锁?
        1.破坏互斥条件：这个条件我们没有办法破坏，因为我们⽤锁本来就是想让他们互斥的（临界资源需要互斥访问）。
        2.破坏请求与保持条件：⼀次性申请所有的资源。
        3.破坏不剥夺条件：占⽤部分资源的线程进⼀步申请其他资源时，如果申请不到，可以主动释放它占有的资源。
        4.破坏循环等待条件：靠按序申请资源来预防。按某⼀顺序申请资源，释放资源则反序释放。破坏循环等待条件。
     */
    private static Object resource1 = new Object();//资源 1
    private static Object resource2 = new Object();//资源 2

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (resource1) {
                System.out.println(Thread.currentThread() + "get resource1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + "waiting get resource2");
                synchronized (resource2) {
                    System.out.println(Thread.currentThread() + "get resource2");
                }
            }
        }, "线程1").start();

        new Thread(() -> {
            synchronized (resource2) {
                System.out.println(Thread.currentThread() + "get resource2");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + "waiting get resource1");
                synchronized (resource1) {
                    System.out.println(Thread.currentThread() + "get resource1");
                }
            }
        }, "线程2").start();
    }

    /*
        https://zhuanlan.zhihu.com/p/51576275
        Linux       ps -ef|grep xxxx        ls -l
        windows下的java运行程序,也有类似ps的查看进程的命令,jps = java ps
            jps -l
            jstack 6164

     */
}