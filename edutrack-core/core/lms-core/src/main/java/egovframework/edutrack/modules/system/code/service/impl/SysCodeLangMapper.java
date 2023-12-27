package egovframework.edutrack.modules.system.code.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Repository;

import egovframework.edutrack.comm.util.web.BeanUtils;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.system.code.service.SysCodeLangVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 *  <b>시스템 - 시스템 코드 언어 관리</b> 영역  DAO 클래스
 * @author Jamfam
 *
 */
@Mapper("sysCodeLangMapper")
public interface SysCodeLangMapper {

    /**
	 * 코드 언어의 전체 목록을 조회한다. 
	 * @param  SysCodeLangVO 
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<SysCodeLangVO> list(SysCodeLangVO vo) throws Exception;
	
    /**
	 * 코드 언어의 검색된 수를 카운트 한다. 
	 * @param  SysCodeLangVO 
	 * @return int
	 * @throws Exception
	 */
	public int count() throws Exception;
	
    /**
	 * 코드 언어의 전체 목록을 조회한다. 
	 * @param  SysCodeLangVO 
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<SysCodeLangVO> listPageing(SysCodeLangVO vo) throws Exception;
	
    /**
	 * 코드 언어의 상세 정보를 조회한다. 
	 * @param  SysCodeLangVO 
	 * @return SysCodeLangVO
	 * @throws Exception
	 */
	public SysCodeLangVO select(SysCodeLangVO vo) throws Exception ;

    /**
     * 코드 언어의 상세 정보를 등록한다.  
     * @param  SysCodeLangVO
     * @return String
     * @throws Exception
     */
    public int insert(SysCodeLangVO vo) throws Exception;
    
    /**
     * 코드 언어의 상세 정보를 수정한다. 
     * @param  SysCodeLangVO
     * @return int
     * @throws Exception
     */
    public int update(SysCodeLangVO vo) throws Exception;
    
    /**
     * 코드 언어의 상세 정보를 삭제한다.  
     * @param  SysCodeLangVO
     * @return int
     * @throws Exception
     */
    public int delete(SysCodeLangVO vo) throws Exception;
    
    /**
     * 코드 정보의 하위 코드 언어 전체를 삭제한다.  
     * @param  SysCodeLangVO
     * @return int
     * @throws Exception
     */
    public int deleteAll(SysCodeLangVO vo) throws Exception;
    
    /**
     * 코드 분류 정보 하위의 코드 언어 전체를 삭제한다.  
     * @param  SysCodeLangVO
     * @return int
     * @throws Exception
     */
    public int deleteAllByCtgr(SysCodeLangVO vo) throws Exception;
}
