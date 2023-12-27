package egovframework.edutrack.modules.user.dept.service.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;

import egovframework.edutrack.modules.user.dept.service.UsrDeptInfoVO;
import egovframework.edutrack.modules.user.info.service.UsrUserInfoVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 *  <b>사용자 - 사용자  관리</b> 영역  DAO 클래스
 * @author Jamfam
 *
 */
@Mapper("usrDeptInfoMapper")
public interface UsrDeptInfoMapper{

   /**
	 * 부서 정보의 전체 목록을 조회한다. 
	 * @param  UsrDeptInfoVO 
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<UsrDeptInfoVO> list(UsrDeptInfoVO vo) throws Exception;
	
	
	public List<UsrDeptInfoVO> searchList(UsrDeptInfoVO vo) throws Exception;

	
    /**
	 * 부서 정보의 검색된 수를 카운트 한다. 
	 * @param  UsrDeptInfoVO 
	 * @return int
	 * @throws Exception
	 */
	public int count(UsrDeptInfoVO vo) throws Exception;

	
    /**
	 * 부서 정보의 전체 목록을 조회한다. 
	 * @param  UsrDeptInfoVO 
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<UsrDeptInfoVO> listPageing(UsrDeptInfoVO vo) throws Exception ;

	
    /**
	 * 부서 정보의 상세 정보를 조회한다. 
	 * @param  UsrDeptInfoVO 
	 * @return UsrDeptInfoVO
	 * @throws Exception
	 */
	public UsrDeptInfoVO select(UsrDeptInfoVO vo);

    /**
	 * 부서 정보의 키를 생성한다. 
	 * @param  UsrDeptInfoVO 
	 * @return UsrDeptInfoVO
	 * @throws Exception
	 */
	public String selectKey() throws Exception;
	
    /**
     * 부서 정보의 상세 정보를 등록한다.  
     * @param  UsrDeptInfoVO
     * @return String
     * @throws Exception
     */
    public int insert(UsrDeptInfoVO vo) throws Exception;
    
    /**
     * 부서 정보의 상세 정보를 수정한다. 
     * @param  UsrDeptInfoVO
     * @return int
     * @throws Exception
     */
    public int update(UsrDeptInfoVO vo) throws Exception; 
    
    /**
     * 부서 정보의 상세 정보를 삭제한다.  
     * @param  UsrDeptInfoVO
     * @return int
     * @throws Exception
     */
    public int delete(UsrDeptInfoVO vo) throws Exception;

    /**
	 * 기업코드의 중복을 체크한다. 
	 * @param  UsrDeptInfoVO 
	 * @return UsrDeptInfoVO
	 * @throws Exception
	 */
    public String selectDeptCdCheck(UsrDeptInfoVO vo) throws Exception;
    
	/**
	 * 기업관리자  목록을 반환한다.
	 * @param CrsTchVO
	 * @reurn ProcessResultVO
	 */
	@SuppressWarnings("unchecked")
	public List<UsrUserInfoVO> listSearch(UsrDeptInfoVO vo)  throws DataAccessException ;
}
