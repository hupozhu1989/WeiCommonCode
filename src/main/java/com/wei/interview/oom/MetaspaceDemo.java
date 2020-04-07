package com.wei.interview.oom;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author weizhenchao
 * @version 1.0
 * @date：2020/4/7
 */
public class MetaspaceDemo {
    /*
        参数配置: -XX:MetaspaceSize=8m -XX:MaxMetaspaceSize=8m -XX:+PrintGCDetails
        故障现象: Caused by: java.lang.OutOfMemoryError: Metaspace

        Java8及之后的版本使用Metaspace来替代永久代
        模拟Meatspace空间溢出,不断生成类往元空间灌,类占据的空间总是会超过Meatspace指定的空间大小
     */
    static class OOMTest{}
    public static void main(String[] args) {
        int i = 0;//模拟计数多少次以后发生异常
        try {
            while (true){
                i++;
                Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(OOMTest.class);
                enhancer.setUseCache(false);
                enhancer.setCallback(new MethodInterceptor() {
                    @Override
                    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                        return methodProxy.invokeSuper(o,args);
                    }
                });
                enhancer.create();
            }
        }catch (Throwable e){
            System.out.println("********多少次以后发生了异常: " +i);
            e.printStackTrace();
        }

    }
}
