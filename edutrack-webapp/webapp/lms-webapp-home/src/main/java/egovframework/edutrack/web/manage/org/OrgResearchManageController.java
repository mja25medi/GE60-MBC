package egovframework.edutrack.web.manage.org;

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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.service.SysCodeMemService;
import egovframework.edutrack.comm.util.web.DateTimeUtil;
import egovframework.edutrack.comm.util.web.FileUtil;
import egovframework.edutrack.comm.util.web.HtmlCleaner;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.course.creCrsResh.service.CreCrsReshVO;
import egovframework.edutrack.modules.course.creCrsResh.service.CrsReshAnsrVO;
import egovframework.edutrack.modules.course.creCrsResh.service.CrsReshResultVO;
import egovframework.edutrack.modules.course.createcourse.service.CreateCourseService;
import egovframework.edutrack.modules.org.resh.service.OrgReshAnsrVO;
import egovframework.edutrack.modules.course.creterm.service.CourseCretermService;
import egovframework.edutrack.modules.course.creterm.service.CourseCretermVO;
import egovframework.edutrack.modules.course.research.service.ResearchBankItemVO;
import egovframework.edutrack.modules.log.prnlog.service.LogPrnLogService;
import egovframework.edutrack.modules.log.prnlog.service.LogPrnLogVO;
import egovframework.edutrack.modules.org.resh.service.OrgReshItemVO;
import egovframework.edutrack.modules.org.resh.service.OrgReshService;
import egovframework.edutrack.modules.org.resh.service.OrgReshVO;
import egovframework.edutrack.modules.system.code.service.SysCodeVO;


@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/mng/org/research")
public class OrgResearchManageController extends GenericController {

	@Autowired @Qualifier("orgReshService")
	private OrgReshService	orgReshService;

	@Autowired @Qualifier("createCourseService")
	private CreateCourseService 	createCourseService;
	
	@Autowired @Qualifier("sysCodeMemService")
	private SysCodeMemService			sysCodeMemService;
	
	@Autowired @Qualifier("courseCretermService")
	private CourseCretermService			courseCretermService;
	
	@Autowired @Qualifier("logPrnLogService")
	private LogPrnLogService				logPrnLogService;
	
