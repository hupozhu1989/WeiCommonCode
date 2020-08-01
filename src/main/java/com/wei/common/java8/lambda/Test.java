package com.wei.common.java8.lambda;

import java.util.Arrays;
import java.util.List;

public class Test {
public static void main(String[] args) {
    FHFilter fhFilter = new FHFilter();
    List<Apple> appleList = Arrays.asList(new Apple("red", "1", 110), new Apple("yellow", "2", 220), new Apple("red", "3", 330));

    // 筛选
    System.out.println("+++++++++++++ 行为参数化 ++++++++++++++");
    List<Apple> myApple = fhFilter.filter(appleList, new ApplePredicate());
    System.out.println(myApple.toString());

    System.out.println("+++++++++++++ lambda ++++++++++++++");
    List<Apple> myApple2 = fhFilter.filter(appleList, (Apple apple) -> apple.getColor().equalsIgnoreCase("yellow"));
    System.out.println(myApple2.toString());
    }
}