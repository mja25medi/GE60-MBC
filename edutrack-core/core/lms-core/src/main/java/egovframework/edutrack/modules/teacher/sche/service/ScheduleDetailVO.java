/**
 *
 */
package egovframework.edutrack.modules.teacher.sche.service;

import egovframework.edutrack.comm.service.DefaultVO;



/**
 * 시스템 코드 VO
 */

public class ScheduleDetailVO extends DefaultVO{

	
	/**
	 * 
	 */
	private static final long	serialVersionUID	= -6493519173660420109L;
	private String userNo     ;
	private int    schSn      ;
	private int    schDetlSn ;
	private String year        ;
	private String month       ;
	private String day         ;
	private String startTm    ;
	private String endTm      ;
	private String startYear      ;
	private String startMonth      ;
	private String startDay      ;
	private String endYear      ;
	private String endMonth      ;
	private String endDay      ;
	private String startHour    ;
	private String startMinute    ;
	private String endHour    ;
	private String endMinute    ;
	private String dDay    ;
	private String tDay    ;
	
	
	
	
	/**
	 * 생성자
	 */
	public ScheduleDetailVO() {
		// 
	}




	public String getUserNo() {
		return userNo;
	}




	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}




	public int getSchSn() {
		return schSn;
	}




	public void setSchSn(int schSn) {
		this.schSn = schSn;
	}




	public int getSchDetlSn() {
		return schDetlSn;
	}




	public void setSchDetlSn(int schDetlSn) {
		this.schDetlSn = schDetlSn;
	}




	public String getYear() {
		return year;
	}




	public void setYear(String year) {
		this.year = year;
	}




	public String getMonth() {
		return month;
	}




	public void setMonth(String month) {
		this.month = month;
	}




	public String getDay() {
		return day;
	}




	public void setDay(String day) {
		this.day = day;
	}




	public String getStartTm() {
		return startTm;
	}




	public void setStartTm(String startTm) {
		this.startTm = startTm;
	}




	public String getEndTm() {
		return endTm;
	}




	public void setEndTm(String endTm) {
		this.endTm = endTm;
	}




	public String getStartYear() {
		return startYear;
	}




	public void setStartYear(String startYear) {
		this.startYear = startYear;
	}




	public String getStartMonth() {
		return startMonth;
	}




	public void setStartMonth(String startMonth) {
		this.startMonth = startMonth;
	}




	public String getStartDay() {
		return startDay;
	}




	public void setStartDay(String startDay) {
		this.startDay = startDay;
	}




	public String getEndYear() {
		return endYear;
	}




	public void setEndYear(String endYear) {
		this.endYear = endYear;
	}




	public String getEndMonth() {
		return endMonth;
	}




	public void setEndMonth(String endMonth) {
		this.endMonth = endMonth;
	}




	public String getEndDay() {
		return endDay;
	}




	public void setEndDay(String endDay) {
		this.endDay = endDay;
	}




	public String getStartHour() {
		return startHour;
	}




	public void setStartHour(String startHour) {
		this.startHour = startHour;
	}




	public String getStartMinute() {
		return startMinute;
	}




	public void setStartMinute(String startMinute) {
		this.startMinute = startMinute;
	}




	public String getEndHour() {
		return endHour;
	}




	public void setEndHour(String endHour) {
		this.endHour = endHour;
	}




	public String getEndMinute() {
		return endMinute;
	}




	public void setEndMinute(String endMinute) {
		this.endMinute = endMinute;
	}




	public String getdDay() {
		return dDay;
	}




	public void setdDay(String dDay) {
		this.dDay = dDay;
	}




	public String gettDay() {
		return tDay;
	}




	public void settDay(String tDay) {
		this.tDay = tDay;
	}
	
	

	

}