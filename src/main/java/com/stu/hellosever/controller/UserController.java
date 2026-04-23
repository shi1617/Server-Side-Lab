package com.stu.hellosever.controller;

import com.stu.hellosever.common.Result;
import com.stu.hellosever.entity.UserInfo;
import com.stu.hellosever.service.UserService;
import com.stu.hellosever.vo.UserDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // 测试接口，无权限拦截
    @GetMapping("/testCache")
    public Result<String> testCache() {
        return Result.success("缓存测试成功！项目运行正常！");
    }

    @GetMapping("/{id}/detail")
    public Result<UserDetailVO> getUserDetail(@PathVariable("id") Long userId) {
        return userService.getUserDetail(userId);
    }

    @PutMapping("/{id}/detail")
    public Result<String> updateUserInfo(
            @PathVariable("id") Long userId,
            @RequestBody UserInfo userInfo) {
        userInfo.setUserId(userId);
        return userService.updateUserInfo(userInfo);
    }

    @DeleteMapping("/{id}")
    public Result<String> deleteUser(@PathVariable("id") Long userId) {
        return userService.deleteUser(userId);
    }
}