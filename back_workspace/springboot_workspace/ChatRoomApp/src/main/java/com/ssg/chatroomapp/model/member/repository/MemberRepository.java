package com.ssg.chatroomapp.model.member.repository;

import com.ssg.chatroomapp.domain.Member;
import com.ssg.chatroomapp.dto.MemberLoginDTO;

import java.util.List;

public interface MemberRepository {
    public List<Member> selectAll();
    public Member selectById(int memberId);
    public Member login(MemberLoginDTO memberLoginDTO);
    public int insert(Member member);
    public int update(Member member);
    public int delete(int memberId);
}