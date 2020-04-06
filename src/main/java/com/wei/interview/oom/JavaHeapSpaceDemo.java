package com.wei.interview.oom;

import java.util.Random;

/**
 * @author weizhenchao
 * @version 1.0
 * @date：2020/4/6
 */
public class JavaHeapSpaceDemo {
    public static void main(String[] args) {
        //Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
        //-Xms10m -Xmx10m

        //byte[] bytes = new byte[20*1024*1024];

        String str = "strong";
        while (true){
            str += str + new Random().nextInt(11111111)+new Random().nextInt(222222222);
            str.intern();
        }

    }
}
