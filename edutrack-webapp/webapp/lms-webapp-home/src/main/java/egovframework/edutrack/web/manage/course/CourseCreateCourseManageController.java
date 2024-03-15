package egovframework.edutrack.web.manage.course;

import java.util.ArrayList;
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
import egovframework.edutrack.comm.util.web.DateTimeUtil;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.course.attendance.service.AttendanceService;
import egovframework.edutrack.modules.course.attendance.service.AttendanceVO;
import egovframework.edutrack.modules.course.contents.service.ContentsService;
import egovframework.edutrack.modules.course.contents.service.ContentsVO;
import egovframework.edutrack.modules.course.course.service.CourseService;
import egovframework.edutrack.modules.course.course.service.CourseVO;
import egovframework.edutrack.modules.course.coursesubject.service.CrsOnlnSbjService;
import egovframework.edutrack.modules.course.coursesubject.service.CrsOnlnSbjVO;
import egovframework.edutrack.modules.course.creCrsResh.service.CreCrsReshService;
import egovframework.edutrack.modules.course.creCrsResh.service.CreCrsReshVO;
import egovframework.edutrack.modules.course.createcourse.service.CreateCourseService;
import egovframework.edutrack.modules.course.createcourse.service.CreateCourseVO;
import egovframework.edutrack.modules.course.createcoursesubject.service.CreateCourseSubjectService;
import egovframework.edutrack.modules.course.createcoursesubject.service.CreateOnlineSubjectVO;
import egovframework.edutrack.modules.course.createcourseteacher.service.CreateCourseTeacherService;
import egovframework.edutrack.modules.course.createcourseteacher.service.TeacherVO;
import egovframework.edutrack.modules.course.createcoursetimetable.service.TimetableService;
import egovframework.edutrack.modules.course.createcoursetimetable.service.TimetableVO;
import egovframework.edutrack.modules.course.subject.service.LecRoomVO;
import egovframework.edutrack.modules.course.subject.service.OnlineSubjectVO;
import egovframework.edutrack.modules.course.subject.service.SubjectService;
import egovframework.edutrack.modules.lecture.assignment.service.AssignmentService;
import egovframework.edutrack.modules.lecture.assignment.service.AssignmentVO;
import egovframework.edutrack.modules.lecture.exam.service.ExamService;
import egovframework.edutrack.modules.lecture.exam.service.ExamVO;
import egovframework.edutrack.modules.org.info.service.OrgOrgInfoService;
import egovframework.edutrack.modules.system.code.service.SysCodeVO;
import egovframework.edutrack.modules.system.config.service.SysCfgService;
import egovframework.edutrack.modules.system.config.service.SysCfgVO;
import egovframework.edutrack.modules.user.dept.service.UsrDeptInfoService;
import egovframework.edutrack.modules.user.dept.service.UsrDeptInfoVO;


@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/mng/course/createCourse")
public class CourseCreateCourseManageController extends GenericController{

	@Autowired @Qualifier("courseService")
	private CourseService			courseService;

	@Autowired @Qualifier("createCourseService")
	private CreateCourseService	createCourseService;

	@Autowired @Qualifier("sysCfgService")
	private SysCfgService			sysCfgService;

	@Autowired @Qualifier("sysCodeMemService")
	private SysCodeMemService			sysCodeMemService;

	@Autowired @Qualifier("orgOrgInfoService")
	private OrgOrgInfoService			orgOrgInfoService;

	@Autowired @Qualifier("createCourseSubjectService")
	private CreateCourseSubjectService 	createCourseSubjectService;

	@Autowired @Qualifier("contentsService")
	private ContentsService 				contentsService;

	@Autowired @Qualifier("timetableService")
	private TimetableService 				timetableService;

	@Autowired @Qualifier("createCourseTeacherService")
	private CreateCourseTeacherService 	createCourseTeacherService;
	
	@Autowired @Qualifier("usrDeptInfoService")
	private UsrDeptInfoService 	usrDeptInfoService;
	
	@Autowired @Qualifier("subjectService")
	private SubjectService 	subjectService;
	
	@Autowired @Qualifier("crsOnlnSbjService")
	private CrsOnlnSbjService	crsOnlnSbjService;
	
	@Autowired @Qualifier("creCrsReshService")
	private CreCrsReshService	creCrsReshService;
	
	@Autowired @Qualifier("examService")
	private ExamService	examService;
	
