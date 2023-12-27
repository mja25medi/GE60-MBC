package egovframework.edutrack.web.manage.etc;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
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

import egovframework.edutrack.comm.exception.MediopiaDefineException;
import egovframework.edutrack.comm.exception.ServiceProcessException;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.util.web.DateTimeUtil;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.URLBuilder;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.course.contents.service.ContentsService;
import egovframework.edutrack.modules.course.course.service.CourseService;
import egovframework.edutrack.modules.course.course.service.CourseVO;
import egovframework.edutrack.modules.etc.hrdapi.service.HrdApiCreService;
import egovframework.edutrack.modules.etc.hrdapi.service.HrdApiCreVO;
import egovframework.edutrack.modules.etc.hrdapi.service.HrdApiOnlnSbjService;
import egovframework.edutrack.modules.etc.hrdapi.service.HrdApiOnlnSbjVO;
import egovframework.edutrack.modules.etc.hrdapi.service.HrdApiScoreService;
import egovframework.edutrack.modules.etc.hrdapi.service.HrdApiScoreVO;
import egovframework.edutrack.modules.etc.hrdapi.service.HrdApiService;
import egovframework.edutrack.modules.etc.hrdapi.service.HrdApiStdService;
import egovframework.edutrack.modules.etc.hrdapi.service.HrdApiStdVO;
import egovframework.edutrack.modules.etc.hrdapi.service.HrdApiSyncVO;
import egovframework.edutrack.modules.etc.hrdapi.service.HrdApiUserInfoService;
import egovframework.edutrack.modules.etc.hrdapi.service.HrdApiUserInfoVO;
import egovframework.edutrack.modules.etc.hrdapi.service.HrdApiUserLoginService;
import egovframework.edutrack.modules.etc.hrdapi.service.HrdApiUserLoginVO;
import egovframework.edutrack.modules.etc.hrdapi.service.HrdApiVO;
import egovframework.edutrack.modules.etc.hrdapi.service.HrdOtpVO;
import egovframework.edutrack.modules.log.apisync.service.LogApiSyncService;
import egovframework.edutrack.modules.log.apisync.service.LogApiSyncVO;
import egovframework.edutrack.modules.log.logintry.service.LogUserLoginTryLogService;
import egovframework.edutrack.modules.log.usercert.service.LogUserCertLogService;
import egovframework.edutrack.modules.log.usercert.service.LogUserCertLogVO;
import egovframework.edutrack.modules.user.info.service.UsrUserInfoService;
import egovframework.edutrack.modules.user.info.service.UsrUserInfoVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Controller
@RequestMapping(value="/mng/etc/HrdApi")
public class HrdApiManageController extends GenericController {
	
	@Autowired @Qualifier("hrdApiService")
	private HrdApiService	hrdApiService;
	
	
	@Autowired @Qualifier("hrdApiCreService")
	private HrdApiCreService	hrdApiCreService;
	
	@Autowired @Qualifier("hrdApiStdService")
	private HrdApiStdService	hrdApiStdService;
	
	@Autowired @Qualifier("hrdApiUserInfoService")
	private HrdApiUserInfoService	hrdApiUserInfoService;
	
	@Autowired @Qualifier("hrdApiUserLoginService")
	private HrdApiUserLoginService	hrdApiUserLoginService;
	
	@Autowired @Qualifier("hrdApiOnlnSbjService")
	private HrdApiOnlnSbjService	hrdApiOnlnSbjService;

	@Autowired @Qualifier("usrUserInfoService")
	private UsrUserInfoService 		usrUserInfoService;
	
	@Autowired @Qualifier("logUserLoginTryLogService")
	private LogUserLoginTryLogService 		logUserLoginTryLogService;
	
	@Autowired @Qualifier("logApiSyncService")
	private LogApiSyncService 		logApiSyncService;
	
	@Autowired @Qualifier("courseService")
	private CourseService			courseService;
	
	@Autowired @Qualifier("logUserCertLogService")
	private LogUserCertLogService			logUserCertLogService;
	
	@Autowired @Qualifier("contentsService")
	private ContentsService		contentsService;
	
	@Autowired @Qualifier("hrdApiScoreService")
	private HrdApiScoreService	hrdApiScoreService;

	/**
     * @Method Name : test otp
     * @Method 설명 : otp관련 테스트 페이지(ajax로 직접 송출)
	 * @param VO
	 * @param commandMap
	 * @param model
	 * @return  
	 * @throws Exception
	 */
	@RequestMapping(value="/test")
	public String test(HrdOtpVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		request.setAttribute("vo", vo);
		return "mng/etc/hrdapi/test";
	}

