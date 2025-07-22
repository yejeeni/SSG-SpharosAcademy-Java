package mall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mall.domain.ProductColor;
import mall.repository.ProductColorDAO;

@Service
public class ProductColorServiceImpl implements ProductColorService{
	
	@Autowired
	private ProductColorDAO productColorDAO;


}
