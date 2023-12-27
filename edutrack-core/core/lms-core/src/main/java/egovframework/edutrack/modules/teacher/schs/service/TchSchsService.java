package egovframework.edutrack.modules.teacher.schs.service;

import org.springframework.transaction.annotation.Transactional;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;

public interface TchSchsService {

	/**
	 * 강사의 학력 목록을 조화하여 반환한다.
	 * @param tchSchsVO
	 * @return
	 */
	public abstract ProcessResultListVO<TchSchsVO> list(
			TchSchsVO tchSchsVO) throws Exception;

	/**
	 * 강사의 학력 페이징 목록을 조화하여 반환한다.
	 * @param tchSchsVO
	 * @return
	 */
	public abstract ProcessResultListVO<TchSchsVO> listPageing(
			TchSchsVO tchSchsVO, int curPage, int listScale,
			int pageScale) throws Exception;

	/**
	 * 강사의 학력 페이징 목록을 조화하여 반환한다.
	 * @param tchSchsVO
	 * @return
	 */
	public abstract ProcessResultListVO<TchSchsVO> listPageing(
			TchSchsVO tchSchsVO, int curPage, int listScale) throws Exception;

	/**
	 * 강사의 학력 페이징 목록을 조화하여 반환한다.
	 * @param tchSchsVO
	 * @return
	 */
	public abstract ProcessResultListVO<TchSchsVO> listPageing(
			TchSchsVO tchSchsVO, int curPage) throws Exception;

	/**
	 * 강사의 학력 단일 항목을 조회하여 반환한다.
	 * @param userNo
	 * @param schsSn
	 * @return
	 */
	public abstract ProcessResultVO<TchSchsVO> view(
			TchSchsVO tchSchsVO) throws Exception;

	/**
	 * 강사의 학력 정보를 등록하고 결과를 반환한다.
	 * @param tchSchsVO
	 * @return
	 */
	public abstract ProcessResultVO<TchSchsVO> add(
			TchSchsVO tchSchsVO) throws Exception;

	/**
	 * 강사의 학력 정보를 수정하고 결과를 반환한다.
	 * @param tchSchsVO
	 * @return
	 */
	public abstract ProcessResultVO<TchSchsVO> edit(
			TchSchsVO tchSchsVO) throws Exception;

	/**
	 * 강사의 학력 정보를 삭제하고 결과를 반환한다.
	 * @param tchSchsVO
	 * @return
	 */
	public abstract ProcessResultVO<TchSchsVO> remove(
			TchSchsVO tchSchsVO) throws Exception;

}