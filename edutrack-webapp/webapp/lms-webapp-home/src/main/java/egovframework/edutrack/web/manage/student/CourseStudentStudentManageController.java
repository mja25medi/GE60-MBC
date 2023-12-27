package egovframework.edutrack.web.manage.student;

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
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.course.contents.service.ContentsService;
import egovframework.edutrack.modules.course.course.service.CourseService;
import egovframework.edutrack.modules.course.createcourse.service.CreateCourseService;
import egovframework.edutrack.modules.course.createcoursesubject.service.CreateCourseSubjectService;
import egovframework.edutrack.modules.course.decls.service.CreCrsDeclsService;
import egovframework.edutrack.modules.course.subject.service.SubjectService;
import egovframework.edutrack.modules.lecture.bookmark.service.BookmarkService;
import egovframework.edutrack.modules.log.message.service.LogMsgLogService;
import egovframework.edutrack.modules.log.privateinfo.service.LogPrivateInfoService;
import egovframework.edutrack.modules.log.prnlog.service.LogPrnLogService;
import egovframework.edutrack.modules.org.config.service.OrgUserInfoCfgService;
import egovframework.edutrack.modules.org.crscert.service.OrgCrsCertService;
import egovframework.edutrack.modules.org.emailtpl.service.OrgEmailTplService;
import egovframework.edutrack.modules.org.info.service.OrgOrgInfoService;
import egovframework.edutrack.modules.student.payment.service.PaymentService;
import egovframework.edutrack.modules.student.student.service.StudentExcelService;
import egovframework.edutrack.modules.student.student.service.StudentPDFService;
import egovframework.edutrack.modules.student.student.service.StudentService;
import egovframework.edutrack.modules.student.student.service.StudentVO;
import egovframework.edutrack.modules.system.config.service.SysCfgService;
import egovframework.edutrack.modules.user.dept.service.UsrDeptInfoService;
import egovframework.edutrack.modules.user.info.service.UsrUserInfoService;


@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/mng/course/std")
public class CourseStudentStudentManageController extends GenericController {

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
	 * [HRD] 회차관리 > 수강생 관리 - 메인
	 * @param vo
	 * @param commandMap
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */	
	@RequestMapping(value="/courseStdPayMain")
	public String courseStdPayMain( StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String crsCreCd = request.getParameter("crsCreCd");
		vo.setCrsCd(crsCreCd);
		
		request.setAttribute("paging", "Y");
		model.addAttribute("crsCreCd", crsCreCd);
		
		return "mng/student/student/course_student_payment_main";
	}
	
	/**
	 * [HRD] 회차관리 > 수강생 관리 - 리스트 페이징
	 * @param vo
	 * @param commandMap
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/listCourseStdPay")
	public String listCourseStdPay( StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		//기업, 기수, 과정 정보로 수강생+결제 리스트, 통계 조회
		ProcessResultListVO<StudentVO> resultListVO = studentService.listStudentPaymentInfoManagePageing(vo);
		
		model.addAttribute("stdPayList", resultListVO.getReturnList());//리스트
		model.addAttribute("stdPayStatusVO", resultListVO.getReturnVO());//상단 통계
		model.addAttribute("pageInfo", resultListVO.getPageInfo());//상단 통계
		

		return "mng/student/student/course_student_payment_list";
	}
	
	/**
	 * 수강 신청 삭제 처리
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/deleteCourseStudent")
	public String deleteCourseStudent( StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultVO<StudentVO> resultVO = studentService.deleteCourseStudent(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "student.message.student.delete.success"));
		} else {
			resultVO.setMessage(getMessage(request, "student.message.student.delete.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}
	
	
	
}