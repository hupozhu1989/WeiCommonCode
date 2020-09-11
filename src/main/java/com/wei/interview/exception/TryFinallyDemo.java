package com.wei.interview.exception;

/**
 * @author weizhenchao
 * @version 1.0
 * @date：2020/9/10
 */
public class TryFinallyDemo {
    public static void main(String[] args) {
        int a = getA();
        System.out.println(a);
    }

    public static int getA() {
        int a = 0;
        try {
            a = a+1;
            return a;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            a = a+2;
            return a;
        }
    }
}
