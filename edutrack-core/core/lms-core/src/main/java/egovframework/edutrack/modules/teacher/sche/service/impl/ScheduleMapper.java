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
	public int selectSchSnKey()  ;
	/**
	 * schDetlSn Key 조회
	 * @param int
	 * @return
	 */
	public int selectSchDetlSnKey()  ;

	/**
	 * 원내교수 일정 관리
	 * 
	 * @reurn 
	 */
	public ScheduleVO insertSchedule(ScheduleVO iSchduleVO) ;
	
	public ScheduleDetailVO insertScheduleDetail(ScheduleDetailVO iSchduleDetailVO) ;
	
	public List<ScheduleProfVO> profList() ;
	
	public List<ScheduleDetailVO> scheduleListContents(ScheduleDetailVO iSchduleDetailVO) ;
	
	public int updateScheduleBatch(List<ScheduleDetailVO> contentsArray) ;
	
	public List<CalendarVO> listSchedule(CalendarVO iSchduleVO) ;
	
	public List<CalendarVO> listScheduleMy(CalendarVO iSchduleVO) ;
	
	public ScheduleVO selectSchedule(ScheduleVO scheduleVO) ;
	
	public int editSchedule(ScheduleVO scheduleVO) ;
	
	public int deleteSchedule(ScheduleVO scheduleVO) ;
	
	public int deleteAllSchedule(ScheduleVO scheduleVO) ;
	
	public List<ScheduleVO> listScheduleResult(ScheduleVO scheduleVO) ;
	
	
}