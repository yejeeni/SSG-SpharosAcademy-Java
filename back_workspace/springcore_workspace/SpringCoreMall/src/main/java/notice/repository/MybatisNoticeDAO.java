package notice.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;
import notice.domain.Notice;
import notice.exception.NoticeException;

@Slf4j
@Repository
public class MybatisNoticeDAO implements NoticeDAO{

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List<Notice> selectAll() {
		log.debug("selectAll()");
		return sqlSessionTemplate.selectList("Notice.selectAll");
	}

	@Override
	public Notice select(int notice_id) {
		 return sqlSessionTemplate.selectOne("Notice.select", notice_id);
	}

	@Override
	public void insert(Notice notice) throws NoticeException{
		int result = sqlSessionTemplate.insert("Notice.insert", notice);
		if (result<1) {
			throw new NoticeException("등록 오류");
		}
	}

	@Override
	public void update(Notice notice) {
		   int result = sqlSessionTemplate.update("Notice.update", notice);
		    if (result < 1) {
		        throw new NoticeException("수정 실패");
		    }
	}


	@Override
	public void delete(int notice_id) {
		int result = sqlSessionTemplate.delete("Notice.delete", notice_id);
	    if (result < 1) {
	        throw new NoticeException("삭제 실패");
	    }
	}

}
