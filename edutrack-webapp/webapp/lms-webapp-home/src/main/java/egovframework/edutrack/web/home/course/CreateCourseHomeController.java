package egovframework.edutrack.web.home.course;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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

import egovframework.edutrack.comm.exception.MediopiaDefineException;
import egovframework.edutrack.comm.exception.ServiceProcessException;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.service.SysCodeMemService;
import egovframework.edutrack.comm.service.SysMenuMemService;
import egovframework.edutrack.comm.util.web.DateTimeUtil;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.URLBuilder;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.course.category.service.CourseCategoryService;
import egovframework.edutrack.modules.course.category.service.CourseCategoryVO;
import egovframework.edutrack.modules.course.contents.service.ContentsService;
import egovframework.edutrack.modules.course.contents.service.ContentsVO;
import egovframework.edutrack.modules.course.course.service.CourseService;
import egovframework.edutrack.modules.course.course.service.CourseVO;
import egovframework.edutrack.modules.course.createcourse.service.CreateCourseService;
import egovframework.edutrack.modules.course.createcourse.service.CreateCourseVO;
import egovframework.edutrack.modules.course.createcoursesubject.service.CreateCourseSubjectService;
import egovframework.edutrack.modules.course.createcoursesubject.service.CreateOnlineSubjectVO;
import egovframework.edutrack.modules.course.createcourseteacher.service.CreateCourseTeacherService;
import egovframework.edutrack.modules.course.createcourseteacher.service.TeacherVO;
import egovframework.edutrack.modules.course.createcoursetimetable.service.TimetableService;
import egovframework.edutrack.modules.course.createcoursetimetable.service.TimetableVO;
import egovframework.edutrack.modules.course.crstch.service.CrsTchService;
import egovframework.edutrack.modules.course.subject.service.OnlineSubjectVO;
import egovframework.edutrack.modules.course.subject.service.SubjectCategoryVO;
import egovframework.edutrack.modules.course.subject.service.SubjectService;
import egovframework.edutrack.modules.opencourse.course.service.OpenCrsService;
import egovframework.edutrack.modules.opencourse.course.service.OpenCrsVO;
import egovframework.edutrack.modules.org.menu.service.OrgMenuVO;
import egovframework.edutrack.modules.org.page.service.OrgPageVO;
import egovframework.edutrack.modules.student.result.service.EduResultService;
import egovframework.edutrack.modules.student.result.service.EduResultVO;
import egovframework.edutrack.modules.system.code.service.SysCodeVO;
import egovframework.edutrack.modules.system.config.service.SysCfgService;
import egovframework.edutrack.modules.system.config.service.SysCfgVO;
import egovframework.edutrack.modules.user.info.service.UsrUserInfoService;
import egovframework.edutrack.modules.user.info.service.UsrUserInfoVO;



/**
 * 개설 과정 엑션 컨트롤
 * @author JNOTE
 *
 */
@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/home/course")
public class CreateCourseHomeController extends GenericController {

	@Autowired @Qualifier("sysMenuMemService")
	private SysMenuMemService 				sysMenuMemService;

	@Autowired @Qualifier("courseService")
	private CourseService 					courseService;

	@Autowired @Qualifier("createCourseService")
	private CreateCourseService 			createCourseService;

	@Autowired @Qualifier("courseCategoryService")
	private CourseCategoryService 			categoryService;

	@Autowired @Qualifier("timetableService")
	private TimetableService 				timetableService;

	@Autowired @Qualifier("contentsService")
	private ContentsService 				contentsService;

	@Autowired @Qualifier("createCourseSubjectService")
	private CreateCourseSubjectService 	createCourseSubjectService;

	@Autowired @Qualifier("sysCodeMemService")
	private SysCodeMemService 				codeMemService;

	@Autowired @Qualifier("createCourseTeacherService")
	private CreateCourseTeacherService 	createCourseTeacherService;

	@Autowired @Qualifier("crsTchService")
	private CrsTchService 					crsTchService;

	@Autowired @Qualifier("sysCfgService")
	private SysCfgService					configService;

	@Autowired @Qualifier("openCrsService")
	private OpenCrsService 		openCrsService;

	@Autowired @Qualifier("eduResultService")
	private EduResultService		eduResultService;
	
	@Autowired @Qualifier("subjectService")
	private SubjectService	subjectService;
	
	@Autowired @Qualifier("usrUserInfoService")
	private UsrUserInfoService userInfoService;

