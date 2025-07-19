package mall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import mall.domain.SubCategory;
import mall.repository.SubCategoryDAO;

@Service
public class SubCategoryServiceImpl implements SubCategoryService{
	
	@Qualifier("mybatisSubCategoryDAO")
	@Autowired
	private SubCategoryDAO subCategoryDAO;

	@Override
	public List<SubCategory> selectAll() {
		return subCategoryDAO.selectAll();
	}

	@Override
	public List<SubCategory> selectByTopCategoryId(int topcategory_id) {
		return subCategoryDAO.selectByTopCategoryId(topcategory_id);
	}
	
}
