package egovframework.edutrack.web.lecture.exam;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.exception.MediopiaDefineException;
import egovframework.edutrack.comm.exception.ServiceProcessException;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.service.SysCodeMemService;
import egovframework.edutrack.comm.util.web.BeanUtils;
import egovframework.edutrack.comm.util.web.DateTimeUtil;
import egovframework.edutrack.comm.util.web.HtmlCleaner;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.URLBuilder;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.course.course.service.CourseService;
import egovframework.edutrack.modules.course.creCrsResh.service.CreCrsReshService;
import egovframework.edutrack.modules.course.creCrsResh.service.CreCrsReshVO;
import egovframework.edutrack.modules.course.creCrsResh.service.CrsReshAnsrService;
import egovframework.edutrack.modules.course.createcourse.service.CreateCourseService;
import egovframework.edutrack.modules.course.createcourse.service.CreateCourseVO;
import egovframework.edutrack.modules.course.createcoursesubject.service.CreateCourseSubjectService;
import egovframework.edutrack.modules.course.createcoursesubject.service.CreateOnlineSubjectVO;
import egovframework.edutrack.modules.course.decls.service.CreCrsDeclsService;
import egovframework.edutrack.modules.course.decls.service.CreCrsDeclsVO;
import egovframework.edutrack.modules.course.exambank.service.ExamQbankCtgrVO;
import egovframework.edutrack.modules.course.exambank.service.ExamQbankQstnVO;
import egovframework.edutrack.modules.course.exambank.service.ExamQbankService;
import egovframework.edutrack.modules.lecture.exam.service.ExamQuestionVO;
import egovframework.edutrack.modules.lecture.exam.service.ExamService;
import egovframework.edutrack.modules.lecture.exam.service.ExamStareVO;
import egovframework.edutrack.modules.lecture.exam.service.ExamStatusVO;
import egovframework.edutrack.modules.lecture.exam.service.ExamVO;
import egovframework.edutrack.modules.lecture.score.service.ScoreDataDto;
import egovframework.edutrack.modules.lecture.score.service.StdScoreService;
import egovframework.edutrack.modules.lecture.score.service.StdScoreVO;
import egovframework.edutrack.modules.org.page.service.OrgPageService;
import egovframework.edutrack.modules.org.page.service.OrgPageVO;
import egovframework.edutrack.modules.student.student.service.StudentService;
import egovframework.edutrack.modules.student.student.service.StudentVO;
import egovframework.edutrack.modules.system.code.service.SysCodeService;
import egovframework.edutrack.modules.system.code.service.SysCodeVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;


/**
 * 시험 컨트롤
 * @author JNOTE
 *
 */
