package egovframework.edutrack.web.manage.course;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
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
import egovframework.edutrack.modules.course.createcourse.service.CreateCourseService;
import egovframework.edutrack.modules.course.createcourse.service.ResearchCourseVO;
import egovframework.edutrack.modules.course.research.service.ResearchBankItemVO;
import egovframework.edutrack.modules.course.research.service.ResearchBankService;
import egovframework.edutrack.modules.course.research.service.ResearchBankVO;
import egovframework.edutrack.modules.system.code.service.SysCodeVO;


@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/mng/course/researchBank")
public class CourseResearchBankManageController extends GenericController {

	@Autowired @Qualifier("researchBankService")
	private ResearchBankService	researchBankService;

	@Autowired @Qualifier("createCourseService")
	private CreateCourseService 	createCourseService;
	
	@Autowired @Qualifier("sysCodeMemService")
	private SysCodeMemService			sysCodeMemService;
	/**
	 * 설문 은행 관리 메인
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/main")
	public String main( ResearchBankVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("vo", vo);
		request.setAttribute("paging", "Y");
		
		return "mng/course/research/research_main";
	}

	/**
	 * 설문 은행 목록 조회
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/list")
	public String list( ResearchBankVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		//researchBankVO.setRegYn(""); //-- 모든 설문을 가져오기 위해 등록 여부 비우기


		ProcessResultListVO<ResearchBankVO> resultList = researchBankService.listPageing(vo, vo.getCurPage(), vo.getListScale());

		request.setAttribute("researchBankListVO", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());
		request.setAttribute("vo", vo);
		return "mng/course/research/research_list_div";
	}

	/**
	 * 설문 은행 목록 조회(설문 명 검색)
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/searchList")
	public String searchList( ResearchBankVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		return JsonUtil
			.responseJson(response, researchBankService.searchListResearch(vo));
	}

	/**
	 * 설문 은행 등록 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addPop")
	public String addPop(ResearchBankVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {

		request.setAttribute("gubun", "A");
		request.setAttribute("vo", vo);
		return "mng/course/research/research_write_pop";
	}


	/**
	 * 설문 은행 수정 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/editPop")
	public String editPop( ResearchBankVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		vo = researchBankService.viewResearch(vo).getReturnVO();

		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "E");
		return "mng/course/research/research_write_pop";
	}

	/**
	 * 설문 은행 정보 조회
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/view")
	public String view( ResearchBankVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		return JsonUtil
			.responseJson(response, researchBankService.viewResearch(vo));
	}

	/**
	 * 설문 은행 정보 등록
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/add")
	public String add( ResearchBankVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		ProcessResultVO<ResearchBankVO> resultVO = researchBankService.addResearch(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "course.message.reshbank.add.success"));
		} else {
			resultVO.setMessage(getMessage(request, "course.message.reshbank.add.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 설문 은행 정보 수정
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/edit")
	public String edit( ResearchBankVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		ProcessResultVO<ResearchBankVO> resultVO = researchBankService.editResearch(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "course.message.reshbank.edit.success"));
		} else {
			resultVO.setMessage(getMessage(request, "course.message.reshbank.edit.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 설문 은행 정보 삭제
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/delete")
	public String delete( ResearchBankVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		ProcessResultVO<ResearchBankVO> resultVO = researchBankService.deleteResearch(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "course.message.reshbank.delete.success"));
		} else {
			resultVO.setMessage(getMessage(request, "course.message.reshbank.delete.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 설문 은행 관리 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/manageMain")
	public String manageForm( ResearchBankVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		vo = researchBankService.viewResearch(vo).getReturnVO();

		request.setAttribute("vo", vo);
		return "mng/course/research/research_manage_main";
	}


	/**
	 * 설문 문제 목록 조회
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/listItem")
	public String listItem( ResearchBankVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		vo = researchBankService.viewResearch(vo).getReturnVO();

		ProcessResultListVO<ResearchBankItemVO> resultList = researchBankService.listItem(vo);

		request.setAttribute("researchBankVO", vo);
		request.setAttribute("researchBankItemListVO", resultList.getReturnList());
		return "mng/course/research/research_item_list_div";
	}

	/**
	 * 설문 문제 등록 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addItemPop")
	public String addItemPop(ResearchBankItemVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<SysCodeVO> reshItemTypeCdList = sysCodeMemService.getSysCodeList("RESH_ITEM_TYPE_CD", UserBroker.getLocaleKey(request));
        List<SysCodeVO> emplViewTypeList = sysCodeMemService.getSysCodeList("EMPL_VIEW_TYPE", UserBroker.getLocaleKey(request));

		String forward = this.getEditorType(request);
		request.setAttribute("gubun", "A");
		request.setAttribute("vo", vo);
		request.setAttribute("reshItemTypeCdList", reshItemTypeCdList);
		request.setAttribute("emplViewTypeList", emplViewTypeList);
		return forward;
	}

	/**
	 * 설문 문제 등록
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/addItem")
	public String addItem( ResearchBankItemVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		if("K".equals(vo.getReshItemTypeCd())) {
			if(StringUtil.nvl(vo.getEmplCnt(),0) < 16)
				vo.setEmpl16("");
			if(StringUtil.nvl(vo.getEmplCnt(),0) < 15)
				vo.setEmpl15("");
			if(StringUtil.nvl(vo.getEmplCnt(),0) < 14)
				vo.setEmpl14("");
			if(StringUtil.nvl(vo.getEmplCnt(),0) < 13)
				vo.setEmpl13("");
			if(StringUtil.nvl(vo.getEmplCnt(),0) < 12)
				vo.setEmpl12("");
			if(StringUtil.nvl(vo.getEmplCnt(),0) < 11)
				vo.setEmpl11("");
			if(StringUtil.nvl(vo.getEmplCnt(),0) < 10)
				vo.setEmpl10("");
			if(StringUtil.nvl(vo.getEmplCnt(),0) < 9)
				vo.setEmpl9("");
			if(StringUtil.nvl(vo.getEmplCnt(),0) < 8)
				vo.setEmpl8("");
			if(StringUtil.nvl(vo.getEmplCnt(),0) < 7)
				vo.setEmpl7("");
			if(StringUtil.nvl(vo.getEmplCnt(),0) < 6)
				vo.setEmpl6("");
			if(StringUtil.nvl(vo.getEmplCnt(),0) < 5)
				vo.setEmpl5("");
			if(StringUtil.nvl(vo.getEmplCnt(),0) < 4)
				vo.setEmpl4("");
			if(StringUtil.nvl(vo.getEmplCnt(),0) < 3)
				vo.setEmpl3("");
		} else {
			vo.setEmplCnt(0);
			vo.setEmpl1("");
			vo.setEmpl2("");
			vo.setEmpl3("");
			vo.setEmpl4("");
			vo.setEmpl5("");
			vo.setEmpl6("");
			vo.setEmpl7("");
			vo.setEmpl8("");
			vo.setEmpl9("");
			vo.setEmpl10("");
			vo.setEmpl11("");
			vo.setEmpl12("");
			vo.setEmpl13("");
			vo.setEmpl14("");
			vo.setEmpl15("");
			vo.setEmpl16("");
		}

		// 스크립트, 스타일 태그 제거
		vo.setReshItemCts(HtmlCleaner.cleanScript(vo.getReshItemCts()) );

		ProcessResultVO<ResearchBankItemVO> resultVO = researchBankService.addItem(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "course.message.reshbank.item.add.success"));
		} else {
			resultVO.setMessage(getMessage(request, "course.message.reshbank.item.add.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 설문 문제 수정 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/editItemPop")
	public String editItemPop( ResearchBankItemVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
        List<SysCodeVO> reshItemTypeCdList = sysCodeMemService.getSysCodeList("RESH_ITEM_TYPE_CD", UserBroker.getLocaleKey(request));
        List<SysCodeVO> emplViewTypeList = sysCodeMemService.getSysCodeList("EMPL_VIEW_TYPE", UserBroker.getLocaleKey(request));
		request.setAttribute("reshItemTypeCdList", reshItemTypeCdList);
		request.setAttribute("emplViewTypeList", emplViewTypeList);
		
		//-- 설문 문제 정보를 찾아 Form에 셋팅
		vo = researchBankService.viewItem(vo).getReturnVO();
		request.setAttribute("vo", vo);
		String forward = this.getEditorType(request);

		request.setAttribute("gubun","E");
		return forward;
	}

	/**
	 * 설문 문제 수정
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/editItem")
	public String editItem( ResearchBankItemVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		if("K".equals(vo.getReshItemTypeCd())) {
			if(StringUtil.nvl(vo.getEmplCnt(),0) < 16)
				vo.setEmpl16("");
			if(StringUtil.nvl(vo.getEmplCnt(),0) < 15)
				vo.setEmpl15("");
			if(StringUtil.nvl(vo.getEmplCnt(),0) < 14)
				vo.setEmpl14("");
			if(StringUtil.nvl(vo.getEmplCnt(),0) < 13)
				vo.setEmpl13("");
			if(StringUtil.nvl(vo.getEmplCnt(),0) < 12)
				vo.setEmpl12("");
			if(StringUtil.nvl(vo.getEmplCnt(),0) < 11)
				vo.setEmpl11("");
			if(StringUtil.nvl(vo.getEmplCnt(),0) < 10)
				vo.setEmpl10("");
			if(StringUtil.nvl(vo.getEmplCnt(),0) < 9)
				vo.setEmpl9("");
			if(StringUtil.nvl(vo.getEmplCnt(),0) < 8)
				vo.setEmpl8("");
			if(StringUtil.nvl(vo.getEmplCnt(),0) < 7)
				vo.setEmpl7("");
			if(StringUtil.nvl(vo.getEmplCnt(),0) < 6)
				vo.setEmpl6("");
			if(StringUtil.nvl(vo.getEmplCnt(),0) < 5)
				vo.setEmpl5("");
			if(StringUtil.nvl(vo.getEmplCnt(),0) < 4)
				vo.setEmpl4("");
			if(StringUtil.nvl(vo.getEmplCnt(),0) < 3)
				vo.setEmpl3("");
		} else {
			vo.setEmplCnt(0);
			vo.setEmpl1("");
			vo.setEmpl2("");
			vo.setEmpl3("");
			vo.setEmpl4("");
			vo.setEmpl5("");
			vo.setEmpl6("");
			vo.setEmpl7("");
			vo.setEmpl8("");
			vo.setEmpl9("");
			vo.setEmpl10("");
			vo.setEmpl11("");
			vo.setEmpl12("");
			vo.setEmpl13("");
			vo.setEmpl14("");
			vo.setEmpl15("");
			vo.setEmpl16("");
		}

		// 스크립트, 스타일 태그 제거
		vo.setReshItemCts(HtmlCleaner.cleanScript(vo.getReshItemCts()) );

		ProcessResultVO<ResearchBankItemVO> resultVO = researchBankService.editItem(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "course.message.reshbank.item.edit.success"));
		} else {
			resultVO.setMessage(getMessage(request, "course.message.reshbank.item.edit.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 설문 문제 삭제
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/deleteItem")
	public String deleteItem( ResearchBankItemVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultVO<ResearchBankItemVO> resultVO = researchBankService.deleteItem(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "course.message.reshbank.item.delete.success"));
		} else {
			resultVO.setMessage(getMessage(request, "course.message.reshbank.item.delete.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 설문 템플릿 popup
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/listReshTemplet")
	public String listReshTemplet( Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {

		String reshTpltGubun = StringUtil.nvl(request.getParameter("reshTpltGubun"),"course");
		request.setAttribute("reshTpltGubun", reshTpltGubun);
		request.setAttribute("popup", "Y");

		return "mng/course/research/research_templet_main_pop";
	}

	private String getEditorType(HttpServletRequest request) {
		String forwardUrl = "mng/course/research/research_item_write_pop";
		//-- 에디터 사용여부를 판단하여 해당 Webeditor를 사용하도록 한다.
		//-- BBS_INFO의 editorUseYn은 더이상 사용하지 않음.
		if("Y".equals(Constants.WEBEDITOR_YSEYN)) {
			if("DAUMEDITOR".equals(Constants.WEBEDITOR_NAME)) {
				forwardUrl = "mng/course/research/research_item_write_daumeditor_pop";
				request.setAttribute("daumeditor", "Y");
			} else if("SUMMERNOTE".equals(Constants.WEBEDITOR_NAME)) {
				forwardUrl = "mng/course/research/research_item_write_summernote_pop";
				request.setAttribute("summernote", "Y");
			}
		}
		return forwardUrl;
	}


	/**
	 * 설문 정보 보기
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/viewReshPop")
	public String viewResh( ResearchBankVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		vo = researchBankService.viewResearch(vo).getReturnVO();

		request.setAttribute("vo", vo);

		return "mng/course/research/research_view_pop";
	}

	/**
	 * 설문지은행 사용중인 과정
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/viewCourse")
	public String viewCourse( ResearchBankVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		Map<String, Object> researchInfo = new Hashtable<String, Object>();
		researchInfo.put("reshSn", vo.getReshSn());

		List<ResearchCourseVO> courseListIng = createCourseService.listCreateCourseForResearch(researchInfo).getReturnList();
		request.setAttribute("courseListIng", courseListIng);

		return "mng/course/research/research_course_ing_div";
	}

	/**
	 * 설문 은행 문제 순서변경
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/listReserchOrder")
	public String listReserchOrder( ResearchBankItemVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultListVO<ResearchBankItemVO> resultList = researchBankService.listItem(vo);

		request.setAttribute("researchBankItemListVO", resultList.getReturnList());
		request.setAttribute("vo", vo);
		return "mng/course/research/research_item_sort";
	}

	@RequestMapping(value="/sortReserchItem")
	public String sortReserchItem( ResearchBankItemVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		return JsonUtil
				.responseJson(response, researchBankService.sortReserchItem(vo));
	}

	/**
	 * 설문 은행 문제 엑셀 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addResearchItemExcelPop")
	public String addResearchItemExcel ( ResearchBankItemVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {
		commonVOProcessing(vo, request);

		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "A");
		request.setAttribute("fileupload", "Y");
		return "mng/course/research/research_item_write_excel_pop";
	}

	/**
	 * 설문 은행 문제 샘플파일 다운로드 폼.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/sampleExcelResearchBank")
	public String sampleExcelResearchBank ( ResearchBankItemVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response)  {
		commonVOProcessing(vo, request);
		

		HashMap<String, String> titles = new HashMap<String, String>();
		titles.put("comment1", getMessage(request, "course.title.reshbank.item.excel.comment1"));
		titles.put("comment2", getMessage(request, "course.title.reshbank.item.excel.comment2"));
		titles.put("comment3", getMessage(request, "course.title.reshbank.item.excel.comment3"));
		titles.put("comment4", getMessage(request, "course.title.reshbank.item.excel.comment4"));
		titles.put("comment5", getMessage(request, "course.title.reshbank.item.excel.comment5"));

		titles.put("reshItemTypeCd", getMessage(request, "course.title.reshbank.item.type.excel"));
		titles.put("emplViewType", getMessage(request, "course.title.reshbank.item.view.excel"));
		titles.put("emplCnt", getMessage(request, "course.title.reshbank.item.emplcnt.excel"));
		titles.put("reshItemCts", getMessage(request, "course.title.reshbank.item.reshitemcts.excel"));
		titles.put("item1", getMessage(request, "course.title.exambank.item1"));
		titles.put("item2", getMessage(request, "course.title.exambank.item2"));
		titles.put("item3", getMessage(request, "course.title.exambank.item3"));
		titles.put("item4", getMessage(request, "course.title.exambank.item4"));
		titles.put("item5", getMessage(request, "course.title.exambank.item5"));
		titles.put("item6", getMessage(request, "course.title.exambank.item6"));
		titles.put("item7", getMessage(request, "course.title.exambank.item7"));
		titles.put("item8", getMessage(request, "course.title.exambank.item8"));
		titles.put("item9", getMessage(request, "course.title.exambank.item9"));
		titles.put("item10", getMessage(request, "course.title.exambank.item10"));
		titles.put("item11", getMessage(request, "course.title.exambank.item11"));
		titles.put("item12", getMessage(request, "course.title.exambank.item12"));
		titles.put("item13", getMessage(request, "course.title.exambank.item13"));
		titles.put("item14", getMessage(request, "course.title.exambank.item14"));
		titles.put("item15", getMessage(request, "course.title.exambank.item15"));
		titles.put("item16", getMessage(request, "course.title.exambank.item16"));
		titles.put("item17", getMessage(request, "course.title.exambank.item17"));
		titles.put("item18", getMessage(request, "course.title.exambank.item18"));
		titles.put("item19", getMessage(request, "course.title.exambank.item19"));
		titles.put("item20", getMessage(request, "course.title.exambank.item20"));

		response.setHeader("Content-Disposition", "attachment;filename=research_item_sample.xlsx;");
		response.setHeader( "Content-Transfer-Coding", "binary" );
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		try {
			researchBankService.sampleExcelDownload(titles, response.getOutputStream());
		} catch (Exception e) {

		}
		return null;
	}

	/**
	 * 설문 은행 문제 엑셀 업로드
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/excelUploadCheckPop")
	public String excelUploadCheckPop( ResearchBankItemVO vo,  Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) {
		String fileName = StringUtil.nvl(request.getParameter("fileName"));
		String filePath = StringUtil.nvl(request.getParameter("filePath"));

		ProcessResultListVO<ResearchBankItemVO> resultList = null;
		resultList = researchBankService.excelUploadValidationCheck(fileName, filePath);
		request.setAttribute("researchBankList", resultList.getReturnList());
		request.setAttribute("vo", vo);
		request.setAttribute("fileupload", "Y");
		return "mng/course/research/research_item_write_excel_validate_pop";
	}

	/**
	 * 설문 은행 문제 엑셀 업로드 , 단건 체크
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/researchBankUploadCheck")
	public String researchBankUploadCheck( ResearchBankItemVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) {
		commonVOProcessing(vo, request);

		String errorCode = "";

		if(!"K".equals(vo.getReshItemTypeCd()) && !"D".equals(vo.getReshItemTypeCd())){
			errorCode += "|"+"RESHITEMTYPECD";
		}
		if(ValidationUtils.isEmpty(vo.getReshItemCts())){
			errorCode += "|"+"RESHITEMCTS";
		}
		if("K".equals(vo.getReshItemTypeCd()) ){
			if(!"H".equals(vo.getEmplViewType()) && !"W".equals(vo.getEmplViewType())){
				errorCode += "|"+"EMPLVIEWTYPE";
			}
			if(ValidationUtils.isZeroNull(vo.getEmplCnt())){
				errorCode += "|"+"EMPLCNT";
			}else {
				if(vo.getEmplCnt() > 20 ){
					errorCode += "|"+"EMPLCNT";
				}
				if(ValidationUtils.isEmpty(vo.getEmpl1()) || vo.getEmpl1().getBytes().length > 1000){
					errorCode += "|"+"EMPL1";
				}
				if(ValidationUtils.isEmpty(vo.getEmpl2()) || vo.getEmpl2().getBytes().length > 1000){
					errorCode += "|"+"EMPL2";
				}
				if(ValidationUtils.isEmpty(vo.getEmpl3()) || vo.getEmpl3().getBytes().length > 1000){
					errorCode += "|"+"EMPL3";
				}
				if(ValidationUtils.isEmpty(vo.getEmpl4()) || vo.getEmpl4().getBytes().length > 1000){
					errorCode += "|"+"EMPL4";
				}
				if(ValidationUtils.isEmpty(vo.getEmpl5()) || vo.getEmpl5().getBytes().length > 1000){
					errorCode += "|"+"EMPL5";
				}
				if(ValidationUtils.isEmpty(vo.getEmpl6()) || vo.getEmpl6().getBytes().length > 1000){
					errorCode += "|"+"EMPL6";
				}
				if(ValidationUtils.isEmpty(vo.getEmpl7()) || vo.getEmpl7().getBytes().length > 1000){
					errorCode += "|"+"EMPL7";
				}
				if(ValidationUtils.isEmpty(vo.getEmpl8()) || vo.getEmpl8().getBytes().length > 1000){
					errorCode += "|"+"EMPL8";
				}
				if(ValidationUtils.isEmpty(vo.getEmpl9()) || vo.getEmpl9().getBytes().length > 1000){
					errorCode += "|"+"EMPL9";
				}
				if(ValidationUtils.isEmpty(vo.getEmpl10()) || vo.getEmpl10().getBytes().length > 1000){
					errorCode += "|"+"EMPL10";
				}
				if(ValidationUtils.isEmpty(vo.getEmpl11()) || vo.getEmpl11().getBytes().length > 1000){
					errorCode += "|"+"EMPL11";
				}
				if(ValidationUtils.isEmpty(vo.getEmpl12()) || vo.getEmpl12().getBytes().length > 1000){
					errorCode += "|"+"EMPL12";
				}
				if(ValidationUtils.isEmpty(vo.getEmpl13()) || vo.getEmpl13().getBytes().length > 1000){
					errorCode += "|"+"EMPL13";
				}
				if(ValidationUtils.isEmpty(vo.getEmpl14()) || vo.getEmpl14().getBytes().length > 1000){
					errorCode += "|"+"EMPL14";
				}
				if(ValidationUtils.isEmpty(vo.getEmpl15()) || vo.getEmpl15().getBytes().length > 1000){
					errorCode += "|"+"EMPL15";
				}
				if(ValidationUtils.isEmpty(vo.getEmpl16()) || vo.getEmpl16().getBytes().length > 1000){
					errorCode += "|"+"EMPL16";
				}
				if(ValidationUtils.isEmpty(vo.getEmpl17()) || vo.getEmpl17().getBytes().length > 1000){
					errorCode += "|"+"EMPL17";
				}
				if(ValidationUtils.isEmpty(vo.getEmpl18()) || vo.getEmpl18().getBytes().length > 1000){
					errorCode += "|"+"EMPL18";
				}
				if(ValidationUtils.isEmpty(vo.getEmpl19()) || vo.getEmpl19().getBytes().length > 1000){
					errorCode += "|"+"EMPL19";
				}
				if(ValidationUtils.isEmpty(vo.getEmpl20()) || vo.getEmpl20().getBytes().length > 1000){
					errorCode += "|"+"EMPL20";
				}
				if(ValidationUtils.isNotZeroNull(vo.getEmplCnt())){
					for(int i=20; i>vo.getEmplCnt(); i--){
						String replaceCode = "|EMPL"+(i);
						errorCode = errorCode.replace(replaceCode, "");
					}
				}
			}
		}
		System.out.println("errorCode : " + errorCode);
		vo.setErrorCode(errorCode);

		return JsonUtil.responseJson(response, vo);
	}

	/**
	 * 사용자 엑셀 업로드 , 최종 업로드
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/researchBankUpload")
	public String researchBankUpload( ResearchBankItemVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) {
		commonVOProcessing(vo, request);
		
		String orgCd = UserBroker.getUserOrgCd(request);
		String reshSn = request.getParameter("reshSn");
		String[] chk = request.getParameterValues("chk");
		String[] lineNo = request.getParameterValues("lineNo");
		String[] reshItemTypeCd = request.getParameterValues("reshItemTypeCd");
		String[] emplViewType = request.getParameterValues("emplViewType");
		String[] emplCnt = request.getParameterValues("emplCnt");
		String[] reshItemCts = request.getParameterValues("reshItemCts");
		String[] empl1 = request.getParameterValues("empl1");
		String[] empl2 = request.getParameterValues("empl2");
		String[] empl3 = request.getParameterValues("empl3");
		String[] empl4 = request.getParameterValues("empl4");
		String[] empl5 = request.getParameterValues("empl5");
		String[] empl6 = request.getParameterValues("empl6");
		String[] empl7 = request.getParameterValues("empl7");
		String[] empl8 = request.getParameterValues("empl8");
		String[] empl9 = request.getParameterValues("empl9");
		String[] empl10 = request.getParameterValues("empl10");
		String[] empl11 = request.getParameterValues("empl11");
		String[] empl12 = request.getParameterValues("empl12");
		String[] empl13 = request.getParameterValues("empl13");
		String[] empl14 = request.getParameterValues("empl14");
		String[] empl15 = request.getParameterValues("empl15");
		String[] empl16 = request.getParameterValues("empl16");
		String[] empl17 = request.getParameterValues("empl17");
		String[] empl18 = request.getParameterValues("empl18");
		String[] empl19 = request.getParameterValues("empl19");
		String[] empl20 = request.getParameterValues("empl20");

		List<ResearchBankItemVO> researchBankList = new ArrayList<ResearchBankItemVO>();
		for(int i=0 ; i < reshItemTypeCd.length; i++) {
			//-- 체크된 사용자만 등록함.
			for(int j=0; j < chk.length; j++) {

				if(lineNo[i].equals(chk[j])) {
					ResearchBankItemVO rbVO = new ResearchBankItemVO();
					rbVO.setReshSn(Integer.parseInt(reshSn));
					rbVO.setReshItemTypeCd(reshItemTypeCd[i]);
					if("W".equals(emplViewType[i])){
						rbVO.setEmplViewType("WIDTH");
					} else if("H".equals(emplViewType[i])){
						rbVO.setEmplViewType("HEIGHT");
					}
					rbVO.setReshItemCts(reshItemCts[i].replaceAll("\\n", "<br/>"));
					if("K".equals(reshItemTypeCd[i])){
						rbVO.setEmplCnt(Integer.parseInt(emplCnt[i]));
						rbVO.setEmpl1(empl1[i]);
						rbVO.setEmpl2(empl2[i]);
						rbVO.setEmpl3(empl3[i]);
						rbVO.setEmpl4(empl4[i]);
						rbVO.setEmpl5(empl5[i]);
						rbVO.setEmpl6(empl6[i]);
						rbVO.setEmpl7(empl7[i]);
						rbVO.setEmpl8(empl8[i]);
						rbVO.setEmpl9(empl9[i]);
						rbVO.setEmpl10(empl10[i]);
						rbVO.setEmpl11(empl11[i]);
						rbVO.setEmpl12(empl12[i]);
						rbVO.setEmpl13(empl13[i]);
						rbVO.setEmpl14(empl14[i]);
						rbVO.setEmpl15(empl15[i]);
						rbVO.setEmpl16(empl16[i]);
						rbVO.setEmpl17(empl17[i]);
						rbVO.setEmpl18(empl18[i]);
						rbVO.setEmpl19(empl19[i]);
						rbVO.setEmpl20(empl20[i]);
					}
					researchBankList.add(rbVO);
				}
			}
		}
		ProcessResultVO<ResearchBankItemVO> resultVO = new ProcessResultVO<ResearchBankItemVO>().setReturnVO(vo);
		try {
			researchBankService.addResearchBankItemBatch(researchBankList);
			resultVO.setMessage(getMessage(request, "course.message.reshbank.item.add.success"));
			resultVO.setResult(1);
		} catch (Exception e) {
			resultVO.setMessage(getMessage(request, "course.message.reshbank.item.add.failed"));
			resultVO.setResult(-1);
		}

		return JsonUtil.responseJson(response, resultVO);
	}

}
