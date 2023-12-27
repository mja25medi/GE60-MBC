package egovframework.edutrack.modules.log.classconn.service;

import org.springframework.transaction.annotation.Transactional;

public interface LogClassConnService {

	/**
	 * 강의실 접속 로그를 기록한다.
	 * @param dto
	 * @return
	 */
	public abstract int addConnLog(
			LogClassConnVO vo) throws Exception;

	/**
	 * 종료일을 기준으로 시작일을 검색한다.
	 * 1일, 일주(7일), 한달(30일), 일년(365맇)
	 * @param iConnectLogDTO
	 * @return
	 */
	public abstract LogClassConnVO viewAutoDate(
			LogClassConnVO vo) throws Exception;

}