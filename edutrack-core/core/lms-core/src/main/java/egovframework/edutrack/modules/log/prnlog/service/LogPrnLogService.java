package egovframework.edutrack.modules.log.prnlog.service;

import egovframework.edutrack.comm.service.ProcessResultListVO;

public interface LogPrnLogService {

	/**
	 * 개인 정보 출력 로그 전체 목록을 조회한다.
	 * @param LogPrnLogVO
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<LogPrnLogVO> list(LogPrnLogVO vo)
			throws Exception;

	/**
	 * 개인 정보 출력 로그 페이징 목록을 조회한다.
	 * @param LogPrnLogVO
	 * @param pageIndex
	 * @param listScale
	 * @param pageScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<LogPrnLogVO> listPageing(
			LogPrnLogVO vo, int pageIndex, int listScale, int pageScale)
			throws Exception;

	/**
	 * 개인 정보 출력 로그 페이징 목록을 조회한다.
	 * @param LogPrnLogVO
	 * @param pageIndex
	 * @param listScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<LogPrnLogVO> listPageing(
			LogPrnLogVO vo, int pageIndex, int listScale) throws Exception;

	/**
	 * 개인 정보 출력 로그 페이징 목록을 조회한다.
	 * @param LogPrnLogVO
	 * @param pageIndex
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<LogPrnLogVO> listPageing(
			LogPrnLogVO vo, int pageIndex) throws Exception;

	/**
	 * 개인 정보 출력 로그의 상세정보를 조회한다.
	 * @param LogPrnLogVO
	 * @return LogPrnLogVO
	 * @throws Exception
	 */
	public abstract LogPrnLogVO view(LogPrnLogVO vo) throws Exception;

	/**
	 * 개인 정보 출력 로그의 상세정보를 등록한다.
	 * @param LogPrnLogVO
	 * @return String
	 * @throws Exception
	 */
	public abstract int add(LogPrnLogVO vo) throws Exception;

}