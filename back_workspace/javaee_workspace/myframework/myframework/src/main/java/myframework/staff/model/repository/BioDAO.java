package myframework.staff.model.repository;

import org.apache.ibatis.session.SqlSession;

import myframework.exception.BioException;
import myframework.exception.StaffException;
import myframework.mybatis.MybatisConfig;
import myframework.staff.model.domain.Bio;

public class BioDAO {
	MybatisConfig config = MybatisConfig.getInstance();
	
	public void insert(SqlSession session, Bio bio) throws BioException{
		int result = session.insert("Bio.insert", bio);
		if (result < 1) {
			throw new StaffException("Bio 등록 실패");
		}
	}
}
