package egovframework.edutrack.modules.course.createcourse.service;

import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import egovframework.edutrack.comm.annotation.HrdApiCrsCreCrs;
import egovframework.edutrack.comm.annotation.HrdApiCrsCreCrs.CreSyncType;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.modules.course.course.service.CourseVO;



public interface CreateCourseService {

	/**
	 * 개설 과정 목록 조회
	 *
	 * @return ProcessResultListVO
	 */
	public abstract  ProcessResultListVO<CreateCourseVO> listCreateCourse(
			CreateCourseVO iCreateCourseVO) throws Exception;


	/**
	 * 개설 과정 정보의 목록을 조회하여 반환한다.
	 * (페이징 정보 포함)
	 * @param CourseVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return ProcessResultListVO<CourseVO>
	 */
	public abstract  ProcessResultListVO<CreateCourseVO> listCreateCoursePageing(
			CreateCourseVO VO, int curPage, int listScale, int pageScale, boolean filein) throws Exception;

	/**
	 * 과정 정보의 목록을 조회하여 반환한다.
	 * (페이징 정보 포함)
	 * @param CourseVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return 조회결과 목록 VO
	 */
	public abstract  ProcessResultListVO<CreateCourseVO> listCreateCoursePageing(
			CreateCourseVO VO, int curPage, int listScale, boolean filein) throws Exception;

	/**
	 * 과정 정보의 목록을 조회하여 반환한다.
	 * (페이징 정보 포함)
	 * @param CourseVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return 조회결과 목록 VO
	 */
	public abstract  ProcessResultListVO<CreateCourseVO> listCreateCoursePageing(
			CreateCourseVO VO, int curPage, boolean filein) throws Exception;

	/**
	 * 수강신청용 회차 목록 조회
	 *
	 * @return ProcessResultListVO
	 */
	public abstract  ProcessResultListVO<CreateCourseVO> listCreateCourseForEnroll(CreateCourseVO VO) throws Exception;
	
	/**
	 * 수강신청용 회차 정보 조회
	 *
	 * @return ProcessResultListVO
	 */
	public abstract  ProcessResultListVO<CreateCourseVO> createCourseForEnroll(CreateCourseVO VO) throws Exception;

	/**
	 * 날짜별 개설 과정 목록 조회
	 *
	 * @return ProcessResultListVO
	 */
	public abstract  ProcessResultListVO<CreateCourseVO> listCreateCourseForEnrollDate(CreateCourseVO iCreateCourseVO) throws Exception;

	/**
	 * 날짜별 개설 과정 목록 조회 - 기숙사 배정용 과정 목록
	 *
	 * @return ProcessResultListVO
	 */
	public abstract  ProcessResultListVO<CreateCourseVO> listCreateCourseForEnrollDateOffline(CreateCourseVO iCreateCourseVO) throws Exception;

	/**
	 * 과정명 검색 개설 과정 목록 조회
	 *
	 * @return ProcessResultListVO
	 */
	public abstract  ProcessResultListVO<CreateCourseVO> listCreateCourseForEnrollSearch(CreateCourseVO iCreateCourseVO) throws Exception;

	/**
	 * 개설 과정 정보 조회
	 *
	 * @return  ProcessResultVO
	 */
	public abstract  ProcessResultVO<CreateCourseVO> viewCreateCourse(
			CreateCourseVO iCreateCourseVO) throws Exception;

	/**
	 * 개설 과정 등록
	 *
	 * @return  ProcessResultVO
	 */
	@HrdApiCrsCreCrs(CreSyncType.CREATE)
	public abstract  ProcessResultVO<CreateCourseVO> addCreateCourse(
			CreateCourseVO iCreateCourseVO) throws Exception;
	
	
	/**
	 * 자격증 개설 과정 등록
	 *
	 * @return  ProcessResultVO
	 */
	public abstract  ProcessResultVO<CreateCourseVO> addCertificateCreateCourse(
			CreateCourseVO iCreateCourseVO) throws Exception;
	

	/**
	 * 개설 과정 수정
	 *
	 * @return  ProcessResultVO
	 */
	@HrdApiCrsCreCrs(CreSyncType.CREUPDATE)
	public abstract  ProcessResultVO<CreateCourseVO> editCreateCourse(
			CreateCourseVO iCreateCourseVO) throws Exception;
	

	/**
	 * 개설 과정 수정
	 *
	 * @return  ProcessResultVO
	 */
	public abstract  ProcessResultVO<CreateCourseVO> editCertCreateCourse(
			CreateCourseVO iCreateCourseVO) throws Exception;
	

