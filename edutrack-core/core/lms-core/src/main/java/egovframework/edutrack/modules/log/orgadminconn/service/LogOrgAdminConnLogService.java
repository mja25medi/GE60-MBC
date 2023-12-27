package egovframework.edutrack.modules.log.orgadminconn.service;

import egovframework.edutrack.comm.service.ProcessResultListVO;

public interface LogOrgAdminConnLogService {

	/**
	 *  관리자 접속 로그 전체 목록을 조회한다.
	 * @param LogOrgAdminConnLogVO
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<LogOrgAdminConnLogVO> list(
			LogOrgAdminConnLogVO vo) throws Exception;

	/**
	 * 관리자 접속 로그 페이징 목록을 조회한다.
	 * @param LogOrgAdminConnLogVO
	 * @param pageIndex
	 * @param listScale
	 * @param pageScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<LogOrgAdminConnLogVO> listPageing(
			LogOrgAdminConnLogVO vo, int pageIndex, int listScale, int pageScale)
			throws Exception;

	/**
	 * 관리자 접속 로그 페이징 목록을 조회한다.
	 * @param LogOrgAdminConnLogVO
	 * @param pageIndex
	 * @param listScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<LogOrgAdminConnLogVO> listPageing(
			LogOrgAdminConnLogVO vo, int pageIndex, int listScale)
			throws Exception;

	/**
	 * 관리자 접속 로그 페이징 목록을 조회한다.
	 * @param LogOrgAdminConnLogVO
	 * @param pageIndex
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<LogOrgAdminConnLogVO> listPageing(
			LogOrgAdminConnLogVO vo, int pageIndex) throws Exception;

	/**
	 * 관리자 접속 로그 상세 정보를 조회한다.
	 * @param LogOrgAdminConnLogVO
	 * @return LogOrgAdminConnLogVO
	 * @throws Exception
	 */
	public abstract LogOrgAdminConnLogVO view(LogOrgAdminConnLogVO vo)
			throws Exception;

 	/**
 	 * 관리자 접속 로그 최종 접속 키 정보를 조회한다.
 	 * @param LogOrgAdminConnLogVO
 	 * @return int
 	 * @throws Exception
 	 */
	public abstract int viewLast(LogOrgAdminConnLogVO vo) throws Exception;
	
	/**
	 * 관리자 접속 로그 정보를 등록한다.
	 * @param LogOrgAdminConnLogVO
	 * @return String
	 * @throws Exception
	 */
	public abstract void add(LogOrgAdminConnLogVO vo) throws Exception;

	/**
	 * 관리자 접속 로그 정보를 수정한다.
	 * @param LogOrgAdminConnLogVO
	 * @return int
	 * @throws Exception
	 */
	public abstract void edit(LogOrgAdminConnLogVO vo) throws Exception;

}