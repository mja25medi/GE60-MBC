package egovframework.edutrack.web.home.teacher;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.service.SysCodeMemService;
import egovframework.edutrack.comm.service.SysMenuMemService;
import egovframework.edutrack.comm.util.security.CryptoUtil;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.RedisUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.URLBuilder;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.course.createcourseteacher.service.CreateCourseTeacherService;
import egovframework.edutrack.modules.course.createcourseteacher.service.TeacherVO;
import egovframework.edutrack.modules.log.login.service.LogSysLoginLogService;
import egovframework.edutrack.modules.log.login.service.LogSysLoginLogVO;
import egovframework.edutrack.modules.log.logintry.service.LogUserLoginTryLogService;
import egovframework.edutrack.modules.log.logintry.service.LogUserLoginTryLogVO;
import egovframework.edutrack.modules.log.userconn.service.LogUserConnLogService;
import egovframework.edutrack.modules.org.code.service.OrgCodeService;
import egovframework.edutrack.modules.org.config.service.OrgUserInfoCfgService;
import egovframework.edutrack.modules.org.domain.service.OrgDomainService;
import egovframework.edutrack.modules.org.domain.service.OrgDomainVO;
import egovframework.edutrack.modules.org.info.service.OrgOrgInfoService;
import egovframework.edutrack.modules.org.menu.service.OrgMenuVO;
import egovframework.edutrack.modules.org.page.service.OrgPageService;
import egovframework.edutrack.modules.system.code.service.SysCodeVO;
import egovframework.edutrack.modules.system.config.service.SysCfgService;
import egovframework.edutrack.modules.system.menu.service.SysMenuService;
import egovframework.edutrack.modules.teacher.info.service.TchInfoService;
import egovframework.edutrack.modules.teacher.info.service.TchInfoVO;
import egovframework.edutrack.modules.user.dept.service.UsrDeptInfoService;
import egovframework.edutrack.modules.user.info.service.UsrLoginService;
import egovframework.edutrack.modules.user.info.service.UsrLoginVO;
import egovframework.edutrack.modules.user.info.service.UsrUserAuthGrpService;
import egovframework.edutrack.modules.user.info.service.UsrUserAuthGrpVO;
import egovframework.edutrack.modules.user.info.service.UsrUserInfoService;
import egovframework.edutrack.modules.user.info.service.UsrUserInfoVO;

@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/home/tch/info")
public class TchInfoHomeController extends GenericController {

	@Autowired @Qualifier("tchInfoService")
	private TchInfoService 	tchInfoService;

	@Autowired @Qualifier("usrUserAuthGrpService")
	private UsrUserAuthGrpService usrUserAuthGrpService;

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

	@Autowired @Qualifier("orgPageService")
	private OrgPageService 		orgPageService;
	
	@Autowired @Qualifier("usrDeptInfoService")
	private UsrDeptInfoService	usrDeptInfoService;
	
	@Autowired @Qualifier("createCourseTeacherService")
	private CreateCourseTeacherService	createCourseTeacherService;
	
	@Autowired @Qualifier("orgDomainService")
	private OrgDomainService 		orgDomainService;

	@Autowired @Qualifier("sysCfgService")
	private SysCfgService				configService;


