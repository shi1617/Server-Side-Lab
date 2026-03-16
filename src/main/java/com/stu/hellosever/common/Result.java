package com.stu.hellosever.common;

/**
 * 全局统一响应结果
 * @param <T> 响应数据的泛型，适配不同类型返回值
 */
public class Result<T> {
    // 状态码：200=成功，500=失败
    private Integer code;
    // 提示信息
    private String msg;
    // 响应数据
    private T data;

    // 私有构造：禁止外部直接new，通过静态方法创建
    private Result() {}

    private Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    // 成功响应：无数据返回
    public static <T> Result<T> success() {
        return new Result<>(200, "操作成功", null);
    }

    // 成功响应：带数据返回
    public static <T> Result<T> success(T data) {
        return new Result<>(200, "操作成功", data);
    }

    // 失败响应：自定义状态码和提示信息
    public static <T> Result<T> error(Integer code, String msg) {
        return new Result<>(code, msg, null);
    }

    // 失败响应：默认系统异常（500）
    public static <T> Result<T> error() {
        return new Result<>(500, "系统内部异常，请稍后重试", null);
    }

    // getter 和 setter（JSON序列化需要）
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}