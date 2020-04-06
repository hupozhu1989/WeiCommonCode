package com.wei.interview.oom;

/**
 * @author weizhenchao
 * @version 1.0
 * @date：2020/4/6
 */
public class StackOverFlowErrorDemo {
    public static void main(String[] args) {
        //Exception in thread "main" java.lang.StackOverflowError
        method01();
    }

    private static void method01(){
        method01();
    }
}
