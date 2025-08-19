package com.ssg.chatroomapp.model.member.service;

import com.ssg.chatroomapp.domain.Member;
import com.ssg.chatroomapp.dto.MemberLoginDTO;
import com.ssg.chatroomapp.exception.MemberException;
import com.ssg.chatroomapp.model.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public List<Member> selectAll() {
        return memberRepository.selectAll();
    }

    @Override
    public Member selectById(int memberId) {
        return memberRepository.selectById(memberId);
    }

    @Override
    public Member login(MemberLoginDTO memberLoginDTO) {
        Member member = memberRepository.login(memberLoginDTO);
        if (member == null){
            throw new MemberException("아이디 또는 비밀번호가 올바르지 않습니다.");
        }
        return member;
    }

    @Override
    public int insert(Member member) throws MemberException {
        int result = memberRepository.insert(member);
        if (result == 0) {
            throw new MemberException("회원가입에 실패했습니다. 다시 시도해주세요.");
        }
        return result;
    }

    @Override
    public int update(Member member) {
        return memberRepository.update(member);
    }

    @Override
    public int delete(int memberId) {
        return memberRepository.delete(memberId);
    }
}
