package com.wei.common.java8.lambda;

//定义函数式接口的一个实现
public class ApplePredicate implements FilterPredicate<Apple> {
    @Override
    public boolean filter(Apple apple) {
        return apple.getColor().equalsIgnoreCase("red");
    }
}