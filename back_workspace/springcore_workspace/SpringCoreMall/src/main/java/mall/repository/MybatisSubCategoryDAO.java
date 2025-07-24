package mall.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mall.domain.SubCategory;

@Repository
public class MybatisSubCategoryDAO implements SubCategoryDAO{
	@Autowired
	private SqlSessionTemplate sessionTemplate;

	@Override
	public List<SubCategory> selectAll() {
		return null;
	}

	@Override
	public List<SubCategory> selectByTopCategoryId(int topcategory_id) {
	    return sessionTemplate.selectList("mall.repository.SubCategoryDAO.selectByTopCategoryId", topcategory_id);
	}

	
}
