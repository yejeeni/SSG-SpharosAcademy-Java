package com.ssg.customlogindb.model.member.repository;

import com.ssg.customlogindb.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    public Member findById(String id);

    /**
     * 비밀번호로 Member을 조회
     * @param Password
     * @return
     */
    public Member findByPassword(String Password);
}
