package com.wei.spring.ioc;

/**
 * 循环依赖B
 */
public class B {
    private A a;

    public A getA() {
        return a;
    }

    public void setA(A a) {
        this.a = a;
    }
}
