package egovframework.edutrack.modules.course.course.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import egovframework.edutrack.comm.annotation.HrdApiCrsCreCrs;
import egovframework.edutrack.comm.annotation.HrdApiCrsCreCrs.CreSyncType;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;


public interface CourseService {

	/**
	 * 과정 정보의 목록을 조회하여 반환한다.
	 * @param CourseCretermVO
	 * @return ProcessResultListVO<CourseVO>
	 */
	public abstract ProcessResultListVO<CourseVO> list(CourseVO VO) throws Exception;

	/**
	 * 과정 정보의 목록을 조회하여 반환한다.
	 * (페이징 정보 포함)
	 * @param CourseCretermVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return ProcessResultListVO<CourseVO>
	 */
	public abstract ProcessResultListVO<CourseVO> listPageing(CourseVO VO,
			int curPage, int listScale, int pageScale) throws Exception;

	/**
	 * 과정 정보의 목록을 조회하여 반환한다.
	 * (페이징 정보 포함)
	 * @param CourseCretermVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return 조회결과 목록 VO
	 */
	@Transactional(readOnly = true)
	public abstract ProcessResultListVO<CourseVO> listPageing(CourseVO VO,
			int curPage, int listScale) throws Exception;

	/**
	 * 과정 정보의 목록을 조회하여 반환한다.
	 * (페이징 정보 포함)
	 * @param CourseCretermVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return 조회결과 목록 VO
	 */
	public abstract ProcessResultListVO<CourseVO> listPageing(CourseVO VO,
			int curPage) throws Exception;

	/**
	 * 과정 정보의 단일 레코드를 조회하여 반환한다.
	 * @param CourseVO.atclSn
	 * @return ProcessResultVO<CourseVO>
	 */
	public abstract ProcessResultVO<CourseVO> view(CourseVO VO) throws Exception;

	/**
	 * 과정 정보의 목록을 조회하여 반환한다.
	 * @param CourseCretermVO
	 * @return ProcessResultListVO<CourseVO>
	 */
	public ProcessResultListVO<CourseVO> listForEnroll(CourseVO VO) throws Exception;

	/**
	 * 과정 정보를 등록하고 결과를 반환한다.
	 * @param CourseCretermVO
	 * @return ProcessResultVO<CourseVO>
	 */
	public abstract ProcessResultVO<CourseVO> add(CourseVO VO) throws Exception;

	/**
	 * 과정 정보를 수정하고 결과를 반환한다.
	 * @param CourseCretermVO
	 * @return ProcessResultVO<CourseVO>
	 */
	@HrdApiCrsCreCrs(CreSyncType.CRSUPDATE)
	public abstract ProcessResultVO<CourseVO> edit(CourseVO VO) throws Exception;

	/**
	 * 과정 정보 삭제하고 결과를 반환한다.
	 * @param articleVO 삭제 대상 고유 번호
	 * @return 삭제 처리 결과 VO
	 */
	public abstract ProcessResultVO<?> remove(CourseVO VO) throws Exception;

	/**
	 * 과정명으로 중복이 있는지 확인한다.
	 * @param CourseCretermVO
	 * @return ProcessResultVO<CourseVO>
	 */
	public ProcessResultVO<CourseVO> isDupulicationByCrsNm(
			String orgCd, String crsNm) throws Exception;

	/**
	 * 트랜잭션 테스트용 매소드(테스트에서만 사용된다.)
	 */
	@Deprecated
	public abstract ProcessResultVO<CourseVO> transactionRollbackMethod(
			CourseVO VO) throws Exception;

	/**
	 * 과정 정보의 목록을 paging 조회하여 반환한다.
	 * @param CourseCretermVO
	 * @return ProcessResultListVO<CourseVO>
	 */
	public ProcessResultListVO<CourseVO> listForEnrollPaging(CourseVO VO) throws Exception;

	/**
	 * 과목 사용중인 과정 정보의 목록을 조회하여 반환한다. - 온라인
	 * (페이징 정보 포함)
	 * @param CourseCretermVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return ProcessResultListVO<CourseVO>
	 */
	public abstract ProcessResultListVO<CourseVO> listSubInfo(CourseVO VO) throws Exception;

	/**
	 * 과목 사용중인 과정 정보의 목록을 조회하여 반환한다. - 오프라인
	 * (페이징 정보 포함)
	 * @param CourseCretermVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return ProcessResultListVO<CourseVO>
	 */
	public abstract ProcessResultListVO<CourseVO> listSubInfoOffline(CourseVO VO) throws Exception;
	
	public abstract ProcessResultListVO<CourseVO> listUserCourse(String userNo) throws Exception;
	
	public abstract ProcessResultListVO<CourseVO> listStdCoursePaging(CourseVO VO)  throws Exception;
	
	/**
	 * [HRD] 수강생이 신청 및 결제한 기수 리스트 조회
	 */
	public abstract List<CourseVO> listStudentPaymentCourseByUserNo(CourseVO VO);
	
	
	/**
	 * 과정 분류 목록을 조회하여 반환한다.
	 * @param CourseVO
	 * @return ProcessResultListVO<CourseVO>
	 */
	public abstract ProcessResultListVO<CourseVO> listCourse(CourseVO VO) throws Exception;

}