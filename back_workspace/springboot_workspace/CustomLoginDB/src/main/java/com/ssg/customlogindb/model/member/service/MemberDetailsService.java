package com.ssg.customlogindb.model.member.service;

import com.ssg.customlogindb.domain.Member;
import com.ssg.customlogindb.model.member.CustomUserDetails;
import com.ssg.customlogindb.model.member.repository.MemberJpaDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberDetailsService implements UserDetailsService {

    private final MemberJpaDAO memberJpaDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        log.debug("아이디 {}로 회원 정보 조회 시도", username);

        
        Member member = memberJpaDAO.getMemberById(username);
        if (member == null) {
            throw new UsernameNotFoundException(username);
        }
        log.debug("DB password: {}", member.getPassword());
        log.debug("회원 정보 가져옴");
        return new CustomUserDetails(member);
    }
}
