package egovframework.edutrack.web.org;

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
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.org.info.service.OrgOrgInfoService;
import egovframework.edutrack.modules.org.info.service.OrgOrgInfoVO;
import egovframework.edutrack.modules.org.page.service.OrgPageService;
import egovframework.edutrack.modules.org.page.service.OrgPageVO;

@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/adm/org/page")
public class OrgPageAdminController extends GenericController {

    /** service */
	@Autowired @Qualifier("orgPageService")
	private OrgPageService 		orgPageService;

	@Autowired @Qualifier("orgOrgInfoService")
	private OrgOrgInfoService	orgOrgInfoService;
	
	/**
     * @Method Name : main
     * @Method 설명 : 기관 페이지 관리 메인 화면으로 이동한다. 
	 * @param OrgPageVO 
	 * @param commandMap
	 * @param model
	 * @return  "/adm/org/page/main.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/main")
	public String main(OrgPageVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		
		List<OrgOrgInfoVO> orgInfoList = orgOrgInfoService.list(new OrgOrgInfoVO()).getReturnList();
		request.setAttribute("orgInfoList", orgInfoList);
		request.setAttribute("paging", "Y");
		
		return "org/page/main";
	}
	
	/**
     * @Method Name : SysCodeCtgrList
     * @Method 설명 : 기관 페이지 관리 분류 목록을 보여 준다. 
	 * @param OrgPageVO 
	 * @param commandMap
	 * @param model
	 * @return  "/adm/org/page/list_page.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/list")
	public String list(OrgPageVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		
		ProcessResultListVO<OrgPageVO> resultList = orgPageService.listPageing(vo, vo.getPageIndex(), vo.getListScale());
		request.setAttribute("pageList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());
		return "org/page/list_page";
	}
	
	/**
     * @Method Name : addFrom
     * @Method 설명 : 기관 페이지 관리 분류 등록 화면을 보여 준다. 
	 * @param OrgPageVO 
	 * @param commandMap
	 * @param model
	 * @return  "/adm/org/page/write_page.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/addFormPop")
	public String addFormPop(OrgPageVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		
		vo.setUseYn("Y");
		
		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "A");
		request.setAttribute("summernote", "Y");
		return "org/page/write_page";
	}
	
	/**
     * @Method Name : add
     * @Method 설명 : 기관 페이지 관리 분류를 등록 한다. 
	 * @param OrgPageVO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/add")
	public String add(OrgPageVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		ProcessResultVO<OrgPageVO> result = new ProcessResultVO<OrgPageVO>();
		try {
			orgPageService.add(vo);
			result.setResult(1);
			result.setMessage(getMessage(request, "system.message.page.add.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "system.message.page.add.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}
	
	/**
     * @Method Name : editFormPop
     * @Method 설명 : 기관 페이지 수정 화면을 보여준다. 
	 * @param OrgPageVO 
	 * @param commandMap
	 * @param model
	 * @return  "/adm/org/page/write_page.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/editFormPop")
	public String editFormPop(OrgPageVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		
		vo = orgPageService.view(vo);
		
		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "E");
		request.setAttribute("summernote", "Y");
		return "org/page/write_page";
	}
	
	/**
     * @Method Name : SysCodeCtgrAdd
     * @Method 설명 : 기관 페이지 관리 분류를 수정 한다. 
	 * @param OrgPageVO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/edit")
	public String edit(OrgPageVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		ProcessResultVO<OrgPageVO> result = new ProcessResultVO<OrgPageVO>();
		try {
			orgPageService.edit(vo);
			result.setResult(1);
			result.setMessage(getMessage(request, "system.message.page.edit.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "system.message.page.edit.failed"));
		}		
		return JsonUtil.responseJson(response, result);
	}
	
	/**
     * @Method Name : remove
     * @Method 설명 : 기관 페이지 관리 분류를 삭제한다. 
	 * @param OrgPageVO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/remove")
	public String remove(OrgPageVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		ProcessResultVO<OrgPageVO> result = new ProcessResultVO<OrgPageVO>();
		try {
			orgPageService.remove(vo);
			result.setResult(1);
			result.setMessage(getMessage(request, "system.message.page.delete.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "system.message.page.delete.failed"));
		}		
		return JsonUtil.responseJson(response, result);
	}
}
