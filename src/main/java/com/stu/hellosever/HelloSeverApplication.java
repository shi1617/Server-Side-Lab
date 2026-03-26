package com.stu.hellosever;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类：实验要求添加@MapperScan扫描mapper包，批量注册Mapper接口
 */
@SpringBootApplication
@MapperScan("com.stu.hellosever.mapper") // 匹配项目mapper包路径，必须指定
public class HelloSeverApplication {
    public static void main(String[] args) {
        SpringApplication.run(HelloSeverApplication.class, args);
    }
}