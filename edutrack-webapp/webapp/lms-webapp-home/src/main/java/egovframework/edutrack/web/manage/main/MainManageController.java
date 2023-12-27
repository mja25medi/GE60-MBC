package egovframework.edutrack.web.manage.main;

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
import egovframework.edutrack.comm.exception.AccessDeniedException;
import egovframework.edutrack.comm.exception.ServiceProcessException;
import egovframework.edutrack.comm.service.SysCodeMemService;
import egovframework.edutrack.comm.service.SysMenuMemService;
import egovframework.edutrack.comm.util.security.ApiSecurityUtils;
import egovframework.edutrack.comm.util.security.CryptoUtil;
import egovframework.edutrack.comm.util.web.DateTimeUtil;
import egovframework.edutrack.comm.util.web.HttpRequestUtil;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.RedisUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.URLBuilder;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.course.createcourseteacher.service.CreateCourseTeacherService;
import egovframework.edutrack.modules.course.createcourseteacher.service.TeacherVO;
import egovframework.edutrack.modules.log.educourse.service.LogEduCourseStatusVO;
import egovframework.edutrack.modules.log.login.service.LogSysLoginLogService;
import egovframework.edutrack.modules.log.login.service.LogSysLoginLogVO;
import egovframework.edutrack.modules.log.logintry.service.LogUserLoginTryLogService;
import egovframework.edutrack.modules.log.logintry.service.LogUserLoginTryLogVO;
import egovframework.edutrack.modules.log.orgadminconn.service.LogOrgAdminConnLogService;
import egovframework.edutrack.modules.log.orgadminconn.service.LogOrgAdminConnLogVO;
import egovframework.edutrack.modules.log.orgstatus.service.LogOrgStatusService;
import egovframework.edutrack.modules.log.orgstatus.service.LogOrgStatusVO;
import egovframework.edutrack.modules.log.sysconn.service.LogSysConnLogService;
import egovframework.edutrack.modules.log.sysconn.service.LogSysConnLogVO;
import egovframework.edutrack.modules.log.sysuser.service.LogSysUserLogService;
import egovframework.edutrack.modules.log.sysuser.service.LogSysUserLogVO;
import egovframework.edutrack.modules.log.userconn.service.LogUserConnLogService;
import egovframework.edutrack.modules.log.userconn.service.LogUserConnLogVO;
import egovframework.edutrack.modules.main.service.MainVO;
import egovframework.edutrack.modules.org.domain.service.OrgDomainService;
import egovframework.edutrack.modules.org.domain.service.OrgDomainVO;
import egovframework.edutrack.modules.org.info.service.OrgOrgInfoService;
import egovframework.edutrack.modules.org.info.service.OrgOrgInfoVO;
import egovframework.edutrack.modules.system.code.service.SysCodeVO;
import egovframework.edutrack.modules.system.config.service.SysCfgService;
import egovframework.edutrack.modules.system.menu.service.SysMenuLangVO;
import egovframework.edutrack.modules.system.menu.service.SysMenuService;
import egovframework.edutrack.modules.system.menu.service.SysMenuVO;
import egovframework.edutrack.modules.user.info.service.UsrLoginService;
import egovframework.edutrack.modules.user.info.service.UsrLoginVO;
import egovframework.edutrack.modules.user.info.service.UsrUserAuthGrpService;
import egovframework.edutrack.modules.user.info.service.UsrUserAuthGrpVO;
import egovframework.edutrack.modules.user.info.service.UsrUserInfoService;
import egovframework.edutrack.modules.user.info.service.UsrUserInfoVO;

@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/mng/main")
public class MainManageController extends GenericController {

	@Autowired @Qualifier("usrUserInfoService")
	private UsrUserInfoService 			usrUserInfoService;

	@Autowired @Qualifier("usrLoginService")
	private UsrLoginService 			usrLoginService;
	
	@Autowired @Qualifier("usrUserAuthGrpService")
	private UsrUserAuthGrpService 		usrUserAuthGrpService;
	
	@Autowired @Qualifier("logOrgStatusService")
	private LogOrgStatusService 		logOrgStatusService;
	
	@Autowired @Qualifier("logOrgAdminConnLogService")
	private LogOrgAdminConnLogService 	logOrgAdminConnLogService;

	//@Autowired @Qualifier("logEduCourseStatusService")
	//private LogEduCourseStatusService 	logEduCourseStatusService;

	@Autowired @Qualifier("sysCfgService")
	private SysCfgService 				sysCfgService;
	
	@Autowired @Qualifier("logUserLoginTryLogService")
	private LogUserLoginTryLogService 	logUserLoginTryLogService;

	@Autowired @Qualifier("logUserConnLogService")
	private LogUserConnLogService 		logUserConnLogService;
	
	@Autowired @Qualifier("sysMenuMemService")
	private SysMenuMemService			sysMenuMemService;
	
	@Autowired @Qualifier("sysMenuService")
	private SysMenuService				sysMenuService;

	@Autowired @Qualifier("logSysConnLogService")
	private LogSysConnLogService		logSysConnLogService;

	@Autowired @Qualifier("logSysLoginLogService")
	private LogSysLoginLogService		logSysLoginLogService;
	
