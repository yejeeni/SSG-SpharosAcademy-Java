package com.ssg.customlogindb.security;

import com.ssg.customlogindb.model.member.service.MemberDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * 스프링이 기본적으로 지원하는 플러그인을 커스텀하기 위한 설정 클래스
 *
 * Spring Boot 자동 설정이 해주는 일:
 * MemberDetailsService (@Service) 자동 감지 → UserDetailsService로 등록
 * PasswordEncoder Bean 자동 감지
 * DaoAuthenticationProvider 자동 생성 및 연결
 * AuthenticationManager 자동 생성
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final MemberDetailsService memberDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * 비밀번호를 검증하는 DaoAuthenticationProvider 등록 (-> repository에서 password를 사용할 필요 X)
     * 스프링이 자동 생성해줌
     * @return
     */
//    @Bean
//    public DaoAuthenticationProvider daoAuthenticationProvider(){
//        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//        daoAuthenticationProvider.setUserDetailsService(memberDetailsService); // 서비스 선택
//        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder()); // 비밀번호 알고리즘
//
//        return daoAuthenticationProvider;
//    }

    /**
     * AuthenticationManager 등록
     * 스프링이 자동 생성해줌
     * @param configuration
     * @return
     * @throws Exception
     */
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
//        return configuration.getAuthenticationManager();
//    }

    /**
     * 스프링 시큐리티의 처리를 담당하는 객체 SecurityFilterChain
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests(auth -> auth
                        // 허용
                        .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                        .requestMatchers( "/", "/loginform").permitAll()
                        // 인증이 필요한 경로들
                        .requestMatchers("/main").authenticated()
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/loginform")
                        .loginProcessingUrl("/login") // 로그인 요청을 처리하는 uri
                        .usernameParameter("id")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/", true)
                        .permitAll()
                )
                .httpBasic(basic -> basic.disable())  // HTTP Basic 인증 비활성화
                .csrf(csrf -> csrf.disable());  // CSRF 비활성화

        return http.build();
    }
}
