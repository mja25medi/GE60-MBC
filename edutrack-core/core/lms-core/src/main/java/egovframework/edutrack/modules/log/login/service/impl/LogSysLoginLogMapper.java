package egovframework.edutrack.modules.log.login.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.edutrack.modules.log.login.service.LogSysLoginLogVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 *  <b>로그 - 사용자 로그인 로그</b> 영역  DAO 클래스
 * @author Jamfam
 *
 */
@Mapper("logSysLoginLogMapper")
public interface LogSysLoginLogMapper {

    /**
	 * 월별 시스템 로그인 로그 목록을 조회한다. 
	 * @param  LogSysLoginLogVO 
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<LogSysLoginLogVO> listMonth(LogSysLoginLogVO vo) throws Exception;
	
    /**
	 * 주별 시스템 로그인 로그 목록을 조회한다. 
	 * @param  LogSysLoginLogVO 
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<LogSysLoginLogVO> listWeek(LogSysLoginLogVO vo) throws Exception;
	
    /**
	 * 일별 시스템 로그인 로그 목록을 조회한다. 
	 * @param  LogSysLoginLogVO 
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<LogSysLoginLogVO> listDay(LogSysLoginLogVO vo) throws Exception;
	
    /**
	 * 시간별 시스템 로그인 로그 목록을 조회한다. 
	 * @param  LogSysLoginLogVO 
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<LogSysLoginLogVO> listHour(LogSysLoginLogVO vo) throws Exception;
	
	/**
	 * 월별 모든 시스템 로그인 로그 목록을 조회한다. 
	 * @param  LogSysLoginLogVO 
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<LogSysLoginLogVO> AllListMonth(LogSysLoginLogVO vo) throws Exception;
	
    /**
	 * 주별 모든 시스템 로그인 로그 목록을 조회한다. 
	 * @param  LogSysLoginLogVO 
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<LogSysLoginLogVO> AllListWeek(LogSysLoginLogVO vo) throws Exception;
	
    /**
	 * 일별 모든 시스템 로그인 로그 목록을 조회한다. 
	 * @param  LogSysLoginLogVO 
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<LogSysLoginLogVO> AllListDay(LogSysLoginLogVO vo) throws Exception;
	
    /**
	 * 시간별 모든 시스템 로그인 로그 목록을 조회한다. 
	 * @param  LogSysLoginLogVO 
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<LogSysLoginLogVO> AllListHour(LogSysLoginLogVO vo) throws Exception;
	
    /**
	 * 자동 날짜 조회. 
	 * @param  LogSysLoginLogVO 
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public LogSysLoginLogVO selectAutoDate(LogSysLoginLogVO vo) throws Exception;
	
    /**
	 * 시간별 시스템 로그인 로그 목록을 조회한다. 
	 * @param  LogSysLoginLogVO 
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<LogSysLoginLogVO> listHourByDay(LogSysLoginLogVO vo) throws Exception;
	
    /**
	 * 요일별 시스템 로그인 로그 목록을 조회한다. 
	 * @param  LogSysLoginLogVO 
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<LogSysLoginLogVO> listDayByWeek(LogSysLoginLogVO vo) throws Exception;
	
    /**
	 * 일자별 시스템 로그인 로그 목록을 조회한다. 
	 * @param  LogSysLoginLogVO 
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<LogSysLoginLogVO> listDayByMonth(LogSysLoginLogVO vo) throws Exception;
	

    /**
     * 시스템 로그인 로그의 상세 정보를 등록한다.  
     * @param  LogSysLoginLogVO
     * @return String
     * @throws Exception
     */
    public int insert(LogSysLoginLogVO vo) throws Exception;
}
