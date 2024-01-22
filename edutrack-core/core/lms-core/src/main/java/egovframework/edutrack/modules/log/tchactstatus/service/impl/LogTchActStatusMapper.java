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
	public List<LogTchActStatusVO> list(LogTchActStatusVO vo) ;

	/**
	 * 교수자/투터의 목록 수을 가져온다.
	 * 교수자의 과정 연결 수 내역을 포함.
	 * @param UserInfoDTO dto
	 * @return int
	 */
	public int count(LogTchActStatusVO vo)  ;
	/**
	 * 교수자/투터의 목록을 가져온다.
	 * 교수자의 과정 연결 수 내역을 포함.
	 * @param UserInfoDTO dto
	 * @return ProcessResultListDTO<UserInfoDTO>
	 */
	public List<LogTchActStatusVO> listPageing(LogTchActStatusVO vo)  ;

	/**
	 * 해당 기간에 속하는 과정의 목록을 가져온다.
	 * 선택된 교수자의 과정만.
	 * @param UserInfoDTO dto
	 * @return ProcessResultListDTO<UserInfoDTO>
	 */
	public List<LogTchActStatusDetailVO> listCourse(LogTchActStatusDetailVO vo) ;

	/**
	 * 해당 기간에 속하는 과정의 목록 수을 가져온다.
	 * 선택된 교수자의 과정만.
	 * @param UserInfoDTO dto
	 * @return int
	 */
	public int courseCount(LogTchActStatusDetailVO vo) ;
	/**
	 * 해당 기간에 속하는 과정의 목록을 가져온다.
	 * 선택된 교수자의 과정만.
	 * @param UserInfoDTO dto
	 * @return ProcessResultListDTO<UserInfoDTO>
	 */
	public List<LogTchActStatusDetailVO> listCoursePageing(LogTchActStatusDetailVO vo) ;


	public LogTchActStatusVO select(LogTchActStatusVO dto) ;


	public List<LogTchActStatusDetailVO> listMonth(
			LogTchActStatusDetailVO dto)  ;

	public List<LogTchActStatusDetailVO> listWeek(
			LogTchActStatusDetailVO dto)  ;

	public List<LogTchActStatusDetailVO> listDay(
			LogTchActStatusDetailVO dto)  ;
}
