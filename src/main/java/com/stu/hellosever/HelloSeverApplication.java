package com.stu.hellosever;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 项目启动类
 * 路径：src/main/java/com/stu/helloserver/HelloServerApplication.java
 */
@SpringBootApplication // 开启Spring Boot自动配置、组件扫描
public class HelloSeverApplication {

    // 主方法：启动内嵌Tomcat服务器
    public static void main(String[] args) {
        SpringApplication.run(HelloSeverApplication.class, args);
    }
}