/**
 *
 */
package egovframework.edutrack.modules.teacher.sche.service;

import java.util.List;

/**
 * Xdoclet을 위한 태그
 * @struts:form name="scheduleForm"
 */
@SuppressWarnings("serial")
public class ScheduleForm {
	
	/**
	 * 생성자
	 */
	public ScheduleForm() {
		scheduleVO = new ScheduleVO();
		scheduleDetailVO = new ScheduleDetailVO();
		scheduleProfVO = new ScheduleProfVO();
		calendarVO = new CalendarVO();
	}
	

	
	/**
	 * 시스템코드 VO
	 */
	private ScheduleVO scheduleVO;

	/**
	 * 시스템 코드 카테고리 VO
	 */
	private ScheduleDetailVO scheduleDetailVO;
	
	
	private ScheduleProfVO scheduleProfVO;
	/**
	 * 시스템 코드 리스트
	 */
	private List<ScheduleVO> teacherScheduleList;

	/**
	 * 시스템 코드 카테고리 리스트
	 */
	private List<ScheduleDetailVO> teacherScheduleDetailList;
	
	/**
	 * 시스템 코드 카테고리 리스트
	 */
	private List<ScheduleProfVO> teacherScheduleProfList;
	
	private String nowDate;
	
	private CalendarVO calendarVO;
	
	private List<CalendarVO> calendarList;
	
	private String delYn;    //스케쥴 마스타 삭제 구분
	


	/**
	 * @return the systemCodeVO
	 */
	public ScheduleVO getScheduleVO() {
		return scheduleVO;
	}
	
	
	/**
	 * @param systemCodeVO the systemCodeVO to set
	 */
	public void setScheduleVO(ScheduleVO scheduleVO) {
		this.scheduleVO = scheduleVO;
	}

	/**
	 * @return the systemCodeCategoryVO
	 */
	public ScheduleDetailVO getScheduleDetailVO() {
		return scheduleDetailVO;
	}
	
	/**
	 * @param systemCodeCategoryVO the systemCodeCategoryVO to set
	 */
	public void setScheduleDetailVO(ScheduleDetailVO scheduleDetailVO) {
		this.scheduleDetailVO = scheduleDetailVO;
	}
	

	public List<ScheduleVO> getTeacherScheduleList() {
		return teacherScheduleList;
	}


	public void setTeacherScheduleList(List<ScheduleVO> teacherScheduleList) {
		this.teacherScheduleList = teacherScheduleList;
	}


	public List<ScheduleDetailVO> getTeacherScheduleDetailList() {
		return teacherScheduleDetailList;
	}


	public void setTeacherScheduleDetailList(
			List<ScheduleDetailVO> teacherScheduleDetailList) {
		this.teacherScheduleDetailList = teacherScheduleDetailList;
	}



	public ScheduleProfVO getScheduleProfVO() {
		return scheduleProfVO;
	}


	public void setScheduleProfVO(ScheduleProfVO scheduleProfVO) {
		this.scheduleProfVO = scheduleProfVO;
	}
	
	public List<ScheduleProfVO> getTeacherScheduleProfList() {
		return teacherScheduleProfList;
	}


	public void setTeacherScheduleProfList(
			List<ScheduleProfVO> teacherScheduleProfList) {
		this.teacherScheduleProfList = teacherScheduleProfList;
	}


	/**
	 * @return the nowDate
	 */
	public String getNowDate() {
		return nowDate;
	}


	/**
	 * @param nowDate the nowDate to set
	 */
	public void setNowDate(String nowDate) {
		this.nowDate = nowDate;
	}

	
	
	
	
	

	/**
	 * @return the calendarVO
	 */
	public CalendarVO getCalendarVO() {
		return calendarVO;
	}


	/**
	 * @param calendarVO the calendarVO to set
	 */
	public void setCalendarVO(CalendarVO calendarVO) {
		this.calendarVO = calendarVO;
	}


	/**
	 * @return the calendarList
	 */
	public List<CalendarVO> getCalendarList() {
		return calendarList;
	}


	/**
	 * @param calendarList the calendarList to set
	 */
	public void setCalendarList(List<CalendarVO> calendarList) {
		this.calendarList = calendarList;
	}


	/**
	 * @return the delYn
	 */
	public String getDelYn() {
		return delYn;
	}


	/**
	 * @param delYn the delYn to set
	 */
	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}

}