package com.stu.hellosever.service.impl;

import cn.hutool.json.JSONUtil;
import com.stu.hellosever.common.Result;
import com.stu.hellosever.common.ResultCode;
import com.stu.hellosever.entity.UserInfo;
import com.stu.hellosever.mapper.UserInfoMapper;
import com.stu.hellosever.service.UserService;
import com.stu.hellosever.vo.UserDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {

    private static final String CACHE_KEY_PREFIX = "user:detail:";

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public Result<UserDetailVO> getUserDetail(Long userId) {
        String key = CACHE_KEY_PREFIX + userId;

        // 查缓存
        String json = redisTemplate.opsForValue().get(key);
        if (json != null && !json.isBlank()) {
            try {
                UserDetailVO cacheVO = JSONUtil.toBean(json, UserDetailVO.class);
                return Result.success(cacheVO);
            } catch (Exception e) {
                redisTemplate.delete(key);
            }
        }

        // 查数据库
        UserDetailVO detail = userInfoMapper.getUserDetail(userId);
        if (detail == null) {
            return Result.error(ResultCode.USER_NOT_EXIST);
        }

        // 写入缓存
        redisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(detail), 10, TimeUnit.MINUTES);

        return Result.success(detail);
    }

    @Override
    @Transactional
    public Result<String> updateUserInfo(UserInfo userInfo) {
        if (userInfo == null || userInfo.getUserId() == null) {
            return Result.error(ResultCode.ERROR);
        }

        int rows = userInfoMapper.updateById(userInfo);
        if (rows > 0) {
            redisTemplate.delete(CACHE_KEY_PREFIX + userInfo.getUserId());
            return Result.success("更新成功");
        }
        return Result.error(ResultCode.ERROR);
    }

    @Override
    @Transactional
    public Result<String> deleteUser(Long userId) {
        int rows = userInfoMapper.deleteById(userId);
        if (rows > 0) {
            redisTemplate.delete(CACHE_KEY_PREFIX + userId);
            return Result.success("删除成功");
        }
        return Result.error(ResultCode.ERROR);
    }
}