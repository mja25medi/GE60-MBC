package egovframework.edutrack.comm.util.security;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.file.AccessDeniedException;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.util.ClassUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.exception.SessionBrokenException;
import egovframework.edutrack.comm.service.DefaultVO;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.SysMenuMemService;
import egovframework.edutrack.comm.util.web.DecoratorUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.course.createcourse.service.CreateCourseService;
import egovframework.edutrack.modules.course.createcourse.service.CreateCourseVO;
import egovframework.edutrack.modules.course.createcourse.service.UserCourseVO;
import egovframework.edutrack.modules.course.createcourseteacher.service.CreateCourseTeacherService;
import egovframework.edutrack.modules.course.createcourseteacher.service.TeacherVO;
import egovframework.edutrack.modules.log.classconn.service.LogClassConnService;
import egovframework.edutrack.modules.log.classconn.service.LogClassConnVO;
import egovframework.edutrack.modules.org.connip.service.OrgConnIpService;
import egovframework.edutrack.modules.org.connip.service.OrgConnIpVO;
import egovframework.edutrack.modules.org.menu.service.OrgMenuService;
import egovframework.edutrack.modules.org.menu.service.OrgMenuVO;
import egovframework.edutrack.modules.student.student.service.StudentService;
import egovframework.edutrack.modules.student.student.service.StudentVO;
import egovframework.edutrack.modules.system.menu.service.SysMenuLangVO;
import egovframework.edutrack.modules.system.menu.service.SysMenuService;
import egovframework.edutrack.modules.system.menu.service.SysMenuVO;
import egovframework.edutrack.modules.user.info.service.UsrUserInfoService;
import egovframework.edutrack.modules.user.info.service.UsrUserInfoVO;




public class SecurityUtil {

	private static Log log = LogFactory.getLog(SecurityUtil.class);
	
	private SecurityUtil() {
		//-- 생성자
	}

