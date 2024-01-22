package egovframework.edutrack.modules.org.code.service.impl;

import java.util.List;

import egovframework.edutrack.modules.org.code.service.OrgCodeLangVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 *  <b>기관 - 기관 코드 언어 관리</b> 영역  DAO 클래스
 * @author Jamfam
 *
 */
@Mapper("orgCodeLangMapper")
public interface OrgCodeLangMapper{

    /**
	 * 코드 언어의 전체 목록을 조회한다. 
	 * @param  OrgCodeLangVO 
	 * @return List
	 * @
	 */
	
	public List<OrgCodeLangVO> list(OrgCodeLangVO vo) ;
	
    /**
	 * 코드 언어의 검색된 수를 카운트 한다. 
	 * @param  OrgCodeLangVO 
	 * @return int
	 * @
	 */
	public int count() ;
	
    /**
	 * 코드 언어의 전체 목록을 조회한다. 
	 * @param  OrgCodeLangVO 
	 * @return List
	 * @
	 */
	
	public List<OrgCodeLangVO> listPageing(OrgCodeLangVO vo) ;
	
    /**
	 * 코드 언어의 상세 정보를 조회한다. 
	 * @param  OrgCodeLangVO 
	 * @return OrgCodeLangVO
	 * @
	 */
	public OrgCodeLangVO select(OrgCodeLangVO vo) ;

    /**
     * 코드 언어의 상세 정보를 등록한다.  
     * @param  OrgCodeLangVO
     * @return String
     * @
     */
    public int insert(OrgCodeLangVO vo) ;
    
    /**
     * 코드 언어의 상세 정보를 수정한다. 
     * @param  OrgCodeLangVO
     * @return int
     * @
     */
    public int update(OrgCodeLangVO vo) ;
    
    /**
     * 코드 언어의 상세 정보를 삭제한다.  
     * @param  OrgCodeLangVO
     * @return int
     * @
     */
    public int delete(OrgCodeLangVO vo) ;
    
    /**
     * 코드 정보의 하위 코드 언어 전체를 삭제한다.  
     * @param  OrgCodeLangVO
     * @return int
     * @
     */
    public int deleteAll(OrgCodeLangVO vo) ;
    
    /**
     * 코드 분류 정보 하위의 코드 언어 전체를 삭제한다.  
     * @param  OrgCodeLangVO
     * @return int
     * @
     */
    public int deleteAllByCtgr(OrgCodeLangVO vo) ;    
}
