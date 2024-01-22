package egovframework.edutrack.modules.log.sysconn.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.edutrack.modules.log.sysconn.service.LogSysConnLogVO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 *  <b>로그 - 관리자 접속 로그</b> 영역  Mapper 클래스
 * @author Jamfam
 *
 */
@Mapper("logSysConnLogMapper")
public interface LogSysConnLogMapper  {

    /**
	 * 월별 시스템 접속 로그 목록을 조회한다. 
	 * @param  LogSysConnLogVO 
	 * @return List
	 * @
	 */
	
	public List<LogSysConnLogVO> listMonth(LogSysConnLogVO vo) ;
	
    /**
	 * 주별 시스템 접속 로그 목록을 조회한다. 
	 * @param  LogSysConnLogVO 
	 * @return List
	 * @
	 */
	
	public List<LogSysConnLogVO> listWeek(LogSysConnLogVO vo) ;
	
    /**
	 * 일별 시스템 접속 로그 목록을 조회한다. 
	 * @param  LogSysConnLogVO 
	 * @return List
	 * @
	 */
	
	public List<LogSysConnLogVO> listDay(LogSysConnLogVO vo) ;
	
    /**
	 * 시간별 시스템 접속 로그 목록을 조회한다. 
	 * @param  LogSysConnLogVO 
	 * @return List
	 * @
	 */
	
	public List<LogSysConnLogVO> listHour(LogSysConnLogVO vo) ;
	
	/**
	 * 월별 시스템 모든 접속 로그 목록을 조회한다. 
	 * @param  LogSysConnLogVO 
	 * @return List
	 * @
	 */
	
	public List<LogSysConnLogVO> AllListMonth(LogSysConnLogVO vo) ;
	
    /**
	 * 주별 시스템 모든 접속 로그 목록을 조회한다. 
	 * @param  LogSysConnLogVO 
	 * @return List
	 * @
	 */
	
	public List<LogSysConnLogVO> AllListWeek(LogSysConnLogVO vo) ;
	
    /**
	 * 일별 시스템 모든 접속 로그 목록을 조회한다. 
	 * @param  LogSysConnLogVO 
	 * @return List
	 * @
	 */
	
	public List<LogSysConnLogVO> AllListDay(LogSysConnLogVO vo) ;
	
    /**
	 * 시간별 시스템 모든 접속 로그 목록을 조회한다. 
	 * @param  LogSysConnLogVO 
	 * @return List
	 * @
	 */
	
	public List<LogSysConnLogVO> AllListHour(LogSysConnLogVO vo) ;
	
	/**
	 * 월별 시스템 모든 접속 로그 목록을 조회한다. 
	 * @param  LogSysConnLogVO 
	 * @return List
	 * @
	 */
	
	public List<LogSysConnLogVO> sysListMonth(LogSysConnLogVO vo) ;
	
    /**
	 * 주별 시스템 모든 접속 로그 목록을 조회한다. 
	 * @param  LogSysConnLogVO 
	 * @return List
	 * @
	 */
	
	public List<LogSysConnLogVO> sysListWeek(LogSysConnLogVO vo) ;
	
    /**
	 * 일별 시스템 모든 접속 로그 목록을 조회한다. 
	 * @param  LogSysConnLogVO 
	 * @return List
	 * @
	 */
	
	public List<LogSysConnLogVO> sysListDay(LogSysConnLogVO vo) ;
	
    /**
	 * 시간별 시스템 모든 접속 로그 목록을 조회한다. 
	 * @param  LogSysConnLogVO 
	 * @return List
	 * @
	 */
	
	public List<LogSysConnLogVO> sysListHour(LogSysConnLogVO vo) ;
	
    /**
	 * 자동 날짜 조회. 
	 * @param  LogSysConnLogVO 
	 * @return List
	 * @
	 */
	
	public LogSysConnLogVO selectAutoDate(LogSysConnLogVO vo) ;
	
    /**
	 * 시간별 시스템 접속 로그 목록을 조회한다. 
	 * @param  LogSysConnLogVO 
	 * @return List
	 * @
	 */
	
	public List<LogSysConnLogVO> listHourByDay(LogSysConnLogVO vo) ;
	
    /**
	 * 요일별 시스템 접속 로그 목록을 조회한다. 
	 * @param  LogSysConnLogVO 
	 * @return List
	 * @
	 */
	
	public List<LogSysConnLogVO> listDayByWeek(LogSysConnLogVO vo) ;
	
    /**
	 * 일자별 시스템 접속 로그 목록을 조회한다. 
	 * @param  LogSysConnLogVO 
	 * @return List
	 * @
	 */
	
	public List<LogSysConnLogVO> listDayByMonth(LogSysConnLogVO vo) ;
	

    /**
     * 시스템 접속 로그의 상세 정보를 등록한다.  
     * @param  LogSysConnLogVO
     * @return String
     * @
     */
    public int insert(LogSysConnLogVO vo) ;
}
