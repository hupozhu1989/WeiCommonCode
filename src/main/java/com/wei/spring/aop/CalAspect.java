package com.wei.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author weizhenchao
 * @version 1.0
 * @date：2021/1/3
 */
@Aspect
@Component
public class CalAspect {
    /*
        Spring4 + SpringBoot1.5.9
        Spring5 + SpringBoot2.3.3
     */
    @Before("execution(public int com.wei.spring.aop.service.impl.CalServiceImpl.*(..))")
    public void beforeNotify(){
        System.out.println("****** @Before我是前置通知");
    }

    @After("execution(public int com.wei.spring.aop.service.impl.CalServiceImpl.*(..))")
    public void afterNotify(){
        System.out.println("****** @After我是后置通知");
    }

    @AfterReturning("execution(public int com.wei.spring.aop.service.impl.CalServiceImpl.*(..))")
    public void afterReturningNotify(){
        System.out.println("****** @AfterReturning我是返回后通知");
    }

    @AfterThrowing("execution(public int com.wei.spring.aop.service.impl.CalServiceImpl.*(..))")
    public void afterThrowingNotify(){
        System.out.println("****** @AfterThrowing我是异常通知");
    }

    @Around("execution(public int com.wei.spring.aop.service.impl.CalServiceImpl.*(..))")
    public Object aroundNotify(ProceedingJoinPoint joinPoint) throws Throwable {
        Object retValue = null;
        System.out.println("****** @Around我是环绕通知之前AA");
        retValue = joinPoint.proceed();
        System.out.println("****** @Around我是环绕通知之后BB");
        return retValue;
    }

}
