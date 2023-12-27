package egovframework.edutrack.modules.org.info.service.impl;

import java.util.List;

import egovframework.edutrack.modules.org.info.service.OrgOrgInfoVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 *  <b>기관 - 기관 정보 관리</b> 영역  DAO 클래스
 * @author Jamfam
 *
 */
@Mapper("orgOrgInfoMapper")
public interface OrgOrgInfoMapper{

    /**
	 * 기관 정보의 전체 목록을 조회한다. 
	 * @param  OrgOrgInfoVO 
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<OrgOrgInfoVO> list(OrgOrgInfoVO vo) throws Exception;
	
    /**
	 * 기관 정보의 검색된 수를 카운트 한다. 
	 * @param  OrgOrgInfoVO 
	 * @return int
	 * @throws Exception
	 */
	public int count(OrgOrgInfoVO vo) throws Exception;
	
    /**
	 * 기관 정보의 전체 목록을 조회한다. 
	 * @param  OrgOrgInfoVO 
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<OrgOrgInfoVO> listPageing(OrgOrgInfoVO vo) throws Exception;
	
    /**
	 * 기관 정보의 상세 정보를 조회한다. 
	 * @param  OrgOrgInfoVO 
	 * @return OrgOrgInfoVO
	 * @throws Exception
	 */
	public OrgOrgInfoVO select(OrgOrgInfoVO vo) throws Exception;
	
    /**
	 * 기관 정보의 상세 정보를 조회한다. 
	 * @param  OrgOrgInfoVO 
	 * @return OrgOrgInfoVO
	 * @throws Exception
	 */
	public OrgOrgInfoVO selectByDomain(OrgOrgInfoVO vo) throws Exception;
	
    /**
	 * 기관 정보의 상세 정보를 조회한다. 
	 * @return String
	 * @throws Exception
	 */
	public String selectKey() throws Exception;	

    /**
     * 기관 정보의 상세 정보를 등록한다.  
     * @param  OrgOrgInfoVO
     * @return String
     * @throws Exception
     */
    public int insert(OrgOrgInfoVO vo) throws Exception;
    
    /**
     * 기관 정보의 상세 정보를 수정한다. 
     * @param  OrgOrgInfoVO
     * @return int
     * @throws Exception
     */
    public int update(OrgOrgInfoVO vo) throws Exception;
    
    /**
     * 기관 정보의 기본 정보만 수정한다. 
     * @param  OrgOrgInfoVO
     * @return int
     * @throws Exception
     */
    public int updateInfo(OrgOrgInfoVO vo) throws Exception;
    
    /**
     * 기관 정보의 템플릿 정보만 수정한다. 
     * @param  OrgOrgInfoVO
     * @return int
     * @throws Exception
     */
    public int updateTemplate(OrgOrgInfoVO vo) throws Exception;    

    /**
     * 기관 정보의 디자인 정보만 수정한다. 
     * @param  OrgOrgInfoVO
     * @return int
     * @throws Exception
     */
    public int updateDesign(OrgOrgInfoVO vo) throws Exception;
    
    /**
     * 기관 정보의 메타버스 정보만 수정한다. 
     * @param  OrgOrgInfoVO
     * @return int
     * @throws Exception
     */
    public int updateMeta(OrgOrgInfoVO vo) throws Exception;
    
    /**
     * 기관 정보의 상세 정보를 삭제한다.  
     * @param  OrgOrgInfoVO
     * @return int
     * @throws Exception
     */
    public int delete(OrgOrgInfoVO vo) throws Exception; 
    
    /**
     * 기관의 메뉴 버전을 가져온다. 
     * @param  OrgOrgInfoVO
     * @return int
     * @throws Exception
     */
    public int selectMenuVersion(OrgOrgInfoVO vo) throws Exception;
    
    /**
     * 기관의 메뉴 버전 값을 증가 시킨다. 
     * @param  OrgOrgInfoVO
     * @return int
     * @throws Exception
     */
    public int updateMenuVersion(OrgOrgInfoVO vo) throws Exception;
    
    /**
     * 기관 생성시 기본 게시판 정보를 등록한다.  
     * @param  OrgOrgInfoVO
     * @return String
     * @throws Exception
     */
    public int insertBbs(OrgOrgInfoVO vo) throws Exception;
    
    /**
     * 기관 삭제시 기관의 게시판 전부를 삭제한다.  
     * @param  OrgOrgInfoVO
     * @return int
     * @throws Exception
     */
    public int deleteBbs(OrgOrgInfoVO vo) throws Exception;
    
