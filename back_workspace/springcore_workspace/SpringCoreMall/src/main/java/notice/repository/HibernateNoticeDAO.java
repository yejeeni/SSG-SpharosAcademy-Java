package notice.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;
import notice.domain.Notice;
import notice.exception.NoticeException;

@Slf4j
@Repository
public class HibernateNoticeDAO implements NoticeDAO{
	
	@Autowired // AdminWebConfig의 sessionFactoryBean()?
	private SessionFactory sessionFactory;

	@Override
	public List<Notice> selectAll() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Notice", Notice.class);
		return query.getResultList();
	}

	@Override
	public Notice select(int notice_id) {
		return sessionFactory.getCurrentSession().get(Notice.class, notice_id);
	}

	@Override
	public void insert(Notice notice) throws NoticeException{
		try {
			sessionFactory.getCurrentSession().save(notice);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("등록 실패", e);
			throw new NoticeException("등록 실패", e);
		}
	}

	@Override
	public void update(Notice notice) {
	    sessionFactory.getCurrentSession().update(notice);		
	}


	@Override
	public void delete(int notice_id) {
		Notice notice = sessionFactory.getCurrentSession().get(Notice.class, notice_id);
	    if (notice != null) {
	        sessionFactory.getCurrentSession().delete(notice);
	    }		
	}

}
