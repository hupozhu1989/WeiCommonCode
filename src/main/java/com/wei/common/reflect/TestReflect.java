package com.wei.common.reflect;
 
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
 
/**
 * Created by Liuxd on 2018/8/14.
 */
public class TestReflect {
    @SuppressWarnings("unchecked")
    public static void main(String[] args)  {
        try {
            //获取class对象
            Class c = Class.forName("com.wei.common.reflect.Student");
            Student stu1 = (Student) c.newInstance();
 
            // 第一种方法，实例化默认构造方法，调用set赋值
            stu1.setAddress("深圳南山");
            System.out.println(stu1);
            System.out.println("=========================");
 
            // 第二种方法 取得全部的构造函数 使用构造函数赋值
            Constructor<Student> constructor = c.getConstructor(String.class, int.class, String.class, String.class);
            Student stu2 = (Student) constructor.newInstance("李四", 18, "七班", "深圳");
            System.out.println(stu2);
            System.out.println("=========================");
            /**
             * 獲取方法并执行方法
             */
            Method show = c.getMethod("showInfo");//获取showInfo()方法
            Object object = show.invoke(stu2);//调用showInfo()方法
            System.out.println(object);
 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}