package com.wei.common.reflect;
 
import java.lang.reflect.Method;

public class TestMethod {
 
    public static void main(String[] args) {
        Class stu = null;
        try {
            stu = Class.forName("com.wei.common.reflect.Student");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // 获取对象的所有公共方法,包括继承的
        Method[] methods = stu.getMethods();
        for (Method m : methods) {
            System.out.println(m);
        }
        System.out.println("---------------------");
        // 获取对象所有方法(包括私有的)，但不包含继承的
        Method[] declaredMethods = stu.getDeclaredMethods();
        for (Method ms : declaredMethods) {
            System.out.println(ms);
        }
 
    }
}