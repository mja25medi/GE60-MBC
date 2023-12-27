package egovframework.edutrack.web.manage.course;

import java.util.ArrayList;
import java.util.HashMap;
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
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.service.SysCodeMemService;
import egovframework.edutrack.comm.util.web.HtmlCleaner;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.course.exambank.service.ExamQbankCtgrVO;
import egovframework.edutrack.modules.course.exambank.service.ExamQbankQstnVO;
import egovframework.edutrack.modules.course.exambank.service.ExamQbankService;
import egovframework.edutrack.modules.system.code.service.SysCodeVO;


@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/mng/course/examQbank")
public class CourseExamBankManageController extends GenericController{

	@Autowired @Qualifier("examQbankService")
	private ExamQbankService	examQbankService;

	@Autowired @Qualifier("sysCodeMemService")
	private SysCodeMemService			sysCodeMemService;
	/**
	 * 시험 문제은행 관리 메인
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/examQbankMain")
	public String examQbankMain( ExamQbankCtgrVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {
		commonVOProcessing(vo, request);
		request.setAttribute("paging", "Y");
		request.setAttribute("vo", vo);
		return "mng/course/exambank/exam_qbank_main";
	}

	/**
	 * 시험 문제은행 분류 등록 폼
	 */
	@RequestMapping(value="/addFormCategory")
	public String addFormCategory( ExamQbankCtgrVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		request.setAttribute("gubun", "A");
		request.setAttribute("vo", vo);
		return "mng/course/exambank/exam_qbank_write_pop";
	}
	

	/**
	 * 시험 문제은행 분류 등록 폼
	 */
	@RequestMapping(value="/subEditFormCategory")
	public String subEditFormCategory( ExamQbankCtgrVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		ProcessResultVO<ExamQbankCtgrVO> resultVO = examQbankService.viewCtgr(vo.getCtgrCd());
		
		request.setAttribute("gubun", "E");
		request.setAttribute("subAddCategory", "Y");
		request.setAttribute("vo", resultVO.getReturnVO());
		return "mng/course/exambank/exam_qbank_write_pop";
	}
	
	
	
	/**
	 * 시험 문제은행 분류 수정 폼
	 */
	@RequestMapping(value="/editFormCategory")
	public String editFormCategory( ExamQbankCtgrVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		ProcessResultVO<ExamQbankCtgrVO> resultVO = examQbankService.viewCtgr(vo.getCtgrCd());
		request.setAttribute("gubun", "E");
		request.setAttribute("vo", resultVO.getReturnVO());
		return "mng/course/exambank/exam_qbank_write_pop";
	}
	
	/**
	 * 시험 문제은행 분류 등록 폼
	 */
	@RequestMapping(value="/subAddFormCategory")
	public String subAddFormCategory( ExamQbankCtgrVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		//상위 분류 조회
		ExamQbankCtgrVO returnVO = examQbankService.viewCtgr(vo.getCtgrCd()).getReturnVO();
		
		request.setAttribute("gubun", "A");
		request.setAttribute("subAddCategory", "Y");
		request.setAttribute("parVO", returnVO);
		return "mng/course/exambank/exam_qbank_write_pop";
	}

