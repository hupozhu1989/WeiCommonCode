package com.wei.interview.a07_autoBox;

/**
 * @author weizhenchao
 * @create 2020-08-17-15:44
 */
public class Demo03 {
    public static void main(String[] args) {
        Boolean i1 = false;
        Boolean i2 = false;
        Boolean i3 = true;
        Boolean i4 = true;

        System.out.println(i1 == i2);
        System.out.println(i3 == i4);
        
        Boolean b1= new Boolean(true);
        Boolean b2= new Boolean(true);
        System.out.println(b1 == b2);
    }
}
