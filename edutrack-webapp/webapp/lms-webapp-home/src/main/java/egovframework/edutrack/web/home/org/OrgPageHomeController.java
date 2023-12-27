package egovframework.edutrack.web.home.org;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.org.info.service.OrgOrgInfoService;
import egovframework.edutrack.modules.org.page.service.OrgPageService;
import egovframework.edutrack.modules.org.page.service.OrgPageVO;

@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/home/org/page")
public class OrgPageHomeController extends GenericController {
	
    /** service */
	@Autowired @Qualifier("orgPageService")
	private OrgPageService 		orgPageService;

	@Autowired @Qualifier("orgOrgInfoService")
	private OrgOrgInfoService	orgOrgInfoService;
	
	/**
     * @Method Name : readPage
     * @Method 설명 : 페이지 읽기
	 * @param OrgPageVO 
	 * @param commandMap
	 * @param model
	 * @return  "org/page/main.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/viewPageMain")
	public String viewPage(OrgPageVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		String tplCd = UserBroker.getUserColorTpl(request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		vo = orgPageService.view(vo);
		request.setAttribute("vo", vo);
		return "home/org/page/view_page";
	}
	
	/**
	 * @Method Name : readPage
	 * @Method 설명 : 페이지 읽기(팝업)
	 * @param OrgPageVO 
	 * @param commandMap
	 * @param model
	 * @return  "org/page/main.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/viewPagePopup")
	public String viewPagePopup(OrgPageVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		String tplCd = UserBroker.getUserColorTpl(request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		vo = orgPageService.view(vo);
		request.setAttribute("vo", vo);
		return "home/org/page/view_page_pop";
	}
	
	/**
	 * 메인페이지 모달용
	 * @param vo
	 * @param commandMap
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/viewPagePop")
	public String viewPagePopup2(OrgPageVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		String tplCd = UserBroker.getUserColorTpl(request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		vo = orgPageService.view(vo);
		request.setAttribute("vo", vo);
		return "home/org/page/view_page_pop";
	}

}
