package egovframework.edutrack.web.manage.org;

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
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.service.SysCodeMemService;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.org.info.service.OrgOrgInfoService;
import egovframework.edutrack.modules.org.info.service.OrgOrgInfoVO;
import egovframework.edutrack.modules.system.code.service.SysCodeVO;
import egovframework.edutrack.modules.system.tpl.service.SysTplService;
import egovframework.edutrack.modules.system.tpl.service.SysTplVO;

@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/mng/org/meta")
public class OrgMetaManageController extends GenericController {
	
    /** service */
	@Autowired @Qualifier("orgOrgInfoService")
	private OrgOrgInfoService 		orgOrgInfoService;

	@Autowired @Qualifier("sysCodeMemService")
	private SysCodeMemService		sysCodeMemService;

	@Autowired @Qualifier("sysTplService")
	private SysTplService 			sysTplService;
	
	/**
     * @Method Name : editInfoForm
     * @Method 설명 : 메타버스 관리 화면을 보여 준다. 
	 * @param OrgOrgInfoVO 
	 * @param commandMap
	 * @param model
	 * @return  "/mng/org/orginfo/edit_info.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/main")
	public String main(OrgOrgInfoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		//-- 기관 정보 검색
		vo = orgOrgInfoService.view(vo);
		request.setAttribute("vo", vo);

		request.setAttribute("xrcloud_api_key", Constants.XRCLOUD_API_KEY);	
		request.setAttribute("xrcloud_project_id", Constants.XRCLOUD_PROJECT_ID);	
		request.setAttribute("product_host_url", Constants.PRODUCT_HOST_URL);	
		
		// 이곳에 
		return "mng/org/meta/edit_meta_main";
	}
	
	/**
     * @Method Name : editMeta
     * @Method 설명 : 기관의 메타 정보를 수정 한다. 
	 * @param OrgOrgInfoVO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/editMeta")
	public String editMeta(OrgOrgInfoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		ProcessResultVO<OrgOrgInfoVO> result = new ProcessResultVO<OrgOrgInfoVO>();
		try {
			orgOrgInfoService.editMeta(vo);
			result.setResult(1);
			result.setMessage(getMessage(request, "org.message.orginfo.edit.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "org.message.orginfo.edit.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}
	
}
