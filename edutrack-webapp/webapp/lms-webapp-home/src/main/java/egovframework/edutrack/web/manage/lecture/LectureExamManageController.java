package egovframework.edutrack.web.manage.lecture;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import egovframework.edutrack.comm.util.web.HtmlCleaner;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.URLBuilder;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.course.createcoursesubject.service.CreateCourseSubjectService;
import egovframework.edutrack.modules.course.createcoursesubject.service.CreateCourseSubjectVO;
import egovframework.edutrack.modules.course.createcoursesubject.service.CreateOnlineSubjectVO;
import egovframework.edutrack.modules.course.decls.service.CreCrsDeclsService;
import egovframework.edutrack.modules.course.decls.service.CreCrsDeclsVO;
import egovframework.edutrack.modules.course.exambank.service.ExamQbankQstnVO;
import egovframework.edutrack.modules.course.exambank.service.ExamQbankService;
import egovframework.edutrack.modules.lecture.exam.service.ExamQuestionVO;
import egovframework.edutrack.modules.lecture.exam.service.ExamService;
import egovframework.edutrack.modules.lecture.exam.service.ExamStareVO;
import egovframework.edutrack.modules.lecture.exam.service.ExamVO;
import egovframework.edutrack.modules.student.student.service.StudentService;
import egovframework.edutrack.modules.student.student.service.StudentVO;
import egovframework.edutrack.modules.system.code.service.SysCodeService;
import egovframework.edutrack.modules.system.code.service.SysCodeVO;


@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/mng/lecture/exam")
public class LectureExamManageController extends GenericController{

	@Autowired @Qualifier("examService")
	private ExamService 				examService;

	@Autowired @Qualifier("sysCodeService")
	private SysCodeService				sysCodeService;

	@Autowired @Qualifier("examQbankService")
	private ExamQbankService			examQbankService;

	@Autowired @Qualifier("createCourseSubjectService")
	private CreateCourseSubjectService createCourseSubjectService;

	@Autowired @Qualifier("studentService")
	private StudentService 			studentService;

	@Autowired @Qualifier("creCrsDeclsService")
	private CreCrsDeclsService 		creCrsDeclsService;

	@Autowired @Qualifier("sysCodeMemService")
	private SysCodeMemService			sysCodeMemService;


