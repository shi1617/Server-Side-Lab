package com.stu.hellosever.service.impl;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stu.hellosever.service.ChatService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ChatServiceImpl implements ChatService {

    @Value("${spring.ai.dashscope.api-key}")
    private String apiKey;

    private static final String API_URL = "https://dashscope.aliyuncs.com/api/v1/services/aigc/text-generation/generation";
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String chat(String message) {
        // 构建请求体
        Map<String, Object> input = Map.of(
                "messages", List.of(
                        Map.of("role", "system", "content", "你是专业友好简洁的中文智能助手"),
                        Map.of("role", "user", "content", message)
                )
        );
        Map<String, Object> requestBody = Map.of(
                "model", "qwen-turbo",
                "input", input,
                "parameters", Map.of("result_format", "message")
        );

        // 发送请求
        try (HttpResponse response = HttpRequest.post(API_URL)
                .header("Authorization", "Bearer " + apiKey)
                .header("Content-Type", "application/json")
                .body(objectMapper.writeValueAsString(requestBody))
                .execute()) {

            if (response.isOk()) {
                JsonNode json = objectMapper.readTree(response.body());
                return json.get("output")
                        .get("choices")
                        .get(0)
                        .get("message")
                        .get("content")
                        .asText();
            } else {
                throw new RuntimeException("调用AI接口失败：" + response.body());
            }
        } catch (Exception e) {
            throw new RuntimeException("调用AI接口出错", e);
        }
    }
}