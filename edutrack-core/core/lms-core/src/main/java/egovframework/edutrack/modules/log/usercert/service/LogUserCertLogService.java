package egovframework.edutrack.modules.log.usercert.service;

import egovframework.edutrack.comm.service.ProcessResultListVO;

public interface LogUserCertLogService {


	/**
	 * 사용자 접속 로그 페이징 목록을 조회한다.
	 * @param LogUserCertLogVO
	 * @param pageIndex
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<LogUserCertLogVO> listPageing(LogUserCertLogVO vo) throws Exception;
	
	
	/**
	 * 사용자 접속 로그 정보를 등록한다.
	 * @param LogUserCertLogVO
	 * @return String
	 * @throws Exception
	 */
	public void add(LogUserCertLogVO vo) throws Exception;



}