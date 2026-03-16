package com.stu.hellosever.controller;

import com.stu.hellosever.common.Result;
import com.stu.hellosever.entity.User;
import org.springframework.web.bind.annotation.*;

/**
 * 用户RESTful接口控制器
 * 提供GET/POST/PUT/DELETE四大接口
 */
@RestController // 标识为REST控制器，返回JSON
@RequestMapping("/api/users") // 接口根路径
public class UserController {

    // 1. 查询：根据ID获取用户（新增异常测试代码）
    @GetMapping("/{id}")
    public Result<Object> getUser(@PathVariable("id") Long id) {
        // ========== 新增的异常测试代码（加在这里） ==========
        // 故意触发算术异常（除数为0），测试全局异常处理器

        // ==================================================

        // 模拟查询逻辑，返回提示信息
        String result = "查询成功，ID为" + id + "的用户信息已返回";
        return Result.success(result);
    }

    // 2. 新增：创建用户（接收JSON请求体）
    @PostMapping
    public Result<Object> createUser(@RequestBody User user) {
        // 模拟新增逻辑，返回接收的用户信息
        String result = "新增成功，用户名称：" + user.getName() + "，年龄：" + user.getAge();
        return Result.success(result);
    }

    // 3. 更新：根据ID更新用户信息
    @PutMapping("/{id}")
    public Result<Object> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        // 模拟更新逻辑
        String result = "更新成功，ID为" + id + "的用户已修改为：" + user.getName();
        return Result.success(result);
    }

    // 4. 删除：根据ID删除用户
    @DeleteMapping("/{id}")
    public Result<Object> deleteUser(@PathVariable("id") Long id) {
        // 模拟删除逻辑
        String result = "删除成功，ID为" + id + "的用户已移除";
        return Result.success(result);
    }
}