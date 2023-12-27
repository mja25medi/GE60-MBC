package egovframework.edutrack.web.system;

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
import egovframework.edutrack.modules.system.tpl.service.SysTplService;
import egovframework.edutrack.modules.system.tpl.service.SysTplVO;

@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/adm/system/tpl")
public class SysTplAdminController extends GenericController {

    /** service */
	@Autowired @Qualifier("sysTplService")
	private SysTplService 	sysTplService;
	
	/**
     * @Method Name : main
     * @Method 설명 : 템플릿 관리 메인 화면으로 이동한다. 
	 * @param SysTplVO 
	 * @param commandMap
	 * @param model
	 * @return  "/adm/system/tpl/main.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/main")
	public String main(SysTplVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		request.setAttribute("paging", "Y");
		
		return "system/tpl/main";
	}
	
	/**
     * @Method Name : list
     * @Method 설명 : 템플릿 관리 권한 목록을 보여 준다. 
	 * @param SysTplVO 
	 * @param commandMap
	 * @param model
	 * @return  "/adm/system/tpl/list_tpl.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/list")
	public String list(SysTplVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultListVO<SysTplVO> resultList = sysTplService.listPageing(vo, vo.getPageIndex());
		request.setAttribute("tplList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());
		return "system/tpl/list_tpl";
	}
	
	/**
     * @Method Name : addForm
     * @Method 설명 : 템플릿 관리 등록 화면을 보여 준다.
	 * @param SysTplVO 
	 * @param commandMap
	 * @param model
	 * @return  "/adm/system/tpl/write_tpl.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/addFormPop")
	public String addFormPop(SysTplVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);

		vo.setUseYn("Y");
		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "A");
		request.setAttribute("fileupload", "Y");
		return "system/tpl/write_tpl";
	}
	
	/**
     * @Method Name : add
     * @Method 설명 : 템플릿을 등록 한다. 
	 * @param SysTplVO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/add")
	public String add(SysTplVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		ProcessResultVO<SysTplVO> result = new ProcessResultVO<SysTplVO>();
		try {
			sysTplService.add(vo);
			result.setResult(1);
			result.setMessage(getMessage(request, "system.message.template.add.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "system.message.template.add.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}
	
	/**
     * @Method Name : editForm
     * @Method 설명 : 템플릿 수정 화면을 보여 준다. 
	 * @param SysTplVO 
	 * @param commandMap
	 * @param model
	 * @return  "/adm/system/tpl/write_tpl.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/editFormPop")
	public String editFormPop(SysTplVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);

		vo = sysTplService.view(vo);
		
		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "E");
		request.setAttribute("fileupload", "Y");
		return "system/tpl/write_tpl";
	}
	
	/**
     * @Method Name : edit
     * @Method 설명 : 템플릿을 수정 한다. 
	 * @param SysTplVO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/edit")
	public String edit(SysTplVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		ProcessResultVO<SysTplVO> result = new ProcessResultVO<SysTplVO>();
		try {
			sysTplService.edit(vo);
			result.setResult(1);
			result.setMessage(getMessage(request, "system.message.template.edit.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "system.message.template.edit.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}
	
	/**
     * @Method Name : remove
     * @Method 설명 : 템플릿을 삭제 한다. 
	 * @param SysTplVO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/remove")
	public String remove(SysTplVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		ProcessResultVO<SysTplVO> result = new ProcessResultVO<SysTplVO>();
		try {
			sysTplService.remove(vo);
			result.setResult(1);
			result.setMessage(getMessage(request, "system.message.template.delete.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "system.message.template.delete.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}	
}
