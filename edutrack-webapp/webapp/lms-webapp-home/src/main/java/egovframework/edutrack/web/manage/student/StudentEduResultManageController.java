package egovframework.edutrack.web.manage.student;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.edutrack.comm.exception.AjaxProcessException;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.course.createcourse.service.CreateCourseService;
import egovframework.edutrack.modules.course.createcourse.service.CreateCourseVO;
import egovframework.edutrack.modules.course.decls.service.CreCrsDeclsService;
import egovframework.edutrack.modules.course.decls.service.CreCrsDeclsVO;
import egovframework.edutrack.modules.log.privateinfo.service.LogPrivateInfoInqLogVO;
import egovframework.edutrack.modules.log.privateinfo.service.LogPrivateInfoService;
import egovframework.edutrack.modules.log.prnlog.service.LogPrnLogService;
import egovframework.edutrack.modules.log.prnlog.service.LogPrnLogVO;
import egovframework.edutrack.modules.student.result.service.EduResultService;
import egovframework.edutrack.modules.student.result.service.EduResultVO;
import egovframework.edutrack.modules.student.student.service.StudentService;
import egovframework.edutrack.modules.student.student.service.StudentVO;
import egovframework.edutrack.modules.student.worklog.service.StdEduRsltWorkLogVO;


@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/mng/std/eduResult")
public class StudentEduResultManageController extends GenericController{

	@Autowired @Qualifier("eduResultService")
	private EduResultService			eduResultService;

	@Autowired @Qualifier("createCourseService")
	private CreateCourseService		createCourseService;

	@Autowired @Qualifier("logPrnLogService")
//	private PrintLogService			printLogService;
	private LogPrnLogService			logPrnLogService;

	@Autowired @Qualifier("logPrivateInfoService")
	private LogPrivateInfoService		logPrivateInfoService;

	@Autowired @Qualifier("creCrsDeclsService")
	private CreCrsDeclsService 		creCrsDeclsService;

