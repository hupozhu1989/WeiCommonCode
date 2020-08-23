package com.wei.common.reflect;

public class Student extends Person implements Study {
    public String className;// 班级
    private String address;// 住址
 
    public Student() {
        super();
    }
 
    public Student(String name, int age, String className, String address) {
        super(name, age);
        this.className = className;
        this.address = address;
    }

    public Student(String className) {
        this.className = className;
    }
 
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String toString() {
        return "姓名：" + name + ",年龄：" + age + ",班级：" + className + ",住址："
                + address;
    }

    private void sayHello(){

    }

}