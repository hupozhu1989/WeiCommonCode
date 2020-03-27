package com.wei.interview.cas;

import lombok.*;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 原子引用
 * @author weizhenchao
 * @version 1.0
 * @date：2020/3/27
 */
public class AtomicReferenceDemo {
    public static void main(String[] args) {
        User z3 = new User("z3",22);
        User li4 = new User("li4",25);

        AtomicReference<User> atomicReference = new AtomicReference<>();
        atomicReference.set(z3);
        System.out.println(atomicReference.compareAndSet(z3,li4)+"\t"+atomicReference.get().toString());
        System.out.println(atomicReference.compareAndSet(z3,li4)+"\t"+atomicReference.get().toString());
    }
}

@ToString
@AllArgsConstructor
@Getter
@Setter
class User{
    String userName;
    int age;
}
