package mall.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mall.domain.SnsProvider;

@Repository
public class MybatisSnsProviderDAO implements SnsProviderDAO{
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public List<SnsProvider> selectAll() {
		return sqlSessionTemplate.selectList("SnsProvider.selectAll");
	}

	@Override
	public SnsProvider selectByName(String providerName) {
		return sqlSessionTemplate.selectOne("SnsProvider.selectByName", providerName);
	}

}
