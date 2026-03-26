package com.stu.hellosever.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stu.hellosever.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * Mapper接口：实验要求新建，继承BaseMapper<T>，使用MyBatis-Plus内置CRUD
 * 无需手写SQL，实验要求的insert/selectOne/selectById已由BaseMapper实现
 */
@Mapper // 实验要求：标识为MyBatis映射接口，交由Spring容器管理
public interface UserMapper extends BaseMapper<User> {
    // 空接口：继承BaseMapper即可，实验无额外SQL要求
}