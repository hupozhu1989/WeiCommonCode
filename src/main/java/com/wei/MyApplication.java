package com.wei;

import com.wei.spring.beanFactoryPostProcessor.BenzCar;
import com.wei.spring.beanFactoryPostProcessor.Engine;
import com.wei.spring.beanFactoryPostProcessor.SpecialBeanForEngine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @author weizhenchao
 * @versio 1.0
 * @date：2020/3/6 0006
 */
@SpringBootApplication
public class MyApplication {
    /*
        启动成功:
        Started MyApplication in 5.581 seconds (JVM running for 6.32)
     */
    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate(new OkHttp3ClientHttpRequestFactory());
    }

    //BenzCar类（奔驰汽车类）有成员属性Engine（发动机），Engine是接口，无具体的实现类。本代码例子，通过BeanFactoryPostProcessor，
    //FactoryBean,动态代理三项技术实现给BenzCar装配上Engine
    @Bean(initMethod = "start")
    BenzCar benzCar(Engine engine) {
        System.err.println("BenzCar bean name :benzCar");
        BenzCar car = new BenzCar();
        car.engine = engine;
        return car;
    }
    @Bean
    SpecialBeanForEngine specialBeanForEngine(){
        System.err.println("SpecialBeanForEngine bean name :specialBeanForEngine");
        return new SpecialBeanForEngine();
    }
}
