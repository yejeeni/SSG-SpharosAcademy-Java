package com.ssg.catchtableapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * 비밀번호 암호화
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 보안 설정
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/members/**", "/login/**", "/list", "/api/**").permitAll()  // 인증 없이 접근 가능
                        .requestMatchers("/list", "/api/**").permitAll()  // 인증 없이 접근 가능
                        .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()  // 정적 리소스
                        .anyRequest().authenticated()  // 나머지는 인증 필요
                )
                .formLogin(form -> form.disable())  // 기본 로그인 폼 비활성화
                .httpBasic(basic -> basic.disable())  // HTTP Basic 인증 비활성화
                .csrf(csrf -> csrf.disable());  // CSRF 비활성화 (개발용이므로 추후 지우기)

        return http.build();
    }
}