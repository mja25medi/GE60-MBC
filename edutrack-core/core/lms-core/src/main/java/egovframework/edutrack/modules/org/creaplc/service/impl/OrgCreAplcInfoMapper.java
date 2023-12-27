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
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<OrgCreAplcInfoVO> list(OrgCreAplcInfoVO vo) throws Exception;
	
    /**
	 * 사이트 개설신청 검색된 수를 카운트 한다. 
	 * @param  OrgCreAplcInfoVO 
	 * @return int
	 * @throws Exception
	 */
	public int count(OrgCreAplcInfoVO vo) throws Exception;
	
    /**
	 * 사이트 개설신청의 전체 목록을 조회한다. 
	 * @param  OrgConnIpVO 
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<OrgCreAplcInfoVO> listPageing(OrgCreAplcInfoVO vo) throws Exception;
	
    /**
	 * 사이트 개설신청 상세 정보를 조회한다. 
	 * @param  OrgCreAplcInfoVO 
	 * @return OrgCreAplcInfoVO
	 * @throws Exception
	 */
	public OrgCreAplcInfoVO select(OrgCreAplcInfoVO vo) throws Exception;
	
    /**
	 * 사이트 개설신청 키를 생성한다. 
	 * @return String
	 * @throws Exception
	 */
	public Integer selectKey() throws Exception;	

    /**
     * 사이트 개설신청 상세 정보를 등록한다.  
     * @param  OrgCreAplcInfoVO
     * @return String
     * @throws Exception
     */
    public int insert(OrgCreAplcInfoVO vo) throws Exception;
    
    /**
     * 사이트 개설신청 상세 정보를 수정한다. 
     * @param  OrgCreAplcInfoVO
     * @return int
     * @throws Exception
     */
    public int update(OrgCreAplcInfoVO vo) throws Exception;
    
    /**
     * 사이트 개설 후 사이트 개설신청 정보를 수정한다.(기관코드 등록) 
     * @param  OrgCreAplcInfoVO
     * @return int
     * @throws Exception
     */
    public int updateOrgCd(OrgCreAplcInfoVO vo) throws Exception;
    
    /**
     * 사이트 개설 후 사이트 개설신청 정보를 수정한다.(기관코드 삭제) 
     * @param  OrgCreAplcInfoVO
     * @return int
     * @throws Exception
     */
    public int deleteOrgCd(OrgCreAplcInfoVO vo) throws Exception;
    
    /**
     * 사이트 개설신청 상세 정보를 삭제한다.  
     * @param  OrgCreAplcInfoVO
     * @return int
     * @throws Exception
     */
    public int delete(OrgCreAplcInfoVO vo) throws Exception;

    /**
     * 사이트 이메일을 인증한다.
     * @param  OrgCreAplcInfoVO
     * @return int
     * @throws Exception
     */
	public int updateEmailCertYn(OrgCreAplcInfoVO vo);

}
