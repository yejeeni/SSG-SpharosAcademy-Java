package mall.service;

import mall.domain.Member;

public interface MemberService {

	public Member selectById(String openId);
	public Member login(Member member);
	public void regist(Member member);
}