	@Autowired @Qualifier("studentService")
	private StudentService			studentService;
	/**
	 * 수강 결과 관리 메인
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/resultMain")
	public String resultMain( EduResultVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		//-- 개설 과정 정보를 가져온다.
		CreateCourseVO createCourseVO = new CreateCourseVO();
		createCourseVO.setCrsCreCd(vo.getCrsCreCd());
		createCourseVO = createCourseService.viewCreateCourse(createCourseVO).getReturnVO();
		request.setAttribute("createCourseVO", createCourseVO);

		//--- 분반 목록
		CreCrsDeclsVO creCrsDeclsVO = new CreCrsDeclsVO();
		creCrsDeclsVO.setCrsCreCd(vo.getCrsCreCd());
		List<CreCrsDeclsVO> creCrsDeclsList = creCrsDeclsService.list(creCrsDeclsVO).getReturnList();
		request.setAttribute("creCrsDeclsList", creCrsDeclsList);
		request.setAttribute("vo", vo);
		request.setAttribute("paging", "Y");
		
		return "mng/student/result/result_main";
	}
	

	/**
	 * 수강 결과자/수료자 목록 조회
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/listResult")
	public String listResult( EduResultVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		commonVOProcessing(vo, request);

		vo.setCurPage(Integer.parseInt(StringUtil.nvl(request.getParameter("curPage"),"1")));
		vo.setListScale(Integer.parseInt(StringUtil.nvl(request.getParameter("listScale"),"20")));
		vo.setPageScale(10);
		ProcessResultListVO<EduResultVO> resultList = eduResultService.listResultPaging(vo);

		//-- 개설 과정 정보를 가져온다.
		CreateCourseVO createCourseVO = new CreateCourseVO();
		createCourseVO.setCrsCreCd(vo.getCrsCreCd());
		createCourseVO = createCourseService.viewCreateCourse(createCourseVO).getReturnVO();
		request.setAttribute("createCourseVO", createCourseVO);

	
		request.setAttribute("eduResultList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());
		request.setAttribute("vo", vo);
		return "mng/student/result/result_list_div";
	}


	/**
	 * 수강 결과자/수료자 목록 조회 (JSON 형태로 반환)
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/listResultJSON")
	public String listResultJSON( EduResultVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

	
		vo.setCurPage(Integer.parseInt(StringUtil.nvl(request.getParameter("curPage"),"1")));
		vo.setListScale(Integer.parseInt(StringUtil.nvl(request.getParameter("listScale"),"20")));
		vo.setPageScale(10);

		ProcessResultListVO<EduResultVO> resultListVO = eduResultService.listResult(vo);

		return JsonUtil
			.responseJson(response, resultListVO);
	}


	/**
	 * 수강 결과 등록
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/addResult")
	public String addResult( EduResultVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		
		commonVOProcessing(vo, request);

		// 작업 메소드 설정 MGR_ 관리자, LEC_ 강의실
		// AUTO 자동 점수 처리, BATCH 배치 성적 처리, EACH 단건 성적 처리
		StdEduRsltWorkLogVO lVO = new StdEduRsltWorkLogVO();
		lVO.setRegNo(vo.getRegNo());
		lVO.setCrsCreCd(vo.getCrsCreCd());
		lVO.setRegDivCd("MGR_EACH");
		lVO.setRegMthd("StudentEduResultManageAction.addResult");
		lVO.setRegCts(vo.getStringValue());

		ProcessResultVO<EduResultVO> resultVO = eduResultService.addResult(vo, lVO);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "student.message.result.add.success"));
		} else {
			resultVO.setMessage(getMessage(request, "student.message.result.add.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 수강 결과 등록
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/addResultAll")
	public String addResultAll( EduResultVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		
		commonVOProcessing(vo, request);
		
		String strStdNo = StringUtil.nvl(request.getParameter("strStdNo"));
		String strPrgrScore = StringUtil.nvl(request.getParameter("strPrgrScore"));
		String strAttdScore = StringUtil.nvl(request.getParameter("strAttdScore"));
		String strExamScore = StringUtil.nvl(request.getParameter("strExamScore"));
		String strSemiExamScore = StringUtil.nvl(request.getParameter("strSemiExamScore"));
		String strAsmtScore = StringUtil.nvl(request.getParameter("strAsmtScore"));
		String strForumScore = StringUtil.nvl(request.getParameter("strForumScore"));
		String strJoinScore = StringUtil.nvl(request.getParameter("strJoinScore"));
		String strEtcScore = StringUtil.nvl(request.getParameter("strEtcScore"));
		String strTotScore = StringUtil.nvl(request.getParameter("strTotScore"));

		// 작업 메소드 설정 MGR_ 관리자, LEC_ 강의실
		// AUTO 자동 점수 처리, BATCH 배치 성적 처리, EACH 단건 성적 처리
		StdEduRsltWorkLogVO lVO = new StdEduRsltWorkLogVO();
		lVO.setRegNo(vo.getRegNo());
		lVO.setCrsCreCd(vo.getCrsCreCd());
		lVO.setRegDivCd("MGR_BATCH");
		lVO.setRegMthd("StudentEduResultManageAction.addResultAll");
		lVO.setRegCts(vo.getStringValue());

		ProcessResultVO<EduResultVO> resultVO = eduResultService.addResultAll(vo, strStdNo,
				strPrgrScore, strAttdScore, strExamScore, strSemiExamScore, strAsmtScore, strForumScore,
				strJoinScore, strEtcScore, strTotScore,"", lVO);

		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "student.message.result.add.success"));
		} else {
			resultVO.setMessage(getMessage(request, "student.message.result.add.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 자동 점수 처리
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/addAutoResult")
	public String addAutoResult(EduResultVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {

		commonVOProcessing(vo, request);

		// 작업 메소드 설정 MGR_ 관리자, LEC_ 강의실
		// AUTO 자동 점수 처리, BATCH 배치 성적 처리, EACH 단건 성적 처리
		StdEduRsltWorkLogVO lVO = new StdEduRsltWorkLogVO();
		lVO.setRegNo(vo.getRegNo());
		lVO.setCrsCreCd(vo.getCrsCreCd());
		lVO.setRegDivCd("MGR_AUTO");
		lVO.setRegMthd("StudentEduResultManageAction.addAutoResult");
		lVO.setRegCts(vo.getStringValue());

		ProcessResultVO<EduResultVO> resultVO = eduResultService.addAutoResult(vo, lVO);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "lecture.message.score.add.success"));
		} else {
			resultVO.setMessage(getMessage(request, "lecture.message.score.add.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 수강 결과 엑셀 다운로드
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/listExcelDownload")
	public String listExcelDownload(EduResultVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {

		commonVOProcessing(vo, request);

		//-- 개설 과정 정보를 가져온다.
		CreateCourseVO createCourseVO = new CreateCourseVO();
		createCourseVO.setCrsCreCd(vo.getCrsCreCd());
		createCourseVO = createCourseService.viewCreateCourse(createCourseVO).getReturnVO();

		//-- 엑셀 출력 로그를 저장한다.
//		PrintLogVO printLogVO = new PrintLogVO();	=> 기존
		LogPrnLogVO printLogVO = new LogPrnLogVO();
		printLogVO.setUserNo(UserBroker.getUserNo(request));
		printLogVO.setUserNm(UserBroker.getUserName(request));
		printLogVO.setPrnDoc(createCourseVO.getCrsCreNm()+"과정 : 성적관리 - 수강생성적리스트 엑셀 출력");
		printLogVO.setParam(vo.toString());
//		printLogService.addPrintLog(printLogVO);
		logPrnLogService.add(printLogVO);


		//-- 개인 정보 조회 로그를 저장한다.
//		PrivateInfoLogVO privateInfoLogVO = new PrivateInfoLogVO();
		LogPrivateInfoInqLogVO privateInfoLogVO = new LogPrivateInfoInqLogVO();
		privateInfoLogVO.setOrgCd(UserBroker.getUserOrgCd(request));
		privateInfoLogVO.setMenuCd(UserBroker.getMenuCode(request));
		privateInfoLogVO.setDivCd("EXCEL");
		privateInfoLogVO.setUserNo(UserBroker.getUserNo(request));
		privateInfoLogVO.setUserNm(UserBroker.getUserName(request));
		privateInfoLogVO.setInqCts(createCourseVO.getCrsCreNm()+"\n"+vo.toString());
		logPrivateInfoService.add(privateInfoLogVO);

		response.setHeader("Content-Disposition", "attachment;filename=ScoreList_"+createCourseVO.getCrsCreCd()+".xlsx;");
		response.setHeader( "Content-Transfer-Coding", "binary" );
		response.setContentType("application/octet-stream");

		eduResultService.listExcelDownload(vo, createCourseVO, response.getOutputStream());

		return null;
	}

	/**
	 * 성적 엑셀 업로드 폼
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addFormExcelPop")
	public String addFormExcelPop(EduResultVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) {

		commonVOProcessing(vo, request);

		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "A");		
		
		return "mng/student/student/excel_upload_pop";
	}

	/**
	 * 성적 엑셀 업로드
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/addExcelUpload")
	public String addExcelUpload( EduResultVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		
		commonVOProcessing(vo, request);

		String fileName = StringUtil.nvl(request.getParameter("fileName"));
		String filePath = StringUtil.nvl(request.getParameter("filePath"));

		//-- 개설 과정 정보를 가져온다.
		CreateCourseVO createCourseVO = new CreateCourseVO();
		createCourseVO.setCrsCreCd(vo.getCrsCreCd());
		createCourseVO = createCourseService.viewCreateCourse(createCourseVO).getReturnVO();

		ProcessResultVO<EduResultVO> resultVO = eduResultService.addExcelUpload(vo, createCourseVO, fileName, filePath);
		if(!"".equals(resultVO.getMessage())) throw new AjaxProcessException("엑셀 성적 등록을 완료하였습니다.\n\n- 오류 라인 -\n"+resultVO.getMessage());

		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "student.message.result.add.success"));
		} else {
			resultVO.setMessage(getMessage(request, "student.message.result.add.failed"));
		}
		
		return JsonUtil.responseJson(response, resultVO);
	}



	/**
	 * 일괄저장용 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/scoreAllPop")
	public String scoreAllPop( EduResultVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		//-- 개설 과정 정보를 가져온다.
		CreateCourseVO createCourseVO = new CreateCourseVO();
		createCourseVO.setCrsCreCd(vo.getCrsCreCd());
		createCourseVO = createCourseService.viewCreateCourse(createCourseVO).getReturnVO();
		request.setAttribute("createCourseVO", createCourseVO);
		request.setAttribute("vo", vo);
		request.setAttribute("fileupload", "Y");
		
		return "mng/student/result/result_score_all_pop";
	}

	/**
	 * 성적우수자 등록
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/addEclt")
	public String addEclt( StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String studentList = request.getParameter("studentList");
		String targetList = request.getParameter("targetList");
		String crsCreCd = UserBroker.getCreCrsCd(request);

		ProcessResultVO<StudentVO> resultVO = studentService.editScoreEcltBatch(
				crsCreCd, targetList, studentList);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "lecture.message.score.excellent.add.success"));
		} else {
			resultVO.setMessage(getMessage(request, "lecture.message.score.excellent.add.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}
	

	/**
	 * crm 성적정보 조회
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/listStdEduResultForCrm")
	public String listStdEduResultForCrm( EduResultVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ProcessResultListVO<EduResultVO> resultList = eduResultService.listCrmEduResultPaging(vo);
		
		request.setAttribute("eduResultList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());
		request.setAttribute("paging", "Y");
		request.setAttribute("eduResultVO", vo);

		return "mng/user/info/user_std_edu_rslt_list";
	}
	
	/**
	 * 학습관리 - 상세보기
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/listResultPop")
	public String listResultPop( EduResultVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultListVO<EduResultVO> resultList = eduResultService.listResult(vo);

		//-- 개설 과정 정보를 가져온다.
		CreateCourseVO createCourseVO = new CreateCourseVO();
		createCourseVO.setCrsCreCd(vo.getCrsCreCd());
		createCourseVO = createCourseService.viewCreateCourse(createCourseVO).getReturnVO();
		request.setAttribute("createCourseVO", createCourseVO);

	
		request.setAttribute("eduResultList", resultList.getReturnList());
		request.setAttribute("vo", vo);
		
		return "mng/student/result/result_main_pop";
	}

}

