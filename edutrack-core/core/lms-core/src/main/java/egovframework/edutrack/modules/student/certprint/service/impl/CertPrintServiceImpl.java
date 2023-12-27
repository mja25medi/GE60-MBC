package egovframework.edutrack.modules.student.certprint.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.modules.student.certprint.service.CertPrintService;
import egovframework.edutrack.modules.student.certprint.service.CertPrintVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;


@Service("certPrintService")
public class CertPrintServiceImpl extends EgovAbstractServiceImpl implements CertPrintService {
	
	/** Mapper */
	@Resource(name="certPrintMapper")
	private CertPrintMapper 		certPrintMapper;
	
	/**
	 * 수료증 출력 목록 조회 : 전체
	 * 
	 * @return ProcessResultListVO
	 * @throws Exception 
	 */
	public ProcessResultListVO<CertPrintVO> list(CertPrintVO iCertPrintVO) throws Exception {
		
		ProcessResultListVO<CertPrintVO> resultList = new ProcessResultListVO<CertPrintVO>(); 
		try {
			List<CertPrintVO> returnList = certPrintMapper.listPrint(iCertPrintVO);
			resultList.setResult(1);
			resultList.setReturnList(returnList);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
		
	}
	
	/**
	 * 수료증 출력 목록 조히 : 페이징
	 * 
	 * @return ProcessResultListVO
	 * @throws Exception 
	 */
	public ProcessResultListVO<CertPrintVO> list(CertPrintVO iCertPrintVO, int curPage, int listScale, int pageScale ) throws Exception {
		
		ProcessResultListVO<CertPrintVO> resultList = new ProcessResultListVO<CertPrintVO>(); 
		try {
			List<CertPrintVO> returnList =certPrintMapper.listPrint(iCertPrintVO, curPage, listScale, pageScale);
			resultList.setResult(1);
			resultList.setReturnList(returnList);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
		
	}
	
	/**
	 * 수료증 출력 정보 등록
	 *
	 * @return  ProcessResultVO
	 * @throws Exception 
	 */
	public ProcessResultVO<CertPrintVO> add(CertPrintVO iCertPrintVO) throws Exception{
		ProcessResultVO<CertPrintVO> resultVO = new ProcessResultVO<CertPrintVO>(); 
		try {
			iCertPrintVO.setPrnSn(certPrintMapper.selectKey());
			certPrintMapper.insertPrint(iCertPrintVO);
			resultVO.setResult(1);
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResult(-1);
			resultVO.setMessage(e.getMessage());
		}
		return resultVO;
	}
	
	/**
	 * 수료증 출력 정보 등록
	 *
	 * @return  ProcessResultVO
	 * @throws Exception 
	 */
	public ProcessResultVO<CertPrintVO> selectPrintNo() throws Exception{
		ProcessResultVO<CertPrintVO> resultVO = new ProcessResultVO<CertPrintVO>(); 
		try {
			CertPrintVO returnVO =  certPrintMapper.selectPrintNo();
			resultVO.setResult(1);
			resultVO.setReturnVO(returnVO);
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResult(-1);
			resultVO.setMessage(e.getMessage());
		}
		return resultVO;
		
	}
}