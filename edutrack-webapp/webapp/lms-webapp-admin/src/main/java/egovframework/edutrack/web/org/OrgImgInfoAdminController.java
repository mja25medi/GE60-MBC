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

import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.org.image.service.OrgImgInfoService;
import egovframework.edutrack.modules.org.image.service.OrgImgInfoVO;

@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/adm/org/imginfo")
public class OrgImgInfoAdminController extends GenericController{

	@Autowired @Qualifier("orgImgInfoService")
	private OrgImgInfoService 		orgImgInfoService;
	
	/**
     * @Method Name : list
     * @Method 설명 : 교유기관의 이미지 목록 화면을 보여준다. 
	 * @param OrgImgInfoVO 
	 * @param commandMap
	 * @param model
	 * @return  "/org/imginfo/list.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/list")
	public String main(OrgImgInfoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);	

		List<OrgImgInfoVO> orgImgInfoList = orgImgInfoService.list(vo).getReturnList();

		request.setAttribute("orgImgInfoList", orgImgInfoList);
		request.setAttribute("vo", vo);
		return "org/imginfo/list_img";
	}
	
	/**
     * @Method Name : addForm
     * @Method 설명 : 교육기관의 이미지 등록 화면을 보여준다. 
	 * @param OrgImgInfoVO 
	 * @param commandMap
	 * @param model
	 * @return  "/org/imginfo/write_img.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/addForm")
	public String addForm(OrgImgInfoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);	

		String colorTplCd = StringUtil.nvl(request.getParameter("colorTplCd"),"001");
		request.setAttribute("colorTplCd", colorTplCd);
		
		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "A");
		//request.setAttribute("colorpicker", "Y");
		request.setAttribute("fileupload", "Y");
		return "org/imginfo/write_img";
	}
	
	/**
     * @Method Name : add
     * @Method 설명 : 교육기관의 이미지를 등록 한다.
	 * @param OrgImgInfoVO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/add")
	public String add(OrgImgInfoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		ProcessResultVO<OrgImgInfoVO> result = new ProcessResultVO<OrgImgInfoVO>();
		try {
			orgImgInfoService.add(vo);
			result.setResult(1);
			result.setMessage(getMessage(request, "org.message.imginfo.add.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "org.message.imginfo.add.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}
	
	/**
     * @Method Name : editForm
     * @Method 설명 : 교육기관의 이미지 수정 화면을 보여준다. 
	 * @param OrgImgInfoVO 
	 * @param commandMap
	 * @param model
	 * @return  "/org/imginfo/write_img.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/editForm")
	public String editForm(OrgImgInfoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);	

		vo = orgImgInfoService.view(vo);
		
		String colorTplCd = StringUtil.nvl(request.getParameter("colorTplCd"),"001");
		request.setAttribute("colorTplCd", colorTplCd);
		
		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "E");
		request.setAttribute("fileupload", "Y");
		return "org/imginfo/write_img";
	}
	
	/**
     * @Method Name : edit
     * @Method 설명 : 교육기관의 이미지를 등록 한다.
	 * @param OrgImgInfoVO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/edit")
	public String edit(OrgImgInfoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		ProcessResultVO<OrgImgInfoVO> result = new ProcessResultVO<OrgImgInfoVO>();
		try {
			orgImgInfoService.edit(vo);
			result.setResult(1);
			result.setMessage(getMessage(request, "org.message.imginfo.edit.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "org.message.imginfo.edit.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}
	
	/**
     * @Method Name : remove
     * @Method 설명 : 교육기관의 이미지를 삭제 한다.
	 * @param OrgImgInfoVO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/remove")
	public String remove(OrgImgInfoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		ProcessResultVO<OrgImgInfoVO> result = new ProcessResultVO<OrgImgInfoVO>();
		try {
			orgImgInfoService.remove(vo);
			result.setResult(1);
			result.setMessage(getMessage(request, "org.message.imginfo.delete.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "org.message.imginfo.delete.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}
	
	/**
     * @Method Name : sort
     * @Method 설명 : 교육기관의 이미지의 순서를 변경한다.
	 * @param OrgImgInfoVO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/sort")
	public String sort(OrgImgInfoVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		ProcessResultVO<OrgImgInfoVO> result = new ProcessResultVO<OrgImgInfoVO>();
		try {
			orgImgInfoService.sort(vo);
			result.setResult(1);
			result.setMessage(getMessage(request, "org.message.imginfo.sort.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "org.message.imginfo.sort.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}	
}
