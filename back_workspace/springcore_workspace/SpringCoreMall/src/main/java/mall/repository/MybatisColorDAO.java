package mall.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mall.domain.Color;

@Repository
public class MybatisColorDAO implements ColorDAO{
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public List<Color> selectAll() {
		return sqlSessionTemplate.selectList("Color.selectAll");
	}

	@Override
	public Color selectById(int id) {
		return null;
	}
	
}
