package egovframework.edutrack.web.main;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.service.SysCodeMemService;
import egovframework.edutrack.comm.service.SysMenuMemService;
import egovframework.edutrack.comm.util.security.CryptoUtil;
import egovframework.edutrack.comm.util.web.HttpRequestUtil;
import egovframework.edutrack.comm.util.web.LocaleUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.URLBuilder;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.log.adminconn.service.LogAdminConnLogService;
import egovframework.edutrack.modules.log.adminconn.service.LogAdminConnLogVO;
import egovframework.edutrack.modules.log.orgstatus.service.LogOrgStatusService;
import egovframework.edutrack.modules.log.orgstatus.service.LogOrgStatusVO;
import egovframework.edutrack.modules.main.service.MainVO;
import egovframework.edutrack.modules.system.code.service.SysCodeLangVO;
import egovframework.edutrack.modules.system.code.service.SysCodeVO;
import egovframework.edutrack.modules.system.menu.service.SysMenuLangVO;
import egovframework.edutrack.modules.system.menu.service.SysMenuService;
import egovframework.edutrack.modules.system.menu.service.SysMenuVO;
import egovframework.edutrack.modules.user.info.service.UsrLoginVO;
import egovframework.edutrack.modules.user.info.service.UsrUserInfoService;
import egovframework.edutrack.modules.user.info.service.UsrUserInfoVO;

@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/adm/main")
public class MainAdminController extends GenericController {
	
    /** service */
	@Autowired @Qualifier("usrUserInfoService")
	private UsrUserInfoService 		usrUserInfoService;

	@Autowired @Qualifier("logAdminConnLogService")
	private LogAdminConnLogService 	logAdminConnLogService;
	
	@Autowired @Qualifier("sysMenuService")
	private SysMenuService 			sysMenuService;
	
	@Autowired @Qualifier("sysCodeMemService")
	private SysCodeMemService 		sysCodeMemService;

	@Autowired @Qualifier("sysMenuMemService")
	private SysMenuMemService 		sysMenuMemService;

	@Autowired @Qualifier("logOrgStatusService")
	private LogOrgStatusService		logOrgStatusService;
	
	/**
     * @Method Name : MainPage
     * @Method 설명 : 관리자 메인 프레임 페이지 
	 * @param  
	 * @param commandMap
	 * @param model
	 * @return  "/main/main_page.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/main")
	public String main(MainVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		
		String localKey = UserBroker.getLocaleKey(request);
		
		List<SysCodeVO> langList = sysCodeMemService.getSysCodeList("LANG_CD");
		for(SysCodeVO sysCodeVO : langList) {
			for(SysCodeLangVO sysCodeLangVO : sysCodeVO.getCodeLangList()) {
				if(localKey.equals(sysCodeLangVO.getLangCd())) sysCodeVO.setCodeNm(sysCodeLangVO.getCodeNm());
			}
		}
		request.setAttribute("langList",langList);

		String authGrpCd = UserBroker.getAdmType(request);
		if("".equals(authGrpCd)) authGrpCd = "GUEST";
		List<SysMenuVO> sysMenuList = sysMenuMemService.getAdmMenuList(authGrpCd);
		request.setAttribute("sysMenuList",sysMenuList);		
		request.setAttribute("paging", "Y");

		String Action = request.getRequestURI();
		return "main/main_frame";
	}	
	
	/**
     * @Method Name : MainPage
     * @Method 설명 : 관리자 메인 페이지 
	 * @param  
	 * @param commandMap
	 * @param model
	 * @return  "/main/main_page.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/mainPageMain")
	public String mainPageMain(MainVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		
		LogOrgStatusVO losvo = logOrgStatusService.viewTotalStatus(new LogOrgStatusVO());
		request.setAttribute("orgStatusVO", losvo);
		request.setAttribute("paging", "Y");
	
		return "main/main_page";
	}
	
	@RequestMapping(value="/indexMainPageMain")
	public String indexMainPageMain(MainVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		
		LogOrgStatusVO losvo = logOrgStatusService.viewTotalStatus(new LogOrgStatusVO());
		request.setAttribute("orgStatusVO", losvo);
		request.setAttribute("paging", "Y");
	
		return "main/main_page";
	}
	
	/**
     * @Method Name : LoginForm
     * @Method 설명 : 로그인 페이지 
	 * @param  
	 * @param commandMap
	 * @param model
	 * @return  "/system/code/main.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/loginForm")
	public String loginForm(MainVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		
		String admType = StringUtil.nvl(UserBroker.getAdmType(request));
		HttpSession session = request.getSession();
		session.setAttribute(Constants.SYSTEM_LOCALEKEY, UserBroker.getLocaleKey(request));
		if(!"".equals(admType)) {
			//return "redirect:"+new URLBuilder("/adm/main/goMenuPage").addParameter("mcd", "MA01001000").toString();
			return "redirect:/adm/main/indexMainPageMain";
		}
		request.setAttribute("encryptjs", "Y");
		
		
		return "main/login_page";
	}
	
	/**
     * @Method Name : Login
     * @Method 설명 : 관리자 로그인 처리 
	 * @param  
	 * @param commandMap
	 * @param model
	 * @return  ""
	 * @throws Exception
	 */
	@RequestMapping(value="/login")
	public String login(UsrLoginVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request,  HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String userId = "";
		String userPass = "";
		
		UsrUserInfoVO usrUserInfoVO = new UsrUserInfoVO();

		// 암호화 파라매터가 넘어왔을 경우 복호화해서 DTO에 설정
		if(StringUtil.isNotNull(vo.getEncryptData())) {
			log.debug("암호화 파라매터 복호화 처리 시작..");
			String decrypt[] = CryptoUtil.descrypt(vo.getEncryptData());
			usrUserInfoVO.setUserId(decrypt[0]);
			usrUserInfoVO.setUserPass(decrypt[1]);
			userId = decrypt[0];
			userPass = decrypt[1];
			log.debug("암호화 파라매터 복호화 처리 성공..");
		}

		try {
			usrUserInfoVO = usrUserInfoService.viewForLogin(usrUserInfoVO);
			if(!usrUserInfoVO.getUserPass().equals(usrUserInfoVO.getEncUserPass())) {
				setAlertMessage(request, getMessage(request, "user.message.login.failed"));
				return "redirect:/adm/main/loginForm";
			}
		} catch (DataRetrievalFailureException e) {
			setAlertMessage(request, getMessage(request, "user.message.login.failed"));
			return "redirect:/adm/main/loginForm";
		}

		if(StringUtil.isNull(usrUserInfoVO.getAdminAuthGrpCd())) {
			throw new Exception(getMessage(request, "user.message.login.noauth.admin"));
		}

		if(StringUtil.isNull(usrUserInfoVO.getAdminLoginAcptDivCd()) || !usrUserInfoVO.getAdminLoginAcptDivCd().equals("ACNT")) {
			throw new Exception(getMessage(request, "user.message.login.noaccount"));
		}

		// 로그인 처리
		this.processLogin(request, usrUserInfoVO);

		//로그인 후 ssl 해제
		try {
			String requestUrl = request.getRequestURL().toString();
			//-- 개발 PC아니고 ssl 일때
			if( requestUrl.indexOf("localhost") < 0 && requestUrl.indexOf("local.edutrack.co.kr") < 0 && requestUrl.indexOf("https://") >= 0) {
				URLBuilder urlBuilder = new URLBuilder((requestUrl.replace("https://", "http://")).replace(":444", ":8088"));

				HttpRequestUtil.requestParamToUrlBuilder(request, urlBuilder);
				response.sendRedirect(urlBuilder.toString());
				return null;
			}
		} catch (Exception ie) {
			//return "redirect:/adm/main/goMenuPage?mcd=MA01000000";
			return "redirect:/adm/main/indexMainPageMain";
		}
		//return "redirect:/adm/main/goMenuPage?mcd=MA01000000";
		return "redirect:/adm/main/indexMainPageMain";
	}
	
