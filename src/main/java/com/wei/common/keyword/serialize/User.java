package com.wei.common.keyword.serialize;

import java.io.Serializable;

/**
 * @author weizhenchao
 * @version 1.0
 * @date：2020/3/20 0020
 */
public class User implements Serializable {
    private static final long serialVersionUID = 2635626465456l;
    private String name;
    private transient int age;
    private static String hobby;
    private static transient boolean gender;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public boolean isGender() {
        return gender;
    }
    public void setGender(boolean gender) {
        this.gender = gender;
    }
    public String getHobby() {
        return hobby;
    }
    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", hobby='" + hobby + '\'' +
                ", gender=" + gender +
                '}';
    }
}
