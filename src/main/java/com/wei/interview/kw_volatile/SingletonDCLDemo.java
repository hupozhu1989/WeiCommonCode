package com.wei.interview.kw_volatile;

/**
 * volatile禁止指令重排
 * @author weizhenchao
 * @version 1.0
 * @date：2020/3/26
 */
public class SingletonDCLDemo {
    //DCL：double check lock
    public static volatile SingletonDCLDemo instance = null;

    private SingletonDCLDemo() {
        System.out.println(Thread.currentThread().getName()+"\t 我是构造方法");
    }
    
    public static SingletonDCLDemo getInstance(){
        if (instance == null){
            synchronized (SingletonDCLDemo.class){
                if (instance == null){
                    instance = new SingletonDCLDemo();
                    /*
                        instance = new SingletonDemo();可以分为下面3步完成
                        1.分配对象内存空间
                        2.初始化对象
                        3.设置instance指向刚分配的内存地址,此时instance != null
                        对应的汇编码如下(参考T02_CreateObject.java)：
                            0 new #2 <java/lang/Object>
                            4 invokespecial #1 <java/lang/Object.<init>>
                            7 astore_1

                        java创建对象的过程:①类加载检查②分配内存③初始化零值④设置对象头⑤执行init方法
                        计算机在执行程序时,为了提高性能,编译器和处理器常常会做指令重排,一把分为以下3种:
                        源代码->编译器优化的重排->指令并行的重排->内存系统的重排->最终执行的指令
                        处理器在进行重新排序时必须要考虑指令之间的数据依赖性

                        实现方式：内存屏障(memory barrier)
                        MESI协议,英特尔x86硬件层面多颗cpu保持缓存一致性的协议,与volatile无关
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
                SingletonDCLDemo.getInstance();
            },String.valueOf(i)).start();
        }
    }
}
