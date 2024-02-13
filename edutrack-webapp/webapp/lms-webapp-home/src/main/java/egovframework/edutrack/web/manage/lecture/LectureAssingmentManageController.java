package egovframework.edutrack.web.manage.lecture;

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
import egovframework.edutrack.modules.course.createcoursesubject.service.CreateCourseSubjectService;
import egovframework.edutrack.modules.course.createcoursesubject.service.CreateCourseSubjectVO;
import egovframework.edutrack.modules.course.createcoursesubject.service.CreateOnlineSubjectVO;
import egovframework.edutrack.modules.course.decls.service.CreCrsDeclsService;
import egovframework.edutrack.modules.course.decls.service.CreCrsDeclsVO;
import egovframework.edutrack.modules.lecture.assignment.service.AssignmentSendVO;
import egovframework.edutrack.modules.lecture.assignment.service.AssignmentService;
import egovframework.edutrack.modules.lecture.assignment.service.AssignmentSubConstVO;
import egovframework.edutrack.modules.lecture.assignment.service.AssignmentSubVO;
import egovframework.edutrack.modules.lecture.assignment.service.AssignmentVO;
import egovframework.edutrack.modules.log.prnlog.service.LogPrnLogService;
import egovframework.edutrack.modules.log.prnlog.service.LogPrnLogVO;
import egovframework.edutrack.modules.student.student.service.StudentService;
import egovframework.edutrack.modules.student.student.service.StudentVO;
import egovframework.edutrack.modules.system.code.service.SysCodeVO;


@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/mng/lecture/assignment")
public class LectureAssingmentManageController extends GenericController {

	@Autowired @Qualifier("assignmentService")
	private AssignmentService 			assignmentService;

	@Autowired @Qualifier("createCourseSubjectService")
	private CreateCourseSubjectService createCourseSubjectService;

	@Autowired @Qualifier("studentService")
	private StudentService 			studentService;

	@Autowired @Qualifier("creCrsDeclsService")
	private CreCrsDeclsService 		creCrsDeclsService;

	@Autowired @Qualifier("logPrnLogService")
	private LogPrnLogService		logPrnLogService;
	
	@Autowired @Qualifier("sysCodeMemService")
	private SysCodeMemService			sysCodeMemService;
	
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
	@RequestMapping(value="/asmtMain")
	public String asmtMain( AssignmentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String crsCreCd = vo.getCrsCreCd();

		//-- 개설 과정 연결 과목 목록을 가져온다.
		CreateCourseSubjectVO createCourseSubjectVO = new CreateCourseSubjectVO();
		createCourseSubjectVO.setCrsCreCd(crsCreCd);

		List<CreateCourseSubjectVO> subjectList = createCourseSubjectService.listSubject(createCourseSubjectVO).getReturnList();
		request.setAttribute("subjectList", subjectList);
		request.setAttribute("vo", vo);

		return "mng/lecture/assignment/asmt_main";
	}

