package egovframework.edutrack.web.manage.student;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.exception.MediopiaDefineException;
import egovframework.edutrack.comm.exception.ServiceProcessException;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.service.SysCodeMemService;
import egovframework.edutrack.comm.util.web.DateTimeUtil;
import egovframework.edutrack.comm.util.web.FileUtil;
import egovframework.edutrack.comm.util.web.InicisUtil;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.POIExcelUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.course.contents.service.ContentsService;
import egovframework.edutrack.modules.course.contents.service.ContentsVO;
import egovframework.edutrack.modules.course.course.service.CourseService;
import egovframework.edutrack.modules.course.course.service.CourseVO;
import egovframework.edutrack.modules.course.createcourse.service.CreateCourseService;
import egovframework.edutrack.modules.course.createcourse.service.CreateCourseVO;
import egovframework.edutrack.modules.course.createcourse.service.UserCourseVO;
import egovframework.edutrack.modules.course.createcoursesubject.service.CreateCourseSubjectService;
import egovframework.edutrack.modules.course.createcoursesubject.service.CreateOnlineSubjectVO;
import egovframework.edutrack.modules.course.createcourseteacher.service.TeacherVO;
import egovframework.edutrack.modules.course.decls.service.CreCrsDeclsService;
import egovframework.edutrack.modules.course.decls.service.CreCrsDeclsVO;
import egovframework.edutrack.modules.course.exambank.service.ExamQbankQstnVO;
import egovframework.edutrack.modules.course.subject.service.OnlineSubjectVO;
import egovframework.edutrack.modules.course.subject.service.SubjectService;
import egovframework.edutrack.modules.lecture.bookmark.service.BookmarkService;
import egovframework.edutrack.modules.lecture.bookmark.service.BookmarkVO;
import egovframework.edutrack.modules.log.message.service.LogMsgLogService;
import egovframework.edutrack.modules.log.message.service.LogMsgLogVO;
import egovframework.edutrack.modules.log.message.service.LogMsgTransLogVO;
import egovframework.edutrack.modules.log.privateinfo.service.LogPrivateInfoInqLogVO;
import egovframework.edutrack.modules.log.privateinfo.service.LogPrivateInfoService;
import egovframework.edutrack.modules.log.prnlog.service.LogPrnLogService;
import egovframework.edutrack.modules.log.prnlog.service.LogPrnLogVO;
import egovframework.edutrack.modules.org.config.service.OrgUserInfoCfgService;
import egovframework.edutrack.modules.org.config.service.OrgUserInfoCfgVO;
import egovframework.edutrack.modules.org.crscert.service.OrgCrsCertService;
import egovframework.edutrack.modules.org.crscert.service.OrgCrsCertVO;
import egovframework.edutrack.modules.org.emailtpl.service.OrgEmailTplService;
import egovframework.edutrack.modules.org.info.service.OrgOrgInfoService;
import egovframework.edutrack.modules.org.info.service.OrgOrgInfoVO;
import egovframework.edutrack.modules.student.payment.service.PaymentInicisCancelResultDTO;
import egovframework.edutrack.modules.student.payment.service.PaymentInicisParCancelResultDTO;
import egovframework.edutrack.modules.student.payment.service.PaymentService;
import egovframework.edutrack.modules.student.student.service.StudentExcelService;
import egovframework.edutrack.modules.student.student.service.StudentExcelVO;
import egovframework.edutrack.modules.student.student.service.StudentPDFService;
import egovframework.edutrack.modules.student.student.service.StudentService;
import egovframework.edutrack.modules.student.student.service.StudentVO;
import egovframework.edutrack.modules.system.code.service.SysCodeVO;
import egovframework.edutrack.modules.system.config.service.SysCfgService;
import egovframework.edutrack.modules.system.config.service.SysCfgVO;
import egovframework.edutrack.modules.user.dept.service.UsrDeptInfoService;
import egovframework.edutrack.modules.user.dept.service.UsrDeptInfoVO;
import egovframework.edutrack.modules.user.info.service.UsrUserInfoService;
import egovframework.edutrack.modules.user.info.service.UsrUserInfoVO;
import egovframework.edutrack.notification.MessageNotificationException;


@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/mng/std/student")
public class StudentStudentManageController extends GenericController {

	@Autowired @Qualifier("studentService")
	private StudentService					studentService;

	@Autowired @Qualifier("studentExcelService")
	private StudentExcelService			studentExcelService;

	@Autowired @Qualifier("studentPDFService")
	private StudentPDFService				studentPDFService;
 
	@Autowired @Qualifier("createCourseService")
	private CreateCourseService			createCourseService;

	@Autowired @Qualifier("courseService")
	private CourseService					courseService;

	@Autowired @Qualifier("sysCfgService")
	private SysCfgService					sysCfgService;

	@Autowired @Qualifier("logPrnLogService")
	private LogPrnLogService				logPrnLogService;

	@Autowired @Qualifier("createCourseSubjectService")
	private CreateCourseSubjectService 	createCourseSubjectService;

	@Autowired @Qualifier("bookmarkService")
	private BookmarkService 				bookmarkService;

	@Autowired @Qualifier("sysCodeMemService")
	private SysCodeMemService					sysCodeMemService;

	@Autowired @Qualifier("creCrsDeclsService")
	private CreCrsDeclsService 			creCrsDeclsService;

	@Autowired @Qualifier("usrUserInfoService")
	private UsrUserInfoService	 			usrUserInfoService;

	@Autowired @Qualifier("orgOrgInfoService")
	private OrgOrgInfoService		 			orgOrgInfoService;

	@Autowired @Qualifier("orgEmailTplService")
	private OrgEmailTplService		 		orgEmailTplService;

	@Autowired @Qualifier("logMsgLogService")
	private LogMsgLogService					logMsgLogService;

	@Autowired @Qualifier("orgCrsCertService")
	private OrgCrsCertService				orgCrsCertService;

	@Autowired @Qualifier("logPrivateInfoService")
	private LogPrivateInfoService 			logPrivateInfoService;

	@Autowired @Qualifier("orgUserInfoCfgService")
	private OrgUserInfoCfgService 	 		orgUserInfoCfgService;
	
	@Autowired @Qualifier("paymentService")
	private PaymentService paymentService;
	
	@Autowired @Qualifier("subjectService")
	private SubjectService	subjectService;
	
	@Autowired @Qualifier("contentsService")
	private ContentsService	contentsService;	
	
	@Autowired @Qualifier("usrDeptInfoService")
	private UsrDeptInfoService	usrDeptInfoService;

	private static final String STUDENT_MAIN				= "student_main";
	private static final String STUDENT_COURSE_LIST			= "student_course_list";
	private static final String STUDENT_SUB_MAIN			= "student_sub_main";
	private static final String ENROLL_LIST					= "enroll_list";
	private static final String COMPLETE_LIST				= "complete_list";
	private static final String EDUNO_LIST					= "eduno_list";
	private static final String STUDENT_WRITE				= "student_write";

