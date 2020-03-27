package com.wei.interview.kw_volatile;

/**
 * @author weizhenchao
 * @version 1.0
 * @date：2020/3/26
 */
public class SingletonDemo {
    //volatile禁止指令重排
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
