package com.ssg.jwttokenlogin.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {
    // "클래스가 레코드 클래스일 수 있습니다." 라는 조언이 뜨는데, 레코드는 final(상속불가)이고, abstract로 선언 불가
    // JPA에서는 지연로딩 시 엔티티 객체의 프록시 객체를 생성하는데, 프록시 객체는 원본 객체를 상속하여 확장된 클래스이다.
    // 레코드는 상속이 불가하므로 엔티티로 사용 불가

    private final Member member;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO 학습용 기본권한 부여
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getId();
    }
}
