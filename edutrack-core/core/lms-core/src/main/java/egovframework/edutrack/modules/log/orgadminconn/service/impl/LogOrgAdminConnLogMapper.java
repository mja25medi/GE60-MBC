package egovframework.edutrack.modules.log.orgadminconn.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.edutrack.modules.log.orgadminconn.service.LogOrgAdminConnLogVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 *  <b>로그 - 관리자 접속 로그</b> 영역  Mapper 클래스
 * @author Jamfam
 *
 */
@Mapper("logOrgAdminConnLogMapper")
public interface LogOrgAdminConnLogMapper {
	
    /**
	 * 관리자 접속 로그의 전체 목록을 조회한다. 
	 * @param  LogOrgAdminConnLogVO 
	 * @return List
	 * @
	 */
	
	public List<LogOrgAdminConnLogVO> list(LogOrgAdminConnLogVO vo) ;
	
    /**
	 * 관리자 접속 로그의 검색된 수를 카운트 한다. 
	 * @param  LogOrgAdminConnLogVO 
	 * @return int
	 * @
	 */
	public int count(LogOrgAdminConnLogVO vo) ;
	
    /**
	 * 관리자 접속 로그의 전체 목록을 조회한다. 
	 * @param  LogOrgAdminConnLogVO 
	 * @return List
	 * @
	 */
	
	public List<LogOrgAdminConnLogVO> listPageing(LogOrgAdminConnLogVO vo)  ;
	
    /**
	 * 관리자 접속 로그의 상세 정보를 조회한다. 
	 * @param  LogOrgAdminConnLogVO 
	 * @return LogOrgAdminConnLogVO
	 * @
	 */
	public LogOrgAdminConnLogVO select(LogOrgAdminConnLogVO vo) ;
	
    /**
	 * 관리자 접속 로그의 키를 생성한다. 
	 * @param   
	 * @return Integer
	 * @
	 */
	public int selectKey() ;
	
    /**
	 * 관리자 접속 로그의 상세 정보를 조회한다. 
	 * @param  LogOrgAdminConnLogVO 
	 * @return LogOrgAdminConnLogVO
	 * @
	 */
	public int selectLast(LogOrgAdminConnLogVO vo) ;

    /**
     * 관리자 접속 로그의 상세 정보를 등록한다.  
     * @param  LogOrgAdminConnLogVO
     * @return String
     * @
     */
    public void insert(LogOrgAdminConnLogVO vo) ;
    
    /**
     * 관리자 접속 로그의 상세 정보를 수정한다. 
     * @param  LogOrgAdminConnLogVO
     * @return int
     * @
     */
    public void update(LogOrgAdminConnLogVO vo) ;
}
