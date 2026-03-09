package com.stu.hellosever;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RESTful API 测试控制器
 * 路径：src/main/java/com/stu/helloserver/HelloController.java
 */
@RestController // 标记为REST控制器，返回数据而非视图
public class HelloController {

    /**
     * 测试接口：GET请求，路径/hello
     * 访问地址：http://localhost:8080/hello
     */
    @GetMapping("/hello")
    public String helloApi() {
        // 自定义返回内容，可根据需求修改
        return "Hello RESTful API! 接口测试成功！";
    }
}