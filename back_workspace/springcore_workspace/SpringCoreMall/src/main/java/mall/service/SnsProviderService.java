package mall.service;

import java.util.List;

import mall.domain.SnsProvider;

public interface SnsProviderService {
	public List<SnsProvider> selectAll();
	public SnsProvider selectByName(String providerName);
}
