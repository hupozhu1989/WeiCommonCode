package com.wei.common.zklock;

/**
 * @author weizhenchao
 * @version 1.0
 * @date：2020/4/13
 */
public interface ZkLock {
    public void zklock();
    public void zkUnlock();
}
