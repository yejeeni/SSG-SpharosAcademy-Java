package com.ssg.customlogindb.model.member.repository;

import com.ssg.customlogindb.domain.Member;

import java.util.List;

public interface MemberDAO {

    public List<Member> selectAll();

    public Member select(Long memberId);

    public void insert(Member member);

    public void delete(Member member);

    public void update(Member member);

    public Member getMemberById(String id);

}