    /**
     * 기관 생성시 기본 페이지 정보를 등록한다.  
     * @param  OrgOrgInfoVO
     * @return String
     * @throws Exception
     */
    public int insertPage(OrgOrgInfoVO vo) throws Exception;
    
    /**
     * 기관 삭제시 기관의 페이지 전부를 삭제한다.  
     * @param  OrgOrgInfoVO
     * @return int
     * @throws Exception
     */
    public int deletePage(OrgOrgInfoVO vo) throws Exception;
    
    /**
     * 기관 생성시 기본 이메일 템플릿 정보를 등록한다.  
     * @param  OrgOrgInfoVO
     * @return String
     * @throws Exception
     */
    public int insertEmailTpl(OrgOrgInfoVO vo) throws Exception;
    
    /**
     * 기관 삭제시 기관의 이메일 템플릿 전부를 삭제한다.  
     * @param  OrgOrgInfoVO
     * @return int
     * @throws Exception
     */
    public int deleteEmailTpl(OrgOrgInfoVO vo) throws Exception;
    
    /**
     * 기관 삭제시 기관의 도메인 전부를 삭제한다.  
     * @param  OrgOrgInfoVO
     * @return int
     * @throws Exception
     */
    public int deleteDomain(OrgOrgInfoVO vo) throws Exception;
    
    /**
     * 기관의 로그 기록 횟수를 구한다.  
     * @param  OrgOrgInfoVO
     * @return int
     * @throws Exception
     */
    public int selectLogCount(OrgOrgInfoVO vo) throws Exception;
    
    /**
     * 기관 생성시 기본 코드 분류를 등록한다.  
     * @param  OrgOrgInfoVO
     * @return String
     * @throws Exception
     */
    public int insertCodeCtgr(OrgOrgInfoVO vo) throws Exception;
    
    /**
     * 기관 삭제시 코드 분류 전부를 삭제한다.  
     * @param  OrgOrgInfoVO
     * @return int
     * @throws Exception
     */
    public int deleteCodeCtgr(OrgOrgInfoVO vo) throws Exception;
    
    /**
     * 기관 생성시 기본 코드를 등록한다.  
     * @param  OrgOrgInfoVO
     * @return String
     * @throws Exception
     */
    public int insertCode(OrgOrgInfoVO vo) throws Exception;
    
    /**
     * 기관 삭제시 코드 전부를 삭제한다.  
     * @param  OrgOrgInfoVO
     * @return int
     * @throws Exception
     */
    public int deleteCode(OrgOrgInfoVO vo) throws Exception;
    
    /**
     * 기관 생성시 사용자 정보 설정값를 등록한다.  
     * @param  OrgOrgInfoVO
     * @return String
     * @throws Exception
     */
    public int insertUserInfoCfg(OrgOrgInfoVO vo) throws Exception;
    
    /**
     * 기관 삭제시 사용자 정보 설정값 전부를 삭제한다.  
     * @param  OrgOrgInfoVO
     * @return int
     * @throws Exception
     */
    public int deleteUserInfoCfg(OrgOrgInfoVO vo) throws Exception;
    
    /**
     * 기관 생성시 지식분류 코드값를 등록한다.  
     * @param  OrgOrgInfoVO
     * @return String
     * @throws Exception
     */
    public int insertKnowCtgr(OrgOrgInfoVO vo) throws Exception;
    
    /**
     * 기관 삭제시 사용자의 지식분류 코드값 전부를 삭제한다.  
     * @param  OrgOrgInfoVO
     * @return int
     * @throws Exception
     */
    public int deleteUserKnowCtgr(OrgOrgInfoVO vo) throws Exception;   
    
    /**
     * 기관 삭제시 지식분류 코드값 전부를 삭제한다.  
     * @param  OrgOrgInfoVO
     * @return int
     * @throws Exception
     */
    public int deleteKnowCtgr(OrgOrgInfoVO vo) throws Exception;    
    
    /**
     * 기관 삭제시 관련사이트 정보 삭제
     * @param  OrgOrgInfoVO
     * @return int
     * @throws Exception
     */
    public int deleteRelatedSite(OrgOrgInfoVO vo) throws Exception; 
    
    /**
     * 기관 삭제시 관련사이트 분류의 정보 삭제
     * @param  OrgOrgInfoVO
     * @return int
     * @throws Exception
     */
    public int deleteRelatedSiteCtgr(OrgOrgInfoVO vo) throws Exception;    
    
    
    /**
     * 산업인력공단 API 사용여부 확인
     * @param  OrgOrgInfoVO
     * @return String
     * @throws Exception
     */
    public String selectHrdApiUseYn(String orgCd) throws Exception;    
}
