package egovframework.edutrack.modules.org.menu.service;

import egovframework.edutrack.comm.service.ProcessResultListVO;

public interface OrgMenuService {

	/**
	 *  메뉴의 전체 목록을 트리 형태로 조회한다.
	 * @param OrgMenuVO
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<OrgMenuVO> listTreeMenu(OrgMenuVO vo)
			throws Exception;

	/**
	 * 기관의 권한 메뉴 조회
	 * 하위 메뉴를 포함한 최상위 메뉴 DTO를 반환함.
	 * @param dto.orgCd : 기관 코드 (필수)
	 * @param dto.menuType : 메뉴 유형 (필수)
	 * @return ProcessResultListDTO
	 */
	public abstract OrgMenuVO listAuthGrpTreeMenu(OrgMenuVO vo)
			throws Exception;

	/**
	 * 주어진 MenuDTO에서 menuCd에 해당되는 메뉴를 찾아서 반환한다.
	 * @param rootMenu 찾고자 하는 대상 루트메뉴
	 * @param menuCd 찾을 MenuCd
	 * @return 찾아진 MenuDTO, 없을 경우 null 반환.
	 */
	public abstract OrgMenuVO findMenuByMenuCd(OrgMenuVO rootMenu, String menuCd);

	/**
	 * 기관 메뉴의 단일 레코드를 반환한다.
	 * @param dto.orgCd : 기관 코드 (필수)
	 * @param dto.menuType : 메뉴 유형 (필수)
	 * @param dto.menuCd : 메뉴 코드 (필수)
	 * @return ProcessResultListDTO
	 */
	public abstract OrgMenuVO viewMenu(OrgMenuVO vo) throws Exception;

	/**
	 * 기관의 메뉴를 등록한다.
	 * @param OrgMenuVO
	 * @return String
	 * @throws Exception
	 */
	public abstract int addMenu(OrgMenuVO vo) throws Exception;

	/**
	 * 기관의 메뉴를 수정한다.
	 * @param OrgMenuVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int editMenu(OrgMenuVO vo) throws Exception;

	/**
	 * 기관의 메뉴를 삭제한다.
	 * @param OrgMenuVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int removeMenu(OrgMenuVO vo) throws Exception;

	/**
	 * 기관의 메뉴의 순서를 변경한다.
	 * @param OrgMenuVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int moveMenu(OrgMenuVO vo, String moveType)
			throws Exception;
	
	/**
	 * 기관의 메뉴의 순서를 변경한다.
	 * @param OrgMenuVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int sortMenu(OrgMenuVO vo)
			throws Exception;

	/**
	 * 기관의 메뉴를 초기화 한다.
	 * @param OrgMenuVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int initMenu(OrgMenuVO vo) throws Exception;

	/**
	 * 기관의 권한 메뉴를 등록 한다.
	 * @param OrgAuthGrpMenuVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int addAuthGrpMenu(OrgAuthGrpMenuVO vo) throws Exception;

	/**
	 * 기관 메뉴의 권한 정보 조회
	 * @param OrgAuthGrpMenuVO
	 * @return int
	 * @throws Exception
	 */
	public abstract OrgMenuVO viewAuthorizeByMenu(OrgMenuVO vo)
			throws Exception;

	public abstract OrgMenuVO viewAuthorizeByMenu2(OrgMenuVO vo)
			throws Exception;
}