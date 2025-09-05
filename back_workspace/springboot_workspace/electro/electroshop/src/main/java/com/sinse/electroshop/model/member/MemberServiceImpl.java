package com.sinse.electroshop.model.member;

import com.sinse.electroshop.domain.Member;
import com.sinse.electroshop.exception.MemberNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberDAO memberDAO;

    @Override
    public Member authenticate(Member member) throws MemberNotFoundException {
        Member obj=memberDAO.findLoginMember(member);
        if(obj ==null){ //일치하는 회원 정보 없음
            throw new MemberNotFoundException("로그인 정보가 일치하지 않습니다");
        }
        return obj;
    }
}
