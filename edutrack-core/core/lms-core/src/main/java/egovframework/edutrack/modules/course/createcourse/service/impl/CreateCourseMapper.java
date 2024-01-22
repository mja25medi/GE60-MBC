package egovframework.edutrack.modules.course.createcourse.service.impl;

import java.util.List;
import java.util.Map;

import egovframework.edutrack.modules.course.createcourse.service.CreateCourseVO;
import egovframework.edutrack.modules.course.createcourse.service.ResearchCourseVO;
import egovframework.edutrack.modules.course.createcourse.service.UserCourseVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;


@Mapper("createCourseMapper")
public interface CreateCourseMapper {

	/**
	 * 개설 과정 목록 조회
	 *
	 * @return ProcessReslutListVO
	 */
	
	public List<CreateCourseVO> listCreateCourse(CreateCourseVO iCreateCourseVO)   ;

	/**
	 * 개설 과정 Pageing 목록 조회
	 *
	 * @return ProcessReslutListVO
	 */
	
	public List<CreateCourseVO> listCreateCoursePageing(CreateCourseVO iCreateCourseVO)   ;

	/**
	 * 개설 과정 Pageing 목록 조회
	 *
	 * @return ProcessReslutListVO
	 */
	
	public int listCreateCoursePageingCount (CreateCourseVO iCreateCourseVO)   ;

	
	/**
	 * 회차 목록 조회 - 과정 안내 / 신청용
	 *
	 * @return ProcessReslutListVO
	 */
	
	public List<CreateCourseVO> listCreateCourseForEnroll(CreateCourseVO VO)    ;
	
	/**
	 * 회차 조회 - 과정 안내 / 신청용
	 *
	 * @return ProcessReslutListVO
	 */
	
	public List<CreateCourseVO> createCourseForEnroll(CreateCourseVO VO)    ;

	/**
	 * 날짜별 개설 과정 목록 조회 - 과정 안내 / 신청용
	 *
	 * @return ProcessReslutListVO
	 */
	
	public List<CreateCourseVO> listCreateCourseForEnrollDate(CreateCourseVO VO)    ;

	/**
	 * 날짜별 개설 과정 목록 조회 - 기숙사 배정 관리용.
	 *
	 * @return ProcessReslutListVO
	 */
	
	public List<CreateCourseVO> listCreateCourseForEnrollDateOffline(CreateCourseVO iCreateCourseVO)   ;

	/**
	 * 개설 과정명 검색 목록 조회 - 과정 안내 / 신청용
	 *
	 * @return ProcessReslutListVO
	 */
	
	public List<CreateCourseVO> listCreateCourseForEnrollSearch(CreateCourseVO VO)   ;

	/**
	 * 개설 과정 정보 조회
	 *
	 * @return ProcessResultVO
	 */
	public CreateCourseVO selectCreateCourse(CreateCourseVO iCreateCourseVO)   ;



	/**
	 * 개설 과정 등록
	 *
	 * @reurn ProcessResultVO
	 */
	public int insertCreateCourse(CreateCourseVO iCreateCourseVO)   ;

	/**
	 * 개설 과정 코드 조회
	 *
	 * @return ProcessResultVO
	 */
	public String selectCreateCourseCd()   ;

	/**
	 * 개설 과정 수정
	 *
	 * @reurn ProcessResultVO
	 */
	public int updateCreateCourse(CreateCourseVO iCreateCourseVO)   ;

	/**
	 * 개설 과정 삭제
	 *
	 * @reurn ProcessResultVO
	 */
	public int deleteCreateCourse(CreateCourseVO iCreateCourseVO)   ;
	
	
	/**
	 * 개설 과정 QR 정보삭제
	 *
	 * @reurn ProcessResultVO
	 */
	public int deleteCreateCourseQr(CreateCourseVO iCreateCourseVO)   ;
	
	/**
	 * 학습자 - 수강 신청한 목록 조회
	 *
	 * @return ProcessReslutListVO
	 */
	
