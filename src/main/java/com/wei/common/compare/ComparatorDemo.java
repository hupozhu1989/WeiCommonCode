package com.wei.common.compare;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * @author weizhenchao
 * @version 1.0
 * @date：2020/3/13
 */
public class ComparatorDemo {
    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        arrayList.add(-1);
        arrayList.add(3);
        arrayList.add(3);
        arrayList.add(-5);
        arrayList.add(7);
        arrayList.add(4);
        arrayList.add(-9);
        arrayList.add(-7);
        System.out.println("原始数组:"+arrayList);
        // void reverse(List list)：反转
        Collections.reverse(arrayList);
        System.out.println("Collections.reverse(arrayList):"+arrayList);
        // void sort(List list),按⾃然排序的升序排序
        Collections.sort(arrayList);
        System.out.println("Collections.sort(arrayList):"+arrayList);
        // 定制排序的⽤法
        Collections.sort(arrayList, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        System.out.println("定制排序后："+arrayList);
    }
}
