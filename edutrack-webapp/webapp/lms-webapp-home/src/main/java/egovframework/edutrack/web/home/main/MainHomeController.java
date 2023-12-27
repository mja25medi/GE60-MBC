package egovframework.edutrack.web.home.main;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.SysCodeMemService;
import egovframework.edutrack.comm.service.SysMenuMemService;
import egovframework.edutrack.comm.util.web.DateTimeUtil;
import egovframework.edutrack.comm.util.web.HtmlCleaner;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.board.bbs.service.BrdBbsAtclService;
import egovframework.edutrack.modules.board.bbs.service.BrdBbsAtclVO;
import egovframework.edutrack.modules.board.bbs.service.BrdBbsInfoService;
import egovframework.edutrack.modules.board.popup.service.BrdPopupNoticeService;
import egovframework.edutrack.modules.board.popup.service.BrdPopupNoticeVO;
import egovframework.edutrack.modules.course.category.service.CourseCategoryService;
import egovframework.edutrack.modules.course.category.service.CourseCategoryVO;
import egovframework.edutrack.modules.course.course.service.CourseService;
import egovframework.edutrack.modules.course.course.service.CourseVO;
import egovframework.edutrack.modules.course.createcourse.service.CreateCourseService;
import egovframework.edutrack.modules.course.createcourse.service.CreateCourseVO;
import egovframework.edutrack.modules.course.createcourse.service.UserCourseVO;
import egovframework.edutrack.modules.course.subject.service.OnlineSubjectVO;
import egovframework.edutrack.modules.course.subject.service.SubjectService;
import egovframework.edutrack.modules.etc.banner.service.EtcBnnrService;
import egovframework.edutrack.modules.etc.banner.service.EtcBnnrVO;
import egovframework.edutrack.modules.etc.event.service.EtcEventService;
import egovframework.edutrack.modules.etc.event.service.EtcEventVO;
import egovframework.edutrack.modules.etc.relatedsite.service.EtcRelatedSiteService;
import egovframework.edutrack.modules.log.message.service.LogMsgLogService;
import egovframework.edutrack.modules.log.message.service.LogMsgLogVO;
import egovframework.edutrack.modules.log.sysconn.service.LogSysConnLogService;
import egovframework.edutrack.modules.log.sysconn.service.LogSysConnLogVO;
import egovframework.edutrack.modules.main.service.MainVO;
import egovframework.edutrack.modules.opencourse.course.service.OpenCrsService;
import egovframework.edutrack.modules.org.domain.service.OrgDomainService;
import egovframework.edutrack.modules.org.domain.service.OrgDomainVO;
import egovframework.edutrack.modules.org.image.service.OrgImgInfoService;
import egovframework.edutrack.modules.org.image.service.OrgImgInfoVO;
import egovframework.edutrack.modules.org.info.service.OrgOrgInfoService;
import egovframework.edutrack.modules.org.info.service.OrgOrgInfoVO;
import egovframework.edutrack.modules.org.menu.service.OrgMenuVO;
import egovframework.edutrack.modules.org.page.service.OrgPageService;
import egovframework.edutrack.modules.org.page.service.OrgPageVO;
import egovframework.edutrack.modules.system.config.service.SysCfgCtgrVO;
import egovframework.edutrack.modules.system.config.service.SysCfgService;
import egovframework.edutrack.modules.system.config.service.SysCfgVO;
import egovframework.edutrack.modules.user.info.service.UsrUserAuthGrpVO;
import egovframework.edutrack.modules.user.info.service.UsrUserInfoService;
import egovframework.edutrack.modules.user.info.service.UsrUserInfoVO;

@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/home/main")
public class MainHomeController extends GenericController {
	
	@Autowired @Qualifier("sysMenuMemService")
	private SysMenuMemService 	sysMenuMemService;
	
	@Autowired @Qualifier("etcRelatedSiteService")
	private EtcRelatedSiteService 	etcRelatedSiteService;
	
	@Autowired @Qualifier("brdPopupNoticeService")
	private BrdPopupNoticeService 	brdPopupNoticeService;
	
	@Autowired @Qualifier("sysCodeMemService")
	private SysCodeMemService 	sysCodeMemService;
	
	@Autowired @Qualifier("sysCfgService")
	private SysCfgService 	sysCfgService;