	/**
	 * 개설 과정 리스트
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/listCourseMain")
	public String listCourseMain( CourseVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String returnUrl = "";

		String orgCd = UserBroker.getUserOrgCd(request);

		CourseCategoryVO courseCategoryVO = new CourseCategoryVO();
		List<CourseCategoryVO> crsCtgrList = categoryService.listCategorySub(orgCd, "").getReturnList();
		request.setAttribute("crsCtgrList", crsCtgrList);

		//-- 현재의 년도를 Attribute에 세팅한다.
		String curYear = DateTimeUtil.getYear();
		if(ValidationUtils.isEmpty(vo.getCreYear())){
			vo.setCreYear(Integer.parseInt(curYear));
		}
		vo.setOrgCd(orgCd);

		//-- 시작년도 가져오기
		SysCfgVO configVO = new SysCfgVO();
		configVO.setCfgCtgrCd("SYSTEM");
		configVO.setCfgCd("START_YEAR");
		configVO = configService.viewCfg(configVO);
		List<String> yearList = new ArrayList<String>();
		for(int i= Integer.parseInt(curYear,10)+1; i >= Integer.parseInt(configVO.getCfgVal(),10); i--) {
			yearList.add(Integer.toString(i));
		}
		//-- 현재의 년도를 Attribute에 세팅한다.
		request.setAttribute("yearList", yearList);
		
		// 기본 최신 들록순으로 정렬
		if(ValidationUtils.isEmpty(vo.getSortKey())) vo.setSortKey("REG_DTTM_DESC");
		
		CreateCourseVO cvo = new CreateCourseVO();
		cvo.setCurPage(vo.getCurPage());
		cvo.setListScale(vo.getListScale());
		cvo.setCreTypeCd(vo.getCrsOperMthd());
		cvo.setSortKey(vo.getSortKey());
		
		ProcessResultListVO<CreateCourseVO> createCourseList = createCourseService.listCreateCoursePageing(cvo, cvo.getCurPage(), cvo.getListScale(), true);
		List<CreateCourseVO >resultList = createCourseList.getReturnList();

		ProcessResultListVO<CourseVO> courseList = courseService.listForEnrollPaging(vo);
		request.setAttribute("courseList", courseList.getReturnList());
		request.setAttribute("createCourseList", resultList);
	   	request.setAttribute("pageInfo", courseList.getPageInfo());
	   	request.setAttribute("vo", vo);
	   	
	   	returnUrl = "home/course/createcourse/list_course_main";

		return returnUrl;
	}

	/**
	 * 과정 정보 보기, 개설 과정 목록 포함.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/readCourseMain")
	public String readCourseMain( CourseVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String returnUrl = "";
		String orgCd = UserBroker.getUserOrgCd(request);

		//-- 과정 정보를 가져온다.
		String curYear = DateTimeUtil.getYear();
		vo.setCreYear(Integer.parseInt(curYear));

		vo = courseService.view(vo).getReturnVO();

		//-- 코드를 가져와 수강대상을 셋팅한다.
		List<SysCodeVO> codeList = codeMemService.getSysCodeList("EDU_TARGET");
		String target = "";
		if(!"".equals(StringUtil.nvl(vo.getEduTarget()))) {
			String[] targetArray = StringUtil.split(vo.getEduTarget(),"|");
			for(int i=0; i < codeList.size(); i++) {
				SysCodeVO codeVO = codeList.get(i);
				for(int j=0; j < targetArray.length; j++) {
					if(targetArray[j].equals(codeVO.getCodeCd())) target = target+", "+codeVO.getCodeNm();
				}
			}
			target = StringUtil.substring(target, 2, target.length());
		}
		vo.setEduTargetNm(target);

		request.setAttribute("courseVO", vo);

		CreateCourseVO createCourseVO = new CreateCourseVO();
		createCourseVO.setCrsCd(vo.getCrsCd());
		createCourseVO.setSearchMode(vo.getSearchMode());
		createCourseVO.setCreYear(Integer.parseInt(curYear));
		createCourseVO.setRegNo(UserBroker.getUserNo(request));

		//-- 해당 년도의 개설 과정 목록을 가져온다.
		List<CreateCourseVO> createCourseList = createCourseService.listCreateCourseForEnroll(createCourseVO).getReturnList();

		request.setAttribute("createCourseList", createCourseList);

		//-- 등록된 과정이 있는 경우
		if(createCourseList.size() > 0) {
			CreateCourseVO ccVO = createCourseList.get(0);

			//-- 개설 과정 과목 정보를 가져온다.
			CreateOnlineSubjectVO onlineSubjectVO = new CreateOnlineSubjectVO();
			onlineSubjectVO.setCrsCreCd(ccVO.getCrsCreCd());
			List<CreateOnlineSubjectVO> onlineSubjectList = createCourseSubjectService.listOnlineSubject(onlineSubjectVO).getReturnList();
			for(CreateOnlineSubjectVO onlnSbjVO : onlineSubjectList) {
				ContentsVO contentsVO = new ContentsVO();
				contentsVO = contentsService.listContentsTree(onlnSbjVO.getSbjCd(), "");
				onlnSbjVO.setContentsVO(contentsVO);
			}
			request.setAttribute("onlineSubjectList", onlineSubjectList);

			//-- 시간표 정보를 가져온다.
			TimetableVO timetableVO = new TimetableVO();
			timetableVO.setCrsCreCd(ccVO.getCrsCreCd());
			List<TimetableVO> timetableList = timetableService.listTimetable(timetableVO).getReturnList();
			request.setAttribute("timetableList", timetableList);
		}

		//-- 교수자 정보를 가져온다.
		/*
		CrsTchVO crsTchVO = new CrsTchVO();
		crsTchVO.setCrsCd(courseVO.getCrsCd());
		crsTchVO.setTchType("TEACHER");
		*/
		TeacherVO teacherVO = new TeacherVO();
		teacherVO.setCrsCreCd(createCourseVO.getCrsCreCd());
		teacherVO.setTchType("TEACHER");
		List<TeacherVO> teacherList = createCourseTeacherService.listTeacher(teacherVO).getReturnList();
		request.setAttribute("teacherList", teacherList);
		request.setAttribute("createCourseVO", createCourseVO);
		
