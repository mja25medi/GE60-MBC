package egovframework.edutrack.modules.log.connect.service.impl;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.modules.log.connect.service.ConnectLogVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 *  <b>로그 - 예외 로그</b> 영역  DAO 클래스
 * @author Jamfam
 *
 */
@Mapper("connectLogMapper")
public interface ConnectLogMapper {

	public abstract ProcessResultListVO<ConnectLogVO> list(ConnectLogVO vo);

	public abstract ProcessResultListVO<ConnectLogVO> listMonth(
			ConnectLogVO vo);

	public abstract ProcessResultListVO<ConnectLogVO> listWeek(
			ConnectLogVO vo);

	public abstract ProcessResultListVO<ConnectLogVO> listDay(
			ConnectLogVO vo);

	public abstract ProcessResultListVO<ConnectLogVO> listHour(
			ConnectLogVO vo);

	public abstract ProcessResultVO<ConnectLogVO> insert(ConnectLogVO vo);

	public abstract ProcessResultVO<ConnectLogVO> update(ConnectLogVO vo);

	public ProcessResultVO<ConnectLogVO> merge(ConnectLogVO vo);

	public abstract ProcessResultVO<ConnectLogVO> selectAutoDate(ConnectLogVO vo);

	public abstract ProcessResultListVO<ConnectLogVO> listHourByDay(ConnectLogVO vo);

	public abstract ProcessResultListVO<ConnectLogVO> listDayByWeek(ConnectLogVO vo);

	public abstract ProcessResultListVO<ConnectLogVO> listDayByMonth(ConnectLogVO vo);
}

