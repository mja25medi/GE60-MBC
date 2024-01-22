package egovframework.edutrack.modules.org.emailtpl.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Repository;

import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.org.emailtpl.service.OrgEmailTplVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 *  <b>기관 - 기관 메일 템플릿 관리</b> 영역  DAO 클래스
 * @author Jamfam
 *
 */
@Mapper("orgEmailTplMapper")
public interface OrgEmailTplMapper{
	
   /**
	 * 이메일 템플릿의 전체 목록을 조회한다. 
	 * @param  OrgEmailTplVO 
	 * @return List
	 * @
	 */
	
	public List<OrgEmailTplVO> list(OrgEmailTplVO vo) ;
	
    /**
	 * 이메일 템플릿의 검색된 수를 카운트 한다. 
	 * @param  OrgEmailTplVO 
	 * @return int
	 * @
	 */
	public int count(OrgEmailTplVO vo) ;
	
    /**
	 * 이메일 템플릿의 전체 목록을 조회한다. 
	 * @param  OrgEmailTplVO 
	 * @return List
	 * @
	 */
	
	public List<OrgEmailTplVO> listPageing(OrgEmailTplVO vo) ;
	
    /**
	 * 이메일 템플릿의 상세 정보를 조회한다. 
	 * @param  OrgEmailTplVO 
	 * @return OrgEmailTplVO
	 * @
	 */
	public OrgEmailTplVO select(OrgEmailTplVO vo) ;
	
    /**
	 * 이메일 템플릿의 키를 생성한다. 
	 * @return String
	 * @
	 */
	public String selectKey() ;	

    /**
     * 이메일 템플릿의 상세 정보를 등록한다.  
     * @param  OrgEmailTplVO
     * @return String
     * @
     */
    public int insert(OrgEmailTplVO vo) ;
    
    /**
     * 이메일 템플릿의 상세 정보를 수정한다. 
     * @param  OrgEmailTplVO
     * @return int
     * @
     */
    public int update(OrgEmailTplVO vo) ;
    
    /**
     * 이메일 템플릿의 상세 정보를 삭제한다.  
     * @param  OrgEmailTplVO
     * @return int
     * @
     */
    public int delete(OrgEmailTplVO vo) ;

    /**
     * 기관의 이메일 템플릿의 전체 삭제한다.  
     * @param  OrgEmailTplVO
     * @return int
     * @
     */
    public int deleteAll(OrgEmailTplVO vo) ;
}
