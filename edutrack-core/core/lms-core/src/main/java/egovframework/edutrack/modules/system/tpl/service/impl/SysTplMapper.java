package egovframework.edutrack.modules.system.tpl.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Repository;

import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.system.tpl.service.SysTplVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 *  <b>시스템 - 시스템 템플릿 관리</b> 영역  DAO 클래스
 * @author Jamfam
 *
 */
@Mapper("sysTplMapper")
public interface SysTplMapper {

    /**
	 * 시스템 템플릿의 전체 목록을 조회한다. 
	 * @param  SysTplVO 
	 * @return List
	 * @
	 */
	
	public List<SysTplVO> list(SysTplVO vo) ;
	
    /**
	 * 시스템 템플릿의 검색된 수를 카운트 한다. 
	 * @param  SysTplVO 
	 * @return int
	 * @
	 */
	public int count(SysTplVO vo) ;
	
    /**
	 * 시스템 템플릿의 전체 목록을 조회한다. 
	 * @param  SysTplVO 
	 * @return List
	 * @
	 */
	
	public List<SysTplVO> listPageing(SysTplVO vo)  ;
	
    /**
	 * 시스템 템플릿의 상세 정보를 조회한다. 
	 * @param  SysTplVO 
	 * @return SysTplVO
	 * @
	 */
	public SysTplVO select(SysTplVO vo) ;

    /**
     * 시스템 템플릿의 상세 정보를 등록한다.  
     * @param  SysTplVO
     * @return String
     * @
     */
    public int insert(SysTplVO vo) ;
    
    /**
     * 시스템 템플릿의 상세 정보를 수정한다. 
     * @param  SysTplVO
     * @return int
     * @
     */
    public int update(SysTplVO vo)  ;
    
    /**
     * 시스템 템플릿의 상세 정보를 삭제한다.  
     * @param  SysTplVO
     * @return int
     * @
     */
    public int delete(SysTplVO vo)  ; 
}
