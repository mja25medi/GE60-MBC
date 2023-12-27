package egovframework.edutrack.modules.log.adminconn.service;

import egovframework.edutrack.comm.service.ProcessResultListVO;

public interface LogAdminConnLogService {

	/**
	 *  관리자 접속 로그 전체 목록을 조회한다.
	 * @param LogAdminConnLogVO
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<LogAdminConnLogVO> list(
			LogAdminConnLogVO vo) throws Exception;

	/**
	 * 관리자 접속 로그 페이징 목록을 조회한다.
	 * @param LogAdminConnLogVO
	 * @param pageIndex
	 * @param listScale
	 * @param pageScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<LogAdminConnLogVO> listPageing(
			LogAdminConnLogVO vo, int pageIndex, int listScale, int pageScale)
			throws Exception;

	/**
	 * 관리자 접속 로그 페이징 목록을 조회한다.
	 * @param LogAdminConnLogVO
	 * @param pageIndex
	 * @param listScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<LogAdminConnLogVO> listPageing(
			LogAdminConnLogVO vo, int pageIndex, int listScale)
			throws Exception;

	/**
	 * 관리자 접속 로그 페이징 목록을 조회한다.
	 * @param LogAdminConnLogVO
	 * @param pageIndex
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<LogAdminConnLogVO> listPageing(
			LogAdminConnLogVO vo, int pageIndex) throws Exception;

	/**
	 * 관리자 접속 로그 상세 정보를 조회한다.
	 * @param LogAdminConnLogVO
	 * @return LogAdminConnLogVO
	 * @throws Exception
	 */
	public abstract LogAdminConnLogVO view(LogAdminConnLogVO vo)
			throws Exception;

 	/**
 	 * 관리자 접속 로그 최종 접속 키 정보를 조회한다.
 	 * @param LogAdminConnLogVO
 	 * @return int
 	 * @throws Exception
 	 */
	public abstract int viewLast(LogAdminConnLogVO vo) throws Exception;
	
	/**
	 * 관리자 접속 로그 정보를 등록한다.
	 * @param LogAdminConnLogVO
	 * @return String
	 * @throws Exception
	 */
	public abstract int add(LogAdminConnLogVO vo) throws Exception;

	/**
	 * 관리자 접속 로그 정보를 수정한다.
	 * @param LogAdminConnLogVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int edit(LogAdminConnLogVO vo) throws Exception;

}