package egovframework.edutrack.modules.system.file.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Repository;

import egovframework.edutrack.comm.util.web.BeanUtils;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 *  <b>시스템 - 시스템 파일 관리</b> 영역  DAO 클래스
 * @author Jamfam
 *
 */
@Mapper("sysFileMapper")
public interface SysFileMapper {

    /**
	 * 파일의 키를 생성한다. 
	 * @param  SysFileVO 
	 * @return SysFileVO
	 * @throws Exception
	 */
	public int selectKey() throws Exception;
	/**
	 * 파일의 전체 목록을 조회한다. 
	 * @param  SysFileVO 
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<SysFileVO> list(SysFileVO vo) throws Exception;
	
    /**
	 * 파일의 검색된 수를 카운트 한다. 
	 * @param  SysFileVO 
	 * @return int
	 * @throws Exception
	 */
	public int count(SysFileVO vo) throws Exception;
	
    /**
	 * 파일의 전체 목록을 조회한다. 
	 * @param  SysFileVO 
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<SysFileVO> listPageing(SysFileVO vo) throws Exception ;
	
    /**
	 * 파일의 상세 정보를 조회한다. 
	 * @param  SysFileVO 
	 * @return SysFileVO
	 * @throws Exception
	 */
	public SysFileVO select(SysFileVO vo) throws Exception;
	

    /**
     * 파일의 상세 정보를 등록한다.  
     * @param  SysFileVO
     * @return String
     * @throws Exception
     */
    public int insert(SysFileVO vo) throws Exception;
    
    /**
     * 파일의 바인딩 정보를 수정한다. 
     * @param  SysFileVO
     * @return int
     * @throws Exception
     */
    public int updateFileBindData(SysFileVO vo) throws Exception ;
    
    /**
     * 파일의 바인딩 정보를 삭제한다. 
     * @param  SysFileVO
     * @return int
     * @throws Exception
     */
    public int deleteFileBindData(SysFileVO vo) throws Exception ;
    
    /**
     * 파일의 상세 정보를 삭제한다.  
     * @param  SysFileVO
     * @return int
     * @throws Exception
     */
    public int delete(SysFileVO vo) throws Exception;
    
    /**
     * 파일의 접근 정보를 갱신 한다. 
     * @param  SysFileVO
     * @return int
     * @throws Exception
     */
    public int updateFileLastInqDttm(SysFileVO vo) throws Exception ;
}
