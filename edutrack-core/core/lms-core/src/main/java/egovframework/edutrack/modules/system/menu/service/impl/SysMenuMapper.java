package egovframework.edutrack.modules.system.menu.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Repository;

import egovframework.edutrack.comm.util.web.BeanUtils;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.system.menu.service.SysAuthGrpMenuVO;
import egovframework.edutrack.modules.system.menu.service.SysMenuVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 *  <b>시스템 - 시스템 메뉴 관리</b> 영역  DAO 클래스
 * @author Jamfam
 *
 */
@Mapper("sysMenuMapper")
public interface SysMenuMapper{
	
    /**
	 * 시스템 메뉴의 전체 목록을 조회한다. 
	 * @param  SysMenuVO 
	 * @return List
	 * @
	 */
	
	public List<SysMenuVO> list(SysMenuVO vo) ;

    /**
	 * 시스템 메뉴의 상세 정보를 조회한다. 
	 * @param  SysMenuVO 
	 * @return SysMenuVO
	 * @
	 */
	public SysMenuVO select(SysMenuVO vo) ;
	
    /**
	 * 권한별 메뉴의 목록을 조회한다. 
	 * @param  SysMenuVO 
	 * @return List
	 * @
	 */
	
	public List<SysMenuVO> listByAuth(SysMenuVO vo) ;
	
    /**
	 * 권한별 메뉴의 상세 정보를 조회한다. 
	 * @param  SysMenuVO 
	 * @return SysMenuVO
	 * @
	 */
	public SysMenuVO selectByAuth(SysMenuVO vo) ;
	
    /**
	 * 시스템 메뉴의 코드를 생성한다. 
	 * @param  SysMenuVO 
	 * @return SysMenuVO
	 * @
	 */
	public String selectCd() ;
	
    /**
     * 시스템 메뉴의 상세 정보를 등록한다.  
     * @param  SysMenuVO
     * @return String
     * @
     */
    public int insert(SysMenuVO vo) ;
    
    /**
     * 시스템 메뉴의 상세 정보를 수정한다. 
     * @param  SysMenuVO
     * @return int
     * @
     */
    public int update(SysMenuVO vo)  ;
    
    /**
     * 시스템 메뉴의 상세 정보를 삭제한다.  
     * @param  SysMenuVO
     * @return int
     * @
     */
    public int delete(SysMenuVO vo)  ;
    
    /**
     * 권한별 메뉴의 사용 권한을 등록한다.  
     * @param  SysMenuVO
     * @return int
     * @
     */
    public int insertAuthGrpMenu(SysAuthGrpMenuVO vo)  ;
    
    /**
     * 권한별 메뉴의 사용 권한을 수정한다.  
     * @param  SysMenuVO
     * @return int
     * @
     */
    public int updateAuthGrpMenu(SysAuthGrpMenuVO vo)  ;
    
    /**
     * 권한 그룹에 부여된 권한 메뉴를 삭제한다.  
     * @param  SysAuthGrpMenuVO
     * @return int
     * @
     */
    public int deleteAuthGrpMenuByAuthGrp(SysAuthGrpMenuVO vo)  ;
    
    /**
     * 메뉴에 연결된 권한 메뉴를 삭제한다.  
     * @param  SysAuthGrpMenuVO
     * @return int
     * @
     */
    public int deleteAuthGrpMenuByMenuCd(SysAuthGrpMenuVO vo)  ;
    
    /**
	 * 설정 테이블에 메뉴의 버전 값을 조회 한다.
	 * @return int
	 * @
	 */
	public int selectVersion() ;
    
    /**
	 * 설정 테이블에 메뉴의 버전 값을 증가 시킨다.
	 * @return int
	 * @
	 */
	public int updateVersion() ;
}
