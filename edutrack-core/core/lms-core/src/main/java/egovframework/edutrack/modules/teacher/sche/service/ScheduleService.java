package egovframework.edutrack.modules.teacher.sche.service;

import java.io.OutputStream;

import org.springframework.transaction.annotation.Transactional;

import egovframework.edutrack.comm.exception.ServiceProcessException;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;


public interface ScheduleService {

	/**
	 * 시스템코드 카테고리 등록
	 *
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<ScheduleForm> addSchedule(
			ScheduleForm scheduleForm) throws Exception;
	

	public abstract ProcessResultListVO<ScheduleProfVO> profList() throws Exception;
	
	
	
	/**
	 * month 목록 조회
	 * 
	 * @return ProcessResultListVO
	 */
	public abstract ProcessResultListVO<CalendarVO> listScheMonth(CalendarVO calendarDto) throws Exception;
	public abstract ProcessResultListVO<CalendarVO> listScheMonthMy(CalendarVO calendarDto) throws Exception;
	
	public abstract ProcessResultVO<ScheduleVO> selectSchedule(ScheduleVO scheduleVO) throws Exception;
	
	public abstract ProcessResultVO<ScheduleVO> editSchedule(ScheduleVO scheduleVO) throws Exception;
	
	public abstract ProcessResultVO<ScheduleVO> deleteSchedule(ScheduleVO scheduleVO) throws Exception;
	
	public abstract ProcessResultVO<ScheduleVO> deleteAllSchedule(ScheduleVO scheduleVO) throws Exception;
	
	
	/**
	 * 강사 일정 조회(엑셀다운로드)
	 * 
	 * @return ProcessResultListVO
	 * @throws Exception 
	 */
	public abstract ProcessResultListVO<ScheduleVO> listScheduleResult(ScheduleVO scheduleVO) throws Exception;
	public abstract void listScheMonthExcelDownload(ScheduleVO scheduleVO,  OutputStream os) throws ServiceProcessException, Exception;

	
}