	public static void authorizationCheckRunner(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			// 크롬 보안정책으로 인한 이니시스 결제 후 세션 사라짐 방지
			response.setHeader("Set-Cookie", "jsessionid=" + request.getSession().getId() + "; Secure; SameSite=None");
			SecurityUtil.authorizationCheck(request, response);
		} catch (Exception ex) {
			throw new Exception(ex);
		}
	}

	public static void authorizationCheck(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String Action = StringUtil.split(request.getRequestURI(), ";jsessionid=")[0]; // /adm/user/userinfo.do 형태로 들어온다.
		String cmd = request.getParameter("cmd"); // Commend를 받아온다.
		String mcd = request.getParameter("mcd"); //-- 추가 2015.11.09 Jamfam 메뉴코드를 가지고 넘어오는 요청은 메뉴코드의 권한으로 체크
		
		if(!Action.startsWith("/app") && !Action.startsWith("/decorator")) { 		
			//.do java 모듈일 경우만 권한 검사함.
			SysMenuService sysMenuService = WebApplicationContextUtils
					.getWebApplicationContext(request.getSession().getServletContext())
					.getBean(SysMenuService.class);
	
			OrgMenuService orgMenuService = WebApplicationContextUtils
					.getWebApplicationContext(request.getSession().getServletContext())
					.getBean(OrgMenuService.class);
	
			OrgConnIpService orgConnIpService = WebApplicationContextUtils
					.getWebApplicationContext(request.getSession().getServletContext())
					.getBean(OrgConnIpService.class);
			
			String userType = "";
			//-- 추가 2015.11.09 Jamfam 메뉴코드를 가지고 넘어오는 요청은 메뉴코드의 권한으로 체크
			String menuCode = (ValidationUtils.isNotEmpty(mcd)) ? mcd : UserBroker.getMenuCode(request);
			//String menuCode = UserBroker.getMenuCode(request);
			String orgCd = UserBroker.getUserOrgCd(request);

			String remoteIp = request.getRemoteAddr();

			// sitemesh decorator 템플릿을 위한 로직
			HttpSession session = request.getSession();
			String authCd = "";
			if(Action.startsWith("/home")) {
				authCd = "home";
			}  else if(Action.startsWith("/lec")) {
				authCd = "lec";
			}   else if(Action.startsWith("/mng")) {
				authCd = "mng";
			}    else if(Action.startsWith("/adm")) {
				authCd = "adm";
			} 
			
			//강제 설정된 url의 탬플릿 코드를 조회 한다.
			DecoratorUtil decoratorUtil = new DecoratorUtil();
			String decoratorTplTypeCd = decoratorUtil.getTplTypeCd(authCd, Action); 
			String tplTypeCd = decoratorTplTypeCd.equals("") ? "sub" : decoratorTplTypeCd;
			session.setAttribute(Constants.SAAS_LAYOUT_AUTH , authCd);
			session.setAttribute(Constants.SAAS_LAYOUT_TPL_TYPE , tplTypeCd);

			
			
			//-- 접속 IP 관련 처리
			int equalCnt = 0;
//			if(Action.endsWith("Manage.do") && !"127.0.0.1".equals(remoteIp)) {
			if(Action.startsWith("/mng")) {

				String[] remoteIpArr = StringUtil.split(remoteIp, ".");
				String remoteIpA = "";
				if(remoteIpArr.length > 3) {
					remoteIpA = remoteIpArr[0]+"."+remoteIpArr[1]+"."+remoteIpArr[2]+".*";
				}
				//-- 사이트 관리자 접속시 로컬 접속이 아니면 IP를 검사하여 권한 체크를 한다.
				OrgConnIpVO ocivo = new OrgConnIpVO();
				ocivo.setOrgCd(orgCd);
				ocivo.setUseYn("Y"); //-- 사용중인 IP만 가져온다.
				List<OrgConnIpVO> orgConnIpList = orgConnIpService.list(ocivo).getReturnList();
				for(OrgConnIpVO socivo : orgConnIpList) {
					if("A".equals(socivo.getDivCd())) {
						if(remoteIpA.equals(socivo.getConnIp())) equalCnt++;
					} else {
						if(remoteIp.equals(socivo.getConnIp())) equalCnt++;
					}
				}
				if(orgConnIpList.size() > 0) {
					if(equalCnt <= 0) {
						throw new AccessDeniedException("common.message.security.badrequest");
					}
				}
			}
			if(Action.startsWith("/lec")) {
				if(!isMainDirectWithoutMcd(Action)) {
					goLecture(request);
				}
			}

			
			
			// 인증 패스 페이지가 아니라면..(로그인 없이 사용하거나. 모두 사용 가능한 페이지인 경우는 패스 시킨다.)
			if(!isAnonmousAuthorize(Action) && !isMainDirectWithoutMcd(Action)) {
				if(Action.startsWith("/adm")) {	//-- 관리자 메뉴인 경우
					userType = UserBroker.getAdmType(request);
				} else if(Action.startsWith("/mng")) {
					userType = UserBroker.getMngType(request);
				} else if(Action.startsWith("/lec")) {	//-- 강의실 메뉴인 경우
					userType = UserBroker.getClassUserType(request);
				} else { //-- 홈페이지 메뉴인 경우
					userType = UserBroker.getUserType(request);
				}
				//-- 로그인 하지 않은 경우 게스트로 취급.
				if("".equals(userType))  userType = "GUEST";
	
				String viewAuth = "";
				String creAuth = "";
	
				if(Action.startsWith("/home")) {
					//-- 홈페이지인 경우 기관의 권한을 체크한다.
					OrgMenuVO orgMenuVO = new OrgMenuVO();
					orgMenuVO.setOrgCd(orgCd);
					orgMenuVO.setMenuCd(menuCode);
					orgMenuVO.setAuthGrpCd(userType);
					try {
						orgMenuVO = orgMenuService.viewAuthorizeByMenu(orgMenuVO);
					} catch (EmptyResultDataAccessException ex) {
						throw new AccessDeniedException("common.message.security.badrequest");
					}
	
					//강의실에서 메인페이지로 이동시 TB_SYS_AUTH_GRP_MENU 테이블 정보가 필요해서 추가
					if(orgMenuVO==null) {
						orgMenuVO = new OrgMenuVO();
						orgMenuVO.setMenuCd(menuCode);
						orgMenuVO = orgMenuService.viewAuthorizeByMenu2(orgMenuVO);
					}
					
					viewAuth = orgMenuVO.getViewAuth();
					creAuth = orgMenuVO.getCreAuth();
					
					/*
					 * if(isMainDirectWithMcd(Action) && ValidationUtils.isNotEmpty(mcd)){
					 * SysMenuMemService sysMenuMemService = WebApplicationContextUtils
					 * .getWebApplicationContext(request.getSession().getServletContext())
					 * .getBean(SysMenuMemService.class);
					 * 
					 * sysMenuMemService.decideHomeMenuWithSession(mcd, request); }
					 */					
					 // 페이지 직접 접속의 경우 메뉴 세션 갱신을 위해 모든 java 페이지에서 처리하도록 수정(2023.11.01)
					  if(ValidationUtils.isNotEmpty(mcd)){
						  SysMenuMemService sysMenuMemService = WebApplicationContextUtils
						  .getWebApplicationContext(request.getSession().getServletContext())
						  .getBean(SysMenuMemService.class);
						  
						  sysMenuMemService.decideHomeMenuWithSession(mcd, request); 
					  }
					 					
				} else {
					//-- 권한 설정 검색
					SysMenuVO sysMenuVO = new SysMenuVO();
					sysMenuVO.setMenuCd(menuCode);
					sysMenuVO.setAuthGrpCd(userType);
	
					if(StringUtil.isNull(menuCode) && !Action.startsWith("/adm") && !Action.startsWith("/mng") && !Action.startsWith("/lec")) {
						// 세션에 menuCode 가 없는 경우 홈페이지만 view 권한을 부여한다.
						// Admin, Manage, Lecture는 권한이 있어야 접근을 할 수 있도록 구현
						sysMenuVO.setViewAuth("Y");
					} else {
						//menuDTO = menuService.viewAuthMenu(menuDTO).getReturnDto();
						sysMenuVO = sysMenuService.viewMenuByAuth(sysMenuVO);
						if(ValidationUtils.isEmpty(sysMenuVO)) {
							
							//관리자에서 미리보기로 인해 현재 메뉴코들를 잃어벼렸을때 저장해둔 임시 메뉴코드가 있으면 다시 넣어준다.
							String tempMenuCode = (String)request.getSession().getAttribute(Constants.TEMP_CUR_MENU_CODE);
							String tempMenuName = (String)request.getSession().getAttribute(Constants.TEMP_CUR_MENU_NAME);
							String tempLocation = (String)request.getSession().getAttribute(Constants.TEMP_MENU_LOCATION);
							if(tempMenuCode != null){
								sysMenuVO = new SysMenuVO();
								sysMenuVO.setMenuCd(tempMenuCode);
								sysMenuVO.setAuthGrpCd(userType);
								
								request.getSession().setAttribute(Constants.CUR_MENU_CODE, tempMenuCode);
								request.getSession().setAttribute(Constants.CUR_MENU_NAME, tempMenuName);
								request.getSession().setAttribute(Constants.MENU_LOCATION, tempLocation);
								
								request.getSession().removeAttribute(Constants.TEMP_CUR_MENU_CODE);
								request.getSession().removeAttribute(Constants.TEMP_CUR_MENU_NAME);
								request.getSession().removeAttribute(Constants.TEMP_MENU_LOCATION);
							}
							sysMenuVO = sysMenuService.viewMenuByAuth(sysMenuVO);
							
							if(ValidationUtils.isEmpty(sysMenuVO)) {
								throw new AccessDeniedException("common.message.security.badrequest");
							}
						}else if(ValidationUtils.isNotEmpty(mcd)){
							String menuType="";
							String location = "";
							if(Action.startsWith("/mng")) {
								menuType = "MANAGE";
								location = getMenuLocation(UserBroker.getMngType(request), UserBroker.getLocaleKey(request), sysMenuVO.getMenuPath(), request, menuType);
							}else if(Action.startsWith("/lec")) {
								menuType = "LECT";
								location = getMenuLocation(UserBroker.getClassUserType(request), UserBroker.getLocaleKey(request), sysMenuVO.getMenuPath(), request, menuType);
							}

							// 세션 정보를 셋팅해 준다.
							request.getSession().setAttribute(Constants.MENU_LOCATION, location);
							// 세션 정보를 셋팅해 준다.
							request.getSession().setAttribute(Constants.CUR_MENU_TITLE, sysMenuVO.getMenuTitle());
							request.getSession().setAttribute(Constants.CUR_MENU_NAME, sysMenuVO.getMenuNm());
							request.getSession().setAttribute(Constants.CUR_MENU_CODE, sysMenuVO.getMenuCd());
						}
					}
	
					viewAuth = sysMenuVO.getViewAuth();
					creAuth = sysMenuVO.getCreAuth();
	
					// 사용,쓰기권한
					request.getSession().setAttribute(Constants.MENU_VIEW_AUTH, viewAuth);
					request.getSession().setAttribute(Constants.MENU_CRE_AUTH, creAuth);

				}
	
				//-- 로그인이 안되어 잇는 경우
				if( isCreateAuthorize(Action) && !"Y".equals(creAuth)) {
					if("".equals(UserBroker.getUserNo(request)))
						throw new SessionBrokenException("common.message.security.sessionend");
					else
						throw new AccessDeniedException("common.message.security.noauth");
	
				} else if( isViewAuthorize(Action) && !"Y".equals(viewAuth)){
					if("".equals(UserBroker.getUserNo(request)))
						throw new SessionBrokenException("common.message.security.sessionend");
					else
						throw new AccessDeniedException("common.message.security.noauth");
				} else {
					// 권한 모델이 없는 경우...
				}
			}
		}
	}

	private static String getMenuLocation(String authGrpCd, String locale, String menuPath, HttpServletRequest request, String menuType) throws Exception {
		String[] menuCds = StringUtil.split(menuPath,"|");
		String[] authGrpCds = StringUtil.split(authGrpCd,"|");
		String menuPathNm = "";
		SysMenuService sysMenuService = WebApplicationContextUtils
				.getWebApplicationContext(request.getSession().getServletContext())
				.getBean(SysMenuService.class);
		for(int i=1; i < menuCds.length; i++) {
			SysMenuVO smvo = null;
			for(int j=0; j < authGrpCds.length; j++) {
				smvo = sysMenuService.getMenuByCache(menuType, authGrpCd, menuCds[i]);
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
	 * ActionForm의 맴버중 GenericDTO를 상속받은 필드를 찾아서 regNo와 modNo등의 인증정보를 대입시킨다.
	 * @param form
	 * @param request
	 */
	public static void authorizationInfoInsert(HttpServletRequest request) {

		Field[] fields = request.getClass().getDeclaredFields();

		for (Field field : fields) {
			// DefaultVO를 상속받은 필드라면..
			if(instanceOfClass(field.getType(), DefaultVO.class)) {

				String fieldName = field.getName();
				String getterMethod = "get" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);

				// 맴버에서 DefaultVO롤 캐스팅 추출을 해서 인증 정보를 삽입한다.
				try {
					Method getMethod = request.getClass().getMethod(getterMethod, (Class[]) null);
					DefaultVO vo = (DefaultVO)getMethod.invoke(request, (Object[]) null);

					if(ValidationUtils.isNotNull(vo)) {
						vo.setRegNo(UserBroker.getUserNo(request));
						vo.setModNo(UserBroker.getUserNo(request));
						//dto.setSystemLocale(UserBroker.getLocaleKey(request)); // 시스템 언어 셋팅.
					}
				} catch (Exception e) {
					e.printStackTrace();
					log.error("Could not invoke method '" + getterMethod + "' on " + ClassUtils.getShortName(request.getClass()));
				}
			}
		}
	}

	/**
	 * 권한 체크 없이 사용할 수 있는 페이지 인지 판단 함. 
	 * @param action
	 * @return boolean
	 */
	private static boolean isAnonmousAuthorize(String Action) {
		boolean result = false;
		result =  Action.matches(".*/*anonymous.*")
			|| Action.matches(".*/*index.*")
			|| Action.matches(".*/*gpin.*")	// G-PIN
			|| Action.matches(".*/*oivs.*")	// 실명인증
			|| Action.matches(".*/*login.*")
			|| Action.matches(".*/*logout.*")
			|| Action.matches(".*/*join.*")
			|| Action.matches(".*/*changeLang.*")
			|| Action.matches(".*/*goMenuPage.*")
			|| Action.matches(".*/*goLecture.*")
			|| Action.matches(".*/*readCmiData.*")
			|| Action.matches(".*/*addCmiData.*")			
			|| Action.matches(".*/*apiCallback.*")
			|| Action.matches(".*/*ssoResult.*")
			|| Action.matches(".*/*ssoLogin.*")
			|| Action.matches(".*/*adminLogin.*")
			|| Action.matches(".*/*jsonKnowList.*")
			|| Action.matches(".*/*HrdApi.*")
			|| Action.matches(".*/*searchId.*")
			|| Action.matches(".*/*searchPw.*")
			|| Action.matches(".*/*findId.*")
			|| Action.matches(".*/*findPass.*")
			|| Action.matches(".*/*securityPwd.*")
			|| Action.matches(".*/*listCourseEnroll.*")
			|| Action.matches(".*/home/brd/qna/add")
			|| Action.matches(".*/home/user/editUserAvatar");
			;

		return result;
	}
	
	private static boolean isMainDirectWithoutMcd(String Action) {
		boolean result = false;
		result = Action.matches(".*/listRecvPop2")
				|| Action.matches(".*/listMsg")
				|| Action.matches(".*/viewMsg")
				|| Action.matches(".*/initVerify.*")
				|| Action.matches(".*/addRedisCrs.*")
				|| Action.matches(".*/niceCheck.*");
		
		return result;
	}
	
	private static boolean isMainDirectWithMcd(String Action) {
		boolean result = false;
		result = Action.matches(".*/readCourseMain")
				|| Action.matches(".*/viewAtclMain")
				|| Action.matches(".*/addFormAtclMain");
		return result;
	}
	
	/**
	 * 권한 체크 없이 사용할 수 있는 페이지 인지 판단 함. 
	 * @param action
	 * @return boolean
	 */
	private static void goLecture(HttpServletRequest request) {
		if(ValidationUtils.isNotEmpty(UserBroker.getUserNo(request))) {
			CreateCourseService createCourseService = WebApplicationContextUtils
					.getWebApplicationContext(request.getSession().getServletContext())
					.getBean(CreateCourseService.class);
			
			StudentService studentService = WebApplicationContextUtils
					.getWebApplicationContext(request.getSession().getServletContext())
					.getBean(StudentService.class);
			
			CreateCourseTeacherService teacherService = WebApplicationContextUtils
					.getWebApplicationContext(request.getSession().getServletContext())
					.getBean(CreateCourseTeacherService.class);
			
			LogClassConnService logClassConnService = WebApplicationContextUtils
					.getWebApplicationContext(request.getSession().getServletContext())
					.getBean(LogClassConnService.class);
			
			try {
				Map<String, Object> userInfo = new Hashtable<String, Object>();
				userInfo.put("userNo", UserBroker.getUserNo(request));
				
				TeacherVO teacherVO = new TeacherVO();
				teacherVO.setUserNo(UserBroker.getUserNo(request));
				teacherVO = teacherService.isTeacher(teacherVO).getReturnVO();
				
				if("Y".equals(teacherVO.getTchYn())) {
					request.getSession().setAttribute(Constants.LOGIN_CLASSUSERTYPE, "TCH");
					
					if(ValidationUtils.isEmpty(request.getParameter("crsCd")) && ValidationUtils.isEmpty(UserBroker.getCrsCd(request))) {
						ProcessResultListVO<UserCourseVO> myCrsList = createCourseService.myCrsListForTeacher(userInfo); 
						if(myCrsList.getReturnList().size() > 0) {
							request.getSession().setAttribute(Constants.LOGIN_CRSCD, myCrsList.getReturnList().get(0).getCrsCd());
							userInfo.put("crsCd", myCrsList.getReturnList().get(0).getCrsCd());
						}
					}else if(ValidationUtils.isNotEmpty(request.getParameter("crsCd")) && !(request.getParameter("crsCd").equals(UserBroker.getCrsCd(request)))){
						request.getSession().setAttribute(Constants.LOGIN_CRSCD,request.getParameter("crsCd"));
						userInfo.put("crsCd", request.getParameter("crsCd"));
					}else {
						userInfo.put("crsCd", UserBroker.getCrsCd(request));
					}
					
					if(ValidationUtils.isEmpty(request.getParameter("crsCreCd")) && ValidationUtils.isEmpty(UserBroker.getCreCrsCd(request)) || ("CHANGE").equals(request.getParameter("crsCreCd"))) {
						ProcessResultListVO<UserCourseVO> myCreList = createCourseService.myCreListForTeacher(userInfo); 
						if(myCreList.getReturnList().size() > 0) {
							request.getSession().setAttribute(Constants.LOGIN_CRSCRECD, myCreList.getReturnList().get(0).getCrsCreCd());
							request.getSession().setAttribute(Constants.LOGIN_SBJCD, myCreList.getReturnList().get(0).getSbjCd());
						}
					}else if(ValidationUtils.isNotEmpty(request.getParameter("crsCreCd")) && !(request.getParameter("crsCreCd").equals(UserBroker.getCreCrsCd(request)))){
						request.getSession().setAttribute(Constants.LOGIN_CRSCRECD, request.getParameter("crsCreCd"));
						if(ValidationUtils.isNotEmpty(request.getParameter("sbjCd")) && !(request.getParameter("sbjCd").equals(UserBroker.getSbjCd(request)))){
							CreateCourseVO createCourseVO = new CreateCourseVO();
							createCourseVO.setCrsCreCd(request.getParameter("crsCreCd"));
							createCourseVO = createCourseService.viewCreateCourse(createCourseVO).getReturnVO();
							request.getSession().setAttribute(Constants.LOGIN_SBJCD, createCourseVO.getSbjCd());
							
						}
					}
					
				} else {
					request.getSession().setAttribute(Constants.LOGIN_CLASSUSERTYPE, "STU");
					
					if(ValidationUtils.isEmpty(request.getParameter("crsCd")) && ValidationUtils.isEmpty(UserBroker.getCrsCd(request))) {
						ProcessResultListVO<UserCourseVO> myCrsList = createCourseService.myCrsListForStudent(userInfo); 
						if(myCrsList.getReturnList().size() > 0) {
							request.getSession().setAttribute(Constants.LOGIN_CRSCD, myCrsList.getReturnList().get(0).getCrsCd());
							userInfo.put("crsCd", myCrsList.getReturnList().get(0).getCrsCd());
						}
					}else if(ValidationUtils.isNotEmpty(request.getParameter("crsCd")) && !(request.getParameter("crsCd").equals(UserBroker.getCrsCd(request)))){
						request.getSession().setAttribute(Constants.LOGIN_CRSCD,request.getParameter("crsCd"));
						userInfo.put("crsCd", request.getParameter("crsCd"));
					}else {
						userInfo.put("crsCd", UserBroker.getCrsCd(request));
					}
					
					if((ValidationUtils.isEmpty(request.getParameter("crsCreCd")) && ValidationUtils.isEmpty(UserBroker.getCreCrsCd(request))) || ("CHANGE").equals(request.getParameter("crsCreCd"))) {
						ProcessResultListVO<UserCourseVO> myCreList = createCourseService.myCreListForStudent(userInfo); 
						if(myCreList.getReturnList().size() > 0) {
							request.getSession().setAttribute(Constants.LOGIN_CRSCRECD, myCreList.getReturnList().get(0).getCrsCreCd());
							request.getSession().setAttribute(Constants.LOGIN_SBJCD, myCreList.getReturnList().get(0).getSbjCd());
							request.getSession().setAttribute(Constants.LOGIN_STUDENTNO, myCreList.getReturnList().get(0).getStdNo());
						}
					}else if(ValidationUtils.isNotEmpty(request.getParameter("crsCreCd")) && !(request.getParameter("crsCreCd").equals(UserBroker.getCreCrsCd(request)))){
						request.getSession().setAttribute(Constants.LOGIN_CRSCRECD, request.getParameter("crsCreCd"));
						if(ValidationUtils.isNotEmpty(request.getParameter("sbjCd")) && !(request.getParameter("sbjCd").equals(UserBroker.getSbjCd(request)))){
							CreateCourseVO createCourseVO = new CreateCourseVO();
							createCourseVO.setCrsCreCd(request.getParameter("crsCreCd"));
							createCourseVO = createCourseService.viewCreateCourse(createCourseVO).getReturnVO();
							request.getSession().setAttribute(Constants.LOGIN_SBJCD, createCourseVO.getSbjCd());
							
						}
						StudentVO svo = new StudentVO();
						svo.setCrsCreCd(request.getParameter("crsCreCd"));
						svo.setUserNo(UserBroker.getUserNo(request));
						svo = studentService.selectMyStdNo(svo).getReturnVO();
						if(ValidationUtils.isNotEmpty(svo)) {
							request.getSession().setAttribute(Constants.LOGIN_STUDENTNO, svo.getStdNo());
						}
					}
				}
				
				//-- 강의실 접속 로그 등록
				LogClassConnVO cclVO = new LogClassConnVO();
				cclVO.setOrgCd(UserBroker.getUserOrgCd(request));
				cclVO.setCrsCreCd(UserBroker.getCreCrsCd(request));
				cclVO.setUserNo(UserBroker.getUserNo(request));
				logClassConnService.addConnLog(cclVO);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 보기 권한을 체크 해야 하는 페이지 인지 판단함.
	 * 쓰기 권한을 체크해야 하는 페이지는 보기 권한을 체크해야 하는 페이지로 판단.
	 * @param action
	 * @return
	 */
	private static boolean isViewAuthorize(String Action) {
		return !isCreateAuthorize(Action);
	}

	/**
	 * 쓰기 권한을 체크해야 하는 페이지 인지 판단 함.
	 * @param action
	 * @return
	 */
	private static boolean isCreateAuthorize(String Action) {
		
		return Action.matches(".*/*add.*")
			|| Action.matches(".*/*edit.*")
			|| Action.matches(".*/*delete.*") 
			|| Action.matches(".*/*remove.*")  
			|| Action.matches(".*/*sort.*")  
			|| Action.matches(".*/*write.*");
	}

	/**
	 * 클래스의 원형중에 원하는 클래스가 있는지 찾아서 결과를 반환한다.
	 * @param clazz 대상 클래스
	 * @param findClazz 찾고자 하는 원형 클래스
	 * @return
	 */
	private static boolean instanceOfClass(Class<?> clazz, Class<?> findClazz) {
		if(clazz == null) {
			return false;
		} else if(clazz.equals(findClazz)) {
			return true;
		} else {
			return instanceOfClass(clazz.getSuperclass(), findClazz);
		}
	}
}