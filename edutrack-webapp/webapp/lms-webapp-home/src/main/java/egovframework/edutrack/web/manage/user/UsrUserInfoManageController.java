package egovframework.edutrack.web.manage.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.exception.ServiceProcessException;
import egovframework.edutrack.comm.service.AbstractResult;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.service.SysCodeMemService;
import egovframework.edutrack.comm.util.security.CryptoUtil;
import egovframework.edutrack.comm.util.security.KISASeed;
import egovframework.edutrack.comm.util.web.DateTimeUtil;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.course.course.service.CourseService;
import egovframework.edutrack.modules.course.course.service.CourseVO;
import egovframework.edutrack.modules.course.createcourse.service.CreateCourseService;
import egovframework.edutrack.modules.course.createcourse.service.CreateCourseVO;
import egovframework.edutrack.modules.log.educourse.service.LogEduCourseStatusService;
import egovframework.edutrack.modules.log.message.service.LogMsgLogService;
import egovframework.edutrack.modules.log.message.service.LogMsgLogVO;
import egovframework.edutrack.modules.log.message.service.LogMsgTransLogVO;
import egovframework.edutrack.modules.log.privateinfo.service.LogPrivateInfoInqLogVO;
import egovframework.edutrack.modules.log.privateinfo.service.LogPrivateInfoService;
import egovframework.edutrack.modules.log.usercert.service.LogUserCertLogService;
import egovframework.edutrack.modules.log.usercert.service.LogUserCertLogVO;
import egovframework.edutrack.modules.log.usercnsl.service.LogUserCnslLogService;
import egovframework.edutrack.modules.log.usercnsl.service.LogUserCnslLogVO;
import egovframework.edutrack.modules.log.userconn.service.LogUserConnLogService;
import egovframework.edutrack.modules.log.userconn.service.LogUserConnLogVO;
import egovframework.edutrack.modules.org.code.service.OrgCodeService;
import egovframework.edutrack.modules.org.code.service.OrgCodeVO;
import egovframework.edutrack.modules.org.config.service.OrgUserInfoCfgService;
import egovframework.edutrack.modules.org.config.service.OrgUserInfoCfgVO;
import egovframework.edutrack.modules.org.emailtpl.service.OrgEmailTplService;
import egovframework.edutrack.modules.org.info.service.OrgOrgInfoService;
import egovframework.edutrack.modules.org.info.service.OrgOrgInfoVO;
import egovframework.edutrack.modules.system.code.service.SysCodeService;
import egovframework.edutrack.modules.system.code.service.SysCodeVO;
import egovframework.edutrack.modules.system.config.service.SysCfgService;
import egovframework.edutrack.modules.system.config.service.SysCfgVO;
import egovframework.edutrack.modules.system.menu.service.SysAuthGrpVO;
import egovframework.edutrack.modules.system.menu.service.SysMenuService;
import egovframework.edutrack.modules.user.dept.service.UsrDeptInfoService;
import egovframework.edutrack.modules.user.dept.service.UsrDeptInfoVO;
import egovframework.edutrack.modules.user.info.service.UsrLoginService;
import egovframework.edutrack.modules.user.info.service.UsrLoginVO;
import egovframework.edutrack.modules.user.info.service.UsrUserAuthGrpVO;
import egovframework.edutrack.modules.user.info.service.UsrUserInfoService;
import egovframework.edutrack.modules.user.info.service.UsrUserInfoVO;
import egovframework.edutrack.notification.MessageNotificationException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/mng/user/userInfo")
public class UsrUserInfoManageController extends GenericController {

   /** service */
	@Autowired @Qualifier("usrUserInfoService")
	private UsrUserInfoService 		usrUserInfoService;

	@Autowired @Qualifier("usrLoginService")
	private UsrLoginService 		usrLoginService;
	
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

	@Autowired @Qualifier("logPrivateInfoService")
	private LogPrivateInfoService			logPrivateInfoService;
	
	@Autowired @Qualifier("orgEmailTplService")
	private OrgEmailTplService			orgEmailTplService;
	
	@Autowired @Qualifier("logMsgLogService")
	private LogMsgLogService			logMsgLogService;
	
	@Autowired @Qualifier("usrDeptInfoService")
	private UsrDeptInfoService			usrDeptInfoService;
	
	@Autowired @Qualifier("sysCodeService")
	private SysCodeService				sysCodeService;
	
	
	@Autowired @Qualifier("logUserCertLogService")
	private LogUserCertLogService			logUserCertLogService;
	
	@Autowired @Qualifier("logUserCnslLogService")
	private LogUserCnslLogService	logUserCnslLogService;
	
	@Autowired @Qualifier("logUserConnLogService")
	private LogUserConnLogService			logUserConnLogService;
	
	@Autowired @Qualifier("courseService")
	private CourseService			courseService;
	
	@Autowired @Qualifier("createCourseService")
	private CreateCourseService			createCourseService;
	
	@Autowired @Qualifier("logEduCourseStatusService")
	private LogEduCourseStatusService		logEduCourseStatusService;
	
