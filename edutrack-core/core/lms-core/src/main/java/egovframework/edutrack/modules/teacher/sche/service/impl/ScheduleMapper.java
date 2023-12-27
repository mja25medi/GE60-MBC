/**
 *
 */
package egovframework.edutrack.modules.teacher.sche.service.impl;

import java.util.List;

import egovframework.edutrack.modules.teacher.sche.service.CalendarVO;
import egovframework.edutrack.modules.teacher.sche.service.ScheduleDetailVO;
import egovframework.edutrack.modules.teacher.sche.service.ScheduleProfVO;
import egovframework.edutrack.modules.teacher.sche.service.ScheduleVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;



/**
 *  DAO 인터페이스
 * 
 */
@Mapper("scheduleMapper")
public interface ScheduleMapper {

	
	/**
	 * schSn Key 조회
	 * @param int
	 * @return
	 */
	public int selectSchSnKey()  throws Exception;
	/**
	 * schDetlSn Key 조회
	 * @param int
	 * @return
	 */
	public int selectSchDetlSnKey()  throws Exception;

	/**
	 * 원내교수 일정 관리
	 * 
	 * @reurn 
	 */
	public ScheduleVO insertSchedule(ScheduleVO iSchduleVO) throws Exception;
	
	public ScheduleDetailVO insertScheduleDetail(ScheduleDetailVO iSchduleDetailVO) throws Exception;
	
	public List<ScheduleProfVO> profList() throws Exception;
	
	public List<ScheduleDetailVO> scheduleListContents(ScheduleDetailVO iSchduleDetailVO) throws Exception;
	
	public int updateScheduleBatch(List<ScheduleDetailVO> contentsArray) throws Exception;
	@SuppressWarnings("unchecked")
	public List<CalendarVO> listSchedule(CalendarVO iSchduleVO) throws Exception;
	@SuppressWarnings("unchecked")
	public List<CalendarVO> listScheduleMy(CalendarVO iSchduleVO) throws Exception;
	
	public ScheduleVO selectSchedule(ScheduleVO scheduleVO) throws Exception;
	
	public int editSchedule(ScheduleVO scheduleVO) throws Exception;
	
	public int deleteSchedule(ScheduleVO scheduleVO) throws Exception;
	
	public int deleteAllSchedule(ScheduleVO scheduleVO) throws Exception;
	
	public List<ScheduleVO> listScheduleResult(ScheduleVO scheduleVO) throws Exception;
	
	
}