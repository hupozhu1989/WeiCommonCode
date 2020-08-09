package com.wei.spring.beanFactoryPostProcessor;

import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;

/*
    @PostConstruct、afterPropertiesSet()、initMethod三选一即可，先后顺序
 */
public class BenzCar implements InitializingBean {
    public Engine engine;
    
    public BenzCar(){
        System.err.println("BenzCar Constructor");
        if(engine==null){
            System.err.println("BenzCar's engine not setting");
        }else{
            System.err.println("BenzCar's engine installed");
        }
    }

    @PostConstruct
    public void postConstruct(){
        System.err.println("BenzCar postConstruct");
        if(engine==null){
            System.err.println("BenzCar's engine not setting, in postConstruct");
        }else{
            System.err.println("BenzCar's engine installed, in postConstruct");
            engine.fire();
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.err.println("BenzCar initializingBean after propertieSet");
        if(engine==null){
            System.err.println("BenzCar's engine not setting, in initializingBean ");
        }else{
            System.err.println("BenzCar's engine installed, in initializingBean");
            engine.fire();
        }
    }

    void start(){
        System.err.println("BenzCar start");
        engine.fire();
    }

}