package egovframework.edutrack.web.org;

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
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.board.bbs.service.BrdBbsInfoService;
import egovframework.edutrack.modules.board.bbs.service.BrdBbsInfoVO;
import egovframework.edutrack.modules.org.menu.service.OrgAuthGrpMenuVO;
import egovframework.edutrack.modules.org.menu.service.OrgMenuLangVO;
import egovframework.edutrack.modules.org.menu.service.OrgMenuService;
import egovframework.edutrack.modules.org.menu.service.OrgMenuVO;
import egovframework.edutrack.modules.org.page.service.OrgPageService;
import egovframework.edutrack.modules.org.page.service.OrgPageVO;
import egovframework.edutrack.modules.system.code.service.SysCodeVO;
import egovframework.edutrack.modules.system.menu.service.SysAuthGrpVO;
import egovframework.edutrack.modules.system.menu.service.SysMenuService;

@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/adm/org/menu")
public class OrgMenuAdminController extends GenericController {

    /** service */
	@Autowired @Qualifier("orgMenuService")
	private OrgMenuService 		orgMenuService;
	
	@Autowired @Qualifier("orgPageService")
	private OrgPageService 		orgPageService;
	
	@Autowired @Qualifier("brdBbsInfoService")
	private BrdBbsInfoService 	brdBbsInfoService;	

	@Autowired @Qualifier("sysCodeMemService")
	private SysCodeMemService	sysCodeMemService;

	@Autowired @Qualifier("sysMenuService")
	private SysMenuService 		sysMenuService;
	
