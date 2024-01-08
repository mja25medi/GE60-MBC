package egovframework.edutrack.modules.board.popup.service;

import egovframework.edutrack.comm.service.ProcessResultListVO;


public interface BrdPopupNoticeService {

	/**
	 * 팝업 공지사항 페이징 목록을 조회한다.
	 */
	public abstract ProcessResultListVO<BrdPopupNoticeVO> listPageing(
			BrdPopupNoticeVO vo, int pageIndex, int listScale, int pageScale)
			throws Exception;

	/**
	 * 팝업 공지사항 페이징 목록을 조회한다..
	 */
	public abstract ProcessResultListVO<BrdPopupNoticeVO> listPageing(
			BrdPopupNoticeVO vo, int pageIndex, int listScale) throws Exception;

	/**
	 * 팝업 공지사항 페이징 목록을 조회한다.
	 */
	public abstract ProcessResultListVO<BrdPopupNoticeVO> listPageing(
			BrdPopupNoticeVO vo, int pageIndex) throws Exception;

	/**
	 * 팝업 공지사항 페이징 목록을 조회한다.
	 */
	public abstract ProcessResultListVO<BrdPopupNoticeVO> listPopup(
			BrdPopupNoticeVO vo) throws Exception;
	
	/**
	 * 팝업 공지사항 상세정보를 조회한다.
	 */
	public abstract BrdPopupNoticeVO view(BrdPopupNoticeVO vo) throws Exception;

	/**
	 * 팝업 공지사항을 등록한다
	 */
	public abstract String addPopup(BrdPopupNoticeVO vo) throws Exception;

	/**
	 * 팝업 공지사항을 수정한다.
	 */
	public abstract int editPopup(BrdPopupNoticeVO vo) throws Exception;

	/**
	 * 기관 정보 정보를 삭제 한다.
	 * @param UsrDeptInfoVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int remove(BrdPopupNoticeVO vo) throws Exception;

	public abstract int listPopupCnt(BrdPopupNoticeVO popupNoticeVO) throws Exception;

}