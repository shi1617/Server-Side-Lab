package com.stu.hellosever.service;

import com.stu.hellosever.common.Result;
import com.stu.hellosever.entity.UserInfo;
import com.stu.hellosever.vo.UserDetailVO;

public interface UserService {
    // 这三个方法必须声明，否则 Controller 里调用会报错
    Result<UserDetailVO> getUserDetail(Long userId);
    Result<String> updateUserInfo(UserInfo userInfo);
    Result<String> deleteUser(Long userId);
}