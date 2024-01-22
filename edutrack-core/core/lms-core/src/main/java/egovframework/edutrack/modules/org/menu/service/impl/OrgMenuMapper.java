package egovframework.edutrack.modules.org.menu.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Repository;

import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.org.menu.service.OrgMenuVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 *  <b>기관 - 기관 메뉴 관리</b> 영역  DAO 클래스
 * @author Jamfam
 *
 */
@Mapper("orgMenuMapper")
public interface OrgMenuMapper {
	
    /**
	 * 기관 메뉴의 전체 목록을 조회한다. 
	 * @param  OrgMenuVO 
	 * @return List
	 * @
	 */
	
	public List<OrgMenuVO> list(OrgMenuVO vo) ;
	
    /**
	 * 기관 메뉴의 상세 정보를 조회한다. 
	 * @param  OrgMenuVO 
	 * @return OrgMenuVO
	 * @
	 */
	public OrgMenuVO selectAuthorizeByMenu(OrgMenuVO vo) ;
	
	public OrgMenuVO selectAuthorizeByMenu2(OrgMenuVO vo) ;
	
    /**
	 * 기관 메뉴의 상세 정보를 조회한다. 
	 * @param  OrgMenuVO 
	 * @return OrgMenuVO
	 * @
	 */
	public OrgMenuVO select(OrgMenuVO vo) ;
	
    /**
	 * 기관 메뉴의 전체 목록을 조회한다. 
	 * @param  OrgMenuVO 
	 * @return List
	 * @
	 */
	
	public List<OrgMenuVO> listAuthGrpMenu(OrgMenuVO vo) ;
	
    /**
	 * 기관 메뉴의 키를 생성한다. 
	 * @param  OrgMenuVO 
	 * @return OrgMenuVO
	 * @
	 */
	public String selectKey() ;	

    /**
     * 기관 메뉴의 상세 정보를 등록한다.  
     * @param  OrgMenuVO
     * @return String
     * @
     */
    public int insert(OrgMenuVO vo) ;
    
    /**
     * 기관 메뉴의 상세 정보를 수정한다. 
     * @param  OrgMenuVO
     * @return int
     * @
     */
    public int update(OrgMenuVO vo) ;
    
    /**
     * 기관 메뉴의 정보를 삭제한다.  
     * @param  OrgMenuVO
     * @return int
     * @
     */
    public int delete(OrgMenuVO vo) ;
    
    /**
     * 기관 메뉴의 초기화 등록한다.  
     * @param  OrgMenuVO
     * @return String
     * @
     */
    public int insertInit(OrgMenuVO vo) ;
    
    /**
     * 기관 메뉴의 초기화 삭제한다.  
     * @param  OrgMenuVO
     * @return int
     * @
     */
    public int deleteByMenuLvl(OrgMenuVO vo) ;    

}
