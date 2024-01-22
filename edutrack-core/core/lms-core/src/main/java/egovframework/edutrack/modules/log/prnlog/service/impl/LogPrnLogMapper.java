package egovframework.edutrack.modules.log.prnlog.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.board.bbs.service.BrdBbsAtclVO;
import egovframework.edutrack.modules.log.prnlog.service.LogPrnLogVO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 *  <b>로그 - 개인 정보 출력 로그</b> 영역  Mapper 클래스
 * @author Jamfam
 *
 */
@Mapper("logPrnLogMapper")
public interface LogPrnLogMapper  {

	/**
	 * 개인 정보 출력 로그 전체 목록을 조회한다. 
	 * @param  LogPrnLogVO 
	 * @return List
	 * @
	 */
	
	public List<LogPrnLogVO> list(LogPrnLogVO vo) ;
	
    /**
	 * 개인 정보 출력 로그의 검색된 수를 조회한다. 
	 * @param  BrdBbsAtclVO 
	 * @return int
	 * @
	 */
	public int count(LogPrnLogVO vo) ;
	
    /**
	 * 개인 정보 출력 로그 페이징 목록을 조회한다. 
	 * @param  LogPrnLogVO 
	 * @return List
	 * @
	 */
	
	public List<LogPrnLogVO> listPageing(LogPrnLogVO vo) ;
	
    /**
	 * 개인 정보 출력 로그의 정보를 조회한다. 
	 * @param  LogPrnLogVO 
	 * @return LogPrnLogVO
	 * @
	 */
	public LogPrnLogVO select(LogPrnLogVO vo) ;

    /**
     * 개인 정보 출력 로그의 정보를 등록한다.  
     * @param  LogPrnLogVO
     * @return String
     * @
     */
    public int insert(LogPrnLogVO vo) ;
}
