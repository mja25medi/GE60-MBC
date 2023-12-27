package egovframework.edutrack.modules.user.info.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Repository;

import egovframework.edutrack.comm.util.web.BeanUtils;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.user.info.service.UsrUserAuthGrpVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 *  <b>사용자 - 사용자 권한 그룹 정보 관리</b> 영역  DAO 클래스
 * @author Jamfam
 *
 */
@Mapper("usrUserAuthGrpMapper")
public interface UsrUserAuthGrpMapper  {
	
    /**
	 * 사용자 권한 정보의 전체 목록을 조회한다. 
	 * @param  UsrUserAuthGrpVO 
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<UsrUserAuthGrpVO> list(UsrUserAuthGrpVO vo) throws Exception;
	
    /**
	 * 사용자 권한 정보의 상세 정보를 조회한다.
	 * @param  UsrUserAuthGrpVO 
	 * @return UsrUserAuthGrpVO
	 * @throws Exception
	 */
	public UsrUserAuthGrpVO select(UsrUserAuthGrpVO vo) throws Exception;
	
    /**
     * 사용자 권한 정보의 상세 정보를 등록한다.  
     * @param  UsrUserAuthGrpVO
     * @return String
     * @throws Exception
     */
    public int insert(UsrUserAuthGrpVO vo) throws Exception;
    
    /**
     * 사용자 권한 정보 단일 항목을 삭제한다.  
     * @param  UsrUserAuthGrpVO
     * @return int
     * @throws Exception
     */
    public int delete(UsrUserAuthGrpVO vo) throws Exception ;

    /**
     * 사용자 권한 정보중 메뉴 구분의 전체 권한을 삭제한다.  
     * @param  UsrUserAuthGrpVO
     * @return int
     * @throws Exception
     */
    public int deleteAll(UsrUserAuthGrpVO vo) throws Exception ;
    
    /**
     * 사용자 권한 정보 전체를 삭제한다.  
     * @param  UsrUserAuthGrpVO
     * @return int
     * @throws Exception
     */
    public int deleteAllUser(UsrUserAuthGrpVO vo) throws Exception ;
}
