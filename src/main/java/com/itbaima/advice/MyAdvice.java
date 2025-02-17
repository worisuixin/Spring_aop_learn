package com.itbaima.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


@Component
@Aspect   //写上这两个注解就可以使用注解开发，第一个是将其注入，这个是表明这个是aop且这个是用于增强的类
public class MyAdvice {

    @Before("execution(* com.itbaima.service.impl.*.*(..))")  //这个注解就表明这个是前置通知。类似注解aop的那个前置标签
    public void beforeAdvice() {
        System.out.println("前置的增强beforeAdvice");
    }
    public void afterReturningAdvice() {
        System.out.println("后置的增强afterReturningAdvice");
    }
    public void afterThrowingAdvice() {
        System.out.println("异常抛出通知增强afterThrowingAdvice"); //报异常才会执行
    }
    public void afterAdvice() {
        System.out.println("最终的增强afterAdvice");
    }
    public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("环绕前增强aroundAdvice");
        Object proceed = pjp.proceed(); //这个就是切点的那个方法，上下就是用来增强的方法
        System.out.println("环绕后增强aroundAdvice");
        return proceed;
    }
//    不过上面的方法名字到底是什么并不重要，因为最终是通过xml配置，指明哪一个是什么类型
}
