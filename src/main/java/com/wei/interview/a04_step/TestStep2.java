package com.wei.interview.a04_step;

/**
 * 有n步台阶，一次只能上1步或2步，共有多少种走法？
 * 循环迭代
 */
public class TestStep2 {
	public static void main(String[] args) {
		int n = 40;
		long start = System.currentTimeMillis();
		System.out.println(loop(n));//165580141
		long end = System.currentTimeMillis();
		System.out.println(end-start);//<1ms

	}
	
	public static int loop(int n){
		if(n<1){
			throw new IllegalArgumentException(n + "不能小于1");
		}
		if(n==1 || n==2){
			return n;
		}
		int one = 2;//初始化为走到第二级台阶的走法
		int two = 1;//初始化为走到第一级台阶的走法
		int sum = 0;
		
		for(int i=3; i<=n; i++){
			//最后跨2步 + 最后跨1步的走法
			sum = two + one;
			two = one;
			one = sum;
		}
		return sum;
	}
}