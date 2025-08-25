package com.ssg.catchtableapp.model.member.service;

import com.ssg.catchtableapp.domain.Member;
import com.ssg.catchtableapp.model.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 회원 가입
     */
    @Transactional
    public int join(Member member) {
        validateDuplicateMember(member); // 값 검증

        // 암호화한 값으로 비밀번호 수정
        String encodedPassword = passwordEncoder.encode(member.getPassword());
        member.setPassword(encodedPassword);

        memberRepository.save(member);
        return member.getMemberId();
    }

    /**
     * 로그인
     */
    public Member login(String loginId, String password) {
        log.debug("login id: {}, password: {}", loginId, password);
        Member member = memberRepository.findById(loginId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        if (!passwordEncoder.matches(password, member.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return member;
    }

    /**
     * 가입하는 회원의 정보 검증
     * @param member
     */
    private void validateDuplicateMember(Member member) {
        memberRepository.findById(member.getId())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 아이디입니다.");
                });
    }
}