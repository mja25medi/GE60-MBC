package egovframework.edutrack.modules.system.code.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Repository;

import egovframework.edutrack.comm.util.web.BeanUtils;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.system.code.service.SysCodeVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 *  <b>시스템 - 시스템 코드 정보 관리</b> 영역  DAO 클래스
 * @author Jamfam
 *
 */
@Mapper("sysCodeMapper")
public interface SysCodeMapper {

    /**
	 * 코드 정보의 전체 목록을 조회한다. 
	 * @param  SysCodeVO 
	 * @return List
	 * @
	 */
	
	public List<SysCodeVO> list(SysCodeVO vo) ;
	
    /**
	 * 코드 정보의 검색된 수를 카운트 한다. 
	 * @param  SysCodeVO 
	 * @return int
	 * @
	 */
	public int count(SysCodeVO vo) ;
	
    /**
	 * 코드 정보의 전체 목록을 조회한다. 
	 * @param  SysCodeVO 
	 * @return List
	 * @
	 */
	
	public List<SysCodeVO> listPageing(SysCodeVO vo) ;
	
    /**
	 * 코드 정보의 상세 정보를 조회한다. 
	 * @param  SysCodeVO 
	 * @return SysCodeVO
	 * @
	 */
	public SysCodeVO select(SysCodeVO vo) ;

    /**
     * 코드 정보의 상세 정보를 등록한다.  
     * @param  SysCodeVO
     * @return String
     * @
     */
    public int insert(SysCodeVO vo) ;
    
    /**
     * 코드 정보의 상세 정보를 수정한다. 
     * @param  SysCodeVO
     * @return int
     * @
     */
    public int update(SysCodeVO vo) ;
    
    /**
     * 코드 정보의 상세 정보를 일괄 수정한다. 
     * @param  SysCodeVO
     * @return int
     * @
     */
    public int updateBatch(List<SysCodeVO> codeArray) ;
    
    /**
     * 코드 정보의 상세 정보를 삭제한다.  
     * @param  SysCodeVO
     * @return int
     * @
     */
    public int delete(SysCodeVO vo) ;

    /**
     * 코드 분류 하위의 코드 정보 전체를 상세 정보를 삭제한다.  
     * @param  SysCodeVO
     * @return int
     * @
     */
    public int deleteAll(SysCodeVO vo) ;
    
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
	
	/**
	 * 시스템 코드 목록 (직업구분에 따른 JOB_CTGR )
	 * @param codeCtgrCd
	 * @param codeCd
	 * @return
	 */
	public List<SysCodeVO> listSelectCd(SysCodeVO vo)  ;

	/**
	 * 시스템 코드 목록 (직업구분에 따른 JOB_CTGR의 CODE_OPTN코드 조회 )
	 * @param codeCtgrCd
	 * @param codeOptn
	 * @return
	 */
	public List<SysCodeVO> listSelectOptn(SysCodeVO vo)  ;

}
