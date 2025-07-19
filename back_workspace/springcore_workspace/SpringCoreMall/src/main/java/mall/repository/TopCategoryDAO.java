package mall.repository;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import mall.domain.TopCategory;

@Mapper
public interface TopCategoryDAO {
	public List<TopCategory> selectAll();
	public TopCategory select(int topcategory_id);
}
