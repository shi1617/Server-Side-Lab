package com.stu.hellosever.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.stu.hellosever.common.Result;
import com.stu.hellosever.entity.UserInfo;
import com.stu.hellosever.vo.UserDetailVO;

public interface UserService extends IService<UserInfo> {
    Result<String> login(String username, String password);
    Result<UserDetailVO> getUserDetail(Long userId);
    Result<String> updateUserInfo(UserInfo userInfo);
}