package egovframework.edutrack.modules.etc.relatedsite.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Repository;

import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.etc.relatedsite.service.EtcRelatedSiteVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 *  <b>기타 - 기타 관련사이트 관리</b> 영역  DAO 클래스
 * @author Jamfam
 *
 */
@Mapper("etcRelatedSiteMapper")
public interface EtcRelatedSiteMapper {

    /**
	 * 관련사이트의 전체 목록을 조회한다. 
	 * @param  EtcRelatedSiteVO 
	 * @return List
	 * @
	 */
	
	public List<EtcRelatedSiteVO> list(EtcRelatedSiteVO vo)  ;
	
    /**
	 * 관련사이트의 검색된 수를 카운트 한다. 
	 * @param  EtcRelatedSiteVO 
	 * @return int
	 * @
	 */
	public int count(EtcRelatedSiteVO vo)  ;
	
    /**
	 * 관련사이트의 전체 목록을 조회한다. 
	 * @param  EtcRelatedSiteVO 
	 * @return List
	 * @
	 */
	
	public List<EtcRelatedSiteVO> listPageing(EtcRelatedSiteVO vo)  ;
	
    /**
	 * 관련사이트의 상세 정보를 조회한다. 
	 * @param  EtcRelatedSiteVO 
	 * @return EtcRelatedSiteVO
	 * @
	 */
	public EtcRelatedSiteVO select(EtcRelatedSiteVO vo)  ;
	
    /**
	 * 관련사이트의 키를 생성 한다. 
	 * @return Integer
	 * @
	 */
	public int selectKey()  ;

    /**
     * 관련사이트의 상세 정보를 등록한다.  
     * @param  EtcRelatedSiteVO
     * @return String
     * @
     */
    public int insert(EtcRelatedSiteVO vo)  ;
    
    /**
     * 관련사이트의 상세 정보를 수정한다. 
     * @param  EtcRelatedSiteVO
     * @return int
     * @
     */
    public int update(EtcRelatedSiteVO vo)  ;
    
    /**
     * 관련사이트의 상세 정보를 삭제한다.  
     * @param  EtcRelatedSiteVO
     * @return int
     * @
     */
    public int delete(EtcRelatedSiteVO vo)  ;

    /**
     * 관련사이트의 전체 정보를 삭제한다.  
     * @param  EtcRelatedSiteVO
     * @return int
     * @
     */
    public int deleteAll(EtcRelatedSiteVO vo)  ;
}
