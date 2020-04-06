package com.wei.interview.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * @author weizhenchao
 * @version 1.0
 * @date：2020/4/6
 */
public class GCOverheadDemo {
    /*
        JVM参数配置演示:  -Xms10m -Xmx10m -XX:MaxDirectMemorySize=5m -XX:+PrintGCDetails
        GC回收时机过长会抛出 OutOfMemoryError ,过长的定义是 超过98%的时间用来做GC并且回收了不到2%的堆内存,连续多次GC,都只回收了不到2%的极端情况下才会抛出
        假如不抛出 GC overhead limit exceeded ,GC清理的这么点内存很快会再次填满,迫使GC再次执行,就这样行程恶性循环,CPU使用率一直是100%,而GC却没有任何成果
     */
    public static void main(String[] args) {
        //java.lang.OutOfMemoryError: GC overhead limit exceeded
        int i = 0;
        List<String> list = new ArrayList<>();
        try {
            while (true){
                list.add(String.valueOf(++i).intern());
            }
        }catch (Throwable e){
            System.out.println("*********i: "+i);
            e.printStackTrace();
            throw e;
        }

    }
}
