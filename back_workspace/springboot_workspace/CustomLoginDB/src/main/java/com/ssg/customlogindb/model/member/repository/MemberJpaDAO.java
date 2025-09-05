package com.ssg.customlogindb.model.member.repository;

import com.ssg.customlogindb.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberJpaDAO implements MemberDAO {
    private final MemberRepository memberRepository;

    @Override
    public List<Member> selectAll() {
        return List.of();
    }

    @Override
    public Member select(Long memberId) {
        return null;
    }

    @Override
    public void insert(Member member) {

    }

    @Override
    public void delete(Member member) {

    }

    @Override
    public void update(Member member) {

    }

    @Override
    public Member getMemberById(String id) {
        return memberRepository.findById(id);
    }
}
