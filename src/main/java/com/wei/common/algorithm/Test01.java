package com.wei.common.algorithm;


/**
 * 字符串空格替换成 "%20"
 * @author weizhenchao
 * @version 1.0
 * @date：2020/3/12
 */
public class Test01 {
    public static void main(String[] args) {
        String str = " hello world ";
        System.out.println(method01(str));
        System.out.println("======================");
        System.out.println(method02(str));
    }

    /**
     * 方法一
     */
    public static String method01(String str){
        int length = str.length();
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < length; i++) {
            char c = str.charAt(i);
            if (String.valueOf(c).equals(" ")){
                buffer.append("%20");
            }else {
                buffer.append(c);
            }
        }
        return buffer.toString();
    }

    /**
     * 方法二
     */
    public static String method02(String str){
        return str.replaceAll(" ","%20");
    }

}
