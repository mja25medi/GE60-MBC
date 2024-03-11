package egovframework.edutrack.modules.student.student.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataAccessException;

import egovframework.edutrack.comm.annotation.HrdApiStdStd;
import egovframework.edutrack.comm.annotation.HrdApiStdStd.SyncType;
import egovframework.edutrack.comm.annotation.RefundHistory;
import egovframework.edutrack.comm.annotation.RefundHistory.RefundType;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.modules.course.course.service.CourseVO;
import egovframework.edutrack.modules.user.info.service.UsrUserInfoVO;


public interface StudentService {

	/**
	 * 수강 신청 목록 조회
	 *
	 * @return ProcessResultListVO
	 */
	public abstract ProcessResultListVO<StudentVO> listStudent(StudentVO iStudentVO) throws Exception;

	/**
	 * 수강 신청 목록 조회(페이징)
	 *
	 * @return ProcessResultListVO
	 */
	public abstract ProcessResultListVO<StudentVO> listStudentPageing(StudentVO iStudentVO ) throws Exception;

	/**
	 * 수강 신청 정보 조회
	 *
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<StudentVO> viewStudent(StudentVO iStudentVO) throws Exception;

	/**
	 * 수강 신청 등록 (학습자, 회원정보와 수강신청 정보를 같이 바꾼다.)
	 *
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<StudentVO> addEnrollStudent(StudentVO iStudentVO) throws Exception;

	/**
	 * 수강 신청 등록
	 *
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<StudentVO> addStudent(StudentVO iStudentVO) throws Exception;

	/**
	 * 수강 신청 등록 배치
	 *
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<Object> addStudentBatch(List<StudentVO> iStudentArray) throws Exception;

	/**
	 * 수강 신청 수정
	 *
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<StudentVO> editStudent(StudentVO iStudentVO) throws Exception;
	
	/**
	 * 수강생 ide 수정
	 *
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<StudentVO> editStudentUrl(StudentVO iStudentVO) throws Exception;

	/**
	 * 수강 신청 인증 처리
	 * @param request
	 *
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<StudentVO> confirmStudent(StudentVO iStudentVO, HttpServletRequest request) throws Exception;

	/**
	 * 수강 신청 취소 처리
	 * @param request
	 *
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<StudentVO> cancelStudent(StudentVO iStudentVO, HttpServletRequest request) throws Exception;

	/**
	 * 수강 신청 삭제 처리
	 *
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<StudentVO> deleteStudent(StudentVO iStudentVO) throws Exception;
	
	/**
	 * 수강 신청 삭제 처리 (개별)
	 *
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<StudentVO> deleteCourseStudent(StudentVO iStudentVO) throws Exception;

	/**
	 * 수료(미수료) 취소 처리
	 *
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<StudentVO> cancelComplete(StudentVO iStudentVO) throws Exception;

	/**
	 * 사용자 목록 조회 - 수강 신청용
	 *
	 * @return ProcessResultListVO
	 */
	public abstract ProcessResultListVO<StudentVO> listUser(StudentVO iStudentVO) throws Exception;

	/**
	 * 수강생인지 검색
	 *
	 * @return ProcessResultListVO
	 */
	public abstract ProcessResultVO<StudentVO> isStudent(StudentVO iStudentVO) throws Exception;

	/**
	 * 수강신청이 있는지 검색
	 *
	 * @return ProcessResultListVO
	 */
	public abstract ProcessResultVO<StudentVO> isEnroll(StudentVO iStudentVO) throws Exception;

	/**
	 * 수강신청이 있는지 검색
	 *
	 * @return ProcessResultListVO
	 */
	public abstract ProcessResultVO<StudentVO> enrollCnt(StudentVO iStudentVO) throws Exception;
	
	/**
	 * 수강신청이 있는지 검색
	 *
	 * @return ProcessResultListVO
	 */
	public abstract ProcessResultVO<StudentVO> selectMyStdNo(StudentVO iStudentVO) throws Exception;

	/**
	 * 수강생의 학습 정보를 조회한다.
	 *
	 * @return ProcessResultListVO
	 */
	public abstract ProcessResultVO<StudentVO> viewStudentInfo(StudentVO iStudentVO) throws Exception;

