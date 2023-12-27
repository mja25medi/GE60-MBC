package egovframework.edutrack.web.lecture.student;

import java.io.IOException;
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

import egovframework.edutrack.comm.service.CommStatusVO;
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
import egovframework.edutrack.modules.lecture.assignment.service.AssignmentService;
import egovframework.edutrack.modules.lecture.assignment.service.AssignmentVO;
import egovframework.edutrack.modules.lecture.bookmark.service.BookmarkService;
import egovframework.edutrack.modules.lecture.bookmark.service.BookmarkVO;
import egovframework.edutrack.modules.lecture.exam.service.ExamService;
import egovframework.edutrack.modules.lecture.exam.service.ExamVO;
import egovframework.edutrack.modules.lecture.forum.service.ForumService;
import egovframework.edutrack.modules.lecture.forum.service.ForumVO;
import egovframework.edutrack.modules.lecture.main.service.MainLectureService;
import egovframework.edutrack.modules.lecture.main.service.MainLectureVO;
import egovframework.edutrack.modules.lecture.score.service.StdScoreService;
import egovframework.edutrack.modules.lecture.score.service.StdScoreVO;
import egovframework.edutrack.modules.log.privateinfo.service.LogPrivateInfoInqLogVO;
import egovframework.edutrack.modules.log.privateinfo.service.LogPrivateInfoService;
import egovframework.edutrack.modules.log.prnlog.service.LogPrnLogService;
import egovframework.edutrack.modules.log.prnlog.service.LogPrnLogVO;
import egovframework.edutrack.modules.student.result.service.EduResultService;
import egovframework.edutrack.modules.student.result.service.EduResultVO;
import egovframework.edutrack.modules.student.student.service.StudentService;
import egovframework.edutrack.modules.student.student.service.StudentVO;
import egovframework.edutrack.modules.student.worklog.service.StdEduRsltWorkLogVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;



/**
 * 수강 결과 콘트롤
 * @author JNOTE
 *
 */