	@Autowired @Qualifier("logSysConnLogService")
	private LogSysConnLogService 		logSysConnLogService;

	@Autowired @Qualifier("brdBbsInfoService")
	private BrdBbsInfoService brdBbsInfoService;

	@Autowired @Qualifier("brdBbsAtclService")
	private BrdBbsAtclService brdBbsAtclService;
	
	@Autowired @Qualifier("orgImgInfoService")
	private OrgImgInfoService		orgImgInfoService;

	@Autowired @Qualifier("logMsgLogService")
	private LogMsgLogService			logMsgLogService;
	
	@Autowired @Qualifier("createCourseService")
	private CreateCourseService 	createCourseService;

	@Autowired @Qualifier("openCrsService")
	private OpenCrsService			openCrsService;

	@Autowired @Qualifier("etcBnnrService")
	private EtcBnnrService			etcBnnrService;
	
	@Autowired @Qualifier("orgOrgInfoService")
	private OrgOrgInfoService		orgOrgInfoService;
	
	@Autowired @Qualifier("subjectService")
	private SubjectService	subjectService;
	
	@Autowired @Qualifier("orgDomainService")
	private OrgDomainService 		orgDomainService;
	
	@Autowired @Qualifier("courseCategoryService")
	private CourseCategoryService	courseCategoryService;
	
	@Autowired @Qualifier("courseService")
	private CourseService 					courseService;
	
	@Autowired @Qualifier("orgPageService")
	private OrgPageService 		orgPageService;
	
	@Autowired @Qualifier("etcEventService")
	private EtcEventService 		etcEventService;
	
	@Autowired @Qualifier("usrUserInfoService")
	private UsrUserInfoService 		usrUserInfoService;
	
