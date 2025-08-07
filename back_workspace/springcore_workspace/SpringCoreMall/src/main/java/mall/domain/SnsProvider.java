package mall.domain;

import java.util.List;

import lombok.Data;

@Data
public class SnsProvider {
	    private int snsProviderId;

	    private String providerName;

	    private List<Member> members;
}
