package com.wei.common.zklock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author weizhenchao
 * @version 1.0
 * @date：2020/4/13
 */
public class OrderService {
    private OrderNumCreateUtil orderNumCreateUtil = new OrderNumCreateUtil();
    private Lock lock = new ReentrantLock();
    private ZkLock zkLock = new ZkDistributedLock();

    public String getOrderNum(){
        //lock.lock();
        zkLock.zklock();
        try {
            return orderNumCreateUtil.getOrderNum();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //lock.unlock();
            zkLock.zkUnlock();
        }

        return orderNumCreateUtil.getOrderNum();
    }
}
