package egovframework.edutrack.modules.course.creterm.service;

import org.springframework.transaction.annotation.Transactional;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;


public interface CourseCretermService {

	/**
	 * 과정 정보의 목록을 조회하여 반환한다.
	 * @param CourseCretermVO
	 * @return ProcessResultListVO<CourseVO>
	 */
	public abstract ProcessResultListVO<CourseCretermVO> list(CourseCretermVO VO) throws Exception;

	/**
	 * 과정 정보의 목록을 조회하여 반환한다.
	 * (페이징 정보 포함)
	 * @param CourseCretermVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return ProcessResultListVO<CourseVO>
	 */
	public abstract ProcessResultListVO<CourseCretermVO> listPageing(CourseCretermVO VO) throws Exception;

	/**
	 * 과정 정보의 단일 레코드를 조회하여 반환한다.
	 * @param CourseVO.atclSn
	 * @return ProcessResultVO<CourseVO>
	 */
	public abstract ProcessResultVO<CourseCretermVO> view(CourseCretermVO VO) throws Exception;

	/**
	 * 과정 정보를 등록하고 결과를 반환한다.
	 * @param CourseCretermVO
	 * @return ProcessResultVO<CourseVO>
	 */
	public abstract ProcessResultVO<CourseCretermVO> add(CourseCretermVO VO) throws Exception;

	/**
	 * 과정 정보를 수정하고 결과를 반환한다.
	 * @param CourseCretermVO
	 * @return ProcessResultVO<CourseVO>
	 */
	public abstract ProcessResultVO<CourseCretermVO> edit(CourseCretermVO VO) throws Exception;

	/**
	 * 과정 정보 삭제하고 결과를 반환한다.
	 * @param articleVO 삭제 대상 고유 번호
	 * @return 삭제 처리 결과 VO
	 */
	public abstract ProcessResultVO<?> remove(CourseCretermVO VO) throws Exception;


}