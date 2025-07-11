package myframework.staff.model.service;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import myframework.exception.BioException;
import myframework.exception.StaffException;
import myframework.mybatis.MybatisConfig;
import myframework.staff.model.domain.Bio;
import myframework.staff.model.repository.BioDAO;
import myframework.staff.model.repository.StaffDAO;

/**
 * 비즈니스 로직을 캡슐화하는 Service
 * Controller와 Model 사이의 복잡도를 줄여줌
 */
public class StaffService {
	Logger logger = LoggerFactory.getLogger(getClass());

	StaffDAO staffDAO = new StaffDAO();
	BioDAO bioDAO = new BioDAO();
	
	public void regist(Bio bio) {
		// staff, bio를 추가하는 두 개의 업무를 수행
		MybatisConfig config = MybatisConfig.getInstance(); // 다수의 DAO가 하나의 트랜잭션에 들어가도록 해야 함
		
		SqlSession session = config.getSqlSession();

		try {
			// logger.debug("등록 전 staff id="+bio.getStaff_id());
			staffDAO.insert(session, bio.getStaff());
			// logger.debug("등록 후 staff id="+bio.getStaff_id());
			bioDAO.insert(session, bio);
			
			session.commit();
		} catch (StaffException | BioException e) {
			e.printStackTrace();
			session.rollback();
		} finally {
			session.close();
		}
		
	}
	
}
