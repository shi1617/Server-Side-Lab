package com.stu.hellosever.controller;

import com.stu.hellosever.common.Result;
import org.springframework.web.bind.annotation.*;

// 模拟 User 实体类（如果你的项目中有实体类，替换成自己的即可）
class User {
    private Long id;
    private String username;
    private Integer age;

    // 空参构造（Spring 解析 JSON 需要）
    public User() {}

    // 全参构造
    public User(Long id, String username, Integer age) {
        this.id = id;
        this.username = username;
        this.age = age;
    }

    // Getter & Setter（必须，否则 JSON 序列化/反序列化会空）
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }
}

@RestController
@RequestMapping("/api/users")
public class UserController {

    // 1. 登录接口（修复 500 异常，返回正常数据）
    @GetMapping("/login")
    public Result<String> login() {
        // 模拟登录成功，生成 Token（无业务异常，避免 500）
        return Result.success("登录成功，生成Token：test-token-123456");
    }

    // 2. 查询用户（GET，精细化放行，返回非 null 数据）
    @GetMapping("/{id}")
    public Result<User> getUserById(@PathVariable Long id) {
        // 模拟查询到用户数据，避免返回 null
        User user = new User(id, "测试用户" + id, 20 + id.intValue());
        return Result.success(user);
    }

    // 3. 新增用户（POST，放行，接收参数避免 null）
    @PostMapping
    public Result<String> createUser(@RequestBody User user) {
        // 处理参数为空的情况，避免返回 "null"
        String username = user.getUsername() == null ? "默认用户" : user.getUsername();
        Integer age = user.getAge() == null ? 18 : user.getAge();
        return Result.success("新增成功，用户名称：" + username + "，年龄：" + age);
    }

    // 4. 更新用户（PUT，需 Token 校验）
    @PutMapping("/{id}")
    public Result<String> updateUser(@PathVariable Long id, @RequestBody User user) {
        String username = user.getUsername() == null ? "默认用户" : user.getUsername();
        return Result.success("更新成功，ID为" + id + "的用户已修改为：" + username);
    }

    // 5. 删除用户（DELETE，需 Token 校验）
    @DeleteMapping("/{id}")
    public Result<String> deleteUser(@PathVariable Long id) {
        return Result.success("删除成功，ID为" + id + "的用户已移除");
    }
}