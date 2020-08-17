package com.wei.interview.a06_equals;

/**
 * @author weizhenchao
 * @create 2020-04-13-上午 9:18
 */
public class Demo02 {
    public static void main(String[] args) {
        String s1 = "abc";
        String s2 = new String("abc");
        String s3 = "abc";
        String s4 = "xxx";
        String s5 = "abc" + "xxx";
        String s6 = s3 + s4;

        System.out.println(s1 == s2);//false
        System.out.println(s1 == s2.intern());//true
        System.out.println(s1 == s3);//true
        System.out.println(s5 == s6);//false
        System.out.println(s5 == s6.intern());//true
        System.out.println(s6 == s6.intern());//false
    }
}
