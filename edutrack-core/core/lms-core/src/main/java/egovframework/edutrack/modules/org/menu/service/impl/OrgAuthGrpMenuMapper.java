package egovframework.edutrack.modules.org.menu.service.impl;

import egovframework.edutrack.modules.org.menu.service.OrgAuthGrpMenuVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 *  <b>기관 - 기관 권한 그룹 메뉴 관리</b> 영역  DAO 클래스
 * @author Jamfam
 *
 */
@Mapper("orgAuthGrpMenuMapper")
public interface OrgAuthGrpMenuMapper{

    /**
     * 권한 그룹 메뉴의 상세 정보를 등록한다.  
     * @param  OrgAuthGrpMenuVO
     * @return String
     * @
     */
    public int merge(OrgAuthGrpMenuVO vo) ;
    
    /**
     * 권한 그룹 메뉴의 상세 정보를 삭제한다.  
     * @param  OrgAuthGrpMenuVO
     * @return int
     * @
     */
    public int delete(OrgAuthGrpMenuVO vo) ;
    
    /**
     * 권한 그룹 메뉴를 초기화 등록한다.  
     * @param  OrgAuthGrpMenuVO
     * @return String
     * @
     */
    public int insertInit(OrgAuthGrpMenuVO vo) ;
    
    /**
     * 권한 그룹 메뉴를 초기화 삭제한다.  
     * @param  OrgAuthGrpMenuVO
     * @return int
     * @
     */
    public int deleteInit(OrgAuthGrpMenuVO vo) ;    
}
