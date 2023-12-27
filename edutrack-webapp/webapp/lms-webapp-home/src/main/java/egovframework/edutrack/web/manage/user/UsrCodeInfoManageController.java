package egovframework.edutrack.web.manage.user;

import java.util.ArrayList;
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
import egovframework.edutrack.comm.service.SysCodeMemService;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.org.code.service.OrgCodeCtgrVO;
import egovframework.edutrack.modules.org.code.service.OrgCodeLangVO;
import egovframework.edutrack.modules.org.code.service.OrgCodeService;
import egovframework.edutrack.modules.org.code.service.OrgCodeVO;

@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/mng/user/codeInfo")
public class UsrCodeInfoManageController extends GenericController {

	@Autowired @Qualifier("orgCodeService")
	private OrgCodeService 	 orgCodeService;

	@Autowired @Qualifier("sysCodeMemService")
	private SysCodeMemService			sysCodeMemService;

	/**
	 * 소속 관리 메인 화면
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/codeInfoMain")
	public String codeInfoMain(OrgCodeCtgrVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) {

		String codeCtgrCd = vo.getCodeCtgrCd();

		request.setAttribute("vo", vo);
		request.setAttribute("codeCtgrCd", codeCtgrCd);
		request.setAttribute("paging", "Y");
		
		return "mng/user/code/user_code_main";
	}
	
	/**
	 * 사용자 코드 관리 목록
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/listCodeInfo")
	public String listCodeInfo(OrgCodeVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {


		String orgCd = UserBroker.getUserOrgCd(request);
		String cdCtgrCd = request.getParameter("codeCtgrCd");
		request.setAttribute("codeCtgrCd", cdCtgrCd);

		vo.setCodeCtgrCd(cdCtgrCd);
		vo.setOrgCd(orgCd);

		vo.setPageScale(15);
		ProcessResultListVO<OrgCodeVO> resultList = orgCodeService.listCodePageing(vo);
		request.setAttribute("orgCodeList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());

		ProcessResultListVO<OrgCodeVO> result = new ProcessResultListVO<OrgCodeVO>();
		List<OrgCodeVO> returnList = new ArrayList<OrgCodeVO>();
		for(OrgCodeVO sdto : resultList.getReturnList()) {
			OrgCodeLangVO cldto = new OrgCodeLangVO();
			cldto.setCodeCtgrCd(sdto.getCodeCtgrCd());
			cldto.setCodeCd(sdto.getCodeCd());
			cldto.setOrgCd(sdto.getOrgCd());
			List<OrgCodeLangVO> codeLangList = orgCodeService.langList(cldto);
			sdto.setCodeLangList(codeLangList);
			returnList.add(sdto);
		}
		result.setResult(1);
		result.setReturnList(returnList);

		/*
		String locale = UserBroker.getLocaleKey(request);
		List<OrgCodeDTO> codeList = new ArrayList<OrgCodeDTO>();
		for(OrgCodeDTO codeDTO : result.getReturnList()) {
			for(OrgCodeLangDTO codeLangDTO : codeDTO.getCodeLangList()) {
				if(locale.equals(codeLangDTO.getLangCd())) codeDTO.setCodeNm(codeLangDTO.getCodeNm());
			}
			codeList.add(codeDTO);
		}
		*/
		request.setAttribute("codeList", resultList.getReturnList());



		return "mng/user/code/user_code_list_div";
	}
	
	/**
	 * 사용자 코드 관리 메인 화면
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/mainCodePop")
	public String mainCode(OrgCodeCtgrVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		request.setAttribute("vo", vo);
		request.setAttribute("paging", "Y");

		return "mng/user/code/main_code_pop";
	}
	
	
	/**
	 * 사용자 코드 관리 목록
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/listCode")
	public String listUserCode(OrgCodeVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		String cdCtgrCd = request.getParameter("codeCtgrCd");
		request.setAttribute("codeCtgrCd", cdCtgrCd);
        
		vo.setCurPage(vo.getPageIndex());
		ProcessResultListVO<OrgCodeVO> resultList = orgCodeService.listCodePageing(vo);
    	request.setAttribute("codeList", resultList.getReturnList());
    	request.setAttribute("pageInfo", resultList.getPageInfo());
		request.setAttribute("vo", vo);
		


		return "mng/user/code/list_code";
	}

	
	
	/**
	 * 사용자 코드 코드 등록 폼
	 *
	 * @return ProcessResultDTO
	 */
	@RequestMapping(value="/addCodePop")
	public String addCodePop(OrgCodeVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {

		String cdCtgrCd = vo.getCodeCtgrCd();
		request.setAttribute("codeCtgrCd", cdCtgrCd);
		request.setAttribute("isPop", request.getParameter("isPop"));

		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "A");
		return "mng/user/code/write_code_pop";
	}

	/**
	 * 사용자 코드 코드 등록
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addCode")
	public String addCode(OrgCodeVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		ProcessResultVO<OrgCodeVO> result = new ProcessResultVO<OrgCodeVO>();
		
		try {
			orgCodeService.addCode(vo);
			result.setResult(1);
			result.setMessage(getMessage(request, "user.message.code.add.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "user.message.code.add.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}


	/**
	 * 사용자 코드 코드 수정 폼
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/editCodePop")
	public String editCodePop(OrgCodeVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {

		String cdCtgrCd = vo.getCodeCtgrCd();
		request.setAttribute("codeCtgrCd", cdCtgrCd);
		request.setAttribute("isPop", request.getParameter("isPop"));

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		// 시스템코드 정보 조회
		vo = orgCodeService.viewCode(orgCd, cdCtgrCd, vo.getCodeCd());
		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "E");
		return "mng/user/code/write_code_pop";
	}

	/**
	 * 사용자 코드 코드 수정
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/editCode")
	public String editCode(OrgCodeVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		ProcessResultVO<OrgCodeVO> result = new ProcessResultVO<OrgCodeVO>();
		
		try {
			orgCodeService.editCode(vo);
			result.setResult(1);
			result.setMessage(getMessage(request, "user.message.code.edit.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "user.message.code.edit.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}

	/**
	 * 사용자 코드 코드 삭제
	 *
	 * @return ProcessResultDTO
	 */
	@RequestMapping(value="/deleteCode")
	public String deleteCode(OrgCodeVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response ) throws Exception {
		
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		ProcessResultVO<OrgCodeVO> result = new ProcessResultVO<OrgCodeVO>();
		
		try {
			orgCodeService.removeCode(orgCd, vo.getCodeCtgrCd(), vo.getCodeCd());
			result.setResult(1);
			result.setMessage(getMessage(request, "user.message.code.delete.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "user.message.code.delete.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}

	
}
