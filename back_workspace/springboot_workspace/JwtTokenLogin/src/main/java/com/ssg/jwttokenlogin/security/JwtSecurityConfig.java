package com.ssg.jwttokenlogin.security;

import com.ssg.jwttokenlogin.filter.JwtAuthFilter;
import com.ssg.jwttokenlogin.model.member.CustomUserDetailsService;
import com.ssg.jwttokenlogin.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * JWT 토큰 사용 시의 Spring Security 설정 클래스
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class JwtSecurityConfig {

    private final JwtUtil jwtUtil;

    /**
     * JWT 인증 필터 등록
     * @return
     */
    @Bean
    public OncePerRequestFilter jwtAuthFilter(){
        return new JwtAuthFilter(jwtUtil);
    }

    /**
     * 비밀번호 암호화 인코더
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(); // 비밀번호 결과물에 salt가 포함되어 있음
    }

//    @Bean
//    public DaoAuthenticationProvider daoAuthenticationProvider(){
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setUserDetailsService(customUserDetailService);
//        provider.setPasswordEncoder(passwordEncoder());
//        return provider;
//    }

    /**
     * 프로그래밍 방식 인증을 위한 authenticationManager
     * @param configuration
     * @return
     * @throws Exception
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }

    /**
     * JWT 기반 보안 필터 체인 설정
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, CustomUserDetailsService customUserDetailsService) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable) // rest, jwt 사용하므로 csrf 비활성화
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // 시큐리티가 인증 상태를 세션에 저장하지 않게 해야하므로 세션을 만들지도 사용하지도 않도록 STATELESS
                .formLogin(AbstractHttpConfigurer ::disable) // JWT 토큰을 컨트롤러에서 발급해야하므로 컨트롤러를 활성화하고, form 로그인 기본 기능 비활성화
                .authorizeHttpRequests(auth->auth
                        .requestMatchers("/member/login.html", "/member/login").permitAll()
                        .anyRequest().authenticated())
//                .authenticationProvider(provider)
                .addFilterBefore(jwtAuthFilter(), AuthorizationFilter.class)
                .build();
    }
}
