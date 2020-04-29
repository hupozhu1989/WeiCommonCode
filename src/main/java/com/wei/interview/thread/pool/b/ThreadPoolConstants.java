package com.wei.interview.thread.pool.b;

public class ThreadPoolConstants {
    public static final int CORE_POOL_SIZE = 5;
    public static final int MAX_POOL_SIZE = 10;
    public static final int QUEUE_CAPACITY = 100;
    public static final Long KEEP_ALIVE_TIME = 1L;
    private ThreadPoolConstants(){
        //私有构造方法,不允许创建对象实例,直接类名.静态变量
    }
}
