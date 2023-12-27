package egovframework.edutrack.modules.log.tchactstatus.service.impl;

import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import egovframework.edutrack.modules.log.tchactstatus.service.LogTchActStatusDetailVO;
import egovframework.edutrack.modules.log.tchactstatus.service.LogTchActStatusVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("logTchActStatusMapper")
public interface LogTchActStatusMapper{

	/**
	 * 교수자/투터의 목록을 가져온다.
	 * 교수자의 과정 연결 수 내역을 포함.
	 * @param UserInfoDTO dto
	 * @return ProcessResultListDTO<UserInfoDTO>
	 */
	public List<LogTchActStatusVO> list(LogTchActStatusVO vo) throws Exception;

	/**
	 * 교수자/투터의 목록 수을 가져온다.
	 * 교수자의 과정 연결 수 내역을 포함.
	 * @param UserInfoDTO dto
	 * @return int
	 */
	public int count(LogTchActStatusVO vo)  throws Exception;
	/**
	 * 교수자/투터의 목록을 가져온다.
	 * 교수자의 과정 연결 수 내역을 포함.
	 * @param UserInfoDTO dto
	 * @return ProcessResultListDTO<UserInfoDTO>
	 */
	public List<LogTchActStatusVO> listPageing(LogTchActStatusVO vo)  throws Exception;

	/**
	 * 해당 기간에 속하는 과정의 목록을 가져온다.
	 * 선택된 교수자의 과정만.
	 * @param UserInfoDTO dto
	 * @return ProcessResultListDTO<UserInfoDTO>
	 */
	public List<LogTchActStatusDetailVO> listCourse(LogTchActStatusDetailVO vo) throws Exception;

	/**
	 * 해당 기간에 속하는 과정의 목록 수을 가져온다.
	 * 선택된 교수자의 과정만.
	 * @param UserInfoDTO dto
	 * @return int
	 */
	public int courseCount(LogTchActStatusDetailVO vo) throws Exception;
	/**
	 * 해당 기간에 속하는 과정의 목록을 가져온다.
	 * 선택된 교수자의 과정만.
	 * @param UserInfoDTO dto
	 * @return ProcessResultListDTO<UserInfoDTO>
	 */
	public List<LogTchActStatusDetailVO> listCoursePageing(LogTchActStatusDetailVO vo) throws Exception;


	public LogTchActStatusVO select(LogTchActStatusVO dto) throws Exception;


	public List<LogTchActStatusDetailVO> listMonth(
			LogTchActStatusDetailVO dto)  throws Exception;

	public List<LogTchActStatusDetailVO> listWeek(
			LogTchActStatusDetailVO dto)  throws Exception;

	public List<LogTchActStatusDetailVO> listDay(
			LogTchActStatusDetailVO dto)  throws Exception;
}
