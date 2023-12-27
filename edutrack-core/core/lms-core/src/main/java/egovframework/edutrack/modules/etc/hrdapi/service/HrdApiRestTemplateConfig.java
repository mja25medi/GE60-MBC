package egovframework.edutrack.modules.etc.hrdapi.service;

import java.nio.charset.Charset;

import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.exception.RestResponseErrorHandler;

@Configuration
public class HrdApiRestTemplateConfig{

	private static final String KEEP_ALIVE_TARGET_HOST_NAME = Constants.HRD_API_DOMAIN;
	/**
	 * Hrd API RestTemplate 설정 
	 */
	@Bean(name = "hrdApiSimpleTemplate")
	public RestTemplate getSimpleRestTemplate() {
		
		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
		
		factory.setReadTimeout(1000*20);//읽기 시간 초과
		factory.setConnectTimeout(1000*20); // 서버에 연결을ㄹ 맺을 때의 타임 아웃
		
		HttpClient httpClient = HttpClientBuilder.create()
				.setMaxConnTotal(100)// 최대 커넥션 개수
				.setMaxConnPerRoute(5) //IP/domain name당 최대 커넥션 갯수
				//.setKeepAliveStrategy(this.connectionKeepAliveStrategy())
				.setKeepAliveStrategy(new ConnectionKeepAliveStrategy() {
		            public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
		                return 1;
		            }
		        })
				.build();
		
		factory.setHttpClient(httpClient); // 동기실행에 사용될 HttpClent 세팅 
		factory.setReadTimeout(1000*20);
		
		RestTemplate restTemplate = new RestTemplate(new BufferingClientHttpRequestFactory(factory));
		restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
		
		return restTemplate;
	}
	
	/**
	 *  RestTemplate 설정 
	 */
	@Bean(name = "hrdApiTemplate")
	public RestTemplate getHrdApiTemplate() {
		RestTemplate instance = this.getSimpleRestTemplate();
		instance.setErrorHandler(new RestResponseErrorHandler());
		return instance;
	}
	
	public ConnectionKeepAliveStrategy connectionKeepAliveStrategy() {
		return new ConnectionKeepAliveStrategy() {
			
			@Override
			public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
				// TODO Auto-generated method stub
				// Honor 'keep-alive' header
		        HeaderElementIterator it = new BasicHeaderElementIterator(
		            response.headerIterator(HTTP.CONN_KEEP_ALIVE));
		        while (it.hasNext()) {
		            HeaderElement he = it.nextElement();
		            String param = he.getName();
		            String value = he.getValue();
		            if (value != null && param.equalsIgnoreCase("timeout")) {
		                try {
		                    return Long.parseLong(value) * 1000;
		                } catch (NumberFormatException ignore) {
		                }
		            }
		        }

		        HttpHost target = (HttpHost) context.getAttribute(HttpClientContext.HTTP_TARGET_HOST);
		        if (KEEP_ALIVE_TARGET_HOST_NAME.equalsIgnoreCase(target.getHostName())) {
		            return 5 ;
		        } else {
		            return 30 ;
		        }
			}
		};
	}
}
