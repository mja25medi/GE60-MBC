package egovframework.edutrack.web.home.etc;

import static egovframework.edutrack.Constants.CHECKPLUS_SITE_CODE;
import static egovframework.edutrack.Constants.CHECKPLUS_SITE_PASSWORD;
import static egovframework.edutrack.Constants.IPIN_SITE_CODE;
import static egovframework.edutrack.Constants.IPIN_SITE_PASSWORD;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import Kisinfo.Check.IPINClient;
import NiceID.Check.CPClient;
import egovframework.edutrack.modules.user.info.service.UsrUserInfoVO;

@Controller
@RequestMapping(value="/home/etc/niceCheck")
public class NiceCheckController {
	private static final String CHECKPLUS_SUCCESS_MAPPING = "/home/etc/niceCheck/checkSuccess"; // 성공시
	private static final String CHECKPLUS_ERROR_MAPPING = "/home/etc/niceCheck/checkFail"; // 실패시
	
	private static final String IPIN_PROCESS_MAPPING = "/home/etc/niceCheck/ipinProcess"; // 중간체크
	private static final String IPIN_RESULT_MAPPING = "/home/etc/niceCheck/ipinResult"; // 최종성공
	
	/*
	 * NICE 휴대폰 인증 팝업 호출
	 */
	@RequestMapping(value="/viewUserCheck")
	public String userCheckMain(UsrUserInfoVO vo, HttpServletRequest request, HttpSession session) throws Exception {
		CPClient cpClient = new CPClient();
		String requestNum = cpClient.getRequestNO(CHECKPLUS_SITE_CODE);
		
		// 리턴 URL 세팅
		String CHECKPLUS_SUCCESS_URL = getReturnUrl(request, CHECKPLUS_SUCCESS_MAPPING);
		String CHECKPLUS_ERROR_URL = getReturnUrl(request, CHECKPLUS_ERROR_MAPPING);
		
		String authType = "M";
		String customize = "";
		
		// plain 데이터 생성
		String sPlainData = "7:REQ_SEQ" + requestNum.getBytes().length + ":" + requestNum +
                "8:SITECODE" + CHECKPLUS_SITE_CODE.getBytes().length + ":" + CHECKPLUS_SITE_CODE +
                "9:AUTH_TYPE" + authType.getBytes().length + ":" + authType +
                "7:RTN_URL" + CHECKPLUS_SUCCESS_URL.getBytes().length + ":" + CHECKPLUS_SUCCESS_URL +
                "7:ERR_URL" + CHECKPLUS_ERROR_URL.getBytes().length + ":" + CHECKPLUS_ERROR_URL +
                "9:CUSTOMIZE" + customize.getBytes().length + ":" + customize;
		
		// 데이터 암호화 처리
		int iReturn = cpClient.fnEncode(CHECKPLUS_SITE_CODE, CHECKPLUS_SITE_PASSWORD, sPlainData);
		checkIReturn(iReturn);
		
		// 세션값 비교 위한 세팅
		session.setAttribute("REQ_SEQ", requestNum);
		
		String encData = cpClient.getCipherData();
	    request.setAttribute("encData", encData);
	    
		return "/home/user/join/checkplus_main";
	}
	
	/*
	 * NICE 휴대폰 인증 성공
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/checkSuccess")
	public String checkSuccess(UsrUserInfoVO vo, HttpServletRequest request, HttpSession session) throws Exception {
		CPClient cpClient = new CPClient();
		
		// 데이터 복호화 처리
		String encodeData = requestReplace(request.getParameter("EncodeData"), "encodeData");
		int iReturn = cpClient.fnDecode(CHECKPLUS_SITE_CODE, CHECKPLUS_SITE_PASSWORD, encodeData);
		checkIReturn(iReturn);
		
		String plainData = cpClient.getPlainData();
    	
    	Map<String,Object> resultMap = cpClient.fnParse(plainData);
		
    	//세션값 비교
		String session_sRequestNumber = (String)session.getAttribute("REQ_SEQ");
        String sRequestNumber = (String)resultMap.get("REQ_SEQ");
        if(!sRequestNumber.equals(session_sRequestNumber)) {
        	throw new Exception("세션값 불일치 오류!");
        }
        
        String name	= (String)resultMap.get("NAME");
        String dupInfo = (String)resultMap.get("DI");
        String birthDate = (String)resultMap.get("BIRTHDATE");
        String gender = (String)resultMap.get("GENDER");
        String mobileNo = (String)resultMap.get("MOBILE_NO");
        String nationalInfo = (String)resultMap.get("NATIONAINFO");
        
        UsrUserInfoVO user = (UsrUserInfoVO) session.getAttribute("joinUsrUserInfoVO");
        if(user == null) {user = new UsrUserInfoVO();}
        user.setUserNm(name);
        user.setDupInfo(dupInfo);
        user.setMobileNo(mobileNo);
        user.setBirth(birthDate);
        user.setSexCd("1".equals(gender) ? "M" : "F");
        user.setPhoneVeriYn("Y");
        user.setForeignYn("1".equals(nationalInfo) ? "Y" : "N");

        // 중복회원 체크 시 dupInfo 체크
        
        session.setAttribute("joinUsrUserInfoVO", user);
		
		return "/home/user/join/checkplus_success";
	}
	
	/*
	 * NICE 휴대폰 인증 실패
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/checkFail")
	public String checkFail(UsrUserInfoVO vo, HttpServletRequest request) throws Exception {
		CPClient cpClient = new CPClient();
		
		String encodeData = requestReplace(request.getParameter("EncodeData"), "encodeData");
		int iReturn = cpClient.fnDecode(CHECKPLUS_SITE_CODE, CHECKPLUS_SITE_PASSWORD, encodeData);
		checkIReturn(iReturn);
		
		String plainData = cpClient.getPlainData();
    	
    	Map<String,Object> resultMap = cpClient.fnParse(plainData);
		
        String errorCode = (String)resultMap.get("ERR_CODE");
        request.setAttribute("errorCode",errorCode);
        
		return "/home/user/join/checkplus_fail";
	}
	
	/*
	 * 아이핀 팝업 호출
	 */
	@RequestMapping(value="/viewUserIpin")
	public String viewUserIpin(UsrUserInfoVO vo, HttpServletRequest request, HttpSession session) throws Exception {
		IPINClient ipClient = new IPINClient();
		String requestNum = ipClient.getRequestNO(IPIN_SITE_CODE);
		
		// 리턴 URL 세팅(중간 체크)
		String IPIN_PROCESS_URL = getReturnUrl(request, IPIN_PROCESS_MAPPING);
		
		// 데이터 암호화 처리
		int iReturn = ipClient.fnRequest(IPIN_SITE_CODE, IPIN_SITE_PASSWORD, requestNum, IPIN_PROCESS_URL);
		checkIReturn(iReturn);
		
		session.setAttribute("REQ_SEQ", requestNum);
		
		String encData = ipClient.getCipherData();
		request.setAttribute("encData", encData);
		
		return "/home/user/join/ipin_main";
	}
	
