package com.stu.hellosever.controller;

import com.stu.hellosever.common.Result;
import com.stu.hellosever.dto.UserDTO;
import com.stu.hellosever.service.UserService;
import org.springframework.web.bind.annotation.*;

/**
 * 控制层：实验5.3重构，所有接口调用真实数据库逻辑
 * 实验要求：根路径/api/users，包含注册/登录/根据ID查询接口
 */
@RestController // 标识为REST接口，返回JSON
@RequestMapping("/api/users") // 实验要求：接口根路径
public class UserController {

    // 构造器注入：替代@Autowired，消除IDE警告，规范开发
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 实验要求：注册接口 - POST /api/users/register
    @PostMapping("/register")
    public Result<String> register(@RequestBody UserDTO userDTO) {
        return userService.register(userDTO);
    }

    // 实验要求：登录接口 - POST /api/users/login（POST传JSON，适配密码校验）
    @PostMapping("/login")
    public Result<String> login(@RequestBody UserDTO userDTO) {
        return userService.login(userDTO);
    }

    // 实验5.1要求：根据ID查询接口 - GET /api/users/{id}（路径传参）
    @GetMapping("/{id}")
    public Result<String> getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }
}