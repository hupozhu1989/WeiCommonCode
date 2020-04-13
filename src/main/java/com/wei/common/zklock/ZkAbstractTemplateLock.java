package com.wei.common.zklock;

import org.I0Itec.zkclient.ZkClient;

import java.util.concurrent.CountDownLatch;

/**
 * @author weizhenchao
 * @version 1.0
 * @date：2020/4/13
 */
public abstract class ZkAbstractTemplateLock implements ZkLock {

    public static final String ZKSERVER = "192.168.111.144:2181";
    public static final int TIME_OUT = 45*1000;
    protected static final String PATH = "/zklock0401";
    ZkClient zkClient = new ZkClient(ZKSERVER, TIME_OUT);
    protected CountDownLatch countDownLatch = null;

    @Override
    public void zklock() {
        if (tryZkLock()){
            System.out.println(Thread.currentThread().getName()+"\t占用锁成功");
        }else {
            waitZkLock();
            zklock();
        }
    }

    public abstract boolean tryZkLock();

    public abstract void waitZkLock();

    @Override
    public void zkUnlock() {
        if (zkClient != null){
            zkClient.close();
        }
        System.out.println(Thread.currentThread().getName()+"\t释放锁成功");
    }
}