	public List<UserCourseVO> listCreateCourseForStudent(Map<String, Object> userInfo)   ;

	/**
	 * 학습자 - 수강 신청한 목록 조회
	 *
	 * @return ProcessReslutListVO
	 */
	
	public List<ResearchCourseVO> listCreateCourseForResearch(Map<String, Object> researchInfo)   ;

	/**
	 * 학습자 - 수강 신청한 목록 조회
	 *
	 * @return ProcessReslutListVO
	 */
	
	public List<UserCourseVO> listCreateCourseForStudentPageing(Map<String, Object> userInfo)   ;

	/**
	 * 학습자 - 수강 신청한 목록 조회
	 *
	 * @return ProcessReslutListVO
	 */
	
	public int listCreateCourseForStudentPageingCount(Map<String, Object> userInfo)   ;

	
	/**
	 * 교수자 - 진행중인 과정 목록 조회
	 *
	 * @return ProcessReslutListVO
	 */
	
	public List<UserCourseVO> listCreateCourseForTeacher(Map<String, Object> userInfo)   ;

	/**
	 * 교수자 - 진행중인 과정 목록수 반환
	 *
	 * @return ProcessReslutListVO
	 */
	
	public int listCreateCourseForTeacherCount (Map<String, Object> userInfo)   ;

	
	/**
	 * 교수저 - 진행중닌 과정 목록 조회
	 *
	 * @return ProcessReslutListVO
	 */
	
	public List<UserCourseVO> listCreateCourseForTeacher(Map<String, Object> userInfo, int curPage, int listScale, int pageScale)   ;

	/**
	 * 월별 개설과정 목록 조회(리스트로)
	 *
	 * @return ProcessReslutListVO
	 */
	
	public List<CreateCourseVO> listCreateCourseForMonth(CreateCourseVO iCreateCourseVO)   ;

	/**
	 * 월별 개설과정 목록 조회(리스트로)
	 *
	 * @return ProcessReslutListVO
	 */
	
	public List<CreateCourseVO> calendarCreateCourseForMonth(CreateCourseVO iCreateCourseVO)   ;

	/**
	 * 과정 코드로 기수 목록 조회
	 * @param iCreateCourseVO
	 * @return
	 */
	
	public List<CreateCourseVO> listCourseTerm(CreateCourseVO iCreateCourseVO)   ;

	/**
	 * 월별 개설과정 목록 조회(리스트로)
	 *
	 * @return ProcessReslutListVO
	 */
	
	public List<CreateCourseVO> calendarCreateCourse(CreateCourseVO iCreateCourseVO)   ;

	/**
	 * 과정 기수 최대값 가져오기.
	 * @return ProcessResultVO
	 */
	public CreateCourseVO selectMaxTerm(CreateCourseVO VO)   ;

	/**
	 * 개설 과정명 검색 목록 조회 - 과정 안내 / 신청용 Paging
	 *
	 * @return ProcessReslutListVO
	 */
	
	public List<CreateCourseVO> listCreateCourseForEnrollSearchPaging(CreateCourseVO VO)   ;

	/**
	 * 개설 과정명 검색 목록 조회 - 과정 안내 / 신청용 Paging
	 *
	 * @return ProcessReslutListVO
	 */
	
	public int listCreateCourseForEnrollSearchPagingCount(CreateCourseVO VO)   ;

	
	/**
	 * 과목 사용중인 개설 과정 목록 조회 - 온라인
	 *
	 * @return ProcessReslutListVO
	 */
	
	public List<CreateCourseVO> listSubInfo(CreateCourseVO iCreateCourseVO)   ;

	/**
	 * 과목 사용중인 개설 과정 목록 조회 - 오프라인
	 *
	 * @return ProcessReslutListVO
	 */
	
	public List<CreateCourseVO> listSubInfoOffline(CreateCourseVO iCreateCourseVO)   ;