	/**
	 * 시험 문제은행 분류 목록 조회
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/listCategory")
	public String listCategory(ExamQbankCtgrVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		ProcessResultListVO<ExamQbankCtgrVO> resultVOList = examQbankService.listCtgr(vo);
		
		return JsonUtil.responseJson(response, resultVOList);
	}

	/**
	 * 하위 분류 목록 조회
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/subListCategory")
	public String subListCategory( Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String parCtgrCd = StringUtil.nvl(request.getParameter("parCtgrCd"));
		String useYn = StringUtil.nvl(request.getParameter("useYn"));
		
		ExamQbankCtgrVO iExamQbankCtgrVO = new ExamQbankCtgrVO();
		iExamQbankCtgrVO.setUseYn(useYn);
		iExamQbankCtgrVO.setParCtgrCd(parCtgrCd);
		return JsonUtil.responseJson(response, examQbankService.subListCtgrUseY(iExamQbankCtgrVO));
	}

	
	/**
	 * 시험 문제은행 하위 분류 목록 조회
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/subList")
	public String subList(ExamQbankCtgrVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String parCtgrCd = StringUtil.nvl(request.getParameter("parCtgrCd"));
		Map searchVO = new HashMap<String, String>();
		Map svo = new HashMap<String, String>();
		if(request.getSession().getAttribute("SearchVO") != null) {
			searchVO = (Map) request.getSession().getAttribute("SearchVO");
		}
		svo.put("searchCd", parCtgrCd);
		searchVO.put("ExamQbankMain", svo);
		request.getSession().setAttribute("SearchVO", searchVO);
		
		ProcessResultListVO<ExamQbankCtgrVO> resultVOList = examQbankService.subListCtgr(parCtgrCd);
		request.setAttribute("vo", resultVOList.getReturnList());
		
		return "mng/course/exambank/exam_qbank_list_div";
	}
	
	/**
	 * 시험 문제은행 분류 정보 조회
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/viewCategory")
	public String viewCategory( Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		String ctgrCd = StringUtil.nvl(request.getParameter("ctgrCd"));

		return JsonUtil
			.responseJson(response, examQbankService.viewCtgr(ctgrCd));
	}


	/**
	 * 시험 문제은행 분류 등록
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/addCategory")
	public String addCategory( ExamQbankCtgrVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		ProcessResultVO<ExamQbankCtgrVO> resultVO = examQbankService.addCtgr(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "course.message.exambank.category.add.success"));
		} else {
			resultVO.setMessage(getMessage(request, "course.message.exambank.category.add.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 시험 문제은행 분류 수정
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/editCategory")
	public String editCategory( ExamQbankCtgrVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultVO<ExamQbankCtgrVO> resultVO = examQbankService.editCtgr(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "course.message.exambank.category.edit.success"));
		} else {
			resultVO.setMessage(getMessage(request, "course.message.exambank.category.edit.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}
	
	/**
	 * 시험 문제은행 분류 등록
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/subAddCategory")
	public String subAddCategory( ExamQbankCtgrVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		ProcessResultVO<ExamQbankCtgrVO> resultVO = examQbankService.addCtgr(vo);
		
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "course.message.exambank.category.add.success"));
		} else {
			resultVO.setMessage(getMessage(request, "course.message.exambank.category.add.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}
	
	/**
	 * 시험 문제은행 분류 수정
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/subEditCategory")
	public String subEditCategory( ExamQbankCtgrVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultVO<ExamQbankCtgrVO> resultVO = examQbankService.editCtgr(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "course.message.exambank.category.edit.success"));
		} else {
			resultVO.setMessage(getMessage(request, "course.message.exambank.category.edit.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}
	
	/**
	 * 시험 문제은행 분류 삭제
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/deleteCategory")
	public String deleteCategory( ExamQbankCtgrVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		ProcessResultVO<ExamQbankCtgrVO> resultVO = examQbankService.deleteCtgr(vo.getCtgrCd());
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "course.message.exambank.category.delete.success"));
		} else {
			resultVO.setMessage(getMessage(request, "course.message.exambank.category.delete.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	
	
	
	
	/**
	 * 시험 문제은행 관리 메인
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/questionMain")
	public String questionMain( ExamQbankCtgrVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {
		commonVOProcessing(vo, request);
		request.setAttribute("paging", "Y");
		request.setAttribute("vo", vo);
		return "mng/course/exambank/exam_qbank_qstn_main";
	}
	
	
	/**
	 * 시험 문제은행 문제 목록 조회
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/listQuestion")
	public String listQuestion( ExamQbankQstnVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String parCtgrCd = StringUtil.nvl(request.getParameter("parCtgrCd"));
		String ctgrCd = StringUtil.nvl(request.getParameter("ctgrCd"));
		String qstnType = StringUtil.nvl(request.getParameter("qstnType"));
		String searchKey = StringUtil.nvl(request.getParameter("searchKey"));
		String useYn = StringUtil.nvl(request.getParameter("useYn"));
		
		Map searchVO = new HashMap<String, Object>();
		Map svo = new HashMap<String, String>();
		if(request.getSession().getAttribute("SearchVO") != null) {
			searchVO = (Map) request.getSession().getAttribute("SearchVO");
		}
		svo.put("searchCd", ctgrCd);
		svo.put("searchCd1", parCtgrCd);
		svo.put("searchCd2", qstnType);
		svo.put("searchCd3", searchKey);
		searchVO.put("ExamQbankQstnMain", svo);
		request.getSession().setAttribute("SearchVO", searchVO);

//		ProcessResultListVO<ExamQbankQstnVO> resultList = examQbankService.listQstnPageing(vo, vo.getCurPage(), vo.getListScale() );
		ProcessResultListVO<ExamQbankQstnVO> resultList = examQbankService.listQstnUseY(ctgrCd, parCtgrCd, qstnType, searchKey, useYn);
		request.setAttribute("examQbankQstnList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());
		return "mng/course/exambank/exam_qbank_qstn_list_div";
	}

	/**
	 * 시험 문제은행 문제 등록 폼
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/addFormQuestion")
	public String addFormQuestion( ExamQbankQstnVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		List<SysCodeVO> qstnTypeList = sysCodeMemService.getSysCodeList("EXAM_QSTN_TYPE");
		//-- 내장형 시험인 경우 템프 문제를 만들어 진행함.

		String forwardUrl = this.getEditorType();

		request.setAttribute("vo", vo);
		request.setAttribute("qstnTypeList", qstnTypeList);
		request.setAttribute("summernote", "A");
		request.setAttribute("uploadify", "A");
		request.setAttribute("gubun", "A");
		return forwardUrl;
	}

	/**
	 * 시험 문제은행 문제 수정 폼
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/editFormQuestion")
	public String editFormQuestion( ExamQbankQstnVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String forwardUrl = this.getEditorType();
		String searchKey = vo.getSearchKey();
		vo = examQbankService.viewQstn(vo).getReturnVO();
		request.setAttribute("summernote", "A");
		request.setAttribute("uploadify", "A");
		request.setAttribute("vo", vo);
		request.setAttribute("searchKey", searchKey);
		request.setAttribute("gubun", "E");
		return forwardUrl;
	}

	/**
	 * 시험 문제은행 문제 정보 조회
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/viewQuestion")
	public String viewQuestion( ExamQbankQstnVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		return JsonUtil
			.responseJson(response, examQbankService.viewQstn(vo));
	}

	/**
	 * 시험 문제은행 문제 등록
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/addQuestion")
	public String addQuestion( ExamQbankQstnVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		vo.setQstnCts( HtmlCleaner.cleanScript(vo.getQstnCts()) );

		ProcessResultVO<ExamQbankQstnVO> resultVO = examQbankService.addQstn(vo);
		
		ExamQbankQstnVO iExamQbankQstnVO = new ExamQbankQstnVO();
		iExamQbankQstnVO.setCtgrCd(vo.getCtgrCd());
		iExamQbankQstnVO.setParCtgrCd(vo.getParCtgrCd());
		
		if(resultVO.getResult() > 0) {
			iExamQbankQstnVO.setQstnType(vo.getQstnType());
			iExamQbankQstnVO.setSearchKey(vo.getSearchKey());
			resultVO.setReturnVO(iExamQbankQstnVO);
			resultVO.setMessage(getMessage(request, "lecture.message.exam.question.add.success"));
		} else {
			resultVO.setMessage(getMessage(request, "lecture.message.exam.question.add.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 시험 문제은행 문제 수정
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/editQuestion")
	public String editQuestion( ExamQbankQstnVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ExamQbankQstnVO iExamQbankQstnVO = new ExamQbankQstnVO();
		iExamQbankQstnVO.setCtgrCd(vo.getParCtgrCd());
		iExamQbankQstnVO.setParCtgrCd(vo.getCtgrCd());
		
		ProcessResultVO<ExamQbankQstnVO> resultVO = examQbankService.editQstn(vo);
		if(resultVO.getResult() > 0) {
			iExamQbankQstnVO.setQstnType(vo.getQstnType());
			iExamQbankQstnVO.setSearchKey(vo.getSearchKey());
			resultVO.setReturnVO(iExamQbankQstnVO);
			resultVO.setMessage(getMessage(request, "lecture.message.exam.question.edit.success"));
		} else {
			resultVO.setMessage(getMessage(request, "lecture.message.exam.question.edit.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 시험 문제은행 문제 삭제
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/deleteQuestion")
	public String deleteQuestion( ExamQbankQstnVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		ExamQbankQstnVO iExamQbankQstnVO = new ExamQbankQstnVO();
		iExamQbankQstnVO.setCtgrCd(vo.getCtgrCd());
		iExamQbankQstnVO.setParCtgrCd(vo.getParCtgrCd());
		
		ProcessResultVO<ExamQbankQstnVO> resultVO = examQbankService.deleteQstn(vo);
		
		if(resultVO.getResult() > 0) {
			iExamQbankQstnVO.setQstnType(vo.getQstnType());
			iExamQbankQstnVO.setSearchKey(vo.getSearchKey());
			resultVO.setReturnVO(iExamQbankQstnVO);
			resultVO.setMessage(getMessage(request, "lecture.message.exam.question.delete.success"));
		} else {
			resultVO.setMessage(getMessage(request, "lecture.message.exam.question.delete.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	private String getEditorType() {
//		String forwardUrl = "mng/course/exambank/exam_qbank_write_pop";
		//-- 에디터 사용여부를 판단하여 해당 Webeditor를 사용하도록 한다.
		//-- BBS_INFO의 editorUseYn은 더이상 사용하지 않음.
/*		if("Y".equals(Constants.WEBEDITOR_YSEYN)) {
			if("DAUMEDITOR".equals(Constants.WEBEDITOR_NAME)) forwardUrl = "mng/course/exambank/exam_qbank_write_daumeditor_pop";
			else if("SUMMERNOTE".equals(Constants.WEBEDITOR_NAME)) forwardUrl = "mng/course/exambank/exam_qbank_write_summernote_pop";
		}*/
		String forwardUrl = "mng/course/exambank/exam_qbank_write_summernote_pop";
		return forwardUrl;
	}


	/**
	 * 시험 문제은행 문제 등록폼 메인
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/editQstnFormStep2")
	public String editQstnFormStep2( ExamQbankQstnVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		vo = examQbankService.viewQstn(vo).getReturnVO();

		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "E");
		return "mng/course/exambank/exam_qbank_write_step2_pop";

	}



	/**
	 * 시험 문제은행 문제 정보 조회
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/previewPaperPop")
	public String previewPaperPop( ExamQbankQstnVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		

		ExamQbankQstnVO examQbankQstnVO = examQbankService.viewQstn(vo).getReturnVO();

		request.setAttribute("vo", examQbankQstnVO);
		return "mng/course/exambank/exam_qbank_preview_pop";
	}


	/**
	 * 시험 문제은행 문제 엑셀 폼
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/addQuestionExcelPop")
	public String addQuestionExcelPop( ExamQbankQstnVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "A");
		request.setAttribute("fileupload", "A");
		return "mng/course/exambank/exam_qbank_write_excel_pop";
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
	public String sampleExcelQuestion ( ExamQbankQstnVO vo, Map commandMap, ModelMap model,
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
		titles.put("item1", getMessage(request, "course.title.exambank.item1"));
		titles.put("item2", getMessage(request, "course.title.exambank.item2"));
		titles.put("item3", getMessage(request, "course.title.exambank.item3"));
		titles.put("item4", getMessage(request, "course.title.exambank.item4"));
		titles.put("item5", getMessage(request, "course.title.exambank.item5"));
		titles.put("rightansr", getMessage(request, "course.title.exambank.rightansr"));
		titles.put("comment", getMessage(request, "course.title.exambank.comment"));

		response.setHeader("Content-Disposition", "attachment;filename=question_item_sample.xlsx;");
		response.setHeader( "Content-Transfer-Coding", "binary" );
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		try {
			examQbankService.sampleExcelDownload(titles, response.getOutputStream(),orgCd);
		} catch (Exception e) {
			log.error("IOException occurred");
		}
		return null;
	}

	/**
	 * 시험 문제은행 엑셀 업로드
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/excelUploadCheckPop")
	public String excelUploadCheck( ExamQbankQstnVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String fileName = StringUtil.nvl(request.getParameter("fileName"));
		String filePath = StringUtil.nvl(request.getParameter("filePath"));

		ProcessResultListVO<ExamQbankQstnVO> resultList = null;
		resultList = examQbankService.excelUploadValidationCheck(fileName, filePath);
		request.setAttribute("qstnList", resultList.getReturnList());
		request.setAttribute("fileupload", "A");
		request.setAttribute("vo", vo);
		return "mng/course/exambank/exam_qbank_write_excel_validate";
	}


	/**
	 * 설문 은행 문제 엑셀 업로드 , 단건 체크
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/questionUploadCheck")
	public String questionUploadCheck( ExamQbankQstnVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) {
		commonVOProcessing(vo, request);

		String errorCode = "";
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
			if(ValidationUtils.isEmpty(vo.getView1())){
				errorCode += "|"+"EMPTYVIEW1";
			}
			if(ValidationUtils.isEmpty(vo.getView2())){
				errorCode += "|"+"EMPTYVIEW2";
			}
			if(ValidationUtils.isEmpty(vo.getView3())){
				errorCode += "|"+"EMPTYVIEW3";
			}
			if(ValidationUtils.isEmpty(vo.getView4())){
				errorCode += "|"+"EMPTYVIEW4";
			}
			if(!StringUtil.isNumber(vo.getRgtAnsr())){
				errorCode += "|"+"TYPERGTANSR";
			} else {
				if(Integer.parseInt(vo.getRgtAnsr()) > 5){
					errorCode += "|"+"TYPERGTANSR";
				}
				if(ValidationUtils.isEmpty(vo.getView5()) && Integer.parseInt(vo.getRgtAnsr()) > 4 ){
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
	public String questionUpload( ExamQbankQstnVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) {
		commonVOProcessing(vo, request);
		
		String orgCd = UserBroker.getUserOrgCd(request);
		Integer qstnSn = Integer.parseInt(StringUtil.nvl(request.getParameter("qstnSn"), "0"));
		String sbjCd = StringUtil.nvl(request.getParameter("sbjCd"),"");
		String ctgrCd = StringUtil.nvl(request.getParameter("ctgrCd"),"");
		String[] chk = request.getParameterValues("chk");
		String[] lineNo = request.getParameterValues("lineNo");
		String[] qstnType = request.getParameterValues("qstnType");
		String[] title = request.getParameterValues("title");
		String[] qstnCts = request.getParameterValues("qstnCts");
		String[] view1 = request.getParameterValues("view1");
		String[] view2 = request.getParameterValues("view2");
		String[] view3 = request.getParameterValues("view3");
		String[] view4 = request.getParameterValues("view4");
		String[] view5 = request.getParameterValues("view5");
		String[] rgtAnsr = request.getParameterValues("rgtAnsr");
		String[] qstnExpl = request.getParameterValues("qstnExpl");

		List<ExamQbankQstnVO> questionBankList = new ArrayList<ExamQbankQstnVO>();
		for(int i=0 ; i < qstnType.length; i++) {
			//-- 체크된 사용자만 등록함.
			for(int j=0; j < chk.length; j++) {

				if(lineNo[i].equals(chk[j])) {
					ExamQbankQstnVO qstnVO = new ExamQbankQstnVO();

					qstnVO.setOrgCd(orgCd);
					qstnVO.setQstnSn(qstnSn);
					qstnVO.setSbjCd(sbjCd);
					qstnVO.setCtgrCd(ctgrCd);
					qstnVO.setTitle(title[i]);
					qstnVO.setQstnType(qstnType[i]);
					qstnVO.setQstnCts(qstnCts[i]);
					if("K".equals(qstnType[i])){
						qstnVO.setView1(view1[i]);
						qstnVO.setView2(view2[i]);
						qstnVO.setView3(view3[i]);
						qstnVO.setView4(view4[i]);
						qstnVO.setView5(view5[i]);
						qstnVO.setRgtAnsr(rgtAnsr[i]);
					} else if("S".equals(qstnType[i])){
						qstnVO.setRgtAnsr(rgtAnsr[i].toUpperCase());
					} else if("D".equals(qstnType[i])){
						String an = rgtAnsr[i];
						if(an.endsWith("|")) {
							an = an.substring(0, an.length()-1);
						}
						qstnVO.setRgtAnsr(an);
					} else {
						qstnVO.setRgtAnsr(rgtAnsr[i]);
					}
					qstnVO.setQstnExpl(qstnExpl[i]);

					questionBankList.add(qstnVO);
				}
			}
		}
		ProcessResultVO<ExamQbankQstnVO> resultVO = new ProcessResultVO<ExamQbankQstnVO>().setReturnVO(vo);
		try {
			examQbankService.addQstnBatch(questionBankList);
			resultVO.setMessage(getMessage(request, "lecture.message.exam.question.add.success"));
			resultVO.setResult(1);
		} catch (Exception e) {
			resultVO.setMessage(getMessage(request, "lecture.message.exam.question.add.failed"));
			resultVO.setResult(-1);
		}

		return JsonUtil.responseJson(response, resultVO);

	}

}
