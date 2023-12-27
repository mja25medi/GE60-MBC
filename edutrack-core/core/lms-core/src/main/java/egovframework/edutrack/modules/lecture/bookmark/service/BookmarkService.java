package egovframework.edutrack.modules.lecture.bookmark.service;

import egovframework.edutrack.comm.service.ProcessResultVO;

import java.util.List;

import egovframework.edutrack.comm.annotation.HrdApiScore;
import egovframework.edutrack.comm.service.ProcessResultListVO;

public interface BookmarkService {

	/**
	 * 교재 목록 조회 (트리형태의 VO 반환)
	 * @param sbjCd
	 * @param parUnitCd
	 * @return
	 */
	public abstract ProcessResultListVO<BookmarkVO> listBookmark(
			BookmarkVO iBookmarkVO) throws Exception;

	/**
	 * 교재 목록 조회 (트리형태의 VO 반환)
	 * @param sbjCd
	 * @param parUnitCd
	 * @return
	 */
	public abstract ProcessResultVO<BookmarkVO> listBookmarkTreeVO(
			BookmarkVO iBookmarkVO) throws Exception;

	/**
	 * 교내 학습 관리용 교재 목록 조회 (트리형태의 VO 반환)
	 * @param sbjCd
	 * @param parUnitCd
	 * @return
	 */
	public abstract ProcessResultListVO<BookmarkVO> listCntsForClass(
			BookmarkVO iBookmarkVO) throws Exception;

	/**
	 * 교재 목록 조회 (트리형태의 VO 반환)
	 * @param sbjCd
	 * @param parUnitCd
	 * @return
	 */
	public abstract ProcessResultListVO<BookmarkVO> listBookmarkSub(
			BookmarkVO iBookmarkVO, String rootUnitCd) throws Exception;


	public abstract ProcessResultVO<BookmarkVO> viewBookmark(
			BookmarkVO iBookmarkVO) throws Exception;
	
	//@HrdApiScore(category = Category.BOOKMARK, saveType = SaveType.START)
	@HrdApiScore
	public abstract ProcessResultVO<BookmarkVO> viewBookmarkHrdLog(BookmarkVO iBookmarkVO) throws Exception;
	

	public abstract ProcessResultVO<BookmarkVO> viewContents(
			BookmarkVO iBookmarkVO) throws Exception;

	public abstract ProcessResultVO<BookmarkVO> addBookmark(
			BookmarkVO iBookmarkVO) throws Exception;
	
	public abstract ProcessResultVO<BookmarkVO> addBookmarkDetail(
			BookmarkVO iBookmarkVO) throws Exception;

	//@HrdApiScore(category = Category.BOOKMARK, saveType = SaveType.END)
	public abstract ProcessResultVO<BookmarkVO> editBookmark(
			BookmarkVO iBookmarkVO) throws Exception;
	
	public abstract ProcessResultVO<BookmarkVO> editDetailStudyYn(
			BookmarkVO iBookmarkVO) throws Exception;

	public abstract ProcessResultVO<BookmarkVO> hitsupBookmark(
			BookmarkVO iBookmarkVO) throws Exception;

	public abstract ProcessResultVO<BookmarkVO> deleteBookmark(
			BookmarkVO iBookmarkVO) throws Exception;

	public abstract ProcessResultVO<BookmarkVO> viewBookmarkStd(
			BookmarkVO iBookmarkVO ) throws Exception;

	public abstract ProcessResultVO<BookmarkVO> viewTodayStudyYn(
			BookmarkVO iBookmarkVO) throws Exception;

	/**
	 * 단원별 학습자의 진도율 목록을 반환한다.
	 * @param sbjCd
	 * @param parUnitCd
	 * @return
	 */
	public ProcessResultListVO<BookmarkVO> listStdBookmark(BookmarkVO iBookmarkVO) throws Exception;

	/**
	 * 교내학습관리 수강승인용
	 * @param iBookmarkVO
	 * @return
	 */
	//public	ProcessResultVO<BookmarkVO> addClassBookmark(BookmarkVO iBookmarkVO) throws Exception;

	/**
	 * 교내학습관리 승인취소용
	 * @param iBookmarkVO
	 * @return
	 */
	//public	ProcessResultVO<BookmarkVO> delClassBookmark(BookmarkVO iBookmarkVO) throws Exception;
	/**
	 * 교재 목록 조회 (트리형태의 VO 반환)
	 * @param sbjCd
	 * @param parUnitCd
	 * @return
	 */
	public ProcessResultListVO<BookmarkVO> openCourseListBookmark(BookmarkVO iBookmarkVO) throws Exception ;

	/**
	 * 학습자의 과정 최종 학습일자를 조회한다.
	 * @param iBookmarkVO
	 * @return
	 */
	public abstract ProcessResultVO<BookmarkVO> selectLastStudyDttm(BookmarkVO iBookmarkVO) throws Exception ;
	
	/**
	 * 학습자의 과정 최종 학습일자를 조회한다.
	 * @param iBookmarkVO
	 * @return
	 */
	public abstract ProcessResultVO<BookmarkVO> checkDayLimit(BookmarkVO iBookmarkVO) throws Exception ;
	
	/**
	 * 학습자의 과정 최종 학습일자를 조회한다.
	 * @param iBookmarkVO
	 * @return
	 */
	public abstract ProcessResultVO<BookmarkVO> selectSbjBookmarkCnt(BookmarkVO iBookmarkVO) throws Exception ;
	
	/**
	 * 학습자의 진행단계평가 정보 및 과정 학습정보를 조회한다.
	 * @param iBookmarkVO
	 * @return
	 */
	public abstract ProcessResultVO<BookmarkVO> selectBookmarkInfo(BookmarkVO iBookmarkVO) throws Exception;
	
	public abstract ProcessResultVO<BookmarkVO> addBookmarkLog(BookmarkVO iBookmarkVO) throws Exception;
	
	public int updateAttendStsList(List<BookmarkVO> list) throws Exception;

	public int updateAttendSts(BookmarkVO vo) throws Exception;
	/**
	 * 단원별 학습자의 진도율 목록을 반환한다.
	 * @param sbjCd
	 * @param parUnitCd
	 * @return
	 */
	public ProcessResultListVO<BookmarkVO> listBookmarkLog(BookmarkVO iBookmarkVO) throws Exception;
}