	@Autowired @Qualifier("assignmentService")
	private AssignmentService 			assignmentService;
	
	@Autowired @Qualifier("attendanceService")
	private AttendanceService	attendanceService;
	

	private static final String CREATE_COURSE_MAIN		= "create_course_main";
	private static final String COURSE_LIST				= "course_list";
	private static final String CREATE_COURSE_LIST		= "create_course_list";
	private static final String CREATE_COURSE_WRITE		= "create_course_write";
	private static final String CREATE_COURSE_EDIT		= "create_course_edit";

	/**
	 * 개설 과정 관리 메인
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/createCourseMain")
	public String mainCreateCourse( CreateCourseVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String curYear = DateTimeUtil.getYear();
		//-- 시작년도 가져오기
		SysCfgVO syscfgVO = new SysCfgVO();
		syscfgVO.setCfgCtgrCd("SYSTEM");
		syscfgVO.setCfgCd("START_YEAR");
		syscfgVO = sysCfgService.viewCfg(syscfgVO);
		
		vo.setUserNo(UserBroker.getUserNo(request));
		
		List<String> yearList = new ArrayList<String>();
		for(int i= Integer.parseInt(curYear,10)+1; i >= Integer.parseInt(syscfgVO.getCfgVal(),10); i--) {
			yearList.add(Integer.toString(i));
		}
		//-- 현재의 년도를 Attribute에 세팅한다.
		request.setAttribute("curYear", curYear);
		request.setAttribute("yearList", yearList);
		request.setAttribute("vo", vo);
		request.setAttribute("paging", "Y");
		request.setAttribute("jstree", "Y");
		return "mng/course/createcourse/create_course_main";
	}

	/**
	 * 과정 목록 조회
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/listCourse")
	public String listCourse( CourseVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		ProcessResultListVO<CourseVO> resultList = courseService.listPageing(vo, vo.getCurPage(), vo.getListScale());
		
		request.setAttribute("courseList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());
		request.setAttribute("courseVO", vo);
		
		return "mng/course/createcourse/course_list_div";
	}

	/**
	 * 개설 과정 목록 조회
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/listCreateCourse")
	public String listCreateCourse( CreateCourseVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		//-- 과정 정보 검색
//		CourseVO courseVO = new CourseVO();
//		courseVO.setCrsCd(createCourseVO.getCrsCd());
//		courseVO = courseService.view(courseVO).getReturnVO();

		request.setAttribute("crsOperType", vo.getCrsOperType());
		
		vo.setMngType(UserBroker.getMngType(request));
		if (vo.getMngType().contains("TCHMGR")) {
			vo.setUserNo(UserBroker.getUserNo(request));
		}
		
		//List<CreateCourseVO> createCourseList = createCourseService.listCreateCourse(vo).getReturnList();
		ProcessResultListVO<CreateCourseVO> createCourseList = createCourseService.listCreateCoursePageing(vo, vo.getCurPage(), vo.getListScale(), true);
		request.setAttribute("pageInfo", createCourseList.getPageInfo());
		request.setAttribute("vo", vo);
		request.setAttribute("createCourseList", createCourseList.getReturnList());
		return "mng/course/createcourse/create_course_list_div";
	}

	/**
	 * 과정 목록 조회(Json)
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/listCourseJson")
	public String listCourseJson( CourseVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		return JsonUtil
			.responseJson(response, courseService.list(vo));
		
	}

	/**
	 * 개설 과정 목록 조회(Json)
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/listCreateCourseJson")
	public String listCreateCourseJson( CreateCourseVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		return JsonUtil
			.responseJson(response, createCourseService.listCreateCourse(vo));
	}

	/**
	 * 회차 등록 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addFormCreateCourse")
	public String addFormCreateCourse( CreateCourseVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String creCnt = StringUtil.nvl(request.getParameter("creCnt"),"0");

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		CreateCourseVO ccVO = createCourseService.selectMaxTerm(vo).getReturnVO();
		request.setAttribute("maxTerm", ccVO.getCreTerm());

		//-- 과정의 정보르 검색 하여 과정명 셋팅
		CourseVO courseVO = new CourseVO();
		courseVO.setCrsCd(vo.getCrsCd());
		ProcessResultVO<CourseVO> ResultVO = courseService.view(courseVO);
		courseVO = ResultVO.getReturnVO();
		
		//강의실 정보
		LecRoomVO lvo = new LecRoomVO();
		ProcessResultListVO<LecRoomVO> resultList = subjectService.listLecRoomPageing(lvo, lvo.getCurPage(), lvo.getListScale());
		request.setAttribute("lecRoomList", resultList.getReturnList());

		request.setAttribute("vo", vo);
		request.setAttribute("courseVO", courseVO);
		
		List<SysCodeVO> nopLimitList = sysCodeMemService.getSysCodeList("NOP_LIMIT_YN");
		request.setAttribute("nopLimitList", nopLimitList);

		List<CreateCourseVO> createCourseList = createCourseService.listCreateCourse(vo).getReturnList();
		request.setAttribute("createCourseList", createCourseList);

		request.setAttribute("creCnt", creCnt);
		request.setAttribute("gubun", "A");
		return "mng/course/createcourse/create_course_write";
	}

	/**
	 * 회차 등록
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/addCreateCourse")
	public String addCreateCourse( CreateCourseVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		ProcessResultVO<CreateCourseVO> resultVO = new ProcessResultVO<CreateCourseVO>();
		try {
			resultVO = createCourseService.addCreateCourse(vo);
			resultVO.setMessage(getMessage(request, "course.message.createcourse.add.success"));
		}catch (Exception e){
			resultVO.setResultFailed();
			resultVO.setMessage(getMessage(request, "course.message.createcourse.add.failed"));
		}
		
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 개설 과정 수정 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/editFormCreateCourse")
	public String editFormCreateCourse( CreateCourseVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String crsCtgrCd = vo.getCrsCtgrCd();
		String parcrsCtgrCd = vo.getParCtgrCd();

		//-- 개설 과정 정보를 가져온다.
		ProcessResultVO<CreateCourseVO> resultVO = createCourseService.viewCreateCourse(vo);
		vo = resultVO.getReturnVO();
		vo.setCrsCtgrCd(crsCtgrCd);
		vo.setParCtgrCd(parcrsCtgrCd);
		request.setAttribute("vo", vo);
		//-- 과정 정보를 가져온다.
		CourseVO courseVO = new CourseVO();
		courseVO.setCrsCd(vo.getCrsCd());
		courseVO = courseService.view(courseVO).getReturnVO();
		request.setAttribute("courseVO", courseVO);
		List<SysCodeVO> nopLimitList = sysCodeMemService.getSysCodeList("NOP_LIMIT_YN");
		
		request.setAttribute("nopLimitList", nopLimitList);

		request.setAttribute("gubun", "E");
		return "mng/course/createcourse/create_course_edit";
	}

	/**
	 * 개설 과정 수정 팝업
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/editCreateCoursePop")
	public String editCreateCoursePop(CreateCourseVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String crsCtgrCd = vo.getCrsCtgrCd();
		String parcrsCtgrCd = vo.getParCtgrCd();

		//-- 개설 과정 정보를 가져온다.
		ProcessResultVO<CreateCourseVO> resultVO = createCourseService.viewCreateCourse(vo);
		vo = resultVO.getReturnVO();
		vo.setCrsCtgrCd(crsCtgrCd);
		vo.setParCtgrCd(parcrsCtgrCd);
		request.setAttribute("vo", vo);
		
		//강의실 정보
		LecRoomVO lvo = new LecRoomVO();
		ProcessResultListVO<LecRoomVO> lecRoomList = subjectService.listLecRoomPageing(lvo, lvo.getCurPage(), lvo.getListScale());
		request.setAttribute("lecRoomList", lecRoomList.getReturnList());
		//-- 과정 정보를 가져온다.
		CourseVO courseVO = new CourseVO();
		courseVO.setCrsCd(vo.getCrsCd());
		courseVO = courseService.view(courseVO).getReturnVO();
		request.setAttribute("courseVO", courseVO);
		List<SysCodeVO> nopLimitList = sysCodeMemService.getSysCodeList("NOP_LIMIT_YN");
		request.setAttribute("nopLimitList", nopLimitList);

		request.setAttribute("gubun", "E");
		return "mng/course/createcourse/create_course_edit_pop";
	}

	/**
	 * 회차 수정
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/editCreateCourse")
	public String editCreateCourse( CreateCourseVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		AttendanceVO avo = new AttendanceVO();
		avo.setCrsCreCd(vo.getCrsCreCd());
		int count = attendanceService.countAttend(avo);
		ProcessResultVO<CreateCourseVO> resultVO = new ProcessResultVO<CreateCourseVO>();
		if(count > 0) {
			resultVO.setResultFailed();
			resultVO.setMessage("출석한 수강생이 있어 변경이 불가 합니다");
			return JsonUtil.responseJson(response, resultVO);
		}
		try {
			resultVO = createCourseService.editCreateCourse(vo);
			resultVO.setMessage(getMessage(request, "course.message.createcourse.edit.success"));
		}catch(Exception e) {
			resultVO.setMessage(getMessage(request, "course.message.createcourse.edit.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 개설 과정 삭제
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/deleteCreateCourse")
	public String deleteCreateCourse( CreateCourseVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		CreateCourseVO ccVO = createCourseService.viewCreateCourse(vo).getReturnVO();
		ProcessResultVO<?> resultVO = new ProcessResultVO<Object>();
		
		CreCrsReshVO ccrVO = new CreCrsReshVO();
		ccrVO.setCrsCreCd(ccVO.getCrsCreCd());
		List<CreCrsReshVO> reshList = creCrsReshService.list(ccrVO).getReturnList();
		if(!reshList.isEmpty()) {
			resultVO.setResult(-1);
			resultVO.setMessage("등록된 설문이 있습니다. 개설과정을 삭제 할 수 없습니다.");
			return JsonUtil.responseJson(response, resultVO);
		}
		
		ExamVO evo = new ExamVO();
		evo.setCrsCreCd(ccVO.getCrsCreCd());
		List<ExamVO> examList = examService.listExam(evo).getReturnList();
		if(!examList.isEmpty()) {
			resultVO.setResult(-1);
			resultVO.setMessage("등록된 시험이 있습니다. 개설과정을 삭제 할 수 없습니다.");
			return JsonUtil.responseJson(response, resultVO);
		}
		
		AssignmentVO avo = new AssignmentVO();
		avo.setCrsCreCd(ccVO.getCrsCreCd());
		List<AssignmentVO> asmtList = assignmentService.listAssignment(avo).getReturnList();
		if(!asmtList.isEmpty()) {
			resultVO.setResult(-1);
			resultVO.setMessage("등록된 과제가 있습니다. 개설과정을 삭제 할 수 없습니다.");
			return JsonUtil.responseJson(response, resultVO);
		}
		
		if(ccVO.getStuCnt() > 0) {
			resultVO.setResult(-1);
			resultVO.setMessage(getMessage(request, "course.message.createcourse.alert.delete1").replace("\\n\\n", "\r\n"));
		} else if (ccVO.getSbjCnt() > 0) {
			resultVO.setResult(-1);
			resultVO.setMessage(getMessage(request, "course.message.createcourse.alert.delete2").replace("\\n\\n", "\r\n"));
		} else if (ccVO.getTchCnt() > 0) {
			resultVO.setResult(-1);
			resultVO.setMessage(getMessage(request, "course.message.createcourse.alert.delete3").replace("\\n\\n", "\r\n"));
		}else {
				resultVO = createCourseService.deleteCreateCourse(vo);
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 과정별 기수 목록 조회(Json)
	 */
	@RequestMapping(value="/listCourseTermJson")
	public String listCourseTermJson( CreateCourseVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		return JsonUtil
			.responseJson(response, createCourseService.listCourseTerm(vo));
	}


