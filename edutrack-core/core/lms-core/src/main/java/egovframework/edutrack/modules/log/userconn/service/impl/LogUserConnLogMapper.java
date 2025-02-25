package egovframework.edutrack.modules.log.userconn.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.edutrack.modules.log.adminconn.service.LogAdminConnLogVO;
import egovframework.edutrack.modules.log.userconn.service.LogUserConnLogVO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 *  <b>로그 - 사용자 접속 로그</b> 영역  Mapper 클래스
 * @author Jamfam
 *
 */
@Mapper("logUserConnLogMapper")
public interface LogUserConnLogMapper  {

	/**
	 * 사용자 접속 로그의 전체 목록을 조회한다. 
	 * @param  LogUserConnLogVO 
	 * @return List
	 * @
	 */
	
	public List<LogUserConnLogVO> list(LogUserConnLogVO vo) ;
	
    /**
	 * 사용자 접속 로그의 검색된 수를 카운트 한다. 
	 * @param  LogUserConnLogVO 
	 * @return int
	 * @
	 */
	public int count(LogUserConnLogVO vo) ;
	
    /**
	 * 사용자 접속 로그의 페이징 목록을 조회한다. 
	 * @param  LogUserConnLogVO 
	 * @return List
	 * @
	 */
	
	public List<LogUserConnLogVO> listPageing(LogUserConnLogVO vo) ;
	
    /**
     * 사용자 접속 로그의 상세 정보를 등록한다.  
     * @param  LogUserConnLogVO
     * @return String
     * @
     */
    public int insert(LogUserConnLogVO vo) ;
    
    /**
     * 사용자 접속 로그의 상세 정보를 수정한다. 
     * @param  LogUserConnLogVO
     * @return int
     * @
     */
    public int update(LogUserConnLogVO vo) ;

}
