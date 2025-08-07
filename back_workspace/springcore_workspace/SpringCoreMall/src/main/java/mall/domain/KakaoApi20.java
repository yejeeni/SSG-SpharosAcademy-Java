package mall.domain;

import com.github.scribejava.core.builder.api.DefaultApi20;

/**
 * scribejava api는 구글을 지원하지만, 네이버, 카카오는 지원하지 않으므로 직접 구현 
 */
public class KakaoApi20 extends DefaultApi20{
	
	private static final String AUTHORIZE_URL = "https://kauth.kakao.com/oauth/authorize";
	private static final String ACCESS_TOKEN_URL_STRING = "https://kauth.kakao.com/oauth/token";
	
	protected KakaoApi20() {	}
	private static class InstanceHolder{
		private static final KakaoApi20 INSTANCE = new KakaoApi20();
	}
	
	public static KakaoApi20 instance() {
		return InstanceHolder.INSTANCE;
	}

	@Override
	public String getAccessTokenEndpoint() {
		return ACCESS_TOKEN_URL_STRING;
	}

	@Override
	protected String getAuthorizationBaseUrl() {
		return AUTHORIZE_URL;
	}

}
