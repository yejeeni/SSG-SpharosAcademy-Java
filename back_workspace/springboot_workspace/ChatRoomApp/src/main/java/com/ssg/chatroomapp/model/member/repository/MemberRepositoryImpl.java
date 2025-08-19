package com.ssg.chatroomapp.model.member.repository;

import com.ssg.chatroomapp.domain.Member;
import com.ssg.chatroomapp.dto.MemberLoginDTO;
import com.ssg.chatroomapp.exception.MemberException;
import com.ssg.chatroomapp.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository{

    private final MemberMapper mapper;

    @Override
    public List<Member> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public Member selectById(int memberId) {
        return mapper.selectById(memberId);
    }

    @Override
    public Member login(MemberLoginDTO memberLoginDTO) throws MemberException{
        try {
            log.debug("memberLoginDTO: {}, {} ", memberLoginDTO.getId(), memberLoginDTO.getPassword());
            return mapper.login(memberLoginDTO);
        } catch (Exception e) {
            throw new MemberException("로그인 실패", e);
        }
    }

    @Override
    public int insert(Member member) throws MemberException{
        try {
            return mapper.insert(member);
        } catch (Exception e) {
            throw new MemberException("가입 실패", e);
        }
    }

    @Override
    public int update(Member member) {
        return mapper.update(member);
    }

    @Override
    public int delete(int memberId) {
        return mapper.delete(memberId);
    }
}
