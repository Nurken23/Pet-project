package com.example.booking_system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // Говорит Spring, что это конфигурационный класс
@EnableWebSecurity // Включает веб-безопасность Spring
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 1. Отключаем CSRF-защиту. Для REST API, которые не используют сессии, это обычная практика.
                .csrf(csrf -> csrf.disable())
                // 2. Настраиваем правила авторизации запросов
                .authorizeHttpRequests(auth -> auth
                        // 3. Разрешаем ВСЕ запросы к ЛЮБЫМ эндпоинтам.
                        // Пока что для простоты тестирования мы откроем доступ ко всему.
                        .anyRequest().permitAll()
                );

        return http.build();
    }
}