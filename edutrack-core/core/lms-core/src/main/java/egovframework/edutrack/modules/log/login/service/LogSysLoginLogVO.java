package egovframework.edutrack.modules.log.login.service;

import egovframework.edutrack.comm.service.DefaultVO;

public class LogSysLoginLogVO extends DefaultVO {

	private static final long serialVersionUID = 2943666798074367270L;

	private String  orgCd;
	private String	year;
	private String	month;
	private String	day;
	private String	tm;
	private int		loginCnt;

	private String	codeNm;
	private String	startDt;
	private String	endDt;
	private String	viewType;
	private String	dateType;


	public String getOrgCd() {
		return orgCd;
	}

	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}

	public String getDateType() {
		return this.dateType;
	}

	public void setDateType(String dateType) {
		this.dateType = dateType;
	}
	
	public String getViewType() {
		return this.viewType;
	}

	public void setViewType(String viewType) {
		this.viewType = viewType;
	}

	public String getCodeNm() {
		return this.codeNm;
	}

	public void setCodeNm(String codeNm) {
		this.codeNm = codeNm;
	}

	public String getStartDt() {
		return this.startDt;
	}

	public void setStartDt(String startDt) {
		this.startDt = startDt;
	}

	public String getEndDt() {
		return this.endDt;
	}

	public void setEndDt(String endDt) {
		this.endDt = endDt;
	}

	public String getYear() {
		return this.year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return this.month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return this.day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getTm() {
		return this.tm;
	}

	public void setTm(String tm) {
		this.tm = tm;
	}

	public int getLoginCnt() {
		return this.loginCnt;
	}

	public void setLoginCnt(int loginCnt) {
		this.loginCnt = loginCnt;
	}
}
