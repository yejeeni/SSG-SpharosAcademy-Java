package com.ssg.jwttokenlogin.model.member;

import com.ssg.jwttokenlogin.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Member 리포지토리
 */
public interface MemberRepository extends JpaRepository<Member, Integer> {

    /**
     * id로 member 조회
     * 비밀번호 검증은 시큐리티가 함
     * @param id
     * @return
     */
    public Member findById(String id);
}
