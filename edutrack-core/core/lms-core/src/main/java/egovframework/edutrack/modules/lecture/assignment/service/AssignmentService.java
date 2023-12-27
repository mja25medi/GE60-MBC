package egovframework.edutrack.modules.lecture.assignment.service;

import java.io.OutputStream;
import java.util.List;

import egovframework.edutrack.comm.annotation.HrdApiScore;
import egovframework.edutrack.comm.annotation.HrdApiStdStd;
import egovframework.edutrack.comm.annotation.HrdApiStdStd.SyncType;
import egovframework.edutrack.comm.exception.ServiceProcessException;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.modules.course.createcoursesubject.service.CreateOnlineSubjectVO;

public interface AssignmentService {

	/**
	 * 과제 정보 목록 조회	
	 */
	public ProcessResultListVO<AssignmentVO> listAssignment(AssignmentVO iAssignmentVO) throws Exception ;
	
	/**
	 * 과제 정보 목록 조회	(학습자용)
	 */
	public	ProcessResultListVO<AssignmentVO> listAssignmentStd(AssignmentVO iAssignmentVO) throws Exception ;

	/**
	 * 과제 정보 조회
	 */
	public ProcessResultVO<AssignmentVO> viewAssignment(AssignmentVO iAssignmentVO) throws Exception ;

	/**
	 * 과제 정보 등록
	 */
	public ProcessResultVO<AssignmentVO> addAssignment(AssignmentVO iAssignmentVO) throws Exception ;

	/**
	 * 과제 등록 완료
	 */
	public ProcessResultVO<AssignmentVO> addRegistAssignment(AssignmentVO iAssignmentVO) throws Exception ;

	/**
	 * 과제 정보 수정
	 */
	public ProcessResultVO<AssignmentVO> editAssignment(AssignmentVO iAssignmentVO) throws Exception ;

	/**
	 * 과제 정보 삭제
	 */
	public ProcessResultVO<AssignmentVO> deleteAssignment(AssignmentVO iAssignmentVO) throws Exception ;
	

	/**
	 * 과제 제출 (학습자)
	 * @param iAssignmentSendVO
	 * @return
	 */
	public	ProcessResultVO<AssignmentSendVO> addSend(AssignmentSendVO iAssignmentSendVO) throws Exception ;
	
	/**
	 * 과제 제출하기 위해 화면 들어갈 때  (학습자)
	 * @param iAssignmentSendVO
	 * @param string 
	 * @return
	 */
	//@HrdApiScore(category = Category.ASMT, saveType = SaveType.START)
	@HrdApiScore
	public	ProcessResultVO<AssignmentSendVO> addEnterSend(AssignmentSendVO iAssignmentSendVO, String addYn) throws Exception ;
	
	
	/**
	 * 과제 수정 제출(학습자)
	 */
	//@HrdApiScore(category = Category.ASMT, saveType = SaveType.END)
	public ProcessResultVO<AssignmentSendVO> editSend(AssignmentSendVO iAssignmentSendVO) throws Exception ;
	
	/**
	 * 과제 후보 목록 조회
	 */

	/**
	 * 과제 후보 목록 조회(과제 선택 유형 코드R,S) - 수강생 조회, 문제 조회, insert 한번에(HRD 미사용)
	 */
	public ProcessResultVO<AssignmentSubVO> randomReadSub(AssignmentVO iAssignmentVO) throws Exception ;

	/**
	 * 과제 후보 목록 조회(과제 선택 유형 코드R) - 문제 조회만
	 */
	ProcessResultVO<AssignmentSubVO> viewOnlySubRandomRead(AssignmentVO iAssignmentVO) throws Exception ;
	
	/**
	 * 과제 후보 정보 조회
	 */
	public ProcessResultVO<AssignmentSubVO> viewSub(AssignmentSubVO iAssignmentSubVO) throws Exception ;

	/**
	 * 과제 후보 정보 등록
	 */
	public ProcessResultVO<AssignmentSubVO> addSub(AssignmentSubVO iAssignmentSubVO) throws Exception ;
	
	/**
	 * 과제 문제 정보 등록 (문제 은행에서 가져오기)
	 */
	public	ProcessResultVO<AssignmentSubVO> addQbankSub(AssignmentVO iAssignmentVO) throws Exception ;

	/**
	 * 과제 후보 정보 수정
	 */
	public ProcessResultVO<AssignmentSubVO> editSub(AssignmentSubVO iAssignmentSubVO) throws Exception ;

