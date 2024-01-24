package egovframework.edutrack.web.home.user;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.github.scribejava.core.model.OAuth2AccessToken;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.exception.ServiceProcessException;
import egovframework.edutrack.comm.service.AbstractResult;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.service.SysCodeMemService;
import egovframework.edutrack.comm.service.SysMenuMemService;
import egovframework.edutrack.comm.util.security.CryptoUtil;
import egovframework.edutrack.comm.util.web.BeanUtils;
import egovframework.edutrack.comm.util.web.DateTimeUtil;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.RedisUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.URLBuilder;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.atalk.AtalkVO;
import egovframework.edutrack.modules.atalk.service.AtalkService;
import egovframework.edutrack.modules.board.qna.service.NonMemQnaVO;
import egovframework.edutrack.modules.log.login.service.LogSysLoginLogService;
import egovframework.edutrack.modules.log.login.service.LogSysLoginLogVO;
import egovframework.edutrack.modules.log.logintry.service.LogUserLoginTryLogService;
import egovframework.edutrack.modules.log.logintry.service.LogUserLoginTryLogVO;
import egovframework.edutrack.modules.log.message.service.LogMsgLogService;
import egovframework.edutrack.modules.log.message.service.LogMsgLogVO;
import egovframework.edutrack.modules.log.message.service.LogMsgTransLogVO;
import egovframework.edutrack.modules.log.userconn.service.LogUserConnLogService;
import egovframework.edutrack.modules.log.userconn.service.LogUserConnLogVO;
import egovframework.edutrack.modules.main.service.MainVO;
import egovframework.edutrack.modules.org.code.service.OrgCodeService;
import egovframework.edutrack.modules.org.code.service.OrgCodeVO;
import egovframework.edutrack.modules.org.config.service.OrgUserInfoCfgService;
import egovframework.edutrack.modules.org.config.service.OrgUserInfoCfgVO;
import egovframework.edutrack.modules.org.domain.service.OrgDomainService;
import egovframework.edutrack.modules.org.domain.service.OrgDomainVO;
import egovframework.edutrack.modules.org.emailtpl.service.OrgEmailTplService;
import egovframework.edutrack.modules.org.info.service.OrgOrgInfoService;
import egovframework.edutrack.modules.org.info.service.OrgOrgInfoVO;
import egovframework.edutrack.modules.org.menu.service.OrgMenuVO;
import egovframework.edutrack.modules.org.page.service.OrgPageService;
import egovframework.edutrack.modules.org.page.service.OrgPageVO;
import egovframework.edutrack.modules.system.code.service.SysCodeVO;
import egovframework.edutrack.modules.system.config.service.SysCfgService;
import egovframework.edutrack.modules.system.config.service.SysCfgVO;
import egovframework.edutrack.modules.system.menu.service.SysMenuService;
import egovframework.edutrack.modules.user.dept.service.UsrDeptInfoService;
import egovframework.edutrack.modules.user.dept.service.UsrDeptInfoVO;
import egovframework.edutrack.modules.user.info.service.UsrLoginService;
import egovframework.edutrack.modules.user.info.service.UsrLoginVO;
import egovframework.edutrack.modules.user.info.service.UsrUserAuthGrpVO;
import egovframework.edutrack.modules.user.info.service.UsrUserInfoService;
import egovframework.edutrack.modules.user.info.service.UsrUserInfoVO;
import egovframework.edutrack.notification.MessageNotificationException;

@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/home/user")
public class UserHomeController extends GenericController {
	
	private static final String	INDEX_MAIN_URL		= "/home/main/indexMain";
	
	private final static String KEY_SNS_FACEBOOK = Constants.framework.getString("framework.key.sns.facebook");
	private final static String KEY_SNS_KAKAO = Constants.framework.getString("framework.key.sns.kakao");
	private final static String KEY_SNS_GOOGLE = Constants.framework.getString("framework.key.sns.google");
	private final static String KEY_SNS_NAVER = Constants.framework.getString("framework.key.sns.naver");
	private final static String KEY_SNS_DAUM = Constants.framework.getString("framework.key.sns.daum");
	
	private final static String TUTOR_DOMAIN = Constants.PRODUCT_SUB_DOMAIN_TUTOR;
	
	@Autowired @Qualifier("usrUserInfoService")
	private UsrUserInfoService 		usrUserInfoService;
	
	@Autowired @Qualifier("usrLoginService")
	private UsrLoginService 		usrLoginService;
	
	@Autowired @Qualifier("logSysLoginLogService")
	private LogSysLoginLogService 	logSysLoginLogService;
	
	@Autowired @Qualifier("logUserLoginTryLogService")
	private LogUserLoginTryLogService 	logUserLoginTryLogService;
	
	@Autowired @Qualifier("logUserConnLogService")
	private LogUserConnLogService 	logUserConnLogService;
	
	@Autowired @Qualifier("sysMenuMemService")
	private SysMenuMemService 		sysMenuMemService;
	
	@Autowired @Qualifier("sysMenuService")
	private SysMenuService 			sysMenuService;
	
	@Autowired @Qualifier("sysCodeMemService")
	private SysCodeMemService 		sysCodeMemService;
	
	@Autowired @Qualifier("orgOrgInfoService")
	private OrgOrgInfoService 		orgOrgInfoService;

	@Autowired @Qualifier("orgUserInfoCfgService")
	private OrgUserInfoCfgService	orgUserInfoCfgService;

	@Autowired @Qualifier("sysCfgService")
	private SysCfgService			sysCfgService;

	@Autowired @Qualifier("orgCodeService")
	private OrgCodeService			orgCodeService;

	@Autowired
	private LogMsgLogService	messageService;

	@Autowired @Qualifier("orgPageService")
	private OrgPageService 		orgPageService;
	
	@Autowired @Qualifier("usrDeptInfoService")
	private UsrDeptInfoService	usrDeptInfoService;

	@Autowired
	private OrgEmailTplService	emailTplService;
	
	@Autowired @Qualifier("atalkService")
	private AtalkService 		atalkService;
	
	@Autowired @Qualifier("orgDomainService")
	private OrgDomainService 		orgDomainService;
	
	/* NaverLoginBO */
	/** 네이버 Login service*/
	@Resource(name = "naverLoginBO")
	private NaverLoginBO naverLoginBO;
	private String apiResult = null;
	
	@Autowired @Qualifier("naverLoginBO")
	private void setNaverLoginBO(NaverLoginBO naverLoginBO) {
		this.naverLoginBO = naverLoginBO;
	}
	
	/* KakaoLoginBO */
	@Autowired
	private KakaoLoginBO kakaoLoginBO;
	
	/**
	 * 세션에서 가입처리중이던 회원 정보를 추출해서 반환.<br>
	 * 만약 세션에 해당 정보가 없을 경우 예외를 반환한다.
	 * @param request
	 * @return
	 */
	private UsrUserInfoVO getSessionAttrUserInfoWithAssertCheck(HttpServletRequest request) {
		UsrUserInfoVO usrUserInfoVO = (UsrUserInfoVO)request.getSession().getAttribute("joinUsrUserInfoVO");
//		Assert.notNull(UsrUserInfoVO, getMessage(request, "user", "user.message.login.nosession"));
		return usrUserInfoVO;
	}

	private void setSessionUserInfo(HttpServletRequest request, UsrUserInfoVO UsrUserInfoVO) {
		request.getSession().setAttribute("joinUsrUserInfoVO", UsrUserInfoVO);
	}
	
	
	/**
	 * 소셜 로그인 추가
	 */
	@RequestMapping(value="/loginForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String loginForm(Model model, HttpSession session) {
		/* 네이버아이디로 인증 URL을 생성하기 위하여 naverLoginBO클래스의 getAuthorizationUrl메소드 호출 */
		String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);
		model.addAttribute("naverAuthUrl", naverAuthUrl);
		
		String kakaoAuthUrl = kakaoLoginBO.getAuthorizationUrl(session);
		model.addAttribute("kakaoAuthUrl", kakaoAuthUrl);
		
