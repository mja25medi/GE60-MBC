package egovframework.edutrack.modules.system.menu.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Repository;

import egovframework.edutrack.comm.util.web.BeanUtils;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.system.menu.service.SysMenuLangVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 *  <b>시스템 - 시스템 메뉴 언어 언어 관리</b> 영역  DAO 클래스
 * @author Jamfam
 *
 */
@Mapper("sysMenuLangMapper")
public interface SysMenuLangMapper {

   /**
	 * 시스템 메뉴 언어의 전체 목록을 조회한다. 
	 * @param  SysMenuLangVO 
	 * @return List
	 * @
	 */
	
	public List<SysMenuLangVO> list(SysMenuLangVO vo) ;
	
     /**
	 * 시스템 메뉴 언어의 상세 정보를 조회한다. 
	 * @param  SysMenuLangVO 
	 * @return SysMenuLangVO
	 * @
	 */
	public SysMenuLangVO select(SysMenuLangVO vo) ;

    /**
     * 시스템 메뉴 언어의 상세 정보를 등록한다.  
     * @param  SysMenuLangVO
     * @return String
     * @
     */
    public int insert(SysMenuLangVO vo) ;
    
    /**
     * 시스템 메뉴 언어의 상세 정보를 수정한다. 
     * @param  SysMenuLangVO
     * @return int
     * @
     */
    public int update(SysMenuLangVO vo)  ;
    
    /**
     * 시스템 메뉴 언어의 상세 정보를 삭제한다.  
     * @param  SysMenuLangVO
     * @return int
     * @
     */
    public int delete(SysMenuLangVO vo)  ;
    
    /**
     * 시스템 메뉴 언어의 전체 정보를 삭제한다.  
     * @param  SysMenuLangVO
     * @return int
     * @
     */
    public int deleteAll(SysMenuLangVO vo)  ;
}
