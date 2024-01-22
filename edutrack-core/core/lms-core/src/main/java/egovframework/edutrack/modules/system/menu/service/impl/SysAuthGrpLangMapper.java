package egovframework.edutrack.modules.system.menu.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Repository;

import egovframework.edutrack.comm.util.web.BeanUtils;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.system.menu.service.SysAuthGrpLangVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 *  <b>시스템 - 시스템 권한 언어 언어 관리</b> 영역  DAO 클래스
 * @author Jamfam
 *
 */
@Mapper("sysAuthGrpLangMapper")
public interface SysAuthGrpLangMapper {

   /**
	 * 시스템 권한 언어의 전체 목록을 조회한다. 
	 * @param  SysAuthGrpLangVO 
	 * @return List
	 * @
	 */
	
	public List<SysAuthGrpLangVO> list(SysAuthGrpLangVO vo) ;
	
     /**
	 * 시스템 권한 언어의 상세 정보를 조회한다. 
	 * @param  SysAuthGrpLangVO 
	 * @return SysAuthGrpLangVO
	 * @
	 */
	public SysAuthGrpLangVO select(SysAuthGrpLangVO vo) ;

    /**
     * 시스템 권한 언어의 상세 정보를 등록한다.  
     * @param  SysAuthGrpLangVO
     * @return String
     * @
     */
    public int insert(SysAuthGrpLangVO vo) ;
    
    /**
     * 시스템 권한 언어의 상세 정보를 수정한다. 
     * @param  SysAuthGrpLangVO
     * @return int
     * @
     */
    public int update(SysAuthGrpLangVO vo)  ;
    
    /**
     * 시스템 권한 언어의 상세 정보를 삭제한다.  
     * @param  SysAuthGrpLangVO
     * @return int
     * @
     */
    public int delete(SysAuthGrpLangVO vo)  ;
    
    /**
     * 시스템 권한 언어의 전체 정보를 삭제한다.  
     * @param  SysAuthGrpLangVO
     * @return int
     * @
     */
    public int deleteAll(SysAuthGrpLangVO vo)  ;     
}
