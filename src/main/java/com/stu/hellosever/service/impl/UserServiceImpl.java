package com.stu.hellosever.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stu.hellosever.common.Result;
import com.stu.hellosever.entity.UserInfo;
import com.stu.hellosever.mapper.UserInfoMapper;
import com.stu.hellosever.security.JwtUtil;
import com.stu.hellosever.service.UserService;
import com.stu.hellosever.vo.UserDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserService {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public Result<String> login(String username, String password) {
        LambdaQueryWrapper<UserInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserInfo::getUsername, username);
        UserInfo user = getOne(wrapper);

        // 用户名或密码错误
        if (user == null || !user.getPassword().equals(password)) {
            return Result.success(null);
        }

        // 生成真实 JWT 并返回
        String token = jwtUtil.generateToken(username);
        return Result.success(token);
    }

    @Override
    public Result<UserDetailVO> getUserDetail(Long userId) {
        UserInfo user = getById(userId);
        if (user == null) {
            return Result.success(null);
        }
        UserDetailVO vo = new UserDetailVO();
        vo.setUserId(user.getId());
        vo.setUsername(user.getUsername());
        return Result.success(vo);
    }

    @Override
    public Result<String> updateUserInfo(UserInfo userInfo) {
        updateById(userInfo);
        return Result.success("修改成功");
    }
}