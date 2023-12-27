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
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<SysCodeVO> list(SysCodeVO vo) throws Exception;
	
    /**
	 * 코드 정보의 검색된 수를 카운트 한다. 
	 * @param  SysCodeVO 
	 * @return int
	 * @throws Exception
	 */
	public int count(SysCodeVO vo) throws Exception;
	
    /**
	 * 코드 정보의 전체 목록을 조회한다. 
	 * @param  SysCodeVO 
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<SysCodeVO> listPageing(SysCodeVO vo) throws Exception;
	
    /**
	 * 코드 정보의 상세 정보를 조회한다. 
	 * @param  SysCodeVO 
	 * @return SysCodeVO
	 * @throws Exception
	 */
	public SysCodeVO select(SysCodeVO vo) throws Exception;

    /**
     * 코드 정보의 상세 정보를 등록한다.  
     * @param  SysCodeVO
     * @return String
     * @throws Exception
     */
    public int insert(SysCodeVO vo) throws Exception;
    
    /**
     * 코드 정보의 상세 정보를 수정한다. 
     * @param  SysCodeVO
     * @return int
     * @throws Exception
     */
    public int update(SysCodeVO vo) throws Exception;
    
    /**
     * 코드 정보의 상세 정보를 일괄 수정한다. 
     * @param  SysCodeVO
     * @return int
     * @throws Exception
     */
    public int updateBatch(List<SysCodeVO> codeArray) throws Exception;
    
    /**
     * 코드 정보의 상세 정보를 삭제한다.  
     * @param  SysCodeVO
     * @return int
     * @throws Exception
     */
    public int delete(SysCodeVO vo) throws Exception;

    /**
     * 코드 분류 하위의 코드 정보 전체를 상세 정보를 삭제한다.  
     * @param  SysCodeVO
     * @return int
     * @throws Exception
     */
    public int deleteAll(SysCodeVO vo) throws Exception;
    
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
	
	/**
	 * 시스템 코드 목록 (직업구분에 따른 JOB_CTGR )
	 * @param codeCtgrCd
	 * @param codeCd
	 * @return
	 */
	public List<SysCodeVO> listSelectCd(SysCodeVO vo)  throws Exception;

	/**
	 * 시스템 코드 목록 (직업구분에 따른 JOB_CTGR의 CODE_OPTN코드 조회 )
	 * @param codeCtgrCd
	 * @param codeOptn
	 * @return
	 */
	public List<SysCodeVO> listSelectOptn(SysCodeVO vo)  throws Exception;

}
