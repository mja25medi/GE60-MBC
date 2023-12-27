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
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<LogSysConnLogVO> listMonth(LogSysConnLogVO vo) throws Exception;
	
    /**
	 * 주별 시스템 접속 로그 목록을 조회한다. 
	 * @param  LogSysConnLogVO 
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<LogSysConnLogVO> listWeek(LogSysConnLogVO vo) throws Exception;
	
    /**
	 * 일별 시스템 접속 로그 목록을 조회한다. 
	 * @param  LogSysConnLogVO 
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<LogSysConnLogVO> listDay(LogSysConnLogVO vo) throws Exception;
	
    /**
	 * 시간별 시스템 접속 로그 목록을 조회한다. 
	 * @param  LogSysConnLogVO 
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<LogSysConnLogVO> listHour(LogSysConnLogVO vo) throws Exception;
	
	/**
	 * 월별 시스템 모든 접속 로그 목록을 조회한다. 
	 * @param  LogSysConnLogVO 
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<LogSysConnLogVO> AllListMonth(LogSysConnLogVO vo) throws Exception;
	
    /**
	 * 주별 시스템 모든 접속 로그 목록을 조회한다. 
	 * @param  LogSysConnLogVO 
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<LogSysConnLogVO> AllListWeek(LogSysConnLogVO vo) throws Exception;
	
    /**
	 * 일별 시스템 모든 접속 로그 목록을 조회한다. 
	 * @param  LogSysConnLogVO 
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<LogSysConnLogVO> AllListDay(LogSysConnLogVO vo) throws Exception;
	
    /**
	 * 시간별 시스템 모든 접속 로그 목록을 조회한다. 
	 * @param  LogSysConnLogVO 
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<LogSysConnLogVO> AllListHour(LogSysConnLogVO vo) throws Exception;
	
	/**
	 * 월별 시스템 모든 접속 로그 목록을 조회한다. 
	 * @param  LogSysConnLogVO 
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<LogSysConnLogVO> sysListMonth(LogSysConnLogVO vo) throws Exception;
	
    /**
	 * 주별 시스템 모든 접속 로그 목록을 조회한다. 
	 * @param  LogSysConnLogVO 
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<LogSysConnLogVO> sysListWeek(LogSysConnLogVO vo) throws Exception;
	
    /**
	 * 일별 시스템 모든 접속 로그 목록을 조회한다. 
	 * @param  LogSysConnLogVO 
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<LogSysConnLogVO> sysListDay(LogSysConnLogVO vo) throws Exception;
	
    /**
	 * 시간별 시스템 모든 접속 로그 목록을 조회한다. 
	 * @param  LogSysConnLogVO 
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<LogSysConnLogVO> sysListHour(LogSysConnLogVO vo) throws Exception;
	
    /**
	 * 자동 날짜 조회. 
	 * @param  LogSysConnLogVO 
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public LogSysConnLogVO selectAutoDate(LogSysConnLogVO vo) throws Exception;
	
    /**
	 * 시간별 시스템 접속 로그 목록을 조회한다. 
	 * @param  LogSysConnLogVO 
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<LogSysConnLogVO> listHourByDay(LogSysConnLogVO vo) throws Exception;
	
    /**
	 * 요일별 시스템 접속 로그 목록을 조회한다. 
	 * @param  LogSysConnLogVO 
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<LogSysConnLogVO> listDayByWeek(LogSysConnLogVO vo) throws Exception;
	
    /**
	 * 일자별 시스템 접속 로그 목록을 조회한다. 
	 * @param  LogSysConnLogVO 
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<LogSysConnLogVO> listDayByMonth(LogSysConnLogVO vo) throws Exception;
	

    /**
     * 시스템 접속 로그의 상세 정보를 등록한다.  
     * @param  LogSysConnLogVO
     * @return String
     * @throws Exception
     */
    public int insert(LogSysConnLogVO vo) throws Exception;
}
