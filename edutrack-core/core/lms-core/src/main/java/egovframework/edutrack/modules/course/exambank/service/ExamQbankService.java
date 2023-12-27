package egovframework.edutrack.modules.course.exambank.service;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.springframework.transaction.annotation.Transactional;

import egovframework.edutrack.comm.exception.ServiceProcessException;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;

public interface ExamQbankService {

	/**
	 * 시험 문제은행 분류 목록 조회
	 *
	 * @return ProcessResultListVO
	 */
	@Transactional(readOnly = true)
	public ProcessResultListVO<ExamQbankCtgrVO> listCtgr(ExamQbankCtgrVO vo) throws Exception;

	/**
	 * 시험 문제은행 하위 분류 목록 조회
	 *
	 * @return ProcessResultListVO
	 */
	@Transactional(readOnly = true)
	public ProcessResultListVO<ExamQbankCtgrVO> subListCtgr(String parCtgrCd) throws Exception;
	
	/**
	 * 시험 문제은행 분류 정보 조회
	 *
	 * @return  ProcessResultVO
	 */
	@Transactional(readOnly = true)
	public ProcessResultVO<ExamQbankCtgrVO> viewCtgr(String ctgrCd) throws Exception;

	/**
	 * 시험 문제은행 분류 정보 등록
	 *
	 * @return  ProcessResultVO
	 */
	public ProcessResultVO<ExamQbankCtgrVO> addCtgr(
			ExamQbankCtgrVO VO) throws Exception;

	/**
	 * 시험 문제은행 분류 정보 수정
	 *
	 * @return  ProcessResultVO
	 */
	public ProcessResultVO<ExamQbankCtgrVO> editCtgr(
			ExamQbankCtgrVO VO) throws Exception;

	/**
	 * 시험 문제은행 분류 정보 삭제
	 *
	 * @return  ProcessResultVO
	 */
	public ProcessResultVO<ExamQbankCtgrVO> deleteCtgr(String ctgrCd) throws Exception;

	/**
	 * 시험 문제은행 문제 목록 조회
	 *
	 * @return ProcessResultListVO
	 */
	@Transactional(readOnly = true)
	public ProcessResultListVO<ExamQbankQstnVO> listQstn(String ctgrCd,
			String parCtgrCd, String qstnType, String searchKey) throws Exception;

	/**
	 * 시험 문제은행 문제 정보 조회
	 *
	 * @return  ProcessResultVO
	 */
	@Transactional(readOnly = true)
	public ProcessResultVO<ExamQbankQstnVO> viewQstn(ExamQbankQstnVO VO) throws Exception;

	/**
	 * 시험 문제은행 문제 정보 등록
	 *
	 * @return  ProcessResultVO
	 */
	public ProcessResultVO<ExamQbankQstnVO> addQstn(ExamQbankQstnVO VO) throws Exception;

	/**
	 * 시험 문제은행 문제 정보 수정
	 *
	 * @return  ProcessResultVO
	 */
	public ProcessResultVO<ExamQbankQstnVO> editQstn(ExamQbankQstnVO VO) throws Exception;

	/**
	 * 시험 문제은행 문제 정보 삭제
	 *
	 * @return  ProcessResultVO
	 */
	public ProcessResultVO<ExamQbankQstnVO> deleteQstn(ExamQbankQstnVO VO) throws Exception;


	/**
	 * 샘플 엑셀파일 다운로드
	 * @param (HashMap<String, String> titles
	 * @param OutputStream os
	 * @throws ServiceProcessException
	 */
	public abstract void sampleExcelDownload(HashMap<String, String> titles, OutputStream os, String orgCd) throws ServiceProcessException;

	/**
	 * Upload 문제 정보 체크 하여 돌려 준다.
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public abstract ProcessResultListVO<ExamQbankQstnVO> excelUploadValidationCheck(
			String fileName, String filePath) throws ServiceProcessException;

	/**
	 * 시험 문제은행 문제 일괄 등록
	 * @param List<UserInfoVO> userList
	 * @return
	 */
	public abstract ProcessResultVO<ExamQbankQstnVO> addQstnBatch(
			List<ExamQbankQstnVO> questionBankList) throws Exception;
	
	/**
	 * 게시판 글 정보 조회 (조회수 미증가)
	 * @param articleVO 글 고유 번호
	 * @return 조회 결과 VO
	 */
	public abstract ProcessResultVO<ExamQbankQstnVO> getArticle(ExamQbankQstnVO VO) throws Exception;
	
	/**
	 * 시험 문제은행 하위 분류 목록 조회 (사용만)
	 *
	 * @return ProcessResultListVO
	 */
	@Transactional(readOnly = true)
	public ProcessResultListVO<ExamQbankCtgrVO> subListCtgrUseY(ExamQbankCtgrVO vo) throws Exception;
	
	/**
	 * 시험 문제은행 문제 사용 목록 조회
	 *
	 * @return ProcessResultListVO
	 */
	@Transactional(readOnly = true)
	public ProcessResultListVO<ExamQbankQstnVO> listQstnUseY(String ctgrCd,
			String parCtgrCd, String qstnType, String searchKey, String useYn) throws Exception;

}