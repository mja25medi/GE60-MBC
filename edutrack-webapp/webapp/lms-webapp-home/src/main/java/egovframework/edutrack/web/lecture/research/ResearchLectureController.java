package egovframework.edutrack.web.lecture.research;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.edutrack.comm.web.GenericController;

/**
 * 설문 컨트롤
 * @author JNOTE
 *
 */

@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/lec/research")
public class ResearchLectureController extends GenericController {

//	@Autowired
//	private IResearchService		researchService;
//
//	@Autowired
//	private IPrintLogService		printLogService;
//
//	@Autowired
//	private IResearchBankService 	researchBankService;
//
//	@Autowired
//	private ICodeMemService 			codeMemService;
//
//	/**
//	 * 설문 관리 메인
//	 * @param mapping
//	 * @param form
//	 * @param request
//	 * @param response
//	 * @return
//	 */
//	public String mainResearch( vo, Map commandMap, ModelMap model,
//			HttpServletRequest request, HttpServletResponse response) {
//		ResearchForm researchForm = (ResearchForm)form;
//		ResearchVO researchVO = researchForm.getResearchVO();
//
//		String crsCreCd = UserBroker.getCreCrsCd(request);
//		researchVO.setCrsCreCd(crsCreCd);
//
//		ProcessResultListVO<ResearchVO> resultList = researchService.listResearch(researchVO);
//
//		researchForm.setResearchListVO(resultList.getReturnList());
//		researchForm.setPageInfo(resultList.getPageInfo());
//		return mapping.findForward("list_research");
//	}
//
//	/**
//	 * 설문 목록 조회
//	 * @param request
//	 * @return
//	 */
//	@AjaxAction
//	public String listResearch( vo, Map commandMap, ModelMap model,
//			HttpServletRequest request, HttpServletResponse response) {
//
//		ResearchForm researchForm = (ResearchForm)form;
//		ResearchVO researchVO = researchForm.getResearchVO();
//
//
//		String crsCreCd = UserBroker.getCreCrsCd(request);
//
//		researchVO.setCrsCreCd(crsCreCd);
//
//		return JsonUtil
//			.responseJson(response, researchService.listResearch(researchVO));
//	}
//
//	/**
//	 * 설문 등록 폼
//	 * @param mapping
//	 * @param form
//	 * @param request
//	 * @param response
//	 * @return
//	 */
//	public String addFormResearch(ActionMapping mapping,
//			ActionForm form, HttpServletRequest request,
//			HttpServletResponse response) {
//
//		ResearchForm researchForm = (ResearchForm)form;
//
//		researchForm.setGubun("A");
//		return mapping.findForward("write_research");
//	}
//
//	/**
//	 * 설문 저장
//	 *
//	 * @return  ProcessResultVO
//	 */
//	public String addResearch(ActionMapping mapping,
//			ActionForm form, HttpServletRequest request,
//			HttpServletResponse response){
//		ResearchForm researchForm = (ResearchForm)form;
//		ResearchVO researchVO = researchForm.getResearchVO();
//
//		//스크립트 제거
//		researchVO.setReshCts(HtmlCleaner.cleanScript(researchVO.getReshCts()));
//		researchVO.setReshTitle(HtmlCleaner.cleanScript(researchVO.getReshTitle()));
//
//		String crsCreCd = UserBroker.getCreCrsCd(request);
//		researchVO.setCrsCreCd(crsCreCd);
//
//
//		try {
//			researchVO = researchService.addResearch(researchVO).getReturnVO();
//		} catch (Exception e) {
//			researchForm.setGubun("A");
//			e.printStackTrace();
//			setAlertMessage(request, "설문을 등록하지 못하였습니다.");
//			return mapping.findForward("write_research");	// 다시 편집 화면으로
//		}
//
//		setAlertMessage(request, "설문 등록을 완료 하였습니다.");
//
//		// 읽기 화면으로
//		return redirect(
//			new URLBuilder("/ResearchLecture.do")
//				.addParameter("cmd", "listItem")
//				.addParameter("researchVO.crsCreCd", researchVO.getCrsCreCd())
//				.addParameter("researchVO.reshSn", researchVO.getReshSn())
//				.toString()
//		);
//	}
//
//	/**
//	 * 설문 등록 완료
//	 *
//	 * @return  ProcessResultVO
//	 */
//	public String regResearch(ActionMapping mapping,
//			ActionForm form, HttpServletRequest request,
//			HttpServletResponse response){
//		ResearchForm researchForm = (ResearchForm)form;
//		ResearchVO researchVO = researchForm.getResearchVO();
//
//		try {
//			researchService.editResearch(researchVO);
//		} catch (Exception e) {
//			researchForm.setGubun("E");
//			e.printStackTrace();
//			setAlertMessage(request, "설문 등록 완료를 하지 못하였습니다.");
//			// 읽기 화면으로
//			return redirect(
//				new URLBuilder("/ResearchLecture.do")
//					.addParameter("cmd", "listItem")
//					.addParameter("researchVO.crsCreCd", researchVO.getCrsCreCd())
//					.addParameter("researchVO.reshSn", researchVO.getReshSn())
//					.toString()
//			);
//		}
//
//		setAlertMessage(request, "설문 등록 완료를 하였습니다.");
//
//		// 읽기 화면으로
//		return redirect(
//			new URLBuilder("/ResearchLecture.do")
//				.addParameter("cmd", "listItem")
//				.addParameter("researchVO.crsCreCd", researchVO.getCrsCreCd())
//				.addParameter("researchVO.reshSn", researchVO.getReshSn())
//				.toString()
//		);
//	}
//
//	/**
//	 * 설문 등록 취소
//	 *
//	 * @return  ProcessResultVO
//	 */
//	@AjaxAction
//	@AjaxMessage("설문 등록 최소를")
//	public String regCancelResearch(ActionMapping mapping,
//			ActionForm form, HttpServletRequest request,
//			HttpServletResponse response){
//		ResearchForm researchForm = (ResearchForm)form;
//		ResearchVO researchVO = researchForm.getResearchVO();
//
//		String crsCreCd = UserBroker.getCreCrsCd(request);
//		researchVO.setCrsCreCd(crsCreCd);
//
//
//		try {
//			researchService.editResearch(researchVO);
//		} catch (Exception e) {
//			researchForm.setGubun("E");
//			e.printStackTrace();
//			setAlertMessage(request, "설문 등록 취소를 하지 못하였습니다.");
//			// 읽기 화면으로
//			return redirect(
//				new URLBuilder("/ResearchLecture.do")
//					.addParameter("cmd", "listItem")
//					.addParameter("researchVO.crsCreCd", researchVO.getCrsCreCd())
//					.addParameter("researchVO.reshSn", researchVO.getReshSn())
//					.toString()
//			);
//		}
//
//		setAlertMessage(request, "설문 등록 취소를 하였습니다.");
//
//		// 읽기 화면으로
//		return redirect(
//			new URLBuilder("/ResearchLecture.do")
//				.addParameter("cmd", "listItem")
//				.addParameter("researchVO.crsCreCd", researchVO.getCrsCreCd())
//				.addParameter("researchVO.reshSn", researchVO.getReshSn())
//				.toString()
//		);
//	}
//
//
//	/**
//	 * 설문 수정 폼
//	 * @param mapping
//	 * @param form
//	 * @param request
//	 * @param response
//	 * @return
//	 */
//	public String editFormResearch(ActionMapping mapping,
//			ActionForm form, HttpServletRequest request,
//			HttpServletResponse response){
//
//		ResearchForm researchForm = (ResearchForm)form;
//		ResearchVO researchVO = researchForm.getResearchVO();
//
//		String crsCreCd = UserBroker.getCreCrsCd(request);
//		researchVO.setCrsCreCd(crsCreCd);
//
//		//-- research험의 정보를 가져온다.
//		researchForm.setResearchVO(researchService.viewResearch(researchVO).getReturnVO());
//
//		researchForm.setGubun("E");
//		return mapping.findForward("edit_research");
//	}
//
//	/**
//	 * 설문 수정
//	 *
//	 * @return  ProcessResultVO
//	 */
//	public String editResearch(ActionMapping mapping,
//			ActionForm form, HttpServletRequest request,
//			HttpServletResponse response){
//		ResearchForm researchForm = (ResearchForm)form;
//		ResearchVO researchVO = researchForm.getResearchVO();
//
//		researchVO.setReshCts(HtmlCleaner.cleanScript(researchVO.getReshCts()));
//		researchVO.setReshTitle(HtmlCleaner.cleanScript(researchVO.getReshTitle()));
//
//		String crsCreCd = UserBroker.getCreCrsCd(request);
//		researchVO.setCrsCreCd(crsCreCd);
//
//		try {
//			researchVO = researchService.editResearch(researchVO).getReturnVO();
//		} catch (Exception e) {
//			e.printStackTrace();
//			setAlertMessage(request, "설문을 수정하지 못하였습니다.");
//			return redirect(
//					new URLBuilder("/ResearchLecture.do")
//						.addParameter("cmd", researchVO.getEditMode())
//						.addParameter("researchVO.crsCreCd", researchVO.getCrsCreCd())
//						.addParameter("researchVO.reshSn", researchVO.getReshSn())
//						.toString()
//				);
//		}
//
//		setAlertMessage(request, "설문 수정을 완료 하였습니다.");
//
//		// 읽기 화면으로
//		return redirect(
//			new URLBuilder("/ResearchLecture.do")
//				.addParameter("cmd", researchVO.getEditMode())
//				.addParameter("researchVO.crsCreCd", researchVO.getCrsCreCd())
//				.addParameter("researchVO.reshSn", researchVO.getReshSn())
//				.toString()
//		);
//	}
//
//	/**
//	 * 설문 삭제
//	 *
//	 * @return  ProcessResultVO
//	 */
//	public String deleteResearch(ActionMapping mapping,
//			ActionForm form, HttpServletRequest request,
//			HttpServletResponse response){
//		ResearchForm researchForm = (ResearchForm)form;
//		ResearchVO researchVO = researchForm.getResearchVO();
//
//		try {
//			researchService.deleteResearch(researchVO).getReturnVO();
//		} catch (Exception e) {
//			e.printStackTrace();
//			setAlertMessage(request, "설문을 삭제하지 못하였습니다.");
//			return redirect(
//					new URLBuilder("/ResearchLecture.do")
//						.addParameter("cmd", researchVO.getEditMode())
//						.addParameter("researchVO.crsCreCd", researchVO.getCrsCreCd())
//						.addParameter("researchVO.reshSn", researchVO.getReshSn())
//						.toString()
//				);
//		}
//
//		setAlertMessage(request, "설문 삭제를 완료 하였습니다.");
//
//		// 읽기 화면으로
//		return redirect(
//			new URLBuilder("/ResearchLecture.do")
//				.addParameter("cmd", "mainResearch")
//				.toString()
//		);
//	}
//
//	/**
//	 * 설문 문제 목록 조회
//	 * @param request
//	 * @return
//	 */
//	@AjaxAction
//	public String listItem( vo, Map commandMap, ModelMap model,
//			HttpServletRequest request, HttpServletResponse response) {
//		ResearchForm researchForm = (ResearchForm)form;
//		ResearchVO researchVO = researchForm.getResearchVO();
//		ResearchItemVO researchItemVO = researchForm.getResearchItemVO();
//
//		//-- 설문 정보를 가져온다.
//		String crsCreCd = UserBroker.getCreCrsCd(request);
//		researchVO.setCrsCreCd(crsCreCd);
//		researchVO = researchService.viewResearch(researchVO).getReturnVO();
//
//		//-- 설문 문제 목록을 가져온다.
//		researchItemVO.setCrsCreCd(researchVO.getCrsCreCd());
//		researchItemVO.setReshSn(researchVO.getReshSn());
//		ProcessResultListVO<ResearchItemVO> resultList = researchService.listItem(researchItemVO);
//
//		researchForm.setResearchVO(researchVO);
//		researchForm.setResearchItemListVO(resultList.getReturnList());
//		researchForm.setPageInfo(resultList.getPageInfo());
//		return mapping.findForward("list_item");
//	}
//
//	/**
//	 * 설문 문제 결과 목록 조회
//	 * @param request
//	 * @return
//	 */
//	@AjaxAction
//	public String listResult( vo, Map commandMap, ModelMap model,
//			HttpServletRequest request, HttpServletResponse response) {
//		ResearchForm researchForm = (ResearchForm)form;
//		ResearchVO researchVO = researchForm.getResearchVO();
//		ResearchItemVO researchItemVO = researchForm.getResearchItemVO();
//
//		//-- 설문 정보를 가져온다.
//		String crsCreCd = UserBroker.getCreCrsCd(request);
//		researchVO.setCrsCreCd(crsCreCd);
//		researchVO = researchService.viewResearch(researchVO).getReturnVO();
//
//		//-- 설문 결과 목록을 가져온다.
//		researchItemVO.setCrsCreCd(researchVO.getCrsCreCd());
//		researchItemVO.setReshSn(researchVO.getReshSn());
//		ProcessResultListVO<ResearchResultVO> resultList = researchService.listResult(researchItemVO);
//
//		researchForm.setResearchVO(researchVO);
//		researchForm.setResearchResultList(resultList.getReturnList());
//		researchForm.setPageInfo(resultList.getPageInfo());
//		return mapping.findForward("list_result");
//	}
//
//
//	/**
//	 * 설문 문제 등록 폼
//	 * @param mapping
//	 * @param form
//	 * @param request
//	 * @param response
//	 * @return
//	 */
//	public String addFormItem(ActionMapping mapping,
//			ActionForm form, HttpServletRequest request,
//			HttpServletResponse response) {
//		ResearchForm researchForm = (ResearchForm)form;
//
//		List<CodeVO> codeList = codeService.listCode("RESH_ITEM_TYPE_CD").getReturnList();
//		request.setAttribute("codeList", codeList);
//
//		researchForm.setGubun("A");
//		return mapping.findForward("research_item_write");
//	}
//
//	/**
//	 * 설문 문제 등록
//	 *
//	 * @return  ProcessResultVO
//	 */
//	public String addItem(ActionMapping mapping,
//			ActionForm form, HttpServletRequest request,
//			HttpServletResponse response){
//
//		ResearchForm researchForm = (ResearchForm)form;
//		ResearchItemVO researchItemVO = researchForm.getResearchItemVO();
//
//		researchItemVO.setReshItemCts(HtmlCleaner.cleanScript(researchItemVO.getReshItemCts()));
//		researchItemVO.setEmpl1(HtmlCleaner.cleanScript(researchItemVO.getEmpl1()));
//		researchItemVO.setEmpl2(HtmlCleaner.cleanScript(researchItemVO.getEmpl2()));
//		researchItemVO.setEmpl3(HtmlCleaner.cleanScript(researchItemVO.getEmpl3()));
//		researchItemVO.setEmpl4(HtmlCleaner.cleanScript(researchItemVO.getEmpl4()));
//		researchItemVO.setEmpl5(HtmlCleaner.cleanScript(researchItemVO.getEmpl5()));
//		researchItemVO.setEmpl6(HtmlCleaner.cleanScript(researchItemVO.getEmpl6()));
//		researchItemVO.setEmpl7(HtmlCleaner.cleanScript(researchItemVO.getEmpl7()));
//		researchItemVO.setEmpl8(HtmlCleaner.cleanScript(researchItemVO.getEmpl8()));
//		researchItemVO.setEmpl9(HtmlCleaner.cleanScript(researchItemVO.getEmpl9()));
//		researchItemVO.setEmpl10(HtmlCleaner.cleanScript(researchItemVO.getEmpl10()));
//
//
//		String crsCreCd = UserBroker.getCreCrsCd(request);
//		researchItemVO.setCrsCreCd(crsCreCd);
//
//		// 스크립트, 스타일 태그 제거
//		researchItemVO.setReshItemCts(HtmlCleaner.cleanScript(researchItemVO.getReshItemCts()) );
//
//		try {
//			researchItemVO = researchService.addItem(researchItemVO).getReturnVO();
//		} catch (Exception e) {
//			researchForm.setGubun("A");
//			e.printStackTrace();
//			setAlertMessage(request, "설문 문제를 등록하지 못하였습니다.");
//			return mapping.findForward("research_item_write");	// 다시 편집 화면으로
//		}
//
//		setAlertMessage(request, "설문 문제 등록을 완료 하였습니다.");
//
//		// 읽기 화면으로
//		return redirect(
//			new URLBuilder("/ResearchLecture.do")
//				.addParameter("cmd", "listItem")
//				.addParameter("researchVO.crsCreCd", researchItemVO.getCrsCreCd())
//				.addParameter("researchVO.reshSn", researchItemVO.getReshSn())
//				.toString()
//		);
//	}
//
//	/**
//	 * 설문 문제 수정 폼
//	 * @param mapping
//	 * @param form
//	 * @param request
//	 * @param response
//	 * @return
//	 */
//	public String editFormItem(ActionMapping mapping,
//			ActionForm form, HttpServletRequest request,
//			HttpServletResponse response) {
//
//		ResearchForm researchForm = (ResearchForm)form;
//		ResearchItemVO researchItemVO = researchForm.getResearchItemVO();
//
//		String crsCreCd = UserBroker.getCreCrsCd(request);
//		researchItemVO.setCrsCreCd(crsCreCd);
//
//		//-- 설문 문제 정보를 찾아 Form에 셋팅
//		researchItemVO = researchService.viewItem(researchItemVO).getReturnVO();
//		researchForm.setResearchItemVO(researchItemVO);
//
//		List<CodeVO> codeList = codeService.listCode("RESH_ITEM_TYPE_CD").getReturnList();
//		request.setAttribute("codeList", codeList);
//
//		researchForm.setGubun("E");
//		return mapping.findForward("research_item_write");
//	}
//
//	/**
//	 * 설문 문제 수정
//	 *
//	 * @return  ProcessResultVO
//	 */
//	@AjaxAction
//	@AjaxMessage("설문 문제 수정을")
//	public String editItem(ActionMapping mapping,
//			ActionForm form, HttpServletRequest request,
//			HttpServletResponse response){
//
//		ResearchForm researchForm = (ResearchForm)form;
//		ResearchItemVO researchItemVO = researchForm.getResearchItemVO();
//
//		researchItemVO.setReshItemCts(HtmlCleaner.cleanScript(researchItemVO.getReshItemCts()));
//		researchItemVO.setEmpl1(HtmlCleaner.cleanScript(researchItemVO.getEmpl1()));
//		researchItemVO.setEmpl2(HtmlCleaner.cleanScript(researchItemVO.getEmpl2()));
//		researchItemVO.setEmpl3(HtmlCleaner.cleanScript(researchItemVO.getEmpl3()));
//		researchItemVO.setEmpl4(HtmlCleaner.cleanScript(researchItemVO.getEmpl4()));
//		researchItemVO.setEmpl5(HtmlCleaner.cleanScript(researchItemVO.getEmpl5()));
//		researchItemVO.setEmpl6(HtmlCleaner.cleanScript(researchItemVO.getEmpl6()));
//		researchItemVO.setEmpl7(HtmlCleaner.cleanScript(researchItemVO.getEmpl7()));
//		researchItemVO.setEmpl8(HtmlCleaner.cleanScript(researchItemVO.getEmpl8()));
//		researchItemVO.setEmpl9(HtmlCleaner.cleanScript(researchItemVO.getEmpl9()));
//		researchItemVO.setEmpl10(HtmlCleaner.cleanScript(researchItemVO.getEmpl10()));
//		// 스크립트, 스타일 태그 제거
//		researchItemVO.setReshItemCts(HtmlCleaner.cleanScript(researchItemVO.getReshItemCts()) );
//
//		try {
//			researchService.editItem(researchItemVO);
//		} catch (Exception e) {
//			researchForm.setGubun("E");
//			e.printStackTrace();
//			setAlertMessage(request, "설문 문제를 수정하지 못하였습니다.");
//			return mapping.findForward("research_item_write");	// 다시 편집 화면으로
//		}
//
//		setAlertMessage(request, "설문 문제 수정을 완료 하였습니다.");
//
//		// 읽기 화면으로
//		return redirect(
//			new URLBuilder("/ResearchLecture.do")
//				.addParameter("cmd", "listItem")
//				.addParameter("researchVO.crsCreCd", researchItemVO.getCrsCreCd())
//				.addParameter("researchVO.reshSn", researchItemVO.getReshSn())
//				.toString()
//		);
//	}
//
//	/**
//	 * 설문 문제 삭제
//	 *
//	 * @return  ProcessResultVO
//	 */
//	@AjaxAction
//	@AjaxMessage("설문 문제 삭제를")
//	public String deleteItem(ActionMapping mapping,
//			ActionForm form, HttpServletRequest request,
//			HttpServletResponse response){
//
//		ResearchForm researchForm = (ResearchForm)form;
//		ResearchItemVO researchItemVO = researchForm.getResearchItemVO();
//
//		try {
//			researchService.deleteItem(researchItemVO);
//		} catch (Exception e) {
//			researchForm.setGubun("E");
//			e.printStackTrace();
//			setAlertMessage(request, "설문 문제를 삭제하지 못하였습니다.");
//			return mapping.findForward("research_item_write");	// 다시 편집 화면으로
//		}
//
//		setAlertMessage(request, "설문 문제 삭제를 완료 하였습니다.");
//
//		// 읽기 화면으로
//		return redirect(
//			new URLBuilder("/ResearchLecture.do")
//				.addParameter("cmd", "listItem")
//				.addParameter("researchVO.crsCreCd", researchItemVO.getCrsCreCd())
//				.addParameter("researchVO.reshSn", researchItemVO.getReshSn())
//				.toString()
//		);
//	}
//
//	/**
//	 * 설문 응시생 목록 조회
//	 * @param request
//	 * @return
//	 */
//	@AjaxAction
//	public String listJoinUser( vo, Map commandMap, ModelMap model,
//			HttpServletRequest request, HttpServletResponse response) {
//		ResearchForm researchForm = (ResearchForm)form;
//		ResearchVO researchVO = researchForm.getResearchVO();
//		ResearchAnswerVO researchAnswerVO = researchForm.getResearchAnswerVO();
//
//		//-- 설문 정보를 가져온다.
//		String crsCreCd = UserBroker.getCreCrsCd(request);
//		researchVO.setCrsCreCd(crsCreCd);
//		researchVO = researchService.viewResearch(researchVO).getReturnVO();
//
//		researchAnswerVO.setCrsCreCd(crsCreCd);
//		researchAnswerVO.setReshSn(researchVO.getReshSn());
//
//		ProcessResultListVO<ResearchAnswerVO> resultList = researchService.listJoinUser(researchAnswerVO);
//
//		researchForm.setResearchVO(researchVO);
//		researchForm.setResearchAnswerListVO(resultList.getReturnList());
//		researchForm.setPageInfo(resultList.getPageInfo());
//		return mapping.findForward("list_user");
//	}
//
//	/**
//	 * 설문 참여 메인
//	 * @param mapping
//	 * @param form
//	 * @param request
//	 * @param response
//	 * @return
//	 */
//	public String mainJoinResearch( vo, Map commandMap, ModelMap model,
//			HttpServletRequest request, HttpServletResponse response) {
//		ResearchForm researchForm = (ResearchForm)form;
//		ResearchVO researchVO = researchForm.getResearchVO();
//
//
//		String crsCreCd = UserBroker.getCreCrsCd(request);
//		researchVO.setCrsCreCd(crsCreCd);
//
//		String stuNo = UserBroker.getStudentNo(request);
//		researchVO.setStdNo(stuNo);
//		researchVO.setRegYn("Y"); //-- 등록 완료된 설문만 가져오기
//		ProcessResultListVO<ResearchVO> resultList = researchService.listResearch(researchVO);
//
//		researchForm.setResearchListVO(resultList.getReturnList());
//		researchForm.setPageInfo(resultList.getPageInfo());
//		return mapping.findForward("list_join_research");
//	}
//
//	/**
//	 * 설문 참여 폼
//	 * @param request
//	 * @return
//	 */
//	public String joinFormResearch(ActionMapping mapping,
//			ActionForm form, HttpServletRequest request,
//			HttpServletResponse response){
//		ResearchForm researchForm = (ResearchForm)form;
//		ResearchVO researchVO = researchForm.getResearchVO();
//		ResearchItemVO researchItemVO = researchForm.getResearchItemVO();
//
//		String crsCreCd = UserBroker.getCreCrsCd(request);
//		researchVO.setCrsCreCd(crsCreCd);
//		researchVO = researchService.viewResearch(researchVO).getReturnVO();
//
//		//-- 설문 결과 목록을 가져온다.
//		researchItemVO.setCrsCreCd(researchVO.getCrsCreCd());
//		researchItemVO.setReshSn(researchVO.getReshSn());
//		ProcessResultListVO<ResearchResultVO> resultList = researchService.listResult(researchItemVO);
//
//		researchForm.setResearchVO(researchVO);
//		researchForm.setResearchResultList(resultList.getReturnList());
//		researchForm.setPageInfo(resultList.getPageInfo());
//		return mapping.findForward("write_join_research");
//	}
//
//
//	/**
//	 * 설문 참여 등록
//	 *
//	 * @return  ProcessResultVO
//	 */
//	public String saveResearch(ActionMapping mapping,
//			ActionForm form, HttpServletRequest request,
//			HttpServletResponse response){
//		ResearchForm researchForm = (ResearchForm)form;
//		ResearchAnswerVO researchAnswerVO = researchForm.getResearchAnswerVO();
//
//		String crsCreCd = UserBroker.getCreCrsCd(request);
//		researchAnswerVO.setCrsCreCd(crsCreCd);              //과제코드
//
//		String stuCd = UserBroker.getStudentNo(request);
//		researchAnswerVO.setStdNo(stuCd);					// 학생id
//
//		String usrNm = UserBroker.getUserName(request);
//		researchAnswerVO.setUserNm(usrNm);					// 학생명
//
//		try {
//			researchService.addAnswer(researchAnswerVO);
//		} catch (Exception e) {
//			e.printStackTrace();
//			setAlertMessage(request, "설문 응시를 하지 못하였습니다.");
//			return redirect(
//				new URLBuilder("/ResearchLecture.do")
//					.addParameter("cmd", "joinFormResearch")
//					.addParameter("researchVO.crsCreCd", crsCreCd)
//					.addParameter("researchVO.reshSn", researchAnswerVO.getReshSn())
//					.toString()
//			);
//		}
//
//		setAlertMessage(request, "설문 응시를 완료 하였습니다.");
//
//		// 읽기 화면으로
//		return redirect(
//			new URLBuilder("/ResearchLecture.do")
//				.addParameter("cmd", "mainJoinResearch")
//				.toString()
//		);
//	}
//
//	/**
//	 * 설문(서술형 답변자 리스트
//	 *
//	 * @return  ProcessResultVO
//	 */
//	public String listReshPop(ActionMapping mapping,
//			ActionForm form, HttpServletRequest request,
//			HttpServletResponse response){
//		ResearchForm researchForm = (ResearchForm)form;
//		ResearchAnswerVO researchAnswerVO = researchForm.getResearchAnswerVO();
//		ResearchVO researchVO = researchForm.getResearchVO();
//
//
//		String crsCreCd = UserBroker.getCreCrsCd(request);
//		researchAnswerVO.setCrsCreCd(crsCreCd);
//
//
//		researchVO.setCrsCreCd(crsCreCd);
//		researchVO.setReshSn(researchAnswerVO.getReshSn());
//
//		//-- research험의 정보를 가져온다.
//		researchForm.setResearchVO(researchService.viewResearch(researchVO).getReturnVO());
//
//		ProcessResultListVO<ResearchAnswerVO> resultList = researchService.listOpinion(researchAnswerVO);
//				//researchService.li listItem(researchAnswerVO, researchForm.getCurPage());
//
//		researchForm.setResearchAnswerListVO(resultList.getReturnList());
//		researchForm.setPageInfo(resultList.getPageInfo());
//
//		return mapping.findForward("list_item_pop");
//	}
//
//	/**
//	 * 참가자 엑셀 다운로드
//	 *
//	 * @return  ProcessResultVO
//	 */
//	public String listExcelDownloadConfirm(ActionMapping mapping,
//			ActionForm form, HttpServletRequest request,
//			HttpServletResponse response) throws Exception {
//
//		ResearchForm researchForm = (ResearchForm)form;
//		ResearchVO researchVO = researchForm.getResearchVO();
//		ResearchAnswerVO researchAnswerVO = researchForm.getResearchAnswerVO();
//
//		String crsCreCd = UserBroker.getCreCrsCd(request);
//		researchAnswerVO.setCrsCreCd(crsCreCd);
//
//		//--- 수강승인자만 검색하도록 수강상태 셋팅
//		researchAnswerVO.setReshItemTypeCd("D");
//		researchAnswerVO.setReshSn(researchVO.getReshSn());
//
//		//-- 엑셀 출력 로그를 저장한다.
//		PrintLogVO printLogVO = new PrintLogVO();
//		printLogVO.setUserNo(UserBroker.getUserNo(request));
//		printLogVO.setUserNm(UserBroker.getUserName(request));
//		printLogVO.setPrnDoc(" 설문 참가자명단 엑셀 출력(서술형답변)");
//		printLogVO.setParam(researchAnswerVO.toString());
//		printLogService.addPrintLog(printLogVO);
//
//
//		response.setHeader("Content-Disposition", "attachment;filename=ResearchList_"+researchAnswerVO.getCrsCreCd()+".xls;");
//		response.setHeader( "Content-Transfer-Coding", "binary" );
//		response.setContentType("application/octet-stream");
//
//		researchService.listExcelDownloadConfirm(researchAnswerVO, response.getOutputStream());
//
//		return null;
//	}
//
//
//	/**
//	 * 설문 은행 선택 폼
//	 *
//	 * @return  ProcessResultVO
//	 */
//
//	public String editFormItemBank(ActionMapping mapping,
//			ActionForm form, HttpServletRequest request,
//			HttpServletResponse response){
//
//		ResearchForm researchForm = (ResearchForm)form;
//		ResearchVO researchVO = researchForm.getResearchVO();
//
//		String crsCreCd = UserBroker.getCreCrsCd(request);
//		researchVO.setCrsCreCd(crsCreCd);
//		researchForm.setResearchVO(researchVO);
//
//		//-- 문제 은행의 정보를 목록으로 가져온다.
//		List<ResearchBankVO> bankList = researchBankService.listResearch(new ResearchBankVO()).getReturnList();
//		request.setAttribute("bankList", bankList);
//
//		return mapping.findForward("item_bank");
//	}
//
//	/**
//	 * 설문 문제은행 문제 목록
//	 * @param request
//	 * @return
//	 */
//	@AjaxAction
//	public String listBankItem( vo, Map commandMap, ModelMap model,
//			HttpServletRequest request, HttpServletResponse response) {
//
//		String reshSn = request.getParameter("reshSn");
//		ResearchBankVO researchBankVO = new ResearchBankVO();
//
//		researchBankVO.setReshSn(Integer.parseInt(reshSn));
//
//		return JsonUtil
//			.responseJson(response, researchBankService.listItem(researchBankVO));
//	}
//
//
//	/**
//	 * 권한 그룹 메뉴 등록
//	 *
//	 * @return  ProcessResultVO
//	 */
////	@AjaxAction
////	@AjaxMessage("설문 문제 등록을")
////	public String addResearchItemFromBank(ActionMapping mapping,
////			ActionForm form, HttpServletRequest request,
////			HttpServletResponse response){
////		ResearchForm researchForm = (ResearchForm)form;
////		ResearchVO researchVO = researchForm.getResearchVO();
////
////		return JsonUtil.
////			responseJsonWithResult(request, response, researchService.addResearchItemFromBank(researchVO));
////	}
//
//	/**
//	 * 참가자 엑셀 다운로드
//	 *
//	 * @return  ProcessResultVO
//	 */
//	public String listExcelDownloadJoinConfirm(ActionMapping mapping,
//			ActionForm form, HttpServletRequest request,
//			HttpServletResponse response) throws Exception {
//
//		ResearchForm researchForm = (ResearchForm)form;
//		ResearchVO researchVO = researchForm.getResearchVO();
//		ResearchAnswerVO researchAnswerVO = new ResearchAnswerVO();
//
//		String crsCreCd = UserBroker.getCreCrsCd(request);
//		researchAnswerVO.setCrsCreCd(crsCreCd);
//
//		researchAnswerVO.setReshSn(researchVO.getReshSn());
//
//		//-- 엑셀 출력 로그를 저장한다.
//		PrintLogVO printLogVO = new PrintLogVO();
//		printLogVO.setUserNo(UserBroker.getUserNo(request));
//		printLogVO.setUserNm(UserBroker.getUserName(request));
//		printLogVO.setPrnDoc(" 설문 참가자명단 엑셀 출력");
//		printLogVO.setParam(researchAnswerVO.toString());
//		printLogService.addPrintLog(printLogVO);
//
//		response.setHeader("Content-Disposition", "attachment;filename=ResearchList_"+researchAnswerVO.getCrsCreCd()+".xls;");
//		response.setHeader( "Content-Transfer-Coding", "binary" );
//		response.setContentType("application/octet-stream");
//
//		researchService.listExcelDownloadJoinConfirm(researchAnswerVO, response.getOutputStream());
//
//		return null;
//	}
//
//	/**
//	 * 설문결과 엑셀 출력
//	 * @param mapping
//	 * @param form
//	 * @param request
//	 * @param response
//	 * @return
//	 * @throws Exception
//	 */
//	public String listExcelDownloadlResearchResultList(ActionMapping mapping,
//			ActionForm form, HttpServletRequest request,
//			HttpServletResponse response) throws Exception {
//
//		ResearchForm researchForm = (ResearchForm)form;
//		ResearchVO researchVO = researchForm.getResearchVO();
//		ResearchAnswerVO researchAnswerVO = new ResearchAnswerVO();
//		ResearchItemVO iResearchItemVO = new ResearchItemVO();
//
//		String crsCreCd = UserBroker.getCreCrsCd(request);
//		iResearchItemVO.setCrsCreCd(crsCreCd);
//
//		iResearchItemVO.setReshSn(researchVO.getReshSn());
//
//		//-- 엑셀 출력 로그를 저장한다.
//		PrintLogVO printLogVO = new PrintLogVO();
//		printLogVO.setUserNo(UserBroker.getUserNo(request));
//		printLogVO.setUserNm(UserBroker.getUserName(request));
//		printLogVO.setPrnDoc(" 설문 결과 엑셀 출력");
//		printLogVO.setParam(researchAnswerVO.toString());
//		printLogService.addPrintLog(printLogVO);
//
//
//		response.setHeader("Content-Disposition", "attachment;filename=ResearchList_"+researchAnswerVO.getCrsCreCd()+".xls;");
//		response.setHeader( "Content-Transfer-Coding", "binary" );
//		response.setContentType("application/octet-stream");
//
//		researchService.listExcelDownloadlResearchResultList(iResearchItemVO, response.getOutputStream());
//
//		return null;
//	}


}