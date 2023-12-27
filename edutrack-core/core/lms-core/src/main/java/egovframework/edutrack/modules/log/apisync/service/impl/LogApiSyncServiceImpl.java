package egovframework.edutrack.modules.log.apisync.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.modules.log.apisync.service.LogApiSyncService;
import egovframework.edutrack.modules.log.apisync.service.LogApiSyncVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Service("logApiSyncService")
public class LogApiSyncServiceImpl 
extends EgovAbstractServiceImpl  implements LogApiSyncService{
	
	/** Mapper */
    @Resource(name="logApiSyncMapper")
    private LogApiSyncMapper 		logApiSyncMapper;
	
	/**
	 * api 전송 이력 리스트 조회
	 * @param  LogApiSyncVO 
	 * @return ProcessResultListVO<EgovMap>
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<EgovMap> listApiSync(LogApiSyncVO vo) throws Exception{
		ProcessResultListVO<EgovMap> resultList = new ProcessResultListVO<EgovMap>(); 
 		try {
 			List<EgovMap> logList =  logApiSyncMapper.listApiSync(vo);
 			resultList.setResult(1);
 			resultList.setReturnList(logList);
 		} catch (Exception e){
 			e.printStackTrace();
 			resultList.setResult(-1);
 			resultList.setMessage(e.getMessage());
 		}
 		return resultList;
	}

	/**
	 * api 전송 이력 조회
	 * @param  LogApiSyncVO 
	 * @return EgovMap
	 * @throws Exception
	 */
	@Override
	public EgovMap selectApiSync(LogApiSyncVO vo) throws Exception{
		EgovMap resultMap = new EgovMap();
 		try {
 			resultMap =  logApiSyncMapper.selectApiSync(vo);
 		} catch (Exception e){
 			e.printStackTrace();
 		}
 		return resultMap;
	}
	
	/**
	 * api 전송 이력 등록
	 * @param  LogApiSyncVO 
	 * @return int
	 * @throws Exception
	 */
	public int insertApiSync(LogApiSyncVO vo) throws Exception{
		return logApiSyncMapper.insertApiSync(vo);
	}
	
	/**
	 * api 전송 이력 수정
	 * @param  LogApiSyncVO 
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int updateApiSync(LogApiSyncVO vo) throws Exception{
		return logApiSyncMapper.updateApiSync(vo);
	}
	
	/**
	 * api 전송 이력 삭제
	 * @param  LogApiSyncVO 
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int deleteApiSync(LogApiSyncVO vo) throws Exception{
		return logApiSyncMapper.deleteApiSync(vo);
	}
}
