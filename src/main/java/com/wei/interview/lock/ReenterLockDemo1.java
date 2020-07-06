package com.wei.interview.lock;

/**
 *  可重入锁(也叫做递归锁)
 *  在同一线程外层方法获取锁的时候,在进入内层方法会自动获取锁
 *  线程可以进入任何一个它已经标记的锁所同步的代码块
 *
 * @author veliger@163.com
 * @date 2019-04-12 23:36
 **/
public class ReenterLockDemo1 {

    public static void main(String[] args) {
        Phone1 phone = new Phone1();
        new Thread(()->{
            try {
                phone.sendSms();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"t1").start();
        new Thread(()->{
            try {
                phone.sendSms();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"t2").start();
    }
}

class Phone1 {
    public synchronized void sendSms() throws Exception{
        System.out.println(Thread.currentThread().getName()+"\tsendSms");
        sendEmail();
    }
    public synchronized void sendEmail() throws Exception{
        System.out.println(Thread.currentThread().getName()+"\tsendEmail");
    }
}