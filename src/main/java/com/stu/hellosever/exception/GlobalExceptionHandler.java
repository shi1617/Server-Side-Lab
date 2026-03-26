package com.stu.hellosever.exception;

import com.stu.hellosever.common.Result;
import com.stu.hellosever.common.ResultCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result<String> handleAllException(Exception e) {
        // 注释掉 printStackTrace 消除警告，或者保留它（不影响运行）
        // e.printStackTrace();
        return Result.error(ResultCode.ERROR);
    }
}