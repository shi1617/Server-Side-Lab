package com.stu.hellosever.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.PrintWriter;

public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1. 获取请求方法和URI
        String method = request.getMethod();
        String uri = request.getRequestURI();

        // 2. 公开接口直接放行
        // 放行：登录接口
        if (uri.equals("/api/users/login")) {
            return true;
        }
        // 放行：POST 注册用户
        if (method.equals("POST") && uri.equals("/api/users")) {
            return true;
        }
        // 放行：GET 查询数字ID用户（精细化正则匹配）
        if (method.equals("GET") && uri.matches("/api/users/\\d+")) {
            return true;
        }

        // 3. 其他请求需要校验 Token
        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty()) {
            // 设置响应格式为 JSON，避免中文乱码
            response.setContentType("application/json;charset=UTF-8");
            // 可选：同步设置 HTTP 状态码为 401（更符合 RESTful 规范）
            // response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

            // 构造统一格式的 401 错误 JSON
            String errorJson = "{\"code\":401,\"msg\":\"登录凭证已缺失或过期，请重新登录\"}";

            // 写入响应并关闭流
            PrintWriter writer = response.getWriter();
            writer.write(errorJson);
            writer.flush();
            writer.close();

            // 拦截请求，不再进入 Controller
            return false;
        }

        // 4. 有 Token 则放行（实际业务可加 Token 验签/解析逻辑）
        return true;
    }
}