	/**
	 * 자동 수료 처리
	 *
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<StudentVO> autoComplete(StudentVO iStudentVO) throws Exception;

	/**
	 * 선택 수료 처리
	 *
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<StudentVO> checkComplete(StudentVO iStudentVO) throws Exception;

	/**
	 * 수강 결과 등록
	 *
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<StudentVO> addEduNoAll(StudentVO iStudentVO, String strStdNo, String strEduNo) throws Exception;


	/**
	 * 회원정보를 검사한 뒤 있을 경우 update 없을 경우 insert를 수행한다.<br>
	 * 학생정보를 검사한 뒤 수강신청 상태가 아닌 경우 Insert를 수행한다.
	 * @param studentVO
	 * @param newUserDto
	 * @throws ValidateStudentException 이미 수강신청중인 교육생이거나 학생정보 변환중 오류 발생일 경우..
	 * @throws Exception 
	 * @throws DataAccessException 
	 */
	public abstract void saveUserStudent(StudentVO studentVO, UsrUserInfoVO userInfoVO) throws ValidateStudentException, DataAccessException, Exception;

	/**
	 * 수강생의 분반 정보 변경
	 *
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<StudentVO> editDecls(String userList, Integer declsNo) throws Exception;
	
	/**
	 * 트랜잭션 테스트용 매소드(테스트에서만 사용된다.)
	 */
	@Deprecated
	public abstract ProcessResultVO<StudentVO> transactionRollbackMethod(
			StudentVO iStudentVO) throws Exception;

	/**
	 * 성적우수자 선정/해제
	 * @author twkim 
	 * @date 2013. 10. 30.
	 * @param iStudentVO
	 * @return ProcessResultVO<StudentVO>
	 */
	public abstract ProcessResultVO<StudentVO> editScoreEclt(StudentVO iStudentVO) throws Exception;

	/**
	 * 성적우수자 선정/해제 Batch
	 * @param crsCreCd
	 * @param targetList
	 * @param studentList
	 * @return ProcessResultVO<StudentVO>
	 */
	public abstract ProcessResultVO<StudentVO> editScoreEcltBatch(String crsCreCd, String targetList, String studentList) throws Exception;
	
	/**
	 * 수강생의 비밀번호 ID로 변경
	 *
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<StudentVO> resetPassStudent(StudentVO iStudentVO, HttpServletRequest request) throws Exception;
	
	
	/**
	 * 수강생 입금 일괄 처리
	 * @param request
	 *
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<StudentVO> confirmDepositStudent(StudentVO iStudentVO, HttpServletRequest request) throws Exception;

	
	public abstract ProcessResultVO<StudentVO> viewCrsRatioInfo(StudentVO iStudentVO) throws Exception;
	
	public abstract ProcessResultListVO<StudentVO> listStudentPageingForMng(StudentVO studentVO) throws Exception;

	public List<StudentVO> listStudentForMng(StudentVO vo) throws Exception;
	
	/**
	 * [HRD] 회원번호로 수강생의 결제내역 조회(수강생 기준)
	 * @param iStudentVO
	 * @return
	 */
	public abstract List<StudentVO> listStudentPaymentInfoByUserNo(StudentVO iStudentVO);

	/**
	 * [HRD] 회원번호, 개설과정코드로 대기중인 수강생 조회(수강생 기준)
	 * @param iStudentVO
	 * @return
	 */
	public abstract ProcessResultVO<StudentVO> selectStudentEnrollBefore(StudentVO iStudentVO);
	
	/**
	 * [HRD] 관리자>수강신청관리 - 수강생 리스트 조회
	 * @param iStudentVO
	 * @return
	 */
	public abstract ProcessResultListVO<StudentVO> listStudentPaymentInfoManage(StudentVO iStudentVO);
	
	/**
	 * [HRD] 관리자>수강신청관리 - IDE 리스트 조회
	 * @param iStudentVO
	 * @return
	 */
	public abstract ProcessResultListVO<StudentVO> listIdeManage(StudentVO iStudentVO);
	
	/**
	 * [HRD] 관리자>수강신청관리 - 수강생 리스트 페이징 조회
	 * @param iStudentVO
	 * @return
	 */
	public abstract ProcessResultListVO<StudentVO> listStudentPaymentInfoManagePageing(StudentVO iStudentVO);

	/**
	 * [HRD] 결제내역 - 수강취소
	 * @param vo
	 * @return
	 */
	public abstract ProcessResultVO<StudentVO> cancelStudentEnrlStsE(StudentVO vo);
	
	/**
	 * [HRD] 수강신청관리 상세보기
	 */
	public abstract ProcessResultVO<StudentVO> viewStudentPaymentInfoManage(StudentVO vo);

	/**
	 * [HRD] 관리자>수강신청관리>승인
	 * @param vo
	 * @return
	 */
	public abstract ProcessResultVO<StudentVO> confirmStudentPayment(StudentVO vo);

