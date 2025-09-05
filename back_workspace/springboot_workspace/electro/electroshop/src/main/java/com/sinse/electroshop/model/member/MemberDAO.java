package com.sinse.electroshop.model.member;

import com.sinse.electroshop.domain.Member;

public interface MemberDAO {
    public Member findLoginMember(Member member);
}