	/**
	 * 개설 과정 삭제
	 * @param crsCd 삭제 대상 코드값
	 * @return
	 */
	@HrdApiCrsCreCrs(CreSyncType.DELETE)
	public abstract  ProcessResultVO<?> deleteCreateCourse(
			CreateCourseVO iCreateCourseVO) throws Exception;
	
	/**
	 * 개설 과정 삭제
	 * @param crsCd 삭제 대상 코드값
	 * @return
	 */
	public abstract  ProcessResultVO<?> deleteCertCreateCourse(
			CreateCourseVO iCreateCourseVO) throws Exception;
	
	

	/**
	 * 설문지 은행 사용중인 과정 리스트
	 *
	 * @return ProcessResultListVO
	 */
	public abstract  ProcessResultListVO<ResearchCourseVO> listCreateCourseForResearch(Map<String, Object> researchInfo) throws Exception;


	/**
	 * 학습자 학습중인 과정 리스트
	 *
	 * @return ProcessResultListVO
	 */
	public abstract  ProcessResultListVO<UserCourseVO> listCreateCourseForStudent(Map<String, Object> userInfo) throws Exception;

	/**
	 * 학습자 학습중인 과정 리스트
	 *
	 * @return ProcessResultListVO
	 */
	public abstract  ProcessResultListVO<UserCourseVO> listCreateCourseForStudent(Map<String, Object> userInfo,
			int curPage, int listScale, int pageScale) throws Exception;

	/**
	 * 학습자 학습중인 과정 리스트
	 *
	 * @return ProcessResultListVO
	 */
	public abstract  ProcessResultListVO<UserCourseVO> listCreateCourseForStudent(Map<String, Object> userInfo,
			int curPage, int listScale) throws Exception;

	/**
	 * 학습자 학습중인 과정 리스트
	 *
	 * @return ProcessResultListVO
	 */
	public abstract  ProcessResultListVO<UserCourseVO> listCreateCourseForStudent(Map<String, Object> userInfo, int curPage) throws Exception;

	/**
	 * 교수자 담당 과정 목록
	 *
	 * @return ProcessResultListVO
	 */
	public abstract  ProcessResultListVO<UserCourseVO> listCreateCourseForTeacher(Map<String, Object> userInfo) throws Exception;

	/**
	 * 교수자 담당 과정 목록
	 *
	 * @return ProcessResultListVO
	 */
	public abstract  ProcessResultListVO<UserCourseVO> listCreateCourseForTeacher(Map<String, Object> userInfo,
			int curPage, int listScale, int pageScale) throws Exception;

	/**
	 * 교수자 담당 과정 목록
	 *
	 * @return ProcessResultListVO
	 */
	public abstract  ProcessResultListVO<UserCourseVO> listCreateCourseForTeacher(Map<String, Object> userInfo,
			int curPage, int listScale) throws Exception;

	/**
	 * 교수자 담당 과정 목록
	 *
	 * @return ProcessResultListVO
	 */
	public abstract  ProcessResultListVO<UserCourseVO> listCreateCourseForTeacher(Map<String, Object> userInfo, int curPage) throws Exception;

	/**
	 * 월별 개설 과정 목록 조회 (리스트 형태)
	 *
	 * @return ProcessResultListVO
	 */
	public abstract  ProcessResultListVO<CreateCourseVO> listCreateCourseForMonth(CreateCourseVO iCreateCourseVO) throws Exception;

	/**
	 * 월별 개설 과정 목록 조회 (칼렌더 형태)
	 *
	 * @return ProcessResultListVO
	 */
	public abstract  ProcessResultListVO<CreateCourseVO> calendarCreateCourseForMonth(CreateCourseVO iCreateCourseVO) throws Exception;

	/**
	 * 트랜잭션 테스트용 매소드(테스트에서만 사용된다.)
	 */
	@Deprecated
	public abstract  ProcessResultVO<CreateCourseVO> transactionRollbackMethod(
			CreateCourseVO iCreateCourseVO) throws Exception;
    /**
     * 과정별 기수 조회
     * @param iCreateCourseVO
     * @return
     */
	public abstract  ProcessResultListVO<CreateCourseVO> listCourseTerm(CreateCourseVO iCreateCourseVO) throws Exception;

	/**
	 * 월별 개설 과정 목록 조회 (칼렌더 형태)
	 *
	 * @return ProcessResultListVO
	 */
	public abstract  ProcessResultListVO<CreateCourseVO> calendarCreateCourse(CreateCourseVO iCreateCourseVO) throws Exception;

