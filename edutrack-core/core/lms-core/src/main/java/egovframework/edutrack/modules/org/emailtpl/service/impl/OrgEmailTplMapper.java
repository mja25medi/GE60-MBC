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
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<OrgEmailTplVO> list(OrgEmailTplVO vo) throws Exception;
	
    /**
	 * 이메일 템플릿의 검색된 수를 카운트 한다. 
	 * @param  OrgEmailTplVO 
	 * @return int
	 * @throws Exception
	 */
	public int count(OrgEmailTplVO vo) throws Exception;
	
    /**
	 * 이메일 템플릿의 전체 목록을 조회한다. 
	 * @param  OrgEmailTplVO 
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<OrgEmailTplVO> listPageing(OrgEmailTplVO vo) throws Exception;
	
    /**
	 * 이메일 템플릿의 상세 정보를 조회한다. 
	 * @param  OrgEmailTplVO 
	 * @return OrgEmailTplVO
	 * @throws Exception
	 */
	public OrgEmailTplVO select(OrgEmailTplVO vo) throws Exception;
	
    /**
	 * 이메일 템플릿의 키를 생성한다. 
	 * @return String
	 * @throws Exception
	 */
	public String selectKey() throws Exception;	

    /**
     * 이메일 템플릿의 상세 정보를 등록한다.  
     * @param  OrgEmailTplVO
     * @return String
     * @throws Exception
     */
    public int insert(OrgEmailTplVO vo) throws Exception;
    
    /**
     * 이메일 템플릿의 상세 정보를 수정한다. 
     * @param  OrgEmailTplVO
     * @return int
     * @throws Exception
     */
    public int update(OrgEmailTplVO vo) throws Exception;
    
    /**
     * 이메일 템플릿의 상세 정보를 삭제한다.  
     * @param  OrgEmailTplVO
     * @return int
     * @throws Exception
     */
    public int delete(OrgEmailTplVO vo) throws Exception;

    /**
     * 기관의 이메일 템플릿의 전체 삭제한다.  
     * @param  OrgEmailTplVO
     * @return int
     * @throws Exception
     */
    public int deleteAll(OrgEmailTplVO vo) throws Exception;
}
