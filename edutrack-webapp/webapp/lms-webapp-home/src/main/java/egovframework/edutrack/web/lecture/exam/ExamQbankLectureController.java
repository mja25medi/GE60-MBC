package egovframework.edutrack.web.lecture.exam;

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

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.course.exambank.service.ExamQbankCtgrVO;
import egovframework.edutrack.modules.course.exambank.service.ExamQbankQstnVO;
import egovframework.edutrack.modules.course.exambank.service.ExamQbankService;


/**
 * 시험 문제은행 컨트롤
 * @author JNOTE
 *
 */
@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/lec/examQbank")
public class ExamQbankLectureController
		extends GenericController {

	@Autowired @Qualifier("examQbankService")
	private ExamQbankService	examQbankService;

	/**
	 * 시험 문제은행 관리 메인
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/mainExamQbank")
	public String mainExamQbank(Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception{

		return "exam_qbank_main";
	}

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
		return "home/lecture/exam/teacher/exam_qbank_qstn_list_div";
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
	public String viewCategory(ExamQbankCtgrVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		commonVOProcessing(vo, request);

		String sbjCd = StringUtil.nvl(request.getParameter("sbjCd"));
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
	public String addCategory(ExamQbankCtgrVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		commonVOProcessing(vo, request);

		return JsonUtil
			.responseJson( response, examQbankService.addCtgr(vo));
	}

	/**
	 * 시험 문제은행 분류 수정
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/editCategory")
	public String editCategory(ExamQbankCtgrVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		commonVOProcessing(vo, request);

		return JsonUtil
			.responseJson( response, examQbankService.editCtgr(vo));
	}

	/**
	 * 시험 문제은행 분류 삭제
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/deleteCategory")
	public String deleteCategory(ExamQbankCtgrVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		commonVOProcessing(vo, request);

		return JsonUtil
			.responseJson( response,
					examQbankService.deleteCtgr(vo.getCtgrCd()));
	}

	/**
	 * 시험 문제은행 문제 목록 조회
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/listQuestion")
	public String listQuestion(ExamQbankQstnVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		commonVOProcessing(vo, request);

		String sbjCd = StringUtil.nvl(request.getParameter("sbjCd"));
		String ctgrCd = StringUtil.nvl(request.getParameter("ctgrCd"));
		String parCtgrCd = "";
		String qstnType = "";
		String searchKey = "";
		return JsonUtil
			.responseJson(response, examQbankService.listQstn(ctgrCd, parCtgrCd, qstnType, searchKey));
	}

	/**
	 * 시험 문제은행 문제 정보 조회
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/viewQuestion")
	public String viewQuestion(ExamQbankQstnVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		commonVOProcessing(vo, request);

		vo.setSbjCd(StringUtil.nvl(request.getParameter("sbjCd")));
		vo.setCtgrCd(StringUtil.nvl(request.getParameter("ctgrCd")));
		vo.setQstnSn(Integer.parseInt(request.getParameter("qstnSn")));


		return JsonUtil
			.responseJson(response, examQbankService.viewQstn(vo));
	}

	/**
	 * 문제은행 문제 상세보기
	 * @author twkim
	 * @date 2013. 11. 14.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	@RequestMapping(value="/readQbankQstnPop")
	public String readQbankQstnPop(ExamQbankQstnVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		commonVOProcessing(vo, request);

		Integer examSn = StringUtil.nvl(request.getParameter("examSn"),0);
		request.setAttribute("examSn", examSn);

		//문제은행 문제 정보 조회
		request.setAttribute("vo", examQbankService.viewQstn(vo).getReturnVO());

		return "home/lecture/exam/teacher/exam_qbank_read_pop";
	}

	/**
	 * 시험 문제은행 문제 등록
	 *
	 * @return  ProcessResultVO
	 */
//	@AjaxAction
//	@AjaxMessage("문제 등록을")
//	public String addQuestion(ActionMapping mapping,
//			ActionForm form, HttpServletRequest request,
//			HttpServletResponse response){
//
//		ExamQbankForm examQbankForm = (ExamQbankForm)form;
//		ExamQbankQuestionVO examQbankQstnVO = examQbankForm.getExamQbankQuestionVO();
//
//		examQbankQstnVO.setQstnCts( HtmlCleaner.cleanScript(examQbankQstnVO.getQstnCts()) );
//
//
//		//List<Integer> txAttachFiles = convertStringDelimToIntegerList(examQbankForm.getTxAttachFiles());
//		List<Integer> txAttachImages = convertStringDelimToIntegerList(examQbankForm.getTxAttachImages());
//
//		return JsonUtil
//			.responseJsonWithResult(request, response, examQbankService.addQuestion(examQbankQstnVO,txAttachImages));
//	}

	/**
	 * 시험 문제은행 문제 수정
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/editQuestion")
	public String editQuestion(ExamQbankQstnVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		commonVOProcessing(vo, request);

		return JsonUtil
			.responseJson( response, examQbankService.editQstn(vo));
	}

	/**
	 * 시험 문제은행 문제 삭제
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/deleteQuestion")
	public String deleteQuestion(ExamQbankQstnVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		commonVOProcessing(vo, request);

		return JsonUtil
			.responseJson(response,
					examQbankService.deleteQstn(vo));
	}

	/**
	 * 문제파일 정보
	 *
	 * @return ProcessResultVO
	 */
//	@AjaxAction
//	public String listFileQuestion( vo, Map commandMap, ModelMap model,
//									   HttpServletRequest request, HttpServletResponse response) {
//
//		ExamQbankForm examQbankForm = (ExamQbankForm)form;
//		ExamQbankQuestionVO examQbankQstnVO = examQbankForm.getExamQbankQuestionVO();
//
//		if(ValidationUtils.isNull(examQbankQstnVO.getSearchValue()))
//			return JsonUtil.responseJson(response, new Object());
//
//		//-- 등록인 경우 빈 값을 넘겨줌.
//		if(examQbankQstnVO.getQstnSn()==0) {
//			if(examQbankQstnVO.getSearchValue().equals("image")) {
//				return JsonUtil.responseJson(response,
//						SysFileVOUtil.toJsonAttachImagesForEditorWrapper(new AssignmentQbankQuestionVO().getAttachImages()));
//			}
//		}
//
//		examQbankQstnVO = examQbankService.listAttachFiles(examQbankQstnVO).getReturnVO(); // 첨부파일 정보만 가진 VO
//
//		if(examQbankQstnVO.getSearchValue().equals("image")) {
//			// 다음에디터 첨부파일 관리 형태로 한번 더 감싸서 반환한다.
//			return JsonUtil.responseJson(response,
//					SysFileVOUtil.toJsonAttachImagesForEditorWrapper(examQbankQstnVO.getAttachImages()));
//		}
//		throw new AjaxIllegalParameterException("파일 정보를 조회할 수 없습니다.");
//	}
	/**
	 * 구분자(!@!)를 사용해서 구성된 배열 항목의 단일 문자열을 {@code List<Integer>} 형태로 변환해서 반환.
	 * 숫자로 변환하던중 Exception이 발생하는 항목은 무시하고 정상 처리된 항목만 반환한다.
	 * @param source
	 * @return
	 */
	private List<Integer> convertStringDelimToIntegerList(String source) {
		String[] strArray = source.split("!@!");
		List<Integer> intList = new ArrayList<Integer>();
		for (String str : strArray) {
			try {
				Integer intValue = Integer.parseInt(str);
				intList.add(intValue); // Integer 파싱에 실패한 값은 저장되지 않는다.
			} catch (NumberFormatException ignore) {
			}
		}
		return intList;
	}
}