	/**
	 * 강사정보 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/viewTch")
	public String viewTch(TchInfoVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception{
		commonVOProcessing(vo, request);

		String userNo = UserBroker.getUserNo(request);
		UsrUserInfoVO usrUserInfoVO = new UsrUserInfoVO();
		usrUserInfoVO.setUserNo(userNo);

		String orgCd = UserBroker.getUserOrgCd(request);
		String creCrsCd = request.getParameter("CRSCRECD");
		usrUserInfoVO.setOrgCd(orgCd);

		String isPop = vo.getIsPop();

		try {
			vo = tchInfoService.view(vo).getReturnVO();
			request.setAttribute("gubun", "E");
		} catch (Exception e) {
			request.setAttribute("gubun", "A");
		}
		

		String teacherYn = "N";
		UsrUserAuthGrpVO uagVO = new UsrUserAuthGrpVO();
		uagVO.setUserNo(usrUserInfoVO.getUserNo());
		List<UsrUserAuthGrpVO> authList = usrUserAuthGrpService.list(uagVO).getReturnList();
		for(int i=0; i < authList.size(); i++) {
			UsrUserAuthGrpVO iagVO = authList.get(i);
			if("HOME".equals(iagVO.getMenuType())) {
				if("TEACHER".equals(iagVO.getAuthGrpCd())) teacherYn = "Y";
			}
		}
		request.setAttribute("teacherYn", teacherYn);
		request.setAttribute("isPop", isPop);
		request.setAttribute("creCrsCd", creCrsCd);

		request.setAttribute("usrUserInfoVO", usrUserInfoVO);
		request.setAttribute("vo", vo);


		return "home/teacher/teacher_info_view";

	}

	/**
	 * 강사정보수정 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/editForm")
	public String editForm(TchInfoVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception{
		commonVOProcessing(vo, request);

		String userNo = UserBroker.getUserNo(request);
		UsrUserInfoVO usrUserInfoVO = new UsrUserInfoVO();

		usrUserInfoVO.setUserNo(userNo);
		vo.setUserNo(userNo);

		String orgCd = UserBroker.getUserOrgCd(request);
		usrUserInfoVO.setOrgCd(orgCd);

	    List<SysCodeVO> tchCtgrCdList = sysCodeMemService.getSysCodeList("TCH_CTGR_CD", UserBroker.getLocaleKey(request));
		request.setAttribute("tchCtgrCdList", tchCtgrCdList);
	    List<SysCodeVO> tchDivCdList = sysCodeMemService.getSysCodeList("TCH_DIV_CD", UserBroker.getLocaleKey(request));
		request.setAttribute("tchDivCdList", tchDivCdList);
	
		
		
		try {
			vo = tchInfoService.view(vo).getReturnVO();
			request.setAttribute("gubun", "E");
		} catch (Exception e) {
			request.setAttribute("gubun", "A");
		}

		String teacherYn = "N";
		UsrUserAuthGrpVO uagVO = new UsrUserAuthGrpVO();
		uagVO.setUserNo(usrUserInfoVO.getUserNo());
		List<UsrUserAuthGrpVO> authList = usrUserAuthGrpService.list(uagVO).getReturnList();
		for(int i=0; i < authList.size(); i++) {
			UsrUserAuthGrpVO iagVO = authList.get(i);
			if("HOME".equals(iagVO.getMenuType())) {
				if("TEACHER".equals(iagVO.getAuthGrpCd())) teacherYn = "Y";
			}
		}
		request.setAttribute("teacherYn", teacherYn);

		request.setAttribute("usrUserInfoVO", usrUserInfoVO);
		request.setAttribute("vo", vo);
		
		

		return "home/teacher/teacher_info_edit";

	}

	/**
	 * 강사 등록(AJAX)
	 *
	 * @return ProcessResultVO
	 */
	@RequestMapping(value="/add")
	public String add(TchInfoVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		UsrUserInfoVO userInfoVO = new UsrUserInfoVO();

		String orgCd = UserBroker.getUserOrgCd(request);
		userInfoVO.setOrgCd(orgCd);
		ProcessResultVO<TchInfoVO> resultVO = null;
		try {
			resultVO = tchInfoService.add(vo);
			if(resultVO.getResult() > 0) {
				resultVO.setMessage(getMessage(request, "teacher.message.teacherinfo.add.success"));
			} else {
				resultVO.setMessage(getMessage(request, "teacher.message.teacherinfo.add.failed"));
			}
		} catch (Exception e) {
			resultVO = tchInfoService.edit(vo);
			if(resultVO.getResult() > 0) {
				resultVO.setMessage(getMessage(request, "teacher.message.teacherinfo.edit.success"));
			} else {
				resultVO.setMessage(getMessage(request, "teacher.message.teacherinfo.edit.failed"));
			}
		}
		return JsonUtil.responseJson(response, resultVO);
	}
	
