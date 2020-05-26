package com.wei.common.algorithm;


import java.util.Arrays;

/**
 * 最长公共前缀
 * @author weizhenchao
 * @create 2020-05-25-下午 4:01
 */
public class Test02 {
    public static void main(String[] args) {
        String[] arr1 = {"flower","flow","flight","fat"};
        String a = null;
        String[] arr2 = {"flower","flow","flight",a};

        System.out.println("最长公共前缀："+method01(arr1));
    }

    public static String method01(String[] arr) {
        if (!checkStrs(arr)){
            return "";
        }
        printStrs(arr,"排序前>>>");
        Arrays.sort(arr);
        printStrs(arr,"排序后>>>");
        String m = arr[0];//第一个
        String n = arr[arr.length-1];//最后一个
        int minLength = Math.min(m.length(), n.length());
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < minLength; i++) {
            if (m.charAt(i) == n.charAt(i)){
                buffer.append(m.charAt(i));
            }else {
                break;
            }
        }
        return buffer.toString();
    }

    public static boolean checkStrs(String[] arr){
        if (arr == null){
            return false;
        }
        for (String s : arr) {
            if (s == null || s.length() == 0){
                return false;
            }
        }
        return true;
    }

    public static void printStrs(String[] arr,String notice){
        StringBuffer buffer = new StringBuffer(notice);
        for (String s : arr) {
            buffer.append(s).append("...");
        }
        System.out.println(buffer.toString());
    }
}
