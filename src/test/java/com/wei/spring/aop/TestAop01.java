package com.wei.spring.aop;

import com.wei.spring.aop.service.CalService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.SpringVersion;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author weizhenchao
 * @version 1.0
 * @date：2021/1/3
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestAop01 {

    @Autowired
    private CalService calService;

    @Test
    public void testAop4(){
        /*
            Spring4 + SpringBoot1.5.9
            @SpringBootTest
         */
        System.out.println("spring版本："+ SpringVersion.getVersion()+"\t"+"springboot版本："+ SpringBootVersion.getVersion());
        calService.div(10,2);
    }
}
