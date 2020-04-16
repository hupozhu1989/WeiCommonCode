package com.wei.common.zklock;

/**
 * @author weizhenchao
 * @version 1.0
 * @date：2020/4/13
 */
public class Client {
    public static void main(String[] args) {
        //method01();
        for (int i = 1; i <= 50; i++) {
            new Thread(()->{
                String orderNum = new OrderService().getOrderNum();
                System.out.println(Thread.currentThread().getName()+"\t"+orderNum);
            },String.valueOf(i)).start();
        }
        /*
            JUC
            sync+lock 仅限于一个jvm环境(单机)
            1:N
            JVM:N thread
            N jvm: N thread

            分布式锁实现:①redis-redission ②zookeeper
         */
    }

    public static void method01() {
        OrderService orderService = new OrderService();
        for (int i = 1; i <= 50; i++) {
            new Thread(()->{
                String orderNum = orderService.getOrderNum();
                System.out.println(Thread.currentThread().getName()+"\t"+orderNum);
            },String.valueOf(i)).start();
        }
    }
}
