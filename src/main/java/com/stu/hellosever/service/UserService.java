package com.stu.hellosever.service;

import com.stu.hellosever.common.Result;
import com.stu.hellosever.dto.UserDTO;

public interface UserService {
    Result<String> register(UserDTO userDTO); // 注册方法
    Result<String> login(UserDTO userDTO);     // 登录方法
    Result<String> getUserById(Integer id);   // 实验新增：根据ID查询（适配MySQL Integer）


    Result<Object> getUserPage(Integer pageNum, Integer pageSize);
}