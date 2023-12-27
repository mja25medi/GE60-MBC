package egovframework.edutrack.modules.log.sysuser.service;

import java.io.OutputStream;

import egovframework.edutrack.comm.service.ProcessResultListVO;

public interface LogSysUserLogService {

	/**
	 * 보기 유형에 따른 시스템 접속 현황을 가져온다.
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<LogSysUserLogVO> listLog(
			LogSysUserLogVO vo) throws Exception;

	/**
	 * 일주, 한달, 일년 이전 날짜 검색
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public abstract LogSysUserLogVO viewAutoDate(LogSysUserLogVO vo)
			throws Exception;

	/**
	 * 홈페이지 접속 통계 엑셀다운로드
	 *
	 * @return  ProcessResultDTO
	 */
	public abstract void listExcelDownload(LogSysUserLogVO vo,
			OutputStream os) throws Exception;

}