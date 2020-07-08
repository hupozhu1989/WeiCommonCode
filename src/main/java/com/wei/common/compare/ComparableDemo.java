package com.wei.common.compare;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author weizhenchao
 * @version 1.0
 * @date：2020/3/13
 */
public class ComparableDemo {
    public static void main(String[] args) {
        List<Person1> list = new ArrayList<>();
        list.add(new Person1("张三", 30));
        list.add(new Person1("王五", 10));
        list.add(new Person1("李四", 20));
        list.add(new Person1("⼩红", 5));
        //遍历
        for (Person1 person : list) {
            System.out.println(person);
        }
        System.out.println("========================");
        TreeMap<Person1, String> pdata = new TreeMap<Person1, String>();
        pdata.put(new Person1("张三", 30), "zhangsan");
        pdata.put(new Person1("李四", 20), "lisi");
        pdata.put(new Person1("王五", 10), "wangwu");
        pdata.put(new Person1("⼩红", 5), "xiaohong");
        // 得到key的值的同时得到key所对应的值
        Set<Person1> keys = pdata.keySet();
        for (Person1 person : keys) {
            System.out.println(person.getAge() + "-" + person.getName());
        }

    }
}

//person对象没有实现Comparable接⼝，所以必须实现，这样才不会出错，才可以使treemap中的数据按顺序排列
//String类和Integer类已经默认实现了Comparable接⼝，详细可以查看String类的API⽂档
class Person1 implements Comparable<Person1> {
    private String name;
    private int age;
    public Person1(String name, int age) {
        super();
        this.name = name;
        this.age = age;
    }
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
    /**
     * 重写compareTo⽅法实现按年龄来排序
     */
    @Override
    public int compareTo(Person1 o) {
        if (this.age > o.getAge()) {
            return 1;
        } else if (this.age < o.getAge()) {
            return -1;
        }
        return age;
    }

    @Override
    public String toString() {
        return "Person1{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
