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

    /*
        Spring的@Bean注解用于告诉方法，产生一个Bean对象，然后这个Bean对象交给Spring管理。产生这个Bean对象的方法Spring只会调用一次，随后这个Spring
        将会将这个Bean对象放在自己的IOC容器中。SpringIOC 容器管理一个或者多个bean，这些bean都需要在@Configuration注解下进行创建，在一个方法上
        使用@Bean注解就表明这个方法需要交给Spring进行管理

        初始化方法的3种实现:
        ①initMethod: spring容器初始bean时，调用我们定义的初始化方法
        ②InitializationBean 接口有一个定义好的初始化方法-->void afterPropertiesSet() throws Exception
        ③@PostConstruct注解
        Spring不推荐使用InitializationBean 来调用其初始化方法，因为它不必要地将代码耦合到Spring。Spring推荐使用@PostConstruct注解
        或者为POJO类指定其初始化方法这两种方式来完成初始化。

        BenzCar类（奔驰汽车类）有成员属性Engine（发动机），Engine是接口，无具体的实现类。本代码例子，通过BeanFactoryPostProcessor和FactoryBean,
        动态代理三项技术实现给BenzCar装配上Engine
     */
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
