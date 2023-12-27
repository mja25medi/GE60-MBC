package egovframework.edutrack.modules.student.certprint.service;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;


public interface CertPrintService {

	/**
	 * 수료증 출력 목록 조회 : 전체
	 * 
	 * @return ProcessResultListVO
	 */
	public abstract ProcessResultListVO<CertPrintVO> list(CertPrintVO iCertPrintVO) throws Exception;

	/**
	 * 수료증 출력 목록 조히 : 페이징
	 * 
	 * @return ProcessResultListVO
	 */
	public abstract ProcessResultListVO<CertPrintVO> list(CertPrintVO iCertPrintVO, int curPage, int listScale, int pageScale) throws Exception;

	/**
	 * 수료증 출력 정보 등록
	 *
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<CertPrintVO> add(CertPrintVO iCertPrintVO) throws Exception;

	/**
	 * 수료증 출력 정보 등록
	 *
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<CertPrintVO> selectPrintNo() throws Exception;
}