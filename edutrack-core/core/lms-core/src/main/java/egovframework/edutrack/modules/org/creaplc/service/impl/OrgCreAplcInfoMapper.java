package egovframework.edutrack.modules.org.creaplc.service.impl;

import java.util.List;

import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Repository;

import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.org.creaplc.service.OrgCreAplcInfoVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 *  <b>사이트개설승인 - 사이트 개설/신청 승인 관리</b> 영역  DAO 클래스
 * @author hy
 *
 */
@Mapper("orgCreAplcInfoMapper")
public interface OrgCreAplcInfoMapper{

	/**
	 * 사이트 개설신청 목록을 조회한다. 
	 * @param  OrgCreAplcInfoVO 
	 * @return List
	 * @
	 */
	
	public List<OrgCreAplcInfoVO> list(OrgCreAplcInfoVO vo) ;
	
    /**
	 * 사이트 개설신청 검색된 수를 카운트 한다. 
	 * @param  OrgCreAplcInfoVO 
	 * @return int
	 * @
	 */
	public int count(OrgCreAplcInfoVO vo) ;
	
    /**
	 * 사이트 개설신청의 전체 목록을 조회한다. 
	 * @param  OrgConnIpVO 
	 * @return List
	 * @
	 */
	
	public List<OrgCreAplcInfoVO> listPageing(OrgCreAplcInfoVO vo) ;
	
    /**
	 * 사이트 개설신청 상세 정보를 조회한다. 
	 * @param  OrgCreAplcInfoVO 
	 * @return OrgCreAplcInfoVO
	 * @
	 */
	public OrgCreAplcInfoVO select(OrgCreAplcInfoVO vo) ;
	
    /**
	 * 사이트 개설신청 키를 생성한다. 
	 * @return String
	 * @
	 */
	public Integer selectKey() ;	

    /**
     * 사이트 개설신청 상세 정보를 등록한다.  
     * @param  OrgCreAplcInfoVO
     * @return String
     * @
     */
    public int insert(OrgCreAplcInfoVO vo) ;
    
    /**
     * 사이트 개설신청 상세 정보를 수정한다. 
     * @param  OrgCreAplcInfoVO
     * @return int
     * @
     */
    public int update(OrgCreAplcInfoVO vo) ;
    
    /**
     * 사이트 개설 후 사이트 개설신청 정보를 수정한다.(기관코드 등록) 
     * @param  OrgCreAplcInfoVO
     * @return int
     * @
     */
    public int updateOrgCd(OrgCreAplcInfoVO vo) ;
    
    /**
     * 사이트 개설 후 사이트 개설신청 정보를 수정한다.(기관코드 삭제) 
     * @param  OrgCreAplcInfoVO
     * @return int
     * @
     */
    public int deleteOrgCd(OrgCreAplcInfoVO vo) ;
    
    /**
     * 사이트 개설신청 상세 정보를 삭제한다.  
     * @param  OrgCreAplcInfoVO
     * @return int
     * @
     */
    public int delete(OrgCreAplcInfoVO vo) ;

    /**
     * 사이트 이메일을 인증한다.
     * @param  OrgCreAplcInfoVO
     * @return int
     * @
     */
	public int updateEmailCertYn(OrgCreAplcInfoVO vo);

}
