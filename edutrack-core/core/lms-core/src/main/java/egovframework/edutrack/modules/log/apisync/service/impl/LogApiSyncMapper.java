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
	 * @
	 */
	public List<EgovMap> listApiSync(LogApiSyncVO vo) ; 

	/**
	 * api 전송 이력 조회
	 * @param  LogApiSyncVO 
	 * @return EgovMap
	 * @
	 */
	public EgovMap selectApiSync(LogApiSyncVO vo) ; 
	
	/**
	 * api 전송 이력 등록
	 * @param  LogApiSyncVO 
	 * @return int
	 * @
	 */
	public int insertApiSync(LogApiSyncVO vo) ; 
	
	/**
	 * api 전송 이력 수정
	 * @param  LogApiSyncVO 
	 * @return int
	 * @
	 */
	public int updateApiSync(LogApiSyncVO vo) ; 
	
	/**
	 * api 전송 이력 삭제
	 * @param  LogApiSyncVO 
	 * @return int
	 * @
	 */
	public int deleteApiSync(LogApiSyncVO vo) ; 
	
}
