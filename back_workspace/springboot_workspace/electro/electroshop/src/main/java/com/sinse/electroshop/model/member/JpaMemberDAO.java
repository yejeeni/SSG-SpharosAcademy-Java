package com.sinse.electroshop.model.member;

import com.sinse.electroshop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JpaMemberDAO implements MemberDAO {

    private final JpaMemberRepository memberRepository;

    @Override
    public Member findLoginMember(Member member) {
        return memberRepository.findByIdAndPassword(member.getId(), member.getPassword());
    }
}
