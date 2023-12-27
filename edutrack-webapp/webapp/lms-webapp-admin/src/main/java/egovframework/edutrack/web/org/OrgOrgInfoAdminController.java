package egovframework.edutrack.web.org;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.service.SysCodeMemService;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.org.info.service.OrgOrgInfoService;
import egovframework.edutrack.modules.org.info.service.OrgOrgInfoVO;
import egovframework.edutrack.modules.system.code.service.SysCodeVO;
import egovframework.edutrack.modules.system.tpl.service.SysTplService;
import egovframework.edutrack.modules.system.tpl.service.SysTplVO;

@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/adm/org/orginfo")
public class OrgOrgInfoAdminController extends GenericController {

    /** service */
	@Autowired @Qualifier("orgOrgInfoService")
	private OrgOrgInfoService 		orgOrgInfoService;

	@Autowired @Qualifier("sysCodeMemService")
	private SysCodeMemService		sysCodeMemService;

	@Autowired @Qualifier("sysTplService")
	private SysTplService 			sysTplService;
	
	/**
     * @Method Name : main
     * @Method 설명 : 기관 관리 메인 화면으로 이동한다. 
	 * @param OrgOrgInfoVO 
	 * @param commandMap
	 * @param model
	 * @return  "/adm/org/info/main.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/main")
	public String main(OrgOrgInfoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		request.setAttribute("paging", "Y");
		request.setAttribute("fileupload", "Y");

		return "org/orginfo/main";
	}
	
	/**
     * @Method Name : list
     * @Method 설명 : 기관 목록을 보여 준다. 
	 * @param OrgOrgInfoVO 
	 * @param commandMap
	 * @param model
	 * @return  "/adm/org/info/list_info.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/list")
	public String list(OrgOrgInfoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		
		ProcessResultListVO<OrgOrgInfoVO> resultList = orgOrgInfoService.listPageing(vo, vo.getPageIndex(), vo.getListScale());
		request.setAttribute("orgList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());
		request.setAttribute("vo", vo);
		return "org/orginfo/list_info";
	}
	
	/**
     * @Method Name : addForm
     * @Method 설명 : 기관 등록 화면을 보여 준다. 
	 * @param OrgOrgInfoVO 
	 * @param commandMap
	 * @param model
	 * @return  "/adm/org/orginfo/write_info.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/addFormPop")
	public String addFormPop(OrgOrgInfoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		
		vo.setUseYn("Y");
		vo.setKollusUseYn("N"); //-- 콜루스 사용은 기본 "N"
		vo.setHrdApiUseYn("Y"); //-- 산업인력공단 API 사용은 기본 "Y"

		//-- Language Code
		List<SysCodeVO> langList = sysCodeMemService.getSysCodeList("LANG_CD");
		request.setAttribute("langList", langList);

		//-- Product Code
		List<SysCodeVO> productList = sysCodeMemService.getSysCodeList("PRODUCT_CD");
		request.setAttribute("productList", productList);
		
		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "A");
		return "org/orginfo/write_info";
	}
	
	/**
     * @Method Name : add
     * @Method 설명 : 기관을 등록 한다. 
	 * @param OrgOrgInfoVO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/add")
	public String add(OrgOrgInfoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		vo.setStartDttm(StringUtils.replace(vo.getStartDttm(),".", ""));
		vo.setEndDttm(StringUtils.replace(vo.getEndDttm(),".", ""));
		ProcessResultVO<OrgOrgInfoVO> result = new ProcessResultVO<OrgOrgInfoVO>();
		try {
			orgOrgInfoService.add(vo);
			result.setResult(1);
			result.setMessage(getMessage(request, "org.message.orginfo.add.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "org.message.orginfo.add.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}
	
	/**
     * @Method Name : editForm
     * @Method 설명 : 기관 수정 화면을 보여 준다. 
	 * @param OrgOrgInfoVO 
	 * @param commandMap
	 * @param model
	 * @return  "/adm/org/orginfo/write_info.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/editFormPop")
	public String editFormPop(OrgOrgInfoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		
		//-- 기관 정보 검색
		vo = orgOrgInfoService.view(vo);
		//-- Language Code
		List<SysCodeVO> langList = sysCodeMemService.getSysCodeList("LANG_CD");
		request.setAttribute("langList", langList);
		//-- Product Code
		List<SysCodeVO> productList = sysCodeMemService.getSysCodeList("PRODUCT_CD");
		request.setAttribute("productList", productList);
		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "E");
		return "org/orginfo/write_info";
	}
	
	/**
     * @Method Name : edit
     * @Method 설명 : 기관을 수정 한다. 
	 * @param OrgOrgInfoVO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/edit")
	public String edit(OrgOrgInfoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		vo.setStartDttm(StringUtils.replace(vo.getStartDttm(),".", ""));
		vo.setEndDttm(StringUtils.replace(vo.getEndDttm(),".", ""));		
		ProcessResultVO<OrgOrgInfoVO> result = new ProcessResultVO<OrgOrgInfoVO>();
		try {
			orgOrgInfoService.edit(vo);
			result.setResult(1);
			result.setMessage(getMessage(request, "org.message.orginfo.edit.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "org.message.orginfo.edit.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}
	
	/**
     * @Method Name : edit
     * @Method 설명 : 기관을 삭제 한다. 
	 * @param OrgOrgInfoVO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/remove")
	public String remove(OrgOrgInfoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		ProcessResultVO<OrgOrgInfoVO> result = new ProcessResultVO<OrgOrgInfoVO>();
		try {
			orgOrgInfoService.remove(vo);
			result.setResult(1);
			result.setMessage(getMessage(request, "org.message.orginfo.delete.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "org.message.orginfo.delete.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}
	
	/**
     * @Method Name : editInfoForm
     * @Method 설명 : 기관 수정 화면을 보여 준다. 
	 * @param OrgOrgInfoVO 
	 * @param commandMap
	 * @param model
	 * @return  "/adm/org/orginfo/edit_info.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/editInfoFormMain")
	public String editInfoFormMain(OrgOrgInfoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		
		//-- 기관 정보 검색
		vo = orgOrgInfoService.view(vo);
		request.setAttribute("vo", vo);
		
		//-- Language Code
		List<SysCodeVO> langList = sysCodeMemService.getSysCodeList("LANG_CD");
		request.setAttribute("langList", langList);

		request.setAttribute("gubun", "E");
		request.setAttribute("fileupload", "Y");
		return "org/orginfo/edit_info";
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
	 * @return  "/adm/org/orginfo/manage_template.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/editTemplateFormMain")
	public String editTemplateFormMain(OrgOrgInfoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		
		//-- 기관 정보 검색
		vo = orgOrgInfoService.view(vo);
		request.setAttribute("vo", vo);
		
		//-- 템플릿 목록 검색
		SysTplVO stvo = new SysTplVO();
		List<SysTplVO> tplList = sysTplService.list(stvo).getReturnList();
		request.setAttribute("tplList", tplList);
		
		request.setAttribute("gubun", "E");
		request.setAttribute("paging", "Y");
		return "org/orginfo/edit_template";
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
	 * @return  "/adm/org/orginfo/manage_template.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/editDesignFormMain")
	public String editDesignFormMain(OrgOrgInfoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		
		//-- 기관 정보 검색
		vo = orgOrgInfoService.view(vo);
		request.setAttribute("vo", vo);
		
		
		request.setAttribute("gubun", "E");
		request.setAttribute("fileupload", "Y");
		request.setAttribute("paging", "Y");
		return "org/orginfo/edit_design";
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
