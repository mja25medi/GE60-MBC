package egovframework.edutrack.modules.lecture.mybbs.service;

import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.service.ProcessResultListVO;

public interface MyLecBbsAtclService {

	/**
	 * 나의 학습Q&A 페이징 목록을 반환한다.
	 * 
	 * @param MyLecBbsAtclVO
	 * @param curPage
	 * @param listScale
	 * @return 조회결과 목록 VO
	 */
		public abstract ProcessResultListVO<MyLecBbsAtclVO> listPageing(
			MyLecBbsAtclVO vo, int curPage, int listScale, int pageScale) throws Exception;

	/**
	 * 나의 학습Q&A 페이징 목록을 반환한다.
	 * 
	 * @param MyLecBbsAtclVO
	 * @param curPage
	 * @param listScale
	 * @return 조회결과 목록 VO
	 */
		public abstract ProcessResultListVO<MyLecBbsAtclVO> listPageing(
			MyLecBbsAtclVO vo, int curPage, int listScale) throws Exception;

	/**
	 * 나의 학습Q&A 페이징 목록을 반환한다.
	 * 
	 * @param MyLecBbsAtclVO
	 * @param curPage
	 * @return 조회결과 목록 VO
	 */
		public abstract ProcessResultListVO<MyLecBbsAtclVO> listPageing(
			MyLecBbsAtclVO vo, int curPage) throws Exception;

	/**
	 * 게시판 글 조회
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public abstract ProcessResultVO<MyLecBbsAtclVO> view(MyLecBbsAtclVO vo) throws Exception;

}