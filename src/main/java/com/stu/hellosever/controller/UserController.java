package com.stu.hellosever.controller;

import com.stu.hellosever.common.Result;
import com.stu.hellosever.entity.UserInfo;
import com.stu.hellosever.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result<String> login(@RequestBody UserInfo loginInfo) {
        return userService.login(loginInfo.getUsername(), loginInfo.getPassword());
    }

    @GetMapping("/{id}")
    public Result<?> getUserDetail(@PathVariable Long id) {
        return userService.getUserDetail(id);
    }

    @PutMapping
    public Result<String> updateUser(@RequestBody UserInfo userInfo) {
        return userService.updateUserInfo(userInfo);
    }
}