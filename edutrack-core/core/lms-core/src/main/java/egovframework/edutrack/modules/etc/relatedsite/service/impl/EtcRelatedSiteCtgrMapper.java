package egovframework.edutrack.modules.etc.relatedsite.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Repository;

import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.etc.relatedsite.service.EtcRelatedSiteCtgrVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 *  <b>기타 - 기타 관련사이트 분류 관리</b> 영역  DAO 클래스
 * @author Jamfam
 *
 */
@Mapper("etcRelatedSiteCtgrMapper")
public interface EtcRelatedSiteCtgrMapper {

    /**
	 * 관련사이트 분류의 전체 목록을 조회한다. 
	 * @param  EtcRelatedSiteCtgrVO 
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<EtcRelatedSiteCtgrVO> list(EtcRelatedSiteCtgrVO vo) throws Exception;
	
    /**
	 * 관련사이트 분류의 검색된 수를 카운트 한다. 
	 * @param  EtcRelatedSiteCtgrVO 
	 * @return int
	 * @throws Exception
	 */
	public int count(EtcRelatedSiteCtgrVO vo) throws Exception;
	
    /**
	 * 관련사이트 분류의 전체 목록을 조회한다. 
	 * @param  EtcRelatedSiteCtgrVO 
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<EtcRelatedSiteCtgrVO> listPageing(EtcRelatedSiteCtgrVO vo) throws Exception;
	
    /**
	 * 관련사이트 분류의 상세 정보를 조회한다. 
	 * @param  EtcRelatedSiteCtgrVO 
	 * @return EtcRelatedSiteCtgrVO
	 * @throws Exception
	 */
	public EtcRelatedSiteCtgrVO select(EtcRelatedSiteCtgrVO vo) throws Exception ;
	
    /**
	 * 관련사이트 분류의 키를 생성 한다. 
	 * @return Integer
	 * @throws Exception
	 */
	public String selectKey() throws Exception ;

    /**
     * 관련사이트 분류의 상세 정보를 등록한다.  
     * @param  EtcRelatedSiteCtgrVO
     * @return String
     * @throws Exception
     */
    public int insert(EtcRelatedSiteCtgrVO vo) throws Exception;
    
    /**
     * 관련사이트 분류의 상세 정보를 수정한다. 
     * @param  EtcRelatedSiteCtgrVO
     * @return int
     * @throws Exception
     */
    public int update(EtcRelatedSiteCtgrVO vo) throws Exception;
    
    /**
     * 관련사이트 분류의 상세 정보를 삭제한다.  
     * @param  EtcRelatedSiteCtgrVO
     * @return int
     * @throws Exception
     */
    public int delete(EtcRelatedSiteCtgrVO vo) throws Exception;
}
