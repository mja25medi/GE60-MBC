package egovframework.edutrack.modules.board.bbs.service;

import egovframework.edutrack.comm.service.ProcessResultListVO;

public interface BrdBbsAtclService {

	/**
	 * 게시판 게시물 전체 목록을 조회한다.
	 * @param BrdBbsAtclVO
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<BrdBbsAtclVO> listAtcl(BrdBbsAtclVO vo)
			throws Exception;

	/**
	 * 게시판 게시물 전체 목록을 조회한다.
	 * @param BrdBbsAtclVO
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<BrdBbsAtclVO> listAtcl(BrdBbsAtclVO vo,
			boolean filein) throws Exception;
	
	/**
	 * 게시판 게시물 상위 목록을 조회한다.
	 * @param BrdBbsAtclVO
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<BrdBbsAtclVO> listTopAtcl(BrdBbsAtclVO vo)
			throws Exception;
	
	/**
	 * 게시판 게시물 상위 목록을 조회한다.
	 * @param BrdBbsAtclVO
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<BrdBbsAtclVO> listTopAtcl(BrdBbsAtclVO vo, 
			boolean filein) throws Exception;	

	/**
	 * 게시판 게시물 페이징 목록을 조회한다.
	 * @param BrdBbsAtclVO
	 * @param pageIndex
	 * @param listScale
	 * @param pageScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<BrdBbsAtclVO> listAtclPageing(
			BrdBbsAtclVO vo, int pageIndex, int listScale, int pageScale,
			boolean filein) throws Exception;

	/**
	 * 게시판 게시물 페이징 목록을 조회한다.
	 * @param BrdBbsAtclVO
	 * @param pageIndex
	 * @param listScale
	 * @param pageScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<BrdBbsAtclVO> listAtclPageing(
			BrdBbsAtclVO vo, int pageIndex, int listScale, int pageScale)
			throws Exception;

	/**
	 * 게시판 게시물 페이징 목록을 조회한다.
	 * @param BrdBbsAtclVO
	 * @param pageIndex
	 * @param listScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<BrdBbsAtclVO> listAtclPageing(
			BrdBbsAtclVO vo, int pageIndex, int listScale) throws Exception;

	/**
	 * 게시판 게시물 페이징 목록을 조회한다.
	 * @param BrdBbsAtclVO
	 * @param pageIndex
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<BrdBbsAtclVO> listAtclPageing(
			BrdBbsAtclVO vo, int pageIndex) throws Exception;

	/**
	 * 게시판 게시물 페이징 목록을 조회한다.
	 * @param BrdBbsAtclVO
	 * @param pageIndex
	 * @param listScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<BrdBbsAtclVO> listAtclPageing(
			BrdBbsAtclVO vo, int pageIndex, int listScale, boolean filein)
			throws Exception;

	/**
	 * 게시판 게시물 페이징 목록을 조회한다.
	 * @param BrdBbsAtclVO
	 * @param pageIndex
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<BrdBbsAtclVO> listAtclPageing(
			BrdBbsAtclVO vo, int pageIndex, boolean filein) throws Exception;

	/**
	 * 게시판 게시물 상세 게시물를 조회한다.
	 * @param BrdBbsAtclVO
	 * @return BrdBbsAtclVO
	 * @throws Exception
	 */
	public abstract BrdBbsAtclVO viewAtcl(BrdBbsAtclVO vo) throws Exception;
	
	/**
	 * 게시판 게시물 상세 게시물를 조회한다.
	 * @param BrdBbsAtclVO
	 * @return BrdBbsAtclVO
	 * @throws Exception
	 */
	public abstract BrdBbsAtclVO viewAtcl(BrdBbsAtclVO vo, boolean hitsup)
			throws Exception;
	
	public abstract BrdBbsAtclVO viewAtclService(BrdBbsAtclVO vo, boolean hitsup)
			throws Exception;
	
	/**
	 * 게시판 게시물 상세 게시물를 조회한다.(이전글, 다음글 포함)
	 * @param BrdBbsAtclVO
	 * @return BrdBbsAtclVO
	 * @throws Exception
	 */
	public abstract BrdBbsAtclVO viewAtclWithPreNext(BrdBbsAtclVO vo, boolean hitsup) 
			throws Exception;

	/**
	 * 게시판 게시물 게시물를 등록한다.
	 * @param BrdBbsAtclVO
	 * @return String
	 * @throws Exception
	 */
	public abstract String addAtcl(BrdBbsAtclVO vo) throws Exception;

	public abstract String addAtclService(BrdBbsAtclVO vo) throws Exception;
	
	/**
	 * 게시판 게시물 게시물를 수정한다.
	 * @param BrdBbsAtclVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int editAtcl(BrdBbsAtclVO vo) throws Exception;

	/**
	 * 게시판 게시물 게시물를 삭제 한다.
	 * @param BrdBbsAtclVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int removeAtcl(BrdBbsAtclVO vo) throws Exception;

	/**
	 *  게시판 글댓 전체 목록을 조회한다.
	 * @param BrdBbsCmntVO
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<BrdBbsCmntVO> listCmnt(BrdBbsCmntVO vo)
			throws Exception;

	/**
	 * 게시판 글댓 페이징 목록을 조회한다.
	 * @param BrdBbsCmntVO
	 * @param pageIndex
	 * @param listScale
	 * @param pageScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<BrdBbsCmntVO> listCmntPageing(
			BrdBbsCmntVO vo, int pageIndex, int listScale, int pageScale)
			throws Exception;

	/**
	 * 게시판 글댓 페이징 목록을 조회한다.
	 * @param BrdBbsCmntVO
	 * @param pageIndex
	 * @param listScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<BrdBbsCmntVO> listCmntPageing(
			BrdBbsCmntVO vo, int pageIndex, int listScale) throws Exception;

	/**
	 * 게시판 글댓 페이징 목록을 조회한다.
	 * @param BrdBbsCmntVO
	 * @param pageIndex
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<BrdBbsCmntVO> listCmntPageing(
			BrdBbsCmntVO vo, int pageIndex) throws Exception;

	/**
	 * 게시판 글댓 상세 글댓를 조회한다.
	 * @param BrdBbsCmntVO
	 * @return BrdBbsCmntVO
	 * @throws Exception
	 */
	public abstract BrdBbsCmntVO viewCmnt(BrdBbsCmntVO vo) throws Exception;

	/**
	 * 게시판 글댓 글댓를 등록한다.
	 * @param BrdBbsCmntVO
	 * @return String
	 * @throws Exception
	 */
	public abstract String addCmnt(BrdBbsCmntVO vo) throws Exception;

	/**
	 * 게시판 글댓 글댓를 수정한다.
	 * @param BrdBbsCmntVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int editCmnt(BrdBbsCmntVO vo) throws Exception;

	/**
	 * 게시판 글댓 글댓를 삭제 한다.
	 * @param BrdBbsCmntVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int removeCmnt(BrdBbsCmntVO vo) throws Exception;

	public abstract BrdBbsAtclVO checkPassword(BrdBbsAtclVO vo) throws Exception;

}