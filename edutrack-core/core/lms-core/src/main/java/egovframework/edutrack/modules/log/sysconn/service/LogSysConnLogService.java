package egovframework.edutrack.modules.log.sysconn.service;

import java.io.OutputStream;

import egovframework.edutrack.comm.service.ProcessResultListVO;

public interface LogSysConnLogService {

	/**
	 * 보기 유형에 따른 시스템 접속 현황을 가져온다.
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<LogSysConnLogVO> listLog(
			LogSysConnLogVO vo) throws Exception;
	
	/**
	 * 보기 유형에 따른 시스템 접속 현황을 가져온다.
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<LogSysConnLogVO> AllListLog(
			LogSysConnLogVO vo) throws Exception;
	
	/**
	 * 보기 유형에 따른 시스템 접속 현황을 가져온다.
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<LogSysConnLogVO> sysListLog(
			LogSysConnLogVO vo) throws Exception;

	/**
	 * 일별 접속 현황 목록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<LogSysConnLogVO> listLogByDay(
			LogSysConnLogVO vo) throws Exception;

	/**
	 * 주별 접속 현황 목록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<LogSysConnLogVO> listLogByWeek(
			LogSysConnLogVO vo) throws Exception;

	/**
	 * 월별 접속 현황 목록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<LogSysConnLogVO> listLogByMonth(
			LogSysConnLogVO vo) throws Exception;

	/**
	 * 시스템 접속 로그 저장
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public abstract String add(LogSysConnLogVO vo) throws Exception;

	/**
	 * 일주, 한달, 일년 이전 날짜 검색
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public abstract LogSysConnLogVO viewAutoDate(LogSysConnLogVO vo)
			throws Exception;

	/**
	 * 홈페이지 접속 통계 엑셀다운로드
	 *
	 * @return  ProcessResultDTO
	 */
	public abstract void listExcelDownload(LogSysConnLogVO vo,
			OutputStream os) throws Exception;

}