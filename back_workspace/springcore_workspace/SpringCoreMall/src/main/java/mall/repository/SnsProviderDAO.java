package mall.repository;

import java.util.List;

import mall.domain.SnsProvider;

public interface SnsProviderDAO {
	public List<SnsProvider> selectAll();
	public SnsProvider selectByName(String providerName);
}
