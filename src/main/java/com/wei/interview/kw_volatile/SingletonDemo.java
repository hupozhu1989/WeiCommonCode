package com.wei.interview.kw_volatile;

/**
 * volatile禁止指令重排
 * @author weizhenchao
 * @version 1.0
 * @date：2020/3/26
 */
public class SingletonDemo {
    public static volatile SingletonDemo instance = null;

    private SingletonDemo() {
        System.out.println(Thread.currentThread().getName()+"\t 我是构造方法");
    }
    
    public static SingletonDemo getInstance(){
        if (instance == null){
            synchronized (SingletonDemo.class){
                if (instance == null){
                    instance = new SingletonDemo();
                    /*
                        instance = new SingletonDemo();可以分为下面3步完成
                        1.分配对象内存空间
                        2.初始化对象
                        3.设置instance指向刚分配的内存地址,此时instance != null

                        java创建对象的过程:①类加载检查②分配内存③初始化零值④设置对象头⑤执行init方法
                        计算机在执行程序时,为了提高性能,编译器和处理器常常会做指令重排,一把分为以下3种:
                        源代码->编译器优化的重排->指令并行的重排->内存系统的重排->最终执行的指令
                        处理器在进行重新排序时必须要考虑指令之间的数据依赖性
                     */
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        /*System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());*/

        for (int i = 1; i <= 10; i++) {
            new Thread(()->{
                SingletonDemo.getInstance();
            },String.valueOf(i)).start();
        }
    }
}
