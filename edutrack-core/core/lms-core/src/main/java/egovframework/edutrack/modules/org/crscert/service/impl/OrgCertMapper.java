package egovframework.edutrack.modules.org.crscert.service.impl;

import egovframework.edutrack.modules.org.crscert.service.OrgCertVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 *  <b>기관 - 기관 과정 수료증 관리</b> 영역  DAO 클래스
 * @author Jamfam
 *
 */
@Mapper("orgCertMapper")
public interface OrgCertMapper{

    /**
	 * 기관 수료증의 상세 정보를 조회한다. 
	 * @param  OrgCertVO 
	 * @return OrgCertVO
	 * @
	 */
	public OrgCertVO select(OrgCertVO vo) ;

    /**
     * 기관 수료증의 상세 정보를 등록한다.  
     * @param  OrgCertVO
     * @return String
     * @
     */
    public int insert(OrgCertVO vo) ;
    
    /**
     * 기관 수료증의 상세 정보를 수정한다. 
     * @param  OrgCertVO
     * @return int
     * @
     */
    public int update(OrgCertVO vo) ;
    
    /**
     * 기관 수료증의 상세 정보를 삭제한다.  
     * @param  OrgCertVO
     * @return int
     * @
     */
    public int delete(OrgCertVO vo) ;
}
