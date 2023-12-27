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
@RequestMapping(value="/mng/org/orgInfo")
public class OrgOrgInfoManageController extends GenericController {
	
    /** service */
	@Autowired @Qualifier("orgOrgInfoService")
	private OrgOrgInfoService 		orgOrgInfoService;

	@Autowired @Qualifier("sysCodeMemService")
	private SysCodeMemService		sysCodeMemService;

	@Autowired @Qualifier("sysTplService")
	private SysTplService 			sysTplService;
	
	/**
     * @Method Name : editInfoForm
     * @Method 설명 : 기관 수정 화면을 보여 준다. 
	 * @param OrgOrgInfoVO 
	 * @param commandMap
	 * @param model
	 * @return  "/mng/org/orginfo/edit_info.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/editInfoMain")
	public String editInfoMain(OrgOrgInfoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		//-- 기관 정보 검색
		vo = orgOrgInfoService.view(vo);
		request.setAttribute("vo", vo);
		
		//-- Language Code
		List<SysCodeVO> langList = sysCodeMemService.getSysCodeList("LANG_CD");
		request.setAttribute("langList", langList);

		request.setAttribute("gubun", "E");
		request.setAttribute("fileupload", "Y");
		return "mng/org/orginfo/edit_info_main";
	}
	
	/**
     * @Method Name : editInfo
     * @Method 설명 : 기관의 기본정보를 수정 한다. 
	 * @param OrgOrgInfoVO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/editInfo")
	public String editInfo(OrgOrgInfoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		ProcessResultVO<OrgOrgInfoVO> result = new ProcessResultVO<OrgOrgInfoVO>();
		try {
			orgOrgInfoService.editInfo(vo);
			result.setResult(1);
			result.setMessage(getMessage(request, "org.message.orginfo.edit.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "org.message.orginfo.edit.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}
	
	/**
     * @Method Name : editTemplateForm
     * @Method 설명 : 기관 템플릿 수정 화면을 보여 준다. 
	 * @param OrgOrgInfoVO 
	 * @param commandMap
	 * @param model
	 * @return  "/mng/org/orginfo/manage_template.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/editTemplateMain")
	public String editTemplateForm(OrgOrgInfoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		//-- 기관 정보 검색
		vo = orgOrgInfoService.view(vo);
		request.setAttribute("vo", vo);
		
		//-- 템플릿 목록 검색
		SysTplVO stvo = new SysTplVO();
		List<SysTplVO> tplList = sysTplService.list(stvo).getReturnList();
		request.setAttribute("tplList", tplList);
		
		request.setAttribute("gubun", "E");
		return "mng/org/orginfo/edit_template_main";
	}
	
	/**
     * @Method Name : editTemplate
     * @Method 설명 : 기관의 템플릿 정보를 수정 한다. 
	 * @param OrgOrgInfoVO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/editTemplate")
	public String editTemplate(OrgOrgInfoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		ProcessResultVO<OrgOrgInfoVO> result = new ProcessResultVO<OrgOrgInfoVO>();
		try {
			orgOrgInfoService.editTemplate(vo);
			result.setResult(1);
			result.setMessage(getMessage(request, "org.message.orginfo.edit.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "org.message.orginfo.edit.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}
	
	/**
     * @Method Name : editDesignForm
     * @Method 설명 : 기관 디자인 수정 화면을 보여 준다. 
	 * @param OrgOrgInfoVO 
	 * @param commandMap
	 * @param model
	 * @return  "/mng/org/orginfo/manage_template.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/editDesignMain")
	public String editDesignForm(OrgOrgInfoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		//-- 기관 정보 검색
		vo = orgOrgInfoService.view(vo);
		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "E");
		request.setAttribute("paging", "Y");
		request.setAttribute("fileupload", "Y");
		return "mng/org/orginfo/edit_design_main";
	}
	
	/**
     * @Method Name : editDesign
     * @Method 설명 : 기관의 템플릿 정보를 수정 한다. 
	 * @param OrgOrgInfoVO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/editDesign")
	public String editDesign(OrgOrgInfoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		ProcessResultVO<OrgOrgInfoVO> result = new ProcessResultVO<OrgOrgInfoVO>();
		try {
			orgOrgInfoService.editDesign(vo);
			result.setResult(1);
			result.setMessage(getMessage(request, "org.message.orginfo.edit.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "org.message.orginfo.edit.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}

}
