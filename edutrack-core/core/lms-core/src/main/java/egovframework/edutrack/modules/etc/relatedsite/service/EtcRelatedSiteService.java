package egovframework.edutrack.modules.etc.relatedsite.service;

import egovframework.edutrack.comm.service.ProcessResultListVO;

public interface EtcRelatedSiteService {

	/**
	 * 관련사이트 분류 전체 목록을 조회한다.
	 * @param EtcRelatedSiteCtgrVO
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<EtcRelatedSiteCtgrVO> listCtgr(
			EtcRelatedSiteCtgrVO vo) throws Exception;

	/**
	 * 관련사이트 분류 페이징 목록을 조회한다.
	 * @param EtcRelatedSiteCtgrVO
	 * @param pageIndex
	 * @param listScale
	 * @param pageScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<EtcRelatedSiteCtgrVO> listCtgrPageing(
			EtcRelatedSiteCtgrVO vo, int pageIndex, int listScale, int pageScale)
			throws Exception;

	/**
	 * 관련사이트 분류 페이징 목록을 조회한다.
	 * @param EtcRelatedSiteCtgrVO
	 * @param pageIndex
	 * @param listScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<EtcRelatedSiteCtgrVO> listCtgrPageing(
			EtcRelatedSiteCtgrVO vo, int pageIndex, int listScale)
			throws Exception;

	/**
	 * 관련사이트 분류 페이징 목록을 조회한다.
	 * @param EtcRelatedSiteCtgrVO
	 * @param pageIndex
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<EtcRelatedSiteCtgrVO> listCtgrPageing(
			EtcRelatedSiteCtgrVO vo, int pageIndex) throws Exception;

	/**
	 * 관련사이트 분류 상세 정보를 조회한다.
	 * @param EtcRelatedSiteCtgrVO
	 * @return EtcRelatedSiteCtgrVO
	 * @throws Exception
	 */
	public abstract EtcRelatedSiteCtgrVO viewCtgr(EtcRelatedSiteCtgrVO vo)
			throws Exception;

	/**
	 * 관련사이트 분류 정보를 등록한다.
	 * @param EtcRelatedSiteCtgrVO
	 * @return String
	 * @throws Exception
	 */
	public abstract int addCtgr(EtcRelatedSiteCtgrVO vo) throws Exception;

	/**
	 * 관련사이트 분류 정보를 수정한다.
	 * @param EtcRelatedSiteCtgrVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int editCtgr(EtcRelatedSiteCtgrVO vo) throws Exception;

	/**
	 * 관련사이트 분류 정렬 순서를 변경한다.
	 * @param EtcRelatedSiteCtgrVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int sortCrgr(EtcRelatedSiteCtgrVO vo) throws Exception;

	/**
	 * 관련사이트 분류 정보를 삭제 한다.
	 * @param EtcRelatedSiteCtgrVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int removeCtgr(EtcRelatedSiteCtgrVO vo) throws Exception;

	/**
	 * 관련사이트 전체 목록을 조회한다.
	 * @param EtcRelatedSiteVO
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<EtcRelatedSiteVO> listSite(
			EtcRelatedSiteVO vo) throws Exception;

	/**
	 * 관련사이트 페이징 목록을 조회한다.
	 * @param EtcRelatedSiteVO
	 * @param pageIndex
	 * @param listScale
	 * @param pageScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<EtcRelatedSiteVO> listSitePageing(
			EtcRelatedSiteVO vo, int pageIndex, int listScale, int pageScale)
			throws Exception;

	/**
	 * 관련사이트 페이징 목록을 조회한다.
	 * @param EtcRelatedSiteVO
	 * @param pageIndex
	 * @param listScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<EtcRelatedSiteVO> listSitePageing(
			EtcRelatedSiteVO vo, int pageIndex, int listScale) throws Exception;

	/**
	 * 관련사이트 페이징 목록을 조회한다.
	 * @param EtcRelatedSiteVO
	 * @param pageIndex
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<EtcRelatedSiteVO> listSitePageing(
			EtcRelatedSiteVO vo, int pageIndex) throws Exception;

	/**
	 * 관련사이트 상세 정보를 조회한다.
	 * @param EtcRelatedSiteVO
	 * @return EtcRelatedSiteVO
	 * @throws Exception
	 */
	public abstract EtcRelatedSiteVO viewSite(EtcRelatedSiteVO vo)
			throws Exception;

	/**
	 * 관련사이트 정보를 등록한다.
	 * @param EtcRelatedSiteVO
	 * @return String
	 * @throws Exception
	 */
	public abstract int addSite(EtcRelatedSiteVO vo) throws Exception;

	/**
	 * 관련사이트 정보를 수정한다.
	 * @param EtcRelatedSiteVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int editSite(EtcRelatedSiteVO vo) throws Exception;

	/**
	 * 관련사이트 정렬 순서를 변경한다.
	 * @param EtcRelatedSiteVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int sortSite(EtcRelatedSiteVO vo) throws Exception;

	/**
	 * 관련사이트 정보를 삭제 한다.
	 * @param EtcRelatedSiteVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int removeSite(EtcRelatedSiteVO vo) throws Exception;

	/**
	 * 관련사이트 페이징 목록을 조회한다.
	 * @param EtcRelatedSiteCtgrVO
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<EtcRelatedSiteCtgrVO> listSiteAll(
			EtcRelatedSiteCtgrVO vo) throws Exception;

}