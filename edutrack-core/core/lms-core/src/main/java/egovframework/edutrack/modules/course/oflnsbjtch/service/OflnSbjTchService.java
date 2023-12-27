package egovframework.edutrack.modules.course.oflnsbjtch.service;

import org.springframework.transaction.annotation.Transactional;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;

public interface OflnSbjTchService {

	/**
	 * 오프라인 과목 강사의 목록을 조회하여 반환한다.
	 * @param OflnSbjTchVO
	 * @return ProcessResultListVO<OflnSbjTchVO>
	 */
	public abstract ProcessResultListVO<OflnSbjTchVO> list(OflnSbjTchVO VO) throws Exception;

	/**
	 * 오프라인 과목 강사의 페이징 목록을 조회하여 반환한다.
	 * @param OflnSbjTchVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return ProcessResultListVO<OflnSbjTchVO>
	 */
	public abstract ProcessResultListVO<OflnSbjTchVO> listPageing(
			OflnSbjTchVO VO, int curPage, int listScale, int pageScale) throws Exception;

	/**
	 * 오프라인 과목 강사의 페이징 목록을 조회하여 반환한다.
	 * @param OflnSbjTchVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @return ProcessResultListVO<OflnSbjTchVO>
	 */
	public abstract ProcessResultListVO<OflnSbjTchVO> listPageing(
			OflnSbjTchVO VO, int curPage, int listScale) throws Exception;

	/**
	 * 오프라인 과목 강사의 페이징 목록을 조회하여 반환한다.
	 * @param OflnSbjTchVO
	 * @param curPage 현재 페이지
	 * @return ProcessResultListVO<OflnSbjTchVO>
	 */
	public abstract ProcessResultListVO<OflnSbjTchVO> listPageing(
			OflnSbjTchVO VO, int curPage) throws Exception;

	/**
	 * 오프라인 과목 강사의 단일 레코드를 조회하여 반환한다.
	 * @param OflnSbjTchVO.rateSn
	 * @return ProcessResultVO<OflnSbjTchVO>
	 */
	public abstract ProcessResultVO<OflnSbjTchVO> view(OflnSbjTchVO VO) throws Exception;

	/**
	 * 오프라인 과목 강사를 등록하고 결과를 반환한다.
	 * @param OflnSbjTchVO
	 * @return ProcessResultVO<OflnSbjTchVO>
	 */
	public abstract ProcessResultVO<OflnSbjTchVO> add(OflnSbjTchVO VO) throws Exception;

	/**
	 * 오프라인 과목 강사를 수정하고 결과를 반환한다.
	 * @param OflnSbjTchVO
	 * @return ProcessResultVO<OflnSbjTchVO>
	 */
	public abstract ProcessResultVO<OflnSbjTchVO> edit(OflnSbjTchVO VO) throws Exception;

	/**
	 * 오프라인 과목 강사를 삭제하고 결과를 반환한다.
	 * @param OflnSbjTchVO
	 * @return ProcessResultVO<OflnSbjTchVO>
	 */
	public abstract ProcessResultVO<OflnSbjTchVO> remove(OflnSbjTchVO VO) throws Exception;

}