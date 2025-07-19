package mall.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import mall.domain.TopCategory;

public class MybatisTopCategoryDAO implements TopCategoryDAO{
	
	@Autowired
	private SqlSessionTemplate sessionTemplate;

	@Override
	public List<TopCategory> selectAll() {
		return sessionTemplate.selectList("TopCategory.selectAll");
	}

	@Override
	public TopCategory select(int topcategory_id) {
		return null;
	}

}
