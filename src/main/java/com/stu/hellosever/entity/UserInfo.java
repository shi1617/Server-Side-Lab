package com.stu.hellosever.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sys_user")
public class UserInfo {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String username;
    private String password;
}