package com.itbaima;

import com.itbaima.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = context.getBean(UserService.class);
        userService.show1();


//        用于演示注解aop使用
        ApplicationContext context2 = new ClassPathXmlApplicationContext("applicationContext2.xml");
        UserService userService2 = context2.getBean(UserService.class);
        userService2.show1();
    }
}
