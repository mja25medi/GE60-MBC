package egovframework.edutrack.web.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.service.SysCodeMemService;
import egovframework.edutrack.comm.util.security.CryptoUtil;
import egovframework.edutrack.comm.util.web.DateTimeUtil;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.course.createcourse.service.CreateCourseService;
import egovframework.edutrack.modules.course.createcourse.service.UserCourseVO;
import egovframework.edutrack.modules.log.privateinfo.service.LogPrivateInfoInqLogVO;
import egovframework.edutrack.modules.org.code.service.OrgCodeService;
import egovframework.edutrack.modules.org.code.service.OrgCodeVO;
import egovframework.edutrack.modules.org.config.service.OrgUserInfoCfgService;
import egovframework.edutrack.modules.org.config.service.OrgUserInfoCfgVO;
import egovframework.edutrack.modules.org.info.service.OrgOrgInfoService;
import egovframework.edutrack.modules.org.info.service.OrgOrgInfoVO;
import egovframework.edutrack.modules.system.code.service.SysCodeVO;
import egovframework.edutrack.modules.system.config.service.SysCfgService;
import egovframework.edutrack.modules.system.config.service.SysCfgVO;
import egovframework.edutrack.modules.system.menu.service.SysAuthGrpVO;
import egovframework.edutrack.modules.system.menu.service.SysMenuService;
import egovframework.edutrack.modules.user.info.service.UsrLoginService;
import egovframework.edutrack.modules.user.info.service.UsrLoginVO;
import egovframework.edutrack.modules.user.info.service.UsrUserAuthGrpVO;
import egovframework.edutrack.modules.user.info.service.UsrUserInfoService;
import egovframework.edutrack.modules.user.info.service.UsrUserInfoVO;

@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/adm/user/info")
public class UsrUserInfoAdminController extends GenericController {
	
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
	
	@Autowired @Qualifier("createCourseService")
	private CreateCourseService 			createCourseService;
	
