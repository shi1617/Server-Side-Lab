package com.stu.hellosever.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.stu.hellosever.common.Result;
import com.stu.hellosever.common.ResultCode;
import com.stu.hellosever.dto.UserDTO;
import com.stu.hellosever.entity.User;
import com.stu.hellosever.mapper.UserMapper;
import com.stu.hellosever.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 服务层实现：实验5.2核心重构，删除内存Map，改用UserMapper操作真实数据库
 * 使用LambdaQueryWrapper构造查询条件，严格匹配实验步骤
 */
@Service // 实验要求：标识为Spring服务层组件
public class UserServiceImpl implements UserService {

    // 实验要求：删除内存Map，改为@Autowired注入UserMapper
    @Autowired
    private UserMapper userMapper;

    @Override
    public Result<String> register(UserDTO userDTO) {
        // 实验步骤1：使用LambdaQueryWrapper根据用户名查询是否存在
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, userDTO.getUsername());
        User dbUser = userMapper.selectOne(queryWrapper);

        // 实验要求：用户名已存在，返回USER_HAS_EXISTED错误
        if (dbUser != null) {
            return Result.error(ResultCode.USER_HAS_EXISTED);
        }

        // 实验步骤2：组装User实体对象，赋值用户名和密码
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());

        // 实验步骤3：调用Mapper插入数据库，使用MyBatis-Plus内置insert
        userMapper.insert(user);

        // 实验要求：注册成功返回指定文案"注册成功!"
        return Result.success("注册成功!");
    }

    @Override
    public Result<String> login(UserDTO userDTO) {
        // 实验步骤1：使用LambdaQueryWrapper根据用户名查询数据库
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, userDTO.getUsername());
        User dbUser = userMapper.selectOne(queryWrapper);

        // 实验要求：用户不存在，返回USER_NOT_EXIST错误
        if (dbUser == null) {
            return Result.error(ResultCode.USER_NOT_EXIST);
        }

        // 实验要求：密码错误场景，返回PWD_ERROR错误（实验需测试该场景）
        if (!dbUser.getPassword().equals(userDTO.getPassword())) {
            return Result.error(ResultCode.PWD_ERROR);
        }

        // 登录成功，返回指定文案"登录成功!"
        return Result.success("登录成功!");
    }

    @Override
    public Result<String> getUserById(Integer id) {
        // 实验要求：使用BaseMapper内置selectById根据ID查询
        User dbUser = userMapper.selectById(id);

        // 用户不存在返回错误，存在则返回查询结果（含用户名）
        if (dbUser == null) {
            return Result.error(ResultCode.USER_NOT_EXIST);
        }
        return Result.success("查询成功：" + dbUser.getUsername());
    }
}