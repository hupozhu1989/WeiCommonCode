package com.wei.interview.thread.pool;

/**
 * 合理配置线程池
 * @author weizhenchao
 * @create 2020-04-01-下午 2:18
 */
public class ThreadPoolConfig {
    public static void main(String[] args) {
        //查看CPU核数
        System.out.println(Runtime.getRuntime().availableProcessors());
    }
    /*
        合理配置线程池
        1.CPU密集型:该任务需要大量的运算,而没有阻塞,CPU一直全速运行
        配置尽可能少的线程数量,一般公式:CPU核数+1个线程的线程池
        2.IO密集型:该任务需要大量的IO,即大量的阻塞
        IO密集型时,大部分线程都阻塞,故需要多配置线程数,利用被浪费掉的阻塞时间
        参考公式:CPU核数/(1-阻塞系数)     阻塞系数在0.8~0.9之间
        比如8核CPU:8/(1-0.9)=80 个线程数

     */
}
