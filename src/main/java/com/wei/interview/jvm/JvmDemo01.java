package com.wei.interview.jvm;

/**
 * @author weizhenchao
 * @version 1.0
 * @date：2020/4/2
 */
public class JvmDemo01 {
    public static void main(String[] args) throws Exception{
        /*System.out.println("hello GC");
        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);*/

        countMemory();
    }

    private static void countMemory() {
        long totalMemory = Runtime.getRuntime().totalMemory();//返回Java虚拟机中的内存总量
        long maxMemory = Runtime.getRuntime().maxMemory();//返回Java虚拟机试图使用的最大内存量
        System.out.println("TOTAL_MEMORY(-Xms) = "+totalMemory+"字节,"+(totalMemory/(double)1024/1024)+"MB");
        System.out.println("MAX_MEMORY(-Xmx) = "+maxMemory+"字节,"+(maxMemory/(double)1024/1024)+"MB");
    }

    /*
        JVM体系结构:
            线程共享:方法区    堆
            线程独占:虚拟机栈   本地方法栈   程序计数器
        GC作用的区域:堆和方法区
        常见的垃圾回收算法:
            引用计数法
            根可达分析法
            复制
            标记清楚
            标记整理

        垃圾:内存中已经不再被使用到的空间就是垃圾
        如何判断一个对象是否可以被回收:
            1.引用计数法:给对象添加一个引用计数器,每当有一个地方引用它,计数器值加1,每当有一个引用失效时,计数器值减1。
            缺点:对象之间相互循环引用的问题
            2.根可达分析法:通过"GC Roots"的对象作为起始点,开始向下搜索,如果一个对象到GC Roots没有任何引用链相连时,则说明此对象不可用
        Java可以做GCRoots的对象:
            虚拟机栈(栈帧)中的局部变量区,也叫做局部变量表
            方法区中的类静态属性引用的对象
            方法区中常量引用的对象
            本地方法栈中(Native方法)引用的对象

        JVM的参数类型
            标配参数    -version    -help
            X参数(了解)
                -Xint       解释执行
                -Xcomp      第一次使用就编译成本地代码
                -Xmixed     混合模式
            XX参数
                Boolean类型
                    公式:
                        -XX:+或者-某个属性值
                        +表示开启   -表示关闭
                    Case:
                        是否打印GC收集细节
                            -XX:+PrintGCDetails
                            -XX:-PrintGCDetails
                        是否使用串行垃圾收集器
                            -XX:-UseSerialGC
                            -XX:+UseSerialGC
                KV设值类型
                    公式:
                        -XX:属性key=属性值value
                    Case
                        -XX:MetaspaceSize=128m
                        -XX:MaxTenuringThreshold=15
        查看java的进程,并输出完全的包名      jps -l
        jinfo举例，如何查看当前运行程序的配置
            公式:
                jinfo -flag 配置项 进程编号
            Case
                jinfo -flags 8372
                jinfo -flag InitialHeapSize 8372
                jinfo -flag UseSerialGC 8372
                jinfo -flag UseParallelGC 8372
                jinfo -flag MaxHeapSize 8372
        两个经典参数:
            -Xms    等价于 -XX:InitialHeapSize
            -Xmx    等价于 -XX:MaxHeapSize

        查看JVM默认值
            查看初始默认值,公式:
                java -XX:+PrintFlagsInitial -version
                java -XX:+PrintFlagsInitial
            主要查看修改更新,公式:
                java -XX:+PirntFlagsFinal
                java -XX:+PirntFlagsFinal -version
            java -XX:+PrintCommandLineFlags




     */
}