	/*
	 * 아이핀 중간체크
	 */
	@RequestMapping(value="/ipinProcess")
	public String ipinProcess(UsrUserInfoVO vo, HttpServletRequest request) throws Exception {
		// 리턴 URL 세팅(최종 성공)
		String IPIN_RESULT_URL = getReturnUrl(request, IPIN_RESULT_MAPPING);
		
		String responseData = requestReplace(request.getParameter("enc_data"), "encodeData");
		request.setAttribute("responseData", responseData);
		request.setAttribute("resultUrl", IPIN_RESULT_URL);
		
		return "/home/user/join/ipin_process";
	}
	
	/*
	 * 아이핀 최종성공
	 */
	@RequestMapping(value="/ipinResult")
	public String ipinResult(UsrUserInfoVO vo, HttpServletRequest request, HttpSession session) throws Exception {
		IPINClient ipClient = new IPINClient();
		String responseData = requestReplace(request.getParameter("enc_data"), "encodeData");
		
		// 데이터 복호화 처리
		int iReturn = ipClient.fnResponse(IPIN_SITE_CODE, IPIN_SITE_PASSWORD, responseData);
		checkIReturn(iReturn);
		
		//세션값 비교
		String session_sRequestNumber = (String)session.getAttribute("REQ_SEQ");
        String sRequestNumber = ipClient.getCPRequestNO();
        if(!sRequestNumber.equals(session_sRequestNumber)) {
        	throw new Exception("세션값 불일치 오류!");
        }
        
        String name	= ipClient.getName();
        String birthDate = ipClient.getBirthDate();
        String gender = ipClient.getGenderCode();
        String nationalInfo = ipClient.getNationalInfo();
        String dupInfo = ipClient.getDupInfo();
        
        UsrUserInfoVO user = (UsrUserInfoVO) session.getAttribute("joinUsrUserInfoVO");
        user.setUserNm(name);
        user.setDupInfo(dupInfo);
        user.setBirth(birthDate);
        user.setSexCd("1".equals(gender) ? "M" : "F");
        user.setForeignYn("1".equals(nationalInfo) ? "Y" : "N");
        
        // 중복회원 체크 시 dupInfo 체크
        
        session.setAttribute("joinUsrUserInfoVO", user);
		
		return "/home/user/join/ipin_result";
	}
	
	
	
	private void checkIReturn(int iReturn) throws Exception {
		if(iReturn != 0 && iReturn != 1) {
			throw new Exception("암/복호화 오류! iReturn : " + iReturn);
		}
	}
	
	private String getReturnUrl(HttpServletRequest request, String mappingUrl) {
		return request.getScheme() + "://" + request.getServerName() + mappingUrl;
	}
	
	private String requestReplace(String paramValue, String gubun) {
        String result = "";
        if (paramValue != null) {
        	
        	paramValue = paramValue.replaceAll("<", "&lt;").replaceAll(">", "&gt;");

        	paramValue = paramValue.replaceAll("\\*", "");
        	paramValue = paramValue.replaceAll("\\?", "");
        	paramValue = paramValue.replaceAll("\\[", "");
        	paramValue = paramValue.replaceAll("\\{", "");
        	paramValue = paramValue.replaceAll("\\(", "");
        	paramValue = paramValue.replaceAll("\\)", "");
        	paramValue = paramValue.replaceAll("\\^", "");
        	paramValue = paramValue.replaceAll("\\$", "");
        	paramValue = paramValue.replaceAll("'", "");
        	paramValue = paramValue.replaceAll("@", "");
        	paramValue = paramValue.replaceAll("%", "");
        	paramValue = paramValue.replaceAll(";", "");
        	paramValue = paramValue.replaceAll(":", "");
        	paramValue = paramValue.replaceAll("-", "");
        	paramValue = paramValue.replaceAll("#", "");
        	paramValue = paramValue.replaceAll("--", "");
        	paramValue = paramValue.replaceAll("-", "");
        	paramValue = paramValue.replaceAll(",", "");
        	
        	if(gubun != "encodeData"){
        		paramValue = paramValue.replaceAll("\\+", "");
        		paramValue = paramValue.replaceAll("/", "");
        		paramValue = paramValue.replaceAll("=", "");
        	}        	
        	result = paramValue;
            
        }
        return result;
	}
}