	/**
     * @Method Name : test otp ajax
     * @Method 설명 : otp관련 java 전송(ajax에서  java로 전송후 송출)
	 * @param VO
	 * @param commandMap
	 * @param model
	 * @return  
	 * @throws Exception
	 */
	@RequestMapping(value="/callHrdApi")
	public String callHrdApi(HrdApiVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		ProcessResultVO<HrdApiVO> result = new ProcessResultVO<HrdApiVO>();
		try {
			hrdApiService.callHrdApi(vo);
			result.setResult(1);
			result.setMessage(getMessage(request, "etc.message.relatedsite.ctgr.add.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "etc.message.relatedsite.ctgr.add.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}
	
	/**
	 * @Method Name : test otp ajax restTemplate
	 * @Method 설명 : otp관련 java 전송(ajax에서  java로 전송후 송출)
	 * @param VO
	 * @param commandMap
	 * @param model
	 * @return  
	 * @throws Exception
	 */
	@RequestMapping(value="/callRestTemplateHrdApi")
	public String callRestTemplateHrdApi(HrdApiVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		
		ProcessResultVO<HrdApiVO> result = new ProcessResultVO<HrdApiVO>();
		try {
			ProcessResultListVO<HrdApiVO> resultList = hrdApiService.callRestTemplateHrdApi(vo);
			result.setResult(1);
			result.setMessage(getMessage(request, "etc.message.relatedsite.ctgr.add.success"));
			result.setReturnVO(resultList.getReturnList().get(0));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "etc.message.relatedsite.ctgr.add.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}
	
	
	/**
     * @Method Name : viewMotp
     * @Method 설명 : mOTP 팝업 화면
	 * @param HrdOtpVO 
	 * @param commandMap
	 * @param model
	 * @return  "mng/etc/hrdapi/view_motp_pop.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/viewMotp")
	public String viewMotp(HrdOtpVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		String userId = UserBroker.getUserId(request);
		String userNo = UserBroker.getUserNo(request);
		String sbjCd = UserBroker.getSbjCd(request);
		String userNm = "";
		String mobileNo = "";
		String hostIp = "";
		try {
			boolean isLoopBack = true;
			Enumeration<NetworkInterface> en;
			en = NetworkInterface.getNetworkInterfaces();
			while(en.hasMoreElements()) {
				NetworkInterface ni = en.nextElement();
				if(ni.isLoopback())
					continue;
				
				Enumeration<InetAddress> inetAddresses = ni.getInetAddresses();
				while(inetAddresses.hasMoreElements()) {
					InetAddress ia = inetAddresses.nextElement();
					if (ia.getHostAddress() != null && ia.getHostAddress().indexOf(".") != -1) {
						hostIp = ia.getHostAddress();
						System.out.println(hostIp);
						isLoopBack = false;
						break;
					}
				}
				if (!isLoopBack)
					break;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			hostIp = "127.0.0.1";
		}
		vo.setUsrid(userId);
		vo.setExip(hostIp);
		vo.setEvalCd(StringUtil.nvl(vo.getEvalCd(),"99"));
		vo = hrdApiService.getClassTmeEval(vo);
		
		if(!"".equals(StringUtil.nvl(userNo))) {
			UsrUserInfoVO uuivo = new UsrUserInfoVO();
			uuivo.setUserNo(userNo);
			uuivo = usrUserInfoService.view(uuivo);
			if(uuivo != null) {
				userNm = uuivo.getUserNm();
				mobileNo = uuivo.getMobileNo();
			}
		}
		vo.setUserNm(userNm);
		vo.setUserTel(mobileNo);
		if("".equals(StringUtil.nvl(vo.getSbjCd()))) {
			vo.setSbjCd(sbjCd);
		}
		vo.setSessionid(request.getSession().getId());
		request.setAttribute("vo", vo);
		
		return "mng/etc/hrdapi/view_motp_pop";
	}
	
	/**
	 * @Method Name : Api main
	 * @Method 설명 : 관리자 API 리스트 조회
	 * @param HrdApiVO 
	 * @param commandMap
	 * @param model
	 * @return  "mng/main/main_page.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/hrdapiMain")
	public String hrdapiMain(HrdApiVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		String crsCd = StringUtil.nvl(request.getParameter("crsCd"));
		
		//-- 로그인이 안되어 있을 경우 로그인 페이지로
		String userNo = UserBroker.getUserNo(request);
		String mngType = StringUtil.nvl(UserBroker.getMngType(request));
		if("".equals(userNo) || "".equals(mngType)) {
			return "redirect:"+new URLBuilder("/mng/main/loginMain").toString();
		}

		String curYear = DateTimeUtil.getYear();
		String curMon = DateTimeUtil.getMonth();
		String curDate = DateTimeUtil.getDate();

		String nowDate = DateTimeUtil.getDateType(1, DateTimeUtil.getDate(),".");
		request.setAttribute("nowDate", nowDate);
		
		CourseVO courseVO = new CourseVO();
		courseVO.setSortKey("CRS_YEAR_TERM_DESC");
		List<CourseVO> courseList = courseService.list(courseVO).getReturnList();
		request.setAttribute("courseList", courseList);
		
		courseVO.setCrsCd(crsCd);
		request.setAttribute("courseVO", courseVO);
		
		return "mng/etc/hrdapi/hrdapi_main";
	}
	
	/**
     * @Method Name : listUserInfoApi
     * @Method 설명 : 회원정보 api 전송이력  조회
	 * @param HrdApiVO 
	 * @param commandMap
	 * @param model
	 * @return  "/mng/etc/hrdapi/list_user_info_api.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/listUserInfoApi")
	public String listUserInfoApi(HrdApiUserInfoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		int syncTotalCnt = 0;
		
		List<EgovMap> resultList = new ArrayList<EgovMap>();
		HrdApiUserInfoVO hauivo = new HrdApiUserInfoVO();
		hauivo.setSearchFrom(vo.getSearchFrom());
		hauivo.setSearchTo(vo.getSearchTo());
		if(!"".equals(StringUtil.nvl(vo.getSyncStatus()))) {
			hauivo.setSearchKey("syncStatus");
			hauivo.setSearchValue(vo.getSyncStatus());
		}
		if(!"".equals(StringUtil.nvl(vo.getNum()))) {
			hauivo.setNum(vo.getNum());
		}
		if(!"".equals(StringUtil.nvl(vo.getUserAgentPk()))) {
			hauivo.setUserAgentPk(vo.getUserAgentPk());
		}
		resultList = hrdApiUserInfoService.listUserInfo(hauivo);
		if(resultList != null) {
			syncTotalCnt = resultList.size();
		}
		
		request.setAttribute("userInfoApiList", resultList);
		request.setAttribute("syncTotalCnt", syncTotalCnt);
		request.setAttribute("vo", vo);
		
		return "mng/etc/hrdapi/list_user_info_api";
	}
	
	/**
     * @Method Name : listUserLoginApi
     * @Method 설명 : 회원로그인 api 전송이력  조회
	 * @param HrdApiVO 
	 * @param commandMap
	 * @param model
	 * @return  "/mng/etc/hrdapi/list_user_login_api.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/listUserLoginApi")
	public String listUserLoginApi(HrdApiUserLoginVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		int syncTotalCnt = 0;
		
		List<EgovMap> resultList = new ArrayList<EgovMap>();
		HrdApiUserLoginVO haulvo = new HrdApiUserLoginVO();
		haulvo.setSearchFrom(vo.getSearchFrom());
		haulvo.setSearchTo(vo.getSearchTo());
		if(!"".equals(StringUtil.nvl(vo.getSyncStatus()))) {
			haulvo.setSearchKey("syncStatus");
			haulvo.setSearchValue(vo.getSyncStatus());
		}
		if(!"".equals(StringUtil.nvl(vo.getNum()))) {
			haulvo.setNum(vo.getNum());
		}
		if(!"".equals(StringUtil.nvl(vo.getUserAgentPk()))) {
			haulvo.setUserAgentPk(vo.getUserAgentPk());
		}
		
		resultList = hrdApiUserLoginService.listUserLogin(haulvo);
		if(resultList != null) {
			syncTotalCnt = resultList.size();
		}
		
		request.setAttribute("userLoginApiList", resultList);
		request.setAttribute("syncTotalCnt", syncTotalCnt);
		request.setAttribute("vo", vo);
		
		return "mng/etc/hrdapi/list_user_login_api";
	}
	
	/**
	 * @Method Name : callUserInfoHrdApi
	 * @Method 설명 : 회원 정보 api java 전송
	 * @param VO
	 * @param commandMap
	 * @param model
	 * @return  
	 * @throws Exception
	 */
	@RequestMapping(value="/callUserInfoHrdApi")
	public String callUserInfoHrdApi(HrdApiUserInfoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String userNo = UserBroker.getUserNo(request);
		String searchFrom = StringUtil.nvl(vo.getSearchFrom());
		String searchTo = StringUtil.nvl(vo.getSearchTo());
		
		ProcessResultListVO<HrdApiVO> resultList = new ProcessResultListVO<HrdApiVO>();
		try {
			List<EgovMap> egovList = new ArrayList<EgovMap>();
			HrdApiUserInfoVO hauivo = new HrdApiUserInfoVO();
			hauivo.setRegNo(userNo);
			hauivo.setModNo(userNo);
			hauivo.setSearchFrom(searchFrom);
			hauivo.setSearchTo(searchTo);
			if(!"".equals(StringUtil.nvl(vo.getSyncStatus()))) {
				hauivo.setSearchKey("syncStatus");
				hauivo.setSearchValue(vo.getSyncStatus());
			}
			if(!"".equals(StringUtil.nvl(vo.getNum()))) {
				hauivo.setNum(vo.getNum());
			}
			if(!"".equals(StringUtil.nvl(vo.getUserAgentPk()))) {
				hauivo.setUserAgentPk(vo.getUserAgentPk());
			}
			egovList = hrdApiUserInfoService.listUserInfoHrdApiSync(hauivo);
			resultList = hrdApiUserInfoService.callUserInfoHrdApi(egovList,hauivo);
		} catch (Exception e) {
			resultList.setResult(-1);
		}
		return JsonUtil.responseJson(response, resultList);
	}
	
	/**
	 * @Method Name : callUserLoginHrdApi
	 * @Method 설명 : 회원 로그인 정보 api java 전송
	 * @param VO
	 * @param commandMap
	 * @param model
	 * @return  
	 * @throws Exception
	 */
	@RequestMapping(value="/callUserLoginHrdApi")
	public String callUserLoginHrdApi(HrdApiUserLoginVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String crsCd = StringUtil.nvl(request.getParameter("crsCd"));
		String userNo = UserBroker.getUserNo(request);
		String searchFrom = StringUtil.nvl(vo.getSearchFrom());
		String searchTo = StringUtil.nvl(vo.getSearchTo());
		
		ProcessResultListVO<HrdApiVO> resultList = new ProcessResultListVO<HrdApiVO>();
		try {
			List<EgovMap> egovList = new ArrayList<EgovMap>();
			HrdApiUserLoginVO haulvo = new HrdApiUserLoginVO();
			haulvo.setRegNo(userNo);
			haulvo.setModNo(userNo);
			haulvo.setSearchFrom(searchFrom);
			haulvo.setSearchTo(searchTo);
			if(!"".equals(StringUtil.nvl(vo.getSyncStatus()))) {
				haulvo.setSearchKey("syncStatus");
				haulvo.setSearchValue(vo.getSyncStatus());
			}
			if(!"".equals(StringUtil.nvl(vo.getNum()))) {
				haulvo.setNum(vo.getNum());
			}
			if(!"".equals(StringUtil.nvl(vo.getUserAgentPk()))) {
				haulvo.setUserAgentPk(vo.getUserAgentPk());
			}
			
			egovList = hrdApiUserLoginService.listUserLoginHrdApiSync(haulvo);
			resultList = hrdApiUserLoginService.callUserLoginHrdApi(egovList,haulvo);
		} catch (Exception e) {
			resultList.setResult(-1);
		}
		return JsonUtil.responseJson(response, resultList);
	}
	
	
	/**
     * @Method Name : viewApiLogPop
     * @Method 설명 : api  전송 이력 조회 리스트
	 * @param HrdOtpVO 
	 * @param commandMap
	 * @param model
	 * @return  "mng/etc/hrdapi/view_log_api_pop.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/viewApiLogPop")
	public String viewApiLogPop(LogApiSyncVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		String crsCd = StringUtil.nvl(request.getParameter("crsCd"));
		String searchFrom = StringUtil.nvl(vo.getSearchFrom());
		String searchTo = StringUtil.nvl(vo.getSearchTo());
		request.setAttribute("crsCd", crsCd);
		request.setAttribute("vo", vo);
		
		String nowDate = DateTimeUtil.getDateType(1, DateTimeUtil.getDate(),".");
		request.setAttribute("nowDate", nowDate);
		
		if("".equals(searchFrom)) {
			searchFrom = nowDate;
		}
		if("".equals(searchTo)) {
			searchTo = nowDate;
		}
		request.setAttribute("searchFrom", searchFrom);
		request.setAttribute("searchTo", searchTo);
		
		return "mng/etc/hrdapi/view_api_log_pop";
	}
	
	/**
     * @Method Name : listApiLog
     * @Method 설명 : api  전송 이력 조회 리스트
	 * @param HrdOtpVO 
	 * @param commandMap
	 * @param model
	 * @return  "mng/etc/hrdapi/list_log_api.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/listApiLog")
	public String listApiLog(LogApiSyncVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		String crsCd = StringUtil.nvl(request.getParameter("crsCd"));
		ProcessResultListVO<EgovMap> resultListVO = new ProcessResultListVO<>();
		List<EgovMap> egovList = new ArrayList<EgovMap>();
		
		if(!"".equals(crsCd)) {
			vo.setCrsCd(crsCd);
		}
		
		resultListVO = logApiSyncService.listApiSync(vo);
		if(resultListVO.getResult() > 0) {
			egovList = resultListVO.getReturnList();
		}
		request.setAttribute("apiLogList", egovList);
		request.setAttribute("vo", vo);
		
		return "mng/etc/hrdapi/list_api_log";
	}
	
	/**
     * @Method Name : addMotpLog
     * @Method 설명 : motp 인증 시 로그 저장
	 * @param HrdOtpVO 
	 * @param commandMap
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/addMotpLog")
	public String addMotpLog(HrdOtpVO vo, Map commandMap, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		LogUserCertLogVO luclvo = new LogUserCertLogVO();
		luclvo.setStdNo(UserBroker.getStudentNo(request));
		String connIp = request.getRemoteAddr();
		luclvo.setConnIp(connIp); 
		luclvo.setCertEvalCd(vo.getEvalCd());
		luclvo.setCertMthd(vo.getEvalType());
		luclvo.setCrsCreCd(UserBroker.getCreCrsCd(request));
		luclvo.setCrsCd(UserBroker.getCrsCd(request));
		
		ProcessResultVO<LogUserCertLogVO> result = new ProcessResultVO<LogUserCertLogVO>();
		
		try {
				logUserCertLogService.add(luclvo);
		} catch (Exception e) {
		}
		return JsonUtil.responseJson(response, result);
	}
	
	
	/**
     * @Method Name : viewEtcApiSyncPop
     * @Method 설명 : api  전송 결과 조회 리스트
	 * @param HrdOtpVO 
	 * @param commandMap
	 * @param model
	 * @return  "mng/etc/hrdapi/view_etc_api_sync_pop.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/viewEtcApiSyncPop")
	public String viewEtcApiSyncPop(HrdApiSyncVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		String crsCd = StringUtil.nvl(request.getParameter("crsCd"));
		request.setAttribute("crsCd", crsCd);
		request.setAttribute("vo", vo);
		
		return "mng/etc/hrdapi/view_etc_api_sync_pop";
	}
	
	/**
     * @Method Name : listEtcApiSync
     * @Method 설명 : api  전송 결과 조회 리스트
	 * @param HrdOtpVO 
	 * @param commandMap
	 * @param model
	 * @return  "mng/etc/hrdapi/list_etc_api_sync.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/listEtcApiSync")
	public String listEtcApiSync(HrdApiSyncVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		String crsCd = StringUtil.nvl(request.getParameter("crsCd"));
		int totalCnt = 0;
		
		ProcessResultListVO<EgovMap> resultListVO = new ProcessResultListVO<>();
		ProcessResultVO<CourseVO> resultVO = new ProcessResultVO<>();
		List<EgovMap> egovList = new ArrayList<EgovMap>();
		CourseVO courseVO = new CourseVO();
		courseVO.setSortKey("CRS_YEAR_TERM_ASC");
		courseVO.setCrsCd(crsCd);
		resultVO = courseService.view(courseVO);
		if(resultVO.getResult() > 0) {
			courseVO = resultVO.getReturnVO();
		}
		vo.setCrsYear(courseVO.getCrsYear());
		vo.setCrsTerm(courseVO.getCrsTerm());
		resultListVO = hrdApiService.listPageingUserInfo(vo);
		if(resultListVO.getResult() > 0) {
			egovList = resultListVO.getReturnList();
			totalCnt = resultListVO.getPageInfo().getTotalRecordCount();
		}
		request.setAttribute("totalCnt", totalCnt);
		request.setAttribute("etcApiSyncList", egovList);
		request.setAttribute("pageInfo", resultListVO.getPageInfo());
		request.setAttribute("vo", vo);
		
		return "mng/etc/hrdapi/list_etc_api_sync";
	}
	
	/**
     * @Method Name : viewUserInfoApiSyncPop
     * @Method 설명 : api  전송 결과 조회 리스트
	 * @param HrdOtpVO 
	 * @param commandMap
	 * @param model
	 * @return  "mng/etc/hrdapi/view_user_info_api_sync_pop.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/viewUserInfoApiSyncPop")
	public String viewUserInfoApiSyncPop(HrdApiSyncVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		String searchFrom = StringUtil.nvl(vo.getSearchFrom());
		String searchTo = StringUtil.nvl(vo.getSearchTo());
		String nowDate = DateTimeUtil.getDateType(1, DateTimeUtil.getDate(),".");
		if("".equals(searchFrom)) {
			searchFrom = nowDate;
		}else {
			searchFrom = DateTimeUtil.getDateType(1, searchFrom,".");
		}
		if("".equals(searchTo)) {
			searchTo = nowDate;
		}else {
			searchTo = DateTimeUtil.getDateType(1, searchTo,".");
		}
		request.setAttribute("searchFrom", searchFrom);
		request.setAttribute("searchTo", searchTo);
		request.setAttribute("paging", "Y");
		request.setAttribute("vo", vo);
		
		return "mng/etc/hrdapi/view_user_info_api_sync_pop";
	}
	
	/**
     * @Method Name : listUserInfoApiSync
     * @Method 설명 : api  전송 결과 조회 리스트
	 * @param HrdOtpVO 
	 * @param commandMap
	 * @param model
	 * @return  "mng/etc/hrdapi/list_user_info_api_sync.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/listUserInfoApiSync")
	public String listUserInfoApiSync(HrdApiUserInfoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		String searchFrom = StringUtil.nvl(vo.getSearchFrom());
		String searchTo = StringUtil.nvl(vo.getSearchTo());
		int totalCnt = 0;
		
		ProcessResultListVO<EgovMap> resultListVO = new ProcessResultListVO<>();
		List<EgovMap> egovList = new ArrayList<EgovMap>();
		vo.setSearchFrom(searchFrom);
		vo.setSearchTo(searchTo);
		resultListVO = hrdApiUserInfoService.listPageingUserInfo(vo);
		if(resultListVO.getResult() > 0) {
			egovList = resultListVO.getReturnList();
			totalCnt = resultListVO.getPageInfo().getTotalRecordCount();
		}
		request.setAttribute("totalCnt", totalCnt);
		request.setAttribute("etcApiSyncList", egovList);
		request.setAttribute("pageInfo", resultListVO.getPageInfo());
		request.setAttribute("vo", vo);
		
		return "mng/etc/hrdapi/list_user_info_api_sync";
	}
	
	/**
     * @Method Name : viewUserLoginApiSyncPop
     * @Method 설명 : api  전송 결과 조회 리스트
	 * @param HrdOtpVO 
	 * @param commandMap
	 * @param model
	 * @return  "mng/etc/hrdapi/view_user_login_api_sync_pop.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/viewUserLoginApiSyncPop")
	public String viewUserLoginApiSyncPop(HrdApiSyncVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		String searchFrom = StringUtil.nvl(vo.getSearchFrom());
		String searchTo = StringUtil.nvl(vo.getSearchTo());
		String nowDate = DateTimeUtil.getDateType(1, DateTimeUtil.getDate(),".");
		if("".equals(searchFrom)) {
			searchFrom = nowDate;
		}
		if("".equals(searchTo)) {
			searchTo = nowDate;
		}
		request.setAttribute("searchFrom", searchFrom);
		request.setAttribute("searchTo", searchTo);
		request.setAttribute("paging", "Y");
		request.setAttribute("vo", vo);
		
		return "mng/etc/hrdapi/view_user_login_api_sync_pop";
	}
	
	/**
     * @Method Name : listUserLoginApiSync
     * @Method 설명 : api  전송 결과 조회 리스트
	 * @param HrdOtpVO 
	 * @param commandMap
	 * @param model
	 * @return  "mng/etc/hrdapi/list_user_login_api_sync.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/listUserLoginApiSync")
	public String listUserLoginApiSync(HrdApiUserLoginVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		String searchFrom = StringUtil.nvl(vo.getSearchFrom());
		String searchTo = StringUtil.nvl(vo.getSearchTo());
		int totalCnt = 0;
		
		ProcessResultListVO<EgovMap> resultListVO = new ProcessResultListVO<>();
		List<EgovMap> egovList = new ArrayList<EgovMap>();
		vo.setSearchFrom(searchFrom);
		vo.setSearchTo(searchTo);
		resultListVO = hrdApiUserLoginService.listPageingUserLogin(vo);
		if(resultListVO.getResult() > 0) {
			egovList = resultListVO.getReturnList();
			totalCnt = resultListVO.getPageInfo().getTotalRecordCount();
		}
		request.setAttribute("totalCnt", totalCnt);
		request.setAttribute("etcApiSyncList", egovList);
		request.setAttribute("pageInfo", resultListVO.getPageInfo());
		request.setAttribute("vo", vo);
		
		return "mng/etc/hrdapi/list_user_login_api_sync";
	}
	
	/**
	 * @Method Name : callCntsHisHrdApi
     * @Method 설명 : api  전송 결과 조회 리스트
	 * @param vo
	 * @param commandMap
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/callCntsHisHrdApi")
	public String callCntsHisHrdApi(HrdApiSyncVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ProcessResultListVO<HrdApiVO> resultList = new ProcessResultListVO<HrdApiVO>();
		
		String crsCd = StringUtil.nvl(request.getParameter("crsCd"));//관리자페이지에서 전달받은 기수
		String userNo = StringUtil.nvl(UserBroker.getUserNo(request));
		
		try {
			if(ValidationUtils.isEmpty(crsCd)) {
				throw new ServiceProcessException("기수를 선택바랍니다.");
			}
			
			//과정 정보 조회
			CourseVO courseVO = new CourseVO();
			courseVO.setCrsCd(crsCd);
			CourseVO returnCourseVO = courseService.view(courseVO).getReturnVO();
			
			String crsYear = returnCourseVO.getCrsYear().toString();
			String crsTerm = returnCourseVO.getCrsTerm().toString();
			
			if(ValidationUtils.isEmpty(returnCourseVO)) {
				throw new ServiceProcessException("선택한 기수의 정보가 없습니다.");
			}
			
			if(ValidationUtils.isEmpty(crsYear) || ValidationUtils.isEmpty(crsTerm)) {
				throw new ServiceProcessException("기수의 년도 또는 기수 이름이 없습니다.");
			}
			
			//콘텐츠 정보 조회
			List<EgovMap> egovList = new ArrayList<EgovMap>();
			
			HrdApiSyncVO hasvo = new HrdApiSyncVO();
			hasvo.setCrsYear(returnCourseVO.getCrsYear());
			hasvo.setCrsTerm(returnCourseVO.getCrsTerm());
			hasvo.setRegNo(userNo);
			hasvo.setModNo(userNo);
			
			//merge
			hrdApiService.mergeCntsHrdApiSync(hasvo);
			
			//select
			egovList = hrdApiService.listCntsHrdApiSync(hasvo);
			
			//api
			//hrdApiService.callCntsHrdApi(egovList,hasvo,request);
			
		} catch (MediopiaDefineException e1) {
			resultList.setResult(-1);
			resultList.setMessage(e1.getMessage());
			log.error("[관리자 컨텐츠 등록 실패]" + e1.getMessage());
		} catch (Exception e) {
			resultList.setResult(-1);
		}
		
		return JsonUtil.responseJson(response, resultList);
	}
	
	
	/****************************************************** 수강정보 API START *********************************************************/
	/**
     * @Method Name : listUserInfoApiLog
     * @Method 설명 : 회원정보 api 전송이력  조회
	 * @param HrdApiVO 
	 * @param commandMap
	 * @param model
	 * @return  "/mng/etc/hrdapi/view_motp_pop.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/listStdInfoApi")
	public String listStdInfoApi(HrdApiStdVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		
		EgovMap countMap = new EgovMap();
		//여기서 각자 만든 테이블 카운팅 조회해와
		countMap = hrdApiStdService.countStdApiStatus(vo);
		request.setAttribute("countMap", countMap);
		request.setAttribute("vo", vo);
		
		return "mng/etc/hrdapi/list_std_info_api";
	}
	
	/**
	 * @Method Name : callUserInfoHrdApi
	 * @Method 설명 : 회원 정보 api java 전송
	 * @param VO
	 * @param commandMap
	 * @param model
	 * @return  
	 * @throws Exception
	 */
	@RequestMapping(value="/callStdInfoHrdApi")
	public String callStdInfoHrdApi(HrdApiStdVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String crsCd = StringUtil.nvl(request.getParameter("crsCd"));
		String userNo = UserBroker.getUserNo(request);
		
		ProcessResultListVO<HrdApiVO> resultList = new ProcessResultListVO<HrdApiVO>();
		try {
			List<EgovMap> egovList = new ArrayList<EgovMap>();
			
			HrdApiStdVO hasvo = new HrdApiStdVO();
			hasvo.setNum(vo.getNum());
			hasvo.setCrsCd(vo.getCrsCd());
			hasvo.setRegNo(userNo);
			hasvo.setModNo(userNo);
			hasvo.setSyncStatus(StringUtil.nvl(vo.getSyncStatus(),"W"));
			egovList = hrdApiStdService.listStdInfo(hasvo);
			String jsonString = JsonUtil.getJsonStringHrdApi(egovList);
			
			resultList = hrdApiStdService.callStdInfoHrdApi(jsonString, egovList, hasvo);
		} catch (Exception e) {
			resultList.setResult(-1);
		}
		return JsonUtil.responseJson(response, resultList);
	}
	
	/**
     * @Method Name : viewUserInfoApiSyncPop
     * @Method 설명 : api  전송 결과 조회 리스트
	 * @param HrdOtpVO 
	 * @param commandMap
	 * @param model
	 * @return  "mng/etc/hrdapi/view_user_info_api_sync_pop.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/viewStdApiResultPop")
	public String viewStdApiResultPop(HrdApiStdVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		request.setAttribute("vo", vo);
		
		return "mng/etc/hrdapi/view_std_api_result_pop";
	}
	
	/**
     * @Method Name : listUserInfoApiSync
     * @Method 설명 : api  전송 결과 조회 리스트
	 * @param HrdOtpVO 
	 * @param commandMap
	 * @param model
	 * @return  "mng/etc/hrdapi/list_user_info_api_sync.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/listStdApiResult")
	public String listStdApiResult(HrdApiStdVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		String searchFrom = StringUtil.nvl(vo.getSearchFrom());
		String searchTo = StringUtil.nvl(vo.getSearchTo());
		int totalCnt = 0;
		
		ProcessResultListVO<EgovMap> resultListVO = new ProcessResultListVO<>();
		List<EgovMap> egovList = new ArrayList<EgovMap>();
		resultListVO = hrdApiStdService.listStdInfoPageing(vo);
		if(resultListVO.getResult() > 0) {
			egovList = resultListVO.getReturnList();
			totalCnt = resultListVO.getPageInfo().getTotalRecordCount();
		}
		request.setAttribute("totalCnt", totalCnt);
		request.setAttribute("stdList", egovList);
		request.setAttribute("vo", vo);
		
		return "mng/etc/hrdapi/list_std_api_result";
	}
	/****************************************************** 수강정보 API END *********************************************************/
	
	/****************************************************** 과정정보 API START *********************************************************/
	/**
     * @Method Name : listCourseInfoApi
     * @Method 설명 : 과정 정보 API 전송 이력 조회
	 * @param HrdApiSyncVO 
	 * @param HttpServletRequest
	 * @return  "/mng/etc/hrdapi/list_course_info_api.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/listCourseInfoApi")
	public String listCourseInfoApi(HrdApiSyncVO vo, HttpServletRequest request) throws Exception {
		List<EgovMap> resultList = hrdApiOnlnSbjService.getList(new HrdApiOnlnSbjVO());
		int syncTotalCnt = resultList.size();

		request.setAttribute("courseInfoApiList", resultList);
		request.setAttribute("syncTotalCnt", syncTotalCnt);
		request.setAttribute("vo", vo);
		
		return "mng/etc/hrdapi/list_course_info_api";
	}
	
	/**
     * @Method Name : callCourseInfoHrdApi
     * @Method 설명 : 과정 정보 API 전송
	 * @param HrdApiOnlnSbjVO 
	 * @param HttpServletRequest
	 * @return  ProcessResultListVO<HrdApiVO>
	 * @throws Exception
	 */
	@RequestMapping(value="/callCourseInfoHrdApi")
	@ResponseBody
	public ProcessResultListVO<HrdApiVO> callCourseInfoHrdApi(HrdApiOnlnSbjVO vo, HttpServletRequest request) throws Exception {
		ProcessResultListVO<HrdApiVO> resultList = new ProcessResultListVO<HrdApiVO>();
		try {
			List<EgovMap> egovList = hrdApiOnlnSbjService.getListForSync(vo);
			resultList = hrdApiOnlnSbjService.callCourseInfoHrdApi(egovList,vo);
		} catch (Exception e) {
			resultList.setResult(-1);
		}
		return resultList;
	}
	
	/**
     * @Method Name : viewCourseInfoApiSyncPop
     * @Method 설명 : API 전송 리스트 조회 팝업
	 * @param HrdApiOnlnSbjVO 
	 * @param HttpServletRequest
	 * @return  "mng/etc/hrdapi/view_course_info_api_sync_pop.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/viewCourseInfoApiSyncPop")
	public String viewCourseInfoApiSyncPop(HrdApiOnlnSbjVO vo, HttpServletRequest request) throws Exception {
		String searchFrom = StringUtil.nvl(vo.getSearchFrom());
		String searchTo = StringUtil.nvl(vo.getSearchTo());
		String nowDate = DateTimeUtil.getDateType(1, DateTimeUtil.getDate(),".");
		if("".equals(searchFrom)) {
			searchFrom = nowDate;
		}
		if("".equals(searchTo)) {
			searchTo = nowDate;
		}
		request.setAttribute("searchFrom", searchFrom);
		request.setAttribute("searchTo", searchTo);
		request.setAttribute("paging", "Y");
		request.setAttribute("vo", vo);
		
		return "mng/etc/hrdapi/view_course_info_api_sync_pop";
	}
	
	/**
     * @Method Name : listCourseInfoApiSync
     * @Method 설명 : API 전송 리스트 조회
	 * @param HrdApiOnlnSbjVO 
	 * @param HttpServletRequest
	 * @return  "mng/etc/hrdapi/list_course_info_api_sync.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/listCourseInfoApiSync")
	public String listCourseInfoApiSync(HrdApiOnlnSbjVO vo,	HttpServletRequest request) throws Exception {
		String searchFrom = StringUtil.nvl(vo.getSearchFrom());
		String searchTo = StringUtil.nvl(vo.getSearchTo());
		int totalCnt = 0;
		
		ProcessResultListVO<EgovMap> resultListVO = new ProcessResultListVO<>();
		List<EgovMap> egovList = new ArrayList<EgovMap>();
		vo.setSearchFrom(searchFrom);
		vo.setSearchTo(searchTo);
		
		resultListVO = hrdApiOnlnSbjService.getListPageing(vo);
		
		egovList = resultListVO.getReturnList();
		totalCnt = resultListVO.getPageInfo().getTotalRecordCount();
		
		request.setAttribute("totalCnt", totalCnt);
		request.setAttribute("courseApiSyncList", egovList);
		request.setAttribute("pageInfo", resultListVO.getPageInfo());
		request.setAttribute("vo", vo);
		
		return "mng/etc/hrdapi/list_course_info_api_sync";
	}
	/****************************************************** 과정정보 API END *********************************************************/
	
	
	/****************************************************** 수업정보 API START *********************************************************/
	/**
     * @Method Name : listCreInfoApiLog
     * @Method 설명 : 수업정보 api 전송이력  조회
	 * @param HrdApiVO 
	 * @param commandMap
	 * @param model
	 * @return  "/mng/etc/hrdapi/list_cre_info_api.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/listCreInfoApi")
	public String listCreInfoApi(HrdApiCreVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		
		int syncTotalCnt = 0;
		
		List<EgovMap> resultList = new ArrayList<>();
		resultList =hrdApiCreService.listCreInfo(vo);
		if(resultList != null) {
			syncTotalCnt = resultList.size();
		}

		request.setAttribute("creInfoApiList", resultList);
		request.setAttribute("syncTotalCnt", syncTotalCnt);
		request.setAttribute("vo", vo);
		
		
		return "mng/etc/hrdapi/list_cre_info_api";
	}
	
	/**
	 * @Method Name : callCreInfoHrdApi
	 * @Method 설명 : 수업 정보 api java 전송
	 * @param VO
	 * @param commandMap
	 * @param model
	 * @return  
	 * @throws Exception
	 */
	@RequestMapping(value="/callCreInfoHrdApi")
	public String callCreInfoHrdApi(HrdApiCreVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String crsCd = StringUtil.nvl(request.getParameter("crsCd"));
		String userNo = UserBroker.getUserNo(request);
		
		ProcessResultListVO<HrdApiVO> resultList = new ProcessResultListVO<HrdApiVO>();
		try {
			HrdApiCreVO hasvo = new HrdApiCreVO();
			hasvo.setCrsCd(crsCd);
			hasvo.setClassAgentPk(vo.getClassAgentPk());
			hasvo.setRegNo(userNo);
			hasvo.setModNo(userNo);
			hasvo.setSyncGubunCd(vo.getSyncGubunCd());
			if(!"".equals(StringUtil.nvl(vo.getSyncStatus()))) {
				hasvo.setSearchKey("syncStatus");
				hasvo.setSearchValue(vo.getSyncStatus());
			}
			List<EgovMap> egovList = new ArrayList<EgovMap>();
			egovList =hrdApiCreService.listCreInfoApi(hasvo);
			String jsonString = JsonUtil.getJsonStringHrdApi(egovList);
			
			resultList = hrdApiCreService.callCreInfoHrdApi(jsonString, egovList, hasvo);
		} catch (Exception e) {
			resultList.setResult(-1);
		}
		return JsonUtil.responseJson(response, resultList);
	}
	
	/**
     * @Method Name : viewUserInfoApiSyncPop
     * @Method 설명 : api  전송 결과 조회 리스트
	 * @param HrdOtpVO 
	 * @param commandMap
	 * @param model
	 * @return  "mng/etc/hrdapi/view_user_info_api_sync_pop.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/viewCreApiSyncPop")
	public String viewCreApiFailApiSyncPop(HrdApiCreVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {

		request.setAttribute("vo", vo);
		request.setAttribute("paging", "Y");
		return "mng/etc/hrdapi/view_cre_api_sync_pop";
	}
	
	/**
     * @Method Name : listUserInfoApiSync
     * @Method 설명 : api  전송 결과 조회 리스트
	 * @param HrdOtpVO 
	 * @param commandMap
	 * @param model
	 * @return  "mng/etc/hrdapi/list_user_info_api_sync.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/listCreApiSyncResult")
	public String listCreApiFailResult(HrdApiCreVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		int totalCnt = 0;
		
		ProcessResultListVO<EgovMap> resultListVO = new ProcessResultListVO<>();
		List<EgovMap> egovList = new ArrayList<EgovMap>();
		resultListVO = hrdApiCreService.listCreInfoPageing(vo);
		if(resultListVO.getResult() > 0) {
			egovList = resultListVO.getReturnList();
			totalCnt = resultListVO.getPageInfo().getTotalRecordCount();
		}
		request.setAttribute("totalCnt", totalCnt);
		request.setAttribute("pageInfo", resultListVO.getPageInfo());
		request.setAttribute("creList", egovList);
		request.setAttribute("vo", vo);
		
		return "mng/etc/hrdapi/list_cre_api_sync_result";
	}
	/****************************************************** 수업정보 API END *********************************************************/
	
	
	/****************************************************** 성적이력 API START *********************************************************/
	/**
     * @Method Name : listScoreApi
     * @Method 설명 : 성적이력 api 전송이력  조회
	 * @param HrdApiVO 
	 * @param commandMap
	 * @param model
	 * @return  "/mng/etc/hrdapi/list_score_api.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/listScoreApi")
	public String listScoreApi(HrdApiScoreVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		
		List<EgovMap> resultList = new ArrayList<EgovMap>();
		String crsCd = StringUtil.nvl(vo.getCrsCd());
		
		int syncTotalCnt = 0;
		resultList = hrdApiScoreService.listScore(vo);
		
		if(resultList != null) {
			syncTotalCnt = resultList.size();
		}
		
		request.setAttribute("scoreApiList", resultList);
		request.setAttribute("syncTotalCnt", syncTotalCnt);
		request.setAttribute("vo", vo);
		
		return "mng/etc/hrdapi/list_score_api";
	}
	
	/**
     * @Method Name : viewScoreApiSyncPop
     * @Method 설명 : api 전송 결과 조회 리스트
	 * @param HrdOtpVO 
	 * @param commandMap
	 * @param model
	 * @return  "mng/etc/hrdapi/view_score_api_sync_pop.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/viewScoreApiSyncPop")
	public String viewScoreApiSyncPop(HrdApiScoreVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		String crsCd = StringUtil.nvl(vo.getCrsCd());
		
		//request.setAttribute("searchFrom", searchFrom);
		//request.setAttribute("searchTo", searchTo);
		request.setAttribute("paging", "Y");
		request.setAttribute("vo", vo);
		
		return "mng/etc/hrdapi/view_score_api_sync_pop";
	}
	
	/**
     * @Method Name : listScoreApiSync
     * @Method 설명 : api  전송 결과 조회 리스트
	 * @param HrdOtpVO 
	 * @param commandMap
	 * @param model
	 * @return  "mng/etc/hrdapi/list_score_api_sync.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/listScoreApiSync")
	public String listScoreApiSync(HrdApiScoreVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		//String searchFrom = StringUtil.nvl(vo.getSearchFrom());
		//String searchTo = StringUtil.nvl(vo.getSearchTo());
		int totalCnt = 0;
		
		ProcessResultListVO<EgovMap> resultListVO = new ProcessResultListVO<>();
		List<EgovMap> egovList = new ArrayList<EgovMap>();
		//vo.setSearchFrom(searchFrom);
		//vo.setSearchTo(searchTo);
		resultListVO = hrdApiScoreService.listPagingScore(vo);
		if(resultListVO.getResult() > 0) {
			egovList = resultListVO.getReturnList();
			totalCnt = resultListVO.getPageInfo().getTotalRecordCount();
		}
		request.setAttribute("totalCnt", totalCnt);
		request.setAttribute("etcApiSyncList", egovList);
		request.setAttribute("pageInfo", resultListVO.getPageInfo());
		request.setAttribute("vo", vo);
		
		return "mng/etc/hrdapi/list_score_api_sync";
	}
	
	/**
	 * @Method Name : callScoreHrdApi
     * @Method 설명 : api  전송 결과 조회 리스트
	 * @param vo
	 * @param commandMap
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/callScoreHrdApi")
	public String callScoreHrdApi(HrdApiScoreVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ProcessResultListVO<HrdApiVO> resultList = new ProcessResultListVO<HrdApiVO>();
				
		try {
			HrdApiScoreVO paramVO = new HrdApiScoreVO();
			
			if(!"".equals(StringUtil.nvl(vo.getSyncStatus()))) {
				paramVO.setSearchKey("syncStatus");
				paramVO.setSearchValue(vo.getSyncStatus());
			}
			if(!"".equals(StringUtil.nvl(vo.getNum()))) {
				paramVO.setNum(vo.getNum());
			}
			if(!"".equals(StringUtil.nvl(vo.getUserAgentPk()))) {
				paramVO.setUserAgentPk(vo.getUserAgentPk());
			}
			if(!"".equals(StringUtil.nvl(vo.getCrsCd()))) {
				paramVO.setCrsCd(vo.getCrsCd());
			}
			List<EgovMap> egovList = hrdApiScoreService.listForApi(paramVO);
			resultList = hrdApiScoreService.callApi(egovList, paramVO);
			resultList.setResult(1);
			//resultList.setMessage("성공");
		} catch (MediopiaDefineException e1) {
			resultList.setResult(-1);
			resultList.setMessage(e1.getMessage());
		} catch (Exception e) {
			resultList.setResult(-1);
			//resultList.setMessage("실패");
		}
		
		return JsonUtil.responseJson(response, resultList);
	}
	/****************************************************** 성적이력 API END *********************************************************/
	
}