	@Autowired @Qualifier("logSysUserLogService")
	private LogSysUserLogService		logSysUserLogService;
	
	@Autowired @Qualifier("sysCodeMemService")
	private SysCodeMemService 			sysCodeMemService;
	
	@Autowired @Qualifier("orgOrgInfoService")
	private OrgOrgInfoService 		orgOrgInfoService;
	
	@Autowired @Qualifier("createCourseTeacherService")
	private CreateCourseTeacherService	createCourseTeacherService;
	
	@Autowired @Qualifier("orgDomainService")
	private OrgDomainService 		orgDomainService;
	
	private static final String	HOME_INDEX_MAIN_URL = "/home/main/indexMain";
	private static final String	HOME_INDEX_MCD = "MC00000000";
	private static final String	TUTOR_INDEX_MCD = "MC00000003";
	/**
     * @Method Name : mainPage
     * @Method 설명 : 사이트 관리자 로그인 페이지 
	 * @param MainVO 
	 * @param commandMap
	 * @param model
	 * @return  "mng/main/main_page.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/indexMain")
	public String indexMain(MainVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
	
		
		//-- 로그인이 안되어 있을 경우 로그인 페이지로
		String userNo = UserBroker.getUserNo(request);
		String mngType = StringUtil.nvl(UserBroker.getMngType(request));
		
		String serverName = request.getServerName();
		String orgCd = UserBroker.getUserOrgCd(request);
		
		OrgDomainVO orgDomainVO = new OrgDomainVO();
		orgDomainVO.setOrgCd(orgCd);
		orgDomainVO.setOrgDomain(serverName);
		
		orgDomainVO = orgDomainService.view(orgDomainVO);

		if(!orgDomainVO.getDomainTypeCd().equals("ADMIN")) {
			return "redirect:/home/main/indexMain";
		}
		
		if("".equals(userNo) || "".equals(mngType)) {
			return "redirect:"+new URLBuilder("/mng/main/loginMain").toString();
		}

		commonVOProcessing(vo, request);
		LogOrgStatusVO losvo = new LogOrgStatusVO();
		losvo.setOrgCd(orgCd);

		losvo = logOrgStatusService.viewOrgStatus(losvo);
		request.setAttribute("losvo", losvo);

		String curYear = DateTimeUtil.getYear();
		String curMon = DateTimeUtil.getMonth();
		String curDate = DateTimeUtil.getDate();

		LogEduCourseStatusVO lecsvo = new LogEduCourseStatusVO();
		lecsvo.setCreYear(curYear);
		lecsvo.setOrgCd(orgCd);

		//List<LogEduCourseStatusVO> eduCourseStatusList = logEduCourseStatusService.listCourseResultDash(lecsvo).getReturnList();
		//request.setAttribute("eduCourseStatusList", eduCourseStatusList);

		String nowDate = DateTimeUtil.getDateType(1, DateTimeUtil.getDate(),".");
		request.setAttribute("nowDate", nowDate);
		
		//STATUS_TYPE
		List<SysCodeVO> codeList = sysCodeMemService.getSysCodeList("STATUS_TYPE");
		request.setAttribute("codeList", codeList);

		//-- Use Resource Size Log
		/*UseSizeLogDTO usldto = new UseSizeLogDTO();
		usldto.setOrgCd(orgCd);
		usldto.setDivCd("HDD");
		String hddUseSize = "0";
		try {
			usldto = useSizeLogService.view(usldto).getReturnDto();
			hddUseSize = FileUtil.fileSizeFormatter(usldto.getUseSize());
		} catch (Exception e) {

		}
		request.setAttribute("hddUseSize", hddUseSize);*/

