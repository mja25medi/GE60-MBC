package egovframework.edutrack.modules.course.courseplan.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.modules.course.course.service.CourseVO;


public interface CrsPlanService {

	/**
	 * 년간 과정 계획의 단일 레코드를 조회하여 반환한다.
	 * @param CrsPlanVO.atclSn
	 * @return ProcessResultVO<CrsPlanVO>
	 */
	public abstract ProcessResultVO<CrsPlanVO> view(CrsPlanVO VO) throws Exception;

	/**
	 * 년간 과정 계획를 등록하고 결과를 반환한다.
	 * @param CrsPlanVO
	 * @return ProcessResultVO<CrsPlanVO>
	 */
	public abstract ProcessResultVO<CrsPlanVO> add(CrsPlanVO VO) throws Exception;

	/**
	 * 년간 과정 계획를 수정하고 결과를 반환한다.
	 * @param CrsPlanVO
	 * @return ProcessResultVO<CrsPlanVO>
	 */
	public abstract ProcessResultVO<CrsPlanVO> edit(CrsPlanVO VO) throws Exception;

	/**
	 * 년간 과정 계획를 등록 또는 수정하고 결과를 반환한다.
	 * @param CrsPlanVO
	 * @return ProcessResultVO<CrsPlanVO>
	 */
	public abstract ProcessResultVO<CrsPlanVO> marge(CrsPlanVO VO) throws Exception;

	/**
	 * 연간 계획을 포함한 과정 정보의 목록을 조회하여 반환한다.
	 * @param CourseCretermVO
	 * @return ProcessResultListVO<CourseVO>
	 */
	public ProcessResultListVO<CourseVO> list(CourseVO VO) throws Exception;

	/**
	 * 연간 계획을 포함한 과정 정보의 목록을 조회하여 반환한다.
	 * (페이징 정보 포함)
	 * @param CourseCretermVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return ProcessResultListVO<CourseVO>
	 */
	public ProcessResultListVO<CourseVO> listPageing(
			CourseVO VO, int curPage, int listScale, int pageScale) throws Exception;

	/**
	 * 연간 계획을 포함한 과정 정보의 목록을 조회하여 반환한다.
	 * (페이징 정보 포함)
	 * @param CourseCretermVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return 조회결과 목록 VO
	 */
	public ProcessResultListVO<CourseVO> listPageing(
			CourseVO VO, int curPage, int listScale) throws Exception;

	/**
	 * 연간 계획을 포함한 과정 정보의 목록을 조회하여 반환한다.
	 * (페이징 정보 포함)
	 * @param CourseCretermVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return 조회결과 목록 VO
	 */
	public ProcessResultListVO<CourseVO> listPageing(
			CourseVO VO, int curPage) throws Exception;

	/**
	 * 년간 과정 계획를 일괄 등록 또는 수정하고 결과를 반환한다.
	 * @param CrsPlanVO
	 * @return ProcessResultVO<CrsPlanVO>
	 */
	public abstract ProcessResultVO<CrsPlanVO> margeAll(List<CrsPlanVO> addCrsPlanList, CrsPlanVO crsPlanVO) throws Exception;
}