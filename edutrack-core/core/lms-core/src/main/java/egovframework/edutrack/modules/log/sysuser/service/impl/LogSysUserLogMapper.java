package egovframework.edutrack.modules.log.sysuser.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.edutrack.modules.log.sysuser.service.LogSysUserLogVO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 *  <b>로그 - 사용자통계</b> 영역  Mapper 클래스
 * @author ymlee
 *
 */
@Mapper("logSysUserLogMapper")
public interface LogSysUserLogMapper  {

    /**
	 * 국가별 가입 목록을 조회한다. 
	 * @param  LogSysUserLogVO 
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<LogSysUserLogVO> listCountry(LogSysUserLogVO vo) throws Exception;
	
    /**
	 * 소셜별 가입 목록을 조회한다. 
	 * @param  LogSysUserLogVO 
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<LogSysUserLogVO> listSns(LogSysUserLogVO vo) throws Exception;
	
    /**
	 * 성별 가입 목록을 조회한다. 
	 * @param  LogSysUserLogVO 
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<LogSysUserLogVO> listSex(LogSysUserLogVO vo) throws Exception;
	
    /**
	 * 나이별 가입 목록을 조회한다. 
	 * @param  LogSysUserLogVO 
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<LogSysUserLogVO> listAge(LogSysUserLogVO vo) throws Exception;
	
    /**
	 * 자동 날짜 조회. 
	 * @param  LogSysUserLogVO 
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public LogSysUserLogVO selectAutoDate(LogSysUserLogVO vo) throws Exception;
	
}
