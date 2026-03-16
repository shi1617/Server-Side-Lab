package com.stu.hellosever.exception;

import com.stu.hellosever.common.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 * 拦截所有REST接口的异常，统一返回格式
 */
@RestControllerAdvice // 全局异常拦截，作用于所有@RestController
public class GlobalExceptionHandler {

    // 处理算术异常（如1/0，用于测试）
    @ExceptionHandler(ArithmeticException.class)
    public Result<Object> handleArithmeticException(ArithmeticException e) {
        // 打印堆栈，方便后端排查
        e.printStackTrace();
        // 返回友好提示给前端
        return Result.error(500, "算术异常：除数不能为0");
    }

    // 处理运行时异常（通用业务异常）
    @ExceptionHandler(RuntimeException.class)
    public Result<Object> handleRuntimeException(RuntimeException e) {
        e.printStackTrace();
        return Result.error(500, "运行时异常：" + e.getMessage());
    }

    // 处理所有异常（终极兜底）
    @ExceptionHandler(Exception.class)
    public Result<Object> handleException(Exception e) {
        e.printStackTrace();
        return Result.error(500, "系统异常：" + e.getMessage());
    }
}