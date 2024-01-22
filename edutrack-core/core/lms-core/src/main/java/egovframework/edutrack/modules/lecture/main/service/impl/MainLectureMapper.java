package egovframework.edutrack.modules.lecture.main.service.impl;

import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import egovframework.edutrack.modules.lecture.main.service.MainLectureVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Mapper("mainLectureMapper")
public interface MainLectureMapper {

	/**
	 * 강의실 정보 조회
	 * @param vo
	 * @return
	 */
	public MainLectureVO select(MainLectureVO vo ) ;
	
	/**
	 * 강의 일정 조회 (과정)
	 * @param vo
	 * @return
	 */
	public MainLectureVO selectCourseSchedule(MainLectureVO vo ) ;
	
	/** 
	 * 강의 일정 조회 (회차)
	 * @param vo
	 * @return
	 */
	public MainLectureVO selectCreateCourseSchedule(MainLectureVO vo ) ;
	
	/**
	 * 미채점 현황 리스트 과정,시험,과제 성적 리스트별 정보를 가져온다
	 * @param vo
	 * @return
	 * @ 
	 */
	public abstract List<EgovMap> listUnScoreStatus(MainLectureVO vo) ;

}
