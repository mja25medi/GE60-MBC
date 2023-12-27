package egovframework.edutrack.modules.system.menu.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Repository;

import egovframework.edutrack.comm.util.web.BeanUtils;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.system.menu.service.SysAuthGrpVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 *  <b>시스템 - 시스템 권한 관리</b> 영역  DAO 클래스
 * @author Jamfam
 *
 */
@Mapper("sysAuthGrpMapper")
public interface SysAuthGrpMapper{
	
    /**
	 * 시스템 권한의 전체 목록을 조회한다. 
	 * @param  SysAuthGrpVO 
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<SysAuthGrpVO> list(SysAuthGrpVO vo) throws Exception;
	
     /**
	 * 시스템 권한의 상세 정보를 조회한다. 
	 * @param  SysAuthGrpVO 
	 * @return SysAuthGrpVO
	 * @throws Exception
	 */
	public SysAuthGrpVO select(SysAuthGrpVO vo) throws Exception;

    /**
     * 시스템 권한의 상세 정보를 등록한다.  
     * @param  SysAuthGrpVO
     * @return String
     * @throws Exception
     */
    public int insert(SysAuthGrpVO vo) throws Exception;
    
    /**
     * 시스템 권한의 상세 정보를 수정한다. 
     * @param  SysAuthGrpVO
     * @return int
     * @throws Exception
     */
    public int update(SysAuthGrpVO vo) throws Exception;
    
    /**
     * 시스템 권한의 상세 정보를 삭제한다.  
     * @param  SysAuthGrpVO
     * @return int
     * @throws Exception
     */
    public int delete(SysAuthGrpVO vo) throws Exception ; 
}