	@RequestMapping(value="/loginForm")
	public String loginForm(UsrLoginVO vo, HttpServletRequest request) throws Exception {
		
		String orgCd = UserBroker.getUserOrgCd(request);
		String serverName = request.getServerName();
		
		OrgDomainVO orgDomainVO = new OrgDomainVO();
		orgDomainVO.setOrgCd(orgCd);
		orgDomainVO.setOrgDomain(serverName);
		orgDomainVO = orgDomainService.view(orgDomainVO);
		
		if(orgDomainVO.getDomainTypeCd().equals("HOME")) {
			return "redirect:/home/user/loginForm";
		}
		
		String lecMenuNo = configService.getValue("MENUNO", "TCHLECMAIN");

		
		if(isTeacher(request)) {
			return "redirect:" + new URLBuilder("/lec/main/goMenuPage") 
									.addParameter("mcd", lecMenuNo) // 강의실
									.toString();
		}
		
		return "/home/teacher/teacher_login_form";
	}
	
	private boolean isTeacher(HttpServletRequest request) {
		boolean loginChk = ValidationUtils.isNotEmpty(UserBroker.getUserNo(request));
		String chkUserType = StringUtil.nvl(UserBroker.getUserType(request));
		boolean teacherChk = chkUserType.contains("TEACHER") || chkUserType.contains("TUTOR") || chkUserType.contains("TCHMGR") || chkUserType.contains("ASSTCHMGR");
		
		return loginChk && teacherChk;
	}
	
