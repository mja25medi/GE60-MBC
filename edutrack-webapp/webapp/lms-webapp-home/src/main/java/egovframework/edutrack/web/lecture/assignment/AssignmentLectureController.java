package egovframework.edutrack.web.lecture.assignment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.exception.MediopiaDefineException;
import egovframework.edutrack.comm.exception.ServiceProcessException;
import egovframework.edutrack.comm.service.CommStatusVO;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.service.SysCodeMemService;
import egovframework.edutrack.comm.util.web.DateTimeUtil;
import egovframework.edutrack.comm.util.web.HtmlCleaner;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.URLBuilder;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.course.assignmentbank.service.AssignmentQbankCategoryService;
import egovframework.edutrack.modules.course.assignmentbank.service.AssignmentQbankCategoryVO;
import egovframework.edutrack.modules.course.assignmentbank.service.AssignmentQbankQuestionVO;
import egovframework.edutrack.modules.course.assignmentbank.service.AssignmentQbankService;
import egovframework.edutrack.modules.course.createcoursesubject.service.CreateCourseSubjectService;
import egovframework.edutrack.modules.course.createcoursesubject.service.CreateOnlineSubjectVO;
import egovframework.edutrack.modules.course.decls.service.CreCrsDeclsService;
import egovframework.edutrack.modules.course.decls.service.CreCrsDeclsVO;
import egovframework.edutrack.modules.lecture.assignment.service.AssignmentSendVO;
import egovframework.edutrack.modules.lecture.assignment.service.AssignmentService;
import egovframework.edutrack.modules.lecture.assignment.service.AssignmentStatusVO;
import egovframework.edutrack.modules.lecture.assignment.service.AssignmentSubConstVO;
import egovframework.edutrack.modules.lecture.assignment.service.AssignmentSubVO;
import egovframework.edutrack.modules.lecture.assignment.service.AssignmentVO;
import egovframework.edutrack.modules.lecture.exam.service.ExamVO;
import egovframework.edutrack.modules.lecture.score.service.ScoreDataDto;
import egovframework.edutrack.modules.lecture.score.service.StdScoreService;
import egovframework.edutrack.modules.lecture.score.service.StdScoreVO;
import egovframework.edutrack.modules.log.prnlog.service.LogPrnLogService;
import egovframework.edutrack.modules.log.prnlog.service.LogPrnLogVO;
import egovframework.edutrack.modules.student.student.service.StudentService;
import egovframework.edutrack.modules.student.student.service.StudentVO;
import egovframework.edutrack.modules.system.code.service.SysCodeVO;


/**
 * 과제 컨트롤
 * @author JNOTE
 *
 */
@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/lec/assignment")
public class AssignmentLectureController extends GenericController {

	@Autowired @Qualifier("assignmentService")
	private AssignmentService			assignmentService;

	@Autowired @Qualifier("createCourseSubjectService")
	private CreateCourseSubjectService	createCourseSubjectService;

	@Autowired @Qualifier("studentService")
	private StudentService				studentService;

	@Autowired @Qualifier("sysCodeMemService")
	private SysCodeMemService				codeMemService;

	@Autowired @Qualifier("creCrsDeclsService")
	private CreCrsDeclsService 		creCrsDeclsService;

	@Autowired @Qualifier("assignmentQbankService")
	private AssignmentQbankService		assignmentQbankService;

	@Autowired @Qualifier("assignmentQbankCategoryService")
	private AssignmentQbankCategoryService		assignmentQbankCategoryService;
	
	
	@Autowired @Qualifier("logPrnLogService")
	private LogPrnLogService			logPrnLogService;

	@Autowired @Qualifier("stdScoreService")
	private StdScoreService			stdScoreService;
	
	private final static String GPT_URL = Constants.framework.getString("framework.gpt.call.url");
	private final static String GPT_VERSION = Constants.framework.getString("framework.gpt.version");
	private final static String GPT_KEY = Constants.framework.getString("framework.gpt.api.key");

