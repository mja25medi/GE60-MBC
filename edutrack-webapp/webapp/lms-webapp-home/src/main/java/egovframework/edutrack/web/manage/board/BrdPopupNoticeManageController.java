package egovframework.edutrack.web.manage.board;

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
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.service.SysCodeMemService;
import egovframework.edutrack.comm.util.web.HtmlCleaner;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.URLBuilder;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.board.bbs.service.BrdBbsInfoVO;
import egovframework.edutrack.modules.board.popup.service.BrdPopupNoticeService;
import egovframework.edutrack.modules.board.popup.service.BrdPopupNoticeVO;
import egovframework.edutrack.modules.system.code.service.SysCodeVO;

@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/mng/brd/popupNotice")
public class BrdPopupNoticeManageController extends GenericController {
	
	@Autowired @Qualifier("brdPopupNoticeService")
	private BrdPopupNoticeService 			brdPopupNoticeService;
	
	@Autowired @Qualifier("sysCodeMemService")
	private SysCodeMemService 			sysCodeMemService;

	
	/**
     * @Method Name : main
     * @Method 설명 : 팝업공지사항 메인(목록) 화면으로 이동
	 * @param VO
	 * @param commandMap
	 * @param model
	 * @return  
	 * @throws Exception
	 */
	@RequestMapping(value="/main")
	public String main(BrdPopupNoticeVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		if(vo.getPageIndex() == 0){
			vo.setPageIndex(1);
		}
		ProcessResultListVO<BrdPopupNoticeVO> resultList = brdPopupNoticeService.listPageing(vo, vo.getPageIndex());
    	request.setAttribute("popupList", resultList.getReturnList());
    	request.setAttribute("pageInfo", resultList.getPageInfo());
		request.setAttribute("vo", vo);
		request.setAttribute("paging", "Y");
		return "mng/board/popup/main";
	}
	
	/**
     * @Method Name : addFormPopup
     * @Method 설명 : 팝업공지사항 등록화면으로 이동
	 * @param VO
	 * @param commandMap
	 * @param model
	 * @return  
	 * @throws Exception
	 */
	@RequestMapping(value="/addFormPopupMain")
	public String addFormPopupMain(BrdPopupNoticeVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		vo.setEditorYn("Y");
		
		//팝업타입
		List<SysCodeVO> popupTypeList = sysCodeMemService.getSysCodeList("POPUP_TYPE_CD");
		request.setAttribute("popupTypeList", popupTypeList);
		
		request.setAttribute("gubun", "A");
		request.setAttribute("vo", vo);
		request.setAttribute("summernote", "Y");
		request.setAttribute("fileupload", "Y");
		return "mng/board/popup/write_popup";
	}
	
	/**
     * @Method Name : addPopup
     * @Method 설명 : 팝업공지사항 등록
	 * @param VO 
	 * @param commandMap
	 * @param model
	 * @return  
	 * @throws Exception
	 */
	@RequestMapping(value="/addPop")
	public String addPop(BrdPopupNoticeVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setRegNm(UserBroker.getUserName(request));
		vo.setOrgCd(orgCd);
		
		// 스크립트, 스타일 태그 제거
		vo.setCts( HtmlCleaner.cleanScript(vo.getCts()) );

		try {
			brdPopupNoticeService.addPopup(vo);
		} catch (Exception e) {
//			e.printStackTrace();
			setAlertMessage(request, getMessage(request, "board.message.popup.add.failed"));
			request.setAttribute("gubun", "A");
			request.setAttribute("vo", vo);			
			return "mng/board/popup/write_popup";
		}

		setAlertMessage(request, getMessage(request, "board.message.popup.add.success"));
		// 메인 화면으로
		return "redirect:"+
			new URLBuilder("/mng/brd/popupNotice/main")
				.addParameter("pageIndex", vo.getPageIndex())
				.addParameter("searchValue", vo.getSearchValue())
				.toString();
	}
	
	/**
     * @Method Name : editFormPopup
     * @Method 설명 : 팝업공지사항 수정화면으로 이동
	 * @param VO
	 * @param commandMap
	 * @param model
	 * @return  
	 * @throws Exception
	 */
	@RequestMapping(value="/editFormPopupMain")
	public String editFormPopupMain(BrdPopupNoticeVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		//팝업타입
		List<SysCodeVO> popupTypeList = sysCodeMemService.getSysCodeList("POPUP_TYPE_CD");
		request.setAttribute("popupTypeList", popupTypeList);
		vo = brdPopupNoticeService.view(vo);
		request.setAttribute("summernote", "Y");
		request.setAttribute("fileupload", "Y");
		request.setAttribute("gubun", "E");
		request.setAttribute("vo", vo);
		return "mng/board/popup/write_popup";
	}
	
	/**
     * @Method Name : editPopup
     * @Method 설명 : 팝업공지사항 수정
	 * @param VO 
	 * @param commandMap
	 * @param model
	 * @return  
	 * @throws Exception
	 */
	@RequestMapping(value="/editPopupMain")
	public String editPopupMain(BrdPopupNoticeVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setRegNm(UserBroker.getUserName(request));
		vo.setOrgCd(orgCd);
		
		// 스크립트, 스타일 태그 제거
		vo.setCts( HtmlCleaner.cleanScript(vo.getCts()) );

		try {
			brdPopupNoticeService.editPopup(vo);
		} catch (Exception e) {
//			e.printStackTrace();
			setAlertMessage(request, getMessage(request, "board.message.popup.edit.failed"));
			request.setAttribute("gubun", "E");
			request.setAttribute("vo", vo);			
			return "mng/board/popup/write_popup";
		}

		setAlertMessage(request, getMessage(request, "board.message.popup.edit.success"));
		// 메인 화면으로
		return "redirect:"+
			new URLBuilder("/mng/brd/popupNotice/main")
				.addParameter("pageIndex", vo.getPageIndex())
				.addParameter("searchValue", vo.getSearchValue())
				.toString();
	}
	
	/**
     * @Method Name : viewPopup
     * @Method 설명 : 팝업공지사항 상세화면으로 이동
	 * @param VO
	 * @param commandMap
	 * @param model
	 * @return  
	 * @throws Exception
	 */
	@RequestMapping(value="/viewPop")
	public String viewPop(BrdPopupNoticeVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		vo = brdPopupNoticeService.view(vo);
		request.setAttribute("vo", vo);
		request.setAttribute("commonPaging", "Y");
		return "mng/board/popup/view_popup";
	}
	
	/**
     * @Method Name : deletePopup
     * @Method 설명 :  팝업공지사항 삭제
	 * @param BrdBbsInfoVO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/deletePopup")
	public String deletePopup(BrdPopupNoticeVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		try {
			brdPopupNoticeService.remove(vo);
		} catch (Exception e) {
//			e.printStackTrace();
			setAlertMessage(request, getMessage(request, "board.message.popup.delete.failed"));
			request.setAttribute("gubun", "E");
			request.setAttribute("vo", vo);			
			return "mng/board/popup/write_popup";
		}

		setAlertMessage(request, getMessage(request, "board.message.popup.delete.success"));
		// 메인 화면으로
		return "redirect:"+
			new URLBuilder("/mng/brd/popupNotice/main")
				.addParameter("pageIndex", vo.getPageIndex())
				.addParameter("searchValue", vo.getSearchValue())
				.toString();
	}
}
