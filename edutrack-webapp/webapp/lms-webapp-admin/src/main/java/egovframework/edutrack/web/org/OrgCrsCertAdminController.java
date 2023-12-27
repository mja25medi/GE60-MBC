package egovframework.edutrack.web.org;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.org.crscert.service.OrgCrsCertService;
import egovframework.edutrack.modules.org.crscert.service.OrgCrsCertVO;

@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/adm/org/crscert")
public class OrgCrsCertAdminController extends GenericController {
	
	@Autowired @Qualifier("orgCrsCertService")
	private OrgCrsCertService 		orgCrsCertService;
	
	/**
     * @Method Name : main
     * @Method 설명 : 기관 과정 수료증 관리 메인 화면으로 이동한다. 
	 * @param OrgCrsCertVO 
	 * @param commandMap
	 * @param model
	 * @return  "/adm/org/crscert/editCertForm.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/editCertFormMain")
	public String main(OrgCrsCertVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		
		try {
			vo = orgCrsCertService.view(vo);
			request.setAttribute("gubun", "E");
		} catch (Exception e) {
			vo.setPrnDirec("VER"); //-- 세로를 기본으로 함.
			request.setAttribute("gubun", "A");
		}
		request.setAttribute("vo", vo);
		request.setAttribute("fileupload", "Y");
		return "org/crscert/edit_cert";
	}
	
	/**
     * @Method Name : add
     * @Method 설명 : 기관의 수료증을 등록 한다. 
	 * @param OrgCrsCertVO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/add")
	public String add(OrgCrsCertVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		ProcessResultVO<OrgCrsCertVO> result = new ProcessResultVO<OrgCrsCertVO>();
		try {
			orgCrsCertService.add(vo);
			result.setResult(1);
			result.setMessage(getMessage(request, "org.message.crscert.add.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "org.message.crscert.add.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}
	
	/**
     * @Method Name : edit
     * @Method 설명 : 기관의 수료증을 수정 한다. 
	 * @param OrgCrsCertVO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/edit")
	public String edit(OrgCrsCertVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		ProcessResultVO<OrgCrsCertVO> result = new ProcessResultVO<OrgCrsCertVO>();
		try {
			orgCrsCertService.edit(vo);
			result.setResult(1);
			result.setMessage(getMessage(request, "org.message.crscert.add.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "org.message.crscert.add.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}
	
	/**
     * @Method Name : remove
     * @Method 설명 : 기관의 수료증을 삭제 한다. 
	 * @param OrgCrsCertVO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/remove")
	public String remove(OrgCrsCertVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		ProcessResultVO<OrgCrsCertVO> result = new ProcessResultVO<OrgCrsCertVO>();
		try {
			orgCrsCertService.edit(vo);
			result.setResult(1);
			result.setMessage(getMessage(request, "org.message.crscert.delete.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "org.message.crscert.delete.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}
	
	/**
     * @Method Name : preview
     * @Method 설명 : 기관 과정 수료증 관리 메인 화면으로 이동한다. 
	 * @param OrgCrsCertVO 
	 * @param commandMap
	 * @param model
	 * @return  "/adm/org/crscert/editCertForm.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/preview")
	public String preview(OrgCrsCertVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		
		vo = orgCrsCertService.view(vo);

		request.setAttribute("vo", vo);
		request.setAttribute("fileupload", "Y");
		return "org/crscert/preview_cert";
	}	
}
