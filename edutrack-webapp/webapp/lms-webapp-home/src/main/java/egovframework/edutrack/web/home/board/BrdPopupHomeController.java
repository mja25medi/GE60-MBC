package egovframework.edutrack.web.home.board;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.board.popup.service.BrdPopupNoticeService;
import egovframework.edutrack.modules.board.popup.service.BrdPopupNoticeVO;
import egovframework.edutrack.modules.main.service.MainVO;

@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/home/brd/popup")
public class BrdPopupHomeController extends GenericController {
	
	@Autowired @Qualifier("brdPopupNoticeService")
	private BrdPopupNoticeService brdPopupNoticeService; //인터페이스 선언부
	
	/**
     * @Method Name : indexRead
     * @Method 설명 : 팝업 보기
	 * @param MainVO 
	 * @param commandMap
	 * @param model
	 * @return  "board/popup/popup_notice_pop"
	 * @throws Exception
	 */
	@RequestMapping(value="/indexRead")
	public String indexRead(MainVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);

		BrdPopupNoticeVO brdPopupNoticeVO = new BrdPopupNoticeVO();
		brdPopupNoticeVO.setOrgCd(orgCd);
		brdPopupNoticeVO.setPopupSn(Integer.parseInt(request.getParameter("popupSn")));

		brdPopupNoticeVO = brdPopupNoticeService.view(brdPopupNoticeVO);
		model.addAttribute("brdPopupNoticeVO", brdPopupNoticeVO);

		return "home/board/popup/popup_notice_pop";
	}
	
	@RequestMapping(value="/indexReadPop2")
	public String indexReadPop2(MainVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);

		BrdPopupNoticeVO brdPopupNoticeVO = new BrdPopupNoticeVO();
		brdPopupNoticeVO.setOrgCd(orgCd);
		brdPopupNoticeVO.setPopupSn(Integer.parseInt(request.getParameter("popupSn")));

		brdPopupNoticeVO = brdPopupNoticeService.view(brdPopupNoticeVO);
		model.addAttribute("brdPopupNoticeVO", brdPopupNoticeVO);

		return "home/board/popup/popup_notice_pop";
	}

}
