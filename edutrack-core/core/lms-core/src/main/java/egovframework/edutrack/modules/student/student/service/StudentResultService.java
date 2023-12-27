package egovframework.edutrack.modules.student.student.service;

import org.springframework.transaction.annotation.Transactional;

import egovframework.edutrack.comm.service.ProcessResultListVO;

public interface StudentResultService {

	/**
	 * 수강생 결과 목록
	 *
	 * @return ProcessResultListVO
	 */
	public abstract ProcessResultListVO<StudentResultVO> list(
			StudentResultVO vo) throws Exception;

	/**
	 * 수강생 결과 목록(페이징)
	 *
	 * @return ProcessResultListVO
	 */
	public abstract ProcessResultListVO<StudentResultVO> listPageing(
			StudentResultVO vo, int curPage, int listScale, int pageScale) throws Exception;

	/**
	 * 수강생 결과 목록(페이징)
	 *
	 * @return ProcessResultListVO
	 */
	public abstract ProcessResultListVO<StudentResultVO> listPageing(
			StudentResultVO vo, int curPage, int listScale) throws Exception;

	/**
	 * 수강생 결과 목록(페이징)
	 *
	 * @return ProcessResultListVO
	 */
	public abstract ProcessResultListVO<StudentResultVO> listPageing(
			StudentResultVO vo, int curPage) throws Exception;

}