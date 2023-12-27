package egovframework.edutrack.comm.web;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.util.web.LocaleUtil;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.modules.main.service.MainVO;

@Controller
public class GenericController {

    /** MessageSource */
    @Resource(name = "messageSource")
    private MessageSource messageSource;
    
    protected static Logger log = Logger.getLogger(GenericController.class);
    
    public GenericController() {
    	
    }
    
	/**
	 * 작업결과 메시지 토큰 설정 (세션기반) 리퀘스트 기반으로 변경할 수도 있음.
	 *
	 * @param request
	 * @param resultMessageAlert
	 */
	public void setAlertMessage(HttpServletRequest request, String resultMessageAlert) {
		HttpSession session = request.getSession();
		session.setAttribute("ALERT_MESSAGE", resultMessageAlert);
	}
	
	/**
	 * 작업결과 메시지 토큰 재설정
	 * @param request
	 */
	public void resetMessageToken(HttpServletRequest request) {
		HttpSession session = request.getSession();

		String resultMessageKey = (String) session.getAttribute("RESULT_MESSAGE_ALERT");

		if (resultMessageKey != null) {
			request.setAttribute("ALERT_MESSAGE", resultMessageKey);
			session.setAttribute("ALERT_MESSAGE", "null");
		} else {
			request.setAttribute("ALERT_MESSAGE", "null");
			session.setAttribute("ALERT_MESSAGE", "null");
		}
	}	
    
	/**
	 * 메시지 반환
	 * @param HttpServletRequest request
	 * @param msgkey
	 * @return
	 */
	public String getMessage(HttpServletRequest request, String msgkey) {
		Locale locale = LocaleUtil.getLocale(request);		
		String message = "";
 		try {
			message = messageSource.getMessage(msgkey, null, "", locale);
			if(message == null) {
				message = msgkey;
			}
 		}
 		catch (Exception e) { 
 			return msgkey;
 		}
 		return message;
	}
	
	/**
	 * 메시지 반환
	 * @param HttpServletRequest request
	 * @param msgkey
	 * @return
	 */
	public String getMessage(HttpServletRequest request, String msgkey, String[] args) {
		Locale locale = LocaleUtil.getLocale(request);		
		String message = "";
 		try {
			message = messageSource.getMessage(msgkey, args, "", locale);
			if(message == null) {
				message = msgkey;
			}
 		}
 		catch (Exception e) { 
 			return msgkey;
 		}
 		return message;
	}	
	
	/**
	 * Response에 인스턴스를 json으로 변경해서 출력. 메시지 처리를 하지 않는다.
	 * @param response 응답 인스턴스
	 * @param obj json으로 보내고자 하는 인스턴스
	 */
	public static ModelAndView responseJson(Map<String, Object> resultMap) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addAllObjects(resultMap);
		modelAndView.setViewName("jonView");
		return modelAndView;
	}

	/**
	 * Response에 인스턴스를 json으로 변경해서 출력. 메시지 처리를 하지 않는다.
	 * @param response 응답 인스턴스
	 * @param obj json으로 보내고자 하는 인스턴스
	 */
	public static ModelAndView responseJson(Object obj) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject(obj);
		modelAndView.setViewName("jsonView");
		return modelAndView;
	}
	
	/**
	 * 공통 vo에 로그인세션 관련 데이터를 set 해준다
	 * @param Object vo : set 해줄 vo 객체 
	 * @param HttpServletRequest req : request 객체
	 */
	public void commonVOProcessing(Object vo, HttpServletRequest request) {
		try {
			HttpSession session = request.getSession();
			
			Class<?> actualEditable = vo.getClass();
			PropertyDescriptor[] targetPds = BeanUtils.getPropertyDescriptors(actualEditable);
			
			for (PropertyDescriptor targetPd : targetPds) {
				if (targetPd.getWriteMethod() != null) {
					Method writeMethod = targetPd.getWriteMethod();
					if (writeMethod.getName().equals("setRegNo")) {
						writeMethod.invoke(vo, session.getAttribute(Constants.LOGIN_USERNO));
					} else if (writeMethod.getName().equals("setModNo")) {
						writeMethod.invoke(vo, session.getAttribute(Constants.LOGIN_USERNO));
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 페이징 캐쉬를 비활성화 합니다. 액션에서 캐쉬페이지를 보여주지 말아야 하는 구간에서 사용합니다.
	 * @param response
	 */
	public void setCachePagingDisable(HttpServletResponse response) {
		response.setHeader("cache-control", "no-cache");
		response.setHeader("expires", "0");
		response.setHeader("pragma", "no-cache");
	}	
}
