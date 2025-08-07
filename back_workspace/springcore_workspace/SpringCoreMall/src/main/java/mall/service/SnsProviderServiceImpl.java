package mall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mall.domain.SnsProvider;
import mall.repository.SnsProviderDAO;

@Service
public class SnsProviderServiceImpl implements SnsProviderService{
	
	@Autowired
	private SnsProviderDAO snsProviderDAO;

	@Override
	public List<SnsProvider> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SnsProvider selectByName(String providerName) {
		return snsProviderDAO.selectByName(providerName);
	}

}
