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
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<EtcRelatedSiteVO> list(EtcRelatedSiteVO vo) throws Exception ;
	
    /**
	 * 관련사이트의 검색된 수를 카운트 한다. 
	 * @param  EtcRelatedSiteVO 
	 * @return int
	 * @throws Exception
	 */
	public int count(EtcRelatedSiteVO vo) throws Exception ;
	
    /**
	 * 관련사이트의 전체 목록을 조회한다. 
	 * @param  EtcRelatedSiteVO 
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<EtcRelatedSiteVO> listPageing(EtcRelatedSiteVO vo) throws Exception ;
	
    /**
	 * 관련사이트의 상세 정보를 조회한다. 
	 * @param  EtcRelatedSiteVO 
	 * @return EtcRelatedSiteVO
	 * @throws Exception
	 */
	public EtcRelatedSiteVO select(EtcRelatedSiteVO vo) throws Exception ;
	
    /**
	 * 관련사이트의 키를 생성 한다. 
	 * @return Integer
	 * @throws Exception
	 */
	public int selectKey() throws Exception ;

    /**
     * 관련사이트의 상세 정보를 등록한다.  
     * @param  EtcRelatedSiteVO
     * @return String
     * @throws Exception
     */
    public int insert(EtcRelatedSiteVO vo) throws Exception ;
    
    /**
     * 관련사이트의 상세 정보를 수정한다. 
     * @param  EtcRelatedSiteVO
     * @return int
     * @throws Exception
     */
    public int update(EtcRelatedSiteVO vo) throws Exception ;
    
    /**
     * 관련사이트의 상세 정보를 삭제한다.  
     * @param  EtcRelatedSiteVO
     * @return int
     * @throws Exception
     */
    public int delete(EtcRelatedSiteVO vo) throws Exception ;

    /**
     * 관련사이트의 전체 정보를 삭제한다.  
     * @param  EtcRelatedSiteVO
     * @return int
     * @throws Exception
     */
    public int deleteAll(EtcRelatedSiteVO vo) throws Exception ;
}
