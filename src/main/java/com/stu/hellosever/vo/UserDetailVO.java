package com.stu.hellosever.vo;

import lombok.Data;

@Data
public class UserDetailVO {
    private Long userId;   // 你这里是 userId！！！
    private String username;
    private String realName;
    private String phone;
    private String address;
}