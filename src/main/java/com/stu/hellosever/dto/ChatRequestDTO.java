package com.stu.hellosever.dto;

public class ChatRequestDTO {
    private String sessionId;
    private String message;

    // 必须有 get 方法，否则 Spring 无法解析请求
    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}