	/**
	 * 과제 목록 조회
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/listAsmt")
	public String listAsmt( AssignmentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultListVO<AssignmentVO> resultList = assignmentService.listAssignment(vo);
		request.setAttribute("assignmentListVO", resultList.getReturnList());
		return "mng/lecture/assignment/asmt_list_div";
	}

	/**
	 * 과제 등록 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addAsmtPop")
	public String addAsmtPop( AssignmentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String crsCreCd = vo.getCrsCreCd();
		
		List<SysCodeVO> asmtTypeCdList = sysCodeMemService.getSysCodeList("ASMT_TYPE_CD", UserBroker.getLocaleKey(request));
        List<SysCodeVO> asmtSelectTypeCdList = sysCodeMemService.getSysCodeList("ASMT_SELECT_TYPE_CD", UserBroker.getLocaleKey(request));
        List<SysCodeVO> asmtServiceCdList = sysCodeMemService.getSysCodeList("ASMT_SVC_CD", UserBroker.getLocaleKey(request));
        List<SysCodeVO> regYnList = sysCodeMemService.getSysCodeList("REG_YN", UserBroker.getLocaleKey(request));

		//-- 개설 과정 연결 과목 목록을 가져온다.
//		CreateCourseSubjectVO createCourseSubjectVO = new CreateCourseSubjectVO();
//		createCourseSubjectVO.setCrsCreCd(crsCreCd);

//		List<CreateCourseSubjectVO> subjectList = createCourseSubjectService.listSubject(createCourseSubjectVO).getReturnList();

        CreateOnlineSubjectVO  createOnlineSubjectVO = new CreateOnlineSubjectVO();
		createOnlineSubjectVO.setCrsCreCd(crsCreCd);
		List<CreateOnlineSubjectVO> subjectList = createCourseSubjectService.listOnlineSubject(createOnlineSubjectVO).getReturnList();
		request.setAttribute("subjectList", subjectList);

		request.setAttribute("gubun", "A");
		request.setAttribute("vo", vo);
		request.setAttribute("asmtTypeCdList", asmtTypeCdList);
		request.setAttribute("asmtSelectTypeCdList", asmtSelectTypeCdList);
		request.setAttribute("asmtServiceCdList", asmtServiceCdList);
		request.setAttribute("regYnList", regYnList);
		request.setAttribute("fileupload", "Y");
		return "mng/lecture/assignment/asmt_write_pop";
	}

	/**
	 * 과제 등록
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/addAsmt")
	public String addAsmt( AssignmentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		// 스크립트, 스타일 태그 제거
		vo.setAsmtCts(HtmlCleaner.cleanScript(vo.getAsmtCts()) );
		vo.setAsmtSvcCd(vo.getAsmtTypeCd().equals("OFF") ? "GEN" : vo.getAsmtSvcCd());
		vo.setAsmtSelectTypeCd(vo.getAsmtSvcCd().equals("CODE") ? "S" : vo.getAsmtSelectTypeCd());

		ProcessResultVO<AssignmentVO> resultVO = assignmentService.addAssignment(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "lecture.message.assignment.add.success"));
		} else {
			resultVO.setMessage(getMessage(request, "lecture.message.assignment.add.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 과제 등록 완료
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/registerAsmt")
	public String registerAsmt( AssignmentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		vo = assignmentService.viewAssignment(vo).getReturnVO();
		vo.setRegYn("Y");

		ProcessResultVO<AssignmentVO> resultVO = assignmentService.editAssignment(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "lecture.message.assignment.regist.success"));
		} else {
			resultVO.setMessage(getMessage(request, "lecture.message.assignment.regist.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 과제 등록 취소
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/registerCancelAsmt")
	public String registerCancelAsmt( AssignmentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		vo = assignmentService.viewAssignment(vo).getReturnVO();
		vo.setRegYn("N");

		ProcessResultVO<AssignmentVO> resultVO = assignmentService.editAssignment(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "lecture.message.assignment.cancelregist.success"));
		} else {
			resultVO.setMessage(getMessage(request, "lecture.message.assignment.cancelregist.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 과제 수정 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/editAsmtPop")
	public String editAsmtPop( AssignmentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		//-- assignment험의 정보를 가져온다.
		request.setAttribute("vo", assignmentService.viewAssignment(vo).getReturnVO());
		 List<SysCodeVO> regYnList = sysCodeMemService.getSysCodeList("REG_YN", UserBroker.getLocaleKey(request));
		 request.setAttribute("regYnList", regYnList);
		//--- 분반 목록
		CreCrsDeclsVO creCrsDeclsVO = new CreCrsDeclsVO();
		creCrsDeclsVO.setCrsCreCd(vo.getCrsCreCd());
		List<CreCrsDeclsVO> creCrsDeclsList = creCrsDeclsService.list(creCrsDeclsVO).getReturnList();
		request.setAttribute("creCrsDeclsList", creCrsDeclsList);

	    CreateOnlineSubjectVO  createOnlineSubjectVO = new CreateOnlineSubjectVO();
		createOnlineSubjectVO.setCrsCreCd(vo.getCrsCreCd());
		List<CreateOnlineSubjectVO> subjectList = createCourseSubjectService.listOnlineSubject(createOnlineSubjectVO).getReturnList();
		request.setAttribute("subjectList", subjectList);
		
		
		request.setAttribute("gubun", "E");
		request.setAttribute("fileupload", "Y");
		return "mng/lecture/assignment/asmt_write_pop";
	}

	/**
	 * 과제 수정
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/editAsmt")
	public String editAsmt( AssignmentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		// 스크립트, 스타일 태그 제거
		vo.setAsmtCts(HtmlCleaner.cleanScript(vo.getAsmtCts()) );
		vo.setAsmtSvcCd(vo.getAsmtTypeCd().equals("OFF") ? "GEN" : vo.getAsmtSvcCd());
		vo.setAsmtSelectTypeCd(vo.getAsmtSvcCd().equals("CODE") ? "S" : vo.getAsmtSelectTypeCd());

		ProcessResultVO<AssignmentVO> resultVO = assignmentService.editAssignment(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "lecture.message.assignment.edit.success"));
		} else {
			resultVO.setMessage(getMessage(request, "lecture.message.assignment.edit.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 과제 삭제
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/removeAsmt")
	public String removeAsmt( AssignmentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultVO<AssignmentVO> resultVO = assignmentService.deleteAssignment(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "lecture.message.assignment.delete.success"));
		} else {
			resultVO.setMessage(getMessage(request, "lecture.message.assignment.delete.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 과제 관리 메인
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/manageAsmtMain")
	public String manageAsmt( AssignmentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		//-- assignment험의 정보를 가져온다.
		vo = assignmentService.viewAssignment(vo).getReturnVO();
		request.setAttribute("vo", vo);
		if("Y".equals(vo.getRegYn()) || "OFF".equals(vo.getAsmtTypeCd())) {
			//-- 시험이 등록된 경우 평가 관리로 연결.
			return "redirect:"+(
					new URLBuilder("/mng/lecture/assignment/manageRateMain")
						.addParameter("crsCreCd", vo.getCrsCreCd())
						.addParameter("asmtSn", vo.getAsmtSn())
						.toString()
				);
		} else {
			//-- 시험이 등록 되지 않은경우 문제 관리로 연결
			return "redirect:"+(
					new URLBuilder("/mng/lecture/assignment/manageQstnMain")
						.addParameter("crsCreCd", vo.getCrsCreCd())
						.addParameter("asmtSn", vo.getAsmtSn())
						.toString()
				);
		}
	}

	/**
	 * 과제 문제 관리 메인
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/manageQstnMain")
	public String manageQstnMain( AssignmentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		//-- assignment험의 정보를 가져온다.
		request.setAttribute("vo", assignmentService.viewAssignment(vo).getReturnVO());
		request.setAttribute("paging", "Y");
		return "mng/lecture/assignment/asmt_manage_qstn_main";
	}

	/**
	 * 과제 평가 관리 메인
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/manageRateMain")
	public String manageRateMain( AssignmentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		//-- assignment험의 정보를 가져온다.
		request.setAttribute("vo", assignmentService.viewAssignment(vo).getReturnVO());
		//--- 분반 목록
		CreCrsDeclsVO creCrsDeclsVO = new CreCrsDeclsVO();
		creCrsDeclsVO.setCrsCreCd(vo.getCrsCreCd());
		List<CreCrsDeclsVO> creCrsDeclsList = creCrsDeclsService.list(creCrsDeclsVO).getReturnList();
		request.setAttribute("creCrsDeclsList", creCrsDeclsList);

		request.setAttribute("gubun", "E");
		request.setAttribute("paging", "Y");
		return "mng/lecture/assignment/asmt_manage_rate_main";
	}

	/**
	 * 과제 결과 현황
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/manageRsltMain")
	public String manageRsltMain( AssignmentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		//-- assignment험의 정보를 가져온다.
		request.setAttribute("vo", assignmentService.viewAssignment(vo).getReturnVO());
		//--- 분반 목록
		CreCrsDeclsVO creCrsDeclsVO = new CreCrsDeclsVO();
		creCrsDeclsVO.setCrsCreCd(vo.getCrsCreCd());
		List<CreCrsDeclsVO> creCrsDeclsList = creCrsDeclsService.list(creCrsDeclsVO).getReturnList();
		request.setAttribute("creCrsDeclsList", creCrsDeclsList);

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
		request.setAttribute("rslt", "Y");
		return "mng/lecture/assignment/asmt_manage_rslt_main";
	}

	/**
	 * 과제 후보 목록 조회
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/listQstn")
	public String listQstn( AssignmentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultListVO<AssignmentSubVO> resultList = assignmentService.listSub(vo);
		request.setAttribute("assignmentSibListVO", resultList.getReturnList());
		request.setAttribute("vo", vo);
		return "mng/lecture/assignment/asmt_qstn_list_div";
	}

	/**
	 * 과제 후보 등록 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addQstnPop")
	public String addQstnPop( AssignmentSubVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response ) throws Exception {
		commonVOProcessing(vo, request);

		String forward = this.getEditorType(request);
		List<SysCodeVO> asmtDevLangCdList = sysCodeMemService.getSysCodeList("DEV_LANG_CD", UserBroker.getLocaleKey(request));
        List<SysCodeVO> asmtDiffLvlCdList = sysCodeMemService.getSysCodeList("DIFF_LVL_CD", UserBroker.getLocaleKey(request));
		
        AssignmentVO aVO= new AssignmentVO();
        aVO.setCrsCreCd(vo.getCrsCreCd());
        aVO.setAsmtSn(vo.getAsmtSn());
        request.setAttribute("aVO", assignmentService.viewAssignment(aVO).getReturnVO());
		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "A");
		request.setAttribute("asmtDevLangCdList", asmtDevLangCdList);
		request.setAttribute("asmtDiffLvlCdList", asmtDiffLvlCdList);
		return forward;
	}

	/**
	 * 과제 후보 등록
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/addQstn")
	public String addQstn( AssignmentSubVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
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
	@RequestMapping(value="/editQstnPop")
	public String editQstnPop( AssignmentSubVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		AssignmentVO aVO= new AssignmentVO();
        aVO.setCrsCreCd(vo.getCrsCreCd());
        aVO.setAsmtSn(vo.getAsmtSn());
	    request.setAttribute("aVO", assignmentService.viewAssignment(aVO).getReturnVO());
		//-- 과제 후보 정보를 찾아 Form에 셋팅
		vo = assignmentService.viewSub(vo).getReturnVO();
		
		AssignmentSubConstVO cVO = new AssignmentSubConstVO();
		cVO.setCrsCreCd(vo.getCrsCreCd());
        cVO.setAsmtSn(vo.getAsmtSn());
        cVO.setAsmtSubSn(vo.getAsmtSubSn());
        ProcessResultListVO<AssignmentSubConstVO> constList = assignmentService.listSubConst(cVO);
		
		request.setAttribute("vo", vo);
		request.setAttribute("constList", constList.getReturnList());
		String forward = this.getEditorType(request);

		request.setAttribute("gubun", "E");
		return forward;
	}

	/**
	 * 과제 후보 수정
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/editQstn")
	public String editQstn(AssignmentSubVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
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
	@RequestMapping(value="/removeQstn")
	public String removeQstn( AssignmentSubVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
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
	@RequestMapping(value="/addQbankForm")
	public String addQbankForm( AssignmentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		//-- 연결과목을 가져와 Attribute에 셋팅
		CreateOnlineSubjectVO onlineSubjectVO = new CreateOnlineSubjectVO();
		onlineSubjectVO.setCrsCreCd(vo.getCrsCreCd());
		List<CreateOnlineSubjectVO> subjectList = createCourseSubjectService.listOnlineSubject(onlineSubjectVO).getReturnList();

		request.setAttribute("subjectList", subjectList);

		return "assignment_qbank_write";
	}

	/**
	 * 과제 후보 등록
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/addQbank")
	public String addQbank( AssignmentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultVO<AssignmentSubVO> resultVO = assignmentService.addQbankSub(vo);
		
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "lecture.message.assignment.question.add.success"));
		} else {
			resultVO.setMessage(getMessage(request, "lecture.message.assignment.question.add.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 과제 응시생 목록 조회
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/listStd")
	public String listStd( AssignmentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		Integer declsNo = vo.getDeclsNo();
		String  userNm = vo.getUserNm();

		vo = assignmentService.viewAssignment(vo).getReturnVO();
		vo.setDeclsNo(declsNo);
		vo.setUserNm(userNm);

		vo.setCurPage(Integer.parseInt(StringUtil.nvl(request.getParameter("curPage"),"1")));
		vo.setListScale(Integer.parseInt(StringUtil.nvl(request.getParameter("listScale"),"20")));
		vo.setPageScale(10);
		ProcessResultListVO<AssignmentSendVO> resultList = assignmentService.listSendPageing(vo);
		request.setAttribute("assignmentSendListVO", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());
		request.setAttribute("vo", vo);
		return "mng/lecture/assignment/asmt_std_list_div";
	}

	/**
	 * 과제 평가 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/rateAsmtForm")
	public String rateAsmtForm( AssignmentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		AssignmentSendVO assignmentSendVO = vo.getAssignmentSendVO();
		AssignmentSubVO assignmentSubVO = vo.getAssignmentSubVO();
		//-- 학습자 정보 조회
		StudentVO studentVO = new StudentVO();
		studentVO.setStdNo(vo.getStdNo());
		studentVO = studentService.viewStudent(studentVO).getReturnVO();
		request.setAttribute("studentVO", studentVO);

		//-- 학습자 답안 조회
		assignmentSendVO.setCrsCreCd(vo.getCrsCreCd());
		assignmentSendVO.setStdNo(vo.getStdNo());
		assignmentSendVO.setAsmtSn(vo.getAsmtSn());
		assignmentSendVO = assignmentService.viewSend(assignmentSendVO).getReturnVO();
		request.setAttribute("assignmentSendVO", vo);
		//-- 과제 문제 정보 가죠오기

		vo = assignmentService.viewAssignment(vo).getReturnVO();
		request.setAttribute("vo", vo);

		//과제 후보정보 조회
		assignmentSubVO.setCrsCreCd(assignmentSendVO.getCrsCreCd());
		assignmentSubVO.setAsmtSn(assignmentSendVO.getAsmtSn());
		assignmentSubVO.setAsmtSubSn(assignmentSendVO.getAsmtSubSn());

		assignmentSubVO = assignmentService.viewSub(assignmentSubVO).getReturnVO();
		request.setAttribute("assignmentSubVO", assignmentSubVO);
		request.setAttribute("assignmentSendVO", assignmentSendVO);
		return "mng/lecture/assignment/asmt_rate_write_pop";
	}



	/**
	 * 과제 평가 등록
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/rateAsmt")
	public String rateAsmt( AssignmentSendVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
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
	@RequestMapping(value="/addScore")
	public String addScore( AssignmentSendVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

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
	@RequestMapping(value="/addScoreAll")
	public String addScoreAll( AssignmentSendVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
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
	 * 과제 제출 폼 임시.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addSendForm")
	public String addSendForm( AssignmentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		//-- 과제에 대한 정보를 찾아 attribute에 담는다.
		vo = assignmentService.viewAssignment(vo).getReturnVO();
		request.setAttribute("vo", vo);

		//-- 과제 후보 리스트를 가져온다.
		List<AssignmentSubVO> subList = assignmentService.listSub(vo).getReturnList();
		request.setAttribute("subList", subList);
		request.setAttribute("stdNo", vo.getStdNo());
		request.setAttribute("daumeditor", "Y");
		request.setAttribute("uploadify", "Y");

		return "mng/lecture/assignment/asmt_send_write_pop";
	}

	/**
	 * 제출제 설정
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/removeSend")
	public String removeSend(AssignmentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		AssignmentSendVO assignmentSendVO = new AssignmentSendVO();
		assignmentSendVO.setCrsCreCd(vo.getCrsCreCd());
		assignmentSendVO.setAsmtSn(vo.getAsmtSn());
		assignmentSendVO.setUserNoObj(request.getParameter("userNoObj"));
		assignmentSendVO.setUserNoObjArr(StringUtil.split(assignmentSendVO.getUserNoObj(), ","));
		assignmentSendVO.setRegNo(vo.getRegNo());
		
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

		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 성적 다운로드
	 * @author twkim
	 * @date 2013. 11. 25.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception ActionForward
	 */
	@RequestMapping(value="/excelDownload")
	public String excelDownload( AssignmentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		LogPrnLogVO printLogVO = new LogPrnLogVO();
		printLogVO.setUserNo(UserBroker.getUserNo(request));
		printLogVO.setUserNm(UserBroker.getUserName(request));
		printLogVO.setPrnDoc("과제 성적 엑셀 출력");
		printLogVO.setParam(vo.toString());
		logPrnLogService.add(printLogVO);

		response.setHeader("Content-Disposition", "attachment;filename=ResearhCourseList_"+DateTimeUtil.getCurrentString()+".xlsx;");
		response.setHeader( "Content-Transfer-Coding", "binary" );
		response.setContentType("application/octet-stream");

		assignmentService.listReshCourseExcelDownload(vo, response.getOutputStream());

		return null;
	}