	/**
	 * 관리자의 로그인을 처리 한다. 세션정보 저장 및 관리자 로그인 로그 저장
	 * @param request
	 * @param userDTO
	 */
	private void processLogin(HttpServletRequest request, 
			UsrUserInfoVO usrUserInfoVO) throws Exception {
		
		HttpSession session = request.getSession();
		LogAdminConnLogVO laclvo = new LogAdminConnLogVO();

		//--- 관리자 접속 로그 남김
		laclvo.setUserNo(usrUserInfoVO.getUserNo());
		laclvo.setUserNm(usrUserInfoVO.getUserNm());
		laclvo.setLoginIp(request.getRemoteAddr());
		logAdminConnLogService.add(laclvo);

		// 세션 정보를 셋팅해 준다.
		session.setAttribute(Constants.LOGIN_USERID, usrUserInfoVO.getUserId());
		session.setAttribute(Constants.LOGIN_USERNO, usrUserInfoVO.getUserNo());
		session.setAttribute(Constants.LOGIN_USERTYPE, usrUserInfoVO.getWwwAuthGrpCd());
		session.setAttribute(Constants.LOGIN_USERNAME, usrUserInfoVO.getUserNm());
		session.setAttribute(Constants.LOGIN_ADMTYPE, usrUserInfoVO.getAdminAuthGrpCd());

		//-- 메뉴관련 세션은 초기화.
		session.setAttribute(Constants.MENU_LOCATION, "");
		session.setAttribute(Constants.CUR_MENU_NAME, "");
		session.setAttribute(Constants.CUR_MENU_CODE, "");
		session.setAttribute(Constants.ROT_MENU_CODE, "");
	}
	
