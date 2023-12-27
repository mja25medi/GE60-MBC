package egovframework.edutrack.modules.log.exception.service;

import egovframework.edutrack.comm.service.ProcessResultListVO;

public interface LogExceptionService {

	public final static String	EXCEPTION_DIV_SERVICE	= "SERVICE";
	public final static String	EXCEPTION_DIV_ACTION	= "ACTION";
	
	/**
	 * 오류 로그 전체 목록을 조회한다.
	 * @param LogExcepVO
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<LogExceptionVO> list(LogExceptionVO vo)
			throws Exception;

	/**
	 * 오류 로그 페이징 목록을 조회한다.
	 * @param LogExcepVO
	 * @param pageIndex
	 * @param listScale
	 * @param pageScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<LogExceptionVO> listPageing(
			LogExceptionVO vo, int pageIndex, int listScale, int pageScale)
			throws Exception;

	/**
	 * 오류 로그 페이징 목록을 조회한다.
	 * @param LogExcepVO
	 * @param pageIndex
	 * @param listScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<LogExceptionVO> listPageing(
			LogExceptionVO vo, int pageIndex, int listScale) throws Exception;

	/**
	 * 오류 로그 페이징 목록을 조회한다.
	 * @param LogExcepVO
	 * @param pageIndex
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<LogExceptionVO> listPageing(
			LogExceptionVO vo, int pageIndex) throws Exception;

	/**
	 * 오류 로그 정보를 등록한다.
	 * @param LogExcepVO
	 * @return String
	 * @throws Exception
	 */
	public abstract void add(String exceptionDiv, String methodNm, String exceptionNm, Throwable throwable) throws Exception;

}