package egovframework.edutrack.modules.board.faq.service;

import egovframework.edutrack.comm.service.ProcessResultListVO;

public interface BrdFaqService {

	/**
	 *  FAQ 분류 전체 목록을 조회한다.
	 * @param BrdFaqCtgrVO
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<BrdFaqCtgrVO> listCtgr(BrdFaqCtgrVO vo)
			throws Exception;

	/**
	 * FAQ 분류 페이징 목록을 조회한다.
	 * @param BrdFaqCtgrVO
	 * @param pageIndex
	 * @param listScale
	 * @param pageScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<BrdFaqCtgrVO> listCtgrPageing(
			BrdFaqCtgrVO vo, int pageIndex, int listScale, int pageScale)
			throws Exception;

	/**
	 * FAQ 분류 페이징 목록을 조회한다.
	 * @param BrdFaqCtgrVO
	 * @param pageIndex
	 * @param listScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<BrdFaqCtgrVO> listCtgrPageing(
			BrdFaqCtgrVO vo, int pageIndex, int listScale) throws Exception;

	/**
	 * FAQ 분류 페이징 목록을 조회한다.
	 * @param brdFaqCtgrVO
	 * @param pageIndex
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<BrdFaqCtgrVO> listCtgrPageing(
			BrdFaqCtgrVO vo, int pageIndex) throws Exception;

	/**
	 * FAQ 분류 상세 정보를 조회한다.
	 * @param brdFaqCtgrVO
	 * @return brdFaqCtgrVO
	 * @throws Exception
	 */
	public abstract BrdFaqCtgrVO viewCtgr(BrdFaqCtgrVO vo) throws Exception;

	/**
	 * FAQ 분류 정보를 등록한다.
	 * @param brdFaqCtgrVO
	 * @return String
	 * @throws Exception
	 */
	public abstract String addCtgr(BrdFaqCtgrVO vo) throws Exception;

	/**
	 * FAQ 분류 정보를 수정한다.
	 * @param brdFaqCtgrVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int editCtgr(BrdFaqCtgrVO vo) throws Exception;

	/**
	 * FAQ 분류 정보를 삭제 한다.
	 * @param brdFaqCtgrVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int removeCtgr(BrdFaqCtgrVO vo) throws Exception;
	
	/**
	 * FAQ 분류 정보 순서를 변경한다.
	 * @param BrdFaqCtgrVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int sortCtgr(BrdFaqCtgrVO vo) throws Exception;

	/**
	 *  FAQ 게시물 전체 목록을 조회한다.
	 * @param BrdFaqAtclVO
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<BrdFaqAtclVO> listAtcl(BrdFaqAtclVO vo)
			throws Exception;

	/**
	 * FAQ 게시물 페이징 목록을 조회한다.
	 * @param BrdFaqAtclVO
	 * @param pageIndex
	 * @param listScale
	 * @param pageScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<BrdFaqAtclVO> listAtclPageing(
			BrdFaqAtclVO vo, int pageIndex, int listScale, int pageScale)
			throws Exception;

	/**
	 * FAQ 게시물 페이징 목록을 조회한다.
	 * @param BrdFaqAtclVO
	 * @param pageIndex
	 * @param listScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<BrdFaqAtclVO> listAtclPageing(
			BrdFaqAtclVO vo, int pageIndex, int listScale) throws Exception;

	/**
	 * FAQ 게시물 페이징 목록을 조회한다.
	 * @param BrdFaqAtclVO
	 * @param pageIndex
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<BrdFaqAtclVO> listAtclPageing(
			BrdFaqAtclVO vo, int pageIndex) throws Exception;

	/**
	 * FAQ 게시물 상세 정보를 조회한다.
	 * @param BrdFaqAtclVO
	 * @return BrdFaqAtclVO
	 * @throws Exception
	 */
	public abstract BrdFaqAtclVO viewAtcl(BrdFaqAtclVO vo) throws Exception;

	/**
	 * FAQ 게시물 정보를 등록한다.
	 * @param BrdFaqAtclVO
	 * @return String
	 * @throws Exception
	 */
	public abstract String addAtcl(BrdFaqAtclVO vo) throws Exception;

	/**
	 * FAQ 게시물 정보를 수정한다.
	 * @param BrdFaqAtclVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int editAtcl(BrdFaqAtclVO vo) throws Exception;

	/**
	 * FAQ 게시물 순서를 변경한다.
	 * @param BrdFaqAtclVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int sortAtcl(BrdFaqAtclVO vo) throws Exception;

	/**
	 * FAQ 게시물 정보를 삭제 한다.
	 * @param BrdFaqAtclVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int removeAtcl(BrdFaqAtclVO vo) throws Exception;

}