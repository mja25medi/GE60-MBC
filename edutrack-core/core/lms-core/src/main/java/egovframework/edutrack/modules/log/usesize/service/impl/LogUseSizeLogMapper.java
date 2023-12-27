package egovframework.edutrack.modules.log.usesize.service.impl;

import org.springframework.stereotype.Repository;

import egovframework.edutrack.modules.log.usesize.service.LogUseSizeLogVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 *  <b>로그 - 사용량 로그</b> 영역  Mapper 클래스
 * @author Jamfam
 *
 */
@Mapper("logUseSizeLogMapper")
public interface LogUseSizeLogMapper  {

    /**
	 * 사용량 로그를 조회한다. 
	 * @param  LogUseSizeLogVO 
	 * @return LogUseSizeLogVO
	 * @throws Exception
	 */
	public LogUseSizeLogVO select(LogUseSizeLogVO vo) throws Exception;
	
    /**
	 * 파일 사용량 로그를 조회한다. 
	 * @param  LogUseSizeLogVO 
	 * @return LogUseSizeLogVO
	 * @throws Exception
	 */
	public LogUseSizeLogVO selectAtchSize(LogUseSizeLogVO vo) throws Exception;
	
    /**
     * 사용량 로그 정보를 등록한다.  
     * @param  LogUseSizeLogVO
     * @return String
     * @throws Exception
     */
    public int insert(LogUseSizeLogVO vo) throws Exception;
}
