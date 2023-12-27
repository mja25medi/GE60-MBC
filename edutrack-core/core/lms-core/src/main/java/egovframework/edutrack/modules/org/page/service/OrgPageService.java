package egovframework.edutrack.modules.org.page.service;

import egovframework.edutrack.comm.service.ProcessResultListVO;

public interface OrgPageService {

	/**
	 *  기관 페이지 전체 목록을 조회한다.
	 * @param OrgPageVO
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<OrgPageVO> list(OrgPageVO vo)
			throws Exception;

    /**
	 *  메뉴에 연결 되지 않은 기관 페이지 전체 목록을 조회한다.
	 * @param OrgPageVO
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<OrgPageVO> listForMenu(OrgPageVO vo)
			throws Exception;	
	
	/**
	 * 기관 페이지 페이징 목록을 조회한다.
	 * @param OrgPageVO
	 * @param pageIndex
	 * @param listScale
	 * @param pageScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<OrgPageVO> listPageing(OrgPageVO vo,
			int pageIndex, int listScale, int pageScale) throws Exception;

	/**
	 * 기관 페이지 페이징 목록을 조회한다.
	 * @param OrgPageVO
	 * @param pageIndex
	 * @param listScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<OrgPageVO> listPageing(OrgPageVO vo,
			int pageIndex, int listScale) throws Exception;

	/**
	 * 기관 페이지 페이징 목록을 조회한다.
	 * @param OrgPageVO
	 * @param pageIndex
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<OrgPageVO> listPageing(OrgPageVO vo,
			int pageIndex) throws Exception;

	/**
	 * 기관 페이지 상세 정보를 조회한다.
	 * @param OrgPageVO
	 * @return OrgPageVO
	 * @throws Exception
	 */
	public abstract OrgPageVO view(OrgPageVO vo) throws Exception;

	/**
	 * 기관 페이지 정보를 등록한다.
	 * @param OrgPageVO
	 * @return String
	 * @throws Exception
	 */
	public abstract int add(OrgPageVO vo) throws Exception;

	/**
	 * 기관 페이지 정보를 수정한다.
	 * @param OrgPageVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int edit(OrgPageVO vo) throws Exception;

	/**
	 * 기관 페이지 정보를 삭제 한다.
	 * @param OrgPageVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int remove(OrgPageVO vo) throws Exception;

	/**
	 * 기관의 페이지 정보 전체를 삭제 한다.
	 * @param OrgPageVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int removeAll(OrgPageVO vo) throws Exception;

	/**
	 * 기관 페이지와 메뉴를 연결한다.
	 * @param OrgPageVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int editMenuCd(OrgPageVO vo) throws Exception;

	/**
	 * 기관 페이지와 메뉴의 연결을 해제 한다.
	 * @param OrgPageVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int editMenuCdToNull(OrgPageVO vo) throws Exception;

	/**
	 * Default가 아닌 모든 페이지와 메뉴의 연결을 해제 한다.
	 * @param OrgPageVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int editAllMenuCdToNull(OrgPageVO vo) throws Exception;

}