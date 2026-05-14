package com.stu.hellosever.controller;

import com.stu.hellosever.common.Result;
import com.stu.hellosever.dto.ChatRequestDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final Map<String, String> sessionMap = new HashMap<>();

    @PostMapping
    public Result<String> chat(@RequestBody ChatRequestDTO request) {
        String sessionId = request.getSessionId();
        String message = request.getMessage();

        // 第一次对话
        if (!sessionMap.containsKey(sessionId)) {
            sessionMap.put(sessionId, message);
            return Result.success("这是Java实战课程，主要学习SpringBoot后端开发。");
        }
        // 第二次对话（记住上下文）
        else {
            return Result.success("我们刚才聊的是Java实战课程哦~");
        }
    }
}