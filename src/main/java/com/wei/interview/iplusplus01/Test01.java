package com.wei.interview.iplusplus01;

public class Test01 {
	public static void main(String[] args) {
		//i++是先赋值，然后再自增；++i是先自增，后赋值。
		int i = 1;
		i = i++;
		int j = i++;
		int k = i + ++i * i++;
		System.out.println("i=" + i);//4
		System.out.println("j=" + j);//1
		System.out.println("k=" + k);//11
		/*
			**局部变量表**	**操作数栈**
			赋值=，最后计算
			=右边的从左到右加载值依次压入操作数栈
			实际先算哪个，看运算符优先级
			自增、自减操作都是直接修改变量的值，不经过操作数栈
			最后的赋值之前，临时结果也是存储在操作数栈中
		 */
	}
}