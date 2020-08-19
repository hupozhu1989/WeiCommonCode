package com.wei.interview.a07_autoBox;

/**
 * https://www.cnblogs.com/dolphin0520/p/3780005.html
 * 装箱就是  自动将基本数据类型转换为包装器类型；拆箱就是  自动将包装器类型转换为基本数据类型。
 * 装箱过程是通过调用包装器的valueOf方法实现的，而拆箱过程是通过调用包装器的 xxxValue方法实现的。（xxx代表对应的基本数据类型）
 * @author weizhenchao
 * @create 2020-08-17-15:36
 */
public class Demo01 {
    public static void main(String[] args) {
        Integer i1 = 100;
        Integer i2 = 100;
        Integer i3 = 200;
        Integer i4 = 200;

        System.out.println(i1 == i2);//true
        System.out.println(i3 == i4);//false
    }
}