@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/lec/exam")
public class ExamLectureController
		extends GenericController {

	@Autowired @Qualifier("examService")
	private ExamService				examService;

	@Autowired @Qualifier("createCourseSubjectService")
	private CreateCourseSubjectService	createCourseSubjectService;

	@Autowired @Qualifier("studentService")
	private StudentService				studentService;

	@Autowired @Qualifier("sysCodeMemService")
	private SysCodeMemService				sysCodeMemService;

	@Autowired @Qualifier("examQbankService")
	private ExamQbankService			examQbankService;
	
	@Autowired @Qualifier("createCourseService")
	private CreateCourseService		createCourseService;
	
	@Autowired @Qualifier("courseService")
	private CourseService				courseService;

	@Autowired @Qualifier("creCrsDeclsService")
	private CreCrsDeclsService 		creCrsDeclsService;

	@Autowired @Qualifier("sysCodeService")
	private SysCodeService				sysCodeService;

	@Autowired @Qualifier("crsReshAnsrService")
	private CrsReshAnsrService			crsReshAnsrService;

	@Autowired @Qualifier("stdScoreService")
	private StdScoreService			stdScoreService;

	@Autowired @Qualifier("orgPageService")
	private OrgPageService			orgPageService;
	
	@Autowired @Qualifier("creCrsReshService")
	private CreCrsReshService		creCrsReshService;
	
	/**
	 * 시험 관리 메인
	 * @author twkim
	 * @date 2013. 11. 12.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	@RequestMapping(value="/examMain")
	public String examMain(ExamVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String stdNo = UserBroker.getStudentNo(request);
		String crsCreCd = UserBroker.getCreCrsCd(request);
		
		String returnUrl = "home/lecture/exam/teacher/exam_main";
		if("STU".equals(UserBroker.getClassUserType(request))){
			returnUrl = "home/lecture/exam/exam_main";
			vo.setStdNo(UserBroker.getStudentNo(request));
			vo.setRegYn("Y");
			
			CreCrsReshVO reshVO = new CreCrsReshVO();
			reshVO.setCrsCreCd(crsCreCd);
			reshVO.setUseYn("Y");
			reshVO.setRegYn("Y"); 
			reshVO.setStdNo(stdNo);

			reshVO = creCrsReshService.itemCount(reshVO).getReturnVO();
			reshVO= creCrsReshService.ansrCount(reshVO).getReturnVO();
			request.setAttribute("reshVO", reshVO);
		}

		//시험 목록 가져오기
		vo.setCrsCreCd(crsCreCd);
		request.setAttribute("examListVO", examService.listExam(vo).getReturnList());
		request.setAttribute("vo", vo);

		return returnUrl;
	}

	private HttpServletRequest request() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 시험 정보 등록 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addFormExamMain")
	public String addFormExamMain(ExamVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		//시험유형 가져오기
		List<SysCodeVO> examTypeList = sysCodeMemService.getSysCodeList("EXAM_TYPE_CD");
		request.setAttribute("examTypeList", examTypeList);

		//시험응시 유형 가져오기
		List<SysCodeVO> examStareTypeList = sysCodeMemService.getSysCodeList("EXAM_STARE_TYPE_CD");
		request.setAttribute("examStareTypeList", examStareTypeList);

		//시간 체크 사용여부 가져오기
		List<SysCodeVO> stareTmUseList = sysCodeMemService.getSysCodeList("STARE_TM_USE_YN");
		request.setAttribute("stareTmUseList", stareTmUseList);

		//문제 유형 가져오기
		List<SysCodeVO> examViewTypeList = sysCodeMemService.getSysCodeList("EXAM_VIEW_TYPE_CD");
		request.setAttribute("examViewTypeList", examViewTypeList);

		//사용 등록 여부 가져오기
		List<SysCodeVO> regYnList = sysCodeMemService.getSysCodeList("REG_YN");
		request.setAttribute("regYnList", regYnList);

	   	request.setAttribute("gubun", "A");
	   	request.setAttribute("vo", vo);

		return "home/lecture/exam/teacher/exam_write";
	}
	
	/**
	 * 시험 등록 폼 (모달) - 관리자와 동일하게 처리
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addExamMain")
	public String addExamMain( ExamVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		List<SysCodeVO> examTypeCdList = sysCodeMemService.getSysCodeList("EXAM_TYPE_CD", UserBroker.getLocaleKey(request));
        List<SysCodeVO> examStareTypeCdList = sysCodeMemService.getSysCodeList("EXAM_STARE_TYPE_CD", UserBroker.getLocaleKey(request));
        List<SysCodeVO> stareTmUseYnList = sysCodeMemService.getSysCodeList("STARE_TM_USE_YN", UserBroker.getLocaleKey(request));
        
		//사용 등록 여부 가져오기
		List<SysCodeVO> regYnList = sysCodeService.listCode("REG_YN").getReturnList();
		request.setAttribute("regYnList", regYnList);
		
		CreateOnlineSubjectVO  createOnlineSubjectVO = new CreateOnlineSubjectVO();
		String crsCreCd = UserBroker.getCreCrsCd(request);
		createOnlineSubjectVO.setCrsCreCd(crsCreCd);
		List<CreateOnlineSubjectVO> onlineList = createCourseSubjectService.listOnlineSubject(createOnlineSubjectVO).getReturnList();
		request.setAttribute("onlineSubjectList", onlineList);
		
		request.setAttribute("gubun", "A");
		request.setAttribute("vo", vo);
		request.setAttribute("examTypeCdList", examTypeCdList);
		request.setAttribute("examStareTypeCdList", examStareTypeCdList);
		request.setAttribute("stareTmUseYnList", stareTmUseYnList);
		
		return "home/lecture/exam/teacher/exam_write_main";
	}
	
	/**
	 * 시험 수정 폼 (모달) - 관리자와 동일하게 처리
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/editExamMain")
	public String editExamPop( ExamVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

        List<SysCodeVO> examTypeCdList = sysCodeMemService.getSysCodeList("EXAM_TYPE_CD", UserBroker.getLocaleKey(request));
        List<SysCodeVO> examStareTypeCdList = sysCodeMemService.getSysCodeList("EXAM_STARE_TYPE_CD", UserBroker.getLocaleKey(request));
        List<SysCodeVO> stareTmUseYnList = sysCodeMemService.getSysCodeList("STARE_TM_USE_YN", UserBroker.getLocaleKey(request));
        
        CreateOnlineSubjectVO  createOnlineSubjectVO = new CreateOnlineSubjectVO();
		String crsCreCd = UserBroker.getCreCrsCd(request);
		createOnlineSubjectVO.setCrsCreCd(crsCreCd);
		List<CreateOnlineSubjectVO> onlineList = createCourseSubjectService.listOnlineSubject(createOnlineSubjectVO).getReturnList();
		request.setAttribute("onlineSubjectList", onlineList);
        
		request.setAttribute("examTypeCdList", examTypeCdList);
		request.setAttribute("examStareTypeCdList", examStareTypeCdList);
		request.setAttribute("stareTmUseYnList", stareTmUseYnList);
		//-- exam험의 정보를 가져온다.
		request.setAttribute("vo", examService.viewExam(vo).getReturnVO());

		//사용 등록 여부 가져오기
		List<SysCodeVO> regYnList = sysCodeService.listCode("REG_YN").getReturnList();
		request.setAttribute("regYnList", regYnList);

		request.setAttribute("gubun", "E");
		return "home/lecture/exam/teacher/exam_write_main";
	}

	/**
	 * 시험 정보 등록 폼 - 페이지 방식
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	/*@RequestMapping(value="/editExamPop")
	public String editExamPop(ExamVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		//시험유형 가져오기
		List<SysCodeVO> examTypeList = sysCodeMemService.getSysCodeList("EXAM_TYPE_CD");
		request.setAttribute("examTypeList", examTypeList);

		//시험응시 유형 가져오기
		List<SysCodeVO> examStareTypeList = sysCodeMemService.getSysCodeList("EXAM_STARE_TYPE_CD");
		request.setAttribute("examStareTypeList", examStareTypeList);

		//시간 체크 사용여부 가져오기
		List<SysCodeVO> stareTmUseList = sysCodeMemService.getSysCodeList("STARE_TM_USE_YN");
		request.setAttribute("stareTmUseList", stareTmUseList);

		//문제 유형 가져오기
		List<SysCodeVO> examViewTypeList = sysCodeMemService.getSysCodeList("EXAM_VIEW_TYPE_CD");
		request.setAttribute("examViewTypeList", examViewTypeList);

		//사용 등록 여부 가져오기
		List<SysCodeVO> regYnList = sysCodeService.listCode("REG_YN").getReturnList();
		request.setAttribute("regYnList", regYnList);

		vo  = examService.viewExam(vo).getReturnVO();

	   	request.setAttribute("gubun", "E");
	   	request.setAttribute("vo", vo);

		return "home/lecture/exam/teacher/exam_edit_pop";
	}*/

	/**
	 * 시험 정보 등록
	 * @author twkim
	 * @date 2013. 11. 12.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	/*@RequestMapping(value="/addExam")
	public String addExam(ExamVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		try {
			examService.addExam(vo);
		} catch (Exception e) {
			e.printStackTrace();
			setAlertMessage(request, getMessage(request, "lecture.message.exam.add.failed"));
			return "home/lecture/exam/teacher/exam_write";	// 다시 편집 화면으로
		}

		setAlertMessage(request, getMessage(request, "lecture.message.exam.add.success"));
		// 읽기 화면으로
		return "redirect:"+ new URLBuilder("/lec/exam/examMain")
					.toString();
	}*/
	
	@RequestMapping(value="/addExam")
	public String addExam( ExamVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultVO<ExamVO> resultVO = new ProcessResultVO<>();
		
		if("ON".equals(StringUtil.nvl(vo.getExamTypeCd()))  && !vo.getIsCalCulateTotScore()) {//온라인 시험의 경우 시험 문항, 배점 확인
			resultVO.setMessage("시험 배점의 총합이 100이 아닙니다. 다시 수정바랍니다.");
			return JsonUtil.responseJson(response, resultVO);
		}
		if("".equals(StringUtil.nvl(vo.getRegYn()))) {
			vo.setRegYn("N");
		}
		if("ON".equals(StringUtil.nvl(vo.getExamTypeCd()))) {
			vo.setRegYn("N");//온라인 시험은 문제 생성 후 공개
		}else if("OFF".equals(StringUtil.nvl(vo.getExamTypeCd()))) {
			vo.setRegYn("Y");//오프라인 시험 생성은 공개 : 공개중인 시험만 성적 적용
		}
		resultVO = examService.addExam(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "lecture.message.exam.add.success"));
		} else {
			resultVO.setMessage(getMessage(request, "lecture.message.exam.add.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 시험 등록
	 * @author twkim
	 * @date 2013. 11. 19.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	@RequestMapping(value="/addRegistExam")
	public String addRegistExam(ExamVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		//관리자와 동일하게 수정, HRD에서 사용하지 않아 기능확인X
		String qstnScores = vo.getQstnScores();
		String examQstnSns = vo.getExamQstnSn();
		String qstnNos = vo.getQstnNos();
		
		vo = examService.viewExam(vo).getReturnVO();
		vo.setQstnScores(qstnScores);
		vo.setExamQstnSn(examQstnSns);
		vo.setQstnNos(qstnNos);
		
		ProcessResultVO<ExamVO> resultVO = new ProcessResultVO<>();

		try {
			if("Y".equals(vo.getRegYn())) {
				throw new ServiceProcessException("이미 공개중인 시험입니다.");
			}
			
			if("ON".equals(vo.getExamTypeCd()) && !vo.getIsCalCulateTotScore()) {
				throw new ServiceProcessException("시험 배점의 총합이 100이 아닙니다.\n시험관리에서 시험을 수정바랍니다.");
			}
			
			resultVO = examService.addRegistExam(vo);
		} catch (MediopiaDefineException e1) {
			resultVO.setMessage(e1.getMessage());
			resultVO.setResultFailed();
			log.error("[강사>시험관리>공개하기 오류] : " + e1.getMessage());
		} catch (Exception e) {
			resultVO.setMessage(getMessage(request, "lecture.message.exam.regist.failed"));
			resultVO.setResultFailed();
			log.error("[강사>시험관리>공개하기 오류] : 시험 공개하기 실패");
		}
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "lecture.message.exam.regist.success"));
		} else {
			resultVO.setMessage(!"".equals(StringUtil.nvl(resultVO.getMessage())) ? resultVO.getMessage() :  getMessage(request, "lecture.message.exam.regist.failed"));
		}
		
		setAlertMessage(request, resultVO.getMessage());
		// 읽기 화면으로
		return "redirect:"+ new URLBuilder("/lec/exam/editFormExamMain")
				.addParameter("examSn", vo.getExamSn())
				.addParameter("crsCreCd", vo.getCrsCreCd())
				.toString();
	}

	/**
	 * 시험 등록 취소
	 * @date 2016. 05. 10.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	@RequestMapping(value="/cancelRegistExam")
	public String cancelRegistExam(ExamVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultVO<ExamVO> resultVO = new ProcessResultVO<>();
		
		try {
			ExamVO resultExamVO = new ExamVO();
			resultExamVO = examService.viewExam(vo).getReturnVO();
			
			if(resultExamVO.getStareCnt() > 0) {
				throw new ServiceProcessException("응시한 수강생이 있습니다. 미공개할 수 없습니다.");
			}
			
			if("N".equals(resultExamVO.getRegYn())) {
				throw new ServiceProcessException("현재 미공개 중인 시험입니다.");
			}
			
			resultExamVO.setRegYn("N");
			resultVO = examService.editExam(resultExamVO);
			if(resultVO.getResult() > 0) {
				resultVO.setMessage(getMessage(request, "lecture.message.exam.cancel.success"));
			} else {
				resultVO.setMessage(getMessage(request, "lecture.message.exam.cancel.failed"));
			}
		} catch (MediopiaDefineException e1) {
			resultVO.setResultFailed();
			resultVO.setMessage(e1.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(getMessage(request, "lecture.message.exam.cancel.failed"));
		}

		setAlertMessage(request, resultVO.getMessage());
		// 읽기 화면으로
		return "redirect:"+ new URLBuilder("/lec/exam/editFormExamMain")
					.addParameter("examSn", vo.getExamSn())
					.addParameter("crsCreCd", vo.getCrsCreCd())
					.toString();

	}

	/**
	 * 시험 정보 수정 폼
	 * @author twkim
	 * @date 2013. 11. 12.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	@RequestMapping(value="/editFormExamMain")
	public String editFormExamMain(ExamVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		//Integer declsNo = vo.getDeclsNo();
		String userNm = vo.getUserNm();
		String gubun = StringUtil.nvl(vo.getGubun(), "Q");
		
		//-- exam험의 정보를 가져온다.
		ExamVO resultExamVO = examService.viewExam(vo).getReturnVO();
		
		//-- 분반과 사용자이름(목록 검색용 파라미터)셋팅
		//vo.setDeclsNo(declsNo);
		//vo.setUserNm(userNm);
		resultExamVO.setUserNm(userNm);
	   	request.setAttribute("vo", resultExamVO);

		//시간 체크 사용여부 가져오기
		List<SysCodeVO> stareTmUseList = sysCodeMemService.getSysCodeList("STARE_TM_USE_YN");
		request.setAttribute("stareTmUseList", stareTmUseList);

		//문제 유형 가져오기
		List<SysCodeVO> examViewTypeList = sysCodeMemService.getSysCodeList("EXAM_VIEW_TYPE_CD");
		request.setAttribute("examViewTypeList", examViewTypeList);

		//사용 등록 여부 가져오기
		List<SysCodeVO> regYnList = sysCodeService.listCode("REG_YN").getReturnList();
		request.setAttribute("regYnList", regYnList);
		
		//문제/평가 관리 url
		String returnUrl = "";
		
		//문제/평가 관리 구분
		if("R".equals(gubun)  || "OFF".equals(resultExamVO.getExamTypeCd())){
			//--- 분반 목록
			CreCrsDeclsVO creCrsDeclsVO = new CreCrsDeclsVO();
			creCrsDeclsVO.setCrsCreCd(vo.getCrsCreCd());
			List<CreCrsDeclsVO> creCrsDeclsList = creCrsDeclsService.list(creCrsDeclsVO).getReturnList();
			request.setAttribute("creCrsDeclsList", creCrsDeclsList);

			vo.setPageScale(20);
			// 평가 목록
			ProcessResultListVO<ExamStareVO> resultList = examService.listExamStarePaging(vo);

		   	request.setAttribute("examStareListVO", resultList.getReturnList());
		   	request.setAttribute("pageInfo", resultList.getPageInfo());
		   	request.setAttribute("paging", "Y");

			returnUrl = "home/lecture/exam/teacher/exam_manage_relt";		//평가관리
		} else if("S".equals(gubun)) {//결과통계 미사용
			//--- 분반 목록
			/*CreCrsDeclsVO creCrsDeclsVO = new CreCrsDeclsVO();
			creCrsDeclsVO.setCrsCreCd(vo.getCrsCreCd());
			List<CreCrsDeclsVO> creCrsDeclsList = creCrsDeclsService.list(creCrsDeclsVO).getReturnList();
			request.setAttribute("creCrsDeclsList", creCrsDeclsList);

			// 평가 목록
			int curPage = Integer.parseInt(StringUtil.nvl(request.getParameter("curPage"),"1"));
			int listScale = Integer.parseInt(StringUtil.nvl(request.getParameter("listScale"),"20"));
			List<ExamStareVO> resultList = examService.listExamStare(vo).getReturnList();
			List<CommStatusVO> statusList = new ArrayList<CommStatusVO>();
			for(int i = 0; i <= 100; i++ ) {
				CommStatusVO csVO = new CommStatusVO();
				csVO.setKeyCode(i+"");
				csVO.setKeyValue(0+"");
				statusList.add(csVO);
			}
			for(ExamStareVO iesVO : resultList) {
				for(CommStatusVO csVO : statusList) {
					if(Math.round(iesVO.getTotGetScore()) == Integer.parseInt(csVO.getKeyCode())) {
						int keyValue = Integer.parseInt(csVO.getKeyValue()) + 1;
						csVO.setKeyValue(keyValue+"");
					}
				}

			}
			request.setAttribute("statusList", statusList);
			request.setAttribute("paging", "Y");
			returnUrl = "home/lecture/exam/teacher/exam_manage_rslt";		//평가현황
			*/

		} else{		//문제관리
			//문제 목록
			ExamQuestionVO examQuestionVO = new ExamQuestionVO();
			examQuestionVO.setCrsCreCd(vo.getCrsCreCd());
			examQuestionVO.setExamSn(vo.getExamSn());
			request.setAttribute("examQuestionListVO", examService.listQstn(examQuestionVO).getReturnList());
			returnUrl = "home/lecture/exam/teacher/exam_manage_qstn";
		}

		return returnUrl;
	}

	/**
	 * 시험 정보 수정
	 * @author twkim
	 * @date 2013. 11. 13.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	/*@RequestMapping(value="/editExam")
	public String editExam(ExamVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultVO<ExamVO> resultVO = new ProcessResultVO<>();
		try {
			ExamVO resultExamVO = new ExamVO();
			resultExamVO = examService.viewExam(vo).getReturnVO();
			
			if(resultExamVO == null) {
				throw new ServiceProcessException("수정하려는 시험이 조회되지 않습니다. 다시 수정바랍니다.");
			}
			
			if("ON".equals(StringUtil.nvl(resultExamVO.getExamTypeCd()))) {//온라인 시험의 경우 시험 문항, 배점 확인
				if(!StringUtil.nvl(vo.getRegYn()).equals(StringUtil.nvl(resultExamVO.getRegYn()))) {//공개 비공개 일치 여부
					throw new ServiceProcessException("수정 직전 시험의 공개 여부가 변경되었습니다. 다시 수정바랍니다.");
				}
				if(!vo.getIsCalCulateTotScore()) {
					throw new ServiceProcessException("시험 배점의 총합이 100이 아닙니다. 다시 수정바랍니다.");
				}
				
				if("Y".equals(resultExamVO.getRegYn()) && resultExamVO.getIsOtherExamSelShortDesDiff(vo)) {//시험 공개 상태에서 기존 시험과 시험 갯수나 배점이 다르다면, 수정 불가  
					throw new ServiceProcessException("공개 상태에서는 시험문항 수와 배점을 변경하실 수 없습니다.");
				}
			}
			
			resultVO = examService.editExam(vo);
			
			if(resultVO.getResult() > 0) {
				resultVO.setMessage(getMessage(request, "lecture.message.exam.edit.success"));
			} else {
				resultVO.setMessage(getMessage(request, "lecture.message.exam.edit.failed"));
			}
		} catch(MediopiaDefineException e1) {
			resultVO.setResultFailed();
			resultVO.setMessage(e1.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(getMessage(request, "lecture.message.exam.edit.failed"));
		}

		setAlertMessage(request, resultVO.getMessage());
		
		// 읽기 화면으로
		return "redirect:"+ new URLBuilder("/lec/exam/editFormExamMain")
					.addParameter("examSn", vo.getExamSn())
					.addParameter("crsCreCd", vo.getCrsCreCd())
					.toString();

	}*/

	/**
	 * 시험 정보 삭제
	 * @author twkim
	 * @date 2013. 11. 13.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	/*@RequestMapping(value="/deleteExam")
	public String deleteExam(ExamVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		
		try {
			examService.deleteExam(vo);
		} catch (Exception e) {
			e.printStackTrace();
			setAlertMessage(request, getMessage(request, "lecture.message.exam.delete.failed"));
			return "redirect:"+ new URLBuilder("/lec/exam/editFormExamMain")
						.addParameter("examSn", vo.getExamSn())
						.toString();
		}

		setAlertMessage(request, getMessage(request, "lecture.message.exam.delete.success"));
		// 읽기 화면으로
		return "redirect:"+ new URLBuilder("/lec/exam/examMain")
					.toString();
	}*/
	
	@RequestMapping(value="/editExam")
	@ResponseBody
	public String editExam( ExamVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		ProcessResultVO<ExamVO> resultVO = new ProcessResultVO<>();
		
		try {
			ExamVO resultExamVO = new ExamVO();
			resultExamVO = examService.viewExam(vo).getReturnVO();
			
			if(resultExamVO == null) {
				throw new ServiceProcessException("수정하려는 시험이 조회되지 않습니다. 다시 수정바랍니다.");
			}
			
			if("ON".equals(StringUtil.nvl(resultExamVO.getExamTypeCd()))) {//온라인 시험의 경우 시험 문항, 배점 확인
				if(!StringUtil.nvl(vo.getRegYn()).equals(StringUtil.nvl(resultExamVO.getRegYn()))) {//공개 비공개 일치 여부
					throw new ServiceProcessException("수정 직전 시험의 공개 여부가 변경되었습니다. 다시 수정바랍니다.");
				}
				if(!vo.getIsCalCulateTotScore()) {
					throw new ServiceProcessException("시험 배점의 총합이 100이 아닙니다. 다시 수정바랍니다.");
				}
				if("Y".equals(resultExamVO.getRegYn()) && resultExamVO.getIsOtherExamSelShortDesDiff(vo)) {//시험 공개 상태에서 기존 시험과 시험 갯수나 배점이 다르다면, 수정 불가  
					throw new ServiceProcessException("공개 상태에서는 시험문항 수와 배점을 변경하실 수 없습니다.");
				}
			}
			
			resultVO = examService.editExam(vo);
			if(resultVO.getResult() > 0) {
				resultVO.setMessage(getMessage(request, "lecture.message.exam.edit.success"));
			} else {
				resultVO.setMessage(getMessage(request, "lecture.message.exam.edit.failed"));
			}
		} catch (MediopiaDefineException e1) {
			resultVO.setMessage(e1.getMessage());
			resultVO.setResultFailed();
		} catch (Exception e) {
			resultVO.setMessage(getMessage(request, "lecture.message.exam.edit.failed"));
			resultVO.setResultFailed();
		}
		
		return JsonUtil.responseJson(response, resultVO);
	}
	
	@RequestMapping(value="/deleteExam")
	@ResponseBody
	public String deleteExamJson( ExamVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultVO<ExamVO> resultVO = new ProcessResultVO<ExamVO>();
		try {
			ExamVO returnExamVO = examService.viewExam(vo).getReturnVO();
			if(returnExamVO == null) {
				throw new ServiceProcessException("시험이 조회되지 않습니다.");
			}
			if(returnExamVO.getStareCnt() > 0) {
				throw new ServiceProcessException("응시한 수강생이 있습니다. 시험을 삭제할 수 없습니다.");
			}
			
			resultVO = examService.deleteExam(vo);
			resultVO.setMessage(getMessage(request, "lecture.message.exam.delete.success"));
		} catch(MediopiaDefineException e) {
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		} catch (Exception e) {
			resultVO.setResultFailed();
			resultVO.setMessage(getMessage(request, "lecture.message.exam.delete.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 시험 문제 등록 폼
	 * @author twkim
	 * @date 2013. 11. 15.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	@RequestMapping(value="/addFormQuestionPop")
	public String addFormQuestionPop(ExamQuestionVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		// 부모창 새로고침 여부
		String refreshYn = StringUtil.nvl(request.getParameter("refreshYn"),"N");
		request.setAttribute("refreshYn", refreshYn);

		List<SysCodeVO> codeList = sysCodeMemService.getSysCodeList("EXAM_QSTN_TYPE");
		request.setAttribute("qstnTypeList", codeList);

		String returnUrl = this.getEditorType(request);

	   	request.setAttribute("gubun", "A");
	   	request.setAttribute("vo", vo);

		return returnUrl;
	}

	/**
	 * 시험 문제 등록
	 * @author twkim
	 * @date 2013. 11. 19.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	@RequestMapping(value="/addQuestion")
	@ResponseBody
	public String addQuestion(ExamQuestionVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		ProcessResultVO<ExamVO> resultVO = new ProcessResultVO<ExamVO>();
		// 스크립트, 스타일 태그 제거
		vo.setQstnCts(HtmlCleaner.cleanScript(vo.getQstnCts()) );

		try {
			examService.addQstn(vo);
		} catch (Exception e) {
			e.printStackTrace();
			setAlertMessage(request, getMessage(request,"lecture.message.exam.question.add.failed"));
			return "redirect:"+ new URLBuilder("/lec/exam/addFormQuestionPop")
						.addParameter("crsCreCd", vo.getCrsCreCd())
						.addParameter("examSn", vo.getExamSn())
						.toString();
		}

		//setAlertMessage(request, getMessage(request, "lecture.message.exam.question.add.success"));
		resultVO.setMessage(getMessage(request,  "lecture.message.exam.question.add.success"));
		// 읽기 화면으로
		/*return "redirect:"+ new URLBuilder("/lec/exam/addFormQuestionPop")
				.addParameter("crsCreCd", vo.getCrsCreCd())
				.addParameter("examSn", vo.getExamSn())
				.addParameter("refreshYn", "N")
				.toString();*/
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 시험 문제 수정 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/editQuestionPop")
	public String editQuestionPop(ExamQuestionVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);


		List<SysCodeVO> codeList = sysCodeMemService.getSysCodeList("EXAM_QSTN_TYPE");
		request.setAttribute("qstnTypeList", codeList);

		// 부모창 새로고침 여부
		String refreshYn = StringUtil.nvl(request.getParameter("refreshYn"),"N");
		request.setAttribute("refreshYn", refreshYn);


		//-- 시험 문제 정보를 찾아 Form에 셋팅
		vo = examService.viewQstn(vo).getReturnVO();
	   	request.setAttribute("vo", vo);

		String returnUrl = this.getEditorType(request);
	   	
	   	request.setAttribute("gubun", "E");


		return returnUrl;
	}

	/**
	 * 시험 문제 수정
	 * @author twkim
	 * @date 2013. 11. 19.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	@RequestMapping(value="/editQuestion")
	@ResponseBody
	public String editQuestion(ExamQuestionVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		ProcessResultVO<ExamVO> resultVO = new ProcessResultVO<ExamVO>();
		// 스크립트, 스타일 태그 제거
		vo.setQstnCts(HtmlCleaner.cleanScript(vo.getQstnCts()) );

		try {
			examService.editQstn(vo);
		} catch (Exception e) {
			e.printStackTrace();
			setAlertMessage(request, getMessage(request, "lecture.message.exam.question.edit.failed"));
			return "redirect:"+ new URLBuilder("/lec/exam/editQuestionPop")
						.addParameter("crsCreCd", vo.getCrsCreCd())
						.addParameter("examSn", vo.getExamSn())
						.addParameter("examQstnSn", vo.getExamQstnSn())
						.toString();
		}
		resultVO.setMessage(getMessage(request, "lecture.message.exam.question.edit.success"));
		// 읽기 화면으로
		/*return "redirect:"+ new URLBuilder("/lec/exam/editQuestionPop")
				.addParameter("crsCreCd", vo.getCrsCreCd())
				.addParameter("examSn", vo.getExamSn())
				.addParameter("examQstnSn", vo.getExamQstnSn())
				.addParameter("refreshYn", "Y")
				.toString();*/
		return JsonUtil.responseJson(response, resultVO);

	}

	/**
	 * 시험 문제 삭제
	 * @author twkim
	 * @date 2013. 11. 19.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	@RequestMapping(value="/deleteQuestion")
	public String deleteQuestion(ExamQuestionVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultVO<ExamQuestionVO> resultVO = examService.deleteQstn(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "lecture.message.exam.question.delete.success"));
		} else {
			resultVO.setMessage(getMessage(request, "lecture.message.exam.question.delete.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);

	}



	/**
	 * 시험 문제 등록
	 * @author twkim
	 * @date 2013. 11. 14.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	@RequestMapping(value="/addQbankQuestion")
	public String addQbankQuestion(ExamQuestionVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultVO<ExamQuestionVO> resultVO = examService.addQbank(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "lecture.message.exam.question.add.success"));
		} else {
			resultVO.setMessage(getMessage(request, "lecture.message.exam.question.add.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 시험 응시생 목록 조회
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/listStare")
	public String listStare(ExamVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		int curPage = Integer.parseInt(StringUtil.nvl(request.getParameter("curPage"),"1"));
		int listScale = Integer.parseInt(StringUtil.nvl(request.getParameter("listScale"),"20"));

		vo.setCurPage(curPage);
		vo.setListScale(listScale);
		vo.setPageScale(10);
		ProcessResultListVO<ExamStareVO> resultListVO = examService.listExamStarePaging(vo);

		return JsonUtil
			.responseJson(response, resultListVO);
	}

	/**
	 * 시험 평가 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/editFormRatePop")
	public String editFormRatePop(ExamStareVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);


		ExamVO examVO = new ExamVO();
		examVO.setCrsCreCd(UserBroker.getCreCrsCd(request));
		examVO.setExamSn(vo.getExamSn());
		examVO = examService.viewExam(examVO).getReturnVO();
	   	request.setAttribute("examVO", examVO);

		//-- 학습자의 답을 검색해 온다.
	   	ExamStareVO strStareVO = new ExamStareVO();
	   	
	   	try {
	   		strStareVO = examService.viewStareNoCont(vo).getReturnVO();
	   		if(strStareVO == null) {
	   			throw new ServiceProcessException("수강생이 제출 완료한 시험만 평가가 가능합니다.");
	   		}
	   		String stdQstnNos = StringUtil.nvl(strStareVO.getQstnNos());
			if("".equals(StringUtil.nvl(strStareVO.getEndDttm()))) {
				throw new ServiceProcessException("수강생이 제출 완료한 시험만 평가가 가능합니다.");
			}
			if("".equals(stdQstnNos) || "@#".equals(stdQstnNos)) {
				throw new ServiceProcessException("수강생의 시험 문항 정보가 없습니다. 재시험설정을 통해 초기화 바랍니다.");
			}
		} catch (MediopiaDefineException e1) {
			model.addAttribute("MESSAGE", e1.getMessage());
			model.addAttribute("modelCloseYn", "Y");
			return "common/message";
		}
	   	
		
		String strExamQstnNo = strStareVO.getQstnNos().substring(2, strStareVO.getQstnNos().length()-2);
		String strStareAnsrs = "";
		if(StringUtil.isNotNull(strStareVO.getStareAnss())) strStareAnsrs = strStareVO.getStareAnss().substring(2, strStareVO.getStareAnss().length()-2);
		String strGetScores = "";
		if(StringUtil.isNotNull(strStareVO.getGetScores())) strGetScores = strStareVO.getGetScores().substring(2, strStareVO.getGetScores().length()-2);

		String[] examQstnNo = strExamQstnNo.split("@#");
		String[] stareAnsr = strStareAnsrs.split("@#");
		String[] getScore = strGetScores.split("@#");

		Map<Integer, Map<String, String>> parentMap = new Hashtable<Integer, Map<String,String>>();
		for(int i=0; i < examQstnNo.length; i++) {
			Map<String, String> childMap = new Hashtable<String, String>();
			String stdAnsr = "";
			if(stareAnsr.length > i) stdAnsr = stareAnsr[i];
			childMap.put("answer", stdAnsr);
			if(getScore.length > i) {
				childMap.put("score", StringUtil.nvl(getScore[i],"0"));
			} else {
				childMap.put("score", "0");
			}
			parentMap.put(Integer.parseInt(examQstnNo[i]), childMap);
		}
		request.setAttribute("stareInfo", parentMap);
	   	request.setAttribute("vo", strStareVO);

		//-- 시험 문제 목록을 검색해 온다.
		ExamQuestionVO examQuestionVO = new ExamQuestionVO();
		examQuestionVO.setCrsCreCd(strStareVO.getCrsCreCd());
		examQuestionVO.setExamSn(strStareVO.getExamSn());
		examQuestionVO.setStrExamQstnSn(strStareVO.getQstnNos());
		examQuestionVO.setGubun("RANDOM");

		List<ExamQuestionVO> questionList = examService.listQstn(examQuestionVO).getReturnList();
		//문제의 점수가 아닌 수강생이 응시한 데이터의 시험 문항 성적을 세팅
		String examQstnScores = strStareVO.getQstnScores();
		String[] getQstnScoreArr = StringUtil.split(StringUtil.substring(examQstnScores, 2, examQstnScores.length() - 2),"@#");//수강생이 응시한 시험 문항의 점수(응시할 때 기준)
		
		for(int i = 0; i < questionList.size(); i++) {
			for(int j=0; j < examQstnNo.length; j++ ) {
				if(String.valueOf(questionList.get(i).getExamQstnSn()).equals(examQstnNo[j])) {
					double score = 0;
					if("".equals(StringUtil.nvl(getQstnScoreArr[j]))) {
						score = 0;
					}else {
						try {
							score = Double.parseDouble(getQstnScoreArr[j]);
						} catch (NumberFormatException e) {
							score = 0;
						}
					}
					questionList.get(i).setQstnScore(score);
				}
			}
		}
		
		for(ExamQuestionVO qstnList : questionList) {
			qstnList.setQstnCts(HtmlCleaner.cleanTag(qstnList.getQstnCts()));
		}
		request.setAttribute("questionList", questionList);

		//-- 학습자 정보 조회
		StudentVO studentVO = new StudentVO();
		studentVO.setStdNo(vo.getStdNo());
		studentVO = studentService.viewStudent(studentVO).getReturnVO();

		request.setAttribute("studentVO", studentVO);

		return "home/lecture/exam/teacher/exam_rate_write_pop";
	}


	/**
	 * 시험결과 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/viewRatePop")
	public String viewRate(ExamStareVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		//시험정보 가져오기
		ExamVO examVO = new ExamVO();
		examVO.setCrsCreCd(vo.getCrsCreCd());
		examVO.setExamSn(vo.getExamSn());
		examVO = examService.viewExam(examVO).getReturnVO();
		
		ExamStareVO strStareVO = new ExamStareVO();
		
		try {
			if("R".equals(examVO.getExamStareTypeCd())){
				boolean nowScoreOpen = DateTimeUtil.chkDateTimeNowAfter(examVO.getRsltCfrmDttm());
				if(!nowScoreOpen) {
					throw new ServiceProcessException("결과 확인 가능 기간이 아닙니다.");
				}
			}
			
		   	request.setAttribute("examVO", examVO);

			//-- 학습자의 답을 검색해 온다.
		   	strStareVO = examService.viewStareNoCont(vo).getReturnVO();
		   	
		   	if(strStareVO == null) {
		   		throw new ServiceProcessException("수강생 시험 응시 정보가 없습니다. 다시 시도바랍니다. 반복되는 경우 관리자에게 문의바랍니다.");
		   	}
		   	
		   	if(ValidationUtils.isEmpty(strStareVO.getEndDttm())) {
		   		throw new ServiceProcessException("최종 제출한 응시 내역이 존재하지 않습니다. 관리자에게 문의바랍니다.");
		   	}
		   	
		   	if(!"Y".equals(StringUtil.nvl(strStareVO.getRateYn()))) {
		   		throw new ServiceProcessException("응시한 시험에 대해 평가가 진행중입니다.");
		   	}
		   	
			//온라인 시험일 경우
			if("ON".equals(examVO.getExamTypeCd())){
				String strExamQstnNo = "";
				String strStareAnsrs = "";
				String strGetScores = "";
				if(ValidationUtils.isNotEmpty(strStareVO.getQstnNos())) {
					strExamQstnNo = strStareVO.getQstnNos().substring(2, strStareVO.getQstnNos().length()-2);
				}
				if(ValidationUtils.isNotEmpty(strStareVO.getStareAnss())) {
					strStareAnsrs = strStareVO.getStareAnss().substring(2, strStareVO.getStareAnss().length()-2);
				}
				if(ValidationUtils.isNotEmpty(strStareVO.getGetScores())) {
					strGetScores = strStareVO.getGetScores().substring(2, strStareVO.getGetScores().length()-2);
				}

				String[] examQstnNo = strExamQstnNo.split("@#");
				String[] stareAnsr = strStareAnsrs.split("@#");
				String[] getScore = strGetScores.split("@#");

				Map<Integer, Map<String, String>> parentMap = new Hashtable<Integer, Map<String,String>>();
				for(int i=0; i < examQstnNo.length; i++) {
					Map<String, String> childMap = new Hashtable<String, String>();
					//2015.11.05 김현욱수정 null로 인한 오류 방지
					if(stareAnsr !=null && stareAnsr.length >= (i+1)){
						childMap.put("answer", StringUtil.nvl(stareAnsr[i],""));
					}else{
						childMap.put("answer", "");
					}
					if(getScore !=null && getScore.length >= (i+1)){
						childMap.put("score", StringUtil.nvl(getScore[i],"0"));
					}else{
						childMap.put("score", "0");
					}
	/*				childMap.put("answer", stareAnsr[i]);
					childMap.put("score", StringUtil.nvl(getScore[i],"0"));*/

					parentMap.put(Integer.parseInt(examQstnNo[i]), childMap);
				}
				request.setAttribute("stareInfo", parentMap);


				//-- 시험 문제 목록을 검색해 온다.
				ExamQuestionVO examQuestionVO = new ExamQuestionVO();
				examQuestionVO.setCrsCreCd(strStareVO.getCrsCreCd());
				examQuestionVO.setExamSn(strStareVO.getExamSn());
				examQuestionVO.setStrExamQstnSn(strStareVO.getQstnNos());
				examQuestionVO.setGubun("RANDOM");

				List<ExamQuestionVO> questionList = examService.listQstn(examQuestionVO).getReturnList();
				
				if(questionList == null || questionList.size() == 0) {
					throw new ServiceProcessException("응시한 시험 문항이 조회되지 않습니다. 다시 시도바랍니다. 반복되는 경우 관리자에게 문의바랍니다.");
				}
				
				//문제의 점수가 아닌 수강생이 응시한 데이터의 시험 문항 성적을 세팅
				String examQstnScores = strStareVO.getQstnScores();
				String[] getQstnScoreArr = StringUtil.split(StringUtil.substring(examQstnScores, 2, examQstnScores.length() - 2),"@#");//수강생이 응시한 시험 문항의 점수(응시할 때 기준)
				
				for(int i = 0; i < questionList.size(); i++) {
					for(int j=0; j < examQstnNo.length; j++ ) {
						if(String.valueOf(questionList.get(i).getExamQstnSn()).equals(examQstnNo[j])) {
							double score = 0;
							if("".equals(StringUtil.nvl(getQstnScoreArr[j]))) {
								score = 0;
							}else {
								try {
									score = Double.parseDouble(getQstnScoreArr[j]);
								} catch (NumberFormatException e) {
									score = 0;
								}
							}
							questionList.get(i).setQstnScore(score);
						}
					}
				}
				
				request.setAttribute("questionList", questionList);
			}
		} catch (MediopiaDefineException e1) {
			model.addAttribute("MESSAGE", e1.getMessage());
			model.addAttribute("modalCloseYn", "Y");
			model.addAttribute("parentReloadYn", "Y");
			log.error("[시험결과 조회 오류]");
			return "common/message";
		} catch (Exception e) {
			model.addAttribute("MESSAGE", "결과 조회도중 문제가 발생하였습니다. 반복되는 경우 관리자에게 문의바랍니다.");
			model.addAttribute("modalCloseYn", "Y");
			model.addAttribute("parentReloadYn", "Y");
			log.error("[시험결과 조회 오류]");
			return "common/message";
		}
		
	   	request.setAttribute("vo", strStareVO);
		//-- 학습자 정보 조회
		StudentVO studentVO = new StudentVO();
		studentVO.setStdNo(strStareVO.getStdNo());
		studentVO = studentService.viewStudent(studentVO).getReturnVO();

		request.setAttribute("studentVO", studentVO);

		return "home/lecture/exam/exam_rate_pop";
	}

	/**
	 * 시험 평가 등록
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/addStareRate")
	public String addStareRate(ExamStareVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultVO<ExamStareVO> resultVO = examService.addStareRate(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "lecture.message.exam.rate.success"));
		} else {
			resultVO.setMessage(getMessage(request, "lecture.message.exam.rate.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 시험 점수 개별 등록
	 * @author twkim
	 * @date 2013. 11. 21.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	@ResponseBody
	@RequestMapping(value="/addStareScore")
	public ProcessResultVO<ExamStareVO> addStareScore(ExamStareVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultVO<ExamStareVO> resultVO = new ProcessResultVO<>();
		try {
			ExamStareVO returnVO = examService.viewStareNoCont(vo).getReturnVO();
			vo.setRateYn("Y");
			if(returnVO == null) {
				vo.setStareCnt(1);
				resultVO = examService.addStareScore(vo);
			}else {
				resultVO = examService.editStareScore(vo);
			}
			resultVO.setMessage(getMessage(request, "lecture.message.exam.stare.add.success"));
		} catch (Exception e) {
			resultVO.setMessage(getMessage(request, "lecture.message.exam.stare.add.failed"));
			resultVO.setResultFailed();
		}
		return resultVO;
	}

	/**
	 * 시험 점수 일괄 등록
	 * @author twkim
	 * @date 2013. 11. 21.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	@RequestMapping(value="/addStareScoreAll")
	public String addStareScoreAll(ExamVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ExamStareVO examStareVO = new ExamStareVO();

		// examVO 정보를 examStareVO에 병합시킨다.
		BeanUtils.mergeBean(examStareVO, vo);

		String strStdNo = request.getParameter("strStdNo");
		String strScore = request.getParameter("strScore");

		examStareVO.setStareCnt(1); //-- 응시 횟수를 늘림.

		ProcessResultVO<ExamStareVO> resultVO = new ProcessResultVO<ExamStareVO>();
		try {
			String[] strStdNoArray = StringUtil.split(strStdNo,"|");
			String[] strScoreArray = StringUtil.split(strScore,"|");
			if( strStdNoArray == null || strStdNoArray.length == 0 || "".equals(strStdNo) || strScoreArray == null || strScoreArray.length == 0 || "".equals(strScore)) {
				throw new ServiceProcessException("성적 저장할 수강생이 없습니다.");
			}
			examStareVO.setStareCnt(1);
			examStareVO.setRateYn("Y");
			examService.addStareScoreAll(examStareVO, strStdNoArray, strScoreArray);
			resultVO.setMessage(getMessage(request, "lecture.message.exam.stare.add.success"));
		} catch (MediopiaDefineException e) {
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		} catch (Exception e) {
			resultVO.setResultFailed();
			resultVO.setMessage(getMessage(request, "lecture.message.exam.stare.add.failed"));
		}
		setAlertMessage(request, resultVO.getMessage());
		// 읽기 화면으로
		return "redirect:"+ new URLBuilder("/lec/exam/editFormExamMain")
					.addParameter("examSn", examStareVO.getExamSn())
					.addParameter("crsCreCd", vo.getCrsCreCd())
					.toString();

	}

	/**
	 * 문제은행 보기 폼
	 * @author twkim
	 * @date 2013. 11. 13.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	@RequestMapping(value="/editQbankPop")
	public String editQbankPop(ExamVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		request.setAttribute("vo", vo);
		request.setAttribute("paging", "Y");

		return "home/lecture/exam/teacher/exam_qbank_write_pop";
	}

	/**
	 * 시험 약관 동의 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/agreePop")
	public String agreePop(ExamStareVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String orgCd = UserBroker.getUserOrgCd(request);
		
		OrgPageVO pageVO = new OrgPageVO();
		pageVO.setOrgCd(orgCd);

		// 시험응시 약관
		pageVO.setPageCd("PAGE040");
		OrgPageVO orgTermsVO = orgPageService.view(pageVO);
		request.setAttribute("orgTermsVO", orgTermsVO);

	   	request.setAttribute("vo", vo);

		return "home/lecture/exam/exam_agree_pop";
	}
	
	

	/**
	 * 시험 응시 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addPaperPop")
	public String addPaperPop(ExamStareVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String returnUrl = "home/lecture/exam/exam_paper_pop";
		
		ExamVO examVO = new ExamVO();
		examVO.setExamSn(vo.getExamSn());
		examVO.setCrsCreCd(UserBroker.getCreCrsCd(request));
		examVO = examService.viewExam(examVO).getReturnVO();
		request.setAttribute("examVO", examVO);
		
		String errorMsgCd = "";
		String errorMsg = "";
		ExamStareVO examStareVO = null;
		List<ExamQuestionVO> questionList = new ArrayList<>();
		try {
			//시험이 존재하는지 확인
			if(examVO == null) {
				throw new ServiceProcessException("시험이 조회되지 않습니다. 새로고침 후 다시 시도바랍니다. 반복되는 경우 관리자에게 문의바랍니다.");
			}
			//등록된 시험인지 확인
			if(!"Y".equals(examVO.getRegYn())) {
				throw new ServiceProcessException("등록된 시험이 아닙니다. 새로고침 후 다시 시도바랍니다. 반복되는 경우 관리자에게 문의바랍니다.");
			}
			//시험의 문제 갯수 설정되어있는지 확인
			if(examVO.getSumSelShortDesCnt() == 0) {
				throw new ServiceProcessException("시험 문제 제출 개수가 0 입니다. 관리자에게 문의바랍니다.");
			}
			//시험 시험 배점의 총합 100인지 확인
			if(!examVO.getIsCalCulateTotScore()) {
				throw new ServiceProcessException("시험 문항이 비정상적입니다. 새로고침 후 다시 시도바랍니다. 반복되는 경우 관리자에게 문의바랍니다.");
			}
			
			//add한 후 시험 리스트 세팅 올바른지 여부?? 확인
			
			//정규시험일 경우에만 기간 체크 
			if("R".equals(examVO.getExamStareTypeCd())){
				boolean examOpen = DateTimeUtil.chkTimeNowBetween(examVO.getExamStartDttm(), examVO.getExamEndDttm());
				if(!examOpen) {
					//기간에 해당되지 않는 경우도 있지만, 시험의 시작 종료 일자가 올바르지 않을 경우도 있음.
					throw new ServiceProcessException("시험 응시 기간에 해당되지 않습니다. 반복되는 경우 관리자에게 문의바랍니다.");
				}
			}//상시시험의 기간 체크?
			
			
			vo.setRegIp(request.getRemoteAddr());//[매뉴얼] 시험 응시할 때, IP 등록
			vo.setStartFlagYn("Y");
			
			examStareVO = examService.viewStareNoCont(vo).getReturnVO();
			if(examStareVO == null) {//첫 시험 입장
				//vo.setExamSetCnt(examVO.getExamSetCnt());
				
				//첫 시험 문제 조회
				ExamQuestionVO examQuestionVO = new ExamQuestionVO();
				examQuestionVO.setCrsCreCd(vo.getCrsCreCd());
				examQuestionVO.setExamSn(vo.getExamSn());
				List<ExamQuestionVO> firstQuestionList = examService.randListQstnType(examQuestionVO, examVO).getReturnList();
				
				if(firstQuestionList == null || firstQuestionList.size() == 0) {
					throw new ServiceProcessException("조회된 시험 문항이 없습니다. 다시 응시바랍니다. 반복되는 경우 관리자에게 문의바랍니다.");
				}
				
				examStareVO = examService.addPaper(vo, examVO, firstQuestionList).getReturnVO();
				vo = examStareVO;
			}else {//두 번째 시험 입장
				
				//응시 횟수 체크
				if(examVO.getStareLimitCnt() <= StringUtil.nvl(examStareVO.getStareCnt(), 0)) {//StareCnt Integer
					throw new ServiceProcessException("시험 응시 횟수를 초과하였습니다. 시험에 응시할 수 없습니다.");
				}
				
				//시험 제출 여부 확인 
				if(ValidationUtils.isNotEmpty(examStareVO.getEndDttm())) {
					throw new ServiceProcessException("시험을 이미 응시하였습니다. 재응시는 불가합니다.");
				}

				//평가 여부
				if("Y".equals(StringUtil.nvl(examStareVO.getRateYn()))) {
					throw new ServiceProcessException("시험의 평가가 완료되었습니다. 시험에 응시할 수 없습니다.");
				}
				
				vo = examStareVO;
				vo.setStareCnt(vo.getStareCnt() + 1);
				vo.setStareTm(vo.getStareTm()*60);
				vo.setStartFlagYn("Y");
				vo.setRegNo(UserBroker.getUserNo(request));//HRD 성적이력 데이터 등록 시, regNo 등록
				vo.setRegIp(request.getRemoteAddr());//[매뉴얼] 시험 응시할 때, IP 등록
				vo.setModNo(UserBroker.getUserNo(request));
				examService.addPaperSubmitStart(vo, examVO);
			}
			
			//시험기본정보 입력
			ExamQuestionVO examQuestionVO = new ExamQuestionVO();
			examQuestionVO.setCrsCreCd(vo.getCrsCreCd());
			examQuestionVO.setExamSn(vo.getExamSn());
			examQuestionVO.setStrExamQstnSn(StringUtil.nvl(vo.getQstnNos()));
			examQuestionVO.setGubun("RANDOM");

			questionList = examService.listQstn(examQuestionVO).getReturnList();
			
			//조회한 시험 문제가 없는 경우
			if(questionList == null || questionList.size() == 0) {
				throw new ServiceProcessException("시험 문제가 조회되지 않습니다. 반복되는 경우 관리자에게 문의바랍니다.");
			}
			
			//재시험의 경우 문제 데이터가 세팅되어 있으므로, 첫 응시할 때만 확인 / 조회한 시험 문제 갯수와 시험관리의 문제 갯수 일치 여부
			if(examStareVO.getStareCnt() == 1 && questionList.size() != examVO.getSumSelShortDesCnt()) {
				throw new ServiceProcessException("조회한 시험 문제가 올바르지 않습니다. 다시 시험에 응시바랍니다. 반복되는 경우 관리자에게 문의바랍니다.");
			}
			
			//문제의 점수가 아닌 수강생이 응시한 데이터의 시험 문항 성적을 세팅
			String examQstnNos = examStareVO.getQstnNos();
			String examQstnScores = examStareVO.getQstnScores();
			String[] examQstnNoArr = StringUtil.split(StringUtil.substring(examQstnNos, 2, examQstnNos.length() - 2),"@#");
			String[] getQstnScoreArr = StringUtil.split(StringUtil.substring(examQstnScores, 2, examQstnScores.length() - 2),"@#");//수강생이 응시한 시험 문항의 점수(응시할 때 기준)
			
			for(int i = 0; i < questionList.size(); i++) {
				for(int j=0; j < examQstnNoArr.length; j++ ) {
					if(String.valueOf(questionList.get(i).getExamQstnSn()).equals(examQstnNoArr[j])) {
						double score = 0;
						if("".equals(StringUtil.nvl(getQstnScoreArr[j]))) {
							score = 0;
						}else {
							try {
								score = Double.parseDouble(StringUtil.nvl(getQstnScoreArr[j],"0"));
							} catch (NumberFormatException e) {
								score = 0;
							}
						}
						questionList.get(i).setQstnScore(score);
					}
				}
			}
			
		} catch (MediopiaDefineException e1) {
			log.error("[시험 응시하기 오류] 메시지 : " + e1.getMessage());
			model.addAttribute("MESSAGE", e1.getMessage());
			model.addAttribute("modelCloseYn", "Y");
			model.addAttribute("parentReloadYn", "Y");
			return "common/message";
		} catch (Exception ex) {
			log.error("[시험 응시하기 오류] 시험 조회, 등록, 수정 오류");
			model.addAttribute("MESSAGE", "시험 조회 오류입니다. 시험에 응시할 수 없습니다. 반복되는 경우 관리자에게 문의바랍니다.");
			model.addAttribute("modelCloseYn", "Y");
			model.addAttribute("parentReloadYn", "Y");
			return "common/message";
		}
		
		request.setAttribute("errorMsgCd", errorMsgCd);
		request.setAttribute("errorMsg", errorMsg);
		request.setAttribute("questionList", questionList);

		//-- 학습자 정보 조회
		StudentVO studentVO = new StudentVO();
		studentVO.setStdNo(vo.getStdNo());
		studentVO = studentService.viewStudent(studentVO).getReturnVO();
		request.setAttribute("studentVO", studentVO);

	   	request.setAttribute("vo", vo);
	   	request.setAttribute("examPaper", "Y");
	   	request.setAttribute("isOpen", true);

		return returnUrl;
	}

	
	

	/**
	 *
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/addPaperSubmit")
	public String addPaperSubmit(ExamStareVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		vo.setQstnNos(request.getParameter("examStareVO.stareAnss"));
		
		vo.setStartFlagYn("N");//종료
		vo.setRegIp(request.getRemoteAddr());
		
		ExamVO examVO = new ExamVO();
		examVO.setExamSn(vo.getExamSn());
		examVO.setCrsCreCd(vo.getCrsCreCd());
		examVO = examService.viewExam(examVO).getReturnVO();
		
		ProcessResultVO<ExamStareVO> resultVO = examService.addPaperSubmitEnd(vo, examVO);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "lecture.message.exam.answer.send.success"));
		} else {
			resultVO.setMessage(getMessage(request, "lecture.message.exam.answer.send.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}
	
	/**
	 * 임시저장
	 * @param vo
	 * @param commandMap
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/addPaperSubmitTemp")
	public String addPaperSubmitTemp(ExamStareVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		vo.setQstnNos(request.getParameter("examStareVO.stareAnss"));
		
		vo.setStartFlagYn("T");//임시저장 T
		vo.setRegIp(request.getRemoteAddr());
		
		ExamVO examVO = new ExamVO();
		examVO.setExamSn(vo.getExamSn());
		examVO.setCrsCreCd(vo.getCrsCreCd());
		examVO = examService.viewExam(examVO).getReturnVO();
		
		ProcessResultVO<ExamStareVO> resultVO = examService.addPaperSubmitEnd(vo, examVO);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "lecture.message.exam.answer.send.success"));
		} else {
			resultVO.setMessage(getMessage(request, "lecture.message.exam.answer.send.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 재시험 설정
	 * @author twkim
	 * @date 2013. 11. 21.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	@RequestMapping(value="/removeStare")
	public String removeStare(ExamVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultVO<ExamStareVO> resultVO = new ProcessResultVO<>();
		
		ExamStareVO examStareVO = vo.getExamStareVO();
		examStareVO.setCrsCreCd(vo.getCrsCreCd());
		examStareVO.setExamSn(vo.getExamSn());
		examStareVO.setUserNoObj(request.getParameter("userNoObj"));
		
		try {
			ExamVO examVO = new ExamVO();
			examVO.setExamSn(vo.getExamSn());
			examVO.setCrsCreCd(vo.getCrsCreCd());
			examVO = examService.viewExam(examVO).getReturnVO();
			
			if(examVO == null) {
				throw new ServiceProcessException("시험이 조회되지 않습니다. 다시 시도바랍니다.");
			}
			examStareVO.setSemiExamYn(examVO.getSemiExamYn());
			
			if(StringUtil.nvl(examStareVO.getUserNoObj()).equals("")) {
				throw new ServiceProcessException("선택된 수강생이 없습니다. 다시 시도바랍니다.");
			}
			
			String[] stdNoObjArr = examStareVO.getUserNoObj().split(",");
			if(stdNoObjArr == null || stdNoObjArr.length == 0) {
				throw new ServiceProcessException("선택된 수강생이 없습니다. 다시 시도바랍니다.");
			}
			
			//체크한 수강생 중 조회된 수강생만 delete 처리
			vo.setStdNoObjArr(stdNoObjArr);
			List<ExamStareVO> creExamStareList = examService.listExamStare(vo).getReturnList();
			
			if(creExamStareList == null || creExamStareList.size() == 0) {
				throw new ServiceProcessException("선택된 수강생이 조회되지 않습니다. 새로고침 후 다시 시도바랍니다.");
			}
			
			resultVO = examService.removeStare(examStareVO, creExamStareList);
			resultVO.setMessage(getMessage(request, "lecture.message.exam.stare.retest.success"));
		} catch (MediopiaDefineException e1) {
			resultVO.setResultFailed();
			resultVO.setMessage(e1.getMessage());
		} catch (Exception e) {
			resultVO.setResultFailed();
			resultVO.setMessage(getMessage(request, "lecture.message.exam.stare.retest.failed"));
		}
		
		setAlertMessage(request, resultVO.getMessage());
		
		// 읽기 화면으로
		return "redirect:"+ new URLBuilder("/lec/exam/editFormExamMain")
					.addParameter("examSn", vo.getExamSn())
					.addParameter("crsCreCd", vo.getCrsCreCd())
					.addParameter("gubun", "R")//평가관리 이동
					.toString();
	}

	/**
	 * 시험 평가 완료
	 * @author twkim
	 * @date 2013. 11. 21.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	@RequestMapping(value="/editExamComplete")
	public String editExamComplete(ExamStareVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultVO<ExamStareVO> resultVO = new ProcessResultVO<>();
		
		try {
			resultVO = examService.editExamComplete(vo);
			resultVO.setMessage(getMessage(request, "lecture.message.exam.rate.success"));
		} catch (Exception e) {
			//e.printStackTrace();
			resultVO.setMessage(getMessage(request, "lecture.message.exam.rate.failed"));
		}

		setAlertMessage(request, resultVO.getMessage());
		// 읽기 화면으로
		return "redirect:"+ new URLBuilder("/lec/exam/editFormExamMain")
					.addParameter("examSn", vo.getExamSn())
					.addParameter("crsCreCd", vo.getCrsCreCd())
					.addParameter("gubun", "R")//평가관리 이동
					.toString();
	}

	/**
	 * 문제 상세 보기 (학습자)
	 * @author twkim
	 * @date 2014. 1. 16.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public String readExam(ExamVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		request.setAttribute("examVO", examService.viewExam(vo).getReturnVO());

		return "home/lecture/exam/exam_read_pop";
	}

	/**
	 * 단답식, 주관식, 시험 평가 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/editRateDanPop")
	public String editRateDanPop(ExamVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String examQstnSn = StringUtil.nvl(request.getParameter("examQstnSn"),"");

		//-- 시험 문제 목록을 검색해 온다.
		ExamQuestionVO examQuestionVO = new ExamQuestionVO();
		examQuestionVO.setCrsCreCd(vo.getCrsCreCd());
		examQuestionVO.setExamSn(vo.getExamSn());

		examQuestionVO.setSearchType("JDRATE");//서술,단답형 응시자 수
		List<ExamQuestionVO> questionList = examService.listQstn(examQuestionVO).getReturnList();
		request.setAttribute("questionList", questionList);

		if("".equals(examQstnSn) && questionList.size() > 0) {
			for(ExamQuestionVO iieqVO : questionList) {
				if("D".equals(iieqVO.getQstnType()) || "J".equals(iieqVO.getQstnType()) ) {
					examQstnSn = Integer.toString(iieqVO.getExamQstnSn());
					break;
				}
			}
		}

		//-- 선택된 문제의 정보를 가져온다.
		for(ExamQuestionVO ieqVO : questionList) {
			if(examQstnSn.equals(Integer.toString(ieqVO.getExamQstnSn()))) {
				examQuestionVO = ieqVO;
			}
		}

		List<ExamStareVO> stareList = examService.listExamStare(vo).getReturnList();
		List<ExamStareVO> stuStareList = new ArrayList<ExamStareVO>();
		for(ExamStareVO iesVO : stareList) {
			String stdQstnNos = StringUtil.nvl(iesVO.getQstnNos());
			
			if("".equals(stdQstnNos) || "@#".equals(stdQstnNos)) {//수강생이 응시한 문항이 없는 경우 통과
				continue;
			}
			
			if(iesVO.getStareCnt() > 0 && ValidationUtils.isNotEmpty(iesVO.getEndDttm())) { //-- 응시 정보가 있는 경우만 셋팅한다.
				String strExamQstnNo = iesVO.getQstnNos().substring(2, iesVO.getQstnNos().length()-2);
				String strStareAnsrs = "";
				if(StringUtil.isNotNull(iesVO.getStareAnss())) strStareAnsrs = iesVO.getStareAnss().substring(2, iesVO.getStareAnss().length()-2);
				String strGetScores = "";
				if(StringUtil.isNotNull(iesVO.getGetScores())) strGetScores = iesVO.getGetScores().substring(2, iesVO.getGetScores().length()-2);

				String[] examQstnNo = StringUtil.split(strExamQstnNo,"@#");
				String[] stareAnsr = StringUtil.split(strStareAnsrs,"@#");
				String[] getScore = StringUtil.split(strGetScores,"@#");
				
				String strQstnScores = StringUtil.nvl(iesVO.getQstnScores(),"");
				if(!"".equals(strQstnScores)) strQstnScores = strQstnScores.substring(2,strQstnScores.length()-2);
				String[] qstnScore = StringUtil.split(strQstnScores, "@#");

				boolean qstnCheckFlag = false; 
				for(int i=0; i < examQstnNo.length; i++) {
					if(examQstnSn.equals(examQstnNo[i])) {
						if(!"".equals(strStareAnsrs)) iesVO.setRgtAnsr(StringUtil.nvl(stareAnsr[i],""));
						else iesVO.setRgtAnsr("");
						//if(!"".equals(strGetScores)) iesVO.setQstnScore(StringUtil.nvl(getScore[i],"0"));
						//else iesVO.setQstnScore("0");
						
						//examQuestionVO.setQstnScore(Double.parseDouble(qstnScore[i]));
						
						if("J".equals(examQuestionVO.getQstnType())) {
							/**
							 * 모사 조회 시작 (서술형만)
							 * pk : crs_cre_cd+"_"+user_id+"_"+std_no+"_"+exam_sn+"_"+qstn_nos
							 */
							//모사 조회 pk 11111111111111
							String copyRatioUri = iesVO.getCrsCreCd()+"_"+iesVO.getUserId()+"_"+iesVO.getStdNo()+"_"+vo.getExamSn()+"_" +examQstnSn;
							
							ExamStareVO copyVO = new ExamStareVO();
							copyVO.setCopyRatioUri(copyRatioUri);
							copyVO = examService.viewStareCopyRatio(copyVO).getReturnVO();
							if(copyVO != null) {
								iesVO.setCopyRatioUri(copyVO.getCopyRatioUri());
								iesVO.setDispTotalCopyRatio(copyVO.getDispTotalCopyRatio());
								iesVO.setCompleteStatus(copyVO.getCompleteStatus());
								iesVO.setCompleteDate(copyVO.getCompleteDate());
								int totalCopyRatio = copyVO.getTotalCopyRatio();
								if((totalCopyRatio > 0) == false ) {
									totalCopyRatio = 0;
								}
								iesVO.setTotalCopyRatio(copyVO.getTotalCopyRatio());
							}
						}
						
						if(!"".equals(strGetScores)) iesVO.setGetScore(StringUtil.nvl(getScore[i],"0"));
						else iesVO.setQstnScore("0");
						
						iesVO.setQstnScore(qstnScore[i]);
						iesVO.setExamSnChk("Y");
						qstnCheckFlag = true;
					}
				}
				if(qstnCheckFlag) {
					stuStareList.add(iesVO);
				}
			}
			request.setAttribute("stuStareList", stuStareList);
		}
		
		request.setAttribute("examQuestionVO", examQuestionVO);
		request.setAttribute("examQstnSn", examQstnSn);

		vo = examService.viewExam(vo).getReturnVO();
	   	request.setAttribute("vo", vo);

		return "home/lecture/exam/teacher/exam_rate_dan_write_pop";
	}

	/**
	 * 단답식,주관식 채점
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/editRateDan")
	public String editRateDan(ExamStareVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		vo.setRegIp(request.getRemoteAddr());
		ProcessResultVO<ExamStareVO> resultVO = examService.rateStareDan(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "lecture.message.exam.rate.janswer.success"));
		} else {
			resultVO.setMessage(getMessage(request, "lecture.message.exam.rate.janswer.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	private String getEditorType(HttpServletRequest request) {
		String returnUrl = "home/lecture/exam/teacher/exam_qstn_write_pop";
		//-- 에디터 사용여부를 판단하여 해당 Webeditor를 사용하도록 한다.
		//-- BBS_INFO의 editorUseYn은 더이상 사용하지 않음.
		if("Y".equals(Constants.WEBEDITOR_YSEYN)) {
			if("DAUMEDITOR".equals(Constants.WEBEDITOR_NAME)) {
				returnUrl = "home/lecture/exam/teacher/exam_qstn_write_daumeditor_pop";
				request.setAttribute("daumeditor", "Y");
			}else if("SUMMERNOTE".equals(Constants.WEBEDITOR_NAME)) {
				returnUrl = "home/lecture/exam/teacher/exam_qstn_write_summernote_pop";
				request.setAttribute("summernote", "Y");
			}
		}
		return returnUrl;
	}

	/**
	 * 학생 시험 정보 조회
	 * 2015.11.04 redmine #267(KNOTZ_NG_102) 추가 시험응시 정보를 취득한다.
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/examPaperStareCnt")
	public String examPaperStareCnt(ExamStareVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultVO<ExamStareVO> resultVO = null;
		try {
			resultVO = examService.viewStare(vo);
		} catch (Exception e) {
			e.printStackTrace();
			resultVO = new ProcessResultVO<ExamStareVO>();
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 시험문제 미리보기
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/previewPaperPop")
	public String previewPaperPop(ExamStareVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);


		ExamQuestionVO examQuestionVO = new ExamQuestionVO();
		examQuestionVO.setCrsCreCd(vo.getCrsCreCd());
		examQuestionVO.setExamSn(vo.getExamSn());
		examQuestionVO.setStrExamQstnSn(vo.getQstnNos());
		
		ExamVO paramExamVO = new ExamVO();
		paramExamVO.setExamSn(vo.getExamSn());
		paramExamVO.setCrsCreCd(vo.getCrsCreCd());
		
		ExamVO resultExamVO = new ExamVO();
		resultExamVO = examService.viewExam(paramExamVO).getReturnVO();
		
		List<ExamQuestionVO> questionList = new ArrayList<>();
		try {
			if(resultExamVO == null) {
				throw new ServiceProcessException("시험이 조회되지 않습니다. 다시 조회 바랍니다.");
			}
			if("OFF".equals(StringUtil.nvl(resultExamVO.getExamTypeCd()))) {
				throw new ServiceProcessException("오프라인 시험은 미리보기할 수 없습니다.");
			}
			if(!"Y".equals(resultExamVO.getRegYn())) {
				throw new ServiceProcessException("공개중인 시험만 미리보기 가능합니다.");
			}
			if("ON".equals(StringUtil.nvl(resultExamVO.getExamTypeCd()))) {//온라인 시험의 경우 시험 문항, 배점 확인
				if(!resultExamVO.getIsCalCulateTotScore()) {
					throw new ServiceProcessException("시험 배점의 총합이 100이 아닙니다. 미공개 후 시험관리에서 수정바랍니다.");
				}
			}
			questionList = examService.randListQstnType(examQuestionVO, resultExamVO).getReturnList();
			if(questionList == null || questionList.size() == 0) {
				throw new ServiceProcessException("미리보기 문제가 조회되지 않습니다. 미공개 후 문제 등록바랍니다. 반복되는 경우 관리자에게 문의바랍니다.");
			}
		} catch (MediopiaDefineException e1) {
			model.addAttribute("modelCloseYn", "Y");
			model.addAttribute("MESSAGE", e1.getMessage());
			log.error("[시험 미리보기 조회 오류] 메시지 : " + e1.getMessage());
			return "common/message";
		} catch (Exception e) {
			log.error("[시험 미리보기 조회 오류]");
			model.addAttribute("modelCloseYn", "Y");
			model.addAttribute("MESSAGE", "조회 도중 오류가 발생하였습니다. 반복되는 경우 관리자에게 문의바랍니다.");
			return "common/message";
		}
		
		request.setAttribute("questionList", questionList);
		request.setAttribute("examVO", resultExamVO);
		request.setAttribute("vo", vo);

		return "home/lecture/exam/teacher/exam_preview_pop";
	}


	/**
	 * 시험 문제 순서변경 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/sortQuestionPop")
	public String sortQuestionPop(ExamQuestionVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		List<ExamQuestionVO> examQuestionList = examService.listQstn(vo).getReturnList();

		request.setAttribute("examQuestionList", examQuestionList);
		request.setAttribute("vo", vo);

		return "home/lecture/exam/teacher/exam_qstn_sort_pop";
	}

	/**
	 * 시험 문제 순서변경
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/sortQuestion")
	public String sortQuestion(ExamQuestionVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultVO<ExamQuestionVO> resultVO = examService.editQstnSort(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "lecture.message.exam.question.edit.success"));
		} else {
			resultVO.setMessage(getMessage(request, "lecture.message.exam.question.edit.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
		/*
		try {
			examService.editQstnSort(examQuestionVO);
		} catch (Exception e) {
			e.printStackTrace();
			setAlertMessage(request, getMessage(request, "lecture", "lecture.message.exam.question.edit.failed"));
			return redirect(
					new URLBuilder("/lec/exam/sortQuestionPop")
						.addParameter("examQuestionVO.crsCreCd", examQuestionVO.getCrsCreCd())
						.addParameter("examQuestionVO.examSn", examQuestionVO.getExamSn())
						.toString()
			);
		}

		setAlertMessage(request, getMessage(request, "lecture", "lecture.message.exam.question.edit.success"));
		return redirect(
				new URLBuilder("/lec/exam/sortQuestionPop")
				.addParameter("examQuestionVO.crsCreCd", examQuestionVO.getCrsCreCd())
				.addParameter("examQuestionVO.examSn", examQuestionVO.getExamSn())
				.addParameter("refreshYn", "Y")
				.toString()
		);
		 */
	}

	/**
	 * 시험 정보 보기
	 * @author twkim
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	@RequestMapping(value="/viewExamPop")
	public String viewExamPop(ExamVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String gubun = vo.getGubun();

		//문제/평가 관리 url
		String returnUrl = "home/lecture/exam/exam_view_pop";

		vo.setCrsCreCd(UserBroker.getCreCrsCd(request));

		Integer declsNo = vo.getDeclsNo();
		String userNm = vo.getUserNm();

		//-- exam험의 정보를 가져온다.
		vo = examService.viewExam(vo).getReturnVO();

		//-- 분반과 사용자이름(목록 검색용 파라미터)셋팅
		vo.setDeclsNo(declsNo);
		vo.setUserNm(userNm);

		request.setAttribute("vo", vo);

		//시간 체크 사용여부 가져오기
		List<SysCodeVO> stareTmUseList = sysCodeMemService.getSysCodeList("STARE_TM_USE_YN");
		request.setAttribute("stareTmUseList", stareTmUseList);

		//문제 유형 가져오기
		List<SysCodeVO> examViewTypeList = sysCodeMemService.getSysCodeList("EXAM_VIEW_TYPE_CD");
		request.setAttribute("examViewTypeList", examViewTypeList);

		//사용 등록 여부 가져오기
		List<SysCodeVO> regYnList = sysCodeService.listCode("REG_YN").getReturnList();
		request.setAttribute("regYnList", regYnList);


		return returnUrl;
	}

	/**
	 * 시험별 성적차트
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	@RequestMapping(value="/examStatus")
	public String examStatus(ExamVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ExamStatusVO esVO = null;
		String stdNo = UserBroker.getStudentNo(request);
		if(vo.getExamSn() == 0 ){
			StdScoreVO ssVO = new StdScoreVO();
			ssVO.setCrsCreCd(vo.getCrsCreCd());
			List<StdScoreVO> stdScoreList = stdScoreService.list(ssVO).getReturnList();
			esVO = new ExamStatusVO();
			String myScore = "";
			for(StdScoreVO issVO : stdScoreList) {
				if(issVO.getExamScore() < 10) {
					esVO.setScoreZone00(esVO.getScoreZone00() + 1);
					if(stdNo.equals(issVO.getStdNo())) myScore = "0";
				} else if(issVO.getExamScore() < 20) {
					esVO.setScoreZone10(esVO.getScoreZone10() + 1);
					if(stdNo.equals(issVO.getStdNo())) myScore = "10";
				} else if(issVO.getExamScore() < 30) {
					esVO.setScoreZone20(esVO.getScoreZone20() + 1);
					if(stdNo.equals(issVO.getStdNo())) myScore = "20";
				} else if(issVO.getExamScore() < 40) {
					esVO.setScoreZone30(esVO.getScoreZone30() + 1);
					if(stdNo.equals(issVO.getStdNo())) myScore = "30";
				} else if(issVO.getExamScore() < 50) {
					esVO.setScoreZone40(esVO.getScoreZone40() + 1);
					if(stdNo.equals(issVO.getStdNo())) myScore = "40";
				} else if(issVO.getExamScore() < 60) {
					esVO.setScoreZone50(esVO.getScoreZone50() + 1);
					if(stdNo.equals(issVO.getStdNo())) myScore = "50";
				} else if(issVO.getExamScore() < 70) {
					esVO.setScoreZone60(esVO.getScoreZone60() + 1);
					if(stdNo.equals(issVO.getStdNo())) myScore = "60";
				} else if(issVO.getExamScore() < 80) {
					esVO.setScoreZone70(esVO.getScoreZone70() + 1);
					if(stdNo.equals(issVO.getStdNo())) myScore = "70";
				} else if(issVO.getExamScore() < 90) {
					esVO.setScoreZone80(esVO.getScoreZone80() + 1);
					if(stdNo.equals(issVO.getStdNo())) myScore = "80";
				} else if(issVO.getExamScore() < 100) {
					esVO.setScoreZone90(esVO.getScoreZone90() + 1);
					if(stdNo.equals(issVO.getStdNo())) myScore = "90";
				} else if(issVO.getExamScore() == 100) {
					esVO.setScoreZone100(esVO.getScoreZone100() + 1);
					if(stdNo.equals(issVO.getStdNo())) myScore = "100";
				}
			}
		} else {

			List<ExamStareVO> resultList = examService.listExamStare(vo).getReturnList();
			esVO = new ExamStatusVO();
			for(ExamStareVO iesVO : resultList) {
				if("Y".equals(iesVO.getRateYn())){
					if(iesVO.getTotGetScore() < 10) {
						esVO.setScoreZone00(esVO.getScoreZone00() + 1);
					} else if(iesVO.getTotGetScore() < 20) {
						esVO.setScoreZone10(esVO.getScoreZone10() + 1);
					} else if(iesVO.getTotGetScore() < 30) {
						esVO.setScoreZone20(esVO.getScoreZone20() + 1);
					} else if(iesVO.getTotGetScore() < 40) {
						esVO.setScoreZone30(esVO.getScoreZone30() + 1);
					} else if(iesVO.getTotGetScore() < 50) {
						esVO.setScoreZone40(esVO.getScoreZone40() + 1);
					} else if(iesVO.getTotGetScore() < 60) {
						esVO.setScoreZone50(esVO.getScoreZone50() + 1);
					} else if(iesVO.getTotGetScore() < 70) {
						esVO.setScoreZone60(esVO.getScoreZone60() + 1);
					} else if(iesVO.getTotGetScore() < 80) {
						esVO.setScoreZone70(esVO.getScoreZone70() + 1);
					} else if(iesVO.getTotGetScore() < 90) {
						esVO.setScoreZone80(esVO.getScoreZone80() + 1);
					} else if(iesVO.getTotGetScore() < 100) {
						esVO.setScoreZone90(esVO.getScoreZone90() + 1);
					} else {
						esVO.setScoreZone100(esVO.getScoreZone100() + 1);
					}
				} else {
					esVO.setScoreZone00(esVO.getScoreZone00() + 1);
				}
			}
		}
		//

		return JsonUtil.responseJson(response, esVO);
	}

	/**
	 * 시험 문제 수정 보기
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/viewQstnPop")
	public String viewQstnPop(ExamQuestionVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		//-- 시험 문제 정보를 찾아 Form에 셋팅
		vo = examService.viewQstn(vo).getReturnVO();

		request.setAttribute("vo", vo);
	   	request.setAttribute("gubun", "E");

	   	return "home/lecture/exam/teacher/exam_qstn_view_pop";
	}

	/**
	 * 시험 성적조회
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/viewScoreMain")
	public String viewScoreMain(ExamVO vo, Map commandMap, ModelMap model,
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
		
		return "home/lecture/exam/exam_view_score_main";
	}

	@RequestMapping(value="/examInfoList")
	public String examInfoList(ExamVO vo, HttpServletRequest request) throws Exception {
		
		vo.setSemiExamYn("N");
		List<ScoreDataDto> dataList = examService.listExamScore(vo).stream()
										.map(ScoreDataDto::new)
										.collect(Collectors.toList());
		
		request.setAttribute("dataList", dataList);
		request.setAttribute("infoTitle", "시험");
		
		return "home/lecture/exam/exam_view_score_detail_div";
	}

	@RequestMapping(value="/semiInfoList")
	public String semiInfoList(ExamVO vo, HttpServletRequest request) throws Exception {
		
		vo.setSemiExamYn("Y");
		List<ScoreDataDto> dataList = examService.listExamScore(vo).stream()
										.map(ScoreDataDto::new)
										.collect(Collectors.toList());
		
		request.setAttribute("dataList", dataList);
		request.setAttribute("infoTitle", "진행단계평가");
		
		return "home/lecture/exam/exam_view_score_detail_div";
	}
}