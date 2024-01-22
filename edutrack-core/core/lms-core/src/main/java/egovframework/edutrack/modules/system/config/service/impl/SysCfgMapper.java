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
	 * @
	 */
	
	public List<SysCfgVO> list(SysCfgVO vo) ;
	
    /**
	 * 설정 정보의 검색된 수를 카운트 한다. 
	 * @param  SysCfgVO 
	 * @return int
	 * @
	 */
	public int count(SysCfgVO vo) ;
	
	/**
	 * 설정 분류의 전체 목록을 조회한다. 
	 * @param  SysCfgCtgrVO 
	 * @return List
	 * @
	 */
	
	public List<SysCfgVO> listConfig(SysCfgCtgrVO vo) ;
    /**
	 * 설정 정보의 전체 목록을 조회한다. 
	 * @param  SysCfgVO 
	 * @return List
	 * @
	 */
	
	public List<SysCfgVO> listPageing(SysCfgVO vo)  ;
	
    /**
	 * 설정 정보의 상세 정보를 조회한다. 
	 * @param  SysCfgVO 
	 * @return SysCfgVO
	 * @
	 */
	public SysCfgVO select(SysCfgVO vo) ;

    /**
     * 설정 정보의 상세 정보를 등록한다.  
     * @param  SysCfgVO
     * @return String
     * @
     */
    public int insert(SysCfgVO vo) ;
    
    /**
     * 설정 정보의 상세 정보를 수정한다. 
     * @param  SysCfgVO
     * @return int
     * @
     */
    public int update(SysCfgVO vo)  ;
    
    /**
     * 설정 정보의 상세 정보를 삭제한다.  
     * @param  SysCfgVO
     * @return int
     * @
     */
    public int delete(SysCfgVO vo) ;
    
    /**
     * 분류 코드 하위의 설정 정보의 상세 정보 전체를 삭제한다.  
     * @param  SysCfgVO
     * @return int
     * @
     */
    public int deleteAll(SysCfgVO vo)  ;
}
