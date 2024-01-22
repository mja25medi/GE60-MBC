package egovframework.edutrack.modules.course.createcoursetimetable.service.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import egovframework.edutrack.modules.course.createcoursetimetable.service.TimetableVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;


@Mapper("timetableMapper")
public interface TimetableMapper {


	/**
	 * 시간표 목록 조회
	 *
	 * @return ProcessReslutListVO
	 */
	
	public List<TimetableVO> listTimetable(TimetableVO iTimetableVO)   ;


	/**
	 * 시간표 일차 목록 조회
	 *
	 * @return ProcessReslutListVO
	 */
	
	public List<TimetableVO> listTimetableDay(TimetableVO iTimetableVO)   ;

	/**
	 * 시간표 정보 조회
	 *
	 * @return ProcessReslutListVO
	 */
	public TimetableVO selectTimetable(TimetableVO iTimetableVO)   ;

	/**
	 * 시간표 고유 번호 조회
	 *
	 * @return ProcessResultVO
	 */
	public TimetableVO selectTmtabSn()   ;

	/**
	 * 시간표 등록
	 *
	 * @reurn ProcessResultVO
	 */
	public int insertTimetable(TimetableVO iTimetableVO)   ;

	/**
	 * 시간표 수정
	 *
	 * @reurn ProcessResultVO
	 */
	public int updateTimetable(TimetableVO iTimetableVO)   ;
	/**
	 * 시간표 수정 (배치)
	 *
	 * @reurn ProcessResultVO
	 */
	public int updateTimetableBatch(List<TimetableVO> tmArray)   ;

	/**
	 * 시간표 삭제
	 *
	 * @reurn ProcessResultVO
	 */
	public int deleteTimetable(TimetableVO iTimetableVO)   ;



}
