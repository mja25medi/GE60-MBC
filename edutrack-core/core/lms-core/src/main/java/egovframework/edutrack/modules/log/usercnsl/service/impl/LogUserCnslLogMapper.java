package egovframework.edutrack.modules.log.usercnsl.service.impl;

import java.util.List;
import egovframework.edutrack.modules.log.usercert.service.LogUserCertLogVO;
import egovframework.edutrack.modules.log.usercnsl.service.LogUserCnslLogVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 *  <b>로그 - 사용자 상담내역 조회 로그</b> 영역  Mapper 클래스
 *
 */
@Mapper("logUserCnslLogMapper")
public interface LogUserCnslLogMapper  {

    /**
	 * 사용자 상담 내역 전체 카운트
	 * @param  LogUserCnslLogVO 
	 * @return int
	 * @
	 */
	public int count(LogUserCnslLogVO vo);
	
    /**
	 * 사용자 상담 내역 페이징
	 * @param  LogUserCnslLogVO 
	 * @return List<LogUserCnslLogVO>
	 * @
	 */
	public List<LogUserCnslLogVO> listPageing(LogUserCnslLogVO vo);
	
    /**
	 * 사용자 상담 내역 1대1문의 답변 조회
	 * @param  LogUserCnslLogVO 
	 * @return List<LogUserCnslLogVO>
	 * @
	 */
	public List<LogUserCnslLogVO> selectQnaAnsr(LogUserCnslLogVO vo);
	
    /**
	 * 사용자 상담 내역 이의제기 답변 조회
	 * @param  LogUserCnslLogVO 
	 * @return List<LogUserCnslLogVO>
	 * @
	 */
	public List<LogUserCnslLogVO> listObjtAnsr(LogUserCnslLogVO vo);
	
    /**
	 * 사용자 상담 내역 질의응답 답변 조회
	 * @param  LogUserCnslLogVO 
	 * @return List<LogUserCnslLogVO>
	 * @
	 */
	public List<LogUserCnslLogVO> listLecQnaAnsr(LogUserCnslLogVO vo);
}