	/**
	 * 과제 관리 메인
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/tchAssignmentMain")
	public String tchAssignmentMain(AssignmentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String crsCreCd = UserBroker.getCreCrsCd(request);
		vo.setCrsCreCd(crsCreCd);

		request.setAttribute("assignmentListVO",assignmentService.listAssignment(vo).getReturnList());

		request.setAttribute("vo", vo);

		return "home/lecture/assignment/teacher/asmt_main";
	}

	/**
	 * 과제 목록 학습자용 (자신의 제출 횟수와 여부 등을 같이 가져온다.)
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/stdAssignmentMain")
	public String stdAssignmentMain(AssignmentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String crsCreCd = UserBroker.getCreCrsCd(request);
		String stdNo = UserBroker.getStudentNo(request);

		vo.setCrsCreCd(crsCreCd);
		vo.setStdNo(stdNo);

		ProcessResultListVO<AssignmentVO> resultList = assignmentService.listAssignmentStd(vo);
		request.setAttribute("assignmentListVO", resultList.getReturnList());
	
		request.setAttribute("vo", vo);
		return "home/lecture/assignment/asmt_main";
	}

	/**
	 * 과제제출 글 조회
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/readAssignment")
	public String readAssignment(AssignmentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultVO<AssignmentVO> resultVO = assignmentService.viewAssignment(vo);
		vo = resultVO.getReturnVO();

		request.setAttribute("assignmentVO", vo);

		return "assignment_read";
	}

	/**
	 * 과제 등록 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addAssignmentMain")
	public String addAssignmentMain(AssignmentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {

		commonVOProcessing(vo, request);

		String crsCreCd = vo.getCrsCreCd();
		
		//과제 유형
		List<SysCodeVO> asmtTypeList = codeMemService.getSysCodeList("ASMT_TYPE_CD");
		request.setAttribute("asmtTypeList", asmtTypeList);

		//과제 출제 유형
		List<SysCodeVO> asmtSelectTypeList = codeMemService.getSysCodeList("ASMT_SELECT_TYPE_CD");
		request.setAttribute("asmtSelectTypeList", asmtSelectTypeList);

		//등록여부
		List<SysCodeVO> regTypeList = codeMemService.getSysCodeList("REG_YN");
		request.setAttribute("regTypeList", regTypeList);
		
		//서비스유형
		List<SysCodeVO> asmtServiceCdList = codeMemService.getSysCodeList("ASMT_SVC_CD", UserBroker.getLocaleKey(request));
		request.setAttribute("asmtServiceCdList", asmtServiceCdList);
		
	   CreateOnlineSubjectVO  createOnlineSubjectVO = new CreateOnlineSubjectVO();
		createOnlineSubjectVO.setCrsCreCd(crsCreCd);
		List<CreateOnlineSubjectVO> subjectList = createCourseSubjectService.listOnlineSubject(createOnlineSubjectVO).getReturnList();
		request.setAttribute("subjectList", subjectList);
	
		request.setAttribute("gubun", "A");
		request.setAttribute("vo", vo);
		request.setAttribute("fileupload", "Y");
		return "home/lecture/assignment/teacher/asmt_write_main";
	}
	
	/**
	 * 과제 등록 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addAssignmentPop")
	public String addAssignmentPop(AssignmentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {

		commonVOProcessing(vo, request);

		//과제 유형
		List<SysCodeVO> asmtTypeList = codeMemService.getSysCodeList("ASMT_TYPE_CD");
		request.setAttribute("asmtTypeList", asmtTypeList);

		//과제 출제 유형
		List<SysCodeVO> asmtSelectTypeList = codeMemService.getSysCodeList("ASMT_SELECT_TYPE_CD");
		request.setAttribute("asmtSelectTypeList", asmtSelectTypeList);

		//등록여부
		List<SysCodeVO> regTypeList = codeMemService.getSysCodeList("REG_YN");
		request.setAttribute("regTypeList", regTypeList);

		request.setAttribute("gubun", "A");
		request.setAttribute("vo", vo);
		request.setAttribute("fileupload", "Y");
		return "home/lecture/assignment/teacher/asmt_write_pop";
	}

	/**
	 * 과제 등록
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/addAssignment")
	public String addAssignment(AssignmentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception{
		commonVOProcessing(vo, request);

		// 스크립트, 스타일 태그 제거
		vo.setAsmtCts(HtmlCleaner.cleanScript(vo.getAsmtCts()) );
		try {
			vo.setAsmtSvcCd(vo.getAsmtTypeCd().equals("OFF") ? "GEN" : vo.getAsmtSvcCd());
			vo.setAsmtSelectTypeCd(vo.getAsmtSvcCd().equals("CODE") ? "S" : vo.getAsmtSelectTypeCd());
			ProcessResultVO<AssignmentVO> resultVO = assignmentService.addAssignment(vo);
			vo.setAsmtSn(resultVO.getReturnVO().getAsmtSn());
			setAlertMessage(request, getMessage(request, "lecture.message.assignment.add.success"));
			
			return "redirect:"+ new URLBuilder("/lec/assignment/editAssignmentManageMain")
					.addParameter("crsCreCd", vo.getCrsCreCd())
					.addParameter("asmtSn", vo.getAsmtSn())
					.toString();

		}catch (Exception e) {
			e.printStackTrace();
			setAlertMessage(request, getMessage(request, "lecture.message.assignment.add.failed"));
			
			return "redirect:" +  new URLBuilder("/lec/assignment/tchAssignmentMain")
					.toString();
		}

		
		/*
		 * ProcessResultVO<AssignmentVO> resultVO = assignmentService.addAssignment(vo);
		 * if(resultVO.getResult() > 0) { resultVO.setMessage(getMessage(request,
		 * "lecture.message.assignment.add.success")); } else {
		 * resultVO.setMessage(getMessage(request,
		 * "lecture.message.assignment.add.failed")); }
		 * 
		 * return JsonUtil.responseJson(response, resultVO);
		 */
	}

	/**
	 * 과제 등록 완료
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/addRegistAssignment")
	public String addRegistAssignment(AssignmentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception{
		commonVOProcessing(vo, request);

		try {
			assignmentService.addRegistAssignment(vo);
			setAlertMessage(request, getMessage(request, "lecture.message.assignment.regist.success"));
		}catch (Exception e) {
			e.printStackTrace();
			setAlertMessage(request, getMessage(request, "lecture.message.assignment.regist.failed"));
		}

		return "redirect:"+ new URLBuilder("/lec/assignment/editAssignmentManageMain")
				.addParameter("crsCreCd", vo.getCrsCreCd())
				.addParameter("asmtSn", vo.getAsmtSn())
				.toString();
	}
	
	/**
	 * 과제 등록 취소
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/editRegistAssignment")
	public String editRegistAssignment(AssignmentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception{
		commonVOProcessing(vo, request);

		try {
			assignmentService.addRegistAssignment(vo);
			setAlertMessage(request, getMessage(request, "lecture.message.assignment.cancelregist.success"));
		}catch (Exception e) {
			e.printStackTrace();
			setAlertMessage(request, getMessage(request, "lecture.message.assignment.cancelregist.failed"));
		}

		return "redirect:"+ new URLBuilder("/lec/assignment/editAssignmentManageMain")
				.addParameter("crsCreCd", vo.getCrsCreCd())
				.addParameter("asmtSn", vo.getAsmtSn())
				.toString();
	}

	
	/**
	 * 과제 수정 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/editAssignmentMain")
	public String editAsmtPop( AssignmentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		//과제 유형
		List<SysCodeVO> asmtTypeList = codeMemService.getSysCodeList("ASMT_TYPE_CD");
		request.setAttribute("asmtTypeList", asmtTypeList);

		//과제 출제 유형
		List<SysCodeVO> asmtSelectTypeList = codeMemService.getSysCodeList("ASMT_SELECT_TYPE_CD");
		request.setAttribute("asmtSelectTypeList", asmtSelectTypeList);

		//등록여부
		List<SysCodeVO> regTypeList = codeMemService.getSysCodeList("REG_YN");
		request.setAttribute("regTypeList", regTypeList);
		
		CreateOnlineSubjectVO  createOnlineSubjectVO = new CreateOnlineSubjectVO();
		createOnlineSubjectVO.setCrsCreCd(vo.getCrsCreCd());
		List<CreateOnlineSubjectVO> subjectList = createCourseSubjectService.listOnlineSubject(createOnlineSubjectVO).getReturnList();
		request.setAttribute("subjectList", subjectList);
		
		request.setAttribute("vo", assignmentService.viewAssignment(vo).getReturnVO());
		request.setAttribute("gubun", "E");
		request.setAttribute("fileupload", "Y");
		return "home/lecture/assignment/teacher/asmt_write_main";
	}
	
	
	
	/**
	 * 과제 수정 폼 메인
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/editAssignmentManageMain")
	public String editAssignmentManageMain(AssignmentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception{
		commonVOProcessing(vo, request);

		//-- assignment험의 정보를 가져온다.
		vo = assignmentService.viewAssignment(vo).getReturnVO();
		//request.setAttribute("assignmentVO", vo);

		if("Y".equals(vo.getRegYn()) || "OFF".equals(vo.getAsmtTypeCd())) {
			//-- 시험이 등록된 경우 평가 관리로 연결.
			return "redirect:"+ new URLBuilder("/lec/assignment/editFormAssignmentRateMain")
						.addParameter("crsCreCd", vo.getCrsCreCd())
						.addParameter("asmtSn", vo.getAsmtSn())
						.toString();
		} else {
			//-- 시험이 등록 되지 않은경우 문제 관리로 연결
			return "redirect:"+ new URLBuilder("/lec/assignment/editFormAssignmentQstnMain")
						.addParameter("crsCreCd", vo.getCrsCreCd())
						.addParameter("asmtSn", vo.getAsmtSn())
						.toString();
		}
	}

	/**
	 * 과제 문제 관리 메인(과제 정보 수정 폼)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/editFormAssignmentQstnMain")
	public String editFormAssignmentQstnMain(AssignmentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception{
		commonVOProcessing(vo, request);

	/*	String tab = request.getParameter("tab");
		request.setAttribute("tab", tab);
		*/
		//-- assignment험의 정보를 가져온다.		
		vo = assignmentService.viewAssignment(vo).getReturnVO();
		//request.setAttribute("vo", assignmentService.viewAssignment(vo).getReturnVO());
		
		//과제 유형
		List<SysCodeVO> regYnList = codeMemService.getSysCodeList("REG_YN");
		request.setAttribute("regYnList", regYnList);

		request.setAttribute("assignmentSubListVO", assignmentService.listSub(vo).getReturnList());
		request.setAttribute("gubun", "E");
		request.setAttribute("vo", vo);
		request.setAttribute("paging", "Y");
		request.setAttribute("fileupload", "Y");

		return "home/lecture/assignment/teacher/asmt_manage_qstn";
	}

	/**
	 * 과제 평가 관리 메인(과제 정보 수정 폼)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/editFormAssignmentRateMain")
	public String editFormAssignmentRateMain(AssignmentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception{
		commonVOProcessing(vo, request);


		//Integer declsNo = vo.getDeclsNo();
		String  userNm = vo.getUserNm();

		//-- assignment험의 정보를 가져온다.
		vo = assignmentService.viewAssignment(vo).getReturnVO();
		//vo.setDeclsNo(declsNo);
		vo.setUserNm(userNm);

		//--- 분반 목록
		CreCrsDeclsVO creCrsDeclsVO = new CreCrsDeclsVO();
		creCrsDeclsVO.setCrsCreCd(vo.getCrsCreCd());
		List<CreCrsDeclsVO> creCrsDeclsList = creCrsDeclsService.list(creCrsDeclsVO).getReturnList();
		request.setAttribute("creCrsDeclsList", creCrsDeclsList);

		//과제 유형
		List<SysCodeVO> regYnList = codeMemService.getSysCodeList("REG_YN");
		request.setAttribute("regYnList", regYnList);

		vo.setPageScale(10);
		//-- 과제 응시생 목록
		ProcessResultListVO<AssignmentSendVO> resultList = assignmentService.listSendPageing(vo);

		request.setAttribute("assignmentSendListVO", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());
		request.setAttribute("gubun", "E");
		request.setAttribute("vo", vo);
		request.setAttribute("paging", "Y");
		request.setAttribute("fileupload", "Y");
		return "home/lecture/assignment/teacher/asmt_manage_rate";
	}

	/**
	 * 과제 평가 통계 메인(과제 정보 수정 폼)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/editFormAssignmentRsltMain")
	public String editFormAssignmentRsltMain(AssignmentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception{
		commonVOProcessing(vo, request);

		//-- assignment험의 정보를 가져온다.
		vo = assignmentService.viewAssignment(vo).getReturnVO();
		request.setAttribute("vo", vo);
		
		//--- 분반 목록
		CreCrsDeclsVO creCrsDeclsVO = new CreCrsDeclsVO();
		creCrsDeclsVO.setCrsCreCd(vo.getCrsCreCd());
		List<CreCrsDeclsVO> creCrsDeclsList = creCrsDeclsService.list(creCrsDeclsVO).getReturnList();
		request.setAttribute("creCrsDeclsList", creCrsDeclsList);

		//과제 유형
		List<SysCodeVO> regYnList = codeMemService.getSysCodeList("REG_YN");
		request.setAttribute("regYnList", regYnList);

		//-- 과제 응시생 목록
		List<AssignmentSendVO> resultList = assignmentService.listSend(vo).getReturnList();
		List<CommStatusVO> statusList = new ArrayList<CommStatusVO>();
		for(int i = 0; i <= 100; i++ ) {
			CommStatusVO csVO = new CommStatusVO();
			csVO.setKeyCode(i+"");
			csVO.setKeyValue(0+"");
			statusList.add(csVO);
		}
		for(AssignmentSendVO asVO : resultList) {
			for(CommStatusVO csVO : statusList) {
				if(Math.round(asVO.getScore()) == Integer.parseInt(csVO.getKeyCode())) {
					int keyValue = Integer.parseInt(csVO.getKeyValue()) + 1;
					csVO.setKeyValue(keyValue+"");
				}
			}

		}
		request.setAttribute("statusList", statusList);
		request.setAttribute("gubun", "E");
		request.setAttribute("paging", "Y");
		request.setAttribute("fileupload", "Y");
		return "home/lecture/assignment/teacher/asmt_manage_rslt";
	}

	/**
	 * 과제 수정
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/editAssignment")
	public String editAssignment(AssignmentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception{
		commonVOProcessing(vo, request);

		// 스크립트, 스타일 태그 제거
		vo.setAsmtCts(HtmlCleaner.cleanScript(vo.getAsmtCts()) );
		vo.setAsmtSvcCd(vo.getAsmtTypeCd().equals("OFF") ? "GEN" : vo.getAsmtSvcCd());
		vo.setAsmtSelectTypeCd(vo.getAsmtSvcCd().equals("CODE") ? "S" : vo.getAsmtSelectTypeCd());
		
		ProcessResultVO<AssignmentVO> resultVO  = assignmentService.editAssignment(vo);
		if(resultVO.getResult() > 0) {
			setAlertMessage(request, getMessage(request, "lecture.message.assignment.edit.success"));
		} else {
			setAlertMessage(request, getMessage(request, "lecture.message.assignment.edit.failed"));
		}
		return "redirect:" +  new URLBuilder("/lec/assignment/tchAssignmentMain")
				.toString();
	}
	

	/**
	 * 과제 삭제
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/deleteAssignment")
	public String deleteAssignment(AssignmentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception{
		commonVOProcessing(vo, request);

		ProcessResultVO<AssignmentVO> resultVO  = assignmentService.deleteAssignment(vo);
		if(resultVO.getResult() > 0) {
			setAlertMessage(request, getMessage(request, "lecture.message.assignment.delete.success"));
		} else {
			setAlertMessage(request, getMessage(request, "lecture.message.assignment.delete.failed"));
		}
		return "redirect:" +  new URLBuilder("/lec/assignment/tchAssignmentMain")
				.toString();
		
		
	}

	/**
	 * 과재 제출 홈 (학습자용) : 첫 입장 시, send정보를 insert 한다.
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value= {"/addSendMain", "/sendResultPop"})
	public String addSendMain(AssignmentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String crsCreCd = UserBroker.getCreCrsCd(request);
		String stdNo = UserBroker.getStudentNo(request);
		String userNo = UserBroker.getUserNo(request);
		
		vo.setCrsCreCd(crsCreCd);
		vo.setStdNo(stdNo);

		ProcessResultVO<AssignmentVO> resultVO = assignmentService.viewAssignment(vo);
		vo = resultVO.getReturnVO();
		vo.setAsmtCts(StringUtil.getHtmlContents(vo.getAsmtCts()));
		
		if (vo.getAsmtStareTypeCd().equals("S")) {
			vo.setSendCanYn("Y");
			vo.setRsltYn("Y");
		}
		
		request.setAttribute("vo", vo);

		//과제 제출 정보
		AssignmentSendVO assignmentSendVO = new AssignmentSendVO();
		assignmentSendVO.setCrsCreCd(crsCreCd);
		assignmentSendVO.setStdNo(stdNo);
		assignmentSendVO.setAsmtSn(vo.getAsmtSn());

		//과제 문제 정보
		if("Y".equals(vo.getRegYn()) && "ON".equals(vo.getAsmtTypeCd())){
			//과제 문제 정보
			try {
				AssignmentSubVO subVO = new AssignmentSubVO();
				AssignmentSendVO sendVO = assignmentService.viewSend(assignmentSendVO).getReturnVO();
				AssignmentSubVO resultVO4 = null;
				
				if(sendVO == null) {//첫 과제 열람
					AssignmentSendVO  tempAssignmentSendVO= new AssignmentSendVO();	//과제 제출 임시로 저장할 VO
					
					if("R".equals(vo.getAsmtSelectTypeCd())){//랜덤형 :  조회 후 문제 세팅
						resultVO4 = assignmentService.viewOnlySubRandomRead(vo).getReturnVO();
						tempAssignmentSendVO.setAsmtSubSn(resultVO4.getAsmtSubSn());
					}else if("S".equals(vo.getAsmtSelectTypeCd())) {//선택형 : 조회
						ProcessResultListVO<AssignmentSubVO> resultVO3 = assignmentService.listSub(vo);
						request.setAttribute("assignmentSubListVO", resultVO3.getReturnList());
					}
					
					tempAssignmentSendVO.setCrsCreCd(crsCreCd);
					tempAssignmentSendVO.setStdNo(stdNo);
					tempAssignmentSendVO.setAsmtSn(vo.getAsmtSn());
					tempAssignmentSendVO.setSendCnt(0);
					tempAssignmentSendVO.setRegNo(userNo);
					tempAssignmentSendVO.setRegIp(request.getRemoteAddr());
					assignmentService.addEnterSend(tempAssignmentSendVO, "Y");//insert Y
				}else {//기존 과제 열람을 한 경우
					if("R".equals(vo.getAsmtSelectTypeCd())){
						subVO.setCrsCreCd(vo.getCrsCreCd());
						subVO.setAsmtSn(vo.getAsmtSn());
						subVO.setAsmtSubSn(sendVO.getAsmtSubSn());
						resultVO4 = assignmentService.viewSub(subVO).getReturnVO();
					}else if("S".equals(vo.getAsmtSelectTypeCd())) {
						if(sendVO.getSendCnt()>0) {
							 subVO.setCrsCreCd(vo.getCrsCreCd());
							 subVO.setAsmtSn(vo.getAsmtSn());
							 subVO.setAsmtSubSn(sendVO.getAsmtSubSn());
							 subVO = assignmentService.viewSub(subVO).getReturnVO();
							 request.setAttribute("subVO", subVO);
						 }else {
							ProcessResultListVO<AssignmentSubVO> resultVO3 = assignmentService.listSub(vo);
							request.setAttribute("assignmentSubListVO", resultVO3.getReturnList());
						 }
					}
					
					AssignmentSendVO  tempAssignmentSendVO= new AssignmentSendVO();	//과제 제출 임시로 저장할 VO (로그 쌓기용 세팅)
					tempAssignmentSendVO.setCrsCreCd(crsCreCd);
					tempAssignmentSendVO.setStdNo(stdNo);
					tempAssignmentSendVO.setAsmtSn(vo.getAsmtSn());
					tempAssignmentSendVO.setSendCnt(sendVO.getSendCnt());//기존 제출 횟수로 세팅
					tempAssignmentSendVO.setRegNo(userNo);
					tempAssignmentSendVO.setRegIp(request.getRemoteAddr());
					
					if(!"Y".equals(StringUtil.nvl(sendVO.getRateYn()))){//평가 완료 시, 시작 로그 저장X
						assignmentService.addEnterSend(tempAssignmentSendVO, "");//insert X, rateYn = Y인 경우에 타면 안 됨.
					}
				}
				request.setAttribute("resultVO4", resultVO4);
				
				//과제 문제 랜덤 출제
				/*if("R".equals(vo.getAsmtSelectTypeCd())){
					//vo.setStdNo(stdNo);
					//AssignmentSubVO resultVO4 = assignmentService.randomReadSub(vo).getReturnVO();//기존 로직 : 수강생 조회, 문제 조회, insert를 하나의 서비스 -> 로그 쌓기를 위해 분리
					
					if(sendVO == null) {
						resultVO4 = assignmentService.viewOnlySubRandomRead(vo).getReturnVO();
						
						AssignmentSendVO  tempAssignmentSendVO= new AssignmentSendVO();	//과제 제출 임시로 저장할 VO
						tempAssignmentSendVO.setCrsCreCd(crsCreCd);
						tempAssignmentSendVO.setStdNo(stdNo);
						tempAssignmentSendVO.setAsmtSn(resultVO4.getAsmtSn());
						tempAssignmentSendVO.setAsmtSubSn(resultVO4.getAsmtSubSn());
						tempAssignmentSendVO.setSendCnt(0);
						tempAssignmentSendVO.setRegNo(userNo);
						tempAssignmentSendVO.setRegIp(request.getRemoteAddr());
						assignmentService.addEnterSend(tempAssignmentSendVO);
					}else {
						subVO.setCrsCreCd(vo.getCrsCreCd());
						subVO.setAsmtSn(vo.getAsmtSn());
						subVO.setAsmtSubSn(sendVO.getAsmtSubSn());
						resultVO4 = assignmentService.viewSub(subVO).getReturnVO();
					}
					
					request.setAttribute("resultVO4", resultVO4);
				}
				//과제 문제 선택 출제
				if("S".equals(vo.getAsmtSelectTypeCd())){
					
					if(sendVO == null) {
						ProcessResultListVO<AssignmentSubVO> resultVO3 = assignmentService.listSub(vo);
						request.setAttribute("assignmentSubListVO", resultVO3.getReturnList());
						
						AssignmentSendVO  tempAssignmentSendVO= new AssignmentSendVO();	//과제 제출 임시로 저장할 VO
						tempAssignmentSendVO.setCrsCreCd(crsCreCd);
						tempAssignmentSendVO.setStdNo(stdNo);
						tempAssignmentSendVO.setAsmtSn(vo.getAsmtSn());
						tempAssignmentSendVO.setSendCnt(0);
						tempAssignmentSendVO.setRegNo(userNo);
						tempAssignmentSendVO.setRegIp(request.getRemoteAddr());
						assignmentService.addEnterSend(tempAssignmentSendVO);
					}else {
						if(sendVO.getSendCnt()>0) {
							 subVO.setCrsCreCd(vo.getCrsCreCd());
							 subVO.setAsmtSn(vo.getAsmtSn());
							 subVO.setAsmtSubSn(sendVO.getAsmtSubSn());
							 subVO = assignmentService.viewSub(subVO).getReturnVO();
							 request.setAttribute("subVO", subVO);
						 }else {
							ProcessResultListVO<AssignmentSubVO> resultVO3 = assignmentService.listSub(vo);
							request.setAttribute("assignmentSubListVO", resultVO3.getReturnList());
						 }
					}
				}*/
				
			} catch (Exception e) {
				request.setAttribute("resultVO4", null);
			}
		}
		boolean isSend 	= true;
		try {
			//-- 학습자 답안 조회
			ProcessResultVO<AssignmentSendVO> resultVO2 = assignmentService.viewSend(assignmentSendVO);
			assignmentSendVO = resultVO2.getReturnVO();
			request.setAttribute("assignmentSendVO", assignmentSendVO);
		} catch (EmptyResultDataAccessException e) {
			request.setAttribute("isSend", isSend);
			return "home/lecture/assignment/asmt_send_main";
		}

		//2015.11.25 김현욱 추가 과제제출건수를 비교
		if(assignmentSendVO != null ) {
			int asmtLimitCnt	= vo.getAsmtLimitCnt();
			int sendCnt			= assignmentSendVO.getSendCnt();
			if(asmtLimitCnt <= sendCnt){
				isSend = false;
			}
		}
		
		request.setAttribute("isSend", isSend);
		request.setAttribute("fileupload", "Y");
		request.setAttribute("textareaResize", "none");
		
		String requestUri = request.getRequestURI();
		if(requestUri.contains("Pop")) return "home/lecture/assignment/asmt_send_info_pop";

		return "home/lecture/assignment/asmt_send_main";
	}

	/**
	 * 과제 제출 (학습자) - (미사용) 과제 입장 시, insert
	 *
	 * @return  ProcessResultVO
	 */
	/*@RequestMapping(value="/addSend")
	public String addSend(AssignmentSendVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception{
		commonVOProcessing(vo, request);

		String crsCreCd = UserBroker.getCreCrsCd(request);
		vo.setStdNo(UserBroker.getStudentNo(request));

		// 스크립트, 스타일 태그 제거
		vo.setSendCts(HtmlCleaner.cleanScript(vo.getSendCts()) );

		ProcessResultVO<AssignmentSendVO> resultVO = assignmentService.addSend(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "lecture.message.assignment.send.success"));
		} else {
			resultVO.setMessage(getMessage(request, "lecture.message.assignment.send.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}*/


	/**
	 * 과제 수정 제출(학습자)
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/editSend")
	public String editSend(AssignmentSendVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception{
		commonVOProcessing(vo, request);

		vo.setStdNo(UserBroker.getStudentNo(request));

		// 스크립트, 스타일 태그 제거
		vo.setSendCts(HtmlCleaner.cleanScript(vo.getSendCts()));
		vo.setRegIp(request.getRemoteAddr());
		
		ProcessResultVO<AssignmentSendVO> resultVO = assignmentService.editSend(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "lecture.message.assignment.send.success"));
		} else {
			resultVO.setMessage(getMessage(request, "lecture.message.assignment.send.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}


	/**
	 * 과제 후보 목록 조회
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/listSub")
	public String listSub(AssignmentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		return JsonUtil
				.responseJson(response, assignmentService.listSub(vo));
	}

	/**
	 * 과제 후보 단일 조회
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/readSubPop")
	public String readSub(AssignmentSubVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception{
		commonVOProcessing(vo, request);
		String crsCreCd = UserBroker.getCreCrsCd(request);

		vo.setCrsCreCd(crsCreCd);

		vo = assignmentService.viewSub(vo).getReturnVO();
		request.setAttribute("vo", vo);


		return "home/lecture/assignment/sub_read_pop";
	}

	/**
	 * 과제 후보 등록 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addFormSubPop")
	public String addFormSubPop(AssignmentSubVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		List<SysCodeVO> asmtDevLangCdList = codeMemService.getSysCodeList("DEV_LANG_CD", UserBroker.getLocaleKey(request));
        List<SysCodeVO> asmtDiffLvlCdList = codeMemService.getSysCodeList("DIFF_LVL_CD", UserBroker.getLocaleKey(request));
		String returnUrl = this.getEditorType();
		 AssignmentVO aVO= new AssignmentVO();
        aVO.setCrsCreCd(vo.getCrsCreCd());
        aVO.setAsmtSn(vo.getAsmtSn());
        request.setAttribute("aVO", assignmentService.viewAssignment(aVO).getReturnVO());
		request.setAttribute("asmtDevLangCdList", asmtDevLangCdList);
		request.setAttribute("asmtDiffLvlCdList", asmtDiffLvlCdList);
		request.setAttribute("summernote", "Y");
		request.setAttribute("fileupload", "Y");
		request.setAttribute("gubun", "A");
		request.setAttribute("vo", vo);

		return returnUrl;
	}

	/**
	 * 과제 후보 등록
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/addSub")
	public String addSub(AssignmentSubVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		commonVOProcessing(vo, request);

		// 스크립트, 스타일 태그 제거
		vo.setAsmtCts(HtmlCleaner.cleanScript(vo.getAsmtCts()) );

		ProcessResultVO<AssignmentSubVO> resultVO = assignmentService.addSub(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "lecture.message.assignment.question.add.success"));
		} else {
			resultVO.setMessage(getMessage(request, "lecture.message.assignment.question.add.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 과제 후보 수정 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/editFormSubPop")
	public String editFormSubPop(AssignmentSubVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		//-- 과제 후보 정보를 찾아 Form에 셋팅
		vo = assignmentService.viewSub(vo).getReturnVO();
		
		AssignmentVO aVO= new AssignmentVO();
        aVO.setCrsCreCd(vo.getCrsCreCd());
        aVO.setAsmtSn(vo.getAsmtSn());
	    request.setAttribute("aVO", assignmentService.viewAssignment(aVO).getReturnVO());
		
		AssignmentSubConstVO cVO = new AssignmentSubConstVO();
		cVO.setCrsCreCd(vo.getCrsCreCd());
        cVO.setAsmtSn(vo.getAsmtSn());
        cVO.setAsmtSubSn(vo.getAsmtSubSn());
		ProcessResultListVO<AssignmentSubConstVO> constList = assignmentService.listSubConst(cVO);
		request.setAttribute("assignmentSubVO", vo);

		String returnUrl = this.getEditorType();
		request.setAttribute("gubun", "E");
		request.setAttribute("summernote", "Y");
		request.setAttribute("fileupload", "Y");
		request.setAttribute("vo", vo);
		request.setAttribute("constList", constList.getReturnList());

		return returnUrl;
	}

	/**
	 * 과제 후보 수정
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/editSub")
	public String editSub(AssignmentSubVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception{
		commonVOProcessing(vo, request);

		// 스크립트, 스타일 태그 제거
		vo.setAsmtCts(HtmlCleaner.cleanScript(vo.getAsmtCts()) );

		ProcessResultVO<AssignmentSubVO> resultVO = assignmentService.editSub(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "lecture.message.assignment.question.edit.success"));
		} else {
			resultVO.setMessage(getMessage(request, "lecture.message.assignment.question.edit.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 과제 후보 삭제
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/deleteSub")
	public String deleteSub(AssignmentSubVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception{
		commonVOProcessing(vo, request);

		ProcessResultVO<AssignmentSubVO> resultVO = assignmentService.deleteSub(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "lecture.message.assignment.question.delete.success"));
		} else {
			resultVO.setMessage(getMessage(request, "lecture.message.assignment.question.delete.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 과제 은행 보기 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/editQbankPop")
	public String editQbankPop(AssignmentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		//-- 연결과목을 가져와 Attribute에 셋팅
		CreateOnlineSubjectVO onlineSubjectVO = new CreateOnlineSubjectVO();
		onlineSubjectVO.setCrsCreCd(vo.getCrsCreCd());
		List<CreateOnlineSubjectVO> subjectList = createCourseSubjectService.listOnlineSubject(onlineSubjectVO).getReturnList();

		request.setAttribute("subjectList", subjectList);

		CreateOnlineSubjectVO createOnlineSubjectVO = new CreateOnlineSubjectVO();
		AssignmentQbankCategoryVO assiQbankCategoryVO = new AssignmentQbankCategoryVO();
		List<AssignmentQbankCategoryVO> sbjCateList = null;
		List<AssignmentQbankQuestionVO> bankQstnList = null;
		//과목이 존재할 경우
		if(subjectList.size() > 0){
			//항목 목록 가져오기
			if(!"".equals(StringUtil.nvl(vo.getSbjCd()))){
				createOnlineSubjectVO.setSbjCd(vo.getSbjCd());
			}else{
				createOnlineSubjectVO = subjectList.get(0);
			}
			sbjCateList = assignmentQbankCategoryService.listCategory(createOnlineSubjectVO.getSbjCd()).getReturnList();

			//항목이 존재할 경우
			if(sbjCateList.size() > 0){
				//문제은행 목록 가져오기
				if(!"".equals(StringUtil.nvl(vo.getCtgrCd()))){
					assiQbankCategoryVO.setCtgrCd(vo.getCtgrCd());
				}else{
					assiQbankCategoryVO = sbjCateList.get(0);
				}
				bankQstnList = assignmentQbankService.listQuestion(createOnlineSubjectVO.getSbjCd(), assiQbankCategoryVO.getCtgrCd()).getReturnList();
			}
		}

		request.setAttribute("sbjCateList", sbjCateList);
		request.setAttribute("bankQstnList", bankQstnList);
		request.setAttribute("vo", vo);

		return "home/lecture/assignment/teacher/qbank_write_pop";
	}

	/**
	 * 과제 후보 등록
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/addQbankSub")
	public String addQbankSub(AssignmentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception{
		commonVOProcessing(vo, request);

		return JsonUtil
				.responseJson(response, assignmentService.addQbankSub(vo));
	}

	/**
	 * 과제 응시생 목록 조회
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/listSend")
	public String listSend(AssignmentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		vo.setCurPage(Integer.parseInt(StringUtil.nvl(request.getParameter("curPage"),"1")));
		vo.setListScale(Integer.parseInt(StringUtil.nvl(request.getParameter("listScale"),"20")));
		vo.setPageScale(10);
		ProcessResultListVO<AssignmentSendVO> resultListVO = assignmentService.listSendPageing(vo);

		return JsonUtil
				.responseJson(response, resultListVO);
	}

	/**
	 * 과제 평가 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/editFormRatePop")
	public String editFormRateMain(AssignmentSendVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		//-- 학습자 정보 조회
		StudentVO studentVO = new StudentVO();
		studentVO.setStdNo(vo.getStdNo());
		studentVO = studentService.viewStudent(studentVO).getReturnVO();
		request.setAttribute("studentVO", studentVO);

		//-- 학습자 답안 조회
		vo = assignmentService.viewSend(vo).getReturnVO();
		request.setAttribute("vo", vo);

		//-- 과제 문제 정보 가져오기
		AssignmentVO assignmentVO = new AssignmentVO();
		assignmentVO.setCrsCreCd(vo.getCrsCreCd());
		assignmentVO.setAsmtSn(vo.getAsmtSn());


		assignmentVO = assignmentService.viewAssignment(assignmentVO).getReturnVO();
		request.setAttribute("assignmentVO", assignmentVO);

		//과제 후보정보 조회
		AssignmentSubVO assignmentSubVO = new AssignmentSubVO();
		assignmentSubVO.setCrsCreCd(vo.getCrsCreCd());
		assignmentSubVO.setAsmtSn(vo.getAsmtSn());
		assignmentSubVO.setAsmtSubSn(vo.getAsmtSubSn());

		assignmentSubVO = assignmentService.viewSub(assignmentSubVO).getReturnVO();
		request.setAttribute("assignmentSubVO", assignmentSubVO);
		request.setAttribute("uploadify", "Y");
		request.setAttribute("textareaResize", "none");
		

		return "home/lecture/assignment/teacher/asmt_rate";
	}


	/**
	 * 과제 평가 등록
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/addSendRate")
	public String addSendRate(AssignmentSendVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception{
		commonVOProcessing(vo, request);
		vo.setRateYn("Y");//평가완료 추가
		ProcessResultVO<AssignmentSendVO> resultVO = assignmentService.addSendRate(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "lecture.message.assignment.rate.success"));
		} else {
			resultVO.setMessage(getMessage(request, "lecture.message.assignment.rate.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 과제 점수 등록
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/addSendScore")
	public String addSendScore(AssignmentSendVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception{
		commonVOProcessing(vo, request);
		int listScale = Integer.parseInt(StringUtil.nvl(request.getParameter("listScale"),"20"));
	
		ProcessResultVO<AssignmentSendVO> resultVO = assignmentService.addSendScore(vo);

		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "lecture.message.assignment.rate.score.add.success"));
		} else {
			resultVO.setMessage(getMessage(request, "lecture.message.assignment.rate.score.add.failed"));
		}
		
		return JsonUtil.responseJson(response, resultVO);
	}


	/**
	 * 과제 점수 등록
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/addSendScoreAll")
	public String addSendScoreAll(AssignmentSendVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception{
		commonVOProcessing(vo, request);

		String strStdNo = request.getParameter("strStdNo");
		String strScore = request.getParameter("strScore");

		ProcessResultVO<AssignmentSendVO> resultVO = assignmentService.addSendScoreAll(vo, strStdNo, strScore);
		
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "lecture.message.assignment.rate.score.add.success"));
		} else {
			resultVO.setMessage(getMessage(request, "lecture.message.assignment.rate.score.add.failed"));
		}

		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 재제출 설정
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/removeSend")
	public String removeSend(AssignmentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception{
		commonVOProcessing(vo, request);

		AssignmentSendVO assignmentSendVO = new AssignmentSendVO();

		assignmentSendVO.setCrsCreCd(vo.getCrsCreCd());
		assignmentSendVO.setAsmtSn(vo.getAsmtSn());
		assignmentSendVO.setUserNoObj(request.getParameter("userNoObj"));
		assignmentSendVO.setUserNoObjArr(StringUtil.split(assignmentSendVO.getUserNoObj(), ","));
		
		ProcessResultVO<AssignmentSendVO> resultVO = new ProcessResultVO<>();
		
		try {
			if(StringUtil.nvl(assignmentSendVO.getUserNoObj()).equals("")) {
				throw new ServiceProcessException("선택된 수강생이 없습니다. 다시 시도바랍니다.");
			}
			
			String[] stdNoObjArr = assignmentSendVO.getUserNoObjArr();
			if(stdNoObjArr == null || stdNoObjArr.length == 0) {
				throw new ServiceProcessException("선택된 수강생이 없습니다. 다시 시도바랍니다.");
			}
			vo.setStdNoObjArr(stdNoObjArr);
			List<AssignmentSendVO> asmtSendCreList = assignmentService.listSend(vo).getReturnList();
			
			if(asmtSendCreList == null || asmtSendCreList.size() == 0) {
				throw new ServiceProcessException("선택된 수강생이 조회되지 않습니다. 새로고침 후 다시 시도바랍니다.");
			}
			
			resultVO = assignmentService.removeSend(assignmentSendVO, asmtSendCreList);
			resultVO.setMessage(getMessage(request, "lecture.message.assignment.rate.resend.success"));
		} catch (MediopiaDefineException e1) {
			resultVO.setResultFailed();
			resultVO.setMessage(e1.getMessage());
		} catch (Exception e) {
			resultVO.setResultFailed();
			resultVO.setMessage(getMessage(request, "lecture.message.assignment.rate.resend.failed"));
		}

		//setAlertMessage(request, getMessage(request, "lecture.message.assignment.rate.resend.success"));

		//return this.editAssignmentManageMain(vo, commandMap, model, request, response);
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 성적 다운로드
	 * @author mhShin
	 * @date 2013. 12. 02.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception ActionForward
	 */
	@RequestMapping(value="/listExcelDownloadScore")
	public String listExcelDownloadScore(AssignmentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		LogPrnLogVO printLogVO = new LogPrnLogVO();
		printLogVO.setUserNo(UserBroker.getUserNo(request));
		printLogVO.setUserNm(UserBroker.getUserName(request));
		printLogVO.setPrnDoc(getMessage(request, "lecture.title.assignment.pring.score"));
		printLogVO.setParam(vo.toString());
		logPrnLogService.add(printLogVO);

		response.setHeader("Content-Disposition", "attachment;filename=AssignmentScoreList_"+DateTimeUtil.getCurrentString()+".xlsx;");
		response.setHeader( "Content-Transfer-Coding", "binary" );
		response.setContentType("application/octet-stream");

		assignmentService.listReshCourseExcelDownload(vo, response.getOutputStream());

		return null;
	}

	/**
	 * 성적 엑셀 업로드 폼
	 * @author mhShin
	 * @date 2013. 12. 02.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception ActionForward
	 */
	@RequestMapping(value="/addFormExcel")
	public String addFormExcel(AssignmentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		request.setAttribute("vo", vo);
		request.setAttribute("uploadify", "Y");		
		return "home/lecture/assignment/teacher/excel_upload_pop";
	}

	/**
	 * 성적 엑셀 업로드
	 * @author mhShin
	 * @date 2013. 12. 02.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception ActionForward
	 */
	@RequestMapping(value="/addExcelUpload")
	public String addExcelUpload(AssignmentSendVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String fileName = StringUtil.nvl(request.getParameter("fileName"));
		String filePath = StringUtil.nvl(request.getParameter("filePath"));

		ProcessResultVO<AssignmentSendVO> resultVO = assignmentService.addExcelUpload(vo, fileName, filePath);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "lecture.message.assignment.rate.excel.upload.success"));
		} else {
			resultVO.setMessage(getMessage(request, "lecture.message.assignment.rate.excel.upload.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}


	/**
	 * 과제 후보 보기
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/viewSubPop")
	public String viewSubPop(AssignmentSubVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		//-- 과제 후보 정보를 찾아 Form에 셋팅
		vo = assignmentService.viewSub(vo).getReturnVO();
		request.setAttribute("vo", vo);
		request.setAttribute("fileupload", "Y");


		return "home/lecture/assignment/teacher/qstn_view";
	}

	/**
	 * 과제 정보 보기
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/viewAssignmentPop")
	public String viewAssignmentPop(AssignmentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception{
		commonVOProcessing(vo, request);
		vo.setCrsCreCd(UserBroker.getCreCrsCd(request));

		//-- assignment험의 정보를 가져온다.
		request.setAttribute("returnAssignmentVO", assignmentService.viewAssignment(vo).getReturnVO());
		//request.setAttribute("assignmentSubListVO", assignmentService.listSub(vo).getReturnList());

		return "home/lecture/assignment/asmt_view_pop";
	}

	/**
	 * 과제 수정 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/editFormAssignmentPop")
	public String editFormAssignmentPop(AssignmentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		//과제 유형
		List<SysCodeVO> asmtTypeList = codeMemService.getSysCodeList("ASMT_TYPE_CD");
		request.setAttribute("asmtTypeList", asmtTypeList);

		//과제 출제 유형
		List<SysCodeVO> asmtSelectTypeList = codeMemService.getSysCodeList("ASMT_SELECT_TYPE_CD");
		request.setAttribute("asmtSelectTypeList", asmtSelectTypeList);

		//등록여부
		List<SysCodeVO> regTypeList = codeMemService.getSysCodeList("REG_YN");
		request.setAttribute("regTypeList", regTypeList);

		request.setAttribute("vo", assignmentService.viewAssignment(vo).getReturnVO());
		request.setAttribute("gubun", "E");
		request.setAttribute("fileupload", "Y");

		return "home/lecture/assignment//teacher/asmt_edit_pop";
	}


	/**
	 * 과제 수정
	 *
	 * @return  ProcessResultVO
	 */
	public String editAssignmentPop(AssignmentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception{
		commonVOProcessing(vo, request);

		// 스크립트, 스타일 태그 제거
		vo.setAsmtCts(HtmlCleaner.cleanScript(vo.getAsmtCts()) );

		ProcessResultVO<AssignmentVO> resultVO = assignmentService.editAssignment(vo);

		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "lecture.message.assignment.edit.success"));
		} else {
			resultVO.setMessage(getMessage(request, "lecture.message.assignment.edit.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 과제별 성적차트
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	@RequestMapping(value="/asmtStatus")
	public String asmtStatus(AssignmentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception{
		commonVOProcessing(vo, request);

		AssignmentStatusVO astVO = new AssignmentStatusVO();
		if(vo.getAsmtSn() == 0){
			StdScoreVO ssVO = new StdScoreVO();
			ssVO.setCrsCreCd(vo.getCrsCreCd());
			List<StdScoreVO> stdScoreList = stdScoreService.list(ssVO).getReturnList();
			astVO = new AssignmentStatusVO();
			for(StdScoreVO issVO : stdScoreList) {
				if(issVO.getAsmtScore() < 10) {
					astVO.setScoreZone00(astVO.getScoreZone00() + 1);
				} else if(issVO.getAsmtScore() < 20) {
					astVO.setScoreZone10(astVO.getScoreZone10() + 1);
				} else if(issVO.getAsmtScore() < 30) {
					astVO.setScoreZone20(astVO.getScoreZone20() + 1);
				} else if(issVO.getAsmtScore() < 40) {
					astVO.setScoreZone30(astVO.getScoreZone30() + 1);
				} else if(issVO.getAsmtScore() < 50) {
					astVO.setScoreZone40(astVO.getScoreZone40() + 1);
				} else if(issVO.getAsmtScore() < 60) {
					astVO.setScoreZone50(astVO.getScoreZone50() + 1);
				} else if(issVO.getAsmtScore() < 70) {
					astVO.setScoreZone60(astVO.getScoreZone60() + 1);
				} else if(issVO.getAsmtScore() < 80) {
					astVO.setScoreZone70(astVO.getScoreZone70() + 1);
				} else if(issVO.getAsmtScore() < 90) {
					astVO.setScoreZone80(astVO.getScoreZone80() + 1);
				} else if(issVO.getAsmtScore() < 100) {
					astVO.setScoreZone90(astVO.getScoreZone90() + 1);
				} else {
					astVO.setScoreZone100(astVO.getScoreZone100() + 1);
				}
			}

		} else {
			List<AssignmentSendVO> resultList = assignmentService.listSend(vo).getReturnList();;
			astVO = new AssignmentStatusVO();
			for(AssignmentSendVO iasmtVO : resultList) {
				if("Y".equals(iasmtVO.getRateYn())){
					if(iasmtVO.getScore() < 10) {
						astVO.setScoreZone00(astVO.getScoreZone00() + 1);
					} else if(iasmtVO.getScore() < 20) {
						astVO.setScoreZone10(astVO.getScoreZone10() + 1);
					} else if(iasmtVO.getScore() < 30) {
						astVO.setScoreZone20(astVO.getScoreZone20() + 1);
					} else if(iasmtVO.getScore() < 40) {
						astVO.setScoreZone30(astVO.getScoreZone30() + 1);
					} else if(iasmtVO.getScore() < 50) {
						astVO.setScoreZone40(astVO.getScoreZone40() + 1);
					} else if(iasmtVO.getScore() < 60) {
						astVO.setScoreZone50(astVO.getScoreZone50() + 1);
					} else if(iasmtVO.getScore() < 70) {
						astVO.setScoreZone60(astVO.getScoreZone60() + 1);
					} else if(iasmtVO.getScore() < 80) {
						astVO.setScoreZone70(astVO.getScoreZone70() + 1);
					} else if(iasmtVO.getScore() < 90) {
						astVO.setScoreZone80(astVO.getScoreZone80() + 1);
					} else if(iasmtVO.getScore() < 100) {
						astVO.setScoreZone90(astVO.getScoreZone90() + 1);
					} else {
						astVO.setScoreZone100(astVO.getScoreZone100() + 1);
					}
				} else {
					astVO.setScoreZone00(astVO.getScoreZone00() + 1);
				}
			}
		}


		return JsonUtil.responseJson(response, astVO);
	}


	private String getEditorType() {
		String returnUrl = "home/lecture/assignment/teacher/qstn_write";
		//-- 에디터 사용여부를 판단하여 해당 Webeditor를 사용하도록 한다.
		//-- BBS_INFO의 editorUseYn은 더이상 사용하지 않음.
		if("Y".equals(Constants.WEBEDITOR_YSEYN)) {
			if("DAUMEDITOR".equals(Constants.WEBEDITOR_NAME)) returnUrl = "home/lecture/assignment/teacher/qstn_write_daumeditor";
			else if("SUMMERNOTE".equals(Constants.WEBEDITOR_NAME)) returnUrl = "home/lecture/assignment/teacher/qstn_write_summernote";
		}
		return returnUrl;
	}

	@RequestMapping(value="/asmtInfoList")
	public String asmtInfoList(AssignmentVO vo, HttpServletRequest request) throws Exception{
		
		List<ScoreDataDto> dataList = assignmentService.listAsmtScore(vo).stream()
										.map(ScoreDataDto::new)
										.collect(Collectors.toList());
		
		request.setAttribute("dataList", dataList);
		request.setAttribute("infoTitle", "과제");
		
		return "home/lecture/exam/exam_view_score_detail_div";
	}
	
	/**
	 * 코딩 과제 문제 조회
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	@RequestMapping(value="/getAsmtSub")
	public String getAsmtSub(AssignmentSubVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception{
		commonVOProcessing(vo, request);

		String crsCreCd = UserBroker.getCreCrsCd(request);
		String StdNo = UserBroker.getStudentNo(request);
		vo.setCrsCreCd(crsCreCd);
		
		ProcessResultVO<AssignmentSubVO> resultVO = assignmentService.viewSub(vo);
		
		//제한사항
		AssignmentSubConstVO ascVO = new AssignmentSubConstVO();
		ascVO.setAsmtSn(vo.getAsmtSn());
		ascVO.setAsmtSubSn(vo.getAsmtSubSn());
		ascVO.setCrsCreCd(crsCreCd);
		resultVO.getReturnVO().setAsmtSubConstVOList(assignmentService.listSubConst(ascVO).getReturnList());
		
		//답변 조회
		AssignmentSendVO sendVO = new AssignmentSendVO();
		sendVO.setCrsCreCd(crsCreCd);
		sendVO.setAsmtSn(vo.getAsmtSn());
		sendVO.setAsmtSubSn(vo.getAsmtSubSn());
		sendVO.setStdNo(StdNo);
		sendVO = assignmentService.selectTestResult(sendVO).getReturnVO();
		
		if(sendVO != null) {
			resultVO.getReturnVO().setSendTitle(sendVO.getSendTitle());
			resultVO.getReturnVO().setSendCts(sendVO.getSendCts());
		}
		

		return JsonUtil.responseJson(response, resultVO);
	}
	
	/**
	 * 코딩과제 제출(학습자) : send정보 insert와 동시에 내용 isnert
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value= {"/addSendCodeAsmt"})
	public String addSendCodeAsmt(AssignmentSendVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		AssignmentVO avo = new AssignmentVO();
		String crsCreCd = UserBroker.getCreCrsCd(request);
		String stdNo = UserBroker.getStudentNo(request);
		String userNo = UserBroker.getUserNo(request);
		
		avo.setCrsCreCd(crsCreCd);
		avo.setStdNo(stdNo);
		avo.setAsmtSn(vo.getAsmtSn());

		avo = assignmentService.viewAssignment(avo).getReturnVO();
		avo.setAsmtCts(StringUtil.getHtmlContents(avo.getAsmtCts()));

		//코딩 과제 제출
		ProcessResultVO<AssignmentSendVO> resultVO = new ProcessResultVO<AssignmentSendVO>();
		
		vo.setCrsCreCd(crsCreCd);
		vo.setStdNo(stdNo);
		vo.setSendCnt(0);
		vo.setRegNo(userNo);
		vo.setSendCts(HtmlCleaner.cleanScript(vo.getSendCts()));
		vo.setRegIp(request.getRemoteAddr());
		resultVO = assignmentService.addSendCodeAsmt(vo);

		//2015.11.25 김현욱 추가 과제제출건수를 비교
		/*
		 * if(vo != null ) { int asmtLimitCnt = avo.getAsmtLimitCnt(); int sendCnt =
		 * vo.getSendCnt(); if(asmtLimitCnt <= sendCnt){ isSend = false; } }
		 */
		
		return JsonUtil.responseJson(response, resultVO);
	}
	
	/**
	 * 오프라인 과제 점수 등록
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/addOffSendScore")
	public String addOffSendScore(AssignmentSendVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception{
		commonVOProcessing(vo, request);
		int listScale = Integer.parseInt(StringUtil.nvl(request.getParameter("listScale"),"20"));
	
		ProcessResultVO<AssignmentSendVO> resultVO = assignmentService.addOffSendScore(vo);

		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "lecture.message.assignment.rate.score.add.success"));
		} else {
			resultVO.setMessage(getMessage(request, "lecture.message.assignment.rate.score.add.failed"));
		}
		
		return JsonUtil.responseJson(response, resultVO);
	}


	/**
	 * 오프라인 과제 점수 등록
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/addOffSendScoreAll")
	public String addOffSendScoreAll(AssignmentSendVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception{
		commonVOProcessing(vo, request);

		String strStdNo = request.getParameter("strStdNo");
		String strScore = request.getParameter("strScore");

		ProcessResultVO<AssignmentSendVO> resultVO = assignmentService.addOffSendScoreAll(vo, strStdNo, strScore);
		
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "lecture.message.assignment.rate.score.add.success"));
		} else {
			resultVO.setMessage(getMessage(request, "lecture.message.assignment.rate.score.add.failed"));
		}

		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 과제 평가 폼(코딩학습)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/rateCodeAsmtFormPop")
	public String rateCodeAsmtForm( AssignmentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		AssignmentSendVO assignmentSendVO = vo.getAssignmentSendVO();
		AssignmentSubVO assignmentSubVO = vo.getAssignmentSubVO();
		//-- 학습자 정보 조회
		StudentVO studentVO = new StudentVO();
		studentVO.setStdNo(vo.getStdNo());
		studentVO = studentService.viewStudent(studentVO).getReturnVO();
		request.setAttribute("studentVO", studentVO);

		vo = assignmentService.viewAssignment(vo).getReturnVO();
		request.setAttribute("vo", vo);

		assignmentSendVO.setAsmtSn(vo.getAsmtSn());
		assignmentSendVO.setCrsCreCd(vo.getCrsCreCd());
		assignmentSendVO.setStdNo(studentVO.getStdNo());
		assignmentSendVO = assignmentService.viewSend(assignmentSendVO).getReturnVO();
		request.setAttribute("assignmentSendVO", assignmentSendVO);
		
		//과제 후보정보 조회
		vo.setStdNo(studentVO.getStdNo());
		List<AssignmentSubVO> asList =assignmentService.listCodeSub(vo).getReturnList();
		request.setAttribute("assignmentSubList", asList);
		
		
		return "home/lecture/assignment/teacher/code_asmt_rate_write_pop";
	}
	
	/**
	 * AI 과제 후보 등록 폼(강사)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addAIQstnPop")
	public String addAIQstnPop( AssignmentSubVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response ) throws Exception {
		commonVOProcessing(vo, request);

		List<SysCodeVO> asmtDevLangCdList = codeMemService.getSysCodeList("DEV_LANG_CD", UserBroker.getLocaleKey(request));
        List<SysCodeVO> asmtDiffLvlCdList = codeMemService.getSysCodeList("DIFF_LVL_CD", UserBroker.getLocaleKey(request));
        AssignmentVO aVO= new AssignmentVO();
        aVO.setCrsCreCd(vo.getCrsCreCd());
        aVO.setAsmtSn(vo.getAsmtSn());
        
        request.setAttribute("asmtDevLangCdList", asmtDevLangCdList);
		request.setAttribute("asmtDiffLvlCdList", asmtDiffLvlCdList);
        request.setAttribute("fileupload", "Y");
        request.setAttribute("summernote", "Y");
        request.setAttribute("aVO", assignmentService.viewAssignment(aVO).getReturnVO());
		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "A");
		
		//gpt정보
		request.setAttribute("gptUrl", GPT_URL);
		request.setAttribute("gptVer", GPT_VERSION);
		request.setAttribute("gptKey", GPT_KEY);
		return "home/lecture/assignment/teacher/asmt_AI_qstn_write_summernote_pop";
	}
}

