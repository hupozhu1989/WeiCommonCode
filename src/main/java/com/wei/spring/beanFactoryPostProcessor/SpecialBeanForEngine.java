package com.wei.spring.beanFactoryPostProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.cglib.proxy.InvocationHandler;
import org.springframework.cglib.proxy.Proxy;

import java.lang.reflect.Method;

public class SpecialBeanForEngine implements BeanFactoryPostProcessor, BeanNameAware {
    
    String name;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinitionRegistry bdr = (BeanDefinitionRegistry)beanFactory;
        GenericBeanDefinition gbd = new GenericBeanDefinition();
        //添加 EngineFactory.class类的Bean
        gbd.setBeanClass(EngineFactory.class);
        gbd.setScope(BeanDefinition.SCOPE_SINGLETON);
        gbd.setAutowireCandidate(true);
        bdr.registerBeanDefinition("engine01-gbd", gbd);
    }
    
    public static class EngineFactory implements FactoryBean<Engine>, BeanNameAware, InvocationHandler {
        String name;
        
        @Override
        public Engine getObject() throws Exception {
            System.err.println("EngineFactory  to build Engine01 , EngineFactory :"+ name);
            //使用动态代理生产Engine接口的代理对象
            Engine prox = (Engine) Proxy.newProxyInstance(this.getClass().getClassLoader(),new Class[]{Engine.class}, this);
            return prox;
        }

        @Override
        public Class<?> getObjectType() {
            return Engine.class;
        }

        @Override
        public boolean isSingleton() {
            return true;
        }

        @Override
        public void setBeanName(String name) {
            this.name = name;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.err.println("here is invoke  engine:"+method.getName());
            return null;
        }
    }

    @Override
    public void setBeanName(String name) {
        this.name =name;
    }
}