	/**
	 * 진행중인과정 - Todo리스트 목록조회
	 *
	 * @return ProcessReslutListVO
	 */
	
	public List<CreateCourseVO> listCreateCourseTodoList(CreateCourseVO VO)   ;

	/**
	 * 개설 과정 전체 목록 조회
	 *
	 * @return List
	 */
	
	public List<CreateCourseVO> listCreateCourseForAll(CreateCourseVO VO)   ;
	
	/**
	 * 개설 과정 전체 목록 조회
	 *
	 * @return List
	 */
	
	public List<CreateCourseVO> listDeptByCrsCd(CreateCourseVO VO)   ;
	
	/**
	 * 개설 과정 전체 목록 조회
	 *
	 * @return List
	 */
	
	public List<CreateCourseVO> listSbjByDeptCd(CreateCourseVO VO)   ;
	
	/**
	 * 개설 과정 전체 목록 조회
	 *
	 * @return List
	 */
	
	public List<UserCourseVO> myCreListForStudent(Map<String, Object> userInfo)   ;
	
	/**
	 * 개설 과정 전체 목록 조회
	 *
	 * @return List
	 */
	
	public List<UserCourseVO> myCrsListForStudent(Map<String, Object> userInfo)   ;
	
	/**
	 * 개설 과정 전체 목록 조회
	 *
	 * @return List
	 */
	
	public List<UserCourseVO> myCreListForTeacher(Map<String, Object> userInfo)   ;
	
	/**
	 * 개설 과정 전체 목록 조회
	 *
	 * @return List
	 */
	
	public List<UserCourseVO> myCrsListForTeacher(Map<String, Object> userInfo)   ;
	/**
	 * 개설과정 카운트 조회 - subject 기준
	 * @param iCreateCourseVO
	 * @return
	 */
	
	public int listCreateCourseForEnrollSearchBySubjectPagingCount(CreateCourseVO vo);

	/**
	 * 개설과정 리스트 페이징 조회 - subject 기준
	 * [HRD] [교육과정/신청>교육과정 및 신청] - 개설과정 리스트 조회
	 * @param vo
	 * @return
	 */
	
	public List<CreateCourseVO> listCreateCourseForEnrollSearchBySubjectPaging(CreateCourseVO vo);

	/**
	 * 개설과정 상세조회 - subject 기준
	 * @param vo
	 * @return
	 */
	
	public CreateCourseVO selectCreateCourseForEnroll(CreateCourseVO vo);
	
	/**
	 * [HRD] 관리자>수강신청관리>엑셀업로드 - 샘플다운로드 개설과정 조회 : 학기의 종료일이 지난 개설과정은 조회하지 않음
	 * @param vo
	 * @return
	 */
	public List<CreateCourseVO> listCreateCourseForManageEnroll(CreateCourseVO vo);
	
	public CreateCourseVO selectCreateCourseForAttend(CreateCourseVO vo);

	public CreateCourseVO cntCourse(CreateCourseVO cntCourseVO);
	
	
	public int countCourse(CreateCourseVO VO)
			 ;
	
	/**
	 * 수강신청내역 조회
	 */
	public List<CreateCourseVO> listUserStdCourse(CreateCourseVO VO) ;
	
	/**
	 * 개설 과정 QR 정보 등록
	 *
	 * @reurn ProcessResultVO
	 */
	public int insertCreateCourseQr(CreateCourseVO iCreateCourseVO)   ;
	
	/**
	 * 개설 과정 Pageing 목록 조회 [담임]
	 *
	 * @return ProcessReslutListVO
	 */
	
	public List<CreateCourseVO> listCreateCourseForTchMgrPageing(CreateCourseVO iCreateCourseVO)   ;

	/**
	 * 개설 과정 Pageing 목록 조회 [담임]
	 *
	 * @return ProcessReslutListVO
	 */
	
	public int listCreateCourseForTchMgrPageingCount (CreateCourseVO iCreateCourseVO)   ;
}

