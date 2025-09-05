package com.sinse.electroshop.model.member;

import com.sinse.electroshop.controller.dto.MemberDTO;
import com.sinse.electroshop.domain.Member;

public interface MemberService {
    public Member authenticate(Member member);
}
