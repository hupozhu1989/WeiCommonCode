package com.wei.common.reflect;
 
import java.lang.reflect.Constructor;

public class TestConstructor {
 
    public static void main(String[] args) {
        Class stu = null;
        try {
            stu = Class.forName("com.wei.common.reflect.Student");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // 获取对象所有的公共构造方法
        Constructor[] constructors = stu.getConstructors();
        for (Constructor c : constructors) {
            System.out.println(c);
        }
        System.out.println("---------------------");
        // 获取对象所有的构造方法(包含私有的)
        Constructor[] declaredConstructors = stu.getDeclaredConstructors();
        for (Constructor con : declaredConstructors) {
            System.out.println(con);
        }
 
    }
}