	/**
	 * 과목 사용중인 개설 과정 목록 조회
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/listSubInfo")
	public String listSubInfo( CreateCourseVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		List<CreateCourseVO> createCourseList = createCourseService.listSubInfo(vo).getReturnList();

		request.setAttribute("createCourseList", createCourseList);
		request.setAttribute("createCourseVO", vo);

		return "mng/course/createcourse/create_course_sub_list_div";
	}

	/**
	 * 과목 사용중인 개설 과정 목록 조회
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/listSubInfoOffline")
	public String listSubInfoOffline( CreateCourseVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		List<CreateCourseVO> createCourseList = createCourseService.listSubInfoOffline(vo).getReturnList();
		request.setAttribute("createCourseVO", vo);
		request.setAttribute("createCourseList", createCourseList);

		return "mng/course/createcourse/create_course_sub_list_div";
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

		//-- 과정 개설 정보를 가져온다.
		vo = createCourseService.viewCreateCourse(vo).getReturnVO();
		request.setAttribute("createCourseVO", vo);

		//-- 과정 정보를 가져온다.
		CourseVO courseVO = new CourseVO();
		courseVO.setCrsCd(vo.getCrsCd());
		courseVO = courseService.view(courseVO).getReturnVO();

		//-- 코드를 가져와 수강대상을 셋팅한다.
		List<SysCodeVO> codeList = sysCodeMemService.getSysCodeList("EDU_TARGET");
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

		return "mng/course/createcourse/view_course_pop";
	}

	/**
	 * 전체 개설 정보 목록 - Dashboard 용
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/listTotalCoursePop")
	public String listTotalCoursePop( CreateCourseVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String curYear = DateTimeUtil.getYear();
		String orgCd = UserBroker.getUserOrgCd(request);


		vo.setCreYear(Integer.parseInt(curYear));
		vo.setOrgCd(orgCd);

		//-- 전체 과정 개설 정보를 가져온다.
		List<CreateCourseVO> createCourseList = createCourseService.listCreateCourseForAll(vo).getReturnList();

		request.setAttribute("createCourseList", createCourseList);
		request.setAttribute("createCourseVO", vo);
		return "mng/course/createcourse/course_list_all_pop";
	}

	/**
	 * 개설 과정 목록 조회
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/listCreateCourseInner")
	public String listCreateCourseInner( CreateCourseVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		List<CreateCourseVO> createCourseList = createCourseService.listCreateCourse(vo).getReturnList();
		request.setAttribute("vo", vo);
		request.setAttribute("createCourseList", createCourseList);
		return "mng/course/createcourse/create_course_inner_list";
	}

	/**
	 * 과정별 기수 목록 조회(Json)
	 */
	@RequestMapping(value="/listDeptByCrsCd")
	public String listDeptByCrsCd( CreateCourseVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		return JsonUtil
			.responseJson(response, createCourseService.listDeptByCrsCd(vo));
	}
	
