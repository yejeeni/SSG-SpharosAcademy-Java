package com.ssg.chatroomapp.mapper;

import com.ssg.chatroomapp.domain.Member;
import com.ssg.chatroomapp.dto.MemberLoginDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {
    public List<Member> selectAll();
    public Member selectById(int memberId);
    public Member login(MemberLoginDTO memberLoginDTO);
    public int insert(Member member);
    public int update(Member member);
    public int delete(int memberId);
}
