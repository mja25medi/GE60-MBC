package egovframework.edutrack.modules.log.apisync.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

public interface LogApiSyncService {
	
	/**
	 * api 전송 이력 리스트 조회
	 * @param  LogApiSyncVO 
	 * @return List<EgovMap>
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<EgovMap> listApiSync(LogApiSyncVO vo) throws Exception; 

	/**
	 * api 전송 이력 조회
	 * @param  LogApiSyncVO 
	 * @return EgovMap
	 * @throws Exception
	 */
	public abstract EgovMap selectApiSync(LogApiSyncVO vo) throws Exception; 
	
	/**
	 * api 전송 이력 등록
	 * @param  LogApiSyncVO 
	 * @return int
	 * @throws Exception
	 */
	public abstract int insertApiSync(LogApiSyncVO vo) throws Exception; 
	
	/**
	 * api 전송 이력 수정
	 * @param  LogApiSyncVO 
	 * @return int
	 * @throws Exception
	 */
	public abstract int updateApiSync(LogApiSyncVO vo) throws Exception; 
	
	/**
	 * api 전송 이력 삭제
	 * @param  LogApiSyncVO 
	 * @return int
	 * @throws Exception
	 */
	public abstract int deleteApiSync(LogApiSyncVO vo) throws Exception;
}
