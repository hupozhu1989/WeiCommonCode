package com.wei.common.compare;

import java.text.Collator;
import java.util.*;

/**
 * @author weizhenchao
 * @version 1.0
 * @date：2020/3/13
 */
public class ComparatorDemo {
    public static void main(String[] args) {
        //Integer类型
        //method01();
        //pojo对象
        method02();
    }

    public static void method02() {
        List<Person2> list = new ArrayList<>();
        list.add(new Person2("张三", 15));
        list.add(new Person2("李四", 30));
        list.add(new Person2("王五", 28));
        list.add(new Person2("赵六", 25));
        list.add(new Person2("田七", 10));
        list.add(new Person2("孙八", 18));
        list.add(new Person2("白九", 12));
        System.out.println("===============原始==============");
        for (Person2 person : list) {
            System.out.println(person);
        }
        //年龄排序
        /*Collections.sort(list, new Comparator<Person2>() {
            @Override
            public int compare(Person2 o1, Person2 o2) {
                return o1.getAge() - o2.getAge();
            }
        });*/
        //姓名排序
        Collections.sort(list, new Comparator<Person2>() {
            Collator collator = Collator.getInstance(Locale.CHINA);
            @Override
            public int compare(Person2 o1, Person2 o2) {
                return collator.compare(o1.getName(),o2.getName());//升序
                //return collator.compare(o2.getName(),o1.getName());//降序
            }
        });
        System.out.println("===============排序后==============");
        for (Person2 person : list) {
            System.out.println(person);
        }
    }

    public static void method01() {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        arrayList.add(-1);
        arrayList.add(3);
        arrayList.add(2);
        arrayList.add(-5);
        arrayList.add(7);
        arrayList.add(4);
        arrayList.add(-9);
        arrayList.add(-7);
        System.out.println("原始数组:"+arrayList);

        // void reverse(List list)：反转
        Collections.reverse(arrayList);
        System.out.println("Collections.reverse(arrayList):"+arrayList);

        // void sort(List list)：按⾃然排序的升序排序
        Collections.sort(arrayList);
        System.out.println("Collections.sort(arrayList):"+arrayList);

        // 定制排序的⽤法
        Collections.sort(arrayList, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                //return o2.compareTo(o1);
                return o2 - o1;
            }
        });
        System.out.println("定制排序后："+arrayList);
    }
}

class Person2{
    private String name;
    private int age;
    public Person2(String name, int age) {
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

    @Override
    public String toString() {
        return "Person2{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}