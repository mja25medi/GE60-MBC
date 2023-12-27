package egovframework.edutrack.modules.system.config.service.impl;

import java.util.List;

import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Repository;

import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.system.config.service.SysCfgCtgrVO;
import egovframework.edutrack.modules.system.config.service.SysCfgVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 *  <b>시스템 - 시스템 설정 분류 관리</b> 영역  DAO 클래스
 * @author Jamfam
 *
 */
@Mapper("sysCfgCtgrMapper")
public interface SysCfgCtgrMapper {
	
    /**
	 * 설정 분류의 전체 목록을 조회한다. 
	 * @param  SysCfgCtgrVO 
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<SysCfgCtgrVO> list(SysCfgCtgrVO vo) throws Exception;
	
    /**
	 * 설정 분류의 검색된 수를 카운트 한다. 
	 * @param  SysCfgCtgrVO 
	 * @return int
	 * @throws Exception
	 */
	public int count(SysCfgCtgrVO vo) throws Exception;
	
    /**
	 * 설정 분류의 전체 목록을 조회한다. 
	 * @param  SysCfgCtgrVO 
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<SysCfgCtgrVO> listPageing(SysCfgCtgrVO vo) throws Exception;
	
	
    /**
	 * 설정 분류의 상세 정보를 조회한다. 
	 * @param  SysCfgCtgrVO 
	 * @return SysCfgCtgrVO
	 * @throws Exception
	 */
	public SysCfgCtgrVO select(SysCfgCtgrVO vo) throws Exception;

    /**
     * 설정 분류의 상세 정보를 등록한다.  
     * @param  SysCfgCtgrVO
     * @return String
     * @throws Exception
     */
    public int insert(SysCfgCtgrVO vo) throws Exception;
    
    /**
     * 설정 분류의 상세 정보를 수정한다. 
     * @param  SysCfgCtgrVO
     * @return int
     * @throws Exception
     */
    public int update(SysCfgCtgrVO vo) throws Exception;
    
    /**
     * 설정 분류의 상세 정보를 삭제한다.  
     * @param  SysCfgCtgrVO
     * @return int
     * @throws Exception
     */
    public int delete(SysCfgCtgrVO vo) throws Exception ;

}
