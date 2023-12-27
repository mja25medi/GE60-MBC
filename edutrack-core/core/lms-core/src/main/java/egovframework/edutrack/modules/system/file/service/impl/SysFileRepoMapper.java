package egovframework.edutrack.modules.system.file.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Repository;

import egovframework.edutrack.comm.util.web.BeanUtils;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.system.file.service.SysFileRepoVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 *  <b>시스템 - 시스템 파일 리파지토리 관리</b> 영역  DAO 클래스
 * @author Jamfam
 *
 */
@Mapper("sysFileRepoMapper")
public interface SysFileRepoMapper {
	
   /**
	 * 파일 리파지토리의 전체 목록을 조회한다. 
	 * @param  SysFileRepoVO 
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<SysFileRepoVO> list(SysFileRepoVO vo) throws Exception;
	
    /**
	 * 파일 리파지토리의 검색된 수를 카운트 한다. 
	 * @param  SysFileRepoVO 
	 * @return int
	 * @throws Exception
	 */
	public int count(SysFileRepoVO vo) throws Exception;
	
    /**
	 * 파일 리파지토리의 전체 목록을 조회한다. 
	 * @param  SysFileRepoVO 
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<SysFileRepoVO> listPageing(SysFileRepoVO vo) throws Exception ;
	
    /**
	 * 파일 리파지토리의 상세 정보를 조회한다. 
	 * @param  SysFileRepoVO 
	 * @return SysFileRepoVO
	 * @throws Exception
	 */
	public SysFileRepoVO select(SysFileRepoVO vo) throws Exception;

    /**
     * 파일 리파지토리의 상세 정보를 등록한다.  
     * @param  SysFileRepoVO
     * @return String
     * @throws Exception
     */
    public int insert(SysFileRepoVO vo) throws Exception;
    
    /**
     * 파일 리파지토리의 상세 정보를 수정한다. 
     * @param  SysFileRepoVO
     * @return int
     * @throws Exception
     */
    public int update(SysFileRepoVO vo) throws Exception;
    
    /**
     * 파일 리파지토리의 상세 정보를 삭제한다.  
     * @param  SysFileRepoVO
     * @return int
     * @throws Exception
     */
    public int delete(SysFileRepoVO vo) throws Exception;

}
