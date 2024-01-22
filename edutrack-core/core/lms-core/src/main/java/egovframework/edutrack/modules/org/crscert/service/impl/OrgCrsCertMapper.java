package egovframework.edutrack.modules.org.crscert.service.impl;

import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Repository;

import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.org.crscert.service.OrgCrsCertVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 *  <b>기관 - 기관 과정 수료증 관리</b> 영역  DAO 클래스
 * @author Jamfam
 *
 */
@Mapper("orgCrsCertMapper")
public interface OrgCrsCertMapper{

    /**
	 * 기관 수료증의 상세 정보를 조회한다. 
	 * @param  OrgCrsCertVO 
	 * @return OrgCrsCertVO
	 * @
	 */
	public OrgCrsCertVO select(OrgCrsCertVO vo) ;

    /**
     * 기관 수료증의 상세 정보를 등록한다.  
     * @param  OrgCrsCertVO
     * @return String
     * @
     */
    public int insert(OrgCrsCertVO vo) ;
    
    /**
     * 기관 수료증의 상세 정보를 수정한다. 
     * @param  OrgCrsCertVO
     * @return int
     * @
     */
    public int update(OrgCrsCertVO vo) ;
    
    /**
     * 기관 수료증의 상세 정보를 삭제한다.  
     * @param  OrgCrsCertVO
     * @return int
     * @
     */
    public int delete(OrgCrsCertVO vo) ;
}
