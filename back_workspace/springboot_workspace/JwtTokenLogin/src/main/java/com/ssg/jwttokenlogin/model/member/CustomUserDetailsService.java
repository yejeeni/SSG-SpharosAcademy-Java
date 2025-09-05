package com.ssg.jwttokenlogin.model.member;

import com.ssg.jwttokenlogin.domain.CustomUserDetails;
import com.ssg.jwttokenlogin.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 스프링 시큐리티에서는 로그인 검증을 위한 서비스 객체를 별도로 정의할 필요 X
 * UserDetailService를 구현
 * */
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 데이터베이스에서 해당 유저명으로 객체 조회
        Member member = memberRepository.findById(username);// 스프링 시큐리티의 username = member의 id
        if (member == null){
            throw new UsernameNotFoundException("로그인 정보가 올바르지 않음");
        }

        // DaoAuthenticationProvider가 비밀번호 검증을 스스로 수행
        return new CustomUserDetails(member);
    }
}