	@RequestMapping(value="/login")
	public String login(UsrLoginVO vo, HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		String serverName = request.getServerName();
		
		UsrUserInfoVO uuivo = new UsrUserInfoVO();

		uuivo.setOrgCd(orgCd);
		String mainMcd = configService.getValue("MENUNO", "LECMAIN"); // 강의실
		String failMcd = configService.getValue("MENUNO", "TCHLOGIN"); // 강사 로그인
		
		String indexUrl = new URLBuilder("/lec/main/goMenuPage")
								.addParameter("mcd", mainMcd)
								.toString();
		
		String goMcd = StringUtil.nvl(vo.getGoMcd(), mainMcd);
		String goUrl = vo.getGoUrl();

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

		// 암호화 파라매터가 넘어왔을 경우 복호화해서 VO에 설정
		if(StringUtil.isNotNull(vo.getEncryptData())) {
			log.debug("암호화 파라매터 복고화 처리 시작..");
			String decrypt[] = CryptoUtil.descrypt(vo.getEncryptData());
			
			uuivo.setOrgCd(orgCd); //현재 로그인 하려는 시스템의 기관코드

			uuivo.setUserId(decrypt[0]);
			uuivo.setUserPass(decrypt[1]);
			vo.setUserId(decrypt[0]);
			vo.setUserPass(decrypt[1]);

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
			
			String chkUserType = StringUtil.nvl(uuivo.getWwwAuthGrpCd(),"");
			
			OrgDomainVO orgDomainVO = new OrgDomainVO();
			orgDomainVO.setOrgCd(orgCd);
			orgDomainVO.setOrgDomain(serverName);
			orgDomainVO = orgDomainService.view(orgDomainVO);
			orgDomainVO.setDomainTypeCd("HOME");
			orgDomainVO = orgDomainService.viewByServiceTypeCd(orgDomainVO);
			
			if(!chkUserType.contains("TEACHER") && !chkUserType.contains("TUTOR") && !chkUserType.contains("TCHMGR") && !chkUserType.contains("ASSTCHMGR")) {
				String normalUrl = request.getScheme() + "://" + orgDomainVO.getOrgDomain();
				return "redirect:"+ new URLBuilder(normalUrl + "/home/main/goMenuPage")
							.addParameter("mcd", "HM04001000")
							.addParameter("loginMsgCd", "MSG01")
							.toString();
			}
			
			TeacherVO teacherVO = new TeacherVO();
			teacherVO.setUserNo(uuivo.getUserNo());
			teacherVO = createCourseTeacherService.isTeacher(teacherVO).getReturnVO();
			if(!"Y".equals(teacherVO.getTchYn())) {
				setAlertMessage(request, "강사로 등록된 과정이 존재하지 않습니다.");
				return "redirect:" + new URLBuilder("/home/main/goMenuPage").addParameter("mcd", failMcd).toString();
			}
			
			//-- 인트라넷에서 넘어온 사용자의 경우 비밀 번호 암호화 및 mid_chk 상태 해제
			if(!uuivo.getUserPass().equals(uuivo.getEncUserPass())) {
				setAlertMessage(request, getMessage(request, "user.message.login.failed"));
				return "redirect:" + new URLBuilder("/home/main/goMenuPage").addParameter("mcd", failMcd).toString();
			}

			//-- 사용자 정보의 ORG CD 와 시스템의 ORG CD가 다른 경우 로그인 시키지 않음.
			if(!orgCd.equals(uuivo.getOrgCd())){
				//통합로그인으로 가능한 ID이면  "Y"가 나옴. -> 통과, "N"일때는 로그인 시키지 않음.
				if("N".equals(uuivo.getItgrtMbrUseYn())){
					setAlertMessage(request, getMessage(request, "user.message.login.failed"));
					return "redirect:" + new URLBuilder("/home/main/goMenuPage").addParameter("mcd", failMcd).toString();
				}
			}
			
			if(StringUtil.isNull(uuivo.getWwwAuthGrpCd())) {
				setAlertMessage(request, getMessage(request, "user.message.login.failed"));
				return "redirect:" + new URLBuilder("/home/main/goMenuPage").addParameter("mcd", failMcd).toString();
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

			} else {
				String[] args = new String[1];
				args[0] = Integer.toString(faileSec);
				setAlertMessage(request, getMessage(request, "user.message.login.failed.time", args));
				return "redirect:" + new URLBuilder("/home/main/goMenuPage").addParameter("mcd", failMcd).toString();
			}
		} catch (DataAccessException e) {
			uuivo = new UsrUserInfoVO();
			uuivo.setUserId(vo.getUserId());
			String errMsg = getMessage(request, "user.message.login.failed");
			lultlvo.setLoginSuccYn("N");

			try {
				vo = usrLoginService.editFailLogin(vo);
			} catch (Exception ex) {
				setAlertMessage(request, errMsg);
				return "redirect:" + new URLBuilder("/home/main/goMenuPage").addParameter("mcd", failMcd).toString();
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
			return "redirect:" + new URLBuilder("/home/main/goMenuPage").addParameter("mcd", failMcd).toString();
		}

		// 세션 정보를 셋팅해 준다.
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

		OrgMenuVO menuVO = null;
		String redirectUrl = "";
		logUserConnLogService.add(request);

		if(mainMcd.equals(goMcd)) {
			redirectUrl = indexUrl;
		} else {
			try{
				menuVO = sysMenuMemService.decideHomeMenuWithSession(goMcd, request);
				redirectUrl = menuVO.getMenuUrl();
			} catch (DataAccessException e) {
				redirectUrl = indexUrl;
			}
		}
		
		if("N".equals(uuivo.getPhoneVeriYn()) && "N".equals(uuivo.getExceptYn())) {	//예외 아이디 체크가 되어 있을 경우, 본인인증 확인 제외
			request.getSession().setAttribute("verifiedId", "false");
		}
		
		return "redirect:"+redirectUrl;
	}
}