	/**
     * @Method Name : Login
     * @Method 설명 : 관리자 로그인 처리 
	 * @param  
	 * @param commandMap
	 * @param model
	 * @return  ""
	 * @throws Exception
	 */
	@RequestMapping(value="/logout")
	public String logout(UsrLoginVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		
		try {
			//--- 관리자 로그아웃 로그 남김
			LogAdminConnLogVO laclvo = new LogAdminConnLogVO();
			laclvo.setUserNo(UserBroker.getUserNo(request));
			int connLogSn = logAdminConnLogService.viewLast(laclvo);
			laclvo.setConnLogSn(connLogSn);
			logAdminConnLogService.edit(laclvo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 세션 정보 초기화
		request.getSession().invalidate();
		return "redirect:/adm/main/loginForm";
	}
	
	/**
     * @Method Name : gomenuPage
     * @Method 설명 : 메뉴 코드를 세션에 셋팅하고 메뉴로 연결한다. 
	 * @param  
	 * @param commandMap
	 * @param model
	 * @return  ""
	 * @throws Exception
	 */
	@RequestMapping(value="/goMenuPage")
	public String goMenuPage(UsrLoginVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request,  HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		//-- 요청 메뉴 코드를 가져온다.
		String mcd = vo.getMcd();
		String admType = UserBroker.getAdmType(request);
		String locale = UserBroker.getLocaleKey(request);

		SysMenuVO sysMenuVO = new SysMenuVO();
		try {
			//-- 권한 설정 검색
			sysMenuVO.setMenuType("ADMIN");
			sysMenuVO.setMenuCd(mcd);
			sysMenuVO.setAuthGrpCd(admType);
			sysMenuVO = sysMenuService.viewMenu(sysMenuVO);
			for(SysMenuLangVO menuLangVO : sysMenuVO.getMenuLangList()) {
				if(locale.equals(menuLangVO.getLangCd())) sysMenuVO.setMenuNm(menuLangVO.getMenuNm());
			}

			String location = getMenuLocation(admType, locale, sysMenuVO.getMenuPath());

			// 세션 정보를 셋팅해 준다.
			request.getSession().setAttribute(Constants.MENU_LOCATION, location);
			request.getSession().setAttribute(Constants.CUR_MENU_NAME, sysMenuVO.getMenuNm());
			request.getSession().setAttribute(Constants.CUR_MENU_CODE, sysMenuVO.getMenuCd());

			//SSL 적용/해제
			String requestUrl = request.getRequestURL().toString();
			if("".equals(StringUtil.nvl(sysMenuVO.getSslYn())))
				sysMenuVO.setSslYn("N");

			//-- 개발 PC아닌 경우 SSL 태우도록 한다.
			if( requestUrl.indexOf("localhost") < 0 && requestUrl.indexOf("local.edutrack.co.kr") < 0) {
				if( requestUrl.indexOf("http://") >= 0 && "Y".equals(sysMenuVO.getSslYn())) {
					URLBuilder urlBuilder = new URLBuilder((requestUrl.replace("http://", "https://")).replace(":8088", ":444"));
					HttpRequestUtil.requestParamToUrlBuilder(request, urlBuilder);
					response.sendRedirect(urlBuilder.toString());
					return null;
				}
				if( requestUrl.indexOf("https://") >= 0 && "N".equals(sysMenuVO.getSslYn())) {
					URLBuilder urlBuilder = new URLBuilder((requestUrl.replace("https://", "http://")).replace(":444", ":8088"));
					HttpRequestUtil.requestParamToUrlBuilder(request, urlBuilder);
					response.sendRedirect(urlBuilder.toString());
					return null;
				}
			}
		} catch (Exception e) {
			//return "redirect:/adm/main/goMenuPage?mcd=MA00000000";
			return "redirect:/adm/main/loginForm";
			
		}
		return "redirect:"+sysMenuVO.getMenuUrl();
	}	

	/**
     * @Method Name : Login
     * @Method 설명 : 언어 설정을 변경한다. 
	 * @param  
	 * @param commandMap
	 * @param model
	 * @return  ""
	 * @throws Exception
	 */
	@RequestMapping(value="/changeLang")
	public String changeLang(UsrLoginVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		
		String localeKey = request.getParameter("langCd");
		HttpSession session = request.getSession();
		String mcd = (String)session.getAttribute(Constants.CUR_MENU_CODE);
		session.setAttribute(Constants.SYSTEM_LOCALEKEY, localeKey);
		LocaleUtil.setLocale(request, localeKey);
		return "redirect:/adm/main/goMenuPage?mcd="+mcd;
	}
	
	private String getMenuLocation(String authGrpCd, String locale, String menuPath) throws Exception {
		String[] menuCds = StringUtil.split(menuPath,"|");
		String[] authGrpCds = StringUtil.split(authGrpCd,"|");
		String menuPathNm = "";
		for(int i=1; i < menuCds.length; i++) {
			SysMenuVO sysMenuVO = null;
			for(int j=0; j < authGrpCds.length; j++) {
				sysMenuVO = sysMenuService.getMenuByCache("ADMIN", authGrpCd, menuCds[i]);
				if(ValidationUtils.isNotEmpty(sysMenuVO)) break;
			}
			if(i > 1) menuPathNm += " > ";
			String menuName = sysMenuVO.getMenuNm();
			for(SysMenuLangVO menuLangVO : sysMenuVO.getMenuLangList()) {
				if(locale.equals(menuLangVO.getLangCd())) menuName = menuLangVO.getMenuNm();
			}
			menuPathNm += menuName;
		}
		return menuPathNm;
	}	
}
