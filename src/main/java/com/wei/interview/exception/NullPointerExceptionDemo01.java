package com.wei.interview.exception;

/**
 * @author weizhenchao
 * @version 1.0
 * @date：2020/7/25
 */
public class NullPointerExceptionDemo01 {
    public static void main(String[] args) {
        Person1 p1 = new Person1();
        p1.setName("小李");

        Person2 p2 = new Person2();
        p2.setAge(p1.getAge());//java.lang.NullPointerException
        System.out.println("结束");
        /*
            自动装箱  Integer total = Integer.valueOf(99);
            自动拆箱  int prim = total.intValue();

         */
    }
}

class Person1{
    private Integer age;
    private String name;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class Person2{
    private int age;
    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}