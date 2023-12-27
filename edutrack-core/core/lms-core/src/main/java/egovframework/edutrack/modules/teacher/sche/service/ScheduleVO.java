/**
 *
 */
package egovframework.edutrack.modules.teacher.sche.service;

import egovframework.edutrack.comm.service.DefaultVO;




/**
 *  VO
 */

public class ScheduleVO extends DefaultVO{


	
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 474645979929766830L;
	private String searchYear;
	private String searchMonth;
	private String searchSchSn;
	private String searchSchDtlSn;
	private String searchUserNo;
	
	private String userNo                      ;
	private String userId ;
	private String userNm ;
	private int    schSn                       ;
	private String schDivCd                   ;
	private String schDivCdnm                   ;
	private String lecDivCd                   ;
	private String lecDivCdnm                   ;
	private String schPurpDivCd              ;
	private String schPurpDivCdnm              ;
	private String otsdLecGoingDttm          ;
	private String otsdLecReturnDttm         ;
	private String reqOrgNm                   ;
	private String place                        ;
	private String trainingCrs                 ;
	private String eduTarget                   ;
	private String lecTitle                    ;
	private int    eduNopCnt                  ;
	private String orgChrgPrsnPhoneno        ;
	private String officialDocRecvYn         ;
	private String officialDocRecvYnnm         ;
	private String otsdLecReptRelevYn       ;
	private String otsdLecReptRelevYnnm       ;
	private String otsdLecReptDataSendYn   ;
	private String otsdLecReptDataSendYnnm   ;
	private String approvalYn                  ;
	private String approvalYnnm                  ;
	private String extrnLecRsltReptSendYn  ;
	private String extrnLecRsltReptSendYnnm  ;
	private String schDesc                    ;
	private String delYn;

	
	

	private String otsdLecGoingDttmDay          ;
	private String otsdLecGoingDttmHour          ;
	private String otsdLecGoingDttmMinute          ;
	private String otsdLecReturnDttmDay        ;
	private String otsdLecReturnDttmHour        ;
	private String otsdLecReturnDttmMinute        ;
	private String bbsCd                     ;
	
	
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
	public ScheduleVO() {
		// 
	}


	
	public String getSearchYear() {
		return this.searchYear;
	}




	
	public void setSearchYear(String searchYear) {
		this.searchYear = searchYear;
	}




	
	public String getSearchMonth() {
		return this.searchMonth;
	}




	
	public void setSearchMonth(String searchMonth) {
		this.searchMonth = searchMonth;
	}




	/**
	 * @return the searchSchSn
	 */
	public String getSearchSchSn() {
		return searchSchSn;
	}




	/**
	 * @param searchSchSn the searchSchSn to set
	 */
	public void setSearchSchSn(String searchSchSn) {
		this.searchSchSn = searchSchSn;
	}




	/**
	 * @return the searchSchDtlSn
	 */
	public String getSearchSchDtlSn() {
		return searchSchDtlSn;
	}




	/**
	 * @param searchSchDtlSn the searchSchDtlSn to set
	 */
	public void setSearchSchDtlSn(String searchSchDtlSn) {
		this.searchSchDtlSn = searchSchDtlSn;
	}




	/**
	 * @return the searchUserNo
	 */
	public String getSearchUserNo() {
		return searchUserNo;
	}




	/**
	 * @param searchUserNo the searchUserNo to set
	 */
	public void setSearchUserNo(String searchUserNo) {
		this.searchUserNo = searchUserNo;
	}




	/**
	 * @return the userNo
	 */
	public String getUserNo() {
		return userNo;
	}




