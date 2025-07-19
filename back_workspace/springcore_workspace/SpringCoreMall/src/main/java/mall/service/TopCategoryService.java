package mall.service;

import java.util.List;

import mall.domain.TopCategory;

public interface TopCategoryService {
	public List<TopCategory> selectAll();
	public TopCategory select(int topcategory_id);
}
