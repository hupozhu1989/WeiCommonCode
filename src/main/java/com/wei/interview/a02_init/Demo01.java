package com.wei.interview.a02_init;

/**
 * @author weizhenchao
 * @create 2020-04-13-下午 3:43
 */
public class Demo01 {
    static {
        System.out.println("88888");
    }
    
    public static void main(String[] args) {
        System.out.println("77777");
        new Zi();
        System.out.println("==================");
        new Zi();
        System.out.println("==================");
        new Fu();
    }
}
class Fu{
    public Fu() {
        System.out.println("11111");
    }
    {
        System.out.println("22222");
    }
    static {
        System.out.println("33333");
    }
}
class Zi extends Fu{
    public Zi() {
        System.out.println("44444");
    }
    {
        System.out.println("55555");
    }
    static {
        System.out.println("66666");
    }
}
