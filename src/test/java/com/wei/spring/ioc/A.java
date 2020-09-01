package com.wei.spring.ioc;

/**
 * 循环依赖A
 */
public class A {
    private B b;

    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
    }
}
