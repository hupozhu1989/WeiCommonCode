package com.wei;

import com.wei.spring.beanFactoryPostProcessor.BenzCar;
import com.wei.spring.beanFactoryPostProcessor.Engine;
import com.wei.spring.beanFactoryPostProcessor.SpecialBeanForEngine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @author weizhenchao
 * @versio 1.0
 * @date：2020/3/6 0006
 */
@SpringBootApplication
@EnableCaching//缓存
public class MyApplication {
    /*
        启动成功:
        Started MyApplication in 5.581 seconds (JVM running for 6.32)
        SpringBoot内置tomcat启动原理: https://www.cnblogs.com/sword-successful/p/11383723.html

        https://zhuanlan.zhihu.com/p/163685081  https://zhuanlan.zhihu.com/p/55637237
        @SpringBootApplication -> @EnableAutoConfiguration -> AutoConfigurationImportSelector.class -> selectImports() ->
            getCandidateConfigurations() -> loadFactoryNames() -> loadSpringFactories()
        自动化配置:spring-boot-autoconfigure包下META-INF/spring.factories
        这些注解都组合了@Conditional注解，只是使用了不同的条件组合最后为true时才会去实例化需要实例化的类，否则忽略过滤掉。
            @ConditionalOnBean：当容器里有指定Bean的条件下
            @ConditionalOnClass：当类路径下有指定的类的条件下
            @ConditionalOnExpression：基于SpEL表达式为true的时候作为判断条件才去实例化
            @ConditionalOnJava：基于JVM版本作为判断条件
            @ConditionalOnJndi：在JNDI存在的条件下查找指定的位置
            @ConditionalOnMissingBean：当容器里没有指定Bean的情况下
            @ConditionalOnMissingClass：当容器里没有指定类的情况下
            @ConditionalOnWebApplication：当前项目时Web项目的条件下
            @ConditionalOnNotWebApplication：当前项目不是Web项目的条件下
            @ConditionalOnProperty：指定的属性是否有指定的值
            @ConditionalOnResource：类路径是否有指定的值
            @ConditionalOnOnSingleCandidate：当指定Bean在容器中只有一个，或者有多个但是指定首选的Bean
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

        执行顺序如下:Bean的构造方法、@PostConstruct注解(Java提供)、InitializingBean接口、@Bean中initMethod属性
        https://www.cnblogs.com/april-chen/p/8182631.html

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
