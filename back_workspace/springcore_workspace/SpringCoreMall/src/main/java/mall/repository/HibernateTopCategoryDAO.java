package mall.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import lombok.extern.slf4j.Slf4j;
import mall.domain.TopCategory;

@Qualifier("hibernateTopCategoryDAO")
@Slf4j
@Repository
public class HibernateTopCategoryDAO implements TopCategoryDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<TopCategory> selectAll() {
		 System.out.println(">>> 트랜잭션 걸려서 실행중? " + TransactionSynchronizationManager.isActualTransactionActive());
		 
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from TopCategory", TopCategory.class);
		
		return query.getResultList();
	}

	@Override
	public TopCategory select(int topcategory_id) {
		return null;
	}

}
