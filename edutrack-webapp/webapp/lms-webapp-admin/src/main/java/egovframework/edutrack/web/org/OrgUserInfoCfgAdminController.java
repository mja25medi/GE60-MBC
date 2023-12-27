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
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.org.config.service.OrgUserInfoCfgService;
import egovframework.edutrack.modules.org.config.service.OrgUserInfoCfgVO;
import egovframework.edutrack.modules.org.info.service.OrgOrgInfoService;
import egovframework.edutrack.modules.org.info.service.OrgOrgInfoVO;

@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/adm/org/config")
public class OrgUserInfoCfgAdminController extends GenericController {

	@Autowired @Qualifier("orgOrgInfoService")
	private OrgOrgInfoService 		orgOrgInfoService;
	
	@Autowired @Qualifier("orgUserInfoCfgService")
	private OrgUserInfoCfgService 	orgUserInfoCfgService;
	
	/**
     * @Method Name : editCfgForm
     * @Method 설명 : 기관 사용자 정보 설정 수정 화면을 보여준다. 
	 * @param OrgUserInfoCfgVO 
	 * @param commandMap
	 * @param model
	 * @return  "/adm/org/config/edit_config.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/editCfgFormMain")
	public String editCfgFormMain(OrgUserInfoCfgVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		
		//-- 기관 정보 검색
		OrgOrgInfoVO ooivo = new OrgOrgInfoVO();
		ooivo.setOrgCd(vo.getOrgCd());
		ooivo = orgOrgInfoService.view(ooivo);
		request.setAttribute("ooivo", ooivo);
		
		OrgUserInfoCfgVO ouicvo = new OrgUserInfoCfgVO();
		ouicvo.setOrgCd(vo.getOrgCd());
		List<OrgUserInfoCfgVO> userInfoCfgList = orgUserInfoCfgService.list(ouicvo).getReturnList();
		request.setAttribute("userInfoCfgList", userInfoCfgList);

		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "E");
		request.setAttribute("fileupload", "Y");
		return "org/config/edit_config";
	}
	
	/**
     * @Method Name : editCfg
     * @Method 설명 : 기관의 사용자 정보 설정 값을 저장한다. 
	 * @param OrgUserInfoCfgVO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/editCfg")
	public String editCfg(OrgUserInfoCfgVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		ProcessResultVO<OrgUserInfoCfgVO> result = new ProcessResultVO<OrgUserInfoCfgVO>();
		try {
			orgUserInfoCfgService.edit(vo);
			result.setResult(1);
			result.setMessage(getMessage(request, "user.message.userinfo.edit.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "user.message.userinfo.edit.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}
	
	/**
     * @Method Name : sortCfg
     * @Method 설명 : 기관의 사용자 정보 설정 값의 순서를 변경한다. 
	 * @param OrgUserInfoCfgVO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/sortCfg")
	public String sortCfg(OrgUserInfoCfgVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		ProcessResultVO<OrgUserInfoCfgVO> result = new ProcessResultVO<OrgUserInfoCfgVO>();
		try {
			orgUserInfoCfgService.sort(vo);
			result.setResult(1);
			result.setMessage(getMessage(request, "user.message.userinfo.sort.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "user.message.userinfo.sort.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}	
}
