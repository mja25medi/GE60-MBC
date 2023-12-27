package egovframework.edutrack.web.manage.org;

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
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.org.connip.service.OrgConnIpService;
import egovframework.edutrack.modules.org.connip.service.OrgConnIpVO;

@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/mng/org/connIp")
public class OrgConnIpManageController extends GenericController {
	
	/** service */
	@Autowired @Qualifier("orgConnIpService")
	private OrgConnIpService 		orgConnIpService;

	
	/**
     * @Method Name : main
     * @Method 설명 : 접속 가능 IP 관리 메인
	 * @param VO
	 * @param commandMap
	 * @param model
	 * @return  
	 * @throws Exception
	 */
	@RequestMapping(value="/main")
	public String main(OrgConnIpVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		request.setAttribute("vo", vo);
		request.setAttribute("paging", "Y");
		return "mng/org/ip/main";
	}
	
	/**
	 *  접속 가능 IP 목록 조회(JSON)
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/list")
	public String list(OrgConnIpVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		ProcessResultListVO<OrgConnIpVO> resultList = orgConnIpService.listPageing(vo, vo.getPageIndex());
		
		request.setAttribute("orgConnIpList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());
		request.setAttribute("vo", vo);
		return "mng/org/ip/list_ip";
	}

	/**
	 * 접속 가능 IP 등록 폼
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/addPop")
	public String addForm(OrgConnIpVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		
		return "mng/org/ip/write_ip_pop";
	}


	/**
	 *  접속 가능 IP 등록
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/add")
	public String add(OrgConnIpVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		if(vo.getConnIp().endsWith("*")) vo.setDivCd("A");
		else vo.setDivCd("B");

		ProcessResultVO<?> result = new ProcessResultVO<OrgConnIpVO>();
		try {
			orgConnIpService.add(vo);
			result.setResult(1);
			result.setMessage(getMessage(request, "org.message.conn.ip.add.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "org.message.conn.ip.add.failed"));
		}
		
		return JsonUtil.responseJson(response, result);
	}

	/**
	 * 접속 가능 IP 삭제
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/remove")
	public String remove(OrgConnIpVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		ProcessResultVO<?> result = new ProcessResultVO<OrgConnIpVO>(); 
		try {
			orgConnIpService.remove(vo);
			result.setResult(1);
			result.setMessage(getMessage(request, "org.message.conn.ip.delete.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "org.message.conn.ip.delete.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}
}
