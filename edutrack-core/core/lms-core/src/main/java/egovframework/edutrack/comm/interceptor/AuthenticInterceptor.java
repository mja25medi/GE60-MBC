package egovframework.edutrack.comm.interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import egovframework.edutrack.comm.util.security.OrganizationUtil;
import egovframework.edutrack.comm.util.security.SecurityUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.util.web.ValidationUtils;

/**
 * 인증여부 체크 인터셉터
 * @author 공통서비스 개발팀 서준식
 * @since 2011.07.01
 * @version 1.0
 * @see
 *  
 * <pre>
 * << 개정이력(Modification Information) >>
 * 
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2011.07.01  서준식          최초 생성 
 *  2011.09.07  서준식          인증이 필요없는 URL을 패스하는 로직 추가
 *  </pre>
 */


public class AuthenticInterceptor extends HandlerInterceptorAdapter {
	
	/**
	 * Controller 실행전 실행됨.
	 * 
	 * 권한 체크
	 * 기관 정보 설정(홈페이지용)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		/** 브라우저 , 호스트 변조 접근 방지 시작 2015.12.15 */
		boolean chkUserAgentHack = false;
		if(ValidationUtils.isNotEmpty(request.getHeader("User-Agent"))) {
			if(request.getHeader("User-Agent").indexOf("--") != -1) chkUserAgentHack = true;
			if(request.getHeader("User-Agent").indexOf("' AND ") !=-1) chkUserAgentHack = true;
			if(request.getHeader("User-Agent").indexOf("' OR ") !=-1) chkUserAgentHack = true;
			if(request.getHeader("User-Agent").indexOf("\" AND ") !=-1) chkUserAgentHack = true;
			if(request.getHeader("User-Agent").indexOf("\" OR ") !=-1) chkUserAgentHack = true;
			if(request.getHeader("User-Agent").indexOf(" AND ") !=-1) chkUserAgentHack = true;
			if(request.getHeader("User-Agent").indexOf(" OR ") !=-1) chkUserAgentHack = true;
		}
		if(ValidationUtils.isNotEmpty(request.getHeader("Host"))) {
			if(request.getHeader("Host").indexOf("--") != -1) chkUserAgentHack = true;
			if(request.getHeader("Host").indexOf("' AND ") !=-1) chkUserAgentHack = true;
			if(request.getHeader("Host").indexOf("' OR ") !=-1) chkUserAgentHack = true;
			if(request.getHeader("Host").indexOf("\" AND ") !=-1) chkUserAgentHack = true;
			if(request.getHeader("Host").indexOf("\" OR ") !=-1) chkUserAgentHack = true;
			if(request.getHeader("Host").indexOf(" AND ") !=-1) chkUserAgentHack = true;
			if(request.getHeader("Host").indexOf(" OR ") !=-1) chkUserAgentHack = true;
		}
		
		//cmd 파라미터 체크(없거나 50자 이상일경우 out)
		if(ValidationUtils.isEmpty(request.getParameter("cmd"))) {
//			response.sendRedirect("/");
//			return false;
		}else{
			String cmd = request.getParameter("cmd").toString();
			if("".equals(cmd) || cmd.length() > 50)
			{
				response.sendRedirect("/");
				return false;
			}
		}
		
		if (chkUserAgentHack) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Invalid access.");
			return false;
		}
		//--기관 정보 관련 셋팅
		/*
		 * String Action = StringUtil.split(request.getRequestURI(), ";jsessionid=")[0];
		 * // /adm/user/userinfo.do 형태로 들어온다. if(needOrganizationSet(Action)) {
		 * OrganizationUtil.organiztionCheckRunner(request, response); }
		 */		
		OrganizationUtil.organiztionCheckRunner(request, response);

		
		
		//--권한 검사 하여 Exception 발생 시킴.
		SecurityUtil.authorizationCheck(request, response);

		return true;
	}
	
	private boolean needOrganizationSet(String Action) {
		boolean result = false;
		result = Action.matches("/decorator")
				|| Action.matches(".*/*indexMain.*")
				|| Action.matches(".*/*lectureMain.*")
				|| Action.matches(".*/*loginMain.*")
				|| Action.matches(".*/*loginForm.*")
				|| Action.matches(".*/*goMenuPage.*")
				|| Action.matches(".*/*indexChgLang.*")
				|| Action.matches(".*/*goLecture.*");
		
		return result;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView model) throws Exception {
		/*try {
			if(ValidationUtils.isNotEmpty(model)) {
				Set key  = model.getModel().keySet();
				for (Iterator iterator = key.iterator(); iterator.hasNext();) {
					String keyName = (String) iterator.next();
					Class<?> clazz = (Class)model.getModel().get(keyName).getClass();
					DefaultVO defaultVO = (DefaultVO)clazz.newInstance();
					defaultVO.setRegNo(UserBroker.getUserNo(request));
					defaultVO.setModNo(UserBroker.getUserNo(request));
					request.
				}
				
				
				
				Set key  = model.getModel().keySet();
				for (Iterator iterator = key.iterator(); iterator.hasNext();) {
					String keyName = (String) iterator.next();
					if(!keyName.startsWith("org.") && keyName.endsWith("VO")) {
						
						PropertyDescriptor[] targetPds = BeanUtils.getPropertyDescriptors(clazz);
						for (PropertyDescriptor targetPd : targetPds) {
							Method readMethod = targetPd.getReadMethod();
							DefaultVO dfltVO = (DefaultVO)readMethod.invoke(clazz, (Object[]) null);
							if(ValidationUtils.isNotNull(dfltVO)) {
								dfltVO.setRegNo(UserBroker.getUserNo(request));
								dfltVO.setModNo(UserBroker.getUserNo(request));
							}
						}
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	/**
	 * This implementation is empty.
	 */
	/*@Override
	public void afterCompletion(
			HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		String msg = ex.toString();
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ex : "+ex);
	}*/
	
	
	
	/**
	 * 클래스의 원형중에 원하는 클래스가 있는지 찾아서 결과를 반환한다.
	 * @param clazz 대상 클래스
	 * @param findClazz 찾고자 하는 원형 클래스
	 * @return
	 */
	/*private static boolean instanceOfClass(Class<?> clazz, Class<?> findClazz) {
		if(clazz == null) {
			return false;
		} else if(clazz.equals(findClazz)) {
			return true;
		} else {
			return instanceOfClass(clazz.getSuperclass(), findClazz);
		}
	}*/	
}