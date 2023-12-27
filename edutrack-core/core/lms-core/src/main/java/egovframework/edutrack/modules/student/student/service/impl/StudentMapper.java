package egovframework.edutrack.modules.student.student.service.impl;

import java.util.List;
import java.util.Map;

import egovframework.edutrack.modules.course.course.service.CourseVO;
import egovframework.edutrack.modules.lecture.bookmark.service.BookmarkVO;
import egovframework.edutrack.modules.student.student.service.StudentVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("studentMapper")
public interface StudentMapper {
	/**
	 * 수강 신청 번호 조회
	 *
	 * @return ProcessResultVO
	 */
	public String selectKey() ;

	/**
	 * 학습자 목록 조회 (전체)
	 *
	 * @return ProcessReslutListVO
	 */
	@SuppressWarnings("unchecked")
	public abstract List<StudentVO> listStudent(StudentVO iStudentVO) throws Exception;

	/**
	 * 학습자 목록 수 조회
	 *
	 * @reurn ProcessResultVO
	 */
	public abstract int count(StudentVO iStudentVO) throws Exception;
	/**
	 * 학습자 목록 조회 (페이징)
	 *
	 * @return ProcessReslutListVO
	 */
	@SuppressWarnings("unchecked")
	public abstract List<StudentVO> listStudentPageing(StudentVO iStudentVO) throws Exception;

	/**
	 * 학습자 정보 조회
	 *
	 * @return ProcessResultVO
	 */
	public abstract StudentVO selectStudent(StudentVO iStudentVO);

	/**
	 * 수강생 등록
	 *
	 * @reurn ProcessResultVO
	 */
	public abstract int insertStudent(StudentVO iStudentVO);

    /**
     * 수강생 일괄등록
     * @param codeArray
     */
	public abstract int insertStudentBatch(List<StudentVO> studentArray) throws Exception;

    /**
     * 수료 처리 일괄 취소
     * @param studentArray(StudentVO)
     */
	public abstract int cancelComplete(List<StudentVO> studentArray) throws Exception;

	/**
	 * 수료 처리 일괄 취소
	 * @param studentArray(StudentVO)
	 */
	public abstract int cancelRsltComplete(List<StudentVO> studentArray) throws Exception;

	/**
	 * 수강생 등록 (프로시저 이용)
	 *
	 * @reurn ProcessResultVO
	 */
	public abstract int insertStudentSp(StudentVO iStudentVO) throws Exception;


	/**
	 * 수강 신청 정보 수정
	 *
	 * @reurn ProcessResultVO
	 */
	public abstract int updateStudent(StudentVO iStudentVO) throws Exception;
	
	/**
	 * 수강 신청 정보 수정
	 *
	 * @reurn ProcessResultVO
	 */
	public abstract int updateStudentIde(StudentVO iStudentVO) throws Exception;

    /**
     * 수강 신청 일괄 인증
     * @param codeArray
     */
	public abstract int confirmStudentBatch(Map<String, Object> studentInfo) throws Exception;

    /**
     * 수강 신청 일괄 인증
     * @param codeArray
     */
	public abstract int confirmStudent(StudentVO iStudentVO) throws Exception;

	/**
     * 수강 신청 일괄 취소
     * @param codeArray
     */
	public abstract int cancelStudent(List<StudentVO> studentArray) throws Exception;

    /**
     * 수강 신청 일괄 삭제
     * @param codeArray
     */
	public abstract int deleteStudent(List<StudentVO> studentArray) throws Exception;

	/**
	 * 사용자 목록 조회 (전체) - 수강생 검색용
	 *
	 * @return ProcessReslutListVO
	 */
	public abstract List<StudentVO> listUser(StudentVO iStudentVO) throws Exception;


	/**
	 * 수강생 검색
	 *
	 * @reurn ProcessResultVO
	 */
	public abstract StudentVO isStudent(StudentVO iStudentVO);
	
	/**
	 * 수강생 검색 - 회원번호, 개설과정코드
	 * @param iStudentVO
	 * @return
	 */
	public abstract StudentVO isStudentByUserNo(StudentVO iStudentVO);

	/**
	 * 수강신청 검색
	 *
	 * @reurn ProcessResultVO
	 */
	public abstract StudentVO isEnroll(StudentVO iStudentVO);

	/**
	 * 수강신청자수 검색
	 * 해당 과정의 수강신청자(수강신청, 수강, 수료, 미수료 포함)
	 * @param studentVO.crsCreCd
	 * @reurn ProcessResultVO(StudentVO) : studentVO.stdYn
	 */
	public abstract StudentVO enrollCnt(StudentVO iStudentVO) throws Exception;
	
	/**
	 * 수강신청자수 검색
	 * 해당 과정의 수강신청자(수강신청, 수강, 수료, 미수료 포함)
	 * @param studentVO.crsCreCd
	 * @reurn ProcessResultVO(StudentVO) : studentVO.stdYn
	 */
	public abstract StudentVO selectMyStdNo(StudentVO iStudentVO) throws Exception;

