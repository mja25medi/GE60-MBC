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
     * @throws Exception
     */
    public int merge(OrgAuthGrpMenuVO vo) throws Exception;
    
    /**
     * 권한 그룹 메뉴의 상세 정보를 삭제한다.  
     * @param  OrgAuthGrpMenuVO
     * @return int
     * @throws Exception
     */
    public int delete(OrgAuthGrpMenuVO vo) throws Exception;
    
    /**
     * 권한 그룹 메뉴를 초기화 등록한다.  
     * @param  OrgAuthGrpMenuVO
     * @return String
     * @throws Exception
     */
    public int insertInit(OrgAuthGrpMenuVO vo) throws Exception;
    
    /**
     * 권한 그룹 메뉴를 초기화 삭제한다.  
     * @param  OrgAuthGrpMenuVO
     * @return int
     * @throws Exception
     */
    public int deleteInit(OrgAuthGrpMenuVO vo) throws Exception;    
}
