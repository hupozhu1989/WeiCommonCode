package com.wei.interview.a06_equals;

/**
 * @author weizhenchao
 * @version 1.0
 * @date：2020/12/27
 */
public class Demo03 {
    public static void main(String[] args) {
        //https://www.cnblogs.com/wangshen31/p/10404353.html
        String str1 = new StringBuilder("mei").append("tuan").toString();
        System.out.println(str1);
        //当调用 intern() 方法时，编译器会将字符串添加到常量池中（stringTable维护），
        //并返回指向该常量的引用
        System.out.println(str1.intern());
        System.out.println(str1 == str1.intern());
        System.out.println("=====================");
        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2);
        System.out.println(str2.intern());
        System.out.println(str2 == str2.intern());

    }
}
