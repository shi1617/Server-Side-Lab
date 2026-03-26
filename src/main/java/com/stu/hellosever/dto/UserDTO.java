package com.stu.hellosever.dto;

/**
 * 数据传输类：仅接收前端传递的用户名和密码，无冗余字段
 * 实验要求：注册/登录时接收前端参数，与User实体解耦
 */
public class UserDTO {
    private String username;
    private String password;

    // 全字段getter/setter方法：@RequestBody接收JSON参数必须，实验要求
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