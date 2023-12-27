package egovframework.edutrack.web.manage.log;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.log.orgadminconn.service.LogOrgAdminConnLogService;
import egovframework.edutrack.modules.log.orgadminconn.service.LogOrgAdminConnLogVO;

@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/mng/log/login")
public class LogLoginManageController extends GenericController {
	
	@Autowired @Qualifier("logOrgAdminConnLogService")
	private LogOrgAdminConnLogService 	logOrgAdminConnLogService;

	
	/**
     * @Method Name : main
     * @Method 설명 : 관리자접속 로그 메인
	 * @param VO
	 * @param commandMap
	 * @param model
	 * @return  
	 * @throws Exception
	 */
	@RequestMapping(value="/main")
	public String main(LogOrgAdminConnLogVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		request.setAttribute("vo", vo);
		request.setAttribute("paging", "Y");
		return "mng/log/main_login";
	}
	
	/**
	 *  관리자접속 로그 목록 조회(JSON)
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/list")
	public String list(LogOrgAdminConnLogVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		ProcessResultListVO<LogOrgAdminConnLogVO> resultList = logOrgAdminConnLogService.listPageing(vo, vo.getPageIndex(), vo.getListScale());
		
		request.setAttribute("orgMgrConnList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());
		request.setAttribute("vo", vo);
		return "mng/log/list_login";
	}

	
}
