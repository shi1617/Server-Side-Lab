package com.stu.hellosever.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 实体类：映射数据库sys_user表，实验要求添加@TableName/@TableId注解
 * 字段与数据库完全一致：id（自增主键）、username、password
 */
@TableName("sys_user") // 实验要求：指定对应数据库表名
public class User {
    @TableId(type = IdType.AUTO) // 实验要求：主键自增，适配MySQL/AUTO
    private Integer id; // 适配MySQL的int类型，与数据库字段一致
    private String username; // 非空唯一，与数据库字段一致
    private String password; // 非空，与数据库字段一致

    // 全字段getter/setter方法：MyBatis-Plus反射赋值/取值必须，实验要求
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}