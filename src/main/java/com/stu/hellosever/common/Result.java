package com.stu.hellosever.common;

/**
 * 通用返回结果类：支持泛型，匹配实验Result<String>返回要求
 * 实验要求：成功返回数据，失败返回错误码+提示信息
 */
public class Result<T> {
    private int code;   // 状态码
    private String msg; // 提示信息
    private T data;     // 返回数据

    // 私有构造器，仅通过静态方法创建
    private Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    // 成功：带返回数据（实验注册/登录/查询均用此方法）
    public static <T> Result<T> success(T data) {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(), data);
    }

    // 失败：传入ResultCode枚举（实验要求的错误码返回）
    public static <T> Result<T> error(ResultCode resultCode) {
        return new Result<>(resultCode.getCode(), resultCode.getMsg(), null);
    }

    // 生成getter方法：前端/框架反射获取数据必须，实验要求返回完整响应体
    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }
}