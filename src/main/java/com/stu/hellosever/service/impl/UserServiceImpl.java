package com.stu.hellosever.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stu.hellosever.common.Result;
import com.stu.hellosever.entity.UserInfo;
import com.stu.hellosever.mapper.UserInfoMapper;
import com.stu.hellosever.service.UserService;
import com.stu.hellosever.vo.UserDetailVO;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserService {

    @Override
    public Result<String> login(String username, String password) {
        return Result.success("登录成功");
    }

    @Override
    public Result<UserDetailVO> getUserDetail(Long userId) {
        return Result.success(new UserDetailVO());
    }

    @Override
    public Result<String> updateUserInfo(UserInfo userInfo) {
        return Result.success("更新成功");
    }
}