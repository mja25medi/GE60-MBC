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
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.org.emailtpl.service.OrgEmailTplService;
import egovframework.edutrack.modules.org.emailtpl.service.OrgEmailTplVO;

@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/mng/org/emailTpl")
public class EmailTplManageController extends GenericController {
	
	@Autowired @Qualifier("orgEmailTplService")
	private OrgEmailTplService 			orgEmailTplService;

	
	/**
     * @Method Name : main
     * @Method 설명 : 이메일 템플릿 관리 메인
	 * @param VO
	 * @param commandMap
	 * @param model
	 * @return  
	 * @throws Exception
	 */
	@RequestMapping(value="/main")
	public String main(OrgEmailTplVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		request.setAttribute("vo", vo);
		return "mng/org/email/tpl_main";
	}
	
	/**
	 * 이메일 템플릿 목록 조회(JSON)
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/list")
	public String list(OrgEmailTplVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {

		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		List<OrgEmailTplVO> emailTplList = orgEmailTplService.list(vo).getReturnList();
		request.setAttribute("emailTplList", emailTplList);
		return "mng/org/email/list_tpl";
	}

	/**
	 *이메일 템플릿 등록 폼
	 *
	 * @return  ProcessResultDTO
	 */
	@RequestMapping(value="/addPop")
	public String addPop(OrgEmailTplVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {

		commonVOProcessing(vo, request);
		vo.setUseYn("Y"); //-- 기본은 사용으로 셋팅

		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "A");	// ADD
		return this.getEditorType(vo.getTplType(),request);
	}


	/**
	 * 이메일 템플릿 등록
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/add")
	public String add(OrgEmailTplVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		// 스크립트, 스타일 태그 제거
		//emailTplDTO.setPageCts(HtmlCleaner.cleanScript(emailTplDTO.getPageCts()) );
		// 저장
		ProcessResultVO<?> result = new ProcessResultVO<OrgEmailTplVO>();
		try {
			orgEmailTplService.add(vo);
			result.setResult(1);
			result.setMessage(getMessage(request, "org.message.emailtpl.add.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "org.message.emailtpl.add.failed"));
		}
		
		return JsonUtil.responseJson(response, result);
	}

	/**
	 *이메일 템플릿 수정 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/editPop")
	public String editPop(OrgEmailTplVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {

		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		//이메일 템플릿 정보 조회
		vo = orgEmailTplService.view(vo);
		
		request.setAttribute("mgrSiteCd", Constants.MGR_SITE_CD);
		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "E");
		return this.getEditorType(vo.getTplType(),request);
	}

	/**
	 *이메일 템플릿 수정
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/edit")
	public String edit(OrgEmailTplVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		// 스크립트, 스타일 태그 제거
		//emailTplDTO.setPageCts(HtmlCleaner.cleanScript(emailTplDTO.getPageCts()) );
		// 저장
		ProcessResultVO<?> result = new ProcessResultVO<OrgEmailTplVO>();
		try {
			orgEmailTplService.edit(vo);
			result.setResult(1);
			result.setMessage(getMessage(request, "org.message.emailtpl.edit.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "org.message.emailtpl.edit.failed"));
		}
		
		return JsonUtil.responseJson(response, result);
	}

	/**
	 *이메일 템플릿 삭제
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/remove")
	public String remove(OrgEmailTplVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		ProcessResultVO<?> result = new ProcessResultVO<OrgEmailTplVO>();
		try {
			orgEmailTplService.remove(vo);
			result.setResult(1);
			result.setMessage(getMessage(request, "org.message.emailtpl.delete.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "org.message.emailtpl.delete.failed"));
		}
		
		return JsonUtil.responseJson(response, result);
	}

	private String getEditorType(String type,HttpServletRequest request) {
		String forwardUrl = "mng/org/email/write_tpl";
		//-- 에디터 사용여부를 판단하여 해당 Webeditor를 사용하도록 한다.
		//-- BBS_INFO의 editorUseYn은 더이상 사용하지 않음.
		if("SMS".equals(type)){
			//sms는 에디터 사용안함
		}else
		if("Y".equals(Constants.WEBEDITOR_YSEYN)) {
			if("SUMMERNOTE".equals(Constants.WEBEDITOR_NAME)) {
				forwardUrl = "mng/org/email/write_tpl_summernote";
				request.setAttribute("summernote", "Y");
			} else if("DAUMEDITOR".equals(Constants.WEBEDITOR_NAME)) {
				forwardUrl = "mng/org/email/write_tpl_daumeditor";
				request.setAttribute("daumeditor", "Y");
			}
		}
		return forwardUrl;
	}
}
