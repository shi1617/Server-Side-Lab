package com.stu.hellosever.service;

import com.stu.hellosever.common.Result;
import com.stu.hellosever.dto.UserDTO;

/**
 * 服务层接口：实验5.1要求，原有注册/登录基础上新增根据ID查询方法
 * 严格匹配实验方法名和返回值：Result<String>
 */
public interface UserService {
    Result<String> register(UserDTO userDTO); // 注册方法
    Result<String> login(UserDTO userDTO);     // 登录方法
    Result<String> getUserById(Integer id);   // 实验新增：根据ID查询（适配MySQL Integer）
}