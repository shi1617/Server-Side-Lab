package com.stu.hellosever.exception;

import com.stu.hellosever.common.Result;
import com.stu.hellosever.common.ResultCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 捕获所有异常
    @ExceptionHandler(Exception.class)
    public Result<?> handleException(Exception e) {
        e.printStackTrace(); // 可替换为日志
        return Result.error(ResultCode.ERROR); // ✅ 仅传1个枚举参数
    }

    // 自定义业务异常（可选）
    @ExceptionHandler(IllegalArgumentException.class)
    public Result<?> handleIllegalArgumentException(IllegalArgumentException e) {
        e.printStackTrace();
        return Result.error(ResultCode.ERROR); // ✅ 仅传1个枚举参数
    }
}