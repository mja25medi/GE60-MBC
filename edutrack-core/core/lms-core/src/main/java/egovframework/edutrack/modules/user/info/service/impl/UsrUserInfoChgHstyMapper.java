package egovframework.edutrack.modules.user.info.service.impl;

import java.util.List;

import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Repository;

import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.user.info.service.UsrUserInfoChgHstyVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 *  <b>사용자 - 사용자 정보 변경 로그 관리</b> 영역  DAO 클래스
 * @author Jamfam
 *
 */
@Mapper("usrUserInfoChgHstyMapper")
public interface UsrUserInfoChgHstyMapper{
	
   /**
	 * 사용자 정보 변경 로그의 전체 목록을 조회한다. 
	 * @param  UsrUserInfoChgHstyVO 
	 * @return List
	 * @
	 */
	
	public List<UsrUserInfoChgHstyVO> list(UsrUserInfoChgHstyVO vo) ;
	
    /**
	 * 사용자 정보 변경 로그의 검색된 수를 카운트 한다. 
	 * @param  UsrUserInfoChgHstyVO 
	 * @return int
	 * @
	 */
	public int count(UsrUserInfoChgHstyVO vo) ;
	
    /**
	 * 사용자 정보 변경 로그의 전체 목록을 조회한다. 
	 * @param  UsrUserInfoChgHstyVO 
	 * @return List
	 * @
	 */
	
	public List<UsrUserInfoChgHstyVO> listPageing(UsrUserInfoChgHstyVO vo)  ;
	
    /**
	 * 사용자 정보 변경 로그의 상세 정보를 조회한다. 
	 * @param  UsrUserInfoChgHstyVO 
	 * @return UsrUserInfoChgHstyVO
	 * @
	 */
	public UsrUserInfoChgHstyVO select(UsrUserInfoChgHstyVO vo) ;

    /**
     * 사용자 정보 변경 로그의 상세 정보를 등록한다.  
     * @param  UsrUserInfoChgHstyVO
     * @return String
     * @
     */
    public int insert(UsrUserInfoChgHstyVO vo) ;
	
}