	/**
	 * 성적 엑셀 업로드 폼
	 * @author twkim
	 * @date 2013. 11. 25.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception ActionForward
	 */
	@RequestMapping(value="/addExcelForm")
	public String addExcelForm(AssignmentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		request.setAttribute("vo", vo);
		request.setAttribute("uploadify", "Y");

		return "mng/lecture/assignment/excel_upload_pop";
	}

	/**
	 * 성적 엑셀 업로드
	 * @author twkim
	 * @date 2013. 11. 25.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception ActionForward
	 */
	@RequestMapping(value="/addExcel")
	public String addExcel(AssignmentSendVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String fileName = StringUtil.nvl(request.getParameter("fileName"));
		String filePath = StringUtil.nvl(request.getParameter("filePath"));

		return JsonUtil
				.responseJson( response, assignmentService.addExcelUpload(vo, fileName, filePath));
	}

	/**
	 * 과제 후보 미리보기
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/viewQstnPop")
	public String viewQstnPop( AssignmentSubVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		//-- 과제 후보 정보를 찾아 Form에 셋팅
		vo = assignmentService.viewSub(vo).getReturnVO();
		request.setAttribute("vo", vo);
		request.setAttribute("fileupload", "Y");
		return "mng/lecture/assignment/asmt_qstn_view_pop";
	}

	private String getEditorType(HttpServletRequest request) {
		String forwardUrl = "mng/lecture/assignment/asmt_qstn_write_pop";
		request.setAttribute("fileupload", "Y");
		//-- 에디터 사용여부를 판단하여 해당 Webeditor를 사용하도록 한다.
		//-- BBS_INFO의 editorUseYn은 더이상 사용하지 않음.
		if("Y".equals(Constants.WEBEDITOR_YSEYN)) {
			if("DAUMEDITOR".equals(Constants.WEBEDITOR_NAME)) {
				request.setAttribute("daumeditor", "Y");
				forwardUrl = "mng/lecture/assignment/asmt_qstn_write_daumeditor_pop";
			} else if("SUMMERNOTE".equals(Constants.WEBEDITOR_NAME)) {
				request.setAttribute("summernote", "Y");
				forwardUrl = "mng/lecture/assignment/asmt_qstn_write_summernote_pop";
			}
		}
		return forwardUrl;
	}
	
	/**
	 * AI 과제 후보 등록 폼
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

		List<SysCodeVO> asmtDevLangCdList = sysCodeMemService.getSysCodeList("DEV_LANG_CD", UserBroker.getLocaleKey(request));
        List<SysCodeVO> asmtDiffLvlCdList = sysCodeMemService.getSysCodeList("DIFF_LVL_CD", UserBroker.getLocaleKey(request));
		
        AssignmentVO aVO= new AssignmentVO();
        aVO.setCrsCreCd(vo.getCrsCreCd());
        aVO.setAsmtSn(vo.getAsmtSn());
        request.setAttribute("fileupload", "Y");
        request.setAttribute("summernote", "Y");
        request.setAttribute("aVO", assignmentService.viewAssignment(aVO).getReturnVO());
		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "A");
		request.setAttribute("asmtDevLangCdList", asmtDevLangCdList);
		request.setAttribute("asmtDiffLvlCdList", asmtDiffLvlCdList);
		
		//gpt정보
		request.setAttribute("gptUrl", GPT_URL);
		request.setAttribute("gptVer", GPT_VERSION);
		request.setAttribute("gptKey", GPT_KEY);
		return "mng/lecture/assignment/asmt_AI_qstn_write_summernote_pop";
	}
	
	/**
	 * 과제 평가 폼(코딩학습)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/rateCodeAsmtForm")
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
		
		
		return "mng/lecture/assignment/code_asmt_rate_write_pop";
	}
	
	/**
	 * 코딩 과제 문제 조회(관리자)
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

		ProcessResultVO<AssignmentSubVO> resultVO = assignmentService.viewSub(vo);
		
		//제출내역
		AssignmentSendVO sendVO = new AssignmentSendVO();
		sendVO.setAsmtSn(vo.getAsmtSn());
		sendVO.setAsmtSubSn(vo.getAsmtSubSn());
		sendVO.setCrsCreCd(vo.getCrsCreCd());
		sendVO.setStdNo(vo.getStdNo());
		sendVO = assignmentService.selectTestResult(sendVO).getReturnVO();
		
		if(sendVO != null) {
			resultVO.getReturnVO().setSendTitle(sendVO.getSendTitle());
			resultVO.getReturnVO().setSendCts(sendVO.getSendCts());
			resultVO.getReturnVO().setGptResult(sendVO.getGptResult());
			resultVO.getReturnVO().setScore(sendVO.getScore());
		}
		
		//제한사항
		AssignmentSubConstVO ascVO = new AssignmentSubConstVO();
		ascVO.setAsmtSn(vo.getAsmtSn());
		ascVO.setAsmtSubSn(vo.getAsmtSubSn());
		ascVO.setCrsCreCd(vo.getCrsCreCd());
		resultVO.getReturnVO().setAsmtSubConstVOList(assignmentService.listSubConst(ascVO).getReturnList()); 
		
		return JsonUtil.responseJson(response, resultVO);
	}
	
	/**
	 * 코딩 과제 문제별 점수 저장(관리자)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	@RequestMapping(value="/saveAsmtSubScore")
	public String saveAsmtSubScore(AssignmentSendVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception{
		commonVOProcessing(vo, request);

		ProcessResultVO<AssignmentSendVO> resultVO = assignmentService.saveAsmtSubScore(vo);
		
		return JsonUtil.responseJson(response, resultVO);
	}
	
	/**
	 * 오프라인 과제 점수 등록
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/addOffScore")
	public String addOffScore( AssignmentSendVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

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
	@RequestMapping(value="/addOffScoreAll")
	public String addOffScoreAll( AssignmentSendVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
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
}