	/**
	 * 개설 기수 최대값 가져오기
	 *
	 * @return ProcessResultListVO
	 */
	public abstract  ProcessResultVO<CreateCourseVO> selectMaxTerm(CreateCourseVO VO) throws Exception;

	/**
	 * 과정명 검색 개설 과정 목록 조회 paging
	 *
	 * @return ProcessResultListVO
	 */
	public abstract  ProcessResultListVO<CreateCourseVO> listCreateCourseForEnrollSearchPaging(CreateCourseVO iCreateCourseVO) throws Exception;

	/**
	 * 과목 사용중인 개설 과정 목록 조회 - 온라인
	 *
	 * @return ProcessResultListVO
	 */
	public abstract  ProcessResultListVO<CreateCourseVO> listSubInfo(CreateCourseVO iCreateCourseVO) throws Exception;

	/**
	 * 과목 사용중인 개설 과정 목록 조회 - 오프라인
	 *
	 * @return ProcessResultListVO
	 */
	public abstract  ProcessResultListVO<CreateCourseVO> listSubInfoOffline(CreateCourseVO iCreateCourseVO) throws Exception;

	/**
	 * 진행중인과정 - Todo리스트 목록조회
	 *
	 * @return ProcessResultListVO
	 */
	public abstract  ProcessResultListVO<CreateCourseVO> listCreateCourseTodoList(CreateCourseVO iCreateCourseVO) throws Exception;


	/**
	 * 개설 과정 전체 목록 조회
	 *
	 * @return ProcessResultListVO
	 */
	public abstract  ProcessResultListVO<CreateCourseVO> listCreateCourseForAll(CreateCourseVO iCreateCourseVO) throws Exception;

    /**
     * 과정별 기수 조회
     * @param iCreateCourseVO
     * @return
     */
	public abstract  ProcessResultListVO<CreateCourseVO> listDeptByCrsCd(CreateCourseVO iCreateCourseVO) throws Exception;
	
	/**
	 * 과정별 기수 조회
	 * @param iCreateCourseVO
	 * @return
	 */
	public abstract  ProcessResultListVO<CreateCourseVO> listSbjByDeptCd(CreateCourseVO iCreateCourseVO) throws Exception;
	
	/**
	 * 과정별 기수 조회
	 * @param iCreateCourseVO
	 * @return
	 */
	public abstract  ProcessResultListVO<UserCourseVO> myCreListForStudent(Map<String, Object> userInfo) throws Exception;
	
	/**
	 * 과정별 기수 조회
	 * @param iCreateCourseVO
	 * @return
	 */
	public abstract  ProcessResultListVO<UserCourseVO> myCrsListForStudent(Map<String, Object> userInfo) throws Exception;
	
	/**
	 * 과정별 기수 조회
	 * @param iCreateCourseVO
	 * @return
	 */
	public abstract  ProcessResultListVO<UserCourseVO> myCreListForTeacher(Map<String, Object> userInfo) throws Exception;
	
	/**
	 * 과정별 기수 조회
	 * @param iCreateCourseVO
	 * @return
	 */
	public abstract  ProcessResultListVO<UserCourseVO> myCrsListForTeacher(Map<String, Object> userInfo) throws Exception;
	/**
	 * 개설과정 리스트 페이징 조회 - subject 기준
	 * @param iCreateCourseVO
	 * @return
	 */
	ProcessResultListVO<CreateCourseVO> listCreateCourseForEnrollSearchBySubjectPaging(CreateCourseVO iCreateCourseVO, boolean filein);


	/**
	 * [HRD] [수강신청결제>강의계획서확인/동의] - 강의정보 조회
	 */
	ProcessResultVO<CreateCourseVO> viewCreateCourseForEnroll(CreateCourseVO vo);

	public abstract CreateCourseVO viewCreateCourseForAttend(CreateCourseVO vo) throws Exception;
	/**
	 * [HRD] 관리자>수강신청관리>엑셀업로드 - 샘플다운로드 개설과정 조회 : 학기의 종료일이 지난 개설과정은 조회하지 않음
	 * @param iCreateCourseVO
	 * @return
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<CreateCourseVO> listCreateCourseForManageEnroll(CreateCourseVO iCreateCourseVO) throws Exception;


	public abstract CreateCourseVO cntCourse(CreateCourseVO cntCourseVO);
	
	/**
	 * 수강신청 목록 조회
	 */
	public abstract ProcessResultListVO<CreateCourseVO> listStdCoursePaging(CreateCourseVO VO)  throws Exception;

}