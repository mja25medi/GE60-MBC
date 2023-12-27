package egovframework.edutrack.web.system;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.service.SysCodeMemService;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.system.code.service.SysCodeVO;
import egovframework.edutrack.modules.system.menu.service.SysAuthGrpLangVO;
import egovframework.edutrack.modules.system.menu.service.SysAuthGrpMenuVO;
import egovframework.edutrack.modules.system.menu.service.SysAuthGrpVO;
import egovframework.edutrack.modules.system.menu.service.SysMenuLangVO;
import egovframework.edutrack.modules.system.menu.service.SysMenuService;
import egovframework.edutrack.modules.system.menu.service.SysMenuVO;

@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/adm/system/menu")
public class SysMenuAdminController extends GenericController {

    /** service */
	@Autowired @Qualifier("sysMenuService")
	private SysMenuService 		sysMenuService;

	@Autowired @Qualifier("sysCodeMemService")
	private SysCodeMemService 	sysCodeMemService;
	
	/**
     * @Method Name : SysMenuMain
     * @Method 설명 : 메뉴 권한 관리 메인 화면으로 이동한다. 
	 * @param SysMenuVO 
	 * @param commandMap
	 * @param model
	 * @return  "/adm/system/menu/main.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/main")
	public String main(SysMenuVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		
		List<SysCodeVO> codeList = sysCodeMemService.getSysCodeList("MENU_TYPE");
		request.setAttribute("codeList", codeList);
				
		return "system/menu/main";
	}
	
	/**
     * @Method Name : SysAuthGrpList
     * @Method 설명 : 메뉴 권한 관리 권한 목록을 보여 준다. 
	 * @param SysAuthGrpVO 
	 * @param commandMap
	 * @param model
	 * @return  "/adm/system/menu/list_auth.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/listAuthGrp")
	public String listAuthGrp(SysAuthGrpVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultListVO<SysAuthGrpVO> resultList = sysMenuService.listAuthGrp(vo);
		request.setAttribute("authGrpList", resultList.getReturnList());
		return "system/menu/list_auth";
	}
	
	/**
     * @Method Name : SysAuthGrpAddForm
     * @Method 설명 : 메뉴 권한 관리 권한 등록 화면을 보여 준다. 
	 * @param SysAuthGrpVO 
	 * @param commandMap
	 * @param model
	 * @return  "/adm/system/menu/write_auth.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/addFormAuthGrpPop")
	public String addFormAuthGrpPop(SysAuthGrpVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);

		String[] langList = StringUtil.split(Constants.LANG_SUPPORT,"/");
		request.setAttribute("langList", langList);
		
		vo.setUseYn("Y");
		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "A");
		return "system/menu/write_auth";
	}
	
	/**
     * @Method Name : SysAuthGrpAdd
     * @Method 설명 : 메뉴 권한 관리 권한을 등록 한다. 
	 * @param SysAuthGrpVO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/addAuthGrp")
	public String addAuthGrp(SysAuthGrpVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String LANG_DEFAULT = Constants.LANG_DEFAULT;
		String[] langList = StringUtil.split(Constants.LANG_SUPPORT,"/");
		List<SysAuthGrpLangVO> authGrpLangList = new ArrayList<SysAuthGrpLangVO>();
		for(int i=0; i < langList.length; i++) {
			SysAuthGrpLangVO saglvo = new SysAuthGrpLangVO();
			saglvo.setMenuType(vo.getMenuType());
			saglvo.setAuthGrpCd(vo.getAuthGrpCd());
			saglvo.setLangCd(langList[i]);
			if(LANG_DEFAULT.equals(langList[i])) {
				saglvo.setAuthGrpNm(vo.getAuthGrpNm());
			} else {
				saglvo.setAuthGrpNm(request.getParameter("authGrpNm_"+langList[i]));
			}
			authGrpLangList.add(saglvo);
		}
		vo.setAuthGrpLangList(authGrpLangList);
		
		ProcessResultVO<SysAuthGrpVO> result = new ProcessResultVO<SysAuthGrpVO>();
		try {
			sysMenuService.addAuthGrp(vo);
			result.setResult(1);
			result.setMessage(getMessage(request, "system.message.menu.add.auth.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "system.message.menu.add.auth.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}
	
	/**
     * @Method Name : SysAuthGrpEditForm
     * @Method 설명 : 메뉴 권한 관리 권한 수정 화면을 보여 준다. 
	 * @param SysAuthGrpVO 
	 * @param commandMap
	 * @param model
	 * @return  "/adm/system/menu/write_auth.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/editFormAuthGrpPop")
	public String editFormAuthGrpPop(SysAuthGrpVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);

		String[] langList = StringUtil.split(Constants.LANG_SUPPORT,"/");
		request.setAttribute("langList", langList);

		vo = sysMenuService.viewAuthGrp(vo);
		
		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "E");
		return "system/menu/write_auth";
	}
	
	/**
     * @Method Name : SysAuthGrpEdit
     * @Method 설명 : 메뉴 권한 관리 권한을 수정 한다. 
	 * @param SysAuthGrpVO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/editAuthGrp")
	public String editAuthGrp(SysAuthGrpVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String LANG_DEFAULT = Constants.LANG_DEFAULT;
		String[] langList = StringUtil.split(Constants.LANG_SUPPORT,"/");
		List<SysAuthGrpLangVO> authGrpLangList = new ArrayList<SysAuthGrpLangVO>();
		for(int i=0; i < langList.length; i++) {
			SysAuthGrpLangVO saglvo = new SysAuthGrpLangVO();
			saglvo.setMenuType(vo.getMenuType());
			saglvo.setAuthGrpCd(vo.getAuthGrpCd());
			saglvo.setLangCd(langList[i]);
			if(LANG_DEFAULT.equals(langList[i])) {
				saglvo.setAuthGrpNm(vo.getAuthGrpNm());
			} else {
				saglvo.setAuthGrpNm(request.getParameter("authGrpNm_"+langList[i]));
			}
			authGrpLangList.add(saglvo);
		}
		vo.setAuthGrpLangList(authGrpLangList);
		
		ProcessResultVO<SysAuthGrpVO> result = new ProcessResultVO<SysAuthGrpVO>();
		try {
			sysMenuService.editAuthGrp(vo);
			result.setResult(1);
			result.setMessage(getMessage(request, "system.message.menu.edit.auth.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "system.message.menu.edit.auth.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}
	
	/**
     * @Method Name : SysAuthGrpRemove
     * @Method 설명 : 메뉴 권한 관리 권한을 삭제 한다. 
	 * @param SysAuthGrpVO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/removeAuthGrp")
	public String removeAuthGrp(SysAuthGrpVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		ProcessResultVO<SysAuthGrpVO> result = new ProcessResultVO<SysAuthGrpVO>();
		try {
			sysMenuService.removeAuthGrp(vo);
			result.setResult(1);
			result.setMessage(getMessage(request, "system.message.menu.delete.auth.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "system.message.menu.delete.auth.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}
	
	/**
     * @Method Name : SysMenuList
     * @Method 설명 : 메뉴 권한 관리 메뉴 목록을 보여 준다. 
	 * @param SysMenuVO 
	 * @param commandMap
	 * @param model
	 * @return  "/adm/system/menu/list_menu.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/listMenu")
	public String listMenu(SysMenuVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultListVO<SysMenuVO> resultList = sysMenuService.listTreeMenu(vo);
		request.setAttribute("menuList", resultList.getReturnList());
		return "system/menu/list_menu";
	}
	
	/**
     * @Method Name : SysMenuAddForm
     * @Method 설명 : 메뉴 권한 관리 메뉴 등록 화면을 보여 준다. 
	 * @param SysMenuVO 
	 * @param commandMap
	 * @param model
	 * @return  "/adm/system/menu/write_menu.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/addFormMenuPop")
	public String addFormMenuPop(SysMenuVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);

		String[] langList = StringUtil.split(Constants.LANG_SUPPORT,"/");
		request.setAttribute("langList", langList);
		
		//-- 메뉴 아이콘 목록 조회
		List<SysCodeVO> iconList = sysCodeMemService.getSysCodeList("MENU_ICON_CD");
		request.setAttribute("iconList", iconList);		
		
		vo.setUseYn("Y");
		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "A");
		return "system/menu/write_menu";
	}
	
	/**
     * @Method Name : SysMenuAddForm
     * @Method 설명 : 메뉴 권한 관리 하위 메뉴 등록 화면을 보여 준다. 
	 * @param SysMenuVO 
	 * @param commandMap
	 * @param model
	 * @return  "/adm/system/menu/write_menu.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/addFormSubMenuPop")
	public String addFormSubMenuPop(SysMenuVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		
		String locale = UserBroker.getLocaleKey(request);

		String[] langList = StringUtil.split(Constants.LANG_SUPPORT,"/");
		request.setAttribute("langList", langList);

		//-- 메뉴 아이콘 목록 조회
		List<SysCodeVO> iconList = sysCodeMemService.getSysCodeList("MENU_ICON_CD");
		request.setAttribute("iconList", iconList);


		SysMenuVO parent = new SysMenuVO();
		//---- 상위 메뉴의 정보를 가져온다.
		parent.setMenuType(vo.getMenuType());
		parent.setMenuCd(vo.getParMenuCd());
		parent = sysMenuService.viewMenu(parent);
		
		String parMenuName = parent.getMenuNm();
		for(SysMenuLangVO smlvo : parent.getMenuLangList()) {
			if(locale.equals(smlvo.getLangCd())) {
				parMenuName = smlvo.getMenuNm();
				break;
			}
		}
		vo.setParMenuCd(parent.getMenuCd());
		vo.setParMenuLvl(parent.getMenuLvl());
		vo.setParMenuNm(parMenuName);

		vo.setParMenuPath(parent.getMenuPath());
		
		vo.setUseYn("Y");
		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "A");
		return "system/menu/write_menu";
	}	
	
	/**
     * @Method Name : SysMenuAdd
     * @Method 설명 : 메뉴 권한 관리 메뉴를 등록 한다. 
	 * @param SysMenuVO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/addMenu")
	public String addMenu(SysMenuVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String LANG_DEFAULT = Constants.LANG_DEFAULT;
		String[] langList = StringUtil.split(Constants.LANG_SUPPORT,"/");
		List<SysMenuLangVO> menuLangList = new ArrayList<SysMenuLangVO>();
		for(int i=0; i < langList.length; i++) {
			SysMenuLangVO smlvo = new SysMenuLangVO();
			smlvo.setMenuCd(vo.getMenuCd());
			smlvo.setLangCd(langList[i]);
			if(LANG_DEFAULT.equals(langList[i])) {
				smlvo.setMenuNm(vo.getMenuNm());
			} else {
				smlvo.setMenuNm(request.getParameter("menuNm_"+langList[i]));
			}
			menuLangList.add(smlvo);
		}
		vo.setMenuLangList(menuLangList);
		
		ProcessResultVO<SysAuthGrpVO> result = new ProcessResultVO<SysAuthGrpVO>();
		try {
			sysMenuService.addMenu(vo);
			result.setResult(1);
			result.setMessage(getMessage(request, "system.message.menu.add.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "system.message.menu.add.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}
	
	/**
     * @Method Name : SysMenuEditForm
     * @Method 설명 : 메뉴 권한 관리 메뉴 수정 화면을 보여 준다. 
	 * @param SysMenuVO 
	 * @param commandMap
	 * @param model
	 * @return  "/adm/system/menu/write_menu.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/editFormMenuPop")
	public String editFormMenuPop(SysMenuVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);

		String[] langList = StringUtil.split(Constants.LANG_SUPPORT,"/");
		request.setAttribute("langList", langList);
		
		//-- 메뉴 아이콘 목록 조회
		List<SysCodeVO> iconList = sysCodeMemService.getSysCodeList("MENU_ICON_CD");
		request.setAttribute("iconList", iconList);
		
		vo = sysMenuService.viewMenu(vo);
		
		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "E");
		return "system/menu/write_menu";
	}
	
	/**
     * @Method Name : SysMenuAdd
     * @Method 설명 : 메뉴 권한 관리 메뉴를 수정 한다. 
	 * @param SysMenuVO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/editMenu")
	public String editMenu(SysMenuVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String LANG_DEFAULT = Constants.LANG_DEFAULT;
		String[] langList = StringUtil.split(Constants.LANG_SUPPORT,"/");
		List<SysMenuLangVO> menuLangList = new ArrayList<SysMenuLangVO>();
		for(int i=0; i < langList.length; i++) {
			SysMenuLangVO smlvo = new SysMenuLangVO();
			smlvo.setMenuCd(vo.getMenuCd());
			smlvo.setLangCd(langList[i]);
			if(LANG_DEFAULT.equals(langList[i])) {
				smlvo.setMenuNm(vo.getMenuNm());
			} else {
				smlvo.setMenuNm(request.getParameter("menuNm_"+langList[i]));
			}
			menuLangList.add(smlvo);
		}
		vo.setMenuLangList(menuLangList);
		
		ProcessResultVO<SysAuthGrpVO> result = new ProcessResultVO<SysAuthGrpVO>();
		try {
			sysMenuService.editMenu(vo);
			result.setResult(1);
			result.setMessage(getMessage(request, "system.message.menu.edit.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "system.message.menu.edit.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}
	
	/**
     * @Method Name : SysMenuSortForm
     * @Method 설명 : 메뉴 권한 관리 메뉴 순서를 변경하는 화면을 보여 준다. 
	 * @param SysMenuVO 
	 * @param commandMap
	 * @param model
	 * @return  "/adm/system/menu/write_menu.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/sortFormMenuPop")
	public String sortFormMenuPop(SysMenuVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);

		//List<SysMenuVO> menuList = sysMenuService.listTreeMenu(vo).getReturnList();
		
		//request.setAttribute("menuList", menuList);
		request.setAttribute("vo", vo);
		return "system/menu/sort_menu";
	}

	/**
     * @Method Name : SysMenuSortForm
     * @Method 설명 : 선택된 메뉴의 하위 메뉴의 목록을 Json 형태로 반환한다. 
	 * @param SysMenuVO 
	 * @param commandMap
	 * @param model
	 * @return  "/adm/system/menu/write_menu.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/listSubMenu")
	public String listSubMenu(SysMenuVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request,  HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		List<SysMenuVO> menuList = sysMenuService.listTreeMenu(vo).getReturnList();
		return JsonUtil.responseJson(response, menuList, this.getMenuVOFilter());
	}
	
	/**
     * @Method Name : SysMenuAdd
     * @Method 설명 : 메뉴 권한 관리 메뉴의 순서를 변경한다. 
	 * @param SysMenuVO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/sortMenu")
	public String sortMenu(SysMenuVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		ProcessResultVO<SysAuthGrpVO> result = new ProcessResultVO<SysAuthGrpVO>();
		try {
			sysMenuService.sortMenu(vo);
			result.setResult(1);
			result.setMessage(getMessage(request, "system.message.menu.sort.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "system.message.menu.sort.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}	
	
	/**
     * @Method Name : SysMenuRemove
     * @Method 설명 : 메뉴 권한 관리 메뉴를 삭제 한다. 
	 * @param SysMenuVO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/removeMenu")
	public String removeMenu(SysMenuVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		ProcessResultVO<SysAuthGrpVO> result = new ProcessResultVO<SysAuthGrpVO>();
		try {
			sysMenuService.removeMenu(vo);
			result.setResult(1);
			result.setMessage(getMessage(request, "system.message.menu.delete.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "system.message.menu.delete.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}	
	
	/**
     * @Method Name : SysAuthGrpMenuAdd
     * @Method 설명 : 메뉴 권한 관리 메뉴를 삭제 한다. 
	 * @param SysMenuVO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/saveAuthMenu")
	public String saveAuthMenu(SysAuthGrpMenuVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		ProcessResultVO<SysAuthGrpVO> result = new ProcessResultVO<SysAuthGrpVO>();
		try {
			sysMenuService.addAuthGroupMenu(vo);
			result.setResult(1);
			result.setMessage(getMessage(request, "system.message.menu.add.authmenu.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "system.message.menu.add.authmenu.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}
	
	/**
	 * MenuDTO의 리스트 조회시 순환참조 방지를 위해 parentMenu의 항목을 무시.
	 * @return
	 */
	private JsonConfig getMenuVOFilter() {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
			public boolean apply(Object obj, String name, Object value) {
				if(obj instanceof SysMenuVO && name.equals("parentMenu"))
					return true;
				return false;
			}
		});

		return jsonConfig;
	}	
}
