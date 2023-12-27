package egovframework.edutrack.web.home.student;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inicis.std.util.HttpUtil;
import com.inicis.std.util.ParseUtil;
import com.inicis.std.util.SignatureUtil;
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
import egovframework.edutrack.comm.service.SysMenuMemService;
import egovframework.edutrack.comm.util.security.CryptoUtil;
import egovframework.edutrack.comm.util.web.DateTimeUtil;
import egovframework.edutrack.comm.util.web.HttpRequestUtil;
import egovframework.edutrack.comm.util.web.InicisUtil;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.URLBuilder;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.comm.web.GenericController;
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
import egovframework.edutrack.modules.lecture.score.service.StdScoreService;
import egovframework.edutrack.modules.lecture.score.service.StdScoreVO;
import egovframework.edutrack.modules.log.message.service.LogMsgLogService;
import egovframework.edutrack.modules.log.message.service.LogMsgLogVO;
import egovframework.edutrack.modules.log.message.service.LogMsgTransLogVO;
import egovframework.edutrack.modules.org.crscert.service.OrgCrsCertService;
import egovframework.edutrack.modules.org.crscert.service.OrgCrsCertVO;
import egovframework.edutrack.modules.org.emailtpl.service.OrgEmailTplService;
import egovframework.edutrack.modules.org.info.service.OrgOrgInfoService;
import egovframework.edutrack.modules.org.info.service.OrgOrgInfoVO;
import egovframework.edutrack.modules.org.menu.service.OrgMenuVO;
import egovframework.edutrack.modules.org.page.service.OrgPageService;
import egovframework.edutrack.modules.org.page.service.OrgPageVO;
import egovframework.edutrack.modules.student.payment.service.BankCode;
import egovframework.edutrack.modules.student.payment.service.CardCode;
import egovframework.edutrack.modules.student.payment.service.PayMthodCode;
import egovframework.edutrack.modules.student.payment.service.PaymentInicisCancelResultDTO;
import egovframework.edutrack.modules.student.payment.service.PaymentInicisParCancelResultDTO;
import egovframework.edutrack.modules.student.payment.service.PaymentService;
import egovframework.edutrack.modules.student.payment.service.PaymentVO;
import egovframework.edutrack.modules.student.result.service.EduResultVO;
import egovframework.edutrack.modules.student.student.service.StudentService;
import egovframework.edutrack.modules.student.student.service.StudentVO;
import egovframework.edutrack.modules.student.student.service.ValidateRollbackStudentException;
import egovframework.edutrack.modules.system.code.service.SysCodeVO;
import egovframework.edutrack.modules.system.config.service.SysCfgService;
import egovframework.edutrack.modules.user.info.service.UsrUserInfoService;
import egovframework.edutrack.modules.user.info.service.UsrUserInfoVO;
import egovframework.edutrack.notification.MessageNotificationException;
import egovframework.edutrack.web.home.student.request.RefundAdd;
import egovframework.rte.psl.dataaccess.util.EgovMap;


/**
 * 수강 신청 엑션 컨트롤
 * @author JNOTE
 *
 */
