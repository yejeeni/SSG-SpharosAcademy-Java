package com.ssg.customlogindb.model.member;

import com.ssg.customlogindb.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * 로그인에 성공한 사용자 정보를 보유한 객체.
 * 로그인 검증이 성공되면 Authentication 객체가 생성되고, 이 객체에 UserDetails 객체가 등록됨
 * 스프링 시큐리티 프레임워크는 회원 정보를 담은 객체가 어떤 클래스인지 알 수 없으므로 UserDetails라는 이름의 인터페이스를 정의해둠
 * 이 인터페이스의 메서드에 회원 정보를 채워넣어야 함
 */
@Getter
@AllArgsConstructor
public class CustomUserDetails implements UserDetails {

    private Member member;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
