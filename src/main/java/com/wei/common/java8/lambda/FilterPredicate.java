package com.wei.common.java8.lambda;

//定义函数式接口
public interface FilterPredicate<T> {
    boolean filter(T t);
}