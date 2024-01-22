package egovframework.edutrack.modules.org.code.service.impl;

import java.util.List;

import egovframework.edutrack.modules.org.code.service.OrgCodeCtgrVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 *  <b>기관 - 기관 코드 분류 관리</b> 영역  DAO 클래스
 * @author Jamfam
 *
 */
@Mapper("orgCodeCtgrMapper")
public interface OrgCodeCtgrMapper{
	
    /**
	 * 코드 분류의 전체 목록을 조회한다. 
	 * @param  OrgCodeCtgrVO 
	 * @return List
	 * @
	 */
	
	public List<OrgCodeCtgrVO> list(OrgCodeCtgrVO vo) ;
	
    /**
	 * 코드 분류의 검색된 수를 카운트 한다. 
	 * @param  OrgCodeCtgrVO 
	 * @return int
	 * @
	 */
	public int count(OrgCodeCtgrVO vo) ;
	
    /**
	 * 코드 분류의 전체 목록을 조회한다. 
	 * @param  OrgCodeCtgrVO 
	 * @return List
	 * @
	 */
	
	public List<OrgCodeCtgrVO> listPageing(OrgCodeCtgrVO vo) ;
	
    /**
	 * 코드 분류의 상세 정보를 조회한다. 
	 * @param  OrgCodeCtgrVO 
	 * @return OrgCodeCtgrVO
	 * @
	 */
	public OrgCodeCtgrVO select(OrgCodeCtgrVO vo) ;

    /**
     * 코드 분류의 상세 정보를 등록한다.  
     * @param  OrgCodeCtgrVO
     * @return String
     * @
     */
    public int insert(OrgCodeCtgrVO vo) ;
    
    /**
     * 코드 분류의 상세 정보를 수정한다. 
     * @param  OrgCodeCtgrVO
     * @return int
     * @
     */
    public int update(OrgCodeCtgrVO vo) ;
    
    /**
     * 코드 분류의 상세 정보를 삭제한다.  
     * @param  OrgCodeCtgrVO
     * @return int
     * @
     */
    public int delete(OrgCodeCtgrVO vo) ;    
	
}