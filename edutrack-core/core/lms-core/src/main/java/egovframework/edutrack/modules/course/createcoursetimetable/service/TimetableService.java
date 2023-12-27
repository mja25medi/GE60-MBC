package egovframework.edutrack.modules.course.createcoursetimetable.service;

import java.io.OutputStream;

import egovframework.edutrack.comm.exception.ServiceProcessException;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.modules.course.createcourse.service.CreateCourseVO;


public interface TimetableService {

	/**
	 * 개설 과정 시간표 목록 조회
	 * 
	 * @return ProcessReslutListVO
	 */
	public ProcessResultListVO<TimetableVO> listTimetable(TimetableVO iTimetableVO) throws Exception;
	
	/**
	 * 개설 과정 시간표 일차 목록 조회
	 * 
	 * @return ProcessReslutListVO
	 */
	public ProcessResultListVO<TimetableVO> listTimetableDay(TimetableVO iTimetableVO) throws Exception;
	
	/**
	 * 개설 과정 시간표 정보 조회
	 * 
	 * @return ProcessReslutListVO
	 */
	public ProcessResultVO<TimetableVO> viewTimetable(TimetableVO iTimetableVO) throws Exception;

	/**
	 * 개설 과정 강사 등록
	 *  
	 * @reurn ProcessResultVO
	 */
	public ProcessResultVO<TimetableVO> addTimetable(TimetableVO iTimetableVO) throws Exception;

	/**
	 * 개설 과정 강사 수정
	 *  
	 * @reurn ProcessResultVO
	 */
	public ProcessResultVO<TimetableVO> editTimetable(TimetableVO iTimetableVO) throws Exception;
	
	/**
	 * 시간표 순서 변경
	 * @return
	 */
	public ProcessResultVO<TimetableVO> sortTimetable(TimetableVO iTimetableVO) throws Exception;

	/**
	 * 개설 과정 강사 삭제
	 *  
	 * @reurn ProcessResultVO
	 */
	public ProcessResultVO<TimetableVO> deleteTimetable(TimetableVO iTimetableVO) throws Exception;
	
	/** 
	 * 시간표 엑셀 다운로드
	 * @param eduResultVO
	 * @param createCourseVO
	 * @param os
	 * @throws ServiceProcessException
	 */
	public void listExcelDownload(TimetableVO timetableVO, CreateCourseVO createCourseVO, OutputStream os) throws ServiceProcessException;

}