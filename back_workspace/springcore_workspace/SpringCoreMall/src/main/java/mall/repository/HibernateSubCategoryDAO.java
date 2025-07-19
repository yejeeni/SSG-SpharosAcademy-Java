package mall.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;
import mall.domain.SubCategory;

@Slf4j
@Repository
public class HibernateSubCategoryDAO {
	
	private SessionFactory sessionFactory;

	public List<SubCategory> selectAll() {
		 
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from SubCategory", SubCategory.class);
		
		return query.getResultList();
	}

	public SubCategory select(int topcategory_id) {
		return null;
	}

}
