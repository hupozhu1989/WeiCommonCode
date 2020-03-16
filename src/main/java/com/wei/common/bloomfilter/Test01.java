package com.wei.common.bloomfilter;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

/**
 * @author weizhenchao
 * @version 1.0
 * @date：2020/3/16
 */
public class Test01 {
    public static void main(String[] args) {
        //method01();
        method02();
    }

    /**
     * 自定义布隆过滤器
     */
    public static void method01(){
        Integer value1 = 13423;
        Integer value2 = 22131;
        MyBloomFilter filter = new MyBloomFilter();
        System.out.println(filter.contains(value1));
        System.out.println(filter.contains(value2));
        filter.add(value1);
        filter.add(value2);
        System.out.println(filter.contains(value1));
        System.out.println(filter.contains(value2));
    }

    /**
     * 使用Guava中自带的布隆过滤器
     */
    public static void method02(){
        Integer value1 = 13423;
        Integer value2 = 22131;
        //创建布隆过滤器对象
        BloomFilter<Integer> filter = BloomFilter.create(
                Funnels.integerFunnel(),
                1500,
                0.01);
        // 判断指定元素是否存在
        System.out.println(filter.mightContain(value1));
        System.out.println(filter.mightContain(value2));
        // 将元素添加进布隆过滤器
        filter.put(value1);
        filter.put(value2);
        System.out.println(filter.mightContain(value1));
        System.out.println(filter.mightContain(value2));
    }
}