	/**
	 * @param userNo the userNo to set
	 */
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}




	/**
	 * @return the userNm
	 */
	public String getUserNm() {
		return userNm;
	}




	/**
	 * @param userNm the userNm to set
	 */
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}




	/**
	 * @return the schSn
	 */
	public int getSchSn() {
		return schSn;
	}




	/**
	 * @param schSn the schSn to set
	 */
	public void setSchSn(int schSn) {
		this.schSn = schSn;
	}




	/**
	 * @return the schDivCd
	 */
	public String getSchDivCd() {
		return schDivCd;
	}




	/**
	 * @param schDivCd the schDivCd to set
	 */
	public void setSchDivCd(String schDivCd) {
		this.schDivCd = schDivCd;
	}




	/**
	 * @return the schDivCdnm
	 */
	public String getSchDivCdnm() {
		return schDivCdnm;
	}




	/**
	 * @param schDivCdnm the schDivCdnm to set
	 */
	public void setSchDivCdnm(String schDivCdnm) {
		this.schDivCdnm = schDivCdnm;
	}




	/**
	 * @return the lecDivCd
	 */
	public String getLecDivCd() {
		return lecDivCd;
	}




	/**
	 * @param lecDivCd the lecDivCd to set
	 */
	public void setLecDivCd(String lecDivCd) {
		this.lecDivCd = lecDivCd;
	}




	/**
	 * @return the lecDivCdnm
	 */
	public String getLecDivCdnm() {
		return lecDivCdnm;
	}




	/**
	 * @param lecDivCdnm the lecDivCdnm to set
	 */
	public void setLecDivCdnm(String lecDivCdnm) {
		this.lecDivCdnm = lecDivCdnm;
	}




	/**
	 * @return the schPurpDivCd
	 */
	public String getSchPurpDivCd() {
		return schPurpDivCd;
	}




	/**
	 * @param schPurpDivCd the schPurpDivCd to set
	 */
	public void setSchPurpDivCd(String schPurpDivCd) {
		this.schPurpDivCd = schPurpDivCd;
	}




	/**
	 * @return the schPurpDivCdnm
	 */
	public String getSchPurpDivCdnm() {
		return schPurpDivCdnm;
	}




	/**
	 * @param schPurpDivCdnm the schPurpDivCdnm to set
	 */
	public void setSchPurpDivCdnm(String schPurpDivCdnm) {
		this.schPurpDivCdnm = schPurpDivCdnm;
	}




	/**
	 * @return the otsdLecGoingDttm
	 */
	public String getOtsdLecGoingDttm() {
		return otsdLecGoingDttm;
	}




	/**
	 * @param otsdLecGoingDttm the otsdLecGoingDttm to set
	 */
	public void setOtsdLecGoingDttm(String otsdLecGoingDttm) {
		this.otsdLecGoingDttm = otsdLecGoingDttm;
	}




	/**
	 * @return the otsdLecReturnDttm
	 */
	public String getOtsdLecReturnDttm() {
		return otsdLecReturnDttm;
	}




	/**
	 * @param otsdLecReturnDttm the otsdLecReturnDttm to set
	 */
	public void setOtsdLecReturnDttm(String otsdLecReturnDttm) {
		this.otsdLecReturnDttm = otsdLecReturnDttm;
	}




	/**
	 * @return the reqOrgNm
	 */
	public String getReqOrgNm() {
		return reqOrgNm;
	}




	/**
	 * @param reqOrgNm the reqOrgNm to set
	 */
	public void setReqOrgNm(String reqOrgNm) {
		this.reqOrgNm = reqOrgNm;
	}




	/**
	 * @return the place
	 */
	public String getPlace() {
		return place;
	}




	/**
	 * @param place the place to set
	 */
	public void setPlace(String place) {
		this.place = place;
	}




	/**
	 * @return the trainingCrs
	 */
	public String getTrainingCrs() {
		return trainingCrs;
	}




	/**
	 * @param trainingCrs the trainingCrs to set
	 */
	public void setTrainingCrs(String trainingCrs) {
		this.trainingCrs = trainingCrs;
	}




	/**
	 * @return the eduTarget
	 */
	public String getEduTarget() {
		return eduTarget;
	}




	/**
	 * @param eduTarget the eduTarget to set
	 */
	public void setEduTarget(String eduTarget) {
		this.eduTarget = eduTarget;
	}




	/**
	 * @return the lecTitle
	 */
	public String getLecTitle() {
		return lecTitle;
	}




	/**
	 * @param lecTitle the lecTitle to set
	 */
	public void setLecTitle(String lecTitle) {
		this.lecTitle = lecTitle;
	}




	/**
	 * @return the eduNopCnt
	 */
	public int getEduNopCnt() {
		return eduNopCnt;
	}




	/**
	 * @param eduNopCnt the eduNopCnt to set
	 */
	public void setEduNopCnt(int eduNopCnt) {
		this.eduNopCnt = eduNopCnt;
	}




	/**
	 * @return the orgChrgPrsnPhoneno
	 */
	public String getOrgChrgPrsnPhoneno() {
		return orgChrgPrsnPhoneno;
	}




	/**
	 * @param orgChrgPrsnPhoneno the orgChrgPrsnPhoneno to set
	 */
	public void setOrgChrgPrsnPhoneno(String orgChrgPrsnPhoneno) {
		this.orgChrgPrsnPhoneno = orgChrgPrsnPhoneno;
	}




	/**
	 * @return the officialDocRecvYn
	 */
	public String getOfficialDocRecvYn() {
		return officialDocRecvYn;
	}




	/**
	 * @param officialDocRecvYn the officialDocRecvYn to set
	 */
	public void setOfficialDocRecvYn(String officialDocRecvYn) {
		this.officialDocRecvYn = officialDocRecvYn;
	}




	/**
	 * @return the officialDocRecvYnnm
	 */
	public String getOfficialDocRecvYnnm() {
		return officialDocRecvYnnm;
	}




	/**
	 * @param officialDocRecvYnnm the officialDocRecvYnnm to set
	 */
	public void setOfficialDocRecvYnnm(String officialDocRecvYnnm) {
		this.officialDocRecvYnnm = officialDocRecvYnnm;
	}




	/**
	 * @return the otsdLecReptRelevYn
	 */
	public String getOtsdLecReptRelevYn() {
		return otsdLecReptRelevYn;
	}




	/**
	 * @param otsdLecReptRelevYn the otsdLecReptRelevYn to set
	 */
	public void setOtsdLecReptRelevYn(String otsdLecReptRelevYn) {
		this.otsdLecReptRelevYn = otsdLecReptRelevYn;
	}



	
	/**
	 * @return the otsdLecReptRelevYnnm
	 */
	public String getOtsdLecReptRelevYnnm() {
		return otsdLecReptRelevYnnm;
	}




	/**
	 * @param otsdLecReptRelevYnnm the otsdLecReptRelevYnnm to set
	 */
	public void setOtsdLecReptRelevYnnm(String otsdLecReptRelevYnnm) {
		this.otsdLecReptRelevYnnm = otsdLecReptRelevYnnm;
	}




	/**
	 * @return the otsdLecReptDataSendYn
	 */
	public String getOtsdLecReptDataSendYn() {
		return otsdLecReptDataSendYn;
	}




	/**
	 * @param otsdLecReptDataSendYn the otsdLecReptDataSendYn to set
	 */
	public void setOtsdLecReptDataSendYn(String otsdLecReptDataSendYn) {
		this.otsdLecReptDataSendYn = otsdLecReptDataSendYn;
	}




	/**
	 * @return the otsdLecReptDataSendYnnm
	 */
	public String getOtsdLecReptDataSendYnnm() {
		return otsdLecReptDataSendYnnm;
	}




	/**
	 * @param otsdLecReptDataSendYnnm the otsdLecReptDataSendYnnm to set
	 */
	public void setOtsdLecReptDataSendYnnm(String otsdLecReptDataSendYnnm) {
		this.otsdLecReptDataSendYnnm = otsdLecReptDataSendYnnm;
	}




	/**
	 * @return the approvalYn
	 */
	public String getApprovalYn() {
		return approvalYn;
	}




	/**
	 * @param approvalYn the approvalYn to set
	 */
	public void setApprovalYn(String approvalYn) {
		this.approvalYn = approvalYn;
	}




	/**
	 * @return the approvalYnnm
	 */
	public String getApprovalYnnm() {
		return approvalYnnm;
	}




	/**
	 * @param approvalYnnm the approvalYnnm to set
	 */
	public void setApprovalYnnm(String approvalYnnm) {
		this.approvalYnnm = approvalYnnm;
	}




	/**
	 * @return the extrnLecRsltReptSendYn
	 */
	public String getExtrnLecRsltReptSendYn() {
		return extrnLecRsltReptSendYn;
	}




	/**
	 * @param extrnLecRsltReptSendYn the extrnLecRsltReptSendYn to set
	 */
	public void setExtrnLecRsltReptSendYn(String extrnLecRsltReptSendYn) {
		this.extrnLecRsltReptSendYn = extrnLecRsltReptSendYn;
	}




	/**
	 * @return the extrnLecRsltReptSendYnnm
	 */
	public String getExtrnLecRsltReptSendYnnm() {
		return extrnLecRsltReptSendYnnm;
	}




	/**
	 * @param extrnLecRsltReptSendYnnm the extrnLecRsltReptSendYnnm to set
	 */
	public void setExtrnLecRsltReptSendYnnm(String extrnLecRsltReptSendYnnm) {
		this.extrnLecRsltReptSendYnnm = extrnLecRsltReptSendYnnm;
	}




	/**
	 * @return the schDesc
	 */
	public String getSchDesc() {
		return schDesc;
	}




	/**
	 * @param schDesc the schDesc to set
	 */
	public void setSchDesc(String schDesc) {
		this.schDesc = schDesc;
	}




	/**
	 * @return the otsdLecGoingDttmDay
	 */
	public String getOtsdLecGoingDttmDay() {
		return otsdLecGoingDttmDay;
	}




	/**
	 * @param otsdLecGoingDttmDay the otsdLecGoingDttmDay to set
	 */
	public void setOtsdLecGoingDttmDay(String otsdLecGoingDttmDay) {
		this.otsdLecGoingDttmDay = otsdLecGoingDttmDay;
	}




	/**
	 * @return the otsdLecGoingDttmHour
	 */
	public String getOtsdLecGoingDttmHour() {
		return otsdLecGoingDttmHour;
	}




	/**
	 * @param otsdLecGoingDttmHour the otsdLecGoingDttmHour to set
	 */
	public void setOtsdLecGoingDttmHour(String otsdLecGoingDttmHour) {
		this.otsdLecGoingDttmHour = otsdLecGoingDttmHour;
	}




	/**
	 * @return the otsdLecGoingDttmMinute
	 */
	public String getOtsdLecGoingDttmMinute() {
		return otsdLecGoingDttmMinute;
	}




	/**
	 * @param otsdLecGoingDttmMinute the otsdLecGoingDttmMinute to set
	 */
	public void setOtsdLecGoingDttmMinute(String otsdLecGoingDttmMinute) {
		this.otsdLecGoingDttmMinute = otsdLecGoingDttmMinute;
	}




	/**
	 * @return the otsdLecReturnDttmDay
	 */
	public String getOtsdLecReturnDttmDay() {
		return otsdLecReturnDttmDay;
	}




	/**
	 * @param otsdLecReturnDttmDay the otsdLecReturnDttmDay to set
	 */
	public void setOtsdLecReturnDttmDay(String otsdLecReturnDttmDay) {
		this.otsdLecReturnDttmDay = otsdLecReturnDttmDay;
	}




	/**
	 * @return the otsdLecReturnDttmHour
	 */
	public String getOtsdLecReturnDttmHour() {
		return otsdLecReturnDttmHour;
	}




	/**
	 * @param otsdLecReturnDttmHour the otsdLecReturnDttmHour to set
	 */
	public void setOtsdLecReturnDttmHour(String otsdLecReturnDttmHour) {
		this.otsdLecReturnDttmHour = otsdLecReturnDttmHour;
	}




	/**
	 * @return the otsdLecReturnDttmMinute
	 */
	public String getOtsdLecReturnDttmMinute() {
		return otsdLecReturnDttmMinute;
	}




	/**
	 * @param otsdLecReturnDttmMinute the otsdLecReturnDttmMinute to set
	 */
	public void setOtsdLecReturnDttmMinute(String otsdLecReturnDttmMinute) {
		this.otsdLecReturnDttmMinute = otsdLecReturnDttmMinute;
	}




	/**
	 * @return the bbsCd
	 */
	public String getBbsCd() {
		return bbsCd;
	}




	/**
	 * @param bbsCd the bbsCd to set
	 */
	public void setBbsCd(String bbsCd) {
		this.bbsCd = bbsCd;
	}




	/**
	 * @return the schDetlSn
	 */
	public int getSchDetlSn() {
		return schDetlSn;
	}




	/**
	 * @param schDetlSn the schDetlSn to set
	 */
	public void setSchDetlSn(int schDetlSn) {
		this.schDetlSn = schDetlSn;
	}




	/**
	 * @return the year
	 */
	public String getYear() {
		return year;
	}




	/**
	 * @param year the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}




	/**
	 * @return the month
	 */
	public String getMonth() {
		return month;
	}




	/**
	 * @param month the month to set
	 */
	public void setMonth(String month) {
		this.month = month;
	}




	/**
	 * @return the day
	 */
	public String getDay() {
		return day;
	}




	/**
	 * @param day the day to set
	 */
	public void setDay(String day) {
		this.day = day;
	}




	/**
	 * @return the startTm
	 */
	public String getStartTm() {
		return startTm;
	}




	/**
	 * @param startTm the startTm to set
	 */
	public void setStartTm(String startTm) {
		this.startTm = startTm;
	}




	/**
	 * @return the endTm
	 */
	public String getEndTm() {
		return endTm;
	}




	/**
	 * @param endTm the endTm to set
	 */
	public void setEndTm(String endTm) {
		this.endTm = endTm;
	}




	/**
	 * @return the startYear
	 */
	public String getStartYear() {
		return startYear;
	}




	/**
	 * @param startYear the startYear to set
	 */
	public void setStartYear(String startYear) {
		this.startYear = startYear;
	}




	/**
	 * @return the startMonth
	 */
	public String getStartMonth() {
		return startMonth;
	}




	/**
	 * @param startMonth the startMonth to set
	 */
	public void setStartMonth(String startMonth) {
		this.startMonth = startMonth;
	}




	/**
	 * @return the startDay
	 */
	public String getStartDay() {
		return startDay;
	}




	/**
	 * @param startDay the startDay to set
	 */
	public void setStartDay(String startDay) {
		this.startDay = startDay;
	}




	/**
	 * @return the endYear
	 */
	public String getEndYear() {
		return endYear;
	}




	/**
	 * @param endYear the endYear to set
	 */
	public void setEndYear(String endYear) {
		this.endYear = endYear;
	}




	/**
	 * @return the endMonth
	 */
	public String getEndMonth() {
		return endMonth;
	}




	/**
	 * @param endMonth the endMonth to set
	 */
	public void setEndMonth(String endMonth) {
		this.endMonth = endMonth;
	}




	/**
	 * @return the endDay
	 */
	public String getEndDay() {
		return endDay;
	}




	/**
	 * @param endDay the endDay to set
	 */
	public void setEndDay(String endDay) {
		this.endDay = endDay;
	}




	/**
	 * @return the startHour
	 */
	public String getStartHour() {
		return startHour;
	}




	/**
	 * @param startHour the startHour to set
	 */
	public void setStartHour(String startHour) {
		this.startHour = startHour;
	}




	/**
	 * @return the startMinute
	 */
	public String getStartMinute() {
		return startMinute;
	}




	/**
	 * @param startMinute the startMinute to set
	 */
	public void setStartMinute(String startMinute) {
		this.startMinute = startMinute;
	}




	/**
	 * @return the endHour
	 */
	public String getEndHour() {
		return endHour;
	}




	/**
	 * @param endHour the endHour to set
	 */
	public void setEndHour(String endHour) {
		this.endHour = endHour;
	}




	/**
	 * @return the endMinute
	 */
	public String getEndMinute() {
		return endMinute;
	}




	/**
	 * @param endMinute the endMinute to set
	 */
	public void setEndMinute(String endMinute) {
		this.endMinute = endMinute;
	}




	/**
	 * @return the dDay
	 */
	public String getdDay() {
		return dDay;
	}




	/**
	 * @param dDay the dDay to set
	 */
	public void setdDay(String dDay) {
		this.dDay = dDay;
	}




	/**
	 * @return the tDay
	 */
	public String gettDay() {
		return tDay;
	}




	/**
	 * @param tDay the tDay to set
	 */
	public void settDay(String tDay) {
		this.tDay = tDay;
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




	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}




	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}


}