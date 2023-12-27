package egovframework.edutrack.modules.org.menu.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Repository;

import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.org.menu.service.OrgMenuLangVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 *  <b>기관 - 기관 메뉴 언어 관리</b> 영역  DAO 클래스
 * @author Jamfam
 *
 */
@Mapper("orgMenuLangMapper")
public interface OrgMenuLangMapper{

    /**
	 * 기관 메뉴 언어 전체 목록을 조회한다. 
	 * @param  OrgMenuLangVO 
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<OrgMenuLangVO> list(OrgMenuLangVO vo) throws Exception;
	
    /**
	 * 기관 메뉴 언어 상세 정보를 조회한다. 
	 * @param  OrgMenuLangVO 
	 * @return OrgMenuLangVO
	 * @throws Exception
	 */
	public OrgMenuLangVO select(OrgMenuLangVO vo) throws Exception;

    /**
     * 기관 메뉴 언어 상세 정보를 등록한다.  
     * @param  OrgMenuLangVO
     * @return String
     * @throws Exception
     */
    public int insert(OrgMenuLangVO vo) throws Exception;
    
    /**
     * 기관 메뉴 언어 상세 정보를 수정한다. 
     * @param  OrgMenuLangVO
     * @return int
     * @throws Exception
     */
    public int update(OrgMenuLangVO vo) throws Exception;
    
    /**
     * 기관 메뉴 언어 상세 정보를 삭제한다.  
     * @param  OrgMenuLangVO
     * @return int
     * @throws Exception
     */
    public int delete(OrgMenuLangVO vo) throws Exception;
    
    /**
     * 기관 메뉴 언어 전체 삭제한다.  
     * @param  OrgMenuLangVO
     * @return int
     * @throws Exception
     */
    public int deleteAll(OrgMenuLangVO vo) throws Exception; 
    
    /**
     * 기관 메뉴 언어 초기화 등록한다.  
     * @param  OrgMenuLangVO
     * @return String
     * @throws Exception
     */
    public int insertInit(OrgMenuLangVO vo) throws Exception; 
    
    /**
     * 기관 메뉴 언어 초기화 삭제한다.  
     * @param  OrgMenuLangVO
     * @return int
     * @throws Exception
     */
    public int deleteInit(OrgMenuLangVO vo) throws Exception;     
}