	/**
	 * 시험 관리 메인
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/examMain")
	public String examMain( ExamVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String crsCreCd = vo.getCrsCreCd();

		//-- 개설 과정 연결 과목 목록을 가져온다.
		CreateCourseSubjectVO createCourseSubjectVO = new CreateCourseSubjectVO();
		createCourseSubjectVO.setCrsCreCd(crsCreCd);

		List<CreateCourseSubjectVO> subjectList = createCourseSubjectService.listSubject(createCourseSubjectVO).getReturnList();
		request.setAttribute("subjectList", subjectList);
		request.setAttribute("paging", "Y");
		request.setAttribute("vo", vo);
		
		String returnUrl = null;
		if(vo.getSemiExamYn().equals("Y")) {	//진행단계평가일 떄
			 returnUrl = "mng/lecture/exam/semi_exam_main";
		}else {	//시험일 떄 
			 returnUrl = "mng/lecture/exam/exam_main";
		}

		return returnUrl;
	}

	/**
	 * 시험 목록 조회
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/listExam")
	public String listExam( ExamVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultListVO<ExamVO> resultListVO = examService.listExam(vo);
		
		String returnUrl = null;
		if(vo.getSemiExamYn().equals("Y")) {	//진행단계평가일 때 
			returnUrl = "mng/lecture/exam/semi_exam_list_div";
		}else {		//시험일 때
			returnUrl = "mng/lecture/exam/exam_list_div";
		}

		request.setAttribute("examListVO", resultListVO.getReturnList());
		request.setAttribute("pageInfo", resultListVO.getPageInfo());
		return returnUrl;
	}

	/**
	 * 시험 등록 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addExamPop")
	public String addExamPop( ExamVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String crsCreCd = vo.getCrsCreCd();
		List<SysCodeVO> examTypeCdList = sysCodeMemService.getSysCodeList("EXAM_TYPE_CD", UserBroker.getLocaleKey(request));
        List<SysCodeVO> examStareTypeCdList = sysCodeMemService.getSysCodeList("EXAM_STARE_TYPE_CD", UserBroker.getLocaleKey(request));
        List<SysCodeVO> stareTmUseYnList = sysCodeMemService.getSysCodeList("STARE_TM_USE_YN", UserBroker.getLocaleKey(request));
        
		//사용 등록 여부 가져오기
		List<SysCodeVO> regYnList = sysCodeService.listCode("REG_YN").getReturnList();
		request.setAttribute("regYnList", regYnList);
		
		CreateOnlineSubjectVO  createOnlineSubjectVO = new CreateOnlineSubjectVO();
		createOnlineSubjectVO.setCrsCreCd(crsCreCd);
		List<CreateOnlineSubjectVO> onlineList = createCourseSubjectService.listOnlineSubject(createOnlineSubjectVO).getReturnList();
		request.setAttribute("onlineSubjectList", onlineList);

		request.setAttribute("gubun", "A");
		request.setAttribute("vo", vo);
		request.setAttribute("examTypeCdList", examTypeCdList);
		request.setAttribute("examStareTypeCdList", examStareTypeCdList);
		request.setAttribute("stareTmUseYnList", stareTmUseYnList);
				
		
		return "mng/lecture/exam/exam_write_pop";
	}

	/**
	 * 시험 등록
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/addExam")
	public String addExam( ExamVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultVO<ExamVO> resultVO = new ProcessResultVO<>();
		
		if("ON".equals(StringUtil.nvl(vo.getExamTypeCd()))  && !vo.getIsCalCulateTotScore()) {//온라인 시험의 경우 시험 문항, 배점 확인
			resultVO.setMessage("시험 배점의 총합이 100이 아닙니다. 다시 수정바랍니다.");
			return JsonUtil.responseJson(response, resultVO);
		}
		
		String regYn = StringUtil.nvl(vo.getRegYn());
		if("".equals(regYn)) {
			vo.setRegYn("N");
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
	 * 시험 등록 완료
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/addRegistExam")
	public String addRegistExam( ExamVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

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
			log.error("[관리>시험관리>공개하기 오류] : " + e1.getMessage());
		} catch (Exception e) {
			resultVO.setMessage(getMessage(request, "lecture.message.exam.regist.failed"));
			resultVO.setResultFailed();
			log.error("[관리>시험관리>공개하기 오류] : 시험 공개하기 실패");
		}
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "lecture.message.exam.regist.success"));
		} else {
			resultVO.setMessage(!"".equals(StringUtil.nvl(resultVO.getMessage())) ? resultVO.getMessage() :  getMessage(request, "lecture.message.exam.regist.failed"));
		}
		
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 시험 등록 취소
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/cancelRegistExam")
	public String cancelRegistExam( ExamVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultVO<ExamVO> resultVO = new ProcessResultVO<>();
		
		try {
			ExamVO resultExamVO = new ExamVO();
			resultExamVO = examService.viewExam(vo).getReturnVO();
			if(vo.getStareCnt() > 0) {
				throw new ServiceProcessException("응시한 수강생이 있습니다. 미공개할 수 없습니다.");
			}
			
			if("N".equals(resultExamVO.getRegYn())) {
				throw new ServiceProcessException("현재 미공개 중인 시험입니다.");
			}
			
			resultExamVO.setRegYn("N");
			resultVO = examService.editExam(resultExamVO);
			resultVO.setMessage(getMessage(request, "lecture.message.exam.cancel.success"));
		} catch (MediopiaDefineException e1) {
			resultVO.setMessage(e1.getMessage());
			resultVO.setResultFailed();
		} catch (Exception e) {
			resultVO.setMessage(getMessage(request, "lecture.message.exam.cancel.failed"));
			resultVO.setResultFailed();
		}
		
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 시험 수정 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/editExamPop")
	public String editExamPop( ExamVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

        List<SysCodeVO> examTypeCdList = sysCodeMemService.getSysCodeList("EXAM_TYPE_CD", UserBroker.getLocaleKey(request));
        List<SysCodeVO> examStareTypeCdList = sysCodeMemService.getSysCodeList("EXAM_STARE_TYPE_CD", UserBroker.getLocaleKey(request));
        List<SysCodeVO> stareTmUseYnList = sysCodeMemService.getSysCodeList("STARE_TM_USE_YN", UserBroker.getLocaleKey(request));
		request.setAttribute("examTypeCdList", examTypeCdList);
		request.setAttribute("examStareTypeCdList", examStareTypeCdList);
		request.setAttribute("stareTmUseYnList", stareTmUseYnList);
		//-- exam험의 정보를 가져온다.
		request.setAttribute("vo", examService.viewExam(vo).getReturnVO());

		//사용 등록 여부 가져오기
		List<SysCodeVO> regYnList = sysCodeService.listCode("REG_YN").getReturnList();
		request.setAttribute("regYnList", regYnList);
		
		CreateOnlineSubjectVO  createOnlineSubjectVO = new CreateOnlineSubjectVO();
		String crsCreCd = vo.getCrsCreCd();
		createOnlineSubjectVO.setCrsCreCd(crsCreCd);
		List<CreateOnlineSubjectVO> onlineList = createCourseSubjectService.listOnlineSubject(createOnlineSubjectVO).getReturnList();
		request.setAttribute("onlineSubjectList", onlineList);

		request.setAttribute("gubun", "E");
		return "mng/lecture/exam/exam_write_pop";
	}

	/**
	 * 시험 수정
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/editExam")
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

	/**
	 * 시험 삭제
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/deleteExam")
	public String deleteExam( ExamVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultVO<ExamVO> resultVO = examService.deleteExam(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "lecture.message.exam.delete.success"));
		} else {
			resultVO.setMessage(getMessage(request, "lecture.message.exam.delete.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 시험 수정 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/manageFormExam")
	public String manageFormExam( ExamVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		//-- 시험 문제 은행 정보
		vo = examService.viewExam(vo).getReturnVO();

		//examVO.crsCreCd=${item.crsCreCd}&examVO.examSn=${item.examSn}

		if("Y".equals(vo.getRegYn()) || "OFF".equals(vo.getExamTypeCd())) {
			//-- 시험이 등록된 경우 평가 관리로 연결.
			return "redirect:"+(
					new URLBuilder("/mng/lecture/exam/manageFormExamRateMain")
						.addParameter("crsCreCd", vo.getCrsCreCd())
						.addParameter("examSn", vo.getExamSn())
						.toString()
				);
		} else {
			//-- 시험이 등록 되지 않은경우 문제 관리로 연결
			return "redirect:"+(
					new URLBuilder("/mng/lecture/exam/manageFormExamQstnMain")
						.addParameter("crsCreCd", vo.getCrsCreCd())
						.addParameter("examSn", vo.getExamSn())
						.toString()
				);
		}
	}

	/**
	 * 시험 문제 관리 메인
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/manageFormExamQstnMain")
	public String manageFormExamQstnMain( ExamVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		//-- exam험의 정보를 가져온다.
		request.setAttribute("vo", examService.viewExam(vo).getReturnVO());

		return "mng/lecture/exam/exam_manage_qstn_main";
	}

	/**
	 * 시험 평가 관리 메인
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/manageFormExamRateMain")
	public String manageFormExamRateMain( ExamVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		//--- 분반 목록
		CreCrsDeclsVO creCrsDeclsVO = new CreCrsDeclsVO();
		creCrsDeclsVO.setCrsCreCd(vo.getCrsCreCd());
		List<CreCrsDeclsVO> creCrsDeclsList = creCrsDeclsService.list(creCrsDeclsVO).getReturnList();
		request.setAttribute("creCrsDeclsList", creCrsDeclsList);

		//-- exam험의 정보를 가져온다.
		request.setAttribute("vo", examService.viewExam(vo).getReturnVO());
		request.setAttribute("paging", "Y");
		return "mng/lecture/exam/exam_manage_rate_main";
	}

	/**
	 * 결과 현황 메인
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/manageFormExamRsltMain")
	public String manageFormExamRsltMain( ExamVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		//--- 분반 목록
		CreCrsDeclsVO creCrsDeclsVO = new CreCrsDeclsVO();
		creCrsDeclsVO.setCrsCreCd(vo.getCrsCreCd());
		List<CreCrsDeclsVO> creCrsDeclsList = creCrsDeclsService.list(creCrsDeclsVO).getReturnList();
		request.setAttribute("creCrsDeclsList", creCrsDeclsList);

		//-- exam험의 정보를 가져온다.
		request.setAttribute("vo", examService.viewExam(vo).getReturnVO());

		List<ExamStareVO> stareList = examService.listExamStare(vo).getReturnList();
		List<CommStatusVO> statusList = new ArrayList<CommStatusVO>();
		for(int i = 0; i <= 100; i++ ) {
			CommStatusVO csVO = new CommStatusVO();
			csVO.setKeyCode(i+"");
			csVO.setKeyValue(0+"");
			statusList.add(csVO);
		}
		for(ExamStareVO iesVO : stareList) {
			for(CommStatusVO csVO : statusList) {
				if(Math.round(iesVO.getTotGetScore()) == Integer.parseInt(csVO.getKeyCode())) {
					int keyValue = Integer.parseInt(csVO.getKeyValue()) + 1;
					csVO.setKeyValue(keyValue+"");
				}
			}

		}
		request.setAttribute("statusList", statusList);
		request.setAttribute("paging", "Y");
		request.setAttribute("rslt", "Y");

		return "mng/lecture/exam/exam_manage_rslt_main";
	}

	/**
	 * 시험 문제 목록 조회
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/listQuestion")
	public String listQuestion( ExamQuestionVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String regYn = request.getParameter("regYn");
		List<ExamQuestionVO> examQuestionList = examService.listQstn(vo).getReturnList();

		request.setAttribute("regYn", regYn);
		request.setAttribute("examQuestionListVO", examQuestionList);
		return "mng/lecture/exam/exam_qstn_list_div";
	}

	/**
	 * 시험 문제 등록 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addFormQuestionPop")
	public String addFormQuestionPop(ExamQuestionVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) {

		String forward = this.getEditorType(request);

		request.setAttribute("gubun", "A");
		request.setAttribute("vo", vo);
		return forward;
	}

	/**
	 * 시험 문제 등록
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/addQuestion")
	public String addQuestion( ExamQuestionVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		// 스크립트, 스타일 태그 제거
		vo.setQstnCts(HtmlCleaner.cleanScript(vo.getQstnCts()) );

		ProcessResultVO<ExamQuestionVO> resultVO = examService.addQstn(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "lecture.message.exam.question.add.success"));
		} else {
			resultVO.setMessage(getMessage(request, "lecture.message.exam.question.add.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
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
	public String viewQstnPop( ExamQuestionVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		//-- 시험 문제 정보를 찾아 Form에 셋팅
		vo = examService.viewQstn(vo).getReturnVO();
		String forward = this.getEditorType(request);

		request.setAttribute("gubun", "E");
		request.setAttribute("vo", vo);
		request.setAttribute("summernote", "Y");
		return "mng/lecture/exam/exam_qstn_view_pop";
	}

	/**
	 * 시험 문제 수정 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/editFormQuestionPop")
	public String editFormQuestionPop( ExamQuestionVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		//-- 시험 문제 정보를 찾아 Form에 셋팅
		vo = examService.viewQstn(vo).getReturnVO();
		request.setAttribute("vo", vo);
		String forward = this.getEditorType(request);

		request.setAttribute("gubun", "E");
		return forward;
	}

	/**
	 * 시험 문제 수정
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/editQuestion")
	public String editQuestion( ExamQuestionVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		// 스크립트, 스타일 태그 제거
		vo.setQstnCts(HtmlCleaner.cleanScript(vo.getQstnCts()) );

		ProcessResultVO<ExamQuestionVO> resultVO = examService.editQstn(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "lecture.message.exam.question.edit.success"));
		} else {
			resultVO.setMessage(getMessage(request, "lecture.message.exam.question.edit.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 시험 문제 삭제
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/deleteQuestion")
	public String deleteQuestion( ExamQuestionVO vo, Map commandMap, ModelMap model,
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
	 * 문제은행 보기 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/editQbankPop")
	public String editQbankPop( ExamVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		request.setAttribute("vo", vo);
		request.setAttribute("paging", "Y");

		return "mng/lecture/exam/exam_qbank_write_pop";
	}

	/**
	 * 문제 은행 문제 목록
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/listQbankQuestion")
	public String listQbankQuestion( ExamQbankQstnVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String qstnType = "";
		String searchKey = "";
		
		String parCtgrCd = StringUtil.nvl(request.getParameter("parCtgrCd"));
		String ctgrCd = StringUtil.nvl(request.getParameter("ctgrCd"));
		
		ProcessResultListVO<ExamQbankQstnVO> resultList = examQbankService.listQstn(ctgrCd, parCtgrCd, qstnType, searchKey);
		request.setAttribute("examQuestionList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());
		return "mng/lecture/exam/exam_qbank_qstn_list_div";
	}

	/**
	 * 시험 문제 등록
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/addQbankQuestion")
	public String addQbankQuestion( ExamQuestionVO vo, Map commandMap, ModelMap model,
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
	public String listStare( ExamVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		Integer declsNo = vo.getDeclsNo();
		String  userNm = vo.getUserNm();
		String stareYn = vo.getStareYn();

		vo = examService.viewExam(vo).getReturnVO();
		vo.setDeclsNo(declsNo);
		vo.setUserNm(userNm);
		vo.setStareYn(stareYn);

		vo.setCurPage(Integer.parseInt(StringUtil.nvl(request.getParameter("curPage"),"1")));
		vo.setListScale(Integer.parseInt(StringUtil.nvl(request.getParameter("listScale"),"20")));
		vo.setPageScale(10);
		ProcessResultListVO<ExamStareVO> resultList = examService.listExamStarePaging(vo);
		request.setAttribute("examStareListVO", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());
		request.setAttribute("vo", vo);
		return "mng/lecture/exam/exam_stare_list_div";
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
	public String editFormRate( ExamStareVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ExamVO examVO = new ExamVO();
		examVO.setCrsCreCd(vo.getCrsCreCd());
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

		String[] examQstnNo = StringUtil.split(strExamQstnNo,"@#");
		String[] stareAnsr = StringUtil.split(strStareAnsrs,"@#");
		String[] getScore = StringUtil.split(strGetScores,"@#");

		int stareAnsrCnt = stareAnsr.length;
		int getScoreCnt = getScore.length;

		Map<Integer, Map<String, String>> parentMap = new Hashtable<Integer, Map<String,String>>();
		for(int i=0; i < examQstnNo.length; i++) {
			Map<String, String> childMap = new Hashtable<String, String>();
			if(!"".equals(strStareAnsrs)) childMap.put("answer", StringUtil.nvl(stareAnsr[i],""));
			else childMap.put("answer", "");
			if(!"".equals(strGetScores))	childMap.put("score", StringUtil.nvl(getScore[i],"0"));
			else childMap.put("score", "0");
			parentMap.put(Integer.parseInt(examQstnNo[i]), childMap);
		}
		request.setAttribute("stareInfo", parentMap);
		request.setAttribute("examStareVO", strStareVO);
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
		
		request.setAttribute("questionList", questionList);

		//-- 학습자 정보 조회
		StudentVO studentVO = new StudentVO();
		studentVO.setStdNo(vo.getStdNo());
		studentVO = studentService.viewStudent(studentVO).getReturnVO();

		request.setAttribute("studentVO", studentVO);
		request.setAttribute("vo", vo);


		return "mng/lecture/exam/exam_rate_write_pop";
	}

	/**
	 * 시험 평가 등록
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/addStareRate")
	public String addStareRate( ExamStareVO vo, Map commandMap, ModelMap model,
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
	 * 개별 시험 점수 등록
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/addStareScore")
	public String addStareScore( ExamStareVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		ProcessResultVO<ExamStareVO> resultVO = new ProcessResultVO<>();
		
		try {
			ExamStareVO returnVO = examService.viewStareNoCont(vo).getReturnVO();
			if(returnVO == null) {
				vo.setStareCnt(1);
				vo.setRateYn("Y");
				resultVO = examService.addStareScore(vo);
			}else {
				resultVO = examService.editStareScore(vo);
			}
			resultVO.setMessage(getMessage(request, "lecture.message.exam.stare.add.success"));
		} catch (Exception e) {
			resultVO.setMessage(getMessage(request, "lecture.message.exam.stare.add.failed"));
			resultVO.setResultFailed();
		}
		return JsonUtil.responseJson(response, resultVO);
	}


	/**
	 * 전체 시험 점수 등록
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/addStareScoreAll")
	public String addStareScoreAll( ExamVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		ExamStareVO examStareVO = vo.getExamStareVO();
		examStareVO.setCrsCreCd(vo.getCrsCreCd());
		examStareVO.setExamSn(vo.getExamSn());
		String strStdNo = request.getParameter("strStdNo");
		String strScore = request.getParameter("strScore");

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
		
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 시험 평가 완료
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/editExamComplete")
	public String editExamComplete( ExamStareVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultVO<ExamStareVO> resultVO = examService.editExamComplete(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request,  "lecture.message.exam.rate.success"));
		} else {
			resultVO.setMessage(getMessage(request,  "lecture.message.exam.rate.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 재시험 설정
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/removeStare")
	public String removeStare( ExamVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		ExamStareVO examStareVO = vo.getExamStareVO();
		examStareVO.setCrsCreCd(vo.getCrsCreCd());
		examStareVO.setExamSn(vo.getExamSn());
		examStareVO.setUserNoObj(request.getParameter("userNoObj"));

		ProcessResultVO<ExamStareVO> resultVO = new ProcessResultVO<>();
		
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
		
		return JsonUtil.responseJson(response, resultVO);
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
	public String editRateDanPop( ExamVO vo, Map commandMap, ModelMap model,
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
				String strExamQstnNo = stdQstnNos.substring(2, iesVO.getQstnNos().length()-2);
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
							//모사 조회 pk
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
		return "mng/lecture/exam/exam_rate_dan_write_pop";
	}

	/**
	 * 단답식,주관식 채점
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/editRateDan")
	public String editRateDan( ExamStareVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		vo.setRegIp(request.getRemoteAddr());
		ProcessResultVO<ExamStareVO> resultVO = examService.rateStareDan(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "lecture.message.exam.rate.success"));
		} else {
			resultVO.setMessage(getMessage(request, "lecture.message.exam.rate.add.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 *
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/addPaperForm")
	public String addPaperForm( ExamStareVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		//시험기본정보 입력

		ExamQuestionVO examQuestionVO = new ExamQuestionVO();
		examQuestionVO.setCrsCreCd(vo.getCrsCreCd());
		examQuestionVO.setExamSn(vo.getExamSn());

		List<ExamQuestionVO> questionList = examService.listPreview(examQuestionVO).getReturnList();
		request.setAttribute("questionList", questionList);

		//-- 학습자 정보 조회
/*
		StudentVO studentVO = new StudentVO();
		studentVO.setStdNo(examStareVO.getStdNo());
		studentVO = studentService.viewStudent(studentVO).getReturnVO();
		request.setAttribute("studentVO", studentVO);


		ExamVO examVO = new ExamVO();
		examVO.setExamSn(examStareVO.getExamSn());
		examVO.setCrsCreCd(UserBroker.getCreCrsCd(request));
		examVO = examService.viewExam(examVO).getReturnVO();
		request.setAttribute("examVO", examVO);

		examForm.setExamStareVO(examStareVO);
*/

