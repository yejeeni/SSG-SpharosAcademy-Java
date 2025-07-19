package mall.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mall.domain.Size;

@Repository
public class MybatisSizeDAO implements SizeDAO{

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List<Size> selectAll() {
		return sqlSessionTemplate.selectList("Size.selectAll");
	}
	
}
