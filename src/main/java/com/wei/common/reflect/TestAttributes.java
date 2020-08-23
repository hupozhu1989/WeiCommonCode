package com.wei.common.reflect;
 
import java.lang.reflect.Field;

public class TestAttributes {
    //https://blog.csdn.net/jiahao1186/article/details/81676281
    public static void main(String[] args) {
        Class stu = null;
        try {
            stu = Class.forName("com.wei.common.reflect.Student");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
 
        // 获取对象的所有公有属性，包括继承的。
        Field[] fields = stu.getFields();
        for (Field f : fields) {
            System.out.println(f);
        }
        System.out.println("---------------------");
        // 获取对象所有属性（包含私有），但不包含继承的。
        Field[] declaredFields = stu.getDeclaredFields();
        for (Field ff : declaredFields) {
            System.out.println(ff);
        }
 
    }
}