	/**
     * @Method Name : mainPage
     * @Method 설명 : 사용자 메인 페이지 
	 * @param MainVO 
	 * @param commandMap
	 * @param model
	 * @return main/main_home
	 * @throws Exception
	 */
	@RequestMapping(value="/indexMain")
	public String mainPage(MainVO vo, Map commandMap, ModelMap model, HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		
		String userNo = UserBroker.getUserNo(request);	// 로그인이 되어 있을 경우 맞춤 콘텐츠 정보 조회
		//-- 권한별 메뉴를 가져와 폼에 심는다.
		String userType = StringUtil.nvl(UserBroker.getUserType(request),"GUEST");
		String mbrAplcUse = UserBroker.getUserMbrAplcUseYn(request);	// 회원가입 사용여부 조회
		String orgCd = UserBroker.getUserOrgCd(request);	// 기관 코드 조회
		String svrName = request.getServerName();
		String tplCd = UserBroker.getUserColorTpl(request);
		
		OrgDomainVO orgDomainVO = new OrgDomainVO();
		orgDomainVO.setOrgCd(orgCd);
		orgDomainVO.setOrgDomain(svrName);
		
		orgDomainVO = orgDomainService.view(orgDomainVO);
		
		if(orgDomainVO.getDomainTypeCd().equals("TUTOR")) {
			return "redirect:/home/tch/info/loginForm";
		}
		
		if(orgDomainVO.getDomainTypeCd().equals("ADMIN")) {
			return "redirect:/mng/main/loginMain";
		}
		

		//-- 시스템 접속 정보를 저장한다. 세션의 정보를 가져와 정보가 없는 경우만 접속정보 저장함.
		if(request.getSession().getAttribute("ConnectChk") == null) {
			LogSysConnLogVO logSysConnLogVO = new LogSysConnLogVO();
			logSysConnLogVO.setOrgCd(orgCd);
			logSysConnLogService.add(logSysConnLogVO);
			request.getSession().setAttribute("ConnectChk", "Y");
		}
		
		OrgMenuVO orgMenuVO = sysMenuMemService.getOrgHomeMenuList(orgCd, userType);
		request.setAttribute("orgMenuVO", orgMenuVO);


		//-- 메인 이미지 가져오기
		OrgImgInfoVO orgImgInfoVO = new OrgImgInfoVO();
		orgImgInfoVO.setOrgCd(orgCd);
		orgImgInfoVO.setImgTypeCd("MAINIMG");
		List<OrgImgInfoVO> orgImgList = orgImgInfoService.list(orgImgInfoVO).getReturnList();
		request.setAttribute("orgImgList", orgImgList);
		
		OrgOrgInfoVO orgInfoVO = new OrgOrgInfoVO();
		orgInfoVO.setOrgCd(orgCd);
		OrgOrgInfoVO orgInfo = orgOrgInfoService.view(orgInfoVO);
		request.setAttribute("orgInfo", orgInfo);

		BrdBbsAtclVO bbsAtclVO = new BrdBbsAtclVO();
		bbsAtclVO.setOrgCd(orgCd);
		bbsAtclVO.setTopCnt(5);

		//-- 공지사항 TOP5 가져오기
		bbsAtclVO.setBbsCd("NOTICE");
		ProcessResultListVO<BrdBbsAtclVO> noticeList = brdBbsAtclService.listTopAtcl(bbsAtclVO);
		request.setAttribute("noticeList", noticeList.getReturnList());

		//-- 자료실 TOP5 가져오기
		bbsAtclVO.setBbsCd("PDS");
		ProcessResultListVO<BrdBbsAtclVO> pdsList = brdBbsAtclService.listTopAtcl(bbsAtclVO);
		request.setAttribute("pdsList", pdsList.getReturnList());
		
		//-- 수강후기 TOP5 가져오기
		bbsAtclVO.setBbsCd("REVIEW");
		bbsAtclVO.setTopCnt(3);
		ProcessResultListVO<BrdBbsAtclVO> resultList = brdBbsAtclService.listTopAtcl(bbsAtclVO);
		List<BrdBbsAtclVO> reviewList = resultList.getReturnList();
		for(BrdBbsAtclVO review : reviewList) {
			review.setAtclCts(HtmlCleaner.cleanTag(review.getAtclCts()));
		}
		request.setAttribute("reviewList", reviewList);

		//-- 팝업 공지 사항 읽기
		BrdPopupNoticeVO popupNoticeVO = new BrdPopupNoticeVO();
		popupNoticeVO.setOrgCd(orgCd);
		popupNoticeVO.setUseYnWww("Y");
		popupNoticeVO.setSearchDate("Y");
		List<BrdPopupNoticeVO> popupNoticeList = brdPopupNoticeService.listPopup(popupNoticeVO).getReturnList();
		request.setAttribute("popupNoticeList", popupNoticeList);

		//-- 안읽은 쪽지수
		LogMsgLogVO logMsgLogVO = new LogMsgLogVO();
		logMsgLogVO.setRegNo(UserBroker.getUserNo(request));
		int msgCnt = logMsgLogService.msgCount(logMsgLogVO);
		request.setAttribute("msgCnt", msgCnt);

		//과목 목록
		OnlineSubjectVO osVO = new OnlineSubjectVO();
		osVO.setMainYn("Y");
		osVO.setOrgCd(orgCd);
		ProcessResultListVO<OnlineSubjectVO> onSbjList = subjectService.listOnline(osVO);
		request.setAttribute("onSbjList", onSbjList.getReturnList());
		/*
		CreateCourseVO ccVO = new CreateCourseVO();
		String curYear = DateTimeUtil.getYear();
		ccVO.setCreYear(curYear);
		ccVO.setRegNo(UserBroker.getUserNo(request));
		ccVO.setOrgCd(orgCd);
		ProcessResultListVO<CreateCourseVO> courseList = createCourseService.listCreateCourseForEnrollSearchPaging(ccVO);
		request.setAttribute("courseList", courseList.getReturnList());
		request.setAttribute("userType", userType);
		*/

		//-- 베너목록 검색
		EtcBnnrVO bnnrVO = new EtcBnnrVO();
		bnnrVO.setOrgCd(orgCd);
		bnnrVO.setOpenYn("Y");
		List<EtcBnnrVO> bannerList = etcBnnrService.list(bnnrVO).getReturnList();
		request.setAttribute("bannerList", bannerList);

		SysCfgCtgrVO ccvo = new SysCfgCtgrVO();
		ccvo.setCfgCtgrCd("MENUNO");
		List<SysCfgVO> configList = sysCfgService.listConfig(ccvo).getReturnList();
		for(SysCfgVO cfgdto : configList) {
			if("JOININ".equals(cfgdto.getCfgCd())) request.setAttribute("joininMcd", cfgdto.getCfgVal());
			if("LOGIN".equals(cfgdto.getCfgCd())) request.setAttribute("loginMcd", cfgdto.getCfgVal());
			if("FINDIDPW".equals(cfgdto.getCfgCd())) request.setAttribute("findidMcd", cfgdto.getCfgVal());
			if("CRSSEARCH".equals(cfgdto.getCfgCd())) request.setAttribute("crsSearchMcd", cfgdto.getCfgVal());
			if("CRSSCHE".equals(cfgdto.getCfgCd())) request.setAttribute("crsCalendarMcd", cfgdto.getCfgVal());
			if("MESSAGEBOX".equals(cfgdto.getCfgCd())) request.setAttribute("messageBoxMcd", cfgdto.getCfgVal());
			if("EDITMYINFO".equals(cfgdto.getCfgCd())) request.setAttribute("editMyinfoMcd", cfgdto.getCfgVal());
			if("NOTICE".equals(cfgdto.getCfgCd())) request.setAttribute("noticeMcd", cfgdto.getCfgVal());
			if("ARCHIVE".equals(cfgdto.getCfgCd())) request.setAttribute("archiveMcd", cfgdto.getCfgVal());
			if("SITEMAP".equals(cfgdto.getCfgCd())) request.setAttribute("siteMapMcd", cfgdto.getCfgVal());
			if("OPENCRS".equals(cfgdto.getCfgCd())) request.setAttribute("openCrsMcd", cfgdto.getCfgVal());
			if("SEARCHFULL".equals(cfgdto.getCfgCd())) request.setAttribute("searchFullMcd", cfgdto.getCfgVal());

		}
		
		List<UserCourseVO> creList = null;
		Map<String, Object> userInfo = new Hashtable<String, Object>();
		userInfo.put("userNo", UserBroker.getUserNo(request));
		userInfo.put("orgCd", UserBroker.getUserOrgCd(request));
		//-- 학습중인과정, 준비중인 과정 목록 가져오기
		if(userType.contains("TEACHER") || userType.contains("TUTOR") || userType.contains("TCH")) {
			//-- 강사나 투터의 경우 운영중인 과정
			userInfo.put("crsCreNm", "");
			userInfo.put("enrlSts", "");
			creList = createCourseService.myCreListForTeacher(userInfo).getReturnList();
		} else {
			//-- 학습중인 과정
			userInfo.put("enrlSts", "ing");
			creList = createCourseService.myCreListForStudent(userInfo).getReturnList();
		}
		request.setAttribute("creCrsList", creList);
		
		//과정 분류 목록 조회
		ProcessResultListVO<CourseCategoryVO> result = courseCategoryService.listCategory(orgCd, "");
		request.setAttribute("courseCategoryList", result.getReturnList());
		
		OrgPageVO pageVO = new OrgPageVO();
		pageVO.setOrgCd(orgCd);
		
		//BAST CTS 조회
		pageVO.setPageCd("MPAGE001");
		OrgPageVO bastPageVO = orgPageService.view(pageVO);
		request.setAttribute("bastPageVO", bastPageVO);
		
		//REVIEW CTS 조회
		pageVO.setPageCd("MPAGE002");
		OrgPageVO reviewPageVO = orgPageService.view(pageVO);
		request.setAttribute("reviewPageVO", reviewPageVO);
		
		//NEWS CTS 조회
		pageVO.setPageCd("MPAGE003");
		OrgPageVO newsPageVO = orgPageService.view(pageVO);
		request.setAttribute("newsPageVO", newsPageVO);
		
		//교육신청 카운트다운 
		CreateCourseVO cntCourseVO = new CreateCourseVO();
		cntCourseVO.setOrgCd(orgCd);
		cntCourseVO = createCourseService.cntCourse(cntCourseVO);
		if(cntCourseVO != null) {
			String cntMonth = cntCourseVO.getEnrlAplcEndDttm().substring(4,6);
			String cntDay = cntCourseVO.getEnrlAplcEndDttm().substring(6,8);
			request.setAttribute("cntCourseVO", cntCourseVO );
			request.setAttribute("cntCourseDate", cntMonth+'/'+cntDay);
		} else {
			request.setAttribute("cntCourse", "0");
		}
		
		//-- 이벤트 목록
		EtcEventVO eventVO = new EtcEventVO();
		eventVO.setOrgCd(orgCd);
		eventVO.setOpenYn("Y");
		List<EtcEventVO> eventList = etcEventService.list(eventVO).getReturnList();
		request.setAttribute("eventList", eventList);
		
		UsrUserInfoVO usrUserInfoVO = new UsrUserInfoVO();
		
		usrUserInfoVO.setOrgCd(orgCd);
		usrUserInfoVO.setUserNo(userNo);
		if(!userNo.equals("") && !orgCd.equals("")){
			usrUserInfoVO = usrUserInfoService.view(usrUserInfoVO);
			request.setAttribute("usrUserInfoVO", usrUserInfoVO);
		}
		
		String avatarEditUrl = Constants.AVATAR_EDIT_URL;
		request.setAttribute("avatarEditUrl",avatarEditUrl);
		
		request.getSession().setAttribute("MAIN_YN","Y");
		return UserBroker.getPrefixHomeUrl(request) + "main";
	}
	/**
	 * 마이페이지 - 진행중인 과정 (교수자)
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/listCourseIngTeacherMain")
	public String listCourseIngTeacherMain(MainVO vo, Map commandMap, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		Integer curPage = Integer.parseInt(StringUtil.nvl(request.getParameter("curPage"),"1"));

		String curYear = DateTimeUtil.getYear();
		String tarYear = StringUtil.nvl(request.getParameter("tarYear"), curYear);
		String crsCreNm = StringUtil.nvl(request.getParameter("crsCreNm"),"");

		request.setAttribute("tarYear", tarYear);
		request.setAttribute("crsCreNm", crsCreNm);

		//-- 시작년도 가져오기
		SysCfgVO configDTO = new SysCfgVO();
		configDTO.setCfgCtgrCd("SYSTEM");
		configDTO.setCfgCd("START_YEAR");
		configDTO = sysCfgService.viewCfg(configDTO);
		List<String> yearList = new ArrayList<String>();
		for(int i= Integer.parseInt(curYear,10)+1; i >= Integer.parseInt(configDTO.getCfgVal(),10); i--) {
			yearList.add(Integer.toString(i));
		}
		request.setAttribute("yearList", yearList);

		Map<String, Object> userInfo = new Hashtable<String, Object>();
		userInfo.put("userNo", UserBroker.getUserNo(request));
		userInfo.put("creYear", tarYear);
		userInfo.put("crsCreNm", crsCreNm);
		userInfo.put("enrlSts", "");

		ProcessResultListVO<UserCourseVO> resultList = createCourseService.listCreateCourseForTeacher(userInfo, curPage);

		request.setAttribute("courseList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());
		request.setAttribute("paging", "Y");
		request.setAttribute("vo", vo);
		return "home/main/list_course_ing_teacher_main";
	}
	/**
	 * 마이페이지 - 진행중인 과정 (학습자)
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/listCourseMain")
	public String listCourseMain(MainVO vo, Map commandMap, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		Integer curPage = Integer.parseInt(StringUtil.nvl(request.getParameter("curPage"),"1"));

		Map<String, Object> userInfo = new Hashtable<String, Object>();
		userInfo.put("userNo", UserBroker.getUserNo(request));
		userInfo.put("creYear", "");
		userInfo.put("enrlSts", "");

		ProcessResultListVO<UserCourseVO> resultList = createCourseService.listCreateCourseForStudent(userInfo);

		String curDate = DateTimeUtil.getDate();
		request.setAttribute("curDate", curDate);

		request.setAttribute("courseList", resultList.getReturnList());
		return "home/main/list_course_main";
	}

	/**
	 * 마이페이지 - 진행중인 과정 (학습자)
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/listCourseIngMain")
	public String listCourseIngMain(MainVO vo, Map commandMap, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		Integer curPage = Integer.parseInt(StringUtil.nvl(request.getParameter("curPage"),"1"));

		Map<String, Object> userInfo = new Hashtable<String, Object>();
		userInfo.put("userNo", UserBroker.getUserNo(request));
		userInfo.put("creYear", "");
		userInfo.put("enrlSts", "ing");

		ProcessResultListVO<UserCourseVO> resultList = createCourseService.listCreateCourseForStudent(userInfo, curPage);

		String curDate = DateTimeUtil.getDate();
		request.setAttribute("curDate", curDate);

		request.setAttribute("courseList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());
		return "home/main/list_course_ing_main";
	}

	/**
	 * 마이페이지 - 대기중인 과정
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/listCourseBefMain")
	public String listCourseBefMain(MainVO vo, Map commandMap, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		Integer curPage = Integer.parseInt(StringUtil.nvl(request.getParameter("curPage"),"1"));

		Map<String, Object> userInfo = new Hashtable<String, Object>();
		userInfo.put("userNo", UserBroker.getUserNo(request));
		userInfo.put("creYear", "");
		userInfo.put("enrlSts", "bef");

		ProcessResultListVO<UserCourseVO> resultList = createCourseService.listCreateCourseForStudent(userInfo, curPage);

		request.setAttribute("courseList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());
		return "home/main/list_course_bef_main";
	}

	/**
	 * 마이페이지 - 완료 과정
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/listCourseHisMain")
	public String listCourseHisMain(MainVO vo, Map commandMap, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		Integer curPage = Integer.parseInt(StringUtil.nvl(request.getParameter("curPage"),"1"));
		
		Map<String, Object> userInfo = new Hashtable<String, Object>();
		userInfo.put("userNo", UserBroker.getUserNo(request));
		userInfo.put("creYear", "");
		userInfo.put("enrlSts", "his");

		ProcessResultListVO<UserCourseVO> resultList = createCourseService.listCreateCourseForStudent(userInfo, curPage);

		request.setAttribute("courseList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());
		return "home/main/list_course_his_main";
	}	
	/**
     * @Method Name : goMenuPage
     * @Method 설명 : 권한 메뉴 연결
	 * @param MainVO 
	 * @param commandMap
	 * @param model
	 * @return url
	 * @throws Exception
	 */
	@RequestMapping(value="/goMenuPage")
	public String goMenuPage(MainVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
			
		String tplCd = UserBroker.getUserColorTpl(request);
		String orgCd = UserBroker.getUserOrgCd(request);
		String mcd = request.getParameter("mcd");
		String subParam = StringUtil.nvl(request.getParameter("subParam"));

		if("MC00000000".equals(mcd)) {
			//-- 임의의 메뉴 코드 (home 링크)
//			HttpSession session = request.getSession(); 
//			session.setAttribute(Constants.MENU_LOCATION, "");
//			session.setAttribute(Constants.CUR_MENU_NAME, "");
//			session.setAttribute(Constants.CUR_MENU_CODE, "");
//			session.setAttribute(Constants.CUR_MENU_TITLE, "");
//			session.setAttribute(Constants.CUR_MENU_CHRG_DEPT, "");
//			session.setAttribute(Constants.CUR_MENU_CHRG_NAME, "");
//			session.setAttribute(Constants.CUR_MENU_CHRG_PHONE, "");
//			session.setAttribute(Constants.ROT_MENU_CODE, "");
//			session.setAttribute(Constants.ROT_MENU_GROUP, "");
			
			request.getSession().setAttribute("MAIN_YN","Y");
			return "redirect:/home/main/indexMain";
		} else {
			request.getSession().setAttribute("MAIN_YN","N");
		}
		
		OrgMenuVO goMenuVO = null;			// 최종 결정된 타겟 메뉴위치.
		String curMenuUrl = "";

		try {
			goMenuVO = sysMenuMemService.decideHomeMenuWithSession(mcd, request);
			curMenuUrl = StringUtil.ReplaceAll(goMenuVO.getMenuUrl(), "+","%2B");	// URL에 +가 들어가면 인식을 못하는 문제를 위해 +를 %2B로 변경하여 전송

			String[] realPathArr = StringUtil.split(curMenuUrl, "?");
			curMenuUrl = realPathArr[0] + "?mcd=" + request.getSession().getAttribute(Constants.CUR_MENU_CODE);
			if(realPathArr.length > 1 ) {
				curMenuUrl += "&" + realPathArr[1];
			}
			
			
			//-- 서브 파라미터가 있는 경우 URL 대체함.
			if(!"".equals(subParam)) {
				String tarMenuUrl = StringUtil.split(goMenuVO.getMenuUrl(), "?")[0];
				//curMenuUrl = tarMenuUrl+"?cmd="+StringUtil.ReplaceAll(subParam, "@$","&");
				
				// 파라미터에 한글 깨짐 처리
				String[] subParamArr = subParam.split("@\\$");
				
				for (String param : subParamArr) {
					if (param.indexOf("=") < 0) {
						curMenuUrl = tarMenuUrl+"?mcd="+mcd+"&"+param;
					} else {
						String[] paramArr = param.split("=");
						if(curMenuUrl.indexOf("?") > -1) {
							curMenuUrl = curMenuUrl+"&"+paramArr[0]+"="+URLEncoder.encode(paramArr[1], "UTF-8");
						} else {
							curMenuUrl = curMenuUrl+"?mcd="+mcd+"&"+paramArr[0]+"="+URLEncoder.encode(paramArr[1], "UTF-8");
						}						
					}
				}
			}

			String requestUrl = request.getRequestURL().toString();
//			System.out.println("requestUrl=="+requestUrl);

			// 로그인 리다이렉트 시 메시지 세팅
			if(isLoginRedirect(mcd)) {
				String loginMsgCd = StringUtil.nvl(request.getParameter("loginMsgCd"),"");
				if("MSG01".equals(loginMsgCd)) {
					setAlertMessage(request, "해당 페이지는 강사 전용 로그인 페이지입니다.\\n\\n수강생 로그인 페이지로 이동합니다.");
				} else if("MSG02".equals(loginMsgCd)) {
					setAlertMessage(request, "해당 페이지는 수강생 전용 로그인 페이지입니다.\\n\\n강사 로그인 페이지로 이동합니다.");
				}
			}
			
			String ip = request.getRemoteAddr();
//			if(!ip.equals("127.0.0.1")){
//				if(WebUtil.isProduct(request)) {
//					if( requestUrl.indexOf("http://") >= 0 && "Y".equals(goMenuVO.getSslYn())) {
//						URLBuilder urlBuilder = new URLBuilder(requestUrl.replace("http://", "https://"));
//						HttpRequestUtil.requestParamToUrlBuilder(request, urlBuilder);
//						return "redirect:" + "https://data.gkedc.go.kr"+curMenuUrl;
//					}
//				}
//			}
//			
		} catch (Exception e) {
			return "redirect:/home/main/indexMain";
		}
		
		return "redirect:" + curMenuUrl;
	}
	
