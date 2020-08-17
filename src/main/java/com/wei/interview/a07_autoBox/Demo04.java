package com.wei.interview.a07_autoBox;

/**
 * @author weizhenchao
 * @create 2020-08-17-15:48
 */
public class Demo04 {
    public static void main(String[] args) {
        /*
            当"=="运算符的两个操作数都是 包装器类型的引用，则是比较指向的是否是同一个对象，
            而如果其中有一个操作数是表达式（即包含算术运算）则比较的是数值（即会触发自动拆箱的过程）。
            另外，对于包装器类型，equals方法并不会进行类型转换,会拆箱。
         */
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Long g = 3L;
        Long h = 2L;

        System.out.println(c==d);//true
        System.out.println(e==f);//false
        System.out.println(c==(a+b));//true
        System.out.println(c.equals(a+b));//true 自动拆箱
        System.out.println(g==(a+b));//true 3l==3  int会自动进行类型提升为long类型
        System.out.println(g.equals(a+b));//false
        System.out.println(g.equals(a+h));//true
    }
}
