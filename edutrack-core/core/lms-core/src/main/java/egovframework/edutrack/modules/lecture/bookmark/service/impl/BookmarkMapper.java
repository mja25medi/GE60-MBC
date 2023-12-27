package egovframework.edutrack.modules.lecture.bookmark.service.impl;

import org.springframework.stereotype.Repository;

import java.util.List;
import egovframework.edutrack.modules.lecture.bookmark.service.BookmarkVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;



@Mapper("bookmarkMapper")
public interface BookmarkMapper {

	/**
	 * 학습자의 학습 목차 리스트를 가져온다.
	 * @param iBookmarkVO
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<BookmarkVO> listBookmark(BookmarkVO iBookmarkVO ) throws Exception;

	/**
	 * 교내 강의 관리용 학습 목차 리스트를 가져온다.
	 * @param iBookmarkVO
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<BookmarkVO> listCntsForClass(BookmarkVO iBookmarkVO ) throws Exception;

	/**
	 * 학습자의 통합 학습 결과를 가져온다.
	 * @param iBookmarkVO
	 * @return
	 */
	public BookmarkVO selectBookmarkStd(BookmarkVO iBookmarkVO )  throws Exception;

	/**
	 * 학습자의 단위 학습 결과를 가져온다.
	 * @param iBookmarkVO
	 * @return
	 */
	public BookmarkVO selectBookmark(BookmarkVO iBookmarkVO) throws Exception;

	/**
	 * 콘텐츠의 정보를 가져온다.
	 * @param iBookmarkVO
	 * @return
	 */
	public BookmarkVO selectContents(BookmarkVO iBookmarkVO) throws Exception;
	
	/**
	 * 콘텐츠의 정보를 가져온다.
	 * @param iBookmarkVO
	 * @return
	 */
	public BookmarkVO selectContentsVer5(BookmarkVO iBookmarkVO) throws Exception;

	/**
	 * 학습 결과를 저장한다.
	 * @param iBookmarkVO
	 * @return
	 */
	public int insertBookmark(BookmarkVO iBookmarkVO)  throws Exception;
	
	/**
	 * 학습 결과를 저장한다.
	 * @param iBookmarkVO
	 * @return
	 */
	public int insertBookmarkDetail(BookmarkVO iBookmarkVO)  throws Exception;

	/**
	 * 학습 결과를 Update한다.
	 * @param iBookmarkVO
	 * @return
	 */
	public int updateBookmark(BookmarkVO iBookmarkVO) throws Exception;
	
	/**
	 * 학습 결과를 Update한다.
	 * @param iBookmarkVO
	 * @return
	 */
	public int updateDetailStudyYn(BookmarkVO iBookmarkVO) throws Exception;

	/**
	 * 학습 횟수를 증가 시킨다.
	 * @param iBookmarkVO
	 * @return
	 */
	public int updateHitsup(BookmarkVO iBookmarkVO)  throws Exception;
	
	/**
	 * 학습 횟수를 증가 시킨다.
	 * @param iBookmarkVO
	 * @return
	 */
	public int updateAttendSts(BookmarkVO vo);

	/**
	 * 학습 결과를 삭제 한다.
	 * @param iBookmarkVO
	 * @return
	 */
	public int deleteBookmark(BookmarkVO iBookmarkVO) throws Exception;

	/**
	 * 학습자 번호로 수강내역 전체 삭제
	 * @param iBookmarkVO
	 * @return
	 */
	public int deleteBookmarkStdNo(BookmarkVO iBookmarkVO)  throws Exception;

	/**
	 * 해당 일자에 수강을 할 수 있는지 결과를 반환한다.
	 * @param iBookmarkVO
	 * @return
	 */
	public BookmarkVO selectTodayStudyYn(BookmarkVO iBookmarkVO) throws Exception;

	/**
	 * 해당 단원의 학습자 진도율 목록을 반환한다.
	 * @param iBookmarkVO
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<BookmarkVO> listStdBookmark(BookmarkVO iBookmarkVO )  throws Exception;

	/**
	 * 과정의 최종 학습일자를 검색한다.
	 * @param iBookmarkVO
	 * @return
	 */
	public BookmarkVO selectLastStudyDttm(BookmarkVO iBookmarkVO) throws Exception;
	
	/**
	 * 과정의 최종 학습일자를 검색한다.
	 * @param iBookmarkVO
	 * @return
	 */
	public BookmarkVO checkDayLimit(BookmarkVO iBookmarkVO) throws Exception;
	
	/**
	 * 과정의 최종 학습일자를 검색한다.
	 * @param iBookmarkVO
	 * @return
	 */
	public BookmarkVO selectSbjBookmarkCnt(BookmarkVO iBookmarkVO) throws Exception;
	
	/**
	 * 진행단계평가 응시 기준조회
	 * @param iBookmarkVO
	 * @return
	 */
	public BookmarkVO selectBookmarkInfo(BookmarkVO iBookmarkVO) throws Exception;
	
	
	/**
	 * 학습 결과를 저장한다.
	 * @param iBookmarkVO
	 * @return
	 */
	public int insertBookmarkLog(BookmarkVO iBookmarkVO)  throws Exception;

	/**
	 * 해당 단원의 학습자 진도율 목록을 반환한다.
	 * @param iBookmarkVO
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<BookmarkVO> listBookmarkLog(BookmarkVO iBookmarkVO )  throws Exception;
}