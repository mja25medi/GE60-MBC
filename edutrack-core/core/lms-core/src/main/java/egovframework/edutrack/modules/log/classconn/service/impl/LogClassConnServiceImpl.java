package egovframework.edutrack.modules.log.classconn.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.edutrack.modules.log.classconn.service.LogClassConnService;
import egovframework.edutrack.modules.log.classconn.service.LogClassConnVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("logClassConnService")
public class LogClassConnServiceImpl
	extends EgovAbstractServiceImpl implements LogClassConnService {

	/** mapper */
    @Resource(name="logClassConnMapper")
 	private LogClassConnMapper 	logClassConnMapper;

	/**
	 * 강의실 접속 로그를 기록한다.
	 * @param dto
	 * @return
	 */
	@Override
	public	int addConnLog(LogClassConnVO dto)  throws Exception {
		return logClassConnMapper.merge(dto);
	}

	/**
	 * 종료일을 기준으로 시작일을 검색한다.
	 * 1일, 일주(7일), 한달(30일), 일년(365맇)
	 * @param iConnectLogDTO
	 * @return
	 */
	@Override
	public	LogClassConnVO viewAutoDate(LogClassConnVO dto)  throws Exception {
		return logClassConnMapper.selectAutoDate(dto);
	}
}
