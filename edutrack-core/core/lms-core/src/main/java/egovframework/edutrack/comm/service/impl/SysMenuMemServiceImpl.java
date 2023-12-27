package egovframework.edutrack.comm.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.exception.AccessDeniedException;
import egovframework.edutrack.comm.service.SysMenuMemService;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.modules.log.menuconn.service.LogWwwMenuConnLogService;
import egovframework.edutrack.modules.log.menuconn.service.LogWwwMenuConnLogVO;
import egovframework.edutrack.modules.org.menu.service.OrgMenuLangVO;
import egovframework.edutrack.modules.org.menu.service.OrgMenuService;
import egovframework.edutrack.modules.org.menu.service.OrgMenuVO;
import egovframework.edutrack.modules.system.menu.service.SysMenuService;
import egovframework.edutrack.modules.system.menu.service.SysMenuVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 *  <b>공통 - 공통 메뉴 메모리 서비스</b> 영역  Service 클래스
 * @author Jamfam
 *
 */
@Service("sysMenuMemService")
public class SysMenuMemServiceImpl 
	extends EgovAbstractServiceImpl implements SysMenuMemService {

	protected final Log log = LogFactory.getLog(getClass());

	@Resource(name="sysMenuService")
	private SysMenuService 				sysMenuService;

	@Resource(name="orgMenuService")
	private OrgMenuService 				orgMenuService;

	@Resource(name="logWwwMenuConnLogService")
	private LogWwwMenuConnLogService	logWwwMenuConnLogService;

	/**
	 * 메뉴 리스트를 반환한다.
	 *
	 * @param authGrpCd
	 * @return
	 */
	@Override
	public synchronized List<SysMenuVO> getAdmMenuList(String authGrpCd) throws Exception {
		String menuType = "ADMIN";
		return sysMenuService.listUserMenu(menuType, authGrpCd).getReturnList();
	}

	/**
	 * 메뉴 리스트를 반환한다.
	 *
	 * @param authGrpCd
	 * @return
	 */
	@Override
	public synchronized List<SysMenuVO> getMngMenuList(String authGrpCd) throws Exception {
		String menuType = "MANAGE";
		return sysMenuService.listUserMenu(menuType, authGrpCd).getReturnList();
	}

	/**
	 * 메뉴 리스트를 반환한다.
	 *
	 * @param authGrpCd
	 * @return
	 */
	@Override
	public synchronized List<SysMenuVO> getHomeMenuList(String authGrpCd) throws Exception {
		String menuType = "HOME";
		return sysMenuService.listUserMenu(menuType, authGrpCd).getReturnList();
	}

	/**
	 * 메뉴 리스트를 반환한다.
	 *
	 * @param authGrpCd
	 * @return
	 */
	@Override
	public synchronized OrgMenuVO getOrgHomeMenuList(String orgCd, String authGrpCd) throws Exception {
		OrgMenuVO omvo = new OrgMenuVO();
		omvo.setOrgCd(orgCd);
		omvo.setMenuType("HOME");
		omvo.setAuthGrpCd(authGrpCd);
		return orgMenuService.listAuthGrpTreeMenu(omvo);
	}

	/**
	 * 메뉴 리스트를 반환한다.
	 *
	 * @param authGrpCd
	 * @return
	 */
	@Override
	public synchronized List<SysMenuVO> getLecMenuList(String authGrpCd) throws Exception {
		String menuType = "LECT";
		return sysMenuService.listUserMenu(menuType, authGrpCd).getReturnList();
	}


	/**
	 * 메뉴코드, 사용자 유형 정보를 이용해서 최종적으로 표시할 메뉴정보를 결정하고,
	 * 이 정보를 세션에 기록한다.
	 * @param mcd 요청메뉴코드
	 * @param userType 사용자 유형
	 * @param request 메뉴정보를 기록할 세션을 담은 request
	 * @return
	 */
	@Override
	public OrgMenuVO decideHomeMenuWithSession(String mcd, HttpServletRequest request) throws Exception {

		//-- 사용자 권한을 가져온다. 권한이 없는 경우 GUEST 권한
		String authGrpCd = StringUtil.nvl(UserBroker.getUserType(request),"GUEST");
		String orgCd = UserBroker.getUserOrgCd(request);

		//-- 권한별 메뉴 목록 검색
		OrgMenuVO rootMenu = new OrgMenuVO();
		rootMenu.setOrgCd(orgCd);
		rootMenu.setMenuType("HOME");
		rootMenu.setAuthGrpCd(authGrpCd);
		rootMenu.setMenuCd(""); //-- 전체 메뉴 가져와 ...
		rootMenu = orgMenuService.listAuthGrpTreeMenu(rootMenu);

		OrgMenuVO targetMenu = orgMenuService.findMenuByMenuCd(rootMenu, mcd);

		//-- 만약 authGrpCd에 유효하지 않은 mcd가 들어왔다면 권한이 없으므로 null이 나온다.
		if(targetMenu == null)
			throw new AccessDeniedException("해당 페이지에 대한 접근 권한이 없습니다.");

		//-- 유효한(URL 정보가 있는) 최초의 메뉴를 찾는다.
		targetMenu = this.searchCorrectUrlMenu(targetMenu);

		//-- 메뉴경로를 가져와 최상위 메뉴를 구한다.
		String rootMenuCd = targetMenu.searchParentLvl(1).getMenuCd(); // menuPath[1];
		String rootMenuNm = targetMenu.searchParentLvl(1).getMenuNm(); // menuPath[1];
		//String rootMenuGrp = StringUtil.nvl(rootSysMenuVO.searchParentLvl(1).getMenuTitle(),"01"); // 기무사프로젝트용으로 추가함.

		// 메뉴별 접속 로그는 다시 생각해 보자..
		try{
			String logMenuCd  = targetMenu.searchParentLvl(2).getMenuCd(); // menuPath[1];

			//-- 메뉴 접속 로그 저장
			LogWwwMenuConnLogVO lwmclvo = new LogWwwMenuConnLogVO();
			lwmclvo.setMenuCd(logMenuCd);
			lwmclvo.setOrgCd(orgCd);
			logWwwMenuConnLogService.add(lwmclvo);
		} catch (Exception e){
			//-- 1단계 메뉴만 있는 경우 logMenu 못 구함.
		}

		//-- 언에 세팅에 따라... MENU 명 설정
		String locale = UserBroker.getLocaleKey(request);
		String curMenuName = targetMenu.getMenuNm();
		for(OrgMenuLangVO omldto : targetMenu.getMenuLangList()) {
			if(locale.equals(omldto.getLangCd())) curMenuName = omldto.getMenuNm();
		}

		String curMenuLocation = "";
		String[] menuCds = StringUtil.split(targetMenu.getMenuPath(),"|");
		for(int i=1; i < menuCds.length; i++) {
			OrgMenuVO omudto = orgMenuService.findMenuByMenuCd(rootMenu, menuCds[i]);
			if(i > 1) curMenuLocation += " > ";
			for(OrgMenuLangVO omldto : omudto.getMenuLangList()) {
				if(locale.equals(omldto.getLangCd())) curMenuLocation += omldto.getMenuNm();
			}
		}

/*
		for(int i=1; i < menuCds.length; i++) {
			OrgMenuVO omudto = orgMenuService.findMenuByMenuCd(rootMenu, menuCds[i]);
			String menuNm = "";
			
			for(OrgMenuLangVO omldto : omudto.getMenuLangList()) {
				if(locale.equals(omldto.getLangCd())) menuNm = omldto.getMenuNm();
			}
			
			if ((i+1) == menuCds.length) curMenuLocation += "<li class=\"on\">" + menuNm + "</li>";
			else curMenuLocation += "<li><a href=\"#\">" + menuNm + "</a></li>";
		}
*/
		// 세션에 메뉴정보를 기록
		request.getSession().setAttribute(Constants.MENU_LOCATION, curMenuLocation);
		request.getSession().setAttribute(Constants.CUR_MENU_NAME, curMenuName);
		request.getSession().setAttribute(Constants.CUR_MENU_CODE, targetMenu.getMenuCd());
		request.getSession().setAttribute(Constants.CUR_MENU_TITLE, targetMenu.getMenuTitle());
		request.getSession().setAttribute(Constants.CUR_MENU_CHRG_DEPT, targetMenu.getChrgPrsnInfo1());
		request.getSession().setAttribute(Constants.CUR_MENU_CHRG_NAME, targetMenu.getChrgPrsnInfo2());
		request.getSession().setAttribute(Constants.CUR_MENU_CHRG_PHONE, targetMenu.getChrgPrsnInfo3());
		request.getSession().setAttribute(Constants.ROT_MENU_CODE, rootMenuCd);
		request.getSession().setAttribute(Constants.ROT_MENU_NAME, rootMenuNm);
		//request.getSession().setAttribute(Constants.ROT_MENU_GROUP, rootMenuGrp);
		return targetMenu;
	}

	private OrgMenuVO searchCorrectUrlMenu(OrgMenuVO vo) {
		if(StringUtil.isNotNull(vo.getMenuUrl())) {
			return vo;
		} else if (!vo.getSubList().isEmpty()) {
			return searchCorrectUrlMenu(vo.getSubList().get(0));
		} else {
			// 잘못된 메뉴 구성이다...
			return null;
		}
	}
}
