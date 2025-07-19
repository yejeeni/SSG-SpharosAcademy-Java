package mall.repository;

import java.util.List;

import mall.domain.SubCategory;

public interface SubCategoryDAO {
	public List<SubCategory> selectAll();
	public List<SubCategory> selectByTopCategoryId(int topcategory_id);
}
