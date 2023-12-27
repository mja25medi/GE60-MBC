package egovframework.edutrack.modules.teacher.writings.service;

import org.springframework.transaction.annotation.Transactional;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;

public interface TchWritingsService {

	/**
	 * 강사의 강의 저서 페이지 목록을 반환한다.
	 * @param teacherWritingsVO
	 * @return
	 */
	public abstract ProcessResultListVO<TchWritingsVO> list(
			TchWritingsVO teacherWritingsVO) throws Exception;

	/**
	 * 강사의 강의 저서 페이지 목록을 반환한다.
	 * @param teacherWritingsVO
	 * @return
	 */
	public abstract ProcessResultListVO<TchWritingsVO> listPageing(
			TchWritingsVO teacherWritingsVO, int curPage, int listScale,
			int pageScale) throws Exception;

	/**
	 * 강사의 강의 저서 페이지 목록을 반환한다.
	 * @param teacherWritingsVO
	 * @return
	 */
	public abstract ProcessResultListVO<TchWritingsVO> listPageing(
			TchWritingsVO teacherWritingsVO, int curPage, int listScale) throws Exception;

	/**
	 * 강사의 강의 저서 페이지 목록을 반환한다.
	 * @param teacherWritingsVO
	 * @return
	 */
	public abstract ProcessResultListVO<TchWritingsVO> listPageing(
			TchWritingsVO teacherWritingsVO, int curPage) throws Exception;

	/**
	 * 강사의 강의 저서 단일 항목을 반환한다.
	 * @return  ProcessResultVO
	 * @return
	 */
	public abstract ProcessResultVO<TchWritingsVO> view(
			TchWritingsVO teacherWritingsVO) throws Exception;

	/**
	 * 강사의 강의 저서 정보를 등록하고 결과를 반환한다.
	 * @param teacherWritingsVO
	 * @return
	 */
	public abstract ProcessResultVO<TchWritingsVO> add(
			TchWritingsVO teacherWritingsVO) throws Exception;

	/**
	 * 강사의 강의 저서 정보를 수정하고 결과를 반환한다. 
	 * @param teacherWritingsVO
	 * @return
	 */
	public abstract ProcessResultVO<TchWritingsVO> edit(
			TchWritingsVO teacherWritingsVO) throws Exception;

	/**
	 * 강사의 강의 저서 정보를 삭제하고 결과를 반환한다.	
	 * @param teacherWritingsVO
	 * @return
	 */
	public abstract ProcessResultVO<TchWritingsVO> remove(
			TchWritingsVO teacherWritingsVO) throws Exception;

}