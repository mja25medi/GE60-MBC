package egovframework.edutrack.modules.student.result.service;

import java.io.OutputStream;

import egovframework.edutrack.comm.annotation.HrdApiScore;
import egovframework.edutrack.comm.annotation.HrdApiStdStd;
import egovframework.edutrack.comm.annotation.HrdApiStdStd.SyncType;
import egovframework.edutrack.comm.exception.ServiceProcessException;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.modules.course.createcourse.service.CreateCourseVO;
import egovframework.edutrack.modules.student.worklog.service.StdEduRsltWorkLogVO;


public interface EduResultService {

	/**
	 * 수강 결과 목록 조회
	 *
	 * @return ProcessResultListVO
	 */
	public abstract ProcessResultListVO<EduResultVO> listResult(EduResultVO iEduResultVO) throws Exception;

	/**
	 * 수강 결과 목록 조회(페이징)
	 *
	 * @return ProcessResultListVO
	 */
	public abstract ProcessResultListVO<EduResultVO> listResultPaging(EduResultVO iEduResultVO) throws Exception;

	/**
	 * 수강 결과 정보 조회
	 *
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<EduResultVO> viewResult(EduResultVO iEduResultVO) throws Exception;
	
	/**
	 * crm 성적정보 조회
	 *
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultListVO<EduResultVO> listCrmEduResultPaging(EduResultVO vo) throws Exception;


	/**
	 * 수강 결과 등록
	 *
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<EduResultVO> addResult(EduResultVO iEduResultVO, StdEduRsltWorkLogVO lvo) throws Exception;

	/**
	 * 수강 결과 등록
	 *
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<EduResultVO> addResultAll(EduResultVO iEduResultVO, String strStdNo,
			String strPrgrScore, String strAttdScore, String strExamScore, String strSemiExamScore, String strAsmtScore,
			String strForumScore, String strJoinScore, String strEtcScore,
			String strTotScore, String strConvScore, StdEduRsltWorkLogVO lvo) throws Exception;

	/**
	 * 엑셀 파일 다운로드
	 * @throws Exception 
	 */
	public abstract void listExcelDownload(EduResultVO eduResultVO, CreateCourseVO createCourseVO, OutputStream os) throws ServiceProcessException, Exception;

	public abstract void listExcelDownloadForMngStd(EduResultVO vo, CreateCourseVO ccvo ,OutputStream os) throws Exception;
	
	/**
	 * 엑셀 파일 업로드 (성적 저장)
	 */
	public abstract ProcessResultVO<EduResultVO> addExcelUpload(EduResultVO eduResultVO, CreateCourseVO createCourseVO, String fileName, String filePath) throws Exception;

	/**
	 * 과정 수강생의 성적을 자동으로 생성한다.
	 * 시험, 과제 등의 성적을 가져와 다시 셋팅한다.
	 * 임의로 입력된 값이 초기화 된다.
	 * @param iEduResultVO
	 * @return
	 */
	public abstract ProcessResultVO<EduResultVO> addAutoResult(EduResultVO iEduResultVO, StdEduRsltWorkLogVO lvo) throws Exception;

	/**
	 * 트랜잭션 테스트용 매소드(테스트에서만 사용된다.)
	 */
	@Deprecated
	public abstract ProcessResultVO<EduResultVO> transactionRollbackMethod(EduResultVO iEduResultVO)
			throws Exception;
	
	@HrdApiStdStd(SyncType.UPDATE)
	@HrdApiScore
	ProcessResultVO<EduResultVO> addEduResultSp(EduResultVO vo, Object param);

}