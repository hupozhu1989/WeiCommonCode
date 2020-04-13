package com.wei.interview.equals06;

import com.wei.interview.passvalue03.Person;

import java.util.HashSet;
import java.util.Set;

/**
 * @author weizhenchao
 * @version 1.0
 * @date：2020/4/12
 */
public class Demo01 {
    public static void main(String[] args) {
        String s1 = new String("abc");
        String s2 = new String("abc");

        System.out.println(s1 == s2);
        System.out.println(s1.equals(s2));
        Set<String> set01 = new HashSet<>();
        set01.add(s1);
        set01.add(s2);
        System.out.println(set01.size());
        System.out.println("=========================");

        Person p1 = new Person("abc");
        Person p2 = new Person("abc");
        System.out.println(p1 == p2);
        System.out.println(p1.equals(p2));
        Set<Person> set02 = new HashSet<>();
        set02.add(p1);
        set02.add(p2);
        System.out.println(set02.size());

    }



}
