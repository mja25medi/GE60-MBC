package egovframework.edutrack.modules.log.orgstatus.service;

import egovframework.edutrack.comm.service.ProcessResultListVO;

public interface LogOrgStatusService {

	/**
	 * 기관 상태 로그 전체 목록을 조회한다.
	 * @param LogOrgStatusVO
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<LogOrgStatusVO> list(LogOrgStatusVO vo)
			throws Exception;

	/**
	 * 기관 상태 로그 페이징 목록을 조회한다.
	 * @param LogOrgStatusVO
	 * @param pageIndex
	 * @param listScale
	 * @param pageScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<LogOrgStatusVO> listPageing(
			LogOrgStatusVO vo, int pageIndex, int listScale, int pageScale)
			throws Exception;

	/**
	 * 기관 상태 로그 페이징 목록을 조회한다.
	 * @param LogOrgStatusVO
	 * @param pageIndex
	 * @param listScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<LogOrgStatusVO> listPageing(
			LogOrgStatusVO vo, int pageIndex, int listScale) throws Exception;

	/**
	 * 기관 상태 로그 페이징 목록을 조회한다.
	 * @param LogOrgStatusVO
	 * @param pageIndex
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<LogOrgStatusVO> listPageing(
			LogOrgStatusVO vo, int pageIndex) throws Exception;

	/**
	 * 시스템 총 현황 검색
	 * @param LogOrgStatusVO
	 * @return LogOrgStatusVO
	 * @throws Exception
	 */
	public abstract LogOrgStatusVO viewTotalStatus(LogOrgStatusVO vo)
			throws Exception;

	/**
	 * 사이트별 메인페이지 현황
	 * @param LogOrgStatusVO
	 * @return LogOrgStatusVO
	 * @throws Exception
	 */
	public abstract LogOrgStatusVO viewOrgStatus(LogOrgStatusVO vo)
			throws Exception;
	
	/**
	 * 사이트별 카운트 현황
	 * @param LogOrgStatusVO
	 * @return LogOrgStatusVO
	 * @throws Exception
	 */
	public abstract LogOrgStatusVO count(LogOrgStatusVO vo)
			throws Exception;

}