		session.removeAttribute("snsJoin");
		return "/home/user/info/login_form";
	}
	
	/**
	 * 카카오 로그인 콜백
	 * @throws Exception 
	 */
	@RequestMapping(value = "/kakaoLoginCallback", method = {RequestMethod.GET, RequestMethod.POST })
	public String kakaoLoginCallback(Model model, UsrLoginVO vo, @RequestParam String code, @RequestParam String state, HttpSession session, 
			@RequestParam Map<String, Object> paramMap, HttpServletRequest request, HttpServletResponse response) throws Exception {
		OAuth2AccessToken oauthToken;
		oauthToken = kakaoLoginBO.getAccessToken(session, code, state);
		apiResult = kakaoLoginBO.getUserProfile(oauthToken);
		System.out.println(apiResult);
		
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObj;
		
		jsonObj = (JSONObject) jsonParser.parse(apiResult);
		JSONObject kakao_account = (JSONObject) jsonObj.get("kakao_account");
		JSONObject response_obj = (JSONObject) kakao_account.get("profile");
		
		String nickname = (String) response_obj.get("nickname");
		if(jsonObj != null && !jsonObj.isEmpty()) {
			String id = jsonObj.get("id").toString();
			paramMap.put("snsKey", id);
			paramMap.put("snsDiv", "K");
			int joinChk = usrUserInfoService.oauthCheckId(paramMap);
			
			if(joinChk > 0) {
				String result = "SUCCESS";
				String orgCd = UserBroker.getUserOrgCd(request);
				
				UsrUserInfoVO uuiVO = new UsrUserInfoVO();
				uuiVO.setOrgCd(orgCd);
				String mainMcd = "MC00000000";
				String goMcd = StringUtil.nvl(vo.getGoMcd(), mainMcd);
				String goUrl = vo.getGoUrl();
				
				boolean chkUserAgentHack = false;
				if(StringUtil.nvl(goMcd).indexOf("' AND ") !=-1) chkUserAgentHack = true;
				if(StringUtil.nvl(goMcd).indexOf("' OR ") !=-1) chkUserAgentHack = true;
				if(StringUtil.nvl(goMcd).indexOf("\" AND ") !=-1) chkUserAgentHack = true;
				if(StringUtil.nvl(goMcd).indexOf("\" OR ") !=-1) chkUserAgentHack = true;
				if(StringUtil.nvl(goMcd).indexOf(" AND ") !=-1) chkUserAgentHack = true;
				if(StringUtil.nvl(goMcd).indexOf(" OR ") !=-1) chkUserAgentHack = true;
				if(StringUtil.nvl(goUrl).indexOf("' AND ") !=-1) chkUserAgentHack = true;
				if(StringUtil.nvl(goUrl).indexOf("' OR ") !=-1) chkUserAgentHack = true;
				if(StringUtil.nvl(goUrl).indexOf("\" AND ") !=-1) chkUserAgentHack = true;
				if(StringUtil.nvl(goUrl).indexOf("\" OR ") !=-1) chkUserAgentHack = true;
				if(StringUtil.nvl(goUrl).indexOf(" AND ") !=-1) chkUserAgentHack = true;
				if(StringUtil.nvl(goUrl).indexOf(" OR ") !=-1) chkUserAgentHack = true;
				if (chkUserAgentHack) {
					// log.error(message);
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "ERROR");
					return null;
				}
				
				uuiVO.setSnsDiv("K"); //KAKAO
				uuiVO.setSnsKey(id);
				
				//-- 로그인 시도 DB에 기록
				LogUserLoginTryLogVO lultlvo = new LogUserLoginTryLogVO();
				lultlvo.setUserId(uuiVO.getUserId());
				lultlvo.setBrowserInfo(request.getHeader("User-Agent"));
				lultlvo.setConnIp(request.getRemoteAddr());
				
				try {
					uuiVO = usrUserInfoService.viewForLogin(uuiVO); //로그인
					
					if("Y".equals(uuiVO.getLoginUseYn())) {
						//-- 회원의 마지막 접속 정보 등록
						usrLoginService.editLastLogin(uuiVO);

						//--홈페이지 로그인 로그 기록 남김
						LogSysLoginLogVO lldto = new LogSysLoginLogVO();
						lldto.setOrgCd(orgCd);
						logSysLoginLogService.add(lldto);

						lultlvo.setLoginSuccYn("Y");
						lultlvo.setUserNo(uuiVO.getUserNo());
					}
					
					// 세션 정보를 셋팅해 준다.
					request.getSession().setAttribute(Constants.LOGIN_USERID, uuiVO.getUserId());
					request.getSession().setAttribute(Constants.LOGIN_USERNO, uuiVO.getUserNo());
					request.getSession().setAttribute(Constants.LOGIN_USERTYPE, uuiVO.getWwwAuthGrpCd());
					request.getSession().setAttribute(Constants.LOGIN_USERNAME, uuiVO.getUserNm());
					request.getSession().setAttribute(Constants.LOGIN_ADMTYPE, uuiVO.getAdminAuthGrpCd());
					request.getSession().setAttribute(Constants.LOGIN_MNGTYPE, uuiVO.getMngAuthGrpCd());
					request.getSession().setAttribute(Constants.LOGIN_DEPTCD, uuiVO.getDeptCd());
					
					logUserConnLogService.add(request);
					
					
				} catch (DataAccessException e) {
					result = "ERROR";
				} finally {
					
					model.addAttribute("memberClass", "N");
					model.addAttribute("message", "로그인되었습니다.");
					model.addAttribute("url", "/home/main/indexMain");
				}

				return "/home/user/join/snsCallback";
			} else {
				//신규 가입
				session.setAttribute("snsKey", paramMap.get("snsKey"));
				session.setAttribute("snsDiv", "K");
				session.setAttribute("snsJoin", "Y");
				
				/*
				 * String birthyear = (String)response_obj.get("birthyear"); String birthday =
				 * (String)response_obj.get("birthday"); String mobile =
				 * (String)response_obj.get("phone_number"); String name =
				 * (String)response_obj.get("name");
				 * 
				 * session.setAttribute("name", name); session.setAttribute("mobile", mobile);
				 * session.setAttribute("birthyear", birthyear);
				 * session.setAttribute("birthday", birthday);
				 */
				
				model.addAttribute("memberClass", "Y");   //회원가입 필요여부
				model.addAttribute("message", "신규 가입 회원입니다.회원가입 화면으로 이동 합니다.");
				model.addAttribute("url", "/home/user/joinSns01_Main");
				
				return "/home/user/join/snsCallback";
			}
		} else {
			model.addAttribute("userType", null);		//유저 구분
			model.addAttribute("message", "카카오 정보조회에 실패했습니다.");
			model.addAttribute("url", "/home/user/login_form");

			model.addAttribute("result", apiResult);
			
			return "/home/user/join/snsCallback";
		}
	}
	
	/**
	 * 네이버 로그인 콜백
	 * @throws Exception 
	 */
	@RequestMapping(value = "/naverLoginCallback", method = { RequestMethod.GET, RequestMethod.POST })
	public String naverLoginCallback(Model model, UsrLoginVO vo, HttpServletRequest request, 
			HttpServletResponse response, @RequestParam String code, @RequestParam String state,
			HttpSession session, @RequestParam Map<String, Object> paramMap) throws Exception {
		OAuth2AccessToken oauthToken;
		oauthToken = naverLoginBO.getAccessToken(session, code, state);
		//1. 로그인 사용자 정보를 읽어온다.
		apiResult = naverLoginBO.getUserProfile(oauthToken); //String형식의 json데이터
		//2. String형식인 apiResult를 json형태로 바꿈
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(apiResult);
		JSONObject jsonObj = (JSONObject) obj;
		//3. 데이터 파싱
		//Top레벨 단계 _response 파싱
		JSONObject response_obj = (JSONObject)jsonObj.get("response");
		if(response_obj != null && !response_obj.equals("")) {
			String id = (String) response_obj.get("id");
			paramMap.put("snsKey", id);
			paramMap.put("snsDiv", "N");
			int joinChk = usrUserInfoService.oauthCheckId(paramMap);
			
			if(joinChk > 0) {
				String result = "SUCCESS";
				boolean snsFlag = true;
				boolean snsLoginFailFlag = true;
				
				String orgCd = UserBroker.getUserOrgCd(request);
				String serverName = request.getServerName();
				UsrUserInfoVO uuiVO = new UsrUserInfoVO();
				uuiVO.setOrgCd(orgCd);
				String mainMcd = "MC00000000"; //메인
				String infoMcd = "MC00000035"; //개인정보수정
				String goMcd = StringUtil.nvl(vo.getGoMcd(), mainMcd);
				String goUrl = vo.getGoUrl();
				
				boolean chkUserAgentHack = false;
				if(StringUtil.nvl(goMcd).indexOf("' AND ") !=-1) chkUserAgentHack = true;
				if(StringUtil.nvl(goMcd).indexOf("' OR ") !=-1) chkUserAgentHack = true;
				if(StringUtil.nvl(goMcd).indexOf("\" AND ") !=-1) chkUserAgentHack = true;
				if(StringUtil.nvl(goMcd).indexOf("\" OR ") !=-1) chkUserAgentHack = true;
				if(StringUtil.nvl(goMcd).indexOf(" AND ") !=-1) chkUserAgentHack = true;
				if(StringUtil.nvl(goMcd).indexOf(" OR ") !=-1) chkUserAgentHack = true;
				if(StringUtil.nvl(goUrl).indexOf("' AND ") !=-1) chkUserAgentHack = true;
				if(StringUtil.nvl(goUrl).indexOf("' OR ") !=-1) chkUserAgentHack = true;
				if(StringUtil.nvl(goUrl).indexOf("\" AND ") !=-1) chkUserAgentHack = true;
				if(StringUtil.nvl(goUrl).indexOf("\" OR ") !=-1) chkUserAgentHack = true;
				if(StringUtil.nvl(goUrl).indexOf(" AND ") !=-1) chkUserAgentHack = true;
				if(StringUtil.nvl(goUrl).indexOf(" OR ") !=-1) chkUserAgentHack = true;
				if (chkUserAgentHack) {
					// log.error(message);
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "ERROR");
					return null;
				}
				
				String exceptionMcd = sysCfgService.getValue("LOGIN", "EXCEPTION");

				int faileSec = Integer.parseInt(sysCfgService.getValue("LOGIN", "GAPTIME"))/60;
				
				uuiVO.setSnsDiv("N");
				uuiVO.setSnsKey(id);
				
				//-- 로그인 시도 DB에 기록
				LogUserLoginTryLogVO lultlvo = new LogUserLoginTryLogVO();
				lultlvo.setUserId(uuiVO.getUserId());
				lultlvo.setBrowserInfo(request.getHeader("User-Agent"));
				lultlvo.setConnIp(request.getRemoteAddr());
				
				try {
					uuiVO = usrUserInfoService.viewForLogin(uuiVO);
					
					if("Y".equals(uuiVO.getLoginUseYn())) {
						//-- 회원의 마지막 접속 정보 등록
						usrLoginService.editLastLogin(uuiVO);

						//--홈페이지 로그인 로그 기록 남김
						LogSysLoginLogVO lldto = new LogSysLoginLogVO();
						lldto.setOrgCd(orgCd);
						logSysLoginLogService.add(lldto);

						lultlvo.setLoginSuccYn("Y");
						lultlvo.setUserNo(uuiVO.getUserNo());
					}
					
					// 세션 정보를 셋팅해 준다.
					request.getSession().setAttribute(Constants.LOGIN_USERID, uuiVO.getUserId());
					request.getSession().setAttribute(Constants.LOGIN_USERNO, uuiVO.getUserNo());
					request.getSession().setAttribute(Constants.LOGIN_USERTYPE, uuiVO.getWwwAuthGrpCd());
					request.getSession().setAttribute(Constants.LOGIN_USERNAME, uuiVO.getUserNm());
					request.getSession().setAttribute(Constants.LOGIN_ADMTYPE, uuiVO.getAdminAuthGrpCd());
					request.getSession().setAttribute(Constants.LOGIN_MNGTYPE, uuiVO.getMngAuthGrpCd());
					request.getSession().setAttribute(Constants.LOGIN_DEPTCD, uuiVO.getDeptCd());
					
					logUserConnLogService.add(request);
					
					
				} catch (DataAccessException e) {
					result = "ERROR";
				} finally {
					if(!snsLoginFailFlag){
						logUserLoginTryLogService.add(lultlvo);
					}
					model.addAttribute("memberClass", "N");
					model.addAttribute("message", "로그인되었습니다.");
					model.addAttribute("url", "/home/main/indexMain");
				}

				return "/home/user/join/snsCallback";
				
			} else {
				//신규 가입
				session.setAttribute("snsKey", paramMap.get("snsKey"));
				session.setAttribute("snsDiv", "N");
				
				session.setAttribute("snsJoin", "Y");
				
				/*
				 * String birthyear = (String)response_obj.get("birthyear"); String birthday =
				 * (String)response_obj.get("birthday"); birthday = birthday.replace("-", "");
				 * String mobile = (String)response_obj.get("mobile"); String name =
				 * (String)response_obj.get("name"); String email =
				 * (String)response_obj.get("email");
				 * 
				 * session.setAttribute("name", name); session.setAttribute("mobile", mobile);
				 * session.setAttribute("birthyear", birthyear);
				 * session.setAttribute("birthday", birthday); session.setAttribute("email",
				 * email);
				 */
				
				model.addAttribute("memberClass", "Y");   //회원가입 필요여부
				model.addAttribute("message", "신규 가입 회원입니다.회원가입 화면으로 이동 합니다.");
				model.addAttribute("url", "/home/user/joinSns01_Main");
				
				
				return "/home/user/join/snsCallback";
			}
		} else {
			model.addAttribute("userType", null);		//유저 구분
			model.addAttribute("message", "네이버 정보조회에 실패했습니다.");
			model.addAttribute("url", "/home/user/login_form");

			model.addAttribute("result", apiResult);
			
			return "/home/user/join/snsCallback";
		}
	}
	

	/**
	 * 홈페이지 로그인
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/login")
	public String login(UsrLoginVO vo, HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		String serverName = request.getServerName();

		UsrUserInfoVO uuivo = new UsrUserInfoVO();

		uuivo.setOrgCd(orgCd);
		String mainMcd = "MC00000000"; //메인
		//String infoMcd = "MC00000035"; //개인정보수정
		String infoMcd = "HM03006000"; //개인정보수정
		String goMcd = StringUtil.nvl(vo.getGoMcd(), mainMcd);
		String goUrl = vo.getGoUrl();
		boolean snsFlag = false;
		boolean snsLoginFailFlag = false;

		/** 브라우저 , 호스트 변조 접근 방지 시작 2015.12.15 */
		boolean chkUserAgentHack = false;
		if(StringUtil.nvl(goMcd).indexOf("' AND ") !=-1) chkUserAgentHack = true;
		if(StringUtil.nvl(goMcd).indexOf("' OR ") !=-1) chkUserAgentHack = true;
		if(StringUtil.nvl(goMcd).indexOf("\" AND ") !=-1) chkUserAgentHack = true;
		if(StringUtil.nvl(goMcd).indexOf("\" OR ") !=-1) chkUserAgentHack = true;
		if(StringUtil.nvl(goMcd).indexOf(" AND ") !=-1) chkUserAgentHack = true;
		if(StringUtil.nvl(goMcd).indexOf(" OR ") !=-1) chkUserAgentHack = true;
		if(StringUtil.nvl(goUrl).indexOf("' AND ") !=-1) chkUserAgentHack = true;
		if(StringUtil.nvl(goUrl).indexOf("' OR ") !=-1) chkUserAgentHack = true;
		if(StringUtil.nvl(goUrl).indexOf("\" AND ") !=-1) chkUserAgentHack = true;
		if(StringUtil.nvl(goUrl).indexOf("\" OR ") !=-1) chkUserAgentHack = true;
		if(StringUtil.nvl(goUrl).indexOf(" AND ") !=-1) chkUserAgentHack = true;
		if(StringUtil.nvl(goUrl).indexOf(" OR ") !=-1) chkUserAgentHack = true;
		if (chkUserAgentHack) {
			// log.error(message);
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "ERROR");
			return null;
		}

		String exceptionMcd = sysCfgService.getValue("LOGIN", "EXCEPTION");

		int faileSec = Integer.parseInt(sysCfgService.getValue("LOGIN", "GAPTIME"))/60;

		if(exceptionMcd.contains(goMcd)) {
			goMcd = mainMcd; //-- 예외 처리 메뉴 번호인 경우 홈페이지로 연결
		}

		String pawdChgReqYn = "N"; //-- 비밀번호 변경 요청 여부.

		// 암호화 파라매터가 넘어왔을 경우 복호화해서 VO에 설정
		if(StringUtil.isNotNull(vo.getEncryptData())) {
			log.debug("암호화 파라매터 복고화 처리 시작..");
			String decrypt[] = CryptoUtil.descrypt(vo.getEncryptData());
			
			uuivo.setOrgCd(orgCd); //현재 로그인 하려는 시스템의 기관코드
			
			//sns로그인 일때
			if(StringUtil.isNotNull(vo.getSnsDiv())){
				snsFlag = true;
				uuivo.setSnsKey(decrypt[0]);
				uuivo.setSnsDiv(decrypt[1]);
			}else{
				uuivo.setUserId(decrypt[0]);
				uuivo.setUserPass(decrypt[1]);
				vo.setUserId(decrypt[0]);
				vo.setUserPass(decrypt[1]);
			}
			log.debug("암호화 파라매터 복고화 처리 성공..");
		}else{
			uuivo.setUserId(vo.getUserId());
			uuivo.setUserPass(vo.getUserPass());
		}

		//-- 로그인 시도 DB에 기록
		LogUserLoginTryLogVO lultlvo = new LogUserLoginTryLogVO();
		lultlvo.setUserId(uuivo.getUserId());
		lultlvo.setBrowserInfo(request.getHeader("User-Agent"));
		lultlvo.setConnIp(request.getRemoteAddr());

		try {
			uuivo = usrUserInfoService.viewForLogin(uuivo);
			
			OrgDomainVO orgDomainVO = new OrgDomainVO();
			orgDomainVO.setOrgCd(orgCd);
			orgDomainVO.setOrgDomain(serverName);
			orgDomainVO = orgDomainService.view(orgDomainVO);
			orgDomainVO.setDomainTypeCd("TUTOR");
			orgDomainVO = orgDomainService.viewByServiceTypeCd(orgDomainVO);
			
			String chkUserType = StringUtil.nvl(uuivo.getWwwAuthGrpCd(),"");
			if(chkUserType.contains("TEACHER") || chkUserType.contains("TUTOR")) {
				String tutorUrl = request.getScheme() + "://" + orgDomainVO.getOrgDomain();
				return "redirect:"+ new URLBuilder(tutorUrl + "/home/main/goMenuPage")
							.addParameter("mcd", "MC00000003")
							.addParameter("loginMsgCd", "MSG02")
							.toString();
			}
			
			//sns로그인 일때
			if(snsFlag){
				//로그인 기록에 SNS명 + userNo 저장
				lultlvo.setUserId(vo.getSnsDiv()+"|"+uuivo.getUserNo());
			}
			
			//-- 인트라넷에서 넘어온 사용자의 경우 비밀 번호 암호화 및 mid_chk 상태 해제
			if(!uuivo.getUserPass().equals(uuivo.getEncUserPass())) {
				goMcd = "HM04001000"; //로그인 페이지 유지
				setAlertMessage(request, getMessage(request, "user.message.login.failed"));
				return "redirect:" + new URLBuilder("/home/main/goMenuPage").addParameter("mcd", goMcd).toString();
			}

			//-- 사용자 정보의 ORG CD 와 시스템의 ORG CD가 다른 경우 로그인 시키지 않음.
			if(!orgCd.equals(uuivo.getOrgCd())){
				//통합로그인으로 가능한 ID이면  "Y"가 나옴. -> 통과, "N"일때는 로그인 시키지 않음.
				if("N".equals(uuivo.getItgrtMbrUseYn())){
					setAlertMessage(request, getMessage(request, "user.message.login.failed"));
					return "redirect:" + new URLBuilder("/home/main/goMenuPage").addParameter("mcd", goMcd).toString();
				}
			}
			
			if(StringUtil.isNull(uuivo.getWwwAuthGrpCd())) {
				setAlertMessage(request, getMessage(request, "user.message.login.failed"));
				return "redirect:" + new URLBuilder("/home/main/goMenuPage").addParameter("mcd", goMcd).toString();
			}

			if("Y".equals(uuivo.getLoginUseYn())) {
				//-- 회원의 마지막 접속 정보 등록
				usrLoginService.editLastLogin(uuivo);

				//--홈페이지 로그인 로그 기록 남김
				LogSysLoginLogVO lldto = new LogSysLoginLogVO();
				lldto.setOrgCd(orgCd);
				logSysLoginLogService.add(lldto);

				lultlvo.setLoginSuccYn("Y");
				lultlvo.setUserNo(uuivo.getUserNo());

				pawdChgReqYn = uuivo.getPswdChgReqYn(); //-- 비밀번호 변경 요청 여부 셋팅.

			} else {
				String[] args = new String[1];
				args[0] = Integer.toString(faileSec);
				setAlertMessage(request, getMessage(request, "user.message.login.failed.time", args));
				return "redirect:" + new URLBuilder("/home/main/goMenuPage").addParameter("mcd", goMcd).toString();
			}
		} catch (DataAccessException e) {
			uuivo = new UsrUserInfoVO();
			uuivo.setUserId(vo.getUserId());
			String errMsg = getMessage(request, "user.message.login.failed");
			lultlvo.setLoginSuccYn("N");
			
			//sns로그인 실패일때 메시지
			if(snsFlag){
				snsLoginFailFlag = true;
				errMsg = getMessage(request, "user.message.login.search.id.result2");
			}

			try {
				vo = usrLoginService.editFailLogin(vo);
			} catch (Exception ex) {
				setAlertMessage(request, errMsg);
				return "redirect:" + new URLBuilder("/home/main/goMenuPage").addParameter("mcd", goMcd).toString();
			}
			if("Y".equals(uuivo.getLoginUseYn())) {
				String loginFailCnt = sysCfgService.getValue("LOGIN", "FAILCNT");
				String[] args = new String[2];
				args[0] = Integer.toString(vo.getLoginFailCnt());
				args[1] = loginFailCnt;
				errMsg += getMessage(request, "user.message.login.failed.cnt", args);
			} else {
				String[] args = new String[1];
				args[0] = Integer.toString(faileSec);
				errMsg += getMessage(request, "user.message.login.failed.cntover", args);
			}
			setAlertMessage(request, errMsg);
			return "redirect:" + new URLBuilder("/home/main/goMenuPage").addParameter("mcd", goMcd).toString();
		} finally {
			//sns로그인 실패일때는 저장안함 
			if(!snsLoginFailFlag){
				logUserLoginTryLogService.add(lultlvo);
			}
		}

		// 세션 정보를 셋팅해 준다.
		request.getSession().setAttribute(Constants.LOGIN_USERID, uuivo.getUserId());
		request.getSession().setAttribute(Constants.LOGIN_USERNO, uuivo.getUserNo());
		request.getSession().setAttribute(Constants.LOGIN_USERTYPE, uuivo.getWwwAuthGrpCd());
		request.getSession().setAttribute(Constants.LOGIN_USERNAME, uuivo.getUserNm());
		request.getSession().setAttribute(Constants.LOGIN_ADMTYPE, uuivo.getAdminAuthGrpCd());
		request.getSession().setAttribute(Constants.LOGIN_MNGTYPE, uuivo.getMngAuthGrpCd());
		request.getSession().setAttribute(Constants.LOGIN_DEPTCD, uuivo.getDeptCd());

		//-- 메뉴관련 세션은 초기화.
		//request.getSession().setAttribute(Constants.MENU_LOCATION, "");
		//request.getSession().setAttribute(Constants.CUR_MENU_NAME, "");
		//request.getSession().setAttribute(Constants.CUR_MENU_CODE, "");
		//request.getSession().setAttribute(Constants.ROT_MENU_CODE, "");
		
		if (Constants.REDIS_CHECK_YN.equals("Y")) {
			int expireTime = 24 * 60 * 60; // 24hour
			RedisUtil.setValue(Constants.REDIS_NAMESPACE+":SID:"+uuivo.getUserId(),
					request.getSession().getId(), expireTime);
		}

		OrgMenuVO menuVO = null;
		String redirectUrl = "";
		logUserConnLogService.add(request);

		if("Y".equals(pawdChgReqYn)) {
			menuVO = sysMenuMemService.decideHomeMenuWithSession(infoMcd, request);
			setAlertMessage(request, getMessage(request, "user.message.userinfo.change.password.durtion2"));
			redirectUrl = "/home/user/editFormUserMain";
		} else if(mainMcd.equals(goMcd)) {
			redirectUrl = INDEX_MAIN_URL;
		} else {
			try{
	//			goUrl = StringUtil.ReplaceAll(StringUtil.getUrlDecode(goUrl),Constants.HOST_CONTEXT_URL,"");
	//			if(StringUtil.isNull(goMcd)) {
	//				menuVO = orgMenuService.viewMenuCdByUrl(goUrl).getReturnDto();
	//				redirectUrl = "/home/main/goMenuPage?mcd="+menuVO.getMenuCd();
	//			}else
				// goMcd가 별도로 있을 경우 메뉴정보를 세션에 다시 저장하고 goUrl로 직접 이동.
				menuVO = sysMenuMemService.decideHomeMenuWithSession(goMcd, request);
				redirectUrl = menuVO.getMenuUrl();
			} catch (DataAccessException e) {
				redirectUrl = INDEX_MAIN_URL;
			}
		}
		
		if("N".equals(uuivo.getPhoneVeriYn())) {
			request.getSession().setAttribute("verifiedId", "false");
		}
		
		
		return "redirect:"+redirectUrl;
	}

	/**
	 * 홈페이지 로그아웃
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/logout")
	public String logout(UsrLoginVO vo, Map commandMap, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		LogUserConnLogVO ucvo = new LogUserConnLogVO();
		ucvo.setUserNo(UserBroker.getUserNo(request));
		logUserConnLogService.edit(ucvo);

		// 세션 정보를 셋팅해 준다.
		request.getSession().setAttribute(Constants.LOGIN_USERID, "");
		request.getSession().setAttribute(Constants.LOGIN_USERNO, "");
		request.getSession().setAttribute(Constants.LOGIN_USERTYPE, "");
		request.getSession().setAttribute(Constants.LOGIN_USERNAME, "");
		request.getSession().setAttribute(Constants.LOGIN_ADMTYPE, "");
		request.getSession().setAttribute(Constants.LOGIN_MNGTYPE, "");
		request.getSession().setAttribute(Constants.LOGIN_DEPTCD, "");

		//-- 메뉴관련 세션은 초기화.
		request.getSession().setAttribute(Constants.MENU_LOCATION, "");
		request.getSession().setAttribute(Constants.CUR_MENU_NAME, "");
		request.getSession().setAttribute(Constants.CUR_MENU_CODE, "");
		request.getSession().setAttribute(Constants.ROT_MENU_CODE, "");

		//-- 로그아웃 기록.

		request.getSession().invalidate();

		return "redirect:"+INDEX_MAIN_URL;
	}

	/**
     * @Method Name : searchIdPw
     * @Method 설명 : 아이디 패스워드 찾기
	 * @param MainVO 
	 * @param commandMap
	 * @param model
	 * @return  join_step_01
	 * @throws Exception
	 */
	@RequestMapping(value="/searchIdPwMain")
	public String searchIdPwMain(MainVO vo, Map commandMap, ModelMap model, HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
//		String tab = StringUtil.nvl(request.getParameter("tab"),"searchId");
//		String findidMcd = StringUtil.nvl(request.getParameter("findidMcd"),"HM04003000");
//
//		request.getSession().setAttribute("joinUsrUserInfoVO", new UsrUserInfoVO());
//		request.setAttribute("tab", tab);
//		request.setAttribute("findidMcd", findidMcd);
		//이메일
		List<SysCodeVO> emailCode = sysCodeMemService.getSysCodeList("USER_EMAIL");
		request.setAttribute("emailCode", emailCode);

		return "home/user/idpw/idpw_search";
	}


	/**
	 * ID 찾기 결과
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/findId")
	public String findId(UsrUserInfoVO vo, Map commandMap, ModelMap model, HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		if(ValidationUtils.isEmpty(vo.getUserNm())) {
			request.setAttribute("errCode", "110");
			request.setAttribute("userId", "");
		} else if(ValidationUtils.isEmpty(vo.getEmail())) {
			request.setAttribute("errCode", "120");
			request.setAttribute("userId", "");
		} else {
			vo = usrUserInfoService.selectSearchId(vo);
			if(vo != null){
				request.setAttribute("errCode", "000");
				request.setAttribute("userId", vo.getUserId());
			}else{
				request.setAttribute("errCode", "100");
				request.setAttribute("userId", "");
			}
		}
		return "home/user/idpw/id_search_result";
	}

	/**
	 * 비밀번호 찾기 결과
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/findPass")
	public String findPass(UsrUserInfoVO vo, Map commandMap, ModelMap model, HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		if(ValidationUtils.isEmpty(vo.getUserId())) {
			request.setAttribute("errCode", "310"); // 아이디를 입력해 주세요.
		} else if(ValidationUtils.isEmpty(vo.getUserNm())) {
			request.setAttribute("errCode", "320"); // 이름을 입력해 주세요.
		} else if(ValidationUtils.isEmpty(vo.getEmail())) {
			request.setAttribute("errCode", "330"); // 이메일을 입력해 주세요.
		} else {
			vo = usrUserInfoService.selectSearchId(vo);
			if(vo != null){
				vo.setUserPass(usrLoginService.getNewPass());
				String newPass = vo.getUserPass();

				OrgOrgInfoVO orgInfoVO = new OrgOrgInfoVO();
				orgInfoVO.setOrgCd(orgCd);
				orgInfoVO = orgOrgInfoService.view(orgInfoVO);
				
				try{
					//초기화된 비밀번호 등록
					usrLoginService.editPass(vo);
				}catch(EmptyResultDataAccessException e) {
					request.setAttribute("errCode", "340");	// 비밀번호를 초기화 하지 못하였습니다.
					return "home/user/idpw/pass_search_result";
				}
				
				LogMsgTransLogVO trans = new LogMsgTransLogVO();

				trans.setRecvAddr(vo.getEmail());
				trans.setRecvNm(vo.getUserNm());
				trans.setRecvYn("Y"); //안받으면 못찾으니까 강제적으로 Y

				LogMsgLogVO message = trans.getMessage();
				message.setSendMenuCd(UserBroker.getMenuCode(request));
				message.setMsgDivCd("EMAIL");
				message.setSendAddr(orgInfoVO.getEmailAddr());
				message.setSendNm(orgInfoVO.getEmailNm());

				message.addSubTrans(trans);

				// 메일 데코레이션
				Map<String, Object> argu = new HashMap<String, Object>();
				argu.put("Name",vo.getUserNm());
				argu.put("UserID", vo.getUserId());
				argu.put("Password", newPass);
				argu.put("SendDate", DateTimeUtil.getCurrentString("yy.MM.dd"));
				emailTplService.decorator(orgCd, "EM002", argu, message);
				
				try{
					messageService.addMessageWithSend(message);		//실서버 메일 등록 및 전송
					request.setAttribute("errCode", "000");
					request.setAttribute("email", trans.getRecvAddr());
				}catch (MessageNotificationException ex) {
					request.setAttribute("errCode", "350"); //이메일 전송과정에서 문제가 발생했습니다.
				}
				
			}else{
				request.setAttribute("errCode", "360"); // 정보검색 실패
			}
		}
		return "home/user/idpw/pass_search_result";
		
	}
	
	
	/**
     * @Method Name : searchIdModalPop
     * @Method 설명 : 아이디 찾기 팝업
	 * @param MainVO 
	 * @param commandMap
	 * @param model
	 * @return  id_search_pop
	 * @throws Exception
	 */
	@RequestMapping(value="/searchIdModalPop2")
	public String searchIdModalPop(MainVO vo, Map commandMap, ModelMap model, HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		//이메일
		List<SysCodeVO> emailCode = sysCodeMemService.getSysCodeList("USER_EMAIL");
		request.setAttribute("emailCode", emailCode);

		return "home/user/idpw/id_search_modal";
	}
	
	
	/**
	 * @Method Name : searchPwModalPop2
	 * @Method 설명 : 패스워드 찾기 팝업
	 * @param MainVO 
	 * @param commandMap
	 * @param model
	 * @return  join_step_01
	 * @throws Exception
	 */
	@RequestMapping(value="/searchPwModalPop2")
	public String searchPwModalPop(MainVO vo, Map commandMap, ModelMap model, HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		//이메일
		List<SysCodeVO> emailCode = sysCodeMemService.getSysCodeList("USER_EMAIL");
		request.setAttribute("emailCode", emailCode);
		
		return "home/user/idpw/pw_search_modal";
	}
	
	/**
	 * @Method Name : editFormMe
	 * @Method 설명 : 개인정보 수정
	 * @param UsrUserInfoVO 
	 * @param commandMap
	 * @param model
	 * @return  edit_me
	 * @throws Exception
	 */
	@RequestMapping(value="/editFormUserMain")
	public String editFormUserMain(UsrUserInfoVO vo, Map commandMap, ModelMap model, HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);
		vo.setOrgCd(orgCd);
		vo.setUserNo(userNo);
		UsrUserAuthGrpVO uuagvo = new UsrUserAuthGrpVO();
		uuagvo.setUserNo(vo.getUserNo());

		vo = usrUserInfoService.view(vo);
		vo.setMobileNo(StringUtil.nvl(vo.getMobileNo(),"").replaceAll("-", ""));
		String homePhoneno = StringUtil.nvl(vo.getHomePhoneno(), "");
		vo.setHomePhoneno(hyphenPhoneNo(homePhoneno));

		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "E");
	
		//휴대폰앞자리
		List<SysCodeVO> moblieCode = sysCodeMemService.getSysCodeList("MOBILE_CODE");
		request.setAttribute("moblieCode", moblieCode);
		
		//지역 전화 코드
		List<SysCodeVO> localPhoneCdList = sysCodeMemService.getSysCodeList("LOCAL_PHONE_CODE");
		request.setAttribute("localPhoneCdList", localPhoneCdList);
	
		//이메일
		List<SysCodeVO> emailCode = sysCodeMemService.getSysCodeList("USER_EMAIL");
		request.setAttribute("emailCode", emailCode);
	
		//사용자 정보관리
		OrgUserInfoCfgVO ouicvo = new OrgUserInfoCfgVO();
		ouicvo.setOrgCd(orgCd);
		List<OrgUserInfoCfgVO> userInfoCfgList = orgUserInfoCfgService.list(ouicvo).getReturnList();
		request.setAttribute("userInfoCfgList", userInfoCfgList);
		request.setAttribute("vo", vo);
		request.setAttribute("fileupload", "Y");
		request.setAttribute("encryptjs", "Y");
		
		return "home/user/info/edit_me";
	}
	
	/**
	 * @Method Name : editFormMe
	 * @Method 설명 : 아바타 편집
	 * @param UsrUserInfoVO 
	 * @param commandMap
	 * @param model
	 * @return  edit_me
	 * @throws Exception
	 */
	@RequestMapping(value="/editFormUserAvatarMain")
	public String editFormUserAvartar(UsrUserInfoVO vo, Map commandMap, ModelMap model, HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);
		vo.setOrgCd(orgCd);
		vo.setUserNo(userNo);
		UsrUserAuthGrpVO uuagvo = new UsrUserAuthGrpVO();
		uuagvo.setUserNo(vo.getUserNo());

		vo = usrUserInfoService.view(vo);
		request.setAttribute("vo", vo);
		
		String avatarEditUrl = Constants.AVATAR_EDIT_URL;
		request.setAttribute("avatarEditUrl", avatarEditUrl+"/?userNo="+vo.getUserNo());
		
		return "home/user/info/edit_avatar";
	}
	
	@RequestMapping(value="/editFormUserAvatar")
	public String editFormUserAvatar(UsrUserInfoVO vo, Map commandMap, ModelMap model, HttpServletRequest request) throws Exception {
		
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);
		vo.setOrgCd(orgCd);
		vo.setUserNo(userNo);
		UsrUserAuthGrpVO uuagvo = new UsrUserAuthGrpVO();
		uuagvo.setUserNo(vo.getUserNo());

		vo = usrUserInfoService.view(vo);
		request.setAttribute("vo", vo);
		
		String avatarEditUrl = Constants.AVATAR_EDIT_URL;
		request.setAttribute("avatarEditUrl", avatarEditUrl+"/?userNo="+vo.getUserNo());
		
		return "home/user/info/edit_avatar_pop";
		
	}
	
	private String hyphenPhoneNo(String phoneNo) {
		if(phoneNo == null || phoneNo.length() < 9) return phoneNo;
		
		StringBuffer sb = new StringBuffer(phoneNo);
		
		int firstIndex = 0;
		int secondIndex = 0;
		if(phoneNo.startsWith("02")) {
			firstIndex = 2;
			secondIndex = phoneNo.length() == 10 ? 6 : 5;
		} else {
			firstIndex = 3;
			secondIndex = phoneNo.length() == 10 ? 6 : 7;
		}
		sb.insert(firstIndex, "-");
		sb.insert(secondIndex+1, "-");
		
		return sb.toString();
	}	
	
	/**
     * @Method Name : editPassForm
     * @Method 설명 : 비밀번호 변경 화면 이동한다.
	 * @param UsrUserInfoVO 
	 * @param commandMap
	 * @param model
	 * @return  "home/user/info/write_me.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/editPassFormPop2")
	public String editPassFormPop(UsrUserInfoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		
		String userNo = UserBroker.getUserNo(request);
		vo.setUserNo(userNo);
		
		vo = usrUserInfoService.view(vo);
		request.setAttribute("vo", vo);
		request.setAttribute("encryptjs", "Y");
		request.setAttribute("pwdmeter", "Y");
		return "home/user/info/edit_pass_pop";
	}
	
	/**
     * @Method 설명 : 본인인증 및 비밀번호 변경 팝업
	 * @param UsrUserInfoVO 
	 * @param commandMap
	 * @param model
	 * @return  "home/user/info/init_verify_pop.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/initVerifyPop2")
	public String initVerifyPop(UsrUserInfoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		
		String userNo = UserBroker.getUserNo(request);
		vo.setUserNo(userNo);
		
		vo = usrUserInfoService.view(vo);
		
		request.setAttribute("vo", vo);
		request.setAttribute("encryptjs", "Y");
		request.setAttribute("pwdmeter", "Y");
		
		return "home/user/info/init_verify_pop";
	}
	
	
	/**
     * @Method Name : editPass
     * @Method 설명 : 비밀번호를 변경한다.
	 * @param UsrUserInfoVO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/editPass")
	public String editPass(UsrUserInfoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		if(StringUtil.isNotNull(vo.getEncryptData())) {
			log.debug("암호화 파라매터 복고화 처리 시작..");
			String decrypt[] = CryptoUtil.descrypt(vo.getEncryptData());
			vo.setUserNo(decrypt[0]);                  //userNo
			vo.setUserPassConfirm(decrypt[1]);          //userPass
			vo.setNewUserPass(decrypt[2]);          //userPass
			vo.setNewUserPassConfirm(decrypt[3]);      //newUserPassCofirm

			log.debug("암호화 파라매터 복고화 처리 성공..");
		}

		//-- 사용자의 정보를 가져온다.
		UsrUserInfoVO suidto = new UsrUserInfoVO();
		suidto.setUserNo(vo.getUserNo());
		suidto.setUserPass(vo.getUserPassConfirm());
		suidto = usrUserInfoService.selectSearchPass(suidto);
		
		ProcessResultVO<UsrUserInfoVO> result = new ProcessResultVO<UsrUserInfoVO>();

		if(suidto == null) {
			result = ProcessResultVO.failed("CHECK_PASS");//기존비밀번호와 다를때
			return JsonUtil.responseJson(response, result);
		} else {
			UsrLoginVO loginVO = new UsrLoginVO();
			loginVO.setUserNo(vo.getUserNo());
			loginVO.setUserPass(vo.getNewUserPass());
			try {
				usrLoginService.editPass(loginVO);
				result.setResult(1);
				result.setMessage("비밀번호 변경에 성공하였습니다.");
			} catch (Exception e) {
				result.setResult(-1);
				result.setMessage("비밀번호 변경에 실패하였습니다.");
			}
			return JsonUtil.responseJson(response, result);
		}
		
	}
	
	/**
     * @Method 설명 : 인증 및 비밀번호를 변경한다.
	 * @param UsrUserInfoVO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/initVerify")
	public ProcessResultVO<UsrUserInfoVO> initVerify(UsrUserInfoVO vo, HttpServletRequest request, HttpSession session) throws Exception {
		commonVOProcessing(vo, request);
		
		if(StringUtil.isNotNull(vo.getEncryptData())) {
			log.debug("암호화 파라매터 복고화 처리 시작..");
			String decrypt[] = CryptoUtil.descrypt(vo.getEncryptData());
			vo.setUserNo(decrypt[0]);                  //userNo
			vo.setUserPassConfirm(decrypt[1]);          //userPass
			vo.setNewUserPass(decrypt[2]);          //userPass
			vo.setNewUserPassConfirm(decrypt[3]);      //newUserPassCofirm

			log.debug("암호화 파라매터 복고화 처리 성공..");
		}

		ProcessResultVO<UsrUserInfoVO> result = new ProcessResultVO<UsrUserInfoVO>();
		//-- 사용자의 정보를 가져온다.
		UsrUserInfoVO suidto = new UsrUserInfoVO();
		suidto.setUserNo(vo.getUserNo());
		suidto.setUserPass(vo.getUserPassConfirm());
		suidto = usrUserInfoService.selectSearchPass(suidto);
		try {
			if(suidto == null) throw new ServiceProcessException("기존 비밀번호가 올바르지 않습니다.");
			UsrUserInfoVO user = (UsrUserInfoVO) session.getAttribute("joinUsrUserInfoVO");
			if(user == null) throw new ServiceProcessException("인증 정보가 올바르지 않습니다. 새로고침 후 다시 시도해주세요.");
			user.setUserNo(vo.getUserNo());
			user.setUserPass(vo.getNewUserPass());
			user.setPhoneVeriYn(vo.getPhoneVeriYn());
			user.setModNo(vo.getUserNo());
			
			usrUserInfoService.initVerify(user);
			result.setResult(1);
			result.setMessage("비밀번호 변경에 성공하였습니다. 다시 로그인 해주십시오.");
			
			session.invalidate();
			
		} catch (ServiceProcessException e) {
			result.setResult(-1);
			result.setMessage("비밀번호 변경에 실패하였습니다. " + e.getMessage());
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage("비밀번호 변경에 실패하였습니다.");
		}
		return result;
	}
	
	/**
	 * 회원 수정
	 * @param UsrUserInfoVO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/editUser")
	public String editUser(UsrUserInfoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		if(ValidationUtils.isEmpty(vo.getEmailRecv())) vo.setEmailRecv("N");
		if(ValidationUtils.isEmpty(vo.getSmsRecv())) vo.setSmsRecv("N");
		if(ValidationUtils.isEmpty(vo.getMsgRecv())) vo.setMsgRecv("N");
		
		// 디비 정보 추출
		UsrUserInfoVO returnVO = usrUserInfoService.view(vo);
				
		BeanUtils.mergeBean(returnVO,vo); //이전에 등록된데이터에 신규로 추가된 값을 merge 시켜서 등록한다.
		
		ProcessResultVO<UsrUserInfoVO> result = new ProcessResultVO<UsrUserInfoVO>();
		
		try {
			usrUserInfoService.edit(returnVO,"UE");
			result.setResult(1);
			result.setMessage(getMessage(request, "user.message.userinfo.edit.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "user.message.userinfo.edit.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}
	
	@ResponseBody
	@RequestMapping(value="/editUserInfo")
	public AbstractResult editUserInfo(UsrUserInfoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		if(ValidationUtils.isEmpty(vo.getEmailRecv())) vo.setEmailRecv("N");
		if(ValidationUtils.isEmpty(vo.getSmsRecv())) vo.setSmsRecv("N");
		if(ValidationUtils.isEmpty(vo.getMsgRecv())) vo.setMsgRecv("N");
		
		AbstractResult result = new AbstractResult();
		try {
		// 디비 정보 추출
		UsrUserInfoVO returnVO = usrUserInfoService.view(vo);
		BeanUtils.mergePropertyBean(returnVO,vo); //이전에 등록된데이터에 신규로 추가된 값을 merge 시켜서 등록한다.
		usrUserInfoService.editUserInfo(returnVO);
		result.setResult(1);
		result.setMessage("정상적으로 처리 되었습니다.");
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage("회원정보 수정 중 오류가 발생했습니다.");
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value="/editUserAvatar", method = {  RequestMethod.POST })
	public AbstractResult editUserAvatar(UsrUserInfoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		AbstractResult result = new AbstractResult();
		try {
			commonVOProcessing(vo, request);
			String orgCd = UserBroker.getUserOrgCd(request);
			vo.setOrgCd(orgCd);
			
			if(StringUtil.isNull(vo.getUserNo())){
				result.setResult(2);
				result.setMessage("USER_NO EMPTY");
				return result;
			}
			
			if(StringUtil.isNull(vo.getAvatar())){
				result.setResult(3);
				result.setMessage("AVATAR EMPTY");
				return result;
			}
			
			usrUserInfoService.editUserAvatar(vo);
			result.setResult(1);
			result.setMessage("SUCCESS");
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage("FAIL");
		}
		return result;
	}
	
	/**
     * @Method Name : joinOut
     * @Method 설명 : 회원 탈퇴
	 * @param UsrUserInfoVO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/joinOut")
	public String joinOut(UsrUserInfoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		ProcessResultVO<UsrLoginVO> result = new ProcessResultVO<UsrLoginVO>();
		try {
			usrUserInfoService.joinOutMember(vo);
			result.setResult(1);
			result.setMessage(getMessage(request, "user.message.userinfo.leave.success"));
			request.getSession().invalidate();
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "user.message.userinfo.leave.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}
	
	/**
     * @Method Name : joinStep0
     * @Method 설명 : 회원 가입 0단계 (회원가입 & 소셜로그인 선택)
	 * @param commandMap
	 * @param model
	 * @return  join_step_00
	 * @throws Exception
	 */
	@RequestMapping(value="/joinStep00_Main")
	public String joinStep00(Model model,HttpSession session) throws Exception {
		String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);
		model.addAttribute("naverAuthUrl", naverAuthUrl);
		
		String kakaoAuthUrl = kakaoLoginBO.getAuthorizationUrl(session);
		model.addAttribute("kakaoAuthUrl", kakaoAuthUrl);

		return "home/user/join/join_step00";
	}
	
	/**
     * @Method Name : joinStep01
     * @Method 설명 : 회원 가입 1단계 (세션개체 생성 및 약관 동의화면 표시)
	 * @param MainVO 
	 * @param commandMap
	 * @param model
	 * @return  join_step_01
	 * @throws Exception
	 */
	@RequestMapping(value="/joinStep01_Main")
	public String joinStep01(MainVO vo, Map commandMap, ModelMap model, HttpServletRequest request, HttpSession session) throws Exception {
		commonVOProcessing(vo, request);

		// 회원가입과정중 사용자 정보를 저장할 세션개체 생성
		request.getSession().setAttribute("joinUsrUserInfoVO", new UsrUserInfoVO());
		String orgCd = UserBroker.getUserOrgCd(request);

		OrgPageVO pageVO = new OrgPageVO();
		pageVO.setOrgCd(orgCd);

		// 이용약관
		pageVO.setPageCd("PAGE001");
		OrgPageVO pageVO1 = orgPageService.view(pageVO);
		request.setAttribute("pageVO1", pageVO1);

		// 개인정보수집 및 이용에 대한 안내
		pageVO.setPageCd("PAGE002");
		OrgPageVO pageVO2 = orgPageService.view(pageVO);
		request.setAttribute("pageVO2", pageVO2);
		
		return "home/user/join/join_step01";
	}
	
	/**
     * @Method Name : joinStep02
     * @Method 설명 : 회원 가입 2단계 (정보입력)
	 * @param MainVO 
	 * @param commandMap
	 * @param model
	 * @return  join_step_02
	 * @throws Exception
	 */
	@RequestMapping(value="/joinStep02_Main")
	public String joinStep02(UsrUserInfoVO vo, Map commandMap, ModelMap model, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		commonVOProcessing(vo, request);
		
		// 캐쉬 비활성화
		setCachePagingDisable(response);
		
		String orgCd = UserBroker.getUserOrgCd(request);
		OrgOrgInfoVO orgInfoVO = new OrgOrgInfoVO();
		orgInfoVO.setOrgCd(orgCd);
		orgInfoVO = orgOrgInfoService.view(orgInfoVO);
		request.setAttribute("mbrIdType", orgInfoVO.getMbrIdType());

		UsrUserInfoVO usrUserInfoVO = this.getSessionAttrUserInfoWithAssertCheck(request);

		if("N".equals(usrUserInfoVO.getCheckAgree())) {
			// 동의를 체크하지 않았다면 다시 인증 화면으로..(javascript를 지원하지 않는 브라우져에서도 지원..)
			if(StringUtil.isNull(request.getParameter("agree1")) || StringUtil.isNull(request.getParameter("agree2"))) {
				setAlertMessage(request, "이용약관 동의에 체크를 하셔야 다음 단계를 실행 할 수 있습니다.");
				return "redirect:/home/user/joinStep01_Main";	// 다시 1단계로
			} else {
				usrUserInfoVO.setCheckAgree("Y"); //-- 이용약관 동의 확인.
				this.setSessionUserInfo(request, usrUserInfoVO);
			}
		}
		
		//-- 시작년도 가져오기
		String curYear = DateTimeUtil.getYear();
		SysCfgVO scvo = new SysCfgVO();
		scvo.setCfgCtgrCd("JOINUS");
		scvo.setCfgCd("BIRTHYEAR");
		scvo = sysCfgService.viewCfg(scvo);
		
		List<String> yearList = new ArrayList<String>();
		for(int i= Integer.parseInt(curYear,10); i >= Integer.parseInt(scvo.getCfgVal(),10); i--) {
			yearList.add(Integer.toString(i));
		}
		
		request.setAttribute("yearList", yearList);
		
		/** 현재 소셜 로그인 세션 초기화  */
		if(session.getAttribute("checkNice") == null) { 
			session.setAttribute("ssoSeq","");
			session.setAttribute("ssoType","");

		}

		//휴대폰앞자리
		List<SysCodeVO> moblieCode = sysCodeMemService.getSysCodeList("MOBILE_CODE");
		request.setAttribute("moblieCode", moblieCode);
		
		//지역 전화 코드
		List<SysCodeVO> localPhoneCdList = sysCodeMemService.getSysCodeList("LOCAL_PHONE_CODE");
		request.setAttribute("localPhoneCdList", localPhoneCdList);

		//이메일
		List<SysCodeVO> emailCode = sysCodeMemService.getSysCodeList("USER_EMAIL");
		request.setAttribute("emailCode", emailCode);

		//내외국인 구분
		List<OrgCodeVO> codeList = orgCodeService.listCode(orgCd, "USER_DIV_CD").getReturnList();
		request.setAttribute("codeList", codeList);

		//지역
		List<OrgCodeVO> areaCode = orgCodeService.listCode(orgCd, "AREA_CD").getReturnList();
		request.setAttribute("areaCode", areaCode);
		
		//직업
		List<OrgCodeVO> jobCode = orgCodeService.listCode(orgCd, "JOB_CD").getReturnList();
		request.setAttribute("jobCode", jobCode);
		
		//소속
		List<OrgCodeVO> deptCode = orgCodeService.listCode(orgCd, "DEPT_CD").getReturnList();
		request.setAttribute("deptCode", deptCode);

		//사용자 정보관리
		OrgUserInfoCfgVO ouicvo = new OrgUserInfoCfgVO();
		ouicvo.setOrgCd(orgCd);
		List<OrgUserInfoCfgVO> userInfoCfgList = orgUserInfoCfgService.listForJoin(ouicvo).getReturnList();
		request.setAttribute("userInfoCfgList", userInfoCfgList);
		request.setAttribute("vo", vo);
		
		UsrUserInfoVO joinUsrUserInfoVO = (UsrUserInfoVO)request.getSession().getAttribute("joinUsrUserInfoVO");
		request.setAttribute("joinUsrUserInfoVO", joinUsrUserInfoVO);
		
		return "home/user/join/join_step02";
	}
	
	@RequestMapping(value="/joinUser")
	public String joinUser(UsrUserInfoVO vo, HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttributes) throws Exception {
		//-- 기관(사이트)코드 설정
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		//sns 키와 타입 설정
		String snsDiv = StringUtils.defaultString((String)session.getAttribute("snsDiv"),"");
		String snsKey = StringUtils.defaultString((String)session.getAttribute("snsKey"),"");

		if(!snsDiv.equals("") && !snsKey.equals("")) {
			vo.setSnsDiv((String)session.getAttribute("snsDiv"));
			vo.setSnsKey((String)session.getAttribute("snsKey"));
		}else {
			vo.setSnsDiv("");
			vo.setSnsKey("");
		}
		// 세션 체크 및 정보 추출
		UsrUserInfoVO sessionUsrUserInfoVO = this.getSessionAttrUserInfoWithAssertCheck(request);

		// 세션의 정보를 UsrUserInfoVO에 병합시킨다.
		BeanUtils.mergePropertyBean(vo, sessionUsrUserInfoVO);

		//-- 회원사 정보 검색
		OrgOrgInfoVO orgInfoVO = new OrgOrgInfoVO();
		orgInfoVO.setOrgCd(orgCd);
		orgInfoVO = orgOrgInfoService.view(orgInfoVO);
		
		try {
			if(!vo.getSnsDiv().equals("") && !vo.getSnsKey().equals("")) {
				usrUserInfoService.addUserSns(vo, "UI");
			} else {
				usrUserInfoService.add(vo, "UI");
			}
			
		}
		catch(Exception e) {
			setAlertMessage(request, "회원 등록 중 오류가 발생했습니다. 다시 시도해 주십시오.");
			return "redirect:/home/user/joinStep02_Main"; // 실패 시 리다이렉트
		}
		redirectAttributes.addFlashAttribute("vo", vo); // 리다이렉트용 1회성
		return "redirect:/home/user/joinStep03_Main";
	}

	/**
     * @Method Name : joinStep03
     * @Method 설명 : 회원 가입 3단계 (가입완료)
	 * @param MainVO 
	 * @param commandMap
	 * @param model
	 * @return  join_step_02
	 * @throws Exception
	 */
	@RequestMapping(value="/joinStep03_Main")
	public String joinStep03(UsrUserInfoVO vo, Map commandMap, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 캐쉬 비활성화
		setCachePagingDisable(response);

		request.setAttribute("vo", vo);
		return "home/user/join/join_step03";
	}
	
	/**
     * @Method Name : joinCancel
     * @Method 설명 : 회원가입 취소 처리(회원가입 세션을 삭제하고 메인화면으로 되돌림.)
	 * @param MainVO 
	 * @param commandMap
	 * @param model
	 * @return redirect:/home/main/indexMain
	 * @throws Exception
	 */
	@RequestMapping(value="/joinCancel")
	public String joinCancel(MainVO vo, Map commandMap, ModelMap model, HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		this.anonymousJoinClearSession(request);
		return "redirect:/home/main/indexMain";
	}
	
	/**
     * @Method Name : anonymousJoinClearSession
     * @Method 설명 : 회원 가입 과정에서 생성되는 세션정보를 삭제시킨다.
	 * @param HttpServletRequest 
	 * @throws Exception
	 */
	private void anonymousJoinClearSession(HttpServletRequest request) {
		request.getSession().removeAttribute("joinUsrUserInfoVO");
	}

	/**
     * @Method Name : userIdCheck
     * @Method 설명 : 회원관리
						└ ID 중복체크
	 * @param UsrUserInfoVO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/userIdCheck")
	public String userIdCheck(UsrLoginVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String result = usrLoginService.userIdCheck(vo);
		Map<String, String> map = new HashMap<String, String>();
		map.put("isUseable", result);
		
		return JsonUtil.responseJson(response, map);
	}
	
	/**
	 * @Method Name : emailCheck
	 * @Method 설명 : 회원관리
						└ 이메일 중복체크
	 * @param UsrUserInfoVO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/emailCheck")
	public String emailCheck(UsrUserInfoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String result = usrUserInfoService.emailCheck(vo);
		Map<String, String> map = new HashMap<String, String>();
		map.put("isUseable", result);
		
		return JsonUtil.responseJson(response, map);
	}
	
	
	
	
	
	
	
	/*********************************
	 *			 소셜 			*/
	
	/**
     * @Method Name : joinStep01
     * @Method 설명 : 회원 가입 1단계 (세션개체 생성 및 약관 동의화면 표시)
	 * @param MainVO 
	 * @param commandMap
	 * @param model
	 * @return  join_step_01
	 * @throws Exception
	 */
	@RequestMapping(value="/joinSns01_Main")
	public String joinSns01(MainVO vo, Map commandMap, ModelMap model, HttpServletRequest request, HttpSession session) throws Exception {
		commonVOProcessing(vo, request);

		// 회원가입과정중 사용자 정보를 저장할 세션개체 생성
		request.getSession().setAttribute("joinUsrUserInfoVO", new UsrUserInfoVO());
		String orgCd = UserBroker.getUserOrgCd(request);

		OrgPageVO pageVO = new OrgPageVO();
		pageVO.setOrgCd(orgCd);

		// 이용약관
		pageVO.setPageCd("PAGE001");
		OrgPageVO pageVO1 = orgPageService.view(pageVO);
		request.setAttribute("pageVO1", pageVO1);

		// 개인정보수집 및 이용에 대한 안내
		pageVO.setPageCd("PAGE002");
		OrgPageVO pageVO2 = orgPageService.view(pageVO);
		request.setAttribute("pageVO2", pageVO2);
		
		return "home/user/join/join_sns01";
	}
	
	/**
     * @Method Name : joinStep02
     * @Method 설명 : 회원 가입 2단계 (정보입력)
	 * @param MainVO 
	 * @param commandMap
	 * @param model
	 * @return  join_step_02
	 * @throws Exception
	 */
	@RequestMapping(value="/joinSns02_Main")
	public String joinSns02(UsrUserInfoVO vo, Map commandMap, ModelMap model, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		commonVOProcessing(vo, request);
		
		// 캐쉬 비활성화
		setCachePagingDisable(response);
		
		String orgCd = UserBroker.getUserOrgCd(request);
		OrgOrgInfoVO orgInfoVO = new OrgOrgInfoVO();
		orgInfoVO.setOrgCd(orgCd);
		orgInfoVO = orgOrgInfoService.view(orgInfoVO);
		request.setAttribute("mbrIdType", orgInfoVO.getMbrIdType());

		UsrUserInfoVO usrUserInfoVO = this.getSessionAttrUserInfoWithAssertCheck(request);

		if("N".equals(usrUserInfoVO.getCheckAgree())) {
			// 동의를 체크하지 않았다면 다시 인증 화면으로..(javascript를 지원하지 않는 브라우져에서도 지원..)
			if(StringUtil.isNull(request.getParameter("agree1")) || StringUtil.isNull(request.getParameter("agree2"))) {
				setAlertMessage(request, "이용약관 동의에 체크를 하셔야 다음 단계를 실행 할 수 있습니다.");
				return "redirect:/home/user/joinStep01_Main";	// 다시 1단계로
			} else {
				usrUserInfoVO.setCheckAgree("Y"); //-- 이용약관 동의 확인.
				this.setSessionUserInfo(request, usrUserInfoVO);
			}
		}
		
		//-- 시작년도 가져오기
		String curYear = DateTimeUtil.getYear();
		SysCfgVO scvo = new SysCfgVO();
		scvo.setCfgCtgrCd("JOINUS");
		scvo.setCfgCd("BIRTHYEAR");
		scvo = sysCfgService.viewCfg(scvo);
		
		List<String> yearList = new ArrayList<String>();
		for(int i= Integer.parseInt(curYear,10); i >= Integer.parseInt(scvo.getCfgVal(),10); i--) {
			yearList.add(Integer.toString(i));
		}
		
		request.setAttribute("yearList", yearList);
		
		/** 현재 소셜 로그인 세션 초기화  */
		if(session.getAttribute("checkNice") == null) { 
			session.setAttribute("ssoSeq","");
			session.setAttribute("ssoType","");

		}

		//휴대폰앞자리
		List<SysCodeVO> moblieCode = sysCodeMemService.getSysCodeList("MOBILE_CODE");
		request.setAttribute("moblieCode", moblieCode);
		
		//지역 전화 코드
		List<SysCodeVO> localPhoneCdList = sysCodeMemService.getSysCodeList("LOCAL_PHONE_CODE");
		request.setAttribute("localPhoneCdList", localPhoneCdList);

		//이메일
		List<SysCodeVO> emailCode = sysCodeMemService.getSysCodeList("USER_EMAIL");
		request.setAttribute("emailCode", emailCode);

		//내외국인 구분
		List<OrgCodeVO> codeList = orgCodeService.listCode(orgCd, "USER_DIV_CD").getReturnList();
		request.setAttribute("codeList", codeList);

		//지역
		List<OrgCodeVO> areaCode = orgCodeService.listCode(orgCd, "AREA_CD").getReturnList();
		request.setAttribute("areaCode", areaCode);
		
		//직업
		List<OrgCodeVO> jobCode = orgCodeService.listCode(orgCd, "JOB_CD").getReturnList();
		request.setAttribute("jobCode", jobCode);
		
		//소속
		List<OrgCodeVO> deptCode = orgCodeService.listCode(orgCd, "DEPT_CD").getReturnList();
		request.setAttribute("deptCode", deptCode);

		//사용자 정보관리
		OrgUserInfoCfgVO ouicvo = new OrgUserInfoCfgVO();
		ouicvo.setOrgCd(orgCd);
		List<OrgUserInfoCfgVO> userInfoCfgList = orgUserInfoCfgService.listForJoin(ouicvo).getReturnList();
		request.setAttribute("userInfoCfgList", userInfoCfgList);
		request.setAttribute("vo", vo);
		
		UsrUserInfoVO joinUsrUserInfoVO = (UsrUserInfoVO)request.getSession().getAttribute("joinUsrUserInfoVO");
		request.setAttribute("joinUsrUserInfoVO", joinUsrUserInfoVO);
		
		return "home/user/join/join_sns02";
	}
	
	/**
     * @Method Name : joinSnsStep01
     * @Method 설명 : SNS 연계 페이지를 호출
	 * @param MainVO 
	 * @param commandMap
	 * @param model
	 * @return  join_step_02
	 * @throws Exception
	 */
	@RequestMapping(value="/joinSnsStep01")
	public String joinSnsStep01(UsrUserInfoVO vo, Map commandMap, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String snsDiv = vo.getSnsDiv();
		String retUrl = "";
		
		if("FACEBOOK".equals(snsDiv)){
			retUrl = "home/user/sns/join_sns_facebook01";
			request.setAttribute("SNS_KEY", KEY_SNS_FACEBOOK);
		}else if("KAKAO".equals(snsDiv)){
			retUrl = "home/user/sns/join_sns_kakao01";
			request.setAttribute("SNS_KEY", KEY_SNS_KAKAO);
		}else if("GOOGLE".equals(snsDiv)){
			retUrl = "home/user/sns/join_sns_google01";
			request.setAttribute("SNS_KEY", KEY_SNS_GOOGLE);
		}else if("NAVER".equals(snsDiv)){
			//네이버와 다음은 세션에 param을 담아놓고 사용
			HttpSession session = request.getSession();
			session.setAttribute("_orgCd", commandMap.get("_orgCd"));
			session.setAttribute("_snsLogin", commandMap.get("_snsLogin"));
			
			retUrl = "home/user/sns/join_sns_naver01";
			request.setAttribute("SNS_KEY", KEY_SNS_NAVER);
		}else{
			//네이버와 다음은 세션에 param을 담아놓고 사용
			HttpSession session = request.getSession();
			session.setAttribute("_orgCd", commandMap.get("_orgCd"));
			session.setAttribute("_snsLogin", commandMap.get("_snsLogin"));
			
			retUrl = "home/user/sns/join_sns_daum01";
			request.setAttribute("SNS_KEY", KEY_SNS_DAUM);
		}
		
		return retUrl;
	}
	
	/**
     * @Method Name : joinSnsStep02
     * @Method 설명 :
	 * @param MainVO 
	 * @param commandMap
	 * @param model
	 * @return  join_step_02
	 * @throws Exception
	 */
	@RequestMapping(value="/joinSnsStep02")
	public String joinSnsStep02(UsrUserInfoVO vo, Map commandMap, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String snsDiv = vo.getSnsDiv();
		String retUrl = "";
		
		//네이버와 다음일때
		HttpSession session = request.getSession();
		request.setAttribute("_orgCd", session.getAttribute("_orgCd"));
		request.setAttribute("_snsLogin", session.getAttribute("_snsLogin"));
		session.removeAttribute("_orgCd");
		session.removeAttribute("_snsLogin");
		
		if("NAVER".equals(snsDiv)){
			retUrl = "home/user/sns/join_sns_naver02";
			request.setAttribute("SNS_KEY", KEY_SNS_NAVER);
		}else{			
			retUrl = "home/user/sns/join_sns_daum02";
			request.setAttribute("SNS_KEY", KEY_SNS_DAUM);
		}
		
		return retUrl;
	}
	
	
	/**
     * @Method Name : joinSnsStep03
     * @Method 설명 : SNS를 통해 사용자를 등록한다. 
	 * @param UsrUserInfoVO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/joinSnsStep03")
	public String joinSnsStep03(UsrUserInfoVO vo, Map commandMap, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		ProcessResultVO<UsrUserInfoVO> result = new ProcessResultVO<UsrUserInfoVO>();
		
		try {
			String userNo = usrUserInfoService.addUserSns(vo, "UI");
			if(userNo == null){
				result.setResult(2);
				result.setMessage(getMessage(request, "user.message.joinin.validate.dup.user"));
			}else{
				result.setResult(1);
				result.setMessage(getMessage(request, "user.message.userinfo.add.success"));
			}
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "user.message.userinfo.add.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}
	
	
	
	private String loginUser(HttpServletRequest request, UsrLoginVO vo)  throws Exception{
		String result = "SUCCESS";
		String orgCd = UserBroker.getUserOrgCd(request);

		UsrUserInfoVO uuivo = new UsrUserInfoVO();
		uuivo.setOrgCd(orgCd);
		boolean snsLoginFailFlag = false;
		uuivo.setUserId(vo.getUserId());
		uuivo.setUserPass(vo.getUserPass());

		//-- 로그인 시도 DB에 기록
		LogUserLoginTryLogVO lultlvo = new LogUserLoginTryLogVO();
		lultlvo.setUserId(uuivo.getUserId());
		lultlvo.setBrowserInfo(request.getHeader("User-Agent"));
		lultlvo.setConnIp(request.getRemoteAddr());

		try {
			uuivo = usrUserInfoService.viewForLogin(uuivo);
			if("Y".equals(uuivo.getLoginUseYn())) {
				//-- 회원의 마지막 접속 정보 등록
				usrLoginService.editLastLogin(uuivo);

				//--홈페이지 로그인 로그 기록 남김
				LogSysLoginLogVO lldto = new LogSysLoginLogVO();
				lldto.setOrgCd(orgCd);
				logSysLoginLogService.add(lldto);

				lultlvo.setLoginSuccYn("Y");
				lultlvo.setUserNo(uuivo.getUserNo());
			}
			
			// 세션 정보를 셋팅해 준다.
			request.getSession().setAttribute(Constants.LOGIN_USERID, uuivo.getUserId());
			request.getSession().setAttribute(Constants.LOGIN_USERNO, uuivo.getUserNo());
			request.getSession().setAttribute(Constants.LOGIN_USERTYPE, uuivo.getWwwAuthGrpCd());
			request.getSession().setAttribute(Constants.LOGIN_USERNAME, uuivo.getUserNm());
			request.getSession().setAttribute(Constants.LOGIN_ADMTYPE, uuivo.getAdminAuthGrpCd());
			request.getSession().setAttribute(Constants.LOGIN_MNGTYPE, uuivo.getMngAuthGrpCd());
			request.getSession().setAttribute(Constants.LOGIN_DEPTCD, uuivo.getDeptCd());
			
			logUserConnLogService.add(request);
			
			
		} catch (DataAccessException e) {
			result = "ERROR";
		} finally {
			//sns로그인 실패일때는 저장안함 
			if(!snsLoginFailFlag){
				logUserLoginTryLogService.add(lultlvo);
			}
		}
		return result;
	}
	
	@RequestMapping("/viewSearchDeptPop2")
	public String viewSearchDeptPop(UsrUserInfoVO vo, HttpServletRequest request) {
		return "/home/user/join/dept_search_pop";
	}
	
	@RequestMapping("/searchDept")
	@ResponseBody
	public ProcessResultListVO<UsrDeptInfoVO> searchDept(UsrDeptInfoVO vo, HttpServletRequest request) throws Exception {
		return usrDeptInfoService.listForSearch(vo);
	}
	
	@RequestMapping("/editEmailPop2")
	public String editEmailPop(UsrUserInfoVO vo, HttpServletRequest request) throws Exception {
		
		List<SysCodeVO> emailCode = sysCodeMemService.getSysCodeList("USER_EMAIL");
		request.setAttribute("emailCode", emailCode);
		
		return "/home/user/info/edit_email_pop";
	}
	
	/**
	 *
	 * 심사용 예외 아이디 확인  
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/selectExceptionIdCheck")
	public String selectExceptionIdCheck(UsrLoginVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {

		UsrLoginVO usrloginVo = new UsrLoginVO();
		String userId = UserBroker.getUserId(request);
		usrloginVo.setUserId(userId);

		String idCheck = usrUserInfoService.selectExceptionIdCheck(usrloginVo);
		Map<String, String> map = new HashMap<String, String>();
		map.put("idCheck", idCheck);
		return JsonUtil.responseJson(response, map);
	}

	
	/**
	 * ID 찾기 결과
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/findIdAtalkTest")
	public String findIdAtalkTest(UsrUserInfoVO vo, Map commandMap, ModelMap model, HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		
		//알림톡 발송
		AtalkVO avo = new AtalkVO();
		//회신번호
		avo.setTranCallback("0212345678");  
		//수신번호
		avo.setTranPhone("01012345678");    
		//템플릿코드 http://talks.mtsco.co.kr에 등록된 템플릿 코드와 일치해야 함
		avo.setTranTmplCd("gkedc_info_00040");
		//기타1 보통 발신자 아이디나 이름으로 사용됨. 발신자 아이디 값 세팅
		avo.setTranEtc1("admin");
		//알림톡 발신 메시지
		StringBuilder sb = new StringBuilder();
		sb.append( 
				"안녕하세요. 알림톡 테스트 메시지 입니다.\r\n"+
				"여기에 발신 메시지를 작성하시면 됩니다."		
				);
		avo.setTranMsg(sb.toString());
		//알림톡 발신 실패 시 발송될 문자 메시지 보통 같은 메시지로 지정하나 긴 알림 시 간략한 내용으로 변경한다.
		avo.setTranReplaceMsg(sb.toString());
		try {
			atalkService.addAtalk(avo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/home/main/indexMain";
	}
}