	/**
     * @Method Name : main
     * @Method 설명 : 기관 메뉴 관리 메인 화면으로 이동한다. 
	 * @param OrgMenuVO 
	 * @param commandMap
	 * @param model
	 * @return  "/adm/org/menu/main.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/main")
	public String main(OrgMenuVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		
		String menuType = "HOME";

		// 홈페이지 권한 목록 가져오기
		SysAuthGrpVO sagvo = new SysAuthGrpVO();
		sagvo.setMenuType(menuType);
		List<SysAuthGrpVO> authGrpList = sysMenuService.listAuthGrp(sagvo).getReturnList();
		request.setAttribute("authGrpList", authGrpList);
		
		request.setAttribute("vo", vo);
		return "org/menu/main";
	}
	
	/**
     * @Method Name : list
     * @Method 설명 : 기관 메뉴 목록을 보여 준다. 
	 * @param OrgMenuVO 
	 * @param commandMap
	 * @param model
	 * @return  "/adm/org/menu/list_menu.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/list")
	public String list(OrgMenuVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);

		vo.setMenuType("HOME"); //-- 홈페이지
		vo.setParMenuCd(""); //-- 최상위 메뉴

		ProcessResultListVO<OrgMenuVO> resultList = orgMenuService.listTreeMenu(vo);
		
		request.setAttribute("menuList", resultList.getReturnList());
		request.setAttribute("authGrpCd", vo.getAuthGrpCd());
		return "org/menu/list_menu";
	}
	
	/**
     * @Method Name : addFromMenu
     * @Method 설명 : 기관 메뉴 등록 화면을 보여 준다. 
	 * @param OrgMenuVO 
	 * @param commandMap
	 * @param model
	 * @return  "/adm/org/menu/write_menu.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/addFormMenuPop")
	public String addFormMenuPop(OrgMenuVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		
		vo.setMenuType("HOME");
		vo.setUseYn("Y");
		
		vo.setParMenuNm(getMessage(request, "system.title.menu.rootmenu"));
		vo.setParMenuLvl(0);

		String[] langList = StringUtil.split(Constants.LANG_SUPPORT,"/");
		request.setAttribute("langList", langList);

		//-- 메뉴 아이콘 목록 조회
		List<SysCodeVO> iconList = sysCodeMemService.getSysCodeList("MENU_ICON_CD");
		request.setAttribute("iconList", iconList);		
		
		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "A");
		request.setAttribute("paging", "Y");
		return "org/menu/write_menu";
	}
	
	/**
     * @Method Name : addFromMenu
     * @Method 설명 : 기관 하위 메뉴 등록 화면을 보여 준다. 
	 * @param OrgMenuVO 
	 * @param commandMap
	 * @param model
	 * @return  "/adm/org/menu/write_menu.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/addFormSubMenu")
	public String addFormSubMenu(OrgMenuVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		
		String locale = UserBroker.getLocaleKey(request);
		vo.setMenuType("HOME");

		String[] langList = StringUtil.split(Constants.LANG_SUPPORT,"/");
		request.setAttribute("langList", langList);

		//-- 메뉴 아이콘 목록 조회
		List<SysCodeVO> iconList = sysCodeMemService.getSysCodeList("MENU_ICON_CD");
		request.setAttribute("iconList", iconList);

		//-- 상위 메뉴의 정보를 검색해 온다.
		OrgMenuVO parMenuVO = new OrgMenuVO();
		parMenuVO.setOrgCd(vo.getOrgCd());
		parMenuVO.setMenuType(vo.getMenuType());
		parMenuVO.setMenuCd(vo.getParMenuCd());
		parMenuVO = orgMenuService.viewMenu(parMenuVO);

		String parMenuName = parMenuVO.getMenuNm();
		for(OrgMenuLangVO omlvo : parMenuVO.getMenuLangList()) {
			if(locale.equals(omlvo.getLangCd())) {
				parMenuName = omlvo.getMenuNm();
				break;
			}
		}
		vo.setParMenuCd(parMenuVO.getMenuCd());
		vo.setParMenuLvl(parMenuVO.getMenuLvl());
		vo.setParMenuNm(parMenuName);
		
		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "A");
		return "org/menu/write_menu";
	}	
	
	/**
     * @Method Name : addMenu
     * @Method 설명 : 기관 메뉴를 등록 한다. 
	 * @param OrgMenuVO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/addMenu")
	public String addMenu(OrgMenuVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		vo.setMenuType("HOME");
		vo.setAutoMakeYn("Y"); //-- 강제로 메뉴코드를 만들도록 셋팅

		String LANG_DEFAULT = Constants.LANG_DEFAULT;
		String[] langList = StringUtil.split(Constants.LANG_SUPPORT,"/");
		List<OrgMenuLangVO> menuLangList = new ArrayList<OrgMenuLangVO>();
		for(int i=0; i < langList.length; i++) {
			OrgMenuLangVO omlvo = new OrgMenuLangVO();
			omlvo.setOrgCd(vo.getOrgCd());
			omlvo.setMenuCd(vo.getMenuCd());
			omlvo.setLangCd(langList[i]);
			if(LANG_DEFAULT.equals(langList[i])) {
				omlvo.setMenuNm(vo.getMenuNm());
			} else {
				omlvo.setMenuNm(request.getParameter("menuNm_"+langList[i]));
			}
			menuLangList.add(omlvo);
		}
		vo.setMenuLangList(menuLangList);

		ProcessResultVO<OrgMenuVO> result = new ProcessResultVO<OrgMenuVO>();
		try {
			orgMenuService.addMenu(vo);
			result.setResult(1);
			result.setMessage(getMessage(request, "system.message.menu.add.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "system.message.menu.add.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}
	
	/**
     * @Method Name : addFromMenu
     * @Method 설명 : 기관 메뉴 수정 화면을 보여 준다. 
	 * @param OrgMenuVO 
	 * @param commandMap
	 * @param model
	 * @return  "/adm/org/menu/write_menu.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/editFormMenuPop")
	public String editFormMenuPop(OrgMenuVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);
		
		String locale = UserBroker.getLocaleKey(request);
		
		vo.setMenuType("HOME");
		vo.setUseYn("Y");
		
		vo.setParMenuNm(getMessage(request, "system.title.menu.rootmenu"));
		vo.setParMenuLvl(0);

		String[] langList = StringUtil.split(Constants.LANG_SUPPORT,"/");
		request.setAttribute("langList", langList);

		//-- 메뉴 아이콘 목록 조회
		List<SysCodeVO> iconList = sysCodeMemService.getSysCodeList("MENU_ICON_CD");
		request.setAttribute("iconList", iconList);		

		vo = orgMenuService.viewMenu(vo);
		
		if(ValidationUtils.isNotEmpty(vo.getParMenuCd())) {
			//-- 상위메뉴
			OrgMenuVO parMenuVO = new OrgMenuVO();
			parMenuVO.setOrgCd(vo.getOrgCd());
			parMenuVO.setMenuType(vo.getMenuType());
			parMenuVO.setMenuCd(vo.getParMenuCd());

			parMenuVO = orgMenuService.viewMenu(parMenuVO);
			String parMenuName = vo.getParMenuNm();

			for(OrgMenuLangVO omlvo : parMenuVO.getMenuLangList()) {
				if(locale.equals(omlvo.getLangCd())) {
					parMenuName = omlvo.getMenuNm();
				}
			}
			vo.setParMenuNm(parMenuName);
			vo.setParMenuLvl(parMenuVO.getMenuLvl());
		} else {
			vo.setParMenuNm(getMessage(request, "system.title.menu.rootmenu"));
			vo.setParMenuLvl(0);
		}		
		
		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "E");
		request.setAttribute("paging", "Y");
		return "org/menu/write_menu";
	}
	
	/**
     * @Method Name : editMenu
     * @Method 설명 : 기관 메뉴를 수정 한다. 
	 * @param OrgMenuVO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/editMenu")
	public String editMenu(OrgMenuVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		vo.setMenuType("HOME");
		vo.setAutoMakeYn("Y"); //-- 강제로 메뉴코드를 만들도록 셋팅

		String LANG_DEFAULT = Constants.LANG_DEFAULT;
		String[] langList = StringUtil.split(Constants.LANG_SUPPORT,"/");
		List<OrgMenuLangVO> menuLangList = new ArrayList<OrgMenuLangVO>();
		for(int i=0; i < langList.length; i++) {
			OrgMenuLangVO omlvo = new OrgMenuLangVO();
			omlvo.setOrgCd(vo.getOrgCd());
			omlvo.setMenuCd(vo.getMenuCd());
			omlvo.setLangCd(langList[i]);
			if(LANG_DEFAULT.equals(langList[i])) {
				omlvo.setMenuNm(vo.getMenuNm());
			} else {
				omlvo.setMenuNm(request.getParameter("menuNm_"+langList[i]));
			}
			menuLangList.add(omlvo);
		}
		vo.setMenuLangList(menuLangList);

		ProcessResultVO<OrgMenuVO> result = new ProcessResultVO<OrgMenuVO>();
		try {
			orgMenuService.editMenu(vo);
			result.setResult(1);
			result.setMessage(getMessage(request, "system.message.menu.edit.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "system.message.menu.edit.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}
	
	/**
     * @Method Name : removeMenu
     * @Method 설명 : 기관 메뉴를 삭제 한다. 
	 * @param OrgMenuVO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/removeMenu")
	public String removeMenu(OrgMenuVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultVO<OrgMenuVO> result = new ProcessResultVO<OrgMenuVO>();
		try {
			orgMenuService.removeMenu(vo);
			result.setResult(1);
			result.setMessage(getMessage(request, "system.message.menu.delete.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "system.message.menu.delete.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}
	
	/**
     * @Method Name : moveUpMenu
     * @Method 설명 : 기관 메뉴를 위로 이동 시킨다. 
	 * @param OrgMenuVO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/moveUpMenu")
	public String moveUpMenu(OrgMenuVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		vo.setMenuType("HOME");

		ProcessResultVO<OrgMenuVO> result = new ProcessResultVO<OrgMenuVO>();
		try {
			orgMenuService.moveMenu(vo, "up");
			result.setResult(1);
			result.setMessage(getMessage(request, "system.message.menu.sort.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "system.message.menu.sort.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}
	
	/**
     * @Method Name : moveDownMenu
     * @Method 설명 : 기관 메뉴를 아래로 이동 시킨다. 
	 * @param OrgMenuVO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/moveDownMenu")
	public String moveDownMenu(OrgMenuVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		vo.setMenuType("HOME");

		ProcessResultVO<OrgMenuVO> result = new ProcessResultVO<OrgMenuVO>();
		try {
			orgMenuService.moveMenu(vo, "down");
			result.setResult(1);
			result.setMessage(getMessage(request, "system.message.menu.sort.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "system.message.menu.sort.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}
	
	/**
     * @Method Name : initMenu
     * @Method 설명 : 기관 메뉴를 초기화 시킨다. 
	 * @param OrgMenuVO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/initMenu")
	public String initMenu(OrgMenuVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		vo.setMenuType("HOME");

		ProcessResultVO<OrgMenuVO> result = new ProcessResultVO<OrgMenuVO>();
		try {
			orgMenuService.initMenu(vo);
			result.setResult(1);
			result.setMessage(getMessage(request, "system.message.menu.init.success"));
		} catch (Exception e) {
			result.setResult(-1);
			result.setMessage(getMessage(request, "system.message.menu.init.failed"));
		}
		return JsonUtil.responseJson(response, result);
	}
	
	/**
     * @Method Name : saveAuthMenu
     * @Method 설명 : 기관의 권한별 메뉴를 저장한다. 
	 * @param OrgAuthGrpMenuVO 
	 * @param commandMap
	 * @param model
	 * @return  json
	 * @throws Exception
	 */
	@RequestMapping(value="/saveAuthMenu")
	public String saveAuthMenu(OrgAuthGrpMenuVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		vo.setMenuType("HOME");

		ProcessResultVO<OrgMenuVO> result = new ProcessResultVO<OrgMenuVO>();
		try {
			orgMenuService.addAuthGrpMenu(vo);
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
	private JsonConfig getMenuDtoFilter() {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
			public boolean apply(Object obj, String name, Object value) {
				if(obj instanceof OrgMenuVO && name.equals("parentMenu"))
					return true;
				return false;
			}
		});

		return jsonConfig;
	}
	
