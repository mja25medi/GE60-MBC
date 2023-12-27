package egovframework.edutrack.modules.log.usercnsl.service;

import egovframework.edutrack.comm.service.ProcessResultListVO;

public interface LogUserCnslLogService {

	/**
	 * [CRM] 유저 상담 내역 조회
	 * @param LogUserCnslLogVO
	 * @return ProcessResultListVO<LogUserCnslLogVO>
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<LogUserCnslLogVO> listPageing(LogUserCnslLogVO vo) throws Exception;
}