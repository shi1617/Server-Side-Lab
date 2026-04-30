package com.stu.hellosever.common;

public enum ResultCode {
    SUCCESS(200, "成功"),
    USER_LOGIN_ERROR(401, "用户名或密码错误"),
    ERROR(500, "服务器内部错误"); // 新增这一行

    private int code;
    private String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}