		return "mng/lecture/exam/exam_paper_student";
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
	public String previewPaperPop( ExamStareVO vo, Map commandMap, ModelMap model,
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

		return "mng/lecture/exam/exam_preview_pop";
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
	public String sortQuestionPop( ExamQuestionVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		List<ExamQuestionVO> examQuestionList = examService.listQstn(vo).getReturnList();

		request.setAttribute("examQuestionListVO", examQuestionList);
		request.setAttribute("vo", vo);
		return "mng/lecture/exam/exam_qstn_sort_pop";
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
	public String sortQuestion( ExamQuestionVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultVO<ExamQuestionVO> resultVO = examService.editQstnSort(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "lecture.message.exam.question.edit.success"));
		} else {
			resultVO.setMessage(getMessage(request, "lecture.message.exam.question.edit.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 시험 문제은행 문제 엑셀 폼
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/addQuestionExcelPop")
	public String addQuestionExcelPop( ExamQuestionVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		request.setAttribute("examQuestionVO", vo);
		request.setAttribute("gubun", "A");
		request.setAttribute("fileupload", "Y");
		return "mng/lecture/exam/exam_qstn_write_excel";
	}

	/**
	 * 시험 문제은행 샘플파일 다운로드 폼.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/sampleExcelQuestion")
	public String sampleExcelQuestion ( ExamQuestionVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);

		HashMap<String, String> titles = new HashMap<String, String>();
		titles.put("info1", getMessage(request, "course.title.excel.exambank.info1"));
		titles.put("info2", getMessage(request, "course.title.excel.exambank.info2"));
		titles.put("info3", getMessage(request, "course.title.excel.exambank.info3"));
		titles.put("info4", getMessage(request, "course.title.excel.exambank.info4"));
		titles.put("info5", getMessage(request, "course.title.excel.exambank.info5"));
		titles.put("info6", getMessage(request, "course.title.excel.exambank.info6"));
		titles.put("info7", getMessage(request, "course.title.excel.exambank.info7"));
		titles.put("info8", getMessage(request, "course.title.excel.exambank.info8"));
		titles.put("info9", getMessage(request, "course.title.excel.exambank.info9"));
		titles.put("info10", getMessage(request, "course.title.excel.exambank.info10"));
		titles.put("info11", getMessage(request, "course.title.excel.exambank.info11"));
		titles.put("info12", getMessage(request, "course.title.excel.exambank.info12"));
		titles.put("type", getMessage(request, "course.title.excel.exambank.question.type"));
		titles.put("no", getMessage(request, "common.title.no"));
		titles.put("title", getMessage(request, "course.title.exambank.title"));
		titles.put("qstncts", getMessage(request, "course.title.reshbank.item.reshitemcts.excel"));
		titles.put("item1", getMessage(request, "course.title.excel.exambank.item1"));
		titles.put("item2", getMessage(request, "course.title.excel.exambank.item2"));
		titles.put("item3", getMessage(request, "course.title.exambank.item3"));
		titles.put("item4", getMessage(request, "course.title.exambank.item4"));
		titles.put("item5", getMessage(request, "course.title.exambank.item5"));
		titles.put("rightansr", getMessage(request, "course.title.exambank.rightansr"));
		titles.put("comment", getMessage(request, "course.title.exambank.comment"));

		response.setHeader("Content-Disposition", "attachment;filename=question_item_sample.xls;");
		response.setHeader( "Content-Transfer-Coding", "binary" );
		response.setContentType("application/octet-stream");

		try {
			examService.sampleExcelDownload(titles, response.getOutputStream(),orgCd);
		} catch (Exception e) {

		}
		return null;
	}


	/**
	 * 시험 문제은행 엑셀 업로드
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/excelUploadCheck")
	public String excelUploadCheck( ExamQuestionVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String fileName = StringUtil.nvl(request.getParameter("fileName"));
		String filePath = StringUtil.nvl(request.getParameter("filePath"));

		ProcessResultListVO<ExamQuestionVO> resultList = null;
		resultList = examService.excelUploadValidationCheck(fileName, filePath);
		request.setAttribute("qstnList", resultList.getReturnList());

		request.setAttribute("examQuestionVO", vo);
		request.setAttribute("fileupload", "Y");
		return "mng/lecture/exam/exam_qstn_write_excel_validate";
	}


	/**
	 * 설문 은행 문제 엑셀 업로드 , 단건 체크
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/questionUploadCheck")
	public String questionUploadCheck( ExamQuestionVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String errorCode = "";
		if(ValidationUtils.isEmpty(vo.getQstnNo())){
			errorCode += "|"+"EMPTYQSTNNO";
		}
		if(vo.getQstnNo() < 1){
			errorCode += "|"+"TYPEQSTNNO";
		}
		if(!"J".equals(vo.getQstnType()) && !"K".equals(vo.getQstnType())
			&& !"S".equals(vo.getQstnType()) && !"D".equals(vo.getQstnType())	){
			errorCode += "|"+"TYPEQSTNTYPE";
		}
		if(ValidationUtils.isEmpty(vo.getTitle())){
			errorCode += "|"+"EMPTYTITLE";
		}
		if(ValidationUtils.isEmpty(vo.getQstnCts())){
			errorCode += "|"+"EMPTYQSTNCTS";
		}
		if(ValidationUtils.isEmpty(vo.getRgtAnsr())){
			errorCode += "|"+"EMPTYRGTANSR";
		}
		if(ValidationUtils.isEmpty(vo.getQstnExpl())){
			//errorCode += "|"+"EMPTYQSTNEXPL";
		}

		if("K".equals(vo.getQstnType()) ){
			if(ValidationUtils.isEmpty(vo.getEmpl1())){
				errorCode += "|"+"EMPTYVIEW1";
			}
			if(ValidationUtils.isEmpty(vo.getEmpl2())){
				errorCode += "|"+"EMPTYVIEW2";
			}
			if(ValidationUtils.isEmpty(vo.getEmpl3())){
				errorCode += "|"+"EMPTYVIEW3";
			}
			if(ValidationUtils.isEmpty(vo.getEmpl4())){
				errorCode += "|"+"EMPTYVIEW4";
			}
			if(!StringUtil.isNumber(vo.getRgtAnsr())){
				errorCode += "|"+"TYPERGTANSR";
			} else {
				if(Integer.parseInt(vo.getRgtAnsr()) > 5){
					errorCode += "|"+"TYPERGTANSR";
				}
				if(ValidationUtils.isEmpty(vo.getEmpl5()) && Integer.parseInt(vo.getRgtAnsr()) > 4 ){
					errorCode += "|"+"TYPERGTANSR";
				}
			}
		}else if("S".equals(vo.getQstnType()) ){
			if(!"O".equals(vo.getRgtAnsr()) && !"X".equals(vo.getRgtAnsr())){
				errorCode += "|"+"TYPERGTANSR";
			}
		}
		System.out.println("errorCode : " + errorCode);
		vo.setErrorCode(errorCode);

		return JsonUtil.responseJson(response, vo);
	}

	/**
	 * 설문 은행 문제 엑셀 업로드 , 최종 업로드
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/questionUpload")
	public String questionUpload( ExamQuestionVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		int qstnSn = Integer.parseInt(StringUtil.nvl(request.getParameter("qstnSn"), "0"));
		String crsCreCd = StringUtil.nvl(request.getParameter("crsCreCd"),"");
		int examSn = Integer.parseInt(StringUtil.nvl(request.getParameter("examSn"),"0"));

		String[] chk = request.getParameterValues("chk");
		String[] qstnNo = request.getParameterValues("qstnNo");
		String[] lineNo = request.getParameterValues("lineNo");
		String[] qstnType = request.getParameterValues("qstnType");
		String[] title = request.getParameterValues("title");
		String[] qstnCts = request.getParameterValues("qstnCts");
		String[] empl1 = request.getParameterValues("empl1");
		String[] empl2 = request.getParameterValues("empl2");
		String[] empl3 = request.getParameterValues("empl3");
		String[] empl4 = request.getParameterValues("empl4");
		String[] empl5 = request.getParameterValues("empl5");
		String[] rgtAnsr = request.getParameterValues("rgtAnsr");
		String[] qstnExpl = request.getParameterValues("qstnExpl");

		List<ExamQuestionVO> questionBankList = new ArrayList<ExamQuestionVO>();
		for(int i=0 ; i < qstnType.length; i++) {
			//-- 체크된 사용자만 등록함.
			for(int j=0; j < chk.length; j++) {

				if(lineNo[i].equals(chk[j])) {
					ExamQuestionVO qstnVO = new ExamQuestionVO();

					qstnVO.setExamSn(examSn);
					qstnVO.setExamQstnSn(qstnSn);
					qstnVO.setQstnNo(Integer.parseInt(qstnNo[i]));
					qstnVO.setCrsCreCd(crsCreCd);
					qstnVO.setTitle(title[i]);
					qstnVO.setQstnType(qstnType[i]);
					qstnVO.setQstnCts(qstnCts[i]);
					if(qstnType[i].equals("K")){
						qstnVO.setEmpl1(empl1[i]);
						qstnVO.setEmpl2(empl2[i]);
						qstnVO.setEmpl3(empl3[i]);
						qstnVO.setEmpl4(empl4[i]);
						qstnVO.setEmpl5(empl5[i]);
					}
					if(qstnType[i] == "S"){
						qstnVO.setRgtAnsr(rgtAnsr[i].toUpperCase());
					} else {
						qstnVO.setRgtAnsr(rgtAnsr[i]);
					}
					qstnVO.setQstnExpl(qstnExpl[i]);

					questionBankList.add(qstnVO);
				}
			}
		}
		ProcessResultVO<ExamQuestionVO> resultVO = new ProcessResultVO<ExamQuestionVO>().setReturnVO(vo);
		try {
			examService.addQstnBatch(questionBankList);
			resultVO.setMessage(getMessage(request, "lecture.message.exam.question.add.success"));
			resultVO.setResult(1);
		} catch (Exception e) {
			resultVO.setMessage(getMessage(request, "lecture.message.exam.question.add.failed"));
			resultVO.setResult(-1);
		}

		return JsonUtil.responseJson(response, resultVO);

	}

	private String getEditorType(HttpServletRequest request) {
		String forwardUrl = "mng/lecture/exam/exam_qstn_write_pop";
		//-- 에디터 사용여부를 판단하여 해당 Webeditor를 사용하도록 한다.
		//-- BBS_INFO의 editorUseYn은 더이상 사용하지 않음.
		if("Y".equals(Constants.WEBEDITOR_YSEYN)) {
			if("DAUMEDITOR".equals(Constants.WEBEDITOR_NAME))  { 
				forwardUrl = "mng/lecture/exam/exam_qstn_write_daumeditor_pop";
				request.setAttribute("daumeditor", "Y");
			} else if("SUMMERNOTE".equals(Constants.WEBEDITOR_NAME)) {
				forwardUrl = "mng/lecture/exam/exam_qstn_write_summernote_pop";
				request.setAttribute("summernote", "Y");
			}
		}
		return forwardUrl;
	}
}
