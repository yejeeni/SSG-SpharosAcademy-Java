package mall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mall.domain.Size;
import mall.repository.SizeDAO;

@Service
public class SizeServiceImpl implements SizeService{

	@Autowired
	SizeDAO sizeDAO;

	@Override
	public List<Size> selectAll() {
		return sizeDAO.selectAll();
	}
	
	
}
