package com.stu.hellosever.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 开启跨域支持
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                // 关闭CSRF（前后端分离项目必须关）
                .csrf(AbstractHttpConfigurer::disable)
                // 无状态会话，不使用Session
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // 权限规则配置
                .authorizeHttpRequests(auth -> auth
                        // 放行注册接口 POST /api/users
                        .requestMatchers(HttpMethod.POST, "/api/users").permitAll()
                        // 放行登录接口 POST /api/users/login
                        .requestMatchers(HttpMethod.POST, "/api/users/login").permitAll()
                        // 放行按ID查询 GET /api/users/{id}
                        .requestMatchers(HttpMethod.GET, "/api/users/*").permitAll()
                        // PUT/DELETE 接口需要认证
                        .requestMatchers(HttpMethod.PUT, "/api/users/**").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/api/users/**").authenticated()
                        // 其他所有请求都放行（按需修改）
                        .anyRequest().permitAll()
                )
                // 关闭Spring Security默认的表单登录页面
                .formLogin(AbstractHttpConfigurer::disable)
                // 关闭HTTP Basic认证
                .httpBasic(AbstractHttpConfigurer::disable);

        return http.build();
    }

    /**
     * 跨域配置，和WebConfig保持一致
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOriginPattern("*");
        config.addAllowedMethod("*");
        config.addAllowedHeader("*");
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}