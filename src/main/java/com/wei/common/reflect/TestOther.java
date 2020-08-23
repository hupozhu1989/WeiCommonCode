package com.wei.common.reflect;

public class TestOther {
 
    public static void main(String[] args) {
        Class stu = null;
        try {
            stu = Class.forName("com.wei.common.reflect.Student");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(stu.getName());//获取对象全限定名称
        System.out.println(stu.getPackage());// 获取包名
        Class[] interfaces = stu.getInterfaces();//获取该类实现的所有接口
        for (Class in : interfaces) {
            System.out.println(in);
        }
 
    }
}