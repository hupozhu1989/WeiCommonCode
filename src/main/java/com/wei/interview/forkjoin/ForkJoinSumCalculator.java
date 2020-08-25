package com.wei.interview.forkjoin;

import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

//继承RecursiveTask来创建可以用于分支/合并框架的任务
public class ForkJoinSumCalculator extends RecursiveTask<Long> {

    //要求和的数组
    private final long[] numbers;
    //子任务处理的数组的起始位置和终止位置
    private final int start;
    private final int end;

    //不在将任务分解为子任务的数组大小
    public static final long THRESHOLD = 10000;

    public ForkJoinSumCalculator(long[] numbers) {
        this(numbers, 0, numbers.length);
    }

    private ForkJoinSumCalculator(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        //该任务负责求和的部分的大小
        int length = end - start;
        //如果大小小于或等于阈值，顺序计算结果
        if (length <= THRESHOLD) {
            return computeSequentially();
        }
        //创建一个子任务来为数组的前一半求和
        ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(numbers, start, start + length/2);
        //利用另一个ForkJoinPool县城异步执行新创建的子任务
        leftTask.fork();
        //创建一盒任务为数组的后一半求和
        ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(numbers, start + length/2, end);
        //同步执行第二个子任务，有可能允许进一步递归划分
        Long rightResult = rightTask.compute();
        //读取第一个子任务的结果，如果尚未完成就等待
        Long leftResult = leftTask.join();
        //任务结果 是两个子任务结果的组合
        return rightResult + leftResult;
    }

    private Long computeSequentially() {
        long sum = 0;
        for (int i = start; i < end; i++ ) {
            sum += numbers[i];
        }
        return sum;
    }


    public static long forkJoinSum(long n){
        long[] numbers = LongStream.rangeClosed(0, n).toArray();
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
        return task.invoke();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            long start = System.currentTimeMillis();
            System.out.println(forkJoinSum(100000000));
            long end = System.currentTimeMillis();
            System.out.println(end - start + "mills");
        }
    }
}