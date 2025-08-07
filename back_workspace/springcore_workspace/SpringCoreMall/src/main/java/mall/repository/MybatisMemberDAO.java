package mall.repository;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mall.domain.Member;
import mall.exception.MemberException;

@Repository
public class MybatisMemberDAO implements MemberDAO{

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public Member selectById(String id) {
		Member member = (Member) sqlSessionTemplate.selectOne("Member.selectById", id);
		return member;
	}

	@Override
	public void insert(Member member) throws MemberException{
		int result = sqlSessionTemplate.insert("Member.insert", member);
		if (result < 1) {
			throw new MemberException("회원 등록 실패");
		}
	}

	@Override
	public Member selectByEmail(String email) throws MemberException {
		Member member = sqlSessionTemplate.selectOne("Member.selectByEmail", email);
		if (member == null) {
			throw new MemberException("일치 정보 없음");
		}
		return member;
	}

	@Override
	public Member login(Member member) {
		Member obj = sqlSessionTemplate.selectOne("Member.login", member);
		if (obj == null) {
			throw new MemberException("로그인 정보가 올바르지 않음");
		}
		
		return null;
	}

}
