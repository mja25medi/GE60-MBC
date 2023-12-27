package egovframework.edutrack.web.system;

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

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.system.code.service.SysCodeCtgrVO;
import egovframework.edutrack.modules.system.code.service.SysCodeLangVO;
import egovframework.edutrack.modules.system.code.service.SysCodeService;
import egovframework.edutrack.modules.system.code.service.SysCodeVO;

@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/adm/system/code")
public class SysCodeAdminController extends GenericController {

    /** service */
	@Autowired @Qualifier("sysCodeService")
	private SysCodeService sysCodeService;
	
	/**
     * @Method Name : SysCodeMain
     * @Method 설명 : 코드 관리 메인 화면으로 이동한다. 
	 * @param SysCodeCtgrVO 
	 * @param commandMap
	 * @param model
	 * @return  "/adm/system/code/main.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/main")
	public String main(SysCodeCtgrVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		
		//throw new NullPointerException("null 포인트 에러");
		request.setAttribute("paging", "Y");
		
		return "system/code/main";
	}
	
	/**
     * @Method Name : SysCodeCtgrList
     * @Method 설명 : 코드 관리 분류 목록을 보여 준다. 
	 * @param SysCodeCtgrVO 
	 * @param commandMap
	 * @param model
	 * @return  "/adm/system/code/main.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/listCtgr")
	public String listCtgr(SysCodeCtgrVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultListVO<SysCodeCtgrVO> resultList = sysCodeService.listCtgrPageing(vo, vo.getPageIndex(), vo.getListScale());
		request.setAttribute("codeCtgrList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());
		return "system/code/list_ctgr";
	}
	
	/**
     * @Method Name : SysCodeCtgrAddForm
     * @Method 설명 : 코드 관리 분류 등록 화면을 보여 준다. 
	 * @param SysCodeCtgrVO 
	 * @param commandMap
	 * @param model
	 * @return  "/adm/system/code/main.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/addFormCtgrPop")
	public String addFormCtgrPop(SysCodeCtgrVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		
		vo.setUseYn("Y");
		
		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "A");
		return "system/code/write_ctgr";
	}
	
	/**
     * @Method Name : SysCodeCtgrAdd
     * @Method 설명 : 코드 관리 분류를 등록 한다. 
	 * @param SysCodeCtgrVO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/addCtgr")
	public String addCtgr(SysCodeCtgrVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		ProcessResultVO<SysCodeCtgrVO> result = new ProcessResultVO<SysCodeCtgrVO>();
		try {
			sysCodeService.addCtgr(vo);
			result.setResult(1);
			result.setMessage(getMessage(request, "system.message.code.add.category.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "system.message.code.add.category.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}
	
	/**
     * @Method Name : SysCodeCtgrAddForm
     * @Method 설명 : 코드 관리 분류 수정 화면을 보여준다. 
	 * @param SysCodeCtgrVO 
	 * @param commandMap
	 * @param model
	 * @return  "/adm/system/code/write_ctgr.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/editFormCtgrPop")
	public String editFormCtgrPop(SysCodeCtgrVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		
		vo = sysCodeService.viewCtgr(vo);
		
		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "E");
		return "system/code/write_ctgr";
	}
	
	/**
     * @Method Name : SysCodeCtgrAdd
     * @Method 설명 : 코드 관리 분류를 수정 한다. 
	 * @param SysCodeCtgrVO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/editCtgr")
	public String editCtgr(SysCodeCtgrVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		ProcessResultVO<SysCodeCtgrVO> result = new ProcessResultVO<SysCodeCtgrVO>();
		try {
			sysCodeService.editCtgr(vo);
			result.setResult(1);
			result.setMessage(getMessage(request, "system.message.code.edit.category.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "system.message.code.edit.category.failed"));
		}		
		return JsonUtil.responseJson(response, result);
	}
	
	/**
     * @Method Name : SysCodeCtgrAdd
     * @Method 설명 : 코드 관리 분류를 삭제한다. 
	 * @param SysCodeCtgrVO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/removeCtgr")
	public String removeCtgr(SysCodeCtgrVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		ProcessResultVO<SysCodeCtgrVO> result = new ProcessResultVO<SysCodeCtgrVO>();
		try {
			sysCodeService.removeCtgr(vo.getCodeCtgrCd());
			result.setResult(1);
			result.setMessage(getMessage(request, "system.message.code.delete.category.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "system.message.code.delete.category.failed"));
		}		
		return JsonUtil.responseJson(response, result);
	}
	
	/**
     * @Method Name : SysCodeCodeList
     * @Method 설명 : 코드 관리 코드 목록을 보여 준다. 
	 * @param SysCodeCtgrVO 
	 * @param commandMap
	 * @param model
	 * @return  "/adm/system/code/list_code.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/listCode")
	public String listCode(SysCodeVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultListVO<SysCodeVO> resultList = sysCodeService.listCode(vo.getCodeCtgrCd(), false);
		request.setAttribute("codeList", resultList.getReturnList());
		return "system/code/list_code";
	}
	
	/**
     * @Method Name : SysCodeCodeAddForm
     * @Method 설명 : 코드 관리 코드 등록 화면을 보여준다. 
	 * @param SysCodeVO 
	 * @param commandMap
	 * @param model
	 * @return  "/adm/system/code/write_code.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/addFormCodePop")
	public String addFormCodePop(SysCodeVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		
		SysCodeCtgrVO ctgrVO = new SysCodeCtgrVO();
		ctgrVO.setCodeCtgrCd(vo.getCodeCtgrCd());
		ctgrVO = sysCodeService.viewCtgr(ctgrVO);
		request.setAttribute("ctgrVo", ctgrVO);
		
		String[] langList = StringUtil.split(Constants.LANG_SUPPORT,"/");
		request.setAttribute("langList", langList);
		
		vo.setUseYn("Y"); //-- 사용여부 초기값 Y로 설정
		request.setAttribute("vo", vo);
		
		request.setAttribute("gubun", "A");
		return "system/code/write_code";
	}
	
	/**
     * @Method Name : SysCodeCodeAdd
     * @Method 설명 : 코드 관리 코드를 등록 한다. 
	 * @param SysCodeVO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/addCode")
	public String addCode(SysCodeVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String LANG_DEFAULT = Constants.LANG_DEFAULT;
		String[] langList = StringUtil.split(Constants.LANG_SUPPORT,"/");
		List<SysCodeLangVO> codeLangList = new ArrayList<SysCodeLangVO>();
		for(int i=0; i < langList.length; i++) {
			SysCodeLangVO sclvo = new SysCodeLangVO();
			sclvo.setCodeCtgrCd(vo.getCodeCtgrCd());
			sclvo.setCodeCd(vo.getCodeCd());
			sclvo.setLangCd(langList[i]);
			if(LANG_DEFAULT.equals(langList[i])) {
				sclvo.setCodeNm(vo.getCodeNm());
			} else {
				sclvo.setCodeNm(request.getParameter("codeNm_"+langList[i]));
			}
			codeLangList.add(sclvo);
		}
		vo.setCodeLangList(codeLangList);	
	
		ProcessResultVO<SysCodeCtgrVO> result = new ProcessResultVO<SysCodeCtgrVO>();
		try {
			sysCodeService.addCode(vo);
			result.setResult(1);
			result.setMessage(getMessage(request, "system.message.code.add.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "system.message.code.add.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}
	
	/**
     * @Method Name : SysCodeCodeAddForm
     * @Method 설명 : 코드 관리 코드 수정 화면을 보여준다. 
	 * @param SysCodeVO 
	 * @param commandMap
	 * @param model
	 * @return  "/adm/system/code/write_code.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/editFormCodePop")
	public String editFormCodePop(SysCodeVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		
		SysCodeCtgrVO ctgrVO = new SysCodeCtgrVO();
		ctgrVO.setCodeCtgrCd(vo.getCodeCtgrCd());
		ctgrVO = sysCodeService.viewCtgr(ctgrVO);
		request.setAttribute("ctgrVo", ctgrVO);
		
		String[] langList = StringUtil.split(Constants.LANG_SUPPORT,"/");
		request.setAttribute("langList", langList);
		
		vo = sysCodeService.viewCode(vo.getCodeCtgrCd(), vo.getCodeCd());
		request.setAttribute("vo", vo);
		
		request.setAttribute("gubun", "E");
		return "system/code/write_code";
	}
	
	/**
     * @Method Name : SysCodeCodeEdit
     * @Method 설명 : 코드 관리 코드를 수정 한다. 
	 * @param SysCodeVO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/editCode")
	public String editCode(SysCodeVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String LANG_DEFAULT = Constants.LANG_DEFAULT;
		String[] langList = StringUtil.split(Constants.LANG_SUPPORT,"/");
		List<SysCodeLangVO> codeLangList = new ArrayList<SysCodeLangVO>();
		for(int i=0; i < langList.length; i++) {
			SysCodeLangVO sclvo = new SysCodeLangVO();
			sclvo.setCodeCtgrCd(vo.getCodeCtgrCd());
			sclvo.setCodeCd(vo.getCodeCd());
			sclvo.setLangCd(langList[i]);
			if(LANG_DEFAULT.equals(langList[i])) {
				sclvo.setCodeNm(vo.getCodeNm());
			} else {
				sclvo.setCodeNm(request.getParameter("codeNm_"+langList[i]));
			}
			codeLangList.add(sclvo);
		}
		vo.setCodeLangList(codeLangList);
		
		ProcessResultVO<SysCodeCtgrVO> result = new ProcessResultVO<SysCodeCtgrVO>();
		try {
			sysCodeService.editCode(vo);
			result.setResult(1);
			result.setMessage(getMessage(request, "system.message.code.edit.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "system.message.code.edit.failed"));
		}
		return JsonUtil.responseJson(response, result);
		//return result;
	}
	
	/**
     * @Method Name : SysCodeSortAddForm
     * @Method 설명 : 코드 관리 코드 순서변경 화면을 보여준다. 
	 * @param SysCodeVO 
	 * @param commandMap
	 * @param model
	 * @return  "/adm/system/code/write_code.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/sortFormCodePop")
	public String sortFormCodePop(SysCodeVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);

		List<SysCodeVO> codeList = sysCodeService.listCode(vo.getCodeCtgrCd()).getReturnList();
		
		request.setAttribute("vo", vo);
		request.setAttribute("codeList", codeList);
		return "system/code/sort_code";
	}
	
	/**
     * @Method Name : SysCodeSortEdit
     * @Method 설명 : 코드 관리 코드 순서를 변경 한다. 
	 * @param SysCodeVO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/sortCode")
	public String sortCode(SysCodeVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		ProcessResultVO<SysCodeCtgrVO> result = new ProcessResultVO<SysCodeCtgrVO>();
		try {
			sysCodeService.sortCode(vo);
			result.setResult(1);
			result.setMessage(getMessage(request, "system.message.code.sort.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "system.message.code.sort.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}	
	
	/**
     * @Method Name : SysCodeCodeRemove
     * @Method 설명 : 코드 관리 코드를 삭제 한다. 
	 * @param SysCodeVO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/removeCode")
	public String removeCode(SysCodeVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		ProcessResultVO<SysCodeCtgrVO> result = new ProcessResultVO<SysCodeCtgrVO>();
		try {
			sysCodeService.removeCode(vo.getCodeCtgrCd(), vo.getCodeCd());
			result.setResult(1);
			result.setMessage(getMessage(request, "system.message.code.delete.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "system.message.code.delete.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}		
}