	/**
     * @Method Name : mainUser
     * @Method 설명 : 사용자 관리 ─ 회원관리
										└ 메인 화면으로 이동 한다. 
	 * @param UsrUserInfoVO 
	 * @param commandMap
	 * @param model
	 * @return  "/user/info/main.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/userMain")
	public String main(UsrUserInfoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		String itgrtMbrUseYn = UserBroker.getItgrtMbrUseYn(request);
		vo.setOrgCd(orgCd);
		vo.setItgrtMbrUseYn(itgrtMbrUseYn);
		request.setAttribute("paging", "Y");		
		request.setAttribute("vo", vo);
		
		UsrDeptInfoVO udivo = new UsrDeptInfoVO();
		udivo.setOrgCd(vo.getOrgCd());
		udivo.setSortKey("DEPT_NM_HANGUL_ASC");
		List<UsrDeptInfoVO> deptList = usrDeptInfoService.list(udivo).getReturnList();
		request.setAttribute("deptList", deptList);
		
		return "mng/user/info/user_main";
	}

	/**
     * @Method Name : listUser
     * @Method 설명 : 회원관리
						└ 목록 화면으로 이동 한다. 
	 * @param UsrUserInfoVO 
	 * @param commandMap
	 * @param model
	 * @return  "/user/info/list_user.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/listUser")
	public String listUser(UsrUserInfoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		String mngType = UserBroker.getMngType(request);
		String userNo = UserBroker.getUserNo(request);
		vo.setOrgCd(orgCd);
        
		ProcessResultListVO<UsrUserInfoVO> resultList;
		
		if (mngType.contains("DEPTMNG")) {
			String deptCd = logEduCourseStatusService.getDeptCd(userNo);
			vo.setDeptCd(deptCd);
			resultList = usrUserInfoService.listPageing(vo);
		}else {
			resultList = usrUserInfoService.listPageing(vo);
		}
		
		//사용자 정보관리
		OrgUserInfoCfgVO ouicvo = new OrgUserInfoCfgVO();
		ouicvo.setOrgCd(vo.getOrgCd());
		List<OrgUserInfoCfgVO> cfgList = orgUserInfoCfgService.list(ouicvo).getReturnList();
		request.setAttribute("userInfoCfgList", cfgList);
		
		request.setAttribute("userInfoList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());
		request.setAttribute("vo", vo);
		request.setAttribute("mngType", mngType);
		
		//-- 개인 정보 조회 로그
		String inqCts = "";
		for(UsrUserInfoVO uidto : resultList.getReturnList()) {
			inqCts += uidto.getOrgCd() + " | " +  uidto.getUserNo() + "\n";
		}
		LogPrivateInfoInqLogVO pilVo = new LogPrivateInfoInqLogVO();
		pilVo.setOrgCd(vo.getOrgCd());
		pilVo.setMenuCd(UserBroker.getMenuCode(request));
		pilVo.setUserNo(UserBroker.getUserNo(request));
		pilVo.setUserNm(UserBroker.getUserName(request));
		pilVo.setDivCd("USER_LIST");
		pilVo.setInqCts(inqCts);
		logPrivateInfoService.add(pilVo);
				
		return "mng/user/info/list_user";
	}
	
	/**
     * @Method Name : addFromUser
     * @Method 설명 : 회원관리
						└ 사용자 등록 화면으로 이동 한다. 
	 * @param UsrUserInfoVO 
	 * @param commandMap
	 * @param model
	 * @return  "/user/info/write_user.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/addUserPop")
	public String addUserPop(UsrUserInfoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		String mngType = UserBroker.getMngType(request);
		vo.setOrgCd(orgCd);
		
		List<OrgOrgInfoVO> orgInfoList = orgOrgInfoService.list(new OrgOrgInfoVO()).getReturnList();
		request.setAttribute("orgInfoList", orgInfoList);

		SysAuthGrpVO sagvo = new SysAuthGrpVO();
		sagvo.setMenuType("ADMIN");
		request.setAttribute("admAuthList", sysMenuService.listAuthGrp(sagvo).getReturnList());
		sagvo.setMenuType("HOME");
		request.setAttribute("wwwAuthList", sysMenuService.listAuthGrp(sagvo).getReturnList());
		sagvo.setMenuType("MANAGE");
		request.setAttribute("mngAuthList", sysMenuService.listAuthGrp(sagvo).getReturnList());
		
		request.setAttribute("userDivList", sysCodeMemService.getSysCodeList("USER_DIV_CD"));

		OrgOrgInfoVO ooivo = new OrgOrgInfoVO();
		ooivo.setOrgCd(vo.getOrgCd());
		ooivo = orgOrgInfoService.view(ooivo);
		request.setAttribute("mbrIdType", ooivo.getMbrIdType());

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

		//휴대폰앞자리
		List<SysCodeVO> mobileCdList = sysCodeMemService.getSysCodeList("MOBILE_CODE");
		request.setAttribute("mobileCdList", mobileCdList);
		
		//지역 전화 코드
		List<SysCodeVO> localPhoneCdList = sysCodeService.listCode("LOCAL_PHONE_CODE").getReturnList();
		request.setAttribute("localPhoneCdList", localPhoneCdList);

		//이메일
		List<SysCodeVO> emailCode = sysCodeMemService.getSysCodeList("USER_EMAIL");
		request.setAttribute("emailCode", emailCode);

		//내외국인 구분
		List<OrgCodeVO> codeList = orgCodeService.listCode(vo.getOrgCd(), "USER_DIV_CD").getReturnList();
		request.setAttribute("codeList", codeList);

		//지역
		List<OrgCodeVO> areaCode = orgCodeService.listCode(vo.getOrgCd(), "AREA_CD").getReturnList();
		request.setAttribute("areaCode", areaCode);
		
		//직업
		List<OrgCodeVO> jobCode = orgCodeService.listCode(vo.getOrgCd(), "JOB_CD").getReturnList();
		request.setAttribute("jobCode", jobCode);
		
		//소속
		List<OrgCodeVO> deptCode = orgCodeService.listCode(vo.getOrgCd(), "DEPT_CD").getReturnList();
		request.setAttribute("deptCode", deptCode);

		//사용자 정보관리
		OrgUserInfoCfgVO ouicvo = new OrgUserInfoCfgVO();
		ouicvo.setOrgCd(vo.getOrgCd());
		List<OrgUserInfoCfgVO> userInfoCfgList = orgUserInfoCfgService.list(ouicvo).getReturnList();
		request.setAttribute("userInfoCfgList", userInfoCfgList);
		
		List<SysCodeVO> stdDivCdList = sysCodeService.listCode("STD_DIV_CD").getReturnList();
		request.setAttribute("stdDivCdList", stdDivCdList);
		
		List<SysCodeVO> nonReguDivCdList = sysCodeService.listCode("NON_REGU_DIV_CD").getReturnList();
		request.setAttribute("nonReguDivCdList", nonReguDivCdList);
		
		UsrDeptInfoVO udivo = new UsrDeptInfoVO();
		udivo.setOrgCd(vo.getOrgCd());
		List<UsrDeptInfoVO> deptList = usrDeptInfoService.list(udivo).getReturnList();
		request.setAttribute("deptList", deptList);
		
		vo.setUserDivCd("C");//-- 초기 일반회원 셋팅
		if(ValidationUtils.isNotEmpty(vo.getOrgCd())) vo.setUserDivCd("O");

		request.setAttribute("teacherYn", "N");
		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "A");
		request.setAttribute("fileupload", "Y");
		request.setAttribute("mngType", mngType);
		
		//회원 등록폼
		String retUrl = "mng/user/info/write_user_pop";
		//사이트 관리자 등록폼
		if("MANAGER".equals(vo.getAuthGrpCd()) || "LEARN_MGR".equals(vo.getAuthGrpCd())){
			retUrl = "mng/user/info/write_manage_pop";
		}
		
		return "mng/user/info/write_user_pop";
	}
	
	/**
     * @Method Name : addUser
     * @Method 설명 : 사용자를 등록 한다. 
	 * @param UsrUserInfoVO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/addUser")
	public String addUser(UsrUserInfoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		vo.setPhoneVeriYn("N");
		
		ProcessResultVO<UsrUserInfoVO> result = new ProcessResultVO<UsrUserInfoVO>();
		
		try {
			usrUserInfoService.add(vo,"AI");
			result.setResult(1);
			result.setMessage(getMessage(request, "user.message.userinfo.add.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "user.message.userinfo.add.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}
	
	/**
     * @Method Name : editUserPop
     * @Method 설명 : 사용자 수정 화면으로 이동 한다. 
	 * @param UsrUserInfoVO 
	 * @param commandMap
	 * @param model
	 * @return  "/user/info/write_user.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/editUserPop")
	public String editUserPop(UsrUserInfoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		List<OrgOrgInfoVO> orgInfoList = orgOrgInfoService.list(new OrgOrgInfoVO()).getReturnList();
		request.setAttribute("orgInfoList", orgInfoList);

		UsrUserAuthGrpVO uuagvo = new UsrUserAuthGrpVO();
		uuagvo.setUserNo(vo.getUserNo());

		vo = usrUserInfoService.view(vo);
		vo.setMobileNo(StringUtil.nvl(vo.getMobileNo(), "").replaceAll("-", ""));
		String plainHomephoneNo = StringUtil.nvl(vo.getHomePhoneno()).replaceAll("-", "");
		vo.setHomePhoneno(hyphenPhoneNo(plainHomephoneNo));
		
		List<UsrUserAuthGrpVO> authList = usrUserInfoService.listUserAuthGrp(uuagvo).getReturnList();

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
		
		//지역 전화 코드
		List<SysCodeVO> localPhoneCdList = sysCodeService.listCode("LOCAL_PHONE_CODE").getReturnList();
		request.setAttribute("localPhoneCdList", localPhoneCdList);
		
		//휴대폰 전화 코드
		List<SysCodeVO> mobileCdList = sysCodeService.listCode("MOBILE_CODE").getReturnList();
		request.setAttribute("mobileCdList", mobileCdList);

		//이메일
		List<SysCodeVO> emailCode = sysCodeMemService.getSysCodeList("USER_EMAIL");
		request.setAttribute("emailCode", emailCode);

		//내외국인 구분
		List<OrgCodeVO> codeList = orgCodeService.listCode(vo.getOrgCd(), "USER_DIV_CD").getReturnList();
		request.setAttribute("codeList", codeList);

		//지역
		List<OrgCodeVO> areaCode = orgCodeService.listCode(vo.getOrgCd(), "AREA_CD").getReturnList();
		request.setAttribute("areaCode", areaCode);
		
		//직업
		List<OrgCodeVO> jobCode = orgCodeService.listCode(vo.getOrgCd(), "JOB_CD").getReturnList();
		request.setAttribute("jobCode", jobCode);
		
		//소속
		List<OrgCodeVO> deptCode = orgCodeService.listCode(vo.getOrgCd(), "DEPT_CD").getReturnList();
		request.setAttribute("deptCode", deptCode);

		//사용자 정보관리
		OrgUserInfoCfgVO ouicvo = new OrgUserInfoCfgVO();
		ouicvo.setOrgCd(vo.getOrgCd());
		List<OrgUserInfoCfgVO> userInfoCfgList = orgUserInfoCfgService.list(ouicvo).getReturnList();
		request.setAttribute("userInfoCfgList", userInfoCfgList);
		
		UsrDeptInfoVO udivo = new UsrDeptInfoVO();
		udivo.setOrgCd(vo.getOrgCd());
		List<UsrDeptInfoVO> deptList = usrDeptInfoService.list(udivo).getReturnList();
		request.setAttribute("deptList", deptList);

		String wwwAuthGrpCd = "";
		String adminAuthGrpCd = "";
		String manageAuthGrpCd = "";
		String userAuthGrpCd = "";
		String teacherYn = "N";

		String retUrl = "mng/user/info/write_user_pop";
		for(int i=0; i < authList.size(); i++) {
			UsrUserAuthGrpVO suuagvo = authList.get(i);
			if("HOME".equals(suuagvo.getMenuType())) {
				wwwAuthGrpCd += "/"+suuagvo.getAuthGrpCd();
				if("TEACHER".equals(suuagvo.getAuthGrpCd())) teacherYn = "Y";
			}
			if("ADMIN".equals(suuagvo.getMenuType())) adminAuthGrpCd += "/"+suuagvo.getAuthGrpCd();
			if("MANAGE".equals(suuagvo.getMenuType())) manageAuthGrpCd += "/"+suuagvo.getAuthGrpCd();
			userAuthGrpCd += "/"+suuagvo.getAuthGrpCd();
			
			//메뉴권한에 따라 화면을 바꿔준다.
			if("MANAGE".equals(suuagvo.getMenuType())){
				retUrl = "mng/user/info/write_manage_pop";
			}
		}

		vo.setWwwAuthGrpCd(wwwAuthGrpCd);
		vo.setAdminAuthGrpCd(adminAuthGrpCd);
		vo.setMngAuthGrpCd(manageAuthGrpCd);
		request.setAttribute("userAuthGrpCd", userAuthGrpCd);
		
		SysAuthGrpVO sagvo = new SysAuthGrpVO();
		sagvo.setMenuType("ADMIN");
		request.setAttribute("admAuthList", sysMenuService.listAuthGrp(sagvo).getReturnList());
		sagvo.setMenuType("HOME");
		request.setAttribute("wwwAuthList", sysMenuService.listAuthGrp(sagvo).getReturnList());
		sagvo.setMenuType("MANAGE");
		request.setAttribute("mngAuthList", sysMenuService.listAuthGrp(sagvo).getReturnList());

		request.setAttribute("userDivList", sysCodeMemService.getSysCodeList("USER_DIV_CD"));
		
		request.setAttribute("teacherYn", "N");
		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "E");
		request.setAttribute("fileupload", "Y");
		
		List<SysCodeVO> stdDivCdList = sysCodeService.listCode("STD_DIV_CD").getReturnList();
		request.setAttribute("stdDivCdList", stdDivCdList);
		
		List<SysCodeVO> nonReguDivCdList = sysCodeService.listCode("NON_REGU_DIV_CD").getReturnList();
		request.setAttribute("nonReguDivCdList", nonReguDivCdList);
		
		//-- 개인 정보 조회 로그
		LogPrivateInfoInqLogVO pilVo = new LogPrivateInfoInqLogVO();
		pilVo.setOrgCd(vo.getOrgCd());
		pilVo.setMenuCd(UserBroker.getMenuCode(request));
		pilVo.setUserNo(UserBroker.getUserNo(request));
		pilVo.setUserNm(UserBroker.getUserName(request));
		pilVo.setDivCd("USER_EDIT");
		pilVo.setInqCts(vo.getOrgCd() + " | " + vo.getUserNo());
		logPrivateInfoService.add(pilVo);
		
		return retUrl;
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
     * @Method Name : editUser
     * @Method 설명 : 회원관리
						└ 사용자를 수정한다. 
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
		
		ProcessResultVO<UsrUserInfoVO> result = new ProcessResultVO<UsrUserInfoVO>();
		
		try {
			usrUserInfoService.edit(vo,"AU");
			result.setResult(1);
			result.setMessage(getMessage(request, "user.message.userinfo.edit.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "user.message.userinfo.edit.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}
	
	/**
     * @Method Name : stopUser
     * @Method 설명 : 회원관리
						└ 사용자를 사용정지 상태로 변경한다. 
	 * @param UsrUserInfoVO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/stopUser")
	public String stopUser(UsrLoginVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		vo.setUserSts("C");
		
		ProcessResultVO<UsrLoginVO> result = new ProcessResultVO<UsrLoginVO>();
		try {
			usrLoginService.editUserSts(vo,"AC");
			result.setResult(1);
			result.setMessage(getMessage(request, "user.message.userinfo.nouse.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "user.message.userinfo.nouse.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}
	
	/**
     * @Method Name : startUser
     * @Method 설명 : 회원관리
						└ 사용자를 사용 상태로 변경한다. 
	 * @param UsrUserInfoVO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/startUser")
	public String startUser(UsrLoginVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		vo.setUserSts("U");
		
		ProcessResultVO<UsrLoginVO> result = new ProcessResultVO<UsrLoginVO>();
		try {
			usrLoginService.editUserSts(vo,"AS");
			result.setResult(1);
			result.setMessage(getMessage(request, "user.message.userinfo.use.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "user.message.userinfo.use.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}
	
	/**
     * @Method Name : joinOut
     * @Method 설명 : 회원관리
						└ 사용자를 탈퇴 상태로 변경한다.
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
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "user.message.userinfo.leave.failed"));
		}
		return JsonUtil.responseJson(response, result);
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
     * @Method Name : resetPass
     * @Method 설명 : 회원관리
						└ 사용자의 비밀번호를 초기화 한다.
	 * @param UsrUserInfoVO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/resetPass")
	public String resetPass(UsrLoginVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		//-- 사용자 정보를 받아온다.
		UsrUserInfoVO uuivo = new UsrUserInfoVO();
		uuivo.setUserNo(vo.getUserNo());
		uuivo = usrUserInfoService.view(uuivo);

		if("NEW".equals(vo.getNewUserPassConfirm())) {
			vo.setUserPass(usrLoginService.getNewPass());
			vo.setMessageDetail(getMessage(request, "user.message.userinfo.reset.password.success")
					+"\n초기화된 비밀번호를 회원 이메일로 발송합니다.");
		} else if("NEWPASS".equals(vo.getNewUserPassConfirm())) {
			vo.setUserPass(vo.getNewUserPass());
			vo.setMessageDetail(getMessage(request, "user.message.userinfo.add.password.success"));
		} else {
			vo.setUserPass(uuivo.getUserId());
			vo.setMessageDetail(getMessage(request, "user.message.userinfo.reset.password.fromid.success"));
		}

		String newUserPass = vo.getUserPass();

		try {
			usrLoginService.editPass(vo);

			if("NEW".equals(vo.getNewUserPassConfirm())) {
				//-- 기관 정보 받아오기
				String orgCd = UserBroker.getUserOrgCd(request);
				OrgOrgInfoVO orgInfoDTO = new OrgOrgInfoVO();
				orgInfoDTO.setOrgCd(orgCd);
				orgInfoDTO = orgOrgInfoService.view(orgInfoDTO);

				//-- 비밀번호 초기화일 경우 사용자에게 메일 보내기...
				LogMsgTransLogVO trans = new LogMsgTransLogVO();

				trans.setRecvAddr(uuivo.getEmail());
				trans.setRecvNm(uuivo.getUserNm());
				trans.setRecvYn("Y"); //안받으면 못찾으니까 강제적으로 Y

				LogMsgLogVO message = trans.getMessage();
				message.setSendMenuCd(UserBroker.getMenuCode(request));
				message.setMsgDivCd("EMAIL");
				message.setSendAddr(orgInfoDTO.getEmailAddr());
				message.setSendNm(orgInfoDTO.getEmailNm());

				message.addSubTrans(trans);

				//-- 메일 데코레이션
				Map<String, Object> argu = new HashMap<String, Object>();
				argu.put("Name",uuivo.getUserNm());
				argu.put("UserID", uuivo.getUserId());
				argu.put("Password", newUserPass);
				argu.put("SendDate", DateTimeUtil.getCurrentString("yy.MM.dd"));
				orgEmailTplService.decorator(orgCd, "EM002", argu, message);

				try{
					logMsgLogService.addMessageWithSend(message);		//실서버 메일 등록 및 전송
				}catch (MessageNotificationException ex) {}
			}
			
		} catch(Exception e) {
			vo.setMessage("ERROR");
			vo.setMessageDetail(getMessage(request, "user.message.userinfo.reset.password.failed"));
			return JsonUtil.responseJson(response, vo);
		}	
		
		return JsonUtil.responseJson(response, vo);
	}		
	
	/**
     * @Method Name : viewUserPop
     * @Method 설명 : 회원관리
						└ 사용자의 상세화면으로 이동한다. 
	 * @param UsrUserInfoVO 
	 * @param commandMap
	 * @param model
	 * @return  "/user/info/view_user.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/viewUserPop")
	public String viewUserPop(UsrUserInfoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		
		UsrUserAuthGrpVO uuagvo = new UsrUserAuthGrpVO();
		uuagvo.setUserNo(vo.getUserNo());

		vo = usrUserInfoService.view(vo);
		List<UsrUserAuthGrpVO> authList = usrUserInfoService.listUserAuthGrp(uuagvo).getReturnList();

		request.setAttribute("userAuthList", authList);

		SysAuthGrpVO sagvo = new SysAuthGrpVO();
		sagvo.setMenuType("ADMIN");
		request.setAttribute("admAuthList", sysMenuService.listAuthGrp(sagvo).getReturnList());
		sagvo.setMenuType("HOME");
		request.setAttribute("wwwAuthList", sysMenuService.listAuthGrp(sagvo).getReturnList());
		sagvo.setMenuType("MANAGE");
		request.setAttribute("mngAuthList", sysMenuService.listAuthGrp(sagvo).getReturnList());
		
		
		//구분1
		List<OrgCodeVO> codeList = orgCodeService.listCode("USER_DIV_CD",vo.getOrgCd()).getReturnList();
		request.setAttribute("codeList", codeList);

		//지역
		List<OrgCodeVO> areaCode = orgCodeService.listCode("AREA_CD",vo.getOrgCd()).getReturnList();
		request.setAttribute("areaCode", areaCode);

		//사용자 정보관리
		OrgUserInfoCfgVO ouicvo = new OrgUserInfoCfgVO();
		ouicvo.setOrgCd(vo.getOrgCd());
		List<OrgUserInfoCfgVO> userInfoCfgList = orgUserInfoCfgService.list(ouicvo).getReturnList();
		request.setAttribute("userInfoCfgList", userInfoCfgList);

		String wwwAuthGrpCd = "";
		String adminAuthGrpCd = "";
		String manageAuthGrpCd = "";
		String userAuthGrpCd = "";
		String teacherYn = "N";
		String tutorYn = "N";

		for(int i=0; i < authList.size(); i++) {
			UsrUserAuthGrpVO iagdto = authList.get(i);
			if("HOME".equals(iagdto.getMenuType())) wwwAuthGrpCd += "/"+iagdto.getAuthGrpCd();
			if("ADMIN".equals(iagdto.getMenuType())) adminAuthGrpCd += "/"+iagdto.getAuthGrpCd();
			if("MANAGE".equals(iagdto.getMenuType())) manageAuthGrpCd += "/"+iagdto.getAuthGrpCd();
			userAuthGrpCd += "/"+iagdto.getAuthGrpCd();
			if("TEACHER".equals(iagdto.getAuthGrpCd())) teacherYn = "Y";
			if("TUTOR".equals(iagdto.getAuthGrpCd())) tutorYn = "Y";
		}

		vo.setWwwAuthGrpCd(wwwAuthGrpCd);
		vo.setAdminAuthGrpCd(adminAuthGrpCd);
		vo.setMngAuthGrpCd(manageAuthGrpCd);
		request.setAttribute("userAuthGrpCd", userAuthGrpCd);

		request.setAttribute("teacherYn", teacherYn);
		request.setAttribute("tutorYn", tutorYn);
		request.setAttribute("vo", vo);
		
		//-- 개인 정보 조회 로그
		LogPrivateInfoInqLogVO pilVo = new LogPrivateInfoInqLogVO();
		pilVo.setOrgCd(vo.getOrgCd());
		pilVo.setMenuCd(UserBroker.getMenuCode(request));
		pilVo.setUserNo(UserBroker.getUserNo(request));
		pilVo.setUserNm(UserBroker.getUserName(request));
		pilVo.setDivCd("USER_VIEW");
		pilVo.setInqCts(vo.getOrgCd() + " | " + vo.getUserNo());
		logPrivateInfoService.add(pilVo);
		
		String retUrl = "mng/user/info/view_user_pop";
		for(int i=0; i < authList.size(); i++) {
			UsrUserAuthGrpVO suuagvo = authList.get(i);
			if("HOME".equals(suuagvo.getMenuType())) {
				wwwAuthGrpCd += "/"+suuagvo.getAuthGrpCd();
				if("TEACHER".equals(suuagvo.getAuthGrpCd())) teacherYn = "Y";
			}
			if("ADMIN".equals(suuagvo.getMenuType())) adminAuthGrpCd += "/"+suuagvo.getAuthGrpCd();
			if("MANAGE".equals(suuagvo.getMenuType())) manageAuthGrpCd += "/"+suuagvo.getAuthGrpCd();
			userAuthGrpCd += "/"+suuagvo.getAuthGrpCd();
			
			//메뉴권한에 따라 화면을 바꿔준다.
			if("MANAGE".equals(suuagvo.getMenuType())){
				retUrl = "mng/user/info/view_manage_pop";
			}
		}
		
		return retUrl;
	}	
	
	/**
     * @Method Name : crmOpenMain
     * @Method 설명 : 회원관리
	 * @param UsrUserInfoVO 
	 * @param commandMap
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/crmOpenPop")
	public String crmOpenPop(UsrUserInfoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
	//	vo = usrUserInfoService.view(vo);

		request.setAttribute("vo", vo);
		String retUrl = "mng/user/info/user_crm";
		return retUrl;
	}	
	

	/**
     * @Method Name : studentInfoTablePop
     * @Method 설명 : 회원관리
	 * @param UsrUserInfoVO 
	 * @param commandMap
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/studentInfoTablePop")
	public String studentTablePop(UsrUserInfoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		vo = usrUserInfoService.view(vo);

		request.setAttribute("vo", vo);
		String retUrl = "mng/user/info/user_info_table_div";
		return retUrl;
	}	
	
	/**
	 *  접속이력 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/connContentPop")
	public String connContentPop(LogUserConnLogVO luclVo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {

		ProcessResultListVO<LogUserConnLogVO> resultList  = logUserConnLogService.listPageing(luclVo);
						
		request.setAttribute("userConnList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());
		request.setAttribute("paging", "Y");
		
		return "mng/user/info/user_conn_list";
	}
	
	/**
	 *  사용자 관리 > 회원 관리 >  회원 정보 > 인증 이력
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/certContentPop")
	public String certContentPop(LogUserCertLogVO luclVO, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		
		ProcessResultListVO<LogUserCertLogVO> resultList = 	logUserCertLogService.listPageing(luclVO);
		
		request.setAttribute("userCertList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());
		request.setAttribute("paging", "Y");
		
		return "mng/user/info/user_cert_list";
	}
	
	@RequestMapping(value="/cnslListPop")
	public String userListPop(LogUserCnslLogVO vo, HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultListVO<LogUserCnslLogVO> resultList = logUserCnslLogService.listPageing(vo);
		
		request.setAttribute("userCnslList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());
		request.setAttribute("paging", "Y");
		request.setAttribute("vo", vo);
		
		return "mng/user/info/user_crm_cnsl_list_div";
	}
	

	/**
	 *  사용자 관리 > 회원 관리 >  회원 정보 > 수강신청내역
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/courseContentPop")
	public String courseContentPop(CreateCourseVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {

		ProcessResultListVO<CreateCourseVO> resultList = createCourseService.listStdCoursePaging(vo);
		
		request.setAttribute("courseList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());
		request.setAttribute("paging", "Y");
		request.setAttribute("vo", vo);
		
		return "mng/user/info/user_course_list";
	}
	
	/**
	 *  사용자 관리 > 회원 관리 >  회원 정보 > 상담 이력
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/consultContentPop")
	public String consultContentPop(UsrUserInfoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {

		ProcessResultListVO<UsrUserInfoVO> resultList = usrUserInfoService.listConsultPaging(vo);
		
		request.setAttribute("userConsultList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());
		request.setAttribute("paging", "Y");
		request.setAttribute("vo", vo);
		
		return "mng/user/info/user_consult_content";
	}
	
	/**
	 * 사용자 관리 > 회원 관리 > 회원 정보 > 상담 등록
	 *
	 * @return ProcessResultVO
	 */
	
