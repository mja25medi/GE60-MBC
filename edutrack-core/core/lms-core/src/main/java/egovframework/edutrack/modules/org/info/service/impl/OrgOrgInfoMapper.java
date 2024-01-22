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
	 * @
	 */
	
	public List<OrgOrgInfoVO> list(OrgOrgInfoVO vo) ;
	
    /**
	 * 기관 정보의 검색된 수를 카운트 한다. 
	 * @param  OrgOrgInfoVO 
	 * @return int
	 * @
	 */
	public int count(OrgOrgInfoVO vo) ;
	
    /**
	 * 기관 정보의 전체 목록을 조회한다. 
	 * @param  OrgOrgInfoVO 
	 * @return List
	 * @
	 */
	
	public List<OrgOrgInfoVO> listPageing(OrgOrgInfoVO vo) ;
	
    /**
	 * 기관 정보의 상세 정보를 조회한다. 
	 * @param  OrgOrgInfoVO 
	 * @return OrgOrgInfoVO
	 * @
	 */
	public OrgOrgInfoVO select(OrgOrgInfoVO vo) ;
	
    /**
	 * 기관 정보의 상세 정보를 조회한다. 
	 * @param  OrgOrgInfoVO 
	 * @return OrgOrgInfoVO
	 * @
	 */
	public OrgOrgInfoVO selectByDomain(OrgOrgInfoVO vo) ;
	
    /**
	 * 기관 정보의 상세 정보를 조회한다. 
	 * @return String
	 * @
	 */
	public String selectKey() ;	

    /**
     * 기관 정보의 상세 정보를 등록한다.  
     * @param  OrgOrgInfoVO
     * @return String
     * @
     */
    public int insert(OrgOrgInfoVO vo) ;
    
    /**
     * 기관 정보의 상세 정보를 수정한다. 
     * @param  OrgOrgInfoVO
     * @return int
     * @
     */
    public int update(OrgOrgInfoVO vo) ;
    
    /**
     * 기관 정보의 기본 정보만 수정한다. 
     * @param  OrgOrgInfoVO
     * @return int
     * @
     */
    public int updateInfo(OrgOrgInfoVO vo) ;
    
    /**
     * 기관 정보의 템플릿 정보만 수정한다. 
     * @param  OrgOrgInfoVO
     * @return int
     * @
     */
    public int updateTemplate(OrgOrgInfoVO vo) ;    

    /**
     * 기관 정보의 디자인 정보만 수정한다. 
     * @param  OrgOrgInfoVO
     * @return int
     * @
     */
    public int updateDesign(OrgOrgInfoVO vo) ;
    
    /**
     * 기관 정보의 메타버스 정보만 수정한다. 
     * @param  OrgOrgInfoVO
     * @return int
     * @
     */
    public int updateMeta(OrgOrgInfoVO vo) ;
    
    /**
     * 기관 정보의 상세 정보를 삭제한다.  
     * @param  OrgOrgInfoVO
     * @return int
     * @
     */
    public int delete(OrgOrgInfoVO vo) ; 
    
    /**
     * 기관의 메뉴 버전을 가져온다. 
     * @param  OrgOrgInfoVO
     * @return int
     * @
     */
    public int selectMenuVersion(OrgOrgInfoVO vo) ;
    
    /**
     * 기관의 메뉴 버전 값을 증가 시킨다. 
     * @param  OrgOrgInfoVO
     * @return int
     * @
     */
    public int updateMenuVersion(OrgOrgInfoVO vo) ;
    
    /**
     * 기관 생성시 기본 게시판 정보를 등록한다.  
     * @param  OrgOrgInfoVO
     * @return String
     * @
     */
    public int insertBbs(OrgOrgInfoVO vo) ;
    
    /**
     * 기관 삭제시 기관의 게시판 전부를 삭제한다.  
     * @param  OrgOrgInfoVO
     * @return int
     * @
     */
    public int deleteBbs(OrgOrgInfoVO vo) ;
    
    /**
     * 기관 생성시 기본 페이지 정보를 등록한다.  
     * @param  OrgOrgInfoVO
     * @return String
     * @
     */
    public int insertPage(OrgOrgInfoVO vo) ;
    
    /**
     * 기관 삭제시 기관의 페이지 전부를 삭제한다.  
     * @param  OrgOrgInfoVO
     * @return int
     * @
     */
    public int deletePage(OrgOrgInfoVO vo) ;
    
    /**
     * 기관 생성시 기본 이메일 템플릿 정보를 등록한다.  
     * @param  OrgOrgInfoVO
     * @return String
     * @
     */
    public int insertEmailTpl(OrgOrgInfoVO vo) ;
    
    /**
     * 기관 삭제시 기관의 이메일 템플릿 전부를 삭제한다.  
     * @param  OrgOrgInfoVO
     * @return int
     * @
     */
    public int deleteEmailTpl(OrgOrgInfoVO vo) ;
    
    /**
     * 기관 삭제시 기관의 도메인 전부를 삭제한다.  
     * @param  OrgOrgInfoVO
     * @return int
     * @
     */
    public int deleteDomain(OrgOrgInfoVO vo) ;
    
    /**
     * 기관의 로그 기록 횟수를 구한다.  
     * @param  OrgOrgInfoVO
     * @return int
     * @
     */
    public int selectLogCount(OrgOrgInfoVO vo) ;
    
    /**
     * 기관 생성시 기본 코드 분류를 등록한다.  
     * @param  OrgOrgInfoVO
     * @return String
     * @
     */
    public int insertCodeCtgr(OrgOrgInfoVO vo) ;
    
    /**
     * 기관 삭제시 코드 분류 전부를 삭제한다.  
     * @param  OrgOrgInfoVO
     * @return int
     * @
     */
    public int deleteCodeCtgr(OrgOrgInfoVO vo) ;
    
    /**
     * 기관 생성시 기본 코드를 등록한다.  
     * @param  OrgOrgInfoVO
     * @return String
     * @
     */
    public int insertCode(OrgOrgInfoVO vo) ;
    
    /**
     * 기관 삭제시 코드 전부를 삭제한다.  
     * @param  OrgOrgInfoVO
     * @return int
     * @
     */
    public int deleteCode(OrgOrgInfoVO vo) ;
    
    /**
     * 기관 생성시 사용자 정보 설정값를 등록한다.  
     * @param  OrgOrgInfoVO
     * @return String
     * @
     */
    public int insertUserInfoCfg(OrgOrgInfoVO vo) ;
    
    /**
     * 기관 삭제시 사용자 정보 설정값 전부를 삭제한다.  
     * @param  OrgOrgInfoVO
     * @return int
     * @
     */
    public int deleteUserInfoCfg(OrgOrgInfoVO vo) ;
    
    /**
     * 기관 생성시 지식분류 코드값를 등록한다.  
     * @param  OrgOrgInfoVO
     * @return String
     * @
     */
    public int insertKnowCtgr(OrgOrgInfoVO vo) ;
    
    /**
     * 기관 삭제시 사용자의 지식분류 코드값 전부를 삭제한다.  
     * @param  OrgOrgInfoVO
     * @return int
     * @
     */
    public int deleteUserKnowCtgr(OrgOrgInfoVO vo) ;   
    
    /**
     * 기관 삭제시 지식분류 코드값 전부를 삭제한다.  
     * @param  OrgOrgInfoVO
     * @return int
     * @
     */
    public int deleteKnowCtgr(OrgOrgInfoVO vo) ;    
    
    /**
     * 기관 삭제시 관련사이트 정보 삭제
     * @param  OrgOrgInfoVO
     * @return int
     * @
     */
    public int deleteRelatedSite(OrgOrgInfoVO vo) ; 
    
    /**
     * 기관 삭제시 관련사이트 분류의 정보 삭제
     * @param  OrgOrgInfoVO
     * @return int
     * @
     */
    public int deleteRelatedSiteCtgr(OrgOrgInfoVO vo) ;    
    
    
    /**
     * 산업인력공단 API 사용여부 확인
     * @param  OrgOrgInfoVO
     * @return String
     * @
     */
    public String selectHrdApiUseYn(String orgCd) ;    
}
