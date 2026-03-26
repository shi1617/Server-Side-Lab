package com.stu.hellosever.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        String method = request.getMethod();

        // 放行：登录/注册/按ID查询
        if (requestURI.endsWith("/api/users/login")
                || (requestURI.equals("/api/users") && method.equals("POST"))
                || (requestURI.matches("/api/users/\\d+") && method.equals("GET"))) {
            return true;
        }

        // 校验：PUT/DELETE 必须带Token
        if (method.equals("PUT") || method.equals("DELETE")) {
            String authHeader = request.getHeader("Authorization");
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                response.setStatus(401);
                response.setContentType("application/json;charset=utf-8");
                response.getWriter().write("{\"code\":401,\"msg\":\"登录凭证已缺失或过期，请重新登录\",\"data\":null}");
                return false;
            }
            String token = authHeader.substring(7);
            if (!"test-token-123".equals(token)) {
                response.setStatus(401);
                response.setContentType("application/json;charset=utf-8");
                response.getWriter().write("{\"code\":401,\"msg\":\"登录凭证已缺失或过期，请重新登录\",\"data\":null}");
                return false;
            }
        }
        return true;
    }
}