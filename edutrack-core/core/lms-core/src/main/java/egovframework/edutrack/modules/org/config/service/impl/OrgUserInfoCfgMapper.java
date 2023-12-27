package egovframework.edutrack.modules.org.config.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.edutrack.modules.org.config.service.OrgUserInfoCfgVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 *  <b>교육기관 - 기관 사용자 정보 설정 관리</b> 영역  DAO 클래스
 * @author Jamfam
 *
 */
@Mapper("orgUserInfoCfgMapper")
public interface OrgUserInfoCfgMapper{
	
    /**
	 * 설정 정보의 전체 목록을 조회한다. 
	 * @param  OrgUserInfoCfgVO 
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<OrgUserInfoCfgVO> list(OrgUserInfoCfgVO vo) throws Exception;
	
    /**
	 * 설정 정보의 상세 정보를 조회한다. 
	 * @param  OrgUserInfoCfgVO 
	 * @return OrgUserInfoCfgVO
	 * @throws Exception
	 */
	public OrgUserInfoCfgVO select(OrgUserInfoCfgVO vo) throws Exception;

    /**
     * 설정 정보의 상세 정보를 등록한다.  
     * @param  OrgUserInfoCfgVO
     * @return String
     * @throws Exception
     */
    public int insert(OrgUserInfoCfgVO vo) throws Exception;
    
    /**
     * 설정 정보의 상세 정보를 수정한다. 
     * @param  OrgUserInfoCfgVO
     * @return int
     * @throws Exception
     */
    public int update(OrgUserInfoCfgVO vo) throws Exception;
    
    /**
     * 설정 정보의 상세 정보를 삭제한다.  
     * @param  OrgUserInfoCfgVO
     * @return int
     * @throws Exception
     */
    public int delete(OrgUserInfoCfgVO vo) throws Exception;
    
    /**
     * 설정 정보의 상세 정보 전체를 삭제한다.  
     * @param  OrgUserInfoCfgVO
     * @return int
     * @throws Exception
     */
    public int deleteAll(OrgUserInfoCfgVO vo) throws Exception;
    
	@SuppressWarnings("unchecked")
	public List<OrgUserInfoCfgVO> listForJoin(OrgUserInfoCfgVO vo) throws Exception;
}