	/**
	 * 수강 신청 관리 메인
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/mainStudent")
	public String mainStudent(StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String curYear = DateTimeUtil.getYear();

	    List<SysCodeVO> eduTeamCdList = sysCodeMemService.getSysCodeList("EDU_TEAM_CD", UserBroker.getLocaleKey(request));
		request.setAttribute("tchCtgrCdList", eduTeamCdList);
		
		
		//-- 시작년도 가져오기
		SysCfgVO sysCfgVO = new SysCfgVO();
		sysCfgVO.setCfgCtgrCd("SYSTEM");
		sysCfgVO.setCfgCd("START_YEAR");
		sysCfgVO = sysCfgService.viewCfg(sysCfgVO);
		List<String> yearList = new ArrayList<String>();
		for(int i= Integer.parseInt(curYear,10)+1; i >= Integer.parseInt(sysCfgVO.getCfgVal(),10); i--) {
			yearList.add(Integer.toString(i));
		}
		//-- 현재의 년도를 Attribute에 세팅한다.
		request.setAttribute("curYear", curYear);
		request.setAttribute("yearList", yearList);
		request.setAttribute("paging", "Y");
		request.setAttribute("jstree", "Y");

		//-- 카테고리 리스트를 받아 온다.
		//ProcessResultListVO<CourseCategoryVO> resultListVO = categoryService.listCategory(courseVO.getCrsCtgrCd(),"Y");
		//request.setAttribute("categoryList", resultListVO.getReturnList());
		return "mng/student/student/"+STUDENT_MAIN;
	}

	/**
	 * 수강 신청 관리 화면
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/manageStudent")
	public String manageStudent( StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		commonVOProcessing(vo, request);

		CreateCourseVO createCourseVO = new CreateCourseVO();
		createCourseVO.setCrsCreCd(vo.getCrsCreCd());
		createCourseVO = createCourseService.viewCreateCourse(createCourseVO).getReturnVO();
		request.setAttribute("createCourseVO", createCourseVO);

		CourseVO courseVO = new CourseVO();
		courseVO.setCrsCd(createCourseVO.getCrsCd());
		courseVO = courseService.view(courseVO).getReturnVO();
		request.setAttribute("courseVO",courseVO);
		request.setAttribute("vo",vo);

		return "mng/student/student/student_course_manage_div";
	}

	/**
	 * 수강 신청 관리 서브 메인
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/mainSubStudent")
	public String mainSubStudent( StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		commonVOProcessing(vo, request);

		//-- 개설 과정의 정보를 가져온다.
		CreateCourseVO createCourseVO = new CreateCourseVO();
		createCourseVO.setCrsCreCd(vo.getCrsCreCd());
		createCourseVO = createCourseService.viewCreateCourse(createCourseVO).getReturnVO();
		request.setAttribute("createCourseVO", createCourseVO);
		
		//-- 과정의 정보를 가져온다.
		CourseVO courseVO = new CourseVO();
		courseVO.setCrsCd(createCourseVO.getCrsCd());
		courseVO = courseService.view(courseVO).getReturnVO();
		request.setAttribute("courseVO", courseVO);
		return "STUDENT_SUB_MAIN";
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

		ProcessResultListVO<CreateCourseVO> resultList = createCourseService.listCreateCoursePageing(vo, vo.getCurPage(), vo.getListScale(), true);

		request.setAttribute("createCourseList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());
		return "mng/student/student/student_course_list_div";
	}


	/**
	 * 수강 신청 관리 메인 화면
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/enrollStudentForm")
	public String enrollStudentForm(StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		CreateCourseVO createCourseVO = new CreateCourseVO();
		createCourseVO.setCrsCreCd(vo.getCrsCreCd());
		createCourseVO = createCourseService.viewCreateCourse(createCourseVO).getReturnVO();

		CourseVO courseVO = new CourseVO();
		courseVO.setCrsCd(createCourseVO.getCrsCd());
		courseVO = courseService.view(courseVO).getReturnVO();
		request.setAttribute("courseVO", courseVO);

		//--- 분반 목록
		CreCrsDeclsVO creCrsDeclsVO = new CreCrsDeclsVO();
		creCrsDeclsVO.setCrsCreCd(vo.getCrsCreCd());
		List<CreCrsDeclsVO> creCrsDeclsList = creCrsDeclsService.list(creCrsDeclsVO).getReturnList();
		request.setAttribute("creCrsDeclsList", creCrsDeclsList);

		//-- 환불 상태 코드
		List<SysCodeVO> repayStsList = sysCodeMemService.getSysCodeList("REPAY_STS_CD");
		request.setAttribute("repayStsList", repayStsList);
		request.setAttribute("vo", vo);
		request.setAttribute("paging", "Y");
		
		

		return "mng/student/student/enroll_form";
	}

	/**
	 * 수강 신청 관리 목록 화면
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/listEnrollStudent")
	public String listEnrollStudent(StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		CreateCourseVO createCourseVO = new CreateCourseVO();
		createCourseVO.setCrsCreCd(vo.getCrsCreCd());
		createCourseVO = createCourseService.viewCreateCourse(createCourseVO).getReturnVO();
		request.setAttribute("createCourseVO", createCourseVO);
		
//		ProcessResultListVO<StudentVO> resultList = studentService.listStudent(vo, curPage, listScale, 10);
		ProcessResultListVO<StudentVO> resultList = studentService.listStudentPageing(vo);

		request.setAttribute("vo", vo);
		request.setAttribute("studentList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());
		return "mng/student/student/enroll_list_div";
	}

	/**
	 * 수강 수료 관리 메인 화면
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/completeStudentForm")
	public String completeStudentForm(StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		request.setAttribute("studentVO", vo);
		
		CreateCourseVO createCourseVO = new CreateCourseVO();
		createCourseVO.setCrsCreCd(vo.getCrsCreCd());
		createCourseVO = createCourseService.viewCreateCourse(createCourseVO).getReturnVO();
		request.setAttribute("createCourseVO", createCourseVO);

		CourseVO courseVO = new CourseVO();
		courseVO.setCrsCd(createCourseVO.getCrsCd());
		courseVO = courseService.view(courseVO).getReturnVO();
		request.setAttribute("courseVO", courseVO);

		//--- 분반 목록
		CreCrsDeclsVO creCrsDeclsVO = new CreCrsDeclsVO();
		creCrsDeclsVO.setCrsCreCd(vo.getCrsCreCd());
		List<CreCrsDeclsVO> creCrsDeclsList = creCrsDeclsService.list(creCrsDeclsVO).getReturnList();
		request.setAttribute("creCrsDeclsList", creCrsDeclsList);
		request.setAttribute("paging", "Y");


		return "mng/student/student/complete_form";
	}


	/**
	 * 수강 수료 관리 목록 화면
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/listCompleteStudent")
	public String listCompleteStudent(StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		vo.setEnrlSts("|S|C|F|");

		CreateCourseVO createCourseVO = new CreateCourseVO();
		createCourseVO.setCrsCreCd(vo.getCrsCreCd());
		createCourseVO = createCourseService.viewCreateCourse(createCourseVO).getReturnVO();
		request.setAttribute("createCourseVO", createCourseVO);

		ProcessResultListVO<StudentVO> resultList = studentService.listStudentPageing(vo);
		
		request.setAttribute("studentList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());
		request.setAttribute("studentVO", vo);

		return "mng/student/student/complete_list_div";
	}

	/**
	 * 교번 관리 메인
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/eduNoStudentForm")
	public String eduNoStudentForm(StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		CreateCourseVO createCourseVO = new CreateCourseVO();
		createCourseVO.setCrsCreCd(vo.getCrsCreCd());
		createCourseVO = createCourseService.viewCreateCourse(createCourseVO).getReturnVO();
		request.setAttribute("createCourseVO", createCourseVO);

		CourseVO courseVO = new CourseVO();
		courseVO.setCrsCd(createCourseVO.getCrsCd());
		courseVO = courseService.view(courseVO).getReturnVO();
		request.setAttribute("courseVO", courseVO);

		//--- 분반 목록
		CreCrsDeclsVO creCrsDeclsVO = new CreCrsDeclsVO();
		creCrsDeclsVO.setCrsCreCd(vo.getCrsCreCd());
		List<CreCrsDeclsVO> creCrsDeclsList = creCrsDeclsService.list(creCrsDeclsVO).getReturnList();
		request.setAttribute("creCrsDeclsList", creCrsDeclsList);

		List<SysCodeVO> declsSortList = sysCodeMemService.getSysCodeList("EDUNO_SORT");
		request.setAttribute("declsSortList", declsSortList);

		request.setAttribute("vo", vo);
		request.setAttribute("paging", "Y");
		return "mng/student/student/eduno_form";
	}

	/**
	 * 교번 관리 목록 화면
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/listEduNoStudent")
	public String listEduNoStudent(StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		int curPage = Integer.parseInt(StringUtil.nvl(request.getParameter("curPage"),"1"));
		int listScale = Integer.parseInt(StringUtil.nvl(request.getParameter("listScale"),"20"));

//		ProcessResultListVO<StudentVO> resultList = studentService.listStudent(studentVO, curPage, listScale, 10);
		ProcessResultListVO<StudentVO> resultList = studentService.listStudentPageing(vo);

		request.setAttribute("studentList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());
		
		return "mng/student/student/eduno_list_div";
	}

	/**
	 * 분반 배정 관리 메인
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/declsStudentForm")
	public String declsStudentForm(StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		CreateCourseVO createCourseVO = new CreateCourseVO();
		createCourseVO.setCrsCreCd(vo.getCrsCreCd());
		createCourseVO = createCourseService.viewCreateCourse(createCourseVO).getReturnVO();
		request.setAttribute("createCourseVO", createCourseVO);

		CourseVO courseVO = new CourseVO();
		courseVO.setCrsCd(createCourseVO.getCrsCd());
		courseVO = courseService.view(courseVO).getReturnVO();
		request.setAttribute("courseVO", courseVO);

		//--- 분반 목록
		CreCrsDeclsVO creCrsDeclsVO = new CreCrsDeclsVO();
		creCrsDeclsVO.setCrsCreCd(vo.getCrsCreCd());
		List<CreCrsDeclsVO> creCrsDeclsList = creCrsDeclsService.list(creCrsDeclsVO).getReturnList();
		request.setAttribute("creCrsDeclsList", creCrsDeclsList);

		request.setAttribute("studentVO", vo);
		request.setAttribute("paging", "Y");
		return "mng/student/student/decls_form";
	}

	/**
	 * 분반 배정 관리 소스 목록 화면
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/listDeclsSource")
	public String listDeclsSource(StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultListVO<StudentVO> resultList = studentService.listStudent(vo);
		request.setAttribute("studentList", resultList.getReturnList());
		return "mng/student/student/decls_source_div";
	}

	/**
	 * 분반 배정 관리 타겟 목록 화면
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/listDeclsTarget")
	public String listDeclsTarget(StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultListVO<StudentVO> resultList = studentService.listStudent(vo);
		request.setAttribute("studentList", resultList.getReturnList());
		return "mng/student/student/decls_target_div";
	}

	/**
	 * 수강생 분반 변경 처리
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/editDecls")
	public String editDecls(StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String userList = request.getParameter("userList");
		Integer declsNo = Integer.parseInt(request.getParameter("declsNo"));

		ProcessResultVO<StudentVO> resultVO = studentService.editDecls(userList, declsNo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "student.message.student.changedecls.success"));
		} else {
			resultVO.setMessage(getMessage(request, "student.message.student.changedecls.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 수강 신청자/수료자 목록 조회
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/listEnrollStudentJSON")
	public String listEnrollStudentJSON( StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		int curPage = Integer.parseInt(StringUtil.nvl(request.getParameter("curPage"),"1"));
		int listScale = Integer.parseInt(StringUtil.nvl(request.getParameter("listScale"),"20"));
		vo.setCurPage(curPage);
		vo.setListScale(listScale);
		return JsonUtil
			.responseJson(response, studentService.listStudentPageing(vo));
//			.responseJson(response, studentService.listStudent(vo, curPage, listScale, 10));
	}

	/**
	 * 수강 신청자/수료자 목록 조회(페이징 사용하지 않음)
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/listEnrollStudent2")
	public String listEnrollStudent2( StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		return JsonUtil
			.responseJson(response, studentService.listStudent(vo));
	}

	/**
	 * 수강 신청 인증 처리
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/editConfirmStudent")
	public String editConfirmStudent(StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultVO<StudentVO> resultVO = studentService.confirmStudent(vo, request);
		int[] returnCnt = resultVO.getRetrunCnt();

		int iSuccess 	= 0;
		int iFail		= 0;
		int iTotal		= returnCnt.length;
		for(int i : returnCnt){
			if(i > 0){
				iSuccess++;
			}else{
				iFail++;
			}
		}
		String[] argsMessage = {iTotal+"", iSuccess+""};

		//-- 수강승인 성공자에게 메일 보내기..
		String orgCd = UserBroker.getUserOrgCd(request);
		OrgOrgInfoVO orgInfoVO = new OrgOrgInfoVO();
		orgInfoVO.setOrgCd(orgCd);
		orgInfoVO = orgOrgInfoService.view(orgInfoVO);

		CreateCourseVO createCourseVO = new CreateCourseVO();
		createCourseVO.setOrgCd(orgCd);
		createCourseVO.setCrsCreCd(vo.getCrsCreCd());
		createCourseVO = createCourseService.viewCreateCourse(createCourseVO).getReturnVO();

		String[] successUserNo = StringUtil.split(resultVO.getMessage(),"/");
		for(int i = 1; i < successUserNo.length; i++) {
			UsrUserInfoVO userInfoVO = new UsrUserInfoVO();
			userInfoVO.setUserNo(successUserNo[i]);
			userInfoVO = usrUserInfoService.view(userInfoVO);

//			MessageTransVO trans = new MessageTransVO(); =>  기존
			LogMsgTransLogVO trans = new LogMsgTransLogVO();

			trans.setRecvAddr(userInfoVO.getEmail());
			trans.setRecvNm(userInfoVO.getUserNm());
			trans.setRecvYn("Y"); //안받으면 못찾으니까 강제적으로 Y

//			MessageVO message = trans.getMessage();	=> 기존
			LogMsgLogVO message = trans.getMessage();
			message.setSendMenuCd(UserBroker.getMenuCode(request));
			message.setMsgDivCd("EMAIL");
			message.setSendAddr(orgInfoVO.getEmailAddr());
			message.setSendNm(orgInfoVO.getEmailNm());

			message.addSubTrans(trans);

			//-- 메일 데코레이션
			Map<String, Object> argu = new HashMap<String, Object>();
			argu.put("Name",userInfoVO.getUserNm());
			argu.put("UserID", userInfoVO.getUserId());
			argu.put("CourseName", createCourseVO.getCrsCreNm()+" ["+createCourseVO.getCreTerm()+"]");
			argu.put("CourseDuration", createCourseVO.getEnrlStartDttm()+"~"+createCourseVO.getEnrlEndDttm());
			argu.put("SendDate", DateTimeUtil.getCurrentString("yy.MM.dd"));
			orgEmailTplService.decorator(orgCd, "EM011", argu, message);

			try{
				logMsgLogService.addMessageWithSend(message);
			}catch (MessageNotificationException ex) {
			}
		}

		if(iTotal == iSuccess) {
			resultVO.setMessage(getMessage(request, "student.message.student.confirm.success"));
		} else {
			resultVO.setMessage(getMessage(request, "student.message.student.confirm.msg", argsMessage));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 수강 신청 취소 처리
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/editCancelStudent")
	public String editCancelStudent( StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);


		ProcessResultVO<StudentVO> resultVO = studentService.cancelStudent(vo, request);
		if(resultVO.getResult() > 0) {
			//-- 수강승인 성공자에게 메일 보내기..
			String orgCd = UserBroker.getUserOrgCd(request);
			OrgOrgInfoVO orgInfoVO = new OrgOrgInfoVO();
			orgInfoVO.setOrgCd(orgCd);
			orgInfoVO = orgOrgInfoService.view(orgInfoVO);

			CreateCourseVO createCourseVO = new CreateCourseVO();
			createCourseVO.setOrgCd(orgCd);
			createCourseVO.setCrsCreCd(vo.getCrsCreCd());
			createCourseVO = createCourseService.viewCreateCourse(createCourseVO).getReturnVO();

			String[] successUserNo = StringUtil.split(resultVO.getMessage(),"/");
			for(int i = 1; i < successUserNo.length; i++) {
				UsrUserInfoVO userInfoVO = new UsrUserInfoVO();
				userInfoVO.setUserNo(successUserNo[i]);
				userInfoVO = usrUserInfoService.view(userInfoVO);

				LogMsgTransLogVO trans = new LogMsgTransLogVO();

				trans.setRecvAddr(userInfoVO.getEmail());
				trans.setRecvNm(userInfoVO.getUserNm());
				trans.setRecvYn("Y"); //안받으면 못찾으니까 강제적으로 Y

				LogMsgLogVO message = trans.getMessage();
				message.setSendMenuCd(UserBroker.getMenuCode(request));
				message.setMsgDivCd("EMAIL");
				message.setSendAddr(orgInfoVO.getEmailAddr());
				message.setSendNm(orgInfoVO.getEmailNm());

				message.addSubTrans(trans);

				//-- 메일 데코레이션
				Map<String, Object> argu = new HashMap<String, Object>();
				argu.put("Name",userInfoVO.getUserNm());
				argu.put("UserID", userInfoVO.getUserId());
				argu.put("CourseName", createCourseVO.getCrsCreNm()+" ["+createCourseVO.getCreTerm()+"]");
				argu.put("CourseDuration", createCourseVO.getEnrlStartDttm()+"~"+createCourseVO.getEnrlEndDttm());
				argu.put("SendDate", DateTimeUtil.getCurrentString("yy.MM.dd"));
				orgEmailTplService.decorator(orgCd, "EM013", argu, message);

				try{
					logMsgLogService.addMessageWithSend(message);
				}catch (MessageNotificationException ex) {
				}
			}
			resultVO.setMessage(getMessage(request, "student.message.student.cancel.success"));
		} else {
			resultVO.setMessage(getMessage(request, "student.message.student.cancel.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 수강 신청 삭제 처리
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/deleteStudent")
	public String deleteStudent( StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);


		ProcessResultVO<StudentVO> resultVO = studentService.deleteStudent(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "student.message.student.delete.success"));
		} else {
			resultVO.setMessage(getMessage(request, "student.message.student.delete.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}


	/**
	 *  수료(미수료) 취소 처리
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/editCancelComplete")
	public String editCancelComplete(StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse respons) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultVO<StudentVO> resultVO = studentService.cancelComplete(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "student.message.student.cancelcomplete.success"));
		} else {
			resultVO.setMessage(getMessage(request, "student.message.student.cancelcomplete.failed"));
		}
		return JsonUtil.responseJson(respons, resultVO);
	}


	/**
	 * 수강 신청 등록 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addFormStudentPop")
	public String addFormStudentPop(StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse respons) throws Exception {
		commonVOProcessing(vo, request);

		//-- 개설 과정에 대한 정보를 검색하여 form에 담는다.
		CreateCourseVO createCourseVO = new CreateCourseVO();
		createCourseVO.setCrsCreCd(vo.getCrsCreCd());
		createCourseVO = createCourseService.viewCreateCourse(createCourseVO).getReturnVO();

		List<SysCodeVO> codeList = sysCodeMemService.getSysCodeList("PAYM_MTHD_CD");
		request.setAttribute("codeList", codeList);

		request.setAttribute("createCourseVO", createCourseVO);
		
		String curDate = DateTimeUtil.getDate();
		curDate = DateTimeUtil.getDateType(1, curDate, ".");
		request.setAttribute("curDate", curDate);

		request.setAttribute("gubun", "A");
		request.setAttribute("vo", vo);
		return "mng/student/student/student_write_pop";
	}

	/**
	 * 수강 신청 등록
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/addStudent")
	public String addStudent( StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		vo.setReceiptDt(StringUtil.ReplaceAll(vo.getReceiptDt(),".",""));
		vo.setPaymDttm(StringUtil.ReplaceAll(vo.getPaymDttm(),".",""));

		String orgCd = UserBroker.getUserOrgCd(request);
		OrgOrgInfoVO orgInfoVO = new OrgOrgInfoVO();
		orgInfoVO.setOrgCd(orgCd);
		orgInfoVO = orgOrgInfoService.view(orgInfoVO);
		
		CreateCourseVO createCourseVO = new CreateCourseVO();
		createCourseVO.setOrgCd(orgCd);
		createCourseVO.setCrsCreCd(vo.getCrsCreCd());
		CreateCourseVO viewCreateCourseVO = createCourseService.viewCreateCourse(createCourseVO).getReturnVO();
		vo.setCrsCd(viewCreateCourseVO.getCrsCd());//crsCd 세팅 (산인공용)

		ProcessResultVO<StudentVO> resultVO = studentService.addStudent(vo);
		if(resultVO.getResult() > 0) {
			////-- 수강승인 성공자에게 메일 보내기..
			
			UsrUserInfoVO userInfoVO = new UsrUserInfoVO();
			userInfoVO.setUserNo(vo.getUserNo());
			userInfoVO = usrUserInfoService.view(userInfoVO);

			LogMsgTransLogVO trans = new LogMsgTransLogVO();

			trans.setRecvAddr(userInfoVO.getEmail());
			trans.setRecvNm(userInfoVO.getUserNm());
			trans.setRecvYn("Y"); //안받으면 못찾으니까 강제적으로 Y

			LogMsgLogVO message = trans.getMessage();
			message.setSendMenuCd(UserBroker.getMenuCode(request));
			message.setMsgDivCd("EMAIL");
			message.setSendAddr(orgInfoVO.getEmailAddr());
			message.setSendNm(orgInfoVO.getEmailNm());

			message.addSubTrans(trans);

			String emailTplCd = "EM010";
			if("S".equals(vo.getEnrlSts())) {
				emailTplCd = "EM011";
			}

			//-- 메일 데코레이션
			Map<String, Object> argu = new HashMap<String, Object>();
			argu.put("Name",userInfoVO.getUserNm());
			argu.put("UserID", userInfoVO.getUserId());
			argu.put("CourseName", createCourseVO.getCrsCreNm()+" ["+createCourseVO.getCreTerm()+"]");
			argu.put("CourseDuration", createCourseVO.getEnrlStartDttm()+"~"+createCourseVO.getEnrlEndDttm());
			argu.put("SendDate", DateTimeUtil.getCurrentString("yy.MM.dd"));
			orgEmailTplService.decorator(orgCd, emailTplCd, argu, message);

			try{
				logMsgLogService.addMessageWithSend(message);
			}catch (MessageNotificationException ex) {
			}

			resultVO.setMessage(getMessage(request, "student.message.student.addenroll.success"));
		} else {
			resultVO.setMessage(getMessage(request, "student.message.student.addenroll.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 시용자 목록 조회 - 수강신청 검색용
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/listUser")
	public String listUser( StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		List<StudentVO> userList = studentService.listUser(vo).getReturnList();
		request.setAttribute("userList", userList);
		return "mng/student/student/user_list_div";
	}

	/**
	 * 수강생/수료생 명단 다운로드
	 * 정의된 해더에 따른 엑셀 필드만 다운로드 시킴.
	 * @return  ProcessResultVO
	 * 2015.12.08 jamfam
	 */
	@RequestMapping(value="/listExcelDownload")
	public String listExcelDownload( StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String excelHeader = StringUtil.nvl(request.getParameter("excelHeader"),"");
		String downloadType = StringUtil.nvl(request.getParameter("downloadType"),"ENROLL");
		String crsCreCd = StringUtil.nvl(request.getParameter("crsCreCd"),"");
		String orgCd = UserBroker.getUserOrgCd(request);
		String printMode = StringUtil.nvl(request.getParameter("printMode"),"EXCEL");

		vo.setOrgCd(orgCd);
		vo.setCrsCreCd(crsCreCd);
		String fileNamePrefix = "";
		String sheetName = "";
		//--- 수강승인자만 검색하도록 수강상태 셋팅
		if("COMPLETE".equals(downloadType)) {
			vo.setEnrlSts("|C|");
			fileNamePrefix = "Complete_List_";
			sheetName = getMessage(request, "student.title.student.list.complete");
		} else if("STUDENT".equals(downloadType)) {
			vo.setEnrlSts("|S|C|F|");
			fileNamePrefix = "Student_List_";
			sheetName = getMessage(request, "student.title.student.list.student");
		} else {
			vo.setEnrlSts("|E|");
			fileNamePrefix = "Enroll_List_";
			sheetName = getMessage(request, "student.title.student.list.enroll");
		}

		//-- 개설 과정 정보를 가져온다.
		CreateCourseVO createCourseVO = new CreateCourseVO();
		createCourseVO.setCrsCreCd(vo.getCrsCreCd());
		createCourseVO = createCourseService.viewCreateCourse(createCourseVO).getReturnVO();

		//-- 엑셀 출력 로그를 저장한다.
//		PrintLogVO printLogVO = new PrintLogVO(); => 기존
		LogPrnLogVO printLogVO = new LogPrnLogVO();
		printLogVO.setUserNo(UserBroker.getUserNo(request));
		printLogVO.setUserNm(UserBroker.getUserName(request));
		printLogVO.setPrnDoc("PRINT EXCEL : Student List ("+orgCd+", "+createCourseVO.getCrsCreCd()+")");
		printLogVO.setParam(vo.toString());
//		printLogService.addPrintLog(printLogVO);	=> 기존
		logPrnLogService.add(printLogVO);

		//-- 개인정보 출력로그 저장
		LogPrivateInfoInqLogVO pilVO = new LogPrivateInfoInqLogVO();
		pilVO.setOrgCd(orgCd);
		pilVO.setMenuCd(UserBroker.getMenuCode(request));
		pilVO.setUserNo(UserBroker.getUserNo(request));
		pilVO.setUserNm(UserBroker.getUserName(request));
		pilVO.setDivCd("ENRL_EXCEL");
		pilVO.setInqCts("EXCEL DOWNLOAD : Student List ("+orgCd+", "+createCourseVO.getCrsCreCd()+")");
		logPrivateInfoService.add(pilVO);

		String labelName = sheetName+" : "+createCourseVO.getCrsCreNm()+" ["+createCourseVO.getCreYear()+"/"+createCourseVO.getCreTerm()+"]";
		String condition = getMessage(request, "common.title.prnday")+": "+DateTimeUtil.getCurrentString("yyyy.MM.dd HH:mm:ss");

		ArrayList<String> titleList = new ArrayList<String>();
		titleList.add(getMessage(request, "common.title.no")); // 0
		titleList.add(getMessage(request, "student.title.student.decls")); // 1
		titleList.add(getMessage(request, "user.title.userinfo.name")); // 2
		titleList.add(getMessage(request, "user.title.userinfo.userid")); // 3
		titleList.add(getMessage(request, "user.title.userinfo.email")); // 4
		titleList.add(getMessage(request, "user.title.userinfo.birth")); // 5
		titleList.add(getMessage(request, "user.title.userinfo.sex")); // 6
		titleList.add(getMessage(request, "user.title.userinfo.phoneno")); // 7
		titleList.add(getMessage(request, "user.title.userinfo.mobileno")); // 8
		titleList.add(getMessage(request, "user.title.userinfo.fax")); // 9
		titleList.add(getMessage(request, "user.title.userinfo.deptname")); // 10
		titleList.add(getMessage(request, "user.title.userinfo.manage.usernmkana")); // 11
		titleList.add(getMessage(request, "user.title.userinfo.manage.usernmeng")); // 12
		titleList.add(getMessage(request, "user.title.userinfo.manage.area")); // 13
		titleList.add(getMessage(request, "user.title.userinfo.manage.userdiv")); // 14
		titleList.add(getMessage(request, "user.title.userinfo.manage.compphone")); // 15
		titleList.add(getMessage(request, "user.title.userinfo.manage.etcphone")); // 16
		titleList.add(getMessage(request, "user.title.userinfo.manage.compaddr")); // 17
		titleList.add(getMessage(request, "user.title.userinfo.manage.homeaddr")); // 18
		titleList.add(getMessage(request, "user.title.userinfo.manage.disablility")); // 19
		titleList.add(getMessage(request, "user.title.userinfo.manage.interest")); // 20
		titleList.add(getMessage(request, "user.title.userinfo.manage.memo")); // 21
		titleList.add(getMessage(request, "student.title.student.enrolldate")); // 22
		titleList.add(getMessage(request, "student.title.student.payment.mthd")); // 23
		titleList.add(getMessage(request, "student.title.student.payment.fee")); // 24
		titleList.add(getMessage(request, "student.title.student.payment.status")); // 25
		titleList.add(getMessage(request, "student.title.student.getscore")); // 26
		titleList.add(getMessage(request, "student.title.student.scoretop.user")); // 27
		titleList.add(getMessage(request, "student.title.student.completeno")); // 28

		titleList.add(getMessage(request, "lecture.title.contents.study.ratio")); // 29
		titleList.add(getMessage(request, "student.title.result.score.final")); // 30

		titleList.add(getMessage(request, "common.title.stats")); // 31

		HashMap<String, String> titles = new HashMap<String, String>();
		titles.put("sexNmM", getMessage(request, "user.title.userinfo.sex_m"));
		titles.put("sexNmF", getMessage(request, "user.title.userinfo.sex_f"));
		titles.put("paymStsNmY", getMessage(request, "student.title.student.payment.complete"));
		titles.put("paymStsNmN", getMessage(request, "student.title.student.payment.nocomplete"));


		if("EXCEL".equals(printMode)) {
			response.setHeader("Content-Disposition", "attachment;filename="+fileNamePrefix+vo.getCrsCreCd()+".xlsx;");
			response.setHeader( "Content-Transfer-Coding", "binary" );
			response.setContentType("application/vnd.ms-excel;charset=utf-8");
			studentExcelService.listExcelDownload(vo, createCourseVO, sheetName, labelName, condition, excelHeader, titleList, request, response.getOutputStream(), titles);
		} else {
			// 파일 다운로드 설정
			String fileName = URLEncoder.encode(getMessage(request, "org.title.crscert"), "UTF-8"); // 파일명이 한글일 땐 인코딩 필요
			response.setHeader("Content-Transper-Encoding", "binary");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "inline; filename=" + fileNamePrefix + vo.getCrsCreCd() + ".pdf");

			studentPDFService.listPDFDownload(vo, createCourseVO, sheetName, labelName, condition, excelHeader, titleList, request, response.getOutputStream());
		}