	/**
	 * 선텍 제출 과제 후보 정보 수정
	 */
	public  ProcessResultVO<AssignmentSubVO> selectEditSub(AssignmentSubVO iAssignmentSubVO) throws Exception ;
		
		
	/**
	 * 과제 후보 정보 삭제
	 */
	public ProcessResultVO<AssignmentSubVO> deleteSub(AssignmentSubVO iAssignmentSubVO) throws Exception ;

	/**
	 * 과제 후보 파일 목록 조회	
	 */
	public ProcessResultVO<AssignmentSubVO> listSubFile(
			AssignmentSubVO iAssignmentSubVO) throws Exception ;

	/**
	 * 과제 응시자 목록 조회	
	 */
	public ProcessResultListVO<AssignmentSendVO> listSend(AssignmentVO iAssignmentVO) throws Exception ;

	/**
	 * 과제 응시자 목록 조회 페이징	
	 */
	public ProcessResultListVO<AssignmentSendVO> listSendPageing(AssignmentVO iAssignmentVO) throws Exception ;

	/**
	 * 과제 응시 결과 정보 조회
	 */
	public ProcessResultVO<AssignmentSendVO> viewSend(
			AssignmentSendVO iAssignmentSendVO) throws Exception ;

	/**
	 * 과제 평가 등록
	 */
	//@HrdApiScore(category = Category.ASMT, saveType = SaveType.RATE)
	public ProcessResultVO<AssignmentSendVO> addSendRate(AssignmentSendVO iAssignmentSendVO) throws Exception ;
	
	/**
	 * 과제 점수 등록
	 */
	public ProcessResultVO<AssignmentSendVO> addSendScore(AssignmentSendVO iAssignmentSendVO) throws Exception ;

	/**
	 * 과제 점수 등록
	 */
	public ProcessResultVO<AssignmentSendVO> addSendScoreAll(
			AssignmentSendVO iAssignmentSendVO, String strStdNo, String strScore) throws Exception ;
	
	/**
	 * 과제 초기화
	 */
	public	ProcessResultVO<AssignmentSendVO> removeSend(AssignmentSendVO assignmentSendVO) throws Exception ;
	
	public ProcessResultVO<AssignmentSendVO> removeSend(AssignmentSendVO assignmentSendVO, List<AssignmentSendVO> asmtSendCreList) throws Exception;

	/**
	 * 성적 다운로드
	 * @author twkim 
	 * @date 2013. 11. 25.
	 * @param iAssignmentVO
	 * @param os
	 * @throws ServiceProcessException void
	 */
	public void listReshCourseExcelDownload(AssignmentVO iAssignmentVO, OutputStream os) throws ServiceProcessException;
	
	/**
	 * 성적 업로드
	 * @author twkim 
	 * @date 2013. 11. 27.
	 * @param assignmentSendVO
	 * @param fileName
	 * @param filePath
	 * @return
	 * @throws ServiceProcessException ProcessResultVO<AssignmentSendVO>
	 */
	public ProcessResultVO<AssignmentSendVO> addExcelUpload(AssignmentSendVO assignmentSendVO, String fileName, String filePath) throws ServiceProcessException;

	/**
	 * 과제 후보 목록 조회
	 */
	public	ProcessResultListVO<AssignmentSubVO> listSub(AssignmentVO iAssignmentVO) throws Exception;
	
	public List<AssignmentVO> listAsmtScore(AssignmentVO vo) throws Exception;

	public ProcessResultListVO<AssignmentSubConstVO> listSubConst(AssignmentSubConstVO cVO) throws Exception;

	public ProcessResultListVO<AssignmentVO> listAssignmentCount(AssignmentVO vo)  throws Exception;

	/**
	 * 코딩 과제 후보 목록 조회(제출여부 포함)
	 * @throws Exception 
	 */
	public ProcessResultListVO<AssignmentSubVO> listCodeSub(AssignmentVO avo) throws Exception;
	/**
	 * 코딩 과제 form insert
	 * @throws Exception 
	 */
	public ProcessResultVO<AssignmentSendVO> addSendCodeAsmt(AssignmentSendVO vo) throws Exception;
	/**
	 * 코딩과제 채점 결과 조회
	 * @throws Exception 
	 */
	public ProcessResultVO<AssignmentSendVO> selectTestResult(AssignmentSendVO vo) throws Exception;

	/**
	 * 코딩과제 문제별 점수 저장
	 * @throws Exception 
	 */
	public ProcessResultVO<AssignmentSendVO> saveAsmtSubScore(AssignmentSendVO vo) throws Exception;

	public ProcessResultVO<AssignmentSendVO> addOffSendScore(AssignmentSendVO vo) throws Exception;

	public ProcessResultVO<AssignmentSendVO> addOffSendScoreAll(AssignmentSendVO vo, String strStdNo, String strScore) throws Exception;

}