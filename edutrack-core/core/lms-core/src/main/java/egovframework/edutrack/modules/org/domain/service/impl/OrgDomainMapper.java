package egovframework.edutrack.modules.org.domain.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Repository;

import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.org.domain.service.OrgDomainVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 *  <b>기관 - 기관 도메인 관리</b> 영역  DAO 클래스
 * @author Jamfam
 *
 */
@Mapper("orgDomainMapper")
public interface OrgDomainMapper{
	
    /**
	 * 기관 도메인의 전체 목록을 조회한다. 
	 * @param  OrgDomainVO 
	 * @return List
	 * @
	 */
	
	public List<OrgDomainVO> list(OrgDomainVO vo) ;
	
    /**
	 * 기관 도메인의 검색된 수를 카운트 한다. 
	 * @param  OrgDomainVO 
	 * @return int
	 * @
	 */
	public int count(OrgDomainVO vo) ;
	
    /**
	 * 기관 도메인의 전체 목록을 조회한다. 
	 * @param  OrgDomainVO 
	 * @return List
	 * @
	 */
	
	public List<OrgDomainVO> listPageing(OrgDomainVO vo) ;
	
    /**
	 * 기관 도메인의 상세 정보를 조회한다. 
	 * @param  OrgDomainVO 
	 * @return OrgDomainVO
	 * @
	 */
	public OrgDomainVO select(OrgDomainVO vo) ;
	
	 /**
	 * 기관 도메인의 상세 정보를 조회한다. 
	 * @param  OrgDomainVO 
	 * @return OrgDomainVO
	 * @
	 */
	public OrgDomainVO selectByTypeCd(OrgDomainVO vo) ;
	
	
	 /**
	 * 기관 도메인의 상세 정보를 조회한다. 
	 * @param  OrgDomainVO 
	 * @return OrgDomainVO
	 * @
	 */
	public OrgDomainVO selectByServiceTypeCd(OrgDomainVO vo) ;

    /**
     * 기관 도메인의 상세 정보를 등록한다.  
     * @param  OrgDomainVO
     * @return String
     * @
     */
    public int insert(OrgDomainVO vo) ;
    
    /**
     * 기관 도메인의 상세 정보를 수정한다. 
     * @param  OrgDomainVO
     * @return int
     * @
     */
    public int update(OrgDomainVO vo) ;
    
    /**
     * 기관 도메인의 상세 정보를 삭제한다.  
     * @param  OrgDomainVO
     * @return int
     * @
     */
    public int delete(OrgDomainVO vo) ;
    
    /**
     * 기관의 대표 도메인 지정을 해지 한다. 
     * @param  OrgDomainVO
     * @return int
     * @
     */
    public int updateRprstN(OrgDomainVO vo) ;

    /**
     * 기관의 대표 도메인 지정을 한다. 
     * @param  OrgDomainVO
     * @return int
     * @
     */
    public int updateRprstY(OrgDomainVO vo) ;    
    
}