@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/lec/eduResult")
public class EduResultLectureController
		extends GenericController {

	@Autowired @Qualifier("eduResultService")
	private EduResultService		eduResultService;

	@Autowired @Qualifier("createCourseService")
	private CreateCourseService	createCourseService;

	@Autowired @Qualifier("logPrnLogService")
	private LogPrnLogService		logPrnLogService;

	@Autowired @Qualifier("logPrivateInfoService")
	private LogPrivateInfoService	logPrivateInfoService;

	@Autowired @Qualifier("examService")
	private ExamService			examService;

	@Autowired @Qualifier("assignmentService")
	private AssignmentService		assignmentService;

	@Autowired @Qualifier("bookmarkService")
	private BookmarkService		bookmarkService;

	@Autowired @Qualifier("studentService")
	private StudentService			studentService;

	@Autowired @Qualifier("creCrsDeclsService")
	private CreCrsDeclsService 		creCrsDeclsService;

	@Autowired @Qualifier("forumService")
	private ForumService				forumService;
	
	@Autowired @Qualifier("stdScoreService")
	private StdScoreService			stdScoreService;
	
	@Autowired @Qualifier("mainLectureService")
	private MainLectureService			mainLectureService;
	
	/**
	 * 수강 결과 관리 메인
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/listResultMain")
	public String listResultMain(EduResultVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		commonVOProcessing(vo, request);

		String crsCreCd = UserBroker.getCreCrsCd(request);
		vo.setCrsCreCd(crsCreCd);

		//-- 개설 과정 정보를 가져온다.
		CreateCourseVO createCourseVO = new CreateCourseVO();
		createCourseVO.setCrsCreCd(vo.getCrsCreCd());
		createCourseVO = createCourseService.viewCreateCourse(createCourseVO).getReturnVO();

		//--- 분반 목록
		CreCrsDeclsVO creCrsDeclsVO = new CreCrsDeclsVO();
		creCrsDeclsVO.setCrsCreCd(crsCreCd);
		List<CreCrsDeclsVO> creCrsDeclsList = creCrsDeclsService.list(creCrsDeclsVO).getReturnList();
		request.setAttribute("creCrsDeclsList", creCrsDeclsList);

		request.setAttribute("createCourseVO", createCourseVO);


		int curPage = Integer.parseInt(StringUtil.nvl(request.getParameter("curPage"),"1"));
		int listScale = Integer.parseInt(StringUtil.nvl(request.getParameter("listScale"),"20"));

		vo.setCurPage(curPage);
		vo.setListScale(listScale);
		vo.setPageScale(10);
		ProcessResultListVO<EduResultVO> resultListVO = eduResultService.listResultPaging(vo);

		request.setAttribute("studentList", resultListVO.getReturnList());
		request.setAttribute("pageInfo", resultListVO.getPageInfo());
		request.setAttribute("curPage", curPage);
		request.setAttribute("listScale", listScale);
		request.setAttribute("userNm", vo.getUserNm());
		request.setAttribute("vo", vo);

		return "home/student/result/result_list";
	}

	/**
	 * 수강 결과 관리 메인
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/statusResultMain")
	public String statusResultMain(EduResultVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		commonVOProcessing(vo, request);

		String crsCreCd = UserBroker.getCreCrsCd(request);
		vo.setCrsCreCd(crsCreCd);

		//-- 개설 과정 정보를 가져온다.
		CreateCourseVO createCourseVO = new CreateCourseVO();
		createCourseVO.setCrsCreCd(vo.getCrsCreCd());
		createCourseVO = createCourseService.viewCreateCourse(createCourseVO).getReturnVO();

		//--- 분반 목록
		CreCrsDeclsVO creCrsDeclsVO = new CreCrsDeclsVO();
		creCrsDeclsVO.setCrsCreCd(crsCreCd);
		List<CreCrsDeclsVO> creCrsDeclsList = creCrsDeclsService.list(creCrsDeclsVO).getReturnList();
		request.setAttribute("creCrsDeclsList", creCrsDeclsList);

		request.setAttribute("createCourseVO", createCourseVO);

		List<EduResultVO> resultList = eduResultService.listResult(vo).getReturnList();
		List<CommStatusVO> statusList = new ArrayList<CommStatusVO>();
		for(int i = 0; i <= 100; i++ ) {
			CommStatusVO csVO = new CommStatusVO();
			csVO.setKeyCode(i+"");
			csVO.setKeyValue(0+"");
			statusList.add(csVO);
		}
		for(EduResultVO erVO : resultList) {
			for(CommStatusVO csVO : statusList) {
				if(Math.round(erVO.getTotScore()) == Integer.parseInt(csVO.getKeyCode())) {
					int keyValue = Integer.parseInt(csVO.getKeyValue()) + 1;
					csVO.setKeyValue(keyValue+"");
				}
			}

		}
		request.setAttribute("statusList", statusList);
		request.setAttribute("vo", vo);
		return "home/student/result/result_status";
	}

	/**
	 * 일괄저장용 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/scoreAllFormPop")
	public String scoreAllFormPop(EduResultVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		commonVOProcessing(vo, request);

		//-- 개설 과정 정보를 가져온다.
		CreateCourseVO createCourseVO = new CreateCourseVO();
		createCourseVO.setCrsCreCd(vo.getCrsCreCd());
		createCourseVO = createCourseService.viewCreateCourse(createCourseVO).getReturnVO();
		request.setAttribute("createCourseVO", createCourseVO);
		request.setAttribute("vo", vo);

		return "home/student/result/result_score_all_pop";
	}

	/**
	 * 수강 결과 등록
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/addResult")
	public String addResult(EduResultVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		commonVOProcessing(vo, request);

		// 작업 메소드 설정 MGR_ 관리자, LEC_ 강의실
		// AUTO 자동 점수 처리, BATCH 배치 성적 처리, EACH 단건 성적 처리
		StdEduRsltWorkLogVO lVO = new StdEduRsltWorkLogVO();
		lVO.setRegNo(vo.getRegNo());
		lVO.setCrsCreCd(vo.getCrsCreCd());
		lVO.setRegDivCd("LEC_EACH");
		lVO.setRegMthd("EduResultLectureAction.addResult");
		lVO.setRegCts(vo.getStringValue());

		ProcessResultVO<EduResultVO> resultVO = eduResultService.addResult(vo, lVO);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "lecture.message.score.add.success"));
		} else {
			resultVO.setMessage(getMessage(request, "lecture.message.score.add.failed"));
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
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		commonVOProcessing(vo, request);

		// 작업 메소드 설정 MGR_ 관리자, LEC_ 강의실
		// AUTO 자동 점수 처리, BATCH 배치 성적 처리, EACH 단건 성적 처리
		StdEduRsltWorkLogVO lVO = new StdEduRsltWorkLogVO();
		lVO.setRegNo(vo.getRegNo());
		lVO.setCrsCreCd(vo.getCrsCreCd());
		lVO.setRegDivCd("LEC_AUTO");
		lVO.setRegMthd("EduResultLectureAction.addAutoResult");
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
	 * 수강 결과 등록
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/addResultAll")
	public String addResultAll(EduResultVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
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
		String strConvScore = "";

		// 작업 메소드 설정 MGR_ 관리자, LEC_ 강의실
		// AUTO 자동 점수 처리, BATCH 배치 성적 처리, EACH 단건 성적 처리
		StdEduRsltWorkLogVO lVO = new StdEduRsltWorkLogVO();
		lVO.setRegNo(vo.getRegNo());
		lVO.setCrsCreCd(vo.getCrsCreCd());
		lVO.setRegDivCd("LEC_BATCH");
		lVO.setRegMthd("EduResultLectureAction.addResultAll");
		lVO.setRegCts(vo.getStringValue());

		ProcessResultVO<EduResultVO> resultVO = eduResultService.addResultAll(vo, strStdNo,
				strPrgrScore, strAttdScore, strExamScore, strSemiExamScore, strAsmtScore, strForumScore,
				strJoinScore, strEtcScore, strTotScore, strConvScore, lVO);
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
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		//-- 개설 과정 정보를 가져온다.
		CreateCourseVO createCourseVO = new CreateCourseVO();
		createCourseVO.setCrsCreCd(vo.getCrsCreCd());
		createCourseVO = createCourseService.viewCreateCourse(createCourseVO).getReturnVO();

		//-- 엑셀 출력 로그를 저장한다.
		LogPrnLogVO printLogVO = new LogPrnLogVO();
		printLogVO.setUserNo(UserBroker.getUserNo(request));
		printLogVO.setUserNm(UserBroker.getUserName(request));
		printLogVO.setPrnDoc(createCourseVO.getCrsCreNm()+" : "+getMessage(request, "lecture.title.score.excel.title"));
		printLogVO.setParam(vo.toString());
		logPrnLogService.add(printLogVO);

		//-- 개인 정보 조회 로그를 저장한다.
		LogPrivateInfoInqLogVO privateInfoLogVO = new LogPrivateInfoInqLogVO();
		privateInfoLogVO.setOrgCd(UserBroker.getUserOrgCd(request));
		privateInfoLogVO.setMenuCd(UserBroker.getMenuCode(request));
		privateInfoLogVO.setDivCd("EXCEL");
		privateInfoLogVO.setUserNo(UserBroker.getUserNo(request));
		privateInfoLogVO.setUserNm(UserBroker.getUserName(request));
		privateInfoLogVO.setInqCts(createCourseVO.getCrsCreNm()+"\n"+vo.toString());
		logPrivateInfoService.add(privateInfoLogVO);

		response.setHeader("Content-Disposition", "attachment;filename=ScoreList_"+createCourseVO.getCrsCreCd()+".xls;");
		response.setHeader( "Content-Transfer-Coding", "binary" );
		response.setContentType("application/octet-stream");

		eduResultService.listExcelDownload(vo, createCourseVO, response.getOutputStream());

		return null;
	}
	
	@RequestMapping(value="/listExcelDownloadForMngStd")
	public String listExcelDownloadForMngStd(EduResultVO vo, HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {
		CreateCourseVO createCourseVO = new CreateCourseVO();
		createCourseVO.setCrsCreCd(vo.getCrsCreCd());
		createCourseVO = createCourseService.viewCreateCourse(createCourseVO).getReturnVO();
		
		String title = "ScoreList_"+createCourseVO.getCreTerm() + "_"+ createCourseVO.getCrsCreCd();		
		response.setHeader("Content-Disposition", "attachment;filename="+ title + ".xls;");
		response.setHeader( "Content-Transfer-Coding", "binary" );
		response.setContentType("application/octet-stream");
		
		eduResultService.listExcelDownloadForMngStd(vo, createCourseVO , response.getOutputStream());
		
		return null;
	}

	@RequestMapping(value="/viewScoreMain")
	public String viewScoreMain(EduResultVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception{
		commonVOProcessing(vo, request);

		String crsCreCd = UserBroker.getCreCrsCd(request);
		String stdNo = UserBroker.getStudentNo(request);

		//-- 개설과정 정보 가져오기
		CreateCourseVO createCourseVO = new CreateCourseVO();
		createCourseVO.setCrsCreCd(crsCreCd);
		createCourseVO = createCourseService.viewCreateCourse(createCourseVO).getReturnVO();
		request.setAttribute("createCourseVO", createCourseVO);


		//-- 시험 관련 정보 가져오기.
		ExamVO examVO = new ExamVO();
		examVO.setCrsCreCd(crsCreCd);
		examVO.setStdNo(stdNo);
		List<ExamVO> examList = examService.listExamStd(examVO).getReturnList();
		request.setAttribute("examList", examList);

		//-- 과제 관련 정보 가져오기
		AssignmentVO assignmentVO = new AssignmentVO();
		assignmentVO.setCrsCreCd(crsCreCd);
		assignmentVO.setStdNo(stdNo);
		List<AssignmentVO> asmtList = assignmentService.listAssignmentStd(assignmentVO).getReturnList();
		request.setAttribute("asmtList", asmtList);

		//-- 진도 점수 가져온다.
		BookmarkVO bookmarkVO = new BookmarkVO();
		bookmarkVO.setCrsCreCd(crsCreCd);
		bookmarkVO.setStdNo(stdNo);
		bookmarkVO = bookmarkService.viewBookmarkStd(bookmarkVO).getReturnVO();
		request.setAttribute("bookmarkVO", bookmarkVO);

		//-- 토론 점수 가져온다.
		ForumVO forumVO = new ForumVO();
		forumVO.setCrsCreCd(crsCreCd);
		forumVO.setStdNo(stdNo);
		List <ForumVO> forumList = forumService.listForumStd(forumVO).getReturnList();
		request.setAttribute("forumList", forumList);

		//-- 나머지 결과 확인
		vo.setCrsCreCd(crsCreCd);
		vo.setStdNo(stdNo);

		try {
			vo = eduResultService.viewResult(vo).getReturnVO();
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		request.setAttribute("vo", vo);
		request.setAttribute("asterplot", "Y");


		return "home/student/result/result_view_main";
	}


	@RequestMapping(value="/viewScoreChart")
	public String viewScoreChart(EduResultVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception{
		commonVOProcessing(vo, request);

		String crsCreCd = UserBroker.getCreCrsCd(request);
		String stdNo = UserBroker.getStudentNo(request);

		//-- 개설과정 정보 가져오기
		CreateCourseVO createCourseVO = new CreateCourseVO();
		createCourseVO.setCrsCreCd(crsCreCd);
		createCourseVO = createCourseService.viewCreateCourse(createCourseVO).getReturnVO();
		request.setAttribute("createCourseVO", createCourseVO);


		//-- 시험 관련 정보 가져오기.
		ExamVO examVO = new ExamVO();
		examVO.setCrsCreCd(crsCreCd);
		examVO.setStdNo(stdNo);
		List<ExamVO> examList = examService.listExamStd(examVO).getReturnList();
		request.setAttribute("examList", examList);

		//-- 과제 관련 정보 가져오기
		AssignmentVO assignmentVO = new AssignmentVO();
		assignmentVO.setCrsCreCd(crsCreCd);
		assignmentVO.setStdNo(stdNo);
		List<AssignmentVO> asmtList = assignmentService.listAssignmentStd(assignmentVO).getReturnList();
		request.setAttribute("asmtList", asmtList);

		//-- 진도 점수 가져온다.
		BookmarkVO bookmarkVO = new BookmarkVO();
		bookmarkVO.setCrsCreCd(crsCreCd);
		bookmarkVO.setStdNo(stdNo);
		bookmarkVO = bookmarkService.viewBookmarkStd(bookmarkVO).getReturnVO();
		request.setAttribute("bookmarkVO", bookmarkVO);

		//-- 토론 점수 가져온다.
		ForumVO forumVO = new ForumVO();
		forumVO.setCrsCreCd(crsCreCd);
		forumVO.setStdNo(stdNo);
		List <ForumVO> forumList = forumService.listForumStd(forumVO).getReturnList();
		request.setAttribute("forumList", forumList);

		//-- 나머지 결과 확인
		vo.setCrsCreCd(crsCreCd);
		vo.setStdNo(stdNo);

		try {
			vo = eduResultService.viewResult(vo).getReturnVO();
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		request.setAttribute("vo", vo);


		return "home/student/result/result_view_json";
	}

	@RequestMapping(value="/viewFinalScoreChart")
	public String viewFinalScoreChart(EduResultVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception{
		commonVOProcessing(vo, request);


		String crsCreCd = UserBroker.getCreCrsCd(request);
		String stdNo = UserBroker.getStudentNo(request);

		//-- 개설과정 정보 가져오기
		CreateCourseVO createCourseVO = new CreateCourseVO();
		createCourseVO.setCrsCreCd(crsCreCd);
		createCourseVO = createCourseService.viewCreateCourse(createCourseVO).getReturnVO();
		request.setAttribute("createCourseVO", createCourseVO);


		//-- 시험 관련 정보 가져오기.
		ExamVO examVO = new ExamVO();
		examVO.setCrsCreCd(crsCreCd);
		examVO.setStdNo(stdNo);
		List<ExamVO> examList = examService.listExamStd(examVO).getReturnList();
		request.setAttribute("examList", examList);

		//-- 과제 관련 정보 가져오기
		AssignmentVO assignmentVO = new AssignmentVO();
		assignmentVO.setCrsCreCd(crsCreCd);
		assignmentVO.setStdNo(stdNo);
		List<AssignmentVO> asmtList = assignmentService.listAssignmentStd(assignmentVO).getReturnList();
		request.setAttribute("asmtList", asmtList);

		//-- 진도 점수 가져온다.
		BookmarkVO bookmarkVO = new BookmarkVO();
		bookmarkVO.setCrsCreCd(crsCreCd);
		bookmarkVO.setStdNo(stdNo);
		bookmarkVO = bookmarkService.viewBookmarkStd(bookmarkVO).getReturnVO();
		request.setAttribute("bookmarkVO", bookmarkVO);

		//-- 토론 점수 가져온다.
		ForumVO forumVO = new ForumVO();
		forumVO.setCrsCreCd(crsCreCd);
		forumVO.setStdNo(stdNo);
		List <ForumVO> forumList = forumService.listForumStd(forumVO).getReturnList();
		request.setAttribute("forumList", forumList);

		//-- 나머지 결과 확인
		vo.setCrsCreCd(crsCreCd);
		vo.setStdNo(stdNo);

		try {
			vo = eduResultService.viewResult(vo).getReturnVO();
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		request.setAttribute("eduResultVO", vo);


		return "home/student/result/result_view_final_json";
	}

	/**
	 * 성적우수자 등록
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/addEclt")
	public String addEclt(Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception{

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

		String classUserType = UserBroker.getClassUserType(request);
		String crsCreCd = UserBroker.getCreCrsCd(request);
		String stdNo = UserBroker.getStudentNo(request);
		String userNo = UserBroker.getUserNo(request);

		String retUrl = "";
		
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
		ProcessResultListVO<EgovMap> resultList = stdScoreService.listEndCreStd(ssVO);
		if(resultList.getResult() > 0 ) {
			stdScoreList = resultList.getReturnList();
		}
		request.setAttribute("stdScoreList", stdScoreList);
		
		//-- 나머지 결과 확인
		vo.setCrsCreCd(crsCreCd);
		vo.setStdNo(stdNo);

		request.setAttribute("vo", vo);


		return "home/student/result/result_view_end_score";
	}
	
	/**
	 * 수료증 리스트 조회 페이지
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/viewCertMain")
	public String viewCertMain(EduResultVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception{
		commonVOProcessing(vo, request);

		String classUserType = UserBroker.getClassUserType(request);
		String crsCreCd = UserBroker.getCreCrsCd(request);
		String stdNo = UserBroker.getStudentNo(request);
		String userNo = UserBroker.getUserNo(request);

		String retUrl = "";
		
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
		ProcessResultListVO<EgovMap> resultList = stdScoreService.listCreStd(ssVO);
		if(resultList.getResult() > 0 ) {
			stdScoreList = resultList.getReturnList();
		}
		request.setAttribute("stdScoreList", stdScoreList);
		
		//-- 나머지 결과 확인
		vo.setCrsCreCd(crsCreCd);
		vo.setStdNo(stdNo);

		request.setAttribute("vo", vo);

		return "home/student/result/result_view_cert_list";
	}
	
	@RequestMapping(value= "/viewResultPop")
	public String viewResultPop(EduResultVO vo, HttpServletRequest request) throws Exception {
		CreateCourseVO ccVO = new CreateCourseVO();
		ccVO.setCrsCreCd(vo.getCrsCreCd());
		CreateCourseVO creCourse = createCourseService.viewCreateCourse(ccVO).getReturnVO();
		
		EduResultVO eduResult = eduResultService.viewResult(vo).getReturnVO();
		
		request.setAttribute("creCourse", creCourse);
		request.setAttribute("eduResult", eduResult);
		
		return "home/lecture/user/teacher/edu_result_pop";
	}
	
	/**
	 * 미채점현황 리스트 조회 페이지
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/viewUnscoreStatusMain")
	public String viewUnscoreStatusMain(EduResultVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception{
		commonVOProcessing(vo, request);

		String classUserType = UserBroker.getClassUserType(request);
		String crsCreCd = UserBroker.getCreCrsCd(request);
		String stdNo = UserBroker.getStudentNo(request);
		String userNo = UserBroker.getUserNo(request);

		String retUrl = "";
		
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
		MainLectureVO mlVO = new MainLectureVO();
		mlVO.setUserNo(userNo);
		ProcessResultListVO<EgovMap> resultList = mainLectureService.listUnScoreStatus(mlVO);
		String forumListYn = "N";
		if(resultList.getResult() > 0 ) {
			stdScoreList = resultList.getReturnList();
			for (int i = 0; i < stdScoreList.size(); i++) {
				if("Y".equals(stdScoreList.get(i).get("forumListYn"))) {
					forumListYn = "Y";
					break;
				}
			}
		}
		request.setAttribute("forumListYn", forumListYn);
		request.setAttribute("stdScoreList", stdScoreList);
		
		//-- 나머지 결과 확인
		vo.setCrsCreCd(crsCreCd);
		vo.setStdNo(stdNo);

		request.setAttribute("vo", vo);


		return "home/student/result/result_view_unscore_status";
	}
}