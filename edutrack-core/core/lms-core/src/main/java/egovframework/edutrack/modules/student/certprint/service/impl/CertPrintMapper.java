package egovframework.edutrack.modules.student.certprint.service.impl;

import java.util.List;

import egovframework.edutrack.modules.student.certprint.service.CertPrintVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("certPrintMapper")
public interface CertPrintMapper {
	/**
	 * Select Key 조회
	 * @param int
	 * @return
	 */
	public int selectKey()  ;

	/**
	 * 출력 로그 목록 조회 (전체)
	 * 
	 * @return ProcessReslutListVO
	 */
	
	public List<CertPrintVO> listPrint(CertPrintVO iCertPrintVO) ;

	/**
	 * 출력 로그 목록 조회 (페이징)
	 * 
	 * @return ProcessReslutListVO
	 */
	
	public List<CertPrintVO> listPrint(CertPrintVO iCertPrintVO, int curPage,
			int listScale, int pageScale) ;

	/**
	 * 학습결과 등록
	 *  
	 * @reurn 
	 */
	public int insertPrint(CertPrintVO iCertPrintVO) ;

	/**
	 * 교부 번호 구하여 반환
	 *  
	 * @return 
	 */
	public CertPrintVO selectPrintNo() ;
}