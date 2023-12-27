package egovframework.edutrack.modules.board.bbs.service;

import egovframework.edutrack.comm.service.ProcessResultListVO;

public interface BrdBbsHeadService {

	/**
	 *  게시판 머릿말 전체 목록을 조회한다.
	 * @param BrdBbsHeadVO
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<BrdBbsHeadVO> list(BrdBbsHeadVO vo)
			throws Exception;

	/**
	 * 게시판 머릿말 페이징 목록을 조회한다.
	 * @param BrdBbsHeadVO
	 * @param pageIndex
	 * @param listScale
	 * @param pageScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<BrdBbsHeadVO> listPageing(
			BrdBbsHeadVO vo, int pageIndex, int listScale, int pageScale)
			throws Exception;

	/**
	 * 게시판 머릿말 페이징 목록을 조회한다.
	 * @param BrdBbsHeadVO
	 * @param pageIndex
	 * @param listScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<BrdBbsHeadVO> listPageing(
			BrdBbsHeadVO vo, int pageIndex, int listScale) throws Exception;

	/**
	 * 게시판 머릿말 페이징 목록을 조회한다.
	 * @param BrdBbsHeadVO
	 * @param pageIndex
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<BrdBbsHeadVO> listPageing(
			BrdBbsHeadVO vo, int pageIndex) throws Exception;

	/**
	 * 게시판 머릿말 상세 정보를 조회한다.
	 * @param BrdBbsHeadVO
	 * @return BrdBbsHeadVO
	 * @throws Exception
	 */
	public abstract BrdBbsHeadVO view(BrdBbsHeadVO vo) throws Exception;

	/**
	 * 게시판 머릿말 정보를 등록한다.
	 * @param BrdBbsHeadVO
	 * @return String
	 * @throws Exception
	 */
	public abstract String add(BrdBbsHeadVO vo) throws Exception;

	/**
	 * 게시판 머릿말 정보를 수정한다.
	 * @param BrdBbsHeadVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int edit(BrdBbsHeadVO vo) throws Exception;

	/**
	 * 게시판 머릿말 정보를 삭제 한다.
	 * @param BrdBbsHeadVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int remove(BrdBbsHeadVO vo) throws Exception;

}