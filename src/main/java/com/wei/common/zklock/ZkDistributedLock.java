package com.wei.common.zklock;

import org.I0Itec.zkclient.IZkDataListener;

import java.util.concurrent.CountDownLatch;

/**
 * @author weizhenchao
 * @version 1.0
 * @date：2020/4/13
 */
public class ZkDistributedLock extends ZkAbstractTemplateLock {
    @Override
    public boolean tryZkLock() {
        try {
            zkClient.createEphemeral(PATH);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public void waitZkLock() {
        IZkDataListener iZkDataListener = new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {
            }

            @Override
            public void handleDataDeleted(String s) throws Exception {
                if (countDownLatch != null){
                    countDownLatch.countDown();
                }
            }
        };
        zkClient.subscribeDataChanges(PATH,iZkDataListener);
        if (zkClient.exists(PATH)){
            //只能等着,不能往下走
            countDownLatch = new CountDownLatch(1);
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        zkClient.unsubscribeDataChanges(PATH,iZkDataListener);
    }
}
