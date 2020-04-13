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
