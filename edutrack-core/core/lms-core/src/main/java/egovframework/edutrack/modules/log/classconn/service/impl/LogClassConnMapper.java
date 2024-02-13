package egovframework.edutrack.modules.log.classconn.service.impl;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import egovframework.edutrack.modules.log.classconn.service.LogClassConnVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;


@Mapper("logClassConnMapper")
public interface LogClassConnMapper {


	/**
	 * 강의실 접속 로그를 등록한다.
	 * @param VO
	 * @return
	 */
	
	public int merge(LogClassConnVO VO) ;

	/**
	 * 종료일을 기준으로 시작일을 검색한다.
	 * 1일, 일주(7일), 한달(30일), 일년(365맇)
	 * @param VO
	 * @return
	 */
	
	public LogClassConnVO selectAutoDate(LogClassConnVO VO)  ;

	/**
	 * 삭제 회차 로그 데이터 삭제한다
	 * @return
	 */
	public void delClassConLog(LogClassConnVO lccvo);
}