		returnUrl="home/course/createcourse/view_course_main";
		

		return returnUrl;
	}
	
	/**
	 * 회차 정보보기
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/readCourseCreateMain")
	public String readCourseCreateMain(CreateCourseVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String returnUrl = "";
		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);
				
		//회차정보
		List<CreateCourseVO> createCourseList = createCourseService.createCourseForEnroll(vo).getReturnList();
		request.setAttribute("createCourseList", createCourseList);
		
		//과정정보
		CourseVO cvo = new CourseVO();
		cvo.setCrsCd(vo.getCrsCd());
		request.setAttribute("courseVO", courseService.view(cvo).getReturnVO());
		
		//-- 과정 과목 정보를 가져온다.
		CreateOnlineSubjectVO onlineSubjectVO = new CreateOnlineSubjectVO();
		onlineSubjectVO.setCrsCreCd(vo.getCrsCreCd());
		List<CreateOnlineSubjectVO> onlineSubjectList = createCourseSubjectService.listOnlineSubject(onlineSubjectVO).getReturnList();
		for(CreateOnlineSubjectVO onlnSbjVO : onlineSubjectList) {
			ContentsVO contentsVO = new ContentsVO();
			contentsVO = contentsService.listContentsTree(onlnSbjVO.getSbjCd(), "");
			onlnSbjVO.setContentsVO(contentsVO);
		}
		request.setAttribute("onlineSubjectList", onlineSubjectList);

		//-- 시간표 정보를 가져온다.
		TimetableVO timetableVO = new TimetableVO();
		timetableVO.setCrsCreCd(vo.getCrsCreCd());
		List<TimetableVO> timetableList = timetableService.listTimetable(timetableVO).getReturnList();
		request.setAttribute("timetableList", timetableList);

		//-- 교수자 정보를 가져온다.
		/*
		CrsTchVO crsTchVO = new CrsTchVO();
		crsTchVO.setCrsCd(courseVO.getCrsCd());
		crsTchVO.setTchType("TEACHER");
		*/
		TeacherVO teacherVO = new TeacherVO();
		teacherVO.setCrsCreCd(vo.getCrsCreCd());
		teacherVO.setTchType("TEACHER");
		List<TeacherVO> teacherList = createCourseTeacherService.listTeacher(teacherVO).getReturnList();
		request.setAttribute("teacherList", teacherList);
		
		returnUrl="home/course/createcourse/view_course_main";
		

		return returnUrl;
	}
	
	/**
	 * 개설 정보 보기
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/readCoursePop")
	public String readCoursePop( CreateCourseVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String returnUrl = "";

		//-- 과정 개설 정보를 가져온다.
		vo = createCourseService.viewCreateCourse(vo).getReturnVO();
		request.setAttribute("createCourseVO", vo);

		//-- 과정 정보를 가져온다.
		CourseVO courseVO = new CourseVO();
		courseVO.setCrsCd(vo.getCrsCd());
		courseVO = courseService.view(courseVO).getReturnVO();

		//-- 코드를 가져와 수강대상을 셋팅한다.
		List<SysCodeVO> codeList = codeMemService.getSysCodeList("EDU_TARGET");
		String target = "";
		if(StringUtil.isNotNull(courseVO.getEduTarget())) {
			String[] targetArray = StringUtil.split(courseVO.getEduTarget(),"|");
			for(int i=0; i < codeList.size(); i++) {
				SysCodeVO codeVO = codeList.get(i);
				for(int j=0; j < targetArray.length; j++) {
					if(targetArray[j].equals(codeVO.getCodeCd())) target = target+", "+codeVO.getCodeNm();
				}
			}
			target = StringUtil.substring(target, 2, target.length());
			//courseVO.setEduTargetNm(target);
		}
		request.setAttribute("courseVO", courseVO);

		//-- 개설 과정 과목 정보를 가져온다.
		CreateOnlineSubjectVO onlineSubjectVO = new CreateOnlineSubjectVO();
		onlineSubjectVO.setCrsCreCd(vo.getCrsCreCd());
		List<CreateOnlineSubjectVO> onlineSubjectList = createCourseSubjectService.listOnlineSubject(onlineSubjectVO).getReturnList();
		for(CreateOnlineSubjectVO onlnSbjVO : onlineSubjectList) {
			ContentsVO contentsVO = new ContentsVO();
			contentsVO = contentsService.listContentsTree(onlnSbjVO.getSbjCd(), "");
			onlnSbjVO.setContentsVO(contentsVO);
		}
		request.setAttribute("onlineSubjectList", onlineSubjectList);

		//-- 시간표 정보를 가져온다.
		TimetableVO timetableVO = new TimetableVO();
		timetableVO.setCrsCreCd(vo.getCrsCreCd());
		List<TimetableVO> timetableList = timetableService.listTimetable(timetableVO).getReturnList();
		request.setAttribute("timetableList", timetableList);

		//-- 교수자 정보를 가져온다.
		/*
		CrsTchVO crsTchVO = new CrsTchVO();
		crsTchVO.setCrsCd(courseVO.getCrsCd());
		crsTchVO.setTchType("TEACHER");
		*/
		TeacherVO teacherVO = new TeacherVO();
		teacherVO.setCrsCreCd(vo.getCrsCreCd());
		teacherVO.setTchType("TEACHER");
		List<TeacherVO> teacherList = createCourseTeacherService.listTeacher(teacherVO).getReturnList();
		request.setAttribute("teacherList", teacherList);

		returnUrl="home/course/createcourse/view_course_pop";
		
		
		return returnUrl;
	}


	/**
	 * 월별 개설과정 목록 (칼렌더)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/listCalendarCourseMain")
	public String listCalendarCourse( CreateCourseVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String returnUrl = "";

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		if(ValidationUtils.isNotEmpty(vo.getMcd())) {
			OrgMenuVO menuVO = sysMenuMemService.decideHomeMenuWithSession(vo.getMcd(), request);
		}

		//-- 현재의 년도를 Attribute에 세팅한다. ("yyyymmdd 형태")
		String curYearMonth = StringUtil.substring(DateTimeUtil.getDate(),0,6);
		String yearMonth = StringUtil.nvl(vo.getYearMonth(), curYearMonth);
		String curDate = StringUtil.nvl(vo.getEnrlStartDttm(), yearMonth);

		//String yearMonth = "";


		//-- 이전달 / 다음달 만들기
		int year = Integer.parseInt(StringUtil.substring(yearMonth, 0, 4),10);
		String curYear = StringUtil.substring(yearMonth, 0, 4);
		int month = Integer.parseInt(StringUtil.substring(yearMonth, 4, 6),10);
		String curMonth = StringUtil.substring(yearMonth, 4, 6);
		int prevYear = year;
		int nextYear = year;
		int prevMonth = month -1;
		int nextMonth = month + 1;

		//-- 이전년도로 넘어갈때.
		if(prevMonth <= 0) {
			prevYear = year -1;
			prevMonth = 12;
		}

		//-- 다음 년도로 넘어갈때
		if(nextMonth > 12) {
			nextYear = year + 1;
			nextMonth = 1;
		}
		String strPrevMonth = "";
		String strNextMonth = "";
		if(prevMonth < 10) strPrevMonth = "0"+Integer.toString(prevMonth);
		else strPrevMonth = Integer.toString(prevMonth);

		if(nextMonth < 10) strNextMonth = "0"+Integer.toString(nextMonth);
		else strNextMonth = Integer.toString(nextMonth);

		String prevYearMonth = Integer.toString(prevYear)+strPrevMonth;
		String nextYearMonth = Integer.toString(nextYear)+strNextMonth;

		request.setAttribute("prevYearMonth", prevYearMonth);
		request.setAttribute("nextYearMonth", nextYearMonth);
		request.setAttribute("yearMonth", yearMonth);
		request.setAttribute("curYear", curYear);
		request.setAttribute("curMonth", curMonth);

		vo.setYearMonth(yearMonth);
		List<CreateCourseVO> calendarCourseList = createCourseService.calendarCreateCourseForMonth(vo).getReturnList();

		for (CreateCourseVO VO : calendarCourseList) {
			VO.setW0Str(dayStringParsingToList((String)VO.getW0Str()));
			VO.setW1Str(dayStringParsingToList((String)VO.getW1Str()));
			VO.setW2Str(dayStringParsingToList((String)VO.getW2Str()));
			VO.setW3Str(dayStringParsingToList((String)VO.getW3Str()));
			VO.setW4Str(dayStringParsingToList((String)VO.getW4Str()));
			VO.setW5Str(dayStringParsingToList((String)VO.getW5Str()));
			VO.setW6Str(dayStringParsingToList((String)VO.getW6Str()));
		}

		request.setAttribute("calendarList", calendarCourseList);

		//--해당 날짜의 개설 과정을 가져온다.
		vo.setEnrlStartDttm(curDate);
		vo.setRegNo(UserBroker.getUserNo(request));

		//-- 과정 목록
		List<CreateCourseVO> courseList = createCourseService.listCreateCourseForEnrollDate(vo).getReturnList();
		request.setAttribute("courseList", courseList);

		request.setAttribute("curDate", curDate);

		//-- 시작년도 가져오기
		SysCfgVO configVO = new SysCfgVO();
		configVO.setCfgCtgrCd("SYSTEM");
		configVO.setCfgCd("START_YEAR");
		configVO = configService.viewCfg(configVO);
		List<String> yearList = new ArrayList<String>();
		for(int i= Integer.parseInt(curYear,10)+1; i >= Integer.parseInt(configVO.getCfgVal(),10); i--) {
			yearList.add(Integer.toString(i));
		}
		//-- 현재의 년도를 Attribute에 세팅한다.
		request.setAttribute("yearList", yearList);

		//1월부터 12월까지 세팅한다.
		List<String> monthList = new ArrayList<String>();
		String strMonth = "";
		for(int i=1; i< 13; i++){
			if(i<10){
				strMonth = "0"+i;
			}else{
				strMonth = ""+i;
			}
			monthList.add(strMonth);
		}
		request.setAttribute("monthList", monthList);
		//시스템 등록년01월과 검색된 년월일 비교하여 이전달 링크여부 결정
		boolean isPreMonth = false;
		try{
			if(Integer.parseInt(yearMonth) == Integer.parseInt(configVO.getCfgVal()+"01")){
				isPreMonth = true;
			}
		}catch(NumberFormatException ne){
			isPreMonth = true;
		}
		request.setAttribute("isPreMonth", isPreMonth);
		
		returnUrl = "home/course/createcourse/calendar_course_main";

		return returnUrl;
	}

	private List<Object> dayStringParsingToList(String day) {
		List<Object> dayList = new ArrayList<Object>();

		if(StringUtil.isNull(day))
			return dayList;

		String[] items = StringUtil.split(day,"@$");
		for (String item : items) {
			String[] value = StringUtil.split(item,"|");
			Map<String, Object> map = new Hashtable<String, Object>();
			map.put("code", value[0]);
			map.put("value", value[1]);
			dayList.add(map);
		}
		return dayList;
	}


	/**
	 * 과정명 검색 과정 목록
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	/*@RequestMapping(value="/listSearchCourseMain")
	public String listSearchCourseMain( CreateCourseVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String returnUrl = "";

		if(ValidationUtils.isNotEmpty(vo.getMcd())) {
			OrgMenuVO menuVO = sysMenuMemService.decideHomeMenuWithSession(vo.getMcd(), request);
		}

		String orgCd = UserBroker.getUserOrgCd(request);

		String curYear = DateTimeUtil.getYear();
		vo.setCreYear(curYear);
		vo.setRegNo(UserBroker.getUserNo(request));
		vo.setOrgCd(orgCd);

		CourseCategoryVO courseCategoryVO = new CourseCategoryVO();
		List<CourseCategoryVO> crsCtgrList = categoryService.listCategorySub(orgCd, "").getReturnList();
		request.setAttribute("crsCtgrList", crsCtgrList);

		ProcessResultListVO<CreateCourseVO> courseList = createCourseService.listCreateCourseForEnrollSearchPaging(vo);
		request.setAttribute("courseList", courseList.getReturnList());
	   	request.setAttribute("pageInfo", courseList.getPageInfo());
	   	request.setAttribute("vo", vo);

	   	returnUrl = "home/course/createcourse/search_course_main";

		return returnUrl;
	}*/
	
	/**
	 * - HRD : [교육과정/신청>교육과정 및 신청] 메뉴 - 개설과정 리스트 조회
	 * @param vo
	 * @param commandMap
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	/*@RequestMapping(value="/listSearchCourseMain")
	public String listSearchCourseMain( CreateCourseVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String returnUrl = "";

		if(ValidationUtils.isNotEmpty(vo.getMcd())) {
			OrgMenuVO menuVO = sysMenuMemService.decideHomeMenuWithSession(vo.getMcd(), request);
		}
		
		//로그인여부
		String userNo = UserBroker.getUserNo(request);
		if(ValidationUtils.isEmpty(userNo)) {
			setAlertMessage(request, "로그인 후 이용바랍니다.");
		}

		String orgCd = UserBroker.getUserOrgCd(request);
		//String deptCd = UserBroker.getUserDeptCd(request);
		String deptCd = "";
		vo.setDeptCd("4");

		String curYear = DateTimeUtil.getYear();
		vo.setCreYear(curYear);
		vo.setRegNo(UserBroker.getUserNo(request));
		vo.setOrgCd(orgCd);

		//로그인한 수강생의 신창 가능한 기수
		
		vo.setSearchMode("ENROLL");
		ProcessResultListVO<CreateCourseVO> courseList = createCourseService.
(vo);
		request.setAttribute("courseList", courseList.getReturnList());
	   	request.setAttribute("pageInfo", courseList.getPageInfo());
	   	request.setAttribute("vo", vo);
	   	request.setAttribute("paging", "Y");

	   	returnUrl = "home/course/createcourse/search_course_main";

		return returnUrl;
	}*/
	

	/**
	 * 과정명 검색 과정 목록
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/listSearchCourseFullMain")
	public String listSearchCourseFullMain( CreateCourseVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String returnUrl = "";
		if(ValidationUtils.isNotEmpty(vo.getMcd())) {
			OrgMenuVO menuVO = sysMenuMemService.decideHomeMenuWithSession(vo.getMcd(), request);
		}

		String orgCd = UserBroker.getUserOrgCd(request);

		String curYear = DateTimeUtil.getYear();
		vo.setCreYear(Integer.parseInt(curYear));
		vo.setRegNo(UserBroker.getUserNo(request));
		vo.setOrgCd(orgCd);

		CourseCategoryVO courseCategoryVO = new CourseCategoryVO();
		List<CourseCategoryVO> crsCtgrList = categoryService.listCategorySub(orgCd, "").getReturnList();
		request.setAttribute("crsCtgrList", crsCtgrList);
		//vo.setListScale(5);
		if(ValidationUtils.isNotEmpty(vo.getSearchValue())) {
			ProcessResultListVO<CreateCourseVO> courseList = createCourseService.listCreateCourseForEnrollSearchPaging(vo);
			request.setAttribute("courseList", courseList.getReturnList());
			request.setAttribute("pageInfo", courseList.getPageInfo());
		}
	   	request.setAttribute("vo", vo);

		OpenCrsVO openCrsVO = new OpenCrsVO();
		openCrsVO.setOrgCd(orgCd);
		openCrsVO.setSearchValue(vo.getSearchValue());
		ProcessResultListVO<OpenCrsVO> openCourseList = openCrsService.listCourse(openCrsVO);
		request.setAttribute("openCourseList", openCourseList.getReturnList());

	   	returnUrl = "home/course/createcourse/search_course_full_main";

		return returnUrl;
	}

	/**
	 * 메인페이지 - 이달의교육과정
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/indexCalendarDiv")
	public String indexCalendarDiv(CreateCourseVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		if(ValidationUtils.isNotEmpty(vo.getMcd())) {
			OrgMenuVO menuVO = sysMenuMemService.decideHomeMenuWithSession(vo.getMcd(), request);
		}

		//-- 현재의 년도를 Attribute에 세팅한다. ("yyyymmdd 형태")
		String curYearMonth = StringUtil.substring(DateTimeUtil.getDate(),0,6);
		String yearMonth = StringUtil.nvl(vo.getYearMonth(), curYearMonth);
		String curDate = StringUtil.nvl(vo.getEnrlStartDttm(), StringUtil.substring(DateTimeUtil.getDate(), 0, 8));

		//String yearMonth = "";


		//-- 이전달 / 다음달 만들기
		int year = Integer.parseInt(StringUtil.substring(yearMonth, 0, 4),10);
		String curYear = StringUtil.substring(yearMonth, 0, 4);
		int month = Integer.parseInt(StringUtil.substring(yearMonth, 4, 6),10);
		String curMonth = StringUtil.substring(yearMonth, 4, 6);
		int prevYear = year;
		int nextYear = year;
		int prevMonth = month -1;
		int nextMonth = month + 1;

		//-- 이전년도로 넘어갈때.
		if(prevMonth <= 0) {
			prevYear = year -1;
			prevMonth = 12;
		}

		//-- 다음 년도로 넘어갈때
		if(nextMonth > 12) {
			nextYear = year + 1;
			nextMonth = 1;
		}
		String strPrevMonth = "";
		String strNextMonth = "";
		if(prevMonth < 10) strPrevMonth = "0"+Integer.toString(prevMonth);
		else strPrevMonth = Integer.toString(prevMonth);

		if(nextMonth < 10) strNextMonth = "0"+Integer.toString(nextMonth);
		else strNextMonth = Integer.toString(nextMonth);

		String prevYearMonth = Integer.toString(prevYear)+strPrevMonth;
		String nextYearMonth = Integer.toString(nextYear)+strNextMonth;

		request.setAttribute("prevYearMonth", prevYearMonth);
		request.setAttribute("nextYearMonth", nextYearMonth);
		request.setAttribute("yearMonth", yearMonth);
		request.setAttribute("curYear", curYear);
		request.setAttribute("curMonth", curMonth);

		vo.setYearMonth(yearMonth);
		List<CreateCourseVO> calendarCourseList = createCourseService.calendarCreateCourseForMonth(vo).getReturnList();

		for (CreateCourseVO VO : calendarCourseList) {
			VO.setW0Str(dayStringParsingToList((String)VO.getW0Str()));
			VO.setW1Str(dayStringParsingToList((String)VO.getW1Str()));
			VO.setW2Str(dayStringParsingToList((String)VO.getW2Str()));
			VO.setW3Str(dayStringParsingToList((String)VO.getW3Str()));
			VO.setW4Str(dayStringParsingToList((String)VO.getW4Str()));
			VO.setW5Str(dayStringParsingToList((String)VO.getW5Str()));
			VO.setW6Str(dayStringParsingToList((String)VO.getW6Str()));
		}

		request.setAttribute("calendarList", calendarCourseList);
		request.setAttribute("curDate", curDate);

		try {
			SysCfgVO cfgVO = new SysCfgVO();
			cfgVO.setCfgCtgrCd("MENUNO");
			cfgVO.setCfgCd("CRSSCHE");
			cfgVO = configService.viewCfg(cfgVO);
			request.setAttribute("crsCalendarMcd", cfgVO.getCfgVal());
		} catch (Exception e) {
			request.setAttribute("crsCalendarMcd", "");
		}

		return "home/course/createcourse/calendar_div";
	}

	/**
	 * 마이페이지 - 진행중인 과정 (학습자) - 과정 Todo리스트
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/listCourseIngTodoList")
	public String listCourseIngTodoList(CreateCourseVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);


		String userNo = UserBroker.getUserNo(request);
		vo.setUserNo(userNo);

		ProcessResultListVO<CreateCourseVO> resultList = createCourseService.listCreateCourseTodoList(vo);

		request.setAttribute("courseList", resultList.getReturnList());
		return "home/course/createcourse/list_course_todo";
	}

	/**
	 * 마이페이지 - 진행중인 과정 (학습자) - 과정 Todo리스트
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public String listCourseHisScore(CreateCourseVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);


		String userNo = UserBroker.getUserNo(request);
		String stdNo = vo.getStdNo();
		vo.setUserNo(userNo);

		vo = createCourseService.viewCreateCourse(vo).getReturnVO();
		request.setAttribute("createCourseVO", vo);

		EduResultVO eduResultVO = new EduResultVO();
		eduResultVO.setCrsCreCd(vo.getCrsCreCd());
		eduResultVO.setStdNo(stdNo);

		try {
			eduResultVO = eduResultService.viewResult(eduResultVO).getReturnVO();
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		request.setAttribute("eduResultVO", eduResultVO);

		return "home/course/createcourse/list_his_score";
	}


	/**
	 * 개설 정보 보기 - 수강신청용
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/enrollCourseViewMain")
	public String enrollCourseViewMain(CreateCourseVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		commonVOProcessing(vo, request);

		//-- 과정 개설 정보를 가져온다.
		vo = createCourseService.viewCreateCourse(vo).getReturnVO();
		request.setAttribute("createCourseVO", vo);

		//-- 과정 정보를 가져온다.
		CourseVO courseVO = new CourseVO();
		courseVO.setCrsCd(vo.getCrsCd());
		courseVO = courseService.view(courseVO).getReturnVO();

		//-- 코드를 가져와 수강대상을 셋팅한다.
		List<SysCodeVO> codeList = codeMemService.getSysCodeList("EDU_TARGET");
		String target = "";
		if(StringUtil.isNotNull(courseVO.getEduTarget())) {
			String[] targetArray = StringUtil.split(courseVO.getEduTarget(),"|");
			for(int i=0; i < codeList.size(); i++) {
				SysCodeVO codeVO = codeList.get(i);
				for(int j=0; j < targetArray.length; j++) {
					if(targetArray[j].equals(codeVO.getCodeCd())) target = target+", "+codeVO.getCodeNm();
				}
			}
			target = StringUtil.substring(target, 2, target.length());
			//courseVO.setEduTargetNm(target);
		}
		request.setAttribute("courseVO", courseVO);

		//-- 개설 과정 과목 정보를 가져온다.
		CreateOnlineSubjectVO onlineSubjectVO = new CreateOnlineSubjectVO();
		onlineSubjectVO.setCrsCreCd(vo.getCrsCreCd());
		List<CreateOnlineSubjectVO> onlineSubjectList = createCourseSubjectService.listOnlineSubject(onlineSubjectVO).getReturnList();
		for(CreateOnlineSubjectVO onlnSbjVO : onlineSubjectList) {
			ContentsVO contentsVO = new ContentsVO();
			contentsVO = contentsService.listContentsTree(onlnSbjVO.getSbjCd(), "");
			onlnSbjVO.setContentsVO(contentsVO);
		}
		request.setAttribute("onlineSubjectList", onlineSubjectList);

		//-- 시간표 정보를 가져온다.
		TimetableVO timetableVO = new TimetableVO();
		timetableVO.setCrsCreCd(vo.getCrsCreCd());
		List<TimetableVO> timetableList = timetableService.listTimetable(timetableVO).getReturnList();
		request.setAttribute("timetableList", timetableList);

		//-- 교수자 정보를 가져온다.
		TeacherVO teacherVO = new TeacherVO();
		teacherVO.setCrsCreCd(vo.getCrsCreCd());
		teacherVO.setTchType("TEACHER");
		List<TeacherVO> teacherList = createCourseTeacherService.listTeacher(teacherVO).getReturnList();
		request.setAttribute("teacherList", teacherList);

		return "home/course/createcourse/view_course_enroll_main";
	}
	
	/**
	 * [HRD]  [교육과정 > 교육과정안내] 메뉴 - sbj 기준 과정 리스트 조회 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/listOnineSbjMain")
	public String listSbjMain( OnlineSubjectVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String returnUrl = "";

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setUseYn("");
		vo.setOrgCd(orgCd);

		SubjectCategoryVO subjectCategoryVO = new SubjectCategoryVO();
		
		ProcessResultListVO<SubjectCategoryVO> sbjCtgrList = subjectService.listCategory(subjectCategoryVO);
		
		request.setAttribute("sbjCtgrList", sbjCtgrList);

		vo.setOrgCd(orgCd);

		ProcessResultListVO<OnlineSubjectVO> onSbjList = subjectService.listOnlinePageing(vo, vo.getCurPage(), vo.getListScale());
		request.setAttribute("onSbjList", onSbjList.getReturnList());
	   	request.setAttribute("pageInfo", onSbjList.getPageInfo());
	   	request.setAttribute("vo", vo);
	   	
	   	returnUrl = "home/course/createcourse/list_online_subject";

		return returnUrl;
	}
	
	/**
	 * [HRD] [교육과정/신청>교육과정 및 신청] 메뉴 - 개설과정 리스트 메인
	 * @param vo
	 * @param commandMap
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/listCourseEnrollMain")
	public String listCourseEnrollMain( CreateCourseVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String returnUrl = "";

		if(ValidationUtils.isNotEmpty(vo.getMcd())) {
			OrgMenuVO menuVO = sysMenuMemService.decideHomeMenuWithSession(vo.getMcd(), request);
		}
		
		List<CourseVO> returnCourseList = new ArrayList<>();
		
		try {
			//로그인여부
			String userNo = UserBroker.getUserNo(request);
			if(ValidationUtils.isEmpty(userNo)) {
				throw new ServiceProcessException("로그인 후 이용바랍니다.");
			}
			
			UsrUserInfoVO userInfoVO = new UsrUserInfoVO();
			userInfoVO.setUserNo(userNo);
			userInfoVO = userInfoService.view(userInfoVO);
			
			/*
			 * String deptCd = StringUtil.nvl(userInfoVO.getDeptCd());
			 * 
			 * if(ValidationUtils.isEmpty(deptCd)) { throw new
			 * ServiceProcessException("회원 정보에서 기업정보를 입력바랍니다."); }
			 */

			//신청기간에 해당하는 기수 조회
			CourseVO paramCourseVO = new CourseVO();
			//paramCourseVO.setDeptCd(deptCd);
			returnCourseList = courseService.listForEnroll(paramCourseVO).getReturnList();
			
			if(returnCourseList != null && returnCourseList.size() == 0) {
				throw new ServiceProcessException("교육과정 신청기간이 아닙니다.");
			}
		} catch (MediopiaDefineException e1) {
			setAlertMessage(request, e1.getMessage());
			return "redirect:"+ new URLBuilder("/home/main/indexMain").toString();
		} catch (Exception e) {
			log.error("[교육과정 및 신청 메인 조회 오류]");
			setAlertMessage(request, "조회 도중 오류가 발생하였습니다. 다시 시도바랍니다.");
			return "redirect:"+ new URLBuilder("/home/main/indexMain").toString();
		}
		
		request.setAttribute("courseList", returnCourseList); 
		request.setAttribute("vo", vo);
	   	request.setAttribute("paging", "Y");

	   	returnUrl = "home/course/createcourse/list_course_enroll_main";

		return returnUrl;
	}
	
	/**
	 * [HRD] [교육과정/신청>교육과정 및 신청] 메뉴 - 개설과정 리스트 조회 (load)
	 * @param vo
	 * @param commandMap
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/listCourseEnroll")
	public String listCourseEnroll( CreateCourseVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String returnUrl = "";

		if(ValidationUtils.isNotEmpty(vo.getMcd())) {
			OrgMenuVO menuVO = sysMenuMemService.decideHomeMenuWithSession(vo.getMcd(), request);
		}
		
		try {
			//로그인여부
			String userNo = UserBroker.getUserNo(request);
			if(ValidationUtils.isEmpty(userNo)) {
				throw new ServiceProcessException("로그인 후 이용바랍니다.");
			}
			
			//회원정보 조회(기업정보 조회)
			UsrUserInfoVO userInfoVO = new UsrUserInfoVO();
			userInfoVO.setUserNo(userNo);
			userInfoVO = userInfoService.view(userInfoVO);
			model.addAttribute("userInfoVO", userInfoVO);
			
			/*
			 * String deptCd = StringUtil.nvl(userInfoVO.getDeptCd()); vo.setDeptCd(deptCd);
			 * vo.setUserNo(userNo);
			 * 
			 * if(ValidationUtils.isEmpty(deptCd)) { throw new
			 * ServiceProcessException("회원 정보에서 기업정보를 입력바랍니다."); } vo.setDeptCd(deptCd);
			 */
			/*if(ValidationUtils.isEmpty(vo.getCrsCd())) {
				setAlertMessage(request, "기수를 선택바랍니다.");
				return "redirect:"+ new URLBuilder("/home/course/listCourseEnrollMain").toString();
			}*/
		} catch (MediopiaDefineException e1) {
			setAlertMessage(request, e1.getMessage());
			return "redirect:"+ new URLBuilder("/home/main/indexMain").toString();
		} catch (Exception e) {
			log.error("[교육과정 및 신청 리스트 조회 오류]");
			setAlertMessage(request, "조회 도중 오류가 발생하였습니다. 다시 시도바랍니다.");
			return "redirect:"+ new URLBuilder("/home/main/indexMain").toString();
		}
		
		String orgCd = UserBroker.getUserOrgCd(request);
		String curYear = DateTimeUtil.getYear();
		vo.setCreYear(Integer.parseInt(curYear));
		vo.setRegNo(UserBroker.getUserNo(request));
		vo.setOrgCd(orgCd);

		vo.setSearchMode("ENROLL");
		ProcessResultListVO<CreateCourseVO> courseList = createCourseService.listCreateCourseForEnrollSearchBySubjectPaging(vo,true);
		request.setAttribute("courseList", courseList.getReturnList());
	   	request.setAttribute("pageInfo", courseList.getPageInfo());
	   	request.setAttribute("vo", vo);
	   	request.setAttribute("paging", "Y");

	   	returnUrl = "home/course/createcourse/list_course_enroll_div";

		return returnUrl;
	}
	
	/**
	 * [HRD] [수강신청결제>강의계획서확인/동의] - 강의정보 조회 (load)
	 * @param vo
	 * @param commandMap
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/viewCourseEnrollInfo")
	public String viewCourseInfo(CreateCourseVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		commonVOProcessing(vo, request);
		
		String CrsCreCd = StringUtil.nvl(vo.getCrsCreCd());
		
		//조회
		CreateCourseVO resultVO =  createCourseService.viewCreateCourseForEnroll(vo).getReturnVO();
		model.addAttribute("createCourseVO", resultVO);
		return "home/course/createcourse/view_course_enroll_info_div";
	}
	
}