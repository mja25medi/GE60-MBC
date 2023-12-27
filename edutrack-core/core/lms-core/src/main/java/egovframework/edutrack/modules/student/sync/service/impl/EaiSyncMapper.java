package egovframework.edutrack.modules.student.sync.service.impl;

import java.util.List;

import egovframework.edutrack.modules.student.sync.service.EaiNkoreducompsendVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("eaiSyncMapper")
public interface EaiSyncMapper {

	/**
	 * Eai 연동 목록 조회
	 * @param  EaiNkoreducompsendVO.linkstatus
	 * @return ProcessReslutListVO(EaiNkoreducompsendVO)
	 */
	@SuppressWarnings("unchecked")
	public List<EaiNkoreducompsendVO> list(
			EaiNkoreducompsendVO iEaiNkoreducompsendVO) throws Exception;

	/**
	 * Eai 연동 목록 조회 (페이징 처리)
	 * @param  EaiNkoreducompsendVO.linkstatus
	 * @return ProcessReslutListVO(EaiNkoreducompsendVO)
	 */
	@SuppressWarnings("unchecked")
	public List<EaiNkoreducompsendVO> listPageing(
			EaiNkoreducompsendVO iEaiNkoreducompsendVO, int curPage, int listScale, int pageScale) throws Exception;
	
	/**
	 * Eai 연동 목록 조회 (페이징 처리)
	 * @param  EaiNkoreducompsendVO.linkstatus
	 * @return int
	 */
	@SuppressWarnings("unchecked")
	public int count(
			EaiNkoreducompsendVO iEaiNkoreducompsendVO) throws Exception;
	

	/**
	 * EAI 전송 초기화(EAI_NKOREDUCOMPSEND) 
	 * @reurn ProcessResultVO
	 */
	public int updateNkoreducompsend(
			EaiNkoreducompsendVO iEaiNkoreducompsendVO) throws Exception;

	/**
	 * EAI 전송 초기화(TBEAISENDRECVSTSMT) 
	 * @reurn ProcessResultVO
	 */
	public int updateEaisendrecvstsmt(
			EaiNkoreducompsendVO iEaiNkoreducompsendVO) throws Exception;


}