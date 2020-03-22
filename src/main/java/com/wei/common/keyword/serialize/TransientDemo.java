package com.wei.common.keyword.serialize;

import java.io.*;

/**
 * @author weizhenchao
 * @version 1.0
 * @date：2020/3/20 0020
 */
public class TransientDemo {
    public static void main(String[] args) throws Exception{
        /**
         * transient的作用就是把这个字段的生命周期仅存于调用者的内存中而不会写到磁盘里持久化
         * 静态变量不管是不是transient关键字修饰，都不会被序列化,静态变量存在方法区中,JVM查找静态变量的值，从方法区查找
         * java的transient关键字为我们提供了便利，你只需要实现Serilizable接口，将不需要序列化的属性前添加关键字transient，序列化对象的时候，这个属性就不会序列化到指定的目的地中
         */
        serializeUser();
    }

    /**
     * 序列化
     */
    public static void serializeUser() throws Exception {
        User user = new User();
        user.setName("张三");
        user.setAge(24);
        user.setHobby("弹钢琴");
        user.setGender(true);
        File file = new File("F:\\transient");
        //序列化
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
        oos.writeObject(user);
        oos.close();
        System.out.println("序列化:"+user.toString());
        //改对象属性
        user.setName("李四");
        user.setAge(26);
        user.setHobby("骑马");
        user.setGender(false);
        System.out.println("修改:"+user.toString());
        //反序列化
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        User modifyUser = (User)ois.readObject();
        ois.close();
        System.out.println("反序列化:"+modifyUser.toString());
    }

}
