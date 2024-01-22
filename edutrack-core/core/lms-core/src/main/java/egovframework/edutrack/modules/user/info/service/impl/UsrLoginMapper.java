package egovframework.edutrack.modules.user.info.service.impl;

import egovframework.edutrack.modules.user.info.service.UsrLoginVO;
import egovframework.edutrack.modules.user.info.service.UsrUserInfoVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 *  <b>사용자 - 사용자 로그인 정보 관리</b> 영역  DAO 클래스
 * @author Jamfam
 *
 */
@Mapper("usrLoginMapper")
public interface UsrLoginMapper{
	
    /**
	 * 사용자 로그인의 상세 정보를 조회한다. 
	 * @param  UsrLoginVO 
	 * @return UsrLoginVO
	 * @
	 */
	public UsrLoginVO select(UsrLoginVO vo) ;

    /**
	 * 사용자 ID의 중복을 체크한다. 
	 * @param  UsrLoginVO 
	 * @return UsrLoginVO
	 * @
	 */
	public String selectIdCheck(UsrLoginVO vo);
	
	 /**
		 * 심사용 예외 아이디 여부 체크한다. 
		 * @param  UsrLoginVO 
		 * @return UsrLoginVO
		 * @
	 */
	public String selectExceptionIdCheck(UsrLoginVO vo) ;
	
	/**
	 * 사용자 SSO ID의 중복을 체크한다. 
	 * @param  UsrLoginVO 
	 * @return UsrLoginVO
	 * @
	 */
	public String selectSsoIdCheck(UsrUserInfoVO vo) ;
	
  /**
	 * snsDiv가 있는지  체크한다. 
	 * @param  UsrLoginVO 
	 * @return UsrLoginVO
	 * @
	 */
	public String snsDivCheck(UsrLoginVO vo) ;
	
    /**
     * 사용자의 로그인 정보를 등록한다.  
     * @param  UsrLoginVO
     * @return String
     * @
     */
    public int insert(UsrLoginVO vo) ;
    
    /**
     * 사용자의 로그인 정보를 수정한다. 
     * @param  UsrLoginVO
     * @return int
     * @
     */
    public int update(UsrLoginVO vo) ;
    
    /**
     * 사용자의 로그인 정보를 삭제한다.  
     * @param  UsrLoginVO
     * @return int
     * @
     */
    public int delete(UsrLoginVO vo) ;
    
    /**
     * 사용자의 로그인 횟수를 증가 시킨다. 
     * @param  UsrLoginVO
     * @return int
     * @
     */
    public int updateLoginCount(UsrLoginVO vo)  ;
    
    /**
     * 사용자의 로그인 실패 정보를 저장 한다. 
     * @param  UsrLoginVO
     * @return int
     * @
     */
    public int updateFailInfo(UsrLoginVO vo) ;

    /**
     * 사용자의 비밀번호를 변경한다. 
     * @param  UsrLoginVO
     * @return int
     * @
     */
    public int updatePswd(UsrLoginVO vo) ;

    /**
     * 사용자의 상태를 변경한다. 
     * @param  UsrLoginVO
     * @return int
     * @
     */
    public int updateStatus(UsrLoginVO vo)  ;

    /**
     * 사용자를 탈퇴 처리 한다. 
     * @param  UsrLoginVO
     * @return int
     * @
     */
    public int updateWithdrawal(UsrLoginVO vo) ;

    /**
     * 사용자를 비밀번호 변경일을 연장한다. 
     * @param  UsrLoginVO
     * @return int
     * @
     */
    public int updatePassDate(UsrLoginVO vo) ;
    
    /**
     * 예외용 아이디 정보 업데이트
     * @param  UsrLoginVO
     * @return int
     * @
     */
    public int updateExceptYn(UsrLoginVO vo) ;
    
    /**
     * 사용자의 sns 정보를 업데이트 한다.  
     * @param  UsrLoginVO
     * @return String
     * @
     */
    public String updateSnsDiv(UsrLoginVO vo) ;
    
    public String getEncryptPassword(String password) ;

	public int updatePassAndVerified(UsrLoginVO vo);
}