	/**
	 * 학습자의 학습정보를 검색한다.
	 *
	 * @return ProcessResultVO
	 */
	public abstract StudentVO selectStudentInfo(StudentVO iStudentVO) throws Exception;

	/**
     * 교번 일괄 저장
     * @param studentArray(StudentVO)
     */
	public abstract int updateEduNo(List<StudentVO> studentArray) throws Exception;

	/**
	 * 수강생 자동 수료 처리 (프로시저 이용)
	 * 수강결과의 총점을 이용하여 수료 처리함, 수료학점, 수료번호, 상대점수 처리
	 * 상대 점수는 총점 및 생년월일을 이용해 순차 조정함.
	 * @param StudentVO
	 * @reurn ProcessResultVO
	 */
	public abstract int autoCompleteSp(StudentVO iStudentVO) throws Exception;

	/**
	 * 수강생 수료 처리 (프로시저 이용)
	 * 설문 응시 후 수강결과의 총점을 이용하여 수료 처리함, 수료학점, 수료번호
	 * @param StudentVO
	 * @reurn ProcessResultVO
	 */
	public abstract int reshAutoCompleteSp(StudentVO iStudentVO) throws Exception;

	/**
	 * 수강생 수료 처리 (프로시저 이용)
	 * 수강결과의 총점을 이용하여 수료 처리함, 수료학점, 수료번호
	 * @param StudentVO
	 * @reurn ProcessResultVO
	 */
	public abstract int checkCompleteSp(StudentVO iStudentVO) throws Exception;

	/**
     * 수강신청 마이그래에선용 업데이트 쿼리
     * @param studentArray(StudentVO)
     */
	public abstract int updateDateForMigration(List<StudentVO> studentArray) throws Exception;

	/**
	 * 개설 과정 정보 수정시
	 * 해당 과정의 학습자 수강일자, 청강일자 조정
	 * @reurn ProcessResultVO
	 */
	public abstract int updateEnrlDuration(StudentVO iStudentVO) throws Exception;

	public abstract int addMergeStudent(StudentVO newStudentVO) throws Exception;


	/**
	 * 수강생의 분반 정보를 수정한다.
	 * @reurn ProcessResultVO
	 */
	public abstract int updateDecls(StudentVO iStudentVO) throws Exception;

	/**
	 * 성적 우수자 선정/해제
	 * @author twkim
	 * @date 2013. 10. 30.
	 * @param iStudentVO
	 * @return ProcessResultVO<StudentVO>
	 */
	public abstract int updateScoreEclt(StudentVO iStudentVO) throws Exception;

    /**
     * 수강 신청 일괄 인증
     * @param codeArray
     */
	public abstract int confirmDepositStudent(List<StudentVO> studentArray) throws Exception;


	/**
	 * 과정 전체 진도율 정보 조회
	 *
	 * @return  ProcessResultVO
	 */
	public abstract StudentVO selectCrsRatio(StudentVO iStudentVO) throws Exception;

	/**
	 * 뒤3자리 수료번호 최고값 + 1 을 구해온다.
	 * @param dto
	 * @return
	 */
	public abstract StudentVO getCompleteNo(StudentVO iStudentVO) throws Exception;

	/**
	 * 수료 처리
	 * @author twkim
	 * @date 2015. 11. 20.
	 * @param iStudentVO
	 * @return ProcessResultVO<StudentVO>
	 */
	public abstract int completeSuccess(StudentVO iStudentVO) throws Exception;

	/**
	 * 미수료 처리
	 * @author twkim
	 * @date 2015. 11. 20.
	 * @param iStudentVO
	 * @return ProcessResultVO<StudentVO>
	 */
	public abstract int completeFailed(StudentVO iStudentVO) throws Exception;
	
	public abstract int countForStdManage(StudentVO vo) throws Exception;
	
	public abstract StudentVO selectStdForAttend(StudentVO vo);
	
	public abstract List<StudentVO> listStudentForStdManage(StudentVO vo);
	
	public abstract List<BookmarkVO> stdBookmarkListForAttend(StudentVO vo);

	public abstract List<StudentVO> listStudentPageingForStdManage(StudentVO vo) throws Exception;

	/**
	 * [HRD] 결제번호로 수강대기(E)인 수강생, 수강중(S) 상태로 변경
	 */
	public int updateStudentEnrlStsVbank(StudentVO vo);
	
	/**
	 * [HRD] 회원번호로 수강생의 결제내역 리스트 조회(수강생 기준)
	 * @param iStudentVO
	 * @return
	 */
	public abstract List<StudentVO> listStudentPaymentInfoByUserNo(StudentVO iStudentVO);
	
	/**
	 * [HRD] 수강생 번호로 수강생의 결제내역 단건 조회(수강생 기준)
	 * @param iStudentVO
	 * @return
	 */
	public abstract StudentVO selectStudentPaymentInfoByStdNo(StudentVO iStudentVO);
	
	/**
	 * [HRD] 회원번호, 개설과정코드로 대기중인 수강생 조회(수강생 기준)
	 * @param iStudentVO
	 * @return
	 */
	public abstract StudentVO selectStudentEnrollBefore(StudentVO iStudentVO);
	
