package com.wei.common.zklock;

/**
 * @author weizhenchao
 * @version 1.0
 * @date：2020/4/13
 */
public class OrderNumCreateUtil {
    private static int number = 0;

    public String getOrderNum(){
        return "\t生成订单号:"+(++number);
    }
}
