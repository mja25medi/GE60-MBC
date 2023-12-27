package egovframework.edutrack.modules.board.bbs.service;

import egovframework.edutrack.comm.service.ProcessResultListVO;

public interface BrdBbsInfoService {

	/**
	 *  게시판 정보 전체 목록을 조회한다.
	 * @param BrdBbsInfoVO
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<BrdBbsInfoVO> list(BrdBbsInfoVO vo)
			throws Exception;

	/**
	 * 게시판 정보 페이징 목록을 조회한다.
	 * @param BrdBbsInfoVO
	 * @param pageIndex
	 * @param listScale
	 * @param pageScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<BrdBbsInfoVO> listPageing(
			BrdBbsInfoVO vo, int pageIndex, int listScale, int pageScale)
			throws Exception;

	/**
	 * 게시판 정보 페이징 목록을 조회한다.
	 * @param BrdBbsInfoVO
	 * @param pageIndex
	 * @param listScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<BrdBbsInfoVO> listPageing(
			BrdBbsInfoVO vo, int pageIndex, int listScale) throws Exception;

	/**
	 * 게시판 정보 페이징 목록을 조회한다.
	 * @param BrdBbsInfoVO
	 * @param pageIndex
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<BrdBbsInfoVO> listPageing(
			BrdBbsInfoVO vo, int pageIndex) throws Exception;

	/**
	 * 게시판 정보 상세 정보를 조회한다.
	 * @param BrdBbsInfoVO
	 * @return BrdBbsInfoVO
	 * @throws Exception
	 */
	public abstract BrdBbsInfoVO view(BrdBbsInfoVO vo) throws Exception;

	/**
	 * 게시판 정보 정보를 등록한다.
	 * @param BrdBbsInfoVO
	 * @return String
	 * @throws Exception
	 */
	public abstract String add(BrdBbsInfoVO vo) throws Exception;

	/**
	 * 게시판 정보 정보를 수정한다.
	 * @param BrdBbsInfoVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int edit(BrdBbsInfoVO vo) throws Exception;

	/**
	 * 게시판 정보 정보를 삭제 한다.
	 * @param BrdBbsInfoVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int remove(BrdBbsInfoVO vo) throws Exception;
	
    /**
	 * 메뉴 관리용 메뉴에 등록되지 않은 게시판 정보 페이징 목록을 조회한다.
	 * @param BrdBbsInfoVO
	 * @param pageIndex
	 * @param listScale
	 * @param pageScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<BrdBbsInfoVO> listPageingForMenu(BrdBbsInfoVO vo, 
			int pageIndex, int listScale, int pageScale) throws Exception;
	
	/**
	 * 게시판과 메뉴를 연결 한다.
	 * @param BrdBbsInfoVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int editMenuCd(BrdBbsInfoVO vo) throws Exception;
	
	/**
	 * 게시판과 메뉴를 연결을 헤제 한다.
	 * @param BrdBbsInfoVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int editMenuCdToNull(BrdBbsInfoVO vo) throws Exception;
	
	/**
	 * Default가 아닌 게시판과 메뉴를 연결을 헤제 한다.
	 * @param BrdBbsInfoVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int editAllMenuCdToNull(BrdBbsInfoVO vo) throws Exception;

}