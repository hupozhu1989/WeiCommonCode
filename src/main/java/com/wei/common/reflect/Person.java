package com.wei.common.reflect;

public class Person {
    public String name;// 姓名
    public int age;// 年龄
 
    public Person() {
        super();
    }
 
    public Person(String name, int age) {
        super();
        this.name = name;
        this.age = age;
    }
 
    public String showInfo() {
        return "name=" + name + ", age=" + age;
    }
}