		return null;
	}

	/**
	 * 출석부 엑셀 다운로드
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/listExcelDownloadAttend")
	public String listExcelDownloadAttend(StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		//--- 수강승인자만 검색하도록 수강상태 셋팅
		vo.setEnrlSts("|S|C|F|");
		//-- 개설 과정 정보를 가져온다.
		CreateCourseVO createCourseVO = new CreateCourseVO();
		createCourseVO.setCrsCreCd(vo.getCrsCreCd());
		createCourseVO = createCourseService.viewCreateCourse(createCourseVO).getReturnVO();
		//-- 엑셀 출력 로그를 저장한다.
//		PrintLogVO printLogVO = new PrintLogVO();	=> 기존
		LogPrnLogVO printLogVO = new LogPrnLogVO();
		printLogVO.setUserNo(UserBroker.getUserNo(request));
		printLogVO.setUserNm(UserBroker.getUserName(request));
		printLogVO.setPrnDoc("엑셀출력 : 수강관리 > 교번관리 > 출석부 출력 ("+createCourseVO.getCrsCreNm()+"과정 "+createCourseVO.getCreTerm()+")");
		printLogVO.setParam(vo.toString());
//		printLogService.addPrintLog(printLogVO);	=> 기존
		logPrnLogService.add(printLogVO);
		response.setHeader("Content-Disposition", "attachment;filename=AttendTable_"+vo.getCrsCreCd()+".xls;");
		response.setHeader( "Content-Transfer-Coding", "binary" );
		response.setContentType("application/octet-stream");
		studentExcelService.listExcelDownloadAttend(vo, createCourseVO, response.getOutputStream());
		return null;
	}

	/**
	 * 수료증 출력
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/viewPrintCerti")
	public String viewPrintCerti( StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		CreateCourseVO createCourseVO = new CreateCourseVO();
		createCourseVO.setCrsCreCd(vo.getCrsCreCd());
		createCourseVO = createCourseService.viewCreateCourse(createCourseVO).getReturnVO();
		request.setAttribute("createCourseVO", createCourseVO);
		
		CourseVO courseVO = new CourseVO();
		courseVO.setCrsCd(createCourseVO.getCrsCd());
		courseVO = courseService.view(courseVO).getReturnVO();
		request.setAttribute("courseVO", courseVO);
		
		String nowDttm = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
		String year = StringUtil.substring(nowDttm, 0, 4);
		String month = StringUtil.substring(nowDttm, 4, 6);
		String day = StringUtil.substring(nowDttm, 6, 8);
		request.setAttribute("year", year);
		request.setAttribute("month", month);
		request.setAttribute("day", day);
		
		//-- 수강생 정보를 가져온다.
		vo = studentService.viewStudent(vo).getReturnVO();
		request.setAttribute("vo", vo);

		String totalEduTm = "0";
		if("ON".equals(courseVO.getCrsOperMthd())) {
			totalEduTm = createCourseVO.getOnlnEduTm();
		} else if("OF".equals(courseVO.getCrsOperMthd())) {
			totalEduTm = createCourseVO.getOflnEduTm();
		} else {
			totalEduTm = Integer.toString(Integer.parseInt(StringUtil.nvl(createCourseVO.getOnlnEduTm(),"0")) + Integer.parseInt(StringUtil.nvl(createCourseVO.getOflnEduTm(),"0")));
		}
		createCourseVO.setEduTm(totalEduTm);
		
		//수료증 백그라운드 이미지 가지고 오기
//		CodeVO codeVO = new CodeVO();
//		codeVO = codeService.viewCode("OPER_ORG_CD",courseVO.getOperOrgCd()).getReturnVO();
//		request.setAttribute("codeVO", codeVO);

		return "mng/student/student/print_certification_pop";
	}


	/**
	 * 자동 수료 처리
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/editAutoComplete")
	public String editAutoComplete( StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception{
		commonVOProcessing(vo, request);


		ProcessResultVO<StudentVO> resultVO = studentService.autoComplete(vo);
		if(resultVO.getResult() > 0) {
			//-- 수강승인 성공자에게 메일 보내기..
			String orgCd = UserBroker.getUserOrgCd(request);
			OrgOrgInfoVO orgInfoVO = new OrgOrgInfoVO();
			orgInfoVO.setOrgCd(orgCd);
			orgInfoVO = orgOrgInfoService.view(orgInfoVO);

			CreateCourseVO createCourseVO = new CreateCourseVO();
			createCourseVO.setOrgCd(orgCd);
			createCourseVO.setCrsCreCd(vo.getCrsCreCd());
			createCourseVO = createCourseService.viewCreateCourse(createCourseVO).getReturnVO();

			String[] userNo = StringUtil.split(resultVO.getMessage(),"/");
			for(int i = 1; i < userNo.length; i++) {

				UsrUserInfoVO userInfoVO = new UsrUserInfoVO();
				userInfoVO.setUserNo(userNo[i]);
				userInfoVO = usrUserInfoService.view(userInfoVO);

				LogMsgTransLogVO trans = new LogMsgTransLogVO();

				trans.setRecvAddr(userInfoVO.getEmail());
				trans.setRecvNm(userInfoVO.getUserNm());
				trans.setRecvYn("Y"); //안받으면 못찾으니까 강제적으로 Y

//				MessageVO message = trans.getMessage();	=> 기존
				LogMsgLogVO message = trans.getMessage();
				message.setSendMenuCd(UserBroker.getMenuCode(request));
				message.setMsgDivCd("EMAIL");
				message.setSendAddr(orgInfoVO.getEmailAddr());
				message.setSendNm(orgInfoVO.getEmailNm());

				message.addSubTrans(trans);

				//-- 메일 데코레이션
				Map<String, Object> argu = new HashMap<String, Object>();
				argu.put("Name",userInfoVO.getUserNm());
				argu.put("UserID", userInfoVO.getUserId());
				argu.put("CourseName", createCourseVO.getCrsCreNm()+" ["+createCourseVO.getCreYear()+"/"+createCourseVO.getCreTerm()+"]");
				argu.put("CourseDuration", createCourseVO.getEnrlStartDttm()+"~"+createCourseVO.getEnrlEndDttm());
				argu.put("SendDate", DateTimeUtil.getCurrentString("yy.MM.dd"));
				orgEmailTplService.decorator(orgCd, "EM012", argu, message);

				try{
					logMsgLogService.addMessageWithSend(message);
				}catch (MessageNotificationException ex) {
				}
			}
			resultVO.setMessage(getMessage(request, "student.message.student.autocomplete.success"));
		} else {
			resultVO.setMessage(getMessage(request, "student.message.student.autocomplete.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 선택 수료 처리
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/edutCheckComplete")
	public String edutCheckComplete( StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultVO<StudentVO> resultVO = studentService.checkComplete(vo);
		if(resultVO.getResult() > 0) {
			//-- 수강승인 성공자에게 메일 보내기..
			String orgCd = UserBroker.getUserOrgCd(request);
			OrgOrgInfoVO orgInfoVO = new OrgOrgInfoVO();
			orgInfoVO.setOrgCd(orgCd);
			orgInfoVO = orgOrgInfoService.view(orgInfoVO);

			CreateCourseVO createCourseVO = new CreateCourseVO();
			createCourseVO.setOrgCd(orgCd);
			createCourseVO.setCrsCreCd(vo.getCrsCreCd());
			createCourseVO = createCourseService.viewCreateCourse(createCourseVO).getReturnVO();

			String[] userNo = StringUtil.split(resultVO.getMessage(),"/");
			for(int i = 1; i < userNo.length; i++) {

				UsrUserInfoVO userInfoVO = new UsrUserInfoVO();
				userInfoVO.setUserNo(userNo[i]);
				userInfoVO = usrUserInfoService.view(userInfoVO);

				LogMsgTransLogVO trans = new LogMsgTransLogVO();

				trans.setRecvAddr(userInfoVO.getEmail());
				trans.setRecvNm(userInfoVO.getUserNm());
				trans.setRecvYn("Y"); //안받으면 못찾으니까 강제적으로 Y

				LogMsgLogVO message = trans.getMessage();
				message.setSendMenuCd(UserBroker.getMenuCode(request));
				message.setMsgDivCd("EMAIL");
				message.setSendAddr(orgInfoVO.getEmailAddr());
				message.setSendNm(orgInfoVO.getEmailNm());

				message.addSubTrans(trans);

				//-- 메일 데코레이션
				Map<String, Object> argu = new HashMap<String, Object>();
				argu.put("Name",userInfoVO.getUserNm());
				argu.put("UserID", userInfoVO.getUserId());
				argu.put("CourseName", createCourseVO.getCrsCreNm()+" ["+createCourseVO.getCreYear()+"/"+createCourseVO.getCreTerm()+"]");
				argu.put("CourseDuration", createCourseVO.getEnrlStartDttm()+"~"+createCourseVO.getEnrlEndDttm());
				argu.put("SendDate", DateTimeUtil.getCurrentString("yy.MM.dd"));
				orgEmailTplService.decorator(orgCd, "EM012", argu, message);

				try{
					logMsgLogService.addMessageWithSend(message);
				}catch (MessageNotificationException ex) {
				}
			}
			resultVO.setMessage(getMessage(request, "student.message.student.chkcomplete.success"));
		} else {
			resultVO.setMessage(getMessage(request, "student.message.student.chkcomplete.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 수강 결과 등록
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/addEduNoAll")
	public String addEduNoAll(StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String strStdNo = StringUtil.nvl(request.getParameter("strStdNo"));
		String strEduNo = StringUtil.nvl(request.getParameter("eduNo"));

		ProcessResultVO<StudentVO> resultVO = studentService.addEduNoAll(vo, strStdNo, strEduNo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request,  "student.message.student.eduno.success"));
		} else {
			resultVO.setMessage(getMessage(request,  "student.message.student.eduno.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 명찰 출력
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/viewPrintNameCard")
	public String viewPrintNameCard( StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {
		commonVOProcessing(vo, request);
		return "print_namecard";
	}

	/**
	 * 교육생카드 출력
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/viewPrintStdCardPop")
	public String viewPrintStdCardPop( StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {
		commonVOProcessing(vo, request);
		return"print_stdcard";
	}

	/**
	 * 교육생의 상세 정보를 검색한다.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/viewStudentPop")
	public String viewStudentPop( StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String stayYn = vo.getStayYn();
		ProcessResultVO<StudentVO> resultVO = studentService.viewStudent(vo);
		vo =resultVO.getReturnVO();

		vo.setAuditEndDttm(this.addDateFormatStr(vo.getAuditEndDttm()));
		vo.setEnrlEndDttm(this.addDateFormatStr(vo.getEnrlEndDttm()));
		vo.setEnrlStartDttm(this.addDateFormatStr(vo.getEnrlStartDttm()));
		vo.setEnrlCancelDttm(this.addDateFormatStr(vo.getEnrlCancelDttm()));
		vo.setEnrlCpltDttm(this.addDateFormatStr(vo.getEnrlCpltDttm()));
		vo.setEnrlAplcDttm(this.addDateFormatStr(vo.getEnrlAplcDttm()));
		vo.setEnrlCertDttm(this.addDateFormatStr(vo.getEnrlCertDttm()));
		vo.setReceiptDt(this.addDateFormatStr(vo.getReceiptDt()));
		vo.setPaymDttm(this.addDateFormatStr(vo.getPaymDttm()));
		vo.setStayYn(stayYn);

		request.setAttribute("vo", vo);
		
		CreateCourseVO createCourseVO = new CreateCourseVO();
		createCourseVO.setCrsCreCd(vo.getCrsCreCd());
		createCourseVO = createCourseService.viewCreateCourse(createCourseVO).getReturnVO();
		request.setAttribute("createCourseVO", createCourseVO);

		CourseVO courseVO = new CourseVO();
		courseVO.setCrsCd(createCourseVO.getCrsCd());
		courseVO = courseService.view(courseVO).getReturnVO();
		request.setAttribute("courseVO", courseVO);

		List<SysCodeVO> codeList = sysCodeMemService.getSysCodeList("PAYM_MTHD_CD");
		request.setAttribute("codeList", codeList);

		return "mng/student/student/view_student_pop";
	}

	/**
	 * 데이터날짜 타입의 데이터를 .로 구분하여 반환한다.(등록시엔 제거하고 16자리로 맞춰줘야함)
	 * @param fileName
	 * @return
	 */
	@RequestMapping(value="/addDateFormatStr")
	public String addDateFormatStr(String str) {
		if(str == null || "".equals(str)) return "";
        return  str.substring(0, 4) + "." + str.substring(4, 6) + "." + str.substring(6, 8);
	}

	/**
	 * 교육생의 정보를 수정한다.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/editStudent")
	public String editStudent( StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String auditEndDttm   = StringUtil.ReplaceAll(vo.getAuditEndDttm(),".","");
		String enrlEndDttm    = StringUtil.ReplaceAll(vo.getEnrlEndDttm(),".","");
		String enrlStartDttm  = StringUtil.ReplaceAll(vo.getEnrlStartDttm(),".","");
		String enrlCancelDttm = StringUtil.ReplaceAll(vo.getEnrlCancelDttm(),".","");
		String enrlCpltDttm   = StringUtil.ReplaceAll(vo.getEnrlCpltDttm(),".","");
		String enrlAplcDttm   = StringUtil.ReplaceAll(vo.getEnrlAplcDttm(),".","");
		String enrlCertDttm   = StringUtil.ReplaceAll(vo.getEnrlCertDttm(),".","");
		String receiptDt   	  = StringUtil.ReplaceAll(vo.getReceiptDt(),".","");
		String paymDttm		  = StringUtil.ReplaceAll(vo.getPaymDttm(), ".", "");
		if(!"".equals(auditEndDttm) && auditEndDttm != null)  auditEndDttm  = auditEndDttm+"235959";
		if(!"".equals(enrlEndDttm) && enrlEndDttm != null)   enrlEndDttm   = enrlEndDttm+"235959";
		if(!"".equals(enrlStartDttm) && enrlStartDttm != null) enrlStartDttm = enrlStartDttm+"000001";
		if(!"".equals(enrlCancelDttm) && enrlCancelDttm != null)enrlCancelDttm = enrlCancelDttm+"000001";
		if(!"".equals(enrlCpltDttm) && enrlCancelDttm != null)  enrlCpltDttm  = enrlCpltDttm+"000001";
		if(!"".equals(enrlAplcDttm) && enrlAplcDttm != null)  enrlAplcDttm  = enrlAplcDttm+"000001";
		if(!"".equals(enrlCertDttm) && enrlCertDttm != null)  enrlCertDttm  = enrlCertDttm+"000001";

		//-- 수강생의 기존 정보를 가져온다.
		StudentVO sVO = studentService.viewStudent(vo).getReturnVO();

		//-- 변경된값 셋팅
		sVO.setAuditEndDttm(auditEndDttm);
		sVO.setEnrlEndDttm(enrlEndDttm);
		sVO.setEnrlStartDttm(enrlStartDttm);
		sVO.setEnrlCancelDttm(enrlCancelDttm);
		sVO.setEnrlCpltDttm(enrlCpltDttm);
		sVO.setEnrlAplcDttm(enrlAplcDttm);
		sVO.setEnrlCertDttm(enrlCertDttm);

		sVO.setPaymMthdCd(vo.getPaymMthdCd());
		sVO.setPaymPrice(vo.getPaymPrice());
		sVO.setPaymStsCd(vo.getPaymStsCd());
		sVO.setPaymDttm(paymDttm);
		sVO.setPaymNo(vo.getPaymNo());
		if(ValidationUtils.isNotEmpty(vo.getUeinsAplcYn()))
			sVO.setUeinsAplcYn(vo.getUeinsAplcYn());
		else
			sVO.setUeinsAplcYn(vo.getUeinsAplyYn());
		sVO.setUeinsAplyYn(vo.getUeinsAplyYn());
		sVO.setRefundPrice(vo.getRefundPrice());
		sVO.setDocReceiptYn(vo.getDocReceiptYn());
		sVO.setDocReceiptNo(vo.getDocReceiptNo());
		sVO.setReceiptDt(receiptDt);
		sVO.setReceiptUserNm(vo.getReceiptUserNm());

		ProcessResultVO<StudentVO> resultVO = studentService.editStudent(sVO);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "student.message.student.edit.enroll.success"));
		} else {
			resultVO.setMessage(getMessage(request, "student.message.student.edit.enroll.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}
	
	/**
	 * 교육생의 IDE 정보를 수정한다.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/editStudentUrl")
	public String editStudentUrl( StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		ProcessResultVO<StudentVO> resultVO = studentService.editStudentUrl(vo);
		
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "student.message.student.edit.enroll.success"));
		} else {
			resultVO.setMessage(getMessage(request, "student.message.student.edit.enroll.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 교육생 일괄등록 (엑셀 업로드 폼)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addFormStudentExcel")
	public String addFormStudentExcel(StudentVO vo,  Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("gubun", "A");
		request.setAttribute("vo", vo);
		return "mng/student/student/excel_upload_pop";
	}


	/**
	 * 수강생 엑셀 파일 업로드 하고
	 * Validation을 check 한다.
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/addCheckStudentExcel")
	public String addCheckStudentExcel(StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {
		commonVOProcessing(vo, request);
		String fileName = StringUtil.nvl(request.getParameter("fileName"));
		String filePath = StringUtil.nvl(request.getParameter("filePath"));

		List<StudentExcelVO> studentExcelList = null;
		try {
			studentExcelList = studentExcelService.listStudentExcel(vo.getCrsCreCd(), fileName, filePath).getReturnList();
		} catch (ServiceProcessException ex) { // 파일처리 오류..
			setAlertMessage(request, "엑셀 파일 처리중 다음 오류가 발생하였습니다.\n\n" + ex.getMessage());
		} finally {
			FileUtil.delFile(filePath, fileName); // -- 사용한 파일 지움.
		}
		request.setAttribute("studentExcelList", studentExcelList);
		request.setAttribute("vo", vo);
		return "mng/student/student/excel_upload_list_pop";
	}

	/**
	 * 수강생 엑셀 업로드
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/addStudentExcel")
	public String addStudentExcel(StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {
		commonVOProcessing(vo, request);

		String[] errorCode = request.getParameterValues("studentExcelVO.errorCode");
		String[] userId = request.getParameterValues("studentExcelVO.userId");
		String[] userNm = request.getParameterValues("studentExcelVO.userNm");
		String[] userPass = request.getParameterValues("studentExcelVO.userPass");
		String[] userSts = request.getParameterValues("studentExcelVO.userSts");
		String[] compNm = request.getParameterValues("studentExcelVO.compNm");
		String[] birth = request.getParameterValues("studentExcelVO.birth");
		String[] sexCd = request.getParameterValues("studentExcelVO.sexCd");
		String[] email = request.getParameterValues("studentExcelVO.email");
		String[] mobileNo = request.getParameterValues("studentExcelVO.mobileNo");
		String[] homePhoneno = request.getParameterValues("studentExcelVO.homePhoneno");
		String[] homeAddrDivCd = request.getParameterValues("studentExcelVO.homeAddrDivCd");
		String[] homePostNo = request.getParameterValues("studentExcelVO.homePostNo");
		String[] homeAddr1 = request.getParameterValues("studentExcelVO.homeAddr1");
		String[] homeAddr2 = request.getParameterValues("studentExcelVO.homeAddr2");

		List<StudentExcelVO> studentExcelList = new ArrayList<StudentExcelVO>();
		for(int i=0; i < userId.length; i++) {
			//-- 오류가 없는 것들만 처리 함.
			if(ValidationUtils.isEmpty(errorCode[i])) {
				StudentExcelVO seVO = new StudentExcelVO();
				seVO.setUserId(userId[i]);
				seVO.setUserNm(userNm[i]);
				seVO.setUserPass(userPass[i]);
				seVO.setUserSts(userSts[i]);
				seVO.setCompNm(compNm[i]);
				seVO.setBirth(birth[i]);
				seVO.setSexCd(sexCd[i]);
				seVO.setEmail(email[i]);
				seVO.setMobileNo(mobileNo[i]);
				seVO.setHomePhoneno(homePhoneno[i]);
				seVO.setHomeAddrDivCd(homeAddrDivCd[i]);
				seVO.setHomePostNo(homePostNo[i]);
				seVO.setHomeAddr1(homeAddr1[i]);
				seVO.setHomeAddr2(homeAddr2[i]);
				studentExcelList.add(seVO);
			}
		}

		ProcessResultVO<StudentVO> result = null;
		try {
			result = studentExcelService.addStudentExcel(studentExcelList, vo);
		} catch (Exception e) {
			result = new ProcessResultVO<StudentVO>().setResultFailed();
		}

		return JsonUtil.responseJson( response, result);

	}

	/**
	 * 교재 목차 목록 화면
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/listStdPrgrRatioDetail")
	public String listStdPrgrRatioDetail( StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		commonVOProcessing(vo, request);
		CreateOnlineSubjectVO onsbjVO = new CreateOnlineSubjectVO();
		onsbjVO.setCrsCreCd(vo.getCrsCreCd());

		BookmarkVO bookmarkVO = new BookmarkVO();
		bookmarkVO.setStdNo(vo.getStdNo());
		bookmarkVO.setCrsCreCd(vo.getCrsCreCd());

		//-- 온라인 과정 목록 가져오기
		List<CreateOnlineSubjectVO> sbjList = createCourseSubjectService.listOnlineSubject(onsbjVO).getReturnList();
		request.setAttribute("sbjList", sbjList);
		if(StringUtil.isNull(bookmarkVO.getSbjCd())) bookmarkVO.setSbjCd(sbjList.get(0).getSbjCd());

		List<BookmarkVO> bookmarkList = bookmarkService.listBookmark(bookmarkVO).getReturnList();
		request.setAttribute("bookmarkList", bookmarkList);
		request.setAttribute("vo", vo);
		
		return "mng/student/student/student_prgr_list_dtl_pop";
	}

	/**
	 * 환불 관리 메인
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/repayStudentMain")
	public String repayStudentMain(StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
	    List<SysCodeVO> repayStsList = sysCodeMemService.getSysCodeList("REPAY_STS_CD", UserBroker.getLocaleKey(request));
		request.setAttribute("repayStsList", repayStsList);

		CreateCourseVO createCourseVO = new CreateCourseVO();
		createCourseVO.setCrsCreCd(vo.getCrsCreCd());
		createCourseVO = createCourseService.viewCreateCourse(createCourseVO).getReturnVO();
		request.setAttribute("createCourseVO", createCourseVO);

		CourseVO courseVO = new CourseVO();
		courseVO.setCrsCd(createCourseVO.getCrsCd());
		courseVO = courseService.view(courseVO).getReturnVO();
		request.setAttribute("courseVO", courseVO);
		request.setAttribute("studentVO", vo);
		request.setAttribute("paging", "Y");
		
		return "mng/student/student/repay_main";
	}

	/**
	 * 환불 관리 학습자 목록
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/listRepayStudent")
	public String listRepayStudent(StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		vo.setSearchMode("REPAY"); //-- 환요청자 및 환불자 관련

		int curPage = Integer.parseInt(StringUtil.nvl(request.getParameter("curPage"),"1"));
		int listScale = Integer.parseInt(StringUtil.nvl(request.getParameter("listScale"),"20"));

//		ProcessResultListVO<StudentVO> resultList = studentService.listStudent(vo, curPage, listScale, 10);
		ProcessResultListVO<StudentVO> resultList = studentService.listStudent(vo);

		request.setAttribute("studentList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());

		return "mng/student/student/repay_list_div";
	}

	/**
	 * 환불 처리 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/editFormRepayPop")
	public String editFormRepayPop(StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
        List<SysCodeVO> repayStsCdList = sysCodeMemService.getSysCodeList("REPAY_STS_CD", UserBroker.getLocaleKey(request));

		vo = studentService.viewStudent(vo).getReturnVO();

		vo.setAuditEndDttm(this.addDateFormatStr(vo.getAuditEndDttm()));
		vo.setEnrlEndDttm(this.addDateFormatStr(vo.getEnrlEndDttm()));
		vo.setEnrlStartDttm(this.addDateFormatStr(vo.getEnrlStartDttm()));
		vo.setEnrlCancelDttm(this.addDateFormatStr(vo.getEnrlCancelDttm()));
		vo.setEnrlCpltDttm(this.addDateFormatStr(vo.getEnrlCpltDttm()));
		vo.setEnrlAplcDttm(this.addDateFormatStr(vo.getEnrlAplcDttm()));
		vo.setEnrlCertDttm(this.addDateFormatStr(vo.getEnrlCertDttm()));
		vo.setReceiptDt(this.addDateFormatStr(vo.getReceiptDt()));
		vo.setPaymDttm(this.addDateFormatStr(vo.getPaymDttm()));
		vo.setRepayReqDttm(this.addDateFormatStr(vo.getRepayReqDttm()));
		vo.setRepayDttm(this.addDateFormatStr(vo.getRepayDttm()));

		//String curDate = DateTimeUtil.getDate();
		//curDate = DateTimeUtil.getDateType(1, curDate, ".");
		//if(ValidationUtils.isEmpty(studentVO.getRepayDttm())) studentVO.setRepayDttm(curDate);

		CreateCourseVO createCourseVO = new CreateCourseVO();
		createCourseVO.setCrsCreCd(vo.getCrsCreCd());
		createCourseVO = createCourseService.viewCreateCourse(createCourseVO).getReturnVO();
		request.setAttribute("createCourseVO", createCourseVO);
		request.setAttribute("repayStsCdList", repayStsCdList);
		
		request.setAttribute("vo", vo);
		return "mng/student/student/repay_edit_pop";
		
	}

	/**
	 * 환불 처리
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/editRepay")
	public String editRepay( StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String repayDttm		  = StringUtil.ReplaceAll(vo.getRepayDttm(), ".", "");
		if(!"".equals(repayDttm) && repayDttm != null)  repayDttm  = repayDttm+"000001";

		//-- 수강생의 기존 정보를 가져온다.
		StudentVO sVO = studentService.viewStudent(vo).getReturnVO();

		//-- 변경된값 셋팅
		sVO.setRepayDttm(repayDttm);
		sVO.setRepayStsCd(vo.getRepayStsCd());

		// 환불 요청일 경우 환불요청일 값
		if("REFUND001".equals(vo.getRepayStsCd())){
			// 환불 요청일이 null 인 경우만
			if(ValidationUtils.isEmpty(sVO.getRepayReqDttm())) {
				String currentDttm = DateTimeUtil.getCurrentString();
				sVO.setRepayReqDttm(currentDttm);
			}
		}

		// 환불 완료 처리가 아닌 경우 환불 완료일은 삭제함.
		if(!"REFUND003".equals(vo.getRepayStsCd())){
			sVO.setRepayDttm("");
		}

		ProcessResultVO<StudentVO> resultVO = studentService.editStudent(sVO);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "student.message.student.edit.enroll.success"));
		} else {
			resultVO.setMessage(getMessage(request, "student.message.student.edit.enroll.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 성적 우수자 선정
	 * @author twkim
	 * @date 2013. 10. 30.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	@RequestMapping(value="/chooseTopStudent")
	public String chooseTopStudent( StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		vo.setScoreEcltYn("Y");

		ProcessResultVO<StudentVO> resultVO = studentService.editScoreEclt(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "student.message.student.add.scoretop.success"));
		} else {
			resultVO.setMessage(getMessage(request, "student.message.student.add.scoretop.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 성적 우수자 해제
	 * @author twkim
	 * @date 2013. 10. 30.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	@RequestMapping(value="/clearTopStudent")
	public String clearTopStudent( StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		vo.setScoreEcltYn("N");

		ProcessResultVO<StudentVO> resultVO = studentService.editScoreEclt(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "student.message.student.cancel.scoretop.success"));
		} else {
			resultVO.setMessage(getMessage(request, "student.message.student.cancel.scoretop.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 환불 결과 상세 보기
	 * @author twkim
	 * @date 2014. 1. 8.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	@RequestMapping(value="/readResultRepay")
	public String readResultRepay( StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		
		commonVOProcessing(vo, request);

		vo = studentService.viewStudent(vo).getReturnVO();

		SysCodeVO codeVO = sysCodeMemService.getCode("REPAY_RSLT_CD", vo.getRepayRsltCd());

		//결과 제목
		request.setAttribute("rslt_title", codeVO.getCodeNm());
		//결과 내용
		request.setAttribute("rslt_desc", codeVO.getCodeDesc());

		return "mng/student/student/result_replay_pop";
	}



	/**
	 * 수료증 출력 (리포트용)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/printCertification")
	public String printCertification( StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		commonVOProcessing(vo, request);
		
		//-- 학습자 정보를 가져온다.
		vo = studentService.viewStudent(vo).getReturnVO();

		//-- 개설 과정 정보를 가져온다.
		CreateCourseVO createCourseVO = new CreateCourseVO();
		createCourseVO.setCrsCreCd(vo.getCrsCreCd());
		createCourseVO = createCourseService.viewCreateCourse(createCourseVO).getReturnVO();


		//-- 과정 정보를 가져온다.
		CourseVO courseVO = new CourseVO();
		courseVO.setCrsCd(createCourseVO.getCrsCd());
		courseVO = courseService.view(courseVO).getReturnVO();


		//-- 리포트 경로를 가져온다.
		String reportUrl = sysCfgService.getValue("REPORTSVR", "REPORTURL");
		request.setAttribute("reportUrl", reportUrl);

		String moeCrsCd = sysCfgService.getValue("MOECOURSE", "COURSE_CD");

		String reportFile = "";
		if(moeCrsCd.equals(createCourseVO.getCrsCd())) {
			//-- 교육부 과정
			reportFile = "certiCard10";
		} else if ("Y".equals(createCourseVO.getUeinsYn())) {
			//-- 고용보험 환급 과정인 경우
			if(Integer.parseInt(StringUtil.ReplaceAll(createCourseVO.getEnrlStartDttm(),".","")) >= Integer.parseInt("20110301")) {
				reportFile = "certiCard08";
			} else {
				if(Integer.parseInt(StringUtil.ReplaceAll(createCourseVO.getEnrlStartDttm(),".","")) >= Integer.parseInt("20090301")) {
					reportFile = "certiCard05";
				} else {
					reportFile = "certiCard02";
				}
			}
		} else if(courseVO.getCrsCtgrPath().contains("040000000")) {
			//-- 사이버 과정의 경우
			if(Integer.parseInt(StringUtil.ReplaceAll(createCourseVO.getEnrlStartDttm(),".","")) >= Integer.parseInt("20110301")) {
				reportFile = "certiCard09";
			} else {
				if(Integer.parseInt(StringUtil.ReplaceAll(createCourseVO.getEnrlStartDttm(),".","")) >= Integer.parseInt("20090301")) {
					reportFile = "certiCard06";
				} else {
					reportFile = "certiCard03";
				}
			}
		} else {
			if(Integer.parseInt(StringUtil.ReplaceAll(createCourseVO.getEnrlStartDttm(),".","")) >= Integer.parseInt("20110301")) {
				reportFile = "certiCard07";
			} else {
				if(Integer.parseInt(StringUtil.ReplaceAll(createCourseVO.getEnrlStartDttm(),".","")) >= Integer.parseInt("20090301")) {
					reportFile = "certiCard04";
				} else {
					reportFile = "certiCard01";
				}
			}
		}
		request.setAttribute("reportFile", reportFile);
		request.setAttribute("stdNo", vo.getStdNo());

		return "mng/student/student/print_certification_report";
	}

	/**
	 * 수료증 출력 (PDF)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/printCertificationMulti")
	public String printCertificationMulti( StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);

		//-- 개설 과정 정보를 가져온다.
		CreateCourseVO createCourseVO = new CreateCourseVO();
		createCourseVO.setCrsCreCd(vo.getCrsCreCd());
		createCourseVO = createCourseService.viewCreateCourse(createCourseVO).getReturnVO();


		//-- 과정 정보를 가져온다.
		CourseVO courseVO = new CourseVO();
		courseVO.setCrsCd(createCourseVO.getCrsCd());
		courseVO = courseService.view(courseVO).getReturnVO();


		String totalEduTm = "0";
		if("ON".equals(courseVO.getCrsOperMthd())) {
			totalEduTm = createCourseVO.getOnlnEduTm();
		} else if("OF".equals(courseVO.getCrsOperMthd())) {
			totalEduTm = createCourseVO.getOflnEduTm();
		} else {
			totalEduTm = Integer.toString(Integer.parseInt(createCourseVO.getOnlnEduTm()) + Integer.parseInt(createCourseVO.getOflnEduTm()));
		}

		//-- 수료증 정보를 가져온다.
		OrgCrsCertVO orgCrsCertVO = new OrgCrsCertVO();
		orgCrsCertVO.setOrgCd(orgCd);
		orgCrsCertVO = orgCrsCertService.view(orgCrsCertVO);

		// 파일 다운로드 설정
		String fileName = URLEncoder.encode(getMessage(request, "org.title.crscert"), "UTF-8"); // 파일명이 한글일 땐 인코딩 필요
		response.setHeader("Content-Transper-Encoding", "binary");
		//response.setContentType("application/pdf");
		response.setContentType("application/octet-stream"); // 다운로드 형태로 변경
		response.setHeader("Content-Disposition", "inline; filename=" + fileName + ".pdf");
		
    	// step 1 : Document 생성
		Document document;
		if("HOR".equals(orgCrsCertVO.getPrnDirec())) {
			document = new Document(PageSize.A4.rotate(), 50, 50, 50, 50); // 용지 및 여백 설정
		} else {
			document = new Document(PageSize.A4, 50, 50, 50, 50); // 용지 및 여백 설정
		}

		// step 2 : 백그라운드 이미지 설정
		String imgFilePath = Constants.FILE_STORAGE_PATH  + "/" + orgCd + "/" + orgCrsCertVO.getImgFileVO().getSaveFilePath();
		Image background = Image.getInstance(imgFilePath);
		float width = document.getPageSize().getWidth();
        float height = document.getPageSize().getHeight();

        // step 3 : PdfWriter 생성
        PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());

        // step 4 : Document Open
        document.open();

        // Measuring a String in Helvetica
        String fontPath = "";
        String locale = UserBroker.getLocaleKey(request);
        BaseFont bf_font;
        if("ko".equals(locale)) {
        	fontPath = request.getSession().getServletContext().getRealPath("/font/Batang.ttf");
        	bf_font = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED); // IDENTITY_V, IDENTITY_H, WINANSI, UniKS-UCS2-H, UniGB-UCS2-H
        } else if("jp".equals(locale)) {
        	fontPath = request.getSession().getServletContext().getRealPath("/font/MSMINCHO.TTF");
        	bf_font = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        } else {
            Font helvetica = new Font(FontFamily.HELVETICA, 12);
            bf_font = helvetica.getCalculatedBaseFont(false);
        }

		String[] studentList = StringUtil.split(vo.getStdNo(),"|");
		
		int cpltCnt = 0;
		for(int i=0; i < studentList.length; i++ ) {
			//-- 선택된 학습자의 정보를 검색한다.
			StudentVO isVO = new StudentVO();
			isVO.setStdNo(studentList[i]);
			isVO = studentService.viewStudent(isVO).getReturnVO();

			if("C".equals(isVO.getEnrlSts())) {
				//-- 학습 완료 과정인 경우만 출력함.
				cpltCnt++;
				if(cpltCnt > 1) {
					document.newPage();
				}
				writer.getDirectContentUnder().addImage(background, width, 0, 0, height, 0, 0);


				String enrlCpltDttm = isVO.getEnrlCpltDttm();
				String year = StringUtil.substring(enrlCpltDttm, 0, 4);
				String month = StringUtil.substring(enrlCpltDttm, 4, 6);
				String day = StringUtil.substring(enrlCpltDttm, 6, 8);
				String studyPeriod = "";
				//-- 정규과정 / 상시과정에 따른 수강기간 가져온기
				if("R".equals(courseVO.getCrsOperType())) {
					studyPeriod = createCourseVO.getEnrlStartDttm()+"~"+createCourseVO.getEnrlEndDttm();
				} else {
					studyPeriod = DateTimeUtil.getDateType(1,isVO.getEnrlStartDttm())+"~"+DateTimeUtil.getDateType(1,isVO.getEnrlEndDttm());
				}

		        // step 6
				String args[] = new String[1];
				args[0]  = isVO.getCpltNo();
		        String cpltNo 		= getMessage(request, "org.title.certificate.cpltno", args);
		        String userNm 		= getMessage(request, "org.title.certificate.username")+" : "+isVO.getUserNm();
		        String birthDay		= getMessage(request, "org.title.certificate.birthday")+" : "+isVO.getBirth();
		        String crsNm 		= getMessage(request, "org.title.certificate.course")+" : "+createCourseVO.getCrsCreNm();
		        String crsPeriod 	= getMessage(request, "org.title.certificate.period")+" : "+studyPeriod;
		        String crsTime		= getMessage(request, "org.title.certificate.time")+" : "+totalEduTm+" "+getMessage(request, "common.title.time");
		        String prnDay 		= year+"."+month+"."+day;

		        // Drawing lines to see where the text is added
		        PdfContentByte canvas = writer.getDirectContent();
		        canvas.beginText();
		        canvas.setFontAndSize(bf_font, 13);
		        canvas.showTextAligned(Element.ALIGN_LEFT, cpltNo, orgCrsCertVO.getCpltnoX(), orgCrsCertVO.getCpltnoY(), 0);
		        canvas.setFontAndSize(bf_font, 19);
		        int margin = 0;
		        if("Y".equals(orgCrsCertVO.getUserNmUseYn())) {
		        	canvas.showTextAligned(Element.ALIGN_LEFT, userNm, orgCrsCertVO.getCrsNmX(), orgCrsCertVO.getCrsNmY() - margin, 0);
		        	margin += 30;
		        }
		        if("Y".equals(orgCrsCertVO.getBirthUseYn())) {
		        	canvas.showTextAligned(Element.ALIGN_LEFT, birthDay, orgCrsCertVO.getCrsNmX(), orgCrsCertVO.getCrsNmY() - margin, 0);
		        	margin += 30;
		        }
		        if("Y".equals(orgCrsCertVO.getCrsNmUseYn())) {
		        	canvas.showTextAligned(Element.ALIGN_LEFT, crsNm, orgCrsCertVO.getCrsNmX(), orgCrsCertVO.getCrsNmY() - margin, 0);
		        	margin += 30;
		        }
		        if("Y".equals(orgCrsCertVO.getEduPeriodUseYn())) {
		        	canvas.showTextAligned(Element.ALIGN_LEFT, crsPeriod, orgCrsCertVO.getCrsNmX(), orgCrsCertVO.getCrsNmY() - margin, 0);
		        	margin += 30;
		        }
		        if("Y".equals(orgCrsCertVO.getEduTmUseYn())) {
		        	canvas.showTextAligned(Element.ALIGN_LEFT, crsTime, orgCrsCertVO.getCrsNmX(), orgCrsCertVO.getCrsNmY() - margin, 0);
		        }
		        canvas.setFontAndSize(bf_font, 19);
		        canvas.showTextAligned(Element.ALIGN_LEFT, prnDay, orgCrsCertVO.getPrndayX(), orgCrsCertVO.getPrndayY(), 0);
		        canvas.endText();
			}
		}

        document.close();
		writer.close();

		return null;
	}

	/**
	 * 수강생 암호 초기화
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/resetPassStudent")
	public String resetPassStudent( StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultVO<StudentVO> resultVO = studentService.resetPassStudent(vo, request);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "student.message.student.reset.password.success"));
		} else {
			resultVO.setMessage(getMessage(request, "student.message.student.reset.password.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}



	/**
	 * 입금 확인 일괄처리
	 *
	 * @return  ProcessResultVO
	 * @throws Exception 
	 */
	@RequestMapping(value="/confirmDepositStudent")
	public String confirmDepositStudent( StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultVO<StudentVO> resultVO = studentService.confirmDepositStudent(vo, request);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "student.message.student.payment.process.success"));
		} else {
			resultVO.setMessage(getMessage(request, "student.message.student.payment.process.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 수강생/수료생 목록 출력홈
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/excelDownloadPop")
	public String excelDownloadPop( StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);

		String downloadType = StringUtil.nvl(request.getParameter("downloadType"),"ENROLL");
		request.setAttribute("downloadType", downloadType);

		//사용자 정보관리
//		UserInfoCfgVO userInfoCfgVO = new UserInfoCfgVO();
		OrgUserInfoCfgVO userInfoCfgVO = new OrgUserInfoCfgVO();
		userInfoCfgVO.setOrgCd(orgCd);
		ProcessResultListVO<OrgUserInfoCfgVO> resultCfgList = orgUserInfoCfgService.list(userInfoCfgVO);
		request.setAttribute("resultCfgList", resultCfgList.getReturnList());
		request.setAttribute("studentVO", vo);
		return "mng/student/student/excel_form_pop";
	}
	
	/**
	 * [HRD] 수강신청관리 - 메인
	 * @param vo
	 * @param commandMap
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/stdPayMain")
	public String stdPayMain( StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String orgCd = UserBroker.getUserOrgCd(request);
		
		
		//전체 기업 조회 - 개설과정에 있는 기업 조회
		UsrDeptInfoVO usrDeptInfoVO = new UsrDeptInfoVO();
		usrDeptInfoVO.setOrgCd(orgCd);
		usrDeptInfoVO.setSearchMode("STDPAY");
		model.addAttribute("deptList", usrDeptInfoService.list(usrDeptInfoVO).getReturnList());
		
		//전체 과정 조회
		CourseVO courseVO = new CourseVO();
		courseVO.setOrgCd(orgCd);
		courseVO.setSearchMode("STDPAY");
		model.addAttribute("crsList", courseService.list(courseVO).getReturnList());
		
		//전체 과정(과목) 조회 - 개설과정에 있는 과정(과목) 조회
		/*OnlineSubjectVO onlineSubjectVO = new OnlineSubjectVO();
		onlineSubjectVO.setOrgCd(orgCd);
		onlineSubjectVO.setSearchMode("STDPAY");
		model.addAttribute("sbjList", subjectService.listOnline(onlineSubjectVO).getReturnList());*/
		
		//과정별 회차 조회
		/*CreateCourseVO createCourseVO = new CreateCourseVO();
		createCourseVO.setCrsCd(vo.getCrsCd());
		createCourseVO.setSearchMode("STDPAY");
		createCourseVO.setSelectMode("");
		model.addAttribute("creList", createCourseService.listCreateCourse(createCourseVO).getReturnList());*/
		
		/*List<SysCodeVO> repayStsCdList = sysCodeMemService.getSysCodeList("REPAY_STS_CD", UserBroker.getLocaleKey(request));
		request.setAttribute("repayStsCdList", repayStsCdList);*/
		
		request.setAttribute("paging", "Y");
		
		return "mng/student/student/student_payment_main";
	}
	
	/**
	 * [HRD] 수강신청관리 - 리스트 페이징
	 * @param vo
	 * @param commandMap
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/listStdPay")
	public String listStdPay( StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		//기업, 기수, 과정 정보로 수강생+결제 리스트, 통계 조회
		ProcessResultListVO<StudentVO> resultListVO = studentService.listStudentPaymentInfoManagePageing(vo);
		
		model.addAttribute("stdPayList", resultListVO.getReturnList());//리스트
		model.addAttribute("stdPayStatusVO", resultListVO.getReturnVO());//상단 통계
		model.addAttribute("pageInfo", resultListVO.getPageInfo());//상단 통계
		
		return "mng/student/student/student_payment_list";
	}
	
	/**
	 * [HRD] 수강신청관리 - 상세보기
	 * @param vo
	 * @param commandMap
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/viewStdPayPop")
	public String viewStdPayPop( StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		//해당 수강생의 결제 조회
		model.addAttribute("studentVO", studentService.viewStudentPaymentInfoManage(vo).getReturnVO());
		
		return "mng/student/student/student_payment_view_pop";
	}
	
	/**
	 * [HRD] 수강신청관리 - IDE 부여
	 * @param vo
	 * @param commandMap
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/uptIdePop")
	public String uptIde( StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		//수강생리스트
		ProcessResultListVO<StudentVO> resultListVO = studentService.listStudentPaymentInfoManage(vo);
		model.addAttribute("stdPayList", resultListVO.getReturnList());
		
		//IDE 리스트
		ProcessResultListVO<StudentVO> ideList = studentService.listIdeManage(vo);
		model.addAttribute("ideList", ideList.getReturnList());
		
		return "mng/student/student/student_ide_pop";
	}
	
	/**
	 * [HRD] 수강신청관리 - 엑셀업로드 폼
	 * @param vo
	 * @param commandMap
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/addStdPayExcelPop")
	public String addStdPayExcelPop( StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		request.setAttribute("fileupload", "Y");
		
		return "mng/student/student/student_payment_write_excel_pop";
	}
	
	/**
	 * [HRD] 관리자>수강신청관리>엑셀업로드 - SAMPLE 다운로드
	 * @param vo
	 * @param commandMap
	 * @param model
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/sampleExcelStdPay")
	public void sampleExcelStdPay ( CreateCourseVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		

		String orgCd = UserBroker.getUserOrgCd(request);
		
		response.setHeader("Content-Disposition", "attachment;filename=stu_sample.xlsx;");
		response.setHeader( "Content-Transfer-Coding", "binary" );
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		OutputStream os = response.getOutputStream();
		try {
			int rowNum = 0;

			XSSFWorkbook wbook = new XSSFWorkbook();
			XSSFSheet sheet = wbook.createSheet("수강생업로드");

			// 페이지 제목줄 .. 작업코멘트 5줄.
			XSSFRow pageRow1 = sheet.createRow((short)rowNum);
			pageRow1.setHeight((short)5000);
			String info = "※ 주의사항(해당사항이 맞지 않을 경우 수강생이 등록되지 않을 수 있습니다.)\n"
							+ "* 3번째 줄부터 순서대로 입력하세요.\n"
							+ "* 중간에 빈칸이거나 공백을 입력하면 안 됩니다.\n"
							+ "* 아이디, 개설과정코드는 필수 입력값입니다. \n"
							+ "* 아이디 회원정보의 기업과 개설과정의 기업이 일치해야 합니다. \n"
							+ "* 결제방법은 관리자입금으로 자동 등록됩니다. \n"
							+ "* 결제 금액은 개설과정에 등록된 금액으로 자동 등록됩니다. \n"
							+ "* 개설 과정의 가격이 입력되어있어야 하며, 0원 보다 커야 합니다. \n"
							+ "* 개설 과정 코드는 두 번째 시트 또는 [과정관리>개설과정관리] 페이지에서 확인바랍니다. \n"
							+ "* 두 번째 시트는 종료되기 전 기수의 개설과정 리스트입니다.\n"
							+ "  - 두 번째 시트의 개설과정은 샘플 파일 다운받을 당시의 정보입니다.\n"
							+ "  - 업로드하는 시점의 LMS 정보로(개설과정관리, 기수관리) 수강생 등록됩니다."
							;
			POIExcelUtil.createMergeCell(info, pageRow1, 0, 5, "left");
			
			//-- 컬럼 제목줄 만들기
			rowNum++;
			XSSFRow titleRow = sheet.createRow((short)rowNum);


			POIExcelUtil.createTitleCell("아이디", titleRow, 0);
			POIExcelUtil.createTitleCell("회차 코드", titleRow, 1);
			//POIExcelUtil.createTitleCell("결제방법", titleRow, 3);
			//POIExcelUtil.createTitleCell("실결제금액(미입력 시, 개설과정의 금액으로 자동 입력)", titleRow, 4);
			//POIExcelUtil.createTitleCell("실결제시간(미입력 시, 현재시간으로 자동 입력)", titleRow, 5);
			//POIExcelUtil.createTitleCell("카드승인번호(카드결제 시 입력)", titleRow, 4);

			//-- 셀의 넓이 조절
			sheet.setColumnWidth(0, sheet.getDefaultColumnWidth() * 500);
			sheet.setColumnWidth(1, sheet.getDefaultColumnWidth() * 600);
			
			/**
			 * 두번째 시트 시작
			 */
			rowNum = 0;
			XSSFSheet sheet2 = wbook.createSheet("회차정보");
			XSSFRow pageRow2 = sheet2.createRow((short)rowNum);
			
			//-- 컬럼 제목줄 만들기
			POIExcelUtil.createTitleCell("과정명", pageRow2, 0);
			//POIExcelUtil.createTitleCell("기업명", pageRow2, 1);
			POIExcelUtil.createTitleCell("회차명", pageRow2, 1);
			POIExcelUtil.createTitleCell("회차코드", pageRow2, 2);
			POIExcelUtil.createTitleCell("가격(원)", pageRow2, 3);
			
			sheet2.setColumnWidth(0, sheet2.getDefaultColumnWidth() * 600);
			//sheet2.setColumnWidth(1, sheet2.getDefaultColumnWidth() * 1000);
			sheet2.setColumnWidth(2, sheet2.getDefaultColumnWidth() * 1000);
			sheet2.setColumnWidth(3, sheet2.getDefaultColumnWidth() * 600);
			sheet2.setColumnWidth(4, sheet2.getDefaultColumnWidth() * 500);
			
			//기수, 개설과정 조회
			List<CreateCourseVO> resultList = createCourseService.listCreateCourseForManageEnroll(vo).getReturnList();

			for (CreateCourseVO cVO : resultList) {
				rowNum++;
				pageRow2 = sheet2.createRow((short)rowNum);
				POIExcelUtil.createContentCell(StringUtil.nvl(cVO.getCrsNm()), pageRow2, 0, "center");
				//POIExcelUtil.createContentCell(StringUtil.nvl(cVO.getDeptNm()), pageRow2, 1, "center");
				POIExcelUtil.createContentCell(StringUtil.nvl(cVO.getCrsCreNm() + cVO.getCreTerm()+"회차"), pageRow2, 1, "center");
				POIExcelUtil.createContentCell(StringUtil.nvl(cVO.getCrsCreCd()), pageRow2, 2, "center");
				POIExcelUtil.createContentCell(StringUtil.nvl(cVO.getEduPrice()), pageRow2, 3, "center");
			}
			
			try {
				wbook.write(os);
			} catch (Exception ex) {
				String name = ex.getClass().getName();
				if (!name.equals("org.apache.catalina.connector.ClientAbortException")) {
					throw ex;
				}
			} finally {
				if(os != null) {
					os.close();
				}
			}
		} catch (Exception e) {
			log.error("IOException occurred");
		}
	}
	
	/**
	 * [HRD] 관리자>수강신청관리>엑셀업로드 - 수강생 등록
	 * @param vo
	 * @param commandMap
	 * @param model
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@PostMapping(value="/uploadExcelStdPay")
	public String uploadExcelStdPay ( StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String fileName = StringUtil.nvl(request.getParameter("fileName"));
		
		String type =  StringUtil.nvl(request.getParameter("type"));
		String filePath = StringUtil.nvl(request.getParameter("filePath"));
		String orgCd = StringUtil.nvl(UserBroker.getUserOrgCd(request), "ORG0000001");
		String regIp = request.getRemoteAddr();
		vo.setOrgCd(orgCd);
		vo.setRegIp(regIp);
		
		ProcessResultVO<StudentVO> resultVO = new ProcessResultVO<>();
		try {
			studentExcelService.addStudentPaymentExcel(vo, fileName, filePath);
			resultVO.setResultSuccess();
			resultVO.setMessage("수강생 등록에 성공하였습니다.");
		} catch(MediopiaDefineException e1) {
			resultVO.setResultFailed();
			resultVO.setMessage(e1.getMessage());
		} catch (Exception e) {
			resultVO.setResultFailed();
			log.error(e.getMessage());
			resultVO.setMessage("수강생 등록에 실패하였습니다. 반복될 경우, 담당자에게 문의바랍니다.");
		}
		
		return JsonUtil.responseJson(response, resultVO);
	}
	
	/**
	 * [HRD] 관리자>수강신청관리>승인
	 * @param vo
	 * @param commandMap
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value="/editConfirmStdPay")
	public String editConfirmStdPay ( StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		ProcessResultVO<StudentVO> resultVO = new ProcessResultVO<>(); 
		
		try {
			resultVO = studentService.confirmStudentPayment(vo);
		} catch(MediopiaDefineException e1) {
			resultVO.setResultFailed();
			resultVO.setMessage(e1.getMessage());
		} catch (Exception e) {
			resultVO.setResultFailed();
			resultVO.setMessage("승인 실패하였습니다.");
		}
		
		return JsonUtil.responseJson(response, resultVO);
	}
	
	/**
	 * [HRD] 관리자>수강신청관리>취소
	 * @param vo
	 * @param commandMap
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value="/editCancelStdPay")
	public String editCancelStdPay ( StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		ProcessResultVO<StudentVO> resultVO = new ProcessResultVO<>();  
		
		try {
			resultVO = studentService.cancelStudentPayment(vo);
		} catch(MediopiaDefineException e1) {
			resultVO.setResultFailed();
			resultVO.setMessage(e1.getMessage());
		} catch (Exception e) {
			resultVO.setResultFailed();
			resultVO.setMessage("취소 실패하였습니다.");
		}
		
		return JsonUtil.responseJson(response, resultVO);
	}
	
	/**
	 * [HRD] 관리자>수강신청관리>엑셀다운로드
	 * @param vo
	 * @param commandMap
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/excelDownloadListStdPay")
	public String excelDownloadListStdPay ( StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		response.setHeader("Content-Disposition", "attachment;filename=stu_pay_list.xlsx;");
		response.setHeader( "Content-Transfer-Coding", "binary" );
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		OutputStream os = response.getOutputStream();
		
		ProcessResultVO resultVO = new ProcessResultVO<>();
		
		//-- 엑셀 출력 로그를 저장한다.
		LogPrnLogVO printLogVO = new LogPrnLogVO();
		printLogVO.setUserNo(UserBroker.getUserNo(request));
		printLogVO.setUserNm(UserBroker.getUserName(request));
		printLogVO.setPrnDoc("PRINT EXCEL : 수강신청관리 엑셀다운로드");
		printLogVO.setParam(vo.toString());
		logPrnLogService.add(printLogVO);

		//-- 개인정보 출력로그 저장
		LogPrivateInfoInqLogVO pilVO = new LogPrivateInfoInqLogVO();
		pilVO.setOrgCd(UserBroker.getUserOrgCd(request));
		pilVO.setMenuCd(UserBroker.getMenuCode(request));
		pilVO.setUserNo(UserBroker.getUserNo(request));
		pilVO.setUserNm(UserBroker.getUserName(request));
		pilVO.setDivCd("EXCEL");
		pilVO.setInqCts("EXCEL DOWNLOAD : 수강신청관리 엑셀다운로드");
		logPrivateInfoService.add(pilVO);
		
		try {
			int rowNum = 0;

			XSSFWorkbook wbook = new XSSFWorkbook();
			XSSFSheet sheet = wbook.createSheet("수강신청관리");

			//-- 컬럼 제목줄
			XSSFRow xssRow = sheet.createRow((short)rowNum);


			POIExcelUtil.createTitleCell("이름", xssRow, 0);
			POIExcelUtil.createTitleCell("아이디", xssRow, 1);
			POIExcelUtil.createTitleCell("생년월일", xssRow, 2);
			//POIExcelUtil.createTitleCell("소속기업", xssRow, 3);
			//POIExcelUtil.createTitleCell("신청기수", xssRow, 4);
			POIExcelUtil.createTitleCell("운영과정", xssRow, 3);
			POIExcelUtil.createTitleCell("승인여부", xssRow, 4);
			POIExcelUtil.createTitleCell("신청일", xssRow, 5);
			POIExcelUtil.createTitleCell("IDE", xssRow, 6);

			//-- 셀의 넓이 조절
			sheet.setColumnWidth(0, sheet.getDefaultColumnWidth() * 500);
			sheet.setColumnWidth(1, sheet.getDefaultColumnWidth() * 600);
			sheet.setColumnWidth(2, sheet.getDefaultColumnWidth() * 600);
			//sheet.setColumnWidth(3, sheet.getDefaultColumnWidth() * 1000);
			//sheet.setColumnWidth(4, sheet.getDefaultColumnWidth() * 1000);
			sheet.setColumnWidth(3, sheet.getDefaultColumnWidth() * 1500);
			sheet.setColumnWidth(4, sheet.getDefaultColumnWidth() * 600);
			sheet.setColumnWidth(5, sheet.getDefaultColumnWidth() * 600);
			sheet.setColumnWidth(6, sheet.getDefaultColumnWidth() * 1500);
			
			
			List<StudentVO> stuList = studentService.listStudentPaymentInfoManage(vo).getReturnList();
			
			String enrlStsNm = "";
			for(StudentVO stuVO : stuList) {
				rowNum++;
				xssRow = sheet.createRow((short)rowNum);
				POIExcelUtil.createContentCell(StringUtil.nvl(stuVO.getUserNm()), xssRow, 0, "center");
				POIExcelUtil.createContentCell(StringUtil.nvl(stuVO.getUserId()), xssRow, 1, "center");
				POIExcelUtil.createContentCell(StringUtil.nvl(stuVO.getBirth()), xssRow, 2, "center");
				//POIExcelUtil.createContentCell(StringUtil.nvl(stuVO.getDeptNm()), xssRow, 3, "center");
				//POIExcelUtil.createContentCell(StringUtil.nvl(stuVO.getCrsYear()+ "년도 " + stuVO.getCrsTerm() + "기수"), xssRow, 4, "center");
				POIExcelUtil.createContentCell(StringUtil.nvl(stuVO.getCrsCreNm() + stuVO.getCreTerm() + "회차"), xssRow, 3, "center");
				
				if("E".equals(stuVO.getEnrlSts())) {
					enrlStsNm = "결제 대기";
				}else if("N".equals(stuVO.getEnrlSts())) {
					enrlStsNm = "결제 취소";
				}else if("S".equals(stuVO.getEnrlSts()) || "C".equals(stuVO.getEnrlSts()) || "F".equals(stuVO.getEnrlSts())) {
					enrlStsNm = "결제 완료";
				}
				
				POIExcelUtil.createContentCell(StringUtil.nvl(enrlStsNm), xssRow, 4, "center");
				POIExcelUtil.createContentCell(DateTimeUtil.getDateType(0, StringUtil.nvl(stuVO.getEnrlAplcDttm())), xssRow, 5, "center");
				POIExcelUtil.createContentCell(StringUtil.nvl(stuVO.getIdeUrl()), xssRow, 6, "center");
			}
			
			try {
				wbook.write(os);
			} catch (Exception ex) {
				String name = ex.getClass().getName();
				if (!name.equals("org.apache.catalina.connector.ClientAbortException")) {
					throw ex;
				}
			} finally {
				if(os != null) {
					os.close();
				}
			}
		} catch(IOException e1) {
			log.error("수강신청관리 엑셀다운로드 오류 : IOException");
			resultVO.setResultFailed();
			resultVO.setMessage("수강신청관리 엑셀다운로드 오류, IOException");
		} catch (Exception e) {
			log.error("수강신청관리 엑셀다운로드 오류");
			resultVO.setResultFailed();
			resultVO.setMessage("수강신청관리 엑셀다운로드 오류");
		}
		
		return null;
	}
	
	/**
	 * [HRD] 관리자>수강신청관리>삭제
	 * @param vo
	 * @param commandMap
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value="/editDeleteStdPay")
	public String editDeleteStdPay ( StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		ProcessResultVO<StudentVO> resultVO = new ProcessResultVO<>();  
		
		try {
			resultVO = studentService.deleteStudentPayment(vo);
		} catch(MediopiaDefineException e1) {
			resultVO.setResultFailed();
			resultVO.setMessage(e1.getMessage());
		} catch (Exception e) {
			resultVO.setResultFailed();
			resultVO.setMessage("삭제 실패하였습니다.");
		}
		
		return JsonUtil.responseJson(response, resultVO);
	}
	
	/**
	 * [HRD] 관리자>수강신청관리>기업 조회 - 기수, 과목 선택 시
	 * @param vo
	 * @param commandMap
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/listDeptStdPay")
	public String listDeptStdPay( StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		UsrDeptInfoVO usrDeptInfoVO = new UsrDeptInfoVO();
		usrDeptInfoVO.setSearchMode("STDPAY");
		usrDeptInfoVO.setCrsCd(vo.getCrsCd());
		usrDeptInfoVO.setSbjCd(vo.getSbjCd());
		
		return JsonUtil.responseJson(response, new ProcessResultListVO<>(usrDeptInfoService.list(usrDeptInfoVO).getReturnList()));
	}
	
	/**
	 * [HRD] 관리자>수강신청관리>기수 조회 - 기업, 과목 선택 시
	 * @param vo
	 * @param commandMap
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/listCrsStdPay")
	public String listCrsStdPay( StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		CourseVO courseVO = new CourseVO();
		courseVO.setSearchMode("STDPAY");
		courseVO.setSbjCd(vo.getSbjCd());
		courseVO.setDeptCd(vo.getDeptCd());
		
		return JsonUtil.responseJson(response, new ProcessResultListVO<>(courseService.list(courseVO).getReturnList()));
	}
	
	/**
	 * [HRD] 관리자>수강신청관리>기수 조회 - 기업, 과목 선택 시
	 * @param vo
	 * @param commandMap
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/listCreStdPay")
	public String listCreStdPay( StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		CreateCourseVO createCourseVO = new CreateCourseVO();
		createCourseVO.setSearchMode("STDPAY");
		createCourseVO.setCrsCd(vo.getCrsCd());
		
		return JsonUtil.responseJson(response, new ProcessResultListVO<>(createCourseService.listCreateCourse(createCourseVO).getReturnList()));
	}
	
	/**
	 * [HRD] 관리자>수강신청관리>과목 조회 - 기업, 과수 선택 시
	 * @param vo
	 * @param commandMap
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/listSbjStdPay")
	public String listSbjStdPay( StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		OnlineSubjectVO onlineSubjectVO = new OnlineSubjectVO();
		onlineSubjectVO.setSearchMode("STDPAY");
		onlineSubjectVO.setDeptCd(vo.getDeptCd());
		onlineSubjectVO.setCrsCd(vo.getCrsCd());
		
		return JsonUtil.responseJson(response, new ProcessResultListVO<>(subjectService.listOnline(onlineSubjectVO).getReturnList()));
	}
	
	/**
	 * 수강 신청 관리 메인
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/listStudyForCrm")
	public String listStudyForCrm(StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Map<String, Object> userInfo = new Hashtable<String, Object>();
		userInfo.put("userNo", vo.getUserNo());
		ProcessResultListVO<UserCourseVO> resultListVO = createCourseService.myCreListForStudent(userInfo);
		request.setAttribute("myCreList", resultListVO.getReturnList());
		return "mng/student/student/list_study_for_crm";
	}

	/**
	 * 학습자의 교재 목차 목록 조회 (단순 목록 형태로 반환)
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/listBookmarkLogPop")
	public String listBookmarkLog(BookmarkVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		List<BookmarkVO> unitLogList = bookmarkService.listBookmarkLog(vo).getReturnList();
		request.setAttribute("unitLogList", unitLogList);
				
		return "mng/student/student/bookmark_log_list_pop";
	}
	
	/**
	 * 환불처리 - 이니시스 미연계 - 환불 요청받은 결제 건에 대해 직접 수강생에게 입금 환불 처리 후 LMS DB 상에서만 환불완료 처리
	 * @param vo
	 * @param commandMap
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value="/editRefund")
	public String editRefund(StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String userNo = UserBroker.getUserNo(request);
		
		ProcessResultVO<StudentVO> resultVO = new ProcessResultVO<StudentVO>();
		try {
			StudentVO studentVO = studentService.viewStudentPaymentInfoByStdNo(vo);
			if(studentVO == null) {
				throw new ServiceProcessException("수강생이 조회되지 않습니다.");
			}
			//수강상태 필요시 고려
			/*if(!"S".equals(studentVO.getEnrlSts())) {
			}*/
			//환불요청 중인 건만 가능
			if(!"REFUND001".equals(studentVO.getRepayStsCd())) {
				throw new ServiceProcessException("환불신청중인 결제 건만 환불 처리 가능합니다.");
			}
			if(vo.getRepayPrice() == null) {
				throw new ServiceProcessException("환불 금액을 입력바랍니다.");
			}
			if(studentVO.getStdPrice() < vo.getRepayPrice()) {
				throw new ServiceProcessException("환불 금액이 결제 금액보다 클 수 없습니다.");
			}
			
			vo.setEnrlSts("N");//수강 취소 처리 
			vo.setRepayStsCd("REFUND003");//환불완료
			vo.setRepayPrice(vo.getRepayPrice());//입력받은 환불금액
			vo.setRepayMemo(StringUtil.nvl(vo.getRepayMemo(), "[수강취소]관리자 직접 환불"));
			
			if(studentService.updateRefundComplete(vo) > 0) {
				resultVO.setResultSuccess();
				resultVO.setMessage("환불 처리하였습니다.");
			}else {
				resultVO.setResultFailed();
				resultVO.setMessage("환불 처리 실패하였습니다.");
			}
		} catch (MediopiaDefineException e) {
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		} catch (Exception e) {
			resultVO.setResultFailed();
			resultVO.setMessage("환불 처리 실패하였습니다.");
		}
				
		return JsonUtil.responseJson(response, resultVO);
	}
	
	/**
	 * 환불취소 - 수강중으로 변경
	 * @param vo
	 * @param commandMap
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value="/editRefundCancel")
	public String editRefundCancel(StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String userNo = UserBroker.getUserNo(request);
		
		ProcessResultVO<StudentVO> resultVO = new ProcessResultVO<StudentVO>();
		try {
			StudentVO studentVO = studentService.viewStudentPaymentInfoByStdNo(vo);
			if(studentVO == null) {
				throw new ServiceProcessException("수강생이 조회되지 않습니다.");
			}
			//if(!"S".equals(studentVO.getEnrlSts())) {//수강상태 조건 고려
			//}
			
			//수강생이 환불신청 후 같은 강의를 새로 수강 신청한 경우 : 불가
			if("Y".equals(studentService.isEnroll(studentVO).getReturnVO().getStdYn())) {
				throw new ServiceProcessException("수강생이 동일한 과정 수강중입니다. 환불 취소 불가능합니다.");
			}
			
			//환불요청 중인 건만 가능
			if(!"REFUND001".equals(studentVO.getRepayStsCd())) {
				throw new ServiceProcessException("환불신청중인 결제 건만 환불 처리 가능합니다.");
			}
			
			StudentVO paramStudentVO = new StudentVO();
			/*paramStudentVO.setRepayBankNm(null);
			paramStudentVO.setRepayUserNm(null);
			paramStudentVO.setRepayAcntNo(null);
			paramStudentVO.setRepayReason(null);
			paramStudentVO.setRepayPrice(null);
			paramStudentVO.setStdPrice(studentVO.getStdPrice());*/
			paramStudentVO.setStdNo(studentVO.getStdNo());
			paramStudentVO.setRepayMemo(StringUtil.nvl(vo.getRepayMemo(), "[환불취소] 관리자 처리"));
			paramStudentVO.setRegNo(userNo);
			paramStudentVO.setModNo(userNo);
			
			if(studentService.updateRefundCancel(paramStudentVO) > 0) {
				resultVO.setResultSuccess();
				resultVO.setMessage("환불 취소 처리하였습니다.");
			}else {
				resultVO.setResultFailed();
				resultVO.setMessage("환불 취소 실패하였습니다.");
			}
		} catch (MediopiaDefineException e) {
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		} catch (Exception e) {
			resultVO.setResultFailed();
			resultVO.setMessage("환불 취소 실패하였습니다.");
		}
		
		return JsonUtil.responseJson(response, resultVO);
	}
	
	/**
	 * 이니시스 결제 취소(카드, 계좌이체) 
	 * @param vo
	 * @param commandMap
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/editIniRefund")
	public String editIniRefund(StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		ProcessResultVO<StudentVO> resultVO = new ProcessResultVO<>();
		
		String userNo = UserBroker.getUserNo(request);
		
		try {
			//1. 조회 및 검증
			StudentVO studentVO = studentService.viewStudentPaymentInfoByStdNo(vo);
			if(studentVO == null) {
				throw new ServiceProcessException("수강생 조회 오류");
			}
			if(vo.getRepayPrice() == null) {
				throw new ServiceProcessException("환불금액 입력바랍니다.");
			}
			int stdPrice = StringUtil.nvl(studentVO.getStdPrice(),0);//결제 세부 건의 금액(std)
			String enrlSts = StringUtil.nvl(studentVO.getEnrlSts());
			String tid = StringUtil.nvl(studentVO.getTid());//이니시스 결제 ID
			int paymPrice = StringUtil.nvl(studentVO.getPaymPrice(),0);//결제 건의 총 금액(pay_info)
			int inputRefundPay = StringUtil.nvl(vo.getRepayPrice(), 0);//입력받은 환불금액
			
			if("".equals(tid)) {
				throw new ServiceProcessException("이니시스 결제 내역이 없습니다.");
			}
			//환불완료 건은 환불 처리 불가
			if("REFUND003".equals(studentVO.getRepayStsCd()) ) {
				throw new ServiceProcessException("이미 환불 완료된 건입니다.");
			}
			if(!"REFUND001".equals(studentVO.getRepayStsCd()) ) {
				throw new ServiceProcessException("환불신청중인 건만 처리 가능합니다.");
			}
			if(stdPrice < inputRefundPay) {
				throw new ServiceProcessException("환불 금액이 결제 금액보다 높을 수 없습니다.");
			}
			
			
			//if("N".equals(enrlSts) || "D".equals(enrlSts)  || "E".equals(enrlSts) || "C".equals(enrlSts)) {//삭제, 취소, 대기, 수료 불가
			/*if(!("S".equals(studentVO.getEnrlSts()) || "F".equals(studentVO.getEnrlSts()))) {
				throw new ServiceProcessException("수강 중인 과정만 결제 취소가 가능합니다.");//환불 신청 시, enrl_sts가 N으로 변경됨
			}*/
			
			//2. 결제 API
			String resultCode = "";
			String resultMsg = "";
			if(paymPrice == inputRefundPay) {//[전체취소API]입력받은 환불금액과 총 결제 가격 같은 경우 전체 취소 API
				if(paymPrice != stdPrice || stdPrice != inputRefundPay) {
					throw new ServiceProcessException("이니시스 전체 환불은 총 결제 금액, 결제 금액, 환불 금액이 동일해야만 가능합니다.");
				}
				
				Map<String, Object> returnVO = paymentService.inicisRefund(InicisUtil.setRefundParameter(request, studentVO)).getReturnVO();
				PaymentInicisCancelResultDTO cancelResultDTO = new ObjectMapper().convertValue(returnVO, PaymentInicisCancelResultDTO.class);
				if(cancelResultDTO == null) {
					log.error("[관리자 이니시스 전체 환불 오류] 수강생번호 : " + studentVO.getStdNo() + ", 메시지 : 이니시스 환불결과 변환 오류");
					throw new ServiceProcessException("환불 처리하지 못하였습니다.\n결제 금액은 환불 되고, 홈페이지에서 수강 취소가 되지 않는 경우 담당자에게 연락바랍니다.");
				}
				resultCode = StringUtil.nvl(cancelResultDTO.getResultCode());
				resultMsg = StringUtil.nvl(cancelResultDTO.getResultMsg());
			}else if(paymPrice > inputRefundPay) {//[부분취소API] 총 결제 금액 > 입력받은 결제
				
				//결제 당시 부분 취소 가능 여부 (CardPrtcCode : 1 가능, 0 불가능)
				if( ("Card".equals(studentVO.getPayMethod()) || "VCard".equals(studentVO.getPayMethod()) || "CARD".equals(studentVO.getPayMethod()) &&  "0".equals(studentVO.getCardPrtcCode())) ) {
					throw new ServiceProcessException("이니시스 부분 취소 환불이 불가능합니다.");
				}
				
				Map<String, Object> returnVO = paymentService.inicisRefund(InicisUtil.setPartialRefundParameter(request, studentVO, vo)).getReturnVO();
				if(returnVO == null) {
					throw new ServiceProcessException("결제 취소 오류");
				}
				PaymentInicisParCancelResultDTO cancelResultDTO = new ObjectMapper().convertValue(returnVO, PaymentInicisParCancelResultDTO.class);
				if(cancelResultDTO == null) {
					log.error("[관리자 이니시스 부분 환불 오류] 수강생번호 : " + studentVO.getStdNo() + ", 메시지 : 이니시스 환불결과 변환 오류");
					throw new ServiceProcessException("환불 처리하지 못하였습니다.\n결제 금액은 환불 되고, 홈페이지에서 수강 취소가 되지 않는 경우 담당자에게 연락바랍니다.");
				}
				resultCode = StringUtil.nvl(cancelResultDTO.getResultCode());
				resultMsg = StringUtil.nvl(cancelResultDTO.getResultMsg());
			}else {
				throw new ServiceProcessException("수강생 조회 오류");
			}
			
			//3. 성공 후 DB 처리 -> 주의) DB 오류에 대한 망 취소 처리 없음
			if("00".equals(resultCode)) {//성공 후 DB 처리
				studentVO.setRegNo(userNo);
				studentVO.setModNo(userNo);
				studentVO.setRepayPrice(inputRefundPay);
				studentVO.setEnrlSts("N");
				studentVO.setRepayStsCd("REFUND003");
				studentVO.setRepayMemo(StringUtil.nvl(vo.getRepayMemo(),"[수강취소] 관리자 처리"));
				
				if(studentService.updateRefundComplete(studentVO) > 0) {
					resultVO.setResultSuccess();
					resultVO.setMessage("환불 처리하였습니다.");
				}else {
					resultVO.setResultFailed();
					resultVO.setMessage("환불 실패하였습니다.");
				}
			}else {
				log.error("[결제취소오류] resultCode : " + resultCode  + ", stdNo : " + studentVO.getStdNo());
				resultVO.setResultFailed();
				throw new ServiceProcessException("[결제 취소 오류]\n 메시지 : " + resultMsg);
			}
		} catch (MediopiaDefineException e) {
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
			
		} catch (Exception e) {
			resultVO.setResultFailed();
			resultVO.setMessage("결제취소 오류");
		}
		
		return JsonUtil.responseJson(response, resultVO);
	}
	
	
	@RequestMapping(value="/listRefundHistory")
	public String listRefundHistory(StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		model.addAttribute("refundList", studentService.listStdPayRefundHsty(vo));
		return "mng/student/student/student_refund_list";
	}
	
	
	
	@RequestMapping(value="/stdAttendForm")
	public String stdAttendMain(StudentVO vo, HttpServletRequest request) throws Exception {
		
		CreateCourseVO createCourseVO = new CreateCourseVO();
		createCourseVO.setCrsCreCd(vo.getCrsCreCd());
		createCourseVO = createCourseService.viewCreateCourseForAttend(createCourseVO);
		
		request.setAttribute("attendCreCourse", createCourseVO);
		
		return "mng/course/creattend/attend_main";
	}
	
	@RequestMapping(value="/stdAttendList")
	public String stdAttendList(StudentVO vo, HttpServletRequest request) throws Exception {
		
		List<StudentVO> stdList = studentService.listStudentForAttend(vo);
		request.setAttribute("stdList", stdList);
		
		ContentsVO contentsVO = new ContentsVO();
		contentsVO.setSbjCd(vo.getSbjCd());
		List<ContentsVO> attendCntsList = contentsService.listAttendContents(contentsVO);
		request.setAttribute("attendCntsList", attendCntsList);
		
		return "mng/course/creattend/attend_list_div";
	}
	
	@RequestMapping(value="/stdRow")
	public String stdRow(StudentVO vo, HttpServletRequest request) throws Exception {
		
		StudentVO student = studentService.viewStudentForAttend(vo);
		request.setAttribute("student", student);
		
		return "mng/course/creattend/attend_std_row";
	}
	
	
	/**
	 * 자격증 과정 관리 상세화면
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/certStudentManage")
	public String certStudentManage(StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		CreateCourseVO createCourseVO = new CreateCourseVO();
		createCourseVO.setCrsCreCd(vo.getCrsCreCd());
		createCourseVO.setCreOperTypeCd("E");
		createCourseVO = createCourseService.viewCreateCourse(createCourseVO).getReturnVO();
		request.setAttribute("vo", createCourseVO);

		CourseVO courseVO = new CourseVO();
		courseVO.setCrsCd(createCourseVO.getCrsCd());
		courseVO = courseService.view(courseVO).getReturnVO();
		request.setAttribute("crsvo", courseVO);
		
		List<SysCodeVO> cpltStsList = sysCodeMemService.getSysCodeList("CERT_STS", UserBroker.getLocaleKey(request));
		request.setAttribute("certStsList", cpltStsList);


		return "mng/student/student/cert_manage";
	}

	/**
	 * 자격증 과정 관리 상세화면 학습자 리스트
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/listCertStudent")
	public String listCertStudent(StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		CreateCourseVO createCourseVO = new CreateCourseVO();
		createCourseVO.setCrsCreCd(vo.getCrsCreCd());
		createCourseVO.setCreOperTypeCd("E");
		createCourseVO = createCourseService.viewCreateCourse(createCourseVO).getReturnVO();
		request.setAttribute("createCourseVO", createCourseVO);
		
//		ProcessResultListVO<StudentVO> resultList = studentService.listStudent(vo, curPage, listScale, 10);
		ProcessResultListVO<StudentVO> resultList = studentService.listStudentPageing(vo);

		request.setAttribute("vo", vo);
		request.setAttribute("studentList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());
		return "mng/student/student/cert_list_div";
	}
	
	/**
	 * 자격증 응시생 일괄 등록 폼 - 엑셀업로드 폼
	 * @param vo
	 * @param commandMap
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/addCertStudentExcelPop")
	public String addCertStudentExcelPop( StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "A");
		request.setAttribute("fileupload", "A");
		
		return "mng/student/student/cert_excel_upload_pop";
	}
	
	/**
	 * 자격증 응시생 등록 샘플파일 다운로드 폼.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/sampleExcelCertStudent")
	public String sampleExcelCertStudent ( StudentExcelVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		HashMap<String, String> titles = new HashMap<String, String>();
		titles.put("info1", "※ 주의사항(해당사항이 맞지 않을 경우 응시생이 등록되지 않을 수 있습니다.)");
		titles.put("info2", "* 응시생 아이디를 입력하세요.");
		titles.put("info3", "- 같은 아이디는 여러개 등록할수 없습니다.");
		titles.put("info4", "- 회원가입을 한 회원만 응시생으로 등록할 수 있습니다.");
		titles.put("info5", "* 자격증 점수를 입력하세요.");
		titles.put("info6", "- 숫자만 입력할수있고 최대 100점입니다.");
		titles.put("info7", "* 합격여부를 입력하세요.");
		titles.put("info8", "- 합격일때는 Y , 불합격일때는 N 으로 입력해주세요.");
		titles.put("info9", "- Y/N 값 이외의 값을 입력시 등록할 수 없습니다.");
		titles.put("userId", "회원아이디");
		titles.put("certScore", "점수");
		titles.put("certPassYn", "합격여부(합격:Y,불합격:N)");

		response.setHeader("Content-Disposition", "attachment;filename=cert_student_sample.xlsx;");
		response.setHeader( "Content-Transfer-Coding", "binary" );
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		try {
			studentExcelService.sampleExcelDownload(titles, response.getOutputStream());
		} catch (Exception e) {
			log.error("IOException occurred");
		}
		return null;
	}


	/**
	 * 자격증 응시생 엑셀 파일 업로드 하고
	 * Validation을 check 한다.
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/addCheckCertStudentExcelPop")
	public String addCheckCertStudentExcelPop(StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {
		commonVOProcessing(vo, request);
		String fileName = StringUtil.nvl(request.getParameter("fileName"));
		String filePath = StringUtil.nvl(request.getParameter("filePath"));

		List<StudentExcelVO> studentExcelList = null;
		try {
			//studentExcelList = studentExcelService.listStudentExcel(vo.getCrsCreCd(), fileName, filePath).getReturnList();
			studentExcelList = studentExcelService.excelUploadValidationCheck(fileName, filePath).getReturnList();
		} catch (ServiceProcessException ex) { // 파일처리 오류..
			setAlertMessage(request, "엑셀 파일 처리중 다음 오류가 발생하였습니다.\n\n" + ex.getMessage());
		} finally {
			FileUtil.delFile(filePath, fileName); // -- 사용한 파일 지움.
		}
		request.setAttribute("studentExcelList", studentExcelList);
		request.setAttribute("fileupload", "A");
		request.setAttribute("vo", vo);
		
		return "mng/student/student/cert_excel_upload_list_pop";
	}
	
	/**
	 * 자격증 응시생 엑셀 업로드 , 단건 체크
	 *
	 * @return  ProcessResultVO
	 * @throws Exception 
	 */
	@RequestMapping(value="/studentUploadCheck")
	public String studentUploadCheck( StudentExcelVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String errorCode = "";
		UsrUserInfoVO userInfoVO = new UsrUserInfoVO();
		userInfoVO.setSearchValue(vo.getUserId());
		userInfoVO.setSearchKey("userId");
		ProcessResultListVO<UsrUserInfoVO> resultVo = usrUserInfoService.list(userInfoVO);
		if(resultVo.getResult() <= 0 || resultVo.getReturnList().size() <= 0) {
			errorCode = "회원이 아닙니다. 회원등록후 응시생을 등록해주세요.";
		}
		if(StringUtils.hasText(errorCode)) {
			log.error("errorCode : " + errorCode);
		}
		vo.setErrorCode(errorCode);

		return JsonUtil.responseJson(response, vo);
	}

	/**
	 * 자격증 응시생 엑셀 업로드
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/addCertStudentExcel")
	public String addCertStudentExcel(StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String[] errorCode = request.getParameterValues("errorCode");
		String[] userId = request.getParameterValues("userId");
		String[] certScore = request.getParameterValues("certScore");
		String[] certPassYn = request.getParameterValues("certPassYn");
		String[] checkInsert = request.getParameterValues("chk");

		List<StudentExcelVO> studentExcelList = new ArrayList<StudentExcelVO>();
		for(int i=0; i < userId.length; i++) {
			boolean status = false;
			for (int j = 0; j < checkInsert.length; j++) {
				if(i+1 == Integer.parseInt(checkInsert[j])) {status =true;}
	        }
			if(status) {
				//-- 오류가 없는 것들만 처리 함.
				if(ValidationUtils.isEmpty(errorCode[i])) {
					StudentExcelVO seVO = new StudentExcelVO();
					seVO.setUserId(userId[i]);
					seVO.setCertScore(Integer.parseInt(certScore[i]));
					seVO.setCertPassYn(certPassYn[i]);
					studentExcelList.add(seVO);
				}
			}
		}
		
		CreateCourseVO ccvo = new CreateCourseVO();
		ccvo.setCrsCreCd(vo.getCrsCreCd());
		ccvo.setCreOperTypeCd("E");
		ccvo = createCourseService.viewCreateCourse(ccvo).getReturnVO();
		
		if(ccvo != null) {
			vo.setCrsCreCd(ccvo.getCrsCreCd());
			vo.setEnrlStartDttm(ccvo.getEnrlStartDttm());
			vo.setEnrlEndDttm(ccvo.getEnrlEndDttm());
		}

		ProcessResultVO<StudentVO> result = null;
		try {
			result = studentExcelService.addCertStudentExcel(studentExcelList, vo);
		} catch (Exception e) {
			result = new ProcessResultVO<StudentVO>().setResultFailed();
		}

		return JsonUtil.responseJson( response, result);

	}
	
	/**
	 * 개설 과정 등록 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/editCertStsPop")
	public String editCertStsPop( StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		ProcessResultVO<StudentVO> ResultVO = studentService.viewStudentInfo(vo);
		vo = ResultVO.getReturnVO();
		request.setAttribute("vo", vo);
		
		List<SysCodeVO> certStsList = sysCodeMemService.getSysCodeList("CERT_STS", UserBroker.getLocaleKey(request));
		request.setAttribute("certStsList", certStsList);

		return "mng/student/student/cert_edit_sts_write";
	}
	
	/**
	 * 자격증 신청 상태 변경
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/editCertSts")
	public String editCertSts(StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		ProcessResultVO<StudentVO> resultVO = null;
		try {
			resultVO = studentService.certAplcStudentByAdmin(vo);
			resultVO.setMessage("진행상태를 변경하였습니다.");
		} catch (Exception e) {
			resultVO = new ProcessResultVO<StudentVO>().setResultFailed();
			resultVO.setMessage("진행상태 변경 중 오류가 발생하였습니다. 잠시 후 다시 시도해주세요.");
		}
		return JsonUtil.responseJson( response, resultVO);
	}
	
	/**
	 * 자격증 과정 수강생 선택 합격처리
	 * @param vo
	 * @param commandMap
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value="/editConfirmStudentCertPass")
	public String editConfirmStudentCertPass ( StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		ProcessResultVO<StudentVO> resultVO = new ProcessResultVO<>(); 
		try {
			resultVO = studentService.confirmStudentCertPass(vo);
			resultVO.setMessage("합격 처리에 성공하였습니다.");
		} catch(MediopiaDefineException e1) {
			resultVO.setResultFailed();
			resultVO.setMessage(e1.getMessage());
		} catch (Exception e) {
			resultVO.setResultFailed();
			resultVO.setMessage("합격 처리에 실패하였습니다.");
		}
		
		return JsonUtil.responseJson(response, resultVO);
	}
	

	/**
	 * 자격증 과정 수강생 선택 불합격처리
	 * @param vo
	 * @param commandMap
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value="/editCancelStudentCertPass")
	public String editCancelStudentCertPass ( StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		ProcessResultVO<StudentVO> resultVO = new ProcessResultVO<>(); 
		try {
			resultVO = studentService.cancelStudentCertPass(vo);
			resultVO.setMessage("불합격 처리에 성공하였습니다.");
		} catch(MediopiaDefineException e1) {
			resultVO.setResultFailed();
			resultVO.setMessage(e1.getMessage());
		} catch (Exception e) {
			resultVO.setResultFailed();
			resultVO.setMessage("불합격 처리에 실패하였습니다.");
		}
		
		return JsonUtil.responseJson(response, resultVO);
	}
	
	/**
	 * 자격증 과정 수강생 상세 엑셀 다운로드
	 * @param vo
	 * @param commandMap
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/excelDownloadCertStdManage")
	public String excelDownloadCertStdManage ( StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		response.setHeader("Content-Disposition", "attachment;filename=cert_stu_list.xlsx;");
		response.setHeader( "Content-Transfer-Coding", "binary" );
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		OutputStream os = response.getOutputStream();
		
		ProcessResultVO resultVO = new ProcessResultVO<>();
		
		//-- 엑셀 출력 로그를 저장한다.
		LogPrnLogVO printLogVO = new LogPrnLogVO();
		printLogVO.setUserNo(UserBroker.getUserNo(request));
		printLogVO.setUserNm(UserBroker.getUserName(request));
		printLogVO.setPrnDoc("PRINT EXCEL : 자격증과정관리 엑셀 다운로드");
		printLogVO.setParam(vo.toString());
		logPrnLogService.add(printLogVO);

		//-- 개인정보 출력로그 저장
		LogPrivateInfoInqLogVO pilVO = new LogPrivateInfoInqLogVO();
		pilVO.setOrgCd(UserBroker.getUserOrgCd(request));
		pilVO.setMenuCd(UserBroker.getMenuCode(request));
		pilVO.setUserNo(UserBroker.getUserNo(request));
		pilVO.setUserNm(UserBroker.getUserName(request));
		pilVO.setDivCd("EXCEL");
		pilVO.setInqCts("EXCEL DOWNLOAD : 자격증과정관리 엑셀 다운로드");
		logPrivateInfoService.add(pilVO);
		
		try {
			int rowNum = 0;

			XSSFWorkbook wbook = new XSSFWorkbook();
			XSSFSheet sheet = wbook.createSheet("자격증과정 관리");

			//-- 컬럼 제목줄
			XSSFRow xssRow = sheet.createRow((short)rowNum);


			POIExcelUtil.createTitleCell("이름", xssRow, 0);
			POIExcelUtil.createTitleCell("점수", xssRow, 1);
			POIExcelUtil.createTitleCell("합격여부", xssRow, 2);
			POIExcelUtil.createTitleCell("자격증 신청일", xssRow, 3);
			POIExcelUtil.createTitleCell("신청상태", xssRow, 4);

			//-- 셀의 넓이 조절
			sheet.setColumnWidth(0, sheet.getDefaultColumnWidth() * 500);
			sheet.setColumnWidth(1, sheet.getDefaultColumnWidth() * 500);
			sheet.setColumnWidth(2, sheet.getDefaultColumnWidth() * 600);
			sheet.setColumnWidth(3, sheet.getDefaultColumnWidth() * 1000);
			sheet.setColumnWidth(4, sheet.getDefaultColumnWidth() * 600);
			
			List<StudentVO> stuList = studentService.listStudent(vo).getReturnList();
			
			
			for(StudentVO stuVO : stuList) {
				String certStsNm = "";
				String certPassYnNm = "";
				
				rowNum++;
				xssRow = sheet.createRow((short)rowNum);
				POIExcelUtil.createContentCell(StringUtil.nvl(stuVO.getUserNm()), xssRow, 0, "center");
				POIExcelUtil.createContentCell(StringUtil.nvl(stuVO.getCertScore()), xssRow, 1, "center");
				
				if("Y".equals(stuVO.getCertPassYn())) {
					certPassYnNm = "합격";
				}else if("N".equals(stuVO.getCertPassYn())) {
					certPassYnNm = "불합격";
				}
					
				POIExcelUtil.createContentCell(StringUtil.nvl(certPassYnNm), xssRow, 2, "center");
				POIExcelUtil.createContentCell(DateTimeUtil.getDateType(0, StringUtil.nvl(stuVO.getCertAplcDttm())), xssRow, 3, "center");
				
				if("E".equals(stuVO.getCertSts())) {
					certStsNm = "대기 중";
				}else if("N".equals(stuVO.getCertSts())) {
					certStsNm = "신청 취소";
				}else if("S".equals(stuVO.getCertSts())) {
					certStsNm = "승인 완료";
				}else if("F".equals(stuVO.getCertSts())) {
					certStsNm = "승인 취소";
				}
				
				POIExcelUtil.createContentCell(StringUtil.nvl(certStsNm), xssRow, 4, "center");
			}
			
			try {
				wbook.write(os);
			} catch (Exception ex) {
				String name = ex.getClass().getName();
				if (!name.equals("org.apache.catalina.connector.ClientAbortException")) {
					throw ex;
				}
			} finally {
				if(os != null) {
					os.close();
				}
			}
		} catch(IOException e1) {
			log.error("자격증과정관리 엑셀다운로드 오류 : IOException");
			resultVO.setResultFailed();
			resultVO.setMessage("자격증과정관리 엑셀다운로드 오류, IOException");
		} catch (Exception e) {
			log.error("자격증과정관리 엑셀다운로드 오류");
			resultVO.setResultFailed();
			resultVO.setMessage("자격증과정관리 엑셀다운로드 오류");
		}
		
		return null;
	}

	
}