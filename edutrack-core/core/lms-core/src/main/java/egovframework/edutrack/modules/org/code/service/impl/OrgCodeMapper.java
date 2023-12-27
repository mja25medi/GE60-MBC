package egovframework.edutrack.modules.org.code.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Repository;

import egovframework.edutrack.comm.util.web.BeanUtils;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.org.code.service.OrgCodeVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 *  <b>기관 - 기관 코드 정보 관리</b> 영역  DAO 클래스
 * @author Jamfam
 *
 */
@Mapper("orgCodeMapper")
public interface OrgCodeMapper{

    /**
	 * 코드 정보의 전체 목록을 조회한다. 
	 * @param  OrgCodeVO 
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<OrgCodeVO> list(OrgCodeVO vo) throws Exception;
	
    /**
	 * 코드 정보의 검색된 수를 카운트 한다. 
	 * @param  OrgCodeVO 
	 * @return int
	 * @throws Exception
	 */
	public int count(OrgCodeVO vo) throws Exception;
	
    /**
	 * 코드 정보의 전체 목록을 조회한다. 
	 * @param  OrgCodeVO 
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<OrgCodeVO> listPageing(OrgCodeVO vo) throws Exception;
	
    /**
	 * 코드 정보의 상세 정보를 조회한다. 
	 * @param  OrgCodeVO 
	 * @return OrgCodeVO
	 * @throws Exception
	 */
	public OrgCodeVO select(OrgCodeVO vo) throws Exception;

    /**
     * 코드 정보의 상세 정보를 등록한다.  
     * @param  OrgCodeVO
     * @return String
     * @throws Exception
     */
    public int insert(OrgCodeVO vo) throws Exception;
    
    /**
     * 코드 정보의 상세 정보를 수정한다. 
     * @param  OrgCodeVO
     * @return int
     * @throws Exception
     */
    public int update(OrgCodeVO vo) throws Exception;
    
    /**
     * 코드 정보의 상세 정보를 일괄 수정한다. 
     * @param  OrgCodeVO
     * @return int
     * @throws Exception
     */
    public int updateBatch(List<OrgCodeVO> codeArray) throws Exception;    
    
    /**
     * 코드 정보의 상세 정보를 삭제한다.  
     * @param  OrgCodeVO
     * @return int
     * @throws Exception
     */
    public int delete(OrgCodeVO vo) throws Exception;

    /**
     * 코드 분류 하위의 코드 정보 전체를 상세 정보를 삭제한다.  
     * @param  OrgCodeVO
     * @return int
     * @throws Exception
     */
    public int deleteAll(OrgCodeVO vo) throws Exception;
    
    /**
	 * 설정 테이블에 코드의 버전 값을 조회 한다.
	 * @return int
	 * @throws Exception
	 */
	public int selectVersion() throws Exception;
    
    /**
	 * 설정 테이블에 코드의 버전 값을 증가 시킨다.
	 * @return int
	 * @throws Exception
	 */
	public int updateVersion() throws Exception;	
	
	public String selectKey(OrgCodeVO vo) throws Exception;
}
