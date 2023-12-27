package egovframework.edutrack.modules.log.apisync.service.impl;

import java.util.List;

import egovframework.edutrack.modules.log.apisync.service.LogApiSyncVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Mapper("logApiSyncMapper")
public interface LogApiSyncMapper {

	/**
	 * api 전송 이력 리스트 조회
	 * @param  LogApiSyncVO 
	 * @return List<EgovMap>
	 * @throws Exception
	 */
	public List<EgovMap> listApiSync(LogApiSyncVO vo) throws Exception; 

	/**
	 * api 전송 이력 조회
	 * @param  LogApiSyncVO 
	 * @return EgovMap
	 * @throws Exception
	 */
	public EgovMap selectApiSync(LogApiSyncVO vo) throws Exception; 
	
	/**
	 * api 전송 이력 등록
	 * @param  LogApiSyncVO 
	 * @return int
	 * @throws Exception
	 */
	public int insertApiSync(LogApiSyncVO vo) throws Exception; 
	
	/**
	 * api 전송 이력 수정
	 * @param  LogApiSyncVO 
	 * @return int
	 * @throws Exception
	 */
	public int updateApiSync(LogApiSyncVO vo) throws Exception; 
	
	/**
	 * api 전송 이력 삭제
	 * @param  LogApiSyncVO 
	 * @return int
	 * @throws Exception
	 */
	public int deleteApiSync(LogApiSyncVO vo) throws Exception; 
	
}
