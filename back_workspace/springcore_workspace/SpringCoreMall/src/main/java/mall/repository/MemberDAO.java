package mall.repository;

import mall.domain.Member;

public interface MemberDAO {
	public Member selectById(String id);
	public void insert(Member member);
	public Member selectByEmail(String email);
	public Member login(Member member);
}
