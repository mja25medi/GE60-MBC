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

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.service.SysCodeMemService;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.org.domain.service.OrgDomainService;
import egovframework.edutrack.modules.org.domain.service.OrgDomainVO;
import egovframework.edutrack.modules.org.info.service.OrgOrgInfoVO;
import egovframework.edutrack.modules.system.code.service.SysCodeVO;

@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/adm/org/domain")
public class OrgDomainAdminController extends GenericController {

	@Autowired @Qualifier("orgDomainService")
	private OrgDomainService 		orgDomainService;
	
	@Autowired @Qualifier("sysCodeMemService")
	private SysCodeMemService		sysCodeMemService;
	
	/**
     * @Method Name : main
     * @Method 설명 : 기관 도메인 관리 메인 화면으로 이동한다. 
	 * @param OrgDomainVO 
	 * @param commandMap
	 * @param model
	 * @return  "/adm/org/domain/main.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/mainPop")
	public String mainPop(OrgDomainVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		
		//-- DomainType Code
		List<SysCodeVO> domainTypeList = sysCodeMemService.getSysCodeList("DOMAIN_TYPE_CD");
		request.setAttribute("domainTypeList", domainTypeList);
		
		//-- SiteServiceType Code
		List<SysCodeVO> siteServiceTypeList = sysCodeMemService.getSysCodeList("SITE_SERVICE_TYPE_CD");
		request.setAttribute("siteServiceTypeList", siteServiceTypeList);
		
		request.setAttribute("vo", vo);
		return "org/domain/main";
	}
	
	/**
     * @Method Name : list
     * @Method 설명 : 기관 도메인 목록을 보여 준다. 
	 * @param OrgDomainVO 
	 * @param commandMap
	 * @param model
	 * @return  "/adm/org/domain/list_domain.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/list")
	public String list(OrgDomainVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		
		ProcessResultListVO<OrgDomainVO> resultList = orgDomainService.list(vo);
		request.setAttribute("domainList", resultList.getReturnList());
		request.setAttribute("vo", vo);
		return "org/domain/list_domain";
	}
	
	/**
     * @Method Name : add
     * @Method 설명 : 기관을 등록 한다. 
	 * @param OrgDomainVO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/add")
	public String add(OrgDomainVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		vo.setDfltYn("N"); //-- 기본 도메인은 교육기관 등록시 생성됨
		vo.setRprstYn("N"); //-- 대표 도메인은 목록에서만 변경
		
		ProcessResultVO<OrgDomainVO> result = new ProcessResultVO<OrgDomainVO>();
		
		boolean isDupYn = false;

		try {
			OrgDomainVO checkVO = orgDomainService.view(vo);
			if(checkVO == null) {//없으면
				isDupYn = false;
			}else {//있으면 중복
				isDupYn = true;
			}
		} catch (Exception e) {
			isDupYn = false;
		}

		if(isDupYn){
			result.setMessage(getMessage(request, "org.message.orginfo.url.alreayuse"));
		} else {
			try {
				orgDomainService.add(vo);
				result.setResult(1);
				result.setMessage(getMessage(request, "org.message.domain.add.success"));
			} catch (Exception e) {
				result.setResult(-1);
				result.setMessage(getMessage(request, "org.message.domain.add.failed"));
			}
		}		
		return JsonUtil.responseJson(response, result);
	}
	
	/**
     * @Method Name : remove
     * @Method 설명 : 기관 도메인을 삭제 한다. 
	 * @param OrgDomainVO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/remove")
	public String remove(OrgDomainVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		ProcessResultVO<OrgDomainVO> result = new ProcessResultVO<OrgDomainVO>();
		try {
			orgDomainService.remove(vo);
			result.setResult(1);
			result.setMessage(getMessage(request, "org.message.domain.delete.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "org.message.domain.delete.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}
	
	/**
     * @Method Name : change
     * @Method 설명 : 대표 도메인을 변경 한다. 
	 * @param OrgDomainVO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/change")
	public String change(OrgDomainVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		ProcessResultVO<OrgDomainVO> result = new ProcessResultVO<OrgDomainVO>();
		try {
			orgDomainService.change(vo);
			result.setResult(1);
			result.setMessage(getMessage(request, "org.message.domain.change.rprst.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "org.message.domain.change.rprst.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}	
}
