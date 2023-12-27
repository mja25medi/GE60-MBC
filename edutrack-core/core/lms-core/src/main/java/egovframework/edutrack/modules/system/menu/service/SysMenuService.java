package egovframework.edutrack.modules.system.menu.service;

import java.util.List;

import egovframework.edutrack.comm.service.ProcessResultListVO;

public interface SysMenuService {

	/**
	 *  권한의 전체 목록을 조회한다.
	 * @param SysAuthGrpVO
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<SysAuthGrpVO> listAuthGrp(
			SysAuthGrpVO vo) throws Exception;

	/**
	 * 권한의 상세 정보를 조회한다.
	 * @param SysAuthGrpVO
	 * @return SysAuthGrpVO
	 * @throws Exception
	 */
	public abstract SysAuthGrpVO viewAuthGrp(SysAuthGrpVO vo) throws Exception;

	/**
	 * 권한의 상세 정보를 등록한다.
	 * @param SysAuthGrpVO
	 * @return String
	 * @throws Exception
	 */
	public abstract int addAuthGrp(SysAuthGrpVO vo) throws Exception;

	/**
	 * 권한의 상세 정보를 수정한다.
	 * @param SysAuthGrpVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int editAuthGrp(SysAuthGrpVO vo) throws Exception;

	/**
	 * 권한의 상세 정보를 삭제 한다.
	 * @param SysAuthGrpVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int removeAuthGrp(SysAuthGrpVO vo) throws Exception;

	/**
	 *  메뉴의 전체 목록을 트리 형태로 조회한다.
	 * @param SysMenuVO
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<SysMenuVO> listTreeMenu(SysMenuVO vo)
			throws Exception;

	/**
	 *  메뉴의 전체 목록을 디비에서 조회한다.
	 * @param SysMenuVO
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract List<SysMenuVO> listMenuByDB(SysMenuVO vo) throws Exception;

	/**
	 * 메뉴의 상세 정보를 조회 한다.
	 * @param SysMenuVO
	 * @return SysMenuVO
	 * @throws Exception
	 */
	public abstract SysMenuVO viewMenu(SysMenuVO vo) throws Exception;
	
 	/**
 	 * 권한별 메뉴의 상세 정보를 조회 한다.
 	 * @param SysMenuVO
 	 * @return SysMenuVO
 	 * @throws Exception
 	 */
	public abstract SysMenuVO viewMenuByAuth(SysMenuVO vo) throws Exception;	

	/**
	 * 메뉴의 상세 정보를 등록한다.
	 * @param SysMenuVO
	 * @return String
	 * @throws Exception
	 */
	public abstract String addMenu(SysMenuVO vo) throws Exception;

	/**
	 * 메뉴의 상세 정보를 수정한다.
	 * @param SysMenuVO
	 * @return String
	 * @throws Exception
	 */
	public abstract int editMenu(SysMenuVO vo) throws Exception;

	/**
	 * 메뉴의 순서를 변경한다.
	 * @param SysMenuVO
	 * @return String
	 * @throws Exception
	 */
	public abstract int sortMenu(SysMenuVO vo) throws Exception;

	/**
	 * 메뉴의 상세 정보를 삭제한다.
	 * @param SysMenuVO
	 * @return String
	 * @throws Exception
	 */
	public abstract int removeMenu(SysMenuVO vo) throws Exception;

	/**
	 * 권한 그룹의 메뉴 사용 권한을 저장한다.
	 * @param SysAuthGrpMenuVO
	 * @return String
	 * @throws Exception
	 */
	public abstract int addAuthGroupMenu(SysAuthGrpMenuVO vo) throws Exception;

	/**
	 * 권한별 메뉴를 목록으로 가져온다.
	 * 회원의 메뉴 조회시 사용
	 * @param SysMenuVO
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<SysMenuVO> listUserMenu(
			String menuType, String authGrpCd) throws Exception;
	
	/**
	 * 메뉴 케시 목록에서 메뉴 VO를 가져온다.
	 * @param menuType
	 * @param authGrpCd
	 * @param menuCd
	 * @return
	 */
	public abstract SysMenuVO getMenuByCache(String menuType, 
			String authGrpCd, String menuCd) throws Exception;	

}