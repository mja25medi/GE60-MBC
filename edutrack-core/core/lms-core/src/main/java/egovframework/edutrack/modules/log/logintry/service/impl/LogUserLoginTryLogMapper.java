package egovframework.edutrack.modules.log.logintry.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.log.logintry.service.LogUserLoginTryLogVO;
import egovframework.edutrack.modules.system.config.service.SysCfgCtgrVO;
import egovframework.edutrack.modules.user.info.service.UsrUserInfoVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 *  <b>로그 - 사용자 로그인 시도 로그 관리</b> 영역  DAO 클래스
 * @author Jamfam
 *
 */
@Mapper("logUserLoginTryLogMapper")
public interface LogUserLoginTryLogMapper {
	
    /**
	 * 로그인 시도 로그의 전체 목록을 조회한다. 
	 * @param  LogUserLoginTryLogVO 
	 * @return List
	 * @
	 */
	
	public List<LogUserLoginTryLogVO> list(LogUserLoginTryLogVO vo) ;
	
    /**
	 * 로그인 시도 로그의 검색된 수를 카운트 한다. 
	 * @param  LogUserLoginTryLogVO 
	 * @return int
	 * @
	 */
	public int count(LogUserLoginTryLogVO vo) ;
	
    /**
	 * 로그인 시도 로그의 전체 목록을 조회한다. 
	 * @param  LogUserLoginTryLogVO 
	 * @return List
	 * @
	 */
	
	public List<LogUserLoginTryLogVO> listPageing(LogUserLoginTryLogVO vo) ;
	
    /**
	 * 로그인 시도 로그의 상세 정보를 조회한다. 
	 * @param   
	 * @return int
	 * @
	 */
	public int selectKey() ;
	
    /**
     * 로그인 시도 로그의 상세 정보를 등록한다.  
     * @param  LogUserLoginTryLogVO
     * @return String
     * @
     */
    public void insert(LogUserLoginTryLogVO vo) ;
    
    /**
	 * api용 회원로그인 조회
	 * @param  LogUserLoginTryLogVO 
	 * @return List<EgovMap>
	 * @
	 */
	public List<EgovMap> selectUserLoginApi(LogUserLoginTryLogVO infoVO) ; 

}