		return "mng/main/main_page";
	}

	/**
     * @Method Name : loginMain
     * @Method 설명 : 사이트 관리자 메인 페이지 
	 * @param MainVO 
	 * @param commandMap
	 * @param model
	 * @return  "mng/main/login_page.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/loginMain")
	public String loginMain(MainVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		
		String serverName = request.getServerName();
		String orgCd = UserBroker.getUserOrgCd(request);
		
		OrgDomainVO orgDomainVO = new OrgDomainVO();
		orgDomainVO.setOrgCd(orgCd);
		orgDomainVO.setOrgDomain(serverName);
		orgDomainVO = orgDomainService.view(orgDomainVO);

		if(!orgDomainVO.getDomainTypeCd().equals("ADMIN")) {
			orgDomainVO.setDomainTypeCd("ADMIN");
			orgDomainVO = orgDomainService.viewByServiceTypeCd(orgDomainVO);
			
			String redirectAdminUrl = request.getScheme()+ "://" + orgDomainVO.getOrgDomain()
						+ "/mng/main/loginMain";
			return "redirect:" + redirectAdminUrl;
		}
		
		String mngType = StringUtil.nvl(UserBroker.getMngType(request));

		HttpSession session = request.getSession();
		session.setAttribute(Constants.SYSTEM_LOCALEKEY, UserBroker.getLocaleKey(request));
		session.setAttribute("encryptjs", "Y");
		

		if(!"".equals(mngType)) {
			return "redirect:"+
					new URLBuilder("/mng/main/goMenuPage")
					.addParameter("mcd", "MS00000000")
					.toString();
		}
		return "mng/main/login_page";
	}
	
	/**
     * @Method Name : loginForm
     * @Method 설명 : 사이트 관리자 메인 페이지 
	 * @param MainVO 
	 * @param commandMap
	 * @param model
	 * @return  "mng/main/login_page.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/ssologinMain")
	public String ssologinMain(MainVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {

		String mngType = StringUtil.nvl(UserBroker.getMngType(request));

		HttpSession session = request.getSession();
		session.setAttribute(Constants.SYSTEM_LOCALEKEY, UserBroker.getLocaleKey(request));

		if(!"".equals(mngType)) {
			return "redirect:"+
					new URLBuilder("/mng/main/goMenuPage")
					.addParameter("mcd", "MS00000000")
					.toString();
		}
		return "mng/main/login_page";
	}

	/**
     * @Method Name : login
     * @Method 설명 : 사이트 관리자 로그인 
	 * @param MainVO 
	 * @param commandMap
	 * @param model
	 * @return  "mng/main/login_page.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/login")
	public String login(UsrLoginVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		UsrUserInfoVO uuivo = new UsrUserInfoVO();

		String userId = "";
		String userPass = "";
		String orgCd = UserBroker.getUserOrgCd(request);
		String pswdChgReqYn = "N";

		int faileSec = Integer.parseInt(sysCfgService.getValue("LOGIN", "GAPTIME"))/60;

		// 암호화 파라매터가 넘어왔을 경우 복호화해서 DTO에 설정
		if(StringUtil.isNotNull(vo.getEncryptData())) {
			log.debug("암호화 파라매터 복호화 처리 시작..");
			String decrypt[] = CryptoUtil.descrypt(vo.getEncryptData());
			uuivo.setUserId(decrypt[0]);
			uuivo.setUserPass(decrypt[1]);
			userId = decrypt[0];
			userPass = decrypt[1];
			log.debug("암호화 파라매터 복호화 처리 성공..");
		}
		
		//-- 로그인 시도 DB에 기록
		LogUserLoginTryLogVO lultlvo = new LogUserLoginTryLogVO();
		lultlvo.setUserId(uuivo.getUserId());
		lultlvo.setBrowserInfo(request.getHeader("User-Agent"));
		
		lultlvo.setConnIp(request.getRemoteAddr());

		try {
			//userInfoDTO = userInfoService.viewInfoByIdPw(userInfoDTO).getReturnDto();
			String orgCd2 = StringUtil.nvl(uuivo.getOrgCd(),"");
			if(orgCd2.equals("")){
				uuivo.setOrgCd(orgCd);
			}
			uuivo = usrUserInfoService.viewForLoginCheck(uuivo);

			if(!orgCd.equals(uuivo.getOrgCd())){
				setAlertMessage(request, getMessage(request, "user.message.login.failed"));
				return "redirect:"+
						new URLBuilder("/mng/main/loginMain")		
						.toString();
			}
			if(StringUtil.isNull(uuivo.getMngAuthGrpCd())) {
				throw new AccessDeniedException("user.message.login.noauth2.admin");
			}

			//-- 로그인 시도 성공 기록
			lultlvo.setLoginSuccYn("Y");
			lultlvo.setUserNo(uuivo.getUserNo());
			if("Y".equals(uuivo.getLoginUseYn())) {
				pswdChgReqYn = uuivo.getPswdChgReqYn();
			} else {
				//-- 로그인 할수 없을 경우
				String[] args = new String[1];
				args[0] = Integer.toString(faileSec);
				setAlertMessage(request, getMessage(request, "user.message.login.failed.time", args));
				return "redirect:"+
						new URLBuilder("/mng/main/loginMain")		
						.toString();
			}
		} catch(ServiceProcessException e) {
			setAlertMessage(request, getMessage(request, "user.message.login.failed"));
			return "redirect:"+
					new URLBuilder("/mng/main/loginMain")		
					.toString();
		}
		catch(DataRetrievalFailureException e) {
			uuivo = new UsrUserInfoVO();
			uuivo.setUserId(vo.getUserId());
			String errMsg = getMessage(request, "user.message.login.failed");
			lultlvo.setLoginSuccYn("N");

			try {
				vo = usrLoginService.editFailLogin(vo);
			} catch (Exception ex) {
				setAlertMessage(request, errMsg);
				return "redirect:"+
						new URLBuilder("/mng/main/loginMain")	
						.toString();
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
			return "redirect:"+
					new URLBuilder("/mng/main/loginMain")		
					.toString();
		} finally {
			logUserLoginTryLogService.add(lultlvo);
		}

		// 로그인 처리
		this.processLogin(request, uuivo);

//		//로그인 후 ssl 해제
//		try {
//			//-- 비밀번호 변경기간이 지난경우 강제 비밀번호 변경화면으로 연결.
//			/*if("Y".equals(pswdChgReqYn)) {
//				String mypageMcd = "HM03006000";
//				OrgMenuVO menuVO = sysMenuMemService. decideHomeMenuWithSession(mypageMcd, request);
//				return "redirect:"+new URLBuilder("/home/user/info/chgPassForm.do").toString();
//			}*/
////
////			String requestUrl = request.getRequestURL().toString();
////			//-- 개발 PC아니고 ssl 일때
////			if( requestUrl.indexOf("localhost") < 0 && requestUrl.indexOf("local.edutrack.co.kr") < 0 && requestUrl.indexOf("https://") >= 0) {
////				URLBuilder urlBuilder = new URLBuilder((requestUrl.replace("https://", "https://")));
////
////				HttpRequestUtil.requestParamToUrlBuilder(request, urlBuilder);
////				response.sendRedirect(urlBuilder.toString());
////				return null;
////			}
//		} catch (Exception ie) {
//			return "redirect:"+
//					new URLBuilder("/mng/main/goMenuPage")
//					.addParameter("mcd", "MS00000000")
//					.toString();
//		}

		return "redirect:"+
				new URLBuilder("/mng/main/goMenuPage")
				.addParameter("mcd", "MS00000000")
				.toString();
	}
	
	@RequestMapping(value="/adminLoginProcess")
	public String adminLoginPop(UsrLoginVO vo, HttpServletRequest request) throws Exception {
		
		String orgCd = UserBroker.getUserOrgCd(request);
		String serverName = request.getServerName();
		String mngType = StringUtil.nvl(UserBroker.getMngType(request));
		if(!mngType.contains("MANAGER")) {
			throw new AccessDeniedException();
		}
		request.setAttribute("vo", vo);
		
		UsrUserAuthGrpVO authVO = new UsrUserAuthGrpVO();
		authVO.setUserNo(vo.getUserNo());
		
		List<UsrUserAuthGrpVO> authList = usrUserAuthGrpService.list(authVO).getReturnList();
		boolean isTeacher = authList.stream().filter(this::isTeacherAuth).findAny().isPresent();
		
		String redirectDomain = "";
		
		if(isTeacher) {
			OrgDomainVO orgDomainVO = new OrgDomainVO();
			orgDomainVO.setOrgCd(orgCd);
			orgDomainVO.setOrgDomain(serverName);
			orgDomainVO = orgDomainService.view(orgDomainVO);
			orgDomainVO.setDomainTypeCd("TUTOR");
			orgDomainVO = orgDomainService.viewByServiceTypeCd(orgDomainVO);
			redirectDomain = orgDomainVO.getOrgDomain();
		}else {
			OrgDomainVO orgDomainVO = new OrgDomainVO();
			orgDomainVO.setOrgCd(orgCd);
			orgDomainVO.setOrgDomain(serverName);
			orgDomainVO = orgDomainService.view(orgDomainVO);
			orgDomainVO.setDomainTypeCd("ADMIN");
			orgDomainVO = orgDomainService.viewByServiceTypeCd(orgDomainVO);
			redirectDomain = orgDomainVO.getOrgDomain();
		}
		
		String redirectUrl = request.getScheme() + "://" + redirectDomain + "/mng/main/adminLogin";
		request.setAttribute("redirectUrl", redirectUrl);
		
		return "mng/main/admin_login_process";
	}
	private boolean isTeacherAuth(UsrUserAuthGrpVO auth) {
		String userAuth = auth.getAuthGrpCd();
		return "TEACHER".equals(userAuth) || "TUTOR".equals(userAuth);
	}
	
	@RequestMapping(value="/adminLogin")
	public String adminLogin(UsrLoginVO vo, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String redirectUrl = HOME_INDEX_MAIN_URL;
		
		String orgCd = vo.getOrgCd();
		
		UsrUserInfoVO uuivo = new UsrUserInfoVO();
		uuivo.setOrgCd(vo.getOrgCd());
		uuivo.setUserNo(vo.getUserNo());
		
		LogUserLoginTryLogVO lultlvo = new LogUserLoginTryLogVO();
		lultlvo.setBrowserInfo(request.getHeader("User-Agent"));
		lultlvo.setConnIp(request.getRemoteAddr());
		lultlvo.setUserNo(vo.getUserNo());
		
		request.getSession().invalidate();
		try {
			String mngType = vo.getMngAuthGrpCd();
			if(!mngType.contains("MANAGER")) {
				throw new AccessDeniedException("접근 권한이 없습니다.");
			}
			
			uuivo = usrUserInfoService.view(uuivo);
			lultlvo.setUserId(uuivo.getUserId());

			if(!orgCd.equals(uuivo.getOrgCd())){
				if("N".equals(uuivo.getItgrtMbrUseYn())){
					throw new AccessDeniedException("유저의 기관정보가 올바르지 않습니다.");
				}
			}
			
			String chkUserType = StringUtil.nvl(uuivo.getWwwAuthGrpCd(),"");
			if("".equals(chkUserType)) {
				throw new AccessDeniedException("유저의 권한정보가 올바르지 않습니다.");
			}
			if(chkUserType.contains("TEACHER") || chkUserType.contains("TUTOR")) {
				
				redirectUrl = new URLBuilder("/home/main/goMenuPage")
						.addParameter("mcd", TUTOR_INDEX_MCD)
						.toString();				
				
				TeacherVO teacherVO = new TeacherVO();
				teacherVO.setUserNo(uuivo.getUserNo());
				teacherVO = createCourseTeacherService.isTeacher(teacherVO).getReturnVO();
				if(!"Y".equals(teacherVO.getTchYn())) {
					setAlertMessage(request, "강사로 등록된 과정이 존재하지 않습니다.");
					return "redirect:" + redirectUrl;
				}
			}

			request.getSession().setAttribute(Constants.LOGIN_USERID, uuivo.getUserId());
			request.getSession().setAttribute(Constants.LOGIN_USERNO, uuivo.getUserNo());
			request.getSession().setAttribute(Constants.LOGIN_USERTYPE, uuivo.getWwwAuthGrpCd());
			request.getSession().setAttribute(Constants.LOGIN_USERNAME, uuivo.getUserNm());
			request.getSession().setAttribute(Constants.LOGIN_ADMTYPE, uuivo.getAdminAuthGrpCd());
			request.getSession().setAttribute(Constants.LOGIN_MNGTYPE, uuivo.getMngAuthGrpCd());
			request.getSession().setAttribute(Constants.LOGIN_DEPTCD, uuivo.getDeptCd());
			
			if (Constants.REDIS_CHECK_YN.equals("Y")) {
				int expireTime = 24 * 60 * 60; // 24hour
				RedisUtil.setValue(Constants.REDIS_NAMESPACE+":SID:"+uuivo.getUserId(),
						request.getSession().getId(), expireTime);
			}
			lultlvo.setLoginSuccYn("Y");
			
			logUserConnLogService.add(request);
			
			return "redirect:" + redirectUrl;
			
		} catch (AccessDeniedException e) {
			lultlvo.setLoginSuccYn("N");
			setAlertMessage(request, "회원 로그인에 실패 했습니다.\\n" + e.getMessage());
			return "redirect:" + new URLBuilder("/home/main/goMenuPage").addParameter("mcd", HOME_INDEX_MCD).toString();
		} catch (Exception e) {
			lultlvo.setLoginSuccYn("N");
			setAlertMessage(request, "서버 오류로 회원 로그인에 실패 했습니다");
			return "redirect:" + new URLBuilder("/home/main/goMenuPage").addParameter("mcd", HOME_INDEX_MCD).toString();
		}
		finally {
			logUserLoginTryLogService.add(lultlvo);
		}
	}
	
	/**
     * @Method Name : ssoLogin
     * @Method 설명 : 사이트 관리자 로그인 
	 * @param MainVO 
	 * @param commandMap
	 * @param model
	 * @return  "mng/main/login_page.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/ssoLogin")
	public String ssoLogin(UsrLoginVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		UsrUserInfoVO uuivo = new UsrUserInfoVO();

		String userId = "";
		String userPass = "";
		String orgCd = UserBroker.getUserOrgCd(request);
		String pswdChgReqYn = "N";
		uuivo.setOrgCd(orgCd);

		int faileSec = Integer.parseInt(sysCfgService.getValue("LOGIN", "GAPTIME"))/60;

		// 암호화 파라매터가 넘어왔을 경우 복호화해서 DTO에 설정
		if(StringUtil.isNotNull(vo.getEncryptData())) {
			log.debug("암호화 파라매터 복호화 처리 시작..");
			String decrypt[] = CryptoUtil.descrypt(vo.getEncryptData());
			uuivo.setUserId(decrypt[0]);
			uuivo.setUserPass(decrypt[1]);
			userId = decrypt[0];
			userPass = decrypt[1];
			log.debug("암호화 파라매터 복호화 처리 성공..");
		}
		
		//-- 로그인 시도 DB에 기록
		LogUserLoginTryLogVO lultlvo = new LogUserLoginTryLogVO();
		lultlvo.setUserId(uuivo.getUserId());
		lultlvo.setBrowserInfo(request.getHeader("User-Agent"));
		
		lultlvo.setConnIp(request.getRemoteAddr());

		try {
			//userInfoDTO = userInfoService.viewInfoByIdPw(userInfoDTO).getReturnDto();
//			uuivo = usrUserInfoService.viewForSsoLogin(uuivo);
			String result = usrLoginService.ssoUserIdCheck(uuivo);
			
			// 회원 미가입 자 처리부
			if(result.equals("Y")) {
				//-- 기관(사이트)코드 설정
				//usrUserInfoVO.setOrgCd(orgCd);
				// 세션 체크 및 정보 추출
				//UsrUserInfoVO sessionUsrUserInfoVO = this.getSessionAttrUserInfoWithAssertCheck(request);
				// 세션의 정보를 UsrUserInfoVO에 병합시킨다.
				//BeanUtils.mergeBean(usrUserInfoVO, sessionUsrUserInfoVO);
				
				//-- 회원사 정보 검색
				OrgOrgInfoVO orgInfoVO = new OrgOrgInfoVO();
				orgInfoVO.setOrgCd(orgCd);
				orgInfoVO = orgOrgInfoService.view(orgInfoVO);
				// 저장
				try {
					uuivo.setUserPass(""); //임의의 패스워드 사용되지 않는 정보
					
					uuivo.setOrgCd(orgCd);
					uuivo.setWwwAuthGrpCd("/MANAGER");
					uuivo.setAdminAuthGrpCd("MANAGER");
					uuivo.setMngAuthGrpCd("");
					uuivo.setLoginUseYn("Y");
					uuivo.setMngAuthGrpCd("|MANAGER");
					uuivo.setUserNm(userId);
					
					usrUserInfoService.ssoAdd(uuivo, "UI");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else{
				uuivo.setUserPass("");	
				uuivo = usrUserInfoService.viewForSsoLogin(uuivo);	
				
					uuivo.setLoginUseYn("Y");
					usrLoginService.editLastLogin(uuivo);
					
				try {
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			uuivo = usrUserInfoService.viewForSsoLogin(uuivo);
			
			/**
			 * 처음 접속한 기관으로만 로그인 가능하도록 한다.(관리자)
			if(!orgCd.equals(uuivo.getOrgCd())){
				setAlertMessage(request, getMessage(request, "user.message.login.failed"));
				return "redirect:"+
						new URLBuilder("/MainManage.do")
						.addParameter("cmd", "SsologinForm")		
						.toString();
			}
			 **/
			//-- 로그인 시도 성공 기록
			lultlvo.setLoginSuccYn("Y");
			lultlvo.setUserNo(uuivo.getUserNo());
			if("Y".equals(uuivo.getLoginUseYn())) {
				pswdChgReqYn = uuivo.getPswdChgReqYn();
			} else {
				//-- 로그인 할수 없을 경우
				String[] args = new String[1];
				args[0] = Integer.toString(faileSec);
				setAlertMessage(request, getMessage(request, "user.message.login.failed.time", args));
				return "redirect:"+
						new URLBuilder("/mng/main/SsologinForm")		
						.toString();
			}
		} catch(DataRetrievalFailureException e) {
			uuivo = new UsrUserInfoVO();
			uuivo.setUserId(vo.getUserId());
			String errMsg = getMessage(request, "user.message.login.failed");
			lultlvo.setLoginSuccYn("N");

			try {
				vo = usrLoginService.editFailLogin(vo);
			} catch (Exception ex) {
				setAlertMessage(request, errMsg);
				return "redirect:"+
						new URLBuilder("/mng/main/SsologinForm")		
						.toString();
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
			return "redirect:"+
					new URLBuilder("/mng/main/SsologinForm")		
					.toString();
		} finally {
			logUserLoginTryLogService.add(lultlvo);
		}

		// 로그인 처리
		this.processLogin(request, uuivo);

		//로그인 후 ssl 해제
		try {
			//-- 비밀번호 변경기간이 지난경우 강제 비밀번호 변경화면으로 연결.
			/*if("Y".equals(pswdChgReqYn)) {
				String mypageMcd = "HM03006000";
				OrgMenuVO menuVO = sysMenuMemService. decideHomeMenuWithSession(mypageMcd, request);
				return "redirect:"+new URLBuilder("/home/user/info/chgPassForm.do").toString();
			}*/

			String requestUrl = request.getRequestURL().toString();
			//-- 개발 PC아니고 ssl 일때
			if( requestUrl.indexOf("localhost") < 0 && requestUrl.indexOf("local.edutrack.co.kr") < 0 && requestUrl.indexOf("https://") >= 0) {
				URLBuilder urlBuilder = new URLBuilder((requestUrl.replace("https://", "http://")));

				HttpRequestUtil.requestParamToUrlBuilder(request, urlBuilder);
				response.sendRedirect(urlBuilder.toString());
				return null;
			}
		} catch (Exception ie) {
			return "redirect:"+
					new URLBuilder("/mng/main/goMenuPage")
					.addParameter("mcd", "MS00000000")
					.toString();
		}

		return "redirect:"+
				new URLBuilder("/mng/main/goMenuPage")
				.addParameter("mcd", "MS00000000")
				.toString();
	}


	/**
     * @Method Name : logout
     * @Method 설명 : 사이트 관리자 로그아웃 
	 * @param MainVO 
	 * @param commandMap
	 * @param model
	 * @return  "mng/main/login_page.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/logout")
	public String logout(UsrLoginVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {

		LogUserConnLogVO luclvo = new LogUserConnLogVO();
		luclvo.setUserNo(UserBroker.getUserNo(request));
		logUserConnLogService.edit(luclvo);
		
		//기관관리자 접속종료 로그 남김
		LogOrgAdminConnLogVO laclvo = new LogOrgAdminConnLogVO();
		laclvo.setUserNo(UserBroker.getUserNo(request));
		laclvo.setLoginIp(request.getRemoteAddr());
		logOrgAdminConnLogService.edit(laclvo);

		// 세션 정보 초기화
		request.getSession().invalidate();
		return "redirect:"+
				new URLBuilder("/mng/main/loginMain")
				.toString();
	}

	
	/**
     * @Method Name : gomenuPage
     * @Method 설명 : 사이트 관리자 메뉴 연결 
	 * @param MainVO 
	 * @param commandMap
	 * @param model
	 * @return  
	 * @throws Exception
	 */
	@RequestMapping(value="/goMenuPage")
	public String goMenuPage(UsrLoginVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {	
		commonVOProcessing(vo, request);
		
		//-- 요청 메뉴 코드를 가져온다.
		String mcd = vo.getMcd();
		String mngType = UserBroker.getMngType(request);
		String locale = UserBroker.getLocaleKey(request);

		SysMenuVO smvo = new SysMenuVO();
		try {
			//-- 권한 설정 검색
			smvo.setMenuType("MANAGE");
			smvo.setMenuCd(mcd);
			smvo.setAuthGrpCd(mngType);
			smvo = sysMenuService.viewMenu(smvo);
			for(SysMenuLangVO smlvo : smvo.getMenuLangList()) {
				if(locale.equals(smlvo.getLangCd())) smvo.setMenuNm(smlvo.getMenuNm());
			}

			String location = getMenuLocation(mngType, locale, smvo.getMenuPath());

			// 세션 정보를 셋팅해 준다.
			request.getSession().setAttribute(Constants.MENU_LOCATION, location);
			request.getSession().setAttribute(Constants.CUR_MENU_TITLE, smvo.getMenuTitle());
			request.getSession().setAttribute(Constants.CUR_MENU_NAME, smvo.getMenuNm());
			request.getSession().setAttribute(Constants.CUR_MENU_CODE, smvo.getMenuCd());

			//SSL 적용/해제
			String requestUrl = request.getRequestURL().toString();
			if("".equals(StringUtil.nvl(smvo.getSslYn())))
				smvo.setSslYn("N");

			//-- 개발 PC아닌 경우 SSL 태우도록 한다.
//			if( requestUrl.indexOf("localhost") < 0 && requestUrl.indexOf("ge60.mediopia.co.kr") < 0) {
//
//				if( requestUrl.indexOf("http://") >= 0 && "Y".equals(smvo.getSslYn())) {
//					URLBuilder urlBuilder = new URLBuilder((requestUrl.replace("http://", "https://")).replace(":8088", ":444"));
//
//					HttpRequestUtil.requestParamToUrlBuilder(request, urlBuilder);
//
//					response.sendRedirect(urlBuilder.toString());
//					return null;
//				}
//
//				if( requestUrl.indexOf("https://") >= 0 && "N".equals(smvo.getSslYn())) {
//					URLBuilder urlBuilder = new URLBuilder((requestUrl.replace("https://", "http://")).replace(":444", ":8088"));
//
//					HttpRequestUtil.requestParamToUrlBuilder(request, urlBuilder);
//
//					response.sendRedirect(urlBuilder.toString());
//					return null;
//
//				}
//			}

		} catch (Exception e) {
			return "redirect:"+
					new URLBuilder("/mng/main/mainPage")
					.toString();
		}
		return "redirect:"+smvo.getMenuUrl();
	}

	/**
     * @Method Name : indexChgLang
     * @Method 설명 : 사이트 관리자 언어 설정 변경 
	 * @param MainVO 
	 * @param commandMap
	 * @param model
	 * @return  
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

		return "redirect:"+
				new URLBuilder("/mng/main/goMenuPage")
				.addParameter("mcd", mcd)
				.toString();
	}

	/**
	 * 관리자의 로그인을 처리 한다. 세션정보 저장 및 관리자 로그인 로그 저장
	 * @param request
	 * @param userDTO
	 */
	private void processLogin(HttpServletRequest request, UsrUserInfoVO uuivo) throws Exception {
		HttpSession session = request.getSession();

		// 세션 정보를 셋팅해 준다.
		session.setAttribute(Constants.LOGIN_USERID, uuivo.getUserId());
		session.setAttribute(Constants.LOGIN_USERNO, uuivo.getUserNo());
		session.setAttribute(Constants.LOGIN_USERTYPE, uuivo.getWwwAuthGrpCd());
		session.setAttribute(Constants.LOGIN_USERNAME, uuivo.getUserNm());
		session.setAttribute(Constants.LOGIN_ADMTYPE, uuivo.getAdminAuthGrpCd());
		session.setAttribute(Constants.LOGIN_MNGTYPE, uuivo.getMngAuthGrpCd());

		//-- 메뉴관련 세션은 초기화.
		session.setAttribute(Constants.MENU_LOCATION, "");
		session.setAttribute(Constants.CUR_MENU_NAME, "");
		session.setAttribute(Constants.CUR_MENU_CODE, "");
		session.setAttribute(Constants.ROT_MENU_CODE, "");
		
		//기관관리자 접속 로그 남김
		LogOrgAdminConnLogVO laclvo = new LogOrgAdminConnLogVO();
		laclvo.setOrgCd(uuivo.getOrgCd());
		laclvo.setUserNo(uuivo.getUserNo());
		laclvo.setUserNm(uuivo.getUserNm());
		laclvo.setLoginIp(request.getRemoteAddr());
		logOrgAdminConnLogService.add(laclvo);

		//사용자로그는 제거
		//logUserConnLogService.add(request);
	}

	private String getMenuLocation(String authGrpCd, String locale, String menuPath) throws Exception {
		String[] menuCds = StringUtil.split(menuPath,"|");
		String[] authGrpCds = StringUtil.split(authGrpCd,"|");
		String menuPathNm = "";
		for(int i=1; i < menuCds.length; i++) {
			SysMenuVO smvo = null;
			for(int j=0; j < authGrpCds.length; j++) {
				smvo = sysMenuService.getMenuByCache("MANAGE", authGrpCd, menuCds[i]);
				if(ValidationUtils.isNotEmpty(smvo)) break;
			}
			if(i > 1) menuPathNm += " > ";
			String menuName = smvo.getMenuNm();
			for(SysMenuLangVO smlvo : smvo.getMenuLangList()) {
				if(locale.equals(smlvo.getLangCd())) menuName = smlvo.getMenuNm();
			}
			menuPathNm += menuName;
		}
		return menuPathNm;
	}

	
	/**
     * @Method Name : searchCourse
     * @Method 설명 : 사이트 관리자 과정 검색 
	 * @param MainVO 
	 * @param commandMap
	 * @param model
	 * @return  
	 * @throws Exception
	 */
	@RequestMapping(value="/searchCourse")
	public String searchCourse(MainVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {	
		commonVOProcessing(vo, request);
		
		String orgCd = UserBroker.getUserOrgCd(request);

		/*CreateCourseDTO ccdto = new CreateCourseDTO();
		ccdto.setOrgCd(orgCd);
		ccdto.setCrsCreNm(mainForm.getSearchTxt());

		List<CreateCourseDTO> creCrsList = createCourseService.listCreateCourse(ccdto).getReturnList();
		request.setAttribute("creCrsList", creCrsList);*/
		
		return "mng/main/list_course";
	}

	/**
     * @Method Name : searchCourse
     * @Method 설명 : 사이트 관리자 과정 검색 
	 * @param MainVO 
	 * @param commandMap
	 * @param model
	 * @return  
	 * @throws Exception
	 */
	@RequestMapping(value="/searchMember")
	public String searchMember(MainVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {	
		commonVOProcessing(vo, request);
		
		String orgCd = UserBroker.getUserOrgCd(request);

		UsrUserInfoVO uuivo = new UsrUserInfoVO();
		uuivo.setOrgCd(orgCd);
		uuivo.setUserSts("U"); //-- 사용중인 사용자중.
		uuivo.setSearchValue(vo.getSearchValue());
		uuivo.setSearchKey(vo.getSearchKey());

		List<UsrUserInfoVO> userList = usrUserInfoService.list(uuivo).getReturnList();
		request.setAttribute("userList", userList);

		return "mng/main/list_user";
	}

	/**
     * @Method Name : connectLogChart
     * @Method 설명 : 사이트 접속 로그 
	 * @param MainVO 
	 * @param commandMap
	 * @param model
	 * @return  
	 * @throws Exception
	 */
	@RequestMapping(value="/connectLogChart")
	public String connectLogChart(MainVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {	
		commonVOProcessing(vo, request);
		
		String orgCd = UserBroker.getUserOrgCd(request);

		String startDt = request.getParameter("startDt");
		String endDt = request.getParameter("endDt");
		String type = request.getParameter("type");

		request.setAttribute("TYPE",type);

		//-- List Connect Log
		LogSysConnLogVO lsclvo = new LogSysConnLogVO();
		lsclvo.setOrgCd(orgCd);
		lsclvo.setStartDt(startDt);
		lsclvo.setEndDt(endDt);
		lsclvo.setViewType(type);
		List<LogSysConnLogVO> connectLogList = logSysConnLogService.listLog(lsclvo).getReturnList();
		request.setAttribute("connectLogList", connectLogList);

		//-- List Login Log
		LogSysLoginLogVO lsllvo = new LogSysLoginLogVO();
		lsllvo.setOrgCd(orgCd);
		lsllvo.setStartDt(startDt);
		lsllvo.setEndDt(endDt);
		lsllvo.setViewType(type);
		List<LogSysLoginLogVO> loginLogList = logSysLoginLogService.listLog(lsllvo).getReturnList();
		request.setAttribute("loginLogList", loginLogList);
		
		//-- List Login Log
		LogSysUserLogVO lsulvo = new LogSysUserLogVO();
		lsulvo.setOrgCd(orgCd);
		lsulvo.setStartDt(startDt);
		lsulvo.setEndDt(endDt);
		lsulvo.setViewType(type);
		List<LogSysUserLogVO> userLogList = logSysUserLogService.listLog(lsulvo).getReturnList();
		request.setAttribute("userLogList", userLogList);

				
		return "mng/main/list_chart";
	}	
	
	/**
     * @Method Name : securityPwd
     * @Method 설명 : 가입된 회원정보 체크 및 sso 패스워드 암호화 
	 * @param MainVO 
	 * @param commandMap
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/securityPwd")
	public String securityPwd(UsrLoginVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String result = usrLoginService.userIdCheck(vo);
			vo.setIsUseable(result);
			vo.setEncUserPass( ApiSecurityUtils.encryptAes128(vo.getUserPass(), vo.getSsoKey()));
		} catch (Exception e) {System.out.println("error:"+e.toString());}
		return JsonUtil.responseJson(response, vo);

	}

}
