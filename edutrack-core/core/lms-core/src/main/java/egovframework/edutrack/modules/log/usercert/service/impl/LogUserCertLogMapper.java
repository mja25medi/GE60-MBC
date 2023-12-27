package egovframework.edutrack.modules.log.usercert.service.impl;

import java.util.List;
import egovframework.edutrack.modules.log.usercert.service.LogUserCertLogVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 *  <b>로그 - 사용자 접속 로그</b> 영역  Mapper 클래스
 * @author Jamfam
 *
 */
@Mapper("logUserCertLogMapper")
public interface LogUserCertLogMapper  {

	
    /**
	 * 사용자 본인인증 로그의 검색된 수를 카운트 한다. 
	 * @param  LogUserCertLogVO 
	 * @return int
	 * @throws Exception
	 */
	public int count(LogUserCertLogVO vo) throws Exception;
	
    /**
	 * 사용자 본인인증 로그의 페이징 목록을 조회한다. 
	 * @param  LogUserCertLogVO 
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<LogUserCertLogVO> listPageing(LogUserCertLogVO vo) throws Exception;
	
    /**
     * 사용자 본인인증 로그의 상세 정보를 등록한다.  
     * @param  LogUserCertLogVO
     * @return String
     * @throws Exception
     */
    public int insert(LogUserCertLogVO vo) throws Exception;


}
