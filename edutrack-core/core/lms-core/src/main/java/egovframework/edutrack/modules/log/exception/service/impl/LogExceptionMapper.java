package egovframework.edutrack.modules.log.exception.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.edutrack.modules.log.exception.service.LogExceptionVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 *  <b>로그 - 예외 로그</b> 영역  DAO 클래스
 * @author Jamfam
 *
 */
@Mapper("logExceptionMapper")
public interface LogExceptionMapper {

	/**
	 * 오류 로그의 전체 목록을 조회한다. 
	 * @param  LogExcepVO 
	 * @return List
	 * @
	 */
	
	public List<LogExceptionVO> list(LogExceptionVO vo) ;
	
    /**
	 * 오류 로그의 검색된 수를 카운트 한다. 
	 * @param  LogExcepVO 
	 * @return int
	 * @
	 */
	public int count(LogExceptionVO vo) ;
	
    /**
	 * 오류 로그의 페이징 목록을 조회한다. 
	 * @param  LogExcepVO 
	 * @return List
	 * @
	 */
	
	public List<LogExceptionVO> listPageing(LogExceptionVO vo) ;
	
    /**
     * 오류 로그의 상세 정보를 등록한다.  
     * @param  LogExcepVO
     * @return String
     * @
     */
    public int insert(LogExceptionVO vo) ;
    
    /**
     * 오류 로그의 상세 정보를 수정한다.  
     * @param  LogExcepVO
     * @return String
     * @
     */
    public int update(LogExceptionVO vo) ;
}
