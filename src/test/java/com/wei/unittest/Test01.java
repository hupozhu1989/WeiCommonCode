package com.wei.unittest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author weizhenchao
 * @version 1.0
 * @date：2020/3/6 0006
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class Test01 {

    @Test
    public void method01(){
        System.out.println("hello world");
    }
}
