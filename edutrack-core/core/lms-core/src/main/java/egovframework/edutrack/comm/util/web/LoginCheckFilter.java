package egovframework.edutrack.comm.util.web;


import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.util.web.UserBroker;

/**
 * 중복로그인 체크
 * Login Check Filter
 */

public class LoginCheckFilter implements Filter {

	/**
	 * init
	 */
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	/**
	 * destroy
	 */
	public void destroy() {

	}


	/**
	 * 로그인 중복 체크 필터
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest)request;

		try {
			String userId 		= UserBroker.getUserId(req);
			String userType		= UserBroker.getUserType(req);
			String sessionId 	= req.getSession().getId();

			String conPath = req.getRequestURI(); // 호출 URI (필요시 필터링 제외조건에 사용)
			boolean check = true;
			
			// 테스트 계정 필터링 제외
			if(userId.equals("mediadmin") || userId.equals("medistu1") || userId.equals("medistu2") || userId.equals("medistu3") ||
			   userId.equals("meditch1") || userId.equals("meditch2") || userId.equals("meditch3") ) {
				check = false;
			}
			
			if (Constants.REDIS_CHECK_YN.equals("Y") && check == true && !userId.equals("")) {

				String sid = RedisUtil.getValue(Constants.REDIS_NAMESPACE+":SID:"+userId);

				// 중복 로그인 시
				if (sid != null && !sessionId.equals(sid)) {
					// 세션 초기화
					Enumeration e = req.getSession().getAttributeNames();
					while(e.hasMoreElements()){
						String key = e.nextElement().toString();
						req.getSession().setAttribute(key, null);
					}
					
					req.getSession().setAttribute("MULTICON_STATE", "LOGOUT");
				}
			}
		}
		catch (Exception e) { }


		// Pass control on to the next filter
		chain.doFilter(request, response);
	}

}
