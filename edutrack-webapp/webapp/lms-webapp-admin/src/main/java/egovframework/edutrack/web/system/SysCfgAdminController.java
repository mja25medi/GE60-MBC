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
import egovframework.edutrack.modules.system.config.service.SysCfgCtgrVO;
import egovframework.edutrack.modules.system.config.service.SysCfgService;
import egovframework.edutrack.modules.system.config.service.SysCfgVO;

@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/adm/system/config")
public class SysCfgAdminController extends GenericController {

    /** service */
	@Autowired @Qualifier("sysCfgService")
	private SysCfgService sysCfgService;
	
	/**
     * @Method Name : SysCfgMain
     * @Method 설명 : 설정 관리 메인 화면으로 이동한다. 
	 * @param SysCfgCtgrVO 
	 * @param commandMap
	 * @param model
	 * @return  "/adm/system/config/main.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/main")
	public String main(SysCfgCtgrVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		request.setAttribute("paging", "Y");
	
		return "system/config/main";
	}
	
	/**
     * @Method Name : SysCfgCtgrList
     * @Method 설명 : 설정 관리 분류 목록을 보여 준다. 
	 * @param SysCfgCtgrVO 
	 * @param commandMap
	 * @param model
	 * @return  "/adm/system/cfg/list_ctgr.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/listCtgr")
	public String listCtgr(SysCfgCtgrVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultListVO<SysCfgCtgrVO> resultList = sysCfgService.listCtgrPageing(vo, vo.getPageIndex(), vo.getListScale());
		request.setAttribute("cfgCtgrList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());
		return "system/config/list_ctgr";
	}
	
	/**
     * @Method Name : SysCfgCtgrAddForm
     * @Method 설명 : 설정 관리 분류 등록 화면을 보여 준다. 
	 * @param SysCfgCtgrVO 
	 * @param commandMap
	 * @param model
	 * @return  "/adm/system/config/write_ctgr.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/addFormCtgrPop")
	public String addFormCtgrPop(SysCfgCtgrVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		
		vo.setUseYn("Y");
		
		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "A");
		return "system/config/write_ctgr";
	}
	
	/**
     * @Method Name : SysCfgCtgrAdd
     * @Method 설명 : 설정 관리 분류를 등록 한다. 
	 * @param SysCfgCtgrVO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/addCtgr")
	public String addCtgr(SysCfgCtgrVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		ProcessResultVO<SysCfgCtgrVO> result = new ProcessResultVO<SysCfgCtgrVO>();
		try {
			sysCfgService.addCtgr(vo);
			result.setResult(1);
			result.setMessage(getMessage(request, "system.message.config.add.category.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "system.message.config.add.category.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}
	
	/**
     * @Method Name : SysCfgCtgrAddForm
     * @Method 설명 : 설정 관리 분류 수정 화면을 보여준다. 
	 * @param SysCfgCtgrVO 
	 * @param commandMap
	 * @param model
	 * @return  "/adm/system/config/write_ctgr.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/editFormCtgrPop")
	public String editFormCtgrPop(SysCfgCtgrVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		
		vo = sysCfgService.viewCtgr(vo);
		
		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "E");
		return "system/config/write_ctgr";
	}
	
	/**
     * @Method Name : SysCfgCtgrEdit
     * @Method 설명 : 설정 관리 분류를 수정 한다. 
	 * @param SysCfgCtgrVO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/editCtgr")
	public String editCtgr(SysCfgCtgrVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		ProcessResultVO<SysCfgCtgrVO> result = new ProcessResultVO<SysCfgCtgrVO>();
		try {
			sysCfgService.editCtgr(vo);
			result.setResult(1);
			result.setMessage(getMessage(request, "system.message.config.edit.category.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "system.message.config.edit.category.failed"));
		}		
		return JsonUtil.responseJson(response, result);
	}
	
	/**
     * @Method Name : SysCfgCtgrAdd
     * @Method 설명 : 설정 관리 분류를 삭제한다. 
	 * @param SysCfgCtgrVO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/removeCtgr")
	public String removeCtgr(SysCfgCtgrVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		ProcessResultVO<SysCfgCtgrVO> result = new ProcessResultVO<SysCfgCtgrVO>();
		try {
			sysCfgService.removeCtgr(vo.getCfgCtgrCd());
			result.setResult(1);
			result.setMessage(getMessage(request, "system.message.config.delete.category.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "system.message.config.delete.category.failed"));
		}		
		return JsonUtil.responseJson(response, result);
	}
	
	/**
     * @Method Name : SysCfgValueList
     * @Method 설명 : 설정 관리 설정값 목록을 보여 준다. 
	 * @param SysCfgVO 
	 * @param commandMap
	 * @param model
	 * @return  "/adm/system/config/list_config.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/listCfg")
	public String listCfg(SysCfgVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultListVO<SysCfgVO> resultList = sysCfgService.listCfg(vo);
		request.setAttribute("configList", resultList.getReturnList());
		return "system/config/list_config";
	}
	
	/**
     * @Method Name : SysCfgAddForm
     * @Method 설명 : 설정 관리 설정값 등록 화면을 보여준다. 
	 * @param SysCfgVO 
	 * @param commandMap
	 * @param model
	 * @return  "/adm/system/config/write_code.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/addFormCfgPop")
	public String addFormCfgPop(SysCfgVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		
		SysCfgCtgrVO ctgrVO = new SysCfgCtgrVO();
		ctgrVO.setCfgCtgrCd(vo.getCfgCtgrCd());
		ctgrVO = sysCfgService.viewCtgr(ctgrVO);
		request.setAttribute("ctgrVo", ctgrVO);
		
		vo.setUseYn("Y"); //-- 사용여부 초기값 Y로 설정
		request.setAttribute("vo", vo);
		
		request.setAttribute("gubun", "A");
		return "system/config/write_config";
	}
	
	/**
     * @Method Name : SysCfgAdd
     * @Method 설명 : 설정 관리 설정값을 등록 한다. 
	 * @param SysCfgVO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/addCfg")
	public String addCfg(SysCfgVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		ProcessResultVO<SysCfgCtgrVO> result = new ProcessResultVO<SysCfgCtgrVO>();
		try {
			sysCfgService.addCfg(vo);
			result.setResult(1);
			result.setMessage(getMessage(request, "system.message.config.add.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "system.message.config.add.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}
	
	/**
     * @Method Name : SysCfgEditForm
     * @Method 설명 : 설정 관리 설정값 수정 화면을 보여준다. 
	 * @param SysCfgVO 
	 * @param commandMap
	 * @param model
	 * @return  "/adm/system/config/write_config.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/editFormCfgPop")
	public String editFormCfgPop(SysCfgVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		
		SysCfgCtgrVO ctgrVO = new SysCfgCtgrVO();
		ctgrVO.setCfgCtgrCd(vo.getCfgCtgrCd());
		ctgrVO = sysCfgService.viewCtgr(ctgrVO);
		request.setAttribute("ctgrVo", ctgrVO);
		
		vo = sysCfgService.viewCfg(vo);
		request.setAttribute("vo", vo);
		
		request.setAttribute("gubun", "E");
		return "system/config/write_config";
	}
	
	/**
     * @Method Name : SysCfgEdit
     * @Method 설명 : 설정 관리 설정값을 수정 한다. 
	 * @param SysCfgVO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/editCfg")
	public String editCfg(SysCfgVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		ProcessResultVO<SysCfgCtgrVO> result = new ProcessResultVO<SysCfgCtgrVO>();
		try {
			sysCfgService.editCfg(vo);
			result.setResult(1);
			result.setMessage(getMessage(request, "system.message.config.edit.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "system.message.config.edit.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}
	
	/**
     * @Method Name : SysCfgRemove
     * @Method 설명 : 설정 관리 설정값을 삭제 한다. 
	 * @param SysCfgVO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/removeCfg")
	public String removeCfg(SysCfgVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		ProcessResultVO<SysCfgVO> result = new ProcessResultVO<SysCfgVO>();
		try {
			sysCfgService.removeCfg(vo);
			result.setResult(1);
			result.setMessage(getMessage(request, "system.message.config.delete.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "system.message.config.delete.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}		
}