	@RequestMapping(value="/addConsult")
	public String addConsult(UsrUserInfoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String cnstId = UserBroker.getUserId(request);
		String cnstUser = UserBroker.getUserName(request);
		String regNo = UserBroker.getUserNo(request);
		vo.setCnstId(cnstId);
		vo.setCnstUser(cnstUser);
		vo.setRegNo(regNo);
		
		ProcessResultVO<UsrUserInfoVO> result = new ProcessResultVO<UsrUserInfoVO>();
		
		try {
			usrUserInfoService.addConsult(vo);
			result.setResult(1);
			result.setMessage("상담 등록을 완료했습니다.");
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage("상담 등록에 실패했습니다.");
		}
		return JsonUtil.responseJson(response, result);
	}

	
	/**
     * @Method Name : userListPop
     * @Method 설명 : 회원관리
	 * @param UsrUserInfoVO 
	 * @param commandMap
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/userListPop")
	public String userListPop(UsrUserInfoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
	 	String mngType = UserBroker.getMngType(request);
		String userNo = UserBroker.getUserNo(request);
		vo.setOrgCd(orgCd);
        
		ProcessResultListVO<UsrUserInfoVO> resultList;
		
		if (mngType.contains("DEPTMNG")) {
			String deptCd = logEduCourseStatusService.getDeptCd(userNo);
			vo.setDeptCd(deptCd);
			resultList = usrUserInfoService.listPageing(vo);
		}else {
			resultList = usrUserInfoService.listPageing(vo);
		}
		
		
		request.setAttribute("userInfoList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());
		request.setAttribute("paging", "Y");
		request.setAttribute("vo", vo);
		
		return "mng/user/info/user_list_div";
	}	
	
	
	/************************************************************************************************/
	
	
	/**
     * @Method Name : mainManage
     * @Method 설명 : 사용자 관리
						└ 관리자 관리 메인 화면으로 이동한다.
	 * @param UsrUserInfoVO 
	 * @param commandMap
	 * @param model
	 * @return  "/user/info/main_manage.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/manageMain")
	public String manageMain(UsrUserInfoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
				
		request.setAttribute("vo", vo);
		request.setAttribute("paging", "Y");
		return "mng/user/info/manage_main";
	}
	
	/**
     * @Method Name : listManage
     * @Method 설명 : 관리자 관리
						└ 목록 화면으로 이동 한다. 
	 * @param UsrUserInfoVO 
	 * @param commandMap
	 * @param model
	 * @return  "/user/info/list_manage.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/listManage")
	public String listManage(UsrUserInfoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		ProcessResultListVO<UsrUserInfoVO> resultList = usrUserInfoService.listPageing(vo);
		
		//사용자 정보관리
		OrgUserInfoCfgVO ouicvo = new OrgUserInfoCfgVO();
		ouicvo.setOrgCd(vo.getOrgCd());
		List<OrgUserInfoCfgVO> cfgList = orgUserInfoCfgService.list(ouicvo).getReturnList();
		request.setAttribute("userInfoCfgList", cfgList);
		
		request.setAttribute("userInfoList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());
		request.setAttribute("vo", vo);
		
		//-- 개인 정보 조회 로그
		String inqCts = "";
		for(UsrUserInfoVO uidto : resultList.getReturnList()) {
			inqCts += uidto.getOrgCd() + " | " +  uidto.getUserNo() + "\n";
		}
		LogPrivateInfoInqLogVO pilVo = new LogPrivateInfoInqLogVO();
		pilVo.setOrgCd(vo.getOrgCd());
		pilVo.setMenuCd(UserBroker.getMenuCode(request));
		pilVo.setUserNo(UserBroker.getUserNo(request));
		pilVo.setUserNm(UserBroker.getUserName(request));
		pilVo.setDivCd("USER_LIST");
		pilVo.setInqCts(inqCts);
		logPrivateInfoService.add(pilVo);

		return "mng/user/info/list_manage";
	}
	
	/**
     * @Method Name : addFromManage
     * @Method 설명 : 관리자 관리
						└ 관리자 등록 화면으로 이동 한다. 
	 * @param UsrUserInfoVO 
	 * @param commandMap
	 * @param model
	 * @return  "/user/info/write_manage.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/addManagePop")
	public String addManagePop(UsrUserInfoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		List<OrgOrgInfoVO> orgInfoList = orgOrgInfoService.list(new OrgOrgInfoVO()).getReturnList();
		request.setAttribute("orgInfoList", orgInfoList);

		SysAuthGrpVO sagvo = new SysAuthGrpVO();
		sagvo.setMenuType("ADMIN");
		request.setAttribute("admAuthList", sysMenuService.listAuthGrp(sagvo).getReturnList());
		sagvo.setMenuType("HOME");
		request.setAttribute("wwwAuthList", sysMenuService.listAuthGrp(sagvo).getReturnList());
		sagvo.setMenuType("MANAGE");
		request.setAttribute("mngAuthList", sysMenuService.listAuthGrp(sagvo).getReturnList());
		
		request.setAttribute("userDivList", sysCodeMemService.getSysCodeList("USER_DIV_CD"));

		OrgOrgInfoVO ooivo = new OrgOrgInfoVO();
		ooivo.setOrgCd(vo.getOrgCd());
		ooivo = orgOrgInfoService.view(ooivo);
		request.setAttribute("mbrIdType", ooivo.getMbrIdType());

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

		//휴대폰앞자리
		List<SysCodeVO> moblieCode = sysCodeMemService.getSysCodeList("MOBILE_CODE");
		request.setAttribute("moblieCode", moblieCode);

		//이메일
		List<SysCodeVO> emailCode = sysCodeMemService.getSysCodeList("USER_EMAIL");
		request.setAttribute("emailCode", emailCode);

		//내외국인 구분
		List<OrgCodeVO> codeList = orgCodeService.listCode(vo.getOrgCd(), "USER_DIV_CD").getReturnList();
		request.setAttribute("codeList", codeList);

		//지역
		List<OrgCodeVO> areaCode = orgCodeService.listCode(vo.getOrgCd(), "AREA_CD").getReturnList();
		request.setAttribute("areaCode", areaCode);
		
		//직업
		List<OrgCodeVO> jobCode = orgCodeService.listCode(vo.getOrgCd(), "JOB_CD").getReturnList();
		request.setAttribute("jobCode", jobCode);
		
		//소속
		List<OrgCodeVO> deptCode = orgCodeService.listCode(vo.getOrgCd(), "DEPT_CD").getReturnList();
		request.setAttribute("deptCode", deptCode);

		//사용자 정보관리
		OrgUserInfoCfgVO ouicvo = new OrgUserInfoCfgVO();
		ouicvo.setOrgCd(vo.getOrgCd());
		List<OrgUserInfoCfgVO> userInfoCfgList = orgUserInfoCfgService.list(ouicvo).getReturnList();
		request.setAttribute("userInfoCfgList", userInfoCfgList);
		
		vo.setUserDivCd("C");//-- 초기 일반회원 셋팅
		if(ValidationUtils.isNotEmpty(vo.getOrgCd())) vo.setUserDivCd("O");

        
		request.setAttribute("teacherYn", "N");
		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "A");
		

        
		return "mng/user/info/write_manage_pop";
	}
	
	/**
     * @Method Name : addManage
     * @Method 설명 : 관리자 관리
						└ 관리자를 등록한다. 
	 * @param UsrUserInfoVO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/addManage")
	public String addManage(UsrUserInfoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		ProcessResultVO<UsrUserInfoVO> result = new ProcessResultVO<UsrUserInfoVO>();
		
		try {
			usrUserInfoService.add(vo,"AI");
			result.setResult(1);
			result.setMessage(getMessage(request, "user.message.userinfo.add.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "user.message.userinfo.add.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}
	
	/**
     * @Method Name : editFormManage
     * @Method 설명 : 관리자 관리
						└ 관리자 수정 화면으로 이동 한다. 
	 * @param UsrUserInfoVO 
	 * @param commandMap
	 * @param model
	 * @return  "/user/info/write_manage.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/editManagePop")
	public String editManagePop(UsrUserInfoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		List<OrgOrgInfoVO> orgInfoList = orgOrgInfoService.list(new OrgOrgInfoVO()).getReturnList();
		request.setAttribute("orgInfoList", orgInfoList);

		UsrUserAuthGrpVO uuagvo = new UsrUserAuthGrpVO();
		uuagvo.setUserNo(vo.getUserNo());

		vo = usrUserInfoService.view(vo);
		List<UsrUserAuthGrpVO> authList = usrUserInfoService.listUserAuthGrp(uuagvo).getReturnList();

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

		//지역 전화 코드
		List<SysCodeVO> localPhoneCdList = sysCodeService.listCode("LOCAL_PHONE_CODE").getReturnList();
		request.setAttribute("localPhoneCdList", localPhoneCdList);
		
		//휴대폰 전화 코드
		List<SysCodeVO> mobileCdList = sysCodeService.listCode("MOBILE_CODE").getReturnList();
		request.setAttribute("mobileCdList", mobileCdList);

		//이메일
		List<SysCodeVO> emailCode = sysCodeMemService.getSysCodeList("USER_EMAIL");
		request.setAttribute("emailCode", emailCode);

		//내외국인 구분
		List<OrgCodeVO> codeList = orgCodeService.listCode(vo.getOrgCd(), "USER_DIV_CD").getReturnList();
		request.setAttribute("codeList", codeList);

		//지역
		List<OrgCodeVO> areaCode = orgCodeService.listCode(vo.getOrgCd(), "AREA_CD").getReturnList();
		request.setAttribute("areaCode", areaCode);
		
		//직업
		List<OrgCodeVO> jobCode = orgCodeService.listCode(vo.getOrgCd(), "JOB_CD").getReturnList();
		request.setAttribute("jobCode", jobCode);
		
		//소속
		List<OrgCodeVO> deptCode = orgCodeService.listCode(vo.getOrgCd(), "DEPT_CD").getReturnList();
		request.setAttribute("deptCode", deptCode);

		//사용자 정보관리
		OrgUserInfoCfgVO ouicvo = new OrgUserInfoCfgVO();
		ouicvo.setOrgCd(vo.getOrgCd());
		List<OrgUserInfoCfgVO> userInfoCfgList = orgUserInfoCfgService.list(ouicvo).getReturnList();
		request.setAttribute("userInfoCfgList", userInfoCfgList);

		String wwwAuthGrpCd = "";
		String adminAuthGrpCd = "";
		String manageAuthGrpCd = "";
		String userAuthGrpCd = "";
		String teacherYn = "N";

		for(int i=0; i < authList.size(); i++) {
			UsrUserAuthGrpVO suuagvo = authList.get(i);
			if("HOME".equals(suuagvo.getMenuType())) {
				wwwAuthGrpCd += "/"+suuagvo.getAuthGrpCd();
				if("TEACHER".equals(suuagvo.getAuthGrpCd())) teacherYn = "Y";
			}
			if("ADMIN".equals(suuagvo.getMenuType())) adminAuthGrpCd += "/"+suuagvo.getAuthGrpCd();
			if("MANAGE".equals(suuagvo.getMenuType())) manageAuthGrpCd += "/"+suuagvo.getAuthGrpCd();
			userAuthGrpCd += "/"+suuagvo.getAuthGrpCd();
		}

		vo.setWwwAuthGrpCd(wwwAuthGrpCd);
		vo.setAdminAuthGrpCd(adminAuthGrpCd);
		vo.setMngAuthGrpCd(manageAuthGrpCd);
		request.setAttribute("userAuthGrpCd", userAuthGrpCd);
		
		SysAuthGrpVO sagvo = new SysAuthGrpVO();
		sagvo.setMenuType("ADMIN");
		request.setAttribute("admAuthList", sysMenuService.listAuthGrp(sagvo).getReturnList());
		sagvo.setMenuType("HOME");
		request.setAttribute("wwwAuthList", sysMenuService.listAuthGrp(sagvo).getReturnList());
		sagvo.setMenuType("MANAGE");
		request.setAttribute("mngAuthList", sysMenuService.listAuthGrp(sagvo).getReturnList());

		request.setAttribute("userDivList", sysCodeMemService.getSysCodeList("USER_DIV_CD"));

		request.setAttribute("teacherYn", "N");
		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "E");
		

		
		//-- 개인 정보 조회 로그
		LogPrivateInfoInqLogVO pilVo = new LogPrivateInfoInqLogVO();
		pilVo.setOrgCd(vo.getOrgCd());
		pilVo.setMenuCd(UserBroker.getMenuCode(request));
		pilVo.setUserNo(UserBroker.getUserNo(request));
		pilVo.setUserNm(UserBroker.getUserName(request));
		pilVo.setDivCd("USER_EDIT");
		pilVo.setInqCts(vo.getOrgCd() + " | " + vo.getUserNo());
		logPrivateInfoService.add(pilVo);
				
		return "mng/user/info/write_manage_pop";
	}
	
	/**
     * @Method Name : editManage
     * @Method 설명 : 관리자 관리
						└ 관리자를 수정한다. 
	 * @param UsrUserInfoVO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/editManage")
	public String editManage(UsrUserInfoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		ProcessResultVO<UsrUserInfoVO> result = new ProcessResultVO<UsrUserInfoVO>();
		
		try {
			usrUserInfoService.edit(vo,"AU");
			result.setResult(1);
			result.setMessage(getMessage(request, "user.message.userinfo.edit.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "user.message.userinfo.edit.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}
	
	
	/**
     * @Method Name : editFormMe
     * @Method 설명 : 개인정보관리
						└ 개인정보관리 화면으로 이동 한다. 
	 * @param UsrUserInfoVO 
	 * @param commandMap
	 * @param model
	 * @return  "/user/info/write_me.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/editMeMain")
	public String editMeMain(UsrUserInfoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		
		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);
		vo.setOrgCd(orgCd);
		vo.setUserNo(userNo);
		UsrUserAuthGrpVO uuagvo = new UsrUserAuthGrpVO();
		uuagvo.setUserNo(vo.getUserNo());

		vo = usrUserInfoService.view(vo);
		List<UsrUserAuthGrpVO> authList = usrUserInfoService.listUserAuthGrp(uuagvo).getReturnList();

		String wwwAuthGrpCd = "";
		String adminAuthGrpCd = "";
		String manageAuthGrpCd = "";
		String userAuthGrpCd = "";
		String teacherYn = "N";

		for(int i=0; i < authList.size(); i++) {
			UsrUserAuthGrpVO suuagvo = authList.get(i);
			if("HOME".equals(suuagvo.getMenuType())) {
				wwwAuthGrpCd += "/"+suuagvo.getAuthGrpCd();
				if("TEACHER".equals(suuagvo.getAuthGrpCd())) teacherYn = "Y";
			}
			if("ADMIN".equals(suuagvo.getMenuType())) adminAuthGrpCd += "/"+suuagvo.getAuthGrpCd();
			if("MANAGE".equals(suuagvo.getMenuType())) manageAuthGrpCd += "/"+suuagvo.getAuthGrpCd();
			userAuthGrpCd += "/"+suuagvo.getAuthGrpCd();
		}

		vo.setWwwAuthGrpCd(wwwAuthGrpCd);
		vo.setAdminAuthGrpCd(adminAuthGrpCd);
		vo.setMngAuthGrpCd(manageAuthGrpCd);
		request.setAttribute("userAuthGrpCd", userAuthGrpCd);
		
		
		SysAuthGrpVO sagvo = new SysAuthGrpVO();
		sagvo.setMenuType("ADMIN");
		request.setAttribute("admAuthList", sysMenuService.listAuthGrp(sagvo).getReturnList());
		sagvo.setMenuType("HOME");
		request.setAttribute("wwwAuthList", sysMenuService.listAuthGrp(sagvo).getReturnList());
		sagvo.setMenuType("MANAGE");
		request.setAttribute("mngAuthList", sysMenuService.listAuthGrp(sagvo).getReturnList());

		request.setAttribute("userDivList", sysCodeMemService.getSysCodeList("USER_DIV_CD"));

		request.setAttribute("teacherYn", "N");
		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "E");
		

        
		//-- 개인 정보 조회 로그
		LogPrivateInfoInqLogVO pilVo = new LogPrivateInfoInqLogVO();
		pilVo.setOrgCd(vo.getOrgCd());
		pilVo.setMenuCd(UserBroker.getMenuCode(request));
		pilVo.setUserNo(UserBroker.getUserNo(request));
		pilVo.setUserNm(UserBroker.getUserName(request));
		pilVo.setDivCd("USER_VIEW");
		pilVo.setInqCts(vo.getOrgCd() + " | " + vo.getUserNo());
		logPrivateInfoService.add(pilVo);
				
		return "mng/user/info/edit_me_main";
	}
	
	/**
     * @Method Name : editPassForm
     * @Method 설명 : 비밀번호 변경 화면 이동한다.
	 * @param UsrUserInfoVO 
	 * @param commandMap
	 * @param model
	 * @return  "/user/info/write_me.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/editPassPop")
	public String editPassPop(UsrUserInfoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		
		String userNo = UserBroker.getUserNo(request);
		vo.setUserNo(userNo);
		
		vo = usrUserInfoService.view(vo);
		request.setAttribute("vo", vo);
		request.setAttribute("encryptjs","Y");
		return "mng/user/info/edit_pass";
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
				result.setMessage(getMessage(request, "user.message.userinfo.reset.password.success"));
			} catch (Exception e) {
				result.setResult(-1);
				result.setMessage(getMessage(request, "user.message.userinfo.reset.password.failed"));
			}
			return JsonUtil.responseJson(response, result);
		}
		
	}		

	/**
     * @Method Name : mainUser
     * @Method 설명 : 사용자 관리 ─ 회원관리
										└ 메인 화면으로 이동 한다. 
	 * @param UsrUserInfoVO 
	 * @param commandMap
	 * @param model
	 * @return  "/user/info/user_status.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/userStatus")
	public String userStatus(UsrUserInfoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		//내외국인 구분
		List<OrgCodeVO> codeList = orgCodeService.listCode(vo.getOrgCd(), "USER_DIV_CD").getReturnList();
		request.setAttribute("codeList", codeList);
		
		request.setAttribute("vo", vo);
		return "mng/user/info/user_status";
	}
	
	/**
     * @Method Name : listUser
     * @Method 설명 : 회원관리
						└ 목록 화면으로 이동 한다. 
	 * @param UsrUserInfoVO 
	 * @param commandMap
	 * @param model
	 * @return  "/user/info/list_user.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/listUserStatus")
	public String listUserStatus(UsrUserInfoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
        
		
		ProcessResultListVO<UsrUserInfoVO> resultList = usrUserInfoService.listPageing(vo);
		
		//사용자 정보관리
		OrgUserInfoCfgVO ouicvo = new OrgUserInfoCfgVO();
		ouicvo.setOrgCd(vo.getOrgCd());
		List<OrgUserInfoCfgVO> cfgList = orgUserInfoCfgService.list(ouicvo).getReturnList();
		request.setAttribute("userInfoCfgList", cfgList);
		
		request.setAttribute("userInfoList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());
		request.setAttribute("vo", vo);
		
		//-- 개인 정보 조회 로그
		String inqCts = "";
		for(UsrUserInfoVO uidto : resultList.getReturnList()) {
			inqCts += uidto.getOrgCd() + " | " +  uidto.getUserNo() + "\n";
		}
		LogPrivateInfoInqLogVO pilVo = new LogPrivateInfoInqLogVO();
		pilVo.setOrgCd(vo.getOrgCd());
		pilVo.setMenuCd(UserBroker.getMenuCode(request));
		pilVo.setUserNo(UserBroker.getUserNo(request));
		pilVo.setUserNm(UserBroker.getUserName(request));
		pilVo.setDivCd("USER_LIST");
		pilVo.setInqCts(inqCts);
		logPrivateInfoService.add(pilVo);
				
		return "mng/user/info/list_user_status";
	}
	
	/**
	 * 탈퇴 사용자 관리 메인 화면
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/removeUserMain")
	public String removeUserMain(UsrUserInfoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		request.setAttribute("vo", vo);
		request.setAttribute("paging", "Y");
		return "mng/user/info/user_remove_main";
	}
	
	/**
	 * 사용자 등록 샘플 엑셀 다운로드
	 * @throws Exception 
	 * @throws IOException 
	 */
	@RequestMapping(value="/sampleExcelUserAdd")
	public String sampleExcelUserAdd(UsrUserInfoVO vo, HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		
		response.setHeader("Content-Disposition", "attachment;filename=add_user_sample.xlsx;");
		response.setHeader( "Content-Transfer-Coding", "binary" );
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		
		usrUserInfoService.sampleExcelDownloadForUserAdd(response.getOutputStream(), orgCd);
		
		return null;
	}
	
