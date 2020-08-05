package com.wei.spring.beanFactoryPostProcessor;

import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;

/*
    initMethod、afterPropertiesSet()、@PostConstruct三选一即可
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
    
    void start(){
        System.err.println("BenzCar start");
        engine.fire();
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

}