	/**
	 * [HRD] 관리자>수강신청관리>취소
	 * @param vo
	 * @return
	 */
	public abstract ProcessResultVO<StudentVO> cancelStudentPayment(StudentVO vo);

	/**
	 * [HRD] 관리자>수강신청관리>삭제
	 * @param vo
	 * @return
	 */
	public abstract ProcessResultVO<StudentVO> deleteStudentPayment(StudentVO vo);
	
	public List<StudentVO> listStudentForAttend(StudentVO vo) throws Exception;
	
	public StudentVO viewStudentForAttend(StudentVO vo) throws Exception;
	
	@HrdApiStdStd(SyncType.CREATE)
	public abstract int addStudentForHrdApi(StudentVO vo);
	
	@HrdApiStdStd(SyncType.UPDATE)
	public abstract int addStudentIdeUrl(StudentVO vo);
	
	@HrdApiStdStd(SyncType.DELETE)
	public abstract int deleteStudentForHrdApi(StudentVO vo);

	@HrdApiStdStd(SyncType.UPDATE)
	public abstract int updateStudentEnrlStsForHrdApi(StudentVO vo);

	/**
	 * 수강생 번호로 수강생의 결제내역 단건 조회(수강생 기준)
	 */
	StudentVO viewStudentPaymentInfoByStdNo(StudentVO iStudentVO);
	
	/**
	 * 환불 신청(수강생)
	 * @param vo
	 * @return
	 */
	@RefundHistory(value = RefundType.REQUEST)
	@HrdApiStdStd(SyncType.UPDATE)
	public abstract int updateAddRefund(StudentVO vo);
	
	/**
	 * 환불 완료(관리자 처리)
	 * @param vo
	 * @return
	 */
	@RefundHistory(value = RefundType.ADMIN_COMPLETE)
	@HrdApiStdStd(SyncType.UPDATE)
	public abstract int updateRefundComplete(StudentVO vo);
	
	/**
	 * 환불 취소(관리자 처리)
	 * @param vo
	 * @return
	 */
	@RefundHistory(value = RefundType.CANCEL)
	@HrdApiStdStd(SyncType.UPDATE)
	int updateRefundCancel(StudentVO vo);
	
	/**
	 * 환불 메모 수정(관리자)
	 * @param vo
	 * @return
	 */
	@RefundHistory(value = RefundType.MEMO)
	public abstract int updateRefundMemo(StudentVO vo);

	/**
	 * [HRD] 수강생 결제 내역 - 취소
	 */
	@RefundHistory(value = RefundType.USER_COMPLETE)
	int cancelStudentPaymentByStudent(StudentVO vo);
	
	/**
	 * 환불 내역 리스트 조회
	 * @param vo
	 * @return
	 */
	List<StudentVO> listStdPayRefundHsty(StudentVO vo);
	
	/**
	 * 환불 내역 리스트 페이징 조회
	 * @param vo
	 * @return
	 */
	ProcessResultListVO<StudentVO> listStdPayRefundHstyPaging(StudentVO vo);

	
	
	/**
	 * 자격증 과정 목록
	 * @param vo
	 * @return
	 */
	public abstract List<StudentVO>  listStdCertCourse(CourseVO vo) throws Exception;
	
	/**
	 * 자격증 과정 정보(개인)
	 * @param vo
	 * @return
	 */
	public abstract StudentVO stdCertCourse(StudentVO vo) throws Exception;
	
	/**
	 * 자격증 신청(수강생)
	 * @param vo
	 * @return
	 */
	public abstract ProcessResultVO<StudentVO> certAplcStudent(StudentVO iStudentVO)  throws Exception;
	
	/**
	 * 자격증 승인(관리자)
	 * @param vo
	 * @return
	 */
	public abstract ProcessResultVO<StudentVO> certAplcStudentByAdmin(StudentVO iStudentVO)  throws Exception;
	
	/**
	 * 자격증 과정 합격처리
	 * @param request
	 *
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<StudentVO> confirmStudentCertPass(StudentVO iStudentVO) throws Exception;
	
	/**
	 * 자격증 과정 불합격처리
	 * @param request
	 *
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<StudentVO> cancelStudentCertPass(StudentVO iStudentVO) throws Exception;
	
	
	/***************************************************** 
	 * 학습자 수료증을 정보를 조회한다.
	 * @param vo
	 * @throws Exception
	 ******************************************************/ 
	public HashMap<String, Object> selectStdCertInfo(StudentVO vo) throws Exception;

	
}