	private boolean isLoginRedirect(String mcd) {
		return "HM04001000".equals(mcd) || "MC00000003".equals(mcd);
	}
	
	/**
	 * TODO: 사이트맵 페이지가 추가되었을 경우 사용
     * @Method Name : siteMap
     * @Method 설명 : 사이트맵
	 * @param MainVO 
	 * @param commandMap
	 * @param model
	 * @return url
	 * @throws Exception
	 */
	@RequestMapping(value="/siteMap")
	public String siteMap(MainVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);

		//-- 권한별 메뉴를 가져와 폼에 심는다.
		String userType = StringUtil.nvl(UserBroker.getUserType(request),"GUEST");
		String orgCd = UserBroker.getUserOrgCd(request);

		OrgMenuVO orgMenuVO = sysMenuMemService.getOrgHomeMenuList(orgCd, userType);
		request.setAttribute("orgMenuVO", orgMenuVO);
		
		return "home/main/site_map";
	}
	
	/**
	 * TODO: 언어 설정 변경이 있을 경우 사용
     * @Method Name : indexChgLang
     * @Method 설명 : 언어 설정 변경
	 * @param MainVO 
	 * @param commandMap
	 * @param model
	 * @return url
	 * @throws Exception
	 */
	@RequestMapping(value="/indexChgLang")
	public String indexChgLang(MainVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		
		
		String localeKey = request.getParameter("langCd");
		HttpSession session = request.getSession();
		session.setAttribute(Constants.SYSTEM_LOCALEKEY, localeKey);

		return "redirect:/home/main/indexMain";
	}
	
	/**
	 * 과정 분류 목록
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/listCourseEnroll")
	public String listCourseEnroll(CourseVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request,  HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String returnUrl = "";
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		vo.setCreatedOnly("");
		
		List<CourseVO> courseList = courseService.listCourse(vo).getReturnList();
		
		request.setAttribute("courseList", courseList);
		returnUrl = "home/main/list_course_enroll_div";

		return returnUrl;
	}
	
}
