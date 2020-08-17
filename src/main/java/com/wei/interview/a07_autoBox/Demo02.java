package com.wei.interview.a07_autoBox;

/**
 * @author weizhenchao
 * @create 2020-08-17-15:41
 */
public class Demo02 {
    public static void main(String[] args) {
        /*
            Integer、Short、Byte、Character、Long这几个类的valueOf方法的实现是类似的。
            Double、Float的valueOf方法的实现是类似的。
         */
        Double i1 = 100.0;
        Double i2 = 100.0;
        Double i3 = 200.0;
        Double i4 = 200.0;

        System.out.println(i1 == i2);//false
        System.out.println(i3 == i4);//false
    }
}
