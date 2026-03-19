package com.stu.hellosever.common;

public class Result<T> {
    private Integer code;
    private String msg;
    private T data;

    // 无参构造
    public Result() {}

    // 全参构造
    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    // 成功：带数据
    public static <T> Result<T> success(T data) {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(), data);
    }

    // 成功：无数据
    public static <T> Result<T> success() {
        return success(null);
    }

    // 失败：指定状态码
    public static <T> Result<T> error(ResultCode code) {
        return new Result<>(code.getCode(), code.getMsg(), null);
    }

    // Getter & Setter
    public Integer getCode() { return code; }
    public void setCode(Integer code) { this.code = code; }
    public String getMsg() { return msg; }
    public void setMsg(String msg) { this.msg = msg; }
    public T getData() { return data; }
    public void setData(T data) { this.data = data; }
}