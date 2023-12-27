package egovframework.edutrack.web.home.user;

import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;

import egovframework.edutrack.Constants;

@Component
public class KakaoLoginBO {

	/* 로컬
	private final static String KAKAO_CLIENT_ID = "6ffb6b7e57b80d59cced1d5eebb3ab4a";
	private final static String KAKAO_CLIENT_SECRET = "b7YogqjTZcBWXRL5KbeqAp15BO55YHmP";
	private final static String KAKAO_REDIRECT_URI = "http://local.co.kr/home/user/kakaoLoginCallback";
	*/
	/* 운영 */
	private final static String KAKAO_CLIENT_ID = Constants.framework.getString("framework.key.sns.kakao");
	private final static String KAKAO_CLIENT_SECRET = Constants.framework.getString("framework.secret.sns.kakao");
	private final static String KAKAO_REDIRECT_URI = Constants.framework.getString("framework.callback.sns.kakao");
	
	
	private final static String SESSION_STATE = "kakao_oauth_state";
	private final static String PROFILE_API_URL = "https://kapi.kakao.com/v2/user/me";

	public String getAuthorizationUrl(HttpSession session) {
		String state = generateRandomString();
		setSession(session, state);
		OAuth20Service oauthService = new ServiceBuilder()
				.apiKey(KAKAO_CLIENT_ID)
				.apiSecret(KAKAO_CLIENT_SECRET)
				.callback(KAKAO_REDIRECT_URI)
				.state(state).build(KakaoLoginApi.instance());
		return oauthService.getAuthorizationUrl();
	}

	public OAuth2AccessToken getAccessToken(HttpSession session, String code, String state) throws Exception {
		String sessionState = getSession(session);
		if (StringUtils.pathEquals(sessionState, state)) {
			OAuth20Service oauthService = new ServiceBuilder()
					.apiKey(KAKAO_CLIENT_ID)
					.apiSecret(KAKAO_CLIENT_SECRET)
					.callback(KAKAO_REDIRECT_URI)
					.state(state).build(KakaoLoginApi.instance());
			OAuth2AccessToken accessToken = oauthService.getAccessToken(code);
			return accessToken;
		}
		return null;
	}

	public String getUserProfile(OAuth2AccessToken oauthToken) throws Exception {
		OAuth20Service oauthService = new ServiceBuilder()
				.apiKey(KAKAO_CLIENT_ID)
				.apiSecret(KAKAO_CLIENT_SECRET)
				.callback(KAKAO_REDIRECT_URI)
				.build(KakaoLoginApi.instance());
		OAuthRequest request = new OAuthRequest(Verb.GET, PROFILE_API_URL, oauthService);
		oauthService.signRequest(oauthToken, request);
		Response response = request.send();
		return response.getBody();
	}
	
	private String generateRandomString() {
		return UUID.randomUUID().toString();
	}

	private void setSession(HttpSession session, String state) {
		session.setAttribute(SESSION_STATE, state);
	}

	private String getSession(HttpSession session) {
		return (String) session.getAttribute(SESSION_STATE);
	}
}