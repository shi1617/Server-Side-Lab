package com.stu.hellosever.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stu.hellosever.common.Result;
import com.stu.hellosever.common.ResultCode;
import com.stu.hellosever.dto.UserDTO;
import com.stu.hellosever.entity.User;
import com.stu.hellosever.mapper.UserMapper;
import com.stu.hellosever.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Result<String> register(UserDTO userDTO) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, userDTO.getUsername());
        User dbUser = userMapper.selectOne(queryWrapper);

        if (dbUser != null) {
            return Result.error(ResultCode.USER_HAS_EXISTED);
        }

        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());

        userMapper.insert(user);

        return Result.success("注册成功!");
    }

    @Override
    public Result<String> login(UserDTO userDTO) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, userDTO.getUsername());
        User dbUser = userMapper.selectOne(queryWrapper);

        if (dbUser == null) {
            return Result.error(ResultCode.USER_NOT_EXIST);
        }

        if (!dbUser.getPassword().equals(userDTO.getPassword())) {
            return Result.error(ResultCode.PWD_ERROR);
        }

        return Result.success("登录成功!");
    }

    @Override
    public Result<String> getUserById(Integer id) {
        User dbUser = userMapper.selectById(id);

        if (dbUser == null) {
            return Result.error(ResultCode.USER_NOT_EXIST);
        }
        return Result.success("查询成功：" + dbUser.getUsername());
    }

    @Override
    public Result<Object> getUserPage(Integer pageNum, Integer pageSize) {
        Page<User> page = new Page<>(pageNum, pageSize);
        Page<User> resultPage = userMapper.selectPage(page, null);
        return Result.success(resultPage);
    }
}