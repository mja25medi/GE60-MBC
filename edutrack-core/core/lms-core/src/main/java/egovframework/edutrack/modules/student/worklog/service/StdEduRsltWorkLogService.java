package egovframework.edutrack.modules.student.worklog.service;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;

public interface StdEduRsltWorkLogService {

	/**
	 * 성적 처리 작업 로그 전체 목록
	 *
	 * @return ProcessResultListVO
	 */
	public abstract ProcessResultListVO<StdEduRsltWorkLogVO> list(
			StdEduRsltWorkLogVO vo) throws Exception;

	/**
	 * 성적 처리 작업 로그 페이징 목록
	 *
	 * @return ProcessResultListVO
	 */
	public abstract ProcessResultListVO<StdEduRsltWorkLogVO> listPageing(
			StdEduRsltWorkLogVO vo, int curPage, int listScale, int pageScale) throws Exception;

	/**
	 * 성적 처리 작업 로그 페이징 목록
	 *
	 * @return ProcessResultListVO
	 */
	public abstract ProcessResultListVO<StdEduRsltWorkLogVO> listPageing(
			StdEduRsltWorkLogVO vo, int curPage, int listScale) throws Exception;

	/**
	 * 성적 처리 작업 로그 페이징 목록
	 *
	 * @return ProcessResultListVO
	 */
	public abstract ProcessResultListVO<StdEduRsltWorkLogVO> listPageing(
			StdEduRsltWorkLogVO vo, int curPage) throws Exception;

	/**
	 * 성적 처리 작업 로그 단일 항목 조회
	 *
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<StdEduRsltWorkLogVO> view(
			StdEduRsltWorkLogVO vo) throws Exception;

	/**
	 * 성적 처리 작업 로그 등록
	 *
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<StdEduRsltWorkLogVO> add(
			StdEduRsltWorkLogVO vo) throws Exception;

}