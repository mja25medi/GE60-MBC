package egovframework.edutrack.web.lecture.main;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
import egovframework.edutrack.comm.util.web.DateTimeUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.URLBuilder;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.board.bbs.service.BrdBbsAtclService;
import egovframework.edutrack.modules.board.bbs.service.BrdBbsAtclVO;
import egovframework.edutrack.modules.board.popup.service.BrdPopupNoticeService;
import egovframework.edutrack.modules.course.attendance.service.AttendanceService;
import egovframework.edutrack.modules.course.attendance.service.AttendanceVO;
import egovframework.edutrack.modules.course.contents.service.ContentsService;
import egovframework.edutrack.modules.course.course.service.CourseService;
import egovframework.edutrack.modules.course.course.service.CourseVO;
import egovframework.edutrack.modules.course.creCrsResh.service.CreCrsReshService;
import egovframework.edutrack.modules.course.createcourse.service.CreateCourseService;
import egovframework.edutrack.modules.course.createcourse.service.CreateCourseVO;
import egovframework.edutrack.modules.course.createcoursesubject.service.CreateCourseSubjectService;
import egovframework.edutrack.modules.course.createcourseteacher.service.CreateCourseTeacherService;
import egovframework.edutrack.modules.course.createcourseteacher.service.TeacherVO;
import egovframework.edutrack.modules.course.crsbbs.atcl.service.CrsBbsAtclService;
import egovframework.edutrack.modules.course.crstch.service.CrsTchService;
import egovframework.edutrack.modules.course.subject.service.SubjectService;
import egovframework.edutrack.modules.lecture.assignment.service.AssignmentService;
import egovframework.edutrack.modules.lecture.assignment.service.AssignmentVO;
import egovframework.edutrack.modules.lecture.bbs.service.LecBbsAtclVO;
import egovframework.edutrack.modules.lecture.bbs.service.LecBbsService;
import egovframework.edutrack.modules.lecture.exam.service.ExamService;
import egovframework.edutrack.modules.lecture.exam.service.ExamVO;
import egovframework.edutrack.modules.lecture.forum.service.ForumService;
import egovframework.edutrack.modules.lecture.main.service.MainLectureService;
import egovframework.edutrack.modules.lecture.main.service.MainLectureVO;
import egovframework.edutrack.modules.log.classconn.service.LogClassConnService;
import egovframework.edutrack.modules.log.classconn.service.LogClassConnVO;
import egovframework.edutrack.modules.log.message.service.LogMsgLogService;
import egovframework.edutrack.modules.log.message.service.LogMsgLogVO;
import egovframework.edutrack.modules.main.service.MainVO;
import egovframework.edutrack.modules.org.info.service.OrgOrgInfoService;
import egovframework.edutrack.modules.student.student.service.StudentService;
import egovframework.edutrack.modules.student.student.service.StudentVO;
import egovframework.edutrack.modules.system.config.service.SysCfgService;
import egovframework.edutrack.modules.system.menu.service.SysMenuLangVO;
import egovframework.edutrack.modules.system.menu.service.SysMenuService;
import egovframework.edutrack.modules.system.menu.service.SysMenuVO;
import egovframework.edutrack.modules.user.info.service.UsrUserInfoService;
import egovframework.edutrack.modules.user.info.service.UsrUserInfoVO;


/**
 * 강의실 메인 컨트롤
 * @author JNOTE
 */