	/**
	 * 사용자 엑셀 등록 팝
	 * @param usrUserInfoVO
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addUserExcelPop")
	public String addUserExcelPop(UsrUserInfoVO vo, HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "A");
		request.setAttribute("fileupload", "A");
		return "mng/user/info/add_user_excel_pop";
	}
	
	/**
	 * 사용자 엑셀 업로드
	 * @param usrUserInfoVO
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/userExcelUpload")
	@ResponseBody
	public ProcessResultListVO<UsrUserInfoVO> userExcelUpload(UsrUserInfoVO vo, HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		ProcessResultListVO<UsrUserInfoVO> result = new ProcessResultListVO<>();
		try {
			usrUserInfoService.excelUploadUserAdd(vo, vo.getFileName(), vo.getFilePath(), orgCd);
			result.setResult(1);
			result.setMessage("회원 등록에 성공했습니다.");
		} catch(ServiceProcessException e) {
			result.setResult(-1);
			result.setMessage(String.format("회원 등록에 실패했습니다. {%s}", e.getMessage()));
		} catch(Exception e) {
			result.setResult(-1);
			result.setMessage("서버 내부 문제로 회원 등록에 실패했습니다.");
		}
		return result;
	}
	
	/**
	 * 사용자 엑셀 등록 팝
	 * @param usrLoginVO
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/editExcept")
	@ResponseBody
	public AbstractResult editExcept(UsrLoginVO vo, HttpServletRequest request) {
		commonVOProcessing(vo, request);
		AbstractResult result = new AbstractResult();
		try {
			usrLoginService.editExceptId(vo);
			result.setResult(1);
		} catch(Exception e) {
			result.setResult(-1);
			result.setMessage("예외 아이디 정보 변경에 실패 했습니다.");
		}
		return result;
	}

	/**
	 * 탈퇴 사용자 관리 목록
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/listRemoveUser")
	public String listRemoveUser(UsrUserInfoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		vo.setUserSts("D"); //-- 삭제된 회원만

		ProcessResultListVO<UsrUserInfoVO> resultList = usrUserInfoService.listPageing(vo);

		//사용자 정보관리
		OrgUserInfoCfgVO ouicvo = new OrgUserInfoCfgVO();
		ouicvo.setOrgCd(vo.getOrgCd());
		List<OrgUserInfoCfgVO> cfgList = orgUserInfoCfgService.list(ouicvo).getReturnList();
		request.setAttribute("userInfoCfgList", cfgList);

		request.setAttribute("userInfoList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());
		request.setAttribute("vo", vo);
	
		return "mng/user/info/user_remove_list_div";
	}
	
	
}
