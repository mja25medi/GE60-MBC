package egovframework.edutrack.comm.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import egovframework.edutrack.modules.org.menu.service.OrgMenuVO;
import egovframework.edutrack.modules.system.menu.service.SysMenuVO;

public interface SysMenuMemService {

	/**
	 * 메뉴 리스트를 반환한다.
	 *
	 * @param authGrpCd
	 * @return
	 */
	public List<SysMenuVO> getAdmMenuList(String authGrpCd)
			throws Exception;

	/**
	 * 메뉴 리스트를 반환한다.
	 *
	 * @param authGrpCd
	 * @return
	 */
	public List<SysMenuVO> getMngMenuList(String authGrpCd)
			throws Exception;

	/**
	 * 메뉴 리스트를 반환한다.
	 *
	 * @param authGrpCd
	 * @return
	 */
	public List<SysMenuVO> getHomeMenuList(String authGrpCd)
			throws Exception;

	/**
	 * 메뉴 리스트를 반환한다.
	 *
	 * @param authGrpCd
	 * @return
	 */
	public OrgMenuVO getOrgHomeMenuList(String orgCd, String authGrpCd)
			throws Exception;

	/**
	 * 메뉴 리스트를 반환한다.
	 *
	 * @param authGrpCd
	 * @return
	 */
	public List<SysMenuVO> getLecMenuList(String authGrpCd)
			throws Exception;

	/**
	 * 메뉴코드, 사용자 유형 정보를 이용해서 최종적으로 표시할 메뉴정보를 결정하고,
	 * 이 정보를 세션에 기록한다.
	 * @param mcd 요청메뉴코드
	 * @param userType 사용자 유형
	 * @param request 메뉴정보를 기록할 세션을 담은 request
	 * @return
	 */
	public OrgMenuVO decideHomeMenuWithSession(String mcd,
			HttpServletRequest request) throws Exception;

}