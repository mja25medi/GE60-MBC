package egovframework.edutrack.modules.system.code.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Repository;

import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.system.code.service.SysCodeCtgrVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 *  <b>시스템 - 시스템 코드 분류 관리</b> 영역  DAO 클래스
 * @author Jamfam
 *
 */
@Mapper("sysCodeCtgrMapper")
public interface SysCodeCtgrMapper {
	
    /**
	 * 코드 분류의 전체 목록을 조회한다. 
	 * @param  SysCodeCtgrVO 
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<SysCodeCtgrVO> list(SysCodeCtgrVO vo) throws Exception;
	
    /**
	 * 코드 분류의 검색된 수를 카운트 한다. 
	 * @param  SysCodeCtgrVO 
	 * @return int
	 * @throws Exception
	 */
	public int count(SysCodeCtgrVO vo) throws Exception;
	
    /**
	 * 코드 분류의 전체 목록을 조회한다. 
	 * @param  SysCodeCtgrVO 
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<SysCodeCtgrVO> listPageing(SysCodeCtgrVO vo) throws Exception;
	
    /**
	 * 코드 분류의 상세 정보를 조회한다. 
	 * @param  SysCodeCtgrVO 
	 * @return SysCodeCtgrVO
	 * @throws Exception
	 */
	public SysCodeCtgrVO select(SysCodeCtgrVO vo) throws Exception ;

    /**
     * 코드 분류의 상세 정보를 등록한다.  
     * @param  SysCodeCtgrVO
     * @return String
     * @throws Exception
     */
    public int insert(SysCodeCtgrVO vo) throws Exception ;
    
    /**
     * 코드 분류의 상세 정보를 수정한다. 
     * @param  SysCodeCtgrVO
     * @return int
     * @throws Exception
     */
    public int update(SysCodeCtgrVO vo) throws Exception ;
    
    /**
     * 코드 분류의 상세 정보를 삭제한다.  
     * @param  SysCodeCtgrVO
     * @return int
     * @throws Exception
     */
    public int delete(SysCodeCtgrVO vo) throws Exception ;
	
}