	/**
	 * 설문 은행 관리 메인
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/main")
	public String main( OrgReshVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		

		request.setAttribute("vo", vo);
		request.setAttribute("paging", "Y");
		
		
		
		return "mng/org/resh/org_resh_main";
	}

	/**
	 * 설문 은행 목록 조회
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/list")
	public String list( OrgReshVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		//OrgReshVO.setRegYn(""); //-- 모든 설문을 가져오기 위해 등록 여부 비우기


		ProcessResultListVO<OrgReshVO> resultList = orgReshService.listPageing(vo, vo.getCurPage(), vo.getListScale());

		request.setAttribute("orgReshList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());
		request.setAttribute("vo", vo);
		return "mng/org/resh/org_resh_list_div";
	}

	/**
	 * 설문 은행 목록 조회(설문 명 검색)
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/searchList")
	public String searchList( OrgReshVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		return JsonUtil
			.responseJson(response, orgReshService.searchListResearch(vo));
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
	public String addPop(OrgReshVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		String orgCd = UserBroker.getUserOrgCd(request);
		
		
		OrgReshVO ovo = new OrgReshVO();
		ovo.setOrgCd(orgCd);
		ovo.setReshTypeCd("D");
		ProcessResultListVO<OrgReshVO> resultList = orgReshService.listPageing(ovo, ovo.getCurPage(), ovo.getListScale());
		
		if(resultList.getReturnList().size() > 0) {
			request.setAttribute("DTypeExist", "Y");			
		} else {
			request.setAttribute("DTypeExist", "N");						
		}
		request.setAttribute("gubun", "A");
		request.setAttribute("vo", vo);
		return "mng/org/resh/org_resh_write_pop";
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
	public String editPop( OrgReshVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		
		OrgReshVO ovo = new OrgReshVO();
		ovo.setOrgCd(orgCd);
		ovo.setReshTypeCd("D");
		ProcessResultListVO<OrgReshVO> resultList = orgReshService.listPageing(ovo, ovo.getCurPage(), ovo.getListScale());
		
		if(resultList.getReturnList().size() > 0) {
			request.setAttribute("DTypeExist", "Y");			
		} else {
			request.setAttribute("DTypeExist", "N");						
		}
		
		vo = orgReshService.viewResearch(vo).getReturnVO();
		
		
		if(ValidationUtils.isNotEmpty(vo.getStartDttm()))
			vo.setStartDttm(DateTimeUtil.getDateText(1, vo.getStartDttm().substring(0,8),"."));
		if(ValidationUtils.isNotEmpty(vo.getEndDttm()))
			vo.setEndDttm(DateTimeUtil.getDateText(1, vo.getEndDttm().substring(0,8),"."));

		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "E");
		return "mng/org/resh/org_resh_write_pop";
	}

	/**
	 * 설문 은행 정보 조회
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/view")
	public String view( OrgReshVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		return JsonUtil
			.responseJson(response, orgReshService.viewResearch(vo));
	}

	/**
	 * 설문 은행 정보 등록
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/add")
	public String add( OrgReshVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		String startDttm = StringUtil.ReplaceAll(vo.getStartDttm(),".","")+"000001"; 
		String endDttm =StringUtil.ReplaceAll(vo.getEndDttm(),".","")+"235959";
		vo.setStartDttm(startDttm); 
		vo.setEndDttm(endDttm);


		ProcessResultVO<OrgReshVO> resultVO = orgReshService.addResearch(vo);
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
	public String edit( OrgReshVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		
		String startDttm = StringUtil.ReplaceAll(vo.getStartDttm(),".","")+"000001"; 
		String endDttm =StringUtil.ReplaceAll(vo.getEndDttm(),".","")+"235959";
		vo.setStartDttm(startDttm); 
		vo.setEndDttm(endDttm);

		ProcessResultVO<OrgReshVO> resultVO = orgReshService.editResearch(vo);
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
	public String delete( OrgReshVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		ProcessResultVO<OrgReshVO> resultVO = orgReshService.deleteResearch(vo);
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
	public String manageForm( OrgReshVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		vo = orgReshService.viewResearch(vo).getReturnVO();
		request.setAttribute("paging", "Y");
		request.setAttribute("vo", vo);
		return "mng/org/resh/org_resh_manage_main";
	}


	/**
	 * 설문 문제 목록 조회
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/listItem")
	public String listItem( OrgReshVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		vo = orgReshService.viewResearch(vo).getReturnVO();

		ProcessResultListVO<OrgReshItemVO> resultList = orgReshService.listItem(vo);

		request.setAttribute("OrgReshVO", vo);
		request.setAttribute("OrgReshItemList", resultList.getReturnList());
		return "mng/org/resh/org_resh_item_list_div";
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
	public String addItemPop(OrgReshItemVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<SysCodeVO> reshItemTypeCdList = sysCodeMemService.getSysCodeList("RESH_ITEM_TYPE_CD", UserBroker.getLocaleKey(request));
        List<SysCodeVO> emplViewTypeList = sysCodeMemService.getSysCodeList("EMPL_VIEW_TYPE", UserBroker.getLocaleKey(request));
		
        OrgReshVO reshVO = new OrgReshVO();
        reshVO.setReshSn(vo.getReshSn());
        
        reshVO = orgReshService.viewResearch(reshVO).getReturnVO();
        
        vo.setReshType(reshVO.getReshType());

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
	public String addItem( OrgReshItemVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		if("K".equals(vo.getReshItemTypeCd()) && "I".equals(vo.getReshType())) {
			if(vo.getEmplCnt() < 20) {
				vo.setEmpl20("");
				vo.setEmplScore20(0);					
			}
			if(vo.getEmplCnt() < 19) {
				vo.setEmpl19("");
				vo.setEmplScore19(0);				
			}
			if(vo.getEmplCnt() < 18) {
				vo.setEmpl18("");
				vo.setEmplScore18(0);				
			}
			if(vo.getEmplCnt() < 17) {
				vo.setEmpl17("");
				vo.setEmplScore17(0);				
			}
			if(vo.getEmplCnt() < 16) {
				vo.setEmpl16("");
				vo.setEmplScore16(0);				
			}
			if(vo.getEmplCnt() < 15) {
				vo.setEmpl15("");
				vo.setEmplScore15(0);				
			}
			if(vo.getEmplCnt() < 14) {
				vo.setEmpl14("");
				vo.setEmplScore14(0);				
			}
			if(vo.getEmplCnt() < 13) {
				vo.setEmpl13("");
				vo.setEmplScore13(0);				
			}
			if(vo.getEmplCnt() < 12) {
				vo.setEmpl12("");
				vo.setEmplScore12(0);				
			}
			if(vo.getEmplCnt() < 11) {
				vo.setEmpl11("");
				vo.setEmplScore11(0);				
			}
			if(vo.getEmplCnt() < 10) {
				vo.setEmpl10("");
				vo.setEmplScore10(0);				
			}
			if(vo.getEmplCnt() < 9) {
				vo.setEmpl9("");
				vo.setEmplScore9(0);				
			}
			if(vo.getEmplCnt() < 8) {
				vo.setEmpl8("");
				vo.setEmplScore8(0);				
			}
			if(vo.getEmplCnt() < 7) {
				vo.setEmpl7("");
				vo.setEmplScore7(0);				
			}
			if(vo.getEmplCnt() < 6) {
				vo.setEmpl6("");
				vo.setEmplScore6(0);				
			}
			if(vo.getEmplCnt() < 5) {
				vo.setEmpl5("");
				vo.setEmplScore5(0);				
			}
			if(vo.getEmplCnt() < 4) {
				vo.setEmpl4("");
				vo.setEmplScore4(0);				
			}
			if(vo.getEmplCnt() < 3) {
				vo.setEmpl3("");
				vo.setEmplScore3(0);				
			}

		} 
		if("G".equals(vo.getReshType()) || ("D".equals(vo.getReshItemTypeCd()) && "I".equals(vo.getReshType()))){
			if("D".equals(vo.getReshItemTypeCd())) {
				vo.setEmplCnt(0);
			}
			vo.setEmplScore1(0);
			vo.setEmplScore2(0);
			vo.setEmplScore3(0);
			vo.setEmplScore4(0);
			vo.setEmplScore5(0);
			vo.setEmplScore6(0);
			vo.setEmplScore7(0);
			vo.setEmplScore8(0);
			vo.setEmplScore9(0);
			vo.setEmplScore10(0);
			vo.setEmplScore11(0);
			vo.setEmplScore12(0);
			vo.setEmplScore13(0);
			vo.setEmplScore14(0);
			vo.setEmplScore15(0);
			vo.setEmplScore16(0);
			vo.setEmplScore17(0);
			vo.setEmplScore18(0);
			vo.setEmplScore19(0);
			vo.setEmplScore20(0);
		}

		// 스크립트, 스타일 태그 제거
		vo.setReshItemCts(HtmlCleaner.cleanScript(vo.getReshItemCts()) );

		ProcessResultVO<OrgReshItemVO> resultVO = orgReshService.addItem(vo);
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
	public String editItemPop( OrgReshItemVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
        List<SysCodeVO> reshItemTypeCdList = sysCodeMemService.getSysCodeList("RESH_ITEM_TYPE_CD", UserBroker.getLocaleKey(request));
        List<SysCodeVO> emplViewTypeList = sysCodeMemService.getSysCodeList("EMPL_VIEW_TYPE", UserBroker.getLocaleKey(request));
		request.setAttribute("reshItemTypeCdList", reshItemTypeCdList);
		request.setAttribute("emplViewTypeList", emplViewTypeList);
		
		//-- 설문 문제 정보를 찾아 Form에 셋팅
		vo = orgReshService.viewItem(vo).getReturnVO();
		
        OrgReshVO reshVO = new OrgReshVO();
        reshVO.setReshSn(vo.getReshSn());
        
        reshVO = orgReshService.viewResearch(reshVO).getReturnVO();
        
        vo.setReshType(reshVO.getReshType());

		
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
	public String editItem( OrgReshItemVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		if("K".equals(vo.getReshItemTypeCd()) && "I".equals(vo.getReshType())) {
			if(vo.getEmplCnt() < 20) {
				vo.setEmpl20("");
				vo.setEmplScore20(0);					
			}
			if(vo.getEmplCnt() < 19) {
				vo.setEmpl19("");
				vo.setEmplScore19(0);				
			}
			if(vo.getEmplCnt() < 18) {
				vo.setEmpl18("");
				vo.setEmplScore18(0);				
			}
			if(vo.getEmplCnt() < 17) {
				vo.setEmpl17("");
				vo.setEmplScore17(0);				
			}
			if(vo.getEmplCnt() < 16) {
				vo.setEmpl16("");
				vo.setEmplScore16(0);				
			}
			if(vo.getEmplCnt() < 15) {
				vo.setEmpl15("");
				vo.setEmplScore15(0);				
			}
			if(vo.getEmplCnt() < 14) {
				vo.setEmpl14("");
				vo.setEmplScore14(0);				
			}
			if(vo.getEmplCnt() < 13) {
				vo.setEmpl13("");
				vo.setEmplScore13(0);				
			}
			if(vo.getEmplCnt() < 12) {
				vo.setEmpl12("");
				vo.setEmplScore12(0);				
			}
			if(vo.getEmplCnt() < 11) {
				vo.setEmpl11("");
				vo.setEmplScore11(0);				
			}
			if(vo.getEmplCnt() < 10) {
				vo.setEmpl10("");
				vo.setEmplScore10(0);				
			}
			if(vo.getEmplCnt() < 9) {
				vo.setEmpl9("");
				vo.setEmplScore9(0);				
			}
			if(vo.getEmplCnt() < 8) {
				vo.setEmpl8("");
				vo.setEmplScore8(0);				
			}
			if(vo.getEmplCnt() < 7) {
				vo.setEmpl7("");
				vo.setEmplScore7(0);				
			}
			if(vo.getEmplCnt() < 6) {
				vo.setEmpl6("");
				vo.setEmplScore6(0);				
			}
			if(vo.getEmplCnt() < 5) {
				vo.setEmpl5("");
				vo.setEmplScore5(0);				
			}
			if(vo.getEmplCnt() < 4) {
				vo.setEmpl4("");
				vo.setEmplScore4(0);				
			}
			if(vo.getEmplCnt() < 3) {
				vo.setEmpl3("");
				vo.setEmplScore3(0);				
			}

		} 
		if("G".equals(vo.getReshType()) || ("D".equals(vo.getReshItemTypeCd()) && "I".equals(vo.getReshType()))){
			if("D".equals(vo.getReshItemTypeCd())) {
				vo.setEmplCnt(0);
			}
			vo.setEmplScore1(0);
			vo.setEmplScore2(0);
			vo.setEmplScore3(0);
			vo.setEmplScore4(0);
			vo.setEmplScore5(0);
			vo.setEmplScore6(0);
			vo.setEmplScore7(0);
			vo.setEmplScore8(0);
			vo.setEmplScore9(0);
			vo.setEmplScore10(0);
			vo.setEmplScore11(0);
			vo.setEmplScore12(0);
			vo.setEmplScore13(0);
			vo.setEmplScore14(0);
			vo.setEmplScore15(0);
			vo.setEmplScore16(0);
			vo.setEmplScore17(0);
			vo.setEmplScore18(0);
			vo.setEmplScore19(0);
			vo.setEmplScore20(0);
		}

		// 스크립트, 스타일 태그 제거
		vo.setReshItemCts(HtmlCleaner.cleanScript(vo.getReshItemCts()) );

		ProcessResultVO<OrgReshItemVO> resultVO = orgReshService.editItem(vo);
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
	public String deleteItem( OrgReshItemVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultVO<OrgReshItemVO> resultVO = new ProcessResultVO<OrgReshItemVO>();
		try {
			resultVO = orgReshService.deleteItem(vo);
			resultVO.setMessage(getMessage(request, "course.message.reshbank.item.delete.success"));
		} catch (Exception e) {
			resultVO.setMessage(getMessage(request, "course.message.reshbank.item.delete.failed.use"));
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

		return "mng/org/resh/org_resh_templet_main_pop";
	}

	private String getEditorType(HttpServletRequest request) {
		String forwardUrl = "mng/org/resh/org_resh_item_write_pop";
		//-- 에디터 사용여부를 판단하여 해당 Webeditor를 사용하도록 한다.
		//-- BBS_INFO의 editorUseYn은 더이상 사용하지 않음.
		if("Y".equals(Constants.WEBEDITOR_YSEYN)) {
			if("DAUMEDITOR".equals(Constants.WEBEDITOR_NAME)) {
				forwardUrl = "mng/org/resh/org_resh_item_write_daumeditor_pop";
				request.setAttribute("daumeditor", "Y");
			} else if("SUMMERNOTE".equals(Constants.WEBEDITOR_NAME)) {
				forwardUrl = "mng/org/resh/org_resh_item_write_summernote_pop";
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
	public String viewResh( OrgReshVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		vo = orgReshService.viewResearch(vo).getReturnVO();

		request.setAttribute("vo", vo);

		return "mng/org/resh/org_resh_view_pop";
	}

	/**
	 * 설문 은행 문제 순서변경
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/listReserchOrder")
	public String listReserchOrder( OrgReshItemVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultListVO<OrgReshItemVO> resultList = orgReshService.listItem(vo);

		request.setAttribute("OrgReshItemList", resultList.getReturnList());
		request.setAttribute("vo", vo);
		return "mng/org/resh/org_resh_item_sort";
	}

	@RequestMapping(value="/sortReserchItem")
	public String sortReserchItem( OrgReshItemVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		return JsonUtil
				.responseJson(response, orgReshService.sortReserchItem(vo));
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
	public String addResearchItemExcel ( OrgReshItemVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {
		commonVOProcessing(vo, request);

		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "A");
		request.setAttribute("fileupload", "Y");
		return "mng/org/resh/org_resh_item_write_excel_pop";
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
	public String sampleExcelResearchBank ( OrgReshItemVO vo, Map commandMap, ModelMap model,
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
		titles.put("score1", getMessage(request, "course.title.exambank.item1.score"));
		titles.put("item2", getMessage(request, "course.title.exambank.item2"));
		titles.put("score2", getMessage(request, "course.title.exambank.item2.score"));
		titles.put("item3", getMessage(request, "course.title.exambank.item3"));
		titles.put("score3", getMessage(request, "course.title.exambank.item3.score"));
		titles.put("item4", getMessage(request, "course.title.exambank.item4"));
		titles.put("score4", getMessage(request, "course.title.exambank.item4.score"));
		titles.put("item5", getMessage(request, "course.title.exambank.item5"));
		titles.put("score5", getMessage(request, "course.title.exambank.item5.score"));
		titles.put("item6", getMessage(request, "course.title.exambank.item6"));
		titles.put("score6", getMessage(request, "course.title.exambank.item6.score"));
		titles.put("item7", getMessage(request, "course.title.exambank.item7"));
		titles.put("score7", getMessage(request, "course.title.exambank.item7.score"));
		titles.put("item8", getMessage(request, "course.title.exambank.item8"));
		titles.put("score8", getMessage(request, "course.title.exambank.item8.score"));
		titles.put("item9", getMessage(request, "course.title.exambank.item9"));
		titles.put("score9", getMessage(request, "course.title.exambank.item9.score"));
		titles.put("item10", getMessage(request, "course.title.exambank.item10"));
		titles.put("score10", getMessage(request, "course.title.exambank.item10.score"));
		titles.put("item11", getMessage(request, "course.title.exambank.item11"));
		titles.put("score11", getMessage(request, "course.title.exambank.item11.score"));
		titles.put("item12", getMessage(request, "course.title.exambank.item12"));
		titles.put("score12", getMessage(request, "course.title.exambank.item12.score"));
		titles.put("item13", getMessage(request, "course.title.exambank.item13"));
		titles.put("score13", getMessage(request, "course.title.exambank.item13.score"));
		titles.put("item14", getMessage(request, "course.title.exambank.item14"));
		titles.put("score14", getMessage(request, "course.title.exambank.item14.score"));
		titles.put("item15", getMessage(request, "course.title.exambank.item15"));
		titles.put("score15", getMessage(request, "course.title.exambank.item15.score"));
		titles.put("item16", getMessage(request, "course.title.exambank.item16"));
		titles.put("score16", getMessage(request, "course.title.exambank.item16.score"));
		titles.put("item17", getMessage(request, "course.title.exambank.item17"));
		titles.put("score17", getMessage(request, "course.title.exambank.item17.score"));
		titles.put("item18", getMessage(request, "course.title.exambank.item18"));
		titles.put("score18", getMessage(request, "course.title.exambank.item18.score"));
		titles.put("item19", getMessage(request, "course.title.exambank.item19"));
		titles.put("score19", getMessage(request, "course.title.exambank.item19.score"));
		titles.put("item20", getMessage(request, "course.title.exambank.item20"));
		titles.put("score20", getMessage(request, "course.title.exambank.item20.score"));

		response.setHeader("Content-Disposition", "attachment;filename=research_item_sample.xlsx;");
		response.setHeader( "Content-Transfer-Coding", "binary" );
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		try {
			orgReshService.sampleExcelDownload(titles, response.getOutputStream());
		} catch (Exception e) {
			log.error("Exception occurred");
		}
		return null;
	}

	/**
	 * 설문 은행 문제 엑셀 업로드
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/excelUploadCheckPop")
	public String excelUploadCheckPop( OrgReshItemVO vo,  Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) {
		String fileName = StringUtil.nvl(request.getParameter("fileName"));
		
		String type =  StringUtil.nvl(request.getParameter("type"));
		String fileDir =  StringUtil.nvl(request.getParameter("fileDir"));
		String orgCd = StringUtil.nvl(UserBroker.getUserOrgCd(request), "ORG0000001");
		String filePath = FileUtil.fullFilePath(type, fileDir, orgCd);
		
		ProcessResultListVO<OrgReshItemVO> resultList = null;
		resultList = orgReshService.excelUploadValidationCheck(fileName, filePath);
		request.setAttribute("researchBankList", resultList.getReturnList());
		request.setAttribute("vo", vo);
		request.setAttribute("fileupload", "Y");
		return "mng/org/resh/org_resh_item_write_excel_validate_pop";
	}

	/**
	 * 설문 은행 문제 엑셀 업로드 , 단건 체크
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/researchBankUploadCheck")
	public String researchBankUploadCheck( OrgReshItemVO vo, Map commandMap, ModelMap model,
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
		if(StringUtils.hasText(errorCode)) {
			log.error("errorCode : " + errorCode);
		}
		vo.setErrorCode(errorCode);

		return JsonUtil.responseJson(response, vo);
	}

	@RequestMapping(value="/researchBankUpload")
	public String researchBankUpload( OrgReshItemVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
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

		String[] emplScore1 = request.getParameterValues("emplScore1");
		String[] emplScore2 = request.getParameterValues("emplScore2");
		String[] emplScore3 = request.getParameterValues("emplScore3");
		String[] emplScore4 = request.getParameterValues("emplScore4");
		String[] emplScore5 = request.getParameterValues("emplScore5");
		String[] emplScore6 = request.getParameterValues("emplScore6");
		String[] emplScore7 = request.getParameterValues("emplScore7");
		String[] emplScore8 = request.getParameterValues("emplScore8");
		String[] emplScore9 = request.getParameterValues("emplScore9");
		String[] emplScore10 = request.getParameterValues("emplScore10");
		String[] emplScore11 = request.getParameterValues("emplScore11");
		String[] emplScore12 = request.getParameterValues("emplScore12");
		String[] emplScore13 = request.getParameterValues("emplScore13");
		String[] emplScore14 = request.getParameterValues("emplScore14");
		String[] emplScore15 = request.getParameterValues("emplScore15");
		String[] emplScore16 = request.getParameterValues("emplScore16");
		String[] emplScore17 = request.getParameterValues("emplScore17");
		String[] emplScore18 = request.getParameterValues("emplScore18");
		String[] emplScore19 = request.getParameterValues("emplScore19");
		String[] emplScore20 = request.getParameterValues("emplScore20");
		
		List<OrgReshItemVO> researchBankList = new ArrayList<OrgReshItemVO>();
		for(int i=0 ; i < reshItemTypeCd.length; i++) {
			//-- 체크된 사용자만 등록함.
			for(int j=0; j < chk.length; j++) {
				

				//설문 은행에서 일반형, 점수형 유형을 가져옴
				OrgReshVO iOrgReshVO = new OrgReshVO();
				iOrgReshVO.setReshSn(Integer.parseInt(reshSn));
				iOrgReshVO = orgReshService.viewResearch(iOrgReshVO).getReturnVO();
				String reshType = iOrgReshVO.getReshTypeCd();
				
				if(lineNo[i].equals(chk[j])) {
					OrgReshItemVO rbVO = new OrgReshItemVO();
					rbVO.setReshSn(Integer.parseInt(reshSn));
					rbVO.setReshItemTypeCd(reshItemTypeCd[i]);
					if("W".equals(emplViewType[i])){
						rbVO.setEmplViewType("WIDTH");
					} else if("H".equals(emplViewType[i])){
						rbVO.setEmplViewType("HEIGHT");
					}
					rbVO.setReshItemCts(reshItemCts[i].replaceAll("\\n", "<br/>"));
					
					if( /*"I".equals(reshType) && */"K".equals(reshItemTypeCd[i]) ){
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

						rbVO.setEmplScore1(Integer.parseInt(emplScore1[i]));
						rbVO.setEmplScore2(Integer.parseInt(emplScore2[i]));
						rbVO.setEmplScore3(Integer.parseInt(emplScore3[i]));
						rbVO.setEmplScore4(Integer.parseInt(emplScore4[i]));
						rbVO.setEmplScore5(Integer.parseInt(emplScore5[i]));
						rbVO.setEmplScore6(Integer.parseInt(emplScore6[i]));
						rbVO.setEmplScore7(Integer.parseInt(emplScore7[i]));
						rbVO.setEmplScore8(Integer.parseInt(emplScore8[i]));
						rbVO.setEmplScore9(Integer.parseInt(emplScore9[i]));
						rbVO.setEmplScore10(Integer.parseInt(emplScore10[i]));
						rbVO.setEmplScore11(Integer.parseInt(emplScore11[i]));
						rbVO.setEmplScore12(Integer.parseInt(emplScore12[i]));
						rbVO.setEmplScore13(Integer.parseInt(emplScore13[i]));
						rbVO.setEmplScore14(Integer.parseInt(emplScore14[i]));
						rbVO.setEmplScore15(Integer.parseInt(emplScore15[i]));
						rbVO.setEmplScore16(Integer.parseInt(emplScore16[i]));
						rbVO.setEmplScore17(Integer.parseInt(emplScore17[i]));
						rbVO.setEmplScore18(Integer.parseInt(emplScore18[i]));
						rbVO.setEmplScore19(Integer.parseInt(emplScore19[i]));
						rbVO.setEmplScore20(Integer.parseInt(emplScore20[i]));
						
						if( Integer.parseInt(emplCnt[i]) < 20) {
							rbVO.setEmplScore20(0);
						}
						if( Integer.parseInt(emplCnt[i]) < 19) {
							rbVO.setEmplScore19(0);
						}
						if( Integer.parseInt(emplCnt[i]) < 18) {
							rbVO.setEmplScore18(0);
						}
						if( Integer.parseInt(emplCnt[i]) < 17) {
							rbVO.setEmplScore17(0);
						}
						if( Integer.parseInt(emplCnt[i]) < 16) {
							rbVO.setEmplScore16(0);
						}
						if( Integer.parseInt(emplCnt[i]) < 15) {
							rbVO.setEmplScore15(0);
						}
						if( Integer.parseInt(emplCnt[i]) < 14) {
							rbVO.setEmplScore14(0);
						}
						if( Integer.parseInt(emplCnt[i]) < 13) {
							rbVO.setEmplScore13(0);
						}
						if( Integer.parseInt(emplCnt[i]) < 12) {
							rbVO.setEmplScore12(0);
						}
						if( Integer.parseInt(emplCnt[i]) < 11) {
							rbVO.setEmplScore11(0);
						}
						if( Integer.parseInt(emplCnt[i]) < 10) {
							rbVO.setEmplScore10(0);
						}
						if( Integer.parseInt(emplCnt[i]) < 9) {
							rbVO.setEmplScore9(0);
						}
						if( Integer.parseInt(emplCnt[i]) < 8) {
							rbVO.setEmplScore8(0);
						}
						if( Integer.parseInt(emplCnt[i]) < 7) {
							rbVO.setEmplScore7(0);
						}
						if( Integer.parseInt(emplCnt[i]) < 6) {
							rbVO.setEmplScore6(0);
						}
						if( Integer.parseInt(emplCnt[i]) < 5) {
							rbVO.setEmplScore5(0);
						}
						if( Integer.parseInt(emplCnt[i]) < 4) {
							rbVO.setEmplScore4(0);
						}
						if( Integer.parseInt(emplCnt[i]) < 3) {
							rbVO.setEmplScore3(0);
						}
					}					

					if( "G".equals(reshType) || ("D".equals(reshItemTypeCd[i]) && "I".equals(reshType))) {
						if("D".equals(reshItemTypeCd[i])) {
							rbVO.setEmplCnt(0);
						}
						if("K".equals(reshItemTypeCd[i])) {
							rbVO.setEmplCnt(Integer.parseInt(emplCnt[i]));
						}						
						rbVO.setEmplScore1(0);
						rbVO.setEmplScore2(0);
						rbVO.setEmplScore3(0);
						rbVO.setEmplScore4(0);
						rbVO.setEmplScore5(0);
						rbVO.setEmplScore6(0);
						rbVO.setEmplScore7(0);
						rbVO.setEmplScore8(0);
						rbVO.setEmplScore9(0);
						rbVO.setEmplScore10(0);
						rbVO.setEmplScore11(0);
						rbVO.setEmplScore12(0);
						rbVO.setEmplScore13(0);
						rbVO.setEmplScore14(0);
						rbVO.setEmplScore15(0);
						rbVO.setEmplScore16(0);
						rbVO.setEmplScore17(0);
						rbVO.setEmplScore18(0);
						rbVO.setEmplScore19(0);
						rbVO.setEmplScore20(0);							
					}
					
					researchBankList.add(rbVO);
				}
			}
		}
		ProcessResultVO<OrgReshItemVO> resultVO = new ProcessResultVO<OrgReshItemVO>().setReturnVO(vo);
		try {
			orgReshService.addOrgReshItemBatch(researchBankList);
			resultVO.setMessage(getMessage(request, "course.message.reshbank.item.add.success"));
			resultVO.setResult(1);
		} catch (Exception e) {
			resultVO.setMessage(getMessage(request, "course.message.reshbank.item.add.failed"));
			resultVO.setResult(-1);
		}

		return JsonUtil.responseJson(response, resultVO);
	}
	
	/**
	 * 설문 관리 메인
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/listExpulsion")
	public String listExpulsion( OrgReshVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		
		OrgReshAnsrVO iOrgReshAnsrVO = new OrgReshAnsrVO();
		iOrgReshAnsrVO.setCurPage(Integer.parseInt(StringUtil.nvl(request.getParameter("curPage"),"1")));
		iOrgReshAnsrVO.setListScale(Integer.parseInt(StringUtil.nvl(request.getParameter("listScale"),"20")));
		iOrgReshAnsrVO.setPageScale(10);
		iOrgReshAnsrVO.setOrgCd(orgCd);
		ProcessResultListVO<OrgReshAnsrVO> resultList = orgReshService.listExpulsionPageing(iOrgReshAnsrVO);
		
		request.setAttribute("vo", vo);
		request.setAttribute("listExpulsion", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());
		return "mng/org/resh/org_resh_expulsion_list_div";
	}

	/**
	 * 퇴교설문 결과 관리
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/reshResultPop")
	public String reshResultPop( OrgReshAnsrVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		request.setAttribute("vo", vo);
		
		ProcessResultListVO<OrgReshAnsrVO> resultList = orgReshService.reshAnsrList(vo);
		request.setAttribute("ansrList", resultList.getReturnList());


		return "mng/org/resh/org_resh_result_pop";
	}
	
	/**
	 * 설문결과 점수결과보기 팝업
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/rsltScorePop")
	public String rsltScorePop( OrgReshItemVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		request.setAttribute("vo", vo);
		
		ProcessResultListVO<OrgReshItemVO> resultList = orgReshService.listRsltScore(vo);
		request.setAttribute("ansrList", resultList.getReturnList());
		
		return "mng/org/resh/org_resh_result_score_pop";
	}
	
	/**
	 * 설문결과 결과보기 팝업
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/rsltPop")
	public String rsltPop( OrgReshItemVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		request.setAttribute("vo", vo);
		
		ProcessResultListVO<OrgReshItemVO> resultList = orgReshService.listRsltScore(vo);
		request.setAttribute("ansrList", resultList.getReturnList());
		
		return "mng/org/resh/org_resh_result_ansr_pop";
	}
	
	/**
	 * 설문결과 점수 다운로드
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/reshScoreExcelDownload")
	public String reshScoreExcelDownload( OrgReshItemVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgNm = UserBroker.getUserOrgNm(request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		//-- 엑셀 출력 로그를 저장한다.
		LogPrnLogVO printLogVO = new LogPrnLogVO();
		printLogVO.setUserNo(UserBroker.getUserNo(request));
		printLogVO.setUserNm(UserBroker.getUserName(request));
		printLogVO.setPrnDoc("설문지 관리 : 일반 설문 관리_설문결과");
		printLogVO.setParam(vo.toString());
		logPrnLogService.add(printLogVO);

		response.setHeader("Content-Disposition", "attachment;filename=Resh_Score_Result.xlsx;");
		response.setHeader( "Content-Transfer-Coding", "binary" );
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		orgReshService.listExcelScoreDownload(vo, orgNm, response.getOutputStream());

		return null;
	}
	
	/**
	 * 설문결과 결과보기 - 결과보기(상세보기 팝업)
	 * @return
	 */
	@RequestMapping(value="/mainOpinionPop")
	public String mainOpinionPop( OrgReshItemVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		List<OrgReshItemVO> crsReshResultList = orgReshService.listOpinion(vo).getReturnList();

		request.setAttribute("vo", vo);
		request.setAttribute("crsReshAnsrList", crsReshResultList);
		return "mng/org/resh/org_resh_result_opinion";
	}

}
