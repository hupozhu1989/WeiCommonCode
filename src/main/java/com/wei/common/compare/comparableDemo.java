package com.wei.common.compare;

import java.util.Set;
import java.util.TreeMap;

/**
 * @author weizhenchao
 * @version 1.0
 * @date：2020/3/13
 */
public class comparableDemo {
    public static void main(String[] args) {
        TreeMap<Person, String> pdata = new TreeMap<Person, String>();
        pdata.put(new Person("张三", 30), "zhangsan");
        pdata.put(new Person("李四", 20), "lisi");
        pdata.put(new Person("王五", 10), "wangwu");
        pdata.put(new Person("⼩红", 5), "xiaohong");
        // 得到key的值的同时得到key所对应的值
        Set<Person> keys = pdata.keySet();
        for (Person key : keys) {
            System.out.println(key.getAge() + "-" + key.getName());
        }

    }
}

//person对象没有实现Comparable接⼝，所以必须实现，这样才不会出错，才可以使treemap中的数据按顺序排列
//前⾯⼀个例⼦的String类和Integer类已经默认实现了Comparable接⼝，详细可以查看String类的API⽂档
class Person implements Comparable<Person> {
    private String name;
    private int age;
    public Person(String name, int age) {
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
    public int compareTo(Person o) {
        if (this.age > o.getAge()) {
            return 1;
        } else if (this.age < o.getAge()) {
            return -1;
        }
        return age;
    }
}
