package com.wei.interview.a03_passvalue;

import java.util.Arrays;

public class Demo01 {
	/*
		①形参是基本数据类型
			传递数据值
		②行参是引用数据类型
			传递地址值
			特俗的类型：String、包装类等对象的不可变性
		栈管运行 堆管存储
		java中只有值传递:
			一个方法不能修改一个基本数据类型的参数（即数值型或布尔型）。
			一个方法可以改变一个对象参数的状态。
			一个方法不能让对象参数引用一个新的对象。
	 */
	public static void main(String[] args) {
		int i = 1;
		String str = "hello";
		Integer num = 200;
		int[] arr = {1,2,3,4,5};
		MyData my = new MyData();
		
		change(i,str,num,arr,my);
		
		System.out.println("i = " + i);
		System.out.println("str = " + str);
		System.out.println("num = " + num);
		System.out.println("arr = " + Arrays.toString(arr));
		System.out.println("my.a = " + my.a);
	}
	public static void change(int j, String s, Integer n, int[] a,MyData m){
		j += 1;
		s += "world";
		n += 1;
		a[0] += 1;
		m.a += 1;
	}
}

class MyData{
	int a = 10;
}