@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/home/student")
public class StudentHomeController
		extends GenericController {

	@Autowired @Qualifier("studentService")
	private StudentService studentService;

	@Autowired @Qualifier("createCourseService")
	private CreateCourseService createCourseService;

	@Autowired @Qualifier("courseService")
	private CourseService courseService;

	@Autowired @Qualifier("usrUserInfoService")
	private UsrUserInfoService userInfoService;

	@Autowired @Qualifier("sysCfgService")
	private SysCfgService configService;

	@Autowired @Qualifier("sysCodeMemService")
	private SysCodeMemService codeMemService;

	@Autowired @Qualifier("orgOrgInfoService")
	private OrgOrgInfoService orgInfoService;

	@Autowired @Qualifier("createCourseTeacherService")
	private CreateCourseTeacherService createCourseTeacherService;

	@Autowired @Qualifier("createCourseSubjectService")
	private CreateCourseSubjectService createCourseSubjectService;

	@Autowired @Qualifier("contentsService")
	private ContentsService contentsService;

	@Autowired @Qualifier("timetableService")
	private TimetableService timetableService;

	@Autowired @Qualifier("crsTchService")
	private CrsTchService 		crsTchService;

	@Autowired @Qualifier("orgEmailTplService")
	private OrgEmailTplService 	emailTplService;

	@Autowired @Qualifier("logMsgLogService")
	private LogMsgLogService		messageService;

	@Autowired @Qualifier("orgCrsCertService")
	private OrgCrsCertService	orgCrsCertService;

	@Autowired @Qualifier("sysMenuMemService")
	private SysMenuMemService 				sysMenuMemService;
	
	@Autowired @Qualifier("paymentService")
	private PaymentService paymentService;
	
	@Autowired @Qualifier("orgPageService")
	private OrgPageService orgPageService;
	
	@Autowired @Qualifier("stdScoreService")
	private StdScoreService			stdScoreService;

	/**
	 * 수강 신청 화면
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/enrollCourseMain")
	public String enrollCourseMain(StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		vo.setUserNo(UserBroker.getUserNo(request));

		// 인증 관련 처리 완료후 돌아올 페이지 URL을 설정
		request.setAttribute("goUrl", HttpRequestUtil.requestToUrlBuilder(request).toString());

		// 비로그인 처리(임시회원 포함)
		if(!UserBroker.isLogin(request) || UserBroker.getUserType(request).equals("MEM_PROVIS")) {
			return "redirect:"+ new URLBuilder("/home/main/goMenuPage")
				.addParameter("mcd", "MC00000172") .toString();
		}

		if(ValidationUtils.isNotEmpty(vo.getMcd())) {
			OrgMenuVO menuVO = sysMenuMemService.decideHomeMenuWithSession(vo.getMcd(), request);
		}

		//-- 사용자 정보를 가져온다.
		UsrUserInfoVO usrUserInfoVO = new UsrUserInfoVO();
		usrUserInfoVO.setUserNo(UserBroker.getUserNo(request));
		usrUserInfoVO = userInfoService.view(usrUserInfoVO);


		//-- 과정 개설 정보를 가져온다.
		CreateCourseVO createCourseVO = new CreateCourseVO();
		createCourseVO.setCrsCtgrCd(vo.getCrsCtgrCd());
		createCourseVO.setCrsCreCd(vo.getCrsCreCd());
		createCourseVO = createCourseService.viewCreateCourse(createCourseVO).getReturnVO();
		request.setAttribute("createCourseVO", createCourseVO);

		//-- 인원제한 과정인 경우 인원 검색하여 수강신청 못하도록 함.
		if(createCourseVO.getStuCnt() >= StringUtil.nvl(createCourseVO.getEnrlNop(),0) && "Y".equals(createCourseVO.getNopLimitYn())) {
			request.setAttribute("errMsgCode", "900");
			return "home/student/student/enrl_result_main";
		}

		//-- 과정의 정보를 가져온다.
		CourseVO courseVO = new CourseVO();
		courseVO.setCrsCd(createCourseVO.getCrsCd());
		courseVO = courseService.view(courseVO).getReturnVO();

		//-- 해당 과정의 강사나 튜터 인지 검색 강사나 튜터인 경우 수강신청 못함.
		TeacherVO teacherVO = new TeacherVO();
		teacherVO.setUserNo(UserBroker.getUserNo(request));
		teacherVO.setCrsCreCd(vo.getCrsCreCd());
		teacherVO = createCourseTeacherService.isTeacher(teacherVO).getReturnVO();
		if("Y".equals(teacherVO.getTchYn())) {
			request.setAttribute("errMsgCode", "110");
			request.setAttribute("errMsg", "적절하지 않은 대상입니다.");
			return "home/student/student/enrl_result_main";
		}

		//-- 수강신청이 이미 되어 있는 경우 처리
		StudentVO iStudentVO = new StudentVO();
		iStudentVO = studentService.isEnroll(vo).getReturnVO();
		if("Y".equals(iStudentVO.getStdYn())) {
			request.setAttribute("errMsgCode", "100");
			request.setAttribute("errMsg", "이미 수강 신청한 과정입니다.");
			return "home/student/student/enrl_result_main";
		}

		//-- 실명인증 체크, 실명인증 체크해야 하고, 실명인증이 되어 있지 않은경우
//		if("Y".equals(configService.getValue("ENROLL", "NAMECHK")) && "N".equals(userInfoVO.getRealnmCertYn())) {
//			return forward(new URLBuilder("/home/user/oivsNameCheckForm").toString());
//		}


		//-- 가격 정보가 있으면 결제로 연결
		if(createCourseVO.getEduPrice() > 0) {
			return "redirect:"+ new URLBuilder("/home/student/enrollPaymMain")
			.addParameter("crsCreCd", vo.getCrsCreCd())
			.toString();
		}

		return "redirect:"+ new URLBuilder("/home/student/enrollRegistMain")
		.addParameter("crsCreCd", vo.getCrsCreCd())
		.toString();
	}
	
	/**
	 * 수강 신청 화면
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
/*	@RequestMapping(value="/enrollPaymMain")
	public String enrollPaymMain(StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);

		if(ValidationUtils.isEmpty(vo.getCrsCreCd())) {
			setAlertMessage(request, getMessage(request, "student.message.student.alert.nocourse"));
			return new URLBuilder("/home/main/goMenuPage")
			.addParameter("mcd", UserBroker.getMenuCode(request)) .toString();
		}

		//-- 개설과정의 정보를 가져온다.
		CreateCourseVO createCourseVO = new CreateCourseVO();
		createCourseVO.setCrsCreCd(vo.getCrsCreCd());
		createCourseVO = createCourseService.viewCreateCourse(createCourseVO).getReturnVO();
		request.setAttribute("createCourseVO", createCourseVO);

		List<SysCodeVO> codeList = codeMemService.getSysCodeList("PAYM_MTHD_CD");
		request.setAttribute("codeList", codeList);

		OrgOrgInfoVO orgInfoVO = new OrgOrgInfoVO();
		orgInfoVO.setOrgCd(orgCd);
		orgInfoVO = orgInfoService.view(orgInfoVO);
		request.setAttribute("orgInfoVO", orgInfoVO);

		UsrUserInfoVO userInfoVO = new UsrUserInfoVO();
		userInfoVO.setUserNo(userNo);
		userInfoVO = userInfoService.view(userInfoVO);
		request.setAttribute("userInfoVO", userInfoVO);

		return "home/student/student/enrl_payment_main";
	}*/
	
	@RequestMapping(value="/enrollPaymMain")
	public String enrollPaymMain(PaymentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		//response.setHeader("Set-Cookie", "jsessionid=" + request.getSession().getId() + "; Secure; SameSite=None");
			String orgCd = UserBroker.getUserOrgCd(request);
			String userNo = UserBroker.getUserNo(request);
			
			// 비로그인 처리(임시회원 포함)
			if(!UserBroker.isLogin(request) || UserBroker.getUserType(request).equals("MEM_PROVIS")) {
				setAlertMessage(request, "로그인 후 이용바랍니다.");
				return "redirect:"+ new URLBuilder("/home/main/indexMain").toString();
			}
			
			//회원정보 조회
			UsrUserInfoVO userInfoVO = new UsrUserInfoVO();
			userInfoVO.setUserNo(userNo);
			userInfoVO = userInfoService.view(userInfoVO);
			model.addAttribute("userInfoVO", userInfoVO);
			
			
			/*
			 * String deptCd = StringUtil.nvl(userInfoVO.getDeptCd()); vo.setDeptCd(deptCd);
			 */
			vo.setUserNo(userNo);
			 
			/*
			 * if(ValidationUtils.isEmpty(deptCd)) { setAlertMessage(request,
			 * "회원 정보에서 기업정보를 입력바랍니다."); return "redirect:"+ new
			 * URLBuilder("/home/main/indexMain").toString(); }
			 */
			
			model.addAttribute("paymentVO", vo);
			
			//bsk 조회
			List<PaymentVO> usrBskList = paymentService.listBasketForEnrollByUserNoDeptCd(vo).getReturnList();
			
			//리스트 없으면 교육과정및신청 페이지로 이동
			if(usrBskList == null || usrBskList.size() == 0) {
				setAlertMessage(request, "신청가능한 과정이 없습니다. 교육과정 및 신청 페이지에서 수강신청 과정을 추가바랍니다.");
				return "redirect:" + new URLBuilder("/home/main/goMenuPage?mcd=MC00000023").toString();
			}
			
			
			model.addAttribute("usrBskList", usrBskList);
			
			OrgPageVO pageVO = new OrgPageVO();
			pageVO.setOrgCd(orgCd);
			
			// 환불규정
			pageVO.setPageCd("PAGE035");
			OrgPageVO refundPageVO = orgPageService.view(pageVO);
			model.addAttribute("refundPageVO", refundPageVO);
			model.addAttribute("vbankDateLimit", LocalDateTime.now().plusDays(7).format(DateTimeFormatter.ofPattern("yyyyMMdd")));//가상계좌 입금기한 설정

		return "home/student/student/enrl_payment_main";
	}

	/**
	 * 수강 등록
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/enrollRegistMain")
	public String enrollRegistMain(StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String userNo = UserBroker.getUserNo(request);

		// 인증 관련 처리 완료후 돌아올 페이지 URL을 설정
		request.setAttribute("goUrl", HttpRequestUtil.requestToUrlBuilder(request).toString());

		vo.setUserNo(userNo);

		//-- 개설 과정의 정보를 가져온다.
		CreateCourseVO createCourseVO = new CreateCourseVO();
		createCourseVO.setCrsCreCd(vo.getCrsCreCd());
		createCourseVO = createCourseService.viewCreateCourse(createCourseVO).getReturnVO();
		request.setAttribute("createCourseVO", createCourseVO);

		//-- 인원제한 과정인 경우 인원 검색하여 수강신청 못하도록 함.
		if("Y".equals(StringUtil.nvl(createCourseVO.getNopLimitYn(),"N"))) {
			if(createCourseVO.getStuCnt() >= createCourseVO.getEnrlNop()) {
				request.setAttribute("errMsgCode", "900");
				return "home/student/student/enrl_result_main";
			}
		}

		//-- 과목의 정보를 가져온다.
		CourseVO courseVO = new CourseVO();
		courseVO.setCrsCd(createCourseVO.getCrsCd());
		courseVO = courseService.view(courseVO).getReturnVO();
		request.setAttribute("courseVO", courseVO);

		//-- 이미 수강등록이 되어 있는 경우
		StudentVO iStudentVO = new StudentVO();
		iStudentVO = studentService.isEnroll(vo).getReturnVO();
		if("Y".equals(iStudentVO.getStdYn())) {
			request.setAttribute("errMsgCode", "100");
			return "home/student/student/enrl_result_main";
		}


		vo.setEnrlSts("E"); //수강대기(E),수강중(S),수료(C),수료취소(F)
		vo.setDeclsNo(1);
		//-- 과정 속성에 따른 수강기간 설정 (수강인증이 자동일때 처리) 수동인 경우 수강일은 수강인증 처리시 작동하도록 함.
		if("AT".equals(courseVO.getEnrlCertMthd())) {
			String startDttm = "";
			String endDttm = "";
			String auditEndDttm = "";
			if("R".equals(courseVO.getCrsOperType())) {
				//-- 정규 강좌인 경우 과정의 수강기간을 따른다.
				startDttm = StringUtil.ReplaceAll(createCourseVO.getEnrlStartDttm(),".","")+"000001";
				endDttm = StringUtil.ReplaceAll(createCourseVO.getEnrlEndDttm(),".","")+"235959";
				auditEndDttm = StringUtil.ReplaceAll(createCourseVO.getAuditEndDttm(), ".", "")+"235959";
			} else {
				//-- 상시 강좌인 경우 수강신청 일부터 수강일 수 까지
				startDttm = DateTimeUtil.getDate()+"000001";
				int enrlDaycnt = 30;
				if(createCourseVO.getEnrlDaycnt() > 0) enrlDaycnt = createCourseVO.getEnrlDaycnt();
				endDttm = DateTimeUtil.afterDate(enrlDaycnt)+"235959";
				auditEndDttm = endDttm;
			}
			vo.setEnrlStartDttm(startDttm);
			vo.setEnrlEndDttm(endDttm);
			vo.setAuditEndDttm(auditEndDttm);
			vo.setEnrlSts("S");
		}
		//가격정보가 없는 경우 수강기간을 자동으로 따르도록 한다.
		if(createCourseVO.getEduPrice().equals(null) || createCourseVO.getEduPrice() == 0) {
			String startDttm = "";
			String endDttm = "";
			String auditEndDttm = "";
			
			startDttm = StringUtil.ReplaceAll(createCourseVO.getEnrlStartDttm(),".","")+"000001";
			endDttm = StringUtil.ReplaceAll(createCourseVO.getEnrlEndDttm(),".","")+"235959";
			auditEndDttm = StringUtil.ReplaceAll(createCourseVO.getAuditEndDttm(), ".", "")+"235959";
			
			vo.setEnrlStartDttm(startDttm);
			vo.setEnrlEndDttm(endDttm);
			vo.setAuditEndDttm(auditEndDttm);
			vo.setEnrlSts("S");
		}
		//-- 과정의 결제가 필요한 경우 처리
		if(createCourseVO.getEduPrice() > 0) {
			//-- 카드 결제나 계좌이체인 경우
			if("PAYM001".equals(vo.getPaymMthdCd()) || "PAYM002".equals(vo.getPaymMthdCd())) {
				//-- 카드결제나 계좌이체에 대한 처리 루틴 필요.

			} else {
				//-- 그 외에는 ....
				vo.setEnrlSts("E"); //-- 입금 확인후 관리자가 수강인등 함.
				vo.setPaymPrice(0);
				vo.setPaymStsCd("N");
			}
		}

		//-- 사용자 정보를 받아온다
		UsrUserInfoVO userInfoVO = new UsrUserInfoVO();
		userInfoVO.setUserNo(userNo);
		userInfoVO = userInfoService.view(userInfoVO);

		//-- 기초 정보를 스튜던트로 복사
		try {
			BeanUtils.copyProperties(vo, userInfoVO);
			vo.setRegNo(userNo); // 샤용자 정보와 합치는 중 .. 이관시 regNo도 같이 합쳐주고 있어 강제로 사용자 아이디로 변경함
			vo.setModNo(userNo);
		} catch (Exception ex) {
			// TODO: handle exception
		}
		try {
			ProcessResultVO<StudentVO> result = studentService.addEnrollStudent(vo);
			request.setAttribute("stdNo", vo.getStdNo());
			request.setAttribute("errMsgCode", "0");

			String orgCd = UserBroker.getUserOrgCd(request);
			OrgOrgInfoVO orgInfoVO = new OrgOrgInfoVO();
			orgInfoVO.setOrgCd(orgCd);
			orgInfoVO = orgInfoService.view(orgInfoVO);

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
			String emailTplCd = "EM011";
			if("E".equals(vo.getEnrlSts())) {
				emailTplCd = "EM010";
			}

			// 메일 데코레이션
			Map<String, Object> argu = new HashMap<String, Object>();
			argu.put("Name",userInfoVO.getUserNm());
			argu.put("UserID", userInfoVO.getUserId());
			argu.put("CourseName", createCourseVO.getCrsCreNm()+" ["+createCourseVO.getCreYear()+"/"+createCourseVO.getCreTerm()+"]");
			argu.put("CourseDuration", createCourseVO.getEnrlStartDttm()+"~"+createCourseVO.getEnrlEndDttm());
			argu.put("SendDate", DateTimeUtil.getCurrentString("yy.MM.dd"));
			emailTplService.decorator(orgCd, emailTplCd, argu, message);

			try{
				messageService.addMessageWithSend(message);
			}catch (MessageNotificationException ex) {
			}
		} catch (Exception ex) {
			request.setAttribute("errMsgCode", "300");
		}


		return "home/student/student/enrl_result_main";
	}

	/**
	 * 수강신청 취소
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/readFormCancel")
	public String readFormCancel(StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String menuCode = UserBroker.getMenuCode(request);

		ProcessResultVO<StudentVO> resultVO =
		studentService.cancelStudent(vo, request);

		return "redirect:"+"/home/main/goMenuPage?mcd="+menuCode;
	}


	/**
	 * 실명인증 팝업 화면
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/readFormCert")
	public String readFormCert(Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {


		return "home/student/student/person_certification_pop";
	}

	/**
	 * 실명 인증 확인
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/readCert")
	public String readCert(UsrUserInfoVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		commonVOProcessing(vo, request);

		ProcessResultVO<UsrUserInfoVO> resultVO = null;
		resultVO = ProcessResultVO.success("SUCCESS");// 실명인증 무조건 성공으로 보내준다.
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 수료증 출력
	 * 수정 2015.11.27 jamfam
	 * - PDF 출력으로 변경
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/printCertificate")
	public String printCertificate(StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);

		CreateCourseVO createCourseVO = new CreateCourseVO();
		createCourseVO.setCrsCreCd(vo.getCrsCreCd());
		createCourseVO = createCourseService.viewCreateCourse(createCourseVO).getReturnVO();

		CourseVO courseVO = new CourseVO();
		courseVO.setCrsCd(createCourseVO.getCrsCd());
		courseVO = courseService.view(courseVO).getReturnVO();

		String totalEduTm = "0";
		if("ON".equals(courseVO.getCrsOperMthd())) {
			totalEduTm = createCourseVO.getOnlnEduTm();
		} else if("OF".equals(courseVO.getCrsOperMthd())) {
			totalEduTm = createCourseVO.getOflnEduTm();
		} else {
			totalEduTm = Integer.toString(Integer.parseInt(StringUtil.nvl(createCourseVO.getOnlnEduTm(),"0")) + Integer.parseInt(StringUtil.nvl(createCourseVO.getOflnEduTm(),"0")));
		}

		//-- 수강생 정보를 가져온다.
		vo = studentService.viewStudent(vo).getReturnVO();
		String enrlCpltDttm = vo.getEnrlCpltDttm();
		String year = StringUtil.substring(enrlCpltDttm, 0, 4);
		String month = StringUtil.substring(enrlCpltDttm, 4, 6);
		String day = StringUtil.substring(enrlCpltDttm, 6, 8);

		try {
			//-- 수료증 정보를 가져온다.
			OrgCrsCertVO orgCrsCertVO = new OrgCrsCertVO();
			orgCrsCertVO.setOrgCd(orgCd);
			orgCrsCertVO = orgCrsCertService.view(orgCrsCertVO);

			String studyPeriod = "";
			//-- 정규과정 / 상시과정에 따른 수강기간 가져온기
			if("R".equals(courseVO.getCrsOperType())) {
				studyPeriod = createCourseVO.getEnrlStartDttm()+"~"+createCourseVO.getEnrlEndDttm();
			} else {
				studyPeriod = DateTimeUtil.getDateType(1,vo.getEnrlStartDttm())+"~"+DateTimeUtil.getDateType(1,vo.getEnrlEndDttm());
			}

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

	        // step 5 : Background Image 등록
			writer.getDirectContentUnder().addImage(background, width, 0, 0, height, 0, 0);


	        // step 6
			String args[] = new String[1];
			args[0]  = vo.getCpltNo();
	        String cpltNo 		= getMessage(request, "org.title.certificate.cpltno", args);
	        String userNm 		= getMessage(request, "org.title.certificate.username")+" : "+vo.getUserNm();
	        String birthDay		= getMessage(request, "org.title.certificate.birthday")+" : "+vo.getBirth();
	        String crsNm 		= getMessage(request, "org.title.certificate.course")+" : "+createCourseVO.getCrsCreNm();
	        String crsPeriod 	= getMessage(request, "org.title.certificate.period")+" : "+studyPeriod;
	        String crsTime		= getMessage(request, "org.title.certificate.time")+" : "+totalEduTm+" "+getMessage(request, "common.title.time");
	        String prnDay 		= year+"."+month+"."+day;


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

	        document.close();
			writer.close();

			return null;
		} catch (Exception e) {
			//-- 수료증 정보가 없을 경우는 기본 html 출력
			request.setAttribute("createCourseVO", createCourseVO);
			request.setAttribute("courseVO", courseVO);
			request.setAttribute("vo", vo);
			request.setAttribute("year", year);
			request.setAttribute("month", month);
			request.setAttribute("day", day);

			String msie6 = "N";
			String userBr = request.getHeader("User-Agent");
			if(userBr.indexOf("MSIE 6") > 0 || userBr.indexOf("MSIE 7") > 0) msie6 = "Y";
			request.setAttribute("msie6", msie6);

			return "home/student/student/print_certification_default_pop";
		}
	}
	
	/**
	 * 수료증 출력
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/printCert")
	public String printCert(StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);

		CreateCourseVO createCourseVO = new CreateCourseVO();
		createCourseVO.setCrsCreCd(vo.getCrsCreCd());
		createCourseVO = createCourseService.viewCreateCourse(createCourseVO).getReturnVO();

		CourseVO courseVO = new CourseVO();
		courseVO.setCrsCd(createCourseVO.getCrsCd());
		courseVO = courseService.view(courseVO).getReturnVO();

		String totalEduTm = "0";
		if("ON".equals(courseVO.getCrsOperMthd())) {
			totalEduTm = createCourseVO.getOnlnEduTm();
		} else if("OF".equals(courseVO.getCrsOperMthd())) {
			totalEduTm = createCourseVO.getOflnEduTm();
		} else {
			totalEduTm = Integer.toString(Integer.parseInt(StringUtil.nvl(createCourseVO.getOnlnEduTm(),"0")) + Integer.parseInt(StringUtil.nvl(createCourseVO.getOflnEduTm(),"0")));
		}
		createCourseVO.setEduTm(totalEduTm);

		//-- 수강생 정보를 가져온다.
		vo = studentService.viewStudent(vo).getReturnVO();
		String nowDttm = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
		String year = StringUtil.substring(nowDttm, 0, 4);
		String month = StringUtil.substring(nowDttm, 4, 6);
		String day = StringUtil.substring(nowDttm, 6, 8);
		
		//-- 수료증 정보를 가져온다.
		OrgCrsCertVO orgCrsCertVO = new OrgCrsCertVO();
		orgCrsCertVO.setOrgCd(orgCd);
		orgCrsCertVO = orgCrsCertService.view(orgCrsCertVO);

		request.setAttribute("createCourseVO", createCourseVO);
		request.setAttribute("courseVO", courseVO);
		request.setAttribute("vo", vo);
		request.setAttribute("year", year);
		request.setAttribute("month", month);
		request.setAttribute("day", day);
		request.setAttribute("orgCrsCertVO", orgCrsCertVO);
		
		String msie6 = "N";
		String userBr = request.getHeader("User-Agent");
		if(userBr.indexOf("MSIE 6") > 0 || userBr.indexOf("MSIE 7") > 0) msie6 = "Y";
		request.setAttribute("msie6", msie6);
		try {
			return "home/student/student/print_certification_pop";
		} catch (Exception e) {
			//-- 수료증 정보가 없을 경우는 기본 html 출력
			return "home/student/student/print_certification_default_pop";
		}
	}

	/**
	 * 수료증 출력 (리포트용)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/printCertificateReport")
	public String printCertificateReport(StudentVO vo, Map commandMap, ModelMap model,
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
		String reportUrl = configService.getValue("REPORTSVR", "REPORTURL");
		request.setAttribute("reportUrl", reportUrl);

		String reportFile = "";
		if("O00073".equals(createCourseVO.getCrsCd())) {
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

		return "home/student/student/print_certification_report";
	}

	/**
	 * 영수증 출력 (리포트용)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/printReceiptReport")
	public String printReceiptReport(StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		//-- 리포트 경로를 가져온다.
		String reportUrl = configService.getValue("REPORTSVR", "REPORTURL");
		request.setAttribute("reportUrl", reportUrl);

		String reportFile = "receipt";

		request.setAttribute("reportFile", reportFile);
		request.setAttribute("stdNo", vo.getStdNo());

		return "home/student/student/print_receipt_report";
	}

	/**
	 * 수강신청 정보 보기
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/viewEnrollMain")
	public String viewEnrollMain(StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		vo = studentService.viewStudentInfo(vo).getReturnVO();

		CreateCourseVO createCourseVO = new CreateCourseVO();
		createCourseVO.setCrsCreCd(vo.getCrsCreCd());

		//-- 과정 개설 정보를 가져온다.
		createCourseVO = createCourseService.viewCreateCourse(createCourseVO).getReturnVO();
		request.setAttribute("createCourseVO", createCourseVO);

		//-- 과정 정보를 가져온다.
		CourseVO courseVO = new CourseVO();
		courseVO.setCrsCd(createCourseVO.getCrsCd());
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
		onlineSubjectVO.setCrsCreCd(createCourseVO.getCrsCreCd());
		List<CreateOnlineSubjectVO> onlineSubjectList = createCourseSubjectService.listOnlineSubject(onlineSubjectVO).getReturnList();
		for(CreateOnlineSubjectVO onlnSbjVO : onlineSubjectList) {
			ContentsVO contentsVO = new ContentsVO();
			contentsVO = contentsService.listContentsTree(onlnSbjVO.getSbjCd(), "");
			onlnSbjVO.setContentsVO(contentsVO);
		}
		request.setAttribute("onlineSubjectList", onlineSubjectList);

		//-- 시간표 정보를 가져온다.
		TimetableVO timetableVO = new TimetableVO();
		timetableVO.setCrsCreCd(createCourseVO.getCrsCreCd());
		List<TimetableVO> timetableList = timetableService.listTimetable(timetableVO).getReturnList();
		request.setAttribute("timetableList", timetableList);

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


		return "home/student/student/view_enroll_info_main";
	}
	
	
	/**
	 * [HRD] 교육과정 및 신청 - 장바구니 담기
	 * @param vo
	 * @param commandMap
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value="/addBasket")
	public String addBasket(PaymentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++가나다");
		commonVOProcessing(vo, request);
		
		ProcessResultVO<PaymentVO> resultVO = new ProcessResultVO<>();
		
		String userNo = UserBroker.getUserNo(request);
		vo.setUserNo(userNo);
		
		if(ValidationUtils.isEmpty(vo.getCrsCreCd())) {
			resultVO.setResultFailed();
			resultVO.setMessage("선택한 강의가 잘못되었습니다. 다시 시도바랍니다.");
			return JsonUtil.responseJson(response, resultVO);
		}
		
		//회원정보 조회 - 기업정보 확인
		UsrUserInfoVO userInfoVO = new UsrUserInfoVO();
		userInfoVO.setUserNo(userNo);
		userInfoVO = userInfoService.view(userInfoVO);
		model.addAttribute("userInfoVO", userInfoVO);
		
		/*
		 * String deptCd = StringUtil.nvl(userInfoVO.getDeptCd()); vo.setDeptCd(deptCd);
		 * 
		 * if(ValidationUtils.isEmpty(deptCd)) { setAlertMessage(request,
		 * "회원 정보에서 기업정보를 입력바랍니다."); return "redirect:"+ new
		 * URLBuilder("/home/main/indexMain").toString(); }
		 */
		
		//담기 전 수강생이 신청 가능한 개설과정인지 확인
		vo.setCrsCreCd(vo.getCrsCreCd());
		/*List<PaymentVO> enrollAbleList = paymentService.listBasketForEnrollByUserNoDeptCd(vo).getReturnList();
		if(enrollAbleList != null && enrollAbleList.size() == 0) {
			try {
				paymentService.addBasket(vo);
			} catch(MediopiaDefineException e1) {
				resultVO.setResultFailed();
				resultVO.setMessage(e1.getMessage());
			} catch (Exception e) {
				resultVO.setResultFailed();
				resultVO.setMessage("수강 바구니에 추가하지 못하였습니다.");
			}
		}else {
			resultVO.setResultFailed();
			resultVO.setMessage("수강 바구니에 담을 수 없습니다.\n이미 바구니에 존재하거나, 수강신청 기간이 아니거나, 수강신청한 강의입니다.");
		}*/
		
		try {
			ProcessResultVO<PaymentVO> addBasket = paymentService.addBasket(vo);
			resultVO.setResult(addBasket.getResult());
			resultVO.setMessage(addBasket.getMessage());
		} catch(MediopiaDefineException e1) {
			resultVO.setResultFailed();
			resultVO.setMessage(e1.getMessage());
		} catch (Exception e) {
			resultVO.setResultFailed();
			resultVO.setMessage("수강 바구니에 추가하지 못하였습니다.");
		}
		
		return JsonUtil.responseJson(response, resultVO);
	}
	
	/**
	 * [HRD] 장바구니 삭제
	 * @param vo
	 * @param commandMap
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value="/removeBasket")
	public String removeBasket( PaymentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		ProcessResultVO<PaymentVO> resultVO = new ProcessResultVO<>();
		
		String userNo = UserBroker.getUserNo(request);
		vo.setUserNo(userNo);
		
		if(ValidationUtils.isEmpty(vo.getCrsCreCd())) {
			resultVO.setResultFailed();
			resultVO.setMessage("선택한 강의가 잘못되었습니다. 다시 시도바랍니다.");
			return JsonUtil.responseJson(response, resultVO);
		}
		
		//회원정보 조회 - 기업정보 확인
		UsrUserInfoVO userInfoVO = new UsrUserInfoVO();
		userInfoVO.setUserNo(userNo);
		userInfoVO = userInfoService.view(userInfoVO);
		model.addAttribute("userInfoVO", userInfoVO);
		
		/*
		 * String deptCd = StringUtil.nvl(userInfoVO.getDeptCd()); vo.setDeptCd(deptCd);
		 * 
		 * if(ValidationUtils.isEmpty(deptCd)) { setAlertMessage(request,
		 * "회원 정보에서 기업정보를 입력바랍니다."); return "redirect:"+ new
		 * URLBuilder("/home/main/indexMain").toString(); }
		 */
		
		resultVO = paymentService.deleteBasket(vo);
		return JsonUtil.responseJson(response, resultVO);
	}
	
	/**
	 * [HRD] 수강신청결제 - 수강 신청 화면
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/enrollPayBasketMain")
	public String enrollBasketMain(PaymentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		//response.setHeader("Set-Cookie", "jsessionid=" + request.getSession().getId() + "; Secure; SameSite=None");
		String orgCd = UserBroker.getUserOrgCd(request);
		String userNo = UserBroker.getUserNo(request);
		
		// 비로그인 처리(임시회원 포함)
		if(!UserBroker.isLogin(request) || UserBroker.getUserType(request).equals("MEM_PROVIS")) {
			setAlertMessage(request, "로그인 후 이용바랍니다.");
			return "redirect:"+ new URLBuilder("/home/main/indexMain").toString();
		}
		
		//회원정보 조회
		UsrUserInfoVO userInfoVO = new UsrUserInfoVO();
		userInfoVO.setUserNo(userNo);
		userInfoVO = userInfoService.view(userInfoVO);
		model.addAttribute("userInfoVO", userInfoVO);
		
		
		/*
		 * String deptCd = StringUtil.nvl(userInfoVO.getDeptCd()); vo.setDeptCd(deptCd);
		 */
		vo.setUserNo(userNo);
		 
		/*
		 * if(ValidationUtils.isEmpty(deptCd)) { setAlertMessage(request,
		 * "회원 정보에서 기업정보를 입력바랍니다."); return "redirect:"+ new
		 * URLBuilder("/home/main/indexMain").toString(); }
		 */
		
		model.addAttribute("paymentVO", vo);
		
		List<PaymentVO> usrBskList = paymentService.listBasketForEnrollByUserNoDeptCd(vo).getReturnList();
		
		//리스트 없으면 교육과정및신청 페이지로 이동
		if(usrBskList == null || usrBskList.size() == 0) {
			setAlertMessage(request, "신청가능한 과정이 없습니다. 교육과정 및 신청 페이지에서 수강신청 과정을 추가바랍니다.");
			return "redirect:" + new URLBuilder("/home/main/goMenuPage?mcd=MC00000023").toString();
		}
		
		int usrBskTotPrice = 0;
		for(PaymentVO listPaymentVO : usrBskList) {
			usrBskTotPrice += listPaymentVO.getEduPrice();
		}
		
		model.addAttribute("usrBskTotPrice", usrBskTotPrice);
		model.addAttribute("usrBskList", usrBskList);
		
		OrgPageVO pageVO = new OrgPageVO();
		pageVO.setOrgCd(orgCd);
		
		// 환불규정
		pageVO.setPageCd("PAGE035");
		OrgPageVO refundPageVO = orgPageService.view(pageVO);
		model.addAttribute("refundPageVO", refundPageVO);
		model.addAttribute("vbankDateLimit", LocalDateTime.now().plusDays(7).format(DateTimeFormatter.ofPattern("yyyyMMdd")));//가상계좌 입금기한 설정
		
		return "home/student/student/enrl_payment_basket_main";
	}
	
	/**
	 * [HRD] 교육과정/신청>수강신청결제 - 수강신청(장바구니 결제)
	 */
	@PostMapping(value="/enrollBasketRegistMain")
	public String enrollBasketRegistMain(StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String userNo = UserBroker.getUserNo(request);
		String orgCd = UserBroker.getUserOrgCd(request);
		
		PaymentVO paymentVO = new PaymentVO();
		paymentVO.setRegNo(userNo);
		paymentVO.setOrgCd(orgCd);
		paymentVO.setRegIp(request.getRemoteAddr());
		
		paymentVO.setUserNo(userNo);
		paymentVO.setDeviceType("PC");
		
		/**
		 * 에러 코드
		 * 100 : 수강신청 전 검증 오류
		 * 200 : 결제 관련
		 * 400 : 로그인X
		 * 401 : 회원의 기업코드 없는 경우
		 */
		//파라미터, DB 검증 / 기업코드, 수강생 리스트 등 세팅
		try {
			enrollVaildationAndSet(request, response, paymentVO, userNo, orgCd);
		} catch (Exception e) {
			log.error("수강신청 검증 에러");
			if("400".equals(e.getMessage()) || "401".equals(e.getMessage())) {//로그아웃, 기업정보 없는 경우 메인이동
				return "redirect:"+ new URLBuilder("/home/main/indexMain").toString();
			}else {
				return enrollBasketResultRedirect("100", StringUtil.nvl(e.getMessage(), "수강신청 검증 오류"));
			}
		}

		//결제 모듈 호출
		String returnPaymNo = "";
		try{

			//#############################
			// 인증결과 파라미터 일괄 수신
			//#############################
			request.setCharacterEncoding("UTF-8");
			Map<String,String> paramMap = new Hashtable<String,String>();
			Enumeration elems = request.getParameterNames();

			String temp = "";
			while(elems.hasMoreElements())
			{
				temp = (String) elems.nextElement();
				paramMap.put(temp, request.getParameter(temp));
			}
			
			System.out.println("paramMap : "+ paramMap.toString());
			
			//#####################
			// 인증이 성공일 경우만
			//#####################
			if("0000".equals(paramMap.get("resultCode"))){

				/*out.println("####인증성공/승인요청####");
				out.println("<br/>");*/
				System.out.println("####인증성공/승인요청####");

				//############################################
				// 1.전문 필드 값 설정(***가맹점 개발수정***)
				//############################################
				
				String mid 		= paramMap.get("mid");						// 가맹점 ID 수신 받은 데이터로 설정
				String signKey	= "SU5JTElURV9UUklQTEVERVNfS0VZU1RS";		// 가맹점에 제공된 키(이니라이트키) (가맹점 수정후 고정) !!!절대!! 전문 데이터로 설정금지
				String timestamp= SignatureUtil.getTimestamp();				// util에 의해서 자동생성
				String charset 	= "UTF-8";								    // 리턴형식[UTF-8,EUC-KR](가맹점 수정후 고정)
				String format 	= "JSON";								    // 리턴형식[XML,JSON,NVP](가맹점 수정후 고정)
				String authToken= paramMap.get("authToken");			    // 취소 요청 tid에 따라서 유동적(가맹점 수정후 고정)
				String authUrl	= paramMap.get("authUrl");				    // 승인요청 API url(수신 받은 값으로 설정, 임의 세팅 금지)
				String netCancel= paramMap.get("netCancelUrl");			 	// 망취소 API url(수신 받은 값으로 설정, 임의 세팅 금지)
				String ackUrl 	= paramMap.get("checkAckUrl");			    // 가맹점 내부 로직 처리후 최종 확인 API URL(수신 받은 값으로 설정, 임의 세팅 금지)		
				String merchantData = paramMap.get("merchantData");			// 가맹점 관리데이터 수신
				
				//#####################
				// 2.signature 생성
				//#####################
				Map<String, String> signParam = new HashMap<String, String>();

				signParam.put("authToken",	authToken);		// 필수
				signParam.put("timestamp",	timestamp);		// 필수

				// signature 데이터 생성 (모듈에서 자동으로 signParam을 알파벳 순으로 정렬후 NVP 방식으로 나열해 hash)
				String signature = SignatureUtil.makeSignature(signParam);

	      		String price = "";  // 가맹점에서 최종 결제 가격 표기 (필수입력아님)
	      		
			    // 1. 가맹점에서 승인시 주문번호가 변경될 경우 (선택입력) 하위 연결.  
			    // String oid = "";             
	      
				//#####################
				// 3.API 요청 전문 생성
				//#####################
				Map<String, String> authMap = new Hashtable<String, String>();

				authMap.put("mid"			    ,mid);			  // 필수
				authMap.put("authToken"		,authToken);	// 필수
				authMap.put("signature"		,signature);	// 필수
				authMap.put("timestamp"		,timestamp);	// 필수
				authMap.put("charset"		  ,charset);		// default=UTF-8
				authMap.put("format"		  ,format);		  // default=XML
	      		//authMap.put("price" 		,price);		    // 가격위변조체크기능 (선택사용)
	      
				System.out.println("##승인요청 API 요청##");

				HttpUtil httpUtil = new HttpUtil();

				try{
					//#####################
					// 4.API 통신 시작
					//#####################

					String authResultString = "";
					authResultString = httpUtil.processHTTP(authMap, authUrl);
					
					//############################################################
					//5.API 통신결과 처리(***가맹점 개발수정***)
					//############################################################
					//out.println("## 승인 API 결과 ##");
					
					String test = authResultString.replace(",", "&").replace(":", "=").replace("\"", "").replace(" ","").replace("\n", "").replace("}", "").replace("{", "");
					
					Map<String, String> resultMap = new HashMap<String, String>();
					resultMap = ParseUtil.parseStringToMap(test); //문자열을 MAP형식으로 파싱
									
					log.info("[PC 결제 결과] resultMap == " + resultMap);
					
					/*************************  결제보안 강화 2016-05-18 START ****************************/ 
					Map<String , String> secureMap = new HashMap<String, String>();
					secureMap.put("mid"			, mid);								//mid
					secureMap.put("tstamp"		, timestamp);						//timestemp
					secureMap.put("MOID"		, resultMap.get("MOID"));			//MOID
					secureMap.put("TotPrice"	, resultMap.get("TotPrice"));		//TotPrice
					
					// signature 데이터 생성 
					String secureSignature = SignatureUtil.makeSignatureAuth(secureMap);
					/*************************  결제보안 강화 2016-05-18 END ****************************/

					if("0000".equals(resultMap.get("resultCode")) && secureSignature.equals(resultMap.get("authSignature")) ){	//결제보안 강화 2016-05-18
					   /*****************************************************************************
				       * 여기에 가맹점 내부 DB에 결제 결과를 반영하는 관련 프로그램 코드를 구현한다.  
					   
						 [중요!] 승인내용에 이상이 없음을 확인한 뒤 가맹점 DB에 해당건이 정상처리 되었음을 반영함
								  처리중 에러 발생시 망취소를 한다.
				       ******************************************************************************/	
						//성공 결과정보
						log.info("[PC 결제 성공] 결과코드 : " + resultMap.get("resultCode") + "결과내용 : " + resultMap.get("resultMsg"));
					} else {
						//실패 결과정보
						log.error("[PC 결제 실패] 결과코드 : " + resultMap.get("resultCode") + " 결과내용 : " + resultMap.get("resultMsg"));
						
						//결제보안키가 다른 경우
						if (!secureSignature.equals(resultMap.get("authSignature")) && "0000".equals(resultMap.get("resultCode"))) {
							//망취소
							if ("0000".equals(resultMap.get("resultCode"))) {//* 데이터 위변조 체크 실패
								throw new Exception("데이터 위변조 체크 실패");
							}
						}
						
						return enrollBasketResultRedirect("200", StringUtil.nvl(resultMap.get("resultMsg"), "결제 실패"));//망취소 없이 바로 결과 페이지로 이동
					}
						
					//공통 부분만
					paymentVO.setTid(resultMap.get("tid"));//거래번호
					paymentVO.setPayMethod(resultMap.get("payMethod"));//결제방법(지불수단)
					paymentVO.setTotPrice(resultMap.get("TotPrice"));//결제완료금액
					paymentVO.setMoid(resultMap.get("MOID"));//주문번호
					paymentVO.setApplDate(resultMap.get("applDate"));//승일날짜
					paymentVO.setApplTime(resultMap.get("applTime"));//승인시간
					
					//기존테이블
					//paymentVO.setPaymPrice(totEduPrice);//결제 가격
					paymentVO.setPaymOidNo(resultMap.get("MOID"));//결제 주문번호
					paymentVO.setPaymDttm(resultMap.get("applDate") + resultMap.get("applTime"));//결제 일시
					
					if("VBank".equals(resultMap.get("payMethod"))){ //가상계좌
						paymentVO.setPaymMthdCd("PAYM003");//가상계좌
						
						paymentVO.setVactNum(resultMap.get("VACT_Num"));//입금 계좌번호
						paymentVO.setVactBankCode(resultMap.get("VACT_BankCode"));//입금 은행코드
						paymentVO.setVactBankName(resultMap.get("vactBankName"));//입금 은행명 
						paymentVO.setVactName(resultMap.get("VACT_Name"));//예금주 명
						paymentVO.setVactInputName(resultMap.get("VACT_InputName"));//송금자 명
						paymentVO.setVactDate(resultMap.get("VACT_Date"));//송금 일자
						paymentVO.setVactTime(resultMap.get("VACT_Time"));//송금 시간
						
						//throw new Exception("망 취소 확인");//망취소 테스트
					}else if("DirectBank".equals(resultMap.get("payMethod")) || "iDirectBank".equals(resultMap.get("payMethod"))){ //실시간계좌이체
						paymentVO.setPaymMthdCd("PAYM002");//실시간 계좌이체
						
						paymentVO.setAcctBankCode(resultMap.get("ACCT_BankCode"));//은행코드
						paymentVO.setCshrResultCode(resultMap.get("CSHR_ResultCode"));//현금영수증 발급결과코드
						paymentVO.setCshrType(resultMap.get("CSHR_Type"));//현금영수증 발급구분코드(0 - 소득공제용, 1 - 지출증빙용)
						 
						//paymentVO.setPaymBankNm(codeMemService.getCode("BANK_CD", paymentVO.getAcctBankCode()).getCodeNm());
						paymentVO.setPaymBankNm(BankCode.findByCodeValue(paymentVO.getAcctBankCode()).getValue());
						//throw new Exception("망 취소 확인");//망취소 테스트
					}else if("Card".equals(resultMap.get("payMethod")) || "VCard".equals(resultMap.get("payMethod"))){//카드 결제 : 신용카드(안심클릭)	Card, 신용카드(ISP)	VCard
						int  quota=Integer.parseInt(resultMap.get("CARD_Quota"));
						
						if(resultMap.get("EventCode")!=null){				
							paymentVO.setEventCode(resultMap.get("EventCode"));//이벤트코드
						}
						if("1".equals(resultMap.get("CARD_Interest")) || "1".equals(resultMap.get("EventCode"))){					
							paymentVO.setInterestFreeYn("Y");//할부 유형 : 무이자
						}else if(quota > 0 && !"1".equals(resultMap.get("CARD_Interest"))){
							paymentVO.setInterestFreeYn("N");//할부 유형 : 유이자 (*유이자로 표시되더라도 EventCode 및 EDI에 따라 무이자 처리가 될 수 있습니다.)
						}
							
						paymentVO.setPaymMthdCd("PAYM001");//카드결제
						paymentVO.setCardQuota(resultMap.get("CARD_Quota"));//카드 할부기간
						paymentVO.setCardNum(resultMap.get("CARD_Num"));//카드번호
						paymentVO.setApplNum(resultMap.get("applNum"));//승인번호
						paymentVO.setCardInterest(resultMap.get("CARD_Interest"));//할부
						paymentVO.setCardCode(resultMap.get("CARD_Code"));//카드 종류
						paymentVO.setCardCropFlag(resultMap.get("CARD_CorpFlag"));//카드구분 ["0":개인카드, "1":법인카드, "9":구분불가] * 승인실패 시 빈값 전달
						paymentVO.setCardCheckFlag(resultMap.get("CARD_CheckFlag"));//카드종류 ["0":신용, "1":체크, "2":기프트]
						paymentVO.setCardPrtcCode(resultMap.get("CARD_PRTC_CODE"));//부분취소 가능여부 ["0":불가능, "1":가능]
						paymentVO.setCardBankcode(resultMap.get("CARD_BankCode"));//카드 발급사
						paymentVO.setCardSrcCode(resultMap.get("CARD_SrcCode"));//간편(앱)결제구분
						paymentVO.setCardPoint(resultMap.get("CARD_Point")); //카드포인트 사용여부 ["":카드 포인트 사용안함, "1":카드 포인트 사용]
						
						//paymentVO.setCardCodeNm(codeMemService.getCode("CARD_CD", paymentVO.getCardCode()).getCodeNm());//카드종류 코드로 카드명 세팅
						paymentVO.setCardCodeNm(CardCode.findByCodeValue(paymentVO.getCardCode()).getValue());//카드종류 코드로 카드명 세팅
						//throw new Exception("망 취소 확인");//망취소 테스트
				    }else {
				    	/*
				    	"HPP".equals(resultMap.get("payMethod")) //휴대폰
						|| "DGCL".equals(resultMap.get("payMethod")) //게임문화상품권
						|| "OCBPoint".equals(resultMap.get("payMethod")) //오케이 캐쉬백
						|| "GSPT".equals(resultMap.get("payMethod")) //U-포인트
						|| "UPNT".equals(resultMap.get("payMethod")) //GSPoint
						|| "KWPY".equals(resultMap.get("payMethod")) //뱅크월렛 카카오
						|| "Culture".equals(resultMap.get("payMethod")) //문화 상품권
						|| "TEEN".equals(resultMap.get("payMethod")) //틴캐시
						|| "Bookcash".equals(resultMap.get("payMethod")) //도서문화상품권
						|| "PhoneBill".equals(resultMap.get("payMethod")) //폰빌전화결제
						|| "Bill".equals(resultMap.get("payMethod")) //빌링결제
						|| "Auth".equals(resultMap.get("payMethod")) //빌링결제
						|| "HPMN".equals(resultMap.get("payMethod")) //해피머니
						*/
				    	throw new Exception("가상계좌, 실시간 계좌이체, 카드결제 외 다른 결제수단 선택으로 인한 오류");//현행 : 가상계좌, 실시간 계좌이체, 카드결제만 가능
				    }
					
					// 수신결과를 파싱후 resultCode가 "0000"이면 승인성공 이외 실패
					// 가맹점에서 스스로 파싱후 내부 DB 처리 후 화면에 결과 표시

					// payViewType을 popup으로 해서 결제를 하셨을 경우
					// 내부처리후 스크립트를 이용해 opener의 화면 전환처리를 하세요
					//throw new Exception("강제 Exception");
					
					//결제모듈 결과 성공이면 DB 저장 처리
					try {
						
						ProcessResultVO<PaymentVO> resultVO = paymentService.addPayment(paymentVO);
						if(resultVO.getResult() > 0) {
							//성공
							returnPaymNo = resultVO.getReturnVO().getPaymNo();
						}else {
							throw new Exception("결제 결과 저장 오류");//exception 발생시켜 망취소
						}
					} catch (Exception e) {
						throw new Exception("결제 결과 저장 오류");
					}
				} catch (Exception ex) {

					//####################################
					// 실패시 처리(***가맹점 개발수정***)
					//####################################

					//#####################
					// 망취소 API
					//#####################
					String netcancelResultString = httpUtil.processHTTP(authMap, netCancel);	// 망취소 요청 API url(고정, 임의 세팅 금지)

					//System.out.println("## 망취소 API 결과 ##");
					//System.out.println("[PC 결제 망취소  API 결과] ==" + netcancelResultString.replaceAll("<", "&lt;").replaceAll(">", "&gt;"));

					// 취소 결과 확인
					log.error("[PC 결제 망취소  API 결과] ==" + netcancelResultString.replaceAll("<", "&lt;").replaceAll(">", "&gt;"));
					
					return enrollBasketResultRedirect("200", ex.getMessage());
				}

			}else{
				//#############
				// 인증 실패시
				//#############
				//System.out.println("####인증실패####");
				//System.out.println("paramMap = " + paramMap.toString());
				log.error("[PC 결제 인증실패] paramMap = " + paramMap.toString());

				return enrollBasketResultRedirect("200", "인증 실패");
				
			}

		}catch(Exception e){
			System.out.println(e.getMessage());
			return enrollBasketResultRedirect("200", e.getMessage());
		}
		
		return "redirect:" + new URLBuilder("/home/student/enrollBasketResultMain").addParameter("errMsgCode", "0")
																					.addParameter("paymNo", StringUtil.nvl(returnPaymNo))
																					.toString();
	}
	
	/**
	 * [HRD] 교육과정/신청>수강신청결제 - 모바일 수강신청(장바구니 결제) 
	 * 							 - 주의 : PC와 모바일은 각각 인코딩, 파라미터가 다르다. 인코딩은 P_CHARSET 으로 해결가능   
	 */
	//@PostMapping(value="/enrollBasketRegistMobileMain")
	@RequestMapping(value="/enrollBasketRegistMobileMain")
	public String enrollBasketRegistMobileMain(StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String userNo = UserBroker.getUserNo(request);
		String orgCd = UserBroker.getUserOrgCd(request);

		PaymentVO paymentVO = new PaymentVO();
		paymentVO.setRegNo(userNo);
		paymentVO.setOrgCd(orgCd);
		paymentVO.setRegIp(request.getRemoteAddr());
		
		paymentVO.setUserNo(userNo);
		paymentVO.setDeviceType("MOBILE");
		
		//파라미터, DB 검증 / 기업코드, 수강생 리스트 등 세팅
		try {
			enrollVaildationAndSet(request, response, paymentVO, userNo, orgCd);
		} catch (Exception e) {
			log.error("수강신청 검증 에러");
			if("400".equals(e.getMessage()) || "401".equals(e.getMessage())) {//로그아웃, 기업정보 없는 경우 메인이동
				return "redirect:"+ new URLBuilder("/home/main/indexMain").toString();
			}else {
				return enrollBasketResultRedirect("100", StringUtil.nvl(e.getMessage(), "수강신청 검증 오류"));
			}
		}
		
		String returnPaymNo = "";
		//결제 모듈 호출
		try{

			//#############################
			// 인증결과 파라미터 일괄 수신
			//#############################
			//request.setCharacterEncoding("euc-kr");
			request.setCharacterEncoding("UTF-8");
			
			String P_STATUS =request.getParameter("P_STATUS");       // 인증 상태
			String P_RMESG1 = request.getParameter("P_RMESG1");      // 인증 결과 메시지
			String P_TID = request.getParameter("P_TID");                   // 인증 거래번호
			String P_REQ_URL = request.getParameter("P_REQ_URL");    // 결제요청 URL
			String P_NOTI = request.getParameter("P_NOTI");              // 기타주문정보

			String P_MID = "INIpayTest";    //모바일 결제는 request.getParameter("P_MID") 파라미터로 받지 않는다.
			String testPayYn				= Constants.INICIS_TEST_YN;
			
			if("Y".equals(testPayYn)){
			//if("127.0.0.1".equals(request.getRemoteAddr()) || "0:0:0:0:0:0:0:1".equals(request.getRemoteAddr())){
				P_MID = Constants.INICIS_TEST_MID;//테스트 가맹점 ID
			}else {
				P_MID = Constants.INICIS_SERVICE_MOBILE_MID;// 가맹점 ID(가맹점 수정후 고정)
			}
			
			
			//String MID = request.getParameter("P_MID");
			String P_OID = "";//응답값에서 세팅
			
			// 승인요청을 위한 P_MID, P_TID 세팅
			if(P_STATUS.equals("01")) {//인증결과가 실패일 경우
				//#############
				// 인증 실패시
				//#############
				System.out.println("####인증실패####");
				
				return enrollBasketResultRedirect("200", StringUtil.nvl(P_RMESG1, "결제 인증 실패"));
			} else {
				 // 승인요청할 데이터
			    P_REQ_URL = P_REQ_URL + "?P_TID=" + P_TID + "&P_MID=" + P_MID;
			    
			    HttpClient client = new HttpClient();
			    GetMethod method = new GetMethod(P_REQ_URL);
			    method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(3, false));
			    
			    
			    //망취소 URL 세팅
			    URI uri = new URI(P_REQ_URL);
			    String cancelUri = "https://" + uri.getHost() + "/smart/payNetCancel.ini";

			    
			    Map<String, String> resultMap = new HashMap<String, String>();
			    
			    try {
			    	int statusCode = client.executeMethod(method);

					if (statusCode != HttpStatus.SC_OK) {
					    log.error("Method failed: " + method.getStatusLine());
					    return enrollBasketResultRedirect("200", "결제 결과 수신 오류");
					}

					// -------------------- 승인결과 수신 -------------------------------------------------
					byte[] responseBody = method.getResponseBody(); 
					System.out.println("responseBody = " + new String(responseBody));
					
					/*String[] values = new String(responseBody).split("&"); 
					for( int x = 0; x < values.length; x++ ){
						System.out.println(values[x]);// 승인결과를 출력
					}*/
					
					resultMap = ParseUtil.parseStringToMap(new String(responseBody));

					System.out.println("resultMap = " + resultMap);
					
					P_OID = resultMap.get("P_OID");//결제요청 시 설정한 P_OID 값
					
					if("00".equals(resultMap.get("P_STATUS"))) {//결제 성공
						log.info("[결제 성공] 결과코드 : " + resultMap.get("P_STATUS") + "결과내용 : " + resultMap.get("P_RMESG1"));
						
					}else {//실패
						log.error("[결제 실패] 결과코드 : " + resultMap.get("P_STATUS") + " 결과내용 : " + resultMap.get("P_RMESG1"));
						//throw new Exception(resultMap.get("P_RMESG1"));
						return enrollBasketResultRedirect("200", StringUtil.nvl(resultMap.get("P_RMESG1"), "결제 실패"));
					}
					
					
					//결제 성공 후 오류 발생 시, exception 발생시켜 망취소 진행
					try {
						//공통 부분
						//***주의 : PC와 응답 파라미터가 다르다
						paymentVO.setTid(resultMap.get("P_TID"));//거래번호
						paymentVO.setPayMethod(resultMap.get("P_TYPE"));//결제방법(지불수단)
						paymentVO.setTotPrice(resultMap.get("P_AMT"));//결제완료금액
						paymentVO.setMoid(resultMap.get("P_OID"));//주문번호
						//paymentVO.setApplDate(resultMap.get("applDate"));//PC 승일날짜
						//paymentVO.setApplTime(resultMap.get("applTime"));//PC 승인시간
						
						//기존테이블
						//paymentVO.setPaymPrice(totEduPrice);//결제 가격
						paymentVO.setPaymOidNo(resultMap.get("P_OID"));//결제 주문번호
						paymentVO.setPaymDttm(resultMap.get("P_AUTH_DT"));///MOBILE 승인일자 [YYYYMMDDhhmmss]
						
						//장바구니의 가격과 승인결과 가격이 다른 경우 exception
						
						/**
						 * 모바일 결제수단(P_TYPE) : PC와 다르니 주의
						 * 신용카드(안심클릭,ISP)	CARD
						 * 실시간계좌이체	BANK
						 * 가상계좌(무통장입금)	VBANK
						 */
						if("VBANK".equals(resultMap.get("P_TYPE"))){ //가상계좌(무통장입금)
							paymentVO.setPaymMthdCd("PAYM003");//가상계좌
							
							paymentVO.setVactNum(resultMap.get("P_VACT_NUM"));//가상계좌번호
							paymentVO.setVactBankCode(resultMap.get("P_VACT_BANK_CODE"));//채번은행코드   
							paymentVO.setVactBankName(resultMap.get("P_FN_NM"));//채번은행 한글명
							paymentVO.setVactName(resultMap.get("P_VACT_NAME"));//예금주 명
							//paymentVO.setVactInputname(resultMap.get("VACT_InputName"));//송금자 명
							paymentVO.setVactDate(resultMap.get("P_VACT_DATE"));//입금마감일자 [YYYYMMDD]
							paymentVO.setVactTime(resultMap.get("P_VACT_TIME"));//입금마감시간 [hhmmss]
							
							//throw new Exception("가상계좌 결제 강제 취소 ");//현행 : 카드결제만 가능
						}else if("BANK".equals(resultMap.get("P_TYPE"))){ //실시간계좌이체
							paymentVO.setPaymMthdCd("PAYM002");//실시간 계좌이체
							
							paymentVO.setAcctBankCode(resultMap.get("P_FN_CD1"));//은행코드
							paymentVO.setPaymBankNm(resultMap.get("P_FN_NM")) ;//결제은행 한글명(기존 테이블)
							//paymentVO.setCshrResultCode(resultMap.get("CSHR_ResultCode"));//현금영수증 발급결과코드
							//paymentVO.setCshrType(resultMap.get("CSHR_Type"));//현금영수증 발급구분코드(0 - 소득공제용, 1 - 지출증빙용)
							 
							//throw new Exception("실시간계좌이체 결제 강제 취소");//현행 : 카드결제만 가능
						}else if("CARD".equals(resultMap.get("P_TYPE"))) {
							
							paymentVO.setPaymMthdCd("PAYM001");//카드결제
							
							paymentVO.setCardQuota(resultMap.get("P_RMESG2"));//카드 할부기간
							paymentVO.setCardNum(resultMap.get("P_CARD_NUM"));//카드번호
							paymentVO.setApplNum(resultMap.get("P_AUTH_NO"));//승인번호
							paymentVO.setCardInterest(resultMap.get("P_CARD_INTEREST"));//할부
							paymentVO.setCardCode(resultMap.get("P_FN_CD1"));//카드 종류
							paymentVO.setCardCropFlag(resultMap.get("CARD_CorpFlag"));//카드구분 ["0":개인카드, "1":법인카드, "9":구분불가] * 승인실패 시 빈값 전달
							paymentVO.setCardCheckFlag(resultMap.get("P_CARD_CHECKFLAG"));//카드종류 ["0":신용, "1":체크, "2":기프트]
							paymentVO.setCardPrtcCode(resultMap.get("P_CARD_PRTC_CODE"));//부분취소 가능여부 ["0":불가능, "1":가능]
							paymentVO.setCardBankcode(resultMap.get("P_CARD_ISSUER_CODE"));//카드 발급사
							paymentVO.setCardSrcCode(resultMap.get("P_SRC_CODE"));
							
							//paymentVO.setCardCodeNm(codeMemService.getCode("CARD_CD", paymentVO.getCardCode()).getCodeNm());//카드종류 코드로 카드명 세팅
							paymentVO.setCardCodeNm(CardCode.findByCodeValue(paymentVO.getCardCode()).getValue());//카드종류 코드로 카드명 세팅
						}else {
							throw new Exception("가상계좌, 실시간 계좌이체, 카드결제 외 다른 결제수단 선택으로 인한 오류");//다른 결제 수단은 불가
						}
						
						//DB 저장
						try {
							ProcessResultVO<PaymentVO> resultVO = paymentService.addPayment(paymentVO);
							
							if(resultVO.getResult() > 0) {
								//성공
								returnPaymNo = resultVO.getReturnVO().getPaymNo();
							}else {
								throw new Exception();//exception 발생시켜 망취소
							}
						} catch (Exception e) {
							throw new Exception("결제 결과 저장 오류");
						}
					} catch (Exception e) {
						//망 취소
						log.error("[모바일결제 후] exception 발생, 모바일 망 취소 시작  오류 메시지 : " + e.getMessage());
						
						// 승인취소 요청할 데이터
						cancelUri = cancelUri + "?P_TID=" + P_TID + "&P_MID=" + resultMap.get("P_MID") + "&P_AMT=" + resultMap.get("P_AMT") + "&P_OID=" + resultMap.get("P_OID");

						HttpClient cancelClient = new HttpClient();
						GetMethod cancelMethod = new GetMethod(cancelUri);
						cancelMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(3, false));
						
						int statusCode2 = cancelClient.executeMethod(cancelMethod);

						byte[] cancelBody = cancelMethod.getResponseBody();
						String[] cancelValues = new String(cancelBody).split("&");
						Map<String , String> cancelMap = new HashMap<String, String>();

						for (String value : cancelValues) {
							cancelMap.put(value.substring(0, value.indexOf("=")), value.substring(value.indexOf("=") + 1));
				    	}
						System.out.println("## 망취소 API 결과 ## : " + cancelMap.get("P_STATUS"));
						return enrollBasketResultRedirect("200", e.getMessage());
					}
					
			    } catch (HttpException e) {
			    	log.error("[모바일결제] Fatal protocol violation: " + e.getMessage());
			    	return enrollBasketResultRedirect("200", "결제 protocol 오류");
			    } catch (IOException e) {
			    	log.error("[모바일결제]Fatal transport error: " + e.getMessage());
			    	return enrollBasketResultRedirect("200", "결제 transport 오류");
			    } catch (Exception e) {
			    	log.error("[모바일결제] exception 발생" + e.getMessage());
			    	
			    	/*return "redirect:" + new URLBuilder("/home/student/enrollBasketResultMain")
							.addParameter("errMsgCode", "100")
							.toString();*/
			    	
			    	return enrollBasketResultRedirect("200", StringUtil.nvl(e.getMessage(), "결제 오류"));
			    } finally {
			    	method.releaseConnection();
			    }  

			}
			
		}catch(Exception e){
			System.out.println(e.getMessage());
			return enrollBasketResultRedirect("100", e.getMessage());
		}
		
		return "redirect:" + new URLBuilder("/home/student/enrollBasketResultMain").addParameter("errMsgCode", "0")
																					.addParameter("paymNo", StringUtil.nvl(returnPaymNo))
																					.toString();
	}
	
	/**
	 * [HRD] 수강신청 결과 화면
	 * @param vo
	 * @param commandMap
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/enrollBasketResultMain")
	public String enrollBasketResultMain(StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String userNo = UserBroker.getUserNo(request);
		String paymNo = StringUtil.nvl(vo.getPaymNo());
		
		//code 값을 전달받아 보여지는 결과 페이지
		
		if(!UserBroker.isLogin(request) || UserBroker.getUserType(request).equals("MEM_PROVIS")) {
			setAlertMessage(request, "로그인 후 이용바랍니다.");
			return "redirect:"+ new URLBuilder("/home/main/indexMain").toString();
		}
		
		//System.out.println(request.getParameter("errMsgCode"));
		//System.out.println(request.getParameter("errMsg"));
		
		
		//결제 결과 받아서 paym_cd 받아서, 가상계좌인 경우 보여줘야 할지??
		//결제 내역 페이지에서 보여주면 될지?
		if(!ValidationUtils.isEmpty(paymNo)) {
			PaymentVO paramPaymentVO = new PaymentVO();
			paramPaymentVO.setPaymNo(paymNo);
			paramPaymentVO.setUserNo(userNo);
			model.addAttribute("paymentVO", paymentService.viewUserPayByPaymNo(paramPaymentVO).getReturnVO());
		}
		
		
		model.addAttribute("errMsgCode", request.getParameter("errMsgCode"));
		model.addAttribute("errMsg", request.getParameter("errMsg"));
		
		return "home/student/student/enrl_result_main";
	}
	
	/**
	 * [HRD] PC, 모바일 결제 시 파라미터 및 DB 검증 및 값 세팅
	 * @param request
	 * @param response
	 * @param paymentVO
	 * @param userNo
	 * @param orgCd
	 * @return
	 * @throws Exception
	 */
	private String enrollVaildationAndSet(HttpServletRequest request, HttpServletResponse response, PaymentVO paymentVO, String userNo, String orgCd) throws Exception{
		//1. 비로그인 처리(임시회원 포함)
		if(!UserBroker.isLogin(request) || UserBroker.getUserType(request).equals("MEM_PROVIS")) {
			setAlertMessage(request, "로그인 후 이용바랍니다.");
			//return "redirect:"+ new URLBuilder("/home/main/indexMain").toString();
			throw new Exception("400");
		}
		
		// 인증 관련 처리 완료후 돌아올 페이지 URL을 설정
		request.setAttribute("goUrl", HttpRequestUtil.requestToUrlBuilder(request).toString());
		
		//회원정보 조회
		UsrUserInfoVO userInfoVO = new UsrUserInfoVO();
		userInfoVO.setUserNo(userNo);
		userInfoVO = userInfoService.view(userInfoVO);
		
		//신청 전, 로그인한 회원 기업코드 DB 확인
		/*
		 * String deptCd = StringUtil.nvl(userInfoVO.getDeptCd());
		 * if(ValidationUtils.isEmpty(deptCd)) { setAlertMessage(request,
		 * "기업 정보가 존재하지 않습니다. 회원정보에서 기업정보를 입력바랍니다."); //return "redirect:"+ new
		 * URLBuilder("/home/main/indexMain").toString(); throw new Exception("401"); }
		 */
		
		//기업코드(파라미터) 기업코드(회원) 비교
		//회원번호(파라미터) 회원번호(로그인) 비교
		
		//DB 장바구니 확인 - 수강신청 기간, 회원번호, 기업코드
		//paymentVO.setDeptCd(deptCd);//기업코드 세팅
		
		//수강신청 페이지의 신청하려는 장바구니(파라미터)와 현재 장바구니(DB) 비교 : 차이나면 취소
		String paramBskListStr = StringUtil.nvl(request.getParameter("paramBskListStr"));
				
		//String[] paramBskList = paramBskListStr.split("\\,");
		List<String> paramBskCreList =  Arrays.asList(paramBskListStr.split("\\,"));
		
		//장바구니에서 선택한 강의만 결제하기 위해 변수추가
		if(paramBskListStr.indexOf(",") != -1){ // 다중 선택이라면 배열로 판단
			paymentVO.setCrsCreCds(paramBskListStr.split("\\,"));
		} else { // 단건
			paymentVO.setCrsCreCd(paramBskListStr);
		}
		
		List<PaymentVO> userBskList = paymentService.listBasketForEnrollByUserNoDeptCd(paymentVO).getReturnList();
		
		//장바구니가 빈 경우
		if(userBskList.size() == 0) {
			throw new Exception("수강 바구니에 강의가 존재하지 않습니다.");
		}
		
		if("".equals(paramBskListStr)){
			throw new Exception("수강 바구니 오류");
		}
		
		if(userBskList.size() == 0) {
			throw new Exception("수강 바구니 오류");
		}
		
		if(paramBskCreList.size() != userBskList.size()) {
			throw new Exception("결제하려는 강의와 수강 바구니의 강의가 미 일치");
		}
		
		int paramTotEduPrice = StringUtil.nvl(request.getParameter("paramTotEduPrice"),0);
		
		//파라미터 결제 금액이 0 인경우
		if(paramTotEduPrice == 0) {
			throw new Exception("결제 금액 오류");
		}
		
		//파라미터 과정과 수강 바구니의 과정 일치 여부 확인
		List<String> userBskCreList = new ArrayList<>();
		for (PaymentVO bskPaymentVO : userBskList) {
			userBskCreList.add(bskPaymentVO.getCrsCreCd());
		}
		userBskCreList.removeAll(paramBskCreList);//중복 삭제
		if(userBskCreList.size() > 0) {
			throw new Exception("결제하려는 강의와 수강 바구니의 강의가 미 일치");
		}
		
		List<StudentVO> paramStudentList = new ArrayList<>();
		int totEduPrice = 0;
		for (PaymentVO bskPaymentVO : userBskList) {
			//수강생 insert를 위해 studentList 세팅
			StudentVO listParamVO = new StudentVO();
			
			listParamVO.setDeclsNo(1);//분반 1로 세팅
			listParamVO.setUserNo(userNo);
			listParamVO.setRegNo(userNo);
			listParamVO.setOrgCd(orgCd);
			
			//기수의 수강시작일, 종료일, 최종 종강일 세팅
			listParamVO.setCrsCreCd(bskPaymentVO.getCrsCreCd());
			listParamVO.setEnrlStartDttm(bskPaymentVO.getEnrlEndDttm());
			listParamVO.setEnrlEndDttm(bskPaymentVO.getEnrlEndDttm());
			listParamVO.setAuditEndDttm(bskPaymentVO.getTermEndDttm());
			
			listParamVO.setStdPrice(bskPaymentVO.getEduPrice());//가격 세팅
			
			//listParamVO.setEnrlSts("E");//결제상태에 따른 enrl_sts 세팅(우선 신청 E로 세팅)
			//listParamVO.setEnrlSts("S");//결제완료 시, S(수강) 으로 세팅 //가상계좌인 경우 신청 상태 E -> 결제수단 확인 후 세팅
			
			//정원 확인
			if("Y".equals(StringUtil.nvl(bskPaymentVO.getNopLimitYn(),"N"))) {
				if(bskPaymentVO.getStuCnt() >= bskPaymentVO.getEnrlNop()) {
					throw new Exception("강의의 정원이 초과");
				}
			}
			
			//이미 수강등록 되어있는 경우, 취소
			StudentVO paramStudentVO = new StudentVO();
			paramStudentVO.setUserNo(userNo);
			paramStudentVO.setCrsCreCd(bskPaymentVO.getCrsCreCd());
			
			StudentVO resultStudentVO = studentService.isEnroll(paramStudentVO).getReturnVO();
			if("Y".equals(StringUtil.nvl(resultStudentVO.getStdYn()))) {
				//이미 수강신청한 과정 장바구니에서 전부 삭제
				throw new Exception("이미 수강신청한 강의가 있습니다.");
			}
			
			totEduPrice += bskPaymentVO.getEduPrice();
			paramStudentList.add(listParamVO);
		}
		
		//파라미터 결제금액과 DB 결제금액의 합이 일치하지 않는 경우 : 취소
		if(totEduPrice != paramTotEduPrice) {
			throw new Exception("결제하려는 가격과 강의의 가격이 미 일치");
		}
		
		//파라미터 세팅
		paymentVO.setPaymPrice(totEduPrice);
		paymentVO.setStudentList(paramStudentList);
			
		return "";
	}
	
	
	/**
	 * [HRD] 수강신청 결과 페이지로 메시지 전달
	 * @param errMsgCode
	 * @param errMsg
	 * @return
	 */
	private String enrollBasketResultRedirect(String errMsgCode, String errMsg) {
		return "redirect:" + new URLBuilder("/home/student/enrollBasketResultMain")
				.addParameter("errMsgCode", errMsgCode)
				.addParameter("errMsg", errMsg)
				.toString();
	}
	
	/**
	 * [HRD] 이니시스 가상계좌 결과 수신(PC)
	 * @param vo
	 * @param commandMap
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	//@PostMapping(value="/indexEnrollPaymentVBank")//임시주석처리
	public String indexEnrollPaymentVBank(PaymentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//요청받은 IP
		String pgIp = request.getRemoteAddr();
		
		/*if($PG_IP == "203.238.37" || $PG_IP == "39.115.212" || $PG_IP == "183.109.71") //PG에서 보냈는지 IP로 체크
		{
		    // 이니시스 NOTI 서버에서 받은 Value
		    $no_tid = $_REQUEST["no_tid"];                      // 거래TID
		    $no_oid 		= $_REQUEST["no_oid"		];                    // 상점주문번호
		    $cd_bank 		= $_REQUEST["cd_bank"		];                // 은행코드
		    $cd_deal 		= $_REQUEST["cd_deal"		];                  // 거래취급 기관코드(실제입금은행)
		    $dt_trans 		= $_REQUEST["dt_trans"		];                 // 금융기관 발생 거래일자
		    $tm_trans 		= $_REQUEST["tm_trans"		];               // 금융기관 발생 거래시각
		    $no_vacct 		= $_REQUEST["no_vacct"		];               // 계좌번호
		    $amt_input 		= $_REQUEST["amt_input"		];           // 입금금액
		    $flg_close 		= $_REQUEST["flg_close"		];               // 마감구분[0:당일마감전, 1:당일마감후]
		    $cl_close 		= $_REQUEST["cl_close"		];                  // 마감구분코드[0:당일마감전, 1:당일마감후]
		    $type_msg 		= $_REQUEST["type_msg"		];             // 거래구분[0200:정상]
		    $nm_inputbank 	= $_REQUEST["nm_inputbank"	]; 		// 입금은행명
		    $nm_input 		= $_REQUEST["nm_input"		];             // 입금자명
		    $dt_inputstd 	= $_REQUEST["dt_inputstd"	];        // 입금기준일자
		    $dt_calculstd 	= $_REQUEST["dt_calculstd"	];       // 정산기준일자
		    $dt_transbase 	= $_REQUEST["dt_transbase"	];     // 거래기준일자
		    $cl_trans 		= $_REQUEST["cl_trans"		];                  // 거래구분코드
		    $cl_kor 		 $_REQUEST["cl_kor"			];                      // 한글구분코드
		    $dt_cshr 		= $_REQUEST["dt_cshr"		];                   // 현금영수증 발급일자
		    $tm_cshr 		= $_REQUEST["tm_cshr"		];                 // 현금영수증 발급시간
		    $no_cshr_appl 	= $_REQUEST["no_cshr_appl"	];   // 현금영수증 발급번호
		    $no_cshr_tid 	= $_REQUEST["no_cshr_tid"	];       // 현금영수증 발급TID
		    // if(데이터베이스 등록 성공 유무 조건변수 = true) 
		    // 주의 : DB처리후 정상일경우만 OK출력
		   echo "OK";
		   // else

		 //echo "FAIL";
		}*/
		
		if("203.238.37".equals(pgIp) || "39.115.212".equals(pgIp) || "183.109.71".equals(pgIp)) {//PG에서 보냈는지 IP로 체크
		//if("127.0.0.1".equals(pgIp) || "0:0:0:0:0:0:0:1".equals(pgIp) ) {//로컬 IP로 수신 확인
			String no_tid 			= request.getParameter("no_tid");                   // 거래번호 (입금거래에 대한 입금TID) * 가상계좌 채번TID 와 상이 : 결제 당시 TID와 상이
			String no_oid 		 	= request.getParameter("no_oid");                 	// 상점 주문번호 (가상계좌 채번요청 시 주문번호) : 결제 당시 주문번호
			String cd_bank 		 	= request.getParameter("cd_bank");               	// 가상계좌 발급 시 은행코드                             
			String cd_deal 		 	= request.getParameter("cd_deal");                 	// 고객 실입금 시 은행코드(실제입금은행)               
			String dt_trans 		= request.getParameter("dt_trans");                	// 금융기관 발생 거래일자                   
			String tm_trans 		= request.getParameter("tm_trans");                	// 금융기관 발생 거래시각                     
			String no_vacct 		= request.getParameter("no_vacct");                	// 계좌번호                             
			String amt_input 		= request.getParameter("amt_input");              	// 입금금액           : 건산원은 입금금액에 대해 UPDATE를 하고 있지 않음.           
			String flg_close 		= request.getParameter("flg_close");                // 마감구분[0:당일마감전, 1:당일마감후]         
			String cl_close 		= request.getParameter("cl_close");                 // 마감구분코드[0:당일마감전, 1:당일마감후]      
			String type_msg 		= request.getParameter("type_msg");              	// 거래구분[0200:정상]                      
			String nm_inputbank 	= request.getParameter("nm_inputbank");          	// 입금은행명                             
			String nm_input 		= request.getParameter("nm_input");              	// 입금자명                               
			String dt_inputstd 	 	= request.getParameter("dt_inputstd");              // 입금기준일자                            
			String dt_calculstd 	= request.getParameter("dt_calculstd");             // 정산기준일자                           
			String dt_transbase 	= request.getParameter("dt_transbase");             // 거래기준일자                             
			String cl_trans 		= request.getParameter("cl_trans");                 // 거래구분코드                        
			String cl_kor 		 	= request.getParameter("cl_kor");                   // 한글구분코드                        
			String dt_cshr 		 	= request.getParameter("dt_cshr");                  // 현금영수증 발급일자(현금영수증 발행요청 건의 한함)             
			String tm_cshr 		 	= request.getParameter("tm_cshr");                	// 현금영수증 발급시간(현금영수증 발행요청 건의 한함)                 
			String no_cshr_appl 	= request.getParameter("no_cshr_appl");            	// 현금영수증 발급번호(현금영수증 발행요청 건의 한함)                     
			String no_cshr_tid 	 	= request.getParameter("no_cshr_tid");              // 현금영수증 발급TID(현금영수증 발행요청 건의 한함)
			
			Map<String,String> paramMap = new Hashtable<String,String>();
			Enumeration elems = request.getParameterNames();

			String temp = "";
			while(elems.hasMoreElements())
			{
				temp = (String) elems.nextElement();
				paramMap.put(temp, request.getParameter(temp));
			}
			
			log.info("[PC 가상계좌 결과 수신] paramMap : "+ paramMap.toString());
			
			//hash 검증 추가 여부
			
			//가상계좌 입금 액이 조금 부족하거나 초과하는 경우도 수신이 되는지 의문
			
			if("0200".equals(type_msg)) {//거래구분이 정상인 경우
				
				//DB 처리
				//no_oid(주문번호) 로 결제 건 조회
				PaymentVO paramVO = new PaymentVO();
				paramVO.setPaymOidNo(no_oid);
				
				PaymentVO paymentResultVO = paymentService.selectByPaymOidNo(paramVO).getReturnVO();
				
				if(paymentResultVO != null) {//매뉴얼에서 DB처리후 정상일경우만 OK출력
					try {
						paymentResultVO.setInDttm(dt_inputstd);//입금 기준일자 세팅
						paymentResultVO.setInPrice(Integer.valueOf(amt_input));//입금금액 세팅
						paymentResultVO.setVactInputDttm(dt_inputstd);
						paymentResultVO.setVactInputPrice(amt_input);
						paymentService.editVBankResult(paymentResultVO);//결제, 수강생 데이터 수정
					} catch (Exception e) {
						log.error("[PC 가상계좌 결과 DB 저장 오류] 주문번호 = " + paymentResultVO.getPaymOidNo() + ", 입금일시 = " + paymentResultVO.getInDttm() + ", 금액 = " + paymentResultVO.getInPrice() + ", 결제번호 = " + paymentResultVO.getPaymNo());
						return "";
					}
					
					return "OK";
				}else {//결제 데이터 없음
					log.error("[PC 가상계좌 결과 수신] 주문번호의 결제 데이터 없음 no_oid : " + no_oid);
				}
			}else {//거래구분이 0200이 아닌 경우
				log.error("[PC 가상계좌 결과 수신 정상X] 거래구분 = " + type_msg);
			}
		}else {//지정된 IP가 아님
			log.error("[PC 가상계좌 결과 수신 IP 오류] IP = " + pgIp);
		}
		
		return "";
	}
	
	/**
	 * [HRD] 이니시스 가상계좌 결과 수신(모바일)
	 * @param vo
	 * @param commandMap
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	//@PostMapping(value="/indexEnrollPaymentVBankMobile")//임시주석처리
	public String indexEnrollPaymentVBankMobile(PaymentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//요청받은 IP
		String pgIp = request.getRemoteAddr();
		if("118.129.210.25".equals(pgIp) || "183.109.71.153".equals(pgIp) || "203.238.37.15".equals(pgIp)) {//PG에서 보냈는지 IP로 체크
		//if("127.0.0.1".equals(pgIp) || "0:0:0:0:0:0:0:1".equals(pgIp) ) {//로컬 IP로 수신 확인
			//작업 당시 샘플코드
			/*if($PG_IP == "118.129.210" || $PG_IP == "183.109.71" || $PG_IP == "203.238.37") //PG에서 보냈는지 IP로 체크
			{
			    // 이니시스 NOTI 서버에서 받은 Value
			    $P_STATUS 			= $_REQUEST["P_STATUS"			];                  // 거래상태 [00:가상계좌 채번, 02:가상계좌입금통보]
			    $P_TID 				= $_REQUEST["P_TID"				];                  // 거래TID
			    $P_TYPE 			= $_REQUEST["P_TYPE"			];                  // 지불수단[VBANK: 가상계좌]
			    $P_AUTH_DT 			= $_REQUEST["P_AUTH_DT"			];                  // 승인일자 [YYMMDDhhmmss]
			    $P_MID 				= $_REQUEST["P_MID"				];                  // 상점아이디
			    $P_OID 				= $_REQUEST["P_OID"				];                  // 상점주문번호
			    $P_FN_CD1 			= $_REQUEST["P_FN_CD1"			];                  // 은행코드
			    $P_FN_CD2 			= $_REQUEST["P_FN_CD2"			];                  // 금융사코드 (빈값으로 전달)
			    $P_FN_NM 			= $_REQUEST["P_FN_NM"			];                  // 은행명
			    $P_AMT 				= $_REQUEST["P_AMT"				];                  // 거래금액
			    $P_UNAME 			= $_REQUEST["P_UNAME"			];                  // 주문자명
			    $P_RMESG1 			= $_REQUEST["P_RMESG1"			];                  // 메시지1 [채번된 가상계좌번호|입금기한]
			    $P_RMESG2 			= $_REQUEST["P_RMESG2"			];                  // 메시지2 (빈값전달)
			    $P_NOTI 			= $_REQUEST["P_NOTI"			];                  // 주문정보 [거래요청시 입력한 P_NOTI 값을 그대로 반환합니다]
			    $P_AUTH_NO 			= $_REQUEST["P_AUTH_NO"			];                  // 승인번호 (빈값전달)
			    $P_CSHR_AMT 		= $_REQUEST["P_CSHR_AMT"		];                 	// 현금영수증 거래 금액
			    $P_CSHR_SUP_AMT 	= $_REQUEST["P_CSHR_SUP_AMT"	];    				// 현금영수증 공급가액
			    $P_CSHR_TAX 		= $_REQUEST["P_CSHR_TAX"		];                  // 현금영수증 부가가치세
			    $P_CSHR_SRVC_AMT 	= $_REQUEST["P_CSHR_SRVC_AMT"	]; 					// 현금영수증 봉사료
			    $P_CSHR_TYPE 		= $_REQUEST["P_CSHR_TYPE"		];                 	// 현금영수증 거래자 구분 [0:소비자소득공제용, 1:사업자지출증빙용]
			    $P_CSHR_DT 			= $_REQUEST["P_CSHR_DT"			];                  // 현금영수증 발행일자 [YYYYMMDDhhmmss]
			    $P_CSHR_AUTH_NO 	= $_REQUEST["P_CSHR_AUTH_NO"	];   				// 현금영수증 발행승인번호
			 
			    // if(데이터베이스 등록 성공 유무 조건변수 = true) 
			    // 주의 : P_STATUS=02 일경우 입금통보이며 입금통보 일경우 DB처리후 정상일경우만 OK출력
			   echo "OK";
			    // else
			// echo "FAIL";

			}
			*/
			String P_STATUS 			= request.getParameter("P_STATUS"			);       // 거래상태 [00:가상계좌 채번, 02:가상계좌입금통보]             
			String P_TID 				= request.getParameter("P_TID"				);       // 거래TID                                      
			String P_TYPE 				= request.getParameter("P_TYPE"			    );       // 지불수단[VBANK: 가상계좌]                          
			String P_AUTH_DT 			= request.getParameter("P_AUTH_DT"			);       // 승인일자 [YYMMDDhhmmss]                        
			String P_MID 				= request.getParameter("P_MID"				);       // 상점아이디                                      
			String P_OID 				= request.getParameter("P_OID"				);       // 상점주문번호                                     
			String P_FN_CD1 			= request.getParameter("P_FN_CD1"			);       // 은행코드                                       
			String P_FN_CD2 			= request.getParameter("P_FN_CD2"			);       // 금융사코드 (빈값으로 전달)                            
			String P_FN_NM 				= request.getParameter("P_FN_NM"			);       // 은행명                                        
			String P_AMT 				= request.getParameter("P_AMT"				);       // 거래금액                                       
			String P_UNAME 				= request.getParameter("P_UNAME"			);       // 주문자명                                       
			String P_RMESG1 			= request.getParameter("P_RMESG1"			);       // 메시지1 [채번된 가상계좌번호|입금기한]                     
			String P_RMESG2 			= request.getParameter("P_RMESG2"			);       // 메시지2 (빈값전달)                                
			String P_NOTI 				= request.getParameter("P_NOTI"			    );       // 주문정보 [거래요청시 입력한 P_NOTI 값을 그대로 반환합니다]       
			String P_AUTH_NO 			= request.getParameter("P_AUTH_NO"			);       // 승인번호 (빈값전달)                                
			String P_CSHR_AMT 			= request.getParameter("P_CSHR_AMT"		    );       // 현금영수증 거래 금액                                
			String P_CSHR_SUP_AMT 		= request.getParameter("P_CSHR_SUP_AMT"	    );       // 현금영수증 공급가액                                 
			String P_CSHR_TAX 			= request.getParameter("P_CSHR_TAX"		    );       // 현금영수증 부가가치세                                
			String P_CSHR_SRVC_AMT 		= request.getParameter("P_CSHR_SRVC_AMT"	);       // 현금영수증 봉사료                                  
			String P_CSHR_TYPE 			= request.getParameter("P_CSHR_TYPE"		);       // 현금영수증 거래자 구분 [0:소비자소득공제용, 1:사업자지출증빙용]      
			String P_CSHR_DT 			= request.getParameter("P_CSHR_DT"			);       // 현금영수증 발행일자 [YYYYMMDDhhmmss]                
			String P_CSHR_AUTH_NO 		= request.getParameter("P_CSHR_AUTH_NO"	    );       // 현금영수증 발행승인번호                               
			
			Map<String,String> paramMap = new Hashtable<String,String>();
			Enumeration elems = request.getParameterNames();

			String temp = "";
			while(elems.hasMoreElements())
			{
				temp = (String) elems.nextElement();
				paramMap.put(temp, request.getParameter(temp));
			}
			
			log.info("[MOBILE 가상계좌 결과 수신] paramMap : "+ paramMap.toString());
			
			if("02".equals(P_STATUS)) {//거래상태가 02(가상계좌입금통보)인 경우
				
				//DB 처리
				//no_oid(주문번호) 로 결제 건 조회
				PaymentVO paramVO = new PaymentVO();
				paramVO.setPaymOidNo(P_OID);
				PaymentVO paymentResultVO = paymentService.selectByPaymOidNo(paramVO).getReturnVO();
				
				if(paymentResultVO != null) {//매뉴얼에서 DB처리후 정상일경우만 OK출력
					try {
						paymentResultVO.setInDttm(P_AUTH_DT);//승인일자 세팅
						paymentResultVO.setInPrice(Integer.valueOf(P_AMT));//입금금액 세팅
						paymentResultVO.setVactInputDttm(P_AUTH_DT);
						paymentResultVO.setVactInputPrice(P_AMT);
						paymentService.editVBankResult(paymentResultVO);//결제, 수강생 데이터 수정
					} catch (Exception e) {
						log.error("[PC 가상계좌 결과 DB 저장 오류] 주문번호 = " + paymentResultVO.getPaymOidNo() + ", 입금일시 = " + paymentResultVO.getInDttm() + ", 금액 = " + paymentResultVO.getInPrice() + ", 결제번호 = " + paymentResultVO.getPaymNo());
						return "";
					}
					
					return "OK";
				}else {//결제 데이터 없음
					log.error("[PC 가상계좌 결과 수신] 주문번호의 결제 데이터 없음 P_OID : " + P_OID);
				}
			}else {//거래상태가 02(가상계좌입금통보)가 아닌 경우
				log.error("[PC 가상계좌 결과 수신 거래상태 입금통보X] 거래상태 = " + P_STATUS);
			}
		}else {//지정된 IP가 아님
			log.error("[MOBILE 가상계좌 결과 수신 IP 오류] IP = " + pgIp);
		}
		return "";
	}
	
	/**
	 * [HRD] 결제내역 메인
	 * @param vo
	 * @param commandMap
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/listPaymentMain")
	public String listPaymentMain(StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		if(!UserBroker.isLogin(request) || UserBroker.getUserType(request).equals("MEM_PROVIS")) {
			setAlertMessage(request, "로그인 후 이용바랍니다.");
			return "redirect:"+ new URLBuilder("/home/main/indexMain").toString();
		}
		String userNo = UserBroker.getUserNo(request);
		String orgCd = UserBroker.getUserOrgCd(request);
		CourseVO courseVO = new CourseVO();
		courseVO.setUserNo(userNo);
		
		//수강생이 수강신청한 기수 조회
		List<CourseVO> userCourseList = courseService.listStudentPaymentCourseByUserNo(courseVO);
		
		if(userCourseList == null && userCourseList.size() == 0) {
			setAlertMessage(request, "결제 내역이 없습니다.");
			return "redirect:"+ new URLBuilder("/home/main/indexMain").toString();
		}
		
		model.addAttribute("stuCrsList", userCourseList);
		
		
		
		//결제 내역 조회
		vo.setUserNo(userNo);
		
		model.addAttribute("stuPayList", studentService.listStudentPaymentInfoByUserNo(vo));
		
		return "home/student/student/list_student_payment_main";
	}
	
	/**
	 * [HRD] 결제내역 리스트 조회
	 * @param vo
	 * @param commandMap
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	/*@RequestMapping(value="/listPayment")
	public String listPayment(StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		if(!UserBroker.isLogin(request) || UserBroker.getUserType(request).equals("MEM_PROVIS")) {
			setAlertMessage(request, "로그인 후 이용바랍니다.");
			return "redirect:"+ new URLBuilder("/home/main/indexMain").toString();
		}
		String userNo = UserBroker.getUserNo(request);
		String orgCd = UserBroker.getUserOrgCd(request);
		
		//결제 내역 조회
		vo.setUserNo(userNo);
		
		model.addAttribute("stuPayList", studentService.listStudentPaymentInfoByUserNo(vo));
		
		model.addAttribute("nowDateTime", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
		
		return "home/student/student/list_student_payment_div";
	}*/
	
	/**
	 * [HRD] 무통장입금 결제 건 입금 전 수강 취소
	 * @param vo
	 * @param commandMap
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value="/removeStudent")
	public String removeStudent(StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		if(!UserBroker.isLogin(request) || UserBroker.getUserType(request).equals("MEM_PROVIS")) {
			setAlertMessage(request, "로그인 후 이용바랍니다.");
			return "redirect:"+ new URLBuilder("/home/main/indexMain").toString();
		}
		
		//로그인한 userNo와 stdNo가 일치 하는지
		vo.setUserNo(UserBroker.getUserNo(request));
		
		/*String stdNo = StringUtil.nvl(vo.getStdNo());
		String crsCreCd = StringUtil.nvl(vo.getCrsCreCd());
		if(ValidationUtils.isNull(stdNo) || ValidationUtils.isNull(crsCreCd)) {
			setAlertMessage(request, "값이 올바르지 않습니다.");
			return "redirect:/home/main/goMenuPage?mcd=MC00000025";
		}*/
		
		/*StudentVO resultVO = studentService.selectStudentEnrollBefore(vo).getReturnVO();
		if(resultVO != null) {
			if(!StringUtil.nvl(resultVO.getStdNo()).equals(vo.getStdNo())){
				setAlertMessage(request, "해당 과정은 수강 취소할 수 없습니다. 새로고침 후 다시 이용바랍니다.");
				return "redirect:"+ new URLBuilder("/home/main/indexMain").toString();
			}else {
				//결제 건 조회, 결제 취소할 수 있는 상태 인지? : 결제 상태가 입금대기, 수강상태가 대기
				if("DI".equals(resultVO.getPaymStsCd()) && "E".equals(resultVO.getEnrlSts())) {
					//취소 진행
					try {//결제 상태는 변경하지 않는다.(취소는 결제모듈 미연동)
						studentService.cancelStudent(resultVO, request);
						setAlertMessage(request, "수강 취소하였습니다.");
						return "redirect:/home/main/goMenuPage?mcd=MC00000025";
					} catch (Exception e) {
						setAlertMessage(request, "수강 취소 중 오류가 발생하였습니다. 새로고침 후 다시 이용바랍니다.");
						return "redirect:"+ new URLBuilder("/home/main/indexMain").toString();
					}
				}else {
					setAlertMessage(request, "새로고침 후 다시 이용바랍니다.");
					return "redirect:"+ new URLBuilder("/home/main/indexMain").toString();
				}
			}
		}else {
			setAlertMessage(request, "새로고침 후 다시 이용바랍니다.");
			return "redirect:"+ new URLBuilder("/home/main/indexMain").toString();
		}*/
		try {
			studentService.cancelStudentEnrlStsE(vo);
		} catch (MediopiaDefineException e1) {
			setAlertMessage(request, StringUtil.nvl(e1.getMessage(), "수강 취소 중 오류가 발생하였습니다. 새로고침 후 다시 이용바랍니다."));
		} catch (Exception e) {
			setAlertMessage(request, "수강 취소 중 오류가 발생하였습니다. 새로고침 후 다시 이용바랍니다.");
		} 
		
		return "redirect:/home/main/goMenuPage?mcd=MC00000025";
	}
	
	//이니시스 전체 취소, 부분 취소 전 수강생 조회 및 확인
	private StudentVO beforeInicisRefund(StudentVO vo, String userNo) {
		//StudentVO studentVO = studentService.viewStudentPaymentInfoManage(vo).getReturnVO();
		StudentVO studentVO = studentService.viewStudentPaymentInfoByStdNo(vo);
		if(studentVO == null) {
			throw new ServiceProcessException("수강생 조회 오류");
		}
		
		String enrlSts = StringUtil.nvl(studentVO.getEnrlSts());
		String tid = StringUtil.nvl(studentVO.getTid());
		//String paymethod = StringUtil.nvl(studentVO.getPayMethod());
		
		if(!userNo.equals(studentVO.getUserNo())) {//로그인 회원 확인
			throw new ServiceProcessException("로그인한 회원과 결제 취소하려는 수강생이 일치하지 않습니다. 새로고침 후 다시 시도바랍니다.");
		}
		if("".equals(tid)) {
			throw new ServiceProcessException("결제 내역이 없습니다. 실제 결제를 진행하신 경우, 담당자에게 문의바랍니다.");
		}
		//환불요청중이거나, 환불완료 건은 추가 환불요청 불가
		if("REFUND001".equals(studentVO.getRepayStsCd())) {
			throw new ServiceProcessException("환불 신청 중입니다.");
		}
		if("REFUND003".equals(studentVO.getRepayStsCd()) ) {
			throw new ServiceProcessException("이미 환불 완료된 건입니다.");
		}
		//if("N".equals(enrlSts) || "D".equals(enrlSts)  || "E".equals(enrlSts) || "C".equals(enrlSts)) {//삭제, 취소, 대기, 수료 불가
		if(!("S".equals(studentVO.getEnrlSts()) || "F".equals(studentVO.getEnrlSts()))) {
			throw new ServiceProcessException("수강 중인 과정만 결제 취소가 가능합니다.");
		}
		return studentVO;
	}
	
	/**
	 * 이니시스 연동 전체 취소
	 * @param vo
	 * @param commandMap
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value="/inicisRefundMain")
	public String inicisRefundMain(StudentVO vo, Map commandMap, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String userNo = UserBroker.getUserNo(request);
		try {
			StudentVO studentVO = beforeInicisRefund(vo, userNo);
			int stdPrice = StringUtil.nvl(studentVO.getStdPrice(),0);//결제 세부 건의 금액(std)
			
			Map<String, Object> returnVO = paymentService.inicisRefund(InicisUtil.setRefundParameter(request, studentVO)).getReturnVO();
			if(returnVO == null) {
				throw new ServiceProcessException("환불 처리하지 못하였습니다.\n결제 금액은 환불 되고, 홈페이지에서 수강 취소가 되지 않는 경우 담당자에게 연락바랍니다.");
			}
			PaymentInicisCancelResultDTO cancelResultDTO = new ObjectMapper().convertValue(returnVO, PaymentInicisCancelResultDTO.class);
			if(cancelResultDTO == null) {
				log.error("[수강생 이니시스 부분환불 오류] 수강생번호 : " + studentVO.getStdNo() + ", 메시지 : 이니시스 환불결과 변환 오류");
				throw new ServiceProcessException("환불 처리하지 못하였습니다.\n결제 금액은 환불 되고, 홈페이지에서 수강 취소가 되지 않는 경우 담당자에게 연락바랍니다.");
			}
			
			String resultCode = StringUtil.nvl(cancelResultDTO.getResultCode());
			if("00".equals(resultCode)) {//성공 후 DB 처리
				StudentVO paramStudentVO = new StudentVO();
				paramStudentVO.setStdNo(studentVO.getStdNo());
				paramStudentVO.setRepayPrice(stdPrice);
				paramStudentVO.setRepayMemo("[수강취소] 수강생 직접 취소, 이니시스 결제 전체 환불 진행");
				paramStudentVO.setModNo(userNo);
				paramStudentVO.setEnrlSts("N");
				if(studentService.cancelStudentPaymentByStudent(paramStudentVO) > 0) {
					setAlertMessage(request, "환불 처리하였습니다.");
				}else {
					log.error("[수강생 이니시스 전체 환불 오류] 수강생번호 : " + studentVO.getStdNo() + ", 메시지 : 이니시스 환불 후 DB 처리 실패");
					throw new ServiceProcessException("환불 처리하지 못하였습니다.\n결제 금액은 환불 되고, 홈페이지에서 수강 취소가 되지 않는 경우 담당자에게 연락바랍니다.");
				}
			}else {
				log.error("[수강생 이니시스 전체 환불 오류] resultCode : " + resultCode  + ", stdNo : " + studentVO.getStdNo());
				throw new ServiceProcessException("[결제 취소 오류]\n 메시지 : " + StringUtil.nvl(cancelResultDTO.getResultMsg()));
			}
		} catch(MediopiaDefineException e) {
			setAlertMessage(request, e.getMessage());
		} catch (Exception e) {
			setAlertMessage(request, "결제 취소 오류");
		}
		
		return "redirect:/home/main/goMenuPage?mcd=MC00000025";
	}
	
	/**
	 * 이니시스 연동 부분 취소
	 * @param vo
	 * @param commandMap
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value="/inicisParRefundMain")
	public String inicisParRefundMain(StudentVO vo, Map commandMap, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String userNo = UserBroker.getUserNo(request);
		try {
			StudentVO studentVO = beforeInicisRefund(vo, userNo);
			int stdPrice = StringUtil.nvl(studentVO.getStdPrice(),0);//결제 세부 건의 금액(std)
			int refundPay = StringUtil.nvl(studentVO.getRepayPrice(),0);//현재까지 환불 금액
			int paymPrice = StringUtil.nvl(studentVO.getPaymPrice(),0);//결제 건의 총 금액(pay_info)
			int stdRepayPriceSum = StringUtil.nvl(studentVO.getStdRepayPriceSum(), 0);//결제 건의 환불금액 합(환불 처리 총 금액)
			int lmsConfirmPrice = paymPrice - stdPrice - stdRepayPriceSum;//처리 후 남은 금액 = 총 금액 - 현재 환불 처리할 금액 - 기존 환불 처리 총 금액
			
			PaymentInicisParCancelResultDTO cancelResultDTO =  null;
			
			//if(stdPrice < refundPay) {
//				throw new ServiceProcessException("환불 처리하지 못하였습니다. 금액 초과");
			//}
			if(stdPrice == paymPrice) {//전체결제 해야됨. 부분결제 불가
				
			}
			
			if( ("Card".equals(studentVO.getPayMethod()) || "VCard".equals(studentVO.getPayMethod()) || "CARD".equals(studentVO.getPayMethod()) &&  "0".equals(studentVO.getCardPrtcCode())) ) {
				throw new ServiceProcessException("이니시스 부분 취소 환불이 불가능합니다.");
			}
			
			//이니시스 결제 조회 API 이용(부분취소 여부)
			vo.setRepayPrice(stdPrice);//수강생이 직접 취소할 시, 결제 금액 전체 취소
			Map<String, Object> returnVO = paymentService.inicisRefund(InicisUtil.setPartialRefundParameter(request, studentVO, vo)).getReturnVO();
			if(returnVO == null) {
				throw new ServiceProcessException("환불 처리하지 못하였습니다.\n결제 금액은 환불 되고, 홈페이지에서 수강 취소가 되지 않는 경우 담당자에게 연락바랍니다.");
			}
			cancelResultDTO = new ObjectMapper().convertValue(returnVO, PaymentInicisParCancelResultDTO.class);
			if(cancelResultDTO == null) {
				log.error("[수강생 이니시스 전체환불 오류] 수강생번호 : " + studentVO.getStdNo() + ", 메시지 : 이니시스 환불결과 변환 오류");
				throw new ServiceProcessException("환불 처리하지 못하였습니다.\n결제 금액은 환불 되고, 홈페이지에서 수강 취소가 되지 않는 경우 담당자에게 연락바랍니다.");
			}
			
			String resultCode = StringUtil.nvl(cancelResultDTO.getResultCode());
			if("00".equals(resultCode)) {//성공 후 DB 처리
				StudentVO paramStudentVO = new StudentVO();
				paramStudentVO.setStdNo(studentVO.getStdNo());
				paramStudentVO.setRepayPrice(stdPrice);
				paramStudentVO.setRepayMemo("[수강취소] 수강생 직접 취소, 이니시스 결제 부분 환불 진행");
				paramStudentVO.setModNo(userNo);
				paramStudentVO.setEnrlSts("N");
				if(studentService.cancelStudentPaymentByStudent(paramStudentVO) > 0) {
					setAlertMessage(request, "환불 처리하였습니다.");
				}else {
					throw new ServiceProcessException("환불 처리하지 못하였습니다.\n결제 금액은 환불 되고, 홈페이지에서 수강 취소가 되지 않는 경우 담당자에게 연락바랍니다.");
				}
			}else {
				log.error("[수강생 이니시스 전체환불 오류] resultCode : " + resultCode  + ", stdNo : " + studentVO.getStdNo());
				throw new ServiceProcessException("[결제 취소 오류]\n 메시지 : " + StringUtil.nvl(cancelResultDTO.getResultMsg()));
			}
		} catch(MediopiaDefineException e) {
			setAlertMessage(request, e.getMessage());
		} catch (Exception e) {
			setAlertMessage(request, "결제 취소 오류");
		}
		
		//return "redirect:/home/student/listPaymentMain";
		return "redirect:/home/main/goMenuPage?mcd=MC00000025";
	}
	
	//환불신청 페이지 접근, 환불신청 전 체크 
	private StudentVO beforeLmsRefund(StudentVO vo, String userNo) {
		StudentVO studentVO = studentService.viewStudentPaymentInfoByStdNo(vo);
		if(studentVO == null) {
			throw new ServiceProcessException("수강생이 조회되지 않습니다.");
		}
		if(!userNo.equals(studentVO.getUserNo())) {//로그인한 회원 확인
			throw new ServiceProcessException("로그인한 회원과 환불신청하려는 수강생이 일치하지 않습니다. 새로고침 후 다시 시도바랍니다.");
		}
		//환불요청중이거나, 환불완료 건은 추가 환불요청 불가
		if("REFUND001".equals(studentVO.getRepayStsCd())) {
			throw new ServiceProcessException("환불 신청 중입니다.");
		}
		if("REFUND003".equals(studentVO.getRepayStsCd()) ) {
			throw new ServiceProcessException("환불 완료된 건입니다.");
		}
		//호출 jsp와 조건 동일하게 맞추기
		if(!("S".equals(studentVO.getEnrlSts()) || "F".equals(studentVO.getEnrlSts()))) {//수강중인과정만 환불신청 가능, F도 가능?
			throw new ServiceProcessException("수강 중인 과정만 환불신청 가능합니다.");
		}
		if(studentVO.getBookmarkCnt() == 0) {//수강전 환불가능?, 이니시스 결제와 연결해서 확인 필요
			
		}
		return studentVO;
	}
	
	/**
	 * 환불 신청 페이지 (모달)
	 * @param vo
	 * @param commandMap
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addRefundPop2")
	public String addRefundPop2 (StudentVO vo, Map commandMap, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String userNo = UserBroker.getUserNo(request);
		try {
			StudentVO studentVO = beforeLmsRefund(vo, userNo);
			model.addAttribute("studentVO", studentVO);			
		} catch (MediopiaDefineException e) {
			model.addAttribute("MESSAGE", e.getMessage());
			model.addAttribute("modalCloseYn", "Y");
			model.addAttribute("parentReloadYn", "Y");
			return "common/message";
		}
		return "home/student/student/add_refund_pop";
	}
	
	/**
	 * 환불 신청
	 * @param requestRefundAdd
	 * @param bindingResult
	 * @param vo
	 * @param commandMap
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/addRefund")
	public String addRefund (@Valid RefundAdd requestRefundAdd, BindingResult bindingResult, StudentVO vo, Map commandMap, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ProcessResultVO<StudentVO> resultVO = new ProcessResultVO<StudentVO>();
		if(bindingResult.hasErrors()){ //validation 에러가 있으면,
			resultVO.setResultFailed();
			String errorMessage = "";
			for(FieldError error : bindingResult.getFieldErrors()) {
				errorMessage = error.getDefaultMessage();
			}
			resultVO.setMessage(errorMessage);
			return JsonUtil.responseJson(response, resultVO);
		}
		
		commonVOProcessing(vo, request);
		String userNo = UserBroker.getUserNo(request);
		try {
			StudentVO studentVO = beforeLmsRefund(vo, userNo);
			vo.setEnrlSts("N");//수강불가 처리 필요로 인해 환불신청은 수강 취소 처리 
			vo.setRepayStsCd("REFUND001");//환불요청
			vo.setStdPrice(studentVO.getStdPrice());
			if(studentService.updateAddRefund(vo) > 0) {
				resultVO.setResultSuccess();
				resultVO.setMessage("환불신청하였습니다.");
			}else {
				resultVO.setResultFailed();
				resultVO.setMessage("환불신청 실패하였습니다.");
			}
		} catch (MediopiaDefineException e) {
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		} catch (Exception e) {
			resultVO.setResultFailed();
			resultVO.setMessage("환불신청 실패하였습니다.");
		}
		
		return JsonUtil.responseJson(response, resultVO);
	}
	
	/**
	 * 종료과정 성적조회
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/viewEndCrsScoreMain")
	public String viewEndCrsScoreMain(EduResultVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception{
		commonVOProcessing(vo, request);

		return "home/student/result/result_view_end_score";
	}
	
	/**
	 * 종료과정 성적조회
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/listEndScore")
	public String listEndCrsScoreMain(EduResultVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception{

		String crsCreCd = UserBroker.getCreCrsCd(request);
		String stdNo = UserBroker.getStudentNo(request);
		String userNo = UserBroker.getUserNo(request);
		
		//-- 개설 과정의 정보를 가져온다.
		CreateCourseVO createCourseVO = new CreateCourseVO();
		createCourseVO.setCrsCreCd(crsCreCd);
		createCourseVO = createCourseService.viewCreateCourse(createCourseVO).getReturnVO();
		request.setAttribute("createCourseVO", createCourseVO);

		//학습자 정보조회
		StudentVO studentVO = new StudentVO();
		studentVO.setStdNo(stdNo);
		studentVO = studentService.viewStudentInfo(studentVO).getReturnVO();
		request.setAttribute("studentVO", studentVO);
		
		//-- 시험 관련 정보 가져오기.
		List<EgovMap> stdScoreList = new ArrayList<>();
		StdScoreVO ssVO = new StdScoreVO();
		ssVO.setCrsCreCd(crsCreCd);
		ssVO.setUserNo(userNo);
		ProcessResultListVO<EgovMap> resultList = stdScoreService.listEndCreStdPageing(ssVO);
		if(resultList.getResult() > 0 ) {
			stdScoreList = resultList.getReturnList();
		}
		request.setAttribute("stdScoreList", stdScoreList);
		
		//-- 나머지 결과 확인
		vo.setCrsCreCd(crsCreCd);
		vo.setStdNo(stdNo);
		
		request.setAttribute("pageInfo", resultList.getPageInfo());
		request.setAttribute("vo", vo);
	
		return "home/student/result/result_end_div";
	}
	
}