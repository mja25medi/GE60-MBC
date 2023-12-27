package egovframework.edutrack.modules.log.menuconn.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.log.menuconn.service.LogWwwMenuConnLogService;
import egovframework.edutrack.modules.log.menuconn.service.LogWwwMenuConnLogVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 *  <b>로그 - 홈페이지 메뉴 접속 로그</b> 영역  Service 클래스
 * @author Jamfam
 *
 */
@Service("logWwwMenuConnLogService")
public class LogWwwMenuConnLogServiceImpl 
	extends EgovAbstractServiceImpl implements LogWwwMenuConnLogService {
	
	/** Mapper */
    @Resource(name="logWwwMenuConnLogMapper")
    private LogWwwMenuConnLogMapper 		logWwwMenuConnLogMapper;
    
 	/**
 	 * 홈페이지 메뉴별 접속 로그 정보를 등록한다.
 	 * @param LogWwwMenuConnLogVO
 	 * @return String
 	 * @throws Exception
 	 */
	@Override
	public void add(LogWwwMenuConnLogVO vo) throws Exception {
 		logWwwMenuConnLogMapper.insert(vo);
 	}

}
