package com.itbaima.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 此代码用于演示配置类代替配置文件xml
 */

@Configuration //表明这是一个注解类
@ComponentScan("com.itbaima") //用于扫描哪些使用了注解
@EnableAspectJAutoProxy //替代自动注册标签、<aop:aspectj-autoproxy/>
public class SpringConfig {
}
