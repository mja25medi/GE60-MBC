package egovframework.edutrack.modules.log.userconn.service;

import javax.servlet.http.HttpServletRequest;

import egovframework.edutrack.comm.service.ProcessResultListVO;

public interface LogUserConnLogService {

	/**
	 *  사용자 접속 로그 전체 목록을 조회한다.
	 * @param LogUserConnLogVO
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<LogUserConnLogVO> list(
			LogUserConnLogVO vo) throws Exception;

	/**
	 * 사용자 접속 로그 페이징 목록을 조회한다.
	 * @param LogUserConnLogVO
	 * @param pageIndex
	 * @param listScale
	 * @param pageScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<LogUserConnLogVO> listPageing(
			LogUserConnLogVO vo, int pageIndex, int listScale, int pageScale)
			throws Exception;

	/**
	 * 사용자 접속 로그 페이징 목록을 조회한다.
	 * @param LogUserConnLogVO
	 * @param pageIndex
	 * @param listScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<LogUserConnLogVO> listPageing(
			LogUserConnLogVO vo, int pageIndex, int listScale) throws Exception;

	/**
	 * 사용자 접속 로그 페이징 목록을 조회한다.
	 * @param LogUserConnLogVO
	 * @param pageIndex
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<LogUserConnLogVO> listPageing(
			LogUserConnLogVO vo) throws Exception;

	/**
	 * 사용자 접속 로그 정보를 등록한다.
	 * @param LogUserConnLogVO
	 * @return String
	 * @throws Exception
	 */
	public abstract int add(HttpServletRequest request) throws Exception;

	/**
	 * 사용자 접속 로그 정보를 수정한다.
	 * @param LogUserConnLogVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int edit(LogUserConnLogVO vo) throws Exception;

}