	/**
	 * [HRD] 관리자>수강신청관리 - 수강생 리스트 조회
	 * @param iStudentVO
	 * @return
	 */
	public abstract List<StudentVO> listStudentPaymentInfoManage(StudentVO iStudentVO);
	
	/**
	 * [HRD] 관리자>수강신청관리 - IDE 리스트 조회
	 * @param iStudentVO
	 * @return
	 */
	public abstract List<StudentVO> listIdeManage(StudentVO iStudentVO);
	
	/**
	 * [HRD] 관리자>수강신청관리 - 수강생 리스트 페이징 조회
	 * @param iStudentVO
	 * @return
	 */
	public abstract List<StudentVO> listStudentPaymentInfoManagePageing(StudentVO iStudentVO);

	/**
	 * [HRD] 관리자>수강신청관리 - 수강생 리스트 페이징 조회 카운트(상단 통계 포함)
	 * @param vo
	 * @return
	 */
	public StudentVO listStudentPaymentInfoManagePageingCount(StudentVO vo);
	
	/**
	 * [HRD] 관리자>수강신청관리 - 수강생 상세보기
	 * @param vo
	 * @return
	 */
	public StudentVO selectStudentPaymentInfoManage(StudentVO vo);
	
	/**
	 * [HRD] 수강데이터
	 * @param vo
	 * @return
	 */
	public StudentVO selectStudentInfoCre(StudentVO vo);

	/**
	 * [HRD] 관리자>수강신청관리>승인
	 * @param vo
	 * @return
	 */
	public int updateStudentEnrlStsConfirm(StudentVO vo);

	/**
	 * [HRD] 관리자>수강신청관리>취소
	 * @param vo
	 * @return
	 */
	public int updateStudentEnrlStsCancel(StudentVO vo);
	
	/**
	 * 수강신청 단건 삭제
	 * @param vo
	 * @return
	 */
	public int deleteStudentOne(StudentVO vo);
	
	/**
	 * 수강생 상태 변경
	 * @param vo
	 * @return
	 */
	public int updateStudentEnrlSts(StudentVO vo);
	
	/**
	 * 수강생 목록 조회(결제 번호, 등록 상태)
	 * @param vo
	 * @return
	 */
	public List<StudentVO> selectStdNoByPayNo(StudentVO vo);
	
	/**
	 * 환불 등록
	 * @param vo
	 * @return
	 */
	public int updateAddRefund(StudentVO vo);
	
	/**
	 * 환불 처리
	 * @param vo
	 * @return
	 */
	public int updateRefundComplete(StudentVO vo);
	
	/**
	 * 
	 * @param vo
	 * @return
	 */
	public int updateRefundCancel(StudentVO vo);
	
	/**
	 * 환불 메모 수정
	 * @param vo
	 * @return
	 */
	public int updateRefundMemo(StudentVO vo);
	
	/**
	 * 환불 히스토리 등록 조회
	 * @param vo
	 * @return
	 */
	StudentVO selectForRefundHsty(StudentVO vo);
	
	/**
	 * 환불 히스토리 등록
	 * @param vo
	 * @return
	 */
	int insertStdPayRefundHsty(StudentVO vo);
	
	/**
	 * 환불 내역 리스트 조회
	 * @param vo
	 * @return
	 */
	List<StudentVO> listStdPayRefundHsty(StudentVO vo);
	
	/**
	 * 환불 내역 리스트 총 갯수 조회
	 * @param vo
	 * @return
	 */
	int listStdPayRefundHstyCount(StudentVO vo);
	
	/**
	 * 환불 내역 리스트 페이징
	 * @param vo
	 * @return
	 */
	List<StudentVO> listStdPayRefundHstyPaging(StudentVO vo);
	
	/**
	 * 자격증 과정 학습자 목록 조회 (전체)
	 *
	 * @return ProcessReslutListVO
	 */
	public abstract List<StudentVO> listStdCertCourse(CourseVO courseVO) throws Exception;
	
	/**
	 * 자격증 과정 학습자 목록 조회 (개인)
	 *
	 * @return ProcessReslutListVO
	 */
	public abstract StudentVO stdCertCourse(StudentVO iStudentVO) throws Exception;
	
	
	/**
	 * 자격증 신청
	 *
	 * @return ProcessReslutListVO
	 */
	public abstract int updateCertStudent(StudentVO iStudentVO) throws Exception;
	
	/**
	 * 자격증 승인(관리자)
	 *
	 * @return ProcessReslutListVO
	 */
	public abstract int updateCertStudentByadmin(StudentVO iStudentVO) throws Exception;

	/**
	 * 자격증 과정 합격처리
	 *
	 * @return ProcessReslutListVO
	 */
	public abstract int updateStudentCertPass(StudentVO iStudentVO) throws Exception;
	
	/**
	 * 자격증 과정 삭제 시 수강생 삭제
	 *
	 * @return ProcessReslutListVO
	 */
	public abstract int deleteCreateCourseStd(StudentVO iStudentVO) throws Exception;
	
}