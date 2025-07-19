package mall.service;

import java.util.List;

import mall.domain.SubCategory;

public interface SubCategoryService {
	public List<SubCategory> selectAll();
	public List<SubCategory> selectByTopCategoryId(int topcategory_id);
}
