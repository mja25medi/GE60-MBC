package egovframework.edutrack.modules.org.page.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Repository;

import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.org.page.service.OrgPageVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 *  <b>기관 - 기관 페이지 관리</b> 영역  DAO 클래스
 * @author Jamfam
 *
 */
@Mapper("orgPageMapper")
public interface OrgPageMapper{
	
    /**
	 * 기관 페이지의 전체 목록을 조회한다. 
	 * @param  OrgPageVO 
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<OrgPageVO> list(OrgPageVO vo) throws Exception;
	
    /**
	 * 메뉴에 연결되지 않은 기관 페이지의 전체 목록을 조회한다. 
	 * @param  OrgPageVO 
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<OrgPageVO> listForMenu(OrgPageVO vo) throws Exception;	
	
    /**
	 * 기관 페이지의 검색된 수를 카운트 한다. 
	 * @param  OrgPageVO 
	 * @return int
	 * @throws Exception
	 */
	public int count(OrgPageVO vo) throws Exception;
	
    /**
	 * 기관 페이지의 전체 목록을 조회한다. 
	 * @param  OrgPageVO 
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<OrgPageVO> listPageing(OrgPageVO vo) throws Exception;
	
    /**
	 * 기관 페이지의 상세 정보를 조회한다. 
	 * @param  OrgPageVO 
	 * @return OrgPageVO
	 * @throws Exception
	 */
	public OrgPageVO select(OrgPageVO vo) throws Exception;

    /**
     * 기관 페이지의 상세 정보를 등록한다.  
     * @param  OrgPageVO
     * @return String
     * @throws Exception
     */
    public int insert(OrgPageVO vo) throws Exception;
    
    /**
     * 기관 페이지의 상세 정보를 수정한다. 
     * @param  OrgPageVO
     * @return int
     * @throws Exception
     */
    public int update(OrgPageVO vo) throws Exception;
    
    /**
     * 기관 페이지의 상세 정보를 삭제한다.  
     * @param  OrgPageVO
     * @return int
     * @throws Exception
     */
    public int delete(OrgPageVO vo) throws Exception;
    
    /**
     * 기관의 전체 페이지를 삭제한다.  
     * @param  OrgPageVO
     * @return int
     * @throws Exception
     */
    public int deleteAll(OrgPageVO vo) throws Exception;    

    /**
     * 페이지와 메뉴를 연결 한다. 
     * @param  OrgPageVO
     * @return int
     * @throws Exception
     */
    public int updateMenuCd(OrgPageVO vo) throws Exception;
    
    /**
     * 페이지와 메뉴의 연결을 해제 한다. 
     * @param  OrgPageVO
     * @return int
     * @throws Exception
     */
    public int updateMenuCdToNull(OrgPageVO vo) throws Exception;
    
    /**
     * Default가 아닌 모늗 페이지와 메뉴의 연결을 해제 한다. 
     * @param  OrgPageVO
     * @return int
     * @throws Exception
     */
    public int updateAllMenuCdToNull(OrgPageVO vo) throws Exception;     
}