	/**
	 * 과정별 기수 목록 조회(Json)
	 */
	@RequestMapping(value="/listSbjByDeptCd")
	public String listSbjByDeptCd( CreateCourseVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		return JsonUtil
				.responseJson(response, createCourseService.listSbjByDeptCd(vo));
	}
	
	/**
	 * 회차 등록 팝업
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addFormCreateCoursePop")
	public String addFormCreateCoursePop( CreateCourseVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String creCnt = StringUtil.nvl(request.getParameter("creCnt"),"0");

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		CreateCourseVO ccVO = createCourseService.selectMaxTerm(vo).getReturnVO();
		request.setAttribute("maxTerm", ccVO.getCreTerm());

		//-- 과정의 정보르 검색 하여 과정명 셋팅
		CourseVO courseVO = new CourseVO();
		courseVO.setCrsCd(vo.getCrsCd());
		courseVO.setListScale(1000);
		
		ProcessResultListVO<CourseVO> resultList = courseService.listPageing(courseVO, courseVO.getCurPage(), courseVO.getListScale());
		request.setAttribute("courseList", resultList.getReturnList());
		//ProcessResultVO<CourseVO> ResultVO = courseService.view(courseVO);
		//courseVO = ResultVO.getReturnVO();
		
		//강의실 정보
		LecRoomVO lvo = new LecRoomVO();
		ProcessResultListVO<LecRoomVO> lecRoomList = subjectService.listLecRoomPageing(lvo, lvo.getCurPage(), lvo.getListScale());
		request.setAttribute("lecRoomList", lecRoomList.getReturnList());

		request.setAttribute("vo", vo);
		request.setAttribute("courseVO", courseVO);
		
		List<SysCodeVO> nopLimitList = sysCodeMemService.getSysCodeList("NOP_LIMIT_YN");
		request.setAttribute("nopLimitList", nopLimitList);

		List<CreateCourseVO> createCourseList = createCourseService.listCreateCourse(vo).getReturnList();
		request.setAttribute("createCourseList", createCourseList);

		request.setAttribute("creCnt", creCnt);
		request.setAttribute("gubun", "A");
		return "mng/course/createcourse/create_course_write_pop";
	}
	
	
	/**
	 * 개설 과정 QR 정보 보기
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/readCourseQrPop")
	public String readCourseQrPop( CreateCourseVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		request.setAttribute("createCourseVO", vo);
		
		return "mng/course/createcourse/view_course_qr_pop";
	}

}
