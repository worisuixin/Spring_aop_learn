package com.itbaima.processor;

import com.itbaima.advice.MyAdvice;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MockAopBeanPostProcessor implements BeanPostProcessor, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//        目的：对UserServiceImpl中的show1和show2方法进行增强，增强的方法在Myadvice中 ，需要userServiceimpl的代理对象
//        问题1：筛选，增强哪些bean？具体的类还是完整的包。  if-else判断
//        问题2：Myadvice怎么获取？  从容器中获得Myadvice
        if(bean.getClass().getPackage().getName().equals("com.itbaima.service.impl")){
//            生成当前Bean的Proxy对象
            Object beanProxy = Proxy.newProxyInstance(
                    bean.getClass().getClassLoader(),
                    bean.getClass().getInterfaces(),
                    new InvocationHandler() {
                        @Override
                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                            执行增强对象的before方法
                            MyAdvice myAdvice = applicationContext.getBean(MyAdvice.class);
                            myAdvice.beforeAdvice();
//                            执行目标对象的目标方法
                            Object result = method.invoke(bean, args);
//                            执行增强对象的after方法
                            myAdvice.afterAdvice();
                            return result;
                        }
                    }
            );
            return beanProxy;  //需要增强，则在这个if里面进行增强，并返回增强的proxy
        }
        return bean; //不需要增强，则直接原封不动地返回
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
