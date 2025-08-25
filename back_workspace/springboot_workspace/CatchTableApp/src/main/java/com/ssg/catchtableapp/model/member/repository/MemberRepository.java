package com.ssg.catchtableapp.model.member.repository;


import com.ssg.catchtableapp.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * MemberRepository
 * 인터페이스만 만들어두면 JPA가 알아서 구현체 생성해줌
 *
 * @author 김예진
 * @since 2025-08-20
 */
public interface MemberRepository  extends JpaRepository<Member, Long> {

    /**
     * id로 사용자 조회
     * @param id
     * @return
     */
    Optional<Member> findById(String id); // Optional: 값이 있을 수도 있고 없을 수도 있음을 명시

}