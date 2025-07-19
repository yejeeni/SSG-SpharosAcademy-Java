package mall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mall.domain.Color;
import mall.repository.ColorDAO;

@Service
public class ColorServiceImpl implements ColorService{

	@Autowired
	ColorDAO colorDAO;
	
	@Override
	public List<Color> selectAll() {
		return colorDAO.selectAll();
	}

}
