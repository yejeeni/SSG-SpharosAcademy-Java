package com.ssg.hiberasync.repository;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ssg.hiberasync.exception.FoodTypeException;
import com.ssg.hiberasync.hibernate.HibernateConfig;
import com.ssg.hiberasync.model.FoodType;


public class FoodTypeDAO {
	HibernateConfig config = HibernateConfig.getInstance();
	
	// 모든 음식 유형 가져오기
	public List selectAll() throws FoodTypeException{
		Transaction transaction = null;
		List list = null;

		/**
		 * java7부터, 자원을 사용한 후 finally 영역에서 닫는 중복코드를 단순화시킬 수 있는 try-with-resource 문법 지원. Closeable을 구현한 객체를 대상으로 함
		 * try-catch만 집중하면 됨
		 */
		try(Session session = config.getSession()){ // close 해야하는 대상
			transaction = session.beginTransaction();
			
			// ORM은 직접 테이블에 접근하지 않음
			// 아래 라인이 테이블에 접근하는 것처럼 보이지만, from 절의 대상이 되는 객체는 테이블이 아니라 클래스임
			TypedQuery<FoodType> query = session.createQuery("from FoodType", FoodType.class);
			list =  query.getResultList();
			
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
				throw new FoodTypeException("데이터 조회 실패", e);
			}
		}
		return list;
	}
}
