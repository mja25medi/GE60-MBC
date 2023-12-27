package egovframework.edutrack.modules.system.file.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.edutrack.modules.system.file.service.SysFileRepoLangVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 *  <b>시스템 - 시스템 파일 리파지토리 언어 언어 관리</b> 영역  DAO 클래스
 * @author Jamfam
 *
 */
@Mapper("sysFileRepoLangMapper")
public interface SysFileRepoLangMapper {

	/**
	 * 파일 리파지토리 언어의 전체 목록을 조회한다. 
	 * @param  SysFileRepoLangVO 
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<SysFileRepoLangVO> list(SysFileRepoLangVO vo) throws Exception;
	
    /**
     * 파일 리파지토리 언어의 상세 정보를 등록한다.  
     * @param  SysFileRepoLangVO
     * @return String
     * @throws Exception
     */
    public int insert(SysFileRepoLangVO vo) throws Exception;
    
    /**
     * 파일 리파지토리 언어의 상세 정보를 수정한다. 
     * @param  SysFileRepoLangVO
     * @return int
     * @throws Exception
     */
    public int update(SysFileRepoLangVO vo) throws Exception;
    
    /**
     * 파일 리파지토리 언어의 상세 정보를 삭제한다.  
     * @param  SysFileRepoLangVO
     * @return int
     * @throws Exception
     */
    public int delete(SysFileRepoLangVO vo) throws Exception ;
    
    /**
     * 파일 리파지토리 언어의 상세 정보를 삭제한다.  
     * @param  SysFileRepoLangVO
     * @return int
     * @throws Exception
     */
    public int deleteAll(SysFileRepoLangVO vo) throws Exception ;
}
