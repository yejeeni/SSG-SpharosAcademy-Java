package mall.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mall.domain.TopCategory;
import mall.repository.TopCategoryDAO;

@Service
public class TopCategoryServiceImpl implements TopCategoryService{
	
	@PostConstruct
	public void init() {
	    System.out.println("TopCategoryServiceImpl 빈 생성됨");
	}


	@Qualifier("hibernateTopCategoryDAO")
	@Autowired
	private TopCategoryDAO topCategoryDAO;
	
	@Transactional
	@Override
	public List<TopCategory> selectAll() {
		 System.out.println("Transactional? " + org.springframework.transaction.support.TransactionSynchronizationManager.isActualTransactionActive());
		return topCategoryDAO.selectAll();
	}

	@Override
	public TopCategory select(int topcategory_id) {
		return null;
	}

}
