package mall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mall.domain.Member;
import mall.exception.MemberException;
import mall.exception.MemberNotFoundException;
import mall.exception.PasswordEncryptException;
import mall.repository.MemberDAO;
import mall.util.PasswordUtil;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberDAO memberDAO;
	@Autowired
	private PasswordUtil passwordUtil;
	
	@Override
	public Member selectById(String id) throws MemberException{
		return memberDAO.selectById(id);
	}

	@Override
	public void regist(Member member) throws PasswordEncryptException{
		log.debug("sns provider = {}", member.getSnsProvider());
		
		// 홈페이지 가입인 경우 암호화 필요
		if (member.getSnsProvider() == null) {
			// 솔트 생성
			String salt = passwordUtil.generateSalt();
			// 전송된 파라미터와 솔트를 섞어서 해시 생성
			String hashedPassword = passwordUtil.hashPassword(member.getPassword(), salt);
			// 해시를 멤버의 비밀번호에 저장
			member.setSalt(salt);
			member.setPassword(hashedPassword);
		} 
		// 가입
		memberDAO.insert(member);
	}

	@Override
	public Member login(Member member) throws MemberException, MemberNotFoundException{
		// 이메일을 이용하여 회원의 salt를 조사
		Member obj = memberDAO.selectByEmail(member.getEmail());
		log.debug("db에 저장된 salt = {}", obj.getSalt());
		log.debug("db에 저장된 pw = {}", obj.getPassword());
		log.debug("salt와 pw의 해시 조합 = {}", passwordUtil.hashPassword(member.getPassword(), obj.getSalt()));
		
		String dbHash = passwordUtil.hashPassword(member.getPassword(), obj.getSalt());
		
		if (!dbHash.equals(obj.getPassword())) {
			throw new MemberNotFoundException("로그인 정보가 올바르지 않습니다");
		}
		
		return obj;
	}
}