	/**
     * @Method Name : mainAdmin
     * @Method 설명 : 슈퍼 관리자 관리 메인 화면으로 이동 한다. 
	 * @param UsrUserInfoVO 
	 * @param commandMap
	 * @param model
	 * @return  "/user/info/main_admin.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/adminMain")
	public String adminMain(UsrUserInfoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		request.setAttribute("paging", "Y");

		return "user/info/main_admin";
	}

	/**
     * @Method Name : listAdmin
     * @Method 설명 : 슈퍼 관리자 목록 화면으로 이동 한다. 
	 * @param UsrUserInfoVO 
	 * @param commandMap
	 * @param model
	 * @return  "/user/info/list_admin.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/listAdmin")
	public String listAdmin(UsrUserInfoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		
		vo.setSearchAuthGrp("ADMIN");
		ProcessResultListVO<UsrUserInfoVO> resultList = usrUserInfoService.listPageing(vo);
		request.setAttribute("userList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());
		return "user/info/list_admin";
	}
	
	/**
     * @Method Name : addFromAdmin
     * @Method 설명 : 슈퍼 관리자 등록 화면으로 이동 한다. 
	 * @param UsrUserInfoVO 
	 * @param commandMap
	 * @param model
	 * @return  "/user/info/write_admin.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/addFormAdminPop")
	public String addFormAdminPop(UsrUserInfoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		
		SysAuthGrpVO sagvo = new SysAuthGrpVO();
		sagvo.setMenuType("ADMIN");
		request.setAttribute("admAuthList", sysMenuService.listAuthGrp(sagvo).getReturnList());
		sagvo.setMenuType("HOME");
		request.setAttribute("wwwAuthList", sysMenuService.listAuthGrp(sagvo).getReturnList());
		sagvo.setMenuType("MANAGE");
		request.setAttribute("mngAuthList", sysMenuService.listAuthGrp(sagvo).getReturnList());

		request.setAttribute("userDivList", sysCodeMemService.getSysCodeList("USER_DIV_CD"));

		vo.setUserDivCd("C");//-- 초기 일반회원 셋팅
		if(ValidationUtils.isNotEmpty(vo.getOrgCd())) vo.setUserDivCd("O");

		request.setAttribute("teacherYn", "N");
		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "A");
		return "user/info/write_admin";
	}
	
	/**
     * @Method Name : addAdmin
     * @Method 설명 : 슈퍼 관리자를 등록 한다. 
	 * @param UsrUserInfoVO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/addAdmin")
	public String addAdmin(UsrUserInfoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
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
     * @Method Name : editFromAdmin
     * @Method 설명 : 슈퍼 관리자 수정 화면으로 이동 한다. 
	 * @param UsrUserInfoVO 
	 * @param commandMap
	 * @param model
	 * @return  "/user/info/write_admin.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/editFormAdminPop")
	public String editFormAdminPop(UsrUserInfoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		
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
		return "user/info/write_admin";
	}
	
	/**
     * @Method Name : editAdmin
     * @Method 설명 : 슈퍼 관리자를 수정 한다. 
	 * @param UsrUserInfoVO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/editAdmin")
	public String editAdmin(UsrUserInfoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		ProcessResultVO<UsrUserInfoVO> result = new ProcessResultVO<UsrUserInfoVO>();
		
		try {
			usrUserInfoService.edit(vo,"AI");
			result.setResult(1);
			result.setMessage(getMessage(request, "user.message.userinfo.edit.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "user.message.userinfo.edit.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}
	
	/**
     * @Method Name : mainUser
     * @Method 설명 : 기관 사용자 관리 메인 화면으로 이동 한다. 
	 * @param UsrUserInfoVO 
	 * @param commandMap
	 * @param model
	 * @return  "/user/info/main_user.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/mainUserMain")
	public String mainUserMain(UsrUserInfoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		
		//-- 회원사 목록을 검색
		List<OrgOrgInfoVO> orgList = orgOrgInfoService.list(new OrgOrgInfoVO()).getReturnList();
		request.setAttribute("orgList", orgList);
		
		request.setAttribute("vo", vo);
		request.setAttribute("paging", "Y");
		return "user/info/main_user";
	}

	/**
     * @Method Name : listUser
     * @Method 설명 : 기관 사용자 목록 화면으로 이동 한다. 
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
		
		ProcessResultListVO<UsrUserInfoVO> resultList = usrUserInfoService.listPageing(vo);
		
		//사용자 정보관리
		OrgUserInfoCfgVO ouicvo = new OrgUserInfoCfgVO();
		ouicvo.setOrgCd(vo.getOrgCd());
		List<OrgUserInfoCfgVO> cfgList = orgUserInfoCfgService.list(ouicvo).getReturnList();
		request.setAttribute("userInfoCfgList", cfgList);
		
		request.setAttribute("userInfoList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());
		request.setAttribute("vo", vo);
		return "user/info/list_user";
	}
	
	/**
     * @Method Name : addFromUser
     * @Method 설명 : 기관 사용자 등록 화면으로 이동 한다. 
	 * @param UsrUserInfoVO 
	 * @param commandMap
	 * @param model
	 * @return  "/user/info/write_user.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/addFormUserPop")
	public String addFormUserPop(UsrUserInfoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		
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

		//구분1
		List<OrgCodeVO> codeList = orgCodeService.listCode("USER_DIV_CD",vo.getOrgCd()).getReturnList();
		request.setAttribute("codeList", codeList);

		//지역
		List<OrgCodeVO> areaCode = orgCodeService.listCode("AREA_CD",vo.getOrgCd()).getReturnList();
		request.setAttribute("areaCode", areaCode);

		//관심분야
		List<OrgCodeVO> interestCode = orgCodeService.listCode("INTEREST_FIELD_CD",vo.getOrgCd()).getReturnList();
		request.setAttribute("interestCode", interestCode);

		//사용자 정보관리
		OrgUserInfoCfgVO ouicvo = new OrgUserInfoCfgVO();
		ouicvo.setOrgCd(vo.getOrgCd());
		ouicvo.setUseYn("Y");
		List<OrgUserInfoCfgVO> userInfoCfgList = orgUserInfoCfgService.list(ouicvo).getReturnList();
		request.setAttribute("userInfoCfgList", userInfoCfgList);
		
		vo.setUserDivCd("C");//-- 초기 일반회원 셋팅
		if(ValidationUtils.isNotEmpty(vo.getOrgCd())) vo.setUserDivCd("O");

		request.setAttribute("teacherYn", "N");
		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "A");
		request.setAttribute("fileupload", "Y");
		return "user/info/write_user";
	}
	
	/**
     * @Method Name : addUser
     * @Method 설명 : 기관 사용자를 등록 한다. 
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
     * @Method Name : editFromUser
     * @Method 설명 : 기관 사용자 수정 화면으로 이동 한다. 
	 * @param UsrUserInfoVO 
	 * @param commandMap
	 * @param model
	 * @return  "/user/info/write_admin.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/editFormUserPop")
	public String editFormUserPop(UsrUserInfoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		
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

		//휴대폰앞자리
		List<SysCodeVO> moblieCode = sysCodeMemService.getSysCodeList("MOBILE_CODE");
		request.setAttribute("moblieCode", moblieCode);

		//이메일
		List<SysCodeVO> emailCode = sysCodeMemService.getSysCodeList("USER_EMAIL");
		request.setAttribute("emailCode", emailCode);

		//구분1
		List<OrgCodeVO> codeList = orgCodeService.listCode("USER_DIV_CD",vo.getOrgCd()).getReturnList();
		request.setAttribute("codeList", codeList);

		//지역
		List<OrgCodeVO> areaCode = orgCodeService.listCode("AREA_CD",vo.getOrgCd()).getReturnList();
		request.setAttribute("areaCode", areaCode);

		//관심분야
		List<OrgCodeVO> interestCode = orgCodeService.listCode("INTEREST_FIELD_CD",vo.getOrgCd()).getReturnList();
		request.setAttribute("interestCode", interestCode);

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
		request.setAttribute("fileupload", "Y");
		
		return "user/info/write_user";
	}
	
	/**
     * @Method Name : editUser
     * @Method 설명 : 기관 사용자를 수정 한다. 
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
		
		ProcessResultVO<UsrUserInfoVO> result = new ProcessResultVO<UsrUserInfoVO>();
		
		try {
			usrUserInfoService.edit(vo,"AI");
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
     * @Method 설명 : 기관 사용자의 사용을 정지시킨다. 
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
     * @Method 설명 : 기관 사용자의 사용 가능하도록 한다. 
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
     * @Method 설명 : 기관 사용자를 탈퇴 처리 한다. 
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
     * @Method 설명 : 사용자 아이디를 중복 체크 한다. 
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
     * @Method 설명 : 사용자의 비밀번호를 초기화 한다. 
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
			vo.setMessageDetail(getMessage(request, "user.message.userinfo.reset.password.success"));
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
		} catch(Exception e) {
			vo.setMessage("ERROR");
			vo.setMessageDetail(getMessage(request, "user.message.userinfo.reset.password.failed"));
			return JsonUtil.responseJson(response, vo);
		}	
		
		return JsonUtil.responseJson(response, vo);
	}		
	
	/**
     * @Method Name : viewUser
     * @Method 설명 : 기관 사용자 정보 보기 화면으로 이동 한다. 
	 * @param UsrUserInfoVO 
	 * @param commandMap
	 * @param model
	 * @return  "/user/info/main_admin.jsp"
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

		//관심분야
		List<OrgCodeVO> interestCode = orgCodeService.listCode("INTEREST_FIELD_CD",vo.getOrgCd()).getReturnList();
		request.setAttribute("interestCode", interestCode);

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
		return "user/info/view_user";
	}	
	
	/**
	 * 운영과정 조회(튜터, 강사)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/viewTchIngPop")
	public String viewTchIng(UsrUserInfoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		Map<String, Object> userInfo = new Hashtable<String, Object>();
		userInfo.put("userNo", vo.getUserNo());
		userInfo.put("creYear", "");

		//-- 나의운영중인 과정 목록
		List<UserCourseVO> courseListIng = createCourseService.listCreateCourseForTeacher(userInfo).getReturnList();
		request.setAttribute("courseListIng", courseListIng);
		request.setAttribute("vo", vo);
		
		UsrUserAuthGrpVO uuagvo = new UsrUserAuthGrpVO();
		uuagvo.setUserNo(vo.getUserNo());

		String wwwAuthGrpCd = "";
		String adminAuthGrpCd = "";
		String manageAuthGrpCd = "";
		String userAuthGrpCd = "";
		String teacherYn = "N";
		String tutorYn = "N";
		
		List<UsrUserAuthGrpVO> authList = usrUserInfoService.listUserAuthGrp(uuagvo).getReturnList();
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
	
		return "user/info/user_tch_ing_div";
	}
	
	/**
     * @Method Name : editFormMe
     * @Method 설명 : 개인정보관리
						└ 개인정보수정 화면으로 이동 한다. 
	 * @param UsrUserInfoVO 
	 * @param commandMap
	 * @param model
	 * @return  "user/info/edit_admin_me_main.jsp"
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

		for(int i=0; i < authList.size(); i++) {
			UsrUserAuthGrpVO suuagvo = authList.get(i);
			if("HOME".equals(suuagvo.getMenuType())) {
				wwwAuthGrpCd += "/"+suuagvo.getAuthGrpCd();
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

		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "E");
		
		return "user/info/edit_admin_me_main";
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
		return "user/info/edit_pass";
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
}