	/**
     * @Method Name : listBbs
     * @Method 설명 : 메뉴에 등록되지 않은 게시판 목록을 가져온다.
	 * @param OrgMenuVO 
	 * @param commandMap
	 * @param model
	 * @return  "/adm/org/menu/list_bbs.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/listBbs")
	public String listBbs(OrgMenuVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);

		BrdBbsInfoVO bbivo = new BrdBbsInfoVO();
		bbivo.setOrgCd(vo.getOrgCd());

		ProcessResultListVO<BrdBbsInfoVO> resultList = brdBbsInfoService.listPageingForMenu(bbivo, vo.getPageIndex(), 5, 5);
		request.setAttribute("bbsInfoList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());
		
		return "org/menu/list_bbs";
	}
	
	/**
     * @Method Name : listPage
     * @Method 설명 : 메뉴에 등록되지 않은 페이지 목록을 가져온다.
	 * @param OrgMenuVO 
	 * @param commandMap
	 * @param model
	 * @return  "/adm/org/menu/list_page.jsp"
	 * @throws Exception
	 */
	@RequestMapping(value="/listPage")
	public String listPage(OrgMenuVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request) throws Exception {
		commonVOProcessing(vo, request);

		OrgPageVO opvo = new OrgPageVO();
		opvo.setOrgCd(vo.getOrgCd());

		ProcessResultListVO<OrgPageVO> resultList = orgPageService.listForMenu(opvo);
		request.setAttribute("pageList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());
		
		return "org/menu/list_page";
	}	
}
