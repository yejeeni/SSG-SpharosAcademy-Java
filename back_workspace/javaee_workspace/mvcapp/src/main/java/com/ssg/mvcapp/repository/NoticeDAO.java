package com.ssg.mvcapp.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.ssg.mvcapp.exception.NoticeException;
import com.ssg.mvcapp.model.Notice;
import com.ssg.mvcapp.mybatis.MybatisConfig;

//CRUD
public class NoticeDAO {
	
	//PoolManager poolManager = PoolManager.getInstance(); -> mybatis주도해서 지금 당장 안씀
	MybatisConfig config = MybatisConfig.getInstance();

	//모든 레코드 가져오기
	public List selectAll() {
		SqlSession sqlSession = config.getSqlSession();
		List list = sqlSession.selectList("Notice.selectAll");
		sqlSession.close();
		
		return list;
	}
	
	//한건 가져오기
	public Notice select(int notice_id) {
		SqlSession sqlSession = config.getSqlSession();
		Notice notice = sqlSession.selectOne("Notice.select", notice_id);
		sqlSession.close(); //select는 커밋x
		
		return notice;
	}
	
	//한건 넣기
	public void insert(Notice notice) throws NoticeException{
		SqlSession sqlSession = config.getSqlSession();
		int result = sqlSession.insert("Notice.insert", notice);
		sqlSession.commit(); //dml의 트랜잭션 확정, SqlSession은 트랜잭션을 수동으로 제어하는 구조(mybatis는 자동커밋이 아니라 커밋을 해줘야 됨)
		sqlSession.close();
		
		if(result <1) {
			throw new NoticeException("등록실패");
		}
		
	}
	
	//수정하기
	public void update(Notice notice) throws NoticeException{
		SqlSession sqlSession = config.getSqlSession();
		int result = sqlSession.update("Notice.update", notice);
		sqlSession.commit();
		sqlSession.close();
		
		if(result <1) {
			throw new NoticeException("수정실패");
		}
		
	}
	
	//삭제하기
	public void delete(int notice_id) throws NoticeException{
		SqlSession sqlSession = config.getSqlSession();
		int result = sqlSession.delete("Notice.delete", notice_id);
		sqlSession.commit();
		sqlSession.close();
		
		if(result<1) {
			throw new NoticeException("삭제실패");
		}
	}
	
}
