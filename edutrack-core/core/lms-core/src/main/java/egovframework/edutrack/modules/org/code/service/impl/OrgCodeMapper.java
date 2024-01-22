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
	 * @
	 */
	
	public List<OrgCodeVO> list(OrgCodeVO vo) ;
	
    /**
	 * 코드 정보의 검색된 수를 카운트 한다. 
	 * @param  OrgCodeVO 
	 * @return int
	 * @
	 */
	public int count(OrgCodeVO vo) ;
	
    /**
	 * 코드 정보의 전체 목록을 조회한다. 
	 * @param  OrgCodeVO 
	 * @return List
	 * @
	 */
	
	public List<OrgCodeVO> listPageing(OrgCodeVO vo) ;
	
    /**
	 * 코드 정보의 상세 정보를 조회한다. 
	 * @param  OrgCodeVO 
	 * @return OrgCodeVO
	 * @
	 */
	public OrgCodeVO select(OrgCodeVO vo) ;

    /**
     * 코드 정보의 상세 정보를 등록한다.  
     * @param  OrgCodeVO
     * @return String
     * @
     */
    public int insert(OrgCodeVO vo) ;
    
    /**
     * 코드 정보의 상세 정보를 수정한다. 
     * @param  OrgCodeVO
     * @return int
     * @
     */
    public int update(OrgCodeVO vo) ;
    
    /**
     * 코드 정보의 상세 정보를 일괄 수정한다. 
     * @param  OrgCodeVO
     * @return int
     * @
     */
    public int updateBatch(List<OrgCodeVO> codeArray) ;    
    
    /**
     * 코드 정보의 상세 정보를 삭제한다.  
     * @param  OrgCodeVO
     * @return int
     * @
     */
    public int delete(OrgCodeVO vo) ;

    /**
     * 코드 분류 하위의 코드 정보 전체를 상세 정보를 삭제한다.  
     * @param  OrgCodeVO
     * @return int
     * @
     */
    public int deleteAll(OrgCodeVO vo) ;
    
    /**
	 * 설정 테이블에 코드의 버전 값을 조회 한다.
	 * @return int
	 * @
	 */
	public int selectVersion() ;
    
    /**
	 * 설정 테이블에 코드의 버전 값을 증가 시킨다.
	 * @return int
	 * @
	 */
	public int updateVersion() ;	
	
	public String selectKey(OrgCodeVO vo) ;
}
