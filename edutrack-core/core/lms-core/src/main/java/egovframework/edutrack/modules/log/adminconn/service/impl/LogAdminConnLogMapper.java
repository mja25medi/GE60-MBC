package egovframework.edutrack.modules.log.adminconn.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Repository;

import egovframework.edutrack.comm.util.web.BeanUtils;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.log.adminconn.service.LogAdminConnLogVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 *  <b>로그 - 관리자 접속 로그</b> 영역  DAO 클래스
 * @author Jamfam
 *
 */
@Mapper("logAdminConnLogMapper")
public interface LogAdminConnLogMapper {
	
    /**
	 * 관리자 접속 로그의 전체 목록을 조회한다. 
	 * @param  LogAdminConnLogVO 
	 * @return List
	 * @
	 */
	
	public List<LogAdminConnLogVO> list(LogAdminConnLogVO vo) ;
	
    /**
	 * 관리자 접속 로그의 검색된 수를 카운트 한다. 
	 * @param  LogAdminConnLogVO 
	 * @return int
	 * @
	 */
	public int count(LogAdminConnLogVO vo) ;
	
    /**
	 * 관리자 접속 로그의 전체 목록을 조회한다. 
	 * @param  LogAdminConnLogVO 
	 * @return List
	 * @
	 */
	
	public List<LogAdminConnLogVO> listPageing(LogAdminConnLogVO vo) ;
	
    /**
	 * 관리자 접속 로그의 상세 정보를 조회한다. 
	 * @param  LogAdminConnLogVO 
	 * @return LogAdminConnLogVO
	 * @
	 */
	public LogAdminConnLogVO select(LogAdminConnLogVO vo) ;
	
    /**
	 * 관리자 접속 로그의 키를 생성한다. 
	 * @param   
	 * @return Integer
	 * @
	 */
	public int selectKey() ;
	
    /**
	 * 관리자 접속 로그의 상세 정보를 조회한다. 
	 * @param  LogAdminConnLogVO 
	 * @return LogAdminConnLogVO
	 * @
	 */
	public int selectLast(LogAdminConnLogVO vo) ;

    /**
     * 관리자 접속 로그의 상세 정보를 등록한다.  
     * @param  LogAdminConnLogVO
     * @return String
     * @
     */
    public int insert(LogAdminConnLogVO vo) ;
    
    /**
     * 관리자 접속 로그의 상세 정보를 수정한다. 
     * @param  LogAdminConnLogVO
     * @return int
     * @
     */
    public int update(LogAdminConnLogVO vo) ;
}
