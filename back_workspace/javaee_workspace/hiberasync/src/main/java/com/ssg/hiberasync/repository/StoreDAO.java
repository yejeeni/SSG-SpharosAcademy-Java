package com.ssg.hiberasync.repository;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ssg.hiberasync.exception.StoreException;
import com.ssg.hiberasync.hibernate.HibernateConfig;
import com.ssg.hiberasync.model.Store;

import antlr.debug.NewLineEvent;

public class StoreDAO {
	HibernateConfig config = HibernateConfig.getInstance();
	
	/**
	 * 데이터 한 건 추가
	 * @param store
	 */
	public void insert(Store store) throws StoreException{
		Transaction transaction = null;
		
		try(Session session = config.getSession()){
			transaction = session.beginTransaction();
			
			session.save(store);
			
			transaction.commit();
		}catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
				throw new StoreException("등록실패", e);
			}
		}
	}
	
	/**
	 * 모든 레코드 가져오기
	 * @return
	 */
	public List<Store> selectAll() throws StoreException{
		Transaction transaction = null;
		List<Store> list = null;
		
		try(Session session = config.getSession()){
			transaction = session.beginTransaction();
			TypedQuery<Store> query = session.createQuery("from Store", Store.class); // 클래스
			list = query.getResultList();
			
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) transaction.rollback();
			throw new StoreException("조회 실패");
		}
		
		return list;
	}
	
	public Store select(int store_id) throws StoreException{
		Transaction transaction = null;
		Store store = null;
		try(Session session = config.getSession()){
			transaction = session.beginTransaction();
			store = session.get(Store.class, store_id);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) transaction.rollback();
			throw new StoreException("조회 실패", e);
		}
		
		return store;
	}
	
	
}
