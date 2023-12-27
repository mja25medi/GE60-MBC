package egovframework.edutrack.comm.util.web;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import egovframework.edutrack.Constants;


public class HttpRequestUtil {
	
	private HttpRequestUtil() {}	// 기본 생성자 차단

	/**
	 * 전체 URI 문자열에서 호스트 URL까지만 추출한다.(http://aaa.bbb.ccom)
	 * @param url
	 * @return
	 */
	public static String getServerURL(String url) {
		return url.substring(0, url.indexOf("/", url.indexOf("/")+2));
	}

	/**
	 * Request의 요청 URL에서 호스트 URL까지만 추출한다.(http://aaa.bbb.ccom)
	 * @param url
	 * @return
	 */
	public static String getServerURL(HttpServletRequest request) {
		return getServerURL(request.getRequestURL().toString());
	}
	
	
	/**
	 * Request의 요청을 URLBuilder로 변환해서 반환한다.<br>
	 * Context 경로를 제거한다.
	 * @param request
	 * @return
	 */
	public static URLBuilder requestToUrlBuilder(HttpServletRequest request) {
		@SuppressWarnings("unchecked")
		Enumeration<String> keys = request.getParameterNames();
		URLBuilder urlBuilder = new URLBuilder(request.getRequestURI().replaceAll(Constants.CONTEXT_ROOT, ""));
		
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			urlBuilder.addParameter(key, request.getParameter(key));
		}
		
		return urlBuilder;
	}

	/**
	 * Request의 파라매터를 추출해서 URLBuilder에 추가한다.
	 * @param request 파라매터가 담긴 request
	 * @param urlBuilder 파라매터를 추가할 UrlBuilder
	 */
	public static void requestParamToUrlBuilder(HttpServletRequest request, URLBuilder urlBuilder) {
		@SuppressWarnings("unchecked")
		Enumeration<String> keys = request.getParameterNames();
		
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			urlBuilder.addParameter(key, request.getParameter(key));
		}
	}
	
	public static HttpServletRequest getRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
	}
}
