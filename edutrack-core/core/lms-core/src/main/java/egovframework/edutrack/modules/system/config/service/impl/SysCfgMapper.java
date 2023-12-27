package egovframework.edutrack.modules.system.config.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Repository;

import egovframework.edutrack.comm.util.web.BeanUtils;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.system.config.service.SysCfgCtgrVO;
import egovframework.edutrack.modules.system.config.service.SysCfgVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 *  <b>시스템 - 시스템 설정 정보 관리</b> 영역  DAO 클래스
 * @author Jamfam
 *
 */
@Mapper("sysCfgMapper")
public interface SysCfgMapper {

    /**
	 * 설정 정보의 전체 목록을 조회한다. 
	 * @param  SysCfgVO 
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<SysCfgVO> list(SysCfgVO vo) throws Exception;
	
    /**
	 * 설정 정보의 검색된 수를 카운트 한다. 
	 * @param  SysCfgVO 
	 * @return int
	 * @throws Exception
	 */
	public int count(SysCfgVO vo) throws Exception;
	
	/**
	 * 설정 분류의 전체 목록을 조회한다. 
	 * @param  SysCfgCtgrVO 
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<SysCfgVO> listConfig(SysCfgCtgrVO vo) throws Exception;
    /**
	 * 설정 정보의 전체 목록을 조회한다. 
	 * @param  SysCfgVO 
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<SysCfgVO> listPageing(SysCfgVO vo) throws Exception ;
	
    /**
	 * 설정 정보의 상세 정보를 조회한다. 
	 * @param  SysCfgVO 
	 * @return SysCfgVO
	 * @throws Exception
	 */
	public SysCfgVO select(SysCfgVO vo) throws Exception;

    /**
     * 설정 정보의 상세 정보를 등록한다.  
     * @param  SysCfgVO
     * @return String
     * @throws Exception
     */
    public int insert(SysCfgVO vo) throws Exception;
    
    /**
     * 설정 정보의 상세 정보를 수정한다. 
     * @param  SysCfgVO
     * @return int
     * @throws Exception
     */
    public int update(SysCfgVO vo) throws Exception ;
    
    /**
     * 설정 정보의 상세 정보를 삭제한다.  
     * @param  SysCfgVO
     * @return int
     * @throws Exception
     */
    public int delete(SysCfgVO vo) throws Exception;
    
    /**
     * 분류 코드 하위의 설정 정보의 상세 정보 전체를 삭제한다.  
     * @param  SysCfgVO
     * @return int
     * @throws Exception
     */
    public int deleteAll(SysCfgVO vo) throws Exception ;
}