@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/lec/main")
public class MainLectureController
		extends GenericController {

	@Autowired @Qualifier("sysMenuService")
	private SysMenuService				menuService;

	@Autowired @Qualifier("createCourseService")
	private CreateCourseService		createCourseService;

	@Autowired @Qualifier("contentsService")
	private ContentsService			contentsService;

	@Autowired @Qualifier("createCourseSubjectService")
	private CreateCourseSubjectService	createCourseSubjectService;

	@Autowired @Qualifier("sysCodeMemService")
	private SysCodeMemService				codeMemService;

	@Autowired @Qualifier("courseService")
	private CourseService				courseService;

	@Autowired @Qualifier("createCourseTeacherService")
	private CreateCourseTeacherService	teacherService;

	@Autowired @Qualifier("studentService")
	private StudentService				studentService;

//	@Autowired
//	private TimetableService			timetableService;

	@Autowired @Qualifier("lecBbsService")
	private LecBbsService				lecBbsService;

	@Autowired @Qualifier("assignmentService")
	private AssignmentService			assignmentService;

	@Autowired @Qualifier("examService")
	private ExamService				examService;

	@Autowired @Qualifier("forumService")
	private ForumService				forumService;

//	@Autowired
//	private ResearchService			researchService;

	@Autowired @Qualifier("creCrsReshService")
	private CreCrsReshService			creCrsReshService;

//	@Autowired
//	private CertificateService			certificateService;

	@Autowired @Qualifier("brdPopupNoticeService")
	private BrdPopupNoticeService			brdPopupNoticeService;

	@Autowired @Qualifier("sysCfgService")
	private SysCfgService				configService;

	@Autowired @Qualifier("usrUserInfoService")
	private UsrUserInfoService			userInfoService;

	@Autowired @Qualifier("orgOrgInfoService")
	private OrgOrgInfoService				orgInfoService;

	@Autowired @Qualifier("crsBbsAtclService")
	private CrsBbsAtclService			crsBbsAtclService;

	@Autowired @Qualifier("subjectService")
	private SubjectService 			subjectService;

	@Autowired @Qualifier("crsTchService")
	private CrsTchService 				crsTchService;

	@Autowired @Qualifier("mainLectureService")
	private MainLectureService			mainLectureService;

	@Autowired @Qualifier("logClassConnService")
	private LogClassConnService		logClassConnService;

	@Autowired @Qualifier("createCourseTeacherService")
	private CreateCourseTeacherService 	createCourseTeacherService;

	@Autowired @Qualifier("brdBbsAtclService")
	private BrdBbsAtclService brdBbsAtclService;

	@Autowired @Qualifier("logMsgLogService")
	private LogMsgLogService 	logMsgLogService;
	
	@Autowired @Qualifier("attendanceService")
	private AttendanceService	attendanceService;
	/**
	 * 강의실 메인 화면
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/lectureMain")
	public String lectureMain(Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String orgCd = UserBroker.getUserOrgCd(request);	// 기관 코드 조회
		String classUserType = UserBroker.getClassUserType(request);
		String crsCreCd = UserBroker.getCreCrsCd(request);
		String stdNo = UserBroker.getStudentNo(request);

		String retUrl = "";
		
		if(ValidationUtils.isEmpty(crsCreCd)) {
			setAlertMessage(request, getMessage(request, "course.message.createcourse.studying.nodata"));
			retUrl = "/home/main/indexMain";
			return "redirect:"+retUrl;
		}
		
		//-- 개설 과정의 정보를 가져온다.
		CreateCourseVO createCourseVO = new CreateCourseVO();
		createCourseVO.setCrsCreCd(crsCreCd);
		createCourseVO.setStdNo(stdNo);
		createCourseVO = createCourseService.viewCreateCourse(createCourseVO).getReturnVO();
		
		if(ValidationUtils.isEmpty(createCourseVO)) {
			setAlertMessage(request, getMessage(request, "course.message.createcourse.studying.nodata"));
			retUrl = "/home/main/indexMain";
			return "redirect:"+retUrl;
		}
		request.setAttribute("createCourseVO", createCourseVO);

		//-- 과정의 정보를 가져온다.
		CourseVO courseVO = new CourseVO();
		courseVO.setCrsCd(createCourseVO.getCrsCd());
		courseVO = courseService.view(courseVO).getReturnVO();
		request.setAttribute("courseVO", courseVO);

		//-- 사용자의 정보를 가져온다.
		UsrUserInfoVO userInfoVO = new UsrUserInfoVO();
		userInfoVO.setUserNo(UserBroker.getUserNo(request));
		userInfoVO = userInfoService.view(userInfoVO);
		request.setAttribute("userInfoVO", userInfoVO);


		//-- 공지사항 TOP5 가져오기 
		BrdBbsAtclVO bbsAtclVO = new BrdBbsAtclVO();
		bbsAtclVO.setOrgCd(orgCd); bbsAtclVO.setTopCnt(5);
		bbsAtclVO.setBbsCd("NOTICE");
		ProcessResultListVO<BrdBbsAtclVO> noticeList = brdBbsAtclService.listTopAtcl(bbsAtclVO);
		request.setAttribute("noticeList",noticeList.getReturnList());
 
		//---- 과정 공지사항 뿌려주기 
		LecBbsAtclVO lecBbsAtclVO = new LecBbsAtclVO();
		lecBbsAtclVO.setCrsCreCd(crsCreCd); lecBbsAtclVO.setBbsCd("NOTICE");
		lecBbsAtclVO.setCurPage(1); lecBbsAtclVO.setListScale(5);
		lecBbsAtclVO.setPageScale(10);
		List<LecBbsAtclVO> creNoticeList = lecBbsService.listAtclPageing(lecBbsAtclVO).getReturnList();
		request.setAttribute("creNoticeList", creNoticeList);

		//---- 과정 자료실 뿌려주기 
		lecBbsAtclVO.setBbsCd("PDS"); 
		List<LecBbsAtclVO> pdsList = lecBbsService.listAtclPageing(lecBbsAtclVO).getReturnList();
		request.setAttribute("pdsList", pdsList);


		LogMsgLogVO lmlVO = new LogMsgLogVO();
		lmlVO.setRegNo(UserBroker.getUserNo(request));
		lmlVO.setTopCnt(5);
		ProcessResultListVO<LogMsgLogVO> msgList = logMsgLogService.listTopRecvMsg(lmlVO);
		request.setAttribute("msgList", msgList.getReturnList());
		
		MainLectureVO mainLectureVO = new MainLectureVO();
		mainLectureVO.setCrsCreCd(crsCreCd);
		mainLectureVO.setClassUserType(classUserType);
		mainLectureVO.setStdNo(UserBroker.getStudentNo(request));
		
		AttendanceVO avo = new AttendanceVO();
		avo.setCrsCreCd(createCourseVO.getCrsCreCd());
		avo.setUserNo(userInfoVO.getUserNo());
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String today = formatter.format(DateTimeUtil.getCurrentDate());
		avo.setAttendDttm(today);
		avo = attendanceService.viewAttend(avo).getReturnVO();
		request.setAttribute("attendanceVO", avo);
		
		
		if("STU".equals(classUserType)) {
				mainLectureVO = mainLectureService.viewCreateCourseSchedule(mainLectureVO).getReturnVO();
				//권장 진도율
				if (createCourseVO.getCrsOperMthd().equals("OF")) {
				float termDayCnt = mainLectureVO.getTermDayCnt();
				float nowDayCnt = mainLectureVO.getNowDayCnt();
				
				int prpsRatio = Math.round((nowDayCnt/termDayCnt)*100);
				request.setAttribute("prpsRatio", Math.min(100, prpsRatio));
			}
			
			String nowDate = DateTimeUtil.getDateType(1, DateTimeUtil.getDate(),".");
			request.setAttribute("nowDate", nowDate);
			
		}else {
			mainLectureVO = mainLectureService.view(mainLectureVO).getReturnVO(); 
		}
		
		if("TCH".equals(classUserType)) {
			retUrl = "home/main/lecture_teacher_main";
		} else {
			boolean isOpen = true;
			boolean nowScoreOpen = false;
			if(!StringUtil.nvl(courseVO.getScoreOpenStartDttm()).equals("") && !StringUtil.nvl(courseVO.getScoreOpenEndDttm()).equals(""))
				nowScoreOpen = DateTimeUtil.chkTimeNowBetween(courseVO.getScoreOpenStartDttm(), courseVO.getScoreOpenEndDttm()); 
			if(!nowScoreOpen) {
				isOpen = false;
				mainLectureVO.hideScore();
			}
			request.setAttribute("isOpen", isOpen);
			retUrl = "home/main/lecture_student_main";
		}
		
		request.setAttribute("mainLectureVO", mainLectureVO);
		//request.setAttribute("prpsRatio", Math.min(100, (mainLectureVO.getNowDayCnt()/mainLectureVO.getTermDayCnt())*100));
				
		return retUrl;
	}

	/**
	 * 강의실 들어가기
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/goLecture")
	public String goLecture(Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		String crsCreCd = StringUtil.nvl(request.getParameter("crsCreCd"));
		String stdNo = StringUtil.nvl(request.getParameter("stdNo"));

		String lecMenuNo = configService.getValue("MENUNO", "LECMAIN");

		String userNo = UserBroker.getUserNo(request);
		String curMenuNo = UserBroker.getMenuCode(request);

		CreateCourseVO createCourseVO = new CreateCourseVO();
		createCourseVO.setCrsCreCd(crsCreCd);
		createCourseVO = createCourseService.viewCreateCourse(createCourseVO).getReturnVO();

		//-- 사용자의 정보를 구한다.
		UsrUserInfoVO userInfoVO = new UsrUserInfoVO();
		userInfoVO.setUserNo(userNo);
		userInfoVO = userInfoService.view(userInfoVO);

		CourseVO courseVO = new CourseVO();
		courseVO.setCrsCd(createCourseVO.getCrsCd());
		courseVO = courseService.view(courseVO).getReturnVO();

		request.getSession().setAttribute(Constants.LOGIN_CRSCRECD, crsCreCd);
		request.getSession().setAttribute(Constants.LOGIN_CRSCRENM, createCourseVO.getCrsCreNm());
		request.getSession().setAttribute(Constants.LOGIN_SBJCD, createCourseVO.getSbjCd());
		request.getSession().setAttribute(Constants.LOGIN_CREYEAR, createCourseVO.getCreYear());
		request.getSession().setAttribute(Constants.LOGIN_CRETERM, createCourseVO.getCreTerm());
		request.getSession().setAttribute(Constants.LOGIN_CRSMTHD, courseVO.getCrsOperMthd());
		request.getSession().setAttribute(Constants.LOGIN_CRSTYPE, courseVO.getCrsOperType());

		//-- 강의실 접속 로그 등록
		LogClassConnVO cclVO = new LogClassConnVO();
		cclVO.setOrgCd(UserBroker.getUserOrgCd(request));
		cclVO.setCrsCreCd(crsCreCd);
		cclVO.setUserNo(userNo);
		logClassConnService.addConnLog(cclVO);

		if("".equals(stdNo)) {
			//-- 과목의 강사자인 경우
			TeacherVO teacherVO = new TeacherVO();
			teacherVO.setCrsCreCd(crsCreCd);
			teacherVO.setUserNo(userNo);
			teacherVO = teacherService.isTeacher(teacherVO).getReturnVO();

			//-- 학습기간 설정
			if("S".equals(courseVO.getCrsOperType())) {
				request.getSession().setAttribute(Constants.LOGIN_CRSDURATION, "상시과정");
			} else {
				request.getSession().setAttribute(Constants.LOGIN_CRSDURATION, createCourseVO.getEnrlStartDttm()+"~"+createCourseVO.getEnrlEndDttm());
			}

			if("Y".equals(teacherVO.getTchYn())) {
				request.getSession().setAttribute(Constants.LOGIN_CLASSUSERTYPE, "TCH");

				request.getSession().setAttribute(Constants.LOGIN_STUDENTNO, crsCreCd+"-"+userNo);
				return "redirect:"+ new URLBuilder("/lec/main/goMenuPage")
						.addParameter("mcd", lecMenuNo)
						.toString();
			} else {
				return "redirect:"+ new URLBuilder("/home/main/goMenuPage")
						.addParameter("mcd", curMenuNo)
						.toString(); 
			}
		} else {
			//-- 과정의 수강생인 경우
			StudentVO studentVO = new StudentVO();
			studentVO.setCrsCreCd(crsCreCd);
			studentVO.setStdNo(stdNo);
			studentVO = studentService.isStudent(studentVO).getReturnVO();

			// 본인 인증이 필요한 과정이고 본인 인증정보가 세션에 없으면 본인확인 페이지를 표시
			/*
			if(courseVO.getPrsnCertYn().equals("Y") && !certificateService.isSelfCheck(request)) {
				// 인증 관련 처리 완료후 돌아올 페이지 URL을 설정
				request.setAttribute("goUrl", HttpRequestUtil.requestToUrlBuilder(request).toString());
				return forward(new URLBuilder("/home/user/oivsSelfCheckForm").toString());
			}*/

			if("Y".equals(studentVO.getStdYn())) {
				studentVO.setCrsCreCd(crsCreCd);
				studentVO.setStdNo(stdNo);
				StudentVO ssVO = studentService.viewStudent(studentVO).getReturnVO();
				String startDt = DateTimeUtil.getDateText(1,ssVO.getEnrlStartDttm(), ".");
				String endDt = DateTimeUtil.getDateText(1, studentVO.getEnrlEndDttm(), ".");

				//-- 학습기간 설정
				if("S".equals(courseVO.getCrsOperType())) {
					request.getSession().setAttribute(Constants.LOGIN_CRSDURATION, startDt+"~"+endDt);
				} else {
					request.getSession().setAttribute(Constants.LOGIN_CRSDURATION, createCourseVO.getEnrlStartDttm()+"~"+createCourseVO.getEnrlEndDttm());
				}

				request.getSession().setAttribute(Constants.LOGIN_CLASSUSERTYPE, "STU");
				request.getSession().setAttribute(Constants.LOGIN_STUDENTNO, stdNo);
				return "redirect:"+ new URLBuilder("/lec/main/goMenuPage")
						.addParameter("mcd", lecMenuNo)
						.toString();
			} else {
				return "redirect:"+ new URLBuilder("/home/main/goMenuPage")
						.addParameter("mcd", curMenuNo)
						.toString(); 
			}
		}
	}
	
	/**
	 * 강의실 나가기 로그아웃
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/logoutLecture")
	public String logoutLecture(Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		String crsCreCd = UserBroker.getCreCrsCd(request);
		String menuCode = configService.getValue("MENUNO", "MYPAGE");

		CreateCourseVO createCourseVO = new CreateCourseVO();
		createCourseVO.setCrsCreCd(crsCreCd);
		createCourseVO = createCourseService.viewCreateCourse(createCourseVO).getReturnVO();

		//-- 로그인 관련 처리는 추후에 ...
		request.getSession().setAttribute(Constants.LOGIN_CRSCRECD, "");
		request.getSession().setAttribute(Constants.LOGIN_CLASSUSERTYPE, "");
		request.getSession().setAttribute(Constants.LOGIN_SBJCD, "");
		request.getSession().setAttribute(Constants.LOGIN_STUDENTNO, "");
		request.getSession().setAttribute(Constants.LOGIN_CRSCRENM, "");
		request.getSession().setAttribute(Constants.LOGIN_CREYEAR, "");
		request.getSession().setAttribute(Constants.LOGIN_CRETERM, "");
		request.getSession().setAttribute(Constants.LOGIN_CRSMTHD, "");
		request.getSession().setAttribute(Constants.LOGIN_CRSTYPE, "");

		return "redirect:"+ new URLBuilder("/home/main/goMenuPage")
				.addParameter("mcd", menuCode)
				.toString(); 
	}

	/**
	 * 개설 정보 보기
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/readCourseInfoMain")
	public String readCourseInfoMain(Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String crsCreCd = UserBroker.getCreCrsCd(request);
		
		CreateCourseVO createCourseVO = new CreateCourseVO();
		createCourseVO.setCrsCreCd(crsCreCd);
		
		//-- 과정 개설 정보를 가져온다.
		createCourseVO = createCourseService.viewCreateCourse(createCourseVO).getReturnVO();
		request.setAttribute("createCourseVO", createCourseVO);
		
		//-- 과정 정보를 가져온다.
		CourseVO courseVO = new CourseVO();
		courseVO.setCrsCd(createCourseVO.getCrsCd());
		courseVO = courseService.view(courseVO).getReturnVO();
		request.setAttribute("courseVO", courseVO);
		
		MainLectureVO mainLectureVO = new MainLectureVO();
		mainLectureVO.setCrsCreCd(crsCreCd);
		mainLectureVO.setStdNo(UserBroker.getStudentNo(request));
		mainLectureVO = mainLectureService.createCourseSchedule(mainLectureVO).getReturnVO();
		
		ExamVO examVO = new ExamVO();
		examVO.setCrsCreCd(crsCreCd);
		List<ExamVO> examList = examService.listExam(examVO).getReturnList();
		String examDuration = examList.stream()
				.filter(exam -> "N".equals(exam.getSemiExamYn()))
				.map(exam -> String.format("[%s] : %s ~ %s", 
							exam.getExamTitle(),
							exam.getExamStartDttm(), 
							exam.getExamEndDttm()))
				.collect(Collectors.joining("<br>"));
		
		String semiDuration = examList.stream()
				.filter(exam -> "Y".equals(exam.getSemiExamYn()))
				.map(exam -> String.format("[%s] : %s ~ %s", 
							exam.getExamTitle(),
							exam.getExamStartDttm(),
							exam.getExamEndDttm()))
				.collect(Collectors.joining("<br>"));
		
		AssignmentVO asmtVO = new AssignmentVO();
		asmtVO.setCrsCreCd(crsCreCd);
		String asmtDuration = assignmentService.listAssignment(asmtVO).getReturnList().stream()
				.map(asmt -> String.format("[%s] : %s", asmt.getAsmtTitle(), asmt.getAsmtDuration()))
				.collect(Collectors.joining("<br>"));
		
		mainLectureVO.setExamDuration(examDuration);
		mainLectureVO.setSemiExamDuration(semiDuration);
		mainLectureVO.setAsmtDuration(asmtDuration);
		
		request.setAttribute("mainLectureVO", mainLectureVO);
		request.setAttribute("prpsRatio", Math.min(100, (mainLectureVO.getNowDayCnt()/mainLectureVO.getTermDayCnt())*100));
		
		return "home/main/lecture_course_info_main";
	}
	/**
	 * 개설 정보 보기
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/readCoursePlanMain")
	public String readCoursePlanMain(Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		String crsCreCd = UserBroker.getCreCrsCd(request);

		CreateCourseVO createCourseVO = new CreateCourseVO();
		createCourseVO.setCrsCreCd(crsCreCd);

		//-- 과정 개설 정보를 가져온다.
		createCourseVO = createCourseService.viewCreateCourse(createCourseVO).getReturnVO();
		request.setAttribute("createCourseVO", createCourseVO);

		//-- 과정 정보를 가져온다.
		CourseVO courseVO = new CourseVO();
		courseVO.setCrsCd(createCourseVO.getCrsCd());
		courseVO = courseService.view(courseVO).getReturnVO();
		request.setAttribute("courseVO", courseVO);
		

		/*
		 * OnlineSubjectVO osVO = new OnlineSubjectVO();
		 * osVO.setSbjCd(createCourseVO.getSbjCd()); ProcessResultVO<OnlineSubjectVO>
		 * resultVO = subjectService.viewOnline(osVO); request.setAttribute("osVO",
		 * resultVO.getReturnVO());
		 * 
		 * ContentsVO cVO = new ContentsVO(); cVO.setSbjCd(osVO.getSbjCd());
		 * ProcessResultListVO<ContentsVO> contentsList =
		 * contentsService.listContents(cVO); request.setAttribute("contentsList",
		 * contentsList.getReturnList());
		 */


		
		return "home/main/lecture_course_plan_main";
	}

	/**
	 * 권한 메뉴 연결
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/goMenuPage")
	public String goMenuPage(MainVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {
		//-- 요청 메뉴 코드를 가져온다.
		String mcd = vo.getMcd();
		String subParam = StringUtil.nvl(vo.getSubParam());
		String locale = UserBroker.getLocaleKey(request);

		//-- 사용자 권한을 가져온다. 권한이 없는 경우 GUEST 권한
		String classUserType = StringUtil.nvl(UserBroker.getClassUserType(request),"GUEST");

		if("MC00000000".equals(mcd)) {
			//-- 임이의 메뉴 코드 (home 링크)
			return "redirect:"+ new URLBuilder("/home/main/indexMain")
					.toString(); 
		}

		SysMenuVO menuVO = new SysMenuVO();
		String curMenuUrl = "";
		try {
			//-- 권한 설정 검색
			menuVO.setMenuType("LECT");
			menuVO.setMenuCd(mcd);
			menuVO.setAuthGrpCd(classUserType);
			menuVO = menuService.viewMenu(menuVO);

			//-- 메뉴경로를 가져와 최상위 메뉴를 구한다.
			String[] menuPath = StringUtil.split(menuVO.getMenuPath(),"|");
			String rootMenuCd = menuPath[1];

			//-- 호출한 메뉴가 최상위 메뉴인 경우 하위 메뉴의 첫번째 메뉴를 가져와 메뉴 코드에 셋팅한다.

			String menuLoaction = "";
			String curMenuName = menuVO.getMenuNm();
			for(SysMenuLangVO mlVO : menuVO.getMenuLangList()) {
				if(locale.equals(mlVO.getLangCd())) curMenuName = mlVO.getMenuNm();
			}
			String[] menuCds0 = StringUtil.split(menuVO.getMenuPath(),"|");
			for(int i=1; i < menuCds0.length; i++) {
				SysMenuVO muVO = new SysMenuVO();
				muVO.setMenuType(menuVO.getMenuType());
				muVO.setMenuCd(menuCds0[i]);
				muVO = menuService.viewMenu(muVO);
				if(i > 1) menuLoaction += " > ";
				for(SysMenuLangVO mlVO : muVO.getMenuLangList()) {
					if(locale.equals(mlVO.getLangCd())) menuLoaction += mlVO.getMenuNm();
				}
			}
			if(ValidationUtils.isEmpty(menuLoaction)) menuLoaction = menuVO.getMenuPathNm();
			String curMenuCode = menuVO.getMenuCd();
			curMenuUrl = menuVO.getMenuUrl();

			//-- 호출된 메뉴가 최상위 메뉴인 경우나. 자신의 경로가 없고 하위 경로를 찾는다.
			if(mcd.equals(rootMenuCd) || "".equals(StringUtil.nvl(curMenuUrl))){
				SysMenuVO subMenuVO = menuService.getMenuByCache("LECT", classUserType, mcd);
				//---- 하위 메뉴가 없는 경우만. 메뉴 신규로 세팅, 하위 메뉴가 없는 경우 최상위 메뉴로 셋팅
				if(!subMenuVO.getSubList().isEmpty()) {
					if("".equals(StringUtil.nvl(subMenuVO.getMenuUrl()))) {
						//-- 첫번째 VO를 가져와 메뉴로 셋팅함.
						SysMenuVO subSubMenuVO = subMenuVO.getSubList().get(0);
						//-- 연결 메뉴가 있으면. 셋팅 없으면 하위 메뉴 뒤진다.
						if(!"".equals(StringUtil.nvl(subSubMenuVO.getMenuUrl()))) {
							menuLoaction = subSubMenuVO.getMenuPathNm();
							curMenuName = subSubMenuVO.getMenuNm();
							for(SysMenuLangVO mlVO : subSubMenuVO.getMenuLangList()) {
								if(locale.equals(mlVO.getLangCd())) curMenuName = mlVO.getMenuNm();
							}
							String[] menuCds = StringUtil.split(subSubMenuVO.getMenuPath(),"|");
							for(int i=1; i < menuCds.length; i++) {
								SysMenuVO muVO = new SysMenuVO();
								muVO.setMenuType(subSubMenuVO.getMenuType());
								muVO.setMenuCd(menuCds[i]);
								muVO = menuService.viewMenu(muVO);
								if(i > 1) menuLoaction += " > ";
								for(SysMenuLangVO mlVO : muVO.getMenuLangList()) {
									if(locale.equals(mlVO.getLangCd())) menuLoaction += mlVO.getMenuNm();
								}
							}
							curMenuCode = subSubMenuVO.getMenuCd();
							curMenuUrl = subSubMenuVO.getMenuUrl();
						} else {
							SysMenuVO subSubSubMenuVO = subSubMenuVO.getSubList().get(0);
							menuLoaction = subSubSubMenuVO.getMenuPathNm();
							curMenuName = subSubSubMenuVO.getMenuNm();
							for(SysMenuLangVO mlVO : subSubSubMenuVO.getMenuLangList()) {
								if(locale.equals(mlVO.getLangCd())) curMenuName = mlVO.getMenuNm();
							}
							String[] menuCds = StringUtil.split(subSubSubMenuVO.getMenuPath(),"|");
							for(int i=1; i < menuCds.length; i++) {
								SysMenuVO muVO = new SysMenuVO();
								muVO.setMenuType(subSubMenuVO.getMenuType());
								muVO.setMenuCd(menuCds[i]);
								muVO = menuService.viewMenu(muVO);
								if(i > 1) menuLoaction += " > ";
								for(SysMenuLangVO mlVO : muVO.getMenuLangList()) {
									if(locale.equals(mlVO.getLangCd())) menuLoaction += mlVO.getMenuNm();
								}
							}
							curMenuCode = subSubSubMenuVO.getMenuCd();
							curMenuUrl = subSubSubMenuVO.getMenuUrl();
						}
					}
				}
			}

			// 세션 정보를 셋팅해 준다.
			request.getSession().setAttribute(Constants.MENU_LOCATION, menuLoaction);
			request.getSession().setAttribute(Constants.CUR_MENU_NAME, curMenuName);
			request.getSession().setAttribute(Constants.CUR_MENU_CODE, curMenuCode);
			request.getSession().setAttribute(Constants.ROT_MENU_CODE, rootMenuCd);

			//-- 서브 파라미터가 있는 경우 URL 대체함.
			if(!"".equals(subParam)) {
				String tarMenuUrl = StringUtil.split(curMenuUrl, "?")[0];
				curMenuUrl = tarMenuUrl+"?"+StringUtil.ReplaceAll(subParam, "@$","&");
			}

		} catch (Exception e) {
			return "redirect:"+ new URLBuilder("/home/main/indexMain")
					.toString(); 
		}
		return "redirect:"+ new URLBuilder(curMenuUrl).toString();
	}

	/**
	 * 언어 설정 변경
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/indexChgLang")
	public String indexChgLang(Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {

		String localeKey = request.getParameter("langCd");
		HttpSession session = request.getSession();
		String mcd = (String)session.getAttribute(Constants.CUR_MENU_CODE);
		session.setAttribute(Constants.SYSTEM_LOCALEKEY, localeKey);

		return "redirect:"+ new URLBuilder("/lec/main/goMenuPage")
				.addParameter("mcd", mcd)
				.toString(); 
	}
	
	
	
	/**
	 * 강의실 과정 정보
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/courseInfo")
	public String courseInfo(Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String orgCd = UserBroker.getUserOrgCd(request);	// 기관 코드 조회
		String classUserType = UserBroker.getClassUserType(request);
		String crsCreCd = UserBroker.getCreCrsCd(request);
		String stdNo = UserBroker.getStudentNo(request);

		//-- 개설 과정의 정보를 가져온다.
		CreateCourseVO createCourseVO = new CreateCourseVO();
		createCourseVO.setCrsCreCd(crsCreCd);
		createCourseVO.setStdNo(stdNo);
		createCourseVO = createCourseService.viewCreateCourse(createCourseVO).getReturnVO();
		
		request.setAttribute("createCourseVO", createCourseVO);

		//-- 과정의 정보를 가져온다.
		CourseVO courseVO = new CourseVO();
		courseVO.setCrsCd(createCourseVO.getCrsCd());
		courseVO = courseService.view(courseVO).getReturnVO();
		request.setAttribute("courseVO", courseVO);

		//-- 사용자의 정보를 가져온다.
		UsrUserInfoVO userInfoVO = new UsrUserInfoVO();
		userInfoVO.setUserNo(UserBroker.getUserNo(request));
		userInfoVO = userInfoService.view(userInfoVO);
		request.setAttribute("userInfoVO", userInfoVO);

		
		MainLectureVO mainLectureVO = new MainLectureVO();
		mainLectureVO.setCrsCreCd(crsCreCd);
		mainLectureVO.setStdNo(UserBroker.getStudentNo(request));
		mainLectureVO = mainLectureService.viewCreateCourseSchedule(mainLectureVO).getReturnVO();
		
		AttendanceVO avo = new AttendanceVO();
		avo.setCrsCreCd(createCourseVO.getCrsCreCd());
		avo.setUserNo(userInfoVO.getUserNo());
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String today = formatter.format(DateTimeUtil.getCurrentDate());
		avo.setAttendDttm(today);
		avo = attendanceService.viewAttend(avo).getReturnVO();
		request.setAttribute("attendanceVO", avo);

		//권장 진도율
		if (createCourseVO.getCrsOperMthd().equals("OF")) {
			float termDayCnt = mainLectureVO.getTermDayCnt();
			float nowDayCnt = mainLectureVO.getNowDayCnt();
			
			int prpsRatio = Math.round((nowDayCnt/termDayCnt)*100);
			request.setAttribute("prpsRatio", Math.min(100, prpsRatio));
		}
		if(!"TCH".equals(classUserType)) {
			boolean isOpen = true;
			boolean nowScoreOpen = false;
			if(!StringUtil.nvl(courseVO.getScoreOpenStartDttm()).equals("") && !StringUtil.nvl(courseVO.getScoreOpenEndDttm()).equals(""))
				nowScoreOpen = DateTimeUtil.chkTimeNowBetween(courseVO.getScoreOpenStartDttm(), courseVO.getScoreOpenEndDttm()); 
			if(!nowScoreOpen) {
				isOpen = false;
				mainLectureVO.hideScore();
			}
			request.setAttribute("isOpen", isOpen);
		}
		request.setAttribute("mainLectureVO", mainLectureVO);
		return UserBroker.getPrefixHomeUrl(request) + "/inc/lec_course";
	}

	
}