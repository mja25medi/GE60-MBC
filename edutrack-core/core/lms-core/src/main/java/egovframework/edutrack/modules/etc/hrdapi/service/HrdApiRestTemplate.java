package egovframework.edutrack.modules.etc.hrdapi.service;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import egovframework.edutrack.modules.log.apisync.service.LogApiSyncVO;

@Component
public class HrdApiRestTemplate {
	
	@Autowired
	@Qualifier("hrdApiTemplate")
	private RestTemplate restTemplate;

	public <T> ResponseEntity<T> exchange(HrdApiVO vo, URI url, Object requestBody,
			Class<T> responseType,LogApiSyncVO lasvo) throws Exception {

		RequestEntity<Object> request = RequestEntity.method(HttpMethod.POST, url)
				.contentType(MediaType.APPLICATION_JSON)
				.header("X-TQIAPI-HEADER", vo.getApiKey())//"지급받은 API KEY"
				.header("X-TQIAPI-USER", vo.getUserId())//"emon 웹 사이트 아이디"
				.body(requestBody);
		
		return restTemplate.exchange(request, responseType);
	}

	public HrdApiRestTemplate() {
		super();
	}

	
}
