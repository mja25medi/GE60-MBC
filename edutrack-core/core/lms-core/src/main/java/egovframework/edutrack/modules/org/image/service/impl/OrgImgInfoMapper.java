package egovframework.edutrack.modules.org.image.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Repository;

import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.org.image.service.OrgImgInfoVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 *  <b>기관 - 기관 이미지 관리</b> 영역  DAO 클래스
 * @author Jamfam
 *
 */
@Mapper("orgImgInfoMapper")
public interface OrgImgInfoMapper{

	/**
	 * 기관 이미지의 전체 목록을 조회한다. 
	 * @param  OrgImgInfoVO 
	 * @return List
	 * @
	 */
	
	public List<OrgImgInfoVO> list(OrgImgInfoVO vo) ;
	
    /**
	 * 기관 이미지의 검색된 수를 카운트 한다. 
	 * @param  OrgImgInfoVO 
	 * @return int
	 * @
	 */
	public int count(OrgImgInfoVO vo) ;
	
    /**
	 * 기관 이미지의 전체 목록을 조회한다. 
	 * @param  OrgImgInfoVO 
	 * @return List
	 * @
	 */
	
	public List<OrgImgInfoVO> listPageing(OrgImgInfoVO vo) ;
	
    /**
	 * 기관 이미지의 상세 정보를 조회한다. 
	 * @param  OrgImgInfoVO 
	 * @return OrgImgInfoVO
	 * @
	 */
	public OrgImgInfoVO select(OrgImgInfoVO vo) ;
	
    /**
	 * 기관 이미지의 키를 생성 한다. 
	 * @param  OrgImgInfoVO 
	 * @return OrgImgInfoVO
	 * @
	 */
	public int selectKey() ;

    /**
     * 기관 이미지의 상세 정보를 등록한다.  
     * @param  OrgImgInfoVO
     * @return String
     * @
     */
    public int insert(OrgImgInfoVO vo) ;
    
    /**
     * 기관 이미지의 상세 정보를 수정한다. 
     * @param  OrgImgInfoVO
     * @return int
     * @
     */
    public int update(OrgImgInfoVO vo) ;
    
    /**
     * 기관 이미지의 상세 정보를 삭제한다.  
     * @param  OrgImgInfoVO
     * @return int
     * @
     */
    public int delete(OrgImgInfoVO vo) ;
    
    /**
     * 기관의 이미지 전체를 삭제한다.  
     * @param  OrgImgInfoVO
     * @return int
     * @
     */
    public int deleteAll(OrgImgInfoVO vo) ;    
}
