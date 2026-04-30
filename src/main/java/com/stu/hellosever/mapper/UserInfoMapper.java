package com.stu.hellosever.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stu.hellosever.entity.UserInfo;
import com.stu.hellosever.vo.UserDetailVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.security.core.userdetails.UserDetails;

@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {

    // 多表联查：用户基本信息 + 详情信息
    @Select("""
            SELECT
                u.id AS userId,
                u.username,
                i.real_name AS realName,
                i.phone,
                i.address
            FROM sys_user u
            LEFT JOIN user_info i ON u.id = i.user_id
            WHERE u.id = #{userId}
            """)
    UserDetailVO getUserDetail(@Param("userId") Long userId);

    // ===================== 我帮你加的 JWT 必需方法 =====================
    @Select("SELECT username, password FROM sys_user WHERE username = #{username}")
    UserDetails findByUsername(@Param("username") String username);
}