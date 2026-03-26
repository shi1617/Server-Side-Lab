package com.stu.hellosever.common;

/**
 * 错误码枚举：严格匹配实验5.2要求，新增全局ERROR处理系统异常
 * 包含：成功、用户名已存在、用户不存在、密码错误、系统错误
 */
public enum ResultCode {
    SUCCESS(200, "操作成功"),          // 通用成功
    USER_HAS_EXISTED(5001, "用户名已存在"), // 实验注册要求
    USER_NOT_EXIST(5002, "用户不存在"),    // 实验登录要求
    PWD_ERROR(5003, "密码错误"),           // 实验密码错误场景要求
    ERROR(500, "系统错误");               // 全局异常处理，解决编译报错

    private final int code;   // 状态码
    private final String msg; // 提示信息

    // 构造器：枚举常量初始化
    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    // getter方法：Result类获取枚举属性
    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}