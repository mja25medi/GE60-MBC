package egovframework.edutrack.modules.log.login.service;

import java.io.OutputStream;

import egovframework.edutrack.comm.service.ProcessResultListVO;

public interface LogSysLoginLogService {

	/**
	 * 보기 유형에 따른 시스템 로그인 현황을 가져온다.
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<LogSysLoginLogVO> listLog(
			LogSysLoginLogVO vo) throws Exception;
	
	/**
	 * 보기 유형에 따른 모든 시스템 로그인 현황을 가져온다.
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<LogSysLoginLogVO> AllListLog(
			LogSysLoginLogVO vo) throws Exception;

	/**
	 * 일별 로그인 현황 목록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<LogSysLoginLogVO> listLogByDay(
			LogSysLoginLogVO vo) throws Exception;

	/**
	 * 주별 로그인 현황 목록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<LogSysLoginLogVO> listLogByWeek(
			LogSysLoginLogVO vo) throws Exception;

	/**
	 * 월별 로그인 현황 목록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<LogSysLoginLogVO> listLogByMonth(
			LogSysLoginLogVO vo) throws Exception;

	/**
	 * 시스템 로그인 로그 저장
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public abstract int add(LogSysLoginLogVO vo) throws Exception;

	/**
	 * 일주, 한달, 일년 이전 날짜 검색
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public abstract LogSysLoginLogVO viewAutoDate(LogSysLoginLogVO vo)
			throws Exception;

	/**
	 * 홈페이지 로그인 통계 엑셀다운로드
	 *
	 * @return  ProcessResultDTO
	 */
	public abstract void listExcelDownload(LogSysLoginLogVO vo, OutputStream os)
			throws Exception;

}